/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teamma.data;

import java.util.Random;
import org.testng.Assert;
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
public class StarPlayerNGTest {

    private static LRB lrb;

    @BeforeClass
    public static void setUpClass() throws Exception {
        lrb = LRB.getLRB(LRB.E_Version.BB2016);
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    public StarPlayerNGTest() {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }

    /**
     * Test of getName method, of class StarPlayer.
     */
    @Test
    @SuppressWarnings("UseOfSystemOutOrSystemErr")
    public void testGetName() {
        System.out.println("getName");
        if (lrb == null) {
            fail("No lrb loaded");
        }
        if (lrb.getStarPlayerCount() == 0) {
            fail("No star player in LRB");
        }
        for (int i = 0; i < lrb.getStarPlayerCount(); i++) {
            StarPlayer instance = lrb.getStarPlayer(i);
            if (instance != null) {
//            String expResult = "";
                String result = instance.getName();
                Assert.assertNotNull(result);
            } else {
                fail("Null star player");
            }
        }
    }

    /**
     * Test of setName method, of class StarPlayer.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        if (lrb == null) {
            fail("No lrb loaded");
        }
        if (lrb.getStarPlayerCount() == 0) {
            fail("No star player in LRB");
        }
        for (int i = 0; i < lrb.getStarPlayerCount(); i++) {
            StarPlayer instance = lrb.getStarPlayer(i);
            if (instance != null) {
                String save = instance.getName();
                instance.setName("TOTO");
                String result = instance.getName();
                Assert.assertEquals(result, "TOTO");
                instance.setName(save);
            } else {
                fail("Null star player name");
            }
        }
    }

    /**
     * Test of getPosition method, of class StarPlayer.
     */
    @Test
    public void testGetPosition() {
        System.out.println("getPosition");
        if (lrb == null) {
            fail("No lrb loaded");
        }
        if (lrb.getStarPlayerCount() == 0) {
            fail("No star player in LRB");
        }
        for (int i = 0; i < lrb.getStarPlayerCount(); i++) {
            StarPlayer instance = lrb.getStarPlayer(i);

            if (instance != null) {
//            String expResult = "";
                String result = instance.getPosition();
                Assert.assertNotNull(result);
            } else {
                fail("Null position found");
            }
        }
    }

    /**
     * Test of setPosition method, of class StarPlayer.
     */
    @Test
    public void testSetPosition() {
        System.out.println("setPosition");
        if (lrb == null) {
            fail("No lrb loaded");
        }
        if (lrb.getStarPlayerCount() == 0) {
            fail("No star player in LRB");
        }
        for (int i = 0; i < lrb.getStarPlayerCount(); i++) {
            StarPlayer instance = lrb.getStarPlayer(i);
            if (instance != null) {
                String save = instance.getPosition();
                instance.setPosition("POSITION");
                String result = instance.getPosition();
                Assert.assertEquals(result, "POSITION");
                instance.setPosition(save);
            } else {
                fail("Null position found");
            }
        }
    }

    /**
     * Test of getLimit method, of class StarPlayer.
     */
    @Test
    public void testGetLimit() {
        System.out.println("getLimit");
        if (lrb == null) {
            fail("No lrb loaded");
        }
        if (lrb.getStarPlayerCount() == 0) {
            fail("No star player in LRB");
        }
        for (int i = 0; i < lrb.getStarPlayerCount(); i++) {
            StarPlayer instance = lrb.getStarPlayer(i);

            if (instance != null) {
//            String expResult = "";
                int result = instance.getLimit();
                Assert.assertEquals(result, 0);
            } else {
                fail("Null position found");
            }
        }
    }

    /**
     * Test of setLimit method, of class StarPlayer.
     */
    @Test
    public void testSetLimit() {
        if (lrb == null) {
            fail("No lrb loaded");
        }
        if (lrb.getStarPlayerCount() == 0) {
            fail("No star player in LRB");
        }
        for (int i = 0; i < lrb.getStarPlayerCount(); i++) {
            StarPlayer instance = lrb.getStarPlayer(i);

            if (instance != null) {
                int save = instance.getLimit();
                instance.setLimit(17);
                int result = instance.getLimit();
                Assert.assertEquals(result, 17);
                instance.setLimit(save);
            } else {
                fail("Null position found");
            }
        }
    }

    /**
     * Test of getMovement method, of class StarPlayer.
     */
    @Test
    public void testGetMovement() {
        System.out.println("getMovement");
        if (lrb == null) {
            fail("No lrb loaded");
        }
        if (lrb.getStarPlayerCount() == 0) {
            fail("No star player in LRB");
        }
        for (int i = 0; i < lrb.getStarPlayerCount(); i++) {
            StarPlayer instance = lrb.getStarPlayer(i);

            if (instance != null) {
//            String expResult = "";
                int result = instance.getMovement();
                Assert.assertNotEquals(result, 0);
            } else {
                fail("Null position found");
            }
        }
    }

    /**
     * Test of setMovement method, of class StarPlayer.
     */
    @Test
    public void testSetMovement() {
        System.out.println("setMovement");
        if (lrb == null) {
            fail("No lrb loaded");
        }
        if (lrb.getStarPlayerCount() == 0) {
            fail("No star player in LRB");
        }
        for (int i = 0; i < lrb.getStarPlayerCount(); i++) {
            StarPlayer instance = lrb.getStarPlayer(i);

            if (instance != null) {
                int save = instance.getMovement();
                instance.setMovement(17);
                int result = instance.getMovement();
                Assert.assertEquals(result, 17);
                instance.setMovement(save);
            } else {
                fail("Null position found");
            }
        }
    }

    /**
     * Test of getStrength method, of class StarPlayer.
     */
    @Test
    public void testGetStrength() {
        System.out.println("getStrength");
        if (lrb == null) {
            fail("No lrb loaded");
        }
        if (lrb.getStarPlayerCount() == 0) {
            fail("No star player in LRB");
        }
        for (int i = 0; i < lrb.getStarPlayerCount(); i++) {
            StarPlayer instance = lrb.getStarPlayer(i);

            if (instance != null) {
//            String expResult = "";
                int result = instance.getStrength();
                Assert.assertNotEquals(result, 0);
            } else {
                fail("Null position found");
            }
        }
    }

    /**
     * Test of setStrength method, of class StarPlayer.
     */
    @Test
    public void testSetStrength() {
        System.out.println("setStrength");
        if (lrb == null) {
            fail("No lrb loaded");
        }
        if (lrb.getStarPlayerCount() == 0) {
            fail("No star player in LRB");
        }
        for (int i = 0; i < lrb.getStarPlayerCount(); i++) {
            StarPlayer instance = lrb.getStarPlayer(i);

            if (instance != null) {
                int save = instance.getStrength();
                instance.setStrength(17);
                int result = instance.getStrength();
                Assert.assertEquals(result, 17);
                instance.setStrength(save);
            } else {
                fail("Null position found");
            }
        }
    }

    /**
     * Test of getAgility method, of class StarPlayer.
     */
    @Test
    public void testGetAgility() {
        System.out.println("getAgility");
        if (lrb == null) {
            fail("No lrb loaded");
        }
        if (lrb.getStarPlayerCount() == 0) {
            fail("No star player in LRB");
        }
        for (int i = 0; i < lrb.getStarPlayerCount(); i++) {
            StarPlayer instance = lrb.getStarPlayer(i);

            if (instance != null) {
//            String expResult = "";
                int result = instance.getAgility();
                Assert.assertNotEquals(result, 0);
            } else {
                fail("Null position found");
            }
        }
    }

    /**
     * Test of setAgility method, of class StarPlayer.
     */
    @Test
    public void testSetAgility() {
        System.out.println("setAgility");
        if (lrb == null) {
            fail("No lrb loaded");
        }
        if (lrb.getStarPlayerCount() == 0) {
            fail("No star player in LRB");
        }
        for (int i = 0; i < lrb.getStarPlayerCount(); i++) {
            StarPlayer instance = lrb.getStarPlayer(i);

            if (instance != null) {
                int save=instance.getAgility();
                instance.setAgility(17);
                int result = instance.getAgility();
                Assert.assertEquals(result, 17);
                instance.setAgility(save);
            } else {
                fail("Null position found");
            }
        }
    }

    /**
     * Test of getArmor method, of class StarPlayer.
     */
    @Test
    public void testGetArmor() {
        System.out.println("getArmor");
        if (lrb == null) {
            fail("No lrb loaded");
        }
        if (lrb.getStarPlayerCount() == 0) {
            fail("No star player in LRB");
        }
        for (int i = 0; i < lrb.getStarPlayerCount(); i++) {
            StarPlayer instance = lrb.getStarPlayer(i);

            if (instance != null) {
//            String expResult = "";
                int result = instance.getArmor();
                Assert.assertNotEquals(result, 0);
            } else {
                fail("Null position found");
            }
        }
    }

    /**
     * Test of setArmor method, of class StarPlayer.
     */
    @Test
    public void testSetArmor() {
        System.out.println("setArmor");
        if (lrb == null) {
            fail("No lrb loaded");
        }
        if (lrb.getStarPlayerCount() == 0) {
            fail("No star player in LRB");
        }
        for (int i = 0; i < lrb.getStarPlayerCount(); i++) {
            StarPlayer instance = lrb.getStarPlayer(i);

            if (instance != null) {
                int save = instance.getArmor();
                instance.setArmor(17);
                int result = instance.getArmor();
                Assert.assertEquals(result, 17);
                instance.setArmor(save);
            } else {
                fail("Null position found");
            }
        }
    }

    /**
     * Test of getCost method, of class StarPlayer.
     */
    @Test
    public void testGetCost() {
        System.out.println("getCost");
        if (lrb == null) {
            fail("No lrb loaded");
        }
        if (lrb.getStarPlayerCount() == 0) {
            fail("No star player in LRB");
        }
        for (int i = 0; i < lrb.getStarPlayerCount(); i++) {
            StarPlayer instance = lrb.getStarPlayer(i);

            if (instance != null) {
                int result = instance.getCost();
                if ((result == 0)&&(!instance.getName().equals("Grotty *CRP*"))
                        &&(!instance.getName().equals("Crumbleberry *BB2016*"))
                        &&(!instance.getName().equals("Valen Swift *BB2016*"))
                        &&(!instance.getName().equals("Grombrindal, The White Dwarf *Special Event*"))
                        &&(!instance.getName().equals("The Black Gobbo *Special Event*"))) {
                    fail("cost is null for "+instance.getName());
                }
            } else {
                fail("Null position found");
            }
        }
    }

    /**
     * Test of setCost method, of class StarPlayer.
     */
    @Test
    public void testSetCost() {
        System.out.println("setCost");
        if (lrb == null) {
            fail("No lrb loaded");
        }
        if (lrb.getStarPlayerCount() == 0) {
            fail("No star player in LRB");
        }
        for (int i = 0; i < lrb.getStarPlayerCount(); i++) {
            StarPlayer instance = lrb.getStarPlayer(i);

            if (instance != null) {
                int save = instance.getCost();
                instance.setCost(3000);
                int result = instance.getCost();
                Assert.assertEquals(result, 3000);
                instance.setCost(save);
            } else {
                fail("Null position found");
            }
        }
    }

    /**
     * Test of addSkill method, of class StarPlayer.
     */
    @Test
    public void testAddSkill() {
        System.out.println("addSkill");
        if (lrb == null) {
            fail("No lrb loaded");
        }
        if (lrb.getStarPlayerCount() == 0) {
            fail("No star player in LRB");
        }
        Random random = new Random();
        for (int i = 0; i < lrb.getStarPlayerCount(); i++) {
            StarPlayer instance = lrb.getStarPlayer(i);
            int skillTypeCount = lrb.getSkillTypeCount();
            if (skillTypeCount == 0) {
                fail("No skill type in LRB)");
            }
            int skillTypeIndex = Math.abs(random.nextInt()) % skillTypeCount;
            SkillType st = lrb.getSkillType(skillTypeIndex);

            int skillCount = st.getSkillCount();
            if (skillCount == 0) {
                fail("No skill in " + st.getName());
            }
            int skillIndex = Math.abs(random.nextInt()) % skillCount;
            Skill s = st.getSkill(skillIndex);

            if (instance != null) {
                int nb = instance.getSkillCount();
                instance.addSkill(s);
                Skill tmp = instance.getSkill(nb);
                Assert.assertEquals(s, tmp);
            } else {
                fail("Null starplayer found");
            }
        }
    }

    /**
     * Test of getSkillCount method, of class StarPlayer.
     */
    @Test
    public void testGetSkillCount() {
        System.out.println("getSkillCount");
        if (lrb == null) {
            fail("No lrb loaded");
        }
        if (lrb.getStarPlayerCount() == 0) {
            fail("No star player in LRB");
        }
        Random random = new Random();
        for (int i = 0; i < lrb.getStarPlayerCount(); i++) {
            StarPlayer instance = lrb.getStarPlayer(i);
            int skillTypeCount = lrb.getSkillTypeCount();
            if (skillTypeCount == 0) {
                fail("No skill type in LRB)");
            }
            int skillTypeIndex = Math.abs(random.nextInt()) % skillTypeCount;
            SkillType st = lrb.getSkillType(skillTypeIndex);

            int skillCount = st.getSkillCount();
            if (skillCount == 0) {
                fail("No skill in " + st.getName());
            }
            int skillIndex = Math.abs(random.nextInt()) % skillCount;
            Skill s = st.getSkill(skillIndex);

            if (instance != null) {
                int nb = instance.getSkillCount();
                instance.addSkill(s);
                Assert.assertEquals(nb + 1, instance.getSkillCount());
            } else {
                fail("Null starplayer found");
            }
        }
    }

    /**
     * Test of getSkill method, of class StarPlayer.
     */
    @Test
    public void testGetSkill() {
        System.out.println("getSkill");
        if (lrb == null) {
            fail("No lrb loaded");
        }
        if (lrb.getStarPlayerCount() == 0) {
            fail("No star player in LRB");
        }
        Random random = new Random();
        for (int i = 0; i < lrb.getStarPlayerCount(); i++) {
            StarPlayer instance = lrb.getStarPlayer(i);
            int skillTypeCount = lrb.getSkillTypeCount();
            if (skillTypeCount == 0) {
                fail("No skill type in LRB)");
            }
            int skillTypeIndex = Math.abs(random.nextInt()) % skillTypeCount;
            SkillType st = lrb.getSkillType(skillTypeIndex);

            int skillCount = st.getSkillCount();
            if (skillCount == 0) {
                fail("No skill in " + st.getName());
            }
            int skillIndex = Math.abs(random.nextInt()) % skillCount;
            Skill s = st.getSkill(skillIndex);

            if (instance != null) {
                int nb = instance.getSkillCount();
                instance.addSkill(s);
                Skill tmp = instance.getSkill(nb);
                Assert.assertEquals(s, tmp);
            } else {
                fail("Null starplayer found");
            }
        }
    }

    /**
     * Test of addRoster method, of class StarPlayer.
     */
    @Test
    public void testAddRoster() {
        System.out.println("addRoster");
        if (lrb == null) {
            fail("No lrb loaded");
        }
        if (lrb.getStarPlayerCount() == 0) {
            fail("No star player in LRB");
        }
        Random random = new Random();
        for (int i = 0; i < lrb.getStarPlayerCount(); i++) {
            StarPlayer instance = lrb.getStarPlayer(i);
            int rosterTypeCount = lrb.getRosterTypeCount();
            if (rosterTypeCount == 0) {
                fail("No roster type in LRB)");
            }
            int rosterTypeIndex = Math.abs(random.nextInt()) % rosterTypeCount;
            RosterType rt=lrb.getRosterType(rosterTypeIndex);

            if (instance != null) {
                int nb = instance.getRosterCount();
                instance.addRoster(rt);
                RosterType tmp = instance.getRoster(nb);
                Assert.assertEquals(rt, tmp);
            } else {
                fail("Null starplayer found");
            }
        }
    }

    /**
     * Test of getRosterCount method, of class StarPlayer.
     */
    @Test
    public void testGetRosterCount() {
        System.out.println("getRosterCount");
        if (lrb == null) {
            fail("No lrb loaded");
        }
        if (lrb.getStarPlayerCount() == 0) {
            fail("No star player in LRB");
        }
        Random random = new Random();
        for (int i = 0; i < lrb.getStarPlayerCount(); i++) {
            StarPlayer instance = lrb.getStarPlayer(i);
            int rosterTypeCount = lrb.getRosterTypeCount();
            if (rosterTypeCount == 0) {
                fail("No roster type in LRB)");
            }
            int rosterTypeIndex = Math.abs(random.nextInt()) % rosterTypeCount;
            RosterType rt=lrb.getRosterType(rosterTypeIndex);

            if (instance != null) {
                int nb = instance.getRosterCount();
                instance.addRoster(rt);
                Assert.assertEquals(nb+1, instance.getRosterCount());
            } else {
                fail("Null starplayer found");
            }
        }
    }

    /**
     * Test of getRoster method, of class StarPlayer.
     */
    @Test
    public void testGetRoster() {
        System.out.println("getRoster");
        if (lrb == null) {
            fail("No lrb loaded");
        }
        if (lrb.getStarPlayerCount() == 0) {
            fail("No star player in LRB");
        }
        Random random = new Random();
        for (int i = 0; i < lrb.getStarPlayerCount(); i++) {
            StarPlayer instance = lrb.getStarPlayer(i);
            int rosterTypeCount = lrb.getRosterTypeCount();
            if (rosterTypeCount == 0) {
                fail("No roster type in LRB)");
            }
            int rosterTypeIndex = Math.abs(random.nextInt()) % rosterTypeCount;
            RosterType rt=lrb.getRosterType(rosterTypeIndex);

            if (instance != null) {
                int nb = instance.getRosterCount();
                instance.addRoster(rt);
                RosterType tmp=instance.getRoster(nb);
                Assert.assertEquals(rt, tmp);
            } else {
                fail("Null starplayer found");
            }
        }
    }

}
