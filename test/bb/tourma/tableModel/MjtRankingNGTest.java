/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bb.tourma.tableModel;

import java.awt.Component;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JTable;
import org.testng.Assert;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import bb.tourma.data.Coach;
import bb.tourma.data.Criterion;
import bb.tourma.data.ObjectRanking;
import bb.tourma.data.Parameters;
import bb.tourma.data.Tournament;
import bb.tourma.languages.Translate;
import bb.tourma.utility.StringConstants;

/**
 *
 * @author WFMJ7631
 */
public class MjtRankingNGTest {

    static ArrayList<Coach> coachs = new ArrayList<>();
    static MjtRanking instance;

    public MjtRankingNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {

    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
        Tournament.resetTournament();
        Tournament.getTournament().loadXML(new File("./test/coach.xml"));

        coachs = new ArrayList<>();
        for (int i = 0; i < Tournament.getTournament().getCoachsCount(); i++) {
            coachs.add(Tournament.getTournament().getCoach(i));
        }
       /* instance = new MjtRankingIndiv(Tournament.getTournament().getRoundsCount() - 1,
                coachs,
                false, false);*/

    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }

  

    /**
     * Test of getSubtypeByValue method, of class MjtRanking.
     */
    @Test
    public void testGetSubtypeByValue() {
        System.out.println("getSubtypeByValue");
        for (int i = 0; i < Tournament.getTournament().getParams().getCriteriaCount(); i++) {
            int valueType = Parameters.C_MAX_RANKING + 1 + i * 3;
            int expResult = Parameters.C_RANKING_SUBTYPE_POSITIVE;
            /*int result = MjtRanking.getSubtypeByValue(valueType);
            assertEquals(result, expResult);

            valueType = Parameters.C_MAX_RANKING + 1 + i * 3 + 1;
            expResult = Parameters.C_RANKING_SUBTYPE_NEGATIVE;
            result = MjtRanking.getSubtypeByValue(valueType);
            assertEquals(result, expResult);

            valueType = Parameters.C_MAX_RANKING + 1 + i * 3 + 2;
            expResult = Parameters.C_RANKING_SUBTYPE_DIFFERENCE;
            result = MjtRanking.getSubtypeByValue(valueType);
            assertEquals(result, expResult);*/
        }
        int valueType = 0;
        int expResult = -1;
       /* int result = MjtRanking.getSubtypeByValue(valueType);
        assertEquals(result, expResult);*/
    }

    /**
     * Test of getCriteriaByValue method, of class MjtRanking.
     */
    @Test
    public void testGetCriteriaByValue() {
        System.out.println("getCriteriaByValue");
        for (int i = Parameters.C_MAX_RANKING; i < Parameters.C_MAX_RANKING + Tournament.getTournament().getParams().getCriteriaCount() * 3; i++) {
            int valueType = i;
            Criterion expResult = null;
           /* Criterion result = MjtRanking.getCriteriaByValue(valueType);
            if (valueType == Parameters.C_MAX_RANKING) {
                expResult = null;
            } else {
                int index = valueType - Parameters.C_MAX_RANKING - 1;
                index /= 3;
                expResult = Tournament.getTournament().getParams().getCriterion(index);
            }
            assertEquals(result, expResult);*/
        }
    }

    /**
     * Test of getRankingString method, of class MjtRanking.
     */
    @Test
    public void testGetRankingString() {
        System.out.println("getRankingString");
        for (int i = 0; i < Parameters.C_MAX_RANKING + Tournament.getTournament().getParams().getCriteriaCount() * 3; i++) {
            int rankingType = i;
            String expResult = "";
            /*final Criterion c = MjtRanking.getCriteriaByValue(rankingType);
            if (c == null) {
                switch (rankingType) {
                    case Parameters.C_RANKING_POINTS:
                        expResult = Translate.translate(Translate.CS_Points);
                        break;
                    case Parameters.C_RANKING_POINTS_WITHOUT_BONUS:
                        expResult = Translate.translate(Translate.CS_Points_Without_Bonus);
                        break;
                    case Parameters.C_RANKING_BONUS_POINTS:
                        expResult = Translate.translate(Translate.CS_Bonus_Points);
                        break;
                    case Parameters.C_RANKING_NONE:
                        expResult = Translate.translate(Translate.CS_Nothing);
                        break;
                    case Parameters.C_RANKING_OPP_POINTS:
                        expResult = Translate.translate(Translate.CS_ACCR_Opponent_Points);
                        break;
                    case Parameters.C_RANKING_OPP_POINTS_OTHER_MATCHS:
                        expResult = Translate.translate(Translate.CS_ACCR_Opponent_Points_Without_Bonus);
                        break;
                    case Parameters.C_RANKING_VND:
                        expResult = Translate.translate(Translate.CS_ACCR_Victory_Drawn_Lost);
                        break;
                    case Parameters.C_RANKING_ELO:
                        expResult = Translate.translate(Translate.CS_ELO);
                        break;
                    case Parameters.C_RANKING_ELO_OPP:
                        expResult = Translate.translate(Translate.CS_OpponentsElo);
                        break;
                    case Parameters.C_RANKING_NB_MATCHS:
                        expResult = Translate.translate(Translate.CS_MatchCount);
                        break;
                    case Parameters.C_RANKING_TABLES:
                        expResult = Translate.translate(Translate.CS_TablesPoints);
                        break;
                    default:
                }
            } else {
                final int subRanking = MjtRanking.getSubtypeByValue(rankingType);
                switch (subRanking) {
                    case Parameters.C_RANKING_SUBTYPE_POSITIVE:
                        expResult = c.getName() + " " + Translate.translate(Translate.CS_Coach);
                        break;
                    case Parameters.C_RANKING_SUBTYPE_NEGATIVE:
                        expResult = c.getName() + " " + Translate.translate(Translate.CS_Opponent);
                        break;
                    case Parameters.C_RANKING_SUBTYPE_DIFFERENCE:
                        expResult = c.getName() + " " + Translate.translate(Translate.CS_Difference);
                        break;
                    default:
                }
            }
            String result = MjtRanking.getRankingString(rankingType);
            assertEquals(result, expResult);*/
        }
    }

