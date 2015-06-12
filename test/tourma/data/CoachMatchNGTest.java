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
public class CoachMatchNGTest {
    
    public CoachMatchNGTest() {
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
     * Test of getXMLElement method, of class CoachMatch.
     */
    @Test
    public void testGetXMLElement() {
        System.out.println("getXMLElement");
        CoachMatch instance = null;
        Element expResult = null;
        Element result = instance.getXMLElement();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setXMLElement method, of class CoachMatch.
     */
    @Test
    public void testSetXMLElement() {
        System.out.println("setXMLElement");
        Element match = null;
        CoachMatch instance = null;
        instance.setXMLElement(match);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getWinner method, of class CoachMatch.
     */
    @Test
    public void testGetWinner() {
        System.out.println("getWinner");
        CoachMatch instance = null;
        Competitor expResult = null;
        Competitor result = instance.getWinner();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLooser method, of class CoachMatch.
     */
    @Test
    public void testGetLooser() {
        System.out.println("getLooser");
        CoachMatch instance = null;
        Competitor expResult = null;
        Competitor result = instance.getLooser();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of resetWL method, of class CoachMatch.
     */
    @Test
    public void testResetWL() {
        System.out.println("resetWL");
        CoachMatch instance = null;
        instance.resetWL();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRoster1 method, of class CoachMatch.
     */
    @Test
    public void testGetRoster1() {
        System.out.println("getRoster1");
        CoachMatch instance = null;
        RosterType expResult = null;
        RosterType result = instance.getRoster1();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setRoster1 method, of class CoachMatch.
     */
    @Test
    public void testSetRoster1() {
        System.out.println("setRoster1");
        RosterType mRoster1 = null;
        CoachMatch instance = null;
        instance.setRoster1(mRoster1);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRoster2 method, of class CoachMatch.
     */
    @Test
    public void testGetRoster2() {
        System.out.println("getRoster2");
        CoachMatch instance = null;
        RosterType expResult = null;
        RosterType result = instance.getRoster2();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setRoster2 method, of class CoachMatch.
     */
    @Test
    public void testSetRoster2() {
        System.out.println("setRoster2");
        RosterType mRoster2 = null;
        CoachMatch instance = null;
        instance.setRoster2(mRoster2);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSubstitute1 method, of class CoachMatch.
     */
    @Test
    public void testGetSubstitute1() {
        System.out.println("getSubstitute1");
        CoachMatch instance = null;
        Substitute expResult = null;
        Substitute result = instance.getSubstitute1();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSubstitute1 method, of class CoachMatch.
     */
    @Test
    public void testSetSubstitute1() {
        System.out.println("setSubstitute1");
        Substitute mSubstitute1 = null;
        CoachMatch instance = null;
        instance.setSubstitute1(mSubstitute1);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSubstitute2 method, of class CoachMatch.
     */
    @Test
    public void testGetSubstitute2() {
        System.out.println("getSubstitute2");
        CoachMatch instance = null;
        Substitute expResult = null;
        Substitute result = instance.getSubstitute2();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSubstitute2 method, of class CoachMatch.
     */
    @Test
    public void testSetSubstitute2() {
        System.out.println("setSubstitute2");
        Substitute mSubstitute2 = null;
        CoachMatch instance = null;
        instance.setSubstitute2(mSubstitute2);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isRefusedBy1 method, of class CoachMatch.
     */
    @Test
    public void testIsRefusedBy1() {
        System.out.println("isRefusedBy1");
        CoachMatch instance = null;
        boolean expResult = false;
        boolean result = instance.isRefusedBy1();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setRefusedBy1 method, of class CoachMatch.
     */
    @Test
    public void testSetRefusedBy1() {
        System.out.println("setRefusedBy1");
        boolean refusedBy1 = false;
        CoachMatch instance = null;
        instance.setRefusedBy1(refusedBy1);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isRefusedBy2 method, of class CoachMatch.
     */
    @Test
    public void testIsRefusedBy2() {
        System.out.println("isRefusedBy2");
        CoachMatch instance = null;
        boolean expResult = false;
        boolean result = instance.isRefusedBy2();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setRefusedBy2 method, of class CoachMatch.
     */
    @Test
    public void testSetRefusedBy2() {
        System.out.println("setRefusedBy2");
        boolean refusedBy2 = false;
        CoachMatch instance = null;
        instance.setRefusedBy2(refusedBy2);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isConcedeedBy1 method, of class CoachMatch.
     */
    @Test
    public void testIsConcedeedBy1() {
        System.out.println("isConcedeedBy1");
        CoachMatch instance = null;
        boolean expResult = false;
        boolean result = instance.isConcedeedBy1();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setConcedeedBy1 method, of class CoachMatch.
     */
    @Test
    public void testSetConcedeedBy1() {
        System.out.println("setConcedeedBy1");
        boolean concedeedBy1 = false;
        CoachMatch instance = null;
        instance.setConcedeedBy1(concedeedBy1);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isConcedeedBy2 method, of class CoachMatch.
     */
    @Test
    public void testIsConcedeedBy2() {
        System.out.println("isConcedeedBy2");
        CoachMatch instance = null;
        boolean expResult = false;
        boolean result = instance.isConcedeedBy2();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setConcedeedBy2 method, of class CoachMatch.
     */
    @Test
    public void testSetConcedeedBy2() {
        System.out.println("setConcedeedBy2");
        boolean concedeedBy2 = false;
        CoachMatch instance = null;
        instance.setConcedeedBy2(concedeedBy2);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getValue method, of class CoachMatch.
     */
    @Test
    public void testGetValue() {
        System.out.println("getValue");
        Criteria c = null;
        CoachMatch instance = null;
        Value expResult = null;
        Value result = instance.getValue(c);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getValueCount method, of class CoachMatch.
     */
    @Test
    public void testGetValueCount() {
        System.out.println("getValueCount");
        CoachMatch instance = null;
        int expResult = 0;
        int result = instance.getValueCount();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of putValue method, of class CoachMatch.
     */
    @Test
    public void testPutValue() {
        System.out.println("putValue");
        Criteria c = null;
        Value v = null;
        CoachMatch instance = null;
        instance.putValue(c, v);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeValue method, of class CoachMatch.
     */
    @Test
    public void testRemoveValue() {
        System.out.println("removeValue");
        Criteria c = null;
        CoachMatch instance = null;
        instance.removeValue(c);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getXMLElementForDisplay method, of class CoachMatch.
     */
    @Test
    public void testGetXMLElementForDisplay() {
        System.out.println("getXMLElementForDisplay");
        CoachMatch instance = null;
        Element expResult = null;
        Element result = instance.getXMLElementForDisplay();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setXMLElementForDisplay method, of class CoachMatch.
     */
    @Test
    public void testSetXMLElementForDisplay() {
        System.out.println("setXMLElementForDisplay");
        Element element = null;
        CoachMatch instance = null;
        instance.setXMLElementForDisplay(element);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isFullNaf method, of class CoachMatch.
     */
    @Test
    public void testIsFullNaf() {
        System.out.println("isFullNaf");
        CoachMatch instance = null;
        boolean expResult = false;
        boolean result = instance.isFullNaf();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
