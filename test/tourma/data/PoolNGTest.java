/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import java.io.File;
import org.jdom.Element;
import org.testng.Assert;
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
        Tournament.getTournament().loadXML(new File("./test/pools.xml"));
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
        if (Tournament.getTournament().getPoolCount() == 0) {
            fail("No pool in file");
        }

        Pool instance = Tournament.getTournament().getPool(0);
        Element e = instance.getXMLElement();

        Pool bis = new Pool();
        bis.setXMLElement(e);
        assertEquals(bis, instance);
    }

    /**
     * Test of setXMLElement method, of class Pool.
     */
    @Test
    public void testSetXMLElement() {
        System.out.println("setXMLElement");
        if (Tournament.getTournament().getPoolCount() == 0) {
            fail("No pool in file");
        }

        Pool instance = Tournament.getTournament().getPool(0);
        Element e = instance.getXMLElement();

        Pool bis = new Pool();
        bis.setXMLElement(e);
        assertEquals(bis, instance);
    }

    /**
     * Test of getName method, of class Pool.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Pool instance = new Pool();
        String expResult = "Test";
        instance.setName(expResult);
        String result = instance.getName();
        assertEquals(result, expResult);
    }

    /**
     * Test of setName method, of class Pool.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        Pool instance = new Pool();
        String expResult = "Test";
        instance.setName(expResult);
        String result = instance.getName();
        assertEquals(result, expResult);
    }

    /**
     * Test of getCompetitor method, of class Pool.
     */
    @Test
    public void testGetCompetitor() {
        System.out.println("getCompetitor");
        if (Tournament.getTournament().getPoolCount() == 0) {
            fail("No pool in file");
        }

        Pool instance = Tournament.getTournament().getPool(0);
        for (int i = 0; i < instance.getCompetitorCount(); i++) {
            Competitor expResult = instance.getCompetitor(i);
            Assert.assertNotNull(expResult);
        }
    }

    /**
     * Test of addCompetitor method, of class Pool.
     */
    @Test
    public void testAddCompetitor() {
        System.out.println("addCompetitor");
        if (Tournament.getTournament().getPoolCount() == 0) {
            fail("No pool in file");
        }

        Pool instance = Tournament.getTournament().getPool(0);
        for (int i = 0; i < instance.getCompetitorCount(); i++) {
            Competitor expResult = instance.getCompetitor(i);
            Assert.assertNotNull(expResult);
        }
        int nb = instance.getCompetitorCount();
        Competitor c = new Coach("Toto");
        instance.addCompetitor(c);
        assertEquals(nb + 1, instance.getCompetitorCount());
    }

    /**
     * Test of getCompetitorCount method, of class Pool.
     */
    @Test
    public void testGetCompetitorCount() {
        System.out.println("getCompetitorCount");
        if (Tournament.getTournament().getPoolCount() == 0) {
            fail("No pool in file");
        }

        Pool instance = Tournament.getTournament().getPool(0);
        for (int i = 0; i < instance.getCompetitorCount(); i++) {
            Competitor expResult = instance.getCompetitor(i);
            Assert.assertNotNull(expResult);
        }
        int nb = instance.getCompetitorCount();
        Competitor c = new Coach("Toto");
        instance.addCompetitor(c);
        assertEquals(nb + 1, instance.getCompetitorCount());
    }

    /**
     * Test of getCompetitors method, of class Pool.
     */
    @Test
    public void testGetCompetitors() {
        System.out.println("getCompetitors");
        if (Tournament.getTournament().getPoolCount() == 0) {
            fail("No pool in file");
        }

        Pool instance = Tournament.getTournament().getPool(0);
        for (int i = 0; i < instance.getCompetitorCount(); i++) {
            Competitor expResult = instance.getCompetitor(i);
            Assert.assertEquals(expResult, instance.getCompetitors().get(i));
        }
    }

    /**
     * Test of getUID method, of class Pool.
     */
    @Test
    public void testGetUID() {
        System.out.println("getUID");
        Pool instance = new Pool();
        int expResult = 0;
        int result = instance.getUID();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setUID method, of class Pool.
     */
    @Test
    public void testSetUID() {
        System.out.println("setUID");
        int UID = 0;
        Pool instance = new Pool();
        instance.setUID(UID);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class Pool.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = null;
        Pool instance = new Pool();
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
