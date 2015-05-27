/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.tableModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Logger;
import tourma.data.Coach;
import tourma.data.CoachMatch;
import tourma.data.Competitor;
import tourma.data.Criteria;
import tourma.data.ObjectAnnexRanking;
import tourma.data.Round;
import tourma.data.Tournament;
import tourma.utility.StringConstants;

/**
 *
 * @author Frederic Berger
 */
public class MjtAnnexRankIndiv extends MjtAnnexRank {

    private static final Logger LOG = Logger.getLogger(MjtAnnexRankIndiv.class.getName());

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
    }

    @Override
    protected void sortDatas() {
        mDatas.clear();
        mDatas = new ArrayList();
        final ArrayList<Coach> coaches = mObjects;
        for (int k = 0; k < coaches.size(); k++) {
            final Coach c = coaches.get(k);
            if (c.getMatchCount() > 0) {
                int value = 0;
                int value1 = 0;
                int value2 = 0;
                int value3 = 0;
                int value4 = 0;
                int value5 = 0;

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
                        aValue.add(getValue(c, m, mCriteria, mSubtype));

                        aValue1.add(getValueByRankingType(mRankingType1, c, m));
                        aValue2.add(getValueByRankingType(mRankingType2, c, m));
                        aValue3.add(getValueByRankingType(mRankingType3, c, m));
                        aValue4.add(getValueByRankingType(mRankingType4, c, m));
                        aValue5.add(getValueByRankingType(mRankingType5, c, m));
                    }
                }
                for (Integer i : aValue) {
                    value += i;
                }

                if (Tournament.getTournament().getParams().isApplyToAnnexIndiv()) {
                    removeMaxValue(aValue);
                    removeMinValue(aValue);
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

                mDatas.add(new ObjectAnnexRanking(c, value, value1, value2, value3, value4, value5));
            }
        }

        Collections.sort(mDatas);
    }

    @Override
    public int getColumnCount() {

        return 5;
    }

    @Override
    public String getColumnName(final int col) {
        String result = java.util.ResourceBundle.getBundle("tourma/languages/language").getString("");
        switch (col) {
            case 0:
                result = java.util.ResourceBundle.getBundle("tourma/languages/language").getString("#");
                break;
            case 1:
                result = java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("Team");
                break;
            case 2:
                result = java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString(StringConstants.CS_COACH);
                break;
            case 3:
                result = java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("Roster");
                break;
            case 4:
                if (mSubtype == 0) {
                    result = mCriteria.getName() + java.util.ResourceBundle.getBundle("tourma/languages/language").getString(" COACH");
                } else {
                    if (mSubtype == 1) {
                        result = mCriteria.getName() + java.util.ResourceBundle.getBundle("tourma/languages/language").getString(" ADVERSAIRE");
                    } else {
                        result = mCriteria.getName() + java.util.ResourceBundle.getBundle("tourma/languages/language").getString(" DIFFERENCE");
                    }
                }
                break;
            default:
        }
        return result;
    }

    @Override
    public Object getValueAt(final int row, final int col) {

        Object val = java.util.ResourceBundle.getBundle("tourma/languages/language").getString("");
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
