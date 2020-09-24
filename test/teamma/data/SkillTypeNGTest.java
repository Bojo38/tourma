/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teamma.data;

import java.util.ArrayList;
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
public class SkillTypeNGTest {

    private static LRB lrb;

    public SkillTypeNGTest() {
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
     * Test of getName method, of class SkillType.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");

        if (lrb == null) {
            fail("No lrb loaded");
        }
        if (lrb.getStarPlayerCount() == 0) {
            fail("No star player in LRB");
        }
        for (int i = 0; i < lrb.getSkillTypeCount(); i++) {
            SkillType instance = lrb.getSkillType(i);
            if (instance != null) {
//            String expResult = "";
                String result = instance.getName();
                Assert.assertNotNull(result);
            } else {
                fail("Null skill type");
            }
        }

    }

    /**
     * Test of setName method, of class SkillType.
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
        for (int i = 0; i < lrb.getSkillTypeCount(); i++) {
            SkillType instance = lrb.getSkillType(i);
            if (instance != null) {
                String save = instance.getName();
                instance.setName("Toto");
                String result = instance.getName();
                Assert.assertEquals(result, "Toto");
                instance.setName(save);
            } else {
                fail("Null skill type");
            }
        }
    }

    /**
     * Test of getAccronym method, of class SkillType.
     */
    @Test
    public void testGetAccronym() {
        System.out.println("getAccronym");
        if (lrb == null) {
            fail("No lrb loaded");
        }
        if (lrb.getStarPlayerCount() == 0) {
            fail("No star player in LRB");
        }
        for (int i = 0; i < lrb.getSkillTypeCount(); i++) {
            SkillType instance = lrb.getSkillType(i);
            if (instance != null) {
                String result = instance.getAccronym();
                Assert.assertNotNull(result);
            } else {
                fail("Null skill type");
            }
        }
    }

    /**
     * Test of setAccronym method, of class SkillType.
     */
    @Test
    public void testSetAccronym() {
        System.out.println("setAccronym");
        if (lrb == null) {
            fail("No lrb loaded");
        }
        if (lrb.getStarPlayerCount() == 0) {
            fail("No star player in LRB");
        }
        for (int i = 0; i < lrb.getSkillTypeCount(); i++) {
            SkillType instance = lrb.getSkillType(i);
            if (instance != null) {
                String save = instance.getAccronym();
                instance.setAccronym("T");
                String result = instance.getAccronym();
                Assert.assertEquals(result, "T");
                instance.setAccronym(save);
            } else {
                fail("Null skill type");
            }
        }
    }

    /**
     * Test of clearSkills method, of class SkillType.
     */
    @Test
    public void testClearSkills() {
        System.out.println("clearSkills");
        if (lrb == null) {
            fail("No lrb loaded");
        }
        if (lrb.getStarPlayerCount() == 0) {
            fail("No star player in LRB");
        }
        for (int i = 0; i < lrb.getSkillTypeCount(); i++) {
            SkillType instance = lrb.getSkillType(i);
            if (instance != null) {
                int nb=instance.getSkillCount();
                if (nb==0)
                {
                    fail("Number of skill is null for "+instance.getName());
                }
                ArrayList<Skill> list=new ArrayList<>();
                for (int j=0; j<instance.getSkillCount(); j++)
                {
                    list.add(instance.getSkill(j));
                }
                instance.clearSkills();
                Assert.assertEquals(instance.getSkillCount(), 0);
                for (int j=0; j<list.size(); j++)
                {
                    instance.addSkill(list.get(j));
                }
            } else {
                fail("Null skill type");
            }
        }
    }

    /**
     * Test of addSkill method, of class SkillType.
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
        for (int i = 0; i < lrb.getSkillTypeCount(); i++) {
            SkillType instance = lrb.getSkillType(i);
            if (instance != null) {
                int nb=instance.getSkillCount();
                if (nb==0)
                {
                    fail("Number of skill is null for "+instance.getName());
                }
                ArrayList<Skill> list=new ArrayList<>();
                for (int j=0; j<instance.getSkillCount(); j++)
                {
                    list.add(instance.getSkill(j));
                }
                instance.clearSkills();
                
                for (int j=0; j<list.size(); j++)
                {
                    int idx=instance.getSkillCount();
                    instance.addSkill(list.get(j));
                    Skill tmp=instance.getSkill(idx);
                    assertEquals(tmp, list.get(j));
                    assertEquals(idx+1, instance.getSkillCount());
                }
            } else {
                fail("Null skill type");
            }
        }
    }

    /**
     * Test of getSkillCount method, of class SkillType.
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
        for (int i = 0; i < lrb.getSkillTypeCount(); i++) {
            SkillType instance = lrb.getSkillType(i);
            if (instance != null) {
                int nb=instance.getSkillCount();
                if (nb==0)
                {
                    fail("Number of skill is null for "+instance.getName());
                }
               } else {
                fail("Null skill type");
            }
        }
    }

    /**
     * Test of getSkill method, of class SkillType.
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
        for (int i = 0; i < lrb.getSkillTypeCount(); i++) {
            SkillType instance = lrb.getSkillType(i);
            if (instance != null) {
                int nb=instance.getSkillCount();
                if (nb==0)
                {
                    fail("Number of skill is null for "+instance.getName());
                }
                ArrayList<Skill> list=new ArrayList<>();
                for (int j=0; j<instance.getSkillCount(); j++)
                {
                    list.add(instance.getSkill(j));
                }
                instance.clearSkills();
                
                for (int j=0; j<list.size(); j++)
                {
                    int idx=instance.getSkillCount();
                    instance.addSkill(list.get(j));
                    Skill tmp=instance.getSkill(idx);
                    assertEquals(tmp, list.get(j));
                    assertEquals(idx+1, instance.getSkillCount());
                }
            } else {
                fail("Null skill type");
            }
        }
    }

    /**
     * Test of isSpecial method, of class SkillType.
     */
    @Test
    public void testIsSpecial() {
        System.out.println("isSpecial");
        if (lrb == null) {
            fail("No lrb loaded");
        }
        if (lrb.getStarPlayerCount() == 0) {
            fail("No star player in LRB");
        }        
         boolean instance = lrb.isAllowSpecialSkills();         
    }

    /**
     * Test of setSpecial method, of class SkillType.
     */
    @Test
    public void testSetSpecial() {
        System.out.println("setSpecial");
       if (lrb == null) {
            fail("No lrb loaded");
        }
        if (lrb.getStarPlayerCount() == 0) {
            fail("No star player in LRB");
        }        
        boolean save= lrb.isAllowSpecialSkills();         
        lrb.setAllowSpecialSkills(true);         
         boolean instance = lrb.isAllowSpecialSkills();         
         Assert.assertTrue(instance);
         lrb.setAllowSpecialSkills(false);         
         instance = lrb.isAllowSpecialSkills();         
         Assert.assertFalse(instance);
         lrb.setAllowSpecialSkills(save);         
    }

}
