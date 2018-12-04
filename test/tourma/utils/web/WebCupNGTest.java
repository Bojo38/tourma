/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.utils.web;

import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tourma.data.Match;

/**
 *
 * @author WFMJ7631
 */
public class WebCupNGTest {
    
    public WebCupNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }

    /**
     * Test of getHTML method, of class WebCup.
     */
    @Test
    public void testGetHTML() {
        System.out.println("getHTML");
        String expResult = "";
        String result = WebCup.getHTML();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getHTMLLine method, of class WebCup.
     */
    @Test
    public void testGetHTMLLine() {
        System.out.println("getHTMLLine");
        Match m = null;
        int index = 0;
        String expResult = "";
        String result = WebCup.getHTMLLine(m, index);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getHTMLHeader method, of class WebCup.
     */
    @Test
    public void testGetHTMLHeader() {
        System.out.println("getHTMLHeader");
        String expResult = "";
        String result = WebCup.getHTMLHeader();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
