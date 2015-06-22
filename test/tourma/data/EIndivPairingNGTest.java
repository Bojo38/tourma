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
public class EIndivPairingNGTest {

    public EIndivPairingNGTest() {
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
     * Test of values method, of class EIndivPairing.
     */
    @Test
    public void testValues() {
        System.out.println("values");
        EIndivPairing[] expResult = {
            EIndivPairing.RANKING,
            EIndivPairing.FREE,
            EIndivPairing.RANDOM,
            EIndivPairing.NAF
        };
        EIndivPairing[] result = EIndivPairing.values();
        assertEquals(result, expResult);
    }

    /**
     * Test of valueOf method, of class EIndivPairing.
     */
    @Test
    public void testValueOf() {
        System.out.println("valueOf");
        String name = "RANKING";
        EIndivPairing expResult = EIndivPairing.RANKING;
        EIndivPairing result = EIndivPairing.valueOf(name);
        assertEquals(result, expResult);

    }

}
