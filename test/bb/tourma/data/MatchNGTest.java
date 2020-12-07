/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bb.tourma.data;

import bb.tourma.data.Round;
import bb.tourma.data.Competitor;
import bb.tourma.data.Team;
import bb.tourma.data.Match;
import bb.tourma.data.Coach;
import bb.tourma.data.Criterion;
import java.io.File;
import org.jdom.Element;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;
import static org.testng.Assert.assertTrue;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author WFMJ7631
 */
public class MatchNGTest {

    public MatchNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        Tournament.getTournament().loadXML(new File("./test/tournament.xml"));
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
     * Test of getWinner method, of class Match.
     */
    @Test
    public void testGetWinner() {
        System.out.println("getWinner");
        Match instance = new MatchImpl();
        Competitor expResult = new Team("A");
        instance.setCompetitor1(expResult);
        instance.setCompetitor2(new Team("B"));

        instance.setWinner(expResult);
        Competitor result = instance.getWinner();
        assertEquals(result, expResult);
    }

    /**
     * Test of getLooser method, of class Match.
     */
    @Test
    public void testGetLooser() {
        System.out.println("getLooser");
        Match instance = new MatchImpl();
        Competitor expResult = new Team("A");
        instance.setCompetitor1(expResult);
        instance.setCompetitor2(new Team("B"));
        ((Match) instance).setLooser(expResult);
        Competitor result = ((Match) instance).getLooser();

        assertEquals(result, expResult);
    }

    /**
     * Test of resetWL method, of class Match.
     */
    @Test
    public void testResetWL() {
        System.out.println("resetWL");
        Match instance = new MatchImpl();
        Competitor expResult = new Team("A");
        instance.setCompetitor1(expResult);
        instance.setCompetitor2(new Team("B"));
        instance.setWinner(expResult);
        Competitor result = instance.getWinner();
        assertEquals(result, expResult);
        instance.resetWL();
        result = instance.getWinner();
        assertEquals(result, null);

    }

    /**
     * Test of getCompetitor1 method, of class Match.
     */
    @Test
    public void testGetCompetitor1() {
        System.out.println("getCompetitor1");
        Match instance = new MatchImpl();
        Competitor expResult = new Coach("A");
        instance.setCompetitor1(expResult);
        instance.setCompetitor2(new Coach("B"));
        Competitor result = instance.getCompetitor1();
        assertEquals(result, expResult);
    }

    /**
     * Test of setCompetitor1 method, of class Match.
     */
    @Test
    public void testSetCompetitor1() {
        System.out.println("setCompetitor1");
        Match instance = new MatchImpl();
        Competitor expResult = new Coach("A");
        instance.setCompetitor1(expResult);
        instance.setCompetitor2(new Coach("B"));
        Competitor result = instance.getCompetitor1();
        assertEquals(result, expResult);
    }

    /**
     * Test of getCompetitor2 method, of class Match.
     */
    @Test
    public void testGetCompetitor2() {
        System.out.println("getCompetitor2");
        Match instance = new MatchImpl();
        Competitor expResult = new Coach("A");
        instance.setCompetitor2(expResult);
        instance.setCompetitor1(new Coach("B"));
        Competitor result = instance.getCompetitor2();
        assertEquals(result, expResult);
    }

    /**
     * Test of setCompetitor2 method, of class Match.
     */
    @Test
    public void testSetCompetitor2() {
        System.out.println("setCompetitor2");
        Match instance = new MatchImpl();
        Competitor expResult = new Coach("A");
        instance.setCompetitor2(expResult);
        instance.setCompetitor1(new Coach("B"));
        Competitor result = instance.getCompetitor2();
        assertEquals(result, expResult);
    }

    /**
     * Test of getRound method, of class Match.
     */
    @Test
    public void testGetRound() {
        System.out.println("getRound");
        Round r = new Round(Tournament.getTournament().getRoundsCount(), Tournament.getTournament());
        Match instance = new MatchImpl();
        instance.setRound(r);
        assertTrue(r == instance.getRound());
    }

    /**
     * Test of setRound method, of class Match.
     */
    @Test
    public void testSetRound() {
        System.out.println("setRound");
        Round r = new Round(Tournament.getTournament().getRoundsCount(), Tournament.getTournament());
        Match instance = new MatchImpl();
        instance.setRound(r);
        assertTrue(r == instance.getRound());
    }

    /**
     * Test of setWinner method, of class Match.
     */
    @Test
    public void testSetWinner() {
        System.out.println("setWinner");
        Match instance = new MatchImpl();
        Competitor expResult = new Team("A");
        instance.setCompetitor1(expResult);
        instance.setCompetitor2(new Team("B"));

        instance.setWinner(expResult);
        Competitor result = instance.getWinner();
        assertEquals(result, expResult);
    }

    /**
     * Test of setLooser method, of class Match.
     */
    @Test
    public void testSetLooser() {
        System.out.println("setLooser");
        Match instance = new MatchImpl();
        Competitor expResult = new Team("A");
        instance.setCompetitor1(expResult);
        instance.setCompetitor2(new Team("B"));
        ((Match) instance).setLooser(expResult);
        Competitor result = ((Match) instance).getLooser();

        assertEquals(result, expResult);
    }

