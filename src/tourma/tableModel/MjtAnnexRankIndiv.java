/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.tableModel;

import java.util.ArrayList;
import java.util.Collections;
import tourma.data.Coach;
import tourma.data.CoachMatch;
import tourma.data.Competitor;
import tourma.data.Criteria;
import tourma.data.Formula;
import tourma.data.ObjectAnnexRanking;
import tourma.data.Parameters;
import tourma.data.Round;
import tourma.data.Tournament;
import tourma.data.Value;
import tourma.languages.Translate;
import tourma.utility.StringConstants;

/**
 *
 * @author Frederic Berger
 */
@SuppressWarnings("serial")
public class MjtAnnexRankIndiv extends MjtAnnexRank {

    private final boolean mTeamTournament;

    /**
     *
     * @param round
     * @param criteria
     * @param subtype
     * @param coachs
     * @param full
     * @param ranking_type1
     * @param ranking_type2
     * @param ranking_type3
     * @param ranking_type4
     * @param ranking_type5
     * @param teamTournament
     * @param round_only
     */
    public MjtAnnexRankIndiv(final int round, final Criteria criteria, final int subtype, final ArrayList<Coach> coachs, final boolean full, final int ranking_type1, final int ranking_type2, final int ranking_type3, final int ranking_type4, final int ranking_type5, final boolean teamTournament, final boolean round_only) {
        super(round, criteria, subtype, coachs, full, ranking_type1, ranking_type2, ranking_type3, ranking_type4, ranking_type5, round_only);
        mTeamTournament = teamTournament;
        sortDatas();
    }

    public MjtAnnexRankIndiv(final int round, final Formula formula, final int subtype, final ArrayList<Coach> coachs, final boolean full, final int ranking_type1, final int ranking_type2, final int ranking_type3, final int ranking_type4, final int ranking_type5, final boolean teamTournament, final boolean round_only) {
        super(round, formula, subtype, coachs, full, ranking_type1, ranking_type2, ranking_type3, ranking_type4, ranking_type5, round_only);
        mTeamTournament = teamTournament;
        sortDatas();

    }

