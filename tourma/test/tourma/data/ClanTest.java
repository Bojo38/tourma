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
public class ClanTest {

    public ClanTest() {
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
     * Test of compareTo method, of class Clan.
     */
    @Test
    public void testCompareTo() {
        System.out.println("compareTo");
        Clan instance1 = new Clan("a");
        Clan instance2 = new Clan("b");
        int result = instance1.compareTo(instance2);
        assertEquals(result, -1);
    }

    /**
     * Test of getXMLElement method, of class Clan.
     */
    @Test
    public void testGetXMLElement() {
        System.out.println("getXMLElement");
        Clan instance = new Clan("clan");
        Element result = instance.getXMLElement();
        assertEquals(result.getAttribute("Name").getValue(), "clan");
    }

    /**
     * Test of setXMLElement method, of class Clan.
     */
    @Test
    public void testSetXMLElement() {
        System.out.println("setXMLElement");
        Clan instance = new Clan("toto");
        Element e = new Element("Clan");
        e.setAttribute("Name", "nouveau nom");
        instance.setXMLElement(e);

         System.out.println("setXMLElement: "+ instance.mName);
        // TODO review the generated test code and remove the default call to fail.
        assertTrue(instance.mName.equals("nouveau nom"));
    }
}
