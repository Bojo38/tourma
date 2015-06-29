/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.tableModel;

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
import tourma.data.Clan;
import tourma.data.Tournament;

/**
 *
 * @author WFMJ7631
 */
public class MjtRankingClanNGTest {

    static ArrayList<Clan> clans = new ArrayList<>();
    static MjtRankingClan instance;
    //static Criteria crit;

    public MjtRankingClanNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        Tournament.getTournament().loadXML(new File("./test/clan.xml"));

        for (int i = 0; i < Tournament.getTournament().getClansCount(); i++) {
            clans.add(Tournament.getTournament().getClan(i));
        }
        //crit=Tournament.getTournament().getParams().getCriteria(0);
        instance = new MjtRankingClan(Tournament.getTournament().getRoundsCount() - 1,
                clans,
                false);
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
     * Test of sortDatas method, of class MjtRankingClan.
     */
    @Test
    public void testSortDatas() {
        System.out.println("sortDatas");
        assertEquals(instance.getRowCount(), clans.size());
        instance.sortDatas();
    }

    /**
     * Test of getColumnCount method, of class MjtRankingClan.
     */
    @Test
    public void testGetColumnCount() {
        System.out.println("getColumnCount");
        int expResult = 2+Tournament.getTournament().getRankingTypes(false).size();
        int result = instance.getColumnCount();
        assertEquals(result, expResult);
    }

    /**
     * Test of getColumnName method, of class MjtRankingClan.
     */
    @Test
    public void testGetColumnName() {
        System.out.println("getColumnName");
        int col = 0;
        MjtRankingClan instance = null;
        String expResult = "";
        String result = instance.getColumnName(col);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getValueAt method, of class MjtRankingClan.
     */
    @Test
    public void testGetValueAt() {
        System.out.println("getValueAt");
        int row = 0;
        int col = 0;
        MjtRankingClan instance = null;
        Object expResult = null;
        Object result = instance.getValueAt(row, col);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTableCellRendererComponent method, of class MjtRankingClan.
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
