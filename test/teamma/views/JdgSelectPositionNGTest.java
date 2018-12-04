/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teamma.views;

import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import teamma.data.PlayerType;
import teamma.data.StarPlayer;

/**
 *
 * @author WFMJ7631
 */
public class JdgSelectPositionNGTest {
    
    public JdgSelectPositionNGTest() {
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
     * Test of getPosition method, of class JdgSelectPosition.
     */
    @Test
    public void testGetPosition() {
        System.out.println("getPosition");
        JdgSelectPosition instance = null;
        PlayerType expResult = null;
        PlayerType result = instance.getPosition();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStarPlayer method, of class JdgSelectPosition.
     */
    @Test
    public void testGetStarPlayer() {
        System.out.println("getStarPlayer");
        JdgSelectPosition instance = null;
        StarPlayer expResult = null;
        StarPlayer result = instance.getStarPlayer();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
