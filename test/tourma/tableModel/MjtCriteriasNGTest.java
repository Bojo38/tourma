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
import tourma.data.Criteria;
import tourma.data.Tournament;
import tourma.languages.Translate;

/**
 *
 * @author WFMJ7631
 */
public class MjtCriteriasNGTest {

    static ArrayList<Criteria> crits = new ArrayList<>();
    static MjtCriterias instance;

    public MjtCriteriasNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        Tournament.getTournament().loadXML(new File("./test/params.xml"));

        for (int i = 0; i < Tournament.getTournament().getParams().getCriteriaCount(); i++) {
            crits.add(Tournament.getTournament().getParams().getCriteria(i));
        }
        instance = new MjtCriterias(Tournament.getTournament());
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
     * Test of getColumnCount method, of class MjtCriterias.
     */
    @Test
    public void testGetColumnCount() {
        System.out.println("getColumnCount");
        int expResult = Tournament.getTournament().getParams().isTeamTournament() ? 5 : 3;
        int result = instance.getColumnCount();
        assertEquals(result, expResult);
    }

    /**
     * Test of getRowCount method, of class MjtCriterias.
     */
    @Test
    public void testGetRowCount() {
        System.out.println("getRowCount");
        int expResult = crits.size();
        int result = instance.getRowCount();
        assertEquals(result, expResult);
    }

    /**
     * Test of getColumnName method, of class MjtCriterias.
     */
    @Test
    public void testGetColumnName() {
        System.out.println("getColumnName");

        for (int i = 0; i < instance.getColumnCount(); i++) {
            String expResult = "";
            switch (i) {
                case 0:
                    expResult = Translate.translate(Translate.CS_Critera_Name);
                    break;
                case 1:
                    expResult = Translate.translate(Translate.CS_Points_Plus);
                    break;
                case 2:
                    expResult = Translate.translate(Translate.CS_Points_Minus);
                    break;
                case 3:
                    expResult = Translate.translate(Translate.CS_Points_Team_Plus);
                    break;
                case 4:
                    expResult = Translate.translate(Translate.CS_Points_Team_Minus);
                    break;
                default:
            }
            String result = instance.getColumnName(i);
            assertEquals(result, expResult);
        }

    }

    /**
     * Test of getValueAt method, of class MjtCriterias.
     */
    @Test
    public void testGetValueAt() {
        System.out.println("getValueAt");
       Object expResult = "";
        for (int i = 0; i < crits.size(); i++) {
            Criteria c = crits.get(i);
            for (int col = 0; col < 5; col++) {

                switch (col) {
                    case 0:
                        expResult = c.getName();
                        break;
                    case 1:
                        expResult = c.getPointsFor();
                        break;
                    case 2:
                        expResult = c.getPointsAgainst();
                        break;
                    case 3:
                        expResult = c.getPointsTeamFor();
                        break;
                    case 4:
                        expResult = c.getPointsTeamAgainst();
                        break;
                    default:
                }
                Object result = instance.getValueAt(i, col);
                assertEquals(result, expResult);
            }
        }
    }

    /**
     * Test of setValueAt method, of class MjtCriterias.
     */
    @Test
    public void testSetValueAt() {
        System.out.println("setValueAt");
        Object expResult=null;
         Object newResult=null;
       for (int i = 0; i < crits.size(); i++) {
            Criteria c = crits.get(i);
            for (int col = 0; col < 5; col++) {

                switch (col) {
                    case 0:
                        expResult = c.getName();
                        newResult="Test";
                        break;
                    case 1:
                        expResult = c.getPointsFor();
                        newResult=1;
                        break;
                    case 2:
                        expResult = c.getPointsAgainst();
                        newResult=2;
                        break;
                    case 3:
                        expResult = c.getPointsTeamFor();
                        newResult=3;
                        break;
                    case 4:
                        expResult = c.getPointsTeamAgainst();
                        newResult=4;
                        break;
                    default:
                }                
                Object result = instance.getValueAt(i, col);                
                instance.setValueAt(newResult, i, col);
                assertEquals(newResult, instance.getValueAt(i, col));
                instance.setValueAt(result, i, col);
            }
        }
    }

    /**
     * Test of getColumnClass method, of class MjtCriterias.
     */
    @Test
    public void testGetColumnClass() {
        System.out.println("getColumnClass");
        for (int i = 0; i < instance.getColumnCount(); i++) {
            Class expResult = null;
            if (i == 0) {
                expResult = String.class;
            } else {
                expResult = Integer.class;
            }
            Class result = instance.getColumnClass(i);
            assertEquals(result, expResult);
        }
    }

    /**
     * Test of isCellEditable method, of class MjtCriterias.
     */
    @Test
    public void testIsCellEditable() {
        System.out.println("isCellEditable");
        for (int row = 0; row<instance.getRowCount(); row++) {
            for (int col = 0; col<instance.getColumnCount(); col++) {
                boolean expResult = Tournament.getTournament().getRoundsCount() <= 0;
                boolean result = instance.isCellEditable(row, col);
                assertEquals(result,expResult);
            }
        }
    }

    /**
     * Test of getTableCellRendererComponent method, of class MjtCriterias.
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
