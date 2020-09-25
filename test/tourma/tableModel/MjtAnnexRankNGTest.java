/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.tableModel;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tourma.data.Criteria;

/**
 *
 * @author WFMJ7631
 */
public class MjtAnnexRankNGTest {

    public MjtAnnexRankNGTest() {
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
     * Test of sortDatas method, of class MjtAnnexRank.
     */
    @Test
    public void testSortDatas() {
        System.out.println("sortDatas");
        MjtAnnexRank instance = new MjtAnnexRankImpl();
        instance.sortDatas();
    }

    /**
     * Test of getColumnCount method, of class MjtAnnexRank.
     */
    @Test
    public void testGetColumnCount() {
        System.out.println("getColumnCount");
        MjtAnnexRank instance = new MjtAnnexRankImpl();
        int expResult = 0;
        int result = instance.getColumnCount();
        assertEquals(result, expResult);
    }

    /**
     * Test of getRowCount method, of class MjtAnnexRank.
     */
    @Test
    public void testGetRowCount() {
        System.out.println("getRowCount");
        MjtAnnexRank instance = new MjtAnnexRankImpl();
        int expResult = 0;
        int result = instance.getRowCount();
        assertEquals(result, expResult);
    }

    /**
     * Test of getColumnName method, of class MjtAnnexRank.
     */
    @Test
    public void testGetColumnName() {
        System.out.println("getColumnName");
        int col = 0;
        MjtAnnexRank instance = new MjtAnnexRankImpl();
        String expResult = "";
        String result = instance.getColumnName(col);
        assertEquals(result, expResult);
    }

    /**
     * Test of getValueAt method, of class MjtAnnexRank.
     */
    @Test
    public void testGetValueAt() {
        System.out.println("getValueAt");
        int row = 0;
        int col = 0;
        MjtAnnexRank instance = new MjtAnnexRankImpl();
        Object expResult = null;
        Object result = instance.getValueAt(row, col);
        assertEquals(result, expResult);
    }

    /**
     * Test of getTableCellRendererComponent method, of class MjtAnnexRank.
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
        MjtAnnexRank instance = new MjtAnnexRankImpl();
        Component expResult = null;
        Component result = instance.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        assertTrue (result instanceof JLabel);
    }

    public class MjtAnnexRankImpl extends MjtAnnexRank {

        public MjtAnnexRankImpl() {
            super(0, null, 0, null, false, 0, 0, 0, 0, 0, false);
        }

        public void sortDatas() {
        }

        public int getColumnCount() {
            return 0;
        }

        public String getColumnName(int col) {
            return "";
        }

        public Object getValueAt(int row, int col) {
            return null;
        }
    }

    /**
     * Test of getDetail method, of class MjtAnnexRank.
     */
    @Test
    public void testGetDetail() {
        System.out.println("getDetail");
        MjtAnnexRank instance = new MjtAnnexRankImpl();
        String expResult = "Test";
        instance.setDetail(expResult);
        String result = instance.getDetail();
        assertEquals(result, expResult);

    }

    /**
     * Test of setDetail method, of class MjtAnnexRank.
     */
    @Test
    public void testSetDetail() {
        System.out.println("setDetail");
        MjtAnnexRank instance = new MjtAnnexRankImpl();
        String expResult = "Test";
        instance.setDetail(expResult);
        String result = instance.getDetail();
        assertEquals(result, expResult);
    }

    /**
     * Test of getCriteria method, of class MjtAnnexRank.
     */
    @Test
    public void testGetCriteria() {
        System.out.println("getCriteria");
        MjtAnnexRank instance = new MjtAnnexRankImpl();
        Criteria expResult = new Criteria("Test");
        instance.setCriteria(expResult);
        Criteria result = instance.getCriteria();
        assertEquals(result, expResult);
    }

    /**
     * Test of setCriteria method, of class MjtAnnexRank.
     */
    @Test
    public void testSetCriteria() {
        System.out.println("setCriteria");
        Criteria c = null;
        MjtAnnexRank instance = null;
        instance.setCriteria(c);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
