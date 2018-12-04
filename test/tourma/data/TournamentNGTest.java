/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

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
      Tournament tour = Tournament.getTournament();
        Assert.assertEquals(tour.getClansCount(), 1);
        tour.loadXML(new File("./test/clan.xml"));
        Assert.assertTrue(tour.getClansCount() > 1);

        for (int i = 0; i < tour.getClansCount(); i++) {
            Clan cat=tour.getClan(i);
            Assert.assertNotNull(cat);
        }
         Clan cat=tour.getClan(tour.getClansCount()-1);
         assertTrue(tour.getDisplayClans().contains(cat));
        for (int i=0; i<tour.getCoachsCount(); i++)
        {
            Coach c=tour.getCoach(i);
            if (c.getClan().equals(cat))
            {
                c.setClan(tour.getClan(0));
            }
        }
        for (int i=0; i<tour.getTeamsCount(); i++)
        {
            Team c=tour.getTeam(i);
           if (c.getClan().equals(cat))
            {
                c.setClan(tour.getClan(0));
            }         
        }
        
        assertFalse(tour.getDisplayClans().contains(cat));
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
            Category cat=tour.getCategory(i);
            Assert.assertNotNull(cat);
        }
         Category cat=tour.getCategory(0);
         assertTrue(tour.getDisplayCategories().contains(cat));
        for (int i=0; i<tour.getCoachsCount(); i++)
        {
            Coach c=tour.getCoach(i);
            c.delCategory(cat);            
        }
        for (int i=0; i<tour.getTeamsCount(); i++)
        {
            Team c=tour.getTeam(i);
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
        Tournament.getTournament().loadXML(new File("./test/tournament.xml"));
        Tournament instance = Tournament.getTournament();
        instance.saveXML(new File("./testLoadSave.xml"));
        try {
            assertTrue(compareTwoFiles("./test/tournament.xml", "./testLoadSave.xml"));
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
        Tournament.getTournament().loadXML(new File("./test/tournament.xml"));
        Tournament instance = Tournament.getTournament();
        File f = new File("./testExportFullFBB.fbb_xml");
        instance.exportFullFBB(f);
        try {
            assertTrue(compareTwoFiles("./test/tournamentExportFullFBB.fbb_xml", "./testExportFullFBB.fbb_xml"));
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
     * Test of exportFBB method, of class Tournament.
     */
    @Test
    public void testExportFBB() {
        System.out.println("exportFBB");
        Tournament.getTournament().loadXML(new File("./test/tournaments/melting2015.xml"));
        Tournament instance = Tournament.getTournament();
        File f = new File("./testExportFBB.csv");
        File f2 = new File("./testExportFBB.csv.PNG");
        instance.exportFBB(f);
        try {
            assertTrue(compareTwoFiles("./test/tournamentExportFBB.csv", "./testExportFBB.csv"));
        } catch (IOException ioe) {
            fail("exception catched");
        }
        try {
            Files.delete(f.toPath());
             Files.delete(f2.toPath());
            
        } catch (IOException ex) {
            Logger.getLogger(TournamentNGTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of exportNAF method, of class Tournament.
     */
    @Test
    public void testExportNAF() {
        System.out.println("exportNAF");
        Tournament.getTournament().loadXML(new File("./test/tournament.xml"));
        Tournament instance = Tournament.getTournament();
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
        Tournament.getTournament().loadXML(new File("./test/tournament.xml"));
        Tournament instance = Tournament.getTournament();
        instance.saveXML(new File("./testLoadSave.xml"));
        try {
            assertTrue(compareTwoFiles("./test/tournament.xml", "./testLoadSave.xml"));
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
        Tournament tour = Tournament.getTournament();
        Assert.assertEquals(tour.getCoachsCount(), 0);
        tour.loadXML(new File("./test/coach.xml"));
        Assert.assertTrue(tour.getCoachsCount() > 1);
        int nb = tour.getCoachsCount();
        for (int i = 0; i < tour.getCoachsCount(); i++) {
            tour.getCoach(i).setActive(true);
        }
        assertEquals(nb, tour.getActiveCoachNumber());
        for (int i = 0; i < tour.getCoachsCount(); i++) {
            tour.getCoach(i).setActive(false);
        }
        assertEquals(0, tour.getActiveCoachNumber());
    }

    /**
     * Test of getActiveCoaches method, of class Tournament.
     */
    @Test
    public void testGetActiveCoaches() {
        System.out.println("getActiveCoaches");
      Tournament tour = Tournament.getTournament();
        Assert.assertEquals(tour.getCoachsCount(), 0);
        tour.loadXML(new File("./test/coach.xml"));
        Assert.assertTrue(tour.getCoachsCount() > 1);
        int nb = tour.getCoachsCount();
        for (int i = 0; i < tour.getCoachsCount(); i++) {
            tour.getCoach(i).setActive(true);
        }
        assertEquals(nb, tour.getActiveCoaches().size());
        for (int i = 0; i < tour.getCoachsCount(); i++) {
            tour.getCoach(i).setActive(false);
        }
        assertEquals(0, tour.getActiveCoaches().size());
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
        Tournament instance = null;
        boolean expResult = false;
        boolean result = instance.isClient();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setIsClient method, of class Tournament.
     */
    @Test
    public void testSetIsClient() {
        System.out.println("setIsClient");
        boolean isClient = false;
        Tournament instance = null;
        instance.setIsClient(isClient);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of recomputeAll method, of class Tournament.
     */
    @Test
    public void testRecomputeAll() {
        System.out.println("recomputeAll");
        Tournament instance = null;
        instance.recomputeAll();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTeams method, of class Tournament.
     */
    @Test
    public void testGetTeams() {
        System.out.println("getTeams");
        Tournament instance = null;
        ArrayList expResult = null;
        ArrayList result = instance.getTeams();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCoachs method, of class Tournament.
     */
    @Test
    public void testGetCoachs() {
        System.out.println("getCoachs");
        Tournament instance = null;
        ArrayList expResult = null;
        ArrayList result = instance.getCoachs();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCategory method, of class Tournament.
     */
    @Test
    public void testGetCategory_int() {
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
     * Test of getCategory method, of class Tournament.
     */
    @Test
    public void testGetCategory_String() {
        System.out.println("getCategory");
        String s = "";
        Tournament instance = null;
        Category expResult = null;
        Category result = instance.getCategory(s);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of containsGroup method, of class Tournament.
     */
    @Test
    public void testContainsGroup() {
        System.out.println("containsGroup");
        Group g = null;
        Tournament instance = null;
        boolean expResult = false;
        boolean result = instance.containsGroup(g);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of saveXML method, of class Tournament.
     */
    @Test
    public void testSaveXML() {
        System.out.println("saveXML");
        File file = null;
        Tournament instance = null;
        instance.saveXML(file);
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
     * Test of getDescription method, of class Tournament.
     */
    @Test
    public void testGetDescription() {
        System.out.println("getDescription");
        Tournament instance = null;
        String expResult = "";
        String result = instance.getDescription();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDescription method, of class Tournament.
     */
    @Test
    public void testSetDescription() {
        System.out.println("setDescription");
        String tmp = "";
        Tournament instance = null;
        instance.setDescription(tmp);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of pull method, of class Tournament.
     */
    @Test
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
    @Test
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
    @Test
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
    @Test
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
    @Test
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
    @Test
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
    @Test
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
        Tournament instance = null;
        HashMap expResult = null;
        HashMap result = instance.getRosterType();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of push method, of class Tournament.
     */
    @Test
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
        Tournament instance = null;
        boolean expResult = false;
        boolean result = instance.isClansUpdated();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setClansUpdated method, of class Tournament.
     */
    @Test
    public void testSetClansUpdated() {
        System.out.println("setClansUpdated");
        boolean clansUpdated = false;
        Tournament instance = null;
        instance.setClansUpdated(clansUpdated);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isCoachsUpdated method, of class Tournament.
     */
    @Test
    public void testIsCoachsUpdated() {
        System.out.println("isCoachsUpdated");
        Tournament instance = null;
        boolean expResult = false;
        boolean result = instance.isCoachsUpdated();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCoachsUpdated method, of class Tournament.
     */
    @Test
    public void testSetCoachsUpdated() {
        System.out.println("setCoachsUpdated");
        boolean coachsUpdated = false;
        Tournament instance = null;
        instance.setCoachsUpdated(coachsUpdated);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isTeamsUpdated method, of class Tournament.
     */
    @Test
    public void testIsTeamsUpdated() {
        System.out.println("isTeamsUpdated");
        Tournament instance = null;
        boolean expResult = false;
        boolean result = instance.isTeamsUpdated();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTeamsUpdated method, of class Tournament.
     */
    @Test
    public void testSetTeamsUpdated() {
        System.out.println("setTeamsUpdated");
        boolean teamsUpdated = false;
        Tournament instance = null;
        instance.setTeamsUpdated(teamsUpdated);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isRoundsUpdated method, of class Tournament.
     */
    @Test
    public void testIsRoundsUpdated() {
        System.out.println("isRoundsUpdated");
        Tournament instance = null;
        boolean expResult = false;
        boolean result = instance.isRoundsUpdated();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setRoundsUpdated method, of class Tournament.
     */
    @Test
    public void testSetRoundsUpdated() {
        System.out.println("setRoundsUpdated");
        boolean roundsUpdated = false;
        Tournament instance = null;
        instance.setRoundsUpdated(roundsUpdated);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of pushClans method, of class Tournament.
     */
    @Test
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
    @Test
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
    @Test
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
    @Test
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
    @Test
    public void testResetUpdated() {
        System.out.println("resetUpdated");
        Tournament instance = null;
        instance.resetUpdated();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTeamsNames method, of class Tournament.
     */
    @Test
    public void testGetTeamsNames() {
        System.out.println("getTeamsNames");
        Tournament instance = null;
        String[] expResult = null;
        String[] result = instance.getTeamsNames();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getActiveCompetitorsCount method, of class Tournament.
     */
    @Test
    public void testGetActiveCompetitorsCount() {
        System.out.println("getActiveCompetitorsCount");
        Tournament instance = null;
        int expResult = 0;
        int result = instance.getActiveCompetitorsCount();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
