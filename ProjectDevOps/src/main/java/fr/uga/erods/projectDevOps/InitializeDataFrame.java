package fr.uga.erods.projectDevOps;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class InitializeDataFrame {

	
	public StringBuffer readFile(String s) {
		StringBuffer sb;
		try{
			// Le fichier d'entrée
		    File file = new File(s);    
		    // Créer l'objet File Reader
  	        FileReader fr = new FileReader(file);  
	        // Créer l'objet BufferedReader        
	        BufferedReader br = new BufferedReader(fr);  
	        sb = new StringBuffer();    
	        String line;
	        while((line = br.readLine()) != null){
	        	StringBuffer texte = sb.append(line);
	        	sb.append("\n");
	        }
	        fr.close();    
	    }
	    catch(IOException e)
	    {
	      sb = new StringBuffer("Erreur");
	      e.printStackTrace();
	    }
		return sb;
	}
	
	public int getLineNumber(String csvFile) {
		
		StringBuffer sb = readFile(csvFile);
		int j = 0;
		for (int i = 0; i < sb.length(); i++) {
			if (sb.charAt(i) == '\n') {
		    	j++;
		    }
		}
		
		return j;
	}
	
	public int getColumnNumber(String csvFile) {
		
		StringBuffer sb = readFile(csvFile);
		int j = 0;
		boolean isQuote = false;
		int i = 0;
		while(sb.charAt(i) != '\n') {
			
			if(sb.charAt(i) == '"' && !isQuote) {
				isQuote = true;
			}
			else if(sb.charAt(i) == '"' && isQuote) {
				isQuote = false;
			}
			
			
			if (sb.charAt(i) == ',' && !isQuote) {
		    	j++;
		    }
			i++;
		}
		
		return j+1;
	}
	
	public String[][] DataFrameTab(String csvFile){
		
		StringBuffer sb = readFile(csvFile); 
		int columnNumber = getColumnNumber(csvFile);
		int lineNumber = getLineNumber(csvFile);
		String[][] tab2D = new String[lineNumber][columnNumber];
		int j=0; int k = 0;
		int commaIndex = 0;
		int length = sb.length();

		for (int i = 0; i < length; i++) {
			
		    
		    if(i == length-1) {
		    	tab2D[k][j]=sb.substring(commaIndex, i);
		    } else if (sb.charAt(i) == ',') {
		    	tab2D[k][j]=sb.substring(commaIndex, i);
		    	j++;
		    	commaIndex = i+1;
		    }
		    
		    
		    if (sb.charAt(i) == '\n') {
		    	tab2D[k][j]=sb.substring(commaIndex, i);
		    	commaIndex = i+1;
		    	k++;
		    	j = 0;
		    }
		    
		}
		return tab2D;
	}
}
