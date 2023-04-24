package fr.uga.erods.projectDevOps;

import java.io.FileNotFoundException;

public class App 
{
	public static void main(String[] args) {
	    try {
	        DataFrame df = new DataFrame("fichierCSV.csv");
	        df.display();
	        System.out.println();
	        
	        df.displayFirstNRows(5);
	        System.out.println();
	        
	        df.displayLastNRows(4);
	        System.out.println();
	        
	        int[] rowIndices = {0, 2, 4};
	        DataFrame dfb = df.selectRows(rowIndices);
	        dfb.display();
	        System.out.println();
	        
	        String[] columnLabels = {"METIER", "GENRE"};
	        DataFrame newDf = df.selectColumns(columnLabels);
	        newDf.display();
	        System.out.println();
	        
	        String[] rowLabels = {"2", "7"};
	        DataFrame locDf = df.loc(rowLabels, columnLabels);
	        locDf.display();
	        System.out.println();
	        
	        
	        System.out.println(df.mean("AGE"));
	        System.out.println();
	        
	        System.out.println(dfb.min("AGE"));
	        System.out.println();
	        
	        System.out.println(df.max("AGE"));
	        System.out.println();
	        
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    }
	}
}
