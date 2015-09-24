/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import org.jdom2.Element;
import org.testng.Assert;
import static org.testng.Assert.assertEquals;
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
        Group g = new Group("Test");
        GroupPoints gp = new GroupPoints();
        gp.setDrawPoints(1);
        gp.setLossPoints(2);
        gp.setVictoryPoints(3);
        Group instance = new Group("Test2");
        
        RosterType.addRosterName("abc");
        RosterType.addRosterName("def");
        RosterType rt1=new RosterType("abc");
        RosterType rt2=new RosterType("def");
        RosterType.putRosterType("abc", rt1);
        RosterType.putRosterType("def", rt2);
        instance.addRoster(rt1);
        instance.addRoster(rt2);
         
        
        instance.setOpponentModificationPoints(g, gp);

        Element result = instance.getXMLElement();

        Group g2 = new Group("None");
        g2.setXMLElement(result);
        assertEquals(instance, g2);
    }

    /**
     * Test of setXMLElement method, of class Group.
     */
    @Test
    public void testSetXMLElement() {
        System.out.println("setXMLElement");
      Group g = new Group("Test");
        GroupPoints gp = new GroupPoints();
        gp.setDrawPoints(1);
        gp.setLossPoints(2);
        gp.setVictoryPoints(3);
        Group instance = new Group("Test2");
        
        RosterType.addRosterName("abc");
        RosterType.addRosterName("def");
        RosterType rt1=new RosterType("abc");
        RosterType rt2=new RosterType("def");
        RosterType.putRosterType("abc", rt1);
        RosterType.putRosterType("def", rt2);
        instance.addRoster(rt1);
        instance.addRoster(rt2);
         
        
        instance.setOpponentModificationPoints(g, gp);

        Element result = instance.getXMLElement();

        Group g2 = new Group("None");
        g2.setXMLElement(result);
        assertEquals(instance, g2);
    }

    /**
     * Test of getName method, of class Group.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Group instance=new Group("Test");
        instance.setName("Test2");
        assertEquals(instance.getName(),"Test2");
    }

    /**
     * Test of setName method, of class Group.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        Group instance=new Group("Test");
        instance.setName("Test2");
        assertEquals(instance.getName(),"Test2");
    }

    /**
     * Test of getRoster method, of class Group.
     */
    @Test
    public void testGetRoster() {
        System.out.println("getRoster");
       Group instance=new Group("Test");
        RosterType rt=new RosterType("abc");
        assertEquals(instance.getRoster(0),null);
        instance.addRoster(rt);
        assertEquals(instance.getRoster(0),rt);
    }

    /**
     * Test of getRosterCount method, of class Group.
     */
    @Test
    public void testGetRosterCount() {
        System.out.println("getRosterCount");
       Group instance=new Group("Test");
        RosterType rt=new RosterType("abc");
        instance.addRoster(rt);
        assertEquals(instance.getRosterCount(),1);
        instance.removeRoster(rt);
        assertEquals(instance.getRosterCount(),0);
    }

    /**
     * Test of addRoster method, of class Group.
     */
    @Test
    public void testAddRoster() {
        System.out.println("addRoster");
       Group instance=new Group("Test");
        RosterType rt=new RosterType("abc");
        instance.addRoster(rt);
        assertEquals(instance.getRosterCount(),1);
        instance.removeRoster(rt);
        assertEquals(instance.getRosterCount(),0);
    }

    /**
     * Test of removeRoster method, of class Group.
     */
    @Test
    public void testRemoveRoster() {
        System.out.println("removeRoster");
        Group instance=new Group("Test");
        RosterType rt=new RosterType("abc");
        instance.addRoster(rt);
        assertEquals(instance.getRosterCount(),1);
        instance.removeRoster(rt);
        assertEquals(instance.getRosterCount(),0);
    }

    /**
     * Test of newRosters method, of class Group.
     */
    @Test
    public void testNewRosters() {
        System.out.println("newRosters");
        Group instance=new Group("Test");
        RosterType rt=new RosterType("abc");
        instance.addRoster(rt);
        assertEquals(instance.getRosterCount(),1);
        instance.newRosters();
        assertEquals(instance.getRosterCount(),0);
    }

    /**
     * Test of getXMLElementForPoints method, of class Group.
     */
    @Test
    public void testGetXMLElementForPoints() {
        System.out.println("getXMLElementForPoints");
        Group g = new Group("Test");
        GroupPoints gp = new GroupPoints();
        gp.setDrawPoints(1);
        gp.setLossPoints(2);
        gp.setVictoryPoints(3);
        Group instance = new Group("Test2");
        
        RosterType.addRosterName("abc");
        RosterType.addRosterName("def");
        RosterType rt1=new RosterType("abc");
        RosterType rt2=new RosterType("def");
        RosterType.putRosterType("abc", rt1);
        RosterType.putRosterType("def", rt2);
        instance.addRoster(rt1);
        instance.addRoster(rt2);
         
        Tournament.getTournament().addGroup(g);
        Tournament.getTournament().addGroup(instance);
        
        instance.setOpponentModificationPoints(g, gp);
        
        Element result1 = instance.getXMLElement();
        Element result2 = instance.getXMLElementForPoints();

        Group g2 = new Group("None");
        g2.setXMLElement(result1);
        g2.setXMLElementForPoints(result2);
        assertEquals(instance, g2);
        
        Tournament.getTournament().removeGroup(g);
        Tournament.getTournament().removeGroup(instance);
    }

    /**
     * Test of setXMLElementForPoints method, of class Group.
     */
    @Test
    public void testSetXMLElementForPoints() {
        System.out.println("setXMLElementForPoints");
       Group g = new Group("Test");
        GroupPoints gp = new GroupPoints();
        gp.setDrawPoints(1);
        gp.setLossPoints(2);
        gp.setVictoryPoints(3);
        Group instance = new Group("Test2");
        
        RosterType.addRosterName("abc");
        RosterType.addRosterName("def");
        RosterType rt1=new RosterType("abc");
        RosterType rt2=new RosterType("def");
        RosterType.putRosterType("abc", rt1);
        RosterType.putRosterType("def", rt2);
        instance.addRoster(rt1);
        instance.addRoster(rt2);
         
        Tournament.getTournament().addGroup(g);
        Tournament.getTournament().addGroup(instance);
        
        instance.setOpponentModificationPoints(g, gp);
        
        Element result1 = instance.getXMLElement();
        Element result2 = instance.getXMLElementForPoints();

        Group g2 = new Group("None");
        g2.setXMLElement(result1);
        g2.setXMLElementForPoints(result2);
        assertEquals(instance, g2);
        
        Tournament.getTournament().removeGroup(g);
        Tournament.getTournament().removeGroup(instance);
    }

    /**
     * Test of getOpponentModificationPoints method, of class Group.
     */
    @Test
    public void testGetOpponentModificationPoints() {
        System.out.println("getOpponentModificationPoints");
         Group g = new Group("Test");
        GroupPoints gp = new GroupPoints();
        gp.setDrawPoints(1);
        gp.setLossPoints(2);
        gp.setVictoryPoints(3);
        Group instance = new Group("Test2");
        instance.setOpponentModificationPoints(g, gp);

        GroupPoints result=instance.getOpponentModificationPoints(g);
        assertEquals(result.getDrawPoints(), 1);
        assertEquals(result.getLossPoints(), 2);
        assertEquals(result.getVictoryPoints(), 3);
    }

    /**
     * Test of setOpponentModificationPoints method, of class Group.
     */
    @Test
    public void testSetOpponentModificationPoints() {
        System.out.println("setOpponentModificationPoints");
        Group g = new Group("Test");
        GroupPoints gp = new GroupPoints();
        gp.setDrawPoints(1);
        gp.setLossPoints(2);
        gp.setVictoryPoints(3);
        Group instance = new Group("Test2");
        instance.setOpponentModificationPoints(g, gp);

        GroupPoints result=instance.getOpponentModificationPoints(g);
        assertEquals(result.getDrawPoints(), 1);
        assertEquals(result.getLossPoints(), 2);
        assertEquals(result.getVictoryPoints(), 3);
        
    }

    /**
     * Test of delOpponentModificationPoints method, of class Group.
     */
    @Test
    public void testDelOpponentModificationPoints() {
        System.out.println("delOpponentModificationPoints");
        Group g = new Group("Test");
        GroupPoints gp = new GroupPoints();
        gp.setDrawPoints(1);
        gp.setLossPoints(2);
        gp.setVictoryPoints(3);
        Group instance = new Group("Test2");
        instance.setOpponentModificationPoints(g, gp);

        GroupPoints result=instance.getOpponentModificationPoints(g);
        assertEquals(result.getDrawPoints(), 1);
        assertEquals(result.getLossPoints(), 2);
        assertEquals(result.getVictoryPoints(), 3);
        
        instance.delOpponentModificationPoints(g);
        result=instance.getOpponentModificationPoints(g);
        Assert.assertNull(result);
        
    }

    /**
     * Test of containsRoster method, of class Group.
     */
    @Test
    public void testContainsRoster() {
        System.out.println("containsRoster");
         Group instance=new Group("Test");
        RosterType rt=new RosterType("abc");
        Assert.assertFalse(instance.containsRoster(rt));
        instance.addRoster(rt);
        Assert.assertTrue(instance.containsRoster(rt));
    }
    
}
