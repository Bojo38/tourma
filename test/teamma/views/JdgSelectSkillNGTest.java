/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teamma.views;

import java.awt.Color;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import teamma.data.Player;
/**
 *
 * @author WFMJ7631
 */
public class JdgSelectSkillNGTest {
    
    public JdgSelectSkillNGTest() {
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
     * Test of getPlayer method, of class JdgSelectSkill.
     */
    @Test
    public void testGetPlayer() {
        System.out.println("getPlayer");
        JdgSelectSkill instance = null;
        Player expResult = null;
        Player result = instance.getPlayer();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPlayer method, of class JdgSelectSkill.
     */
    @Test
    public void testSetPlayer() {
        System.out.println("setPlayer");
        Player _player = null;
        JdgSelectSkill instance = null;
        instance.setPlayer(_player);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getColor method, of class JdgSelectSkill.
     */
    @Test
    public void testGetColor() {
        System.out.println("getColor");
        JdgSelectSkill instance = null;
        Color expResult = null;
        Color result = instance.getColor();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setColor method, of class JdgSelectSkill.
     */
    @Test
    public void testSetColor() {
        System.out.println("setColor");
        Color _color = null;
        JdgSelectSkill instance = null;
        instance.setColor(_color);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
