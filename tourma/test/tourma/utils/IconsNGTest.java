/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.utils;

import javax.swing.ImageIcon;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
/**
 *
 * @author WFMJ7631
 */
public class IconsNGTest {
    
    public IconsNGTest() {
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
     * Test of getDices method, of class Icons.
     */
    @Test
    public void testGetDices() {
        System.out.println("getDices");
        ImageIcon expResult = null;
        ImageIcon result = Icons.getDices();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getParams method, of class Icons.
     */
    @Test
    public void testGetParams() {
        System.out.println("getParams");
        ImageIcon expResult = null;
        ImageIcon result = Icons.getParams();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStar method, of class Icons.
     */
    @Test
    public void testGetStar() {
        System.out.println("getStar");
        ImageIcon expResult = null;
        ImageIcon result = Icons.getStar();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStats method, of class Icons.
     */
    @Test
    public void testGetStats() {
        System.out.println("getStats");
        ImageIcon expResult = null;
        ImageIcon result = Icons.getStats();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
