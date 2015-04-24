/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teamma.data;

import java.util.logging.Logger;
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
public class StarPlayerNGTest {

    private static final Logger LOG = Logger.getLogger(StarPlayerNGTest.class.getName());

    private static LRB lrb;

    @BeforeClass
    public static void setUpClass() throws Exception {
        lrb = LRB.getLRB();
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
        StarPlayer instance = lrb.getStarPlayer(0);
        if (instance != null) {
//            String expResult = "";
            String result = instance.getName();
            Assert.assertNotNull(result);
        } else {
            fail("Null star player name");
        }
    }

    /**
     * Test of setName method, of class StarPlayer.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
//        String _name = "";
        StarPlayer instance = lrb.getStarPlayer(0);
        if (instance != null) {
//            String expResult = "";
            instance.setName("TOTO");
            String result = instance.getName();
            Assert.assertEquals(result, "TOTO");
        } else {
            fail("Null star player name");
        }
    }

    /**
     * Test of getPosition method, of class StarPlayer.
     */
    @Test
    public void testGetPosition() {
        System.out.println("getPosition");
        StarPlayer instance = null;
        if (instance != null) {
//            String expResult = "";
            String result = instance.getPosition();
            Assert.assertNotNull(result);
        } else {
            // TODO review the generated test code and remove the default call to fail.
            fail("The test case is a prototype.");
        }
    }

    /**
     * Test of setPosition method, of class StarPlayer.
     */
    @Test
    public void testSetPosition() {
        System.out.println("setPosition");
        String _position = "";
        StarPlayer instance = null;
        instance.setPosition(_position);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLimit method, of class StarPlayer.
     */
    @Test
    public void testGetLimit() {
        System.out.println("getLimit");
        StarPlayer instance = null;
        int expResult = 0;
        int result = instance.getLimit();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setLimit method, of class StarPlayer.
     */
    @Test
    public void testSetLimit() {
        System.out.println("setLimit");
        int _limit = 0;
        StarPlayer instance = null;
        instance.setLimit(_limit);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMovement method, of class StarPlayer.
     */
    @Test
    public void testGetMovement() {
        System.out.println("getMovement");
        StarPlayer instance = null;
        int expResult = 0;
        int result = instance.getMovement();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setMovement method, of class StarPlayer.
     */
    @Test
    public void testSetMovement() {
        System.out.println("setMovement");
        int _movement = 0;
        StarPlayer instance = null;
        instance.setMovement(_movement);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStrength method, of class StarPlayer.
     */
    @Test
    public void testGetStrength() {
        System.out.println("getStrength");
        StarPlayer instance = null;
        int expResult = 0;
        int result = instance.getStrength();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setStrength method, of class StarPlayer.
     */
    @Test
    public void testSetStrength() {
        System.out.println("setStrength");
        int _strength = 0;
        StarPlayer instance = null;
        instance.setStrength(_strength);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAgility method, of class StarPlayer.
     */
    @Test
    public void testGetAgility() {
        System.out.println("getAgility");
        StarPlayer instance = null;
        int expResult = 0;
        int result = instance.getAgility();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setAgility method, of class StarPlayer.
     */
    @Test
    public void testSetAgility() {
        System.out.println("setAgility");
        int _agility = 0;
        StarPlayer instance = null;
        instance.setAgility(_agility);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getArmor method, of class StarPlayer.
     */
    @Test
    public void testGetArmor() {
        System.out.println("getArmor");
        StarPlayer instance = null;
        int expResult = 0;
        int result = instance.getArmor();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setArmor method, of class StarPlayer.
     */
    @Test
    public void testSetArmor() {
        System.out.println("setArmor");
        int _armor = 0;
        StarPlayer instance = null;
        instance.setArmor(_armor);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCost method, of class StarPlayer.
     */
    @Test
    public void testGetCost() {
        System.out.println("getCost");
        StarPlayer instance = null;
        int expResult = 0;
        int result = instance.getCost();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCost method, of class StarPlayer.
     */
    @Test
    public void testSetCost() {
        System.out.println("setCost");
        int _cost = 0;
        StarPlayer instance = null;
        instance.setCost(_cost);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addSkill method, of class StarPlayer.
     */
    @Test
    public void testAddSkill() {
        System.out.println("addSkill");
        Skill s = null;
        StarPlayer instance = null;
        instance.addSkill(s);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSkillCount method, of class StarPlayer.
     */
    @Test
    public void testGetSkillCount() {
        System.out.println("getSkillCount");
        StarPlayer instance = null;
        int expResult = 0;
        int result = instance.getSkillCount();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSkill method, of class StarPlayer.
     */
    @Test
    public void testGetSkill() {
        System.out.println("getSkill");
        int i = 0;
        StarPlayer instance = null;
        Skill expResult = null;
        Skill result = instance.getSkill(i);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addRoster method, of class StarPlayer.
     */
    @Test
    public void testAddRoster() {
        System.out.println("addRoster");
        RosterType r = null;
        StarPlayer instance = null;
        instance.addRoster(r);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRosterCount method, of class StarPlayer.
     */
    @Test
    public void testGetRosterCount() {
        System.out.println("getRosterCount");
        StarPlayer instance = null;
        int expResult = 0;
        int result = instance.getRosterCount();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRoster method, of class StarPlayer.
     */
    @Test
    public void testGetRoster() {
        System.out.println("getRoster");
        int i = 0;
        StarPlayer instance = null;
        RosterType expResult = null;
        RosterType result = instance.getRoster(i);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
