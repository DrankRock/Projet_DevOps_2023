package fr.uga.erods.projectDevOps;

public class DataFrame {
	
	InitializeDataFrame dataFrameInit;
	String[][] dataFrame;
	public DataFrame(String csvFile) {
		dataFrameInit = new InitializeDataFrame();
		dataFrame = dataFrameInit.DataFrameTab(csvFile);
	}
	
	public DataFrame(String[] array) {
		
	}
	
	public DataFrame(String[][] array) {
		
	}
	
	public void printFirstLine(int numberOfLine) throws Exception {
		
		if(numberOfLine < this.dataFrame.length) {
			for(int i = 0; i < numberOfLine+1;i++) {
				for(int j = 0; j < this.dataFrame[i].length;j++) {	
					System.out.print(this.dataFrame[i][j]+" ");
					
				}
				System.out.println("");
			}
		} else {
			throw new Exception("Out of bound exception"); 
		}
	}
	
	public void printLastLine(int numberOfLine) throws Exception {
		for(int j = 0; j < this.dataFrame[0].length;j++) {	
			System.out.print(this.dataFrame[0][j]+" ");
		}
		System.out.println("");
		if(numberOfLine < this.dataFrame.length) {
			for(int i = this.dataFrame.length - numberOfLine; i < this.dataFrame.length;i++) {
				for(int j = 0; j < this.dataFrame[i].length;j++) {	
					System.out.print(this.dataFrame[i][j]+" ");
					
				}
				System.out.println("");
			}
		} else {
			throw new Exception("Out of bound exception"); 
		}
	}
	
	
	public void printDataframe()
    {
		for(int i = 0; i < this.dataFrame.length;i++) {
			for(int j = 0; j < this.dataFrame[i].length;j++) {	
				System.out.print(this.dataFrame[i][j]+" ");
				
			}
			System.out.println("");
		}
		
	
    }
}