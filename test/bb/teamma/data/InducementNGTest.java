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
public class InducementNGTest {
    
    public InducementNGTest() {
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
     * Test of getType method, of class Inducement.
     */
    @Test
    public void testGetType() {
        System.out.println("getType");
        Inducement instance = new Inducement();
        InducementType expResult = null;
        InducementType result = instance.getType();
        assertEquals(result, expResult);
        
        InducementType it = new InducementType();
        it.setCost(150000);
        it.setName("Test");
        it.setNbMax(1);
        instance.setType(it);
        
        assertEquals(instance.getType().getCost(), 150000);
        assertEquals(instance.getType().getName(), "Test");
        assertEquals(instance.getType().getNbMax(), 1);
    }

    /**
     * Test of setType method, of class Inducement.
     */
    @Test
    public void testSetType() {
        System.out.println("setType");
        Inducement instance = new Inducement();
        InducementType expResult = null;
        InducementType result = instance.getType();
        assertEquals(result, expResult);
        
        InducementType it = new InducementType();
        it.setCost(150000);
        it.setName("Test");
        it.setNbMax(1);
        instance.setType(it);
        
        
        assertEquals(instance.getType().getCost(), 150000);
        assertEquals(instance.getType().getName(), "Test");
        assertEquals(instance.getType().getNbMax(), 1);
    }

    /**
     * Test of getNb method, of class Inducement.
     */
    @Test
    public void testGetNb() {
        System.out.println("getNb");
        Inducement instance = new Inducement();
        int expResult = 0;
        int result = instance.getNb();
        assertEquals(result, expResult);
        
        instance.setNb(15);
        assertEquals(instance.getNb(), 15);
    }

    /**
     * Test of setNb method, of class Inducement.
     */
    @Test
    public void testSetNb() {
        System.out.println("setNb");
        int _nb = 0;
        Inducement instance = new Inducement();
        instance.setNb(_nb);
        // TODO review the generated test code and remove the default call to fail.
        instance.setNb(150);
        assertEquals(instance.getNb(), 150);
    }
    
}
