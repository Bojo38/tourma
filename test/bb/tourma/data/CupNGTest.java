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
public class CupNGTest {

    public CupNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        Tournament.getTournament().loadXML(new File("./test/cup_with_looser.xml"));
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        Tournament.clear();
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }

    /**
     * Test of getTables method, of class Cup.
     */
    @Test
    public void testGetTables() {
        System.out.println("getTables");
        Cup instance = Tournament.getTournament().getCup();

        ArrayList result = instance.getTables();
        assertNotNull(result);
    }

    /**
     * Test of cleanRound method, of class Cup.
     */
    @Test
    public void testCleanRound() {
        System.out.println("cleanRound");
        Tournament.getTournament().loadXML(new File("./test/cup_with_looser.xml"));
        Round r = Tournament.getTournament().getRound(Tournament.getTournament().getRoundsCount() - 1);
        Cup instance = Tournament.getTournament().getCup();

        instance.cleanRound(r);
    }

    /**
     * Test of getXMLElement method, of class Cup.
     */
    @Test
    public void testGetXMLElement() {
        System.out.println("getXMLElement");
        Cup instance = Tournament.getTournament().getCup();
        Element result = instance.getXMLElement();
        Cup cup = new Cup();
        cup.setXMLElement(result);
        //assertEquals(instance, cup);
    }

    /**
     * Test of setXMLElement method, of class Cup.
     */
    @Test
    public void testSetXMLElement() {
        System.out.println("setXMLElement");
        Cup instance = Tournament.getTournament().getCup();
        Element result = instance.getXMLElement();
        Cup cup = new Cup();
        cup.setXMLElement(result);
        //assertEquals(instance, cup);
    }

    /**
     * Test of getInitialDraw method, of class Cup.
     */
    @Test
    public void testGetInitialDraw() {
        System.out.println("getInitialDraw");
        Cup instance = Tournament.getTournament().getCup();
        Cup.INITIAL_DRAW expResult = Cup.INITIAL_DRAW.RANDOM;
        Cup.INITIAL_DRAW result = instance.getInitialDraw();
        assertEquals(result, expResult);
    }

    /**
     * Test of setInitialDraw method, of class Cup.
     */
    @Test
    public void testSetInitialDraw() {
        System.out.println("setInitialDraw");
        Cup instance = Tournament.getTournament().getCup();
        Cup.INITIAL_DRAW expResult = Cup.INITIAL_DRAW.RANDOM;
        Cup.INITIAL_DRAW result = instance.getInitialDraw();
        assertEquals(result, expResult);
        instance.setInitialDraw(Cup.INITIAL_DRAW.RANKING);
        result = instance.getInitialDraw();
        assertEquals(result, Cup.INITIAL_DRAW.RANKING);
    }

    /**
     * Test of isShuffle method, of class Cup.
     */
    @Test
    public void testIsShuffle() {
        System.out.println("isShuffle");
        Cup instance = Tournament.getTournament().getCup();
        boolean expResult = true;
        boolean result = instance.isShuffle();
        assertEquals(result, expResult);

        instance.setShuffle(false);
        result = instance.isShuffle();
        assertEquals(result, false);
    }

    /**
     * Test of setShuffle method, of class Cup.
     */
    @Test
    public void testSetShuffle() {
        System.out.println("setShuffle");
        Tournament.getTournament().loadXML(new File("./test/cup_with_looser.xml"));

        Cup instance = Tournament.getTournament().getCup();
        boolean expResult = true;
        boolean result = instance.isShuffle();
        assertEquals(result, expResult);

        instance.setShuffle(false);
        result = instance.isShuffle();
        assertEquals(result, false);
    }

    /**
     * Test of getType method, of class Cup.
     */
    @Test
    public void testGetType() {
        System.out.println("getType");
        Tournament.getTournament().loadXML(new File("./test/cup_with_looser.xml"));

        Cup instance = Tournament.getTournament().getCup();
        Cup.ROUND_TYPE expResult = Cup.ROUND_TYPE.LOOSER;
        Cup.ROUND_TYPE result = instance.getType();
        assertEquals(result, expResult);

        instance.setType(Cup.ROUND_TYPE.CLASSIC);
        result = instance.getType();
        assertEquals(result, Cup.ROUND_TYPE.CLASSIC);
    }

    /**
     * Test of setType method, of class Cup.
     */
    @Test
    public void testSetType() {
        System.out.println("setType");
        Tournament.getTournament().loadXML(new File("./test/cup_with_looser.xml"));
        Cup instance = Tournament.getTournament().getCup();
        Cup.ROUND_TYPE expResult = Cup.ROUND_TYPE.LOOSER;
        Cup.ROUND_TYPE result = instance.getType();
        assertEquals(result, expResult);

        instance.setType(Cup.ROUND_TYPE.CLASSIC);
        result = instance.getType();
        assertEquals(result, Cup.ROUND_TYPE.CLASSIC);
    }

    /**
     * Test of getRoundsCount method, of class Cup.
     */
    @Test
    public void testGetRoundsCount() {
        System.out.println("getRoundsCount");
        Tournament.getTournament().loadXML(new File("./test/cup_with_looser.xml"));
        Cup instance = Tournament.getTournament().getCup();

        int expResult = 2;
        int result = instance.getRoundsCount();
        assertEquals(result, expResult);

    }

    /**
     * Test of setRoundsCount method, of class Cup.
     */
    @Test
    public void testSetRoundsCount() {
        System.out.println("setRoundsCount");
        Tournament.getTournament().loadXML(new File("./test/cup_with_looser.xml"));
        Cup instance = Tournament.getTournament().getCup();

        int expResult = 2;
        int result = instance.getRoundsCount();
        assertEquals(result, expResult);

        instance.setRoundsCount(4);
        result = instance.getRoundsCount();
        assertEquals(result, 4);
    }

    /**
     * Test of isSwissForLoosers method, of class Cup.
     */
    @Test
    public void testIsSwissForLoosers() {
        System.out.println("isSwissForLoosers");
        Cup instance = new Cup();
        boolean expResult = true;
        boolean result = instance.isSwissForLoosers();
        assertEquals(result, expResult);

        instance.setSwissForLoosers(false);
        result = instance.isSwissForLoosers();
        assertEquals(result, false);
    }

    /**
     * Test of setSwissForLoosers method, of class Cup.
     */
    @Test
    public void testSetSwissForLoosers() {
        System.out.println("setSwissForLoosers");
        Cup instance = new Cup();
        boolean expResult = true;
        boolean result = instance.isSwissForLoosers();
        assertEquals(result, expResult);

        instance.setSwissForLoosers(false);
        result = instance.isSwissForLoosers();
        assertEquals(result, false);
    }

    /**
     * Test of generateMatches method, of class Cup.
     */
    @Test
    public void testGenerateMatches() {
        System.out.println("generateMatches");
        Tournament.getTournament().loadXML(new File("./test/cup_with_looser.xml"));
        Cup instance = Tournament.getTournament().getCup();

        int roundIndex = 0;
        ArrayList<Match> previousRoundMatches = new ArrayList<>();
        ArrayList<Competitor> competitors = new ArrayList<>();
        ArrayList<Coach> coaches = Tournament.getTournament().getCoachs();
        for (int i = 0; i < 4; i++) {
            competitors.add(coaches.get(i));
        }
        Round r = Tournament.getTournament().getRound(Tournament.getTournament().getRoundsCount() - 1);
        for (int i = 0; i < 4; i++) {
            competitors.add(coaches.get(i));
        }
        roundIndex = r.getCupTour();
        Round r1 = Tournament.getTournament().getRound(Tournament.getTournament().getRoundsCount() - 2);
        for (CoachMatch cm : r1.getCoachMatchs()) {

            previousRoundMatches.add(cm);

        }
        ArrayList result = instance.generateMatches(roundIndex, r, previousRoundMatches, competitors);
        //assertEquals(result, expResult);
    }

}
