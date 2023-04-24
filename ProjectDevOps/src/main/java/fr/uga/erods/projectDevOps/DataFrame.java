package fr.uga.erods.projectDevOps;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class DataFrame {
    private List<String[]> data;
    private String[] headers;

    public DataFrame(String filename) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filename));
        data = new ArrayList<>();

        if (scanner.hasNextLine()) {
            headers = scanner.nextLine().split(",");
        }

        while (scanner.hasNextLine()) {
            String[] row = scanner.nextLine().split(",");
            data.add(row);
        }
        scanner.close();
    }
    
    public DataFrame(String[] headers, List<String[]> data) {
        this.headers = headers;
        this.data = data;
    }

    public void display() {

        for (String header : headers) {
            System.out.print(header + "\t");
        }
        System.out.println();

        for (String[] row : data) {
            for (String cell : row) {
                System.out.print(cell + "\t");
            }
            System.out.println();
        }
    }
    
    public void displayFirstNRows(int n) {

        for (String header : headers) {
            System.out.print(header + "\t");
        }
        System.out.println();

        for (int i = 0; i < n && i < data.size(); i++) {
            String[] row = data.get(i);
            for (String cell : row) {
                System.out.print(cell + "\t");
            }
            System.out.println();
        }
    }

    public void displayLastNRows(int n) {
    	
        for (String header : headers) {
            System.out.print(header + "\t");
        }
        System.out.println();

        int start = Math.max(0, data.size() - n);
        for (int i = start; i < data.size(); i++) {
            String[] row = data.get(i);
            for (String cell : row) {
                System.out.print(cell + "\t");
            }
            System.out.println();
        }
    }
    
    public DataFrame selectRows(int[] rowIndices) {
        String[] newHeaders = Arrays.copyOf(this.headers, this.headers.length);

        List<String[]> newData = new ArrayList<>();
        for (int rowIndex : rowIndices) {
            if (rowIndex >= 0 && rowIndex < this.data.size()) {
                newData.add(Arrays.copyOf(this.data.get(rowIndex), this.headers.length));
            }
        }

        return new DataFrame(newHeaders, newData);
    }
    
    private int getColumnIndex(String columnLabel) {
        for (int i = 0; i < this.headers.length; i++) {
            if (this.headers[i].equals(columnLabel)) {
                return i;
            }
        }
        return -1;
    }
    
    public DataFrame selectColumns(String[] columnLabels) {
        String[] newHeaders = Arrays.copyOf(columnLabels, columnLabels.length);

        List<String[]> newData = new ArrayList<>();
        for (String[] row : this.data) {
            String[] newRow = new String[columnLabels.length];
            int columnIndex = 0;
            for (String header : columnLabels) {
                int index = getColumnIndex(header);
                if (index != -1) {
                    newRow[columnIndex] = row[index];
                }
                columnIndex++;
            }
            newData.add(newRow);
        }

        return new DataFrame(newHeaders, newData);
    }
    
    public DataFrame loc(String[] rowLabels, String[] columnLabels) {
        int[] rowIndexes = new int[rowLabels.length];
        int[] columnIndexes = new int[columnLabels.length];
        for (int i = 0; i < rowLabels.length; i++) {
            rowIndexes[i] = getRowIndex(rowLabels[i]);
        }
        for (int i = 0; i < columnLabels.length; i++) {
            columnIndexes[i] = getColumnIndex(columnLabels[i]);
        }
        String[] newHeaders = Arrays.copyOf(columnLabels, columnLabels.length);
        List<String[]> newData = new ArrayList<>();
        for (int rowIndex : rowIndexes) {
            String[] row = this.data.get(rowIndex);
            String[] newRow = new String[columnLabels.length];
            for (int i = 0; i < columnIndexes.length; i++) {
                newRow[i] = row[columnIndexes[i]];
            }
            newData.add(newRow);
        }
        return new DataFrame(newHeaders, newData);
    }

    private int getRowIndex(String rowLabel) {
        for (int i = 0; i < this.data.size(); i++) {
            String[] row = this.data.get(i);
            if (row[0].equals(rowLabel)) {
                return i;
            }
        }
        return -1;
    }
    
    public double mean(String columnName) {
        int columnIndex = getColumnIndex(columnName);
        double sum = 0.0;
        int count = 0;
        for (String[] row : this.data) {
            try {
                double value = Double.parseDouble(row[columnIndex]);
                sum += value;
                count++;
            } catch (NumberFormatException e) {
            	
            }
        }
        if (count == 0) {
            throw new IllegalArgumentException("Pas de valeurs numériques dans la colonne " + columnName);
        }
        return sum / count;
    }

    public String max(String columnName) {
        int columnIndex = getColumnIndex(columnName);
        String maxValue = null;
        for (String[] row : this.data) {
            if (maxValue == null || row[columnIndex].compareTo(maxValue) > 0) {
                maxValue = row[columnIndex];
            }
        }
        return maxValue;
    }

    public String min(String columnName) {
        int columnIndex = getColumnIndex(columnName);
        String minValue = null;
        for (String[] row : this.data) {
            if (minValue == null || row[columnIndex].compareTo(minValue) < 0) {
                minValue = row[columnIndex];
            }
        }
        return minValue;
    }

}