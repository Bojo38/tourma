/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import java.util.ArrayList;
import java.util.Date;
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
public class RoundTest {
    private static final Logger LOG = Logger.getLogger(RoundTest.class.getName());
    
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
    public RoundTest() {
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
     * Test of toString method, of class Round.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Round instance = new Round();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMatchs method, of class Round.
     */
    @Test
    public void testGetMatchs() {
        System.out.println("getMatchs");
        Round instance = new Round();
        ArrayList expResult = null;
        ArrayList result = instance.getMatchs();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setHour method, of class Round.
     */
    @Test
    public void testSetHour() {
        System.out.println("setHour");
        Date heure = null;
        Round instance = new Round();
        instance.setHour(heure);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getHour method, of class Round.
     */
    @Test
    public void testGetHour() {
        System.out.println("getHour");
        Round instance = new Round();
        Date expResult = null;
        Date result = instance.getHour();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getXMLElement method, of class Round.
     */
    @Test
    public void testGetXMLElement() {
        System.out.println("getXMLElement");
        Round instance = new Round();
        Element expResult = null;
        Element result = instance.getXMLElement();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setXMLElement method, of class Round.
     */
    @Test
    public void testSetXMLElement() {
        System.out.println("setXMLElement");
        Element round = null;
        Round instance = new Round();
        instance.setXMLElement(round);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCoachMatchs method, of class Round.
     */
    @Test
    public void testGetCoachMatchs() {
        System.out.println("getCoachMatchs");
        Round instance = new Round();
        ArrayList expResult = null;
        ArrayList result = instance.getCoachMatchs();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
