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

    /**
     * Constructs a DataFrame from the csv file given in input
     * @param filename the path to the file
     * @throws FileNotFoundException if the file does not exist
     */
    public DataFrame(String filename) throws FileNotFoundException {
        File file = new File(filename);
    	if(file.exists()==false) {
    		throw new FileNotFoundException("Le fichier n'existe pas: " + filename);
    	}
	        Scanner scanner = new Scanner(file);
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
    
    /**
     * Constructs a dataframe from the names of the columns and the data as Strings
     * @param headers the name of the columns
     * @param data the content of the Dataframe
     */
    public DataFrame(String[] headers, List<String[]> data) {
        this.headers = headers;
        this.data = data;
    }
    

    /**
     * Convert the Dataframe into a String and returns it
     * @return the Dataframe as a String
     */
    public String toString() {
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
    

    /**
     * Compare a dataframe with this, returns true if the content and headers is the same
     * @param obj the DataFrame to compare
     * @return true if they are equals
     */
	public boolean equals(DataFrame obj) {
		boolean cond = false;
		if(this.toString().equals(obj.toString())) {
			cond = true;
		}
		return cond;
	}

	/**
	 * Get the index of the column given by its name
	 * @param columnLabel the name of the column
	 * @return the index of the column, or -1 if it's not found
	 */
	public int getColumnIndex(String columnLabel) {
        for (int i = 0; i < this.headers.length; i++) {
            if (this.headers[i].equals(columnLabel)) {
                return i;
            }
        }
        return -1;
    }
    
	/**
	 * Return the first n rows of the DataFrame
	 * @param n the number of rows
	 * @return a String containing the first n rows
	 */
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
    
    /**
	 * Return the last n rows of the DataFrame
	 * @param n the number of rows
	 * @return a String containing the last n rows
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

    /**
     * Select rows with index write in integer array
     * i.e: array(4,4) we can do : int[] i = {2,3} and we get
     * just two lines
     * 
     * @param rowIndices indexes of the rows to select
     * @return a new Dataframe containing only these rows
     */
    public DataFrame selectRows(int[] rowIndices) {
        String[] newHeaders = Arrays.copyOf(this.headers, this.headers.length);

        List<String[]> newData = new ArrayList<>();
        for (int rowIndex : rowIndices) {
            if (rowIndex >= 0 && rowIndex < this.data.size()) {
                newData.add(Arrays.copyOf(this.data.get(rowIndex), this.headers.length));
            } else {
            	throw new IndexOutOfBoundsException();
            }
        }
        return new DataFrame(newHeaders, newData);
    }

    /**
     * Select columns with String label write in String array
     * For example, array(4,4) we can do : String[] s = {"bob","rex"} and we get
     * just two columns on 4
     * 
     * @param columnLabels labels of the columns to select
     * @return a new Dataframe containing only these columns
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

    
    /**
     * Select specific rows and columns and returns a new Dataframe containing only the intersection between these.
     * @param ColumnsLabels the labels of the columns to select
     * @param rowIndices the indexes of the rows to select
     * @return a new Dataframe, the intersection of the selection
     */
    public DataFrame loc(String[] ColumnsLabels, int[] rowIndices) {
    	return this.selectRows(rowIndices).selectColumns(ColumnsLabels);
    }


    /**
     * Get the mean of the values of a column (This only works if the column contains ONLY numbers )
     * @param columnName the name of the column
     * @return the mean value
     */
    public double mean(String columnLabel) {
        int columnIndex = getColumnIndex(columnLabel);
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
            throw new IllegalArgumentException("Pas de valeurs numériques dans la colonne " + columnLabel);
        }
        return sum / count;
    }

    /**
     * Get the maximum value of a column that contains only numbers
     * @param columnName the name of the column 
     * @return the maximum of the column as a double
     * @throws NumberFormatException
     */
    public double max(String columnLabel) throws NumberFormatException {
        int columnIndex = getColumnIndex(columnLabel);
        double maxValue = Double.NaN;
        for (String[] row : this.data) {
            try {
                double value = Double.parseDouble(row[columnIndex]);
                if (Double.isNaN(maxValue) || value > maxValue) {
                    maxValue = value;
                }
            } catch (NumberFormatException e) {
                throw new NumberFormatException("Not all values in column '" + columnLabel + "' are numeric.");
            }
        }
        return maxValue;
    }

    /**
     * Get the minimum value of a column containing only numbers
     * @param columnName the name of the column
     * @return the min of the column as a double
     * @throws NumberFormatException
     */
    public double min(String columnLabel)  throws NumberFormatException {
        int columnIndex = getColumnIndex(columnLabel);
        double minValue = Double.NaN;
        for (String[] row : this.data) {
            try {
                double value = Double.parseDouble(row[columnIndex]);
                if (Double.isNaN(minValue) || value < minValue) {
                	minValue = value;
                }
            } catch (NumberFormatException e) {
                throw new NumberFormatException("Not all values in column '" + columnLabel + "' are numeric.");
            }
        }
        return minValue;
    }
    
    public List<String> findDifferentValuesInColumn(String columnLabel) {
    	List<String> values = new ArrayList<>();
    	String currentValue;
    	for (String[] row : this.data) {
    		currentValue = row[getColumnIndex(columnLabel)];
			if(!values.contains(currentValue)) {
				values.add(currentValue);
			}
		}
		return values;
    }
    
    public DataFrame sort(String columnLabel) throws Exception {
    	List<String[]> newData = new ArrayList<>();
    	if(getColumnIndex(columnLabel) == -1) { 
    		throw new Exception();
    	} else {
    		List<String> values = this.findDifferentValuesInColumn(columnLabel);
    		String currentValue;
    		for(String value : values) {
	    		for (String[] row : this.data) {
	    			currentValue = row[getColumnIndex(columnLabel)];
	    			if(currentValue == value) {
	    				newData.add(row);
	    			}
	    		}
    		}
    		
    		
    	}
    	return new DataFrame(this.headers,newData);
    }
    
    public DataFrame groupBy(String columnLabel) throws Exception {
    	if(this.headers.length<1 || getColumnIndex(columnLabel) == -1 ) { 
    		throw new Exception();
    	} else {
	    	String[] newHeaders = new String[this.headers.length];
	    	newHeaders[0] = columnLabel;
	    	for(int i = 1;i<this.headers.length;i++) {
	    		if(i <= getColumnIndex(columnLabel)) {
	    			newHeaders[i]=this.headers[i-1];
	    		}
	    		else{
	    			newHeaders[i] = this.headers[i];	    	
	    		}
	    }
	    List<String[]> newData = new ArrayList<>();
	    for (String[] row : this.data) {
	    	
	    }
    	
    	/*int columnIndex = getColumnIndex(columnName);
    	for (String[] row : this.data) {
    		
    	}*/
    	
       }
		return null;
    }
}
















