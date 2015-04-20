/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teamma.data;

import java.awt.Color;
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
    
    public SkillNGTest() {
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
     * Test of getmName method, of class Skill.
     */
    @Test
    public void testGetmName() {
        System.out.println("getmName");
        Skill instance = null;
        String expResult = "";
        String result = instance.getmName();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setmName method, of class Skill.
     */
    @Test
    public void testSetmName() {
        System.out.println("setmName");
        String mName = "";
        Skill instance = null;
        instance.setmName(mName);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getmCategory method, of class Skill.
     */
    @Test
    public void testGetmCategory() {
        System.out.println("getmCategory");
        Skill instance = null;
        SkillType expResult = null;
        SkillType result = instance.getmCategory();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setmCategory method, of class Skill.
     */
    @Test
    public void testSetmCategory() {
        System.out.println("setmCategory");
        SkillType mCategory = null;
        Skill instance = null;
        instance.setmCategory(mCategory);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getmColor method, of class Skill.
     */
    @Test
    public void testGetmColor() {
        System.out.println("getmColor");
        Skill instance = null;
        Color expResult = null;
        Color result = instance.getmColor();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setmColor method, of class Skill.
     */
    @Test
    public void testSetmColor() {
        System.out.println("setmColor");
        Color mColor = null;
        Skill instance = null;
        instance.setmColor(mColor);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
