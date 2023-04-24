package fr.uga.erods.projectDevOps;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception
    {
        DataFrame test = new DataFrame("fichierCSV.csv");
        test.printDataframe();
        System.out.println("");
        
        int[] index = {0,2,5};
        String[][] dL = test.selectLineDataframe(index);
        for(int i = 0; i < dL.length;i++) {
			for(int j = 0; j < dL[i].length;j++) {	
				System.out.print(dL[i][j]+" ");
				
			}
			System.out.println("");
		}
        System.out.println("");
        
        String[] dC = test.selectColumnDataframe("METIER");
        for(int i = 0; i < dC.length;i++) {	
			System.out.println(dC[i]);	
		}

    }
}
