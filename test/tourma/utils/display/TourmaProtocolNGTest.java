/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.utils.display;

import tourma.utils.display.TourmaProtocol;
import org.testng.Assert;
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
public class TourmaProtocolNGTest {

    public TourmaProtocolNGTest() {
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
     * Test of getKey method, of class TourmaProtocol.
     */
    @Test
    public void testGetKey() {
        System.out.println("getKey");
        TourmaProtocol instance = new TourmaProtocol();

        String k = "INDIVIDUAL_RANK";
        Assert.assertEquals(instance.getKey(k), TourmaProtocol.TKey.INDIVIDUAL_RANK);

        k = "TEAM_RANK";
        Assert.assertEquals(instance.getKey(k), TourmaProtocol.TKey.TEAM_RANK);
        k = "MATCHS";
        Assert.assertEquals(instance.getKey(k), TourmaProtocol.TKey.MATCHS);
        k = "CLAN_RANK";
        Assert.assertEquals(instance.getKey(k), TourmaProtocol.TKey.CLAN_RANK);
        k = "INDIVIDUAL_ANNEX";
        Assert.assertEquals(instance.getKey(k), TourmaProtocol.TKey.INDIVIDUAL_ANNEX);
        k = "TEAM_ANNEX";
        Assert.assertEquals(instance.getKey(k), TourmaProtocol.TKey.TEAM_ANNEX);
        k = "CLAN_ANNEX";
        Assert.assertEquals(instance.getKey(k), TourmaProtocol.TKey.CLAN_ANNEX);
        k = "GROUP_RANK";
        Assert.assertEquals(instance.getKey(k), TourmaProtocol.TKey.GROUP_RANK);
        k = "CATEGORY_RANK";
        Assert.assertEquals(instance.getKey(k), TourmaProtocol.TKey.CATEGORY_RANK);
        k = "GROUP_ANNEX";
        Assert.assertEquals(instance.getKey(k), TourmaProtocol.TKey.GROUP_ANNEX);
        k = "CATEGORY_ANNEX";
        Assert.assertEquals(instance.getKey(k), TourmaProtocol.TKey.CATEGORY_ANNEX);
        k = "POOL_INDIV_RANK";
        Assert.assertEquals(instance.getKey(k), TourmaProtocol.TKey.POOL_INDIV_RANK);
        k = "POOL_TEAM_RANK";
        Assert.assertEquals(instance.getKey(k), TourmaProtocol.TKey.POOL_TEAM_RANK);
        k = "POOL_INDIV_ANNEX";
        Assert.assertEquals(instance.getKey(k), TourmaProtocol.TKey.POOL_INDIV_ANNEX);
        k = "POOL_TEAM_ANNEX";
        Assert.assertEquals(instance.getKey(k), TourmaProtocol.TKey.POOL_TEAM_ANNEX);
        k = "END";
        Assert.assertEquals(instance.getKey(k), TourmaProtocol.TKey.END);
        k = "BlaBla";
        Assert.assertEquals(instance.getKey(k), TourmaProtocol.TKey.UNKNOWN);
    }

    /**
     * Test of processInput method, of class TourmaProtocol.
     */
    @Test(enabled=false)
    public void testProcessInput() {
        System.out.println("processInput");
        Object object = null;
        TourmaProtocol instance = new TourmaProtocol();
        String expResult = "";
        String result = instance.processInput(object);
        //assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
