/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.tableModel;

import tourma.data.Round;
import tourma.data.Match;
import tourma.data.Coach;
import java.util.Collections;
import java.util.ArrayList;
import tourma.data.Criteria;
import tourma.data.ObjectAnnexRanking;
import tourma.data.Team;
import tourma.data.Tournament;
import tourma.utility.StringConstants;

/**
 *
 * @author Frederic Berger
 */
public class mjtAnnexRankTeam extends mjtAnnexRank {

    boolean mRoundOnly = false;

    public mjtAnnexRankTeam(final int round, final Criteria criteria, final int subtype, final ArrayList<Team> teams, final boolean full, final int ranking_type1, final int ranking_type2, final int ranking_type3, final int ranking_type4, final int ranking_type5, final boolean round_only) {
        super(round, criteria, subtype, teams, full, ranking_type1, ranking_type2, ranking_type3, ranking_type4, ranking_type5);
        mRoundOnly = round_only;
    }

    protected void sortDatas() {
        mDatas.clear();

        mDatas = new ArrayList<ObjectAnnexRanking>();
        for (int i = 0; i < mObjects.size(); i++) {
            final Team t = (Team) mObjects.get(i);
            int value = 0;
            int value1 = 0;
            int value2 = 0;
            int value3 = 0;
            int value4 = 0;
            int value5 = 0;
            for (int k = 0; k < t.mCoachs.size(); k++) {
                final Coach c = t.mCoachs.get(k);
                for (int j = 0; j <= c.mMatchs.size() - 1; j++) {

                    final Match m = c.mMatchs.get(j);
                    boolean bFound = false;

                    int l = 0;
                    if (mRoundOnly) {
                        l = mRound;
                    }

                    while ((l <= mRound) && (!bFound)) {

                        // for (int l = 0; (l <= mRound) && (!bFound); l++) {
                        final Round r = Tournament.getTournament().getRounds().get(l);
                        if (r.getMatchs().contains(m)) {
                            bFound = true;
                        }
                        l++;
                    }
                    // test if match is in round
                    if (bFound) {


                        value += getValue(c, m, mCriteria, mSubtype);

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
                            value2 += getValue(c, m, c2, subType2);
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
                    }
                }
            }
            mDatas.add(new ObjectAnnexRanking(t, value, value1, value2, value3, value4, value5));
        }

        Collections.sort(mDatas);
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public String getColumnName(final int col) {
        String val="";
        switch (col) {
            case 0:
                val= "#";
                break;
            case 1:
                val= java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("ClanKey");
                break;
            case 2:
                if (mSubtype == 0) {
                    val= mCriteria.mName + " " + java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString(StringConstants.CS_COACH);
                } else {
                    if (mSubtype == 1) {
                        val= mCriteria.mName + " " + java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("Opponent");
                    } else {
                        val= mCriteria.mName + " " + java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("Difference");
                    }
                }
            default:
        }
        return val;
    }

    @Override
    public Object getValueAt(final int row, final int col) {

        Object val = "";
        final ObjectAnnexRanking obj = (ObjectAnnexRanking) mDatas.get(row);

        switch (col) {
            case 0:
                val = row + 1;
                break;
            case 1:
                val= ((Team) obj.getObject()).mName;
                break;
            case 2:
                val= obj.mValue;
                break;
            default:
        }
        return val;
    }
}
