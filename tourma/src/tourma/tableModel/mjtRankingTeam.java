/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.tableModel;

import java.awt.Component;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import tourma.data.Coach;
import tourma.data.Criteria;
import tourma.data.CoachMatch;
import tourma.data.ObjectRanking;
import tourma.data.Parameters;
import tourma.data.Pool;
import tourma.data.Round;
import tourma.data.Team;
import tourma.data.TeamMatch;
import tourma.data.Tournament;
import tourma.data.Value;
import static tourma.tableModel.mjtRanking.C_ELO_K;
import static tourma.tableModel.mjtRanking.C_STARTING_RANK;
import static tourma.tableModel.mjtRanking.getCoachNbMatchs;
import static tourma.tableModel.mjtRanking.getELOByCoach;
import static tourma.tableModel.mjtRanking.getVNDByCoach;
import tourma.utility.StringConstants;
import tourma.utils.ImageTreatment;

/**
 *
 * @author Frederic Berger
 */
public class mjtRankingTeam extends mjtRanking {

    boolean mTeamVictory;
    boolean mRoundOnly = false;

    public mjtRankingTeam(final boolean teamVictory, final int round, final int ranking_type1, final int ranking_type2, final int ranking_type3, final int ranking_type4, final int ranking_type5, final ArrayList teams, final boolean round_only) {
        super(round, ranking_type1, ranking_type2, ranking_type3, ranking_type4, ranking_type5, teams);
        mTeamVictory = teamVictory;
        mRoundOnly = round_only;
        sortDatas();
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

            for (int j = 0; j < t.mCoachs.size(); j++) {
                final Coach c = t.mCoachs.get(j);
                if (c.mMatchs.size() > i) {
                    final CoachMatch m = (CoachMatch) c.mMatchs.get(i);
                    final Criteria crit = Tournament.getTournament().getParams().mCriterias.get(0);
                    final Value val = m.mValues.get(crit);
                    if (m.mCompetitor1 == c) {
                        if (val.mValue1 > val.mValue2) {
                            victories++;
                        } else {
                            if (val.mValue1 < val.mValue2) {
                                loss++;
                            }
                        }
                        for (int k = 0; k < Tournament.getTournament().getParams().mCriterias.size(); k++) {
                            final Criteria criteria = Tournament.getTournament().getParams().mCriterias.get(k);
                            value += Math.max(m.mValues.get(criteria).mValue1, 0) * criteria.mPointsTeamFor;
                            value += Math.max(m.mValues.get(criteria).mValue2, 0) * criteria.mPointsTeamAgainst;
                        }

                    } else {
                        if (val.mValue1 < val.mValue2) {
                            victories++;
                        } else {
                            if (val.mValue1 > val.mValue2) {
                                loss++;
                            }
                        }
                        for (int k = 0; k < Tournament.getTournament().getParams().mCriterias.size(); k++) {
                            final Criteria criteria = Tournament.getTournament().getParams().mCriterias.get(k);
                            value += Math.max(m.mValues.get(criteria).mValue2, 0) * criteria.mPointsTeamFor;
                            value += Math.max(m.mValues.get(criteria).mValue1, 0) * criteria.mPointsTeamAgainst;
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

        value += countTeamVictories * Tournament.getTournament().getParams().mPointsTeamVictory;
        value += countTeamLoss * Tournament.getTournament().getParams().mPointsTeamLost;
        value += countTeamDraw * Tournament.getTournament().getParams().mPointsTeamDraw;


        return value;
    }

    int getELOByTeam(final Team t, int roundIndex) {
        int value = 0;

        TeamMatch tm = (TeamMatch) t.mMatchs.get(roundIndex);

        int nbVic = tm.getVictories(t);
        int nbLoss = tm.getVictories(t);
        int nbDraw = tm.getVictories(t);

        int lastTeamRank = C_STARTING_RANK;
        int lastOppRank = C_STARTING_RANK;

        Team opp = null;
        if (tm.mCompetitor1 == t) {
            opp = (Team) tm.mCompetitor2;

        }
        if (tm.mCompetitor2 == t) {
            opp = (Team) tm.mCompetitor1;
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

        double EA = 1 / (1 + Math.pow(10.0, (lastOppRank - lastTeamRank) / 400));
        double EB = 1 / (1 + Math.pow(10.0, (lastTeamRank - lastOppRank) / 400));

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
        for (int i = 0; i < Tournament.getTournament().getParams().mCriterias.size(); i++) {
            Criteria crit = Tournament.getTournament().getParams().mCriterias.get(i);
            for (int j = 0; j < tm.mMatchs.size(); j++) {
                CoachMatch m = tm.mMatchs.get(j);
                Value val = m.mValues.get(crit);
                if (tm.mCompetitor1 == t) {
                    SA += val.mValue1;
                    SA -= val.mValue2;
                }
                if (tm.mCompetitor2 == t) {
                    SA -= val.mValue1;
                    SA += val.mValue2;
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

            for (int j = 0; j < t.mCoachs.size(); j++) {
                final Coach c = t.mCoachs.get(j);
                if (c.mMatchs.size() > i) {
                    final CoachMatch m = (CoachMatch) c.mMatchs.get(i);
                    final Criteria crit = Tournament.getTournament().getParams().mCriterias.get(0);
                    final Value val = m.mValues.get(crit);
                    if (m.mCompetitor1 == c) {
                        if (val.mValue1 > val.mValue2) {
                            victories++;
                        } else {
                            if (val.mValue1 < val.mValue2) {
                                loss++;
                            }
                        }
                    } else {
                        if (val.mValue1 < val.mValue2) {
                            victories++;
                        } else {
                            if (val.mValue1 > val.mValue2) {
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
        final Coach c = t.mCoachs.get(0);
        //ArrayList<Team> opponents = new ArrayList<Team>();
        if (mRound <= c.mMatchs.size()) {

            int i = 0;
            if (mRoundOnly) {
                i = mRound;
            }

            while (i <= mRound) {
                //for (int i = 0; i <= mRound; i++) {
                final CoachMatch m = (CoachMatch) c.mMatchs.get(i);
                if (m.mCompetitor1 == c) {
                    value += getPointsByTeam(((Coach) m.mCompetitor2).mTeamMates);
                } else {
                    value += getPointsByTeam(((Coach) m.mCompetitor1).mTeamMates);
                }
                i++;
            }
        }
        return value;
    }

    int getOppELOByTeam(final Team t, int roundIndex) {

        int value = 0;
        final Coach c = t.mCoachs.get(0);
        //ArrayList<Team> opponents = new ArrayList<Team>();
        if (mRound <= c.mMatchs.size()) {

            int i = 0;
            if (mRoundOnly) {
                i = mRound;
            }

            while (i <= mRound) {
                //for (int i = 0; i <= mRound; i++) {
                final CoachMatch m = (CoachMatch) c.mMatchs.get(i);
                if (m.mCompetitor1 == c) {
                    value += getELOByTeam(((Coach) m.mCompetitor2).mTeamMates, roundIndex);
                } else {
                    value += getELOByTeam(((Coach) m.mCompetitor1).mTeamMates, roundIndex);
                }
                i++;
            }
        }
        return value;
    }

    int getValue(final Team t, final int rankingType) {
        int value = 0;

        // Find opposing team in using first Coach

        if (mTeamVictory) {
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
            for (int i = 0; i < t.mCoachs.size(); i++) {
                final Coach c = t.mCoachs.get(i);
                for (int j = 0; j < c.mMatchs.size(); j++) {
                    final CoachMatch m = (CoachMatch) c.mMatchs.get(j);
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
                    value += (getVNDByTeam(t) / 1000000) * Tournament.getTournament().getParams().mPointsTeamVictoryBonus;
                    value += ((getVNDByTeam(t) % 1000000) / 1000) * Tournament.getTournament().getParams().mPointsTeamDrawBonus;
                    break;
                case Parameters.C_RANKING_OPP_POINTS:
                    if (t.mCoachs.size() > 0) {
                        final Coach c = t.mCoachs.get(0);
                        int i = 0;
                        if (mRoundOnly) {
                            i = mRound;
                        }

                        while (i <= mRound) {

                            //for (int i = 0; i <= mRound; i++) {
                            if (c.mMatchs.size() > i) {
                                final CoachMatch m = (CoachMatch) c.mMatchs.get(i);
                                if (m.mCompetitor1 == c) {
                                    value += (getVNDByTeam(((Coach) m.mCompetitor2).mTeamMates) / 1000000) * Tournament.getTournament().getParams().mPointsTeamVictoryBonus;
                                    value += ((getVNDByTeam(((Coach) m.mCompetitor2).mTeamMates) % 1000000) / 1000) * Tournament.getTournament().getParams().mPointsTeamDrawBonus;
                                } else {
                                    value += (getVNDByTeam(((Coach) m.mCompetitor1).mTeamMates) / 1000000) * Tournament.getTournament().getParams().mPointsTeamVictoryBonus;
                                    value += ((getVNDByTeam(((Coach) m.mCompetitor1).mTeamMates) % 1000000) / 1000) * Tournament.getTournament().getParams().mPointsTeamDrawBonus;
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
        for (int i = 0; i < t.mCoachs.size(); i++) {
            final Coach c = t.mCoachs.get(i);
            for (int j = 0; j < c.mMatchs.size(); j++) {
                value += getValue(c, (CoachMatch) c.mMatchs.get(j), crit, subtype);
            }
        }
        return value;
    }

    int getValue(final Team t, final int rankingType, final int round) {
        int value = 0;
        final Criteria c = getCriteriaByValue(rankingType);
        final int subType = getSubtypeByValue(rankingType);
        if (c == null) {
            value += getValue(t, rankingType);
        } else {
            value += getValue(t, c, subType);
        }
        return value;
    }

    @Override
    protected void sortDatas() {

        mDatas.clear();
        mDatas = new ArrayList<>();
        for (int i = 0; i < mObjects.size(); i++) {
            final Team t = (Team) mObjects.get(i);
            final int value1 = getValue(t, mRankingType1, mRound);
            final int value2 = getValue(t, mRankingType2, mRound);
            final int value3 = getValue(t, mRankingType3, mRound);
            final int value4 = getValue(t, mRankingType4, mRound);
            final int value5 = getValue(t, mRankingType5, mRound);


            mDatas.add(new ObjectRanking(t, value1, value2, value3, value4, value5));
        }

        Collections.sort(mDatas);

        final Tournament tour = Tournament.getTournament();

        // On ajuste le tri par poule si nécessaire pour que
        // l'écart minimum entre 2 membres de la même poule
        // soit le nombre de joueurs de la poule
        if ((tour.getPools().size() > 0) && (tour.getRounds().size() > 0) && (!tour.getRounds().get(mRound).mCup)) {
            if (mObjects.size() > tour.getPools().get(0).mCompetitors.size()) {
                final int nbPool = tour.getPools().size();
                Pool p;
                final ArrayList<mjtRankingTeam> pRank = new ArrayList<>();
                for (int j = 0; j < nbPool; j++) {
                    p = tour.getPools().get(j);
                    final mjtRankingTeam mjtr = new mjtRankingTeam(mTeamVictory, mRound, mRankingType1, mRankingType2, mRankingType3, mRankingType4, mRankingType5, p.mCompetitors, mRoundOnly);
                    pRank.add(mjtr);
                }

                final ArrayList datas = new ArrayList<>();

                for (int i = 0; i < tour.getPools().get(0).mCompetitors.size(); i++) {
                    final ArrayList<Team> rank = new ArrayList<>();
                    for (int j = 0; j < nbPool; j++) {
                        final ObjectRanking obj = (ObjectRanking) pRank.get(j).mDatas.get(i);
                        rank.add((Team) obj.getObject());
                    }
                    final mjtRankingTeam mjtr = new mjtRankingTeam(mTeamVictory, mRound, mRankingType1, mRankingType2, mRankingType3, mRankingType4, mRankingType5, rank, mRoundOnly);

                    for (int j = 0; j < mjtr.mDatas.size(); j++) {
                        datas.add(mjtr.mDatas.get(j));
                    }
                }

                mDatas = datas;
            }
        }
    }

    @Override
    public int getColumnCount() {
        int result = 7;
        Parameters params = Tournament.getTournament().getParams();
        if (params.mTeamVictoryOnly) {
            if (params.mRankingTeam5 == 0) {
                result--;
                if (params.mRankingTeam4 == 0) {
                    result--;
                    if (params.mRankingTeam3 == 0) {
                        result--;
                        if (params.mRankingTeam2 == 0) {
                            result--;
                            if (params.mRankingTeam1 == 0) {
                                result--;
                            }
                        }
                    }
                }
            }
        } else {
            if (params.mRankingIndiv5 == 0) {
                result--;
                if (params.mRankingIndiv4 == 0) {
                    result--;
                    if (params.mRankingIndiv3 == 0) {
                        result--;
                        if (params.mRankingIndiv2 == 0) {
                            result--;
                            if (params.mRankingIndiv1 == 0) {
                                result--;
                            }
                        }
                    }
                }
            }
        }
        return result;
    }

    @Override
    public String getColumnName(final int col) {
        String val = "";
        switch (col) {
            case 0:
                val = "#";
                break;
            case 1:
                val = java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("Team");
                break;
            case 2:
                val = getRankingString(mRankingType1);
                break;
            case 3:
                val = getRankingString(mRankingType2);
                break;
            case 4:
                val = getRankingString(mRankingType3);
                break;
            case 5:
                val = getRankingString(mRankingType4);
                break;
            case 6:
                val = getRankingString(mRankingType5);
                break;
            default:
        }
        return val;
    }

    public static int getTeamVND(final Team t, final ArrayList<CoachMatch> v) {
        int value = 0;

        int nbVictory = 0;
        int nbLost = 0;
        for (int i = 0; i < v.size(); i++) {
            final CoachMatch m = v.get(i);
            final Value val = m.mValues.get(Tournament.getTournament().getParams().mCriterias.get(0));
            if (((Coach) m.mCompetitor1).mTeamMates == t) {
                if (val.mValue1 > val.mValue2) {
                    nbVictory++;
                } else {
                    if (val.mValue1 < val.mValue2) {
                        nbLost++;
                    }
                }
            }
            if (((Coach) m.mCompetitor2).mTeamMates == t) {
                if (val.mValue1 < val.mValue2) {
                    nbVictory++;
                } else {
                    if (val.mValue1 > val.mValue2) {
                        nbLost++;
                    }
                }
            }
        }
        if (nbVictory > nbLost) {
            value += 1000000;
        } else {
            if (nbVictory < nbLost) {
                value += 1;
            } else {
                value += 1000;
            }
        }
        return value;
    }

    public static int getTeamPoints(final Team t, final ArrayList<CoachMatch> v) {
        int value = 0;

        int nbVictory = 0;
        int nbLost = 0;
        for (int i = 0; i < v.size(); i++) {
            final CoachMatch m = v.get(i);
            final Value val = m.mValues.get(Tournament.getTournament().getParams().mCriterias.get(0));
            if (((Coach) m.mCompetitor1).mTeamMates == t) {
                if (val.mValue1 > val.mValue2) {
                    nbVictory++;
                } else {
                    if (val.mValue1 < val.mValue2) {
                        nbLost++;
                    }
                }

                for (int j = 0; j < Tournament.getTournament().getParams().mCriterias.size(); j++) {

                    final Criteria cri = Tournament.getTournament().getParams().mCriterias.get(j);
                    final Value va = m.mValues.get(cri);
                    value += va.mValue1 + cri.mPointsFor;
                    value += va.mValue2 + cri.mPointsAgainst;
                }

            }
            if (((Coach) m.mCompetitor2).mTeamMates == t) {
                if (val.mValue1 < val.mValue2) {
                    nbVictory++;
                } else {
                    if (val.mValue1 > val.mValue2) {
                        nbLost++;
                    }
                }
                for (int j = 0; j < Tournament.getTournament().getParams().mCriterias.size(); j++) {
                    final Criteria cri = Tournament.getTournament().getParams().mCriterias.get(j);
                    final Value va = m.mValues.get(cri);
                    value += va.mValue2 + cri.mPointsFor;
                    value += va.mValue1 + cri.mPointsAgainst;
                }
            }
        }

        if (nbVictory > nbLost) {
            value += Tournament.getTournament().getParams().mPointsTeamVictory;
        } else {
            if (nbVictory < nbLost) {
                value += Tournament.getTournament().getParams().mPointsTeamLost;
            } else {
                value += Tournament.getTournament().getParams().mPointsTeamDraw;
            }
        }
        return value;
    }

    /*  public static int getOppTeamPoints(Team t, ArrayList<Match> v, ArrayList<Round> vr) {
     int value = 0;
     Team opponent = null;

     if (v.size() > 0) {
     if (v.get(0).mCompetitor1.mTeamMates == t) {
     opponent = v.get(0).mCompetitor2.mTeamMates;
     } else {
     opponent = v.get(0).mCompetitor1.mTeamMates;
     }
            
     for (int i = 0; i < vr.size(); i++) {
     Round r = vr.get(i);
     ArrayList<Match> v2 = r.getMatchs();
     ArrayList<Match> v_opp = new ArrayList<Match>();
     for (int j = 0; j < v2.size(); j++) {
     CoachMatch m = v2.get(j);
     if ((m.mCompetitor1.mTeamMates == opponent) || (m.mCompetitor2.mTeamMates == opponent)) {
     v_opp.add(m);
     }
     }
     value += getTeamPoints(t, v_opp);
     }
     }
     return value;
     }*/

    /*public static int getValue(Team t, ArrayList<Match> v, int valueType, ArrayList<Round> rounds) {
     int value = 0;
     switch (valueType) {
     case Parameters.C_RANKING_POINTS:
     if (Tournament.getTournament().getParams().mTeamVictoryOnly) {
     value = getTeamPoints(t, v);
     } else {
     for (int i = 0; i < t.mCoachs.size(); i++) {
     for (int j = 0; j < v.size(); j++) {
     value += getPointsByCoach(t.mCoachs.get(i), v.get(j));
     }
     }
     if (getTeamVND(t, v) >= 1000000) {
     value += Tournament.getTournament().getParams().mPointsTeamVictoryBonus;
     }
     }
     break;
     case Parameters.C_RANKING_NONE:
     value = 0;
     break;
     case Parameters.C_RANKING_OPP_POINTS:
     if (Tournament.getTournament().getParams().mTeamVictoryOnly) {
     value = getOppTeamPoints(t, v, rounds);
     } else {
     for (int i = 0; i < t.mCoachs.size(); i++) {
     for (int j = 0; j < v.size(); j++) {
     value += getOppPointsByCoach(t.mCoachs.get(i), rounds);
     }
     }
     if (getTeamVND(t, v) < 1000000) {
     value += Tournament.getTournament().getParams().mPointsTeamVictoryBonus;
     }
     }
     break;
     case Parameters.C_RANKING_VND:
     if (Tournament.getTournament().getParams().mTeamVictoryOnly) {
     value = getTeamVND(t, v);
     } else {
     for (int i = 0; i < t.mCoachs.size(); i++) {
     for (int j = 0; j < v.size(); j++) {
     value += getVNDByCoach(t.mCoachs.get(i), v.get(j));
     }
     }
     }
     break;
     }
     return value;
     }*/
    @Override
    public Object getValueAt(final int row, final int col) {
        Object object = java.util.ResourceBundle.getBundle("tourma/languages/language").getString("");
        if (mDatas.size() > row) {
            final ObjectRanking obj = (ObjectRanking) mDatas.get(row);
            switch (col) {
                case 0:
                    object = row + 1;
                    break;
                case 1:
                    object = ((Team) obj.getObject()).mName;
                    break;
                case 2:
                    object = obj.getValue1();
                    break;
                case 3:
                    object = obj.getValue2();
                    break;
                case 4:
                    object = obj.getValue3();
                    break;
                case 5:
                    object = obj.getValue4();
                    break;
                case 6:
                    object = obj.getValue5();
                    break;
                default:
            }
        }
        return object;
    }

    @Override
    public Component getTableCellRendererComponent(final JTable table, final Object value, final boolean isSelected, final boolean hasFocus, final int row, final int column) {
        JTextField jtf = (JTextField) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        if (Tournament.getTournament().getParams().useImage) {
            if (column == 1) {
                Team t = (Team) mObjects.get(row);
                if (t.picture != null) {
                    JLabel obj = new JLabel();
                    ImageIcon icon = ImageTreatment.resize(new ImageIcon(t.picture), 30, 30);
                    obj.setIcon(icon);
                    obj.setText((String) value);
                    obj.setOpaque(true);
                    obj.setBackground(jtf.getBackground());
                    obj.setForeground(jtf.getForeground());
                    return obj;
                }
            }
        }
        return jtf;
    }
}
