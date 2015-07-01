/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.tableModel;

import java.awt.Component;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.JTextField;
import org.testng.Assert;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tourma.data.Team;
import tourma.data.Tournament;
import tourma.languages.Translate;
import tourma.utility.StringConstants;

/**
 *
 * @author WFMJ7631
 */
public class MjtPairsNGTest {

    static MjtPairs instance;
    static ArrayList<Team> teams1 = new ArrayList<>();
    static ArrayList<Team> teams2 = new ArrayList<>();
    static ArrayList<Boolean> mPairsDone = new ArrayList<>();

    public MjtPairsNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        Tournament.getTournament().loadXML(new File("./test/team.xml"));

        for (int i = 0; i < Tournament.getTournament().getTeamsCount() / 2; i++) {
            teams1.add(Tournament.getTournament().getTeam(2 * i));
            teams2.add(Tournament.getTournament().getTeam(2 * i + 1));
            mPairsDone.add(false);

        }
        instance = new MjtPairs(teams1, teams2, mPairsDone);
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
     * Test of getColumnCount method, of class MjtPairs.
     */
    @Test
    public void testGetColumnCount() {
        System.out.println("getColumnCount");
        int expResult = 4;
        int result = instance.getColumnCount();
        assertEquals(result, expResult);
    }

    /**
     * Test of getRowCount method, of class MjtPairs.
     */
    @Test
    public void testGetRowCount() {
        System.out.println("getRowCount");
        int expResult = mPairsDone.size();
        int result = instance.getRowCount();
        assertEquals(result, expResult);
    }

    /**
     * Test of getColumnName method, of class MjtPairs.
     */
    @Test
    public void testGetColumnName() {
        System.out.println("getColumnName");
        for (int col = 0; col < instance.getColumnCount(); col++) {
            String expResult = "";
            switch (col) {
                case 0:
                    expResult = Translate.translate(Translate.CS_Table);
                    break;
                case 1:
                    expResult = Translate.translate(Translate.CS_Team) + " 1";
                    break;
                case 2:
                    expResult = StringConstants.CS_NULL;
                    break;
                case 3:
                    expResult = Translate.translate(Translate.CS_Team) + " 2";
                    break;
                default:

            }
            String result = instance.getColumnName(col);
            assertEquals(result, expResult);
        }

    }

    /**
     * Test of getValueAt method, of class MjtPairs.
     */
    @Test
    public void testGetValueAt() {
        System.out.println("getValueAt");
        for (int row = 0; row < instance.getRowCount(); row++) {
            for (int col = 0; col < instance.getColumnCount(); col++) {
                Object expResult = null;
                switch (col) {
                    case 0:
                        expResult = row + 1;
                        break;
                    case 1:
                        expResult = teams1.get(row).getName();
                        break;
                    case 2:
                        expResult = Translate.translate(Translate.CS_ACCR_Versus);
                        break;
                    case 3:
                        expResult = teams2.get(row).getName();
                        break;
                }
                Object result = instance.getValueAt(row, col);
                assertEquals(result, expResult);
            }
        }
    }

    /**
     * Test of getColumnClass method, of class MjtPairs.
     */
    @Test
    public void testGetColumnClass() {
        System.out.println("getColumnClass");
        for (int col = 0; col < instance.getColumnCount(); col++) {
            Class expResult = String.class;
            if (col == 0) {
                expResult = Integer.class;
            }
            Class result = instance.getColumnClass(col);
            assertEquals(result, expResult);
        }
    }

    /**
     * Test of isCellEditable method, of class MjtPairs.
     */
    @Test
    public void testIsCellEditable() {
        System.out.println("isCellEditable");
        for (int row = 0; row < instance.getRowCount(); row++) {
            for (int col = 0; col < instance.getColumnCount(); col++) {
                boolean expResult = false;
                boolean result = instance.isCellEditable(row, col);
                assertEquals(result, expResult);
            }
        }
    }

    /**
     * Test of getTableCellRendererComponent method, of class MjtPairs.
     */
    @Test
    public void testGetTableCellRendererComponent() {
        System.out.println("getTableCellRendererComponent");
        JTable table = new JTable();
        table.setModel(instance);
        for (int i = 0; i < instance.getColumnCount(); i++) {
            for (int j = 0; j < instance.getRowCount(); j++) {
                Component result = instance.getTableCellRendererComponent(table, instance.getValueAt(j, i), false, false, j, i);
                Assert.assertTrue(result instanceof JTextField);
            }
        }
    }

}
