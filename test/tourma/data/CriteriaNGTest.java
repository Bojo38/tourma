/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import org.jdom.Element;
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
public class CriteriaNGTest {

    public CriteriaNGTest() {
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
     * Test of getXMLElement method, of class Criteria.
     */
    @Test
    public void testGetXMLElement() {
        System.out.println("getXMLElement");
        Criteria instance = new Criteria("Test");
        instance.setPointsAgainst(1);
        instance.setPointsFor(2);
        instance.setPointsTeamAgainst(3);
        instance.setPointsTeamFor(4);

        Element result = instance.getXMLElement();

        Criteria crit = new Criteria("None");
        crit.setXMLElement(result);
        assertEquals(instance, crit);
    }

    /**
     * Test of setXMLElement method, of class Criteria.
     */
    @Test
    public void testSetXMLElement() {
        System.out.println("setXMLElement");
        Criteria instance = new Criteria("Test");
        instance.setPointsAgainst(1);
        instance.setPointsFor(2);
        instance.setPointsTeamAgainst(3);
        instance.setPointsTeamFor(4);

        Element result = instance.getXMLElement();

        Criteria crit = new Criteria("None");
        crit.setXMLElement(result);
        assertEquals(instance, crit);
    }

    /**
     * Test of getName method, of class Criteria.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Criteria instance = new Criteria("Test");
        instance.setName("Test2");
        assertEquals(instance.getName(), "Test2");
    }

    /**
     * Test of setName method, of class Criteria.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        Criteria instance = new Criteria("Test");
        instance.setName("Test2");
        assertEquals(instance.getName(), "Test2");
    }

    /**
     * Test of getPointsFor method, of class Criteria.
     */
    @Test
    public void testGetPointsFor() {
        System.out.println("getPointsFor");
        Criteria instance = new Criteria("Test");
        int value = 15;
        instance.setPointsFor(value);
        assertEquals(instance.getPointsFor(), value);
    }

    /**
     * Test of setPointsFor method, of class Criteria.
     */
    @Test
    public void testSetPointsFor() {
        System.out.println("setPointsFor");
        Criteria instance = new Criteria("Test");
        int value = 15;
        instance.setPointsFor(value);
        assertEquals(instance.getPointsFor(), value);
    }

    /**
     * Test of getPointsAgainst method, of class Criteria.
     */
    @Test
    public void testGetPointsAgainst() {
        System.out.println("getPointsAgainst");
        Criteria instance = new Criteria("Test");
        int value = 18;
        instance.setPointsAgainst(value);
        assertEquals(instance.getPointsAgainst(), value);
    }

    /**
     * Test of setPointsAgainst method, of class Criteria.
     */
    @Test
    public void testSetPointsAgainst() {
        System.out.println("setPointsAgainst");
        Criteria instance = new Criteria("Test");
        int value = 18;
        instance.setPointsAgainst(value);
        assertEquals(instance.getPointsAgainst(), value);
    }

    /**
     * Test of getPointsTeamFor method, of class Criteria.
     */
    @Test
    public void testGetPointsTeamFor() {
        System.out.println("getPointsTeamFor");
        Criteria instance = new Criteria("Test");
        int value = 21;
        instance.setPointsTeamFor(value);
        assertEquals(instance.getPointsTeamFor(), value);
    }

    /**
     * Test of setPointsTeamFor method, of class Criteria.
     */
    @Test
    public void testSetPointsTeamFor() {
        System.out.println("setPointsTeamFor");
        Criteria instance = new Criteria("Test");
        int value = 21;
        instance.setPointsTeamFor(value);
        assertEquals(instance.getPointsTeamFor(), value);
    }

    /**
     * Test of getPointsTeamAgainst method, of class Criteria.
     */
    @Test
    public void testGetPointsTeamAgainst() {
        System.out.println("getPointsTeamAgainst");
        Criteria instance = new Criteria("Test");
        int value = 21;
        instance.setPointsTeamAgainst(value);
        assertEquals(instance.getPointsTeamAgainst(), value);
    }

    /**
     * Test of setPointsTeamAgainst method, of class Criteria.
     */
    @Test
    public void testSetPointsTeamAgainst() {
        System.out.println("setPointsTeamAgainst");
        Criteria instance = new Criteria("Test");
        int value = 21;
        instance.setPointsTeamAgainst(value);
        assertEquals(instance.getPointsTeamAgainst(), value);
    }

    /**
     * Test of getUID method, of class Criteria.
     */
    @Test
    public void testGetUID() {
        System.out.println("getUID");
        Criteria instance = null;
        int expResult = 0;
        int result = instance.getUID();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setUID method, of class Criteria.
     */
    @Test
    public void testSetUID() {
        System.out.println("setUID");
        int UID = 0;
        Criteria instance = null;
        instance.setUID(UID);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of pull method, of class Criteria.
     */
    @Test
    public void testPull() {
        System.out.println("pull");
        Criteria crit = null;
        Criteria instance = null;
        instance.pull(crit);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAccronym method, of class Criteria.
     */
    @Test
    public void testGetAccronym() {
        System.out.println("getAccronym");
        Criteria instance = new Criteria("touchdown");
        String expResult = "touchdown";
        String result = instance.getAccronym();
        assertEquals(result, expResult);
        instance.setAccronym("td");
        result = instance.getAccronym();
        assertEquals(result, "td");

    }

    /**
     * Test of setAccronym method, of class Criteria.
     */
    @Test
    public void testSetAccronym() {
        System.out.println("setAccronym");
        String accronym = "";
        Criteria instance = null;
        instance.setAccronym(accronym);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class Criteria.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = null;
        Criteria instance = new Criteria("td");
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(result, expResult);
    }

    /**
     * Test of getCriticalThreshold method, of class Criteria.
     */
    @Test
    public void testGetCriticalThreshold() {
        System.out.println("getCriticalThreshold");
        Criteria instance = new Criteria("touchdowns");
        int expResult = 10;
        int result = instance.getCriticalThreshold();
        assertEquals(result, expResult);
        instance.setCriticalThreshold(5);
        result = instance.getCriticalThreshold();
        assertEquals(result, 5);

    }

    /**
     * Test of setCriticalThreshold method, of class Criteria.
     */
    @Test
    public void testSetCriticalThreshold() {
        System.out.println("setCriticalThreshold");
        Criteria instance = new Criteria("touchdowns");
        int expResult = 10;
        int result = instance.getCriticalThreshold();
        assertEquals(result, expResult);
        instance.setCriticalThreshold(5);
        result = instance.getCriticalThreshold();
        assertEquals(result, 5);
    }

    /**
     * Test of getOffensiveDiffThreshold method, of class Criteria.
     */
    @Test
    public void testGetOffensiveDiffThreshold() {
        System.out.println("getOffensiveDiffThreshold");
        Criteria instance = null;
        int expResult = 0;
        int result = instance.getOffensiveDiffThreshold();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setOffensiveDiffThreshold method, of class Criteria.
     */
    @Test
    public void testSetOffensiveDiffThreshold() {
        System.out.println("setOffensiveDiffThreshold");
        int mOffensiveDiffThreshold = 0;
        Criteria instance = null;
        instance.setOffensiveDiffThreshold(mOffensiveDiffThreshold);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDefensiveDiffThreshold method, of class Criteria.
     */
    @Test
    public void testGetDefensiveDiffThreshold() {
        System.out.println("getDefensiveDiffThreshold");
        Criteria instance = new Criteria("td");
        int expResult = 0;
        int result = instance.getDefensiveDiffThreshold();
        assertEquals(result, expResult);
        expResult = 10;
        instance.setDefensiveDiffThreshold(expResult);
        result = instance.getDefensiveDiffThreshold();
        assertEquals(result, expResult);
    }

    /**
     * Test of setDefensiveDiffThreshold method, of class Criteria.
     */
    @Test
    public void testSetDefensiveDiffThreshold() {
        System.out.println("setDefensiveDiffThreshold");
        Criteria instance = new Criteria("td");
        int expResult = 0;
        int result = instance.getDefensiveDiffThreshold();
        assertEquals(result, expResult);
        expResult = 10;
        instance.setDefensiveDiffThreshold(expResult);
        result = instance.getDefensiveDiffThreshold();
        assertEquals(result, expResult);
    }

    /**
     * Test of getOffensiveDiffBonuses method, of class Criteria.
     */
    @Test
    public void testGetOffensiveDiffBonuses() {
        System.out.println("getOffensiveDiffBonuses");
        Criteria instance = null;
        int expResult = 0;
        int result = instance.getOffensiveDiffBonuses();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setOffensiveDiffBonuses method, of class Criteria.
     */
    @Test
    public void testSetOffensiveDiffBonuses() {
        System.out.println("setOffensiveDiffBonuses");
        int mOffensiveDiffBonuses = 0;
        Criteria instance = null;
        instance.setOffensiveDiffBonuses(mOffensiveDiffBonuses);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDefensiveDiffBonuses method, of class Criteria.
     */
    @Test
    public void testGetDefensiveDiffBonuses() {
        System.out.println("getDefensiveDiffBonuses");
        Criteria instance = new Criteria("td");
        int expResult = 0;
        int result = instance.getDefensiveDiffBonuses();
        assertEquals(result, expResult);
        instance.setDefensiveDiffBonuses(10);
        result = instance.getDefensiveDiffBonuses();
        assertEquals(result, 10);
    }

    /**
     * Test of setDefensiveDiffBonuses method, of class Criteria.
     */
    @Test
    public void testSetDefensiveDiffBonuses() {
        System.out.println("setDefensiveDiffBonuses");
        Criteria instance = new Criteria("td");
        int expResult = 0;
        int result = instance.getDefensiveDiffBonuses();
        assertEquals(result, expResult);
        instance.setDefensiveDiffBonuses(10);
        result = instance.getDefensiveDiffBonuses();
        assertEquals(result, 10);
    }

    /**
     * Test of getOffensiveThreshold method, of class Criteria.
     */
    @Test
    public void testGetOffensiveThreshold() {
        System.out.println("getOffensiveThreshold");
        Criteria instance = null;
        int expResult = 0;
        int result = instance.getOffensiveThreshold();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setOffensiveThreshold method, of class Criteria.
     */
    @Test
    public void testSetOffensiveThreshold() {
        System.out.println("setOffensiveThreshold");
        int mOffensiveThreshold = 0;
        Criteria instance = null;
        instance.setOffensiveThreshold(mOffensiveThreshold);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOffensiveBonuses method, of class Criteria.
     */
    @Test
    public void testGetOffensiveBonuses() {
        System.out.println("getOffensiveBonuses");
        Criteria instance = null;
        int expResult = 0;
        int result = instance.getOffensiveBonuses();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOffensiveBonusesByTeam method, of class Criteria.
     */
    @Test
    public void testGetOffensiveBonusesByTeam() {
        System.out.println("getOffensiveBonusesByTeam");
        Criteria instance = null;
        int expResult = 0;
        int result = instance.getOffensiveBonusesByTeam();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setOffensiveBonuses method, of class Criteria.
     */
    @Test
    public void testSetOffensiveBonuses() {
        System.out.println("setOffensiveBonuses");
        int mOffensiveBonuses = 0;
        Criteria instance = null;
        instance.setOffensiveBonuses(mOffensiveBonuses);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setOffensiveBonusesByTeam method, of class Criteria.
     */
    @Test
    public void testSetOffensiveBonusesByTeam() {
        System.out.println("setOffensiveBonusesByTeam");
        int mOffensiveBonuses = 0;
        Criteria instance = null;
        instance.setOffensiveBonusesByTeam(mOffensiveBonuses);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOffensiveDiffThresholdByTeam method, of class Criteria.
     */
    @Test
    public void testGetOffensiveDiffThresholdByTeam() {
        System.out.println("getOffensiveDiffThresholdByTeam");
        Criteria instance = null;
        int expResult = 0;
        int result = instance.getOffensiveDiffThresholdByTeam();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setOffensiveDiffThresholdByTeam method, of class Criteria.
     */
    @Test
    public void testSetOffensiveDiffThresholdByTeam() {
        System.out.println("setOffensiveDiffThresholdByTeam");
        int mOffensiveDiffThresholdByTeam = 0;
        Criteria instance = null;
        instance.setOffensiveDiffThresholdByTeam(mOffensiveDiffThresholdByTeam);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDefensiveDiffThresholdByTeam method, of class Criteria.
     */
    @Test
    public void testGetDefensiveDiffThresholdByTeam() {
        System.out.println("getDefensiveDiffThresholdByTeam");
        Criteria instance = new Criteria("td");
        int expResult = 0;
        int result = instance.getDefensiveDiffThresholdByTeam();
        assertEquals(result, expResult);
        expResult = 10;
        instance.setDefensiveDiffThresholdByTeam(expResult);
        result = instance.getDefensiveDiffThresholdByTeam();
        assertEquals(result, expResult);
    }

    /**
     * Test of setDefensiveDiffThresholdByTeam method, of class Criteria.
     */
    @Test
    public void testSetDefensiveDiffThresholdByTeam() {
        System.out.println("setDefensiveDiffThresholdByTeam");
        Criteria instance = new Criteria("td");
        int expResult = 0;
        int result = instance.getDefensiveDiffThresholdByTeam();
        assertEquals(result, expResult);
        expResult = 10;
        instance.setDefensiveDiffThresholdByTeam(expResult);
        result = instance.getDefensiveDiffThresholdByTeam();
        assertEquals(result, expResult);
    }

    /**
     * Test of getOffensiveDiffBonusesByTeam method, of class Criteria.
     */
    @Test
    public void testGetOffensiveDiffBonusesByTeam() {
        System.out.println("getOffensiveDiffBonusesByTeam");
        Criteria instance = null;
        int expResult = 0;
        int result = instance.getOffensiveDiffBonusesByTeam();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setOffensiveDiffBonusesByTeam method, of class Criteria.
     */
    @Test
    public void testSetOffensiveDiffBonusesByTeam() {
        System.out.println("setOffensiveDiffBonusesByTeam");
        int mOffensiveDiffBonusesByTeam = 0;
        Criteria instance = null;
        instance.setOffensiveDiffBonusesByTeam(mOffensiveDiffBonusesByTeam);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDefensiveDiffBonusesByTeam method, of class Criteria.
     */
    @Test
    public void testGetDefensiveDiffBonusesByTeam() {
        System.out.println("getDefensiveDiffBonusesByTeam");
        Criteria instance = new Criteria("td");
        int expResult = 0;
        int result = instance.getDefensiveDiffBonusesByTeam();
        assertEquals(result, expResult);
        expResult = 10;
        instance.setDefensiveDiffBonusesByTeam(expResult);
        result = instance.getDefensiveDiffBonusesByTeam();
        assertEquals(result, expResult);
    }

    /**
     * Test of setDefensiveDiffBonusesByTeam method, of class Criteria.
     */
    @Test
    public void testSetDefensiveDiffBonusesByTeam() {
        System.out.println("setDefensiveDiffBonusesByTeam");
        Criteria instance = new Criteria("td");
        int expResult = 0;
        int result = instance.getDefensiveDiffBonusesByTeam();
        assertEquals(result, expResult);
        expResult = 10;
        instance.setDefensiveDiffBonusesByTeam(expResult);
        result = instance.getDefensiveDiffBonusesByTeam();
        assertEquals(result, expResult);
    }

    /**
     * Test of getOffensiveThresholdByTeam method, of class Criteria.
     */
    @Test
    public void testGetOffensiveThresholdByTeam() {
        System.out.println("getOffensiveThresholdByTeam");
        Criteria instance = null;
        int expResult = 0;
        int result = instance.getOffensiveThresholdByTeam();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setOffensiveThresholdByTeam method, of class Criteria.
     */
    @Test
    public void testSetOffensiveThresholdByTeam() {
        System.out.println("setOffensiveThresholdByTeam");
        int mOffensiveThresholdByTeam = 0;
        Criteria instance = null;
        instance.setOffensiveThresholdByTeam(mOffensiveThresholdByTeam);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOffensiveDiffBonusesForTeam method, of class Criteria.
     */
    @Test
    public void testGetOffensiveDiffBonusesForTeam() {
        System.out.println("getOffensiveDiffBonusesForTeam");
        Criteria instance = null;
        int expResult = 0;
        int result = instance.getOffensiveDiffBonusesForTeam();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setOffensiveDiffBonusesForTeam method, of class Criteria.
     */
    @Test
    public void testSetOffensiveDiffBonusesForTeam() {
        System.out.println("setOffensiveDiffBonusesForTeam");
        int mOffensiveDiffBonusesForTeam = 0;
        Criteria instance = null;
        instance.setOffensiveDiffBonusesForTeam(mOffensiveDiffBonusesForTeam);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDefensiveDiffBonusesForTeam method, of class Criteria.
     */
    @Test
    public void testGetDefensiveDiffBonusesForTeam() {
        System.out.println("getDefensiveDiffBonusesForTeam");
        Criteria instance = new Criteria("td");
        int expResult = 0;
        int result = instance.getDefensiveDiffBonusesForTeam();
        assertEquals(result, expResult);
        expResult = 10;
        instance.setDefensiveDiffBonusesForTeam(expResult);
        result = instance.getDefensiveDiffBonusesForTeam();
        assertEquals(result, expResult);
    }

    /**
     * Test of setDefensiveDiffBonusesForTeam method, of class Criteria.
     */
    @Test
    public void testSetDefensiveDiffBonusesForTeam() {
        System.out.println("setDefensiveDiffBonusesForTeam");
        Criteria instance = new Criteria("td");
        int expResult = 0;
        int result = instance.getDefensiveDiffBonusesForTeam();
        assertEquals(result, expResult);
        expResult = 10;
        instance.setDefensiveDiffBonusesForTeam(expResult);
        result = instance.getDefensiveDiffBonusesForTeam();
        assertEquals(result, expResult);
    }

    /**
     * Test of getOffensiveBonusesForTeam method, of class Criteria.
     */
    @Test
    public void testGetOffensiveBonusesForTeam() {
        System.out.println("getOffensiveBonusesForTeam");
        Criteria instance = null;
        int expResult = 0;
        int result = instance.getOffensiveBonusesForTeam();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setOffensiveBonusesForTeam method, of class Criteria.
     */
    @Test
    public void testSetOffensiveBonusesForTeam() {
        System.out.println("setOffensiveBonusesForTeam");
        int mOffensiveBonusesForTeam = 0;
        Criteria instance = null;
        instance.setOffensiveBonusesForTeam(mOffensiveBonusesForTeam);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
