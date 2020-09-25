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
        LRB result = LRB.getLRB(LRB.E_Version.BB2016);
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
        LRB instance = LRB.getLRB(LRB.E_Version.BB2016);
        Assert.assertTrue(instance.getSkillTypeCount() > 0);
        for (int i = 0; i < instance.getSkillTypeCount(); i++) {
            SkillType sp = instance.getSkillType(i);
            Assert.assertNotNull(sp);
            Assert.assertTrue(sp.getSkillCount() > 0);
            for (int j = 0; j < sp.getSkillCount(); j++) {
                Skill sk = sp.getSkill(j);
                Assert.assertNotNull(sk);

                Skill sk2 = instance.getSkill(sk.getmName(), false);
                assertEquals(sk, sk2);

                Skill sk3 = instance.getSkill(Translate.translate(sk.getmName()), true);
                assertEquals(sk, sk3);

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
        LRB instance = LRB.getLRB(LRB.E_Version.BB2016);
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
        LRB instance = LRB.getLRB(LRB.E_Version.BB2016);
        RosterType result = instance.getRosterType("Gobelins");
        Assert.assertNotNull(result);
    }

    /**
     * Test of getStarPlayer method, of class LRB.
     */
    @Test
    public void testGetStarPlayer_String() {
        LOG.log(Level.FINE, "getStarPlayer");
        LRB instance = LRB.getLRB(LRB.E_Version.BB2016);
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
        LRB instance = LRB.getLRB(LRB.E_Version.BB2016);
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
        LRB instance = LRB.getLRB(LRB.E_Version.BB2016);
        int result = instance.getRosterTypeCount();
        assertEquals(result, 26);
    }

    /**
     * Test of clearRosterTypes method, of class LRB.
     */
    @Test
    public void testClearRosterTypes() {
        LOG.log(Level.FINE, "clearRosterTypes");
        LRB instance = LRB.getLRB(LRB.E_Version.BB2016);
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
        int nb = LRB.getLRB(LRB.E_Version.BB2016).getRosterTypeCount();
        LRB.getLRB(LRB.E_Version.BB2016).addRosterType(rt);

        assertEquals(nb + 1, LRB.getLRB(LRB.E_Version.BB2016).getRosterTypeCount());
        LRB.unloadLRB();

    }

    /**
     * Test of getStarPlayerCount method, of class LRB.
     */
    @Test
    public void testGetStarPlayerCount() {
        LOG.log(Level.FINE, "getStarPlayerCount");
        LRB instance = LRB.getLRB(LRB.E_Version.BB2016);
        Assert.assertTrue(instance.getStarPlayerCount() > 0);
        LRB.unloadLRB();
    }

    /**
     * Test of getStarPlayer method, of class LRB.
     */
    @Test
    public void testGetStarPlayer_int() {
        LOG.log(Level.FINE, "getStarPlayer");
        LRB instance = LRB.getLRB(LRB.E_Version.BB2016);
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
        LRB instance = LRB.getLRB(LRB.E_Version.BB2016);
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
        LRB instance = LRB.getLRB(LRB.E_Version.BB2016);
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
        LRB instance = LRB.getLRB(LRB.E_Version.BB2016);
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

        LRB instance = LRB.getLRB(LRB.E_Version.BB2016);
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
        LRB instance = LRB.getLRB(LRB.E_Version.BB2016);
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
        LRB instance = LRB.getLRB(LRB.E_Version.BB2016);
        Assert.assertTrue(instance.getSkillTypeCount() > 0);

        LRB.unloadLRB();
    }

    /**
     * Test of getName method, of class LRB.
     */
    @Test
    public void testGetName() {
        LOG.log(Level.FINE, "getName");
        LRB instance = LRB.getLRB(LRB.E_Version.BB2016);
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
        LRB instance = LRB.getLRB(LRB.E_Version.BB2016);
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
        LRB instance = LRB.getLRB(LRB.E_Version.BB2016);
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
        LRB instance = LRB.getLRB(LRB.E_Version.BB2016);
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
        LRB.getLRB(LRB.E_Version.BB2016);
        LRB.unloadLRB();
    }

    /**
     * Test of getRosterType method, of class LRB.
     */
    @Test
    public void testGetRosterType_String_boolean() {
        System.out.println("getRosterType");
        LRB lrb = LRB.getLRB(LRB.E_Version.BB2016);

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
        LRB lrb = LRB.getLRB(LRB.E_Version.BB2016);
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

    /**
     * Test of isChef_enabled method, of class LRB.
     */
    @Test
    public void testIsChef_enabled() {
        System.out.println("isChef_enabled");
        boolean expResult = false;
        LRB.getLRB(LRB.E_Version.BB2016);
        boolean result = LRB.isChef_enabled();
        assertEquals(result, true);
    }

    /**
     * Test of setChef_enabled method, of class LRB.
     */
    @Test
    public void testSetChef_enabled() {
        System.out.println("setChef_enabled");
        boolean _chef_enabled = false;
        LRB.getLRB(LRB.E_Version.BB2016);
        boolean result = LRB.isChef_enabled();
        assertEquals(result, true);
        LRB.setChef_enabled(_chef_enabled);
        result = LRB.isChef_enabled();
        assertEquals(result, false);
    }

    /**
     * Test of isMercenaries_enabled method, of class LRB.
     */
    @Test
    public void testIsMercenaries_enabled() {
        System.out.println("isMercenaries_enabled");
        boolean expResult = true;
        LRB.getLRB(LRB.E_Version.BB2016);
        boolean result = LRB.isMercenaries_enabled();
        assertEquals(result, expResult);
    }

    /**
     * Test of setMercenaries_enabled method, of class LRB.
     */
    @Test
    public void testSetMercenaries_enabled() {
        System.out.println("setMercenaries_enabled");
        boolean expResult = true;
        LRB.getLRB(LRB.E_Version.BB2016);
        boolean result = LRB.isMercenaries_enabled();
        assertEquals(result, expResult);

        boolean _mercenaries_enabled = false;
        LRB.setMercenaries_enabled(_mercenaries_enabled);
        result = LRB.isMercenaries_enabled();
        assertEquals(result, _mercenaries_enabled);
    }

    /**
     * Test of isBabes_enabled method, of class LRB.
     */
    @Test
    public void testIsBabes_enabled() {
        System.out.println("isBabes_enabled");
        boolean expResult = true;
        boolean result = LRB.isBabes_enabled();
        assertEquals(result, expResult);
    }

    /**
     * Test of setBabes_enabled method, of class LRB.
     */
    @Test
    public void testSetBabes_enabled() {
        System.out.println("setBabes_enabled");
        boolean _babes_enabled = false;
        LRB.setBabes_enabled(_babes_enabled);

        boolean result = LRB.isBabes_enabled();
        assertEquals(result, _babes_enabled);
        _babes_enabled = true;
        LRB.setBabes_enabled(_babes_enabled);
        result = LRB.isBabes_enabled();
        assertEquals(result, _babes_enabled);
    }

    /**
     * Test of isWizard_enabled method, of class LRB.
     */
    @Test
    public void testIsWizard_enabled() {
        System.out.println("isWizard_enabled");
        boolean expResult = true;
        LRB.unloadLRB();
        LRB instance = LRB.getLRB(LRB.E_Version.BB2016);
        boolean result = instance.isWizard_enabled();
        assertEquals(result, expResult);
    }

    /**
     * Test of setWizard_enabled method, of class LRB.
     */
    @Test
    public void testSetWizard_enabled() {
        System.out.println("setWizard_enabled");
        boolean _wizard_enabled = false;
        LRB.setWizard_enabled(_wizard_enabled);
        boolean result = LRB.isWizard_enabled();
        assertEquals(result, _wizard_enabled);
        _wizard_enabled = true;
        LRB.setWizard_enabled(_wizard_enabled);
        result = LRB.isWizard_enabled();
        assertEquals(result, _wizard_enabled);
    }

    /**
     * Test of isReroll_enabled method, of class LRB.
     */
    @Test
    public void testIsReroll_enabled() {
        System.out.println("isReroll_enabled");
        boolean expResult = true;
        LRB.unloadLRB();
        LRB instance = LRB.getLRB(LRB.E_Version.BB2016);
        boolean result = LRB.isReroll_enabled();
        assertEquals(result, expResult);

    }

    /**
     * Test of setReroll_enabled method, of class LRB.
     */
    @Test
    public void testSetReroll_enabled() {
        System.out.println("setReroll_enabled");
        boolean _reroll_enabled = false;
        LRB.setReroll_enabled(_reroll_enabled);
        boolean result = LRB.isReroll_enabled();
        assertEquals(result, _reroll_enabled);
        _reroll_enabled = true;
        LRB.setReroll_enabled(_reroll_enabled);
        result = LRB.isReroll_enabled();
        assertEquals(result, _reroll_enabled);
    }

    /**
     * Test of isLocal_apothecaries_enabled method, of class LRB.
     */
    @Test
    public void testIsLocal_apothecaries_enabled() {
        System.out.println("isLocal_apothecaries_enabled");
        boolean expResult = true;
        boolean result = LRB.isLocal_apothecaries_enabled();
        assertEquals(result, expResult);
    }

    /**
     * Test of setLocal_apothecaries_enabled method, of class LRB.
     */
    @Test
    public void testSetLocal_apothecaries_enabled() {
        System.out.println("setLocal_apothecaries_enabled");
        boolean _local_apothecaries_enabled = false;
        LRB.setLocal_apothecaries_enabled(_local_apothecaries_enabled);
        boolean result = LRB.isLocal_apothecaries_enabled();
        assertEquals(result, _local_apothecaries_enabled);
        _local_apothecaries_enabled = true;
        LRB.setLocal_apothecaries_enabled(_local_apothecaries_enabled);
        result = LRB.isLocal_apothecaries_enabled();
        assertEquals(result, _local_apothecaries_enabled);
    }

    /**
     * Test of isIgor_enabled method, of class LRB.
     */
    @Test
    public void testIsIgor_enabled() {
        System.out.println("isIgor_enabled");
        boolean expResult = true;
        boolean result = LRB.isIgor_enabled();
        assertEquals(result, expResult);

    }

    /**
     * Test of setIgor_enabled method, of class LRB.
     */
    @Test
    public void testSetIgor_enabled() {
        System.out.println("setIgor_enabled");
        boolean _igor_enabled = false;
        LRB.setIgor_enabled(_igor_enabled);
        boolean result = LRB.isIgor_enabled();
        assertEquals(result, _igor_enabled);
        _igor_enabled = true;
        LRB.setIgor_enabled(_igor_enabled);
        result = LRB.isIgor_enabled();
        assertEquals(result, _igor_enabled);
    }

    /**
     * Test of isCards_enabled method, of class LRB.
     */
    @Test
    public void testIsCards_enabled() {
        System.out.println("isCards_enabled");
        boolean expResult = true;
        boolean result = LRB.isCards_enabled();
        assertEquals(result, expResult);
    }

    /**
     * Test of setCards_enabled method, of class LRB.
     */
    @Test
    public void testSetCards_enabled() {
        System.out.println("setCards_enabled");
        boolean _cards_enabled = false;
        LRB.setCards_enabled(_cards_enabled);
        boolean result = LRB.isCards_enabled();
        assertEquals(result, _cards_enabled);
        _cards_enabled = true;
        LRB.setCards_enabled(_cards_enabled);
        result = LRB.isCards_enabled();
        assertEquals(result, _cards_enabled);
    }

    /**
     * Test of isStarplayers_enabled method, of class LRB.
     */
    @Test
    public void testIsStarplayers_enabled() {
        System.out.println("isStarplayers_enabled");
        boolean expResult = true;
        LRB.unloadLRB();
        LRB instance = LRB.getLRB(LRB.E_Version.BB2016);
        boolean result = instance.isStarplayers_enabled();
        assertEquals(result, expResult);
    }

    /**
     * Test of setStarplayers_enabled method, of class LRB.
     */
    @Test
    public void testSetStarplayers_enabled() {
        System.out.println("setStarplayers_enabled");
        boolean _starplayers_enabled = false;
        LRB.setStarplayers_enabled(_starplayers_enabled);
                boolean result = LRB.isStarplayers_enabled();
        assertEquals(result, _starplayers_enabled);
        _starplayers_enabled = true;
        LRB.setStarplayers_enabled(_starplayers_enabled);
                 result = LRB.isStarplayers_enabled();
        assertEquals(result, _starplayers_enabled);
    }

    /**
     * Test of isBribes_enabled method, of class LRB.
     */
    @Test
    public void testIsBribes_enabled() {
        System.out.println("isBribes_enabled");
        boolean expResult = true;
        boolean result = LRB.isBribes_enabled();
        assertEquals(result, expResult);

    }

    /**
     * Test of setBribes_enabled method, of class LRB.
     */
    @Test
    public void testSetBribes_enabled() {
        System.out.println("setBribes_enabled");
        boolean _bribes_enabled = false;
        LRB.setBribes_enabled(_bribes_enabled);
         boolean result = LRB.isBribes_enabled();
        assertEquals(result, _bribes_enabled);
        _bribes_enabled = true;
        LRB.setBribes_enabled(_bribes_enabled);
          result = LRB.isBribes_enabled();
        assertEquals(result, _bribes_enabled);
    }

    /**
     * Test of isCheck_nb_big_guys method, of class LRB.
     */
    @Test
    public void testIsCheck_nb_big_guys() {
        System.out.println("isCheck_nb_big_guys");
        boolean expResult = false;
        boolean result = LRB.isCheck_nb_big_guys();
        assertEquals(result, expResult);
        
    }

    /**
     * Test of setCheck_nb_big_guys method, of class LRB.
     */
    @Test
    public void testSetCheck_nb_big_guys() {
        System.out.println("setCheck_nb_big_guys");
        boolean _check_nb_big_guys = false;
        LRB.setCheck_nb_big_guys(_check_nb_big_guys);
        boolean result = LRB.isCheck_nb_big_guys();
        assertEquals(result, _check_nb_big_guys);
        _check_nb_big_guys = true;
        LRB.setCheck_nb_big_guys(_check_nb_big_guys);
        result = LRB.isCheck_nb_big_guys();
        assertEquals(result, _check_nb_big_guys);
    }

    /**
     * Test of getVersion method, of class LRB.
     */
    @Test
    public void testGetVersion() {
        System.out.println("getVersion");
        LRB instance = LRB.getLRB(LRB.E_Version.BB2016);
        LRB.E_Version expResult = LRB.E_Version.BB2016;
        LRB.E_Version result = instance.getVersion();
        assertEquals(result, expResult);

    }

}
