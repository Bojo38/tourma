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
public class CriteriaNGTest {
    
    public CriteriaNGTest() {
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
     * Test of getXMLElement method, of class Criteria.
     */
    @Test
    public void testGetXMLElement() {
        System.out.println("getXMLElement");
        Criteria instance = null;
        Element expResult = null;
        Element result = instance.getXMLElement();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setXMLElement method, of class Criteria.
     */
    @Test
    public void testSetXMLElement() {
        System.out.println("setXMLElement");
        Element criteria = null;
        Criteria instance = null;
        instance.setXMLElement(criteria);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getName method, of class Criteria.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Criteria instance = null;
        String expResult = "";
        String result = instance.getName();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setName method, of class Criteria.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String mName = "";
        Criteria instance = null;
        instance.setName(mName);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPointsFor method, of class Criteria.
     */
    @Test
    public void testGetPointsFor() {
        System.out.println("getPointsFor");
        Criteria instance = null;
        int expResult = 0;
        int result = instance.getPointsFor();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPointsFor method, of class Criteria.
     */
    @Test
    public void testSetPointsFor() {
        System.out.println("setPointsFor");
        int mPointsFor = 0;
        Criteria instance = null;
        instance.setPointsFor(mPointsFor);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPointsAgainst method, of class Criteria.
     */
    @Test
    public void testGetPointsAgainst() {
        System.out.println("getPointsAgainst");
        Criteria instance = null;
        int expResult = 0;
        int result = instance.getPointsAgainst();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPointsAgainst method, of class Criteria.
     */
    @Test
    public void testSetPointsAgainst() {
        System.out.println("setPointsAgainst");
        int mPointsAgainst = 0;
        Criteria instance = null;
        instance.setPointsAgainst(mPointsAgainst);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPointsTeamFor method, of class Criteria.
     */
    @Test
    public void testGetPointsTeamFor() {
        System.out.println("getPointsTeamFor");
        Criteria instance = null;
        int expResult = 0;
        int result = instance.getPointsTeamFor();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPointsTeamFor method, of class Criteria.
     */
    @Test
    public void testSetPointsTeamFor() {
        System.out.println("setPointsTeamFor");
        int mPointsTeamFor = 0;
        Criteria instance = null;
        instance.setPointsTeamFor(mPointsTeamFor);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPointsTeamAgainst method, of class Criteria.
     */
    @Test
    public void testGetPointsTeamAgainst() {
        System.out.println("getPointsTeamAgainst");
        Criteria instance = null;
        int expResult = 0;
        int result = instance.getPointsTeamAgainst();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPointsTeamAgainst method, of class Criteria.
     */
    @Test
    public void testSetPointsTeamAgainst() {
        System.out.println("setPointsTeamAgainst");
        int mPointsTeamAgainst = 0;
        Criteria instance = null;
        instance.setPointsTeamAgainst(mPointsTeamAgainst);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
