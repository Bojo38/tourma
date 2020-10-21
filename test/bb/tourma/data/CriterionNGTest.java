/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bb.tourma.data;

import org.jdom.Element;
import static org.testng.Assert.*;
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
     * Test of getUID method, of class Criterion.
     */
    @Test
    public void testGetUID() {
        System.out.println("getUID");
        Criterion instance = null;
        int expResult = 0;
        int result = instance.getUID();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setUID method, of class Criterion.
     */
    @Test
    public void testSetUID() {
        System.out.println("setUID");
        int UID = 0;
        Criterion instance = null;
        instance.setUID(UID);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of pull method, of class Criterion.
     */
    @Test
    public void testPull() {
        System.out.println("pull");
        Criterion crit = null;
        Criterion instance = null;
        instance.pull(crit);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAccronym method, of class Criterion.
     */
    @Test
    public void testGetAccronym() {
        System.out.println("getAccronym");
        Criterion instance = null;
        String expResult = "";
        String result = instance.getAccronym();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setAccronym method, of class Criterion.
     */
    @Test
    public void testSetAccronym() {
        System.out.println("setAccronym");
        String accronym = "";
        Criterion instance = null;
        instance.setAccronym(accronym);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class Criterion.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = null;
        Criterion instance = null;
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCriticalThreshold method, of class Criterion.
     */
    @Test
    public void testGetCriticalThreshold() {
        System.out.println("getCriticalThreshold");
        Criterion instance = null;
        int expResult = 0;
        int result = instance.getCriticalThreshold();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCriticalThreshold method, of class Criterion.
     */
    @Test
    public void testSetCriticalThreshold() {
        System.out.println("setCriticalThreshold");
        int value = 0;
        Criterion instance = null;
        instance.setCriticalThreshold(value);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getXMLElement method, of class Criterion.
     */
    @Test
    public void testGetXMLElement() {
        System.out.println("getXMLElement");
        Criterion instance = null;
        Element expResult = null;
        Element result = instance.getXMLElement();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setXMLElement method, of class Criterion.
     */
    @Test
    public void testSetXMLElement() {
        System.out.println("setXMLElement");
        Element criteria = null;
        Criterion instance = null;
        instance.setXMLElement(criteria);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getName method, of class Criterion.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Criterion instance = null;
        String expResult = "";
        String result = instance.getName();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setName method, of class Criterion.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String mName = "";
        Criterion instance = null;
        instance.setName(mName);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPointsFor method, of class Criterion.
     */
    @Test
    public void testGetPointsFor() {
        System.out.println("getPointsFor");
        Criterion instance = null;
        int expResult = 0;
        int result = instance.getPointsFor();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPointsFor method, of class Criterion.
     */
    @Test
    public void testSetPointsFor() {
        System.out.println("setPointsFor");
        int mPointsFor = 0;
        Criterion instance = null;
        instance.setPointsFor(mPointsFor);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPointsAgainst method, of class Criterion.
     */
    @Test
    public void testGetPointsAgainst() {
        System.out.println("getPointsAgainst");
        Criterion instance = null;
        int expResult = 0;
        int result = instance.getPointsAgainst();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPointsAgainst method, of class Criterion.
     */
    @Test
    public void testSetPointsAgainst() {
        System.out.println("setPointsAgainst");
        int mPointsAgainst = 0;
        Criterion instance = null;
        instance.setPointsAgainst(mPointsAgainst);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPointsTeamFor method, of class Criterion.
     */
    @Test
    public void testGetPointsTeamFor() {
        System.out.println("getPointsTeamFor");
        Criterion instance = null;
        int expResult = 0;
        int result = instance.getPointsTeamFor();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPointsTeamFor method, of class Criterion.
     */
    @Test
    public void testSetPointsTeamFor() {
        System.out.println("setPointsTeamFor");
        int mPointsTeamFor = 0;
        Criterion instance = null;
        instance.setPointsTeamFor(mPointsTeamFor);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPointsTeamAgainst method, of class Criterion.
     */
    @Test
    public void testGetPointsTeamAgainst() {
        System.out.println("getPointsTeamAgainst");
        Criterion instance = null;
        int expResult = 0;
        int result = instance.getPointsTeamAgainst();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPointsTeamAgainst method, of class Criterion.
     */
    @Test
    public void testSetPointsTeamAgainst() {
        System.out.println("setPointsTeamAgainst");
        int mPointsTeamAgainst = 0;
        Criterion instance = null;
        instance.setPointsTeamAgainst(mPointsTeamAgainst);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOffensiveDiffThreshold method, of class Criterion.
     */
    @Test
    public void testGetOffensiveDiffThreshold() {
        System.out.println("getOffensiveDiffThreshold");
        Criterion instance = null;
        int expResult = 0;
        int result = instance.getOffensiveDiffThreshold();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setOffensiveDiffThreshold method, of class Criterion.
     */
    @Test
    public void testSetOffensiveDiffThreshold() {
        System.out.println("setOffensiveDiffThreshold");
        int mOffensiveDiffThreshold = 0;
        Criterion instance = null;
        instance.setOffensiveDiffThreshold(mOffensiveDiffThreshold);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDefensiveDiffThreshold method, of class Criterion.
     */
    @Test
    public void testGetDefensiveDiffThreshold() {
        System.out.println("getDefensiveDiffThreshold");
        Criterion instance = null;
        int expResult = 0;
        int result = instance.getDefensiveDiffThreshold();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDefensiveDiffThreshold method, of class Criterion.
     */
    @Test
    public void testSetDefensiveDiffThreshold() {
        System.out.println("setDefensiveDiffThreshold");
        int mDefensiveDiffThreshold = 0;
        Criterion instance = null;
        instance.setDefensiveDiffThreshold(mDefensiveDiffThreshold);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOffensiveDiffBonuses method, of class Criterion.
     */
    @Test
    public void testGetOffensiveDiffBonuses() {
        System.out.println("getOffensiveDiffBonuses");
        Criterion instance = null;
        int expResult = 0;
        int result = instance.getOffensiveDiffBonuses();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setOffensiveDiffBonuses method, of class Criterion.
     */
    @Test
    public void testSetOffensiveDiffBonuses() {
        System.out.println("setOffensiveDiffBonuses");
        int mOffensiveDiffBonuses = 0;
        Criterion instance = null;
        instance.setOffensiveDiffBonuses(mOffensiveDiffBonuses);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDefensiveDiffBonuses method, of class Criterion.
     */
    @Test
    public void testGetDefensiveDiffBonuses() {
        System.out.println("getDefensiveDiffBonuses");
        Criterion instance = null;
        int expResult = 0;
        int result = instance.getDefensiveDiffBonuses();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDefensiveDiffBonuses method, of class Criterion.
     */
    @Test
    public void testSetDefensiveDiffBonuses() {
        System.out.println("setDefensiveDiffBonuses");
        int mDefensiveDiffBonuses = 0;
        Criterion instance = null;
        instance.setDefensiveDiffBonuses(mDefensiveDiffBonuses);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOffensiveThreshold method, of class Criterion.
     */
    @Test
    public void testGetOffensiveThreshold() {
        System.out.println("getOffensiveThreshold");
        Criterion instance = null;
        int expResult = 0;
        int result = instance.getOffensiveThreshold();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setOffensiveThreshold method, of class Criterion.
     */
    @Test
    public void testSetOffensiveThreshold() {
        System.out.println("setOffensiveThreshold");
        int mOffensiveThreshold = 0;
        Criterion instance = null;
        instance.setOffensiveThreshold(mOffensiveThreshold);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOffensiveBonuses method, of class Criterion.
     */
    @Test
    public void testGetOffensiveBonuses() {
        System.out.println("getOffensiveBonuses");
        Criterion instance = null;
        int expResult = 0;
        int result = instance.getOffensiveBonuses();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOffensiveBonusesByTeam method, of class Criterion.
     */
    @Test
    public void testGetOffensiveBonusesByTeam() {
        System.out.println("getOffensiveBonusesByTeam");
        Criterion instance = null;
        int expResult = 0;
        int result = instance.getOffensiveBonusesByTeam();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setOffensiveBonuses method, of class Criterion.
     */
    @Test
    public void testSetOffensiveBonuses() {
        System.out.println("setOffensiveBonuses");
        int mOffensiveBonuses = 0;
        Criterion instance = null;
        instance.setOffensiveBonuses(mOffensiveBonuses);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setOffensiveBonusesByTeam method, of class Criterion.
     */
    @Test
    public void testSetOffensiveBonusesByTeam() {
        System.out.println("setOffensiveBonusesByTeam");
        int mOffensiveBonuses = 0;
        Criterion instance = null;
        instance.setOffensiveBonusesByTeam(mOffensiveBonuses);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOffensiveDiffThresholdByTeam method, of class Criterion.
     */
    @Test
    public void testGetOffensiveDiffThresholdByTeam() {
        System.out.println("getOffensiveDiffThresholdByTeam");
        Criterion instance = null;
        int expResult = 0;
        int result = instance.getOffensiveDiffThresholdByTeam();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setOffensiveDiffThresholdByTeam method, of class Criterion.
     */
    @Test
    public void testSetOffensiveDiffThresholdByTeam() {
        System.out.println("setOffensiveDiffThresholdByTeam");
        int mOffensiveDiffThresholdByTeam = 0;
        Criterion instance = null;
        instance.setOffensiveDiffThresholdByTeam(mOffensiveDiffThresholdByTeam);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDefensiveDiffThresholdByTeam method, of class Criterion.
     */
    @Test
    public void testGetDefensiveDiffThresholdByTeam() {
        System.out.println("getDefensiveDiffThresholdByTeam");
        Criterion instance = null;
        int expResult = 0;
        int result = instance.getDefensiveDiffThresholdByTeam();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDefensiveDiffThresholdByTeam method, of class Criterion.
     */
    @Test
    public void testSetDefensiveDiffThresholdByTeam() {
        System.out.println("setDefensiveDiffThresholdByTeam");
        int mDefensiveDiffThresholdByTeam = 0;
        Criterion instance = null;
        instance.setDefensiveDiffThresholdByTeam(mDefensiveDiffThresholdByTeam);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOffensiveDiffBonusesByTeam method, of class Criterion.
     */
    @Test
    public void testGetOffensiveDiffBonusesByTeam() {
        System.out.println("getOffensiveDiffBonusesByTeam");
        Criterion instance = null;
        int expResult = 0;
        int result = instance.getOffensiveDiffBonusesByTeam();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setOffensiveDiffBonusesByTeam method, of class Criterion.
     */
    @Test
    public void testSetOffensiveDiffBonusesByTeam() {
        System.out.println("setOffensiveDiffBonusesByTeam");
        int mOffensiveDiffBonusesByTeam = 0;
        Criterion instance = null;
        instance.setOffensiveDiffBonusesByTeam(mOffensiveDiffBonusesByTeam);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDefensiveDiffBonusesByTeam method, of class Criterion.
     */
    @Test
    public void testGetDefensiveDiffBonusesByTeam() {
        System.out.println("getDefensiveDiffBonusesByTeam");
        Criterion instance = null;
        int expResult = 0;
        int result = instance.getDefensiveDiffBonusesByTeam();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDefensiveDiffBonusesByTeam method, of class Criterion.
     */
    @Test
    public void testSetDefensiveDiffBonusesByTeam() {
        System.out.println("setDefensiveDiffBonusesByTeam");
        int mDefensiveDiffBonusesByTeam = 0;
        Criterion instance = null;
        instance.setDefensiveDiffBonusesByTeam(mDefensiveDiffBonusesByTeam);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOffensiveThresholdByTeam method, of class Criterion.
     */
    @Test
    public void testGetOffensiveThresholdByTeam() {
        System.out.println("getOffensiveThresholdByTeam");
        Criterion instance = null;
        int expResult = 0;
        int result = instance.getOffensiveThresholdByTeam();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setOffensiveThresholdByTeam method, of class Criterion.
     */
    @Test
    public void testSetOffensiveThresholdByTeam() {
        System.out.println("setOffensiveThresholdByTeam");
        int mOffensiveThresholdByTeam = 0;
        Criterion instance = null;
        instance.setOffensiveThresholdByTeam(mOffensiveThresholdByTeam);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOffensiveDiffBonusesForTeam method, of class Criterion.
     */
    @Test
    public void testGetOffensiveDiffBonusesForTeam() {
        System.out.println("getOffensiveDiffBonusesForTeam");
        Criterion instance = null;
        int expResult = 0;
        int result = instance.getOffensiveDiffBonusesForTeam();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setOffensiveDiffBonusesForTeam method, of class Criterion.
     */
    @Test
    public void testSetOffensiveDiffBonusesForTeam() {
        System.out.println("setOffensiveDiffBonusesForTeam");
        int mOffensiveDiffBonusesForTeam = 0;
        Criterion instance = null;
        instance.setOffensiveDiffBonusesForTeam(mOffensiveDiffBonusesForTeam);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDefensiveDiffBonusesForTeam method, of class Criterion.
     */
    @Test
    public void testGetDefensiveDiffBonusesForTeam() {
        System.out.println("getDefensiveDiffBonusesForTeam");
        Criterion instance = null;
        int expResult = 0;
        int result = instance.getDefensiveDiffBonusesForTeam();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDefensiveDiffBonusesForTeam method, of class Criterion.
     */
    @Test
    public void testSetDefensiveDiffBonusesForTeam() {
        System.out.println("setDefensiveDiffBonusesForTeam");
        int mDefensiveDiffBonusesForTeam = 0;
        Criterion instance = null;
        instance.setDefensiveDiffBonusesForTeam(mDefensiveDiffBonusesForTeam);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOffensiveBonusesForTeam method, of class Criterion.
     */
    @Test
    public void testGetOffensiveBonusesForTeam() {
        System.out.println("getOffensiveBonusesForTeam");
        Criterion instance = null;
        int expResult = 0;
        int result = instance.getOffensiveBonusesForTeam();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setOffensiveBonusesForTeam method, of class Criterion.
     */
    @Test
    public void testSetOffensiveBonusesForTeam() {
        System.out.println("setOffensiveBonusesForTeam");
        int mOffensiveBonusesForTeam = 0;
        Criterion instance = null;
        instance.setOffensiveBonusesForTeam(mOffensiveBonusesForTeam);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
