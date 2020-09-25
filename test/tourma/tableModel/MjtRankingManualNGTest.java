/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.tableModel;

import java.awt.Component;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JTable;
import org.testng.Assert;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tourma.data.Coach;
import tourma.data.Competitor;
import tourma.data.Tournament;

/**
 *
 * @author WFMJ7631
 */
public class MjtRankingManualNGTest {

    static ArrayList<Coach> coachs = new ArrayList<>();
    static MjtRankingManual instance;

    public MjtRankingManualNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        Tournament.getTournament().loadXML(new File("./test/coach.xml"));

        for (int i = 0; i < Tournament.getTournament().getCoachsCount(); i++) {
            coachs.add(Tournament.getTournament().getCoach(i));
        }
        //crit=Tournament.getTournament().getParams().getCriteria(0);
        instance = new MjtRankingManual(Tournament.getTournament().getRoundsCount() - 1,
                Tournament.getTournament().getParams().getRankingIndiv1(),
                Tournament.getTournament().getParams().getRankingIndiv2(),
                Tournament.getTournament().getParams().getRankingIndiv3(),
                Tournament.getTournament().getParams().getRankingIndiv4(),
                Tournament.getTournament().getParams().getRankingIndiv5(),
                coachs,
                false);
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
        assertEquals(instance.getRowCount(), coachs.size());
        instance.sortDatas();
    }

    /**
     * Test of addData method, of class MjtRankingManual.
     */
    @Test
    public void testAddData() {
        System.out.println("addData");
        Competitor obj = new Coach("toto");
        int nb = instance.getRowCount();
        instance.addData(obj);
        assertEquals(nb + 1, instance.getRowCount());

        instance.delData(obj);
        assertEquals(nb, instance.getRowCount());

    }

    /**
     * Test of addDatas method, of class MjtRankingManual.
     */
    @Test
    public void testAddDatas() {
        System.out.println("addDatas");
        ArrayList<Competitor> objs = new ArrayList<>();
        for (Coach c:coachs)
        {
            objs.add((Competitor)(new Coach(c.getName()+"1")));
        }
        int nb=instance.getRowCount();
        instance.addDatas(objs);
        int nbtot=coachs.size();
        assertEquals(nbtot,instance.getRowCount());
        for (Competitor c:objs)
        {
            instance.delData(c);
        }
        assertEquals(nb, instance.getRowCount());
    }

    /**
     * Test of getColumnCount method, of class MjtRankingManual.
     */
    @Test
    public void testGetColumnCount() {
        System.out.println("getColumnCount");
        int expResult = 0;
        int result = instance.getColumnCount();
        assertEquals(result, expResult);
    }

    /**
     * Test of getColumnName method, of class MjtRankingManual.
     */
    @Test
    public void testGetColumnName() {
        System.out.println("getColumnName");
        String expResult = "";
        String result = instance.getColumnName(0);
        assertEquals(result, expResult);

    }

    /**
     * Test of getValueAt method, of class MjtRankingManual.
     */
    @Test
    public void testGetValueAt() {
        System.out.println("getValueAt");
        Object expResult = "";
        Object result = instance.getValueAt(0, 0);
        assertEquals(result, expResult);
    }

    /**
     * Test of getTableCellRendererComponent method, of class MjtRankingManual.
     */
    @Test
    public void testGetTableCellRendererComponent() {
        System.out.println("getTableCellRendererComponent");
        JTable table = new JTable();
        table.setModel(instance);
        for (int i = 0; i < instance.getColumnCount(); i++) {
            for (int j = 0; j < instance.getRowCount(); j++) {
                Component result = instance.getTableCellRendererComponent(table, instance.getValueAt(j, i), false, false, j, i);
                Assert.assertTrue(result instanceof JLabel);
            }
        }
    }

    /**
     * Test of delData method, of class MjtRankingManual.
     */
    @Test
    public void testDelData() {
        System.out.println("delData");
        Competitor obj = null;
        MjtRankingManual instance = null;
        instance.delData(obj);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
