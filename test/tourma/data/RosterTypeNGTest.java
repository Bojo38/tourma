/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import java.util.HashMap;
import javax.swing.DefaultComboBoxModel;
import org.jdom.Element;
import org.testng.Assert;
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
        Assert.assertTrue(RosterType.getRostersNamesCount() > 0);
    }

    /**
     * Test of initCollection method, of class RosterType.
     */
    @Test
    public void testInitCollection_int() {
        System.out.println("initCollection");
        int game = 0;
        RosterType.initCollection(game);
        Assert.assertTrue(RosterType.getRostersNamesCount() > 0);
        game = 1;
        RosterType.initCollection(game);
        Assert.assertTrue(RosterType.getRostersNamesCount() > 0);
    }

    /**
     * Test of getRosterName method, of class RosterType.
     */
    @Test
    public void testGetRosterName() {
        System.out.println("getRosterName");
        RosterType.initCollection();
        String name = "Amazone";
        String expResult = "Amazones";
        String result = RosterType.getRosterName(name);
        assertEquals(result, expResult);
    }

    /**
     * Test of getRosterType method, of class RosterType.
     */
    @Test
    public void testGetRosterType_String() {
        System.out.println("getRosterType");
        RosterType.initCollection();
        String r = "Amazones";
        RosterType expResult = RosterType.getRosterType(0);
        RosterType result = RosterType.getRosterType(r);
        assertEquals(result, expResult);
    }

    /**
     * Test of getRosterType method, of class RosterType.
     */
    @Test
    public void testGetRosterType_int() {
        System.out.println("getRosterType");
        RosterType.initCollection();
        String r = "Amazones";
        RosterType expResult = RosterType.getRosterType(0);
        RosterType result = RosterType.getRosterType(r);
        assertEquals(result, expResult);
    }

    /**
     * Test of putRosterType method, of class RosterType.
     */
    @Test
    public void testPutRosterType() {
        System.out.println("putRosterType");
        RosterType.initCollection();
        String n = "Test";
        RosterType r = new RosterType("Test");
        RosterType.putRosterType(n, r);
        RosterType expResult = RosterType.getRosterType(n);
        assertEquals(r, expResult);
    }

    /**
     * Test of addRosterName method, of class RosterType.
     */
    @Test
    public void testAddRosterName() {
        System.out.println("addRosterName");
        RosterType.initCollection();
        String n = "Name";
        int nb = RosterType.getRostersNamesCount();
        RosterType.addRosterName(n);
        assertEquals(nb + 1, RosterType.getRostersNamesCount());
    }

    /**
     * Test of getRostersNamesCount method, of class RosterType.
     */
    @Test
    public void testGetRostersNamesCount() {
        System.out.println("getRostersNamesCount");
        RosterType.initCollection();
        String n = "Name";
        int nb = RosterType.getRostersNamesCount();
        RosterType.addRosterName(n);
        assertEquals(nb + 1, RosterType.getRostersNamesCount());
    }

    /**
     * Test of getRostersName method, of class RosterType.
     */
    @Test
    public void testGetRostersName() {
        System.out.println("getRostersName");
        RosterType.initCollection();
        for (int i = 0; i < RosterType.getRostersNamesCount(); i++) {
            String name = RosterType.getRostersName(i);
            RosterType rt = RosterType.getRosterType(i);

            assertEquals(name, rt.getName());
        }
    }

    /**
     * Test of newRostersNames method, of class RosterType.
     */
    @Test
    public void testNewRostersNames() {
        System.out.println("newRostersNames");
        RosterType.initCollection();
        RosterType.newRostersNames();
        Assert.assertTrue(RosterType.getRostersNamesCount() == 0);
    }

    /**
     * Test of newRostersTypes method, of class RosterType.
     */
    @Test
    public void testNewRostersTypes() {
        System.out.println("newRostersTypes");
        RosterType.initCollection();
        RosterType.newRostersTypes();
        Assert.assertTrue(RosterType.getRosterType(0) == null);
    }

    /**
     * Test of getRostersNamesModel method, of class RosterType.
     */
    @Test
    public void testGetRostersNamesModel() {
        System.out.println("getRostersNamesModel");
        RosterType.initCollection();
        DefaultComboBoxModel<String> result = RosterType.getRostersNamesModel();
        String[] arr = RosterType.getRostersNames();
        for (int i = 0; i < arr.length; i++) {
            assertEquals(arr[i], result.getElementAt(i));
        }
    }

    /**
     * Test of getRostersNames method, of class RosterType.
     */
    @Test
    public void testGetRostersNames() {
        System.out.println("getRostersNames");
        RosterType.initCollection();
        String[] result = RosterType.getRostersNames();
        for (int i = 0; i < result.length; i++) {
            assertEquals(result[i], RosterType.getRostersName(i));
        }
    }

    /**
     * Test of getXMLElement method, of class RosterType.
     */
    @Test
    public void testGetXMLElement() {
        System.out.println("getXMLElement");
        RosterType.initCollection();

        for (int i = 0; i < RosterType.getRostersNamesCount(); i++) {
            RosterType instance = RosterType.getRosterType(i);
            Element result = instance.getXMLElement();
            RosterType rt = new RosterType("abc");
            rt.setXMLElement(result);
            assertEquals(instance.getName(), rt.getName());
        }
    }

    /**
     * Test of setXMLElement method, of class RosterType.
     */
    @Test
    public void testSetXMLElement() {
        System.out.println("setXMLElement");
        RosterType.initCollection();

        for (int i = 0; i < RosterType.getRostersNamesCount(); i++) {
            RosterType instance = RosterType.getRosterType(i);
            Element result = instance.getXMLElement();
            RosterType rt = new RosterType("abc");
            rt.setXMLElement(result);
            assertEquals(instance.getName(), rt.getName());
        }
    }

    /**
     * Test of getName method, of class RosterType.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        RosterType instance = new RosterType("abc");
        String expResult = "Test";
        instance.setName(expResult);
        String result = instance.getName();
        assertEquals(result, expResult);

    }

    /**
     * Test of setName method, of class RosterType.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        RosterType instance = new RosterType("abc");
        String expResult = "Test";
        instance.setName(expResult);
        String result = instance.getName();
        assertEquals(result, expResult);
    }

    /**
     * Test of translate method, of class RosterType.
     */
    @Test
    public void testTranslate() {
        System.out.println("translate");
        String key = "AmazonKey";
        String expResult = "Amazones";
        String result = RosterType.translate(key);
        assertEquals(result, expResult);
    }

    /**
     * Test of getRosterTranslation method, of class RosterType.
     */
    @Test
    public void testGetRosterTranslation() {
        System.out.println("getRosterTranslation");
        String source = "Amazones";
        String expResult = "Amazons";
        String result = RosterType.getRosterTranslation(source);
        assertEquals(result, expResult);
    }

    /**
     * Test of getUID method, of class RosterType.
     */
    @Test
    public void testGetUID() {
        System.out.println("getUID");
        RosterType instance = null;
        int expResult = 0;
        int result = instance.getUID();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setUID method, of class RosterType.
     */
    @Test
    public void testSetUID() {
        System.out.println("setUID");
        int UID = 0;
        RosterType instance = null;
        instance.setUID(UID);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRosters method, of class RosterType.
     */
    @Test
    public void testGetRosters() {
        System.out.println("getRosters");
        HashMap expResult = null;
        HashMap result = RosterType.getRosters();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of pull method, of class RosterType.
     */
    @Test
    public void testPull() {
        System.out.println("pull");
        HashMap<String, RosterType> types = null;
        RosterType.pull(types);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
