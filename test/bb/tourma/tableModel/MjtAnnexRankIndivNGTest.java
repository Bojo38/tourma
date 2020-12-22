/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bb.tourma.tableModel;

import bb.tourma.tableModel.MjtAnnexRankIndiv;
import java.io.File;
import java.util.ArrayList;
import org.testng.Assert;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import bb.tourma.data.Coach;
import bb.tourma.data.Criterion;
import bb.tourma.data.ObjectAnnexRanking;
import bb.tourma.data.Round;
import bb.tourma.data.Tournament;
import bb.tourma.data.ranking.AnnexIndivRanking;
import bb.tourma.languages.Translate;
import bb.tourma.utility.StringConstants;

/**
 *
 * @author WFMJ7631
 */
public class MjtAnnexRankIndivNGTest {

    static ArrayList<Coach> coachs = new ArrayList<>();
    static MjtAnnexRankIndiv instance;
    static Criterion crit;

    public MjtAnnexRankIndivNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        Tournament.getTournament().loadXML(new File("./test/coach.xml"));

        for (int i = 0; i < Tournament.getTournament().getCoachsCount(); i++) {
            coachs.add(Tournament.getTournament().getCoach(i));
        }
        crit = Tournament.getTournament().getParams().getCriterion(0);
       
        
       
        
        crit=Tournament.getTournament().getParams().getCriterion(0);
        Round r=Tournament.getTournament().getRound(0);
        AnnexIndivRanking air=r.getRankings(false).getIndivRankingSet().getAnnexPosRanking().get(crit);
        instance = new MjtAnnexRankIndiv(air,true,0,air.getCount());
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
     * Test of sortDatas method, of class MjtAnnexRankIndiv.
     */
    @Test
    public void testSortDatas() {
        System.out.println("sortDatas");
        assertEquals(instance.getRowCount(),coachs.size());
        //instance.sortDatas();        
    }

    /**
     * Test of getColumnCount method, of class MjtAnnexRankIndiv.
     */
    @Test
    public void testGetColumnCount() {
        System.out.println("getColumnCount");
        int expResult = 5;
        int result = instance.getColumnCount();
        assertEquals(result, expResult);
    }

    /**
     * Test of getColumnName method, of class MjtAnnexRankIndiv.
     */
    @Test
    public void testGetColumnName() {
        System.out.println("getColumnName");
        String name=instance.getColumnName(0);
        assertEquals(name, StringConstants.CS_HASH);
        
        name=instance.getColumnName(1);
        assertEquals(name,Translate.translate(Translate.CS_Team));
        
        name=instance.getColumnName(2);
        assertEquals(name, Translate.translate(Translate.CS_Coach));
        
         name=instance.getColumnName(3);
        assertEquals(name,Translate.translate(Translate.CS_Roster));
        
        name=instance.getColumnName(4);
        assertEquals(name, crit.getName()+" "+Translate.translate(Translate.CS_Coach));
    }

    /**
     * Test of getValueAt method, of class MjtAnnexRankIndiv.
     */
    @Test
    public void testGetValueAt() {
        System.out.println("getValueAt");
        for (int i = 0; i < instance.getColumnCount(); i++) {
            for (int j = 0; j < instance.getRowCount(); j++) {
                Object obj = instance.getValueAt(j, i);
                if ((i == 0) || (i == 4)) {
                    Assert.assertTrue(obj instanceof Integer);
                } else {
                    Assert.assertTrue(obj instanceof String);
                }

            }
        }
    }

    
}
