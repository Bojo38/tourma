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
public class CupRoundNGTest {

    public CupRoundNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        Tournament.getTournament().loadXML(new File("./test/cup_with_looser.xml"));
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
     * Test of getNbMatchs method, of class CupRound.
     */
    @Test
    public void testGetNbMatchs() {
        System.out.println("getNbMatchs");
        int nb = 7;
        CupRound instance = new CupRound();
        instance.setNbMatchs(nb);

        assertEquals(7, instance.getNbMatchs());
    }

    /**
     * Test of setNbMatchs method, of class CupRound.
     */
    @Test
    public void testSetNbMatchs() {
        System.out.println("setNbMatchs");
        int nb = 7;
        CupRound instance = new CupRound();
        instance.setNbMatchs(nb);

        assertEquals(7, instance.getNbMatchs());
    }

    /**
     * Test of getMatchs method, of class CupRound.
     */
    @Test
    public void testGetMatchs() {
        System.out.println("getMatchs");
        ArrayList<Match> mMatchs = new ArrayList<Match>();
        CupRound instance = new CupRound();
        instance.setMatchs(mMatchs);

        assertEquals(mMatchs, instance.getMatchs());
    }

    /**
     * Test of setMatchs method, of class CupRound.
     */
    @Test
    public void testSetMatchs() {
        System.out.println("setMatchs");
        ArrayList<Match> mMatchs = new ArrayList<Match>();
        CupRound instance = new CupRound();
        instance.setMatchs(mMatchs);

        assertEquals(mMatchs, instance.getMatchs());
    }

    /**
     * Test of generateEmptyMatchs method, of class CupRound.
     */
    @Test
    public void testGenerateEmptyMatchs() {
        System.out.println("generateEmptyMatchs");
        CupRound instance = new CupRound();
        instance.generateEmptyMatchs();

        assertEquals(instance.getMatchs().size(), 0);

        instance.setNbMatchs(4);

        instance.generateEmptyMatchs();

        assertEquals(instance.getMatchs().size(), 4);

    }

    /**
     * Test of getXMLElement method, of class CupRound.
     */
    @Test
    public void testGetXMLElement() {
        System.out.println("getXMLElement");
        CupRound instance = Tournament.getTournament().getCup().getTables().get(0).getCupRounds().get(0);
        Element expResult = instance.getXMLElement();
        Element result = instance.getXMLElement();
        //assertEquals(result, expResult);

    }

    /**
     * Test of setXMLElement method, of class CupRound.
     */
    @Test
    public void testSetXMLElement() {
        System.out.println("setXMLElement");
        CupRound instance = Tournament.getTournament().getCup().getTables().get(0).getCupRounds().get(0);

        Element result = instance.getXMLElement();

        CupRound cr = new CupRound();
        cr.setXMLElement(result);
    }

}
