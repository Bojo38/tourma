/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.tableModel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import tourma.data.Coach;
import tourma.data.CoachMatch;
import tourma.data.Competitor;
import tourma.data.Criteria;
import tourma.data.Group;
import tourma.data.ObjectRanking;
import tourma.data.Parameters;
import tourma.data.Round;
import tourma.data.Team;
import tourma.data.TeamMatch;
import tourma.data.Tournament;
import tourma.data.Value;
import tourma.utility.StringConstants;
import tourma.utils.Ranked;

/**
 *
 * @author Frederic Berger
 */
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
     * @return
     */
    public static int getOppPointsByCoach(final Coach c, final CoachMatch m) {
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
                    value += getPointsByCoach((Coach) opponent, (CoachMatch) opponent.getMatch(i));
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
                //double EB = 1 / (1 + Math.pow(10.0, (lastPlayerRank - lastOppRank) / 200));

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
     * @return
     */
    public static int getPointsByCoach(final Coach c, final CoachMatch m) {
        int value = 0;
        if (c.matchIndex(m) == 0) {
            value += c.getHandicap();
        }
        final Criteria td = Tournament.getTournament().getParams().getCriteria(0);
        final Value val = m.getValue(td);
        if (m.getCompetitor1() == c) {
            if (m.isConcedeedBy1()) {
                value += Tournament.getTournament().getParams().getPointsConcedeed();
            } else {
                if (m.isRefusedBy1()) {
                    value += Tournament.getTournament().getParams().getPointsRefused();
                } else {
                    if (val.getValue1() >= 0) {
                        if (val.getValue1() >= val.getValue2() + Tournament.getTournament().getParams().getGapLargeVictory()) {
                            value += Tournament.getTournament().getParams().getPointsIndivLargeVictory();

                        } else {
                            if (val.getValue1() > val.getValue2()) {
                                value += Tournament.getTournament().getParams().getPointsIndivVictory();
                            } else {
                                if (val.getValue1() == val.getValue2()) {
                                    value += Tournament.getTournament().getParams().getPointsIndivDraw();
                                } else {
                                    if (val.getValue1() + Tournament.getTournament().getParams().getGapLittleLost() >= val.getValue2()) {
                                        value += Tournament.getTournament().getParams().getPointsIndivLittleLost();
                                    } else {
                                        value += Tournament.getTournament().getParams().getPointsIndivLost();
                                    }
                                }
                            }
                        }

                        for (int j = 0; j < Tournament.getTournament().getParams().getCriteriaCount(); j++) {
                            final Criteria cri = Tournament.getTournament().getParams().getCriteria(j);
                            final Value va = m.getValue(cri);
                            value += va.getValue1() * cri.getPointsFor();
                            value += va.getValue2() * cri.getPointsAgainst();
                        }

                    }
                    value += getGroupModifier(c, m);
                }
            }
        }
        if (m.getCompetitor2() == c) {
            if (m.isConcedeedBy2()) {
                value += Tournament.getTournament().getParams().getPointsConcedeed();
            } else {
                if (m.isRefusedBy2()) {
                    value += Tournament.getTournament().getParams().getPointsRefused();
                } else {
                    if (val.getValue1() >= 0) {
                        if (val.getValue2() >= val.getValue1() + Tournament.getTournament().getParams().getGapLargeVictory()) {
                            value += Tournament.getTournament().getParams().getPointsIndivLargeVictory();
                        } else {
                            if (val.getValue2() > val.getValue1()) {
                                value += Tournament.getTournament().getParams().getPointsIndivVictory();
                            } else {
                                if (val.getValue2() == val.getValue1()) {
                                    value += Tournament.getTournament().getParams().getPointsIndivDraw();
                                } else {
                                    if (val.getValue2() + Tournament.getTournament().getParams().getGapLittleLost() >= val.getValue1()) {
                                        value += Tournament.getTournament().getParams().getPointsIndivLittleLost();
                                    } else {
                                        value += Tournament.getTournament().getParams().getPointsIndivLost();
                                    }
                                }
                            }
                        }
                        for (int j = 0; j < Tournament.getTournament().getParams().getCriteriaCount(); j++) {

                            final Criteria cri = Tournament.getTournament().getParams().getCriteria(j);
                            final Value va = m.getValue(cri);
                            value += va.getValue2() * cri.getPointsFor();
                            value += va.getValue1() * cri.getPointsAgainst();
                        }
                    }
                    value += getGroupModifier(c, m);
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
        int subType = 0;
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
     * @param lastValue
     * @return
     */
    public static int getValue(final Coach c, final CoachMatch m, final int valueType, int lastValue) {
        int value;

        switch (valueType) {
            case Parameters.C_RANKING_POINTS:
                value = lastValue + getPointsByCoach(c, m);
                break;
            case Parameters.C_RANKING_NONE:
                value = lastValue + 0;
                break;
            case Parameters.C_RANKING_OPP_POINTS:
                value = lastValue + getOppPointsByCoach(c, m);
                break;
            case Parameters.C_RANKING_VND:
                value = lastValue + getVNDByCoach(c, m);
                break;
            case Parameters.C_RANKING_ELO:
                value = getELOByCoach(c, m);
                break;
            case Parameters.C_RANKING_ELO_OPP:
                value = lastValue + getOppELOByCoach(c, m);
                break;
            case Parameters.C_RANKING_NB_MATCHS:
                value = getCoachNbMatchs(c, m);
                break;
            default:
                value = lastValue;
                break;
        }

        return value;
    }

    public static int getRankingFromString(String ranking, ArrayList<String> criterias) {
        ResourceBundle bundle = java.util.ResourceBundle.getBundle("tourma/languages/language");

        if (ranking.equals(bundle.getString("Points"))) {
            return Parameters.C_RANKING_POINTS;
        }

        if (ranking.equals(bundle.getString("Nothing"))) {
            return Parameters.C_RANKING_NONE;
        }
        if (ranking.equals(bundle.getString("Opp. Pts"))) {
            return Parameters.C_RANKING_OPP_POINTS;
        }
        if (ranking.equals(bundle.getString("V/D/L"))) {
            return Parameters.C_RANKING_VND;
        }
        if (ranking.equals(bundle.getString("ELO"))) {
            return Parameters.C_RANKING_ELO;
        }

        if (ranking.equals(bundle.getString("OpponentsElo"))) {
            return Parameters.C_RANKING_ELO_OPP;
        }
        if (ranking.equals(bundle.getString("MatchCount"))) {
            return Parameters.C_RANKING_NB_MATCHS;
        }       
        
        if (ranking.endsWith(bundle.getString(" COACH")))
        {
            String tmp=ranking.replace(bundle.getString(" COACH"), "");
            int i=criterias.indexOf(tmp);
            return Parameters.C_MAX_RANKING+1+(i*3);
            
        }
        if (ranking.endsWith(bundle.getString(" ADVERSAIRE")))
        {
            String tmp=ranking.replace(bundle.getString(" ADVERSAIRE"), "");
            int i=criterias.indexOf(tmp);
            return Parameters.C_MAX_RANKING+1+(i*3)+1;
        }
        if (ranking.endsWith(bundle.getString(" DIFFERENCE")))
        {
            String tmp=ranking.replace(bundle.getString(" DIFFERENCE"), "");
            int i=criterias.indexOf(tmp);
            return Parameters.C_MAX_RANKING+1+(i*3)+2;
        }
        
        return Parameters.C_RANKING_NONE;
    }
    
    /**
     *
     * @param rankingType
     * @return
     */
    public static String getRankingString(final int rankingType) {
        String result = java.util.ResourceBundle.getBundle("tourma/languages/language").getString("");
        final Criteria c = MjtRanking.getCriteriaByValue(rankingType);
        if (c == null) {
            switch (rankingType) {
                case Parameters.C_RANKING_POINTS:
                    result = java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("Points");
                    break;
                case Parameters.C_RANKING_NONE:
                    result = java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("Nothing");
                    break;
                case Parameters.C_RANKING_OPP_POINTS:
                    result = java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("Opp. Pts");
                    break;
                case Parameters.C_RANKING_VND:
                    result = java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("V/D/L");
                    break;
                case Parameters.C_RANKING_ELO:
                    result = java.util.ResourceBundle.getBundle("tourma/languages/language").getString("ELO");
                    break;
                case Parameters.C_RANKING_ELO_OPP:
                    result = java.util.ResourceBundle.getBundle("tourma/languages/language").getString("OpponentsElo");
                    break;
                case Parameters.C_RANKING_NB_MATCHS:
                    result = java.util.ResourceBundle.getBundle("tourma/languages/language").getString("MatchCount");
                    break;
                default:
            }
        } else {
            final int subRanking = MjtRanking.getSubtypeByValue(rankingType);
            switch (subRanking) {
                case Parameters.C_RANKING_SUBTYPE_POSITIVE:
                    result = c.getName() + java.util.ResourceBundle.getBundle("tourma/languages/language").getString(" COACH");
                    break;
                case Parameters.C_RANKING_SUBTYPE_NEGATIVE:
                    result = c.getName() + java.util.ResourceBundle.getBundle("tourma/languages/language").getString(" ADVERSAIRE");
                    break;
                case Parameters.C_RANKING_SUBTYPE_DIFFERENCE:
                    result = c.getName() + java.util.ResourceBundle.getBundle("tourma/languages/language").getString(" DIFFERENCE");
                    break;
                default:
            }
        }
        return result;
    }

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
        //sortDatas();
    }

    private static int getGroupModifier(Coach c, CoachMatch m) {
        int value = 0;
        if (Tournament.getTournament().getGroupsCount() > 1) {
            final Criteria td = Tournament.getTournament().getParams().getCriteria(0);
            Group g = Tournament.getTournament().getGroup(c);
            Value v = m.getValue(td);
            if ((v.getValue1() >= 0) && ((v.getValue2() >= 0))) {
                if (m.getCompetitor1() == c) {
                    Group go = Tournament.getTournament().getGroup((Coach) m.getCompetitor2());

                    if (v.getValue1() > v.getValue2()) {
                        value = g.getOpponentModificationPoints(go).getVictoryPoints();
                    } else {
                        if (v.getValue1() == v.getValue2()) {
                            value = g.getOpponentModificationPoints(go).getDrawPoints();
                        } else {
                            value = g.getOpponentModificationPoints(go).getLossPoints();
                        }
                    }

                }
                if (m.getCompetitor2() == c) {
                    Group go = Tournament.getTournament().getGroup((Coach) m.getCompetitor1());
                    if (v.getValue1() < v.getValue2()) {
                        value = g.getOpponentModificationPoints(go).getVictoryPoints();
                    } else {
                        if (v.getValue1() == v.getValue2()) {
                            value = g.getOpponentModificationPoints(go).getDrawPoints();
                        } else {
                            value = g.getOpponentModificationPoints(go).getLossPoints();
                        }
                    }
                }
            }
        }
        return value;
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
    public ObjectRanking getSortedObject(int index) {
        ObjectRanking obj = (ObjectRanking) mDatas.get(index);
        return obj;
    }

    /**
     * Abstract sortDatas
     */
    protected abstract void sortDatas();

    @Override
    abstract public int getColumnCount();

    @Override
    public abstract String getColumnName(int col);

    @Override
    public abstract Object getValueAt(int row, int col);

    @Override
    public int getRowCount() {
        return mDatas.size();
    }

    int getTeamNbMatch(final Team T) {
        int index = mRound;
        return index + 1;
    }

    int getPointsByTeam(final Team t) {

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

            for (int j = 0; j < t.getCoachCount(); j++) {
                final Coach c = t.getCoach(j);
                if (c.getMatchCount() > i) {
                    final CoachMatch m = (CoachMatch) c.getMatch(i);
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
                        for (int k = 0; k < Tournament.getTournament().getParams().getCriteriaCount(); k++) {
                            final Criteria criteria = Tournament.getTournament().getParams().getCriteria(k);
                            value += Math.max(m.getValue(criteria).getValue1(), 0) * criteria.getPointsTeamFor();
                            value += Math.max(m.getValue(criteria).getValue2(), 0) * criteria.getPointsTeamAgainst();
                        }

                    } else {
                        if (val.getValue1() < val.getValue2()) {
                            victories++;
                        } else {
                            if (val.getValue1() > val.getValue2()) {
                                loss++;
                            }
                        }
                        for (int k = 0; k < Tournament.getTournament().getParams().getCriteriaCount(); k++) {
                            final Criteria criteria = Tournament.getTournament().getParams().getCriteria(k);
                            value += Math.max(m.getValue(criteria).getValue2(), 0) * criteria.getPointsTeamFor();
                            value += Math.max(m.getValue(criteria).getValue1(), 0) * criteria.getPointsTeamAgainst();
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

        value += countTeamVictories * Tournament.getTournament().getParams().getPointsTeamVictory();
        value += countTeamLoss * Tournament.getTournament().getParams().getPointsTeamLost();
        value += countTeamDraw * Tournament.getTournament().getParams().getPointsTeamDraw();

        return value;
    }

    int getELOByTeam(final Team t, int roundIndex) {
        int value;

        TeamMatch tm = (TeamMatch) t.getMatch(roundIndex);

        int nbVic = tm.getVictories(t);
//        int nbLoss = tm.getVictories(t);
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
                lastTeamRank = getELOByTeam(t, roundIndex - 1);
            }

            // Find Previous Match for oponnent player
            if (roundIndex > 0) {
                lastOppRank = getELOByTeam(opp, roundIndex - 1);
            }
        }

        double tmp = ((lastOppRank - lastTeamRank) / (double) 400);
        double EA = 1 / (1 + Math.pow(10.0, tmp));
//        double EB = 1 / (1 + Math.pow(10.0, (lastTeamRank - lastOppRank) / 400));

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

    int getVNDByTeam(final Team t) {

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

            for (int j = 0; j < t.getCoachCount(); j++) {
                final Coach c = t.getCoach(j);
                if (c.getMatchCount() > i) {
                    final CoachMatch m = (CoachMatch) c.getMatch(i);
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

    int getOppPointsByTeam(final Team t) {

        int value = 0;
        final Coach c = t.getCoach(0);
        //ArrayList<Team> opponents = new ArrayList<Team>();
        if (mRound <= c.getMatchCount()) {

            int i = 0;
            if (mRoundOnly) {
                i = mRound;
            }

            while (i <= mRound) {
                //for (int i = 0; i <= mRound; i++) {
                final CoachMatch m = (CoachMatch) c.getMatch(i);
                if (m.getCompetitor1() == c) {
                    value += getPointsByTeam(((Coach) m.getCompetitor2()).getTeamMates());
                } else {
                    value += getPointsByTeam(((Coach) m.getCompetitor1()).getTeamMates());
                }
                i++;
            }
        }
        return value;
    }

    int getOppELOByTeam(final Team t, int roundIndex) {

        int value = 0;
        final Coach c = t.getCoach(0);
        //ArrayList<Team> opponents = new ArrayList<Team>();
        if (mRound <= c.getMatchCount()) {

            int i = 0;
            if (mRoundOnly) {
                i = mRound;
            }

            while (i <= mRound) {
                //for (int i = 0; i <= mRound; i++) {
                final CoachMatch m = (CoachMatch) c.getMatch(i);
                if (m.getCompetitor1() == c) {
                    value += getELOByTeam(((Coach) m.getCompetitor2()).getTeamMates(), roundIndex);
                } else {
                    value += getELOByTeam(((Coach) m.getCompetitor1()).getTeamMates(), roundIndex);
                }
                i++;
            }
        }
        return value;
    }

    int getValue(final Team t, final int rankingType, boolean teamVictory) {
        int value = 0;

        // Find opposing team in using first Coach
        if (teamVictory) {
            switch (rankingType) {
                case Parameters.C_RANKING_POINTS:
                    value = getPointsByTeam(t);
                    break;
                case Parameters.C_RANKING_NONE:
                    value = 0;
                    break;
                case Parameters.C_RANKING_OPP_POINTS:
                    value = getOppPointsByTeam(t);
                    break;
                case Parameters.C_RANKING_VND:
                    value = getVNDByTeam(t);
                    break;
                case Parameters.C_RANKING_ELO:
                    value = getELOByTeam(t, mRound);
                    break;
                case Parameters.C_RANKING_ELO_OPP:
                    value = getOppELOByTeam(t, mRound);
                    break;
                case Parameters.C_RANKING_NB_MATCHS:
                    value = getTeamNbMatch(t);
                    break;
                default:
            }
        } else {
            for (int i = 0; i < t.getCoachCount(); i++) {
                final Coach c = t.getCoach(i);
                for (int j = 0; j < c.getMatchCount(); j++) {
                    final CoachMatch m = (CoachMatch) c.getMatch(j);
                    switch (rankingType) {
                        case Parameters.C_RANKING_POINTS:
                            value += getPointsByCoach(c, m);
                            break;
                        case Parameters.C_RANKING_NONE:
                            value += 0;
                            break;
                        case Parameters.C_RANKING_OPP_POINTS:
                            value += getOppPointsByCoach(c, m);
                            break;
                        case Parameters.C_RANKING_VND:
                            value += getVNDByCoach(c, m);
                            break;
                        case Parameters.C_RANKING_ELO:
                            value += getELOByCoach(c, m);
                            break;
                        case Parameters.C_RANKING_ELO_OPP:
                            value += getOppELOByCoach(c, m);
                            break;
                        case Parameters.C_RANKING_NB_MATCHS:
                            value += getCoachNbMatchs(c, m);
                            break;
                        default:
                    }
                }
            }
            switch (rankingType) {
                case Parameters.C_RANKING_POINTS:
                    value += (getVNDByTeam(t) / 1000000) * Tournament.getTournament().getParams().getPointsTeamVictoryBonus();
                    value += ((getVNDByTeam(t) % 1000000) / 1000) * Tournament.getTournament().getParams().getPointsTeamDrawBonus();
                    break;
                case Parameters.C_RANKING_OPP_POINTS:
                    if (t.getCoachCount() > 0) {
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
                                    value += (getVNDByTeam(((Coach) m.getCompetitor2()).getTeamMates()) / 1000000) * Tournament.getTournament().getParams().getPointsTeamVictoryBonus();
                                    value += ((getVNDByTeam(((Coach) m.getCompetitor2()).getTeamMates()) % 1000000) / 1000) * Tournament.getTournament().getParams().getPointsTeamDrawBonus();
                                } else {
                                    value += (getVNDByTeam(((Coach) m.getCompetitor1()).getTeamMates()) / 1000000) * Tournament.getTournament().getParams().getPointsTeamVictoryBonus();
                                    value += ((getVNDByTeam(((Coach) m.getCompetitor1()).getTeamMates()) % 1000000) / 1000) * Tournament.getTournament().getParams().getPointsTeamDrawBonus();
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

    int getValue(final Team t, final Criteria crit, final int subtype) {
        int value = 0;
        for (int i = 0; i < t.getCoachCount(); i++) {
            final Coach c = t.getCoach(i);
            for (int j = 0; j < c.getMatchCount(); j++) {
                value += getValue(c, (CoachMatch) c.getMatch(j), crit, subtype);
            }
        }
        return value;
    }

    int getValue(final Team t, final int rankingType, final int v, final boolean teamVictory) {
        int value = v;
        final Criteria c = getCriteriaByValue(rankingType);
        final int subType = getSubtypeByValue(rankingType);
        if (c == null) {
            value += getValue(t, rankingType, teamVictory);
        } else {
            value += getValue(t, c, subType);
        }
        return value;
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

    public int getRound()
    {
        return mRound;
    }
}
