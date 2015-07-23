/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.views;

import javax.swing.JLabel;
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
public class GraphicalMatchNGTest {
    
    public GraphicalMatchNGTest() {
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
     * Test of getClanIcon1 method, of class GraphicalMatch.
     */
    @Test(enabled=false)
    public void testGetClanIcon1() {
        System.out.println("getClanIcon1");
        GraphicalMatch instance = null;
        JLabel expResult = null;
        JLabel result = instance.getClanIcon1();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setClanIcon1 method, of class GraphicalMatch.
     */
    @Test(enabled=false)
    public void testSetClanIcon1() {
        System.out.println("setClanIcon1");
        JLabel clanIcon1 = null;
        GraphicalMatch instance = null;
        instance.setClanIcon1(clanIcon1);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getClanIcon2 method, of class GraphicalMatch.
     */
    @Test(enabled=false)
    public void testGetClanIcon2() {
        System.out.println("getClanIcon2");
        GraphicalMatch instance = null;
        JLabel expResult = null;
        JLabel result = instance.getClanIcon2();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setClanIcon2 method, of class GraphicalMatch.
     */
    @Test(enabled=false)
    public void testSetClanIcon2() {
        System.out.println("setClanIcon2");
        JLabel clanIcon2 = null;
        GraphicalMatch instance = null;
        instance.setClanIcon2(clanIcon2);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
