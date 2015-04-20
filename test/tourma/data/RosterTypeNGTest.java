/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import javax.swing.DefaultComboBoxModel;
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
public class RosterTypeNGTest {
    
    public RosterTypeNGTest() {
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
     * Test of initCollection method, of class RosterType.
     */
    @Test
    public void testInitCollection_0args() {
        System.out.println("initCollection");
        RosterType.initCollection();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of initCollection method, of class RosterType.
     */
    @Test
    public void testInitCollection_int() {
        System.out.println("initCollection");
        int game = 0;
        RosterType.initCollection(game);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRosterName method, of class RosterType.
     */
    @Test
    public void testGetRosterName() {
        System.out.println("getRosterName");
        String name = "";
        String expResult = "";
        String result = RosterType.getRosterName(name);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRosterType method, of class RosterType.
     */
    @Test
    public void testGetRosterType_String() {
        System.out.println("getRosterType");
        String r = "";
        RosterType expResult = null;
        RosterType result = RosterType.getRosterType(r);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRosterType method, of class RosterType.
     */
    @Test
    public void testGetRosterType_int() {
        System.out.println("getRosterType");
        int i = 0;
        RosterType expResult = null;
        RosterType result = RosterType.getRosterType(i);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of putRosterType method, of class RosterType.
     */
    @Test
    public void testPutRosterType() {
        System.out.println("putRosterType");
        String n = "";
        RosterType r = null;
        RosterType.putRosterType(n, r);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addRosterName method, of class RosterType.
     */
    @Test
    public void testAddRosterName() {
        System.out.println("addRosterName");
        String n = "";
        RosterType.addRosterName(n);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRostersNamesCount method, of class RosterType.
     */
    @Test
    public void testGetRostersNamesCount() {
        System.out.println("getRostersNamesCount");
        int expResult = 0;
        int result = RosterType.getRostersNamesCount();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRostersName method, of class RosterType.
     */
    @Test
    public void testGetRostersName() {
        System.out.println("getRostersName");
        int i = 0;
        String expResult = "";
        String result = RosterType.getRostersName(i);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of newRostersNames method, of class RosterType.
     */
    @Test
    public void testNewRostersNames() {
        System.out.println("newRostersNames");
        RosterType.newRostersNames();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of newRostersTypes method, of class RosterType.
     */
    @Test
    public void testNewRostersTypes() {
        System.out.println("newRostersTypes");
        RosterType.newRostersTypes();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRostersNamesModel method, of class RosterType.
     */
    @Test
    public void testGetRostersNamesModel() {
        System.out.println("getRostersNamesModel");
        DefaultComboBoxModel expResult = null;
        DefaultComboBoxModel result = RosterType.getRostersNamesModel();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRostersNames method, of class RosterType.
     */
    @Test
    public void testGetRostersNames() {
        System.out.println("getRostersNames");
        String[] expResult = null;
        String[] result = RosterType.getRostersNames();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getXMLElement method, of class RosterType.
     */
    @Test
    public void testGetXMLElement() {
        System.out.println("getXMLElement");
        RosterType instance = null;
        Element expResult = null;
        Element result = instance.getXMLElement();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setXMLElement method, of class RosterType.
     */
    @Test
    public void testSetXMLElement() {
        System.out.println("setXMLElement");
        Element e = null;
        RosterType instance = null;
        instance.setXMLElement(e);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getName method, of class RosterType.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        RosterType instance = null;
        String expResult = "";
        String result = instance.getName();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setName method, of class RosterType.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String mName = "";
        RosterType instance = null;
        instance.setName(mName);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
