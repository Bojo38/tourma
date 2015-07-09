/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.tableModel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import tourma.data.Coach;
import tourma.data.CoachMatch;
import tourma.data.Competitor;
import tourma.data.Criteria;
import tourma.data.ETeamPairing;
import tourma.data.Group;
import tourma.data.GroupPoints;
import tourma.data.ObjectRanking;
import tourma.data.Parameters;
import tourma.data.Round;
import tourma.data.Team;
import tourma.data.TeamMatch;
import tourma.data.Tournament;
import tourma.data.Value;
import tourma.languages.Translate;
import tourma.utility.StringConstants;
import tourma.utils.Ranked;

/**
 *
 * @author Frederic Berger
 */
@SuppressWarnings("serial")
abstract public class MjtRanking extends AbstractTableModel implements TableCellRenderer, Ranked {

    /**
     *
     */
    public static final int C_STARTING_RANK = 1000;
    /**
     *
     */
    public static final int C_ELO_K = 256;


    /**
     *
     * @param c
     * @param m
     * @param includeCurrent
     * @return
     */
    public static int getOppPointsByCoach(final Coach c, final CoachMatch m, boolean includeCurrent) {
        int index = 0;
        CoachMatch tmp_m = (CoachMatch) c.getMatch(index);
        while (tmp_m != m) {
            index++;
            tmp_m = (CoachMatch) c.getMatch(index);
        }

        int value = 0;
        Competitor opponent;
        if (m.getCompetitor1() == c) {
            opponent = m.getCompetitor2();
        } else {
            opponent = m.getCompetitor1();
        }

        if (((Coach) opponent) != null) {
            if (opponent.isMatchsNotNull()) {
                for (int i = 0; i < opponent.getMatchCount(); i++) {
                    CoachMatch om = (CoachMatch) opponent.getMatch(i);
                    if ((includeCurrent) || ((!includeCurrent) && (om != m))) {
                        value += getPointsByCoach((Coach) opponent, om, true, true);
                    }
                    if (om == m) {
                        break;
                    }
                }
            }
        }

        return value;
    }

    /**
     *
     * @param c
     * @param m
     * @return
     */
    public static int getCoachNbMatchs(final Coach c, final CoachMatch m) {
        int index = c.matchIndex(m);
        return index + 1;
    }

    public static int getCoachTablePoints(final Coach c, final CoachMatch m) {
        for (int i = 0; i < Tournament.getTournament().getRoundsCount(); i++) {
            Round r = Tournament.getTournament().getRound(i);
            if (r.containsMatch(m)) {
                // No point for first round
                if (i == 0) {
                    return 0;
                } else {
                    int maxvalue = r.getMatchsCount();
                    return maxvalue - r.indexOf(m);
                }
            }
        }
        return 0;
    }

    /**
     *
     * @param c
     * @param m
     * @return
     */
    public static int getOppELOByCoach(final Coach c, final CoachMatch m) {
        int value;
        Competitor opponent;
        if (m.getCompetitor1() == c) {
            opponent = m.getCompetitor2();
        } else {
            opponent = m.getCompetitor1();
        }
        value = getELOByCoach((Coach) opponent, m);

        return value;
    }

    /**
     *
     * @param c
     * @param m
     * @return
     */
    public static int getVNDByCoach(final Coach c, final CoachMatch m) {
        int value = 0;
        final Value val = m.getValue(Tournament.getTournament().getParams().getCriteria(0));
        if (val.getValue1() >= 0) {
            if (m.getCompetitor1() == c) {
                if (val.getValue1() > val.getValue2()) {
                    value += 1000000;
                } else {
                    if (val.getValue1() >= 0) {
                        if (val.getValue1() == val.getValue2()) {
                            value += 1000;
                        } else {
                            value += 1;
                        }
                    }
                }
            }
            if (m.getCompetitor2() == c) {
                if (val.getValue2() > val.getValue1()) {
                    value += 1000000;
                } else {
                    if (val.getValue1() >= 0) {
                        if (val.getValue1() == val.getValue2()) {
                            value += 1000;
                        } else {
                            value += 1;
                        }
                    }
                }
            }
        }
        return value;
    }

