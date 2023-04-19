package fr.uga.erods.projectDevOps;

public class DataFrame {

	
	public static void main( String[] args )
    {
		InitializeDataFrame test = new InitializeDataFrame();
		String[][] tabTest = test.DataFrameTab("fichierCSV.csv");
		for(int i = 0; i < tabTest.length;i++) {
			for(int j = 0; j < tabTest[i].length;j++) {	
				System.out.print(tabTest[i][j]+" ");
				
			}
			System.out.println("");
		}
		
	
    }
}