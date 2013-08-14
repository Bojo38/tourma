/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.tableModel;

import tourma.data.ObjectRanking;
import tourma.data.Round;
import tourma.data.Match;
import tourma.data.Coach;
import java.util.Collections;
import java.util.ArrayList;
import tourma.data.Criteria;
import tourma.data.Pool;
import tourma.data.Tournament;
import tourma.utility.StringConstants;

/**
 *
 * @author Frederic Berger
 */
public class mjtRankingIndiv extends mjtRanking {

    boolean mTeamTournament;
    boolean mPositive;
    boolean mRoundOnly;
    boolean mForPool;

    public mjtRankingIndiv(final int round, final int ranking_type1, final int ranking_type2, final int ranking_type3, final int ranking_type4, final int ranking_type5, final ArrayList<Coach> coachs, final boolean tournament, final boolean round_only, boolean forPool) {
        super(round, ranking_type1, ranking_type2, ranking_type3, ranking_type4, ranking_type5, coachs);
        mTeamTournament = tournament;
        mRoundOnly = round_only;
        mForPool = forPool;
        sortDatas();
    }

    protected void sortDatas() {
        mDatas.clear();
        mDatas = new ArrayList<ObjectRanking>();

        final ArrayList<Round> rounds = new ArrayList<Round>();

        if (mRoundOnly) {
            rounds.add(Tournament.getTournament().getRounds().get(mRound));
        } else {
            for (int l = 0; (l <= mRound); l++) {
                rounds.add(Tournament.getTournament().getRounds().get(l));
            }
        }

        for (int i = 0; i < mObjects.size(); i++) {
            final Coach c = (Coach) mObjects.get(i);
            int value1 = 0;
            int value2 = 0;
            int value3 = 0;
            int value4 = 0;
            int value5 = 0;

            for (int j = 0; j <= c.mMatchs.size() - 1; j++) {

                final Match m = c.mMatchs.get(j);
                boolean bFound = false;
                for (int l = 0; (l < rounds.size()) && (!bFound); l++) {
                    final Round r = rounds.get(l);
                    if (r.getMatchs().contains(m)) {
                        bFound = true;
                    }
                }
                // test if match is in round
                if (bFound) {
                    final Criteria c1 = getCriteriaByValue(mRankingType1);
                    final int subType1 = getSubtypeByValue(mRankingType1);
                    if (c1 == null) {
                        value1 += getValue(c, m, mRankingType1);
                    } else {
                        value1 += getValue(c, m, c1, subType1);
                    }

                    final Criteria c2 = getCriteriaByValue(mRankingType2);
                    final int subType2 = getSubtypeByValue(mRankingType2);
                    if (c2 == null) {
                        value2 += getValue(c, m, mRankingType2);
                    } else {
                        value2 += getValue(c, m, c1, subType2);
                    }

                    final Criteria c3 = getCriteriaByValue(mRankingType3);
                    final int subType3 = getSubtypeByValue(mRankingType3);
                    if (c3 == null) {
                        value3 += getValue(c, m, mRankingType3);
                    } else {
                        value3 += getValue(c, m, c3, subType3);
                    }

                    final Criteria c4 = getCriteriaByValue(mRankingType4);
                    final int subType4 = getSubtypeByValue(mRankingType4);
                    if (c4 == null) {
                        value4 += getValue(c, m, mRankingType4);
                    } else {
                        value4 += getValue(c, m, c4, subType4);
                    }

                    final Criteria c5 = getCriteriaByValue(mRankingType5);
                    final int subType5 = getSubtypeByValue(mRankingType5);
                    if (c5 == null) {
                        value5 += getValue(c, m, mRankingType5);
                    } else {
                        value5 += getValue(c, m, c5, subType5);
                    }


                    /*value1 = getValue(c, matchs, mRankingType1, _rounds);
                     value2 = getValue(c, matchs, mRankingType2, _rounds);
                     value3 = getValue(c, matchs, mRankingType3, _rounds);
                     value4 = getValue(c, matchs, mRankingType4, _rounds);
                     value5 = getValue(c, matchs, mRankingType5, _rounds);*/
                }
            }
            mDatas.add(new ObjectRanking(c, value1, value2, value3, value4, value5));
        }

        Collections.sort(mDatas);

        final Tournament tour = Tournament.getTournament();

        // On ajuste le tri par poule si nécessaire pour que
        // l'écart minimum entre 2 membres de la même poule
        // soit le nombre de joueurs de la poule
        if (mForPool) {
            if (mObjects.size() > tour.getPools().get(0).mCoachs.size()) {
                final int nbPool = tour.getPools().size();
                Pool p = null;
                final ArrayList<mjtRankingIndiv> pRank = new ArrayList<mjtRankingIndiv>();
                for (int j = 0; j < nbPool; j++) {
                    p = tour.getPools().get(j);
                    final mjtRankingIndiv mjtr = new mjtRankingIndiv(mRound, mRankingType1, mRankingType2, mRankingType3, mRankingType4, mRankingType5, p.mCoachs, mTeamTournament, mRoundOnly,false);
                    pRank.add(mjtr);
                }


                final ArrayList datas = new ArrayList<ObjectRanking>();

                for (int i = 0; i < tour.getPools().get(0).mCoachs.size(); i++) {
                    final ArrayList<Coach> rank = new ArrayList<Coach>();
                    for (int j = 0; j < nbPool; j++) {
                        final ObjectRanking obj = (ObjectRanking) pRank.get(j).mDatas.get(i);
                        rank.add((Coach) obj.getObject());
                    }
                    final mjtRankingIndiv mjtr = new mjtRankingIndiv(mRound, mRankingType1, mRankingType2, mRankingType3, mRankingType4, mRankingType5, rank, mTeamTournament, mRoundOnly,false);

                    for (int j = 0; j < mjtr.mDatas.size(); j++) {
                        datas.add(mjtr.mDatas.get(j));
                    }
                }

                final ArrayList<Coach> rank = new ArrayList<Coach>();
                for (int i = 0; i < tour.getCoachs().size(); i++) {

                    if (!tour.getCoachs().get(i).mActive) {
                        rank.add(tour.getCoachs().get(i));
                    }
                }
                final mjtRankingIndiv mjtr = new mjtRankingIndiv(mRound, mRankingType1, mRankingType2, mRankingType3, mRankingType4, mRankingType5, rank, mTeamTournament, mRoundOnly,false);

                for (int j = 0; j < mjtr.mDatas.size(); j++) {
                    datas.add(mjtr.mDatas.get(j));
                }

                mDatas = datas;
            }
        }
    }

