/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.tableModel;

import tourma.data.ObjectRanking;
import tourma.data.Parameters;
import tourma.data.Value;
import tourma.data.Tournament;
import tourma.data.Match;
import tourma.data.Coach;
import java.util.Collections;
import java.util.ArrayList;
import tourma.data.Criteria;
import tourma.data.Pool;
import tourma.data.Team;
import tourma.utility.StringConstants;

/**
 *
 * @author Frederic Berger
 */
public class mjtRankingTeam extends mjtRanking {

    boolean mTeamVictory;
    boolean mRoundOnly = false;

    public mjtRankingTeam(final boolean teamVictory, final int round, final int ranking_type1, final int ranking_type2, final int ranking_type3, final int ranking_type4, final int ranking_type5, final ArrayList<Team> teams, final boolean round_only) {
        super(round, ranking_type1, ranking_type2, ranking_type3, ranking_type4, ranking_type5, teams);
        mTeamVictory = teamVictory;
        mRoundOnly = round_only;
        sortDatas();
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
                    final Match m = c.mMatchs.get(i);
                    final Criteria crit = Tournament.getTournament().getParams().mCriterias.get(0);
                    final Value val = m.mValues.get(crit);
                    if (m.mCoach1 == c) {
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
                    final Match m = c.mMatchs.get(i);
                    final Criteria crit = Tournament.getTournament().getParams().mCriterias.get(0);
                    final Value val = m.mValues.get(crit);
                    if (m.mCoach1 == c) {
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
                final Match m = c.mMatchs.get(i);
                if (m.mCoach1 == c) {
                    value += getPointsByTeam(m.mCoach2.mTeamMates);
                } else {
                    value += getPointsByTeam(m.mCoach1.mTeamMates);
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
                    value += getPointsByTeam(t);
                    break;
                case Parameters.C_RANKING_NONE:
                    value = 0;
                    break;
                case Parameters.C_RANKING_OPP_POINTS:
                    value += getOppPointsByTeam(t);
                    break;
                case Parameters.C_RANKING_VND:
                    value += getVNDByTeam(t);
                    break;
                default:
            }
        } else {
            for (int i = 0; i < t.mCoachs.size(); i++) {
                final Coach c = t.mCoachs.get(i);
                for (int j = 0; j < c.mMatchs.size(); j++) {
                    final Match m = c.mMatchs.get(j);
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
                    final Coach c = t.mCoachs.get(0);

                    int i = 0;
                    if (mRoundOnly) {
                        i = mRound;
                    }

                    while (i <= mRound) {

                        //for (int i = 0; i <= mRound; i++) {
                        if (c.mMatchs.size() > i) {
                            final Match m = c.mMatchs.get(i);
                            if (m.mCoach1 == c) {
                                value += (getVNDByTeam(m.mCoach2.mTeamMates) / 1000000) * Tournament.getTournament().getParams().mPointsTeamVictoryBonus;
                                value += ((getVNDByTeam(m.mCoach2.mTeamMates) % 1000000) / 1000) * Tournament.getTournament().getParams().mPointsTeamDrawBonus;
                            } else {
                                value += (getVNDByTeam(m.mCoach1.mTeamMates) / 1000000) * Tournament.getTournament().getParams().mPointsTeamVictoryBonus;
                                value += ((getVNDByTeam(m.mCoach1.mTeamMates) % 1000000) / 1000) * Tournament.getTournament().getParams().mPointsTeamDrawBonus;
                            }
                        }
                        i++;
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
                value += getValue(c, c.mMatchs.get(j), crit, subtype);
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

    protected void sortDatas() {

        mDatas.clear();
        mDatas = new ArrayList<ObjectRanking>();
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
            if (mObjects.size() > tour.getPools().get(0).mTeams.size()) {
                final int nbPool = tour.getPools().size();
                Pool p = null;
                final ArrayList<mjtRankingTeam> pRank = new ArrayList<mjtRankingTeam>();
                for (int j = 0; j < nbPool; j++) {
                    p = tour.getPools().get(j);
                    final mjtRankingTeam mjtr = new mjtRankingTeam(mTeamVictory, mRound, mRankingType1, mRankingType2, mRankingType3, mRankingType4, mRankingType5, p.mTeams, mRoundOnly);
                    pRank.add(mjtr);
                }


                final ArrayList datas = new ArrayList<ObjectRanking>();

                for (int i = 0; i < tour.getPools().get(0).mTeams.size(); i++) {
                    final ArrayList<Team> rank = new ArrayList<Team>();
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

    public int getColumnCount() {
        return 7;
    }

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

    public static int getTeamVND(final Team t, final ArrayList<Match> v) {
        int value = 0;

        int nbVictory = 0;
        int nbLost = 0;
        for (int i = 0; i < v.size(); i++) {
            final Match m = v.get(i);
            final Value val = m.mValues.get(Tournament.getTournament().getParams().mCriterias.get(0));
            if (m.mCoach1.mTeamMates == t) {
                if (val.mValue1 > val.mValue2) {
                    nbVictory++;
                } else {
                    if (val.mValue1 < val.mValue2) {
                        nbLost++;
                    }
                }
            }
            if (m.mCoach2.mTeamMates == t) {
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

    public static int getTeamPoints(final Team t, final ArrayList<Match> v) {
        int value = 0;

        int nbVictory = 0;
        int nbLost = 0;
        for (int i = 0; i < v.size(); i++) {
            final Match m = v.get(i);
            final Value val = m.mValues.get(Tournament.getTournament().getParams().mCriterias.get(0));
            if (m.mCoach1.mTeamMates == t) {
                if (val.mValue1 > val.mValue2) {
                    nbVictory++;
                } else {
                    if (val.mValue1 < val.mValue2) {
                        nbLost++;
                    }
                }

                for (int j = 0; j < Tournament.getTournament().getParams().mCriterias.size(); j++) {

                    final Criteria cri = Tournament.getTournament().getParams().mCriterias.get(j);
                    final Value va = m.mValues.get(cri.mName);
                    value += va.mValue1 + cri.mPointsFor;
                    value += va.mValue2 + cri.mPointsAgainst;
                }

            }
            if (m.mCoach2.mTeamMates == t) {
                if (val.mValue1 < val.mValue2) {
                    nbVictory++;
                } else {
                    if (val.mValue1 > val.mValue2) {
                        nbLost++;
                    }
                }
                for (int j = 0; j < Tournament.getTournament().getParams().mCriterias.size(); j++) {
                    final Criteria cri = Tournament.getTournament().getParams().mCriterias.get(j);
                    final Value va = m.mValues.get(cri.mName);
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
     if (v.get(0).mCoach1.mTeamMates == t) {
     opponent = v.get(0).mCoach2.mTeamMates;
     } else {
     opponent = v.get(0).mCoach1.mTeamMates;
     }
            
     for (int i = 0; i < vr.size(); i++) {
     Round r = vr.get(i);
     ArrayList<Match> v2 = r.getMatchs();
     ArrayList<Match> v_opp = new ArrayList<Match>();
     for (int j = 0; j < v2.size(); j++) {
     Match m = v2.get(j);
     if ((m.mCoach1.mTeamMates == opponent) || (m.mCoach2.mTeamMates == opponent)) {
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
    public Object getValueAt(final int row, final int col) {
        Object object = "";
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
}
