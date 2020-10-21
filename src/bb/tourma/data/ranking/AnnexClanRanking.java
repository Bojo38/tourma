/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bb.tourma.data.ranking;

import java.util.ArrayList;
import java.util.Collections;
import org.jdom.Element;
import bb.tourma.data.Clan;
import bb.tourma.data.Coach;
import bb.tourma.data.CoachMatch;
import bb.tourma.data.Criterion;
import bb.tourma.data.Formula;
import bb.tourma.data.ObjectAnnexRanking;
import bb.tourma.data.Parameters;
import bb.tourma.data.Round;
import bb.tourma.data.Team;
import bb.tourma.data.TeamMatch;
import bb.tourma.data.Tournament;
import bb.tourma.utility.StringConstants;

/**
 *
 * @author WFMJ7631
 */
public class AnnexClanRanking extends AnnexRanking {

    public AnnexClanRanking(Element e) {
        super(e);
        setXMLElement(e);
    }

    public AnnexClanRanking(final int round,
            final Criterion criteria,
            final int subtype, final ArrayList<Clan> clans,
            Parameters params,
            final boolean round_only) {
        super(round, criteria, subtype, clans, params.getRankingClan1(), params.getRankingClan2(), params.getRankingClan3(), params.getRankingClan4(), params.getRankingClan5(), round_only);
        sortDatas();
    }

    public AnnexClanRanking(final int round,
            final Formula formula,
            final int subtype, final ArrayList<Clan> clans,
            Parameters params,
            final boolean round_only) {
        super(round, formula, subtype, clans, params.getRankingClan1(), params.getRankingClan2(), params.getRankingClan3(), params.getRankingClan4(), params.getRankingClan5(), round_only);
        sortDatas();
    }

    public AnnexClanRanking(final int round,
            final Criterion criteria,
            final int subtype, final ArrayList<Clan> clans,
            final int ranking_type1,
            final int ranking_type2,
            final int ranking_type3,
            final int ranking_type4,
            final int ranking_type5,
            final boolean round_only) {
        super(round, criteria, subtype, clans, ranking_type1, ranking_type2, ranking_type3, ranking_type4, ranking_type5, round_only);
        sortDatas();
    }

