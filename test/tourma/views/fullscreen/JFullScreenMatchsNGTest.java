/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.views.fullscreen;

import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tourma.data.Coach;
import tourma.data.Team;
import tourma.data.TeamMatch;
/**
 *
 * @author WFMJ7631
 */
public class JFullScreenMatchsNGTest {
    
    public JFullScreenMatchsNGTest() {
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

    @Test(enabled=false)
    public void testSomeMethod() {
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of clientLoop method, of class JFullScreenMatchs.
     */
    @Test(enabled=false)
    public void testClientLoop() throws Exception {
        System.out.println("clientLoop");
        JFullScreenMatchs instance = null;
        instance.clientLoop(0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setStop method, of class JFullScreenMatchs.
     */
    @Test(enabled=false)
    public void testSetStop() {
        System.out.println("setStop");
        boolean s = false;
        JFullScreenMatchs instance = null;
        instance.setStop(s);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of keyPressed method, of class JFullScreenMatchs.
     */
    @Test(enabled=false)
    public void testKeyPressed() {
        System.out.println("keyPressed");
        KeyEvent evt = null;
        JFullScreenMatchs instance = null;
        instance.keyPressed(evt);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createClashTeamPane method, of class JFullScreenMatchs.
     */
    @Test(enabled=false)
    public void testCreateClashTeamPane() {
        System.out.println("createClashTeamPane");
        Team t = null;
        TeamMatch tm = null;
        boolean right = false;
        JFullScreenMatchs instance = null;
        JPanel expResult = null;
        JPanel result = instance.createClashTeamPane(t, tm, right);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createClashCoachPane method, of class JFullScreenMatchs.
     */
    @Test(enabled=false)
    public void testCreateClashCoachPane() {
        System.out.println("createClashCoachPane");
        Coach t = null;
        int score = 0;
        boolean right = false;
        JFullScreenMatchs instance = null;
        JPanel expResult = null;
        JPanel result = instance.createClashCoachPane(t, score, right,0);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
