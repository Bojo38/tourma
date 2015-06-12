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
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import teamma.languages.Translate;

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
        Assert.assertNotEquals(result.getStarPlayerCount(), 0);
    }

    /**
     * Test of getSkill method, of class LRB.
     */
    @Test
    public void testGetSkill() {
        LOG.log(Level.FINE, "getSkill");
        LRB instance = LRB.getLRB();
        Assert.assertTrue(instance.getSkillTypeCount() > 0);
        for (int i = 0; i < instance.getSkillTypeCount(); i++) {
            SkillType sp = instance.getSkillType(i);
            Assert.assertNotNull(sp);
            Assert.assertTrue(sp.getSkillCount() > 0);
            for (int j = 0; j < sp.getSkillCount(); j++) {
                Skill sk = sp.getSkill(j);
                Assert.assertNotNull(sk);

                Skill sk2 = instance.getSkill(sk.getmName());
                assertEquals(sk, sk2);

            }
        }
        LRB.unloadLRB();
    }

    /**
     * Test of getSkillType method, of class LRB.
     */
    @Test
    public void testGetSkillType_String() {
        LOG.log(Level.FINE, "getSkillType");
        LRB instance = LRB.getLRB();
        Assert.assertTrue(instance.getSkillTypeCount() > 0);
        for (int i = 0; i < instance.getSkillTypeCount(); i++) {
            SkillType sp = instance.getSkillType(i);
            Assert.assertNotNull(sp);
            SkillType sp2 = instance.getSkillType(sp.getName());
            assertEquals(sp, sp2);
        }
        LRB.unloadLRB();
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
        LRB instance = LRB.getLRB();
        Assert.assertTrue(instance.getStarPlayerCount() > 0);
        for (int i = 0; i < instance.getStarPlayerCount(); i++) {
            StarPlayer sp = instance.getStarPlayer(i);
            Assert.assertNotNull(sp);

            StarPlayer sp2 = instance.getStarPlayer(sp.getName());
            assertEquals(sp, sp2);
        }
        LRB.unloadLRB();
    }

    /**
     * Test of getRosterType method, of class LRB.
     */
    @Test
    public void testGetRosterType_int() {
        LOG.log(Level.FINE, "getRosterType");
        LRB instance = LRB.getLRB();
        Assert.assertNotNull(instance);
        Assert.assertTrue(instance.getRosterTypeCount() > 0);
        for (int i = 0; i < instance.getRosterTypeCount(); i++) {
            RosterType result = instance.getRosterType(i);
            Assert.assertNotNull(result);
        }
        LRB.unloadLRB();
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
        LRB instance = LRB.getLRB();
        Assert.assertTrue(instance.getStarPlayerCount() > 0);
        LRB.unloadLRB();
    }

    /**
     * Test of getStarPlayer method, of class LRB.
     */
    @Test
    public void testGetStarPlayer_int() {
        LOG.log(Level.FINE, "getStarPlayer");
        LRB instance = LRB.getLRB();
        Assert.assertTrue(instance.getStarPlayerCount() > 0);
        for (int i = 0; i < instance.getStarPlayerCount(); i++) {
            StarPlayer sp = instance.getStarPlayer(i);
            Assert.assertNotNull(sp);
        }
        LRB.unloadLRB();
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
        LRB instance = LRB.getLRB();
        Assert.assertTrue(instance.getSkillTypeCount() > 0);
        for (int i = 0; i < instance.getSkillTypeCount(); i++) {
            SkillType sp = instance.getSkillType(i);
            Assert.assertNotNull(sp);
        }
        LRB.unloadLRB();
    }

    /**
     * Test of getSkillTypeCount method, of class LRB.
     */
    @Test
    public void testGetSkillTypeCount() {
        LOG.log(Level.FINE, "getSkillTypeCount");
        LRB instance = LRB.getLRB();
        Assert.assertTrue(instance.getSkillTypeCount() > 0);

        LRB.unloadLRB();
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
        String _name = "Test";
        LRB instance = LRB.getLRB();
        String save = instance.getName();
        instance.setName(_name);
        assertEquals("Test", instance.getName());
        instance.setName(save);
    }

    /**
     * Test of isAllowSpecialSkills method, of class LRB.
     */
    @Test
    public void testIsAllowSpecialSkills() {
        LOG.log(Level.FINE, "isAllowSpecialSkills");
        LRB instance = LRB.getLRB();
        boolean save = instance.isAllowSpecialSkills();
        instance.setAllowSpecialSkills(true);
        assertEquals(true, instance.isAllowSpecialSkills());
        instance.setAllowSpecialSkills(save);
    }

    /**
     * Test of setAllowSpecialSkills method, of class LRB.
     */
    @Test
    public void testSetAllowSpecialSkills() {
        LOG.log(Level.FINE, "setAllowSpecialSkills");
        LRB instance = LRB.getLRB();
        boolean save = instance.isAllowSpecialSkills();
        instance.setAllowSpecialSkills(true);
        assertEquals(true, instance.isAllowSpecialSkills());
        instance.setAllowSpecialSkills(save);
    }

    /**
     * Test of unloadLRB method, of class LRB.
     */
    @Test
    public void testUnloadLRB() {
        System.out.println("unloadLRB");
        LRB.unloadLRB();
        LRB.getLRB();
        LRB.unloadLRB();
    }

    /**
     * Test of getRosterType method, of class LRB.
     */
    @Test
    public void testGetRosterType_String_boolean() {
        System.out.println("getRosterType");
        LRB lrb = LRB.getLRB();

        for (int i = 0; i < lrb.getRosterTypeCount(); i++) {
            RosterType rt = lrb.getRosterType(i);
            String name = rt.getName();
            Assert.assertNotNull(name);
            RosterType rt2 = lrb.getRosterType(name, false);
            Assert.assertNotNull(rt2);
            Assert.assertEquals(rt, rt2);
        }

        for (int i = 0; i < lrb.getRosterTypeCount(); i++) {
            RosterType rt = lrb.getRosterType(i);
            String name = Translate.translate(rt.getName());
            Assert.assertNotNull(name);
            RosterType rt2 = lrb.getRosterType(name, true);
            Assert.assertNotNull(rt2);
            Assert.assertEquals(rt, rt2);
        }

        LRB.unloadLRB();
    }

    /**
     * Test of getRosterTypeListAsString method, of class LRB.
     */
    @Test
    public void testGetRosterTypeListAsString() {
        System.out.println("getRosterTypeListAsString");
        LRB lrb = LRB.getLRB();
        ArrayList result = lrb.getRosterTypeListAsString(false);
        Assert.assertNotNull(result);
        Assert.assertTrue(result.size() > 0);

        for (int i = 0; i < lrb.getRosterTypeCount(); i++) {
            RosterType rt = lrb.getRosterType(i);
            Assert.assertEquals(rt.getName(), result.get(i));
        }

        result = lrb.getRosterTypeListAsString(true);
        Assert.assertNotNull(result);
        Assert.assertTrue(result.size() > 0);

        for (int i = 0; i < lrb.getRosterTypeCount(); i++) {
            RosterType rt = lrb.getRosterType(i);
            Assert.assertEquals(Translate.translate(rt.getName()), result.get(i));
        }

        LRB.unloadLRB();
    }

}