    public AnnexClanRanking(final int round,
            final Formula formula,
            final int subtype, final ArrayList<Clan> clans,
            final int ranking_type1,
            final int ranking_type2,
            final int ranking_type3,
            final int ranking_type4,
            final int ranking_type5,
            final boolean round_only) {
        super(round, formula, subtype, clans, ranking_type1, ranking_type2, ranking_type3, ranking_type4, ranking_type5, round_only);
        sortDatas();
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void sortDatas() {
        mDatas.clear();
        mDatas = new ArrayList<>();
        if (Tournament.getTournament().getParams().isTeamTournament()) {
            sortDatasTeam();
        } else {
            sortDatasCoach();
        }
        Collections.sort(mDatas);
    }

    /**
     * Sort teams data
     */
    @SuppressWarnings("unchecked")
    private void sortDatasTeam() {

        final ArrayList<Team> teams = new ArrayList<>();
        for (int i = 0; i < Tournament.getTournament().getTeamsCount(); i++) {
            teams.add(Tournament.getTournament().getTeam(i));
        }

        final ArrayList<Clan> clans = mObjects;

        for (Clan clan : clans) {
            final ArrayList<Integer> Vvalue = new ArrayList<>();
            final ArrayList<Integer> Vvalue1 = new ArrayList<>();
            final ArrayList<Integer> Vvalue2 = new ArrayList<>();
            final ArrayList<Integer> Vvalue3 = new ArrayList<>();
            final ArrayList<Integer> Vvalue4 = new ArrayList<>();
            final ArrayList<Integer> Vvalue5 = new ArrayList<>();
            int cvalue = 0;
            int cvalue1 = 0;
            int cvalue2 = 0;
            int cvalue3 = 0;
            int cvalue4 = 0;
            int cvalue5 = 0;
            for (Team t : teams) {
                if (t.getClan() == clan) {
                    int value = 0;
                    int value1;
                    int value2;
                    int value3;
                    int value4;
                    int value5;

                    ArrayList<Integer> aValue = new ArrayList<>();
                    ArrayList<Integer> aValue1 = new ArrayList<>();
                    ArrayList<Integer> aValue2 = new ArrayList<>();
                    ArrayList<Integer> aValue3 = new ArrayList<>();
                    ArrayList<Integer> aValue4 = new ArrayList<>();
                    ArrayList<Integer> aValue5 = new ArrayList<>();

                    for (int j = 0; j <= t.getMatchCount() - 1; j++) {

                        final TeamMatch tm = (TeamMatch) t.getMatch(j);
                        boolean bFound = false;

                        int l = 0;

                        if (mRoundOnly) {
                            l = mRound;
                        }

                        while ((l <= mRound) && (!bFound)) {

                            //for (int l = 0; (l <= mRound) && (!bFound); l++) {
                            final Round r = Tournament.getTournament().getRound(l);
                            if (r.containsMatch(tm)) {
                                if (mCriterion != null) {
                                    aValue.add(tm.getValue(mCriterion, mSubtype, t));
                                } else {
                                    aValue.add(tm.getValue(mFormula, t));
                                }
                                aValue1.add(tm.getValue(1, t));
                                aValue2.add(tm.getValue(2, t));
                                aValue3.add(tm.getValue(3, t));
                                aValue4.add(tm.getValue(4, t));
                                aValue5.add(tm.getValue(5, t));
                            }
                            l++;
                        }

                    }

                    if (Tournament.getTournament().getParams().isApplyToAnnexTeam()) {
                        removeMaxValue(aValue);
                        removeMinValue(aValue);
                    }
                    for (Integer i : aValue) {
                        value += i;
                    }

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
                    } else {
                        if (Tournament.getTournament().getParams().isExceptBestAndWorstTeam()) {
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
                    }
                    value1 = getValueFromArray(mRankingType1, aValue1);
                    value2 = getValueFromArray(mRankingType2, aValue2);
                    value3 = getValueFromArray(mRankingType3, aValue3);
                    value4 = getValueFromArray(mRankingType4, aValue4);
                    value5 = getValueFromArray(mRankingType5, aValue5);

                    Vvalue.add(value);
                    Vvalue1.add(value1);
                    Vvalue2.add(value2);
                    Vvalue3.add(value3);
                    Vvalue4.add(value4);
                    Vvalue5.add(value5);
                }
            }
            while (Vvalue.size() > Tournament.getTournament().getParams().getTeamMatesClansNumber()) {
                int currentValue = Vvalue.get(0);
                // Search minimum and remove it
                for (int j = 1; j < Vvalue.size(); j++) {
                    currentValue = Math.min(currentValue, Vvalue.get(j));
                }
                for (int j = 0; j < Vvalue.size(); j++) {
                    if (Vvalue.get(j) == currentValue) {
                        Vvalue.remove(j);
                        Vvalue1.remove(j);
                        Vvalue2.remove(j);
                        Vvalue3.remove(j);
                        Vvalue4.remove(j);
                        Vvalue5.remove(j);
                        break;
                    }
                }
            }
            for (int j = 0; j < Vvalue.size(); j++) {
                cvalue += Vvalue.get(j);
                cvalue1 += Vvalue1.get(j);
                cvalue2 += Vvalue2.get(j);
                cvalue3 += Vvalue3.get(j);
                cvalue4 += Vvalue4.get(j);
                cvalue5 += Vvalue5.get(j);
            }
            mDatas.add(new ObjectAnnexRanking(clan, cvalue, cvalue1, cvalue2, cvalue3, cvalue4, cvalue5));
        }
    }

    /**
     * Sort data Coach
     */
    @SuppressWarnings("unchecked")
    private void sortDatasCoach() {
        final ArrayList<Coach> coaches = new ArrayList<>();
        for (int i = 0; i < Tournament.getTournament().getCoachsCount(); i++) {
            coaches.add(Tournament.getTournament().getCoach(i));
        }

        final ArrayList<Clan> clans = mObjects;

        for (Clan clan : clans) {
            final ArrayList<Integer> Vvalue = new ArrayList<>();
            final ArrayList<Integer> Vvalue1 = new ArrayList<>();
            final ArrayList<Integer> Vvalue2 = new ArrayList<>();
            final ArrayList<Integer> Vvalue3 = new ArrayList<>();
            final ArrayList<Integer> Vvalue4 = new ArrayList<>();
            final ArrayList<Integer> Vvalue5 = new ArrayList<>();
            int cvalue = 0;
            int cvalue1 = 0;
            int cvalue2 = 0;
            int cvalue3 = 0;
            int cvalue4 = 0;
            int cvalue5 = 0;
            for (Coach c : coaches) {
                if (c.getClan() == clan) {
                    int value = 0;
                    int value1;
                    int value2;
                    int value3;
                    int value4;
                    int value5;

                    ArrayList<Integer> aValue = new ArrayList<>();
                    ArrayList<Integer> aValue1 = new ArrayList<>();
                    ArrayList<Integer> aValue2 = new ArrayList<>();
                    ArrayList<Integer> aValue3 = new ArrayList<>();
                    ArrayList<Integer> aValue4 = new ArrayList<>();
                    ArrayList<Integer> aValue5 = new ArrayList<>();

                    for (int j = 0; j <= c.getMatchCount() - 1; j++) {

                        final CoachMatch m = (CoachMatch) c.getMatch(j);
                        boolean bFound = false;

                        int l = 0;

                        if (mRoundOnly) {
                            l = mRound;
                        }

                        while ((l <= mRound) && (!bFound)) {

                            //for (int l = 0; (l <= mRound) && (!bFound); l++) {
                            final Round r = Tournament.getTournament().getRound(l);
                            if (r.containsMatch(m)) {
                                if (mCriterion != null) {
                                    aValue.add(m.getValue(mCriterion, mSubtype, c));
                                } else {
                                    aValue.add(m.getValue(mFormula, c));
                                }

                                aValue1.add(m.getValue(1, c));
                                aValue3.add(m.getValue(2, c));
                                aValue3.add(m.getValue(3, c));
                                aValue4.add(m.getValue(4, c));
                                aValue5.add(m.getValue(5, c));
                            }
                            l++;
                        }
                    }

                    if (Tournament.getTournament().getParams().isApplyToAnnexIndiv()) {
                        removeMaxValue(aValue);
                        removeMinValue(aValue);
                    }
                    for (Integer i : aValue) {
                        value += i;
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
                    } else {
                        if (Tournament.getTournament().getParams().isExceptBestAndWorstIndiv()) {
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
                    }

                    value1 = getValueFromArray(mRankingType1, aValue1);
                    value2 = getValueFromArray(mRankingType2, aValue2);
                    value3 = getValueFromArray(mRankingType3, aValue3);
                    value4 = getValueFromArray(mRankingType4, aValue4);
                    value5 = getValueFromArray(mRankingType5, aValue5);

                    Vvalue.add(value);
                    Vvalue1.add(value1);
                    Vvalue2.add(value2);
                    Vvalue3.add(value3);
                    Vvalue4.add(value4);
                    Vvalue5.add(value5);
                }
            }
            while (Vvalue.size() > Tournament.getTournament().getParams().getTeamMatesClansNumber()) {
                int currentValue = Vvalue.get(0);
                // Search minimum and remove it
                for (int j = 1; j < Vvalue.size(); j++) {
                    currentValue = Math.min(currentValue, Vvalue.get(j));
                }
                for (int j = 0; j < Vvalue.size(); j++) {
                    if (Vvalue.get(j) == currentValue) {
                        Vvalue.remove(j);
                        Vvalue1.remove(j);
                        Vvalue2.remove(j);
                        Vvalue3.remove(j);
                        Vvalue4.remove(j);
                        Vvalue5.remove(j);
                        break;
                    }
                }
            }
            for (int j = 0; j < Vvalue.size(); j++) {
                cvalue += Vvalue.get(j);
                cvalue1 += Vvalue1.get(j);
                cvalue2 += Vvalue2.get(j);
                cvalue3 += Vvalue3.get(j);
                cvalue4 += Vvalue4.get(j);
                cvalue5 += Vvalue5.get(j);
            }
            mDatas.add(new ObjectAnnexRanking(clan, cvalue, cvalue1, cvalue2, cvalue3, cvalue4, cvalue5));
        }
    }

    @Override
    public Element getXMLElement() {
        Element e = super.getXMLElement();
        e.setName(StringConstants.CS_ANNEX_CLAN_RANKING);

        return e;

    }

    @Override
    public void setXMLElement(Element e) {
        super.setXMLElement(e);
    }

}
