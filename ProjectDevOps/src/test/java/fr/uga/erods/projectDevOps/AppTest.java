package fr.uga.erods.projectDevOps;


import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertThrows;
//import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Unit test for simple App.
 */
public class AppTest 
{

	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();
	
	/*
	 * Test the first constructor with .csv and try to compare two String
	 */
	@Test
    public void testDataFrameConstructor() throws FileNotFoundException {
        String filename = "src/test/java/fr/uga/erods/projectDevOps/fichierCSV.csv";
        File f = new File(filename);
        assert(f.exists());
        DataFrame df = new DataFrame(filename);
        
        assertEquals("ID	METIER	AGE	GENRE	\n"
        		+ "",df.head(0));
    }
	
	/*
	 * Test the first constructor with .csv and try to compare two String
	 */
	@Test(expected = FileNotFoundException.class)
    public void testDataFrameConstructorFail() throws FileNotFoundException {
        String filename = "erreur.csv";
        DataFrame dF = new DataFrame(filename);
    }
	
	
	
	
	
	
	/*
	 * Test the second constructor
	 * Two arguments : String[] headers, List<String[]> data
	 * And test the function 'head' 
	 * We need to do another test for the illegal exception
	 */
	
	@Test
	public void head(){
		String[] top = {"Nom","Prenom"};
		List<String[]> data = new ArrayList<>();
	    data.add(new String[]{"John", "Doe"});
	    data.add(new String[]{"Jane", "Doe"});
	    data.add(new String[]{"Bob", "Smith"});
	    DataFrame df = new DataFrame(top,data);
	    assertEquals("Nom	Prenom	\n"
	    		+ "John	Doe	\n"
	    		+ "",df.head(1));
	}
	
	/**
	 * Case in which one array is not full
	 */
	@Test
	public void arrayNotFull(){
		String[] top = {"Nom","Prenom"};
		List<String[]> data = new ArrayList<>();
	    data.add(new String[]{"John"});
	    data.add(new String[]{"Jane", "Doe"});
	    data.add(new String[]{"Bob", "Smith"});
	    DataFrame df = new DataFrame(top,data);
	    assertEquals("Nom	Prenom	\n"
	    		+ "John	\n"
	    		+ "",df.head(1));
	}
	
	/**
	 * test the function 'tail' 
	 * We need to do another test for the illegal exception
	 */
	
	@Test
	public void tail(){
		String[] top = {"Nom","Prenom"};
		List<String[]> data = new ArrayList<>();
	    data.add(new String[]{"John", "Doe"});
	    data.add(new String[]{"Jane", "Doe"});
	    data.add(new String[]{"Bob", "Smith"});
	    DataFrame df = new DataFrame(top,data);
	    assertEquals("Nom	Prenom	\n"
	    		+ "Bob	Smith	\n"
	    		+ "",df.tail(1));
	}
	
	
	/**
	 * Test the function 'selectRows"
	 * we create a father DataFrame, and born a little son with them catacteritics
	 * Just for lines
	 */
	@Test
	public void selectRows() {
		String[] top = {"Nom","Prenom"};
		List<String[]> data = new ArrayList<>();
	    data.add(new String[]{"John", "Doe"});
	    data.add(new String[]{"Jane", "Doe"});
	    DataFrame father = new DataFrame(top,data);
	    int[] i = {0,1};
	    DataFrame son = father.selectRows(i);
	    assertEquals(true,father.equals(son));
	}
	/**
	 * IndexOutOfBoundsException
	 * the row selected doesn't exist
	 */
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void selectRowsError() {
		String[] top = {"Nom","Prenom"};
		List<String[]> data = new ArrayList<>();
	    data.add(new String[]{"John", "Doe"});
	    data.add(new String[]{"Jane", "Doe"});
	    DataFrame father = new DataFrame(top,data);
	    int[] i = {0,4};
	    DataFrame son = father.selectRows(i);
	   
	}
	