    @Override
    @SuppressWarnings("unchecked")
    protected void sortDatas() {
        mDatas.clear();
        mDatas = new ArrayList<>();
        @SuppressWarnings("unchecked")
        final ArrayList<Coach> coaches = mObjects;
        for (int k = 0; k < coaches.size(); k++) {
            final Coach c = coaches.get(k);
            //if (c.getMatchCount() > 0) {
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

            final ArrayList<Round> rounds = new ArrayList<>();

            if (mRoundOnly) {
                rounds.add(Tournament.getTournament().getRound(mRound));
            } else {
                for (int l = 0; (l <= mRound); l++) {
                    rounds.add(Tournament.getTournament().getRound(l));
                }
            }

            for (int j = 0; j <= c.getMatchCount() - 1; j++) {

                final CoachMatch m = (CoachMatch) c.getMatch(j);
                boolean bFound = false;
                for (int i = 0; (i < rounds.size()) && (!bFound); i++) {
                    final Round r = rounds.get(i);
                    if (r.getCoachMatchs().contains(m)) {
                        bFound = true;
                    }
                }
                // test if match is in round
                if (bFound) {
                    if (mCriteria != null) {
                        aValue.add(m.getValue(mCriteria, mSubtype, c));
                    } else {
                        aValue.add(m.getValue(mFormula, c));
                    }
                    aValue1.add(m.getValue(1, c));
                    aValue3.add(m.getValue(2, c));
                    aValue3.add(m.getValue(3, c));
                    aValue4.add(m.getValue(4, c));
                    aValue5.add(m.getValue(5, c));
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

            mDatas.add(new ObjectAnnexRanking(c, value, value1, value2, value3, value4, value5));
            //}
        }

        // If one of sorting parameteetr is head by head
        for (int i = 1; i < 5; i++) {
            if (Tournament.getTournament().getParams().getIndivRankingType(i) == Parameters.C_RANKING_HEAD_BY_HEAD) {
                // Loop on ranking to sort according to own match of the coaches who are ties.
                // The head by head ranking is nonsense a first ranking type.

                // Now check equalities
                for (int j = 0; j < mDatas.size(); j++) {
                    ObjectAnnexRanking or = (ObjectAnnexRanking) mDatas.get(j);

                    switch (i) {
                        case 1:
                            or.setValue2(0);
                            break;
                        case 2:
                            or.setValue3(0);
                            break;
                        case 3:
                            or.setValue4(0);
                            break;
                        case 4:
                            or.setValue5(0);
                            break;
                    }
                }
                for (int j = 0; j < mDatas.size(); j++) {
                    ObjectAnnexRanking or = (ObjectAnnexRanking) mDatas.get(j);

                    // find objects with same rankings
                    for (int k = j + 1; k < mDatas.size(); k++) {
                        ObjectAnnexRanking or2 = (ObjectAnnexRanking) mDatas.get(k);

                        // If previous ranking is the same;
                        if (i == 1) {
                            if (or.getValue1() == or2.getValue1()) {
                                updateHeadByHeadValue(mRound, i, or, or2);
                            }
                        }
                        if (i == 2) {
                            if ((or.getValue1() == or2.getValue1()) && (or.getValue2() == or2.getValue2())) {
                                updateHeadByHeadValue(mRound, i, or, or2);
                            }
                        }
                        if (i == 3) {
                            if ((or.getValue1() == or2.getValue1()) && (or.getValue2() == or2.getValue2()) && (or.getValue3() == or2.getValue3())) {
                                updateHeadByHeadValue(mRound, i, or, or2);
                            }
                        }
                        if (i == 4) {
                            if ((or.getValue1() == or2.getValue1()) && (or.getValue2() == or2.getValue2()) && (or.getValue3() == or2.getValue3()) && (or.getValue4() == or2.getValue4())) {
                                updateHeadByHeadValue(mRound, i, or, or2);
                            }
                        }
                    }
                }

                // Now checking that all the competitors hava played against all the other ones
                for (int j = 0; j < mDatas.size(); j++) {
                    ObjectAnnexRanking or = (ObjectAnnexRanking) mDatas.get(j);
                    if (or.getValue(i) == -1) {
                        continue;
                    }
                    ArrayList<ObjectAnnexRanking> ors = new ArrayList<>();
                    // find objects with same rankings
                    for (int k = 0; k < mDatas.size(); k++) {
                        ObjectAnnexRanking or2 = (ObjectAnnexRanking) mDatas.get(k);
                        if (or2 != or) {
                            // If previous ranking is the same;
                            if (i == 1) {
                                if (or.getValue1() == or2.getValue1()) {
                                    ors.add(or2);
                                }
                            }
                            if (i == 2) {
                                if ((or.getValue1() == or2.getValue1()) && (or.getValue2() == or2.getValue2())) {
                                    ors.add(or2);
                                }
                            }
                            if (i == 3) {
                                if ((or.getValue1() == or2.getValue1()) && (or.getValue2() == or2.getValue2()) && (or.getValue3() == or2.getValue3())) {
                                    ors.add(or2);
                                }
                            }
                            if (i == 4) {
                                if ((or.getValue1() == or2.getValue1()) && (or.getValue2() == or2.getValue2()) && (or.getValue3() == or2.getValue3()) && (or.getValue4() == or2.getValue4())) {
                                    ors.add(or2);
                                }
                            }
                        }
                    }
                    if (ors.size() > 0) {
                        // 2 points per match
                        // size+1 competitors
                        int nb_theoric = 0;
                        for (int k = ors.size(); k > 0; k--) {
                            nb_theoric += k;
                        }
                        // practical Sum 
                        int r_value = or.getValue(i);
                        for (ObjectAnnexRanking or_tmp : ors) {
                            r_value += or_tmp.getValue(i);
                        }

                        if (r_value != nb_theoric * 2) {
                            or.setValue(i, -1);
                            for (ObjectAnnexRanking or_tmp : ors) {
                                or_tmp.setValue(i, -1);
                            }
                        }
                    }
                }

            }
        }

        Collections.sort(mDatas);
    }

    protected void updateHeadByHeadValue(int round_index, int valueIndex, ObjectAnnexRanking or1, ObjectAnnexRanking or2) {
        Coach c = (Coach) or1.getObject();
        Coach c2 = (Coach) or2.getObject();
        Criteria Tds = Tournament.getTournament().getParams().getCriteria(0);

        for (int l = 0; l < c.getMatchCount(); l++) {
            CoachMatch cm = (CoachMatch) c.getMatch(l);
            Round round = cm.getRound();
            int r_index = Tournament.getTournament().getRoundIndex(round);
            if (r_index <= round_index) {
                if ((cm.getCompetitor1() == c) && (cm.getCompetitor2() == c2)) {
                    Value val = cm.getValue(Tds);
                    if (val.getValue1() > val.getValue2()) {
                        or1.setValue(valueIndex, or1.getValue(valueIndex) + 2);
                    }
                    if (val.getValue1() == val.getValue2()) {
                        or1.setValue(valueIndex, or1.getValue(valueIndex) + 1);
                        or2.setValue(valueIndex, or2.getValue(valueIndex) + 1);
                    }
                    if (val.getValue1() < val.getValue2()) {
                        or2.setValue(valueIndex, or2.getValue(valueIndex) + 2);
                    }
                }
                if ((cm.getCompetitor1() == c2) && (cm.getCompetitor2() == c)) {
                    Value val = cm.getValue(Tds);
                    if (val.getValue1() < val.getValue2()) {
                        or1.setValue(valueIndex, or1.getValue(valueIndex) + 2);
                    }
                    if (val.getValue1() == val.getValue2()) {
                        or1.setValue(valueIndex, or1.getValue(valueIndex) + 1);
                        or2.setValue(valueIndex, or2.getValue(valueIndex) + 1);
                    }
                    if (val.getValue1() > val.getValue2()) {
                        or2.setValue(valueIndex, or2.getValue(valueIndex) + 2);
                    }
                }
            }
        }
    }

    @Override
    public int getColumnCount() {

        return 5;
    }

    @Override
    public String getColumnName(final int col) {
        String result = StringConstants.CS_NULL;
        switch (col) {
            case 0:
                result = StringConstants.CS_HASH;
                break;
            case 1:
                result = Translate.translate(Translate.CS_Team);
                break;
            case 2:
                result = Translate.translate(Translate.CS_Coach);
                break;
            case 3:
                result = Translate.translate(Translate.CS_Roster);
                break;
            case 4:
                if (mCriteria != null) {
                    if (mSubtype == 0) {
                        result = mCriteria.getName() + " " + Translate.translate(Translate.CS_Coach);
                    } else if (mSubtype == 1) {
                        result = mCriteria.getName() + " " + Translate.translate(Translate.CS_Opponent);
                    } else {
                        result = mCriteria.getName() + " " + Translate.translate(Translate.CS_Difference);
                    }
                } else {
                    result = mFormula.getName();
                }
                break;
            default:
        }
        return result;
    }

    @Override
    public Object getValueAt(final int row, final int col) {

        Object val = StringConstants.CS_NULL;

        final ObjectAnnexRanking obj = (ObjectAnnexRanking) mDatas.get(row);
        switch (col) {
            case 0:
                val = row + 1;
                break;
            case 1:
                val = ((Coach) obj.getObject()).getTeam();
                break;
            case 2:
                val = ((Competitor) obj.getObject()).getDecoratedName();
                break;
            case 3:
                val = ((Coach) obj.getObject()).getStringRoster();
                break;
            case 4:
                val = obj.getValue();
                break;
            default:
        }
        return val;

    }
}
