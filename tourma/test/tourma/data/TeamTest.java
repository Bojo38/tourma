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
public class TeamTest {
    
    public TeamTest() {
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
     * Test of compareTo method, of class Team.
     */
    @Test
    public void testCompareTo() {
        System.out.println("compareTo");
        Object obj = null;
        Team instance = new Team();
        int expResult = 0;
        int result = instance.compareTo(obj);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getActivePlayerNumber method, of class Team.
     */
    @Test
    public void testGetActivePlayerNumber() {
        System.out.println("getActivePlayerNumber");
        Team instance = new Team();
        int expResult = 0;
        int result = instance.getActivePlayerNumber();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getActivePlayers method, of class Team.
     */
    @Test
    public void testGetActivePlayers() {
        System.out.println("getActivePlayers");
        Team instance = new Team();
        ArrayList expResult = null;
        ArrayList result = instance.getActivePlayers();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getXMLElement method, of class Team.
     */
    @Test
    public void testGetXMLElement() {
        System.out.println("getXMLElement");
        Team instance = new Team();
        Element expResult = null;
        Element result = instance.getXMLElement();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setXMLElement method, of class Team.
     */
    @Test
    public void testSetXMLElement() {
        System.out.println("setXMLElement");
        Element team = null;
        Team instance = new Team();
        instance.setXMLElement(team);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNullTeam method, of class Team.
     */
    @Test
    public void testGetNullTeam() {
        System.out.println("getNullTeam");
        Team expResult = null;
        Team result = Team.getNullTeam();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of AddMatch method, of class Team.
     */
    @Test
    public void testAddMatch() {
        System.out.println("AddMatch");
        Competitor opponent = null;
        Round r = null;
        Team instance = new Team();
        instance.AddMatch(opponent, r);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of havePlayed method, of class Team.
     */
    @Test
    public void testHavePlayed() {
        System.out.println("havePlayed");
        Competitor opponent = null;
        Team instance = new Team();
        boolean expResult = false;
        boolean result = instance.havePlayed(opponent);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPossibleOpponents method, of class Team.
     */
    @Test
    public void testGetPossibleOpponents() {
        System.out.println("getPossibleOpponents");
        ArrayList<Competitor> opponents = null;
        Team instance = new Team();
        ArrayList expResult = null;
        ArrayList result = instance.getPossibleOpponents(opponents,new Round());
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDecoratedName method, of class Team.
     */
    @Test
    public void testGetDecoratedName() {
        System.out.println("getDecoratedName");
        Team instance = new Team();
        String expResult = "";
        String result = instance.getDecoratedName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of AddMatchRoundRobin method, of class Team.
     */
    @Test
    public void testAddMatchRoundRobin() {
        System.out.println("AddMatchRoundRobin");
        Competitor c = null;
        Round r = null;
        Team instance = new Team();
        instance.AddMatchRoundRobin(c, r);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of RoundCheck method, of class Team.
     */
    @Test
    public void testRoundCheck() {
        System.out.println("RoundCheck");
        Round round = null;
        Team instance = new Team();
        instance.RoundCheck(round);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
