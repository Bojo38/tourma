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
import tourma.data.Coach;
import tourma.data.ObjectRanking;
import tourma.data.Tournament;
import tourma.languages.Translate;
import tourma.utility.StringConstants;
/**
 *
 * @author WFMJ7631
 */
public class MjtRankingIndivNGTest {
    
    static ArrayList<Coach> coachs = new ArrayList<>();
    static MjtRankingIndiv instance;
    
    public MjtRankingIndivNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
         Tournament.getTournament().loadXML(new File("./test/coach.xml"));

        for (int i = 0; i < Tournament.getTournament().getCoachsCount(); i++) {
            coachs.add(Tournament.getTournament().getCoach(i));
        }
        instance = new MjtRankingIndiv(Tournament.getTournament().getRoundsCount() - 1,
                coachs,
                false,false);
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
     * Test of sortDatas method, of class MjtRankingIndiv.
     */
    @Test
    public void testSortDatas() {
        System.out.println("sortDatas");
        assertEquals(instance.getRowCount(), coachs.size());
        instance.sortDatas();
        for (int i=1; i<instance.getRowCount(); i++)
        {
            int val1=(Integer)instance.getValueAt(i-1, 4);
            int val2=(Integer)instance.getValueAt(i, 4);
            Assert.assertTrue(val1>=val2);
        }
    }

    /**
     * Test of getColumnCount method, of class MjtRankingIndiv.
     */
    @Test
    public void testGetColumnCount() {
        System.out.println("getColumnCount");
        int expResult = 4;
        expResult+=Tournament.getTournament().getRankingTypes(false).size();
        int result = instance.getColumnCount();
        assertEquals(result, expResult);
    }

    /**
     * Test of getColumnName method, of class MjtRankingIndiv.
     */
    @Test
    public void testGetColumnName() {
        System.out.println("getColumnName");
       String name = instance.getColumnName(0);
        assertEquals(name, StringConstants.CS_HASH);

        name = instance.getColumnName(1);        
        assertEquals(name, Translate.translate(Translate.CS_Team));
        
         name = instance.getColumnName(2);        
        assertEquals(name, Translate.translate(Translate.CS_Coach));
        
         name = instance.getColumnName(3);        
        assertEquals(name, Translate.translate(Translate.CS_Roster));

        ArrayList<Integer> rt = Tournament.getTournament().getRankingTypes(false);
        for (int i = 0; i < rt.size(); i++) {
            name = instance.getColumnName(4 + i);
            assertEquals(name, MjtRanking.getRankingString(rt.get(i)));
        }
    }

    /**
     * Test of getValueAt method, of class MjtRankingIndiv.
     */
    @Test
    public void testGetValueAt() {
        System.out.println("getValueAt");
        for (int i = 0; i < instance.getColumnCount(); i++) {
            for (int j = 0; j < instance.getRowCount(); j++) {
                Object obj = instance.getValueAt(j, i);
                if ((i == 1) ||(i == 3)||(i == 2)) {
                    Assert.assertTrue(obj instanceof String);
                } else {
                    Assert.assertTrue(obj instanceof Integer);
                }
            }
        }
    }

    /**
     * Test of getTableCellRendererComponent method, of class MjtRankingIndiv.
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

    /**
     * Test of updateHeadByHeadValue method, of class MjtRankingIndiv.
     */
    @Test
    public void testUpdateHeadByHeadValue() {
        System.out.println("updateHeadByHeadValue");
        int round_index = 0;
        int valueIndex = 0;
        ObjectRanking or1 = null;
        ObjectRanking or2 = null;
        MjtRankingIndiv instance = null;
        instance.updateHeadByHeadValue(round_index, valueIndex, or1, or2);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
