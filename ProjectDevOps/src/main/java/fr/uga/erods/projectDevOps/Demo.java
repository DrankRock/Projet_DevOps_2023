package fr.uga.erods.projectDevOps;

import java.io.File;
import java.io.FileNotFoundException;

public class Demo {
	String mode = "[None]";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Demo d = new Demo();
		String filePath = args[0];
		d.setMode("initialize");
		d.p("Create Original DataFrame");
		try {
			
			DataFrame df = new DataFrame(filePath);
			d.p("DataFrame created", 1);
			d.setMode("Basic Operations");
			d.p("Display DataFrame", df.toString());
			
			d.p("Head of 3 rows\n", df.head(3).toString());
			d.p("Tail of 3 rows\n", df.tail(3).toString());
			
			d.p("Select rows 1, 3, 4\n", df.selectRows(new int[] {1, 3, 4}).toString());
			d.p("Select columns NAME and USERNAME",df.selectColumns(new String[] {"NAME", "USERNAME"}).toString());
			
			d.setMode("Advanced Operations");
			
			d.p("Select columns NAME and USERNAME, rows 2, 5, 7\n", df.loc(new String[] {"NAME", "USERNAME"}, new int[] {2, 5, 7}).toString());
			
			d.p("Reminder : this is the Age column", df.selectColumns("AGE").toString());
			d.p("Calculate the mean of the age column", Double.toString(df.mean("AGE")));
			d.p("Calculate the min of the age column", Double.toString(df.min("AGE")));
			d.p("Calculate the max of the age column", Double.toString(df.max("AGE")));
			
			
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			d.setMode("Error");
			d.p("File not found exception", 3);
			System.exit(1);
		}	
	}
	
	
	/**
	 * Colored print, with success, warning and error modes
	 * @param toPrint the string to print
	 * @param err the color, 1 for green, 2 for yellow, 3 for red, anything else in white
	 */
	public void p(String toPrint, int err) {
		String code = "";
	    if (err == 3) {
	        code = "\u001B[31m"; // red
	    } else if (err == 2) {  
	        code = "\u001B[33m"; // yellow
	    } else if (err == 1) {
	        code = "\033[1;32m"; // green
	    }
	    System.out.println(code + mode + toPrint + "\u001B[0m");	
	}
	
	/**
	 * Colored print, with success, warning and error modes
	 * @param toPrint the string to print
	 * @param err the color, 1 for green, 2 for yellow, 3 for red, anything else in white
	 */
	public void p(String title, String toPrint, int err) {
		String code = "";
	    if (err == 3) {
	        code = "\u001B[31m"; // red
	    } else if (err == 2) {  
	        code = "\u001B[33m"; // yellow
	    } else if (err == 1) {
	        code = "\033[1;32m"; // green
	    }
	    System.out.println(code + mode + title + "\u001B[0m" + "\n" + toPrint);	
	}
	
	public void p(String s, String s2) {
		this.p(s, s2, 2);
	}
	
	/**
	 * Print a regular message
	 * @param s message to print
	 */
	public void p(String s) {
		this.p(s, 2);
	}
	
	/**
	 * Set the mode of the Demo Printer, which will be displayed as "[mode] - ..." before each text
	 * @param s the Mode
	 */
	public void setMode(String s) {
		this.mode = "["+s+"] - ";
	}

}
