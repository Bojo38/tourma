/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data.ranking;

import java.util.ArrayList;
import java.util.Collections;
import tourma.data.Clan;
import tourma.data.Coach;
import tourma.data.CoachMatch;
import tourma.data.ObjectRanking;
import tourma.data.Parameters;
import tourma.data.Round;
import tourma.data.Team;
import tourma.data.TeamMatch;
import tourma.data.Tournament;

/**
 *
 * @author WFMJ7631
 */
public class ClanRanking extends Ranking {

    public ClanRanking(final int round,
            Parameters params,
            final ArrayList<Clan> clans, final boolean round_only) {

        super(round, params.getRankingClan1(), params.getRankingClan2(), params.getRankingClan3(), params.getRankingClan4(), params.getRankingClan5(), clans, round_only);

        sortDatas();
    }

    public ClanRanking(final int round,
            final int ranking_type1,
            final int ranking_type2,
            final int ranking_type3,
            final int ranking_type4,
            final int ranking_type5,
            final ArrayList<Clan> clans, final boolean round_only) {

        super(round, ranking_type1, ranking_type2, ranking_type3, ranking_type4, ranking_type5, clans, round_only);

        sortDatas();
    }

    /**
     *
     */
    @Override
    protected void sortDatas() {
        mDatas.clear();
        mDatas = new ArrayList();
        if (Tournament.getTournament().getParams().isTeamTournament()) {
            sortDatasTeam();
        } else {
            sortDatasCoach();
        }
        Collections.sort(mDatas);
    }

