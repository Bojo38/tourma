/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.tableModel;

import java.awt.Component;
import java.util.ArrayList;
import javax.swing.JTable;
import static org.testng.Assert.fail;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tourma.data.Competitor;

/**
 *
 * @author WFMJ7631
 */
public class MjtRankingManualNGTest {
    
    public MjtRankingManualNGTest() {
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
     * Test of sortDatas method, of class MjtRankingManual.
     */
    @Test
    public void testSortDatas() {
        System.out.println("sortDatas");
        MjtRankingManual instance = null;
        instance.sortDatas();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addData method, of class MjtRankingManual.
     */
    @Test
    public void testAddData() {
        System.out.println("addData");
        Competitor obj = null;
        MjtRankingManual instance = null;
        instance.addData(obj);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addDatas method, of class MjtRankingManual.
     */
    @Test
    public void testAddDatas() {
        System.out.println("addDatas");
        ArrayList<Competitor> objs = null;
        MjtRankingManual instance = null;
        instance.addDatas(objs);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getColumnCount method, of class MjtRankingManual.
     */
    @Test
    public void testGetColumnCount() {
        System.out.println("getColumnCount");
        MjtRankingManual instance = null;
        int expResult = 0;
        int result = instance.getColumnCount();
        //assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getColumnName method, of class MjtRankingManual.
     */
    @Test
    public void testGetColumnName() {
        System.out.println("getColumnName");
        int col = 0;
        MjtRankingManual instance = null;
        String expResult = "";
        String result = instance.getColumnName(col);
        //assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getValueAt method, of class MjtRankingManual.
     */
    @Test
    public void testGetValueAt() {
        System.out.println("getValueAt");
        int row = 0;
        int col = 0;
        MjtRankingManual instance = null;
        Object expResult = null;
        Object result = instance.getValueAt(row, col);
        //assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTableCellRendererComponent method, of class MjtRankingManual.
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
        MjtRankingManual instance = null;
        Component expResult = null;
        Component result = instance.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        //assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
