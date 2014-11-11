/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;
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
    private static final Logger LOG = Logger.getLogger(CompetitorTest.class.getName());
    
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
    public CompetitorTest() {
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
     * Test of addMatch method, of class Competitor.
     */
    @Test
    public void testAddMatch() {
        System.out.println("AddMatch");
        Competitor opponent = null;
        Round r = null;
        Competitor instance = new CompetitorImpl();
        instance.addMatch(opponent, r);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addMatchRoundRobin method, of class Competitor.
     */
    @Test
    public void testAddMatchRoundRobin() {
        System.out.println("AddMatchRoundRobin");
        Competitor opponent = null;
        Round r = null;
        Competitor instance = new CompetitorImpl();
        instance.addMatchRoundRobin(opponent, r);
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
     * Test of roundCheck method, of class Competitor.
     */
    @Test
    public void testRoundCheck() {
        System.out.println("RoundCheck");
        Round r = null;
        Competitor instance = new CompetitorImpl();
        instance.roundCheck(r);
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

    /**
     *
     */
    public class CompetitorImpl extends Competitor {

        /**
         *
         * @param opponent
         * @param r
         */
        @Override
        public void addMatch(Competitor opponent, Round r) {
        }

        /**
         *
         * @param opponent
         * @param r
         */
        @Override
        public void addMatchRoundRobin(Competitor opponent, Round r) {
        }

        /**
         *
         * @param opponent
         * @return
         */
        @Override
        public boolean havePlayed(Competitor opponent) {
            return false;
        }

        /**
         *
         * @param opponents
         * @param r
         * @return
         */
        @Override
        public ArrayList<Competitor> getPossibleOpponents(ArrayList<Competitor> opponents,Round r) {
            return null;
        }

        /**
         *
         * @return
         */
        @Override
        public String getDecoratedName() {
            return "";
        }

        /**
         *
         * @param r
         */
        @Override
        public void roundCheck(Round r) {
        }

        @Override
        public int compareTo(Object o) {
            return -1;
        }

        /**
         *
         * @param teams
         * @param current
         * @return
         */
        @Override
        public HashMap<Team, Integer> getTeamOppositionCount(ArrayList<Team> teams, Round current) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        /**
         *
         * @param name
         */
        @Override
        public void setName(String name) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        /**
         *
         * @param p
         */
        @Override
        public void setPicture(BufferedImage p) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
}
