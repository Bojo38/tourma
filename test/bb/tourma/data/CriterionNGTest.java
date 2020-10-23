/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bb.tourma.data;

import bb.tourma.data.Criterion;
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
public class CriterionNGTest {

    public CriterionNGTest() {
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
     * Test of getXMLElement method, of class Criterion.
     */
    @Test
    public void testGetXMLElement() {
        System.out.println("getXMLElement");
        Criterion instance = new Criterion("Test");
        instance.setPointsAgainst(1);
        instance.setPointsFor(2);
        instance.setPointsTeamAgainst(3);
        instance.setPointsTeamFor(4);

        Element result = instance.getXMLElement();

        Criterion crit = new Criterion("None");
        crit.setXMLElement(result);
        assertEquals(instance, crit);
    }

    /**
     * Test of setXMLElement method, of class Criterion.
     */
    @Test
    public void testSetXMLElement() {
        System.out.println("setXMLElement");
        Criterion instance = new Criterion("Test");
        instance.setPointsAgainst(1);
        instance.setPointsFor(2);
        instance.setPointsTeamAgainst(3);
        instance.setPointsTeamFor(4);

        Element result = instance.getXMLElement();

        Criterion crit = new Criterion("None");
        crit.setXMLElement(result);
        assertEquals(instance, crit);
    }

    /**
     * Test of getName method, of class Criterion.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Criterion instance = new Criterion("Test");
        instance.setName("Test2");
        assertEquals(instance.getName(), "Test2");
    }

    /**
     * Test of setName method, of class Criterion.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        Criterion instance = new Criterion("Test");
        instance.setName("Test2");
        assertEquals(instance.getName(), "Test2");
    }

    /**
     * Test of getPointsFor method, of class Criterion.
     */
    @Test
    public void testGetPointsFor() {
        System.out.println("getPointsFor");
        Criterion instance = new Criterion("Test");
        int value = 15;
        instance.setPointsFor(value);
        assertEquals(instance.getPointsFor(), value);
    }

    /**
     * Test of setPointsFor method, of class Criterion.
     */
    @Test
    public void testSetPointsFor() {
        System.out.println("setPointsFor");
        Criterion instance = new Criterion("Test");
        int value = 15;
        instance.setPointsFor(value);
        assertEquals(instance.getPointsFor(), value);
    }

    /**
     * Test of getPointsAgainst method, of class Criterion.
     */
    @Test
    public void testGetPointsAgainst() {
        System.out.println("getPointsAgainst");
        Criterion instance = new Criterion("Test");
        int value = 18;
        instance.setPointsAgainst(value);
        assertEquals(instance.getPointsAgainst(), value);
    }

    /**
     * Test of setPointsAgainst method, of class Criterion.
     */
    @Test
    public void testSetPointsAgainst() {
        System.out.println("setPointsAgainst");
        Criterion instance = new Criterion("Test");
        int value = 18;
        instance.setPointsAgainst(value);
        assertEquals(instance.getPointsAgainst(), value);
    }

    /**
     * Test of getPointsTeamFor method, of class Criterion.
     */
    @Test
    public void testGetPointsTeamFor() {
        System.out.println("getPointsTeamFor");
        Criterion instance = new Criterion("Test");
        int value = 21;
        instance.setPointsTeamFor(value);
        assertEquals(instance.getPointsTeamFor(), value);
    }

    /**
     * Test of setPointsTeamFor method, of class Criterion.
     */
    @Test
    public void testSetPointsTeamFor() {
        System.out.println("setPointsTeamFor");
        Criterion instance = new Criterion("Test");
        int value = 21;
        instance.setPointsTeamFor(value);
        assertEquals(instance.getPointsTeamFor(), value);
    }

    /**
     * Test of getPointsTeamAgainst method, of class Criterion.
     */
    @Test
    public void testGetPointsTeamAgainst() {
        System.out.println("getPointsTeamAgainst");
        Criterion instance = new Criterion("Test");
        int value = 21;
        instance.setPointsTeamAgainst(value);
        assertEquals(instance.getPointsTeamAgainst(), value);
    }

    /**
     * Test of setPointsTeamAgainst method, of class Criterion.
     */
    @Test
    public void testSetPointsTeamAgainst() {
        System.out.println("setPointsTeamAgainst");
        Criterion instance = new Criterion("Test");
        int value = 21;
        instance.setPointsTeamAgainst(value);
        assertEquals(instance.getPointsTeamAgainst(), value);
    }

    /**
     * Test of getUID method, of class Criterion.
     */
    @Test
    public void testGetUID() {
        System.out.println("getUID");
        Criterion instance = new Criterion("Test");
        int expResult = 24;
        int result = instance.getUID();
        assertEquals(result, expResult);

    }

    /**
     * Test of setUID method, of class Criterion.
     */
    @Test
    public void testSetUID() {
        System.out.println("setUID");
        Criterion instance = new Criterion("Test");
        int expResult = 51;
        int result = instance.getUID();
        assertEquals(result, expResult);

        instance.setUID(50);
        result = instance.getUID();
        assertEquals(result, 50);
    }

    /**
     * Test of pull method, of class Criterion.
     */
    @Test
    public void testPull() {
        System.out.println("pull");
        Criterion crit = new Criterion("Test");
        Criterion instance = new Criterion("instance");
        instance.pull(crit);
        assertEquals(crit, instance);
    }

    /**
     * Test of getAccronym method, of class Criterion.
     */
    @Test
    public void testGetAccronym() {
        System.out.println("getAccronym");
        Criterion instance = new Criterion("touchdown");
        String expResult = "touchdown";
        String result = instance.getAccronym();
        assertEquals(result, expResult);
        instance.setAccronym("td");
        result = instance.getAccronym();
        assertEquals(result, "td");

    }

    /**
     * Test of setAccronym method, of class Criterion.
     */
    @Test
    public void testSetAccronym() {
        System.out.println("setAccronym");
        String accronym = "AC";
        Criterion instance = new Criterion("Test");
        instance.setAccronym(accronym);

        String result = instance.getAccronym();
        assertEquals(accronym, result);
    }

    /**
     * Test of equals method, of class Criterion.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = null;
        Criterion instance = new Criterion("td");
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(result, expResult);
    }

    /**
     * Test of getCriticalThreshold method, of class Criterion.
     */
    @Test
    public void testGetCriticalThreshold() {
        System.out.println("getCriticalThreshold");
        Criterion instance = new Criterion("touchdowns");
        int expResult = 10;
        int result = instance.getCriticalThreshold();
        assertEquals(result, expResult);
        instance.setCriticalThreshold(5);
        result = instance.getCriticalThreshold();
        assertEquals(result, 5);

    }

    /**
     * Test of setCriticalThreshold method, of class Criterion.
     */
    @Test
    public void testSetCriticalThreshold() {
        System.out.println("setCriticalThreshold");
        Criterion instance = new Criterion("touchdowns");
        int expResult = 10;
        int result = instance.getCriticalThreshold();
        assertEquals(result, expResult);
        instance.setCriticalThreshold(5);
        result = instance.getCriticalThreshold();
        assertEquals(result, 5);
    }

    /**
     * Test of getOffensiveDiffThreshold method, of class Criterion.
     */
    @Test
    public void testGetOffensiveDiffThreshold() {
        System.out.println("getOffensiveDiffThreshold");
      Criterion instance = new Criterion("Test");

        instance.setOffensiveDiffThreshold(15);
        int result = instance.getOffensiveDiffThreshold();
        assertEquals(result, 15);
    }

    /**
     * Test of setOffensiveDiffThreshold method, of class Criterion.
     */
    @Test
    public void testSetOffensiveDiffThreshold() {
        System.out.println("setOffensiveDiffThreshold");
       Criterion instance = new Criterion("Test");

        instance.setOffensiveDiffThreshold(15);
        int result = instance.getOffensiveDiffThreshold();
        assertEquals(result, 15);
    }

    /**
     * Test of getDefensiveDiffThreshold method, of class Criterion.
     */
    @Test
    public void testGetDefensiveDiffThreshold() {
        System.out.println("getDefensiveDiffThreshold");
        Criterion instance = new Criterion("td");
        int expResult = 0;
        int result = instance.getDefensiveDiffThreshold();
        assertEquals(result, expResult);
        expResult = 10;
        instance.setDefensiveDiffThreshold(expResult);
        result = instance.getDefensiveDiffThreshold();
        assertEquals(result, expResult);
    }

    /**
     * Test of setDefensiveDiffThreshold method, of class Criterion.
     */
    @Test
    public void testSetDefensiveDiffThreshold() {
        System.out.println("setDefensiveDiffThreshold");
        Criterion instance = new Criterion("td");
        int expResult = 0;
        int result = instance.getDefensiveDiffThreshold();
        assertEquals(result, expResult);
        expResult = 10;
        instance.setDefensiveDiffThreshold(expResult);
        result = instance.getDefensiveDiffThreshold();
        assertEquals(result, expResult);
    }

    /**
     * Test of getOffensiveDiffBonuses method, of class Criterion.
     */
    @Test
    public void testGetOffensiveDiffBonuses() {
        System.out.println("getOffensiveDiffBonuses");
        Criterion instance = new Criterion("Test");

        instance.setOffensiveDiffBonuses(15);
        int result = instance.getOffensiveDiffBonuses();
        assertEquals(result, 15);
    }

    /**
     * Test of setOffensiveDiffBonuses method, of class Criterion.
     */
    @Test
    public void testSetOffensiveDiffBonuses() {
        System.out.println("setOffensiveDiffBonuses");
        Criterion instance = new Criterion("Test");

        instance.setOffensiveDiffBonuses(15);
        int result = instance.getOffensiveDiffBonuses();
        assertEquals(result, 15);
    }

    /**
     * Test of getDefensiveDiffBonuses method, of class Criterion.
     */
    @Test
    public void testGetDefensiveDiffBonuses() {
        System.out.println("getDefensiveDiffBonuses");
        Criterion instance = new Criterion("td");
        int expResult = 0;
        int result = instance.getDefensiveDiffBonuses();
        assertEquals(result, expResult);
        instance.setDefensiveDiffBonuses(10);
        result = instance.getDefensiveDiffBonuses();
        assertEquals(result, 10);
    }

    /**
     * Test of setDefensiveDiffBonuses method, of class Criterion.
     */
    @Test
    public void testSetDefensiveDiffBonuses() {
        System.out.println("setDefensiveDiffBonuses");
        Criterion instance = new Criterion("td");
        int expResult = 0;
        int result = instance.getDefensiveDiffBonuses();
        assertEquals(result, expResult);
        instance.setDefensiveDiffBonuses(10);
        result = instance.getDefensiveDiffBonuses();
        assertEquals(result, 10);
    }

    /**
     * Test of getOffensiveThreshold method, of class Criterion.
     */
    @Test
    public void testGetOffensiveThreshold() {
        System.out.println("getOffensiveThreshold");
        Criterion instance = new Criterion("Test");

        instance.setOffensiveThreshold(15);
        int result = instance.getOffensiveThreshold();
        assertEquals(result, 15);
    }

    /**
     * Test of setOffensiveThreshold method, of class Criterion.
     */
    @Test
    public void testSetOffensiveThreshold() {
        System.out.println("setOffensiveThreshold");
        Criterion instance = new Criterion("Test");

        instance.setOffensiveThreshold(15);
        int result = instance.getOffensiveThreshold();
        assertEquals(result, 15);
    }

    /**
     * Test of getOffensiveBonuses method, of class Criterion.
     */
    @Test
    public void testGetOffensiveBonuses() {
        System.out.println("getOffensiveBonuses");
        Criterion instance = new Criterion("Test");

        instance.setOffensiveBonuses(15);
        int result = instance.getOffensiveBonuses();
        assertEquals(result, 15);
    }

    /**
     * Test of getOffensiveBonusesByTeam method, of class Criterion.
     */
    @Test
    public void testGetOffensiveBonusesByTeam() {
        System.out.println("getOffensiveBonusesByTeam");
        Criterion instance = new Criterion("Test");

        instance.setOffensiveBonusesByTeam(15);
        int result = instance.getOffensiveBonusesByTeam();
        assertEquals(result, 15);
    }

    /**
     * Test of setOffensiveBonuses method, of class Criterion.
     */
    @Test
    public void testSetOffensiveBonuses() {
        System.out.println("setOffensiveBonuses");
        Criterion instance = new Criterion("Test");

        instance.setOffensiveBonuses(15);
        int result = instance.getOffensiveBonuses();
        assertEquals(result, 15);
    }

    /**
     * Test of setOffensiveBonusesByTeam method, of class Criterion.
     */
    @Test
    public void testSetOffensiveBonusesByTeam() {
        System.out.println("setOffensiveBonusesByTeam");
        Criterion instance = new Criterion("Test");

        instance.setOffensiveBonusesByTeam(15);
        int result = instance.getOffensiveBonusesByTeam();
        assertEquals(result, 15);
    }

    /**
     * Test of getOffensiveDiffThresholdByTeam method, of class Criterion.
     */
    @Test
    public void testGetOffensiveDiffThresholdByTeam() {
        System.out.println("getOffensiveDiffThresholdByTeam");
       Criterion instance = new Criterion("Test");

        instance.setOffensiveDiffThresholdByTeam(15);
        int result = instance.getOffensiveDiffThresholdByTeam();
        assertEquals(result, 15);
    }

    /**
     * Test of setOffensiveDiffThresholdByTeam method, of class Criterion.
     */
    @Test
    public void testSetOffensiveDiffThresholdByTeam() {
        System.out.println("setOffensiveDiffThresholdByTeam");
         Criterion instance = new Criterion("Test");

        instance.setOffensiveDiffThresholdByTeam(15);
        int result = instance.getOffensiveDiffThresholdByTeam();
        assertEquals(result, 15);
    }

    /**
     * Test of getDefensiveDiffThresholdByTeam method, of class Criterion.
     */
    @Test
    public void testGetDefensiveDiffThresholdByTeam() {
        System.out.println("getDefensiveDiffThresholdByTeam");
        Criterion instance = new Criterion("td");
        int expResult = 0;
        int result = instance.getDefensiveDiffThresholdByTeam();
        assertEquals(result, expResult);
        expResult = 10;
        instance.setDefensiveDiffThresholdByTeam(expResult);
        result = instance.getDefensiveDiffThresholdByTeam();
        assertEquals(result, expResult);
    }

    /**
     * Test of setDefensiveDiffThresholdByTeam method, of class Criterion.
     */
    @Test
    public void testSetDefensiveDiffThresholdByTeam() {
        System.out.println("setDefensiveDiffThresholdByTeam");
        Criterion instance = new Criterion("td");
        int expResult = 0;
        int result = instance.getDefensiveDiffThresholdByTeam();
        assertEquals(result, expResult);
        expResult = 10;
        instance.setDefensiveDiffThresholdByTeam(expResult);
        result = instance.getDefensiveDiffThresholdByTeam();
        assertEquals(result, expResult);
    }

    /**
     * Test of getOffensiveDiffBonusesByTeam method, of class Criterion.
     */
    @Test
    public void testGetOffensiveDiffBonusesByTeam() {
        System.out.println("getOffensiveDiffBonusesByTeam");
       Criterion instance = new Criterion("Test");

        instance.setOffensiveDiffBonusesByTeam(15);
        int result = instance.getOffensiveDiffBonusesByTeam();
        assertEquals(result, 15);
    }

    /**
     * Test of setOffensiveDiffBonusesByTeam method, of class Criterion.
     */
    @Test
    public void testSetOffensiveDiffBonusesByTeam() {
        System.out.println("setOffensiveDiffBonusesByTeam");
       Criterion instance = new Criterion("Test");

        instance.setOffensiveDiffBonusesByTeam(15);
        int result = instance.getOffensiveDiffBonusesByTeam();
        assertEquals(result, 15);
    }

    /**
     * Test of getDefensiveDiffBonusesByTeam method, of class Criterion.
     */
    @Test
    public void testGetDefensiveDiffBonusesByTeam() {
        System.out.println("getDefensiveDiffBonusesByTeam");
        Criterion instance = new Criterion("td");
        int expResult = 0;
        int result = instance.getDefensiveDiffBonusesByTeam();
        assertEquals(result, expResult);
        expResult = 10;
        instance.setDefensiveDiffBonusesByTeam(expResult);
        result = instance.getDefensiveDiffBonusesByTeam();
        assertEquals(result, expResult);
    }

    /**
     * Test of setDefensiveDiffBonusesByTeam method, of class Criterion.
     */
    @Test
    public void testSetDefensiveDiffBonusesByTeam() {
        System.out.println("setDefensiveDiffBonusesByTeam");
        Criterion instance = new Criterion("td");
        int expResult = 0;
        int result = instance.getDefensiveDiffBonusesByTeam();
        assertEquals(result, expResult);
        expResult = 10;
        instance.setDefensiveDiffBonusesByTeam(expResult);
        result = instance.getDefensiveDiffBonusesByTeam();
        assertEquals(result, expResult);
    }

    /**
     * Test of getOffensiveThresholdByTeam method, of class Criterion.
     */
    @Test
    public void testGetOffensiveThresholdByTeam() {
        System.out.println("getOffensiveThresholdByTeam");
        Criterion instance = new Criterion("Test");

        instance.setOffensiveThresholdByTeam(15);
        int result = instance.getOffensiveThresholdByTeam();
        assertEquals(result, 15);
    }

    /**
     * Test of setOffensiveThresholdByTeam method, of class Criterion.
     */
    @Test
    public void testSetOffensiveThresholdByTeam() {
        System.out.println("setOffensiveThresholdByTeam");
       Criterion instance = new Criterion("Test");

        instance.setOffensiveThresholdByTeam(15);
        int result = instance.getOffensiveThresholdByTeam();
        assertEquals(result, 15);
    }

    /**
     * Test of getOffensiveDiffBonusesForTeam method, of class Criterion.
     */
    @Test
    public void testGetOffensiveDiffBonusesForTeam() {
        System.out.println("getOffensiveDiffBonusesForTeam");
       Criterion instance = new Criterion("Test");

        instance.setOffensiveDiffBonusesForTeam(15);
        int result = instance.getOffensiveDiffBonusesForTeam();
        assertEquals(result, 15);
    }

    /**
     * Test of setOffensiveDiffBonusesForTeam method, of class Criterion.
     */
    @Test
    public void testSetOffensiveDiffBonusesForTeam() {
        System.out.println("setOffensiveDiffBonusesForTeam");
      Criterion instance = new Criterion("Test");

        instance.setOffensiveDiffBonusesForTeam(15);
        int result = instance.getOffensiveDiffBonusesForTeam();
        assertEquals(result, 15);
    }

    /**
     * Test of getDefensiveDiffBonusesForTeam method, of class Criterion.
     */
    @Test
    public void testGetDefensiveDiffBonusesForTeam() {
        System.out.println("getDefensiveDiffBonusesForTeam");
        Criterion instance = new Criterion("td");
        int expResult = 0;
        int result = instance.getDefensiveDiffBonusesForTeam();
        assertEquals(result, expResult);
        expResult = 10;
        instance.setDefensiveDiffBonusesForTeam(expResult);
        result = instance.getDefensiveDiffBonusesForTeam();
        assertEquals(result, expResult);
    }

    /**
     * Test of setDefensiveDiffBonusesForTeam method, of class Criterion.
     */
    @Test
    public void testSetDefensiveDiffBonusesForTeam() {
        System.out.println("setDefensiveDiffBonusesForTeam");
        Criterion instance = new Criterion("td");
        int expResult = 0;
        int result = instance.getDefensiveDiffBonusesForTeam();
        assertEquals(result, expResult);
        expResult = 10;
        instance.setDefensiveDiffBonusesForTeam(expResult);
        result = instance.getDefensiveDiffBonusesForTeam();
        assertEquals(result, expResult);
    }

    /**
     * Test of getOffensiveBonusesForTeam method, of class Criterion.
     */
    @Test
    public void testGetOffensiveBonusesForTeam() {
        System.out.println("getOffensiveBonusesForTeam");
        Criterion instance = new Criterion("Test");

        instance.setOffensiveBonusesForTeam(15);
        int result = instance.getOffensiveBonusesForTeam();
        assertEquals(result, 15);
    }

    /**
     * Test of setOffensiveBonusesForTeam method, of class Criterion.
     */
    @Test
    public void testSetOffensiveBonusesForTeam() {
        System.out.println("setOffensiveBonusesForTeam");
        Criterion instance = new Criterion("Test");

        instance.setOffensiveBonusesForTeam(15);
        int result = instance.getOffensiveBonusesForTeam();
        assertEquals(result, 15);
    }

}
