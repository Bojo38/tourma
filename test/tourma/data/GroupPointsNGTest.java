/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import static org.testng.Assert.assertEquals;
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
    
}