	/**
	 * IndexOutOfBoundsException
	 * the row selected doesn't exist and she is the value is negative
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public void selectRowsError2() {
		String[] top = {"Nom","Prenom"};
		List<String[]> data = new ArrayList<>();
	    data.add(new String[]{"John", "Doe"});
	    data.add(new String[]{"Jane", "Doe"});
	    DataFrame father = new DataFrame(top,data);
	    int[] i = {0,-1};
	    DataFrame son = father.selectRows(i);
	}
	
	
	/**
	 * Test the function 'selectColumns"
	 * we create a father DataFrame, and born a little son with them catacteritics
	 * Just for the columns
	 */
	@Test
	public void selectColumns() {
		String[] top = {"Nom","Prenom"};
		List<String[]> data = new ArrayList<>();
	    data.add(new String[]{"John", "Doe"});
	    data.add(new String[]{"Jane", "Doe"});
	    DataFrame father = new DataFrame(top,data);
	    String[] s = {"Nom","Prenom"};
	    DataFrame son = father.selectColumns(s);
	    assertEquals(true,father.equals(son));
	}
	
	/**
	 * we create a father DataFrame, and born a little son with them catacteritics
	 * but, we writed a bad name for one column
	 */
	@Test
	public void selectColumnsError() {
		String[] top = {"Nom","Prenom"};
		List<String[]> data = new ArrayList<>();
	    data.add(new String[]{"John", "Doe"});
	    data.add(new String[]{"Jane", "Doe"});
	    DataFrame father = new DataFrame(top,data);
	    String[] s = {"Nom","PRe"};
	    DataFrame son = father.selectColumns(s);
	    assertEquals(false,father.equals(son));
	}
	
	/**
	 * Test the function 'loc"
	 * we create a father DataFrame, and born a little son with them catacteritics
	 * Both in the same time (columns and lines)
	 */
	@Test
	public void loc() {
		String[] top = {"Nom","Prenom"};
		List<String[]> data = new ArrayList<>();
	    data.add(new String[]{"John", "Doe"});
	    data.add(new String[]{"Jane", "Doe"});
	    DataFrame father = new DataFrame(top,data);
	    String[] s = {"Nom","Prenom"};
	    int[] i = {0,1};
	    DataFrame son = father.loc(s, i);
	    assertEquals(true,father.equals(son));
	}
	
	/**
	 * Test the function 'mean"
	 * calculate mean in column
	 */
	@Test
	public void mean() {
	    String[] top = {"Nom","Prenom","age"};
	    List<String[]> data = new ArrayList<>();
	    data.add(new String[]{"John", "Doe","34"});
	    data.add(new String[]{"Jane", "Doe","24"});
	    DataFrame father = new DataFrame(top,data);
	    double expected = 29.0;
	    double actual = father.mean("age");
	    double delta = 0.0001; // écart entre nos valeurs
	    assertEquals(expected, actual, delta);
	}

	
	/**
	 * Test the function 'mean"
	 * calculate mean in column
	 * with error, we take case in column in which one we don't have any values.
	 */
	@Test
	public void meanError() throws Exception{
		String[] top = {"Nom","Prenom","age"};
		List<String[]> data = new ArrayList<>();
	    data.add(new String[]{"John", "Doe","34"});
	    data.add(new String[]{"Jane", "Doe","24"});
	    DataFrame father = new DataFrame(top,data);
	    
	    exceptionRule.expect(IllegalArgumentException.class);
	    
	    father.mean("Prenom");
	}
	
	/**
	 * Test the function 'max"
	 * calculate max in column
	 */
	@Test
	public void max() {
		String[] top = {"Nom","Prenom","age"};
		List<String[]> data = new ArrayList<>();
	    data.add(new String[]{"John", "Doe","34"});
	    data.add(new String[]{"Jane", "Doe","24"});
	    DataFrame father = new DataFrame(top,data);
	    double delta = 0.0001; // écart entre nos valeurs
	    assertEquals(34.0,father.max("age"), delta);
	}	

	/**
	 * Test the function 'max"
	 * calculate max in column
	 * with error, we take case in column in which one we don't have any values.
	 */
	@Test
	public void maxError() {
		String[] top = {"Nom","Prenom","age"};
		List<String[]> data = new ArrayList<>();
	    data.add(new String[]{"John", "Doe","34"});
	    data.add(new String[]{"Jane", "Doe","24"});
	    DataFrame father = new DataFrame(top,data);
	    
	    exceptionRule.expect(NumberFormatException.class);
	    
	    assertEquals("34",father.max("Prenom"));
	    
	    //Exception exception = assertThrows(NumberFormatException.class, () -> {
	    //	assertEquals("34",father.max("Prenom"));
		//});  
	}
	
