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
public class PoolTest {
    private static final Logger LOG = Logger.getLogger(PoolTest.class.getName());
    
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
    public PoolTest() {
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
     * Test of getXMLElement method, of class Pool.
     */
    @Test
    public void testGetXMLElement() {
        System.out.println("getXMLElement");
        Pool instance = new Pool();
        Element expResult = null;
        Element result = instance.getXMLElement();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setXMLElement method, of class Pool.
     */
    @Test
    public void testSetXMLElement() {
        System.out.println("setXMLElement");
        Element pool = null;
        Pool instance = new Pool();
        instance.setXMLElement(pool);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
