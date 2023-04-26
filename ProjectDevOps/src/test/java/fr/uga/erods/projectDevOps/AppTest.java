package fr.uga.erods.projectDevOps;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void test_printNiceStuff()
    {
    	App app = new App();
    	app.printNiceStuff();
        assertTrue( true );
    }
    
    @Test
    public void test_main() {
    	String[] args = new String[3];
    	App.main(args);
    }
}
