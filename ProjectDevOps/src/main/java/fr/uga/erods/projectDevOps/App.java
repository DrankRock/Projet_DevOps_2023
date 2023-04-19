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
    }
}
