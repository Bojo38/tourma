/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

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
     * Test of getWinner method, of class Match.
     */
    @Test
    public void testGetWinner() {
        System.out.println("getWinner");
        Match instance = null;
        Competitor expResult = null;
        Competitor result = instance.getWinner();
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
        Competitor expResult = null;
        Competitor result = instance.getLooser();
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

    public class MatchImpl extends Match {

        public MatchImpl() {
            super(null);
        }

        public Competitor getWinner() {
            return null;
        }

        public Competitor getLooser() {
            return null;
        }

        @Override
        public Element getXMLElement() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void setXMLElement(Element e) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }
}
