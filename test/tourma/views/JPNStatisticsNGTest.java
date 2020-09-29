/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.views;

import javax.swing.JTabbedPane;
import static org.testng.Assert.fail;
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
public class JPNStatisticsNGTest {
    
    public JPNStatisticsNGTest() {
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
     * Test of update method, of class JPNStatistics.
     */
    @Test(enabled=false)
    public void testUpdate() {
        System.out.println("update");
        JPNStatistics instance = new JPNStatistics();
        instance.update();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTabbedPane method, of class JPNStatistics.
     */
    @Test
    public void testGetTabbedPane() {
        System.out.println("getTabbedPane");
        JPNStatistics instance = new JPNStatistics();
        JTabbedPane expResult = null;
        JTabbedPane result = instance.getTabbedPane();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updatePositions method, of class JPNStatistics.
     */
    @Test
    public void testUpdatePositions() {
        System.out.println("updatePositions");
        JPNStatistics instance = new JPNStatistics();
        instance.updatePositions();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateBalancedTeam method, of class JPNStatistics.
     */
    @Test
    public void testUpdateBalancedTeam() {
        System.out.println("updateBalancedTeam");
        JPNStatistics instance = new JPNStatistics();
        instance.updateBalancedTeam();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateBalancedIndiv method, of class JPNStatistics.
     */
    @Test
    public void testUpdateBalancedIndiv() {
        System.out.println("updateBalancedIndiv");
        JPNStatistics instance = new JPNStatistics();
        instance.updateBalancedIndiv();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateTeamPositions method, of class JPNStatistics.
     */
    @Test
    public void testUpdateTeamPositions() {
        System.out.println("updateTeamPositions");
        JPNStatistics instance = new JPNStatistics();
        instance.updateTeamPositions();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
