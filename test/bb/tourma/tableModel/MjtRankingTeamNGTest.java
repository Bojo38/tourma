/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bb.tourma.tableModel;

import bb.tourma.tableModel.MjtRanking;
import bb.tourma.tableModel.MjtRankingTeam;
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
import static org.testng.Assert.fail;
import org.testng.annotations.Test;
import bb.tourma.data.ObjectRanking;
import bb.tourma.data.Team;
import bb.tourma.data.Tournament;
import bb.tourma.languages.Translate;
import bb.tourma.utility.StringConstants;
/**
 *
 * @author WFMJ7631
 */
public class MjtRankingTeamNGTest {
    
    static ArrayList<Team> teams = new ArrayList<>();
    static MjtRankingTeam instance;
    
    public MjtRankingTeamNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        Tournament.getTournament().loadXML(new File("./test/team.xml"));

        for (int i = 0; i < Tournament.getTournament().getTeamsCount(); i++) {
            teams.add(Tournament.getTournament().getTeam(i));
        }
        //crit=Tournament.getTournament().getParams().getCriterion(0);
        /*instance = new MjtRankingTeam(
                Tournament.getTournament().getParams().isTeamVictoryOnly(),
                Tournament.getTournament().getRoundsCount() - 1,
                teams,
                false);*/
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
     * Test of getColumnCount method, of class MjtRankingTeam.
     */
    @Test
    public void testGetColumnCount() {
        System.out.println("getColumnCount");
        int expResult = 2 + Tournament.getTournament().getRankingTypes(true).size();
        int result = instance.getColumnCount();
        assertEquals(result, expResult);
    }

    /**
     * Test of getColumnName method, of class MjtRankingTeam.
     */
    @Test
    public void testGetColumnName() {
        System.out.println("getColumnName");
        String name = instance.getColumnName(0);
        assertEquals(name, StringConstants.CS_HASH);

        name = instance.getColumnName(1);
        assertEquals(name, Translate.translate(Translate.CS_Team));

        /*ArrayList<Integer> rt = Tournament.getTournament().getRankingTypes(true);
        for (int i = 0; i < rt.size(); i++) {
            name = instance.getColumnName(2 + i);
            assertEquals(name, MjtRanking.getRankingString(rt.get(i)));
        }*/
    }

    /**
     * Test of getValueAt method, of class MjtRankingTeam.
     */
    @Test
    public void testGetValueAt() {
        System.out.println("getValueAt");
        for (int i = 0; i < instance.getColumnCount(); i++) {
            for (int j = 0; j < instance.getRowCount(); j++) {
                Object obj = instance.getValueAt(j, i);
                if (i == 1) {
                    Assert.assertTrue(obj instanceof String);
                } else {
                    Assert.assertTrue(obj instanceof Integer);
                }
            }
        }
    }

    /**
     * Test of getTableCellRendererComponent method, of class MjtRankingTeam.
     */
    @Test
    public void testGetTableCellRendererComponent() {
        System.out.println("getTableCellRendererComponent");
        JTable table = new JTable();
        table.setModel(instance);
        for (int i = 0; i < instance.getColumnCount(); i++) {
            for (int j = 0; j < instance.getRowCount(); j++) {
                Component result = instance.getTableCellRendererComponent(table, instance.getValueAt(j, i), false, false, j, i);
                Assert.assertTrue(result instanceof JLabel);
            }
        }
    }

    
}