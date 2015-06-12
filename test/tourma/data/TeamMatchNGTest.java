/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import org.jdom2.Element;
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
public class TeamMatchNGTest {
    
    public TeamMatchNGTest() {
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
     * Test of getWinner method, of class TeamMatch.
     */
    @Test
    public void testGetWinner() {
        System.out.println("getWinner");
        TeamMatch instance = null;
        Competitor expResult = null;
        Competitor result = instance.getWinner();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLooser method, of class TeamMatch.
     */
    @Test
    public void testGetLooser() {
        System.out.println("getLooser");
        TeamMatch instance = null;
        Competitor expResult = null;
        Competitor result = instance.getLooser();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getXMLElement method, of class TeamMatch.
     */
    @Test
    public void testGetXMLElement() {
        System.out.println("getXMLElement");
        TeamMatch instance = null;
        Element expResult = null;
        Element result = instance.getXMLElement();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setXMLElement method, of class TeamMatch.
     */
    @Test
    public void testSetXMLElement() {
        System.out.println("setXMLElement");
        Element match = null;
        TeamMatch instance = null;
        instance.setXMLElement(match);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getVictories method, of class TeamMatch.
     */
    @Test
    public void testGetVictories() {
        System.out.println("getVictories");
        Team t1 = null;
        TeamMatch instance = null;
        int expResult = 0;
        int result = instance.getVictories(t1);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLoss method, of class TeamMatch.
     */
    @Test
    public void testGetLoss() {
        System.out.println("getLoss");
        Team t1 = null;
        TeamMatch instance = null;
        int expResult = 0;
        int result = instance.getLoss(t1);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDraw method, of class TeamMatch.
     */
    @Test
    public void testGetDraw() {
        System.out.println("getDraw");
        Team t1 = null;
        TeamMatch instance = null;
        int expResult = 0;
        int result = instance.getDraw(t1);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMatchCount method, of class TeamMatch.
     */
    @Test
    public void testGetMatchCount() {
        System.out.println("getMatchCount");
        TeamMatch instance = null;
        int expResult = 0;
        int result = instance.getMatchCount();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMatch method, of class TeamMatch.
     */
    @Test
    public void testGetMatch() {
        System.out.println("getMatch");
        int i = 0;
        TeamMatch instance = null;
        CoachMatch expResult = null;
        CoachMatch result = instance.getMatch(i);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of clearMatchs method, of class TeamMatch.
     */
    @Test
    public void testClearMatchs() {
        System.out.println("clearMatchs");
        TeamMatch instance = null;
        instance.clearMatchs();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of containsMatch method, of class TeamMatch.
     */
    @Test
    public void testContainsMatch() {
        System.out.println("containsMatch");
        CoachMatch c = null;
        TeamMatch instance = null;
        boolean expResult = false;
        boolean result = instance.containsMatch(c);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addMatch method, of class TeamMatch.
     */
    @Test
    public void testAddMatch() {
        System.out.println("addMatch");
        CoachMatch c = null;
        TeamMatch instance = null;
        instance.addMatch(c);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getXMLElementForDisplay method, of class TeamMatch.
     */
    @Test
    public void testGetXMLElementForDisplay() {
        System.out.println("getXMLElementForDisplay");
        TeamMatch instance = null;
        Element expResult = null;
        Element result = instance.getXMLElementForDisplay();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setXMLElementForDisplay method, of class TeamMatch.
     */
    @Test
    public void testSetXMLElementForDisplay() {
        System.out.println("setXMLElementForDisplay");
        Element match = null;
        TeamMatch instance = null;
        instance.setXMLElementForDisplay(match);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
