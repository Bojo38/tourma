/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import java.awt.Color;
import java.util.ArrayList;
import java.util.logging.Logger;
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
public class CoachTest {
    private static final Logger LOG = Logger.getLogger(CoachTest.class.getName());
    
    /**
     *
     */
    @BeforeClass
    public static void setUpClass() {
    }
    
    /**
     *
     */
    @AfterClass
    public static void tearDownClass() {
    }
    
    /**
     *
     */
    public CoachTest() {
    }
    
    /**
     *
     */
    @Before
    public void setUp() {
    }
    
    /**
     *
     */
    @After
    public void tearDown() {
    }

    /**
     * Test of generateRandomColor method, of class Coach.
     */
    @Test
    public void testGenerateRandomColor() {
        System.out.println("generateRandomColor");
        Color mix = null;
        Coach instance = new Coach();
        Color expResult = null;
        Color result = instance.generateRandomColor(mix);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of compareTo method, of class Coach.
     */
    @Test
    public void testCompareTo() {
        System.out.println("compareTo");
        Object obj = null;
        Coach instance = new Coach();
        int expResult = 0;
        int result = instance.compareTo(obj);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getXMLElement method, of class Coach.
     */
    @Test
    public void testGetXMLElement() {
        System.out.println("getXMLElement");
        Coach instance = new Coach();
        Element expResult = null;
        Element result = instance.getXMLElement();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setXMLElement method, of class Coach.
     */
    @Test
    public void testSetXMLElement() {
        System.out.println("setXMLElement");
        Element coach = null;
        Coach instance = new Coach();
        instance.setXMLElement(coach);
    }

    /**
     * Test of getNullCoach method, of class Coach.
     */
    @Test
    public void testGetNullCoach() {
        System.out.println("getNullCoach");
        Coach expResult = null;
        Coach result = Coach.getNullCoach();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addMatch method, of class Coach.
     */
    @Test
    public void testAddMatch() {
        System.out.println("AddMatch");
        Competitor opponent = null;
        Round r = null;
        Coach instance = new Coach();
        instance.addMatch(opponent, r);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createMatch method, of class Coach.
     */
    @Test
    public void testCreateMatch() {
        System.out.println("CreateMatch");
        Competitor opponent = null;
        Round r = null;
        Coach instance = new Coach();
        CoachMatch expResult = null;
        CoachMatch result = instance.createMatch(opponent, r);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of havePlayed method, of class Coach.
     */
    @Test
    public void testHavePlayed() {
        System.out.println("havePlayed");
        Competitor opponent = null;
        Coach instance = new Coach();
        boolean expResult = false;
        boolean result = instance.havePlayed(opponent);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPossibleOpponents method, of class Coach.
     */
    @Test
    public void testGetPossibleOpponents() {
        System.out.println("getPossibleOpponents");
        ArrayList<Competitor> opponents = null;
        Coach instance = new Coach();
        ArrayList expResult = null;
        ArrayList result = instance.getPossibleOpponents(opponents,new Round());
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDecoratedName method, of class Coach.
     */
    @Test
    public void testGetDecoratedName() {
        System.out.println("getDecoratedName");
        Coach instance = new Coach();
        String expResult = "";
        String result = instance.getDecoratedName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addMatchRoundRobin method, of class Coach.
     */
    @Test
    public void testAddMatchRoundRobin() {
        System.out.println("AddMatchRoundRobin");
        Competitor c = null;
        Round r = null;
        Coach instance = new Coach();
        instance.addMatchRoundRobin(c, r);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of roundCheck method, of class Coach.
     */
    @Test
    public void testRoundCheck() {
        System.out.println("RoundCheck");
        Round round = null;
        Coach instance = new Coach();
        instance.roundCheck(round);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
