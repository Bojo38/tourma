/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import javax.swing.ImageIcon;
import org.jdom.Element;
import org.testng.Assert;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
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
        Tournament.getTournament().loadXML(new File("./test/clan.xml"));
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
        Assert.assertTrue(Tournament.getTournament().getClansCount() > 1);
        for (int i = 0; i < Tournament.getTournament().getClansCount(); i++) {
            Clan c = Tournament.getTournament().getClan(i);
            Assert.assertNotNull(Clan.getClan(c.getName()));
        }
    }

    /**
     * Test of putClan method, of class Clan.
     */
    @Test
    public void testPutClan() {
        System.out.println("putClan");
        String s = "TestClan";
        Clan c = new Clan(s);
        Clan.putClan(s, c);
        Clan tmp = Clan.getClan(s);
        assertEquals(c, tmp);
        Clan.delClan(s);
        tmp = Clan.getClan(s);
        Assert.assertNull(tmp);
    }

    /**
     * Test of newClanMap method, of class Clan.
     */
    @Test
    public void testNewClanMap() {
        System.out.println("newClanMap");
        ArrayList<Clan> list = new ArrayList<>();
        for (int i = 0; i < Tournament.getTournament().getClansCount(); i++) {
            Clan c = Tournament.getTournament().getClan(i);
            list.add(c);
        }
        Clan.newClanMap();
        for (int i = 0; i < Tournament.getTournament().getClansCount(); i++) {
            Clan c = Tournament.getTournament().getClan(i);
            Clan tmp = Clan.getClan(c.getName());
            Assert.assertNull(tmp);
            Clan.putClan(c.getName(), c);
            tmp = Clan.getClan(c.getName());
            Assert.assertEquals(c, tmp);
        }
    }

    /**
     * Test of equals method, of class Clan.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        String s = "TestClan";
        Clan c = new Clan(s);
        Clan c2 = new Clan(s);
        Clan c3 = new Clan(s + "2");

        Assert.assertTrue(c.equals(c2));
        Assert.assertFalse(c.equals(c3));
    }

    /**
     * Test of hashCode method, of class Clan.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        String s = "TestCategory";
        for (int i = 0; i < Tournament.getTournament().getClansCount(); i++) {
            Clan c = Tournament.getTournament().getClan(i);
            int h = 7 * 11 + Objects.hashCode(c.getName());
            h = 11 * h + Objects.hashCode(c.getPicture());
            assertEquals(h, c.hashCode());
        }

    }

    /**
     * Test of compareTo method, of class Clan.
     */
    @Test
    public void testCompareTo() {
        System.out.println("compareTo");
        Assert.assertTrue(Tournament.getTournament().getClansCount() > 1);
        for (int i = 0; i < Tournament.getTournament().getClansCount(); i++) {
            Clan c1 = Tournament.getTournament().getClan(i);
            for (int j = 0; j < Tournament.getTournament().getClansCount(); j++) {
                Clan c2 = Tournament.getTournament().getClan(j);
                assertEquals(c1.compareTo(c2), c1.getName().compareTo(c2.getName()));
            }
        }
    }

    /**
     * Test of getXMLElement method, of class Clan.
     */
    @Test
    public void testGetXMLElement() {
        System.out.println("getXMLElement");
        Clan instance = new Clan("Test");
        Element result = instance.getXMLElement();

        Clan cat = new Clan("None");
        cat.setXMLElement(result);
        assertEquals(instance, cat);
    }

    /**
     * Test of setXMLElement method, of class Clan.
     */
    @Test
    public void testSetXMLElement() {
        System.out.println("setXMLElement");
        Clan instance = new Clan("Test");
        Element result = instance.getXMLElement();

        Clan cat = new Clan("None");
        cat.setXMLElement(result);
        assertEquals(instance, cat);
    }

    /**
     * Test of getName method, of class Clan.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        String s = "TestClan";
        Clan c = new Clan(s);
        assertEquals(c.getName(), s);
    }

    /**
     * Test of getPicture method, of class Clan.
     */
    @Test
    public void testGetPicture() {
        System.out.println("getPicture");
        ImageIcon icon = new ImageIcon("./test/clan.png");
        BufferedImage p = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
        Clan instance = new Clan("Test");
        instance.setPicture(icon);

        assertNotNull(instance.getPicture());
        assertEquals(icon,instance.getPicture());
    }

    /**
     * Test of setPicture method, of class Clan.
     */
    @Test
    public void testSetPicture() {
        System.out.println("setPicture");

        ImageIcon icon = new ImageIcon("./test/clan.png");
        BufferedImage p = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
        Clan instance = new Clan("Test");
        instance.setPicture(icon);

        assertNotNull(instance.getPicture());
        assertEquals(icon,instance.getPicture());
    }

    /**
     * Test of setName method, of class Clan.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String s = "TestClan";
        Clan c = new Clan(s);
        assertEquals(c.getName(), s);
        c.setName("Test");
        assertEquals(c.getName(), "Test");
    }

    /**
     * Test of toString method, of class Clan.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        String s = "TestClan";
        Clan c = new Clan(s);
        assertEquals(c.getName(), s);
    }

    /**
     * Test of delClan method, of class Clan.
     */
    @Test
    public void testDelClan() {
        System.out.println("delClan");
        String s = "TestClan";
        Clan c = new Clan(s);
        Clan.putClan(s, c);
        Clan tmp = Clan.getClan(s);
        assertEquals(c, tmp);
        Clan.delClan(s);
        tmp = Clan.getClan(s);
        Assert.assertNull(tmp);
    }

    /**
     * Test of isUpdated method, of class Clan.
     */
    @Test
    public void testIsUpdated() {
        System.out.println("isUpdated");
        Clan instance = new Clan("Clan");
        boolean expResult = false;
        boolean result = instance.isUpdated();
        assertEquals(result, expResult);
    }

    /**
     * Test of setUpdated method, of class Clan.
     */
    @Test
    public void testSetUpdated() {
        System.out.println("setUpdated");
        boolean updated = false;
        Clan instance = new Clan("Clan");;
        instance.setUpdated(updated);
        boolean result = instance.isUpdated();
       assertEquals(result, updated);
       
        updated = true;
        
        instance.setUpdated(updated);
        result = instance.isUpdated();
       assertEquals(result, updated);
    }

    /**
     * Test of getUID method, of class Clan.
     */
    @Test
    public void testGetUID() {
        System.out.println("getUID");
          Random ran=new Random(1000);
        int UID = ran.nextInt();
        Clan instance = new Clan("UID");
        instance.setUID(UID);
        assertEquals(UID, instance.getUID());
    }

    /**
     * Test of setUID method, of class Clan.
     */
    @Test
    public void testSetUID() {
         Random ran=new Random(1000);
        int UID = ran.nextInt();
        Clan instance = new Clan("UID");
        instance.setUID(UID);
        assertEquals(UID, instance.getUID());
    }

    /**
     * Test of pull method, of class Clan.
     */
    @Test
    public void testPull() {
        System.out.println("pull");
       
    }

    /**
     * Test of push method, of class Clan.
     */
    @Test
    public void testPush() {
        System.out.println("push");

    }

}
