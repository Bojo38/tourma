/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import java.awt.Color;
import java.util.ArrayList;
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
public class CompetitorTest {
    
    public CompetitorTest() {
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
     * Test of generateRandomColor method, of class Competitor.
     */
    @Test
    public void testGenerateRandomColor() {
        System.out.println("generateRandomColor");
        Color mix = null;
        Competitor instance = new CompetitorImpl();
        Color expResult = null;
        Color result = instance.generateRandomColor(mix);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getName method, of class Competitor.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Competitor instance = new CompetitorImpl();
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of AddMatch method, of class Competitor.
     */
    @Test
    public void testAddMatch() {
        System.out.println("AddMatch");
        Competitor opponent = null;
        Round r = null;
        Competitor instance = new CompetitorImpl();
        instance.AddMatch(opponent, r);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of AddMatchRoundRobin method, of class Competitor.
     */
    @Test
    public void testAddMatchRoundRobin() {
        System.out.println("AddMatchRoundRobin");
        Competitor opponent = null;
        Round r = null;
        Competitor instance = new CompetitorImpl();
        instance.AddMatchRoundRobin(opponent, r);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of havePlayed method, of class Competitor.
     */
    @Test
    public void testHavePlayed() {
        System.out.println("havePlayed");
        Competitor opponent = null;
        Competitor instance = new CompetitorImpl();
        boolean expResult = false;
        boolean result = instance.havePlayed(opponent);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPossibleOpponents method, of class Competitor.
     */
    @Test
    public void testGetPossibleOpponents() {
        System.out.println("getPossibleOpponents");
        ArrayList<Competitor> opponents = null;
        Competitor instance = new CompetitorImpl();
        ArrayList expResult = null;
        ArrayList result = instance.getPossibleOpponents(opponents,new Round());
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDecoratedName method, of class Competitor.
     */
    @Test
    public void testGetDecoratedName() {
        System.out.println("getDecoratedName");
        Competitor instance = new CompetitorImpl();
        String expResult = "";
        String result = instance.getDecoratedName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of RoundCheck method, of class Competitor.
     */
    @Test
    public void testRoundCheck() {
        System.out.println("RoundCheck");
        Round r = null;
        Competitor instance = new CompetitorImpl();
        instance.RoundCheck(r);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Competitor.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Competitor instance = new CompetitorImpl();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class CompetitorImpl extends Competitor {

        public void AddMatch(Competitor opponent, Round r) {
        }

        public void AddMatchRoundRobin(Competitor opponent, Round r) {
        }

        public boolean havePlayed(Competitor opponent) {
            return false;
        }

        public ArrayList<Competitor> getPossibleOpponents(ArrayList<Competitor> opponents,Round r) {
            return null;
        }

        public String getDecoratedName() {
            return "";
        }

        public void RoundCheck(Round r) {
        }

        @Override
        public int compareTo(Object o) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }
}
