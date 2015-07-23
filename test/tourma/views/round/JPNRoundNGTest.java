/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.views.round;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tourma.data.Round;
/**
 *
 * @author WFMJ7631
 */
public class JPNRoundNGTest {
    
    public JPNRoundNGTest() {
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
     * Test of getRound method, of class JPNRound.
     */
    @Test(enabled=false)
    public void testGetRound() {
        System.out.println("getRound");
        JPNRound instance = null;
        Round expResult = null;
        Round result = instance.getRound();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class JPNRound.
     */
    @Test(enabled=false)
    public void testUpdate() {
        System.out.println("update");
        JPNRound instance = null;
        instance.update();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMatchTableSelectedRow method, of class JPNRound.
     */
    @Test(enabled=false)
    public void testGetMatchTableSelectedRow() {
        System.out.println("getMatchTableSelectedRow");
        JPNRound instance = null;
        int expResult = 0;
        int result = instance.getMatchTableSelectedRow();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setRoundOnly method, of class JPNRound.
     */
    @Test(enabled=false)
    public void testSetRoundOnly() {
        System.out.println("setRoundOnly");
        boolean roundonly = false;
        JPNRound instance = null;
        instance.setRoundOnly(roundonly);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setNafOnly method, of class JPNRound.
     */
    @Test(enabled=false)
    public void testSetNafOnly() {
        System.out.println("setNafOnly");
        boolean nafonly = false;
        JPNRound instance = null;
        instance.setNafOnly(nafonly);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
