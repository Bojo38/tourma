/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teamma.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.testng.Assert;
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
public class RosterNGTest {

    private static LRB lrb;

    public RosterNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        lrb = LRB.getLRB();
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    private Roster roster = null;

    @BeforeMethod
    public void setUpMethod() throws Exception {
        final SAXBuilder sxb = new SAXBuilder();
        final org.jdom2.Document document = sxb.build(new File("test/necros.xml"));
        final Element racine = document.getRootElement();
        roster = new Roster();
        roster.setXMLElement(racine);

    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }

    /**
     * Test of getValue method, of class Roster.
     */
    @Test
    public void testGetValue() {
        System.out.println("getValue");
        if (roster == null) {
            fail("No roster loaded");
        }
        int value = roster.getValue(false);
        Assert.assertTrue(value > 0);
        int value2 = roster.getValue(true);
        Assert.assertTrue(value2 > value);
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
     * Test of getXMLElement method, of class Roster.
     */
    @Test
    public void testGetXMLElement() {
        try {
            System.out.println("getXMLElement");
            Element document = roster.getXMLElement();

            FileOutputStream os = null;
            final XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());

            File f = new File("necros_tmp.xml");
            os = new FileOutputStream(f);
            sortie.output(document, os);
            os.close();

            Assert.assertTrue(compareTwoFiles("necros_tmp.xml", "test/necros.xml"));

            Files.delete(f.toPath());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RosterNGTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RosterNGTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of setXMLElement method, of class Roster.
     */
    @Test
    public void testSetXMLElement() {
        System.out.println("setXMLElement");
       if (roster == null) {
            fail("Roster not loaded");
        } else {
            try {
                final SAXBuilder sxb = new SAXBuilder();
                final org.jdom2.Document document = sxb.build(new File("test/necros.xml"));
                final Element racine = document.getRootElement();
                Roster tmp = new Roster();
                tmp.setXMLElement(racine);
                Assert.assertTrue(roster.getAssistants()==tmp.getAssistants());
                Assert.assertTrue(roster.getBloodweiserbabes()==tmp.getBloodweiserbabes());
                Assert.assertTrue(roster.getCards()==tmp.getCards());
                Assert.assertTrue(roster.getChampionCount()==tmp.getChampionCount());
                Assert.assertTrue(roster.getCheerleaders()==tmp.getCheerleaders());
                Assert.assertTrue(roster.getCorruptions()==tmp.getCorruptions());
                Assert.assertTrue(roster.getExtrarerolls()==tmp.getExtrarerolls());
                Assert.assertTrue(roster.getFanfactor()==tmp.getFanfactor());
                Assert.assertTrue(roster.getLocalapothecary()==tmp.getLocalapothecary());
                Assert.assertTrue(roster.getPlayerCount()==tmp.getPlayerCount());
                Assert.assertTrue(roster.getRerolls()==tmp.getRerolls());
                Assert.assertTrue(roster.getRoster()==tmp.getRoster());
                Assert.assertTrue(roster.getValue(true)==tmp.getValue(true));
                Assert.assertTrue(roster.getValue(false)==tmp.getValue(false));
                
            } catch (JDOMException ex) {
                Logger.getLogger(RosterNGTest.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(RosterNGTest.class.getName()).log(Level.SEVERE, null, ex);
            }
           
       }
    }

    /**
     * Test of getCheerleaders method, of class Roster.
     */
    @Test
    public void testGetCheerleaders() {
        System.out.println("getCheerleaders");
        if (roster == null) {
            fail("Roster not loaded");
        } else {
            int nb = roster.getCheerleaders();
            Assert.assertTrue(nb > 0);
        }
    }

    /**
     * Test of setCheerleaders method, of class Roster.
     */
    @Test
    public void testSetCheerleaders() {
        System.out.println("setCheerleaders");
        if (roster == null) {
            fail("Roster not loaded");
        } else {
            int save = roster.getCheerleaders();
            roster.setCheerleaders(4);
            int nb = roster.getCheerleaders();
            Assert.assertTrue(nb == 4);
            roster.setCheerleaders(save);
        }
    }

    /**
     * Test of getAssistants method, of class Roster.
     */
    @Test
    public void testGetAssistants() {
        System.out.println("getAssistants");
        if (roster == null) {
            fail("Roster not loaded");
        } else {
            int nb = roster.getAssistants();
            Assert.assertTrue(nb > 0);
        }
    }

    /**
     * Test of setAssistants method, of class Roster.
     */
    @Test
    public void testSetAssistants() {
        System.out.println("setAssistants");
        if (roster == null) {
            fail("Roster not loaded");
        } else {
            int save = roster.getAssistants();
            roster.setAssistants(4);
            int nb = roster.getAssistants();
            Assert.assertTrue(nb == 4);
            roster.setAssistants(save);
        }
    }

    /**
     * Test of getExtrarerolls method, of class Roster.
     */
    @Test
    public void testGetExtrarerolls() {
        System.out.println("getExtrarerolls");
        if (roster == null) {
            fail("Roster not loaded");
        } else {
            int nb = roster.getExtrarerolls();
            Assert.assertTrue(nb > 0);
        }
    }

    /**
     * Test of setExtrarerolls method, of class Roster.
     */
    @Test
    public void testSetExtrarerolls() {
        System.out.println("setExtrarerolls");
        if (roster == null) {
            fail("Roster not loaded");
        } else {
            int save = roster.getExtrarerolls();
            roster.setExtrarerolls(4);
            int nb = roster.getExtrarerolls();
            Assert.assertTrue(nb == 4);
            roster.setExtrarerolls(save);
        }
    }

    /**
     * Test of getLocalapothecary method, of class Roster.
     */
    @Test
    public void testGetLocalapothecary() {
        System.out.println("getLocalapothecary");
        if (roster == null) {
            fail("Roster not loaded");
        } else {
            int nb = roster.getLocalapothecary();
            Assert.assertTrue(nb > 0);
        }
    }

    /**
     * Test of setLocalapothecary method, of class Roster.
     */
    @Test
    public void testSetLocalapothecary() {
        System.out.println("setLocalapothecary");
        if (roster == null) {
            fail("Roster not loaded");
        } else {
            int save = roster.getExtrarerolls();
            roster.setExtrarerolls(4);
            int nb = roster.getExtrarerolls();
            Assert.assertTrue(nb == 4);
            roster.setExtrarerolls(save);
        }
    }

    /**
     * Test of getBloodweiserbabes method, of class Roster.
     */
    @Test
    public void testGetBloodweiserbabes() {
        System.out.println("getBloodweiserbabes");
        if (roster == null) {
            fail("Roster not loaded");
        } else {
            int nb = roster.getBloodweiserbabes();
            Assert.assertTrue(nb > 0);
        }
    }

    /**
     * Test of setBloodweiserbabes method, of class Roster.
     */
    @Test
    public void testSetBloodweiserbabes() {
        System.out.println("setBloodweiserbabes");
        if (roster == null) {
            fail("Roster not loaded");
        } else {
            int save = roster.getBloodweiserbabes();
            roster.setBloodweiserbabes(4);
            int nb = roster.getBloodweiserbabes();
            Assert.assertTrue(nb == 4);
            roster.setBloodweiserbabes(save);
        }
    }

    /**
     * Test of getCorruptions method, of class Roster.
     */
    @Test
    public void testGetCorruptions() {
        System.out.println("getCorruptions");
        if (roster == null) {
            fail("Roster not loaded");
        } else {
            int nb = roster.getCorruptions();
            Assert.assertTrue(nb > 0);
        }
    }

    /**
     * Test of setCorruptions method, of class Roster.
     */
    @Test
    public void testSetCorruptions() {
        System.out.println("setCorruptions");
        if (roster == null) {
            fail("Roster not loaded");
        } else {
            int save = roster.getCorruptions();
            roster.setCorruptions(4);
            int nb = roster.getCorruptions();
            Assert.assertTrue(nb == 4);
            roster.setCorruptions(save);
        }
    }

    /**
     * Test of isChef method, of class Roster.
     */
    @Test
    public void testIsChef() {
        System.out.println("isChef");
        if (roster == null) {
            fail("Roster not loaded");
        } else {
            Assert.assertTrue(roster.isChef());
        }
    }

    /**
     * Test of setChef method, of class Roster.
     */
    @Test
    public void testSetChef() {
        System.out.println("setChef");
        if (roster == null) {
            fail("Roster not loaded");
        } else {
            boolean save = roster.isChef();
            roster.setChef(false);
            Assert.assertFalse(roster.isChef());
            roster.setChef(save);
        }
    }

    /**
     * Test of isIgor method, of class Roster.
     */
    @Test
    public void testIsIgor() {
        System.out.println("isIgor");
        if (roster == null) {
            fail("Roster not loaded");
        } else {
            Assert.assertTrue(roster.isIgor());
        }
    }

    /**
     * Test of setIgor method, of class Roster.
     */
    @Test
    public void testSetIgor() {
        System.out.println("setIgor");
        if (roster == null) {
            fail("Roster not loaded");
        } else {
            boolean save = roster.isIgor();
            roster.setIgor(false);
            Assert.assertFalse(roster.isIgor());
            roster.setIgor(save);
        }
    }

    /**
     * Test of isWizard method, of class Roster.
     */
    @Test
    public void testIsWizard() {
        System.out.println("isWizard");
        if (roster == null) {
            fail("Roster not loaded");
        } else {
            Assert.assertTrue(roster.isWizard());
        }
    }

    /**
     * Test of setWizard method, of class Roster.
     */
    @Test
    public void testSetWizard() {
        System.out.println("setWizard");
        if (roster == null) {
            fail("Roster not loaded");
        } else {
            boolean save = roster.isWizard();
            roster.setWizard(false);
            Assert.assertFalse(roster.isWizard());
            roster.setWizard(save);
        }
    }

    /**
     * Test of getCards method, of class Roster.
     */
    @Test
    public void testGetCards() {
        System.out.println("getCards");
        if (roster == null) {
            fail("Roster not loaded");
        } else {
            int nb = roster.getCards();
            Assert.assertTrue(nb > 0);
        }
    }

    /**
     * Test of setCards method, of class Roster.
     */
    @Test
    public void testSetCards() {
        System.out.println("setCards");
        if (roster == null) {
            fail("Roster not loaded");
        } else {
            int save = roster.getCards();
            roster.setCards(4);
            int nb = roster.getCards();
            Assert.assertTrue(nb == 4);
            roster.setCards(save);
        }
    }

    /**
     * Test of getChampion method, of class Roster.
     */
    @Test
    public void testGetChampion() {
        System.out.println("getChampion");
        if (lrb == null) {
            fail("lrb is null");
        }
        if (lrb.getRosterTypeCount() == 0) {
            fail("No roster type in LRB");
        }
        if (roster == null) {
            fail("Roster is null");
        }

        if (roster.getRoster() == null) {
            fail("Roster type not set");
        }
        if (roster.getRoster().getAvailableStarplayerCount() == 0) {
            fail("No availabe start player for the roster");
        }
        for (int i = 0; i < roster.getChampionCount(); i++) {
            StarPlayer sp = roster.getChampion(i);
            Assert.assertNotNull(sp);
        }
    }

    /**
     * Test of removeChampion method, of class Roster.
     */
    @Test
    public void testRemoveChampion() {
        System.out.println("removeChampion");
        if (lrb == null) {
            fail("lrb is null");
        }
        if (lrb.getRosterTypeCount() == 0) {
            fail("No roster type in LRB");
        }
        if (roster == null) {
            fail("Roster is null");
        }

        if (roster.getRoster() == null) {
            fail("Roster type not set");
        }
        if (roster.getRoster().getAvailableStarplayerCount() == 0) {
            fail("No availabe start player for the roster");
        }
        StarPlayer sp = roster.getRoster().getAvailableStarplayer(0);
        int nb = roster.getChampionCount();
        roster.addChampion(sp);
        assertEquals(nb + 1, roster.getChampionCount());
        roster.removeChampion(nb);
        assertEquals(nb, roster.getChampionCount());
    }

    /**
     * Test of addChampion method, of class Roster.
     */
    @Test
    public void testAddChampion() {
        System.out.println("addChampion");
        if (lrb == null) {
            fail("lrb is null");
        }
        if (lrb.getRosterTypeCount() == 0) {
            fail("No roster type in LRB");
        }
        if (roster == null) {
            fail("Roster is null");
        }

        if (roster.getRoster() == null) {
            fail("Roster type not set");
        }
        if (roster.getRoster().getAvailableStarplayerCount() == 0) {
            fail("No availabe start player for the roster");
        }
        StarPlayer sp = roster.getRoster().getAvailableStarplayer(0);
        int nb = roster.getChampionCount();
        roster.addChampion(sp);
        assertEquals(nb + 1, roster.getChampionCount());
        roster.removeChampion(nb);
        assertEquals(nb, roster.getChampionCount());
    }

    /**
     * Test of getChampionCount method, of class Roster.
     */
    @Test
    public void testGetChampionCount() {
        System.out.println("getChampionCount");
        if (lrb == null) {
            fail("lrb is null");
        }
        if (lrb.getRosterTypeCount() == 0) {
            fail("No roster type in LRB");
        }
        if (roster == null) {
            fail("Roster is null");
        }

        if (roster.getRoster() == null) {
            fail("Roster type not set");
        }
        if (roster.getRoster().getAvailableStarplayerCount() == 0) {
            fail("No availabe start player for the roster");
        }
        StarPlayer sp = roster.getRoster().getAvailableStarplayer(0);
        int nb = roster.getChampionCount();
        roster.addChampion(sp);
        assertEquals(nb + 1, roster.getChampionCount());
        roster.removeChampion(nb);
        assertEquals(nb, roster.getChampionCount());
    }

    /**
     * Test of getRoster method, of class Roster.
     */
    @Test
    public void testGetRoster() {
        System.out.println("getRoster");
        if (roster == null) {
            fail("Roster not loaded");
        }

        RosterType save = roster.getRoster();

        Assert.assertNotNull(save);

    }

    /**
     * Test of setRoster method, of class Roster.
     */
    @Test
    public void testSetRoster() {
        System.out.println("setRoster");
        if (lrb == null) {
            fail("lrb is null");
        }
        if (lrb.getRosterTypeCount() == 0) {
            fail("No roster type in LRB");
        }

        RosterType _roster = lrb.getRosterType(0);

        RosterType save = roster.getRoster();
        roster.setRoster(_roster);
        assertEquals(_roster, roster.getRoster());
        roster.setRoster(save);
    }

    /**
     * Test of getPlayer method, of class Roster.
     */
    @Test
    public void testGetPlayer() {
        System.out.println("getPlayer");
       if (lrb == null) {
            fail("lrb is null");
        }
        if (lrb.getRosterTypeCount() == 0) {
            fail("No roster type in LRB");
        }
        if (roster == null) {
            fail("Roster is null");
        }

        if (roster.getRoster() == null) {
            fail("Roster type not set");
        }
        if (roster.getPlayerCount() == 0) {
            fail("No player for the roster");
        }
        int nb = roster.getPlayerCount();
        for (int i = 0; i < nb; i++) {
            Player pl=roster.getPlayer(i);
            Assert.assertNotNull(pl);
        }
        
    }

    /**
     * Test of removePlayer method, of class Roster.
     */
    @Test
    public void testRemovePlayer() {
        System.out.println("removePlayer");
       if (lrb == null) {
            fail("lrb is null");
        }
        if (lrb.getRosterTypeCount() == 0) {
            fail("No roster type in LRB");
        }
        if (roster == null) {
            fail("Roster is null");
        }

        if (roster.getRoster() == null) {
            fail("Roster type not set");
        }
        if (roster.getRoster().getPlayerTypeCount() == 0) {
            fail("No player type for the roster");
        }
        PlayerType pt = roster.getRoster().getPlayerType(0);
        Player pl = new Player(pt);
        int nb = roster.getPlayerCount();
        roster.addPlayer(pl);
        assertEquals(nb + 1, roster.getPlayerCount());
        roster.removePlayer(nb);
        assertEquals(nb, roster.getPlayerCount());
    }

    /**
     * Test of getPlayerCount method, of class Roster.
     */
    @Test
    public void testGetPlayerCount() {
        System.out.println("getPlayerCount");
        if (lrb == null) {
            fail("lrb is null");
        }
        if (lrb.getRosterTypeCount() == 0) {
            fail("No roster type in LRB");
        }
        if (roster == null) {
            fail("Roster is null");
        }

        if (roster.getRoster() == null) {
            fail("Roster type not set");
        }
        if (roster.getRoster().getPlayerTypeCount() == 0) {
            fail("No player type for the roster");
        }
        PlayerType pt = roster.getRoster().getPlayerType(0);
        Player pl = new Player(pt);
        int nb = roster.getPlayerCount();
        roster.addPlayer(pl);
        assertEquals(nb + 1, roster.getPlayerCount());
        roster.removePlayer(nb);
        assertEquals(nb, roster.getPlayerCount());
    }

    /**
     * Test of addPlayer method, of class Roster.
     */
    @Test
    public void testAddPlayer() {
        System.out.println("addPlayer");
        if (lrb == null) {
            fail("lrb is null");
        }
        if (lrb.getRosterTypeCount() == 0) {
            fail("No roster type in LRB");
        }
        if (roster == null) {
            fail("Roster is null");
        }

        if (roster.getRoster() == null) {
            fail("Roster type not set");
        }
        if (roster.getRoster().getPlayerTypeCount() == 0) {
            fail("No player type for the roster");
        }
        PlayerType pt = roster.getRoster().getPlayerType(0);
        Player pl = new Player(pt);
        int nb = roster.getPlayerCount();
        roster.addPlayer(pl);
        assertEquals(nb + 1, roster.getPlayerCount());
        roster.removePlayer(nb);
    }

    /**
     * Test of clearPlayers method, of class Roster.
     */
    @Test
    public void testClearPlayers() {
        System.out.println("clearPlayers");
        if (lrb == null) {
            fail("lrb is null");
        }
        if (lrb.getRosterTypeCount() == 0) {
            fail("No roster type in LRB");
        }
        if (roster == null) {
            fail("Roster is null");
        }

        if (roster.getRoster() == null) {
            fail("Roster type not set");
        }
        if (roster.getPlayerCount() == 0) {
            fail("No player for the roster");
        }
        ArrayList<Player> list = new ArrayList<>();
        int nb = roster.getPlayerCount();
        for (int i = 0; i < nb; i++) {
            list.add(roster.getPlayer(i));
        }
        roster.clearPlayers();
        assertEquals(roster.getPlayerCount(), 0);
        for (Player p:list) {
            roster.addPlayer(p);
        }

    }

    /**
     * Test of getRerolls method, of class Roster.
     */
    @Test
    public void testGetRerolls() {
        System.out.println("getRerolls");
        if (roster == null) {
            fail("Roster not loaded");
        } else {
            int nb = roster.getRerolls();
            Assert.assertTrue(nb > 0);
        }
    }

    /**
     * Test of setRerolls method, of class Roster.
     */
    @Test
    public void testSetRerolls() {
        System.out.println("setRerolls");
        if (roster == null) {
            fail("Roster not loaded");
        } else {
            int save = roster.getRerolls();
            roster.setRerolls(4);
            int nb = roster.getRerolls();
            Assert.assertTrue(nb == 4);
            roster.setRerolls(save);
        }
    }

    /**
     * Test of isApothecary method, of class Roster.
     */
    @Test
    public void testIsApothecary() {
        System.out.println("isApothecary");
        if (roster == null) {
            fail("Roster not loaded");
        } else {
            Assert.assertTrue(roster.isApothecary());
        }
    }

    /**
     * Test of setApothecary method, of class Roster.
     */
    @Test
    public void testSetApothecary() {
        System.out.println("setApothecary");
        if (roster == null) {
            fail("Roster not loaded");
        } else {
            boolean save = roster.isApothecary();
            roster.setApothecary(false);
            Assert.assertFalse(roster.isApothecary());
            roster.setApothecary(save);
        }
    }

    /**
     * Test of getFanfactor method, of class Roster.
     */
    @Test
    public void testGetFanfactor() {
        System.out.println("getFanfactor");
        if (roster == null) {
            fail("Roster not loaded");
        } else {
            int nb = roster.getFanfactor();
            Assert.assertTrue(nb > 0);
        }
    }

    /**
     * Test of setFanfactor method, of class Roster.
     */
    @Test
    public void testSetFanfactor() {
        System.out.println("setFanfactor");
        if (roster == null) {
            fail("Roster not loaded");
        } else {
            int save = roster.getFanfactor();
            roster.setFanfactor(4);
            int nb = roster.getFanfactor();
            Assert.assertTrue(nb == 4);
            roster.setFanfactor(save);
        }
    }

}
