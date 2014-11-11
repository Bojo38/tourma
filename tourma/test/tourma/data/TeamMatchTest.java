/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

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
public class TeamMatchTest {
    private static final Logger LOG = Logger.getLogger(TeamMatchTest.class.getName());
    
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
    public TeamMatchTest() {
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
     * Test of getWinner method, of class TeamMatch.
     */
    @Test
    public void testGetWinner() {
        System.out.println("getWinner");
        TeamMatch instance = null;
        Competitor expResult = null;
        Competitor result = instance.getWinner();
        assertEquals(expResult, result);
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
        assertEquals(expResult, result);
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
        assertEquals(expResult, result);
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
        assertEquals(expResult, result);
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
        assertEquals(expResult, result);
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
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
