/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

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
         GroupPoints instance=new GroupPoints();
        int value=30;
        instance.setVictoryPoints(value);
        assertEquals(instance.getVictoryPoints(),value);
    }

    /**
     * Test of setVictoryPoints method, of class GroupPoints.
     */
    @Test
    public void testSetVictoryPoints() {
        System.out.println("setVictoryPoints");
        GroupPoints instance=new GroupPoints();
        int value=30;
        instance.setVictoryPoints(value);
        assertEquals(instance.getVictoryPoints(),value);
    }

    /**
     * Test of getDrawPoints method, of class GroupPoints.
     */
    @Test
    public void testGetDrawPoints() {
        System.out.println("getDrawPoints");
        GroupPoints instance=new GroupPoints();
        int value=30;
        instance.setDrawPoints(value);
        assertEquals(instance.getDrawPoints(),value);
    }

    /**
     * Test of setDrawPoints method, of class GroupPoints.
     */
    @Test
    public void testSetDrawPoints() {
        System.out.println("setDrawPoints");
        GroupPoints instance=new GroupPoints();
        int value=30;
        instance.setDrawPoints(value);
        assertEquals(instance.getDrawPoints(),value);
    }

    /**
     * Test of getLossPoints method, of class GroupPoints.
     */
    @Test
    public void testGetLossPoints() {
        System.out.println("getLossPoints");
         GroupPoints instance=new GroupPoints();
        int value=15;
        instance.setLossPoints(value);
        assertEquals(instance.getLossPoints(),value);
    }

    /**
     * Test of setLossPoints method, of class GroupPoints.
     */
    @Test
    public void testSetLossPoints() {
        System.out.println("setLossPoints");
         GroupPoints instance=new GroupPoints();
        int value=15;
        instance.setLossPoints(value);
        assertEquals(instance.getLossPoints(),value);
    }

    /**
     * Test of getUID method, of class GroupPoints.
     */
    @Test
    public void testGetUID() {
        System.out.println("getUID");
        GroupPoints instance = new GroupPoints();
        int expResult = 0;
        int result = instance.getUID();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setUID method, of class GroupPoints.
     */
    @Test
    public void testSetUID() {
        System.out.println("setUID");
        int UID = 0;
        GroupPoints instance = new GroupPoints();
        instance.setUID(UID);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of pull method, of class GroupPoints.
     */
    @Test
    public void testPull() {
        System.out.println("pull");
        GroupPoints gp = null;
        GroupPoints instance = new GroupPoints();
        instance.pull(gp);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class GroupPoints.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = null;
        GroupPoints instance = new GroupPoints();
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
