/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teamma.data;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class LRBNGTest {

    private static final Logger LOG = Logger.getLogger(LRBNGTest.class.getName());

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    public LRBNGTest() {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }

    /**
     * Test of getLRB method, of class LRB.
     */
    @Test
    public void testGetLRB() {
        LOG.log(Level.FINE, "getLRB");
        LRB result = LRB.getLRB();
        Assert.assertNotNull(result);
        Assert.assertNotEquals(result.getRosterTypeCount(), 0);
        Assert.assertNotEquals(result.getSkillTypeCount(), 0);
        Assert.assertNotEquals(result.getStarPlayerCount(),0);
    }

    /**
     * Test of getSkill method, of class LRB.
     */
    @Test
    public void testGetSkill() {
        LOG.log(Level.FINE, "getSkill");
        String name = "";
        LRB instance = null;
        Skill expResult = null;
        Skill result = instance.getSkill(name);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSkillType method, of class LRB.
     */
    @Test
    public void testGetSkillType_String() {
        LOG.log(Level.FINE, "getSkillType");
        String name = "";
        LRB instance = null;
        SkillType expResult = null;
        SkillType result = instance.getSkillType(name);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRosterType method, of class LRB.
     */
    @Test
    public void testGetRosterType_String() {
        LOG.log(Level.FINE, "getRosterType");
//        String name = "";
        LRB instance = LRB.getLRB();
        RosterType result = instance.getRosterType("Chaos");
        Assert.assertNotNull(result);
    }

    /**
     * Test of getStarPlayer method, of class LRB.
     */
    @Test
    public void testGetStarPlayer_String() {
        LOG.log(Level.FINE, "getStarPlayer");
        String name = "";
        LRB instance = null;
        StarPlayer expResult = null;
        StarPlayer result = instance.getStarPlayer(name);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRosterTypeListAsString method, of class LRB.
     */
    @Test
    public void testGetRosterTypeListAsString() {
        LOG.log(Level.FINE, "getRosterTypeListAsString");
        LRB instance = null;
        ArrayList expResult = null;
//        ArrayList result = instance.getRosterTypeListAsString();
  //      assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRosterType method, of class LRB.
     */
    @Test
    public void testGetRosterType_int() {
        LOG.log(Level.FINE, "getRosterType");
        int i = 0;
        LRB instance = LRB.getLRB();
        RosterType result = instance.getRosterType(i);
        Assert.assertNotNull(result);
    }

    /**
     * Test of getRosterTypeCount method, of class LRB.
     */
    @Test
    public void testGetRosterTypeCount() {
        LOG.log(Level.FINE, "getRosterTypeCount");
        LRB.unloadLRB();
        LRB instance = LRB.getLRB();
        int result = instance.getRosterTypeCount();
        assertEquals(result, 25);
    }

    /**
     * Test of clearRosterTypes method, of class LRB.
     */
    @Test
    public void testClearRosterTypes() {
        LOG.log(Level.FINE, "clearRosterTypes");
        LRB instance = LRB.getLRB();
        Assert.assertNotEquals(instance.getRosterTypeCount(), 0);
        instance.clearRosterTypes();
        Assert.assertEquals(instance.getRosterTypeCount(), 0);
        LRB.unloadLRB();
    }

    /**
     * Test of addRosterType method, of class LRB.
     */
    @Test
    public void testAddRosterType() {
        LOG.log(Level.FINE, "addRosterType");
        RosterType rt = new RosterType("Donkeys");
        int nb = LRB.getLRB().getRosterTypeCount();
        LRB.getLRB().addRosterType(rt);

        assertEquals(nb + 1, LRB.getLRB().getRosterTypeCount());
        LRB.unloadLRB();

    }

    /**
     * Test of getStarPlayerCount method, of class LRB.
     */
    @Test
    public void testGetStarPlayerCount() {
        LOG.log(Level.FINE, "getStarPlayerCount");
        LRB instance = null;
        int expResult = 0;
        int result = instance.getStarPlayerCount();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStarPlayer method, of class LRB.
     */
    @Test
    public void testGetStarPlayer_int() {
        LOG.log(Level.FINE, "getStarPlayer");
        int i = 0;
        LRB instance = null;
        StarPlayer expResult = null;
        StarPlayer result = instance.getStarPlayer(i);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of clearStarPlayers method, of class LRB.
     */
    @Test
    public void testClearStarPlayers() {
        LOG.log(Level.FINE, "clearStarPlayers");
        LRB instance = LRB.getLRB();
        Assert.assertNotEquals(instance.getStarPlayerCount(), 0);
        instance.clearStarPlayers();
        Assert.assertEquals(instance.getStarPlayerCount(), 0);
        LRB.unloadLRB();

    }

    /**
     * Test of addStarPlayer method, of class LRB.
     */
    @Test
    public void testAddStarPlayer() {
        LOG.log(Level.FINE, "addStarPlayer");
        StarPlayer sp = new StarPlayer("Toto");
        LRB instance = LRB.getLRB();
        int nb = instance.getStarPlayerCount();
        instance.addStarPlayer(sp);
        assertEquals(nb + 1, instance.getStarPlayerCount());
        LRB.unloadLRB();
    }

    /**
     * Test of addSkillType method, of class LRB.
     */
    @Test
    public void testAddSkillType() {
        LOG.log(Level.FINE, "addSkillType");
        SkillType st = new SkillType("Dummy", "D");
        LRB instance = LRB.getLRB();
        int nb = instance.getSkillTypeCount();
        instance.addSkillType(st);

        assertEquals(nb + 1, instance.getSkillTypeCount());
        LRB.unloadLRB();
    }

    /**
     * Test of clearSkillTypes method, of class LRB.
     */
    @Test
    public void testClearSkillTypes() {
        LOG.log(Level.FINE, "clearSkillTypes");

        LRB instance = LRB.getLRB();
        Assert.assertNotEquals(instance.getSkillTypeCount(), 0);
        instance.clearSkillTypes();
        Assert.assertEquals(instance.getSkillTypeCount(), 0);
        LRB.unloadLRB();

    }

    /**
     * Test of getSkillType method, of class LRB.
     */
    @Test
    public void testGetSkillType_int() {
        LOG.log(Level.FINE, "getSkillType");
        int i = 0;
        LRB instance = null;
        SkillType expResult = null;
        SkillType result = instance.getSkillType(i);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSkillTypeCount method, of class LRB.
     */
    @Test
    public void testGetSkillTypeCount() {
        LOG.log(Level.FINE, "getSkillTypeCount");
        LRB instance = null;
        int expResult = 0;
        int result = instance.getSkillTypeCount();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getName method, of class LRB.
     */
    @Test
    public void testGetName() {
        LOG.log(Level.FINE, "getName");
        LRB instance = LRB.getLRB();
        String result = instance.getName();
        Assert.assertNotNull(result);        
    }

    /**
     * Test of setName method, of class LRB.
     */
    @Test
    public void testSetName() {
        LOG.log(Level.FINE, "setName");
        String _name = "";
        LRB instance = null;
        instance.setName(_name);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isAllowSpecialSkills method, of class LRB.
     */
    @Test
    public void testIsAllowSpecialSkills() {
        LOG.log(Level.FINE, "isAllowSpecialSkills");
        LRB instance = null;
        boolean expResult = false;
        boolean result = instance.isAllowSpecialSkills();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setAllowSpecialSkills method, of class LRB.
     */
    @Test
    public void testSetAllowSpecialSkills() {
        LOG.log(Level.FINE, "setAllowSpecialSkills");
        boolean _allowSpecialSkills = false;
        LRB instance = null;
        instance.setAllowSpecialSkills(_allowSpecialSkills);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of unloadLRB method, of class LRB.
     */
    @Test
    public void testUnloadLRB() {
        System.out.println("unloadLRB");
        LRB.unloadLRB();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
