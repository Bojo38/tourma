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
public class GroupNGTest {
    
    public GroupNGTest() {
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
     * Test of getXMLElement method, of class Group.
     */
    @Test
    public void testGetXMLElement() {
        System.out.println("getXMLElement");
        Group instance = null;
        Element expResult = null;
        Element result = instance.getXMLElement();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setXMLElement method, of class Group.
     */
    @Test
    public void testSetXMLElement() {
        System.out.println("setXMLElement");
        Element group = null;
        Group instance = null;
        instance.setXMLElement(group);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getName method, of class Group.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Group instance = null;
        String expResult = "";
        String result = instance.getName();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setName method, of class Group.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String mName = "";
        Group instance = null;
        instance.setName(mName);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRoster method, of class Group.
     */
    @Test
    public void testGetRoster() {
        System.out.println("getRoster");
        int i = 0;
        Group instance = null;
        RosterType expResult = null;
        RosterType result = instance.getRoster(i);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRosterCount method, of class Group.
     */
    @Test
    public void testGetRosterCount() {
        System.out.println("getRosterCount");
        Group instance = null;
        int expResult = 0;
        int result = instance.getRosterCount();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addRoster method, of class Group.
     */
    @Test
    public void testAddRoster() {
        System.out.println("addRoster");
        RosterType rt = null;
        Group instance = null;
        instance.addRoster(rt);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeRoster method, of class Group.
     */
    @Test
    public void testRemoveRoster() {
        System.out.println("removeRoster");
        RosterType rt = null;
        Group instance = null;
        instance.removeRoster(rt);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of newRosters method, of class Group.
     */
    @Test
    public void testNewRosters() {
        System.out.println("newRosters");
        Group instance = null;
        instance.newRosters();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getXMLElementForPoints method, of class Group.
     */
    @Test
    public void testGetXMLElementForPoints() {
        System.out.println("getXMLElementForPoints");
        Group instance = null;
        Element expResult = null;
        Element result = instance.getXMLElementForPoints();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setXMLElementForPoints method, of class Group.
     */
    @Test
    public void testSetXMLElementForPoints() {
        System.out.println("setXMLElementForPoints");
        Element group = null;
        Group instance = null;
        instance.setXMLElementForPoints(group);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOpponentModificationPoints method, of class Group.
     */
    @Test
    public void testGetOpponentModificationPoints() {
        System.out.println("getOpponentModificationPoints");
        Group g = null;
        Group instance = null;
        GroupPoints expResult = null;
        GroupPoints result = instance.getOpponentModificationPoints(g);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setOpponentModificationPoints method, of class Group.
     */
    @Test
    public void testSetOpponentModificationPoints() {
        System.out.println("setOpponentModificationPoints");
        Group g = null;
        GroupPoints gp = null;
        Group instance = null;
        instance.setOpponentModificationPoints(g, gp);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delOpponentModificationPoints method, of class Group.
     */
    @Test
    public void testDelOpponentModificationPoints() {
        System.out.println("delOpponentModificationPoints");
        Group g = null;
        Group instance = null;
        instance.delOpponentModificationPoints(g);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of containsRoster method, of class Group.
     */
    @Test
    public void testContainsRoster() {
        System.out.println("containsRoster");
        RosterType rt = null;
        Group instance = null;
        boolean expResult = false;
        boolean result = instance.containsRoster(rt);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
