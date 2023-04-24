package fr.uga.erods.projectDevOps;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class DataFrame {
    private List<String[]> data;
    private String[] headers;

    /*
     * Class constructor
     * Use for CSV file
     * 
     * 
     * ADD CONDITION IF FILENAME IS NOT GOOD
     * 
     * 
     */
    
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
    
    /*
     * 2nd Class Constructor
     * variable headers = the name for the main cell 
     * variable data = data for each main cell
     */
    public DataFrame(String[] headers, List<String[]> data) {
        this.headers = headers;
        this.data = data;
    }
    
    /*
     * We make the value of DataBase into a String variable
     * in a second time we create the function "display" just for
     * print the values
     */
    
    public String displayString() {
        StringBuilder output = new StringBuilder();
        for (String header : headers) {
            output.append(header).append("\t");
        }
        output.append("\n");
        for (String[] row : data) {
            for (String cell : row) {
                output.append(cell).append("\t");
            }
            output.append("\n");
        }
        return output.toString();
    }
    
    /*
     * We make the n first lines of DataBase into a String variable
     * ADD CONDITION IF n > NumberOfLine
     *
     */
    
	public boolean equals(DataFrame obj) {
		boolean cond = false;
		if(this.displayString().equals(obj.displayString())) {
			cond = true;
		}
		return cond;
	}

	private int getColumnIndex(String columnLabel) {
        for (int i = 0; i < this.headers.length; i++) {
            if (this.headers[i].equals(columnLabel)) {
                return i;
            }
        }
        return -1;
    }
    
    public String head(int n) {
        StringBuilder output = new StringBuilder();
        for (String header : headers) {
            output.append(header).append("\t");
        }
        output.append("\n");
        for (int i = 0; i < n && i < data.size(); i++) {
            String[] row = data.get(i);
            for (String cell : row) {
                output.append(cell).append("\t");
            }
            output.append("\n");
        }
        return output.toString();
    }
    
    /*
     * We make the n last lines of DataBase into a String variable
     * 
     * ADD CONDITION IF n > NumberOfLine
     * 
     * 
     */    

    public String tail(int n) {
        StringBuilder output = new StringBuilder();

        for (String header : headers) {
            output.append(header).append("\t");
        }
        output.append("\n");
        int start = Math.max(0, data.size() - n);
        for (int i=start; i < data.size(); i++) {
            String[] row = data.get(i);
            for (String cell:row) {
                output.append(cell).append("\t");
            }
            output.append("\n");
        }
        return output.toString();
    }

    /*
     * Select rows with index write in integer array
     * For example, array(4,4) we can do : int[} i = {2,3} and we get
     * just two lines
     * 
     * ADD ILLEGAL CONDITION
     */
    
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
    
    /*
     * Select columns with String label write in String array
     * For example, array(4,4) we can do : String[] s = {"bob","rex"} and we get
     * just two columns on 4
     * 
     * ADD ILLEGAL CONDITION
     */
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

    
    /*
     * Use previous functions for select rows and columns in the same time
     */
    public DataFrame loc(String[] ColumnsLabels, int[] rowIndices) {
    	return this.selectRows(rowIndices).selectColumns(ColumnsLabels);
    }


    /*
     * If we give columnName with numeric data, this method
     * return the mean of them
     */
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

    /*
     * If we give columnName with numeric data inside, this method
     * return the max of them
     * ILLEGAL CONDITION
     */
    public double max(String columnName) throws NumberFormatException {
        int columnIndex = getColumnIndex(columnName);
        double maxValue = Double.NaN;
        for (String[] row : this.data) {
            try {
                double value = Double.parseDouble(row[columnIndex]);
                if (Double.isNaN(maxValue) || value > maxValue) {
                    maxValue = value;
                }
            } catch (NumberFormatException e) {
                throw new NumberFormatException("Not all values in column '" + columnName + "' are numeric.");
            }
        }
        return maxValue;
    }

    /*
     * If we give columnName with numeric data inside, this method
     * return the min of them
     * Illegual !!!!!
     */
    
    public double min(String columnName)  throws NumberFormatException {
        int columnIndex = getColumnIndex(columnName);
        double minValue = Double.NaN;
        for (String[] row : this.data) {
            try {
                double value = Double.parseDouble(row[columnIndex]);
                if (Double.isNaN(minValue) || value < minValue) {
                	minValue = value;
                }
            } catch (NumberFormatException e) {
                throw new NumberFormatException("Not all values in column '" + columnName + "' are numeric.");
            }
        }
        return minValue;
    }
}