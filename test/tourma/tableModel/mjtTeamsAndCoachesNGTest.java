/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.tableModel;

import java.awt.Component;
import javax.swing.JLabel;
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
public class mjtTeamsAndCoachesNGTest {
    
    public mjtTeamsAndCoachesNGTest() {
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
     * Test of getRowCount method, of class mjtTeamsAndCoaches.
     */
    @Test
    public void testGetRowCount() {
        System.out.println("getRowCount");
        mjtTeamsAndCoaches instance = null;
        int expResult = 0;
        int result = instance.getRowCount();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getColumnCount method, of class mjtTeamsAndCoaches.
     */
    @Test
    public void testGetColumnCount() {
        System.out.println("getColumnCount");
        mjtTeamsAndCoaches instance = null;
        int expResult = 0;
        int result = instance.getColumnCount();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getValueAt method, of class mjtTeamsAndCoaches.
     */
    @Test
    public void testGetValueAt() {
        System.out.println("getValueAt");
        int rowIndex = 0;
        int columnIndex = 0;
        mjtTeamsAndCoaches instance = null;
        Object expResult = null;
        Object result = instance.getValueAt(rowIndex, columnIndex);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getColumnName method, of class mjtTeamsAndCoaches.
     */
    @Test
    public void testGetColumnName() {
        System.out.println("getColumnName");
        int col = 0;
        mjtTeamsAndCoaches instance = null;
        String expResult = "";
        String result = instance.getColumnName(col);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setLabelLF method, of class mjtTeamsAndCoaches.
     */
    @Test
    public void testSetLabelLF() {
        System.out.println("setLabelLF");
        int row = 0;
        int col = 0;
        boolean isSelected = false;
        boolean hasFocus = false;
        JLabel jlb = null;
        mjtTeamsAndCoaches instance = null;
        instance.setLabelLF(row, col, isSelected, hasFocus, jlb);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTableCellRendererComponent method, of class mjtTeamsAndCoaches.
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
        mjtTeamsAndCoaches instance = null;
        Component expResult = null;
        Component result = instance.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setValueAt method, of class mjtTeamsAndCoaches.
     */
    @Test
    public void testSetValueAt() {
        System.out.println("setValueAt");
        Object value = null;
        int row = 0;
        int col = 0;
        mjtTeamsAndCoaches instance = null;
        instance.setValueAt(value, row, col);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getColumnClass method, of class mjtTeamsAndCoaches.
     */
    @Test
    public void testGetColumnClass() {
        System.out.println("getColumnClass");
        int c = 0;
        mjtTeamsAndCoaches instance = null;
        Class expResult = null;
        Class result = instance.getColumnClass(c);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isCellEditable method, of class mjtTeamsAndCoaches.
     */
    @Test
    public void testIsCellEditable() {
        System.out.println("isCellEditable");
        int row = 0;
        int col = 0;
        mjtTeamsAndCoaches instance = null;
        boolean expResult = false;
        boolean result = instance.isCellEditable(row, col);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
