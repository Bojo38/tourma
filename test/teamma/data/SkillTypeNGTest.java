/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teamma.data;

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
    
    public SkillTypeNGTest() {
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
     * Test of getName method, of class SkillType.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        SkillType instance = null;
        String expResult = "";
        String result = instance.getName();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setName method, of class SkillType.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String _name = "";
        SkillType instance = null;
        instance.setName(_name);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAccronym method, of class SkillType.
     */
    @Test
    public void testGetAccronym() {
        System.out.println("getAccronym");
        SkillType instance = null;
        String expResult = "";
        String result = instance.getAccronym();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setAccronym method, of class SkillType.
     */
    @Test
    public void testSetAccronym() {
        System.out.println("setAccronym");
        String _accronym = "";
        SkillType instance = null;
        instance.setAccronym(_accronym);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of clearSkills method, of class SkillType.
     */
    @Test
    public void testClearSkills() {
        System.out.println("clearSkills");
        SkillType instance = null;
        instance.clearSkills();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addSkill method, of class SkillType.
     */
    @Test
    public void testAddSkill() {
        System.out.println("addSkill");
        Skill s = null;
        SkillType instance = null;
        instance.addSkill(s);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSkillCount method, of class SkillType.
     */
    @Test
    public void testGetSkillCount() {
        System.out.println("getSkillCount");
        SkillType instance = null;
        int expResult = 0;
        int result = instance.getSkillCount();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSkill method, of class SkillType.
     */
    @Test
    public void testGetSkill() {
        System.out.println("getSkill");
        int i = 0;
        SkillType instance = null;
        Skill expResult = null;
        Skill result = instance.getSkill(i);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isSpecial method, of class SkillType.
     */
    @Test
    public void testIsSpecial() {
        System.out.println("isSpecial");
        SkillType instance = null;
        boolean expResult = false;
        boolean result = instance.isSpecial();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSpecial method, of class SkillType.
     */
    @Test
    public void testSetSpecial() {
        System.out.println("setSpecial");
        boolean _special = false;
        SkillType instance = null;
        instance.setSpecial(_special);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
