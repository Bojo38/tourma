/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bb.teamma.tableModel;

import bb.teamma.tableModel.MjtStarPlayers;
import java.awt.Component;
import java.util.ArrayList;
import javax.swing.JEditorPane;
import javax.swing.JTable;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import bb.teamma.data.LRB;
import bb.teamma.data.PlayerType;
import bb.teamma.data.RosterType;
import bb.teamma.data.StarPlayer;

/**
 *
 * @author WFMJ7631
 */
public class MjtStarPlayersNGTest {

    public MjtStarPlayersNGTest() {
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
     * Test of getColumnCount method, of class MjtStarPlayers.
     */
    @Test
    public void testGetColumnCount() {
        System.out.println("getColumnCount");
        LRB lrb = LRB.getLRB(LRB.E_Version.BB2016);
        ArrayList<StarPlayer> ar = new ArrayList<>();
        for (int i = 0; i < lrb.getStarPlayerCount(); i++) {
            ar.add(lrb.getStarPlayer(i));
        }
        MjtStarPlayers instance = new MjtStarPlayers(ar,LRB.E_Version.BB2016);
        int expResult = 8;
        int result = instance.getColumnCount();
        assertEquals(result, expResult);

    }

    /**
     * Test of getRowCount method, of class MjtStarPlayers.
     */
    @Test
    public void testGetRowCount() {
        System.out.println("getRowCount");
        LRB lrb = LRB.getLRB(LRB.E_Version.BB2016);
        ArrayList<StarPlayer> ar = new ArrayList<>();
        for (int i = 0; i < lrb.getStarPlayerCount(); i++) {
            ar.add(lrb.getStarPlayer(i));
        }
        MjtStarPlayers instance = new MjtStarPlayers(ar,LRB.E_Version.BB2016);
        int expResult = 105;
        int result = instance.getRowCount();
        assertEquals(result, expResult);

    }

    /**
     * Test of getColumnName method, of class MjtStarPlayers.
     */
    @Test
    public void testGetColumnName() {
        System.out.println("getColumnName");
        int col = 1;
        LRB lrb = LRB.getLRB(LRB.E_Version.BB2016);
        ArrayList<StarPlayer> ar = new ArrayList<>();
        for (int i = 0; i < lrb.getStarPlayerCount(); i++) {
            ar.add(lrb.getStarPlayer(i));
        }
        MjtStarPlayers instance = new MjtStarPlayers(ar,LRB.E_Version.BB2016);
        String expResult = "Position";
        String result = instance.getColumnName(col);
        assertEquals(result, expResult);

    }

    /**
     * Test of getValueAt method, of class MjtStarPlayers.
     */
    @Test
    public void testGetValueAt() {
        System.out.println("getValueAt");
        int row = 0;
        int col = 0;
        LRB lrb = LRB.getLRB(LRB.E_Version.BB2016);
        ArrayList<StarPlayer> ar = new ArrayList<>();
        for (int i = 0; i < lrb.getStarPlayerCount(); i++) {
            ar.add(lrb.getStarPlayer(i));
        }
        MjtStarPlayers instance = new MjtStarPlayers(ar,LRB.E_Version.BB2016);
        Object expResult = "Slave Giant";
        Object result = instance.getValueAt(row, col);
        assertEquals(result, expResult);

    }

    /**
     * Test of getColumnClass method, of class MjtStarPlayers.
     */
    @Test
    public void testGetColumnClass() {
        System.out.println("getColumnClass");
        int c = 0;
        LRB lrb = LRB.getLRB(LRB.E_Version.BB2016);
        ArrayList<StarPlayer> ar = new ArrayList<>();
        for (int i = 0; i < lrb.getStarPlayerCount(); i++) {
            ar.add(lrb.getStarPlayer(i));
        }
        MjtStarPlayers instance = new MjtStarPlayers(ar,LRB.E_Version.BB2016);

        Class expResult = String.class;
        Class result = instance.getColumnClass(c);
        assertEquals(result, expResult);

    }

    /**
     * Test of isCellEditable method, of class MjtStarPlayers.
     */
    @Test
    public void testIsCellEditable() {
        System.out.println("isCellEditable");
        int row = 0;
        int col = 0;
        LRB lrb = LRB.getLRB(LRB.E_Version.BB2016);
        ArrayList<StarPlayer> ar = new ArrayList<>();
        for (int i = 0; i < lrb.getStarPlayerCount(); i++) {
            ar.add(lrb.getStarPlayer(i));
        }
        MjtStarPlayers instance = new MjtStarPlayers(ar,LRB.E_Version.BB2016);
        boolean expResult = false;
        boolean result = instance.isCellEditable(row, col);
        assertEquals(result, expResult);

    }

    /**
     * Test of getTableCellRendererComponent method, of class MjtStarPlayers.
     */
    @Test
    public void testGetTableCellRendererComponent() {
        System.out.println("getTableCellRendererComponent");
                Object value = "toto";
        boolean isSelected = false;
        boolean hasFocus = false;
        int row = 0;
        int column = 0;
        LRB lrb = LRB.getLRB(LRB.E_Version.BB2016);
        ArrayList<StarPlayer> ar = new ArrayList<>();
        for (int i = 0; i < lrb.getStarPlayerCount(); i++) {
            ar.add(lrb.getStarPlayer(i));
        }
        MjtStarPlayers instance = new MjtStarPlayers(ar,LRB.E_Version.BB2016);
          JTable table = new JTable(instance);
        Component expResult = null;
        Component result = instance.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        assertTrue(result instanceof JEditorPane);
        
    }

}
