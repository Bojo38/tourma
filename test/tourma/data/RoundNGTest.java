/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import java.util.ArrayList;
import java.util.Date;
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
public class RoundNGTest {
    
    public RoundNGTest() {
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
     * Test of toString method, of class Round.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Round instance = new Round();
        String expResult = "";
        String result = instance.toString();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMatch method, of class Round.
     */
    @Test
    public void testGetMatch() {
        System.out.println("getMatch");
        int i = 0;
        Round instance = new Round();
        Match expResult = null;
        Match result = instance.getMatch(i);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMatchsCount method, of class Round.
     */
    @Test
    public void testGetMatchsCount() {
        System.out.println("getMatchsCount");
        Round instance = new Round();
        int expResult = 0;
        int result = instance.getMatchsCount();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addMatch method, of class Round.
     */
    @Test
    public void testAddMatch() {
        System.out.println("addMatch");
        Match m = null;
        Round instance = new Round();
        instance.addMatch(m);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of shuffleMatchs method, of class Round.
     */
    @Test
    public void testShuffleMatchs() {
        System.out.println("shuffleMatchs");
        Round instance = new Round();
        instance.shuffleMatchs();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of containsMatch method, of class Round.
     */
    @Test
    public void testContainsMatch() {
        System.out.println("containsMatch");
        Match m = null;
        Round instance = new Round();
        boolean expResult = false;
        boolean result = instance.containsMatch(m);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of clearMatchs method, of class Round.
     */
    @Test
    public void testClearMatchs() {
        System.out.println("clearMatchs");
        Round instance = new Round();
        instance.clearMatchs();
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
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setHour method, of class Round.
     */
    @Test
    public void testSetHour() {
        System.out.println("setHour");
        String data = "";
        Round instance = new Round();
        instance.setHour(data);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCurrentHour method, of class Round.
     */
    @Test
    public void testSetCurrentHour() {
        System.out.println("setCurrentHour");
        Round instance = new Round();
        instance.setCurrentHour();
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
        assertEquals(result, expResult);
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
        assertEquals(result, expResult);
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
     * Test of isCup method, of class Round.
     */
    @Test
    public void testIsCup() {
        System.out.println("isCup");
        Round instance = new Round();
        boolean expResult = false;
        boolean result = instance.isCup();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCup method, of class Round.
     */
    @Test
    public void testSetCup() {
        System.out.println("setCup");
        boolean mCup = false;
        Round instance = new Round();
        instance.setCup(mCup);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCupTour method, of class Round.
     */
    @Test
    public void testGetCupTour() {
        System.out.println("getCupTour");
        Round instance = new Round();
        int expResult = 0;
        int result = instance.getCupTour();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCupTour method, of class Round.
     */
    @Test
    public void testSetCupTour() {
        System.out.println("setCupTour");
        int mCupTour = 0;
        Round instance = new Round();
        instance.setCupTour(mCupTour);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCupMaxTour method, of class Round.
     */
    @Test
    public void testGetCupMaxTour() {
        System.out.println("getCupMaxTour");
        Round instance = new Round();
        int expResult = 0;
        int result = instance.getCupMaxTour();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCupMaxTour method, of class Round.
     */
    @Test
    public void testSetCupMaxTour() {
        System.out.println("setCupMaxTour");
        int mCupMaxTour = 0;
        Round instance = new Round();
        instance.setCupMaxTour(mCupMaxTour);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isLooserCup method, of class Round.
     */
    @Test
    public void testIsLooserCup() {
        System.out.println("isLooserCup");
        Round instance = new Round();
        boolean expResult = false;
        boolean result = instance.isLooserCup();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setLooserCup method, of class Round.
     */
    @Test
    public void testSetLooserCup() {
        System.out.println("setLooserCup");
        boolean mLooserCup = false;
        Round instance = new Round();
        instance.setLooserCup(mLooserCup);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeMatch method, of class Round.
     */
    @Test
    public void testRemoveMatch_int() {
        System.out.println("removeMatch");
        int i = 0;
        Round instance = new Round();
        instance.removeMatch(i);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeMatch method, of class Round.
     */
    @Test
    public void testRemoveMatch_Match() {
        System.out.println("removeMatch");
        Match i = null;
        Round instance = new Round();
        instance.removeMatch(i);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMinBonus method, of class Round.
     */
    @Test
    public void testGetMinBonus() {
        System.out.println("getMinBonus");
        Round instance = new Round();
        double expResult = 0.0;
        double result = instance.getMinBonus();
        assertEquals(result, expResult, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMaxBonus method, of class Round.
     */
    @Test
    public void testGetMaxBonus() {
        System.out.println("getMaxBonus");
        Round instance = new Round();
        double expResult = 0.0;
        double result = instance.getMaxBonus();
        assertEquals(result, expResult, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setMinBonus method, of class Round.
     */
    @Test
    public void testSetMinBonus() {
        System.out.println("setMinBonus");
        double v = 0.0;
        Round instance = new Round();
        instance.setMinBonus(v);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setMaxBonus method, of class Round.
     */
    @Test
    public void testSetMaxBonus() {
        System.out.println("setMaxBonus");
        double v = 0.0;
        Round instance = new Round();
        instance.setMaxBonus(v);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCoef method, of class Round.
     */
    @Test
    public void testGetCoef() {
        System.out.println("getCoef");
        Match m = null;
        Round instance = new Round();
        double expResult = 0.0;
        double result = instance.getCoef(m);
        assertEquals(result, expResult, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of indexOf method, of class Round.
     */
    @Test
    public void testIndexOf() {
        System.out.println("indexOf");
        Match m = null;
        Round instance = new Round();
        int expResult = 0;
        int result = instance.indexOf(m);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getXMLElementForDisplay method, of class Round.
     */
    @Test
    public void testGetXMLElementForDisplay() {
        System.out.println("getXMLElementForDisplay");
        Round instance = new Round();
        Element expResult = null;
        Element result = instance.getXMLElementForDisplay();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setXMLElementForDisplay method, of class Round.
     */
    @Test
    public void testSetXMLElementForDisplay() {
        System.out.println("setXMLElementForDisplay");
        Element round = null;
        Round instance = new Round();
        instance.setXMLElementForDisplay(round);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
