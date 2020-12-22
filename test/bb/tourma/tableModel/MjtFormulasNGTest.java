/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bb.tourma.tableModel;

import bb.tourma.data.Criterion;
import bb.tourma.data.Formula;
import bb.tourma.data.Tournament;
import bb.tourma.languages.Translate;
import static bb.tourma.tableModel.MjtCriteriasNGTest.crits;
import static bb.tourma.tableModel.MjtCriteriasNGTest.instance;
import java.awt.Component;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.JTextField;
import org.testng.Assert;
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
public class MjtFormulasNGTest {
    
    static ArrayList<Formula> forms = new ArrayList<>();
    static MjtFormulas instance;
    
    public MjtFormulasNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
         Tournament.getTournament().loadXML(new File("./test/tournament.xml"));

        for (int i = 0; i < Tournament.getTournament().getParams().getFormulaCount(); i++) {
            forms.add(Tournament.getTournament().getParams().getFormula(i));
        }
        instance = new MjtFormulas(Tournament.getTournament());
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
     * Test of getColumnCount method, of class MjtFormulas.
     */
    @Test
    public void testGetColumnCount() {
        System.out.println("getColumnCount");
        int expResult = Tournament.getTournament().getParams().isTeamTournament() ? 4 : 2;
        int result = instance.getColumnCount();
        assertEquals(result, expResult);
    }

    /**
     * Test of getRowCount method, of class MjtFormulas.
     */
    @Test
    public void testGetRowCount() {
        System.out.println("getRowCount");
         int expResult = forms.size();
        int result = instance.getRowCount();
        assertEquals(result, expResult);    }

    /**
     * Test of getColumnName method, of class MjtFormulas.
     */
    @Test
    public void testGetColumnName() {
        System.out.println("getColumnName");
         for (int i = 0; i < instance.getColumnCount(); i++) {
            String expResult = "";
            switch (i) {
                case 0:
                    expResult = Translate.translate(Translate.CS_Formula_Name);
                    break;
                case 1:
                    expResult = Translate.translate(Translate.CS_Formula_Formula);
                    break;
                default:
            }
            String result = instance.getColumnName(i);
            assertEquals(result, expResult);
        }
    }

    /**
     * Test of getValueAt method, of class MjtFormulas.
     */
    @Test
    public void testGetValueAt() {
        System.out.println("getValueAt");
        Object expResult = "";
        for (int i = 0; i < forms.size(); i++) {
            Formula c = forms.get(i);
            for (int col = 0; col < 2; col++) {

                switch (col) {
                    case 0:
                        expResult = c.getName();
                        break;
                    case 1:
                        expResult = c.getFormula();
                        break;
                    default:
                }
                Object result = instance.getValueAt(i, col);
                assertEquals(result, expResult);
            }
        }
    }

    /**
     * Test of setValueAt method, of class MjtFormulas.
     */
    @Test
    public void testSetValueAt() {
        System.out.println("setValueAt");
        Object expResult=null;
        Object newResult=null;
       for (int i = 0; i < forms.size(); i++) {
            Formula c = forms.get(i);
            for (int col = 0; col < 2; col++) {

                switch (col) {
                    case 0:
                        expResult = c.getName();
                        newResult="Test";
                        break;
                    case 1:
                        expResult = c.getFormula();
                        newResult="Tds1";
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
     * Test of getColumnClass method, of class MjtFormulas.
     */
    @Test
    public void testGetColumnClass() {
        System.out.println("getColumnClass");
        for (int i = 0; i < instance.getColumnCount(); i++) {
            Class expResult = null;
            if ((i == 0)||(i==1)) {
                expResult = String.class;
            } else {
                expResult = Integer.class;
            }
            Class result = instance.getColumnClass(i);
            assertEquals(result, expResult);
        }
    }

    /**
     * Test of isCellEditable method, of class MjtFormulas.
     */
    @Test
    public void testIsCellEditable() {
        System.out.println("isCellEditable");
        for (int row = 0; row<instance.getRowCount(); row++) {
            for (int col = 0; col<instance.getColumnCount(); col++) {
                
                boolean expResult = true;
                
                boolean result = instance.isCellEditable(row, col);
                
                assertEquals(result,expResult);
            }
        }
    }

    /**
     * Test of getTableCellRendererComponent method, of class MjtFormulas.
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
