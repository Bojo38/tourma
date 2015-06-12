/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import java.awt.image.BufferedImage;
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
public class ClanNGTest {
    
    public ClanNGTest() {
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
     * Test of getClan method, of class Clan.
     */
    @Test
    public void testGetClan() {
        System.out.println("getClan");
        String key = "";
        Clan expResult = null;
        Clan result = Clan.getClan(key);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of putClan method, of class Clan.
     */
    @Test
    public void testPutClan() {
        System.out.println("putClan");
        String key = "";
        Clan c = null;
        Clan.putClan(key, c);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of newClanMap method, of class Clan.
     */
    @Test
    public void testNewClanMap() {
        System.out.println("newClanMap");
        Clan.newClanMap();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class Clan.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = null;
        Clan instance = null;
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hashCode method, of class Clan.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Clan instance = null;
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of compareTo method, of class Clan.
     */
    @Test
    public void testCompareTo() {
        System.out.println("compareTo");
        Object obj = null;
        Clan instance = null;
        int expResult = 0;
        int result = instance.compareTo(obj);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getXMLElement method, of class Clan.
     */
    @Test
    public void testGetXMLElement() {
        System.out.println("getXMLElement");
        Clan instance = null;
        Element expResult = null;
        Element result = instance.getXMLElement();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setXMLElement method, of class Clan.
     */
    @Test
    public void testSetXMLElement() {
        System.out.println("setXMLElement");
        Element e = null;
        Clan instance = null;
        instance.setXMLElement(e);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getName method, of class Clan.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Clan instance = null;
        String expResult = "";
        String result = instance.getName();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPicture method, of class Clan.
     */
    @Test
    public void testGetPicture() {
        System.out.println("getPicture");
        Clan instance = null;
        BufferedImage expResult = null;
        BufferedImage result = instance.getPicture();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPicture method, of class Clan.
     */
    @Test
    public void testSetPicture() {
        System.out.println("setPicture");
        BufferedImage p = null;
        Clan instance = null;
        instance.setPicture(p);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setName method, of class Clan.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "";
        Clan instance = null;
        instance.setName(name);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Clan.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Clan instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