    /**
     * Test of getSortedValue method, of class MjtRanking.
     */
    @Test
    public void testGetSortedValue() {
        System.out.println("getSortedValue");
        Tournament.getTournament().getParams().setRankingIndiv1(Parameters.C_RANKING_ELO);
        Tournament.getTournament().getParams().setRankingIndiv2(Parameters.C_RANKING_POINTS);
        Tournament.getTournament().getParams().setRankingIndiv3(Parameters.C_RANKING_NB_MATCHS);
        Tournament.getTournament().getParams().setRankingIndiv4(Parameters.C_RANKING_ELO_OPP);
        Tournament.getTournament().getParams().setRankingIndiv5(Parameters.C_RANKING_POINTS_WITHOUT_BONUS);

        /*instance = new MjtRankingIndiv(Tournament.getTournament().getRoundsCount() - 1,
                coachs,
                false, false);

        for (int i = 0; i < instance.getRowCount(); i++) {
            ObjectRanking obj = instance.getSortedObject(i);
            int expResult = obj.getValue1();
            int result = instance.getSortedValue(i, 1);
            assertEquals(result, expResult);

            expResult = obj.getValue2();
            result = instance.getSortedValue(i, 2);
            assertEquals(result, expResult);

            expResult = obj.getValue3();
            result = instance.getSortedValue(i, 3);
            assertEquals(result, expResult);

            expResult = obj.getValue4();
            result = instance.getSortedValue(i, 4);
            assertEquals(result, expResult);

            expResult = obj.getValue5();
            result = instance.getSortedValue(i, 5);
            assertEquals(result, expResult);
        }*/
    }

    /**
     * Test of getSortedObject method, of class MjtRanking.
     */
    @Test
    public void testGetSortedObject() {
        System.out.println("getSortedObject");
        /*for (int i = 0; i < instance.getRowCount(); i++) {
            ObjectRanking result = instance.getSortedObject(i);
            assertTrue(coachs.contains(result.getObject()));
        }*/
    }

   

    /**
     * Test of getColumnCount method, of class MjtRanking.
     */
    @Test(enabled = false)
    public void testGetColumnCount() {
        System.out.println("getColumnCount");
        MjtRanking instance = null;
        int expResult = 0;
        int result = instance.getColumnCount();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getColumnName method, of class MjtRanking.
     */
    @Test(enabled = false)
    public void testGetColumnName() {
        System.out.println("getColumnName");
        int col = 0;
        MjtRanking instance = null;
        String expResult = "";
        String result = instance.getColumnName(col);
        assertEquals(result, expResult);
    }

    /**
     * Test of getValueAt method, of class MjtRanking.
     */
    @Test(enabled = false)
    public void testGetValueAt() {
        System.out.println("getValueAt");
        int row = 0;
        int col = 0;
        MjtRanking instance = null;
        Object expResult = null;
        Object result = instance.getValueAt(row, col);
        assertEquals(result, expResult);
    }

    /**
     * Test of getRowCount method, of class MjtRanking.
     */
    @Test
    public void testGetRowCount() {
        System.out.println("getRowCount");
        int expResult = coachs.size();
        int result = instance.getRowCount();
        assertEquals(result, expResult);
    }


    /**
     * Test of getColumnClass method, of class MjtRanking.
     */
    @Test(enabled = false)
    public void testGetColumnClass() {
        System.out.println("getColumnClass");
        int c = 0;
        Class expResult = null;
        Class result = instance.getColumnClass(c);
        assertEquals(result, expResult);
    }

    /**
     * Test of isCellEditable method, of class MjtRanking.
     */
    @Test
    public void testIsCellEditable() {
        System.out.println("isCellEditable");
        for (int row = 0; row < instance.getRowCount(); row++) {
            for (int col = 0; col < instance.getColumnCount(); col++) {

                boolean expResult = false;
                boolean result = instance.isCellEditable(row, col);
                assertEquals(result, expResult);
            }
        }
    }

    /**
     * Test of getTableCellRendererComponent method, of class MjtRanking.
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

    public class MjtRankingImpl extends MjtRanking {

        public MjtRankingImpl() {
            super(null,0,0);
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

  

}