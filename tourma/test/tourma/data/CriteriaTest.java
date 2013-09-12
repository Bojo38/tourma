/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import org.jdom2.Element;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author WFMJ7631
 */
public class CriteriaTest {
    
    public CriteriaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getXMLElement method, of class Criteria.
     */
    @Test
    public void testGetXMLElement() {
        System.out.println("getXMLElement");
        Criteria instance = null;
        Element expResult = null;
        Element result = instance.getXMLElement();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setXMLElement method, of class Criteria.
     */
    @Test
    public void testSetXMLElement() {
        System.out.println("setXMLElement");
        Element criteria = null;
        Criteria instance = null;
        instance.setXMLElement(criteria);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