    public class MatchImpl extends Match {

        public MatchImpl() {
            super(null);
        }

        @Override
        public Element getXMLElement() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void setXMLElement(Element e) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public Element getXMLElementForDisplay() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void setXMLElementForDisplay(Element element) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public int getUID() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void setUID(int UID) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void pull(Match match) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void push(Match match) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void recomputeValues() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public int getValue(Criterion crit, int subtype, Competitor c) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public boolean isEntered() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }

    /**
     * Test of getXMLElementForDisplay method, of class Match.
     */
    @Test(enabled = false)
    public void testGetXMLElementForDisplay() {
        System.out.println("getXMLElementForDisplay");
        Match instance = null;
        Element expResult = null;
        Element result = instance.getXMLElementForDisplay();
        assertEquals(result, expResult);
    }

    /**
     * Test of setXMLElementForDisplay method, of class Match.
     */
    @Test(enabled = false)
    public void testSetXMLElementForDisplay() {
        System.out.println("setXMLElementForDisplay");
        Element element = null;
        Match instance = null;
        instance.setXMLElementForDisplay(element);
    }

    /**
     * Test of isUpdated method, of class Match.
     */
    @Test
    public void testIsUpdated() {
        System.out.println("isUpdated");
        Match instance = new CoachMatch(null);
        boolean expResult = true;
        boolean result = instance.isUpdated();
        assertEquals(result, expResult);
       
        instance.setUpdated(false);
        result = instance.isUpdated();
        assertEquals(result, false);
    }

    /**
     * Test of setUpdated method, of class Match.
     */
    @Test
    public void testSetUpdated() {
        System.out.println("setUpdated");
         Match instance = new CoachMatch(null);
        boolean expResult = true;
        boolean result = instance.isUpdated();
        assertEquals(result, expResult);
       
        instance.setUpdated(false);
        result = instance.isUpdated();
        assertEquals(result, false);
    }

    /**
     * Test of getUID method, of class Match.
     */
    @Test
    public void testGetUID() {
        System.out.println("getUID");
        Match instance = new CoachMatch(null);
        int expResult = 0;
        int result = instance.getUID();

    }

    /**
     * Test of setUID method, of class Match.
     */
    @Test
    public void testSetUID() {
        System.out.println("setUID");
        int UID = 0;
        Match instance = new CoachMatch(null);
        instance.setUID(UID);

    }

    /**
     * Test of pull method, of class Match.
     */
    @Test
    public void testPull() {
        System.out.println("pull");
        Match match = null;
        Match instance = null;
        //instance.pull(match);
       
    }

    /**
     * Test of push method, of class Match.
     */
    @Test
    public void testPush() {
        System.out.println("push");
        Match match = null;
        Match instance = null;
       // instance.push(match);
       
    }

    /**
     * Test of equals method, of class Match.
     */
    @Test(enabled=false)
    public void testEquals() {
        System.out.println("equals");
        Object obj = null;
        Match instance=null;
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isValues_computed method, of class Match.
     */
    @Test
    public void testIsValues_computed() {
        System.out.println("isValues_computed");
        Match instance = new CoachMatch(null);
        boolean expResult = false;
        boolean result = instance.isValues_computed();
        assertEquals(result, expResult);
        
        instance.setValues_computed(true);
        result = instance.isValues_computed();
        assertEquals(result, true);
        
    }

    /**
     * Test of setValues_computed method, of class Match.
     */
    @Test
    public void testSetValues_computed() {
        System.out.println("setValues_computed");
        Match instance = new CoachMatch(null);
        boolean expResult = false;
        boolean result = instance.isValues_computed();
        assertEquals(result, expResult);
        
        instance.setValues_computed(true);
        result = instance.isValues_computed();
        assertEquals(result, true);
    }

    /**
     * Test of recomputeValues method, of class Match.
     */
    @Test(enabled=false)
    public void testRecomputeValues() {
        System.out.println("recomputeValues");
        Match instance = null;
        instance.recomputeValues();
        
    }

    /**
     * Test of getValue method, of class Match.
     */
    @Test
    public void testGetValue_int_Competitor() {
        System.out.println("getValue");
        int indexvalue = 0;
        Competitor c = Tournament.getTournament().getCoach(0);
        Match instance = c.getMatch(0);
        int expResult = 0;
        int result = instance.getValue(indexvalue, c);
        assertEquals(result, expResult);
    }

    /**
     * Test of getValue method, of class Match.
     */
    @Test
    public void testGetValue_3args() {
        System.out.println("getValue");
        Criterion crit = Tournament.getTournament().getParams().getCriterion(0);
        int subtype = 0;
        Competitor c = Tournament.getTournament().getCoach(0);
        Match instance = c.getMatch(0);
        int expResult = 0;
        int result = instance.getValue(crit, subtype, c);
        assertEquals(result, expResult);
    }

    /**
     * Test of isEntered method, of class Match.
     */
    @Test
    public void testIsEntered() {
        System.out.println("isEntered");
        Match instance = new CoachMatch(null);
        boolean expResult = false;
        boolean result = instance.isEntered();
        assertEquals(result, expResult);
      
    }

}
