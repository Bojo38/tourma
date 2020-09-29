/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teamma.data;

import java.awt.Color;
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
public class SkillNGTest {

    private static LRB lrb;

    public SkillNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        lrb = LRB.getLRB(LRB.E_Version.BB2016);
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
     * Test of getmName method, of class Skill.
     */
    @Test
    public void testGetmName() {
        System.out.println("getmName");
        if (lrb == null) {
            fail("No lrb loaded");
        }
        if (lrb.getSkillTypeCount() == 0) {
            fail("No skill type in LRB");
        }
        for (int i = 0; i < lrb.getSkillTypeCount(); i++) {
            SkillType instance = lrb.getSkillType(i);
            if (instance != null) {

                if (instance.getSkillCount() == 0) {
                    fail("No skill in " + instance.getName());
                }
                for (int j = 0; j < instance.getSkillCount(); j++) {
                    Skill sk = instance.getSkill(j);
                    Assert.assertNotNull(sk);
                    String result = sk.getmName();
                    Assert.assertNotNull(result);
                }
            } else {
                fail("Null skill type");
            }
        }
    }

    /**
     * Test of setmName method, of class Skill.
     */
    @Test
    public void testSetmName() {
        System.out.println("setmName");
        if (lrb == null) {
            fail("No lrb loaded");
        }
        if (lrb.getSkillTypeCount() == 0) {
            fail("No skill type in LRB");
        }
        for (int i = 0; i < lrb.getSkillTypeCount(); i++) {
            SkillType instance = lrb.getSkillType(i);
            if (instance != null) {

                if (instance.getSkillCount() == 0) {
                    fail("No skill in " + instance.getName());
                }
                for (int j = 0; j < instance.getSkillCount(); j++) {
                    Skill sk = instance.getSkill(j);
                    Assert.assertNotNull(sk);
                    String save=sk.getmName();
                    sk.setmName("Alpha");
                    String result = sk.getmName();
                    Assert.assertEquals(result, "Alpha");
                    sk.setmName(save);
                }
            } else {
                fail("Null skill type");
            }
        }
    }

    /**
     * Test of getmCategory method, of class Skill.
     */
    @Test
    public void testGetmCategory() {
        System.out.println("getmCategory");
        if (lrb == null) {
            fail("No lrb loaded");
        }
        if (lrb.getSkillTypeCount() == 0) {
            fail("No skill type in LRB");
        }
        for (int i = 0; i < lrb.getSkillTypeCount(); i++) {
            SkillType instance = lrb.getSkillType(i);
            if (instance != null) {

                if (instance.getSkillCount() == 0) {
                    fail("No skill in " + instance.getName());
                }
                for (int j = 0; j < instance.getSkillCount(); j++) {
                    Skill sk = instance.getSkill(j);
                    Assert.assertNotNull(sk);
                    SkillType st= sk.getmCategory();
                    Assert.assertEquals(st, instance);
                }
            } else {
                fail("Null skill type");
            }
        }
    }

    /**
     * Test of setmCategory method, of class Skill.
     */
    @Test
    public void testSetmCategory() {
        System.out.println("setmCategory");
        if (lrb == null) {
            fail("No lrb loaded");
        }
        if (lrb.getSkillTypeCount() == 0) {
            fail("No skill type in LRB");
        }
        for (int i = 0; i < lrb.getSkillTypeCount(); i++) {
            SkillType instance = lrb.getSkillType(i);
            if (instance != null) {

                if (instance.getSkillCount() == 0) {
                    fail("No skill in " + instance.getName());
                }
                for (int j = 0; j < instance.getSkillCount(); j++) {
                    
                    Skill sk = instance.getSkill(j);
                    Assert.assertNotNull(sk);
                    
                    SkillType save=sk.getmCategory();
                    SkillType nst=new SkillType("TEST", "T");
                    sk.setmCategory(nst);
                    SkillType st=sk.getmCategory();
                    assertEquals(nst, st);
                    sk.setmCategory(save);
                    
                }
            } else {
                fail("Null skill type");
            }
        }
    }

    /**
     * Test of getmColor method, of class Skill.
     */
    @Test
    public void testGetmColor() {
        System.out.println("getmColor");
         if (lrb == null) {
            fail("No lrb loaded");
        }
        if (lrb.getSkillTypeCount() == 0) {
            fail("No skill type in LRB");
        }
        for (int i = 0; i < lrb.getSkillTypeCount(); i++) {
            SkillType instance = lrb.getSkillType(i);
            if (instance != null) {

                if (instance.getSkillCount() == 0) {
                    fail("No skill in " + instance.getName());
                }
                for (int j = 0; j < instance.getSkillCount(); j++) {
                    
                    Skill sk = instance.getSkill(j);
                    Assert.assertNotNull(sk);
                    
                    Color save=sk.getmColor();
                    Assert.assertNotNull(save);
                    
                }
            } else {
                fail("Null skill type");
            }
        }
    }

    /**
     * Test of setmColor method, of class Skill.
     */
    @Test
    public void testSetmColor() {
        System.out.println("setmColor");
         if (lrb == null) {
            fail("No lrb loaded");
        }
        if (lrb.getSkillTypeCount() == 0) {
            fail("No skill type in LRB");
        }
        for (int i = 0; i < lrb.getSkillTypeCount(); i++) {
            SkillType instance = lrb.getSkillType(i);
            if (instance != null) {

                if (instance.getSkillCount() == 0) {
                    fail("No skill in " + instance.getName());
                }
                for (int j = 0; j < instance.getSkillCount(); j++) {
                    
                    Skill sk = instance.getSkill(j);
                    Assert.assertNotNull(sk);
                    
                    Color save=sk.getmColor();
                    sk.setmColor(Color.BLACK);
                    Color c=sk.getmColor();
                    assertEquals(c,Color.BLACK);
                    sk.setmColor(save);
                    
                }
            } else {
                fail("Null skill type");
            }
        }
    }

}
