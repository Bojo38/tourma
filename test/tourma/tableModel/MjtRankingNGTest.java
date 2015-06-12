/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.tableModel;

import java.awt.Component;
import java.util.ArrayList;
import javax.swing.JTable;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tourma.data.Coach;
import tourma.data.CoachMatch;
import tourma.data.Criteria;
import tourma.data.ObjectRanking;
import tourma.data.Team;
import tourma.data.TeamMatch;
/**
 *
 * @author WFMJ7631
 */
public class MjtRankingNGTest {
    
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
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }

    /**
     * Test of getOppPointsByCoach method, of class MjtRanking.
     */
    @Test
    public void testGetOppPointsByCoach() {
        System.out.println("getOppPointsByCoach");
        Coach c = null;
        CoachMatch m = null;
        int expResult = 0;
        int result = MjtRanking.getOppPointsByCoach(c, m,true);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCoachNbMatchs method, of class MjtRanking.
     */
    @Test
    public void testGetCoachNbMatchs() {
        System.out.println("getCoachNbMatchs");
        Coach c = null;
        CoachMatch m = null;
        int expResult = 0;
        int result = MjtRanking.getCoachNbMatchs(c, m);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOppELOByCoach method, of class MjtRanking.
     */
    @Test
    public void testGetOppELOByCoach() {
        System.out.println("getOppELOByCoach");
        Coach c = null;
        CoachMatch m = null;
        int expResult = 0;
        int result = MjtRanking.getOppELOByCoach(c, m);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getVNDByCoach method, of class MjtRanking.
     */
    @Test
    public void testGetVNDByCoach() {
        System.out.println("getVNDByCoach");
        Coach c = null;
        CoachMatch m = null;
        int expResult = 0;
        int result = MjtRanking.getVNDByCoach(c, m);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getELOByCoach method, of class MjtRanking.
     */
    @Test
    public void testGetELOByCoach() {
        System.out.println("getELOByCoach");
        Coach c = null;
        CoachMatch m = null;
        int expResult = 0;
        int result = MjtRanking.getELOByCoach(c, m);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPointsByCoach method, of class MjtRanking.
     */
    @Test
    public void testGetPointsByCoach() {
        System.out.println("getPointsByCoach");
        Coach c = null;
        CoachMatch m = null;
        int expResult = 0;
//        int result = MjtRanking.getPointsByCoach(c, m,true);
//        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getValue method, of class MjtRanking.
     */
    @Test
    public void testGetValue_4args_1() {
        System.out.println("getValue");
        Coach c = null;
        CoachMatch m = null;
        Criteria criteria = null;
        int sub_type = 0;
        int expResult = 0;
        int result = MjtRanking.getValue(c, m, criteria, sub_type);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSubtypeByValue method, of class MjtRanking.
     */
    @Test
    public void testGetSubtypeByValue() {
        System.out.println("getSubtypeByValue");
        int valueType = 0;
        int expResult = 0;
        int result = MjtRanking.getSubtypeByValue(valueType);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCriteriaByValue method, of class MjtRanking.
     */
    @Test
    public void testGetCriteriaByValue() {
        System.out.println("getCriteriaByValue");
        int valueType = 0;
        Criteria expResult = null;
        Criteria result = MjtRanking.getCriteriaByValue(valueType);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getValue method, of class MjtRanking.
     */
    @Test
    public void testGetValue_4args_2() {
        System.out.println("getValue");
        Coach c = null;
        CoachMatch m = null;
        int valueType = 0;
        int lastValue = 0;
        int expResult = 0;
/*        int result = MjtRanking.getValue(c, m, valueType, lastValue);
        assertEquals(result, expResult);*/
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRankingString method, of class MjtRanking.
     */
    @Test
    public void testGetRankingString() {
        System.out.println("getRankingString");
        int rankingType = 0;
        String expResult = "";
        String result = MjtRanking.getRankingString(rankingType);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSortedValue method, of class MjtRanking.
     */
    @Test
    public void testGetSortedValue() {
        System.out.println("getSortedValue");
        int index = 0;
        int valIndex = 0;
        MjtRanking instance = null;
        int expResult = 0;
        int result = instance.getSortedValue(index, valIndex);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSortedObject method, of class MjtRanking.
     */
    @Test
    public void testGetSortedObject() {
        System.out.println("getSortedObject");
        int index = 0;
        MjtRanking instance = null;
        ObjectRanking expResult = null;
        ObjectRanking result = instance.getSortedObject(index);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sortDatas method, of class MjtRanking.
     */
    @Test
    public void testSortDatas() {
        System.out.println("sortDatas");
        MjtRanking instance = null;
        instance.sortDatas();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getColumnCount method, of class MjtRanking.
     */
    @Test
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
    @Test
    public void testGetColumnName() {
        System.out.println("getColumnName");
        int col = 0;
        MjtRanking instance = null;
        String expResult = "";
        String result = instance.getColumnName(col);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getValueAt method, of class MjtRanking.
     */
    @Test
    public void testGetValueAt() {
        System.out.println("getValueAt");
        int row = 0;
        int col = 0;
        MjtRanking instance = null;
        Object expResult = null;
        Object result = instance.getValueAt(row, col);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRowCount method, of class MjtRanking.
     */
    @Test
    public void testGetRowCount() {
        System.out.println("getRowCount");
        MjtRanking instance = null;
        int expResult = 0;
        int result = instance.getRowCount();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTeamNbMatch method, of class MjtRanking.
     */
    @Test
    public void testGetTeamNbMatch() {
        System.out.println("getTeamNbMatch");
        Team T = null;
        MjtRanking instance = null;
        int expResult = 0;
/*        int result = instance.getTeamNbMatch(T);
        assertEquals(result, expResult);*/
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPointsByTeam method, of class MjtRanking.
     */
    @Test
    public void testGetPointsByTeam() {
        System.out.println("getPointsByTeam");
        Team t = null;
        MjtRanking instance = null;
        int expResult = 0;
        //int result = instance.getPointsByTeam(t, true,true,true);
//        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getELOByTeam method, of class MjtRanking.
     */
    @Test
    public void testGetELOByTeam() {
        System.out.println("getELOByTeam");
        Team t = null;
        int roundIndex = 0;
        MjtRanking instance = null;
        int expResult = 0;
/*        int result = instance.getELOByTeam(t, roundIndex);
        assertEquals(result, expResult);*/
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getVNDByTeam method, of class MjtRanking.
     */
    @Test
    public void testGetVNDByTeam() {
        System.out.println("getVNDByTeam");
        Team t = null;
        MjtRanking instance = null;
        int expResult = 0;
//        int result = instance.getVNDByTeam(t);
//        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOppPointsByTeam method, of class MjtRanking.
     */
    @Test
    public void testGetOppPointsByTeam() {
        System.out.println("getOppPointsByTeam");
        Team t = null;
        MjtRanking instance = null;
        int expResult = 0;
//        int result = instance.getOppPointsByTeam(t);
//        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOppELOByTeam method, of class MjtRanking.
     */
    @Test
    public void testGetOppELOByTeam() {
        System.out.println("getOppELOByTeam");
        Team t = null;
        int roundIndex = 0;
        MjtRanking instance = null;
        int expResult = 0;
/*        int result = instance.getOppELOByTeam(t, roundIndex);
        assertEquals(result, expResult);*/
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getValue method, of class MjtRanking.
     */
    @Test
    public void testGetValue_3args_1() {
        System.out.println("getValue");
        Team t = null;
        int rankingType = 0;
        boolean teamVictory = false;
        MjtRanking instance = null;
        int expResult = 0;
//        int result = instance.getValue(t, rankingType, teamVictory);
//        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getValue method, of class MjtRanking.
     */
    @Test
    public void testGetValue_3args_2() {
        System.out.println("getValue");
        Team t = null;
        Criteria crit = null;
        int subtype = 0;
        MjtRanking instance = null;
        int expResult = 0;
/*        int result = instance.getValue(t, crit, subtype);
        assertEquals(result, expResult);*/
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getValue method, of class MjtRanking.
     */
    @Test
    public void testGetValue_4args_3() {
        System.out.println("getValue");
        Team t = null;
        int rankingType = 0;
        int v = 0;
        boolean teamVictory = false;
        MjtRanking instance = null;
        int expResult = 0;
//        int result = instance.getValue(t, rankingType, v, teamVictory);
//        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getColumnClass method, of class MjtRanking.
     */
    @Test
    public void testGetColumnClass() {
        System.out.println("getColumnClass");
        int c = 0;
        MjtRanking instance = null;
        Class expResult = null;
        Class result = instance.getColumnClass(c);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isCellEditable method, of class MjtRanking.
     */
    @Test
    public void testIsCellEditable() {
        System.out.println("isCellEditable");
        int row = 0;
        int col = 0;
        MjtRanking instance = null;
        boolean expResult = false;
        boolean result = instance.isCellEditable(row, col);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTableCellRendererComponent method, of class MjtRanking.
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
        MjtRanking instance = null;
        Component expResult = null;
        Component result = instance.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class MjtRankingImpl extends MjtRanking {

        public MjtRankingImpl() {
            super(0, 0, 0, 0, 0, 0, null, false);
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
     * Test of getCoachTable method, of class MjtRanking.
     */
    @Test
    public void testGetCoachTable() {
        System.out.println("getCoachTable");
        Coach c = null;
        CoachMatch m = null;
        int expResult = 0;
        int result = MjtRanking.getCoachTable(c, m);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getValue method, of class MjtRanking.
     */
    @Test
    public void testGetValue_3args() {
        System.out.println("getValue");
        Coach c = null;
        CoachMatch m = null;
        int valueType = 0;
        int expResult = 0;
        int result = MjtRanking.getValue(c, m, valueType);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRankingFromString method, of class MjtRanking.
     */
    @Test
    public void testGetRankingFromString() {
        System.out.println("getRankingFromString");
        String ranking = "";
        ArrayList<String> criterias = null;
        int expResult = 0;
        int result = MjtRanking.getRankingFromString(ranking, criterias);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDetail method, of class MjtRanking.
     */
    @Test
    public void testGetDetail() {
        System.out.println("getDetail");
        MjtRanking instance = null;
        String expResult = "";
        String result = instance.getDetail();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDetail method, of class MjtRanking.
     */
    @Test
    public void testSetDetail() {
        System.out.println("setDetail");
        String d = "";
        MjtRanking instance = null;
        instance.setDetail(d);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getValueByRankingType method, of class MjtRanking.
     */
    @Test
    public void testGetValueByRankingType_3args_1() {
        System.out.println("getValueByRankingType");
        int rt = 0;
        Coach c = null;
        CoachMatch m = null;
        MjtRanking instance = null;
        int expResult = 0;
        int result = instance.getValueByRankingType(rt, c, m);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getValueByRankingType method, of class MjtRanking.
     */
    @Test
    public void testGetValueByRankingType_3args_2() {
        System.out.println("getValueByRankingType");
        int rt = 0;
        Team t = null;
        TeamMatch tm = null;
        MjtRanking instance = null;
        int expResult = 0;
        int result = instance.getValueByRankingType(rt, t, tm);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getValueFromArray method, of class MjtRanking.
     */
    @Test
    public void testGetValueFromArray() {
        System.out.println("getValueFromArray");
        int rt = 0;
        ArrayList<Integer> av = null;
        MjtRanking instance = null;
        int expResult = 0;
        int result = instance.getValueFromArray(rt, av);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeMinValue method, of class MjtRanking.
     */
    @Test
    public void testRemoveMinValue() {
        System.out.println("removeMinValue");
        ArrayList<Integer> aValue = null;
        MjtRanking instance = null;
        instance.removeMinValue(aValue);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeMaxValue method, of class MjtRanking.
     */
    @Test
    public void testRemoveMaxValue() {
        System.out.println("removeMaxValue");
        ArrayList<Integer> aValue = null;
        MjtRanking instance = null;
        instance.removeMaxValue(aValue);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTeamTable method, of class MjtRanking.
     */
    @Test
    public void testGetTeamTable() {
        System.out.println("getTeamTable");
        Team t = null;
        TeamMatch tm = null;
        MjtRanking instance = null;
        int expResult = 0;
        int result = instance.getTeamTable(t, tm);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRound method, of class MjtRanking.
     */
    @Test
    public void testGetRound() {
        System.out.println("getRound");
        MjtRanking instance = null;
        int expResult = 0;
        int result = instance.getRound();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
