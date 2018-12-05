/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.utils.web;

import java.util.Map;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author WFMJ7631
 */
public class WebMatchResultNGTest {
    
    public WebMatchResultNGTest() {
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
     * Test of getHTML method, of class WebMatchResult.
     */
    @Test
    public void testGetHTML() {
        System.out.println("getHTML");
        String expResult = "";
        String result = WebMatchResult.getHTML();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getHTML method, of class WebMatchResult.
     */
    @Test
    public void testGetHTML_String_String() {
        System.out.println("getHTML");
        String name = "";
        String pinCode = "";
        String expResult = "";
        String result = WebMatchResult.getHTML(name, pinCode);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getHTML method, of class WebMatchResult.
     */
    @Test
    public void testGetHTML_Map() {
        System.out.println("getHTML");
        Map<String, String> parms = null;
        String expResult = "";
        String result = WebMatchResult.getHTML(parms);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
