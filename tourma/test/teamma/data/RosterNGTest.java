/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teamma.data;

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
public class RosterNGTest {
    
    public RosterNGTest() {
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
     * Test of getValue method, of class Roster.
     */
    @Test
    public void testGetValue() {
        System.out.println("getValue");
        boolean bWithSkill = false;
        Roster instance = new Roster();
        int expResult = 0;
        int result = instance.getValue(bWithSkill);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getXMLElement method, of class Roster.
     */
    @Test
    public void testGetXMLElement() {
        System.out.println("getXMLElement");
        Roster instance = new Roster();
        Element expResult = null;
        Element result = instance.getXMLElement();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setXMLElement method, of class Roster.
     */
    @Test
    public void testSetXMLElement() {
        System.out.println("setXMLElement");
        Element e = null;
        Roster instance = new Roster();
        instance.setXMLElement(e);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCheerleaders method, of class Roster.
     */
    @Test
    public void testGetCheerleaders() {
        System.out.println("getCheerleaders");
        Roster instance = new Roster();
        int expResult = 0;
        int result = instance.getCheerleaders();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCheerleaders method, of class Roster.
     */
    @Test
    public void testSetCheerleaders() {
        System.out.println("setCheerleaders");
        int _cheerleaders = 0;
        Roster instance = new Roster();
        instance.setCheerleaders(_cheerleaders);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAssistants method, of class Roster.
     */
    @Test
    public void testGetAssistants() {
        System.out.println("getAssistants");
        Roster instance = new Roster();
        int expResult = 0;
        int result = instance.getAssistants();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setAssistants method, of class Roster.
     */
    @Test
    public void testSetAssistants() {
        System.out.println("setAssistants");
        int _assistants = 0;
        Roster instance = new Roster();
        instance.setAssistants(_assistants);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getExtrarerolls method, of class Roster.
     */
    @Test
    public void testGetExtrarerolls() {
        System.out.println("getExtrarerolls");
        Roster instance = new Roster();
        int expResult = 0;
        int result = instance.getExtrarerolls();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setExtrarerolls method, of class Roster.
     */
    @Test
    public void testSetExtrarerolls() {
        System.out.println("setExtrarerolls");
        int _extrarerolls = 0;
        Roster instance = new Roster();
        instance.setExtrarerolls(_extrarerolls);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLocalapothecary method, of class Roster.
     */
    @Test
    public void testGetLocalapothecary() {
        System.out.println("getLocalapothecary");
        Roster instance = new Roster();
        int expResult = 0;
        int result = instance.getLocalapothecary();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setLocalapothecary method, of class Roster.
     */
    @Test
    public void testSetLocalapothecary() {
        System.out.println("setLocalapothecary");
        int _localapothecary = 0;
        Roster instance = new Roster();
        instance.setLocalapothecary(_localapothecary);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBloodweiserbabes method, of class Roster.
     */
    @Test
    public void testGetBloodweiserbabes() {
        System.out.println("getBloodweiserbabes");
        Roster instance = new Roster();
        int expResult = 0;
        int result = instance.getBloodweiserbabes();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setBloodweiserbabes method, of class Roster.
     */
    @Test
    public void testSetBloodweiserbabes() {
        System.out.println("setBloodweiserbabes");
        int _bloodweiserbabes = 0;
        Roster instance = new Roster();
        instance.setBloodweiserbabes(_bloodweiserbabes);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCorruptions method, of class Roster.
     */
    @Test
    public void testGetCorruptions() {
        System.out.println("getCorruptions");
        Roster instance = new Roster();
        int expResult = 0;
        int result = instance.getCorruptions();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCorruptions method, of class Roster.
     */
    @Test
    public void testSetCorruptions() {
        System.out.println("setCorruptions");
        int _corruptions = 0;
        Roster instance = new Roster();
        instance.setCorruptions(_corruptions);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isChef method, of class Roster.
     */
    @Test
    public void testIsChef() {
        System.out.println("isChef");
        Roster instance = new Roster();
        boolean expResult = false;
        boolean result = instance.isChef();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setChef method, of class Roster.
     */
    @Test
    public void testSetChef() {
        System.out.println("setChef");
        boolean _chef = false;
        Roster instance = new Roster();
        instance.setChef(_chef);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isIgor method, of class Roster.
     */
    @Test
    public void testIsIgor() {
        System.out.println("isIgor");
        Roster instance = new Roster();
        boolean expResult = false;
        boolean result = instance.isIgor();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setIgor method, of class Roster.
     */
    @Test
    public void testSetIgor() {
        System.out.println("setIgor");
        boolean _igor = false;
        Roster instance = new Roster();
        instance.setIgor(_igor);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isWizard method, of class Roster.
     */
    @Test
    public void testIsWizard() {
        System.out.println("isWizard");
        Roster instance = new Roster();
        boolean expResult = false;
        boolean result = instance.isWizard();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setWizard method, of class Roster.
     */
    @Test
    public void testSetWizard() {
        System.out.println("setWizard");
        boolean _wizard = false;
        Roster instance = new Roster();
        instance.setWizard(_wizard);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCards method, of class Roster.
     */
    @Test
    public void testGetCards() {
        System.out.println("getCards");
        Roster instance = new Roster();
        int expResult = 0;
        int result = instance.getCards();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCards method, of class Roster.
     */
    @Test
    public void testSetCards() {
        System.out.println("setCards");
        int _cards = 0;
        Roster instance = new Roster();
        instance.setCards(_cards);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getChampion method, of class Roster.
     */
    @Test
    public void testGetChampion() {
        System.out.println("getChampion");
        int i = 0;
        Roster instance = new Roster();
        StarPlayer expResult = null;
        StarPlayer result = instance.getChampion(i);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeChampion method, of class Roster.
     */
    @Test
    public void testRemoveChampion() {
        System.out.println("removeChampion");
        int i = 0;
        Roster instance = new Roster();
        instance.removeChampion(i);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addChampion method, of class Roster.
     */
    @Test
    public void testAddChampion() {
        System.out.println("addChampion");
        StarPlayer sp = null;
        Roster instance = new Roster();
        instance.addChampion(sp);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getChampionCount method, of class Roster.
     */
    @Test
    public void testGetChampionCount() {
        System.out.println("getChampionCount");
        Roster instance = new Roster();
        int expResult = 0;
        int result = instance.getChampionCount();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRoster method, of class Roster.
     */
    @Test
    public void testGetRoster() {
        System.out.println("getRoster");
        Roster instance = new Roster();
        RosterType expResult = null;
        RosterType result = instance.getRoster();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setRoster method, of class Roster.
     */
    @Test
    public void testSetRoster() {
        System.out.println("setRoster");
        RosterType _roster = null;
        Roster instance = new Roster();
        instance.setRoster(_roster);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPlayer method, of class Roster.
     */
    @Test
    public void testGetPlayer() {
        System.out.println("getPlayer");
        int i = 0;
        Roster instance = new Roster();
        Player expResult = null;
        Player result = instance.getPlayer(i);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removePlayer method, of class Roster.
     */
    @Test
    public void testRemovePlayer() {
        System.out.println("removePlayer");
        int i = 0;
        Roster instance = new Roster();
        instance.removePlayer(i);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPlayerCount method, of class Roster.
     */
    @Test
    public void testGetPlayerCount() {
        System.out.println("getPlayerCount");
        Roster instance = new Roster();
        int expResult = 0;
        int result = instance.getPlayerCount();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addPlayer method, of class Roster.
     */
    @Test
    public void testAddPlayer() {
        System.out.println("addPlayer");
        Player p = null;
        Roster instance = new Roster();
        instance.addPlayer(p);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of clearPlayers method, of class Roster.
     */
    @Test
    public void testClearPlayers() {
        System.out.println("clearPlayers");
        Roster instance = new Roster();
        instance.clearPlayers();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRerolls method, of class Roster.
     */
    @Test
    public void testGetRerolls() {
        System.out.println("getRerolls");
        Roster instance = new Roster();
        int expResult = 0;
        int result = instance.getRerolls();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setRerolls method, of class Roster.
     */
    @Test
    public void testSetRerolls() {
        System.out.println("setRerolls");
        int _rerolls = 0;
        Roster instance = new Roster();
        instance.setRerolls(_rerolls);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isApothecary method, of class Roster.
     */
    @Test
    public void testIsApothecary() {
        System.out.println("isApothecary");
        Roster instance = new Roster();
        boolean expResult = false;
        boolean result = instance.isApothecary();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setApothecary method, of class Roster.
     */
    @Test
    public void testSetApothecary() {
        System.out.println("setApothecary");
        boolean _apothecary = false;
        Roster instance = new Roster();
        instance.setApothecary(_apothecary);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFanfactor method, of class Roster.
     */
    @Test
    public void testGetFanfactor() {
        System.out.println("getFanfactor");
        Roster instance = new Roster();
        int expResult = 0;
        int result = instance.getFanfactor();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setFanfactor method, of class Roster.
     */
    @Test
    public void testSetFanfactor() {
        System.out.println("setFanfactor");
        int _fanfactor = 0;
        Roster instance = new Roster();
        instance.setFanfactor(_fanfactor);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
