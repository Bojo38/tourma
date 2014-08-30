/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.tableModel;

import java.util.ArrayList;
import java.util.Collections;
import tourma.data.Coach;
import tourma.data.Criteria;
import tourma.data.CoachMatch;
import tourma.data.ObjectAnnexRanking;
import tourma.data.Round;
import tourma.data.Tournament;
import tourma.utility.StringConstants;

/**
 *
 * @author Frederic Berger
 */
public class mjtAnnexRankIndiv extends mjtAnnexRank {

    boolean mTeamTournament;

    public mjtAnnexRankIndiv(final int round, final Criteria criteria, final int subtype, final ArrayList<Coach> coachs, final boolean full, final int ranking_type1, final int ranking_type2, final int ranking_type3, final int ranking_type4, final int ranking_type5, final boolean teamTournament, final boolean round_only) {
        super(round, criteria, subtype, coachs, full, ranking_type1, ranking_type2, ranking_type3, ranking_type4, ranking_type5,round_only);
        mTeamTournament = teamTournament;
    }

    @Override
    protected void sortDatas() {
        mDatas.clear();
        mDatas = new ArrayList<>();
        final ArrayList<Coach> coaches = (ArrayList<Coach>) mObjects;
        for (int k = 0; k < coaches.size(); k++) {
            final Coach c = coaches.get(k);
            if (c.mMatchs.size() > 0) {
                int value = 0;
                int value1 = 0;
                int value2 = 0;
                int value3 = 0;
                int value4 = 0;
                int value5 = 0;

                final ArrayList<Round> rounds = new ArrayList<>();

                if (mRoundOnly) {
                    rounds.add(Tournament.getTournament().getRounds().get(mRound));
                } else {
                    for (int l = 0; (l <= mRound); l++) {
                        rounds.add(Tournament.getTournament().getRounds().get(l));
                    }
                }


                for (int j = 0; j <= c.mMatchs.size() - 1; j++) {

                    final CoachMatch m = (CoachMatch) c.mMatchs.get(j);
                    boolean bFound = false;
                    for (int i = 0; (i < rounds.size()) && (!bFound); i++) {
                        final Round r = Tournament.getTournament().getRounds().get(i);
                        if (r.getCoachMatchs().contains(m)) {
                            bFound = true;
                        }
                    }
                    // test if match is in round
                    if (bFound) {
                        value += getValue(c, m, mCriteria, mSubtype);

                        final Criteria c1 = getCriteriaByValue(mRankingType1);
                        final int subType1 = getSubtypeByValue(mRankingType1);
                        if (c1 == null) {
                            value1 = getValue(c, m, mRankingType1,value1);
                        } else {
                            value1 += getValue(c, m, c1, subType1);
                        }

                        final Criteria c2 = getCriteriaByValue(mRankingType2);
                        final int subType2 = getSubtypeByValue(mRankingType2);
                        if (c2 == null) {
                            value2 = getValue(c, m, mRankingType2,value2);
                        } else {
                            value2 += getValue(c, m, c2, subType2);
                        }

                        final Criteria c3 = getCriteriaByValue(mRankingType3);
                        final int subType3 = getSubtypeByValue(mRankingType3);
                        if (c3 == null) {
                            value3 = getValue(c, m, mRankingType3,value3);
                        } else {
                            value3 += getValue(c, m, c3, subType3);
                        }

                        final Criteria c4 = getCriteriaByValue(mRankingType4);
                        final int subType4 = getSubtypeByValue(mRankingType4);
                        if (c4 == null) {
                            value4 = getValue(c, m, mRankingType4,value4);
                        } else {
                            value4 += getValue(c, m, c4, subType4);
                        }

                        final Criteria c5 = getCriteriaByValue(mRankingType5);
                        final int subType5 = getSubtypeByValue(mRankingType5);
                        if (c5 == null) {
                            value5 = getValue(c, m, mRankingType5,value5);
                        } else {
                            value5 += getValue(c, m, c5, subType5);

                        }
                    }
                }
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
                    result = mCriteria.mName + java.util.ResourceBundle.getBundle("tourma/languages/language").getString(" COACH");
                } else {
                    if (mSubtype == 1) {
                        result = mCriteria.mName + java.util.ResourceBundle.getBundle("tourma/languages/language").getString(" ADVERSAIRE");
                    } else {
                        result = mCriteria.mName + java.util.ResourceBundle.getBundle("tourma/languages/language").getString(" DIFFERENCE");
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
                val = ((Coach) obj.getObject()).mTeam;
                break;
            case 2:
                val = ((Coach) obj.getObject()).getDecoratedName();
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
