/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bb.tourma.tableModel;

import bb.tourma.data.Team;
import bb.tourma.data.Tournament;
import static bb.tourma.tableModel.MjtRankingTeamNGTest.teams;
import bb.tourma.tableModel.MjtTeamsAndCoaches;
import java.awt.Component;
import java.io.File;
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
public class MjtTeamsAndCoachesNGTest {

    static MjtTeamsAndCoaches instance;

    public MjtTeamsAndCoachesNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        Tournament.getTournament().loadXML(new File("./test/team.xml"));

        for (int i = 0; i < Tournament.getTournament().getTeamsCount(); i++) {
            teams.add(Tournament.getTournament().getTeam(i));
        }

        instance  = new MjtTeamsAndCoaches(true);

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
     * Test of getRowCount method, of class MjtTeamsAndCoaches.
     */
    @Test
    public void testGetRowCount() {
        System.out.println("getRowCount");
        
        int expResult = 112;
        int result = instance.getRowCount();
        assertEquals(result, expResult);
    }

    /**
     * Test of getColumnCount method, of class MjtTeamsAndCoaches.
     */
    @Test
    public void testGetColumnCount() {
        System.out.println("getColumnCount");
        int expResult = 7;
        int result = instance.getColumnCount();
        assertEquals(result, expResult);
    }

    /**
     * Test of getValueAt method, of class MjtTeamsAndCoaches.
     */
    @Test
    public void testGetValueAt() {
        System.out.println("getValueAt");
        int rowIndex = 0;
        int columnIndex = 0;
        Object expResult = "France";
        Object result = instance.getValueAt(rowIndex, columnIndex);
        assertEquals(((Team)result).getName(), expResult);
    }

    /**
     * Test of getColumnName method, of class MjtTeamsAndCoaches.
     */
    @Test
    public void testGetColumnName() {
        System.out.println("getColumnName");
        int col = 0;
        String expResult = "Equipe";
        String result = instance.getColumnName(col);
        assertEquals(result, expResult);
    }

    /**
     * Test of setLabelLF method, of class MjtTeamsAndCoaches.
     */
    @Test
    public void testSetLabelLF() {
        System.out.println("setLabelLF");
        int row = 0;
        int col = 0;
        boolean isSelected = false;
        boolean hasFocus = false;
        JLabel jlb = new JLabel();
        instance.setLabelLF(row, col, isSelected, hasFocus, jlb);
    }

    /**
     * Test of getTableCellRendererComponent method, of class
     * MjtTeamsAndCoaches.
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
        assertTrue(result instanceof JLabel);

    }

    /**
     * Test of setValueAt method, of class MjtTeamsAndCoaches.
     */
    @Test
    public void testSetValueAt() {
        System.out.println("setValueAt");
        Object value = null;
        int row = 0;
        int col = 0;
        instance.setValueAt(value, row, col);
    }

    /**
     * Test of getColumnClass method, of class MjtTeamsAndCoaches.
     */
    @Test
    public void testGetColumnClass() {
        System.out.println("getColumnClass");
        int c = 0;
        Class expResult = null;
        Class result = instance.getColumnClass(c);
        assertEquals(result, Team.class);
    }

    /**
     * Test of isCellEditable method, of class MjtTeamsAndCoaches.
     */
    @Test
    public void testIsCellEditable() {
        System.out.println("isCellEditable");
        int row = 0;
        int col = 0;
        boolean expResult = true;
        boolean result = instance.isCellEditable(row, col);
        assertEquals(result, expResult);
    }

}