    /**
     *
     * @param c
     * @param m
     * @return
     */
    public static int getELOByCoach(final Coach c, final CoachMatch m) {
        int value = 0;

        Value val = m.getValue(Tournament.getTournament().getParams().getCriteria(0));
        if (val.getValue1() >= 0) {
            // Get current Round index
            int indexRound = -1;
            for (int i = 0; i < Tournament.getTournament().getRoundsCount(); i++) {
                Round r = Tournament.getTournament().getRound(i);
                if (r.getCoachMatchs().contains(m)) {
                    indexRound = i;
                    break;
                }
            }

            int lastPlayerRank = C_STARTING_RANK;
            int lastOppRank = C_STARTING_RANK;
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
            if (opp != null) {
                if (indexRound >= 0) {
                    // Find Previous Match for current player
                    int index = c.matchIndex(m);
                    if (index > 0) {
                        lastPlayerRank = getELOByCoach(c, (CoachMatch) c.getMatch(index - 1));
                    }

                    if (opp.isMatchsNotNull()) {
                        // Find Previous Match for oponnent player
                        index = opp.matchIndex(m);
                        if (index > 0) {
                            lastPlayerRank = getELOByCoach(opp, (CoachMatch) opp.getMatch(index - 1));
                        }
                    }
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
                value = Math.round((float) (lastPlayerRank + C_ELO_K * diff));
            }
        }
        return value;
    }

    /**
     *
     * @param c
     * @param m
     * @param withMainPoints
     * @param withBonusPOints
     * @return
     */
    public static int getPointsByCoach(final Coach c, final CoachMatch m, final boolean withMainPoints, final boolean withBonusPOints) {
        int value = 0;
        if (withMainPoints) {
            if (c.matchIndex(m) == 0) {
                value += c.getHandicap();
            }
        }
        final Criteria td = Tournament.getTournament().getParams().getCriteria(0);
        final Value val = m.getValue(td);
        if (m.getCompetitor1() == c) {
            if (m.isConcedeedBy1()) {
                if (withMainPoints) {
                    value += Tournament.getTournament().getParams().getPointsConcedeed();
                }
            } else {
                if (m.isRefusedBy1()) {
                    if (withMainPoints) {
                        value += Tournament.getTournament().getParams().getPointsRefused();
                    }
                } else {

                    if (val.getValue1() >= 0) {
                        if ((val.getValue1() >= val.getValue2() + Tournament.getTournament().getParams().getGapLargeVictory()) && Tournament.getTournament().getParams().isUseLargeVictory()) {
                            value += Tournament.getTournament().getParams().getPointsIndivLargeVictory();
                        } else {

                            if (withMainPoints) {
                                if (val.getValue1() > val.getValue2()) {
                                    value += Tournament.getTournament().getParams().getPointsIndivVictory();
                                } else {
                                    if (val.getValue1() == val.getValue2()) {
                                        value += Tournament.getTournament().getParams().getPointsIndivDraw();
                                    } else {
                                        if ((val.getValue1() + Tournament.getTournament().getParams().getGapLittleLost() >= val.getValue2()) && Tournament.getTournament().getParams().isUseLittleLoss()) {
                                            value += Tournament.getTournament().getParams().getPointsIndivLittleLost();
                                        } else {
                                            value += Tournament.getTournament().getParams().getPointsIndivLost();
                                        }
                                    }
                                }
                            }
                        }

                        if (withBonusPOints) {

                            int bonus = 0;

                            for (int j = 0; j < Tournament.getTournament().getParams().getCriteriaCount(); j++) {
                                final Criteria cri = Tournament.getTournament().getParams().getCriteria(j);
                                final Value va = m.getValue(cri);
                                bonus += va.getValue1() * cri.getPointsFor();
                                bonus += va.getValue2() * cri.getPointsAgainst();
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

                            value += bonus;
                        }
                    }
                    if (withMainPoints) {
                        value += getGroupModifier(c, m);
                    }
                }
            }
        }
        if (m.getCompetitor2() == c) {
            if (m.isConcedeedBy2()) {
                if (withMainPoints) {
                    value += Tournament.getTournament().getParams().getPointsConcedeed();
                }
            } else {
                if (m.isRefusedBy2()) {
                    if (withMainPoints) {
                        value += Tournament.getTournament().getParams().getPointsRefused();
                    }
                } else {
                    if (val.getValue1() >= 0) {
                        if (withMainPoints) {
                            if ((val.getValue2() >= val.getValue1() + Tournament.getTournament().getParams().getGapLargeVictory()) && Tournament.getTournament().getParams().isUseLargeVictory()) {
                                value += Tournament.getTournament().getParams().getPointsIndivLargeVictory();
                            } else {
                                if (val.getValue2() > val.getValue1()) {
                                    value += Tournament.getTournament().getParams().getPointsIndivVictory();
                                } else {
                                    if (val.getValue2() == val.getValue1()) {
                                        value += Tournament.getTournament().getParams().getPointsIndivDraw();
                                    } else {
                                        if ((val.getValue2() + Tournament.getTournament().getParams().getGapLittleLost() >= val.getValue1()) && Tournament.getTournament().getParams().isUseLittleLoss()) {
                                            value += Tournament.getTournament().getParams().getPointsIndivLittleLost();
                                        } else {
                                            value += Tournament.getTournament().getParams().getPointsIndivLost();
                                        }
                                    }
                                }
                            }
                        }
                        if (withBonusPOints) {
                            int bonus = 0;
                            for (int j = 0; j < Tournament.getTournament().getParams().getCriteriaCount(); j++) {

                                final Criteria cri = Tournament.getTournament().getParams().getCriteria(j);
                                final Value va = m.getValue(cri);
                                bonus += va.getValue2() * cri.getPointsFor();
                                bonus += va.getValue1() * cri.getPointsAgainst();
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

                            value += bonus;
                        }
                    }
                    if (withMainPoints) {
                        value += getGroupModifier(c, m);
                    }
                }
            }
        }
        return value;
    }

    /**
     *
     * @param c
     * @param m
     * @param criteria
     * @param sub_type
     * @return
     */
    public static int getValue(final Coach c, final CoachMatch m, final Criteria criteria, final int sub_type) {
        int value = 0;
        if (sub_type == Parameters.C_RANKING_SUBTYPE_POSITIVE) {
            if (c == m.getCompetitor1()) {
                value += Math.max(m.getValue(criteria).getValue1(), 0);
            } else {
                value += Math.max(m.getValue(criteria).getValue2(), 0);
            }
        } else {
            if (sub_type == Parameters.C_RANKING_SUBTYPE_NEGATIVE) {
                if (c == m.getCompetitor1()) {
                    value += Math.max(m.getValue(criteria).getValue2(), 0);
                } else {
                    value += Math.max(m.getValue(criteria).getValue1(), 0);
                }
            } else {
                if (c == m.getCompetitor1()) {
                    value += m.getValue(criteria).getValue1() - m.getValue(criteria).getValue2();
                } else {
                    value += m.getValue(criteria).getValue2() - m.getValue(criteria).getValue1();
                }
            }
        }

        return value;
    }

    /**
     *
     * @param valueType
     * @return
     */
    public static int getSubtypeByValue(final int valueType) {
        int subType = -1;
        if (valueType > Parameters.C_MAX_RANKING) {
            final int value = valueType - Parameters.C_MAX_RANKING - 1;
            subType = value % 3;
        }
        return subType;
    }

    /**
     *
     * @param valueType
     * @return
     */
    public static Criteria getCriteriaByValue(final int valueType) {
        Criteria criteria = null;

        if (valueType > Parameters.C_MAX_RANKING) {
            final int value = valueType - Parameters.C_MAX_RANKING - 1;
            criteria = Tournament.getTournament().getParams().getCriteria(value / 3);
        }
        return criteria;
    }

    /**
     * Find value for criteria and the current match
     *
     * @param c
     * @param m
     * @param valueType
     * @return
     */
    public static int getValue(final Coach c, final CoachMatch m, final int valueType) {
        int value;

        switch (valueType) {
            case Parameters.C_RANKING_POINTS:
                value = getPointsByCoach(c, m, true, true);
                break;
            case Parameters.C_RANKING_POINTS_WITHOUT_BONUS:
                value = getPointsByCoach(c, m, true, false);
                break;
            case Parameters.C_RANKING_BONUS_POINTS:
                value = getPointsByCoach(c, m, false, true);
                break;
            case Parameters.C_RANKING_NONE:
                value = 0;
                break;
            case Parameters.C_RANKING_OPP_POINTS:
                value = getOppPointsByCoach(c, m, true);
                break;
            case Parameters.C_RANKING_OPP_POINTS_OTHER_MATCHS:
                value = getOppPointsByCoach(c, m, false);
                break;
            case Parameters.C_RANKING_VND:
                value = getVNDByCoach(c, m);
                break;
            case Parameters.C_RANKING_ELO:
                value = getELOByCoach(c, m);
                break;
            case Parameters.C_RANKING_ELO_OPP:
                value = getOppELOByCoach(c, m);
                break;
            case Parameters.C_RANKING_NB_MATCHS:
                value = getCoachNbMatchs(c, m);
                break;
            case Parameters.C_RANKING_TABLES:
                value = getCoachTablePoints(c, m);
                break;
            default:
                value = 0;
                break;
        }

        return value;
    }

    public static int getRankingFromString(String ranking, ArrayList<String> criterias) {

        if (ranking.equals(Translate.translate(Translate.CS_Points))) {
            return Parameters.C_RANKING_POINTS;
        }
        if (ranking.equals(Translate.translate(Translate.CS_Points_Without_Bonus))) {
            return Parameters.C_RANKING_POINTS_WITHOUT_BONUS;
        }
        if (ranking.equals(Translate.translate(Translate.CS_Bonus_Points))) {
            return Parameters.C_RANKING_BONUS_POINTS;
        }

        if (ranking.equals(Translate.translate(Translate.CS_Nothing))) {
            return Parameters.C_RANKING_NONE;
        }
        if (ranking.equals(Translate.translate(Translate.CS_ACCR_Opponent_Points))) {
            return Parameters.C_RANKING_OPP_POINTS;
        }
        if (ranking.equals(Translate.translate(Translate.CS_ACCR_Opponent_Points_Without_Bonus))) {
            return Parameters.C_RANKING_OPP_POINTS_OTHER_MATCHS;
        }
        if (ranking.equals(Translate.translate(Translate.CS_ACCR_Victory_Drawn_Lost))) {
            return Parameters.C_RANKING_VND;
        }
        if (ranking.equals(Translate.translate(Translate.CS_ELO))) {
            return Parameters.C_RANKING_ELO;
        }

        if (ranking.equals(Translate.translate(Translate.CS_OpponentsElo))) {
            return Parameters.C_RANKING_ELO_OPP;
        }
        if (ranking.equals(Translate.translate(Translate.CS_MatchCount))) {
            return Parameters.C_RANKING_NB_MATCHS;
        }
        if (ranking.equals(Translate.translate(Translate.CS_TablesPoints))) {
            return Parameters.C_RANKING_TABLES;
        }

        if (ranking.endsWith(" "+Translate.translate(Translate.CS_Coach))) {
            String tmp = ranking.replace(" "+Translate.translate(Translate.CS_Coach), StringConstants.CS_NULL);
            int i = criterias.indexOf(tmp);
            return Parameters.C_MAX_RANKING + 1 + (i * 3);

        }
        if (ranking.endsWith(" "+Translate.translate(Translate.CS_Opponent))) {
            String tmp = ranking.replace(" "+Translate.translate(Translate.CS_Opponent), StringConstants.CS_NULL);
            int i = criterias.indexOf(tmp);
            return Parameters.C_MAX_RANKING + 1 + (i * 3) + 1;
        }
        if (ranking.endsWith(" "+Translate.translate(Translate.CS_Difference))) {
            String tmp = ranking.replace(" "+Translate.translate(Translate.CS_Difference), StringConstants.CS_NULL);
            int i = criterias.indexOf(tmp);
            return Parameters.C_MAX_RANKING + 1 + (i * 3) + 2;
        }

        return Parameters.C_RANKING_NONE;
    }

    /**
     *
     * @param rankingType
     * @return
     */
    public static String getRankingString(final int rankingType) {
        String result = StringConstants.CS_NULL;
        final Criteria c = MjtRanking.getCriteriaByValue(rankingType);
        if (c == null) {
            switch (rankingType) {
                case Parameters.C_RANKING_POINTS:
                    result = Translate.translate(Translate.CS_Points);
                    break;
                case Parameters.C_RANKING_POINTS_WITHOUT_BONUS:
                    result = Translate.translate(Translate.CS_Points_Without_Bonus);
                    break;
                case Parameters.C_RANKING_BONUS_POINTS:
                    result = Translate.translate(Translate.CS_Bonus_Points);
                    break;
                case Parameters.C_RANKING_NONE:
                    result = Translate.translate(Translate.CS_Nothing);
                    break;
                case Parameters.C_RANKING_OPP_POINTS:
                    result = Translate.translate(Translate.CS_ACCR_Opponent_Points);
                    break;
                case Parameters.C_RANKING_OPP_POINTS_OTHER_MATCHS:
                    result = Translate.translate(Translate.CS_ACCR_Opponent_Points_Without_Bonus);
                    break;
                case Parameters.C_RANKING_VND:
                    result = Translate.translate(Translate.CS_ACCR_Victory_Drawn_Lost);
                    break;
                case Parameters.C_RANKING_ELO:
                    result = Translate.translate(Translate.CS_ELO);
                    break;
                case Parameters.C_RANKING_ELO_OPP:
                    result = Translate.translate(Translate.CS_OpponentsElo);
                    break;
                case Parameters.C_RANKING_NB_MATCHS:
                    result = Translate.translate(Translate.CS_MatchCount);
                    break;
                case Parameters.C_RANKING_TABLES:
                    result = Translate.translate(Translate.CS_TablesPoints);
                    break;
                default:
            }
        } else {
            final int subRanking = MjtRanking.getSubtypeByValue(rankingType);
            switch (subRanking) {
                case Parameters.C_RANKING_SUBTYPE_POSITIVE:
                    result = c.getName() + " "+Translate.translate(Translate.CS_Coach);
                    break;
                case Parameters.C_RANKING_SUBTYPE_NEGATIVE:
                    result = c.getName() + " "+Translate.translate(Translate.CS_Opponent);
                    break;
                case Parameters.C_RANKING_SUBTYPE_DIFFERENCE:
                    result = c.getName() + " "+Translate.translate(Translate.CS_Difference);
                    break;
                default:
            }
        }
        return result;
    }
    
    private static int getGroupModifier(Coach c, CoachMatch m) {
        int value = 0;
        if (Tournament.getTournament().getGroupsCount() > 1) {
            final Criteria td = Tournament.getTournament().getParams().getCriteria(0);
            Group g = Tournament.getTournament().getGroup(c);
            if (g != null) {
                Value v = m.getValue(td);
                if ((v.getValue1() >= 0) && ((v.getValue2() >= 0))) {
                    if (m.getCompetitor1() == c) {
                        
                        Group go = Tournament.getTournament().getGroup((Coach) m.getCompetitor2());
                        GroupPoints gp = g.getOpponentModificationPoints(go);
                        if ((go != null) && (gp != null)) {
                            if (v.getValue1() > v.getValue2()) {
                                value = gp.getVictoryPoints();
                            } else {
                                if (v.getValue1() == v.getValue2()) {
                                    value = gp.getDrawPoints();
                                } else {
                                    value = gp.getLossPoints();
                                }
                            }
                        }
                        
                    }
                    if (m.getCompetitor2() == c) {
                        Group go = Tournament.getTournament().getGroup((Coach) m.getCompetitor1());
                        GroupPoints gp = g.getOpponentModificationPoints(go);
                        if ((go != null) && (gp != null)) {
                            if (v.getValue1() < v.getValue2()) {
                                value = gp.getVictoryPoints();
                            } else {
                                if (v.getValue1() == v.getValue2()) {
                                    value = gp.getDrawPoints();
                                } else {
                                    value = gp.getLossPoints();
                                }
                            }
                        }
                    }
                }
            }
        }
        return value;
    }
    protected String mDetails;

    /**
     * Current Round
     */
    @SuppressWarnings("ProtectedField")
    protected int mRound;
    /**
     * Objects to sort
     */
    @SuppressWarnings("ProtectedField")
    protected ArrayList mObjects;
    /**
     * Ranking type #1
     */
    @SuppressWarnings("ProtectedField")
    protected int mRankingType1;
    /**
     * Ranking type #2
     */
    @SuppressWarnings("ProtectedField")
    protected int mRankingType2;
    /**
     * Ranking type #3
     */
    @SuppressWarnings("ProtectedField")
    protected int mRankingType3;
    /**
     * Ranking type #4
     */
    @SuppressWarnings("ProtectedField")
    protected int mRankingType4;
    /**
     * Ranking type #5
     */
    @SuppressWarnings("ProtectedField")
    protected int mRankingType5;
    /**
     * Ranked datas
     */
    @SuppressWarnings("ProtectedField")
    protected ArrayList mDatas = new ArrayList();

    /**
     * Rank only on current round ?
     */
    @SuppressWarnings("ProtectedField")
    protected boolean mRoundOnly = false;

    /**
     *
     * @param round
     * @param ranking_type1
     * @param ranking_type2
     * @param ranking_type3
     * @param ranking_type4
     * @param ranking_type5
     * @param objects
     * @param roundOnly
     */
    public MjtRanking(final int round, final int ranking_type1, final int ranking_type2, final int ranking_type3, final int ranking_type4, final int ranking_type5, final ArrayList objects, final boolean roundOnly) {
        mRound = round;
        mRankingType1 = ranking_type1;
        mRankingType2 = ranking_type2;
        mRankingType3 = ranking_type3;
        mRankingType4 = ranking_type4;
        mRankingType5 = ranking_type5;
        mObjects = objects;
        mRoundOnly = roundOnly;
        //sortDatas();
    }
    
    public String getDetail() {
        return mDetails;
    }
    
    public void setDetail(String d) {
        mDetails = d;
    }
    
    protected int getValueByRankingType(int rt, Coach c, CoachMatch m) {
        int value = 0;
        final Criteria c1 = getCriteriaByValue(rt);
        final int subType1 = getSubtypeByValue(rt);
        if (c1 == null) {
            value = getValue(c, m, rt);
        } else {
            value = getValue(c, m, c1, subType1);
        }
        return value;
    }
    
    protected int getValueByRankingType(int rt, Team t, TeamMatch tm) {
        int value ;
        final Criteria c1 = getCriteriaByValue(rt);
        final int subType1 = getSubtypeByValue(rt);
        boolean teamVictory = Tournament.getTournament().getParams().isTeamVictoryOnly();
        if (c1 == null) {
            value = getValue(t, tm, rt, teamVictory);
        } else {
            value = getValue(t, tm, c1, subType1);
        }
        return value;
    }
    
    protected int getValueFromArray(int rt, ArrayList<Integer> av) {
        int value = 0;

        if (av.size() > 0) {
            if ((rt == Parameters.C_RANKING_ELO)
                    || (rt == Parameters.C_RANKING_NB_MATCHS)) {
                value = av.get(av.size() - 1);
            } else {
                for (Integer i : av) {
                    value += i;
                }
            }
        }
        
        return value;
    }

    protected void removeMinValue(ArrayList<Integer> aValue) {
        int min = Integer.MAX_VALUE;
        int index = 0;
        if (aValue.size() > 0) {
            for (int k = 0; k < aValue.size(); k++) {
                min = Math.min(min, aValue.get(k));
                if (min == aValue.get(k)) {
                    index = k;
                }
            }
            aValue.remove(index);
        }
    }
    
    protected void removeMaxValue(ArrayList<Integer> aValue) {
        int max = Integer.MIN_VALUE;
        int index = 0;
        if (aValue.size() > 0) {
            for (int k = 0; k < aValue.size(); k++) {
                max = Math.max(max, aValue.get(k));
                if (max == aValue.get(k)) {
                    index = k;
                }
            }
            aValue.remove(index);
        }
    }
    
    public int getTeamTable(final Team t, TeamMatch tm) {
        
        for (int i = 0; i < Tournament.getTournament().getRoundsCount(); i++) {
            Round r = Tournament.getTournament().getRound(i);
            if (r.containsMatch(tm)) {
                // No point for first round
                if (i == 0) {
                    return 0;
                } else {
                    int maxvalue = r.getMatchsCount();
                    return maxvalue - r.indexOf(tm);
                }
            }
        }
        return 0;
        
    }
    
    /**
     *
     * @return
     */
    /* public ArrayList<ObjectRanking> getSortedDatas() {
    return mDatas;
    }*/
    /**
     *
     * @param index
     * @param valIndex
     * @return
     */
    @Override
    public int getSortedValue(int index, int valIndex) {
        ObjectRanking obj = (ObjectRanking) mDatas.get(index);
        switch (valIndex) {

            case 1:
                return obj.getValue1();
            case 2:
                return obj.getValue2();
            case 3:
                return obj.getValue3();
            case 4:
                return obj.getValue4();
            case 5:
                return obj.getValue5();
            default:
                return 0;
        }
    }

    /**
     *
     * @param index
     * @return
     */
    @Override
    public ObjectRanking getSortedObject(int index) {
        ObjectRanking obj = (ObjectRanking) mDatas.get(index);
        return obj;
    }

    /**
     * Abstract sortDatas
     */
    protected abstract void sortDatas();

    @Override
    public abstract int getColumnCount();
    
    @Override
    public abstract String getColumnName(int col);

    @Override
    public abstract Object getValueAt(int row, int col);

    @Override
    public int getRowCount() {
        return mDatas.size();
    }

    int getTeamNbMatch(final Team t, TeamMatch tm) {
        int index = t.matchIndex(tm);
        return index + 1;
    }

    public int getPointsByTeam(final Team t, TeamMatch tm, boolean withMainPoints, boolean withBonus) {

        int value = 0;
        int countTeamVictories = 0;
        int countTeamLoss = 0;
        int countTeamDraw = 0;

        int i = 0;
        if (mRoundOnly) {
            i = mRound;
        }

        while (i <= mRound) {
            //for (int i = 0; i <= mRound; i++) {
            int victories = 0;
            int loss = 0;

            for (int j = 0; j < t.getCoachsCount(); j++) {
                final Coach c = t.getCoach(j);
                if (c.getMatchCount() > i) {
                    final CoachMatch m = (CoachMatch) c.getMatch(i);
                    if (tm.containsMatch(m)) {
                        final Criteria crit = Tournament.getTournament().getParams().getCriteria(0);
                        final Value val = m.getValue(crit);
                        if (m.getCompetitor1() == c) {
                            if (val.getValue1() > val.getValue2()) {
                                victories++;
                            } else {
                                if (val.getValue1() < val.getValue2()) {
                                    loss++;
                                }
                            }
                            if (withBonus) {

                                int bonus = 0;
                                for (int k = 0; k < Tournament.getTournament().getParams().getCriteriaCount(); k++) {
                                    final Criteria criteria = Tournament.getTournament().getParams().getCriteria(k);
                                    bonus += Math.max(m.getValue(criteria).getValue1(), 0) * criteria.getPointsTeamFor();
                                    bonus += Math.max(m.getValue(criteria).getValue2(), 0) * criteria.getPointsTeamAgainst();
                                }
                                if (Tournament.getTournament().getParams().isTableBonus()) {
                                    double coef = Tournament.getTournament().getParams().getTableBonusCoef();
                                    bonus += Math.round(getCoachTablePoints(c, m) * coef);
                                }

                                if (Tournament.getTournament().getParams().isTableBonusPerRound()) {
                                    // Find Round
                                    Round round = Tournament.getTournament().getRound(i);

                                    if (round != null) {
                                        if (round.getMatchsCount() > 0) {
                                            double fBonus = bonus * round.getCoef(m);
                                            bonus = (int) Math.round(fBonus);
                                        }

                                    }
                                }
                                value += bonus;
                            }

                        } else {
                            if (val.getValue1() < val.getValue2()) {
                                victories++;
                            } else {
                                if (val.getValue1() > val.getValue2()) {
                                    loss++;
                                }
                            }
                            if (withBonus) {
                                int bonus = 0;
                                for (int k = 0; k < Tournament.getTournament().getParams().getCriteriaCount(); k++) {
                                    final Criteria criteria = Tournament.getTournament().getParams().getCriteria(k);
                                    bonus += Math.max(m.getValue(criteria).getValue2(), 0) * criteria.getPointsTeamFor();
                                    bonus += Math.max(m.getValue(criteria).getValue1(), 0) * criteria.getPointsTeamAgainst();
                                }
                                if (Tournament.getTournament().getParams().isTableBonus()) {
                                    double coef = Tournament.getTournament().getParams().getTableBonusCoef();
                                    bonus += Math.round(getCoachTablePoints(c, m) * coef);
                                }

                                if (Tournament.getTournament().getParams().isTableBonusPerRound()) {
                                    // Find Round
                                    Round round = Tournament.getTournament().getRound(i);

                                    if (round != null) {
                                        if (round.getMatchsCount() > 0) {
                                            double fBonus = bonus * round.getCoef(m);
                                            bonus = (int) Math.round(fBonus);
                                        }

                                    }
                                }
                                value += bonus;
                            }
                        }
                    }
                }
            }
            if (victories > loss) {
                countTeamVictories++;
            } else {
                if (victories < loss) {
                    countTeamLoss++;
                } else {
                    countTeamDraw++;
                }
            }
            i++;
        }
        if (withMainPoints) {
            value += countTeamVictories * Tournament.getTournament().getParams().getPointsTeamVictory();
            value += countTeamLoss * Tournament.getTournament().getParams().getPointsTeamLost();
            value += countTeamDraw * Tournament.getTournament().getParams().getPointsTeamDraw();
        }

        return value;
    }

    int getELOByTeam(final Team t, TeamMatch tm, int roundIndex) {
        int value;

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
        if (roundIndex >= 0) {
            // Find Previous Match for current player

            if (roundIndex > 0) {
                lastTeamRank = getELOByTeam(t, tm, roundIndex - 1);
            }

            // Find Previous Match for oponnent player
            if (roundIndex > 0) {
                lastOppRank = getELOByTeam(opp, tm, roundIndex - 1);
            }
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
        for (int i = 0; i < Tournament.getTournament().getParams().getCriteriaCount(); i++) {
            Criteria crit = Tournament.getTournament().getParams().getCriteria(i);
            for (int j = 0; j < tm.getMatchCount(); j++) {
                CoachMatch m = tm.getMatch(j);
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
        value = Math.round((float) (lastTeamRank + C_ELO_K * (SA - EA)));

        return value;
    }

    public int getVNDByTeam(final Team t, TeamMatch tm, boolean includeCurrent) {

        int value = 0;
        int countTeamVictories = 0;
        int countTeamLoss = 0;
        int countTeamDraw = 0;

        int i = 0;
        if (mRoundOnly) {
            i = mRound;
        }

        while (i <= mRound) {
            //for (int i = 0; i <= mRound; i++) {
            int victories = 0;
            int loss = 0;

            for (int j = 0; j < t.getCoachsCount(); j++) {
                final Coach c = t.getCoach(j);
                if (c.getMatchCount() > i) {
                    final CoachMatch m = (CoachMatch) c.getMatch(i);
                    if (includeCurrent && tm.containsMatch(m)) {
                        final Criteria crit = Tournament.getTournament().getParams().getCriteria(0);
                        final Value val = m.getValue(crit);
                        if (m.getCompetitor1() == c) {
                            if (val.getValue1() > val.getValue2()) {
                                victories++;
                            } else {
                                if (val.getValue1() < val.getValue2()) {
                                    loss++;
                                }
                            }
                        } else {
                            if (val.getValue1() < val.getValue2()) {
                                victories++;
                            } else {
                                if (val.getValue1() > val.getValue2()) {
                                    loss++;
                                }
                            }
                        }
                    }
                }
            }
            if (victories > loss) {
                countTeamVictories++;
            } else {
                if (victories < loss) {
                    countTeamLoss++;
                } else {
                    countTeamDraw++;
                }
            }
            i++;
        }

        value += countTeamVictories * 1000000;
        value += countTeamLoss * 1;
        value += countTeamDraw * 1000;
        return value;
    }

    int getOppPointsByTeam(final Team t, TeamMatch tm, boolean includeCurrent) {

        int index = 0;
        TeamMatch tmp_m = (TeamMatch) t.getMatch(index);
        while (tmp_m != tm) {
            index++;
            tmp_m = (TeamMatch) t.getMatch(index);
        }

        int value = 0;
        Competitor opponent;
        if (tm.getCompetitor1() == t) {
            opponent = tm.getCompetitor2();
        } else {
            opponent = tm.getCompetitor1();
        }

        if (((Team) opponent) != null) {
            if (opponent.isMatchsNotNull()) {
                for (int i = 0; i < opponent.getMatchCount(); i++) {
                    TeamMatch om = (TeamMatch) opponent.getMatch(i);
                    if ((includeCurrent) || ((!includeCurrent) && (om != tm))) {
                        value += getPointsByTeam((Team) opponent, om, true, true);
                    }
                    if (om == tm) {
                        break;
                    }
                }
            }
        }

        return value;
    }

    int getOppELOByTeam(final Team t, TeamMatch tm, int roundIndex) {
        int value;
        Competitor opponent;
        if (tm.getCompetitor1() == t) {
            opponent = tm.getCompetitor2();
        } else {
            opponent = tm.getCompetitor1();
        }
        value = getELOByTeam((Team) opponent, tm, roundIndex);

        return value;
    }

    public int getValue(final Team t, TeamMatch tm, final int rankingType, boolean teamVictory) {
        int value = 0;

        // Find opposing team in using first Coach
        if (teamVictory) {
            switch (rankingType) {
                case Parameters.C_RANKING_POINTS:
                    value = getPointsByTeam(t, tm, true, true);
                    break;
                case Parameters.C_RANKING_POINTS_WITHOUT_BONUS:
                    value = getPointsByTeam(t, tm, true, false);
                    break;
                case Parameters.C_RANKING_BONUS_POINTS:
                    value = getPointsByTeam(t, tm, false, true);
                    break;
                case Parameters.C_RANKING_NONE:
                    value = 0;
                    break;
                case Parameters.C_RANKING_OPP_POINTS:
                    value = getOppPointsByTeam(t, tm, true);
                    break;
                case Parameters.C_RANKING_OPP_POINTS_OTHER_MATCHS:
                    value = getOppPointsByTeam(t, tm, false);
                    break;
                case Parameters.C_RANKING_VND:
                    value = getVNDByTeam(t, tm, true);
                    break;
                case Parameters.C_RANKING_ELO:
                    value = getELOByTeam(t, tm, mRound);
                    break;
                case Parameters.C_RANKING_ELO_OPP:
                    value = getOppELOByTeam(t, tm, mRound);
                    break;
                case Parameters.C_RANKING_NB_MATCHS:
                    value = getTeamNbMatch(t, tm);
                    break;
                case Parameters.C_RANKING_TABLES:
                    value = getTeamTable(t, tm);
                    break;
                default:
            }
        } else {
            /*for (int i = 0; i < t.getCoachsCount(); i++) {
            final Coach c = t.getCoach(i);
            //for (int j = 0; j < c.getMatchCount(); j++) {
            final CoachMatch m = (CoachMatch) c.getMatch(j);*/
            for (int i = 0; i < tm.getMatchCount(); i++) {
                CoachMatch cm = tm.getMatch(i);
                Coach c = null;
                Coach c1 = (Coach) cm.getCompetitor1();
                Coach c2 = (Coach) cm.getCompetitor2();
                if (c1.getTeamMates() == t) {
                    c = c1;
                }
                if (c2.getTeamMates() == t) {
                    c = c2;
                }
                if (c != null) {
                    switch (rankingType) {
                        case Parameters.C_RANKING_POINTS:
                            value += getPointsByCoach(c, cm, true, true);
                            break;
                        case Parameters.C_RANKING_POINTS_WITHOUT_BONUS:
                            value += getPointsByCoach(c, cm, true, false);
                            break;
                        case Parameters.C_RANKING_BONUS_POINTS:
                            value += getPointsByCoach(c, cm, false, true);
                            break;
                        case Parameters.C_RANKING_NONE:
                            value += 0;
                            break;
                        case Parameters.C_RANKING_OPP_POINTS:
                            value += getOppPointsByCoach(c, cm, true);
                            break;
                        case Parameters.C_RANKING_OPP_POINTS_OTHER_MATCHS:
                            value += getOppPointsByCoach(c, cm, false);
                            break;
                        case Parameters.C_RANKING_VND:
                            value += getVNDByCoach(c, cm);
                            break;
                        case Parameters.C_RANKING_ELO:
                            value += getELOByCoach(c, cm);
                            break;
                        case Parameters.C_RANKING_ELO_OPP:
                            value += getOppELOByCoach(c, cm);
                            break;
                        case Parameters.C_RANKING_NB_MATCHS:
                            value += getCoachNbMatchs(c, cm);
                            break;
                        case Parameters.C_RANKING_TABLES:
                            if (Tournament.getTournament().getParams().getTeamPairing() == ETeamPairing.TEAM_PAIRING) {
                                value = getTeamTable(t, tm);
                            } else {
                                value += getCoachTablePoints(c, cm);
                            }
                            break;
                        default:
                    }
                }
            }
            switch (rankingType) {
                case Parameters.C_RANKING_POINTS:
                    value += (getVNDByTeam(t, tm, true) / 1000000) * Tournament.getTournament().getParams().getPointsTeamVictoryBonus();
                    value += ((getVNDByTeam(t, tm, true) % 1000000) / 1000) * Tournament.getTournament().getParams().getPointsTeamDrawBonus();
                    break;
                case Parameters.C_RANKING_POINTS_WITHOUT_BONUS:
                    value += (getVNDByTeam(t, tm, true) / 1000000) * Tournament.getTournament().getParams().getPointsTeamVictoryBonus();
                    value += ((getVNDByTeam(t, tm, true) % 1000000) / 1000) * Tournament.getTournament().getParams().getPointsTeamDrawBonus();
                    break;
                case Parameters.C_RANKING_OPP_POINTS:
                case Parameters.C_RANKING_OPP_POINTS_OTHER_MATCHS:
                    if (t.getCoachsCount() > 0) {
                        final Coach c = t.getCoach(0);
                        int i = 0;
                        if (mRoundOnly) {
                            i = mRound;
                        }

                        while (i <= mRound) {
                            //for (int i = 0; i <= mRound; i++) {
                            if (c.getMatchCount() > i) {
                                final CoachMatch m = (CoachMatch) c.getMatch(i);
                                if (m.getCompetitor1() == null) {
                                    m.setCompetitor1(Coach.getNullCoach());
                                }
                                if (m.getCompetitor2() == null) {
                                    m.setCompetitor2(Coach.getNullCoach());
                                }
                                if (m.getCompetitor1() == c) {
                                    long tmp = getVNDByTeam(((Coach) m.getCompetitor2()).getTeamMates(), tm, rankingType != Parameters.C_RANKING_OPP_POINTS_OTHER_MATCHS);
                                    value += (tmp / 1000000) * Tournament.getTournament().getParams().getPointsTeamVictoryBonus();
                                    value += ((tmp % 1000000) / 1000) * Tournament.getTournament().getParams().getPointsTeamDrawBonus();
                                } else {
                                    long tmp = getVNDByTeam(((Coach) m.getCompetitor1()).getTeamMates(), tm, rankingType != Parameters.C_RANKING_OPP_POINTS_OTHER_MATCHS);
                                    value += (tmp / 1000000) * Tournament.getTournament().getParams().getPointsTeamVictoryBonus();
                                    value += ((tmp % 1000000) / 1000) * Tournament.getTournament().getParams().getPointsTeamDrawBonus();
                                }
                            }
                            i++;
                        }
                    }
                    break;
                default:
            }
        }
        return value;
    }

    int getValue(final Team t, TeamMatch tm, final Criteria crit, final int subtype) {
        int value = 0;
        for (int i = 0; i < tm.getMatchCount(); i++) {
            CoachMatch cm = tm.getMatch(i);

            Coach c = null;

            Coach c1 = (Coach) cm.getCompetitor1();
            Coach c2 = (Coach) cm.getCompetitor2();

            if (c1.getTeamMates() == t) {
                c = c1;
            }
            if (c2.getTeamMates() == t) {
                c = c2;
            }
            if (c != null) {
                value += getValue(c, cm, crit, subtype);
            }
        }
        return value;

        /*int value = 0;
        for (int i = 0; i < t.getCoachsCount(); i++) {
        final Coach c = t.getCoach(i);
        for (int j = 0; j < c.getMatchCount(); j++) {
        value += getValue(c, (CoachMatch) c.getMatch(j), crit, subtype);
        }
        }
        return value;*/
    }

    @Override
    public Class getColumnClass(final int c) {
        return getValueAt(0, c).getClass();
    }

    /*
    * Don't need to implement this method unless your table's
    * editable.
    */
    @Override
    public boolean isCellEditable(final int row, final int col) {
        //Note that the data/cell address is constant,
        //no matter where the cell appears onscreen.        
        return false;
    }

    @Override
    public Component getTableCellRendererComponent(final JTable table, final Object value, final boolean isSelected, final boolean hasFocus, final int row, final int column) {
        final JLabel jlb = new JLabel();
        //jlb.setEditable(false);
        jlb.setOpaque(true);
        jlb.setBackground(new Color(255, 255, 255));
        jlb.setForeground(new Color(0, 0, 0));
        boolean useColor = Tournament.getTournament().getParams().isUseColor();
        if (!useColor) {
            if (row % 2 != 0) {
                jlb.setBackground(new Color(220, 220, 220));
            }
        }

        if (value instanceof String) {
            jlb.setText((String) value);
        }
        if (value instanceof Integer) {
            jlb.setText(Integer.toString((Integer) value));
        }

        if (row == 0) {
            jlb.setFont(jlb.getFont().deriveFont(Font.BOLD));
            if (useColor) {
                jlb.setBackground(new Color(200, 50, 50));
                jlb.setForeground(new Color(255, 255, 255));
            }
        }

        if (row == 1) {
            jlb.setFont(jlb.getFont().deriveFont(Font.ITALIC));
            if (useColor) {
                jlb.setBackground(new Color(200, 100, 100));
                jlb.setForeground(new Color(0, 0, 0));
            }
        }

        if (row == 2) {
            jlb.setFont(jlb.getFont().deriveFont(Font.ITALIC));
            if (useColor) {
                jlb.setBackground(new Color(200, 150, 150));
                jlb.setForeground(new Color(0, 0, 0));
            }
        }

        if ((row == mObjects.size() - 1) && (mObjects.size() > 3)) {
            jlb.setFont(jlb.getFont().deriveFont(Font.BOLD));
            if (useColor) {
                jlb.setBackground(new Color(50, 50, 200));
                jlb.setForeground(new Color(255, 255, 255));
            }
        }

        if ((row == mObjects.size() - 2) && (mObjects.size() > 4)) {
            jlb.setFont(jlb.getFont().deriveFont(Font.ITALIC));
            if (useColor) {
                jlb.setBackground(new Color(100, 100, 200));
                jlb.setForeground(new Color(0, 0, 0));
            }
        }

        if ((row == mObjects.size() - 3) && (mObjects.size() > 5)) {
            jlb.setFont(jlb.getFont().deriveFont(Font.ITALIC));
            if (useColor) {
                jlb.setBackground(new Color(150, 150, 200));
                jlb.setForeground(new Color(0, 0, 0));
            }
        }

        jlb.setHorizontalAlignment(JTextField.CENTER);
        return jlb;
    }

    public int getRound() {
        return mRound;
    }
}
