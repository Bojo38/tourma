/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import java.awt.image.RenderedImage;
import java.io.File;
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
public class TournamentNGTest {
    
    public TournamentNGTest() {
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
     * Test of resetTournament method, of class Tournament.
     */
    @Test
    public void testResetTournament() {
        System.out.println("resetTournament");
        Tournament expResult = null;
        Tournament result = Tournament.resetTournament();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTournament method, of class Tournament.
     */
    @Test
    public void testGetTournament() {
        System.out.println("getTournament");
        Tournament expResult = null;
        Tournament result = Tournament.getTournament();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }



    /**
     * Test of getClansCount method, of class Tournament.
     */
    @Test
    public void testGetClansCount() {
        System.out.println("getClansCount");
        Tournament instance = null;
        int expResult = 0;
        int result = instance.getClansCount();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getClan method, of class Tournament.
     */
    @Test
    public void testGetClan() {
        System.out.println("getClan");
        int i = 0;
        Tournament instance = null;
        Clan expResult = null;
        Clan result = instance.getClan(i);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addClan method, of class Tournament.
     */
    @Test
    public void testAddClan() {
        System.out.println("addClan");
        Clan c = null;
        Tournament instance = null;
        instance.addClan(c);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeClan method, of class Tournament.
     */
    @Test
    public void testRemoveClan_Clan() {
        System.out.println("removeClan");
        Clan c = null;
        Tournament instance = null;
        instance.removeClan(c);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeClan method, of class Tournament.
     */
    @Test
    public void testRemoveClan_int() {
        System.out.println("removeClan");
        int c = 0;
        Tournament instance = null;
        instance.removeClan(c);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of clearClans method, of class Tournament.
     */
    @Test
    public void testClearClans() {
        System.out.println("clearClans");
        Tournament instance = null;
        instance.clearClans();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCategoriesCount method, of class Tournament.
     */
    @Test
    public void testGetCategoriesCount() {
        System.out.println("getCategoriesCount");
        Tournament instance = null;
        int expResult = 0;
        int result = instance.getCategoriesCount();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCategory method, of class Tournament.
     */
    @Test
    public void testGetCategory() {
        System.out.println("getCategory");
        int i = 0;
        Tournament instance = null;
        Category expResult = null;
        Category result = instance.getCategory(i);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addCategory method, of class Tournament.
     */
    @Test
    public void testAddCategory() {
        System.out.println("addCategory");
        Category c = null;
        Tournament instance = null;
        instance.addCategory(c);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeCategory method, of class Tournament.
     */
    @Test
    public void testRemoveCategory_Category() {
        System.out.println("removeCategory");
        Category c = null;
        Tournament instance = null;
        instance.removeCategory(c);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeCategory method, of class Tournament.
     */
    @Test
    public void testRemoveCategory_int() {
        System.out.println("removeCategory");
        int c = 0;
        Tournament instance = null;
        instance.removeCategory(c);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of clearCategories method, of class Tournament.
     */
    @Test
    public void testClearCategories() {
        System.out.println("clearCategories");
        Tournament instance = null;
        instance.clearCategories();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTeamsCount method, of class Tournament.
     */
    @Test
    public void testGetTeamsCount() {
        System.out.println("getTeamsCount");
        Tournament instance = null;
        int expResult = 0;
        int result = instance.getTeamsCount();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTeam method, of class Tournament.
     */
    @Test
    public void testGetTeam() {
        System.out.println("getTeam");
        int i = 0;
        Tournament instance = null;
        Team expResult = null;
        Team result = instance.getTeam(i);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addTeam method, of class Tournament.
     */
    @Test
    public void testAddTeam() {
        System.out.println("addTeam");
        Team c = null;
        Tournament instance = null;
        instance.addTeam(c);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeTeam method, of class Tournament.
     */
    @Test
    public void testRemoveTeam_Team() {
        System.out.println("removeTeam");
        Team c = null;
        Tournament instance = null;
        instance.removeTeam(c);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of containsTeam method, of class Tournament.
     */
    @Test
    public void testContainsTeam() {
        System.out.println("containsTeam");
        Team t = null;
        Tournament instance = null;
        boolean expResult = false;
        boolean result = instance.containsTeam(t);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeTeam method, of class Tournament.
     */
    @Test
    public void testRemoveTeam_int() {
        System.out.println("removeTeam");
        int c = 0;
        Tournament instance = null;
        instance.removeTeam(c);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of clearTeams method, of class Tournament.
     */
    @Test
    public void testClearTeams() {
        System.out.println("clearTeams");
        Tournament instance = null;
        instance.clearTeams();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDisplayClans method, of class Tournament.
     */
    @Test
    public void testGetDisplayClans() {
        System.out.println("getDisplayClans");
        Tournament instance = null;
        ArrayList expResult = null;
        ArrayList result = instance.getDisplayClans();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDisplayCategories method, of class Tournament.
     */
    @Test
    public void testGetDisplayCategories() {
        System.out.println("getDisplayCategories");
        Tournament instance = null;
        ArrayList expResult = null;
        ArrayList result = instance.getDisplayCategories();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCoach method, of class Tournament.
     */
    @Test
    public void testGetCoach_String() {
        System.out.println("getCoach");
        String input = "";
        Tournament instance = null;
        Coach expResult = null;
        Coach result = instance.getCoach(input);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getParams method, of class Tournament.
     */
    @Test
    public void testGetParams() {
        System.out.println("getParams");
        Tournament instance = null;
        Parameters expResult = null;
        Parameters result = instance.getParams();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of containsCoach method, of class Tournament.
     */
    @Test
    public void testContainsCoach() {
        System.out.println("containsCoach");
        Coach c = null;
        Tournament instance = null;
        boolean expResult = false;
        boolean result = instance.containsCoach(c);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeCoach method, of class Tournament.
     */
    @Test
    public void testRemoveCoach_Coach() {
        System.out.println("removeCoach");
        Coach i = null;
        Tournament instance = null;
        instance.removeCoach(i);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeCoach method, of class Tournament.
     */
    @Test
    public void testRemoveCoach_int() {
        System.out.println("removeCoach");
        int i = 0;
        Tournament instance = null;
        instance.removeCoach(i);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of clearCoachs method, of class Tournament.
     */
    @Test
    public void testClearCoachs() {
        System.out.println("clearCoachs");
        Tournament instance = null;
        instance.clearCoachs();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addCoach method, of class Tournament.
     */
    @Test
    public void testAddCoach() {
        System.out.println("addCoach");
        Coach c = null;
        Tournament instance = null;
        instance.addCoach(c);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCoachsCount method, of class Tournament.
     */
    @Test
    public void testGetCoachsCount() {
        System.out.println("getCoachsCount");
        Tournament instance = null;
        int expResult = 0;
        int result = instance.getCoachsCount();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCoach method, of class Tournament.
     */
    @Test
    public void testGetCoach_int() {
        System.out.println("getCoach");
        int i = 0;
        Tournament instance = null;
        Coach expResult = null;
        Coach result = instance.getCoach(i);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addGroup method, of class Tournament.
     */
    @Test
    public void testAddGroup() {
        System.out.println("addGroup");
        Group g = null;
        Tournament instance = null;
        instance.addGroup(g);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeGroup method, of class Tournament.
     */
    @Test
    public void testRemoveGroup_Group() {
        System.out.println("removeGroup");
        Group g = null;
        Tournament instance = null;
        instance.removeGroup(g);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeGroup method, of class Tournament.
     */
    @Test
    public void testRemoveGroup_int() {
        System.out.println("removeGroup");
        int g = 0;
        Tournament instance = null;
        instance.removeGroup(g);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of containsGroups method, of class Tournament.
     */
    @Test
    public void testContainsGroups() {
        System.out.println("containsGroups");
        Group g = null;
        Tournament instance = null;
        boolean expResult = false;
        boolean result = instance.containsGroups(g);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of clearGroups method, of class Tournament.
     */
    @Test
    public void testClearGroups() {
        System.out.println("clearGroups");
        Tournament instance = null;
        instance.clearGroups();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGroup method, of class Tournament.
     */
    @Test
    public void testGetGroup() {
        System.out.println("getGroup");
        int i = 0;
        Tournament instance = null;
        Group expResult = null;
        Group result = instance.getGroup(i);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGroupsCount method, of class Tournament.
     */
    @Test
    public void testGetGroupsCount() {
        System.out.println("getGroupsCount");
        Tournament instance = null;
        int expResult = 0;
        int result = instance.getGroupsCount();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRoundsCount method, of class Tournament.
     */
    @Test
    public void testGetRoundsCount() {
        System.out.println("getRoundsCount");
        Tournament instance = null;
        int expResult = 0;
        int result = instance.getRoundsCount();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRound method, of class Tournament.
     */
    @Test
    public void testGetRound() {
        System.out.println("getRound");
        int i = 0;
        Tournament instance = null;
        Round expResult = null;
        Round result = instance.getRound(i);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of clearRounds method, of class Tournament.
     */
    @Test
    public void testClearRounds() {
        System.out.println("clearRounds");
        Tournament instance = null;
        instance.clearRounds();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addRound method, of class Tournament.
     */
    @Test
    public void testAddRound() {
        System.out.println("addRound");
        Round r = null;
        Tournament instance = null;
        instance.addRound(r);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of saveXML method, of class Tournament.
     */
    @Test
    public void testSaveXML_File() {
        System.out.println("saveXML");
        File file = null;
        Tournament instance = null;
        instance.saveXML(file);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of saveXML method, of class Tournament.
     */
    @Test
    public void testSaveXML_File_boolean() {
        System.out.println("saveXML");
        File file = null;
        boolean withRanking = false;
        Tournament instance = null;
        instance.saveXML(file, withRanking);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of generateCSVRanking method, of class Tournament.
     */
    @Test
    public void testGenerateCSVRanking() {
        System.out.println("generateCSVRanking");
        int round = 0;
        boolean withRoster = false;
        boolean withNaf = false;
        Tournament instance = null;
        String expResult = "";
        String result = instance.generateCSVRanking(round, withRoster, withNaf);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of generateRankingQRCode method, of class Tournament.
     */
    @Test
    public void testGenerateRankingQRCode() {
        System.out.println("generateRankingQRCode");
        int round = 0;
        Tournament instance = null;
        RenderedImage expResult = null;
        RenderedImage result = instance.generateRankingQRCode(round);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of exportFullFBB method, of class Tournament.
     */
    @Test
    public void testExportFullFBB() {
        System.out.println("exportFullFBB");
        File file = null;
        Tournament instance = null;
        instance.exportFullFBB(file);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of exportFBB method, of class Tournament.
     */
    @Test
    public void testExportFBB() {
        System.out.println("exportFBB");
        File file = null;
        Tournament instance = null;
        instance.exportFBB(file);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of exportNAF method, of class Tournament.
     */
    @Test
    public void testExportNAF() {
        System.out.println("exportNAF");
        File file = null;
        Tournament instance = null;
        instance.exportNAF(file);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }



    /**
     * Test of loadXMLv3 method, of class Tournament.
     */
    @Test
    public void testLoadXMLv3() {
        System.out.println("loadXMLv3");
        Element racine = null;
        Tournament instance = null;
        instance.loadXMLv3(racine);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of loadXML method, of class Tournament.
     */
    @Test
    public void testLoadXML() {
        System.out.println("loadXML");
        File file = null;
        Tournament instance = null;
        instance.loadXML(file);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getActiveCoachNumber method, of class Tournament.
     */
    @Test
    public void testGetActiveCoachNumber() {
        System.out.println("getActiveCoachNumber");
        Tournament instance = null;
        int expResult = 0;
        int result = instance.getActiveCoachNumber();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getActiveCoaches method, of class Tournament.
     */
    @Test
    public void testGetActiveCoaches() {
        System.out.println("getActiveCoaches");
        Tournament instance = null;
        ArrayList expResult = null;
        ArrayList result = instance.getActiveCoaches();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPoolCount method, of class Tournament.
     */
    @Test
    public void testGetPoolCount() {
        System.out.println("getPoolCount");
        Tournament instance = null;
        int expResult = 0;
        int result = instance.getPoolCount();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addPool method, of class Tournament.
     */
    @Test
    public void testAddPool() {
        System.out.println("addPool");
        Pool p = null;
        Tournament instance = null;
        instance.addPool(p);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of clearPools method, of class Tournament.
     */
    @Test
    public void testClearPools() {
        System.out.println("clearPools");
        Tournament instance = null;
        instance.clearPools();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPool method, of class Tournament.
     */
    @Test
    public void testGetPool() {
        System.out.println("getPool");
        int i = 0;
        Tournament instance = null;
        Pool expResult = null;
        Pool result = instance.getPool(i);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCoachCount method, of class Tournament.
     */
    @Test
    public void testGetCoachCount() {
        System.out.println("getCoachCount");
        Tournament instance = null;
        int expResult = 0;
        int result = instance.getCoachCount();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setParams method, of class Tournament.
     */
    @Test
    public void testSetParams() {
        System.out.println("setParams");
        Parameters mParams = null;
        Tournament instance = null;
        instance.setParams(mParams);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isRoundRobin method, of class Tournament.
     */
    @Test
    public void testIsRoundRobin() {
        System.out.println("isRoundRobin");
        Tournament instance = null;
        boolean expResult = false;
        boolean result = instance.isRoundRobin();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setRoundRobin method, of class Tournament.
     */
    @Test
    public void testSetRoundRobin() {
        System.out.println("setRoundRobin");
        boolean mRoundRobin = false;
        Tournament instance = null;
        instance.setRoundRobin(mRoundRobin);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of indexOfRound method, of class Tournament.
     */
    @Test
    public void testIndexOfRound() {
        System.out.println("indexOfRound");
        Round r = null;
        Tournament instance = null;
        int expResult = 0;
        int result = instance.indexOfRound(r);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeRound method, of class Tournament.
     */
    @Test
    public void testRemoveRound_Round() {
        System.out.println("removeRound");
        Round r = null;
        Tournament instance = null;
        instance.removeRound(r);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeRound method, of class Tournament.
     */
    @Test
    public void testRemoveRound_int() {
        System.out.println("removeRound");
        int r = 0;
        Tournament instance = null;
        instance.removeRound(r);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
