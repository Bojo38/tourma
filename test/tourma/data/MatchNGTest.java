/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

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
        Round r=new Round();
        Match instance = new MatchImpl();
        instance.setRound(r);
        assertTrue(r== instance.getRound());
    }

    /**
     * Test of setRound method, of class Match.
     */
    @Test
    public void testSetRound() {
        System.out.println("setRound");
       Round r=new Round();
        Match instance = new MatchImpl();
        instance.setRound(r);
        assertTrue(r== instance.getRound());
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
        public int getValue(Criteria crit, int subtype, Competitor c) {
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
        Match instance = null;
        boolean expResult = false;
        boolean result = instance.isUpdated();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setUpdated method, of class Match.
     */
    @Test
    public void testSetUpdated() {
        System.out.println("setUpdated");
        boolean updated = false;
        Match instance = null;
        instance.setUpdated(updated);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUID method, of class Match.
     */
    @Test
    public void testGetUID() {
        System.out.println("getUID");
        Match instance = null;
        int expResult = 0;
        int result = instance.getUID();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setUID method, of class Match.
     */
    @Test
    public void testSetUID() {
        System.out.println("setUID");
        int UID = 0;
        Match instance = null;
        instance.setUID(UID);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of pull method, of class Match.
     */
    @Test
    public void testPull() {
        System.out.println("pull");
        Match match = null;
        Match instance = null;
        instance.pull(match);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of push method, of class Match.
     */
    @Test
    public void testPush() {
        System.out.println("push");
        Match match = null;
        Match instance = null;
        instance.push(match);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class Match.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = null;
        Match instance = null;
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
        Match instance = null;
        boolean expResult = false;
        boolean result = instance.isValues_computed();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setValues_computed method, of class Match.
     */
    @Test
    public void testSetValues_computed() {
        System.out.println("setValues_computed");
        boolean values_computed = false;
        Match instance = null;
        instance.setValues_computed(values_computed);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of recomputeValues method, of class Match.
     */
    @Test
    public void testRecomputeValues() {
        System.out.println("recomputeValues");
        Match instance = null;
        instance.recomputeValues();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getValue method, of class Match.
     */
    @Test
    public void testGetValue_int_Competitor() {
        System.out.println("getValue");
        int indexvalue = 0;
        Competitor c = null;
        Match instance = null;
        int expResult = 0;
        int result = instance.getValue(indexvalue, c);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getValue method, of class Match.
     */
    @Test
    public void testGetValue_3args() {
        System.out.println("getValue");
        Criteria crit = null;
        int subtype = 0;
        Competitor c = null;
        Match instance = null;
        int expResult = 0;
        int result = instance.getValue(crit, subtype, c);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isEntered method, of class Match.
     */
    @Test
    public void testIsEntered() {
        System.out.println("isEntered");
        Match instance = null;
        boolean expResult = false;
        boolean result = instance.isEntered();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
