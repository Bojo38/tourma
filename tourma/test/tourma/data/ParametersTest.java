/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import java.util.logging.Logger;
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
public class ParametersTest {
    private static final Logger LOG = Logger.getLogger(ParametersTest.class.getName());
    
    /**
     *
     */
    @BeforeClass
    public static void setUpClass() {
    }
    
    /**
     *
     */
    @AfterClass
    public static void tearDownClass() {
    }
    
    /**
     *
     */
    public ParametersTest() {
    }
    
    /**
     *
     */
    @Before
    public void setUp() {
    }
    
    /**
     *
     */
    @After
    public void tearDown() {
    }

    /**
     * Test of getXMLElement method, of class Parameters.
     */
    @Test
    public void testGetXMLElement() {
        System.out.println("getXMLElement");
        Parameters instance = new Parameters();
        Element expResult = null;
        Element result = instance.getXMLElement();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setXMLElement method, of class Parameters.
     */
    @Test
    public void testSetXMLElement() {
        System.out.println("setXMLElement");
        Element params = null;
        Parameters instance = new Parameters();
        instance.setXMLElement(params);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Parameters.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Parameters instance = new Parameters();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