    /*protected int getValues(Coach c, int ranking_type, int round) {
     int value = 0;

     Criteria criteria=getCriteriaByValue(ranking_type);
     if (criteria!=null)
     {
     getValue(c, c.mMatchs.get(round), ranking_type, round);
     }
     else
     {
     getValue(c, c.mMatchs.get(round), criteria, mPositive);
     }

     return value;
     }*/
    public int getColumnCount() {
        int result = 9;
        if (mTeamTournament) {
            result = 10;
        }
        return result;
    }

    @Override
    public String getColumnName(final int col) {

        String result = "";
        int cl = col;
        if (mTeamTournament) {
            if (col > 1) {
                cl = col - 1;
            }
            if (col == 1) {
                result = java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("ClanKey");
            }
        }

        switch (cl) {
            case 0:
                result = "#";
                break;
            case 1:
                if (mTeamTournament) {
                    if (col != 1) {
                        result = java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("Team");
                    }
                } else {
                    result = java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("Team");
                }
                break;
            case 2:
                result = java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString(StringConstants.CS_COACH);
                break;
            case 3:
                result = java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("Roster");
                break;
            case 4:
                result = getRankingString(mRankingType1);
                break;
            case 5:
                result = getRankingString(mRankingType2);
                break;
            case 6:
                result = getRankingString(mRankingType3);
                break;
            case 7:
                result = getRankingString(mRankingType4);
                break;
            case 8:
                result = getRankingString(mRankingType5);
                break;
            default:
        }
        return result;
    }

    public Object getValueAt(final int row, final int col) {

        final ObjectRanking obj = (ObjectRanking) mDatas.get(row);

        Object object = "";

        int cl = col;
        if (mTeamTournament) {
            if (col > 1) {
                cl = col - 1;
            }
            if (col == 1) {
                object = ((Coach) obj.getObject()).mTeamMates.mName;
            }
        }

        switch (cl) {
            case 0:
                object = row + 1;
                break;
            case 1:
                if (mTeamTournament) {
                    if (col != 1) {
                        object = ((Coach) obj.getObject()).mTeam;
                    }
                } else {
                    object = ((Coach) obj.getObject()).mTeam;
                }
                break;
            case 2:
                object = ((Coach) obj.getObject()).mName;
                break;
            case 3:
                object = ((Coach) obj.getObject()).mRoster.mName;
                break;
            case 4:
                object = obj.getValue1();
                break;
            case 5:
                object = obj.getValue2();
                break;
            case 6:
                object = obj.getValue3();
                break;
            case 7:
                object = obj.getValue4();
                break;
            case 8:
                object = obj.getValue5();
                break;
            default:
        }



        return object;
    }
}
