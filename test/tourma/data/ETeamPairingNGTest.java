/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

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
public class ETeamPairingNGTest {

    public ETeamPairingNGTest() {
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
     * Test of values method, of class ETeamPairing.
     */
    @Test
    public void testValues() {
        System.out.println("values");
        ETeamPairing[] expResult = {
            ETeamPairing.INDIVIDUAL_PAIRING,
            ETeamPairing.TEAM_PAIRING
        };
        ETeamPairing[] result = ETeamPairing.values();
        assertEquals(result, expResult);

    }

    /**
     * Test of valueOf method, of class ETeamPairing.
     */
    @Test
    public void testValueOf() {
        System.out.println("valueOf");
        String name = "INDIVIDUAL_PAIRING";
        ETeamPairing expResult = ETeamPairing.INDIVIDUAL_PAIRING;
        ETeamPairing result = ETeamPairing.valueOf(name);
        assertEquals(result, expResult);

    }

}
