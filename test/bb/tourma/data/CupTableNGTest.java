/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bb.tourma.data;

import java.io.File;
import java.util.ArrayList;
import org.jdom.Element;
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
public class CupTableNGTest {

    public CupTableNGTest() {
        Tournament.getTournament().loadXML(new File("./test/cup_with_looser.xml"));
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
     * Test of getCupRounds method, of class CupTable.
     */
    @Test
    public void testGetCupRounds() {
        System.out.println("getCupRounds");
        CupTable instance = Tournament.getTournament().getCup().getTables().get(0);
        ArrayList result = instance.getCupRounds();

    }

    /**
     * Test of setCupRounds method, of class CupTable.
     */
    @Test
    public void testSetCupRounds() {
        System.out.println("setCupRounds");
        CupTable instance = Tournament.getTournament().getCup().getTables().get(0);
        ArrayList result = instance.getCupRounds();
        ArrayList<CupRound> mCupRounds = new ArrayList<>();
        instance.setCupRounds(mCupRounds);
        result = instance.getCupRounds();
        assertEquals(result, mCupRounds);
    }

    /**
     * Test of getXMLElement method, of class CupTable.
     */
    @Test
    public void testGetXMLElement() {
        System.out.println("getXMLElement");
        CupTable instance = Tournament.getTournament().getCup().getTables().get(0);
        Element result = instance.getXMLElement();
      
        CupTable ct=new CupTable(instance.getCupRounds().size(), false, false);
        ct.setXMLElement(result);
    }

    /**
     * Test of setXMLElement method, of class CupTable.
     */
    @Test
    public void testSetXMLElement() {
        System.out.println("setXMLElement");
        CupTable instance = Tournament.getTournament().getCup().getTables().get(0);
        Element result = instance.getXMLElement();
      
        CupTable ct=new CupTable(instance.getCupRounds().size(), false, false);
        ct.setXMLElement(result);
    }

}