	/**
	 * The column selected doesn't exist
	 */
	@Test
	public void getColumnIndexError() {
		String[] top = {"Nom","Prenom","age"};
		List<String[]> data = new ArrayList<>();
	    data.add(new String[]{"John", "Doe","34"});
	    data.add(new String[]{"Jane", "Doe","24"});
	    DataFrame father = new DataFrame(top,data);
	    assertEquals(-1,father.getColumnIndex("ger"));
	}
	
	

	/**
	 * Test the function 'min"
	 * calculate min in column
	 */
	@Test
	public void min() {
		String[] top = {"Nom","Prenom","age"};
		List<String[]> data = new ArrayList<>();
	    data.add(new String[]{"John", "Doe","34"});
	    data.add(new String[]{"Jane", "Doe","24"});
	    DataFrame father = new DataFrame(top,data);
	    double delta = 0.0001; // écart entre nos valeurs
	    assertEquals(24.0,father.min("age"), delta);
	}
	
	/**
	 * The min return is not good
	 */
	@Test
	public void minError() {
		String[] top = {"Nom","Prenom","age"};
		List<String[]> data = new ArrayList<>();
	    data.add(new String[]{"John", "Doe","34"});
	    data.add(new String[]{"Jane", "Doe","24"});
	    DataFrame father = new DataFrame(top,data);
	    
	    exceptionRule.expect(NumberFormatException.class);
	    
	    assertEquals("34",father.min("Prenom"));
	}
	
	
	/**
	 * Test with negative input
	 */
	 @Test
	    public void testHeadWithNegativeInput() {
		 	String[] top = {"Header1", "Header2", "Header3"};
		 	List<String[]> data = new ArrayList<>();
		    data.add(new String[]{"Data11", "Data12", "Data13"});
		    data.add(new String[]{"Data21", "Data22", "Data23"});
		    data.add(new String[]{"Data31", "Data32", "Data33"});
		    DataFrame father = new DataFrame(top,data);
	        String expectedOutput = "Header1\tHeader2\tHeader3\t\n";
	        String actualOutput = father.head(-1);
	        assertEquals(expectedOutput, actualOutput);
	    }
	
	 /**
	  * Test with a bigger size in function head
	  */
	 @Test
	    public void testHeadWithMoreThanDataSizeInput() {
		 	String[] top = {"Header1", "Header2", "Header3"};
		 	List<String[]> data = new ArrayList<>();
		    data.add(new String[]{"Data11", "Data12", "Data13"});
		    data.add(new String[]{"Data21", "Data22", "Data23"});
		    data.add(new String[]{"Data31", "Data32", "Data33"});
		    DataFrame father = new DataFrame(top,data);
		 	String expectedOutput = "Header1\tHeader2\tHeader3\t\n" +
	                                "Data11\tData12\tData13\t\n" +
	                                "Data21\tData22\tData23\t\n" +
	                                "Data31\tData32\tData33\t\n";
	        String actualOutput = father.head(5);
	        assertEquals(expectedOutput, actualOutput);
	    }
	 
	 /**
	  * Simple example with groupBy
	  * Sort the data
	  */
	 @Test
	 public void groupBy() {
		 String[] top = {"Header1", "Header2", "Header3"};
		 	List<String[]> data = new ArrayList<>();
		    data.add(new String[]{"Data11", "Data12", "Data13"});
		    data.add(new String[]{"Data21", "Data22", "Data23"});
		    data.add(new String[]{"Data11", "Data32", "Data33"});
		    DataFrame df = new DataFrame(top,data);
		    DataFrame fils;
			try {
				fils = df.groupBy(new String[]{"Header1","Header2"});
				assertEquals("Header1	Header2	Header3	\n"
						+ "Data11	Data12	Data13	\n"
						+ "Data11	Data32	Data33	\n"
						+ "Data21	Data22	Data23	\n"
						+ "",fils.toString());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}   
	 }
	 
