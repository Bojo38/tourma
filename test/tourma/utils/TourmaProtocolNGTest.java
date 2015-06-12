/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.utils;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author WFMJ7631
 */
public class TourmaProtocolNGTest {
    
    public TourmaProtocolNGTest() {
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
     * Test of getKey method, of class TourmaProtocol.
     */
    @Test
    public void testGetKey() {
        System.out.println("getKey");
        String k = "";
        TourmaProtocol instance = new TourmaProtocol();
        TourmaProtocol.TKey expResult = null;
        TourmaProtocol.TKey result = instance.getKey(k);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of processInput method, of class TourmaProtocol.
     */
    @Test
    public void testProcessInput() {
        System.out.println("processInput");
        Object object = null;
        TourmaProtocol instance = new TourmaProtocol();
        String expResult = "";
        String result = instance.processInput(object);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
