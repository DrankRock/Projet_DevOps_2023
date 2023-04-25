package fr.uga.erods.projectDevOps;


import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.function.ThrowingRunnable;

/**
 * Unit test for simple App.
 */
public class AppTest 
{

	
	/*
	 * Test the first constructor with .csv and try to compare two String
	 */
	@Test
    public void testDataFrameConstructor() throws FileNotFoundException {
        String filename = "fichierCSV.csv";
        DataFrame df = new DataFrame(filename);
        
        assertEquals("ID	METIER	AGE	GENRE	\n"
        		+ "",df.head(0));
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
	
	/*
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
	
	
	/*
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
	    System.out.println(son.toString());
	    assertEquals(true,father.equals(son));
	}
	
	/*
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
	
	/*
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
	    System.out.println(son.toString());
	    assertEquals(false,father.equals(son));
	}
	
	/*
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
	
	/*
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
	    assertEquals(29,father.mean("age"));
	}
	
	/*
	 * Test the function 'mean"
	 * calculate mean in column
	 * with error, we take case in column in which one we don't have any values.
	 */
	@Test
	public void meanFalse() throws Exception{
		String[] top = {"Nom","Prenom","age"};
		List<String[]> data = new ArrayList<>();
	    data.add(new String[]{"John", "Doe","34"});
	    data.add(new String[]{"Jane", "Doe","24"});
	    final DataFrame father = new DataFrame(top,data);
		Exception exception = assertThrows(IllegalArgumentException.class, new ThrowingRunnable() {
			@Override
			public void run() throws Throwable {
				father.mean("Prenom");
			}
		});
	}
	
	/*
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
	    System.out.println(father.max("age"));
	    assertEquals(34.0,father.max("age"));
	}	

	/*
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
	    final DataFrame father = new DataFrame(top,data);
	    Exception exception = assertThrows(NumberFormatException.class, new ThrowingRunnable() {
			@Override
			public void run() throws Throwable {
				assertEquals("34",father.max("Prenom"));
			}
		});  
	}
	
	

	/*
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
	    assertEquals(24.0,father.min("age"));
	}
	
	@Test
	public void minError() {
		String[] top = {"Nom","Prenom","age"};
		List<String[]> data = new ArrayList<>();
	    data.add(new String[]{"John", "Doe","34"});
	    data.add(new String[]{"Jane", "Doe","24"});
	    final DataFrame father = new DataFrame(top,data);
	    Exception exception = assertThrows(NumberFormatException.class, new ThrowingRunnable() {
			@Override
			public void run() throws Throwable {
				assertEquals("34",father.min("Prenom"));
			}
		});  
	}
	
	
}
	