    /**
     * Sort Clan data
     */
    private void sortDatasTeam() {

        ArrayList<Team> teams = new ArrayList<>();
        for (int cpt = 0; cpt < Tournament.getTournament().getTeamsCount(); cpt++) {
            teams.add(Tournament.getTournament().getTeam(cpt));
        }

        final ArrayList<Clan> clans = mObjects;

        for (int i = 0; i < clans.size(); i++) {
            final ArrayList<Integer> Vvalue1 = new ArrayList<>();
            final ArrayList<Integer> Vvalue2 = new ArrayList<>();
            final ArrayList<Integer> Vvalue3 = new ArrayList<>();
            final ArrayList<Integer> Vvalue4 = new ArrayList<>();
            final ArrayList<Integer> Vvalue5 = new ArrayList<>();

            int cvalue1 = 0;
            int cvalue2 = 0;
            int cvalue3 = 0;
            int cvalue4 = 0;
            int cvalue5 = 0;

            for (int k = 0; k < teams.size(); k++) {
                final Team t = teams.get(k);

                if (t.getClan() == clans.get(i)) {
                    int value1;
                    int value2;
                    int value3;
                    int value4;
                    int value5;

                    int j = 0;

                    if (mRoundOnly) {
                        j = mRound;
                    }

                    /*TeamMatch tm = null;
                    Round round = Tournament.getTournament().getRound(this.getRound());
                    for (int l = 0; l < round.getMatchsCount(); l++) {
                        TeamMatch tmp = (TeamMatch) round.getMatch(l);
                        if ((tmp.getCompetitor1() == t) || (tmp.getCompetitor2() == t)) {
                            tm = tmp;
                            break;
                        }
                    }*/
                    ArrayList<Integer> aValue1 = new ArrayList<>();
                    ArrayList<Integer> aValue2 = new ArrayList<>();
                    ArrayList<Integer> aValue3 = new ArrayList<>();
                    ArrayList<Integer> aValue4 = new ArrayList<>();
                    ArrayList<Integer> aValue5 = new ArrayList<>();

                    while (j <= mRound) {

                        Round r = Tournament.getTournament().getRound(j);

                        for (int l = 0; l < t.getMatchCount(); l++) {
                            final TeamMatch tm = (TeamMatch) t.getMatch(l);

                            if (r.containsMatch(tm)) {
                                aValue1.add(tm.getValue(1, t));
                                aValue2.add(tm.getValue(2, t));
                                aValue3.add(tm.getValue(3, t));
                                aValue4.add(tm.getValue(4, t));
                                aValue5.add(tm.getValue(5, t));
                            }
                        }
                        j++;
                    }

                    /*while (j <= Math.min(t.getMatchCount() - 1, mRound)) {
                        //for (int j = 0; j <= Math.min(c.mMatchs.size(),mRound); j++) {
                        //final CoachMatch m = (CoachMatch) c.mMatchs.get(j);

                        if (tm == null) {
                            System.out.println("Error");
                        } else {
                            aValue1.add(tm.getValue(1, t));
                            aValue2.add(tm.getValue(2, t));
                            aValue3.add(tm.getValue(3, t));
                            aValue4.add(tm.getValue(4, t));
                            aValue5.add(tm.getValue(5, t));
                        }
                        j++;
                    }*/
                    if (Tournament.getTournament().getParams().isUseBestResultTeam()) {
                        while (aValue1.size() > Tournament.getTournament().getParams().getBestResultTeam()) {
                            removeMinValue(aValue1);
                        }
                        while (aValue2.size() > Tournament.getTournament().getParams().getBestResultTeam()) {
                            removeMinValue(aValue2);
                        }
                        while (aValue3.size() > Tournament.getTournament().getParams().getBestResultTeam()) {
                            removeMinValue(aValue3);
                        }
                        while (aValue4.size() > Tournament.getTournament().getParams().getBestResultTeam()) {
                            removeMinValue(aValue4);
                        }
                        while (aValue5.size() > Tournament.getTournament().getParams().getBestResultTeam()) {
                            removeMinValue(aValue5);
                        }
                    } else if (Tournament.getTournament().getParams().isExceptBestAndWorstTeam()) {
                        removeMaxValue(aValue1);
                        removeMinValue(aValue1);
                        removeMaxValue(aValue2);
                        removeMinValue(aValue2);
                        removeMaxValue(aValue3);
                        removeMinValue(aValue3);
                        removeMaxValue(aValue4);
                        removeMinValue(aValue4);
                        removeMaxValue(aValue5);
                        removeMinValue(aValue5);
                    }
                    value1 = getValueFromArray(mRankingType1, aValue1);
                    value2 = getValueFromArray(mRankingType2, aValue2);
                    value3 = getValueFromArray(mRankingType3, aValue3);
                    value4 = getValueFromArray(mRankingType4, aValue4);
                    value5 = getValueFromArray(mRankingType5, aValue5);

                    Vvalue1.add(value1);
                    Vvalue2.add(value2);
                    Vvalue3.add(value3);
                    Vvalue4.add(value4);
                    Vvalue5.add(value5);
                }

                while (Vvalue1.size() > Tournament.getTournament().getParams().getTeamMatesClansNumber()) {
                    int currentValue = Vvalue1.get(0);
                    // Search minimum and remove it
                    for (int j = 1; j < Vvalue1.size(); j++) {
                        currentValue = Math.min(currentValue, Vvalue1.get(j));
                    }
                    for (int j = 0; j < Vvalue1.size(); j++) {
                        if (Vvalue1.get(j) == currentValue) {
                            Vvalue1.remove(j);
                            Vvalue2.remove(j);
                            Vvalue3.remove(j);
                            Vvalue4.remove(j);
                            Vvalue5.remove(j);
                            break;
                        }
                    }
                }

            }
            for (int j = 0; j < Vvalue1.size(); j++) {
                cvalue1 += Vvalue1.get(j);
                cvalue2 += Vvalue2.get(j);
                cvalue3 += Vvalue3.get(j);
                cvalue4 += Vvalue4.get(j);
                cvalue5 += Vvalue5.get(j);
            }
            mDatas.add(new ObjectRanking(clans.get(i), cvalue1, cvalue2, cvalue3, cvalue4, cvalue5));
        }

    }

