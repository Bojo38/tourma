/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bb.tourma.tableModel;

import bb.tourma.tableModel.MjtAnnexRankClan;
import java.awt.Component;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JTable;
import org.testng.Assert;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import bb.tourma.data.Clan;
import bb.tourma.data.Criterion;
import bb.tourma.data.Round;
import bb.tourma.data.Tournament;
import bb.tourma.data.ranking.AnnexClanRanking;
import bb.tourma.languages.Translate;
import bb.tourma.utility.StringConstants;
/**
 *
 * @author WFMJ7631
 */
public class MjtAnnexRankClanNGTest {
    
    
    static ArrayList<Clan> clans=new ArrayList<>();
    static MjtAnnexRankClan instance;
    static Criterion crit;
    
    
    public MjtAnnexRankClanNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        Tournament.getTournament().loadXML(new File("./test/clan.xml"));
        
        for (int i=0; i<Tournament.getTournament().getClansCount(); i++)
        {
            clans.add(Tournament.getTournament().getClan(i));
        }
        
        crit=Tournament.getTournament().getParams().getCriterion(0);
        Round r=Tournament.getTournament().getRound(0);
        AnnexClanRanking acr=r.getRankings(false).getClanRankingSet().getAnnexPosRanking().get(crit);
        instance = new MjtAnnexRankClan(acr,true,0,acr.getCount());
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
     * Test of sortDatas method, of class MjtAnnexRankClan.
     */
    @Test
    public void testSortDatas() {
        System.out.println("sortDatas");        
        assertEquals(instance.getRowCount(),clans.size());  
    }
    

    /**
     * Test of getColumnCount method, of class MjtAnnexRankClan.
     */
    @Test
    public void testGetColumnCount() {
        System.out.println("getColumnCount");
        int expResult = 3;
        int result = instance.getColumnCount();
        assertEquals(result, expResult);
        
    }

    /**
     * Test of getColumnName method, of class MjtAnnexRankClan.
     */
    @Test
    public void testGetColumnName() {
        System.out.println("getColumnName");
        
        String name=instance.getColumnName(0);
        assertEquals(name, StringConstants.CS_HASH);
        
        name=instance.getColumnName(1);
        assertEquals(name, Translate.translate(Translate.CS_Clan));
        
        name=instance.getColumnName(2);
        assertEquals(name, crit.getName());
    }

    /**
     * Test of getValueAt method, of class MjtAnnexRankClan.
     */
    @Test
    public void testGetValueAt() {
        System.out.println("getValueAt");
        for (int i=0; i<instance.getColumnCount(); i++)
        {
            for(int j=0; j<instance.getRowCount(); j++)
            {
                Object obj= instance.getValueAt(j, i);
                Assert.assertTrue(obj instanceof String);

            }
        }
    }

    /**
     * Test of getTableCellRendererComponent method, of class MjtAnnexRankClan.
     */
    @Test
    public void testGetTableCellRendererComponent() {
        System.out.println("getTableCellRendererComponent");
        JTable table = new JTable();
        table.setModel(instance);
        for (int i=0; i<instance.getColumnCount(); i++)
        {
            for(int j=0; j<instance.getRowCount(); j++)
            {
                Component result = instance.getTableCellRendererComponent(table, instance.getValueAt(j, i), false, false, j, i);
                Assert.assertTrue(result instanceof JLabel);
            }
        }
    }
    
}
