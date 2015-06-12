/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

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
public class GroupPointsNGTest {
    
    public GroupPointsNGTest() {
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
     * Test of getVictoryPoints method, of class GroupPoints.
     */
    @Test
    public void testGetVictoryPoints() {
        System.out.println("getVictoryPoints");
        GroupPoints instance = new GroupPoints();
        int expResult = 0;
        int result = instance.getVictoryPoints();
//        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setVictoryPoints method, of class GroupPoints.
     */
    @Test
    public void testSetVictoryPoints() {
        System.out.println("setVictoryPoints");
        int victoryPoints = 0;
        GroupPoints instance = new GroupPoints();
        instance.setVictoryPoints(victoryPoints);
        // TODO review the generated test code and remove the default call to fail.
       fail("The test case is a prototype.");
    }

    /**
     * Test of getDrawPoints method, of class GroupPoints.
     */
    @Test
    public void testGetDrawPoints() {
        System.out.println("getDrawPoints");
        GroupPoints instance = new GroupPoints();
        int expResult = 0;
        int result = instance.getDrawPoints();
        //assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDrawPoints method, of class GroupPoints.
     */
    @Test
    public void testSetDrawPoints() {
        System.out.println("setDrawPoints");
        int drawPoints = 0;
        GroupPoints instance = new GroupPoints();
        instance.setDrawPoints(drawPoints);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLossPoints method, of class GroupPoints.
     */
    @Test
    public void testGetLossPoints() {
        System.out.println("getLossPoints");
        GroupPoints instance = new GroupPoints();
        int expResult = 0;
        int result = instance.getLossPoints();
        //assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setLossPoints method, of class GroupPoints.
     */
    @Test
    public void testSetLossPoints() {
        System.out.println("setLossPoints");
        int lossPoints = 0;
        GroupPoints instance = new GroupPoints();
        instance.setLossPoints(lossPoints);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
