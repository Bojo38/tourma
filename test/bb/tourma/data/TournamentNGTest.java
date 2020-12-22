/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bb.tourma.data;

import java.awt.image.RenderedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.testng.Assert;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
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

    static Tournament instance;

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
        Tournament.getTournament().loadXML(new File("./test/tournament.xml"));
        instance = Tournament.getTournament();
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

        Assert.assertEquals(instance.getClansCount(), 5);
        instance.loadXML(new File("./test/clan.xml"));

        Assert.assertTrue(instance.getClansCount() > 1);
    }

    /**
     * Test of getClan method, of class Tournament.
     */
    @Test
    public void testGetClan() {
        System.out.println("getClan");

        Assert.assertEquals(instance.getClansCount(), 5);
        instance.loadXML(new File("./test/clan.xml"));
        Assert.assertTrue(instance.getClansCount() > 1);

        for (int i = 0; i < instance.getClansCount(); i++) {
            Assert.assertNotNull(instance.getClan(i));
        }
    }

    /**
     * Test of addClan method, of class Tournament.
     */
    @Test
    public void testAddClan() {
        System.out.println("addClan");

        Assert.assertEquals(instance.getClansCount(), 5);
        instance.loadXML(new File("./test/clan.xml"));
        Assert.assertTrue(instance.getClansCount() > 1);
        int nb = instance.getClansCount();
        Clan cl = new Clan("Bidon");
        instance.addClan(cl);
        assertEquals(instance.getClansCount(), nb + 1);
    }

    /**
     * Test of removeClan method, of class Tournament.
     */
    @Test
    public void testRemoveClan_Clan() {
        System.out.println("removeClan");

        Assert.assertEquals(instance.getClansCount(), 5);
        instance.loadXML(new File("./test/clan.xml"));
        Assert.assertTrue(instance.getClansCount() > 1);
        int nb = instance.getClansCount();
        Clan cl = new Clan("Bidon");
        instance.addClan(cl);
        assertEquals(instance.getClansCount(), nb + 1);
        instance.removeClan(cl);
        assertEquals(instance.getClansCount(), nb);
    }

    /**
     * Test of removeClan method, of class Tournament.
     */
    @Test
    public void testRemoveClan_int() {
        System.out.println("removeClan");

        Assert.assertEquals(instance.getClansCount(), 5);
        instance.loadXML(new File("./test/clan.xml"));
        Assert.assertTrue(instance.getClansCount() > 1);
        int nb = instance.getClansCount();
        Clan cl = new Clan("Bidon");
        instance.addClan(cl);
        assertEquals(instance.getClansCount(), nb + 1);
        instance.removeClan(nb);
        assertEquals(instance.getClansCount(), nb);
    }

    /**
     * Test of clearClans method, of class Tournament.
     */
    @Test
    public void testClearClans() {
        System.out.println("clearClans");

        Assert.assertEquals(instance.getClansCount(), 5);
        instance.loadXML(new File("./test/clan.xml"));
        Assert.assertTrue(instance.getClansCount() > 1);
        int nb = instance.getClansCount();
        instance.clearClans();
        assertEquals(instance.getClansCount(), 0);
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
        Assert.assertEquals(instance.getCategoriesCount(), 1);
        instance.loadXML(new File("./test/category.xml"));
        Assert.assertTrue(instance.getCategoriesCount() > 1);
        int nb = instance.getCategoriesCount();
        instance.clearCategories();
        assertEquals(instance.getCategoriesCount(), 0);
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

        Assert.assertEquals(instance.getClansCount(), 5);
        instance.loadXML(new File("./test/clan.xml"));
        Assert.assertTrue(instance.getClansCount() > 1);

        for (int i = 0; i < instance.getClansCount(); i++) {
            Clan cat = instance.getClan(i);
            Assert.assertNotNull(cat);
        }
        Clan cat = instance.getClan(instance.getClansCount() - 1);
        assertTrue(instance.getDisplayClans().contains(cat));
        for (int i = 0; i < instance.getCoachsCount(); i++) {
            Coach c = instance.getCoach(i);
            if (c.getClan().equals(cat)) {
                c.setClan(instance.getClan(0));
            }
        }
        for (int i = 0; i < instance.getTeamsCount(); i++) {
            Team c = instance.getTeam(i);
            if (c.getClan().equals(cat)) {
                c.setClan(instance.getClan(0));
            }
        }

        assertFalse(instance.getDisplayClans().contains(cat));
    }

    /**
     * Test of getDisplayCategories method, of class Tournament.
     */
    @Test
    public void testGetDisplayCategories() {
        System.out.println("getDisplayCategories");
        Tournament tour = Tournament.getTournament();
        Assert.assertEquals(tour.getCategoriesCount(), 1);
        tour.loadXML(new File("./test/category.xml"));
        Assert.assertTrue(tour.getCategoriesCount() > 1);

        for (int i = 0; i < tour.getCategoriesCount(); i++) {
            Category cat = tour.getCategory(i);
            Assert.assertNotNull(cat);
        }
        Category cat = tour.getCategory(0);
        assertTrue(tour.getDisplayCategories().contains(cat));
        for (int i = 0; i < tour.getCoachsCount(); i++) {
            Coach c = tour.getCoach(i);
            c.delCategory(cat);
        }
        for (int i = 0; i < tour.getTeamsCount(); i++) {
            Team c = tour.getTeam(i);
            c.delCategory(cat);
        }

        assertFalse(tour.getDisplayCategories().contains(cat));

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
        Assert.assertNotEquals(instance.getParams(), expResult);
        instance.setParams(expResult);
        Parameters result = instance.getParams();
        assertTrue(result == expResult);
    }

    /**
     * Test of removeCoach method, of class Tournament.
     */
    @Test
    public void testRemoveCoach_Coach() {
        System.out.println("removeCoach");

        Assert.assertEquals(instance.getCoachsCount(), 14);
        instance.loadXML(new File("./test/coach.xml"));
        Assert.assertTrue(instance.getCoachsCount() > 1);
        int nb = instance.getCoachsCount();
        instance.removeCoach(instance.getCoach(0));
        assertEquals(instance.getCoachsCount(), nb - 1);
    }

    /**
     * Test of removeCoach method, of class Tournament.
     */
    @Test
    public void testRemoveCoach_int() {
        System.out.println("removeCoach");

        Assert.assertEquals(instance.getCoachsCount(), 14);
        instance.loadXML(new File("./test/coach.xml"));
        Assert.assertTrue(instance.getCoachsCount() > 1);
        int nb = instance.getCoachsCount();
        instance.removeCoach(0);
        assertEquals(instance.getCoachsCount(), nb - 1);
    }

    /**
     * Test of clearCoachs method, of class Tournament.
     */
    @Test
    public void testClearCoachs() {
        System.out.println("clearCoachs");

        Assert.assertEquals(instance.getCoachsCount(), 14);
        instance.loadXML(new File("./test/coach.xml"));
        Assert.assertTrue(instance.getCoachsCount() > 1);
        int nb = instance.getCoachsCount();
        instance.clearCoachs();
        assertEquals(instance.getCoachsCount(), 0);
    }

    /**
     * Test of addCoach method, of class Tournament.
     */
    @Test
    public void testAddCoach() {
        System.out.println("addCoach");
        Assert.assertEquals(instance.getCoachsCount(), 14);
        instance.loadXML(new File("./test/coach.xml"));
        Assert.assertTrue(instance.getCoachsCount() > 1);
        int nb = instance.getCoachsCount();
        Coach cl = new Coach("Bidon");
        instance.addCoach(cl);
        assertEquals(instance.getCoachsCount(), nb + 1);
    }

    /**
     * Test of getCoachsCount method, of class Tournament.
     */
    @Test
    public void testGetCoachsCount() {
        System.out.println("getCoachsCount");

        Assert.assertEquals(instance.getCoachsCount(), 14);
        instance.loadXML(new File("./test/coach.xml"));
        Assert.assertTrue(instance.getCoachsCount() > 1);
        int nb = instance.getCoachsCount();
        Coach cl = new Coach("Bidon");
        instance.addCoach(cl);
        assertEquals(instance.getCoachsCount(), nb + 1);
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

        Assert.assertEquals(instance.getGroupsCount(), 4);
        instance.loadXML(new File("./test/group.xml"));
        Assert.assertTrue(instance.getGroupsCount() > 1);
        int nb = instance.getGroupsCount();
        Group cl = new Group("Bidon");
        instance.addGroup(cl);
        assertEquals(instance.getGroupsCount(), nb + 1);
    }

    /**
     * Test of removeGroup method, of class Tournament.
     */
    @Test
    public void testRemoveGroup_Group() {
        System.out.println("removeGroup");

        Assert.assertEquals(instance.getGroupsCount(), 4);
        instance.loadXML(new File("./test/group.xml"));
        Assert.assertTrue(instance.getGroupsCount() > 1);
        int nb = instance.getGroupsCount();
        instance.removeGroup(instance.getGroup(0));
        assertEquals(instance.getGroupsCount(), nb - 1);
    }

    /**
     * Test of removeGroup method, of class Tournament.
     */
    @Test
    public void testRemoveGroup_int() {
        System.out.println("removeGroup");

        Assert.assertEquals(instance.getGroupsCount(), 4);
        instance.loadXML(new File("./test/group.xml"));
        Assert.assertTrue(instance.getGroupsCount() > 1);
        int nb = instance.getGroupsCount();
        instance.removeGroup(0);
        assertEquals(instance.getGroupsCount(), nb - 1);
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

        Assert.assertEquals(instance.getGroupsCount(), 4);
        instance.loadXML(new File("./test/group.xml"));
        Assert.assertTrue(instance.getGroupsCount() > 1);
        int nb = instance.getGroupsCount();
        instance.clearGroups();
        assertEquals(instance.getGroupsCount(), 0);
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

        Assert.assertEquals(instance.getGroupsCount(), 4);
        instance.loadXML(new File("./test/group.xml"));

        Assert.assertTrue(instance.getGroupsCount() > 1);
    }

    /**
     * Test of getRoundsCount method, of class Tournament.
     */
    @Test
    public void testGetRoundsCount() {
        System.out.println("getRoundsCount");

        Assert.assertEquals(instance.getRoundsCount(), 5);
        instance.loadXML(new File("./test/tournament.xml"));

        Assert.assertTrue(instance.getRoundsCount() > 1);
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
        Assert.assertEquals(instance.getRoundsCount(), 5);
        Assert.assertTrue(instance.getRoundsCount() > 1);
        int nb = instance.getRoundsCount();
        instance.clearRounds();
        assertEquals(instance.getRoundsCount(), 0);
    }

    /**
     * Test of addRound method, of class Tournament.
     */
    @Test
    public void testAddRound() {
        System.out.println("addRound");

        Assert.assertEquals(instance.getRoundsCount(), 5);

        Assert.assertTrue(instance.getRoundsCount() > 1);
        int nb = instance.getRoundsCount();
        Round cl = new Round(Tournament.getTournament().getRoundsCount(), Tournament.getTournament());
        instance.addRound(cl);
        assertEquals(instance.getRoundsCount(), nb + 1);
    }

    /**
     * Test of saveXML method, of class Tournament.
     */
    @Test
    public void testSaveXML_File() {
        System.out.println("saveXML");

        instance.saveXML(new File("./testLoadSave.xml"));
        try {
            assertTrue(compareTwoFiles("./test/testLoadSave.xml", "./testLoadSave.xml"));
        } catch (IOException ioe) {
            fail("exception catched");
        }
    }

    /**
     * Test of exportFullFBB method, of class Tournament.
     */
    @Test
    public void testExportFullFBB() {
        System.out.println("exportFullFBB");

        File f = new File("./testExportFullFBB.fbb_xml");
        instance.exportFullFBB(f);
        try {
            assertTrue(compareTwoFiles("./test/tournamentExportFullFBB.fbb_xml", "./testExportFullFBB.fbb_xml"));
        } catch (IOException ioe) {
            fail("exception catched");
        }
        /*try {
            Files.delete(f.toPath());
        } catch (IOException ex) {
            Logger.getLogger(TournamentNGTest.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }

    /**
     * Test of exportNAF method, of class Tournament.
     */
    @Test
    public void testExportNAF() {
        System.out.println("exportNAF");

        File f = new File("./testExportNaf.xml");
        instance.exportNAF(f);
        try {
            assertTrue(compareTwoFiles("./test/tournamentExportNaf.xml", "./testExportNaf.xml"));
        } catch (IOException ioe) {
            fail("exception catched");
        }
        try {
            Files.delete(f.toPath());
        } catch (IOException ex) {
            Logger.getLogger(TournamentNGTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of loadXML method, of class Tournament.
     */
    @Test
    public void testLoadXML() {
        System.out.println("loadXML");
       Tournament.getTournament().loadXML(new File("./test/testLoadSave.xml"));
       instance=Tournament.getTournament();
       instance.saveXML(new File("./testLoadSave.xml2"));
       
        try {
            assertTrue(compareTwoFiles("./test/testLoadSave.xml", "./testLoadSave.xml2"));
        } catch (IOException ioe) {
            fail("exception catched");
        }

    }

    public boolean compareTwoFiles(String file1Path, String file2Path)
            throws IOException {

        File file1 = new File(file1Path);
        File file2 = new File(file2Path);

        BufferedReader br1 = new BufferedReader(new FileReader(file1));
        BufferedReader br2 = new BufferedReader(new FileReader(file2));

        String thisLine = null;
        String thatLine = null;

        List<String> list1 = new ArrayList<String>();
        List<String> list2 = new ArrayList<String>();

        while ((thisLine = br1.readLine()) != null) {
            list1.add(thisLine);
        }
        while ((thatLine = br2.readLine()) != null) {
            list2.add(thatLine);
        }

        br1.close();
        br2.close();

        return list1.equals(list2);
    }

    /**
     * Test of getActiveCoachNumber method, of class Tournament.
     */
    @Test
    public void testGetActiveCoachNumber() {
        System.out.println("getActiveCoachNumber");

        Assert.assertEquals(instance.getCoachsCount(), 14);
        instance.loadXML(new File("./test/coach.xml"));
        Assert.assertTrue(instance.getCoachsCount() > 1);
        int nb = instance.getCoachsCount();
        for (int i = 0; i < instance.getCoachsCount(); i++) {
            instance.getCoach(i).setActive(true);
        }
        assertEquals(nb, instance.getActiveCoachNumber());
        for (int i = 0; i < instance.getCoachsCount(); i++) {
            instance.getCoach(i).setActive(false);
        }
        assertEquals(0, instance.getActiveCoachNumber());
    }

    /**
     * Test of getActiveCoaches method, of class Tournament.
     */
    @Test
    public void testGetActiveCoaches() {
        System.out.println("getActiveCoaches");

        Assert.assertEquals(instance.getCoachsCount(), 14);
        instance.loadXML(new File("./test/coach.xml"));
        Assert.assertTrue(instance.getCoachsCount() > 1);
        int nb = instance.getCoachsCount();
        for (int i = 0; i < instance.getCoachsCount(); i++) {
            instance.getCoach(i).setActive(true);
        }
        assertEquals(nb, instance.getActiveCoaches().size());
        for (int i = 0; i < instance.getCoachsCount(); i++) {
            instance.getCoach(i).setActive(false);
        }
        assertEquals(0, instance.getActiveCoaches().size());
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
        Assert.assertNotEquals(instance.getParams(), expResult);
        instance.setParams(expResult);
        Parameters result = instance.getParams();
        assertTrue(result == expResult);
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
        assertTrue(result == expResult);
        expResult = true;
        instance.setRoundRobin(expResult);
        result = instance.isRoundRobin();
        assertTrue(result == expResult);
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
        assertTrue(result == expResult);
        expResult = true;
        instance.setRoundRobin(expResult);
        result = instance.isRoundRobin();
        assertTrue(result == expResult);
    }

    /**
     * Test of removeRound method, of class Tournament.
     */
    @Test
    public void testRemoveRound_Round() {
        System.out.println("removeRound");

        Assert.assertEquals(instance.getRoundsCount(), 5);
        instance.loadXML(new File("./test/tournament.xml"));
        Assert.assertTrue(instance.getRoundsCount() > 1);
        int nb = instance.getRoundsCount();
        instance.removeRound(instance.getRound(0));
        assertEquals(instance.getRoundsCount(), nb - 1);
    }

    /**
     * Test of removeRound method, of class Tournament.
     */
    @Test
    public void testRemoveRound_int() {
        System.out.println("removeRound");

        Assert.assertEquals(instance.getRoundsCount(), 5);
        instance.loadXML(new File("./test/tournament.xml"));
        Assert.assertTrue(instance.getRoundsCount() > 1);
        int nb = instance.getRoundsCount();
        instance.removeRound(0);
        assertEquals(instance.getRoundsCount(), nb - 1);
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
            Round r = tour.getRound(i);
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
            Team r = tour.getTeam(i);
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
        Tournament.getTournament().loadXML(new File("./test/group.xml"));
        Tournament instance = Tournament.getTournament();
        for (int i = 0; i < instance.getCoachsCount(); i++) {
            Coach C = instance.getCoach(i);
            Group result = instance.getGroup(C);
            Assert.assertNotNull(result);
        }
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
        Tournament.getTournament().loadXML(new File("./test/params.xml"));

        Tournament instance = Tournament.getTournament();
        instance.getParams().setRankingIndiv1(Parameters.C_RANKING_NONE);
        instance.getParams().setRankingIndiv2(Parameters.C_RANKING_NONE);
        instance.getParams().setRankingIndiv3(Parameters.C_RANKING_NONE);
        instance.getParams().setRankingIndiv4(Parameters.C_RANKING_NONE);
        instance.getParams().setRankingIndiv5(Parameters.C_RANKING_NONE);
        ArrayList result = instance.getRankingTypes(false);
        for (Object i : result) {
            if (i instanceof Integer) {
                assertEquals(((Integer) i).intValue(), Parameters.C_RANKING_NONE);
            } else {
                fail("Ranking is not integer");
            }
        }

        instance.getParams().setRankingTeam1(Parameters.C_RANKING_NONE);
        instance.getParams().setRankingTeam2(Parameters.C_RANKING_NONE);
        instance.getParams().setRankingTeam3(Parameters.C_RANKING_NONE);
        instance.getParams().setRankingTeam4(Parameters.C_RANKING_NONE);
        instance.getParams().setRankingTeam5(Parameters.C_RANKING_NONE);
        result = instance.getRankingTypes(true);
        for (Object i : result) {
            if (i instanceof Integer) {
                assertEquals(((Integer) i).intValue(), Parameters.C_RANKING_NONE);
            } else {
                fail("Ranking is not integer");
            }
        }
    }

    /**
     * Test of loadRosters method, of class Tournament.
     */
    @Test
    public void testLoadRosters() {
        System.out.println("loadRosters");

        final SAXBuilder sxb = new SAXBuilder();

        try {
            final org.jdom.Document document = sxb.build(new File("./test/tournament.xml"));
            final Element racine = document.getRootElement();
            Tournament instance = Tournament.getTournament();
            RosterType.newRostersNames();
            RosterType.newRostersTypes();

            assertTrue(RosterType.getRostersNamesCount() == 0);

            instance.loadRosters(racine);

            assertTrue(RosterType.getRostersNamesCount() > 0);
        } catch (Exception e) {
            fail("Exception catched");
        }
    }

    /**
     * Test of isClient method, of class Tournament.
     */
    @Test
    public void testIsClient() {
        System.out.println("isClient");

        boolean result = instance.isClient();
        assertEquals(result, false);
    }

    /**
     * Test of setIsClient method, of class Tournament.
     */
    @Test
    public void testSetIsClient() {
        System.out.println("setIsClient");

        boolean result = instance.isClient();
        assertEquals(result, false);

        instance.setIsClient(true);
        result = instance.isClient();
        assertEquals(result, true);
    }

    /**
     * Test of recomputeAll method, of class Tournament.
     */
    @Test(enabled = false)
    public void testRecomputeAll() {
        System.out.println("recomputeAll");
        Tournament instance = null;
        instance.recomputeAll();
      
    }

    /**
     * Test of getTeams method, of class Tournament.
     */
    @Test
    public void testGetTeams() {
        System.out.println("getTeams");
        Tournament.getTournament().loadXML(new File("./test/team.xml"));
        ArrayList result = instance.getTeams();
        assertEquals(result.get(0), instance.getTeam(0));
    }

    /**
     * Test of getCoachs method, of class Tournament.
     */
    @Test
    public void testGetCoachs() {
        System.out.println("getCoachs");

        ArrayList result = instance.getCoachs();
        assertEquals(result.get(0), instance.getCoach(0));

    }

    /**
     * Test of getCategory method, of class Tournament.
     */
    @Test
    public void testGetCategory_int() {
        System.out.println("getCategory");
        int i = 0;
        Category cat = instance.getCategory(0);

        assertEquals(cat.getName(), "Aucun");
    }

    /**
     * Test of getCategory method, of class Tournament.
     */
    @Test
    public void testGetCategory_String() {
        System.out.println("getCategory");
        String s = "";

        Category cat = instance.getCategory("Aucun");

        assertEquals(cat.getName(), "Aucun");

    }

    /**
     * Test of containsGroup method, of class Tournament.
     */
    @Test
    public void testContainsGroup() {
        System.out.println("containsGroup");

        Group g = instance.getGroup(0);

        boolean expResult = true;
        boolean result = instance.containsGroup(g);
        assertEquals(result, expResult);

        expResult = false;
        g = new Group("Toto");
        result = instance.containsGroup(g);
        assertEquals(result, expResult);

    }

    /**
     * Test of saveXML method, of class Tournament.
     */
    @Test
    public void testSaveXML() {
        System.out.println("saveXML");
       /* File file = null;
        Tournament instance = null;
        instance.saveXML(file);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");*/
    }

    /**
     * Test of getDescription method, of class Tournament.
     */
    @Test
    public void testGetDescription() {
        System.out.println("getDescription");

        String result = instance.getDescription();
        assertEquals(result, "");
    }

    /**
     * Test of setDescription method, of class Tournament.
     */
    @Test
    public void testSetDescription() {
        System.out.println("setDescription");
        String result = instance.getDescription();
        assertEquals(result, "");

        instance.setDescription("Bla Bla Bla");
        result = instance.getDescription();
        assertEquals(result, "Bla Bla Bla");
    }

    /**
     * Test of pull method, of class Tournament.
     */
    @Test(enabled = false)
    public void testPull() {
        System.out.println("pull");
        Tournament tour = null;
        Tournament.pull(tour);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of pullRounds method, of class Tournament.
     */
    @Test(enabled = false)
    public void testPullRounds() {
        System.out.println("pullRounds");
        ArrayList<Round> rounds = null;
        Tournament instance = null;
        instance.pullRounds(rounds);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of pullClans method, of class Tournament.
     */
    @Test(enabled = false)
    public void testPullClans() {
        System.out.println("pullClans");
        ArrayList<Clan> clans = null;
        Tournament instance = null;
        instance.pullClans(clans);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of pullCategories method, of class Tournament.
     */
    @Test(enabled = false)
    public void testPullCategories() {
        System.out.println("pullCategories");
        ArrayList<Category> categories = null;
        Tournament instance = null;
        instance.pullCategories(categories);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of pullTeams method, of class Tournament.
     */
    @Test(enabled = false)
    public void testPullTeams() {
        System.out.println("pullTeams");
        ArrayList<Team> teams = null;
        Tournament instance = null;
        instance.pullTeams(teams);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of pullCoachs method, of class Tournament.
     */
    @Test(enabled = false)
    public void testPullCoachs() {
        System.out.println("pullCoachs");
        ArrayList<Coach> coachs = null;
        Tournament instance = null;
        instance.pullCoachs(coachs);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of pullGroups method, of class Tournament.
     */
    @Test(enabled = false)
    public void testPullGroups() {
        System.out.println("pullGroups");
        ArrayList<Group> groups = null;
        Tournament instance = null;
        instance.pullGroups(groups);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRosterType method, of class Tournament.
     */
    @Test
    public void testGetRosterType() {
        System.out.println("getRosterType");

        HashMap result = instance.getRosterType();
        Assert.assertNotNull(result);
    }

    /**
     * Test of push method, of class Tournament.
     */
    @Test(enabled = false)
    public void testPush() {
        System.out.println("push");
        Tournament tour = null;
        Tournament.push(tour);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isClansUpdated method, of class Tournament.
     */
    @Test
    public void testIsClansUpdated() {
        System.out.println("isClansUpdated");

        boolean result = instance.isClansUpdated();
        assertEquals(result, false);
    }

    /**
     * Test of setClansUpdated method, of class Tournament.
     */
    @Test
    public void testSetClansUpdated() {
        System.out.println("setClansUpdated");
        boolean result = instance.isClansUpdated();
        assertEquals(result, false);

        instance.setClansUpdated(true);
        result = instance.isClansUpdated();
        assertEquals(result, true);
    }

    /**
     * Test of isCoachsUpdated method, of class Tournament.
     */
    @Test
    public void testIsCoachsUpdated() {
        System.out.println("isCoachsUpdated");

        boolean result = instance.isCoachsUpdated();
        assertEquals(result, false);
    }

    /**
     * Test of setCoachsUpdated method, of class Tournament.
     */
    @Test
    public void testSetCoachsUpdated() {
        System.out.println("setCoachsUpdated");
        boolean result = instance.isCoachsUpdated();
        assertEquals(result, false);
        instance.setCoachsUpdated(true);
        result = instance.isCoachsUpdated();
        assertEquals(result, true);
    }

    /**
     * Test of isTeamsUpdated method, of class Tournament.
     */
    @Test
    public void testIsTeamsUpdated() {
        System.out.println("isTeamsUpdated");

        boolean expResult = false;
        boolean result = instance.isTeamsUpdated();
        assertEquals(result, expResult);

        instance.setTeamsUpdated(true);
        result = instance.isTeamsUpdated();
        assertEquals(result, true);
    }

    /**
     * Test of setTeamsUpdated method, of class Tournament.
     */
    @Test
    public void testSetTeamsUpdated() {
        System.out.println("setTeamsUpdated");

        boolean expResult = false;
        boolean result = instance.isTeamsUpdated();
        assertEquals(result, expResult);

        instance.setTeamsUpdated(true);
        result = instance.isTeamsUpdated();
        assertEquals(result, true);
    }

    /**
     * Test of isRoundsUpdated method, of class Tournament.
     */
    @Test
    public void testIsRoundsUpdated() {
        System.out.println("isRoundsUpdated");

        boolean result = instance.isRoundsUpdated();
        assertEquals(result, false);

    }

    /**
     * Test of setRoundsUpdated method, of class Tournament.
     */
    @Test
    public void testSetRoundsUpdated() {
        System.out.println("setRoundsUpdated");

        boolean result = instance.isRoundsUpdated();
        assertEquals(result, false);
        instance.setRoundsUpdated(true);

        result = instance.isRoundsUpdated();
        assertEquals(result, true);

    }

    /**
     * Test of pushClans method, of class Tournament.
     */
    @Test(enabled = false)
    public void testPushClans() {
        System.out.println("pushClans");
        ArrayList<Clan> clans = null;
        Tournament instance = null;
        instance.pushClans(clans);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of pushTeams method, of class Tournament.
     */
    @Test(enabled = false)
    public void testPushTeams() {
        System.out.println("pushTeams");
        ArrayList<Team> teams = null;
        Tournament instance = null;
        instance.pushTeams(teams);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of pushCoachs method, of class Tournament.
     */
    @Test(enabled = false)
    public void testPushCoachs() {
        System.out.println("pushCoachs");
        ArrayList<Coach> coachs = null;
        Tournament instance = null;
        instance.pushCoachs(coachs);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of pushRounds method, of class Tournament.
     */
    @Test(enabled = false)
    public void testPushRounds() {
        System.out.println("pushRounds");
        ArrayList<Round> rounds = null;
        Tournament instance = null;
        instance.pushRounds(rounds);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of resetUpdated method, of class Tournament.
     */
    @Test(enabled = false)
    public void testResetUpdated() {
        System.out.println("resetUpdated");
        instance.resetUpdated();
    }

    /**
     * Test of getTeamsNames method, of class Tournament.
     */
    @Test
    public void testGetTeamsNames() {
        System.out.println("getTeamsNames");
        Tournament.getTournament().loadXML(new File("./test/team.xml"));
        instance = Tournament.getTournament();
        String[] result = instance.getTeamsNames();
        assertEquals(result.length, 14);
    }

    /**
     * Test of getActiveCompetitorsCount method, of class Tournament.
     */
    @Test
    public void testGetActiveCompetitorsCount() {
        System.out.println("getActiveCompetitorsCount");
        int expResult = 14;
        int result = instance.getActiveCompetitorsCount();
        assertEquals(result, expResult);
    }

    /**
     * Test of getClans method, of class Tournament.
     */
    @Test
    public void testGetClans() {
        System.out.println("getClans");

        ArrayList result = instance.getClans();
        assertEquals(result.size(), 5);
    }

    /**
     * Test of getCup method, of class Tournament.
     */
    @Test
    public void testGetCup() {
        System.out.println("getCup");

        Tournament.getTournament().loadXML(new File("./test/cup_teams.xml"));
        instance = Tournament.getTournament();
        Cup result = instance.getCup();
        assertNotNull(result);
    }

    /**
     * Test of setCup method, of class Tournament.
     */
    @Test
    public void testSetCup() {
        System.out.println("setCup");

        Tournament.getTournament().loadXML(new File("./test/cup_teams.xml"));
        instance = Tournament.getTournament();
        Cup result = instance.getCup();
        assertNotNull(result);

        Cup cup = new Cup();

        instance.setCup(cup);
        result = instance.getCup();
        assertEquals(result, cup);
    }
}
