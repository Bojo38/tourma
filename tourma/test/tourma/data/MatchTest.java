/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import java.util.ArrayList;
import org.jdom2.Element;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author WFMJ7631
 */
public class MatchTest {
    
    public MatchTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getXMLElement method, of class Match.
     */
    @Test
    public void testGetXMLElement() {
        System.out.println("getXMLElement");
        Match instance = null;
        Element expResult = null;
        Element result = instance.getXMLElement();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setXMLElement method, of class Match.
     */
    @Test
    public void testSetXMLElement() {
        System.out.println("setXMLElement");
        Element match = null;
        Match instance = null;
        instance.setXMLElement(match);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getWinner method, of class Match.
     */
    @Test
    public void testGetWinner() {
        System.out.println("getWinner");
        Match instance = null;
        Coach expResult = null;
        Coach result = instance.getWinner();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLooser method, of class Match.
     */
    @Test
    public void testGetLooser() {
        System.out.println("getLooser");
        Match instance = null;
        Coach expResult = null;
        Coach result = instance.getLooser();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of resetWL method, of class Match.
     */
    @Test
    public void testResetWL() {
        System.out.println("resetWL");
        Match instance = null;
        instance.resetWL();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTeamMatchWinner method, of class Match.
     */
    @Test
    public void testGetTeamMatchWinner() {
        System.out.println("getTeamMatchWinner");
        int teamMatesNumber = 0;
        int matchIndex = 0;
        ArrayList<Match> matchs = null;
        Team expResult = null;
        Team result = Match.getTeamMatchWinner(teamMatesNumber, matchIndex, matchs);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
