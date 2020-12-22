/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bb.tourma.tableModel;

import bb.tourma.tableModel.MjtMatches;
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
import bb.tourma.data.Coach;
import bb.tourma.data.CoachMatch;
import bb.tourma.data.Criterion;
import bb.tourma.data.Formula;
import bb.tourma.data.Tournament;
import bb.tourma.languages.Translate;
import bb.tourma.utility.StringConstants;

/**
 *
 * @author WFMJ7631
 */
public class MjtMatchesNGTest {

    static ArrayList<CoachMatch> matchs = new ArrayList<>();
    static MjtMatches instance;
    static Tournament tour;

    public MjtMatchesNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        Tournament.getTournament().loadXML(new File("./test/tournament.xml"));
        tour = Tournament.getTournament();
        matchs = tour.getRound(Tournament.getTournament().getRoundsCount() - 1).getCoachMatchs();

        instance = new MjtMatches(matchs, false, false, true, false);
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
     * Test of getColumnCount method, of class MjtMatches.
     */
    @Test
    public void testGetColumnCount() {
        System.out.println("getColumnCount");
        int expResult
                = tour.getParams().getCriteriaCount() * 2 + 5;

        int result = instance.getColumnCount();
        assertEquals(result, expResult);
    }

    /**
     * Test of getRowCount method, of class MjtMatches.
     */
    @Test
    public void testGetRowCount() {
        System.out.println("getRowCount");
        int expResult = matchs.size();
        int result = instance.getRowCount();
        assertEquals(result, expResult);
    }

    /**
     * Test of getColumnName method, of class MjtMatches.
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
                    expResult = Translate.translate(Translate.CS_Coach) + " 1";
                    break;
                case 2:
                    expResult = Translate.translate(Translate.CS_Score) + " 1";
                    break;
                case 3:
                    expResult = Translate.translate(Translate.CS_Score) + " 2";
                    break;
                case 4:
                    expResult = Translate.translate(Translate.CS_Coach) + " 2";
                    break;
                default:
                    if ((col >= 5)
                            && (col < (3 + tour.getParams().getCriteriaCount() * 2))) {
                        final Criterion crit = tour.getParams().getCriterion((col - 3) / 2);
                        if ((col - 3) % 2 > 0) {
                            expResult = crit.getName() + " 2";
                        } else {
                            expResult = crit.getName() + " 1";
                        }
                    } else {
                        final Formula form = Tournament.getTournament().getParams().getFormula((col - 3 - tour.getParams().getCriteriaCount() * 2) / 2);
                        if ((col - 3) % 2 > 0) {
                            expResult = form.getName() + " 2";
                        } else {
                            expResult = form.getName() + " 1";
                        }
                    }
            }

            String result = instance.getColumnName(col);
            assertEquals(result, expResult);
        }
    }

    /**
     * Test of getValueAt method, of class MjtMatches.
     */
    @Test
    public void testGetValueAt() {
        System.out.println("getValueAt");
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
                        if ((col >= 5)
                                && (col < (3 + tour.getParams().getCriteriaCount() * 2))) {
                            crit = tour.getParams().getCriterion((col - 3) / 2);
                            if ((col - 3) % 2 > 0) {
                                expResult = cm.getValue(crit).getValue2();
                            } else {
                                expResult = cm.getValue(crit).getValue1();
                            }
                        } else {
                            Formula form = null;
                            form = tour.getParams().getFormula((col - 3 - tour.getParams().getCriteriaCount() * 2) / 2);
                            if ((col - 3) % 2 > 0) {
                                expResult = cm.getValue(form).getValue2();
                            } else {
                                expResult = cm.getValue(form).getValue1();
                            }
                        }
                }

                Object result = instance.getValueAt(row, col);
                assertEquals(result, expResult);

            }
        }

    }

    /**
     * Test of setValueAt method, of class MjtMatches.
     */
    @Test
    public void testSetValueAt() {
        System.out.println("setValueAt");
        for (int row = 0; row < instance.getRowCount(); row++) {
            CoachMatch cm = matchs.get(row);
            for (int col = 0; col < instance.getColumnCount(); col++) {
                int expResult = 0;
                int save = 0;
                Criterion crit = null;
                switch (col) {
                    case 0:
                    case 1:
                    case 4:
                        continue;
                    case 2:
                        save = cm.getValue(Tournament.getTournament().getParams().getCriterion(0)).getValue1();
                        expResult = 1;
                        break;
                    case 3:
                        expResult = 2;
                        save = cm.getValue(Tournament.getTournament().getParams().getCriterion(0)).getValue2();
                        break;
                    default:
                        if ((col >= 5)
                                && (col < (3 + tour.getParams().getCriteriaCount() * 2))) {
                            crit = Tournament.getTournament().getParams().getCriterion((col - 3) / 2);
                            if ((col - 3) % 2 > 0) {
                                save = cm.getValue(crit).getValue2();
                                expResult = col;
                            } else {
                                save = cm.getValue(crit).getValue1();
                                expResult = col;
                            }
                        }
                }
                if (instance.isCellEditable(row, col)) {
                    instance.setValueAt(expResult, row, col);
                    Object result = instance.getValueAt(row, col);
                    assertEquals(result, expResult);
                    instance.setValueAt(save, row, col);
                }
            }
        }
    }

    /**
     * Test of getColumnClass method, of class MjtMatches.
     */
    @Test
    public void testGetColumnClass() {
        System.out.println("getColumnClass");

        for (int col = 0; col < instance.getColumnCount(); col++) {
            Class expResult = null;
            switch (col) {
                case 1:
                case 4:
                    expResult = String.class;
                    break;
                default:
                    expResult = Integer.class;
            }

            Class result = instance.getColumnClass(col);
            assertEquals(result, expResult);
        }
    }

    /**
     * Test of isCellEditable method, of class MjtMatches.
     */
    @Test
    public void testIsCellEditable() {
        System.out.println("isCellEditable");
        boolean expResult = false;
        for (int row = 0; row < instance.getRowCount(); row++) {
            for (int col = 0; col < instance.getColumnCount(); col++) {
                if ((col == 0) || (col == 1) || (col == 4) || (col >= (3 + tour.getParams().getCriteriaCount() * 2))) {
                    expResult = false;
                } else {
                    expResult = true;
                }
                boolean result = instance.isCellEditable(row, col);
                assertEquals(result, expResult);
            }
        }
    }

    /**
     * Test of getTableCellRendererComponent method, of class MjtMatches.
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
