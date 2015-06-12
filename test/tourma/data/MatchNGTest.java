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
        Match instance = null;
        Competitor expResult = null;
        Competitor result = instance.getWinner();
        assertEquals(result, expResult);
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
        Competitor expResult = null;
        Competitor result = instance.getLooser();
        assertEquals(result, expResult);
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
     * Test of getCompetitor1 method, of class Match.
     */
    @Test
    public void testGetCompetitor1() {
        System.out.println("getCompetitor1");
        Match instance = null;
        Competitor expResult = null;
        Competitor result = instance.getCompetitor1();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCompetitor1 method, of class Match.
     */
    @Test
    public void testSetCompetitor1() {
        System.out.println("setCompetitor1");
        Competitor mCompetitor1 = null;
        Match instance = null;
        instance.setCompetitor1(mCompetitor1);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCompetitor2 method, of class Match.
     */
    @Test
    public void testGetCompetitor2() {
        System.out.println("getCompetitor2");
        Match instance = null;
        Competitor expResult = null;
        Competitor result = instance.getCompetitor2();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCompetitor2 method, of class Match.
     */
    @Test
    public void testSetCompetitor2() {
        System.out.println("setCompetitor2");
        Competitor mCompetitor2 = null;
        Match instance = null;
        instance.setCompetitor2(mCompetitor2);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRound method, of class Match.
     */
    @Test
    public void testGetRound() {
        System.out.println("getRound");
        Match instance = null;
        Round expResult = null;
        Round result = instance.getRound();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setRound method, of class Match.
     */
    @Test
    public void testSetRound() {
        System.out.println("setRound");
        Round mRound = null;
        Match instance = null;
        instance.setRound(mRound);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setWinner method, of class Match.
     */
    @Test
    public void testSetWinner() {
        System.out.println("setWinner");
        Competitor mWinner = null;
        Match instance = null;
        instance.setWinner(mWinner);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setLooser method, of class Match.
     */
    @Test
    public void testSetLooser() {
        System.out.println("setLooser");
        Competitor mLooser = null;
        Match instance = null;
        instance.setLooser(mLooser);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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
    }

    /**
     * Test of getXMLElementForDisplay method, of class Match.
     */
    @Test
    public void testGetXMLElementForDisplay() {
        System.out.println("getXMLElementForDisplay");
        Match instance = null;
        Element expResult = null;
        Element result = instance.getXMLElementForDisplay();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setXMLElementForDisplay method, of class Match.
     */
    @Test
    public void testSetXMLElementForDisplay() {
        System.out.println("setXMLElementForDisplay");
        Element element = null;
        Match instance = null;
        instance.setXMLElementForDisplay(element);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
