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
import org.testng.Assert;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;
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
        Tournament.resetTournament();
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
        Tournament tour = Tournament.getTournament();
        tour.loadXML(new File("./test/tournament.xml"));

        Assert.assertTrue(tour.getCoachsCount() > 0);
        tour = Tournament.resetTournament();
        Assert.assertTrue(tour.getCoachsCount() == 0);
    }

    /**
     * Test of getTournament method, of class Tournament.
     */
    @Test
    public void testGetTournament() {
        System.out.println("getTournament");
        Tournament tour = Tournament.getTournament();
        Assert.assertNotNull(tour);
    }

    /**
     * Test of getClansCount method, of class Tournament.
     */
    @Test
    public void testGetClansCount() {
        System.out.println("getClansCount");
        Tournament tour = Tournament.getTournament();
        Assert.assertEquals(tour.getClansCount(), 1);
        tour.loadXML(new File("./test/clan.xml"));

        Assert.assertTrue(tour.getClansCount() > 1);
    }

    /**
     * Test of getClan method, of class Tournament.
     */
    @Test
    public void testGetClan() {
        System.out.println("getClan");
        Tournament tour = Tournament.getTournament();
        Assert.assertEquals(tour.getClansCount(), 1);
        tour.loadXML(new File("./test/clan.xml"));
        Assert.assertTrue(tour.getClansCount() > 1);

        for (int i = 0; i < tour.getClansCount(); i++) {
            Assert.assertNotNull(tour.getClan(i));
        }
    }

    /**
     * Test of addClan method, of class Tournament.
     */
    @Test
    public void testAddClan() {
        System.out.println("addClan");
        Tournament tour = Tournament.getTournament();
        Assert.assertEquals(tour.getClansCount(), 1);
        tour.loadXML(new File("./test/clan.xml"));
        Assert.assertTrue(tour.getClansCount() > 1);
        int nb = tour.getClansCount();
        Clan cl = new Clan("Bidon");
        tour.addClan(cl);
        assertEquals(tour.getClansCount(), nb + 1);
    }

    /**
     * Test of removeClan method, of class Tournament.
     */
    @Test
    public void testRemoveClan_Clan() {
        System.out.println("removeClan");
        Tournament tour = Tournament.getTournament();
        Assert.assertEquals(tour.getClansCount(), 1);
        tour.loadXML(new File("./test/clan.xml"));
        Assert.assertTrue(tour.getClansCount() > 1);
        int nb = tour.getClansCount();
        Clan cl = new Clan("Bidon");
        tour.addClan(cl);
        assertEquals(tour.getClansCount(), nb + 1);
        tour.removeClan(cl);
        assertEquals(tour.getClansCount(), nb);
    }

    /**
     * Test of removeClan method, of class Tournament.
     */
    @Test
    public void testRemoveClan_int() {
        System.out.println("removeClan");
        Tournament tour = Tournament.getTournament();
        Assert.assertEquals(tour.getClansCount(), 1);
        tour.loadXML(new File("./test/clan.xml"));
        Assert.assertTrue(tour.getClansCount() > 1);
        int nb = tour.getClansCount();
        Clan cl = new Clan("Bidon");
        tour.addClan(cl);
        assertEquals(tour.getClansCount(), nb + 1);
        tour.removeClan(nb);
        assertEquals(tour.getClansCount(), nb);
    }

    /**
     * Test of clearClans method, of class Tournament.
     */
    @Test
    public void testClearClans() {
        System.out.println("clearClans");
        Tournament tour = Tournament.getTournament();
        Assert.assertEquals(tour.getClansCount(), 1);
        tour.loadXML(new File("./test/clan.xml"));
        Assert.assertTrue(tour.getClansCount() > 1);
        int nb = tour.getClansCount();
        tour.clearClans();
        assertEquals(tour.getClansCount(), 0);
    }

    /**
     * Test of getCategoriesCount method, of class Tournament.
     */
    @Test
    public void testGetCategoriesCount() {
        System.out.println("getCategoriesCount");
        Tournament tour = Tournament.getTournament();
        Assert.assertEquals(tour.getCategoriesCount(), 1);
        tour.loadXML(new File("./test/category.xml"));

        Assert.assertTrue(tour.getCategoriesCount() > 1);
    }

    /**
     * Test of getCategory method, of class Tournament.
     */
    @Test
    public void testGetCategory() {
        System.out.println("getCategory");
        Tournament tour = Tournament.getTournament();
        Assert.assertEquals(tour.getCategoriesCount(), 1);
        tour.loadXML(new File("./test/category.xml"));
        Assert.assertTrue(tour.getCategoriesCount() > 1);

        for (int i = 0; i < tour.getCategoriesCount(); i++) {
            Assert.assertNotNull(tour.getCategory(i));
        }
    }

    /**
     * Test of addCategory method, of class Tournament.
     */
    @Test
    public void testAddCategory() {
        System.out.println("addCategory");
        Tournament tour = Tournament.getTournament();
        Assert.assertEquals(tour.getCategoriesCount(), 1);
        tour.loadXML(new File("./test/category.xml"));
        Assert.assertTrue(tour.getCategoriesCount() > 1);
        int nb = tour.getCategoriesCount();
        Category cl = new Category("Bidon");
        tour.addCategory(cl);
        assertEquals(tour.getCategoriesCount(), nb + 1);

    }

    /**
     * Test of removeCategory method, of class Tournament.
     */
    @Test
    public void testRemoveCategory_Category() {
        System.out.println("removeCategory");
         Tournament tour = Tournament.getTournament();
        Assert.assertEquals(tour.getCategoriesCount(), 1);
        tour.loadXML(new File("./test/category.xml"));
        Assert.assertTrue(tour.getCategoriesCount() > 1);
        int nb = tour.getCategoriesCount();
        tour.removeCategory(tour.getCategory(0));
        assertEquals(tour.getCategoriesCount(), nb - 1);
    }

    /**
     * Test of removeCategory method, of class Tournament.
     */
    @Test
    public void testRemoveCategory_int() {
        System.out.println("removeCategory");
         Tournament tour = Tournament.getTournament();
        Assert.assertEquals(tour.getCategoriesCount(), 1);
        tour.loadXML(new File("./test/category.xml"));
        Assert.assertTrue(tour.getCategoriesCount() > 1);
        int nb = tour.getCategoriesCount();
        tour.removeCategory(0);
        assertEquals(tour.getCategoriesCount(), nb - 1);
    }

    /**
     * Test of clearCategories method, of class Tournament.
     */
    @Test
    public void testClearCategories() {
        System.out.println("clearCategories");
        Tournament tour = Tournament.getTournament();
        Assert.assertEquals(tour.getCategoriesCount(), 1);
        tour.loadXML(new File("./test/category.xml"));
        Assert.assertTrue(tour.getCategoriesCount() > 1);
        int nb = tour.getCategoriesCount();
        tour.clearCategories();
        assertEquals(tour.getCategoriesCount(), 0);
    }

    /**
     * Test of getTeamsCount method, of class Tournament.
     */
    @Test
    public void testGetTeamsCount() {
        System.out.println("getTeamsCount");
        Tournament tour = Tournament.getTournament();
        Assert.assertEquals(tour.getTeamsCount(), 0);
        tour.loadXML(new File("./test/team.xml"));

        Assert.assertTrue(tour.getTeamsCount() > 1);
    }

    /**
     * Test of getTeam method, of class Tournament.
     */
    @Test
    public void testGetTeam() {
        System.out.println("getTeam");
        Tournament tour = Tournament.getTournament();
        Assert.assertEquals(tour.getTeamsCount(), 0);
        tour.loadXML(new File("./test/team.xml"));
        Assert.assertTrue(tour.getTeamsCount() > 1);

        for (int i = 0; i < tour.getTeamsCount(); i++) {
            Assert.assertNotNull(tour.getTeam(i));
        }
    }

    /**
     * Test of addTeam method, of class Tournament.
     */
    @Test
    public void testAddTeam() {
        System.out.println("addTeam");
        Tournament tour = Tournament.getTournament();
        Assert.assertEquals(tour.getTeamsCount(), 0);
        tour.loadXML(new File("./test/team.xml"));
        Assert.assertTrue(tour.getTeamsCount() > 1);
        int nb = tour.getTeamsCount();
        Team cl = new Team();
        tour.addTeam(cl);
        assertEquals(tour.getTeamsCount(), nb + 1);
    }

    /**
     * Test of removeTeam method, of class Tournament.
     */
    @Test
    public void testRemoveTeam_Team() {
        System.out.println("removeTeam");
        Tournament tour = Tournament.getTournament();
        Assert.assertEquals(tour.getTeamsCount(), 0);
        tour.loadXML(new File("./test/team.xml"));
        Assert.assertTrue(tour.getTeamsCount() > 1);
        int nb = tour.getTeamsCount();
        tour.removeTeam(tour.getTeam(0));
        assertEquals(tour.getTeamsCount(), nb - 1);
    }

    /**
     * Test of removeTeam method, of class Tournament.
     */
    @Test
    public void testRemoveTeam_int() {
        System.out.println("removeTeam");
      Tournament tour = Tournament.getTournament();
        Assert.assertEquals(tour.getTeamsCount(), 0);
        tour.loadXML(new File("./test/team.xml"));
        Assert.assertTrue(tour.getTeamsCount() > 1);
        int nb = tour.getTeamsCount();
        tour.removeTeam(0);
        assertEquals(tour.getTeamsCount(), nb - 1);
    }

    /**
     * Test of clearTeams method, of class Tournament.
     */
    @Test
    public void testClearTeams() {
        System.out.println("clearTeams");
        Tournament tour = Tournament.getTournament();
        Assert.assertEquals(tour.getTeamsCount(), 0);
        tour.loadXML(new File("./test/team.xml"));
        Assert.assertTrue(tour.getTeamsCount() > 1);
        int nb = tour.getTeamsCount();
        tour.clearTeams();
        assertEquals(tour.getTeamsCount(), 0);
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
        Tournament.getTournament().loadXML(new File("./test/coach.xml"));
        Tournament tour = Tournament.getTournament();
        for (int i = 0; i < tour.getCoachsCount(); i++) {
            Coach cl = tour.getCoach(i);
            assertEquals(tour.getCoach(cl.getName()), cl);
        }
        assertNull(tour.getCoach("ABC"));
    }

    /**
     * Test of getParams method, of class Tournament.
     */
    @Test
    public void testGetParams() {
        System.out.println("getParams");
        Tournament.getTournament().loadXML(new File("./test/params.xml"));
        Tournament instance = Tournament.getTournament();
        Parameters expResult = new Parameters();
        Assert.assertNotEquals(instance.getParams(),expResult);
        instance.setParams(expResult);
        Parameters result = instance.getParams();
        assertTrue(result== expResult);
    }

    /**
     * Test of removeCoach method, of class Tournament.
     */
    @Test
    public void testRemoveCoach_Coach() {
        System.out.println("removeCoach");
        Tournament tour = Tournament.getTournament();
        Assert.assertEquals(tour.getCoachsCount(), 0);
        tour.loadXML(new File("./test/coach.xml"));
        Assert.assertTrue(tour.getCoachsCount() > 1);
        int nb = tour.getCoachsCount();
        tour.removeCoach(tour.getCoach(0));
        assertEquals(tour.getCoachsCount(), nb - 1);
    }

    /**
     * Test of removeCoach method, of class Tournament.
     */
    @Test
    public void testRemoveCoach_int() {
        System.out.println("removeCoach");
       Tournament tour = Tournament.getTournament();
        Assert.assertEquals(tour.getCoachsCount(), 0);
        tour.loadXML(new File("./test/coach.xml"));
        Assert.assertTrue(tour.getCoachsCount() > 1);
        int nb = tour.getCoachsCount();
        tour.removeCoach(0);
        assertEquals(tour.getCoachsCount(), nb - 1);
    }

    /**
     * Test of clearCoachs method, of class Tournament.
     */
    @Test
    public void testClearCoachs() {
        System.out.println("clearCoachs");
        Tournament tour = Tournament.getTournament();
        Assert.assertEquals(tour.getCoachsCount(), 0);
        tour.loadXML(new File("./test/coach.xml"));
        Assert.assertTrue(tour.getCoachsCount() > 1);
        int nb = tour.getCoachsCount();
        tour.clearCoachs();
        assertEquals(tour.getCoachsCount(), 0);
    }

    /**
     * Test of addCoach method, of class Tournament.
     */
    @Test
    public void testAddCoach() {
        System.out.println("addCoach");
        Tournament tour = Tournament.getTournament();
        Assert.assertEquals(tour.getCoachsCount(), 0);
        tour.loadXML(new File("./test/coach.xml"));
        Assert.assertTrue(tour.getCoachsCount() > 1);
        int nb = tour.getCoachsCount();
        Coach cl = new Coach("Bidon");
        tour.addCoach(cl);
        assertEquals(tour.getCoachsCount(), nb + 1);
    }

    /**
     * Test of getCoachsCount method, of class Tournament.
     */
    @Test
    public void testGetCoachsCount() {
        System.out.println("getCoachsCount");
         Tournament tour = Tournament.getTournament();
        Assert.assertEquals(tour.getCoachsCount(), 0);
        tour.loadXML(new File("./test/coach.xml"));
        Assert.assertTrue(tour.getCoachsCount() > 1);
        int nb = tour.getCoachsCount();
        Coach cl = new Coach("Bidon");
        tour.addCoach(cl);
        assertEquals(tour.getCoachsCount(), nb + 1);
    }

    /**
     * Test of getCoach method, of class Tournament.
     */
    @Test
    public void testGetCoach_int() {
        System.out.println("getCoach");
        Tournament.getTournament().loadXML(new File("./test/coach.xml"));
        Tournament tour = Tournament.getTournament();
        for (int i = 0; i < tour.getCoachsCount(); i++) {
            Assert.assertNotNull(tour.getCoach(i));

        }
    }

    /**
     * Test of addGroup method, of class Tournament.
     */
    @Test
    public void testAddGroup() {
        System.out.println("addGroup");
        Tournament tour = Tournament.getTournament();
        Assert.assertEquals(tour.getGroupsCount(), 1);
        tour.loadXML(new File("./test/group.xml"));
        Assert.assertTrue(tour.getGroupsCount() > 1);
        int nb = tour.getGroupsCount();
        Group cl = new Group("Bidon");
        tour.addGroup(cl);
        assertEquals(tour.getGroupsCount(), nb + 1);
    }

    /**
     * Test of removeGroup method, of class Tournament.
     */
    @Test
    public void testRemoveGroup_Group() {
        System.out.println("removeGroup");
       Tournament tour = Tournament.getTournament();
        Assert.assertEquals(tour.getGroupsCount(), 1);
        tour.loadXML(new File("./test/group.xml"));
        Assert.assertTrue(tour.getGroupsCount() > 1);
        int nb = tour.getGroupsCount();
        tour.removeGroup(tour.getGroup(0));
        assertEquals(tour.getGroupsCount(), nb - 1);
    }

    /**
     * Test of removeGroup method, of class Tournament.
     */
    @Test
    public void testRemoveGroup_int() {
        System.out.println("removeGroup");
        Tournament tour = Tournament.getTournament();
        Assert.assertEquals(tour.getGroupsCount(), 1);
        tour.loadXML(new File("./test/group.xml"));
        Assert.assertTrue(tour.getGroupsCount() > 1);
        int nb = tour.getGroupsCount();
        tour.removeGroup(0);
        assertEquals(tour.getGroupsCount(), nb - 1);
    }

    /**
     * Test of containsGroup method, of class Tournament.
     */
    @Test
    public void testContainsGroups() {
        System.out.println("containsGroups");
        Tournament.getTournament().loadXML(new File("./test/group.xml"));
        Tournament tour = Tournament.getTournament();
        for (int i = 0; i < tour.getGroupsCount(); i++) {
            boolean result = tour.containsGroup(tour.getGroup(i));
            assertEquals(result, true);
        }
        assertFalse(tour.containsGroup(new Group("ABC")));
    }

    /**
     * Test of clearGroups method, of class Tournament.
     */
    @Test
    public void testClearGroups() {
        System.out.println("clearGroups");
        Tournament tour = Tournament.getTournament();
        Assert.assertEquals(tour.getGroupsCount(), 1);
        tour.loadXML(new File("./test/group.xml"));
        Assert.assertTrue(tour.getGroupsCount() > 1);
        int nb = tour.getGroupsCount();
        tour.clearGroups();
        assertEquals(tour.getGroupsCount(), 0);
    }

    /**
     * Test of getGroup method, of class Tournament.
     */
    @Test
    public void testGetGroup() {
        System.out.println("getGroup");
       Tournament.getTournament().loadXML(new File("./test/group.xml"));
        Tournament tour = Tournament.getTournament();
        for (int i = 0; i < tour.getGroupsCount(); i++) {
            Assert.assertNotNull(tour.getGroup(i));

        }
    }

    /**
     * Test of getGroupsCount method, of class Tournament.
     */
    @Test
    public void testGetGroupsCount() {
        System.out.println("getGroupsCount");
        Tournament tour = Tournament.getTournament();
        Assert.assertEquals(tour.getGroupsCount(), 1);
        tour.loadXML(new File("./test/group.xml"));

        Assert.assertTrue(tour.getGroupsCount() > 1);
    }

    /**
     * Test of getRoundsCount method, of class Tournament.
     */
    @Test
    public void testGetRoundsCount() {
        System.out.println("getRoundsCount");
        Tournament tour = Tournament.getTournament();
        Assert.assertEquals(tour.getRoundsCount(), 0);
        tour.loadXML(new File("./test/tournament.xml"));

        Assert.assertTrue(tour.getRoundsCount() > 1);
    }

    /**
     * Test of getRound method, of class Tournament.
     */
    @Test
    public void testGetRound() {
        System.out.println("getRound");
        Tournament tour = Tournament.getTournament();
        Assert.assertEquals(tour.getTeamsCount(), 0);
        tour.loadXML(new File("./test/tournament.xml"));
        Assert.assertTrue(tour.getRoundsCount() > 1);

        for (int i = 0; i < tour.getRoundsCount(); i++) {
            Assert.assertNotNull(tour.getRound(i));
        }
    }

    /**
     * Test of clearRounds method, of class Tournament.
     */
    @Test
    public void testClearRounds() {
        System.out.println("clearRounds");
        Tournament tour = Tournament.getTournament();
        Assert.assertEquals(tour.getRoundsCount(), 0);
        tour.loadXML(new File("./test/tournament.xml"));
        Assert.assertTrue(tour.getRoundsCount() > 1);
        int nb = tour.getRoundsCount();
        tour.clearRounds();
        assertEquals(tour.getRoundsCount(), 0);
    }

    /**
     * Test of addRound method, of class Tournament.
     */
    @Test
    public void testAddRound() {
        System.out.println("addRound");
        Tournament tour = Tournament.getTournament();
        Assert.assertEquals(tour.getRoundsCount(), 0);
        tour.loadXML(new File("./test/tournament.xml"));
        Assert.assertTrue(tour.getRoundsCount() > 1);
        int nb = tour.getRoundsCount();
        Round cl = new Round();
        tour.addRound(cl);
        assertEquals(tour.getRoundsCount(), nb + 1);
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
        Tournament tour = Tournament.getTournament();
        Assert.assertEquals(tour.getPoolCount(), 0);
        tour.loadXML(new File("./test/pools.xml"));

        Assert.assertTrue(tour.getPoolCount() > 1);
    }

    /**
     * Test of addPool method, of class Tournament.
     */
    @Test
    public void testAddPool() {
        System.out.println("addPool");
        Tournament tour = Tournament.getTournament();
        Assert.assertEquals(tour.getPoolCount(), 0);
        tour.loadXML(new File("./test/pools.xml"));
        Assert.assertTrue(tour.getPoolCount() > 1);
        int nb = tour.getPoolCount();
        Pool cl = new Pool();
        tour.addPool(cl);
        assertEquals(tour.getPoolCount(), nb + 1);
    }

    /**
     * Test of clearPools method, of class Tournament.
     */
    @Test
    public void testClearPools() {
        System.out.println("clearPools");
        Tournament tour = Tournament.getTournament();
        Assert.assertEquals(tour.getPoolCount(), 0);
        tour.loadXML(new File("./test/pools.xml"));
        Assert.assertTrue(tour.getPoolCount() > 1);
        int nb = tour.getPoolCount();
        tour.clearPools();
        assertEquals(tour.getPoolCount(), 0);
    }

    /**
     * Test of getPool method, of class Tournament.
     */
    @Test
    public void testGetPool() {
        System.out.println("getPool");
         Tournament.getTournament().loadXML(new File("./test/pools.xml"));
        Tournament tour = Tournament.getTournament();
        for (int i = 0; i < tour.getPoolCount(); i++) {
            Assert.assertNotNull(tour.getPool(i));

        }
    }

 
    /**
     * Test of setParams method, of class Tournament.
     */
    @Test
    public void testSetParams() {
        System.out.println("setParams");
         Tournament.getTournament().loadXML(new File("./test/params.xml"));
        Tournament instance = Tournament.getTournament();
        Parameters expResult = new Parameters();
        Assert.assertNotEquals(instance.getParams(),expResult);
        instance.setParams(expResult);
        Parameters result = instance.getParams();
        assertTrue(result== expResult);
    }

    /**
     * Test of isRoundRobin method, of class Tournament.
     */
    @Test
    public void testIsRoundRobin() {
        System.out.println("isRoundRobin");
       Tournament.getTournament().loadXML(new File("./test/params.xml"));
        Tournament instance = Tournament.getTournament();
        boolean expResult = false;
        instance.setRoundRobin(expResult);
        boolean result = instance.isRoundRobin();
        assertTrue(result== expResult);
        expResult = true;
        instance.setRoundRobin(expResult);
        result = instance.isRoundRobin();
        assertTrue(result== expResult);
    }

    /**
     * Test of setRoundRobin method, of class Tournament.
     */
    @Test
    public void testSetRoundRobin() {
        System.out.println("setRoundRobin");
        Tournament.getTournament().loadXML(new File("./test/params.xml"));
        Tournament instance = Tournament.getTournament();
        boolean expResult = false;
        instance.setRoundRobin(expResult);
        boolean result = instance.isRoundRobin();
        assertTrue(result== expResult);
        expResult = true;
        instance.setRoundRobin(expResult);
        result = instance.isRoundRobin();
        assertTrue(result== expResult);
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
        Tournament tour = Tournament.getTournament();
        Assert.assertEquals(tour.getRoundsCount(), 0);
        tour.loadXML(new File("./test/tournament.xml"));
        Assert.assertTrue(tour.getRoundsCount() > 1);
        int nb = tour.getRoundsCount();
        tour.removeRound(tour.getRound(0));
        assertEquals(tour.getRoundsCount(), nb - 1);
    }

    /**
     * Test of removeRound method, of class Tournament.
     */
    @Test
    public void testRemoveRound_int() {
        System.out.println("removeRound");
      Tournament tour = Tournament.getTournament();
        Assert.assertEquals(tour.getRoundsCount(), 0);
        tour.loadXML(new File("./test/tournament.xml"));
        Assert.assertTrue(tour.getRoundsCount() > 1);
        int nb = tour.getRoundsCount();
        tour.removeRound(0);
        assertEquals(tour.getRoundsCount(), nb - 1);
    }

    /**
     * Test of getRoundIndex method, of class Tournament.
     */
    @Test
    public void testGetRoundIndex() {
        System.out.println("getRoundIndex");
        Tournament tour = Tournament.getTournament();
        Assert.assertEquals(tour.getTeamsCount(), 0);
        tour.loadXML(new File("./test/tournament.xml"));
        Assert.assertTrue(tour.getRoundsCount() > 1);

        for (int i = 0; i < tour.getRoundsCount(); i++) {
            Round r=tour.getRound(i);
            assertEquals(tour.getRoundIndex(r), i);
        }
    }

    /**
     * Test of getClan method, of class Tournament.
     */
    @Test
    public void testGetClan_int() {
        System.out.println("getClan");
        Tournament.getTournament().loadXML(new File("./test/clan.xml"));
        Tournament tour = Tournament.getTournament();
        for (int i = 0; i < tour.getClansCount(); i++) {
            Assert.assertNotNull(tour.getClan(i));

        }
    }

    /**
     * Test of getTeam method, of class Tournament.
     */
    @Test
    public void testGetTeam_int() {
        System.out.println("getTeam");
         Tournament.getTournament().loadXML(new File("./test/coach.xml"));
        Tournament tour = Tournament.getTournament();
        for (int i = 0; i < tour.getTeamsCount(); i++) {
            Assert.assertNotNull(tour.getTeam(i));

        }
    }

    /**
     * Test of containsTeam method, of class Tournament.
     */
    @Test
    public void testContainsTeam_Team() {
        System.out.println("containsTeam");
        Tournament.getTournament().loadXML(new File("./test/team.xml"));
        Tournament tour = Tournament.getTournament();
        for (int i = 0; i < tour.getTeamsCount(); i++) {
            boolean result = tour.containsTeam(tour.getTeam(i));
            assertEquals(result, true);
        }
        assertFalse(tour.containsTeam(new Team("ABC")));
    }

    /**
     * Test of containsTeam method, of class Tournament.
     */
    @Test
    public void testContainsTeam_String() {
        System.out.println("containsTeam");
        Tournament.getTournament().loadXML(new File("./test/team.xml"));
        Tournament tour = Tournament.getTournament();
        for (int i = 0; i < tour.getTeamsCount(); i++) {
            boolean result = tour.containsTeam(tour.getTeam(i).getName());
            assertEquals(result, true);
        }
        assertFalse(tour.containsTeam("ABC"));
    }

    /**
     * Test of containsCoach method, of class Tournament.
     */
    @Test
    public void testContainsCoach_String() {
        System.out.println("containsCoach");
        Tournament.getTournament().loadXML(new File("./test/coach.xml"));
        Tournament tour = Tournament.getTournament();
        for (int i = 0; i < tour.getCoachsCount(); i++) {
            boolean result = tour.containsCoach(tour.getCoach(i).getName());
            assertEquals(result, true);
        }
        assertFalse(tour.containsCoach("ABC"));
    }

    /**
     * Test of getTeamIndex method, of class Tournament.
     */
    @Test
    public void testGetTeamIndex() {
        System.out.println("getTeamIndex");
        Tournament tour = Tournament.getTournament();
        Assert.assertEquals(tour.getTeamsCount(), 0);
        tour.loadXML(new File("./test/team.xml"));
        Assert.assertTrue(tour.getTeamsCount() > 1);

        for (int i = 0; i < tour.getTeamsCount(); i++) {
            Team r=tour.getTeam(i);
            assertEquals(tour.getTeamIndex(r.getName()), i);
        }
    }

    /**
     * Test of getTeam method, of class Tournament.
     */
    @Test
    public void testGetTeam_String() {
        System.out.println("getTeam");
        Tournament.getTournament().loadXML(new File("./test/team.xml"));
        Tournament tour = Tournament.getTournament();
        for (int i = 0; i < tour.getTeamsCount(); i++) {
            Team cl = tour.getTeam(i);
            assertEquals(tour.getTeam(cl.getName()), cl);
        }
        assertNull(tour.getTeam("ABC"));
    }

    /**
     * Test of containsCoach method, of class Tournament.
     */
    @Test
    public void testContainsCoach_Coach() {
        System.out.println("containsCoach");
        Tournament.getTournament().loadXML(new File("./test/coach.xml"));
        Tournament tour = Tournament.getTournament();
        for (int i = 0; i < tour.getCoachsCount(); i++) {
            boolean result = tour.containsCoach(tour.getCoach(i));
            assertEquals(result, true);
        }
        assertFalse(tour.containsCoach(new Coach("ABC")));
    }

    /**
     * Test of containsClan method, of class Tournament.
     */
    @Test
    public void testContainsClan() {
        System.out.println("containsClan");
        Tournament.getTournament().loadXML(new File("./test/clan.xml"));
        Tournament tour = Tournament.getTournament();
        for (int i = 0; i < tour.getClansCount(); i++) {
            boolean result = tour.containsClan(tour.getClan(i).getName());
            assertEquals(result, true);
        }
        assertFalse(tour.containsClan("ABC"));
    }

    /**
     * Test of getClan method, of class Tournament.
     */
    @Test
    public void testGetClan_String() {
        System.out.println("getClan");
        Tournament.getTournament().loadXML(new File("./test/clan.xml"));
        Tournament tour = Tournament.getTournament();
        for (int i = 0; i < tour.getClansCount(); i++) {
            Clan cl = tour.getClan(i);
            assertEquals(tour.getClan(cl.getName()), cl);
        }
        assertNull(tour.getClan("ABC"));
    }

    /**
     * Test of getGroup method, of class Tournament.
     */
    @Test
    public void testGetGroup_int() {
        System.out.println("getGroup");
         Tournament.getTournament().loadXML(new File("./test/group.xml"));
        Tournament tour = Tournament.getTournament();
        for (int i = 0; i < tour.getGroupsCount(); i++) {
            Assert.assertNotNull(tour.getGroup(i));

        }
    }

    /**
     * Test of getGroup method, of class Tournament.
     */
    @Test
    public void testGetGroup_Coach() {
        System.out.println("getGroup");
        Coach C = null;
        Tournament instance = null;
        Group expResult = null;
        Group result = instance.getGroup(C);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGroup method, of class Tournament.
     */
    @Test
    public void testGetGroup_String() {
        System.out.println("getGroup");
        Tournament.getTournament().loadXML(new File("./test/group.xml"));
        Tournament tour = Tournament.getTournament();
        for (int i = 0; i < tour.getGroupsCount(); i++) {
            Group cl = tour.getGroup(i);
            assertEquals(tour.getGroup(cl.getName()), cl);
        }
        assertNull(tour.getGroup("ABC"));
    }

    /**
     * Test of getRankingTypes method, of class Tournament.
     */
    @Test
    public void testGetRankingTypes() {
        System.out.println("getRankingTypes");
        boolean byTeam = false;
        Tournament instance = null;
        ArrayList expResult = null;
        ArrayList result = instance.getRankingTypes(byTeam);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of loadRosters method, of class Tournament.
     */
    @Test
    public void testLoadRosters() {
        System.out.println("loadRosters");
        Element racine = null;
        Tournament instance = null;
        instance.loadRosters(racine);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    private void assertNotNull(boolean result, boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
