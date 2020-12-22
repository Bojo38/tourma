/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bb.tourma.tableModel;

import bb.tourma.data.Tournament;
import bb.tourma.tableModel.MjtCriteriasTeamBonus;
import java.awt.Component;
import java.io.File;
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
public class MjtCriteriasTeamBonusNGTest {

    static MjtCriteriasTeamBonus instance;

    public MjtCriteriasTeamBonusNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        Tournament.getTournament().loadXML(new File("./test/tournament.xml"));
        Tournament tour = Tournament.getTournament();
        instance = new MjtCriteriasTeamBonus(tour);
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
     * Test of getColumnCount method, of class MjtCriteriasTeamBonus.
     */
    @Test
    public void testGetColumnCount() {
        System.out.println("getColumnCount");
        int expResult = 7;
        int result = instance.getColumnCount();
        assertEquals(result, expResult);
    }

    /**
     * Test of getRowCount method, of class MjtCriteriasTeamBonus.
     */
    @Test
    public void testGetRowCount() {
        System.out.println("getRowCount");
         int expResult = 3;
        int result = instance.getRowCount();
        assertEquals(result, expResult);
    }

    /**
     * Test of getColumnName method, of class MjtCriteriasTeamBonus.
     */
    @Test
    public void testGetColumnName() {
        System.out.println("getColumnName");
        int col = 0;
        String expResult = "Nom Critère";
        String result = instance.getColumnName(col);
        assertEquals(result, expResult);
    }

    /**
     * Test of getValueAt method, of class MjtCriteriasTeamBonus.
     */
    @Test
    public void testGetValueAt() {
        System.out.println("getValueAt");
        int row = 0;
        int col = 0;
        String expResult = "Touchdowns";
        Object result = instance.getValueAt(row, col);
        assertEquals(result, expResult);
    }

    /**
     * Test of setValueAt method, of class MjtCriteriasTeamBonus.
     */
    @Test
    public void testSetValueAt() {
        System.out.println("setValueAt");
       int row = 0;
        int col = 0;
        
        String expResult = "Touchdowns";
        Object result = instance.getValueAt(row, col);
        assertEquals(result, expResult);
        
        
        instance.setValueAt("Tds", row, col);
        
        expResult = "Tds";
        result = instance.getValueAt(row, col);
        assertEquals(result, expResult);
    }

    /**
     * Test of getColumnClass method, of class MjtCriteriasTeamBonus.
     */
    @Test
    public void testGetColumnClass() {
        System.out.println("getColumnClass");
        int c = 0;
        Class result = instance.getColumnClass(c);
        assertEquals(result, String.class);
    }

    /**
     * Test of isCellEditable method, of class MjtCriteriasTeamBonus.
     */
    @Test
    public void testIsCellEditable() {
        System.out.println("isCellEditable");
        int row = 0;
        int col = 0;
        boolean expResult = false;
        boolean result = instance.isCellEditable(row, col);
        assertEquals(result, expResult);
    }

    /**
     * Test of getTableCellRendererComponent method, of class
     * MjtCriteriasTeamBonus.
     */
    @Test
    public void testGetTableCellRendererComponent() {
        System.out.println("getTableCellRendererComponent");
        JTable table = null;
        Object value = null;
        boolean isSelected = false;
        boolean hasFocus = false;
        int row = 0;
        int column = 0;
        Component expResult = null;
        Component result = instance.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        assertTrue(result instanceof JTextField);
    }

}
