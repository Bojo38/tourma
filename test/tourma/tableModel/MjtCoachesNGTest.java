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
import tourma.data.Coach;
import tourma.data.Tournament;
import tourma.languages.Translate;
import tourma.utility.StringConstants;

/**
 *
 * @author WFMJ7631
 */
public class MjtCoachesNGTest {

    static ArrayList<Coach> coachs = new ArrayList<>();
    static MjtCoaches instance;

    public MjtCoachesNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        Tournament.getTournament().loadXML(new File("./test/coach.xml"));

        for (int i = 0; i < Tournament.getTournament().getCoachsCount(); i++) {
            coachs.add(Tournament.getTournament().getCoach(i));
        }
        //crit=Tournament.getTournament().getParams().getCriteria(0);
        instance = new MjtCoaches(Tournament.getTournament());
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
     * Test of getColumnCount method, of class MjtCoaches.
     */
    @Test
    public void testGetColumnCount() {
        System.out.println("getColumnCount");
        int expResult = 9;
        int result = instance.getColumnCount();
        assertEquals(result, expResult);
    }

    /**
     * Test of getRowCount method, of class MjtCoaches.
     */
    @Test
    public void testGetRowCount() {
        System.out.println("getRowCount");
        int expResult = coachs.size();
        int result = instance.getRowCount();
        assertEquals(result, expResult);
    }

    /**
     * Test of getColumnName method, of class MjtCoaches.
     */
    @Test
    public void testGetColumnName() {
        System.out.println("getColumnName");
        String expResult = "";
        for (int col = 0; col < 9; col++) {

            switch (col) {
                case 0:
                    expResult = StringConstants.CS_HASH;
                    break;
                case 1:
                    expResult = Translate.translate(Translate.CS_Coach);
                    break;
                case 2:
                    expResult = Translate.translate(Translate.CS_Team);
                    break;
                case 3:
                    expResult = Translate.translate(Translate.CS_Roster);
                    break;
                case 4:
                    expResult = Translate.translate(Translate.CS_NAF);
                    break;
                case 5:
                    expResult = Translate.translate(Translate.CS_Ranking);
                    break;
                case 6:
                    expResult = StringConstants.CS_NULL;
                    break;
                case 7:
                    expResult = Translate.translate(Translate.CS_Ranking);
                    break;
                case 8:
                    expResult = Translate.translate(Translate.CS_Clan);
                    break;
                default:

            }
            String result = instance.getColumnName(col);
            assertEquals(result, expResult);
        }
    }

    /**
     * Test of getValueAt method, of class MjtCoaches.
     */
    @Test
    public void testGetValueAt() {
        System.out.println("getValueAt");
        Object expResult = "";
        for (int i = 0; i < coachs.size(); i++) {
            Coach c = coachs.get(i);
            for (int col = 0; col < 9; col++) {

                switch (col) {
                    case 0:
                        expResult = i + 1;
                        break;
                    case 1:
                        expResult = c.getName();
                        break;
                    case 2:
                        expResult = c.getTeam();
                        break;
                    case 3:
                        expResult = c.getRoster().getName();
                        break;
                    case 4:
                        expResult = c.getNaf();
                        break;
                    case 5:
                        expResult = c.getRank();
                        break;
                    case 6:
                        if (c.isActive()) {
                            expResult = Translate.translate(Translate.CS_Active);
                        } else {
                            expResult = Translate.translate(Translate.CS_Inactive);
                        }

                        break;
                    case 7:
                        expResult = Double.toString(c.getNafRank());
                        break;
                    case 8:
                        expResult = c.getClan().getName();
                        break;
                    default:
                }
                Object result = instance.getValueAt(i, col);
                assertEquals(result, expResult);
            }
        }
    }

    /**
     * Test of getColumnClass method, of class MjtCoaches.
     */
    @Test
    public void testGetColumnClass() {
        System.out.println("getColumnClass");
        Class expResult = null;
        for (int col = 0; col < 9; col++) {
            switch (col) {
                case 0:
                case 4:
                case 5:
                    expResult = Integer.class;
                    break;
                case 1:
                case 2:
                case 3:
                case 6:
                case 7:
                case 8:
                    expResult = String.class;
                    break;
                default:
            }
            Class result = instance.getColumnClass(col);
            assertEquals(result, expResult);
        }

    }

    /**
     * Test of isCellEditable method, of class MjtCoaches.
     */
    @Test
    public void testIsCellEditable() {
        System.out.println("isCellEditable");
        for (int row = 0; row<instance.getRowCount(); row++) {
            for (int col = 0; col<instance.getColumnCount(); col++) {
                boolean expResult = false;
                boolean result = instance.isCellEditable(row, col);
                assertEquals(result, col>0);
            }
        }
    }

    /**
     * Test of getTableCellRendererComponent method, of class MjtCoaches.
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
