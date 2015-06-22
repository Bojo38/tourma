/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import org.jdom2.Element;
import static org.testng.Assert.assertEquals;
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
        Criteria instance=new Criteria("Test");
        instance.setName("Test2");
        assertEquals(instance.getName(),"Test2");
    }

    /**
     * Test of setName method, of class Criteria.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        Criteria instance=new Criteria("Test");
        instance.setName("Test2");
        assertEquals(instance.getName(),"Test2");
    }

    /**
     * Test of getPointsFor method, of class Criteria.
     */
    @Test
    public void testGetPointsFor() {
        System.out.println("getPointsFor");
        Criteria instance=new Criteria("Test");
        int value=15;
        instance.setPointsFor(value);
        assertEquals(instance.getPointsFor(),value);
    }

    /**
     * Test of setPointsFor method, of class Criteria.
     */
    @Test
    public void testSetPointsFor() {
        System.out.println("setPointsFor");
       Criteria instance=new Criteria("Test");
        int value=15;
        instance.setPointsFor(value);
        assertEquals(instance.getPointsFor(),value);
    }

    /**
     * Test of getPointsAgainst method, of class Criteria.
     */
    @Test
    public void testGetPointsAgainst() {
        System.out.println("getPointsAgainst");
       Criteria instance=new Criteria("Test");
        int value=18;
        instance.setPointsAgainst(value);
        assertEquals(instance.getPointsAgainst(),value);
    }

    /**
     * Test of setPointsAgainst method, of class Criteria.
     */
    @Test
    public void testSetPointsAgainst() {
        System.out.println("setPointsAgainst");
         Criteria instance=new Criteria("Test");
        int value=18;
        instance.setPointsAgainst(value);
        assertEquals(instance.getPointsAgainst(),value);
    }

    /**
     * Test of getPointsTeamFor method, of class Criteria.
     */
    @Test
    public void testGetPointsTeamFor() {
        System.out.println("getPointsTeamFor");
         Criteria instance=new Criteria("Test");
        int value=21;
        instance.setPointsTeamFor(value);
        assertEquals(instance.getPointsTeamFor(),value);
    }

    /**
     * Test of setPointsTeamFor method, of class Criteria.
     */
    @Test
    public void testSetPointsTeamFor() {
        System.out.println("setPointsTeamFor");
          Criteria instance=new Criteria("Test");
        int value=21;
        instance.setPointsTeamFor(value);
        assertEquals(instance.getPointsTeamFor(),value);
    }

    /**
     * Test of getPointsTeamAgainst method, of class Criteria.
     */
    @Test
    public void testGetPointsTeamAgainst() {
        System.out.println("getPointsTeamAgainst");
           Criteria instance=new Criteria("Test");
        int value=21;
        instance.setPointsTeamAgainst(value);
        assertEquals(instance.getPointsTeamAgainst(),value);
    }

    /**
     * Test of setPointsTeamAgainst method, of class Criteria.
     */
    @Test
    public void testSetPointsTeamAgainst() {
        System.out.println("setPointsTeamAgainst");
          Criteria instance=new Criteria("Test");
        int value=21;
        instance.setPointsTeamAgainst(value);
        assertEquals(instance.getPointsTeamAgainst(),value);
    }
    
}
