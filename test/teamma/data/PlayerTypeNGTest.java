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
public class PlayerTypeNGTest {
    
    public PlayerTypeNGTest() {
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
     * Test of getPosition method, of class PlayerType.
     */
    @Test
    public void testGetPosition() {
        System.out.println("getPosition");
        PlayerType instance = null;
        String expResult = "";
        String result = instance.getPosition();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLimit method, of class PlayerType.
     */
    @Test
    public void testGetLimit() {
        System.out.println("getLimit");
        PlayerType instance = null;
        int expResult = 0;
        int result = instance.getLimit();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMovement method, of class PlayerType.
     */
    @Test
    public void testGetMovement() {
        System.out.println("getMovement");
        PlayerType instance = null;
        int expResult = 0;
        int result = instance.getMovement();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStrength method, of class PlayerType.
     */
    @Test
    public void testGetStrength() {
        System.out.println("getStrength");
        PlayerType instance = null;
        int expResult = 0;
        int result = instance.getStrength();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAgility method, of class PlayerType.
     */
    @Test
    public void testGetAgility() {
        System.out.println("getAgility");
        PlayerType instance = null;
        int expResult = 0;
        int result = instance.getAgility();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getArmor method, of class PlayerType.
     */
    @Test
    public void testGetArmor() {
        System.out.println("getArmor");
        PlayerType instance = null;
        int expResult = 0;
        int result = instance.getArmor();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCost method, of class PlayerType.
     */
    @Test
    public void testGetCost() {
        System.out.println("getCost");
        PlayerType instance = null;
        int expResult = 0;
        int result = instance.getCost();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSkill method, of class PlayerType.
     */
    @Test
    public void testGetSkill() {
        System.out.println("getSkill");
        int i = 0;
        PlayerType instance = null;
        Skill expResult = null;
        Skill result = instance.getSkill(i);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addSkill method, of class PlayerType.
     */
    @Test
    public void testAddSkill() {
        System.out.println("addSkill");
        Skill s = null;
        PlayerType instance = null;
        instance.addSkill(s);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSkillCount method, of class PlayerType.
     */
    @Test
    public void testGetSkillCount() {
        System.out.println("getSkillCount");
        PlayerType instance = null;
        int expResult = 0;
        int result = instance.getSkillCount();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSingle method, of class PlayerType.
     */
    @Test
    public void testGetSingle() {
        System.out.println("getSingle");
        int i = 0;
        PlayerType instance = null;
        SkillType expResult = null;
        SkillType result = instance.getSingle(i);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSingleCount method, of class PlayerType.
     */
    @Test
    public void testGetSingleCount() {
        System.out.println("getSingleCount");
        PlayerType instance = null;
        int expResult = 0;
        int result = instance.getSingleCount();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of containedBySingle method, of class PlayerType.
     */
    @Test
    public void testContainedBySingle() {
        System.out.println("containedBySingle");
        SkillType s = null;
        PlayerType instance = null;
        boolean expResult = false;
        boolean result = instance.containedBySingle(s);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of containedByDouble method, of class PlayerType.
     */
    @Test
    public void testContainedByDouble() {
        System.out.println("containedByDouble");
        SkillType s = null;
        PlayerType instance = null;
        boolean expResult = false;
        boolean result = instance.containedByDouble(s);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addSingle method, of class PlayerType.
     */
    @Test
    public void testAddSingle() {
        System.out.println("addSingle");
        SkillType s = null;
        PlayerType instance = null;
        instance.addSingle(s);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addDouble method, of class PlayerType.
     */
    @Test
    public void testAddDouble() {
        System.out.println("addDouble");
        SkillType s = null;
        PlayerType instance = null;
        instance.addDouble(s);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDouble method, of class PlayerType.
     */
    @Test
    public void testGetDouble() {
        System.out.println("getDouble");
        int i = 0;
        PlayerType instance = null;
        SkillType expResult = null;
        SkillType result = instance.getDouble(i);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDoubleCount method, of class PlayerType.
     */
    @Test
    public void testGetDoubleCount() {
        System.out.println("getDoubleCount");
        PlayerType instance = null;
        int expResult = 0;
        int result = instance.getDoubleCount();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPosition method, of class PlayerType.
     */
    @Test
    public void testSetPosition() {
        System.out.println("setPosition");
        String _position = "";
        PlayerType instance = null;
        instance.setPosition(_position);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setLimit method, of class PlayerType.
     */
    @Test
    public void testSetLimit() {
        System.out.println("setLimit");
        int _limit = 0;
        PlayerType instance = null;
        instance.setLimit(_limit);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setMovement method, of class PlayerType.
     */
    @Test
    public void testSetMovement() {
        System.out.println("setMovement");
        int _movement = 0;
        PlayerType instance = null;
        instance.setMovement(_movement);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setStrength method, of class PlayerType.
     */
    @Test
    public void testSetStrength() {
        System.out.println("setStrength");
        int _strength = 0;
        PlayerType instance = null;
        instance.setStrength(_strength);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setAgility method, of class PlayerType.
     */
    @Test
    public void testSetAgility() {
        System.out.println("setAgility");
        int _agility = 0;
        PlayerType instance = null;
        instance.setAgility(_agility);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setArmor method, of class PlayerType.
     */
    @Test
    public void testSetArmor() {
        System.out.println("setArmor");
        int _armor = 0;
        PlayerType instance = null;
        instance.setArmor(_armor);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCost method, of class PlayerType.
     */
    @Test
    public void testSetCost() {
        System.out.println("setCost");
        int _cost = 0;
        PlayerType instance = null;
        instance.setCost(_cost);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
