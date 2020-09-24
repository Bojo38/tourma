/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teamma.data;

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
public class PlayerTypeNGTest {

    private static LRB lrb;

    @BeforeClass
    public static void setUpClass() throws Exception {
        lrb = LRB.getLRB(LRB.E_Version.BB2016);
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    public PlayerTypeNGTest() {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }

    /**
     * Test of getPosition method, of class PlayerType.
     */
    @Test
    public void testGetPosition() {
        System.out.println("getPosition");
        if (lrb == null) {
            fail("No lrb loaded");
        }
        if (lrb.getRosterTypeCount() == 0) {
            fail("No roster type in LRB");
        }
        for (int i = 0; i < lrb.getRosterTypeCount(); i++) {
            RosterType instance = lrb.getRosterType(i);
            if (instance != null) {

                for (int j = 0; j < instance.getPlayerTypeCount(); j++) {
                    PlayerType pt = instance.getPlayerType(j);
                    Assert.assertNotNull(pt);

                    String result = pt.getPosition();
                    Assert.assertNotNull(result);
                }

            } else {
                fail("Null ExtraRerollCost found");
            }
        }
    }

    /**
     * Test of getLimit method, of class PlayerType.
     */
    @Test
    public void testGetLimit() {
        System.out.println("getLimit");
        if (lrb == null) {
            fail("No lrb loaded");
        }
        if (lrb.getRosterTypeCount() == 0) {
            fail("No roster type in LRB");
        }
        for (int i = 0; i < lrb.getRosterTypeCount(); i++) {
            RosterType instance = lrb.getRosterType(i);
            if (instance != null) {

                for (int j = 0; j < instance.getPlayerTypeCount(); j++) {
                    PlayerType pt = instance.getPlayerType(j);
                    Assert.assertNotNull(pt);

                    int result = pt.getLimit();
                    Assert.assertTrue(result > 0);
                }

            } else {
                fail("Null ExtraRerollCost found");
            }
        }
    }

    /**
     * Test of getMovement method, of class PlayerType.
     */
    @Test
    public void testGetMovement() {
        System.out.println("getMovement");
        if (lrb == null) {
            fail("No lrb loaded");
        }
        if (lrb.getRosterTypeCount() == 0) {
            fail("No roster type in LRB");
        }
        for (int i = 0; i < lrb.getRosterTypeCount(); i++) {
            RosterType instance = lrb.getRosterType(i);
            if (instance != null) {

                for (int j = 0; j < instance.getPlayerTypeCount(); j++) {
                    PlayerType pt = instance.getPlayerType(j);
                    Assert.assertNotNull(pt);

                    int result = pt.getMovement();
                    Assert.assertTrue(result > 0);
                }

            } else {
                fail("Null  found");
            }
        }
    }

    /**
     * Test of getStrength method, of class PlayerType.
     */
    @Test
    public void testGetStrength() {
        System.out.println("getStrength");
        if (lrb == null) {
            fail("No lrb loaded");
        }
        if (lrb.getRosterTypeCount() == 0) {
            fail("No roster type in LRB");
        }
        for (int i = 0; i < lrb.getRosterTypeCount(); i++) {
            RosterType instance = lrb.getRosterType(i);
            if (instance != null) {

                for (int j = 0; j < instance.getPlayerTypeCount(); j++) {
                    PlayerType pt = instance.getPlayerType(j);
                    Assert.assertNotNull(pt);

                    int result = pt.getStrength();
                    Assert.assertTrue(result > 0);
                }

            } else {
                fail("Null found");
            }
        }
    }

    /**
     * Test of getAgility method, of class PlayerType.
     */
    @Test
    public void testGetAgility() {
        System.out.println("getAgility");
        if (lrb == null) {
            fail("No lrb loaded");
        }
        if (lrb.getRosterTypeCount() == 0) {
            fail("No roster type in LRB");
        }
        for (int i = 0; i < lrb.getRosterTypeCount(); i++) {
            RosterType instance = lrb.getRosterType(i);
            if (instance != null) {

                for (int j = 0; j < instance.getPlayerTypeCount(); j++) {
                    PlayerType pt = instance.getPlayerType(j);
                    Assert.assertNotNull(pt);

                    int result = pt.getAgility();
                    Assert.assertTrue(result > 0);
                }

            } else {
                fail("Null found");
            }
        }
    }

    /**
     * Test of getArmor method, of class PlayerType.
     */
    @Test
    public void testGetArmor() {
        System.out.println("getArmor");
        if (lrb == null) {
            fail("No lrb loaded");
        }
        if (lrb.getRosterTypeCount() == 0) {
            fail("No roster type in LRB");
        }
        for (int i = 0; i < lrb.getRosterTypeCount(); i++) {
            RosterType instance = lrb.getRosterType(i);
            if (instance != null) {

                for (int j = 0; j < instance.getPlayerTypeCount(); j++) {
                    PlayerType pt = instance.getPlayerType(j);
                    Assert.assertNotNull(pt);

                    int result = pt.getArmor();
                    Assert.assertTrue(result > 0);
                }

            } else {
                fail("Null found");
            }
        }
    }

    /**
     * Test of getCost method, of class PlayerType.
     */
    @Test
    public void testGetCost() {
        System.out.println("getCost");
        if (lrb == null) {
            fail("No lrb loaded");
        }
        if (lrb.getRosterTypeCount() == 0) {
            fail("No roster type in LRB");
        }
        for (int i = 0; i < lrb.getRosterTypeCount(); i++) {
            RosterType instance = lrb.getRosterType(i);
            if (instance != null) {

                for (int j = 0; j < instance.getPlayerTypeCount(); j++) {
                    PlayerType pt = instance.getPlayerType(j);
                    Assert.assertNotNull(pt);

                    int result = pt.getCost();
                    Assert.assertTrue(result > 0);
                }

            } else {
                fail("Null found");
            }
        }
    }

    /**
     * Test of getSkill method, of class PlayerType.
     */
    @Test
    public void testGetSkill() {
        System.out.println("getSkill");
       if (lrb == null) {
            fail("No lrb loaded");
        }
        if (lrb.getRosterTypeCount() == 0) {
            fail("No roster type in LRB");
        }
        for (int i = 0; i < lrb.getRosterTypeCount(); i++) {
            RosterType instance = lrb.getRosterType(i);
            if (instance != null) {

                for (int j = 0; j < instance.getPlayerTypeCount(); j++) {
                    PlayerType pt = instance.getPlayerType(j);
                    Assert.assertNotNull(pt);

                    Assert.assertTrue(lrb.getSkillTypeCount() > 0);
                    SkillType st = lrb.getSkillType(0);

                    int nb = pt.getSkillCount();
                    //Assert.assertTrue(nb > 0);
                    for (int k = 0; k < nb; k++) {
                        Assert.assertNotNull(pt.getSkill(k));
                    }
               }

            } else {
                fail("Null found");
            }
        }
    }

    /**
     * Test of addSkill method, of class PlayerType.
     */
    @Test
    public void testAddSkill() {
        System.out.println("addSkill");
        if (lrb == null) {
            fail("No lrb loaded");
        }
        if (lrb.getRosterTypeCount() == 0) {
            fail("No roster type in LRB");
        }
        for (int i = 0; i < lrb.getRosterTypeCount(); i++) {
            RosterType instance = lrb.getRosterType(i);
            if (instance != null) {

                for (int j = 0; j < instance.getPlayerTypeCount(); j++) {
                    PlayerType pt = instance.getPlayerType(j);
                    Assert.assertNotNull(pt);

                    Assert.assertTrue(lrb.getSkillTypeCount() > 0);
                    SkillType st = lrb.getSkillType(0);

                    Assert.assertTrue(st.getSkillCount() > 0);
                    Skill sk = st.getSkill(0);

                    int nb = pt.getSkillCount();
                    pt.addSkill(sk);
                    Assert.assertEquals(nb + 1, pt.getSkillCount());
                }
            } else {
                fail("Null found");
            }
        }
    }

    /**
     * Test of getSkillCount method, of class PlayerType.
     */
    @Test
    public void testGetSkillCount() {
        System.out.println("getSkillCount");
       if (lrb == null) {
            fail("No lrb loaded");
        }
        if (lrb.getRosterTypeCount() == 0) {
            fail("No roster type in LRB");
        }
        for (int i = 0; i < lrb.getRosterTypeCount(); i++) {
            RosterType instance = lrb.getRosterType(i);
            if (instance != null) {

                for (int j = 0; j < instance.getPlayerTypeCount(); j++) {
                    PlayerType pt = instance.getPlayerType(j);
                    Assert.assertNotNull(pt);

                    int nb = pt.getSkillCount();                   
               }

            } else {
                fail("Null found");
            }
        }
    }

    /**
     * Test of getSingle method, of class PlayerType.
     */
    @Test
    public void testGetSingle() {
        System.out.println("getSingle");
       if (lrb == null) {
            fail("No lrb loaded");
        }
        if (lrb.getRosterTypeCount() == 0) {
            fail("No roster type in LRB");
        }
        for (int i = 0; i < lrb.getRosterTypeCount(); i++) {
            RosterType instance = lrb.getRosterType(i);
            if (instance != null) {

                for (int j = 0; j < instance.getPlayerTypeCount(); j++) {
                    PlayerType pt = instance.getPlayerType(j);
                    Assert.assertNotNull(pt);

                    Assert.assertTrue(lrb.getSkillTypeCount() > 0);
                    SkillType st = lrb.getSkillType(0);

                    int nb = pt.getSingleCount();
                    Assert.assertTrue(nb > 0);
                    for (int k = 0; k < nb; k++) {
                        Assert.assertNotNull(pt.getSingle(k));
                    }
               }

            } else {
                fail("Null found");
            }
        }
    }

    /**
     * Test of getSingleCount method, of class PlayerType.
     */
    @Test
    public void testGetSingleCount() {
        System.out.println("getSingleCount");
       if (lrb == null) {
            fail("No lrb loaded");
        }
        if (lrb.getRosterTypeCount() == 0) {
            fail("No roster type in LRB");
        }
        for (int i = 0; i < lrb.getRosterTypeCount(); i++) {
            RosterType instance = lrb.getRosterType(i);
            if (instance != null) {

                for (int j = 0; j < instance.getPlayerTypeCount(); j++) {
                    PlayerType pt = instance.getPlayerType(j);
                    Assert.assertNotNull(pt);

                    Assert.assertTrue(lrb.getSkillTypeCount() > 0);
                    SkillType st = lrb.getSkillType(0);

                    int nb = pt.getDoubleCount();
                    Assert.assertTrue(nb > 0);
                    for (int k = 0; k < nb; k++) {
                        Assert.assertNotNull(pt.getDouble(k));
                    }
                }

            } else {
                fail("Null found");
            }
        }
    }

    /**
     * Test of containedBySingle method, of class PlayerType.
     */
    @Test
    public void testContainedBySingle() {
        System.out.println("containedBySingle");
        if (lrb == null) {
            fail("No lrb loaded");
        }
        if (lrb.getRosterTypeCount() == 0) {
            fail("No roster type in LRB");
        }
        for (int i = 0; i < lrb.getRosterTypeCount(); i++) {
            RosterType instance = lrb.getRosterType(i);
            if (instance != null) {

                for (int j = 0; j < instance.getPlayerTypeCount(); j++) {
                    PlayerType pt = instance.getPlayerType(j);
                    Assert.assertNotNull(pt);

                    for (int k = 0; k < pt.getSingleCount(); k++) {
                        SkillType st = pt.getSingle(k);
                        Assert.assertTrue(pt.containedBySingle(st));
                    }
                }
            } else {
                fail("Null found");
            }
        }
    }

    /**
     * Test of containedByDouble method, of class PlayerType.
     */
    @Test
    public void testContainedByDouble() {
        System.out.println("containedByDouble");
        if (lrb == null) {
            fail("No lrb loaded");
        }
        if (lrb.getRosterTypeCount() == 0) {
            fail("No roster type in LRB");
        }
        for (int i = 0; i < lrb.getRosterTypeCount(); i++) {
            RosterType instance = lrb.getRosterType(i);
            if (instance != null) {

                for (int j = 0; j < instance.getPlayerTypeCount(); j++) {
                    PlayerType pt = instance.getPlayerType(j);
                    Assert.assertNotNull(pt);

                    for (int k = 0; k < pt.getDoubleCount(); k++) {
                        SkillType st = pt.getDouble(k);
                        Assert.assertTrue(pt.containedByDouble(st));
                    }

                }
            } else {
                fail("Null found");
            }
        }
    }

    /**
     * Test of addSingle method, of class PlayerType.
     */
    @Test
    public void testAddSingle() {
        System.out.println("addSingle");
        if (lrb == null) {
            fail("No lrb loaded");
        }
        if (lrb.getRosterTypeCount() == 0) {
            fail("No roster type in LRB");
        }
        for (int i = 0; i < lrb.getRosterTypeCount(); i++) {
            RosterType instance = lrb.getRosterType(i);
            if (instance != null) {

                for (int j = 0; j < instance.getPlayerTypeCount(); j++) {
                    PlayerType pt = instance.getPlayerType(j);
                    Assert.assertNotNull(pt);

                    Assert.assertTrue(lrb.getSkillTypeCount() > 0);
                    SkillType st = lrb.getSkillType(0);

                    int nb = pt.getSingleCount();
                    pt.addSingle(st);
                    Assert.assertEquals(nb + 1, pt.getSingleCount());
                }

            } else {
                fail("Null found");
            }
        }
    }

    /**
     * Test of addDouble method, of class PlayerType.
     */
    @Test
    public void testAddDouble() {
        System.out.println("addDouble");
        if (lrb == null) {
            fail("No lrb loaded");
        }
        if (lrb.getRosterTypeCount() == 0) {
            fail("No roster type in LRB");
        }
        for (int i = 0; i < lrb.getRosterTypeCount(); i++) {
            RosterType instance = lrb.getRosterType(i);
            if (instance != null) {

                for (int j = 0; j < instance.getPlayerTypeCount(); j++) {
                    PlayerType pt = instance.getPlayerType(j);
                    Assert.assertNotNull(pt);

                    Assert.assertTrue(lrb.getSkillTypeCount() > 0);
                    SkillType st = lrb.getSkillType(0);

                    int nb = pt.getDoubleCount();
                    pt.addDouble(st);
                    Assert.assertEquals(nb + 1, pt.getDoubleCount());
                }

            } else {
                fail("Null found");
            }
        }
    }

    /**
     * Test of getDouble method, of class PlayerType.
     */
    @Test
    public void testGetDouble() {
        System.out.println("getDouble");
        if (lrb == null) {
            fail("No lrb loaded");
        }
        if (lrb.getRosterTypeCount() == 0) {
            fail("No roster type in LRB");
        }
        for (int i = 0; i < lrb.getRosterTypeCount(); i++) {
            RosterType instance = lrb.getRosterType(i);
            if (instance != null) {

                for (int j = 0; j < instance.getPlayerTypeCount(); j++) {
                    PlayerType pt = instance.getPlayerType(j);
                    Assert.assertNotNull(pt);

                    Assert.assertTrue(lrb.getSkillTypeCount() > 0);
                    SkillType st = lrb.getSkillType(0);

                    int nb = pt.getDoubleCount();
                    Assert.assertTrue(nb > 0);
                    for (int k = 0; k < nb; k++) {
                        Assert.assertNotNull(pt.getDouble(k));
                    }
                }

            } else {
                fail("Null found");
            }
        }
    }

    /**
     * Test of getDoubleCount method, of class PlayerType.
     */
    @Test
    public void testGetDoubleCount() {
        System.out.println("getDoubleCount");
        if (lrb == null) {
            fail("No lrb loaded");
        }
        if (lrb.getRosterTypeCount() == 0) {
            fail("No roster type in LRB");
        }
        for (int i = 0; i < lrb.getRosterTypeCount(); i++) {
            RosterType instance = lrb.getRosterType(i);
            if (instance != null) {

                for (int j = 0; j < instance.getPlayerTypeCount(); j++) {
                    PlayerType pt = instance.getPlayerType(j);
                    Assert.assertNotNull(pt);

                    Assert.assertTrue(lrb.getSkillTypeCount() > 0);
                    SkillType st = lrb.getSkillType(0);

                    int nb = pt.getDoubleCount();
                    Assert.assertTrue(nb > 0);
                }

            } else {
                fail("Null found");
            }
        }
    }

    /**
     * Test of setPosition method, of class PlayerType.
     */
    @Test
    public void testSetPosition() {
        System.out.println("setPosition");
        if (lrb == null) {
            fail("No lrb loaded");
        }
        if (lrb.getRosterTypeCount() == 0) {
            fail("No roster type in LRB");
        }
        for (int i = 0; i < lrb.getRosterTypeCount(); i++) {
            RosterType instance = lrb.getRosterType(i);
            if (instance != null) {

                for (int j = 0; j < instance.getPlayerTypeCount(); j++) {
                    PlayerType pt = instance.getPlayerType(j);
                    Assert.assertNotNull(pt);

                    String save = pt.getPosition();
                    pt.setPosition("Position");
                    Assert.assertEquals("Position", pt.getPosition());
                    pt.setPosition(save);

                }

            } else {
                fail("Null found");
            }
        }
    }

    /**
     * Test of setLimit method, of class PlayerType.
     */
    @Test
    public void testSetLimit() {
        System.out.println("setLimit");
        if (lrb == null) {
            fail("No lrb loaded");
        }
        if (lrb.getRosterTypeCount() == 0) {
            fail("No roster type in LRB");
        }
        for (int i = 0; i < lrb.getRosterTypeCount(); i++) {
            RosterType instance = lrb.getRosterType(i);
            if (instance != null) {

                for (int j = 0; j < instance.getPlayerTypeCount(); j++) {
                    PlayerType pt = instance.getPlayerType(j);
                    Assert.assertNotNull(pt);

                    int save = pt.getLimit();
                    pt.setLimit(6);
                    Assert.assertEquals(6, pt.getLimit());
                    pt.setLimit(save);

                }

            } else {
                fail("Null found");
            }
        }
    }

    /**
     * Test of setMovement method, of class PlayerType.
     */
    @Test
    public void testSetMovement() {
        System.out.println("setMovement");
        if (lrb == null) {
            fail("No lrb loaded");
        }
        if (lrb.getRosterTypeCount() == 0) {
            fail("No roster type in LRB");
        }
        for (int i = 0; i < lrb.getRosterTypeCount(); i++) {
            RosterType instance = lrb.getRosterType(i);
            if (instance != null) {

                for (int j = 0; j < instance.getPlayerTypeCount(); j++) {
                    PlayerType pt = instance.getPlayerType(j);
                    Assert.assertNotNull(pt);

                    int save = pt.getMovement();
                    pt.setMovement(6);
                    Assert.assertEquals(6, pt.getMovement());
                    pt.setMovement(save);

                }

            } else {
                fail("Null found");
            }
        }
    }

    /**
     * Test of setStrength method, of class PlayerType.
     */
    @Test
    public void testSetStrength() {
        System.out.println("setStrength");
        if (lrb == null) {
            fail("No lrb loaded");
        }
        if (lrb.getRosterTypeCount() == 0) {
            fail("No roster type in LRB");
        }
        for (int i = 0; i < lrb.getRosterTypeCount(); i++) {
            RosterType instance = lrb.getRosterType(i);
            if (instance != null) {

                for (int j = 0; j < instance.getPlayerTypeCount(); j++) {
                    PlayerType pt = instance.getPlayerType(j);
                    Assert.assertNotNull(pt);

                    int save = pt.getStrength();
                    pt.setStrength(6);
                    Assert.assertEquals(6, pt.getStrength());
                    pt.setStrength(save);

                }

            } else {
                fail("Null found");
            }
        }
    }

    /**
     * Test of setAgility method, of class PlayerType.
     */
    @Test
    public void testSetAgility() {
        System.out.println("setAgility");
        if (lrb == null) {
            fail("No lrb loaded");
        }
        if (lrb.getRosterTypeCount() == 0) {
            fail("No roster type in LRB");
        }
        for (int i = 0; i < lrb.getRosterTypeCount(); i++) {
            RosterType instance = lrb.getRosterType(i);
            if (instance != null) {

                for (int j = 0; j < instance.getPlayerTypeCount(); j++) {
                    PlayerType pt = instance.getPlayerType(j);
                    Assert.assertNotNull(pt);

                    int save = pt.getAgility();
                    pt.setAgility(6);
                    Assert.assertEquals(6, pt.getAgility());
                    pt.setAgility(save);

                }

            } else {
                fail("Null found");
            }
        }
    }

    /**
     * Test of setArmor method, of class PlayerType.
     */
    @Test
    public void testSetArmor() {
        System.out.println("setArmor");
        if (lrb == null) {
            fail("No lrb loaded");
        }
        if (lrb.getRosterTypeCount() == 0) {
            fail("No roster type in LRB");
        }
        for (int i = 0; i < lrb.getRosterTypeCount(); i++) {
            RosterType instance = lrb.getRosterType(i);
            if (instance != null) {

                for (int j = 0; j < instance.getPlayerTypeCount(); j++) {
                    PlayerType pt = instance.getPlayerType(j);
                    Assert.assertNotNull(pt);

                    int save = pt.getArmor();
                    pt.setArmor(6);
                    Assert.assertEquals(6, pt.getArmor());
                    pt.setArmor(save);

                }

            } else {
                fail("Null found");
            }
        }
    }

    /**
     * Test of setCost method, of class PlayerType.
     */
    @Test
    public void testSetCost() {
        System.out.println("setCost");
        if (lrb == null) {
            fail("No lrb loaded");
        }
        if (lrb.getRosterTypeCount() == 0) {
            fail("No roster type in LRB");
        }
        for (int i = 0; i < lrb.getRosterTypeCount(); i++) {
            RosterType instance = lrb.getRosterType(i);
            if (instance != null) {

                for (int j = 0; j < instance.getPlayerTypeCount(); j++) {
                    PlayerType pt = instance.getPlayerType(j);
                    Assert.assertNotNull(pt);

                    int save = pt.getCost();
                    pt.setCost(6);
                    Assert.assertEquals(6, pt.getCost());
                    pt.setCost(save);

                }

            } else {
                fail("Null found");
            }
        }
    }

}
