/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import java.awt.image.RenderedImage;
import java.io.File;
import java.util.ArrayList;
import org.jdom2.Element;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author WFMJ7631
 */
public class TournamentTest {
    
    public TournamentTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of resetTournament method, of class Tournament.
     */
    @Test
    public void testResetTournament() {
        System.out.println("resetTournament");
        Tournament expResult = null;
        Tournament result = Tournament.resetTournament();
        assertEquals(expResult, result);
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
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getClans method, of class Tournament.
     */
    @Test
    public void testGetClans() {
        System.out.println("getClans");
        Tournament instance = null;
        ArrayList expResult = null;
        ArrayList result = instance.getClans();
        assertEquals(expResult, result);
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
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCoach method, of class Tournament.
     */
    @Test
    public void testGetCoach() {
        System.out.println("getCoach");
        String input = "";
        Tournament instance = null;
        Coach expResult = null;
        Coach result = instance.getCoach(input);
        assertEquals(expResult, result);
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
        assertEquals(expResult, result);
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
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGroups method, of class Tournament.
     */
    @Test
    public void testGetGroups() {
        System.out.println("getGroups");
        Tournament instance = null;
        ArrayList expResult = null;
        ArrayList result = instance.getGroups();
        assertEquals(expResult, result);
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
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRounds method, of class Tournament.
     */
    @Test
    public void testGetRounds() {
        System.out.println("getRounds");
        Tournament instance = null;
        ArrayList expResult = null;
        ArrayList result = instance.getRounds();
        assertEquals(expResult, result);
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
     * Test of getRosterTranslation method, of class Tournament.
     */
    @Test
    public void testGetRosterTranslation() {
        System.out.println("getRosterTranslation");
        String source = "";
        String expResult = "";
        String result = Tournament.getRosterTranslation(source);
        assertEquals(expResult, result);
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
        assertEquals(expResult, result);
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
        assertEquals(expResult, result);
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
     * Test of exportResults method, of class Tournament.
     */
    @Test
    public void testExportResults() {
        System.out.println("exportResults");
        File file = null;
        Tournament instance = null;
        instance.exportResults(file);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of LoadXMLv2 method, of class Tournament.
     */
    @Test
    public void testLoadXMLv2() {
        System.out.println("LoadXMLv2");
        Element Root = null;
        Tournament instance = null;
        instance.LoadXMLv2(Root);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of LoadXMLv3 method, of class Tournament.
     */
    @Test
    public void testLoadXMLv3() {
        System.out.println("LoadXMLv3");
        Element racine = null;
        Tournament instance = null;
        instance.LoadXMLv3(racine);
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
     * Test of GetActiveCoachNumber method, of class Tournament.
     */
    @Test
    public void testGetActiveCoachNumber() {
        System.out.println("GetActiveCoachNumber");
        Tournament instance = null;
        int expResult = 0;
        int result = instance.GetActiveCoachNumber();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of GetActiveCoaches method, of class Tournament.
     */
    @Test
    public void testGetActiveCoaches() {
        System.out.println("GetActiveCoaches");
        Tournament instance = null;
        ArrayList expResult = null;
        ArrayList result = instance.GetActiveCoaches();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPools method, of class Tournament.
     */
    @Test
    public void testGetPools() {
        System.out.println("getPools");
        
        Tournament t=Tournament.getTournament();       
        assertNotNull(t);        
        ArrayList<Pool> pools=t.getPools();
        assertNotNull(pools);        
        
        File f=new File("C:/Perso/Tourma/test/Indiv/Indivtest_clans_groups_pools_1.xml");
        t.loadXML(f);
        
        assertTrue(t.getPools().size()==9);
        
        
    }
}
