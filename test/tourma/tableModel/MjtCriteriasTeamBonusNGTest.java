/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.tableModel;

import java.awt.Component;
import javax.swing.JTable;
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
    
    public MjtCriteriasTeamBonusNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
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
        MjtCriteriasTeamBonus instance = null;
        int expResult = 0;
        int result = instance.getColumnCount();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRowCount method, of class MjtCriteriasTeamBonus.
     */
    @Test
    public void testGetRowCount() {
        System.out.println("getRowCount");
        MjtCriteriasTeamBonus instance = null;
        int expResult = 0;
        int result = instance.getRowCount();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getColumnName method, of class MjtCriteriasTeamBonus.
     */
    @Test
    public void testGetColumnName() {
        System.out.println("getColumnName");
        int col = 0;
        MjtCriteriasTeamBonus instance = null;
        String expResult = "";
        String result = instance.getColumnName(col);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getValueAt method, of class MjtCriteriasTeamBonus.
     */
    @Test
    public void testGetValueAt() {
        System.out.println("getValueAt");
        int row = 0;
        int col = 0;
        MjtCriteriasTeamBonus instance = null;
        Object expResult = null;
        Object result = instance.getValueAt(row, col);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setValueAt method, of class MjtCriteriasTeamBonus.
     */
    @Test
    public void testSetValueAt() {
        System.out.println("setValueAt");
        Object value = null;
        int row = 0;
        int col = 0;
        MjtCriteriasTeamBonus instance = null;
        instance.setValueAt(value, row, col);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getColumnClass method, of class MjtCriteriasTeamBonus.
     */
    @Test
    public void testGetColumnClass() {
        System.out.println("getColumnClass");
        int c = 0;
        MjtCriteriasTeamBonus instance = null;
        Class expResult = null;
        Class result = instance.getColumnClass(c);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isCellEditable method, of class MjtCriteriasTeamBonus.
     */
    @Test
    public void testIsCellEditable() {
        System.out.println("isCellEditable");
        int row = 0;
        int col = 0;
        MjtCriteriasTeamBonus instance = null;
        boolean expResult = false;
        boolean result = instance.isCellEditable(row, col);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTableCellRendererComponent method, of class MjtCriteriasTeamBonus.
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
        MjtCriteriasTeamBonus instance = null;
        Component expResult = null;
        Component result = instance.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
