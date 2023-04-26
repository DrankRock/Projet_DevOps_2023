package fr.uga.erods.projectDevOps;

import java.io.File;
import java.io.FileNotFoundException;

public class Demo {
	String mode = "[None]";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Demo d = new Demo();
		String filePath = "src/main/java/fr/uga/erods/projectDevOps/demo.csv";
		String mode = "[initialize";
		d.p("Create Original DataFrame");
		try {
			
			DataFrame df = new DataFrame(filePath);
			d.dPrint("[initialize] - DataFrame created", 1);
			d.dPrint("[Basic Operation] - Display DataFrame", 0);
			d.dPrint(df.toString(), 0);
			d.dPrint(, 0);
			
			
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			d.dPrint("[initialize] - Error : File not found ", 3);
			System.exit(1);
		}
		
		
		
	}
	
	public void p(String s) {
		System.out.println(s);
	}
	
	public void dPrint(String toPrint, int err) {
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
	
	public void setMode(String s) {
		this.mode = "["+s+"]";
	}

}
