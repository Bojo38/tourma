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
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tourma.data.Coach;
import tourma.data.CoachMatch;
import static tourma.data.CoachMatch.C_STARTING_RANK;
import static tourma.data.CoachMatch.getCoachTablePoints;
import static tourma.data.CoachMatch.getOppPointsByCoach;
import tourma.data.Criteria;
import tourma.data.ObjectRanking;
import tourma.data.Parameters;
import tourma.data.Round;
import tourma.data.Team;
import tourma.data.TeamMatch;
import tourma.data.Tournament;
import tourma.data.Value;
import tourma.languages.Translate;
import static tourma.tableModel.MjtRanking.getCriteriaByValue;
import static tourma.tableModel.MjtRanking.getSubtypeByValue;
import tourma.utility.StringConstants;

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
        instance = new MjtRankingIndiv(Tournament.getTournament().getRoundsCount() - 1,
                coachs,
                false, false);

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
        for (Coach c : coachs) {
            CoachMatch m = (CoachMatch) c.getMatch(0);
            Value val = m.getValue(Tournament.getTournament().getParams().getCriteria(0));
            Coach opp = null;
            int tdPlayer = 0;
            int tdOpp = 0;
            if (m.getCompetitor1() == c) {
                opp = (Coach) m.getCompetitor2();
                tdPlayer = val.getValue1();
                tdOpp = val.getValue2();
            }
            if (m.getCompetitor2() == c) {
                opp = (Coach) m.getCompetitor1();
                tdOpp = val.getValue1();
                tdPlayer = val.getValue2();
            }

            int value = 0;
            if (Tournament.getTournament().getParams().isUseLargeVictory()) {
                if (tdOpp - Tournament.getTournament().getParams().getGapLargeVictory() >= tdPlayer) {
                    value += Tournament.getTournament().getParams().getPointsIndivLargeVictory();
                } else {
                    if (tdPlayer < tdOpp) {
                        value += Tournament.getTournament().getParams().getPointsIndivVictory();
                    }
                }
            } else {
                if (tdPlayer < tdOpp) {
                    value += Tournament.getTournament().getParams().getPointsIndivVictory();
                }
            }

            if (tdPlayer == tdOpp) {
                value += Tournament.getTournament().getParams().getPointsIndivDraw();
            }

            if (tdPlayer > tdOpp) {
                if (Tournament.getTournament().getParams().isUseLittleLoss()) {
                    if (tdOpp + Tournament.getTournament().getParams().getGapLargeVictory() >= tdPlayer) {
                        value += Tournament.getTournament().getParams().getPointsIndivLittleLost();
                    } else {
                        value += Tournament.getTournament().getParams().getPointsIndivLost();
                    }
                } else {
                    value += Tournament.getTournament().getParams().getPointsIndivLost();
                }
            }

            int bonus = 0;
            // Add/Remove Bonuses
            for (int i = 0; i < Tournament.getTournament().getParams().getCriteriaCount(); i++) {
                Criteria crit = Tournament.getTournament().getParams().getCriteria(i);
                val = m.getValue(crit);
                if (m.getCompetitor1() == c) {
                    bonus += val.getValue2() * crit.getPointsFor();
                    bonus += val.getValue1() * crit.getPointsAgainst();
                }
                if (m.getCompetitor2() == c) {
                    bonus += val.getValue1() * crit.getPointsFor();
                    bonus += val.getValue2() * crit.getPointsAgainst();
                }
            }

            if (Tournament.getTournament().getParams().isTableBonus()) {
                double coef = Tournament.getTournament().getParams().getTableBonusCoef();
                bonus += Math.round(getCoachTablePoints(c, m) * coef);
            }

            if (Tournament.getTournament().getParams().isTableBonusPerRound()) {
                // Find Round
                Round round = null;
                for (int cpt = 0; cpt < Tournament.getTournament().getRoundsCount(); cpt++) {

                    Round r = Tournament.getTournament().getRound(cpt);
                    if (r.containsMatch(m)) {
                        round = r;
                    }
                }
                if (round != null) {
                    if (round.getMatchsCount() > 0) {
                        double fBonus = bonus * round.getCoef(m);
                        bonus = (int) Math.round(fBonus);
                    }

                }
            }
            int expResult = value + bonus;

            int result = MjtRanking.getOppPointsByCoach(c, m, true);
            assertEquals(result, expResult);

        }
    }

    /**
     * Test of getCoachNbMatchs method, of class MjtRanking.
     */
    @Test
    public void testGetCoachNbMatchs() {
        System.out.println("getCoachNbMatchs");
        for (Coach c : coachs) {
            for (int i = 0; i < c.getMatchCount(); i++) {
                CoachMatch m = (CoachMatch) c.getMatch(i);
                int expResult = i + 1;
                int result = MjtRanking.getCoachNbMatchs(c, m);
                assertEquals(result, expResult);
            }
        }
    }

    /**
     * Test of getOppELOByCoach method, of class MjtRanking.
     */
    @Test
    public void testGetOppELOByCoach() {
        System.out.println("getOppELOByCoach");
        for (Coach c : coachs) {
            CoachMatch m = (CoachMatch) c.getMatch(0);
            Value val = m.getValue(Tournament.getTournament().getParams().getCriteria(0));
            Coach opp = null;
            int lastPlayerRank = C_STARTING_RANK;
            int lastOppRank = C_STARTING_RANK;

            int tdPlayer = 0;
            int tdOpp = 0;
            if (m.getCompetitor1() == c) {
                opp = (Coach) m.getCompetitor2();
                tdPlayer = val.getValue1();
                tdOpp = val.getValue2();
            }
            if (m.getCompetitor2() == c) {
                opp = (Coach) m.getCompetitor1();
                tdOpp = val.getValue1();
                tdPlayer = val.getValue2();
            }

            double tmp = ((lastPlayerRank - lastOppRank) / (double) 200);
            double EA = 1 / (1 + Math.pow(10.0, tmp));
            // Compute real score
            // Victory is 1000
            // All bonuses to 1
            double SA = 0;
            if (tdPlayer < tdOpp) {
                SA = 1000;
            }
            if (tdPlayer > tdOpp) {
                SA = 0;
            }
            if (tdPlayer == tdOpp) {
                SA = 500;
            }

            // Add/Remove Bonuses
            for (int i = 0; i < Tournament.getTournament().getParams().getCriteriaCount(); i++) {
                Criteria crit = Tournament.getTournament().getParams().getCriteria(i);
                val = m.getValue(crit);
                if (m.getCompetitor1() == c) {
                    SA -= val.getValue1() * crit.getPointsFor();
                    SA += val.getValue2() * crit.getPointsFor();

                    SA += val.getValue1() * crit.getPointsAgainst();
                    SA -= val.getValue2() * crit.getPointsAgainst();
                }
                if (m.getCompetitor2() == c) {
                    SA += val.getValue1() * crit.getPointsFor();
                    SA -= val.getValue2() * crit.getPointsFor();

                    SA += val.getValue2() * crit.getPointsAgainst();
                    SA -= val.getValue1() * crit.getPointsAgainst();
                }
            }
            double diff = SA / 1000 - EA;
            int expResult = Math.round((float) (lastOppRank + C_ELO_K * diff));

            int result = MjtRanking.getOppELOByCoach(c, m);
            assertEquals(result, expResult);
        }
    }

    /**
     * Test of getVNDByCoach method, of class MjtRanking.
     */
    @Test
    public void testGetVNDByCoach() {
        System.out.println("getVNDByCoach");
        for (Coach c : coachs) {
            CoachMatch m = (CoachMatch) c.getMatch(0);
            Value val = m.getValue(Tournament.getTournament().getParams().getCriteria(0));
            Coach opp = null;
            int tdPlayer = 0;
            int tdOpp = 0;
            if (m.getCompetitor1() == c) {
                opp = (Coach) m.getCompetitor2();
                tdPlayer = val.getValue1();
                tdOpp = val.getValue2();
            }
            if (m.getCompetitor2() == c) {
                opp = (Coach) m.getCompetitor1();
                tdOpp = val.getValue1();
                tdPlayer = val.getValue2();
            }

            int value = 0;
            if (tdPlayer > tdOpp) {
                value += 1000000;
            }

            if (tdPlayer == tdOpp) {
                value += 1000;
            }

            if (tdPlayer < tdOpp) {
                value += 1;

            }

            int expResult = value;

            int result = MjtRanking.getVNDByCoach(c, m);
            assertEquals(result, expResult);

        }
    }

    /**
     * Test of getELOByCoach method, of class MjtRanking.
     */
    @Test
    public void testGetELOByCoach() {
        System.out.println("getELOByCoach");
        for (Coach c : coachs) {
            CoachMatch m = (CoachMatch) c.getMatch(0);
            Value val = m.getValue(Tournament.getTournament().getParams().getCriteria(0));
            Coach opp = null;
            int lastPlayerRank = C_STARTING_RANK;
            int lastOppRank = C_STARTING_RANK;

            int tdPlayer = 0;
            int tdOpp = 0;
            if (m.getCompetitor1() == c) {
                opp = (Coach) m.getCompetitor2();
                tdPlayer = val.getValue1();
                tdOpp = val.getValue2();
            }
            if (m.getCompetitor2() == c) {
                opp = (Coach) m.getCompetitor1();
                tdOpp = val.getValue1();
                tdPlayer = val.getValue2();
            }

            double tmp = ((lastOppRank - lastPlayerRank) / (double) 200);
            double EA = 1 / (1 + Math.pow(10.0, tmp));
            // Compute real score
            // Victory is 1000
            // All bonuses to 1
            double SA = 0;
            if (tdPlayer > tdOpp) {
                SA = 1000;
            }
            if (tdPlayer < tdOpp) {
                SA = 0;
            }
            if (tdPlayer == tdOpp) {
                SA = 500;
            }

            // Add/Remove Bonuses
            for (int i = 0; i < Tournament.getTournament().getParams().getCriteriaCount(); i++) {
                Criteria crit = Tournament.getTournament().getParams().getCriteria(i);
                val = m.getValue(crit);
                if (m.getCompetitor1() == c) {
                    SA += val.getValue1() * crit.getPointsFor();
                    SA -= val.getValue2() * crit.getPointsFor();

                    SA -= val.getValue1() * crit.getPointsAgainst();
                    SA += val.getValue2() * crit.getPointsAgainst();
                }
                if (m.getCompetitor2() == c) {
                    SA -= val.getValue1() * crit.getPointsFor();
                    SA += val.getValue2() * crit.getPointsFor();

                    SA -= val.getValue2() * crit.getPointsAgainst();
                    SA += val.getValue1() * crit.getPointsAgainst();
                }
            }
            double diff = SA / 1000 - EA;
            int expResult = Math.round((float) (lastPlayerRank + C_ELO_K * diff));

            int result = MjtRanking.getELOByCoach(c, m);
            assertEquals(result, expResult);
        }
    }

    /**
     * Test of getPointsByCoach method, of class MjtRanking.
     */
    @Test
    public void testGetPointsByCoach() {
        System.out.println("getPointsByCoach");
        for (Coach c : coachs) {
            CoachMatch m = (CoachMatch) c.getMatch(0);
            Value val = m.getValue(Tournament.getTournament().getParams().getCriteria(0));
            Coach opp = null;
            int tdPlayer = 0;
            int tdOpp = 0;
            if (m.getCompetitor1() == c) {
                opp = (Coach) m.getCompetitor2();
                tdPlayer = val.getValue1();
                tdOpp = val.getValue2();
            }
            if (m.getCompetitor2() == c) {
                opp = (Coach) m.getCompetitor1();
                tdOpp = val.getValue1();
                tdPlayer = val.getValue2();
            }

            int value = 0;
            if (Tournament.getTournament().getParams().isUseLargeVictory()) {
                if (tdPlayer - Tournament.getTournament().getParams().getGapLargeVictory() >= tdOpp) {
                    value += Tournament.getTournament().getParams().getPointsIndivLargeVictory();
                } else {
                    if (tdPlayer > tdOpp) {
                        value += Tournament.getTournament().getParams().getPointsIndivVictory();
                    }
                }
            } else {
                if (tdPlayer > tdOpp) {
                    value += Tournament.getTournament().getParams().getPointsIndivVictory();
                }
            }

            if (tdPlayer == tdOpp) {
                value += Tournament.getTournament().getParams().getPointsIndivDraw();
            }

            if (tdPlayer < tdOpp) {
                if (Tournament.getTournament().getParams().isUseLittleLoss()) {
                    if (tdPlayer + Tournament.getTournament().getParams().getGapLargeVictory() >= tdOpp) {
                        value += Tournament.getTournament().getParams().getPointsIndivLittleLost();
                    } else {
                        value += Tournament.getTournament().getParams().getPointsIndivLost();
                    }
                } else {
                    value += Tournament.getTournament().getParams().getPointsIndivLost();
                }
            }

            int bonus = 0;
            // Add/Remove Bonuses
            for (int i = 0; i < Tournament.getTournament().getParams().getCriteriaCount(); i++) {
                Criteria crit = Tournament.getTournament().getParams().getCriteria(i);
                val = m.getValue(crit);
                if (m.getCompetitor1() == c) {
                    bonus += val.getValue1() * crit.getPointsFor();
                    bonus += val.getValue2() * crit.getPointsAgainst();
                }
                if (m.getCompetitor2() == c) {
                    bonus += val.getValue2() * crit.getPointsFor();
                    bonus += val.getValue1() * crit.getPointsAgainst();
                }
            }

            if (Tournament.getTournament().getParams().isTableBonus()) {
                double coef = Tournament.getTournament().getParams().getTableBonusCoef();
                bonus += Math.round(getCoachTablePoints(c, m) * coef);
            }

            if (Tournament.getTournament().getParams().isTableBonusPerRound()) {
                // Find Round
                Round round = null;
                for (int cpt = 0; cpt < Tournament.getTournament().getRoundsCount(); cpt++) {

                    Round r = Tournament.getTournament().getRound(cpt);
                    if (r.containsMatch(m)) {
                        round = r;
                    }
                }
                if (round != null) {
                    if (round.getMatchsCount() > 0) {
                        double fBonus = bonus * round.getCoef(m);
                        bonus = (int) Math.round(fBonus);
                    }

                }
            }
            int expResult = value;

            int result = MjtRanking.getPointsByCoach(c, m, true, false);
            assertEquals(result, expResult);

            expResult = bonus;

            result = MjtRanking.getPointsByCoach(c, m, false, true);
            assertEquals(result, expResult);

            expResult = value + bonus;

            result = MjtRanking.getPointsByCoach(c, m, true, true);
            assertEquals(result, expResult);

        }
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
            int result = MjtRanking.getSubtypeByValue(valueType);
            assertEquals(result, expResult);

            valueType = Parameters.C_MAX_RANKING + 1 + i * 3 + 1;
            expResult = Parameters.C_RANKING_SUBTYPE_NEGATIVE;
            result = MjtRanking.getSubtypeByValue(valueType);
            assertEquals(result, expResult);

            valueType = Parameters.C_MAX_RANKING + 1 + i * 3 + 2;
            expResult = Parameters.C_RANKING_SUBTYPE_DIFFERENCE;
            result = MjtRanking.getSubtypeByValue(valueType);
            assertEquals(result, expResult);
        }
        int valueType = 0;
        int expResult = -1;
        int result = MjtRanking.getSubtypeByValue(valueType);
        assertEquals(result, expResult);
    }

    /**
     * Test of getCriteriaByValue method, of class MjtRanking.
     */
    @Test
    public void testGetCriteriaByValue() {
        System.out.println("getCriteriaByValue");
        for (int i = Parameters.C_MAX_RANKING; i < Parameters.C_MAX_RANKING + Tournament.getTournament().getParams().getCriteriaCount() * 3; i++) {
            int valueType = i;
            Criteria expResult = null;
            Criteria result = MjtRanking.getCriteriaByValue(valueType);
            if (valueType == Parameters.C_MAX_RANKING) {
                expResult = null;
            } else {
                int index = valueType - Parameters.C_MAX_RANKING - 1;
                index /= 3;
                expResult = Tournament.getTournament().getParams().getCriteria(index);
            }
            assertEquals(result, expResult);
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
            final Criteria c = MjtRanking.getCriteriaByValue(rankingType);
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
            assertEquals(result, expResult);
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

        instance = new MjtRankingIndiv(Tournament.getTournament().getRoundsCount() - 1,
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
        }
    }

    /**
     * Test of getSortedObject method, of class MjtRanking.
     */
    @Test
    public void testGetSortedObject() {
        System.out.println("getSortedObject");
        for (int i = 0; i < instance.getRowCount(); i++) {
            ObjectRanking result = instance.getSortedObject(i);
            assertTrue(coachs.contains(result.getObject()));
        }
    }

    /**
     * Test of sortDatas method, of class MjtRanking.
     */
    @Test(enabled = false)
    public void testSortDatas() {
        System.out.println("sortDatas");
        instance.sortDatas();
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
     * Test of getTeamNbMatch method, of class MjtRanking.
     */
    @Test
    public void testGetTeamNbMatch() {
        System.out.println("getTeamNbMatch");
        Tournament.resetTournament();
        Tournament.getTournament().loadXML(new File("./test/team.xml"));

        for (int i = 0; i < Tournament.getTournament().getCoachsCount(); i++) {
            coachs.add(Tournament.getTournament().getCoach(i));
        }
        instance = new MjtRankingIndiv(Tournament.getTournament().getRoundsCount() - 1,
                coachs,
                false, true);

        for (int i = 0; i < Tournament.getTournament().getTeamsCount(); i++) {
            Team t = Tournament.getTournament().getTeam(i);
            for (int j = 0; j < t.getMatchCount(); j++) {
                TeamMatch m = (TeamMatch) t.getMatch(j);
                int expResult = j + 1;
                int result = instance.getTeamNbMatch(t, m);
                assertEquals(result, expResult);
            }
        }
    }

    /**
     * Test of getPointsByTeam method, of class MjtRanking.
     */
    @Test
    public void testGetPointsByTeam() {
        System.out.println("getPointsByTeam");
        Tournament.resetTournament();
        Tournament.getTournament().loadXML(new File("./test/team.xml"));

        for (int i = 0; i < Tournament.getTournament().getCoachsCount(); i++) {
            coachs.add(Tournament.getTournament().getCoach(i));
        }
        instance = new MjtRankingIndiv(Tournament.getTournament().getRoundsCount() - 1,
                coachs,
                false, true);

        for (int i = 0; i < Tournament.getTournament().getTeamsCount(); i++) {
            Team t = Tournament.getTournament().getTeam(i);
            TeamMatch tm = (TeamMatch) t.getMatch(Tournament.getTournament().getRoundsCount() - 1);

            int nbVic = tm.getVictories(t);
            int nbLos = tm.getLoss(t);
            int value = 0;
            if (nbVic > nbLos) {
                value += Tournament.getTournament().getParams().getPointsTeamVictory();
            }

            if (nbVic == nbLos) {
                value += Tournament.getTournament().getParams().getPointsTeamDraw();
            }

            if (nbVic < nbLos) {

                value += Tournament.getTournament().getParams().getPointsTeamLost();
            }

            int bonus = 0;

            for (int k = 0; k < tm.getMatchCount(); k++) {
                CoachMatch m = tm.getMatch(k);
                Value val;

                Coach c = null;
                if (t.containsCoach((Coach) m.getCompetitor1())) {
                    c = (Coach) m.getCompetitor1();
                } else {
                    c = (Coach) m.getCompetitor2();
                }

                // Add/Remove Bonuses
                for (int j = 0; j < Tournament.getTournament().getParams().getCriteriaCount(); j++) {
                    Criteria crit = Tournament.getTournament().getParams().getCriteria(j);
                    val = m.getValue(crit);
                    if (m.getCompetitor1() == c) {
                        bonus += val.getValue1() * crit.getPointsFor();
                        bonus += val.getValue2() * crit.getPointsAgainst();
                    }
                    if (m.getCompetitor2() == c) {
                        bonus += val.getValue2() * crit.getPointsFor();
                        bonus += val.getValue1() * crit.getPointsAgainst();
                    }
                }

                if (Tournament.getTournament().getParams().isTableBonus()) {
                    double coef = Tournament.getTournament().getParams().getTableBonusCoef();
                    bonus += Math.round(getCoachTablePoints(c, m) * coef);
                }

                if (Tournament.getTournament().getParams().isTableBonusPerRound()) {
                    // Find Round
                    Round round = null;
                    for (int cpt = 0; cpt < Tournament.getTournament().getRoundsCount(); cpt++) {

                        Round r = Tournament.getTournament().getRound(cpt);
                        if (r.containsMatch(m)) {
                            round = r;
                        }
                    }
                    if (round != null) {
                        if (round.getMatchsCount() > 0) {
                            double fBonus = bonus * round.getCoef(m);
                            bonus = (int) Math.round(fBonus);
                        }
                    }
                }
            }
            int expResult = value;

            int result = instance.getPointsByTeam(t, tm, true, false);
            assertEquals(result, expResult);

            expResult = bonus;

            result = instance.getPointsByTeam(t, tm, false, true);
            assertEquals(result, expResult);

            expResult = value + bonus;

            result = instance.getPointsByTeam(t, tm, true, true);
            assertEquals(result, expResult);

        }
    }

    /**
     * Test of getELOByTeam method, of class MjtRanking.
     */
    @Test
    public void testGetELOByTeam() {
        System.out.println("getELOByTeam");
        Tournament.resetTournament();
        Tournament.getTournament().loadXML(new File("./test/team.xml"));

        for (int i = 0; i < Tournament.getTournament().getCoachsCount(); i++) {
            coachs.add(Tournament.getTournament().getCoach(i));
        }
        instance = new MjtRankingIndiv(Tournament.getTournament().getRoundsCount() - 1,
                coachs,
                false, false);

        for (int i = 0; i < Tournament.getTournament().getTeamsCount(); i++) {
            Team t = Tournament.getTournament().getTeam(0);
            int roundIndex = 0;

            int expResult = 0;
            TeamMatch tm = (TeamMatch) t.getMatch(0);
            int nbVic = tm.getVictories(t);
            int nbDraw = tm.getVictories(t);

            int lastTeamRank = C_STARTING_RANK;
            int lastOppRank = C_STARTING_RANK;

            Team opp = null;
            if (tm.getCompetitor1() == t) {
                opp = (Team) tm.getCompetitor2();

            }
            if (tm.getCompetitor2() == t) {
                opp = (Team) tm.getCompetitor1();
            }

            double tmp = ((lastOppRank - lastTeamRank) / (double) 400);
            double EA = 1 / (1 + Math.pow(10.0, tmp));

            // Compute real score
            // Victory is 1000
            // All bonuses to 1
            double SA = 0;
            if (nbVic > nbDraw) {
                SA = 1000;
            }
            if (nbVic < nbDraw) {
                SA = 0;
            }
            if (nbVic == nbDraw) {
                SA = 500;
            }

            // Add/Remove Bonuses
            for (int j = 0; j < Tournament.getTournament().getParams().getCriteriaCount(); j++) {
                Criteria crit = Tournament.getTournament().getParams().getCriteria(j);
                for (int k = 0; k < tm.getMatchCount(); k++) {
                    CoachMatch m = tm.getMatch(k);
                    Value val = m.getValue(crit);
                    if (tm.getCompetitor1() == t) {
                        SA += val.getValue1();
                        SA -= val.getValue2();
                    }
                    if (tm.getCompetitor2() == t) {
                        SA -= val.getValue1();
                        SA += val.getValue2();
                    }
                }
            }
            expResult = Math.round((float) (lastTeamRank + C_ELO_K * (SA - EA)));
            int result = instance.getELOByTeam(t, tm, roundIndex);
            assertEquals(result, expResult);
        }
    }

    /**
     * Test of getVNDByTeam method, of class MjtRanking.
     */
    @Test
    public void testGetVNDByTeam() {
        System.out.println("getVNDByTeam");
        Tournament.resetTournament();
        Tournament.getTournament().loadXML(new File("./test/team.xml"));

        instance = new MjtRankingIndiv(0,
                coachs,
                false, true);

        for (int i = 0; i < Tournament.getTournament().getTeamsCount(); i++) {
            Team t = Tournament.getTournament().getTeam(i);
            TeamMatch m = (TeamMatch) t.getMatch(0);

            Team opp = null;
            int vPlayer = 0;
            int vOpp = 0;
            if (m.getCompetitor1() == t) {
                opp = (Team) m.getCompetitor2();

            }
            if (m.getCompetitor2() == t) {
                opp = (Team) m.getCompetitor1();
            }
            vPlayer = m.getVictories(t);
            vOpp = m.getVictories(opp);

            int value = 0;
            if (vPlayer > vOpp) {
                value += 1000000;
            }

            if (vPlayer == vOpp) {
                value += 1000;
            }

            if (vPlayer < vOpp) {
                value += 1;

            }

            int expResult = value;

            int result = instance.getVNDByTeam(t, m, true);
            assertEquals(result, expResult);

        }
    }

    /**
     * Test of getOppPointsByTeam method, of class MjtRanking.
     */
    @Test
    public void testGetOppPointsByTeam() {
        System.out.println("getOppPointsByTeam");
        Tournament.resetTournament();
        Tournament.getTournament().loadXML(new File("./test/team.xml"));

        for (int i = 0; i < Tournament.getTournament().getCoachsCount(); i++) {
            coachs.add(Tournament.getTournament().getCoach(i));
        }
        instance = new MjtRankingIndiv(Tournament.getTournament().getRoundsCount() - 1,
                coachs,
                false, true);

        for (int i = 0; i < Tournament.getTournament().getTeamsCount(); i++) {
            Team t = Tournament.getTournament().getTeam(i);
            TeamMatch tm = (TeamMatch) t.getMatch(Tournament.getTournament().getRoundsCount() - 1);

            int nbVic = tm.getVictories(t);
            int nbLos = tm.getLoss(t);
            int value = 0;
            if (nbVic < nbLos) {
                value += Tournament.getTournament().getParams().getPointsTeamVictory();
            }

            if (nbVic == nbLos) {
                value += Tournament.getTournament().getParams().getPointsTeamDraw();
            }

            if (nbVic > nbLos) {

                value += Tournament.getTournament().getParams().getPointsTeamLost();
            }

            int bonus = 0;

            for (int k = 0; k < tm.getMatchCount(); k++) {
                CoachMatch m = tm.getMatch(k);
                Value val;

                Coach c = null;
                if (t.containsCoach((Coach) m.getCompetitor1())) {
                    c = (Coach) m.getCompetitor2();
                } else {
                    c = (Coach) m.getCompetitor1();
                }

                // Add/Remove Bonuses
                for (int j = 0; j < Tournament.getTournament().getParams().getCriteriaCount(); j++) {
                    Criteria crit = Tournament.getTournament().getParams().getCriteria(j);
                    val = m.getValue(crit);
                    if (m.getCompetitor1() == c) {
                        bonus += val.getValue1() * crit.getPointsFor();
                        bonus += val.getValue2() * crit.getPointsAgainst();
                    }
                    if (m.getCompetitor2() == c) {
                        bonus += val.getValue2() * crit.getPointsFor();
                        bonus += val.getValue1() * crit.getPointsAgainst();
                    }
                }

                if (Tournament.getTournament().getParams().isTableBonus()) {
                    double coef = Tournament.getTournament().getParams().getTableBonusCoef();
                    bonus += Math.round(getCoachTablePoints(c, m) * coef);
                }

                if (Tournament.getTournament().getParams().isTableBonusPerRound()) {
                    // Find Round
                    Round round = null;
                    for (int cpt = 0; cpt < Tournament.getTournament().getRoundsCount(); cpt++) {

                        Round r = Tournament.getTournament().getRound(cpt);
                        if (r.containsMatch(m)) {
                            round = r;
                        }
                    }
                    if (round != null) {
                        if (round.getMatchsCount() > 0) {
                            double fBonus = bonus * round.getCoef(m);
                            bonus = (int) Math.round(fBonus);
                        }
                    }
                }
            }
            int expResult = value + bonus;

            int result = instance.getOppPointsByTeam(t, tm, false);
            assertEquals(result, expResult);
        }
    }

    /**
     * Test of getOppELOByTeam method, of class MjtRanking.
     */
    @Test
    public void testGetOppELOByTeam() {
        System.out.println("getOppELOByTeam");
        Tournament.resetTournament();
        Tournament.getTournament().loadXML(new File("./test/team.xml"));

        for (int i = 0; i < Tournament.getTournament().getCoachsCount(); i++) {
            coachs.add(Tournament.getTournament().getCoach(i));
        }
        instance = new MjtRankingIndiv(Tournament.getTournament().getRoundsCount() - 1,
                coachs,
                false, false);

        for (int i = 0; i < Tournament.getTournament().getTeamsCount(); i++) {
            Team t = Tournament.getTournament().getTeam(0);
            int roundIndex = 0;

            int expResult = 0;
            TeamMatch tm = (TeamMatch) t.getMatch(0);
            int nbVic = tm.getVictories(t);
            int nbDraw = tm.getVictories(t);

            int lastTeamRank = C_STARTING_RANK;
            int lastOppRank = C_STARTING_RANK;

            Team opp = null;
            if (tm.getCompetitor1() == t) {
                opp = (Team) tm.getCompetitor2();

            }
            if (tm.getCompetitor2() == t) {
                opp = (Team) tm.getCompetitor1();
            }

            double tmp = ((lastTeamRank - lastOppRank) / (double) 400);
            double EA = 1 / (1 + Math.pow(10.0, tmp));

            // Compute real score
            // Victory is 1000
            // All bonuses to 1
            double SA = 0;
            if (nbVic < nbDraw) {
                SA = 1000;
            }
            if (nbVic > nbDraw) {
                SA = 0;
            }
            if (nbVic == nbDraw) {
                SA = 500;
            }

            // Add/Remove Bonuses
            for (int j = 0; j < Tournament.getTournament().getParams().getCriteriaCount(); j++) {
                Criteria crit = Tournament.getTournament().getParams().getCriteria(j);
                for (int k = 0; k < tm.getMatchCount(); k++) {
                    CoachMatch m = tm.getMatch(k);
                    Value val = m.getValue(crit);
                    if (tm.getCompetitor1() == t) {
                        SA -= val.getValue1();
                        SA += val.getValue2();
                    }
                    if (tm.getCompetitor2() == t) {
                        SA += val.getValue1();
                        SA -= val.getValue2();
                    }
                }
            }
            expResult = Math.round((float) (lastOppRank + C_ELO_K * (SA - EA)));
            int result = instance.getOppELOByTeam(t, tm, roundIndex);
            assertEquals(result, expResult);
        }
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
     * Test of getCoachTablePoints method, of class MjtRanking.
     */
    @Test
    public void testGetCoachTable() {
        System.out.println("getCoachTable");
        for (Coach c : coachs) {
            Round r = Tournament.getTournament().getRound(Tournament.getTournament().getRoundsCount() - 1);
            ArrayList<CoachMatch> acm = r.getCoachMatchs();
            int expResult = -1;
            CoachMatch m = null;
            for (int i = 0; i < acm.size(); i++) {
                CoachMatch cm = acm.get(i);
                if ((cm.getCompetitor1() == c) || (cm.getCompetitor2() == c)) {
                    m = cm;
                    expResult = acm.size() - i;
                    break;
                }
            }
            int result = MjtRanking.getCoachTablePoints(c, m);
            assertEquals(result, expResult);
        }
    }

    /**
     * Test of getRankingFromString method, of class MjtRanking.
     */
    @Test
    public void testGetRankingFromString() {
        System.out.println("getRankingFromString");

        for (int cpt = 0; cpt < Parameters.C_MAX_RANKING + Tournament.getTournament().getParams().getCriteriaCount() * 3; cpt++) {
            String ranking = MjtRanking.getRankingString(cpt);

            ArrayList<String> criterias = new ArrayList<>();
            for (int i = 0; i < Tournament.getTournament().getParams().getCriteriaCount(); i++) {
                criterias.add(Tournament.getTournament().getParams().getCriteria(i).getName());
            }

            int expResult = Parameters.C_RANKING_NONE;

            if (ranking.equals(Translate.translate(Translate.CS_Points))) {
                expResult = Parameters.C_RANKING_POINTS;
            }
            if (ranking.equals(Translate.translate(Translate.CS_Points_Without_Bonus))) {
                expResult = Parameters.C_RANKING_POINTS_WITHOUT_BONUS;
            }
            if (ranking.equals(Translate.translate(Translate.CS_Bonus_Points))) {
                expResult = Parameters.C_RANKING_BONUS_POINTS;
            }

            if (ranking.equals(Translate.translate(Translate.CS_Nothing))) {
                expResult = Parameters.C_RANKING_NONE;
            }
            if (ranking.equals(Translate.translate(Translate.CS_ACCR_Opponent_Points))) {
                expResult = Parameters.C_RANKING_OPP_POINTS;
            }
            if (ranking.equals(Translate.translate(Translate.CS_ACCR_Opponent_Points_Without_Bonus))) {
                expResult = Parameters.C_RANKING_OPP_POINTS_OTHER_MATCHS;
            }
            if (ranking.equals(Translate.translate(Translate.CS_ACCR_Victory_Drawn_Lost))) {
                expResult = Parameters.C_RANKING_VND;
            }
            if (ranking.equals(Translate.translate(Translate.CS_ELO))) {
                expResult = Parameters.C_RANKING_ELO;
            }

            if (ranking.equals(Translate.translate(Translate.CS_OpponentsElo))) {
                expResult = Parameters.C_RANKING_ELO_OPP;
            }
            if (ranking.equals(Translate.translate(Translate.CS_MatchCount))) {
                expResult = Parameters.C_RANKING_NB_MATCHS;
            }
            if (ranking.equals(Translate.translate(Translate.CS_TablesPoints))) {
                expResult = Parameters.C_RANKING_TABLES;
            }

            if (ranking.endsWith(" " + Translate.translate(Translate.CS_Coach))) {
                String tmp = ranking.replace(" " + Translate.translate(Translate.CS_Coach), StringConstants.CS_NULL);
                int i = criterias.indexOf(tmp);
                expResult = Parameters.C_MAX_RANKING + 1 + (i * 3);

            }
            if (ranking.endsWith(" " + Translate.translate(Translate.CS_Opponent))) {
                String tmp = ranking.replace(" " + Translate.translate(Translate.CS_Opponent), StringConstants.CS_NULL);
                int i = criterias.indexOf(tmp);
                expResult = Parameters.C_MAX_RANKING + 1 + (i * 3) + 1;
            }
            if (ranking.endsWith(" " + Translate.translate(Translate.CS_Difference))) {
                String tmp = ranking.replace(" " + Translate.translate(Translate.CS_Difference), StringConstants.CS_NULL);
                int i = criterias.indexOf(tmp);
                expResult = Parameters.C_MAX_RANKING + 1 + (i * 3) + 2;
            }

            int result = MjtRanking.getRankingFromString(ranking, criterias);
            assertEquals(result, expResult);
        }
    }

    /**
     * Test of getDetail method, of class MjtRanking.
     */
    @Test
    public void testGetDetail() {
        System.out.println("getDetail");
        String expResult = "Test";
        instance.setDetail(expResult);
        String result = instance.getDetail();
        assertEquals(result, expResult);
    }

    /**
     * Test of setDetail method, of class MjtRanking.
     */
    @Test
    public void testSetDetail() {
        System.out.println("setDetail");
        String expResult = "Test";
        instance.setDetail(expResult);
        String result = instance.getDetail();
        assertEquals(result, expResult);

    }

    /**
     * Test of getValueByRankingType method, of class MjtRanking.
     */
    @Test
    public void testGetValueByRankingType_3args_1() {
        System.out.println("getValueByRankingType");

        for (Coach c : coachs) {
            Round r = Tournament.getTournament().getRound(Tournament.getTournament().getRoundsCount() - 1);
            ArrayList<CoachMatch> acm = r.getCoachMatchs();
            for (int cpt = 0; cpt < c.getMatchCount(); cpt++) {
                CoachMatch cm = (CoachMatch) c.getMatch(cpt);
                for (int i = 0; i < Parameters.C_MAX_RANKING + Tournament.getTournament().getParams().getCriteriaCount() * 3; i++) {
                    final Criteria c1 = getCriteriaByValue(i);
                    final int subType1 = getSubtypeByValue(i);
                    int value = -1;
                    if (c1 == null) {
                        value = instance.getValue(c, cm, i);
                    } else {
                        value = instance.getValue(c, cm, c1, subType1);
                    }

                    int expResult = value;
                    int result = instance.getValueByRankingType(i, c, cm);
                    assertEquals(result, expResult);
                }
            }
        }
    }

    /**
     * Test of getValueByRankingType method, of class MjtRanking.
     */
    @Test
    public void testGetValueByRankingType_3args_2() {
        System.out.println("getValueByRankingType");

        Tournament.resetTournament();
        Tournament.getTournament().loadXML(new File("./test/team.xml"));

        for (int i = 0; i < Tournament.getTournament().getCoachsCount(); i++) {
            coachs.add(Tournament.getTournament().getCoach(i));
        }
        instance = new MjtRankingIndiv(Tournament.getTournament().getRoundsCount() - 1,
                coachs,
                false, true);

        for (int j = 0; j < Tournament.getTournament().getTeamsCount(); j++) {
            Team t = Tournament.getTournament().getTeam(j);
            Round r = Tournament.getTournament().getRound(Tournament.getTournament().getRoundsCount() - 1);
            for (int cpt = 0; cpt < t.getMatchCount(); cpt++) {
                TeamMatch tm = (TeamMatch) t.getMatch(cpt);
                for (int i = 0; i < Parameters.C_MAX_RANKING + Tournament.getTournament().getParams().getCriteriaCount() * 3; i++) {
                    final Criteria c1 = getCriteriaByValue(i);
                    final int subType1 = getSubtypeByValue(i);
                    int value = -1;
                    if (c1 == null) {
                        value = instance.getValue(t, tm, i, Tournament.getTournament().getParams().isTeamVictoryOnly());
                    } else {
                        value = instance.getValue(t, tm, c1, subType1);
                    }

                    int expResult = value;
                    int result = instance.getValueByRankingType(i, t, tm);
                    assertEquals(result, expResult);
                }
            }
        }
    }

    /**
     * Test of getValueFromArray method, of class MjtRanking.
     */
    @Test
    public void testGetValueFromArray() {
        System.out.println("getValueFromArray");
        ArrayList<Integer> aValue = new ArrayList<>();
        int expResult = 0;
        for (int i = 0; i < 200; i++) {
            aValue.add(i);
            expResult += i;
        }
        int result = instance.getValueFromArray(Parameters.C_RANKING_POINTS, aValue);
        assertEquals(result, expResult);
        result = instance.getValueFromArray(Parameters.C_RANKING_NB_MATCHS, aValue);
        expResult = 199;
        assertEquals(result, expResult);
    }

    /**
     * Test of removeMinValue method, of class MjtRanking.
     */
    @Test
    public void testRemoveMinValue() {
        System.out.println("removeMinValue");
        ArrayList<Integer> aValue = new ArrayList<>();
        for (int i = 0; i < 200; i++) {
            aValue.add(i);
        }
        instance.removeMinValue(aValue);
        assertEquals(aValue.size(), 199);
        assertFalse(aValue.contains(0));
    }

    /**
     * Test of removeMaxValue method, of class MjtRanking.
     */
    @Test
    public void testRemoveMaxValue() {
        System.out.println("removeMaxValue");
        ArrayList<Integer> aValue = new ArrayList<>();
        for (int i = 0; i < 200; i++) {
            aValue.add(i);
        }
        instance.removeMinValue(aValue);
        assertEquals(aValue.size(), 199);
        assertFalse(aValue.contains(200));
    }

    /**
     * Test of getTeamTable method, of class MjtRanking.
     */
    @Test
    public void testGetTeamTable() {
        System.out.println("getTeamTable");

        Tournament.resetTournament();
        Tournament.getTournament().loadXML(new File("./test/team.xml"));

        for (int i = 0; i < Tournament.getTournament().getCoachsCount(); i++) {
            coachs.add(Tournament.getTournament().getCoach(i));
        }
        instance = new MjtRankingIndiv(Tournament.getTournament().getRoundsCount() - 1,
                coachs,
                false, true);

        for (int i = 0; i < Tournament.getTournament().getTeamsCount(); i++) {
            Team t = Tournament.getTournament().getTeam(i);
            Round r = Tournament.getTournament().getRound(Tournament.getTournament().getRoundsCount() - 1);
            ArrayList<TeamMatch> atm = new ArrayList<>();
            for (int j = 0; j < r.getMatchsCount(); j++) {
                atm.add((TeamMatch) r.getMatch(j));
            }

            int expResult = -1;
            TeamMatch m = null;
            for (int j = 0; j < atm.size(); j++) {
                TeamMatch cm = atm.get(j);
                if ((cm.getCompetitor1() == t) || (cm.getCompetitor2() == t)) {
                    m = cm;
                    expResult = atm.size() - j;
                    break;
                }
            }
            int result = instance.getTeamTable(t, m);
            assertEquals(result, expResult);
        }
    }

    /**
     * Test of getRound method, of class MjtRanking.
     */
    @Test
    public void testGetRound() {
        System.out.println("getRound");
        int expResult = Tournament.getTournament().getRoundsCount() - 1;
        int result = instance.getRound();
        assertEquals(result, expResult);
    }

    /**
     * Test of getCoachTablePoints method, of class MjtRanking.
     */
    @Test
    public void testGetCoachTablePoints() {
        System.out.println("getCoachTablePoints");
        for (Coach c : coachs) {
            CoachMatch m = (CoachMatch) c.getMatch(Tournament.getTournament().getRoundsCount() - 1);
            Value val = m.getValue(Tournament.getTournament().getParams().getCriteria(0));
            Round r = Tournament.getTournament().getRound(Tournament.getTournament().getRoundsCount() - 1);
            int index = -1;
            for (int i = 0; i < r.getMatchsCount(); i++) {
                if (r.getMatch(i) == m) {
                    index = i;
                    break;
                }
            }
            int expResult = r.getMatchsCount() - index;

            int result = MjtRanking.getCoachTablePoints(c, m);
            assertEquals(result, expResult);

        }
    }

    /**
     * Test of getValue method, of class MjtRanking.
     */
    @Test
    public void testGetValue_4args() {
        System.out.println("getValue");
        for (Coach c : coachs) {
            for (int i = 0; i < c.getMatchCount(); i++) {
                CoachMatch m = (CoachMatch) c.getMatch(i);
                for (int j = 0; j < Tournament.getTournament().getParams().getCriteriaCount(); j++) {
                    Criteria criteria = Tournament.getTournament().getParams().getCriteria(j);                    
                    for (int k = 0; k < 3; k++) {
                        int expResult = 0;
                        if (k == Parameters.C_RANKING_SUBTYPE_POSITIVE) {
                            if (c == m.getCompetitor1()) {
                                expResult += Math.max(m.getValue(criteria).getValue1(), 0);
                            } else {
                                expResult += Math.max(m.getValue(criteria).getValue2(), 0);
                            }
                        } else {
                            if (k == Parameters.C_RANKING_SUBTYPE_NEGATIVE) {
                                if (c == m.getCompetitor1()) {
                                    expResult += Math.max(m.getValue(criteria).getValue2(), 0);
                                } else {
                                    expResult += Math.max(m.getValue(criteria).getValue1(), 0);
                                }
                            } else {
                                if (c == m.getCompetitor1()) {
                                    expResult += m.getValue(criteria).getValue1() - m.getValue(criteria).getValue2();
                                } else {
                                    expResult += m.getValue(criteria).getValue2() - m.getValue(criteria).getValue1();
                                }
                            }
                        }
                        int result = MjtRanking.getValue(c, m, criteria, k);
                        assertEquals(result, expResult);
                    }

                }
            }
        }
    }

    /**
     * Test of getValue method, of class MjtRanking.
     */
    @Test
    public void testGetValue_3args() {
        System.out.println("getValue");
        for (Coach c : coachs) {
            for (int i = 0; i < c.getMatchCount(); i++) {
                CoachMatch m = (CoachMatch) c.getMatch(i);
                for (int j = 0; j < Parameters.C_MAX_RANKING; j++) {
                    int valueType = j;
                    int expResult = 0;
                    switch (valueType) {
                        case Parameters.C_RANKING_POINTS:
                            expResult = getPointsByCoach(c, m, true, true);
                            break;
                        case Parameters.C_RANKING_POINTS_WITHOUT_BONUS:
                            expResult = getPointsByCoach(c, m, true, false);
                            break;
                        case Parameters.C_RANKING_BONUS_POINTS:
                            expResult = getPointsByCoach(c, m, false, true);
                            break;
                        case Parameters.C_RANKING_NONE:
                            expResult = 0;
                            break;
                        case Parameters.C_RANKING_OPP_POINTS:
                            expResult = getOppPointsByCoach(c, m, true);
                            break;
                        case Parameters.C_RANKING_OPP_POINTS_OTHER_MATCHS:
                            expResult = getOppPointsByCoach(c, m, false);
                            break;
                        case Parameters.C_RANKING_VND:
                            expResult = getVNDByCoach(c, m);
                            break;
                        case Parameters.C_RANKING_ELO:
                            expResult = getELOByCoach(c, m);
                            break;
                        case Parameters.C_RANKING_ELO_OPP:
                            expResult = getOppELOByCoach(c, m);
                            break;
                        case Parameters.C_RANKING_NB_MATCHS:
                            expResult = getCoachNbMatchs(c, m);
                            break;
                        case Parameters.C_RANKING_TABLES:
                            expResult = getCoachTablePoints(c, m);
                            break;
                        default:
                            expResult = 0;
                            break;
                    }

                    int result = MjtRanking.getValue(c, m, valueType);
                    assertEquals(result, expResult);
                }
            }
        }
    }

    /**
     * Test of convertVND method, of class MjtRanking.
     */
    @Test
    public void testConvertVND() {
        System.out.println("convertVND");
        int value = 0;
        MjtRanking instance = null;
        String expResult = "";
        String result = instance.convertVND(value);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
