/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bb.teamma.data;

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
public class InducementTypeNGTest {

    public InducementTypeNGTest() {
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
     * Test of getName method, of class InducementType.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        InducementType instance = new InducementType();
        String expResult = "";
        String result = instance.getName();
        assertEquals(result, expResult);

        instance.setName("test");
        assertEquals(instance.getName(), "test");
    }

    /**
     * Test of setName method, of class InducementType.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        InducementType instance = new InducementType();
        String expResult = "";
        String result = instance.getName();
        assertEquals(result, expResult);

        instance.setName("test");
        assertEquals(instance.getName(), "test");
    }

    /**
     * Test of getCost method, of class InducementType.
     */
    @Test
    public void testGetCost() {
        System.out.println("getCost");
        InducementType instance = new InducementType();
        int expResult = 0;
        int result = instance.getCost();
        assertEquals(result, expResult);

        instance.setCost(100000);
        assertEquals(instance.getCost(), 100000);
    }

    /**
     * Test of setCost method, of class InducementType.
     */
    @Test
    public void testSetCost() {
        System.out.println("setCost");
        int _cost = 0;
        InducementType instance = new InducementType();
        instance.setCost(_cost);

        int expResult = 0;
        int result = instance.getCost();
        assertEquals(result, expResult);

        instance.setCost(100000);
        assertEquals(instance.getCost(), 100000);
    }

    /**
     * Test of getNbMax method, of class InducementType.
     */
    @Test
    public void testGetNbMax() {
        System.out.println("getNbMax");
        InducementType instance = new InducementType();
        int expResult = 0;
        int result = instance.getNbMax();
        assertEquals(result, expResult);

        instance.setNbMax(5);
        assertEquals(instance.getNbMax(), 5);
    }

    /**
     * Test of setNbMax method, of class InducementType.
     */
    @Test
    public void testSetNbMax() {
        System.out.println("setNbMax");
        int _nbMax = 0;
        InducementType instance = new InducementType();
        instance.setNbMax(_nbMax);
        assertEquals(instance.getNbMax(), _nbMax);
        instance.setNbMax(5);
        assertEquals(instance.getNbMax(), 5);
    }

}
