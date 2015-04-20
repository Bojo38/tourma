/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import java.util.ArrayList;
import org.jdom2.Element;
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
public class PoolNGTest {
    
    public PoolNGTest() {
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
     * Test of getXMLElement method, of class Pool.
     */
    @Test
    public void testGetXMLElement() {
        System.out.println("getXMLElement");
        Pool instance = new Pool();
        Element expResult = null;
        Element result = instance.getXMLElement();
        assertEquals(result, expResult);
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

    /**
     * Test of getName method, of class Pool.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Pool instance = new Pool();
        String expResult = "";
        String result = instance.getName();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setName method, of class Pool.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String mName = "";
        Pool instance = new Pool();
        instance.setName(mName);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCompetitor method, of class Pool.
     */
    @Test
    public void testGetCompetitor() {
        System.out.println("getCompetitor");
        int i = 0;
        Pool instance = new Pool();
        Competitor expResult = null;
        Competitor result = instance.getCompetitor(i);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addCompetitor method, of class Pool.
     */
    @Test
    public void testAddCompetitor() {
        System.out.println("addCompetitor");
        Competitor c = null;
        Pool instance = new Pool();
        instance.addCompetitor(c);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCompetitorCount method, of class Pool.
     */
    @Test
    public void testGetCompetitorCount() {
        System.out.println("getCompetitorCount");
        Pool instance = new Pool();
        int expResult = 0;
        int result = instance.getCompetitorCount();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCompetitors method, of class Pool.
     */
    @Test
    public void testGetCompetitors() {
        System.out.println("getCompetitors");
        Pool instance = new Pool();
        ArrayList expResult = null;
        ArrayList result = instance.getCompetitors();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