    /**
     * Sort Coach data
     */
    private void sortDatasCoach() {

        final ArrayList<Coach> coaches = new ArrayList<>();

        for (int i = 0; i < Tournament.getTournament().getCoachsCount(); i++) {
            coaches.add(Tournament.getTournament().getCoach(i));
        }
        final ArrayList<Clan> clans = mObjects;

        for (int i = 0; i < clans.size(); i++) {
            final ArrayList<Integer> Vvalue1 = new ArrayList<>();
            final ArrayList<Integer> Vvalue2 = new ArrayList<>();
            final ArrayList<Integer> Vvalue3 = new ArrayList<>();
            final ArrayList<Integer> Vvalue4 = new ArrayList<>();
            final ArrayList<Integer> Vvalue5 = new ArrayList<>();

            int cvalue1 = 0;
            int cvalue2 = 0;
            int cvalue3 = 0;
            int cvalue4 = 0;
            int cvalue5 = 0;

            for (int k = 0; k < coaches.size(); k++) {
                final Coach c = coaches.get(k);

                if (c.getClan() == clans.get(i)) {
                    int value1;
                    int value2;
                    int value3;
                    int value4;
                    int value5;

                    ArrayList<Integer> aValue1 = new ArrayList<>();
                    ArrayList<Integer> aValue2 = new ArrayList<>();
                    ArrayList<Integer> aValue3 = new ArrayList<>();
                    ArrayList<Integer> aValue4 = new ArrayList<>();
                    ArrayList<Integer> aValue5 = new ArrayList<>();

                    int j = 0;

                    if (mRoundOnly) {
                        j = mRound;
                    }

                    while (j <= mRound) {

                        Round r = Tournament.getTournament().getRound(j);

                        ArrayList<CoachMatch> ar_cm = r.getCoachMatchs();
                        for (int l = 0; l < c.getMatchCount(); l++) {
                            final CoachMatch m = (CoachMatch) c.getMatch(l);

                            if (ar_cm.contains(m)) {
                                aValue1.add(m.getValue(1, c));
                                aValue1.add(m.getValue(2, c));
                                aValue1.add(m.getValue(3, c));
                                aValue1.add(m.getValue(4, c));
                                aValue1.add(m.getValue(5, c));
                            }
                        }
                        j++;
                    }

                    if (Tournament.getTournament().getParams().isUseBestResultIndiv()) {
                        while (aValue1.size() > Tournament.getTournament().getParams().getBestResultIndiv()) {
                            removeMinValue(aValue1);
                        }
                        while (aValue2.size() > Tournament.getTournament().getParams().getBestResultIndiv()) {
                            removeMinValue(aValue2);
                        }
                        while (aValue3.size() > Tournament.getTournament().getParams().getBestResultIndiv()) {
                            removeMinValue(aValue3);
                        }
                        while (aValue4.size() > Tournament.getTournament().getParams().getBestResultIndiv()) {
                            removeMinValue(aValue4);
                        }
                        while (aValue5.size() > Tournament.getTournament().getParams().getBestResultIndiv()) {
                            removeMinValue(aValue5);
                        }
                    } else if (Tournament.getTournament().getParams().isExceptBestAndWorstIndiv()) {
                        removeMaxValue(aValue1);
                        removeMinValue(aValue1);
                        removeMaxValue(aValue2);
                        removeMinValue(aValue2);
                        removeMaxValue(aValue3);
                        removeMinValue(aValue3);
                        removeMaxValue(aValue4);
                        removeMinValue(aValue4);
                        removeMaxValue(aValue5);
                        removeMinValue(aValue5);
                    }

                    value1 = getValueFromArray(mRankingType1, aValue1);
                    value2 = getValueFromArray(mRankingType2, aValue2);
                    value3 = getValueFromArray(mRankingType3, aValue3);
                    value4 = getValueFromArray(mRankingType4, aValue4);
                    value5 = getValueFromArray(mRankingType5, aValue5);

                    Vvalue1.add(value1);
                    Vvalue2.add(value2);
                    Vvalue3.add(value3);
                    Vvalue4.add(value4);
                    Vvalue5.add(value5);
                }

                while (Vvalue1.size() > Tournament.getTournament().getParams().getTeamMatesClansNumber()) {
                    int currentValue = Vvalue1.get(0);
                    // Search minimum and remove it
                    for (int j = 1; j < Vvalue1.size(); j++) {
                        currentValue = Math.min(currentValue, Vvalue1.get(j));
                    }
                    for (int j = 0; j < Vvalue1.size(); j++) {
                        if (Vvalue1.get(j) == currentValue) {
                            Vvalue1.remove(j);
                            Vvalue2.remove(j);
                            Vvalue3.remove(j);
                            Vvalue4.remove(j);
                            Vvalue5.remove(j);
                            break;
                        }
                    }
                }

            }
            for (int j = 0; j < Vvalue1.size(); j++) {
                cvalue1 += Vvalue1.get(j);
                cvalue2 += Vvalue2.get(j);
                cvalue3 += Vvalue3.get(j);
                cvalue4 += Vvalue4.get(j);
                cvalue5 += Vvalue5.get(j);
            }
            mDatas.add(new ObjectRanking(clans.get(i), cvalue1, cvalue2, cvalue3, cvalue4, cvalue5));
        }

    }
}
