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
public class PlayerNGTest {
    
    public PlayerNGTest() {
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
     * Test of getPlayertype method, of class Player.
     */
    @Test
    public void testGetPlayertype() {
        System.out.println("getPlayertype");
        Player instance = null;
        PlayerType expResult = null;
        PlayerType result = instance.getPlayertype();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getName method, of class Player.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Player instance = null;
        String expResult = "";
        String result = instance.getName();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSkill method, of class Player.
     */
    @Test
    public void testGetSkill() {
        System.out.println("getSkill");
        int i = 0;
        Player instance = null;
        Skill expResult = null;
        Skill result = instance.getSkill(i);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSkillCount method, of class Player.
     */
    @Test
    public void testGetSkillCount() {
        System.out.println("getSkillCount");
        Player instance = null;
        int expResult = 0;
        int result = instance.getSkillCount();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeSkill method, of class Player.
     */
    @Test
    public void testRemoveSkill() {
        System.out.println("removeSkill");
        int i = 0;
        Player instance = null;
        instance.removeSkill(i);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addSkill method, of class Player.
     */
    @Test
    public void testAddSkill() {
        System.out.println("addSkill");
        Skill s = null;
        Player instance = null;
        instance.addSkill(s);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMovement method, of class Player.
     */
    @Test
    public void testGetMovement() {
        System.out.println("getMovement");
        Player instance = null;
        int expResult = 0;
        int result = instance.getMovement();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStrength method, of class Player.
     */
    @Test
    public void testGetStrength() {
        System.out.println("getStrength");
        Player instance = null;
        int expResult = 0;
        int result = instance.getStrength();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAgility method, of class Player.
     */
    @Test
    public void testGetAgility() {
        System.out.println("getAgility");
        Player instance = null;
        int expResult = 0;
        int result = instance.getAgility();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getArmor method, of class Player.
     */
    @Test
    public void testGetArmor() {
        System.out.println("getArmor");
        Player instance = null;
        int expResult = 0;
        int result = instance.getArmor();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getValue method, of class Player.
     */
    @Test
    public void testGetValue() {
        System.out.println("getValue");
        boolean bWithSkill = false;
        Player instance = null;
        int expResult = 0;
        int result = instance.getValue(bWithSkill);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPlayertype method, of class Player.
     */
    @Test
    public void testSetPlayertype() {
        System.out.println("setPlayertype");
        PlayerType _playertype = null;
        Player instance = null;
        instance.setPlayertype(_playertype);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setName method, of class Player.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String _name = "";
        Player instance = null;
        instance.setName(_name);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
