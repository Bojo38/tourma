/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teamma.tableModel;

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
import teamma.data.LRB;
import teamma.data.PlayerType;
import teamma.data.RosterType;
import tourma.data.Tournament;

/**
 *
 * @author WFMJ7631
 */
public class MjtPlayerTypesNGTest {
    
    public MjtPlayerTypesNGTest() {
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
     * Test of getColumnCount method, of class MjtPlayerTypes.
     */
    @Test
    public void testGetColumnCount() {
        System.out.println("getColumnCount");
        LRB lrb=LRB.getLRB(LRB.E_Version.BB2016);
        RosterType rt=lrb.getRosterType(0);
        ArrayList<PlayerType> ar=new ArrayList<>();
        for (int i=0; i<rt.getPlayerTypeCount(); i++)
        {
            ar.add(rt.getPlayerType(i));
        }
        MjtPlayerTypes instance = new MjtPlayerTypes(ar);
        int expResult = 10;
        int result = instance.getColumnCount();
        assertEquals(result, expResult);
        
    }

    /**
     * Test of getRowCount method, of class MjtPlayerTypes.
     */
    @Test
    public void testGetRowCount() {
        System.out.println("getRowCount");
        LRB lrb=LRB.getLRB(LRB.E_Version.BB2016);
        RosterType rt=lrb.getRosterType(0);
        ArrayList<PlayerType> ar=new ArrayList<>();
        for (int i=0; i<rt.getPlayerTypeCount(); i++)
        {
            ar.add(rt.getPlayerType(i));
        }
        MjtPlayerTypes instance = new MjtPlayerTypes(ar);
        int expResult = 4;
        int result = instance.getRowCount();
        assertEquals(result, expResult);
 
    }

    /**
     * Test of getColumnName method, of class MjtPlayerTypes.
     */
    @Test
    public void testGetColumnName() {
        System.out.println("getColumnName");
        int col = 1;
         LRB lrb=LRB.getLRB(LRB.E_Version.BB2016);
        RosterType rt=lrb.getRosterType(0);
        ArrayList<PlayerType> ar=new ArrayList<>();
        for (int i=0; i<rt.getPlayerTypeCount(); i++)
        {
            ar.add(rt.getPlayerType(i));
        }
        MjtPlayerTypes instance = new MjtPlayerTypes(ar);
        
        String expResult = "Position";
        String result = instance.getColumnName(col);
        assertEquals(result, expResult);

    }

    /**
     * Test of getValueAt method, of class MjtPlayerTypes.
     */
    @Test
    public void testGetValueAt() {
        System.out.println("getValueAt");
        int row = 0;
        int col = 0;
         LRB lrb=LRB.getLRB(LRB.E_Version.BB2016);
        RosterType rt=lrb.getRosterType(0);
        ArrayList<PlayerType> ar=new ArrayList<>();
        for (int i=0; i<rt.getPlayerTypeCount(); i++)
        {
            ar.add(rt.getPlayerType(i));
        }
        MjtPlayerTypes instance = new MjtPlayerTypes(ar);
        Object expResult = 4;
        Object result = instance.getValueAt(row, col);
        assertEquals(result, expResult);

    }

    /**
     * Test of getColumnClass method, of class MjtPlayerTypes.
     */
    @Test
    public void testGetColumnClass() {
        System.out.println("getColumnClass");
        int c = 1;
        LRB lrb=LRB.getLRB(LRB.E_Version.BB2016);
        RosterType rt=lrb.getRosterType(0);
        ArrayList<PlayerType> ar=new ArrayList<>();
        for (int i=0; i<rt.getPlayerTypeCount(); i++)
        {
            ar.add(rt.getPlayerType(i));
        }
        MjtPlayerTypes instance = new MjtPlayerTypes(ar);
        
        Class expResult = String.class;
        Class result = instance.getColumnClass(c);
        assertEquals(result, expResult);

    }

    /**
     * Test of isCellEditable method, of class MjtPlayerTypes.
     */
    @Test
    public void testIsCellEditable() {
        System.out.println("isCellEditable");
        int row = 0;
        int col = 0;
        LRB lrb=LRB.getLRB(LRB.E_Version.BB2016);
        RosterType rt=lrb.getRosterType(0);
        ArrayList<PlayerType> ar=new ArrayList<>();
        for (int i=0; i<rt.getPlayerTypeCount(); i++)
        {
            ar.add(rt.getPlayerType(i));
        }
        MjtPlayerTypes instance = new MjtPlayerTypes(ar);
        boolean expResult = false;
        boolean result = instance.isCellEditable(row, col);
        assertEquals(result, expResult);

    }

    /**
     * Test of getTableCellRendererComponent method, of class MjtPlayerTypes.
     */
    @Test
    public void testGetTableCellRendererComponent() {
        System.out.println("getTableCellRendererComponent");
        JTable table = null;
        
        boolean isSelected = false;
        boolean hasFocus = false;
        int row = 0;
        int column = 0;
        LRB lrb=LRB.getLRB(LRB.E_Version.BB2016);
        RosterType rt=lrb.getRosterType(0);
        ArrayList<PlayerType> ar=new ArrayList<>();
        for (int i=0; i<rt.getPlayerTypeCount(); i++)
        {
            ar.add(rt.getPlayerType(i));
        }
        MjtPlayerTypes instance = new MjtPlayerTypes(ar);
        
        String val="exemple";
        table=new JTable(instance);
        Component result = instance.getTableCellRendererComponent(table, val, isSelected, hasFocus, row, column);
        assertTrue(result instanceof JEditorPane);

    }
    
}
