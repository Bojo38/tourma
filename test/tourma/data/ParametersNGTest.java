/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import java.text.SimpleDateFormat;
import java.util.Date;
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
public class ParametersNGTest {
    
    public ParametersNGTest() {
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
     * Test of getXMLElement method, of class Parameters.
     */
    @Test
    public void testGetXMLElement() {
        System.out.println("getXMLElement");
        Parameters instance = new Parameters();
        Element expResult = null;
        Element result = instance.getXMLElement();
        assertEquals(result, expResult);
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
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTeamRankingType method, of class Parameters.
     */
    @Test
    public void testGetTeamRankingType() {
        System.out.println("getTeamRankingType");
        int j = 0;
        Parameters instance = new Parameters();
        int expResult = 0;
        int result = instance.getTeamRankingType(j);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getIndivRankingType method, of class Parameters.
     */
    @Test
    public void testGetIndivRankingType() {
        System.out.println("getIndivRankingType");
        int j = 0;
        Parameters instance = new Parameters();
        int expResult = 0;
        int result = instance.getIndivRankingType(j);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getIndivRankingNumber method, of class Parameters.
     */
    @Test
    public void testGetIndivRankingNumber() {
        System.out.println("getIndivRankingNumber");
        Parameters instance = new Parameters();
        int expResult = 0;
        int result = instance.getIndivRankingNumber();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTeamRankingNumber method, of class Parameters.
     */
    @Test
    public void testGetTeamRankingNumber() {
        System.out.println("getTeamRankingNumber");
        Parameters instance = new Parameters();
        int expResult = 0;
        int result = instance.getTeamRankingNumber();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCriteriaCount method, of class Parameters.
     */
    @Test
    public void testGetCriteriaCount() {
        System.out.println("getCriteriaCount");
        Parameters instance = new Parameters();
        int expResult = 0;
        int result = instance.getCriteriaCount();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCriteria method, of class Parameters.
     */
    @Test
    public void testGetCriteria() {
        System.out.println("getCriteria");
        int i = 0;
        Parameters instance = new Parameters();
        Criteria expResult = null;
        Criteria result = instance.getCriteria(i);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of clearCiterias method, of class Parameters.
     */
    @Test
    public void testClearCiterias() {
        System.out.println("clearCiterias");
        Parameters instance = new Parameters();
        instance.clearCiterias();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addCriteria method, of class Parameters.
     */
    @Test
    public void testAddCriteria() {
        System.out.println("addCriteria");
        Criteria c = null;
        Parameters instance = new Parameters();
        instance.addCriteria(c);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeCriteria method, of class Parameters.
     */
    @Test
    public void testRemoveCriteria() {
        System.out.println("removeCriteria");
        int c = 0;
        Parameters instance = new Parameters();
        instance.removeCriteria(c);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGame method, of class Parameters.
     */
    @Test
    public void testGetGame() {
        System.out.println("getGame");
        Parameters instance = new Parameters();
        int expResult = 0;
        int result = instance.getGame();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setGame method, of class Parameters.
     */
    @Test
    public void testSetGame() {
        System.out.println("setGame");
        int mGame = 0;
        Parameters instance = new Parameters();
        instance.setGame(mGame);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPointsIndivVictory method, of class Parameters.
     */
    @Test
    public void testGetPointsIndivVictory() {
        System.out.println("getPointsIndivVictory");
        Parameters instance = new Parameters();
        int expResult = 0;
        int result = instance.getPointsIndivVictory();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPointsIndivVictory method, of class Parameters.
     */
    @Test
    public void testSetPointsIndivVictory() {
        System.out.println("setPointsIndivVictory");
        int mPointsIndivVictory = 0;
        Parameters instance = new Parameters();
        instance.setPointsIndivVictory(mPointsIndivVictory);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPointsIndivLargeVictory method, of class Parameters.
     */
    @Test
    public void testGetPointsIndivLargeVictory() {
        System.out.println("getPointsIndivLargeVictory");
        Parameters instance = new Parameters();
        int expResult = 0;
        int result = instance.getPointsIndivLargeVictory();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPointsIndivLargeVictory method, of class Parameters.
     */
    @Test
    public void testSetPointsIndivLargeVictory() {
        System.out.println("setPointsIndivLargeVictory");
        int mPointsIndivLargeVictory = 0;
        Parameters instance = new Parameters();
        instance.setPointsIndivLargeVictory(mPointsIndivLargeVictory);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPointsIndivDraw method, of class Parameters.
     */
    @Test
    public void testGetPointsIndivDraw() {
        System.out.println("getPointsIndivDraw");
        Parameters instance = new Parameters();
        int expResult = 0;
        int result = instance.getPointsIndivDraw();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPointsIndivDraw method, of class Parameters.
     */
    @Test
    public void testSetPointsIndivDraw() {
        System.out.println("setPointsIndivDraw");
        int mPointsIndivDraw = 0;
        Parameters instance = new Parameters();
        instance.setPointsIndivDraw(mPointsIndivDraw);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPointsIndivLittleLost method, of class Parameters.
     */
    @Test
    public void testGetPointsIndivLittleLost() {
        System.out.println("getPointsIndivLittleLost");
        Parameters instance = new Parameters();
        int expResult = 0;
        int result = instance.getPointsIndivLittleLost();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPointsIndivLittleLost method, of class Parameters.
     */
    @Test
    public void testSetPointsIndivLittleLost() {
        System.out.println("setPointsIndivLittleLost");
        int mPointsIndivLittleLost = 0;
        Parameters instance = new Parameters();
        instance.setPointsIndivLittleLost(mPointsIndivLittleLost);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPointsIndivLost method, of class Parameters.
     */
    @Test
    public void testGetPointsIndivLost() {
        System.out.println("getPointsIndivLost");
        Parameters instance = new Parameters();
        int expResult = 0;
        int result = instance.getPointsIndivLost();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPointsIndivLost method, of class Parameters.
     */
    @Test
    public void testSetPointsIndivLost() {
        System.out.println("setPointsIndivLost");
        int mPointsIndivLost = 0;
        Parameters instance = new Parameters();
        instance.setPointsIndivLost(mPointsIndivLost);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPointsRefused method, of class Parameters.
     */
    @Test
    public void testGetPointsRefused() {
        System.out.println("getPointsRefused");
        Parameters instance = new Parameters();
        int expResult = 0;
        int result = instance.getPointsRefused();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPointsRefused method, of class Parameters.
     */
    @Test
    public void testSetPointsRefused() {
        System.out.println("setPointsRefused");
        int mPointsRefused = 0;
        Parameters instance = new Parameters();
        instance.setPointsRefused(mPointsRefused);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPointsConcedeed method, of class Parameters.
     */
    @Test
    public void testGetPointsConcedeed() {
        System.out.println("getPointsConcedeed");
        Parameters instance = new Parameters();
        int expResult = 0;
        int result = instance.getPointsConcedeed();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPointsConcedeed method, of class Parameters.
     */
    @Test
    public void testSetPointsConcedeed() {
        System.out.println("setPointsConcedeed");
        int mPointsConcedeed = 0;
        Parameters instance = new Parameters();
        instance.setPointsConcedeed(mPointsConcedeed);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isTeamVictoryOnly method, of class Parameters.
     */
    @Test
    public void testIsTeamVictoryOnly() {
        System.out.println("isTeamVictoryOnly");
        Parameters instance = new Parameters();
        boolean expResult = false;
        boolean result = instance.isTeamVictoryOnly();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTeamVictoryOnly method, of class Parameters.
     */
    @Test
    public void testSetTeamVictoryOnly() {
        System.out.println("setTeamVictoryOnly");
        boolean mTeamVictoryOnly = false;
        Parameters instance = new Parameters();
        instance.setTeamVictoryOnly(mTeamVictoryOnly);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isPortugal method, of class Parameters.
     */
    @Test
    public void testIsPortugal() {
        System.out.println("isPortugal");
        Parameters instance = new Parameters();
        boolean expResult = false;
        boolean result = instance.isPortugal();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPortugal method, of class Parameters.
     */
    @Test
    public void testSetPortugal() {
        System.out.println("setPortugal");
        boolean mPortugal = false;
        Parameters instance = new Parameters();
        instance.setPortugal(mPortugal);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGapLargeVictory method, of class Parameters.
     */
    @Test
    public void testGetGapLargeVictory() {
        System.out.println("getGapLargeVictory");
        Parameters instance = new Parameters();
        int expResult = 0;
        int result = instance.getGapLargeVictory();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setGapLargeVictory method, of class Parameters.
     */
    @Test
    public void testSetGapLargeVictory() {
        System.out.println("setGapLargeVictory");
        int mGapLargeVictory = 0;
        Parameters instance = new Parameters();
        instance.setGapLargeVictory(mGapLargeVictory);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGapLittleLost method, of class Parameters.
     */
    @Test
    public void testGetGapLittleLost() {
        System.out.println("getGapLittleLost");
        Parameters instance = new Parameters();
        int expResult = 0;
        int result = instance.getGapLittleLost();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setGapLittleLost method, of class Parameters.
     */
    @Test
    public void testSetGapLittleLost() {
        System.out.println("setGapLittleLost");
        int mGapLittleLost = 0;
        Parameters instance = new Parameters();
        instance.setGapLittleLost(mGapLittleLost);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isSubstitutes method, of class Parameters.
     */
    @Test
    public void testIsSubstitutes() {
        System.out.println("isSubstitutes");
        Parameters instance = new Parameters();
        boolean expResult = false;
        boolean result = instance.isSubstitutes();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSubstitutes method, of class Parameters.
     */
    @Test
    public void testSetSubstitutes() {
        System.out.println("setSubstitutes");
        boolean mSubstitutes = false;
        Parameters instance = new Parameters();
        instance.setSubstitutes(mSubstitutes);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTournamentName method, of class Parameters.
     */
    @Test
    public void testGetTournamentName() {
        System.out.println("getTournamentName");
        Parameters instance = new Parameters();
        String expResult = "";
        String result = instance.getTournamentName();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTournamentName method, of class Parameters.
     */
    @Test
    public void testSetTournamentName() {
        System.out.println("setTournamentName");
        String mTournamentName = "";
        Parameters instance = new Parameters();
        instance.setTournamentName(mTournamentName);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTournamentOrga method, of class Parameters.
     */
    @Test
    public void testGetTournamentOrga() {
        System.out.println("getTournamentOrga");
        Parameters instance = new Parameters();
        String expResult = "";
        String result = instance.getTournamentOrga();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of semTournamentOrga method, of class Parameters.
     */
    @Test
    public void testSemTournamentOrga() {
        System.out.println("semTournamentOrga");
        String mTournamentOrga = "";
        Parameters instance = new Parameters();
        instance.semTournamentOrga(mTournamentOrga);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPlace method, of class Parameters.
     */
    @Test
    public void testGetPlace() {
        System.out.println("getPlace");
        Parameters instance = new Parameters();
        String expResult = "";
        String result = instance.getPlace();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPlace method, of class Parameters.
     */
    @Test
    public void testSetPlace() {
        System.out.println("setPlace");
        String mPlace = "";
        Parameters instance = new Parameters();
        instance.setPlace(mPlace);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStringDate method, of class Parameters.
     */
    @Test
    public void testGetStringDate() {
        System.out.println("getStringDate");
        SimpleDateFormat format = null;
        Parameters instance = new Parameters();
        String expResult = "";
        String result = instance.getStringDate(format);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDateTime method, of class Parameters.
     */
    @Test
    public void testGetDateTime() {
        System.out.println("getDateTime");
        Parameters instance = new Parameters();
        long expResult = 0L;
        long result = instance.getDateTime();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDate method, of class Parameters.
     */
    @Test
    public void testSetDate() {
        System.out.println("setDate");
        Date mDate = null;
        Parameters instance = new Parameters();
        instance.setDate(mDate);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRankingIndiv1 method, of class Parameters.
     */
    @Test
    public void testGetRankingIndiv1() {
        System.out.println("getRankingIndiv1");
        Parameters instance = new Parameters();
        int expResult = 0;
        int result = instance.getRankingIndiv1();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setRankingIndiv1 method, of class Parameters.
     */
    @Test
    public void testSetRankingIndiv1() {
        System.out.println("setRankingIndiv1");
        int mRankingIndiv1 = 0;
        Parameters instance = new Parameters();
        instance.setRankingIndiv1(mRankingIndiv1);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRankingIndiv2 method, of class Parameters.
     */
    @Test
    public void testGetRankingIndiv2() {
        System.out.println("getRankingIndiv2");
        Parameters instance = new Parameters();
        int expResult = 0;
        int result = instance.getRankingIndiv2();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setRankingIndiv2 method, of class Parameters.
     */
    @Test
    public void testSetRankingIndiv2() {
        System.out.println("setRankingIndiv2");
        int mRankingIndiv2 = 0;
        Parameters instance = new Parameters();
        instance.setRankingIndiv2(mRankingIndiv2);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRankingIndiv3 method, of class Parameters.
     */
    @Test
    public void testGetRankingIndiv3() {
        System.out.println("getRankingIndiv3");
        Parameters instance = new Parameters();
        int expResult = 0;
        int result = instance.getRankingIndiv3();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setRankingIndiv3 method, of class Parameters.
     */
    @Test
    public void testSetRankingIndiv3() {
        System.out.println("setRankingIndiv3");
        int mRankingIndiv3 = 0;
        Parameters instance = new Parameters();
        instance.setRankingIndiv3(mRankingIndiv3);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRankingIndiv4 method, of class Parameters.
     */
    @Test
    public void testGetRankingIndiv4() {
        System.out.println("getRankingIndiv4");
        Parameters instance = new Parameters();
        int expResult = 0;
        int result = instance.getRankingIndiv4();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setRankingIndiv4 method, of class Parameters.
     */
    @Test
    public void testSetRankingIndiv4() {
        System.out.println("setRankingIndiv4");
        int mRankingIndiv4 = 0;
        Parameters instance = new Parameters();
        instance.setRankingIndiv4(mRankingIndiv4);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRankingIndiv5 method, of class Parameters.
     */
    @Test
    public void testGetRankingIndiv5() {
        System.out.println("getRankingIndiv5");
        Parameters instance = new Parameters();
        int expResult = 0;
        int result = instance.getRankingIndiv5();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setRankingIndiv5 method, of class Parameters.
     */
    @Test
    public void testSetRankingIndiv5() {
        System.out.println("setRankingIndiv5");
        int mRankingIndiv5 = 0;
        Parameters instance = new Parameters();
        instance.setRankingIndiv5(mRankingIndiv5);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isTeamTournament method, of class Parameters.
     */
    @Test
    public void testIsTeamTournament() {
        System.out.println("isTeamTournament");
        Parameters instance = new Parameters();
        boolean expResult = false;
        boolean result = instance.isTeamTournament();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTeamTournament method, of class Parameters.
     */
    @Test
    public void testSetTeamTournament() {
        System.out.println("setTeamTournament");
        boolean mTeamTournament = false;
        Parameters instance = new Parameters();
        instance.setTeamTournament(mTeamTournament);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isMultiRoster method, of class Parameters.
     */
    @Test
    public void testIsMultiRoster() {
        System.out.println("isMultiRoster");
        Parameters instance = new Parameters();
        boolean expResult = false;
        boolean result = instance.isMultiRoster();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setMultiRoster method, of class Parameters.
     */
    @Test
    public void testSetMultiRoster() {
        System.out.println("setMultiRoster");
        boolean mMultiRoster = false;
        Parameters instance = new Parameters();
        instance.setMultiRoster(mMultiRoster);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTeamPairing method, of class Parameters.
     */
    @Test
    public void testGetTeamPairing() {
        System.out.println("getTeamPairing");
        Parameters instance = new Parameters();
        ETeamPairing expResult = null;
        ETeamPairing result = instance.getTeamPairing();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTeamPairing method, of class Parameters.
     */
    @Test
    public void testSetTeamPairing() {
        System.out.println("setTeamPairing");
        ETeamPairing mTeamPairing = null;
        Parameters instance = new Parameters();
        instance.setTeamPairing(mTeamPairing);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTeamIndivPairing method, of class Parameters.
     */
    @Test
    public void testGetTeamIndivPairing() {
        System.out.println("getTeamIndivPairing");
        Parameters instance = new Parameters();
        EIndivPairing expResult = null;
        EIndivPairing result = instance.getTeamIndivPairing();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTeamIndivPairing method, of class Parameters.
     */
    @Test
    public void testSetTeamIndivPairing() {
        System.out.println("setTeamIndivPairing");
        EIndivPairing mTeamIndivPairing = null;
        Parameters instance = new Parameters();
        instance.setTeamIndivPairing(mTeamIndivPairing);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTeamMatesNumber method, of class Parameters.
     */
    @Test
    public void testGetTeamMatesNumber() {
        System.out.println("getTeamMatesNumber");
        Parameters instance = new Parameters();
        int expResult = 0;
        int result = instance.getTeamMatesNumber();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTeamMatesNumber method, of class Parameters.
     */
    @Test
    public void testSetTeamMatesNumber() {
        System.out.println("setTeamMatesNumber");
        int mTeamMatesNumber = 0;
        Parameters instance = new Parameters();
        instance.setTeamMatesNumber(mTeamMatesNumber);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getClansMembersNumber method, of class Parameters.
     */
    @Test
    public void testGetClansMembersNumber() {
        System.out.println("getClansMembersNumber");
        Parameters instance = new Parameters();
        int expResult = 0;
        int result = instance.getClansMembersNumber();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setClansMembersNumber method, of class Parameters.
     */
    @Test
    public void testSetClansMembersNumber() {
        System.out.println("setClansMembersNumber");
        int mClansMembersNumber = 0;
        Parameters instance = new Parameters();
        instance.setClansMembersNumber(mClansMembersNumber);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isIndivPairingTeamBalanced method, of class Parameters.
     */
    @Test
    public void testIsIndivPairingTeamBalanced() {
        System.out.println("isIndivPairingTeamBalanced");
        Parameters instance = new Parameters();
        boolean expResult = false;
        boolean result = instance.isIndivPairingTeamBalanced();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setIndivPairingTeamBalanced method, of class Parameters.
     */
    @Test
    public void testSetIndivPairingTeamBalanced() {
        System.out.println("setIndivPairingTeamBalanced");
        boolean mIndivPairingTeamBalanced = false;
        Parameters instance = new Parameters();
        instance.setIndivPairingTeamBalanced(mIndivPairingTeamBalanced);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isIndivPairingIndivBalanced method, of class Parameters.
     */
    @Test
    public void testIsIndivPairingIndivBalanced() {
        System.out.println("isIndivPairingIndivBalanced");
        Parameters instance = new Parameters();
        boolean expResult = false;
        boolean result = instance.isIndivPairingIndivBalanced();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setIndivPairingIndivBalanced method, of class Parameters.
     */
    @Test
    public void testSetIndivPairingIndivBalanced() {
        System.out.println("setIndivPairingIndivBalanced");
        boolean mIndivPairingIndivBalanced = false;
        Parameters instance = new Parameters();
        instance.setIndivPairingIndivBalanced(mIndivPairingIndivBalanced);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPointsTeamVictory method, of class Parameters.
     */
    @Test
    public void testGetPointsTeamVictory() {
        System.out.println("getPointsTeamVictory");
        Parameters instance = new Parameters();
        int expResult = 0;
        int result = instance.getPointsTeamVictory();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPointsTeamVictory method, of class Parameters.
     */
    @Test
    public void testSetPointsTeamVictory() {
        System.out.println("setPointsTeamVictory");
        int mPointsTeamVictory = 0;
        Parameters instance = new Parameters();
        instance.setPointsTeamVictory(mPointsTeamVictory);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPointsTeamDraw method, of class Parameters.
     */
    @Test
    public void testGetPointsTeamDraw() {
        System.out.println("getPointsTeamDraw");
        Parameters instance = new Parameters();
        int expResult = 0;
        int result = instance.getPointsTeamDraw();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPointsTeamDraw method, of class Parameters.
     */
    @Test
    public void testSetPointsTeamDraw() {
        System.out.println("setPointsTeamDraw");
        int mPointsTeamDraw = 0;
        Parameters instance = new Parameters();
        instance.setPointsTeamDraw(mPointsTeamDraw);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPointsTeamLost method, of class Parameters.
     */
    @Test
    public void testGetPointsTeamLost() {
        System.out.println("getPointsTeamLost");
        Parameters instance = new Parameters();
        int expResult = 0;
        int result = instance.getPointsTeamLost();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPointsTeamLost method, of class Parameters.
     */
    @Test
    public void testSetPointsTeamLost() {
        System.out.println("setPointsTeamLost");
        int mPointsTeamLost = 0;
        Parameters instance = new Parameters();
        instance.setPointsTeamLost(mPointsTeamLost);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRankingTeam1 method, of class Parameters.
     */
    @Test
    public void testGetRankingTeam1() {
        System.out.println("getRankingTeam1");
        Parameters instance = new Parameters();
        int expResult = 0;
        int result = instance.getRankingTeam1();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setRankingTeam1 method, of class Parameters.
     */
    @Test
    public void testSetRankingTeam1() {
        System.out.println("setRankingTeam1");
        int mRankingTeam1 = 0;
        Parameters instance = new Parameters();
        instance.setRankingTeam1(mRankingTeam1);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of gemRankingTeam2 method, of class Parameters.
     */
    @Test
    public void testGemRankingTeam2() {
        System.out.println("gemRankingTeam2");
        Parameters instance = new Parameters();
        int expResult = 0;
        int result = instance.gemRankingTeam2();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setRankingTeam2 method, of class Parameters.
     */
    @Test
    public void testSetRankingTeam2() {
        System.out.println("setRankingTeam2");
        int mRankingTeam2 = 0;
        Parameters instance = new Parameters();
        instance.setRankingTeam2(mRankingTeam2);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRankingTeam3 method, of class Parameters.
     */
    @Test
    public void testGetRankingTeam3() {
        System.out.println("getRankingTeam3");
        Parameters instance = new Parameters();
        int expResult = 0;
        int result = instance.getRankingTeam3();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setRankingTeam3 method, of class Parameters.
     */
    @Test
    public void testSetRankingTeam3() {
        System.out.println("setRankingTeam3");
        int mRankingTeam3 = 0;
        Parameters instance = new Parameters();
        instance.setRankingTeam3(mRankingTeam3);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRankingTeam4 method, of class Parameters.
     */
    @Test
    public void testGetRankingTeam4() {
        System.out.println("getRankingTeam4");
        Parameters instance = new Parameters();
        int expResult = 0;
        int result = instance.getRankingTeam4();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setRankingTeam4 method, of class Parameters.
     */
    @Test
    public void testSetRankingTeam4() {
        System.out.println("setRankingTeam4");
        int mRankingTeam4 = 0;
        Parameters instance = new Parameters();
        instance.setRankingTeam4(mRankingTeam4);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRankingTeam5 method, of class Parameters.
     */
    @Test
    public void testGetRankingTeam5() {
        System.out.println("getRankingTeam5");
        Parameters instance = new Parameters();
        int expResult = 0;
        int result = instance.getRankingTeam5();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setRankingTeam5 method, of class Parameters.
     */
    @Test
    public void testSetRankingTeam5() {
        System.out.println("setRankingTeam5");
        int mRankingTeam5 = 0;
        Parameters instance = new Parameters();
        instance.setRankingTeam5(mRankingTeam5);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPointsTeamVictoryBonus method, of class Parameters.
     */
    @Test
    public void testGetPointsTeamVictoryBonus() {
        System.out.println("getPointsTeamVictoryBonus");
        Parameters instance = new Parameters();
        int expResult = 0;
        int result = instance.getPointsTeamVictoryBonus();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPointsTeamVictoryBonus method, of class Parameters.
     */
    @Test
    public void testSetPointsTeamVictoryBonus() {
        System.out.println("setPointsTeamVictoryBonus");
        int mPointsTeamVictoryBonus = 0;
        Parameters instance = new Parameters();
        instance.setPointsTeamVictoryBonus(mPointsTeamVictoryBonus);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPointsTeamDrawBonus method, of class Parameters.
     */
    @Test
    public void testGetPointsTeamDrawBonus() {
        System.out.println("getPointsTeamDrawBonus");
        Parameters instance = new Parameters();
        int expResult = 0;
        int result = instance.getPointsTeamDrawBonus();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPointsTeamDrawBonus method, of class Parameters.
     */
    @Test
    public void testSetPointsTeamDrawBonus() {
        System.out.println("setPointsTeamDrawBonus");
        int mPointsTeamDrawBonus = 0;
        Parameters instance = new Parameters();
        instance.setPointsTeamDrawBonus(mPointsTeamDrawBonus);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isGroupsEnable method, of class Parameters.
     */
    @Test
    public void testIsGroupsEnable() {
        System.out.println("isGroupsEnable");
        Parameters instance = new Parameters();
        boolean expResult = false;
        boolean result = instance.isGroupsEnable();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setGroupsEnable method, of class Parameters.
     */
    @Test
    public void testSetGroupsEnable() {
        System.out.println("setGroupsEnable");
        boolean mGroupsEnable = false;
        Parameters instance = new Parameters();
        instance.setGroupsEnable(mGroupsEnable);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isEnableClans method, of class Parameters.
     */
    @Test
    public void testIsEnableClans() {
        System.out.println("isEnableClans");
        Parameters instance = new Parameters();
        boolean expResult = false;
        boolean result = instance.isEnableClans();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setEnableClans method, of class Parameters.
     */
    @Test
    public void testSetEnableClans() {
        System.out.println("setEnableClans");
        boolean mEnableClans = false;
        Parameters instance = new Parameters();
        instance.setEnableClans(mEnableClans);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isAvoidClansFirstMatch method, of class Parameters.
     */
    @Test
    public void testIsAvoidClansFirstMatch() {
        System.out.println("isAvoidClansFirstMatch");
        Parameters instance = new Parameters();
        boolean expResult = false;
        boolean result = instance.isAvoidClansFirstMatch();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setAvoidClansFirstMatch method, of class Parameters.
     */
    @Test
    public void testSetAvoidClansFirstMatch() {
        System.out.println("setAvoidClansFirstMatch");
        boolean mAvoidClansFirstMatch = false;
        Parameters instance = new Parameters();
        instance.setAvoidClansFirstMatch(mAvoidClansFirstMatch);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isAvoidClansMatch method, of class Parameters.
     */
    @Test
    public void testIsAvoidClansMatch() {
        System.out.println("isAvoidClansMatch");
        Parameters instance = new Parameters();
        boolean expResult = false;
        boolean result = instance.isAvoidClansMatch();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setAvoidClansMatch method, of class Parameters.
     */
    @Test
    public void testSetAvoidClansMatch() {
        System.out.println("setAvoidClansMatch");
        boolean mAvoidClansMatch = false;
        Parameters instance = new Parameters();
        instance.setAvoidClansMatch(mAvoidClansMatch);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTeamMatesClansNumber method, of class Parameters.
     */
    @Test
    public void testGetTeamMatesClansNumber() {
        System.out.println("getTeamMatesClansNumber");
        Parameters instance = new Parameters();
        int expResult = 0;
        int result = instance.getTeamMatesClansNumber();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTeamMatesClansNumber method, of class Parameters.
     */
    @Test
    public void testSetTeamMatesClansNumber() {
        System.out.println("setTeamMatesClansNumber");
        int mTeamMatesClansNumber = 0;
        Parameters instance = new Parameters();
        instance.setTeamMatesClansNumber(mTeamMatesClansNumber);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isUseColor method, of class Parameters.
     */
    @Test
    public void testIsUseColor() {
        System.out.println("isUseColor");
        Parameters instance = new Parameters();
        boolean expResult = false;
        boolean result = instance.isUseColor();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setUseColor method, of class Parameters.
     */
    @Test
    public void testSetUseColor() {
        System.out.println("setUseColor");
        boolean useColor = false;
        Parameters instance = new Parameters();
        instance.setUseColor(useColor);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isUseImage method, of class Parameters.
     */
    @Test
    public void testIsUseImage() {
        System.out.println("isUseImage");
        Parameters instance = new Parameters();
        boolean expResult = false;
        boolean result = instance.isUseImage();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setUseImage method, of class Parameters.
     */
    @Test
    public void testSetUseImage() {
        System.out.println("setUseImage");
        boolean useImage = false;
        Parameters instance = new Parameters();
        instance.setUseImage(useImage);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
