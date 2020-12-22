/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bb.tourma.tableModel;

import bb.tourma.data.Coach;
import bb.tourma.data.CoachMatch;
import bb.tourma.data.Competitor;
import bb.tourma.data.Criterion;
import bb.tourma.data.Tournament;
import bb.tourma.languages.Translate;
import static bb.tourma.tableModel.MjtMatchesNGTest.instance;
import static bb.tourma.tableModel.MjtMatchesNGTest.matchs;
import bb.tourma.utility.StringConstants;
import java.awt.Component;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.JTextField;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author WFMJ7631
 */
public class MjtManualMatchsNGTest {

    static MjtManualMatchs instance;
    static ArrayList<Competitor> comps;

    public MjtManualMatchsNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {

        Tournament.getTournament().loadXML(new File("./test/tournament.xml"));
        comps = new ArrayList<Competitor>();

        Tournament tour = Tournament.getTournament();

        for (int i = 0; i < tour.getCoachsCount(); i++) {
            Competitor c = tour.getCoach(i);
            comps.add(c);
        }

        instance = new MjtManualMatchs(comps);
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
     * Test of getColumnCount method, of class MjtManualMatchs.
     */
    @Test
    public void testGetColumnCount() {
        System.out.println("getColumnCount");
        int expResult = 3;

        int result = instance.getColumnCount();
        assertEquals(result, expResult);
    }

    /**
     * Test of getRowCount method, of class MjtManualMatchs.
     */
    @Test
    public void testGetRowCount() {
        System.out.println("getRowCount");
        int expResult = comps.size() / 2;
        int result = instance.getRowCount();
        assertEquals(result, expResult);
    }

    /**
     * Test of getColumnName method, of class MjtManualMatchs.
     */
    @Test
    public void testGetColumnName() {
        System.out.println("getColumnName");
        for (int col = 0; col < instance.getColumnCount(); col++) {
            String expResult = "";
            String result = instance.getColumnName(col);
            assertEquals(result, expResult);
        }
    }

    /**
     * Test of getValueAt method, of class MjtManualMatchs.
     */
    @Test
    public void testGetValueAt() {
        System.out.println("getValueAt");
        for (int row = 0; row < instance.getRowCount(); row++) {
            Competitor c = comps.get(row);

            Object result = instance.getValueAt(row, 0);
            assertTrue(comps.contains(result));

        }
    }

    /**
     * Test of setValueAt method, of class MjtManualMatchs.
     */
    @Test(enabled = false)
    public void testSetValueAt() {
        System.out.println("setValueAt");
        for (int row = 0; row < instance.getRowCount(); row++) {
            CoachMatch cm = matchs.get(row);
            for (int col = 0; col < instance.getColumnCount(); col++) {
                Object expResult = null;
                Criterion crit = null;
                switch (col) {
                    case 0:
                        expResult = row + 1;
                        break;
                    case 1:
                        expResult = cm.getCompetitor1().getName() + StringConstants.CS_THICK + ((Coach) cm.getCompetitor1()).getStringRoster();
                        break;
                    case 2:
                        expResult = cm.getValue(Tournament.getTournament().getParams().getCriterion(0)).getValue1();
                        break;
                    case 3:
                        expResult = cm.getValue(Tournament.getTournament().getParams().getCriterion(0)).getValue2();
                        break;
                    case 4:
                        expResult = cm.getCompetitor2().getName() + StringConstants.CS_THICK + ((Coach) cm.getCompetitor2()).getStringRoster();
                        break;
                    default:
                        crit = Tournament.getTournament().getParams().getCriterion((col - 3) / 2);
                        if ((col - 3) % 2 > 0) {
                            expResult = cm.getValue(crit).getValue2();
                        } else {
                            expResult = cm.getValue(crit).getValue1();
                        }
                }

                Object result = instance.getValueAt(row, col);
                assertEquals(result, expResult);

            }
        }
    }

    /**
     * Test of getColumnClass method, of class MjtManualMatchs.
     */
    @Test
    public void testGetColumnClass() {
        System.out.println("getColumnClass");
        for (int col = 0; col < instance.getColumnCount(); col++) {
            Class expResult = null;
            switch (col) {
                case 0:
                    expResult = Coach.class;
                    break;
                case 1:
                    expResult = String.class;
                    break;
                case 2:
                    expResult = Coach.class;
                    break;
                default:
                    expResult = Integer.class;
            }

            Class result = instance.getColumnClass(col);
            assertEquals(result, expResult);
        }
    }

    /**
     * Test of isCellEditable method, of class MjtManualMatchs.
     */
    @Test
    public void testIsCellEditable() {
        System.out.println("isCellEditable");
        boolean expResult = false;
        for (int row = 0; row < instance.getRowCount(); row++) {
            for (int col = 0; col < instance.getColumnCount(); col++) {
                expResult = false;

                boolean result = instance.isCellEditable(row, col);
                assertEquals(result, expResult);
            }
        }
    }

    /**
     * Test of getTableCellRendererComponent method, of class MjtManualMatchs.
     */
    @Test
    public void testGetTableCellRendererComponent() {
        System.out.println("getTableCellRendererComponent");
        JTable table = new JTable();
        Object value = new String("Toto");
        boolean isSelected = false;
        boolean hasFocus = false;
        int row = 0;
        int column = 0;
        Component result = instance.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        assertTrue(result instanceof JTextField);

    }

}