	 /**
	  * The value in groupBy("value1","value2") doesn't exist in the dataframe
	  */
	 @Test(expected = Exception.class)
	 public void groupByError() throws Exception{
		 String[] top = {"Header1", "Header2", "Header3"};
		 	List<String[]> data = new ArrayList<>();
		    data.add(new String[]{"Data11", "Data12", "Data13"});
		    data.add(new String[]{"Data21", "Data22", "Data23"});
		    data.add(new String[]{"Data11", "Data32", "Data33"});
		    DataFrame df = new DataFrame(top,data);
		    DataFrame fils;
		    fils = df.groupBy(new String[]{"error","Header2"});
	 }
	 
	 /**
	  * We add an error on the data return
	  */
	 @Test
	 public void groupByError2() {
		 String[] top = {"Header1", "Header2", "Header3"};
		 	List<String[]> data = new ArrayList<>();
		    data.add(new String[]{"Data11", "Data12", "Data13"});
		    data.add(new String[]{"Data21", "Data22", "Data23"});
		    data.add(new String[]{"Data11", "Data32", "Data33"});
		    DataFrame df = new DataFrame(top,data);
		    DataFrame fils;
			try {
				fils = df.groupBy(new String[]{"Header1","Header2"});
				assertNotEquals("Header1	Header2	Header3	\n"
						+ "Data11	Data12	Data13	\n"
						+ "Data21	Data32	Data33	\n"
						+ "Data11	Data22	Data23	\n"
						+ "",fils.toString());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}   
	 }
	 
	 /**
	  * AggregateMin good return
	  * @throws Exception
	  */
	 @Test
	 public void aggregateMin() throws Exception {
		 DataFrame df = new DataFrame("src/test/java/fr/uga/erods/projectDevOps/fichierCSV.csv");	
		 DataFrame fils = df.groupBy(new String[]{"GENRE"}).aggregate("min", "GENRE", "AGE");
		 assertEquals("GENRE	min (AGE)	\n"
		 		+ "FEMME	23.0	\n"
		 		+ "HOMME	23.0	\n"
		 		+ "",fils.toString());
	 }
	 /**
	  * AggregateMax good return
	  * @throws Exception
	  */
	 @Test
	 public void aggregateMax() throws Exception {
		 DataFrame df = new DataFrame("src/test/java/fr/uga/erods/projectDevOps/fichierCSV.csv");	
		 DataFrame fils = df.groupBy(new String[]{"GENRE"}).aggregate("max", "GENRE", "AGE");
		 assertEquals("GENRE	max (AGE)	\n"
		 		+ "FEMME	63.0	\n"
		 		+ "HOMME	53.0	\n"
		 		+ "",fils.toString());
	 }
	 
	 /**
	  * AggregateMean good return
	  * @throws Exception
	  */
	 @Test
	 public void aggregateMean() throws Exception {
		 DataFrame df = new DataFrame("src/test/java/fr/uga/erods/projectDevOps/fichierCSV.csv");	
		 DataFrame fils = df.groupBy(new String[]{"GENRE"}).aggregate("mean", "GENRE", "AGE");
		 assertEquals("GENRE	mean (AGE)	\n"
		 		+ "FEMME	37.142857142857146	\n"
		 		+ "HOMME	32.166666666666664	\n"
		 		+ "",fils.toString());
	 }
	 /**
	  * AggregateCount good return
	  * @throws Exception
	  */
	 @Test
	 public void aggregateCount() throws Exception {
		 DataFrame df = new DataFrame("src/test/java/fr/uga/erods/projectDevOps/fichierCSV.csv");	
		 DataFrame fils = df.groupBy(new String[]{"GENRE"}).aggregate("count", "GENRE", "AGE");
		 assertEquals("GENRE	count (AGE)	\n"
		 		+ "FEMME	7.0	\n"
		 		+ "HOMME	6.0	\n"
		 		+ "",fils.toString());
	 }
	 
	 /**
	  * AggregateCount with bad name for column
	  * @throws Exception
	  */
	 @Test(expected = IllegalArgumentException.class)
	 public void aggregateCountWithError() throws Exception {
		 DataFrame df = new DataFrame("src/test/java/fr/uga/erods/projectDevOps/fichierCSV.csv");	
		 DataFrame fils = df.groupBy(new String[]{"GENRE"}).aggregate("cout", "GENRE", "AGE");
		 
	 }
	 
}
	
