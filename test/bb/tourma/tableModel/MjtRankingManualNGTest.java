/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bb.tourma.tableModel;

import bb.tourma.tableModel.MjtRankingManual;
import java.awt.Component;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JTable;
import org.testng.Assert;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import bb.tourma.data.Coach;
import bb.tourma.data.Competitor;
import bb.tourma.data.Tournament;
import bb.tourma.data.ranking.ManualRanking;

/**
 *
 * @author WFMJ7631
 */
public class MjtRankingManualNGTest {

    static ArrayList<Coach> coachs = new ArrayList<>();
    static MjtRankingManual instance;

    public MjtRankingManualNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        Tournament.getTournament().loadXML(new File("./test/coach.xml"));

        for (int i = 0; i < Tournament.getTournament().getCoachsCount(); i++) {
            coachs.add(Tournament.getTournament().getCoach(i));
        }
        //crit=Tournament.getTournament().getParams().getCriterion(0);
        ManualRanking ranking=new ManualRanking(0, 1, 2,3, 4, 5, coachs, true);
        instance=new MjtRankingManual(ranking, 0, coachs.size());
                
        
        /*instance = new MjtRankingManual(Tournament.getTournament().getRoundsCount() - 1,
                Tournament.getTournament().getParams().getRankingIndiv1(),
                Tournament.getTournament().getParams().getRankingIndiv2(),
                Tournament.getTournament().getParams().getRankingIndiv3(),
                Tournament.getTournament().getParams().getRankingIndiv4(),
                Tournament.getTournament().getParams().getRankingIndiv5(),
                coachs,
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
     * Test of getColumnCount method, of class MjtRankingManual.
     */
    @Test
    public void testGetColumnCount() {
        System.out.println("getColumnCount");
        int expResult = 0;
        int result = instance.getColumnCount();
        assertEquals(result, expResult);
    }

    /**
     * Test of getColumnName method, of class MjtRankingManual.
     */
    @Test
    public void testGetColumnName() {
        System.out.println("getColumnName");
        String expResult = "";
        String result = instance.getColumnName(0);
        assertEquals(result, expResult);

    }

    /**
     * Test of getValueAt method, of class MjtRankingManual.
     */
    @Test
    public void testGetValueAt() {
        System.out.println("getValueAt");
        Object expResult = "";
        Object result = instance.getValueAt(0, 0);
        assertEquals(result, expResult);
    }

    /**
     * Test of getTableCellRendererComponent method, of class MjtRankingManual.
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
