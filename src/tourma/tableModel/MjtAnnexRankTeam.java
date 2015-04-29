/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.tableModel;

import java.awt.Component;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import tourma.data.Criteria;
import tourma.data.IWithNameAndPicture;
import tourma.data.ObjectAnnexRanking;
import tourma.data.Round;
import tourma.data.Team;
import tourma.data.TeamMatch;
import tourma.data.Tournament;
import tourma.utility.StringConstants;
import tourma.utils.ImageTreatment;

/**
 *
 * @author Frederic Berger
 */
public final class MjtAnnexRankTeam extends MjtAnnexRank {

    private static final Logger LOG = Logger.getLogger(MjtAnnexRankTeam.class.getName());

    /**
     *
     * @param round
     * @param criteria
     * @param subtype
     * @param teams
     * @param full
     * @param ranking_type1
     * @param ranking_type2
     * @param ranking_type3
     * @param ranking_type4
     * @param ranking_type5
     * @param round_only
     */
    public MjtAnnexRankTeam(final int round, final Criteria criteria, final int subtype, final ArrayList<Team> teams, final boolean full, final int ranking_type1, final int ranking_type2, final int ranking_type3, final int ranking_type4, final int ranking_type5, final boolean round_only) {
        super(round, criteria, subtype, teams, full, ranking_type1, ranking_type2, ranking_type3, ranking_type4, ranking_type5, round_only);
    }

    /**
     *
     * @param teamVictory
     * @param round
     * @param criteria
     * @param subtype
     * @param teams
     * @param full
     * @param round_only
     */
    public MjtAnnexRankTeam(final boolean teamVictory, final int round, final Criteria criteria, final int subtype, final ArrayList<Team> teams, final boolean full, final boolean round_only) {

        super(round, criteria, subtype, teams, full, Tournament.getTournament().getParams().getRankingTeam1(), Tournament.getTournament().getParams().gemRankingTeam2(), Tournament.getTournament().getParams().getRankingTeam3(), Tournament.getTournament().getParams().getRankingTeam4(), Tournament.getTournament().getParams().getRankingTeam5(), round_only);
        if (!teamVictory) {
            this.mRankingType1 = Tournament.getTournament().getParams().getRankingIndiv1();
            this.mRankingType2 = Tournament.getTournament().getParams().getRankingIndiv2();
            this.mRankingType3 = Tournament.getTournament().getParams().getRankingIndiv3();
            this.mRankingType4 = Tournament.getTournament().getParams().getRankingIndiv4();
            this.mRankingType5 = Tournament.getTournament().getParams().getRankingIndiv5();
        }
        sortDatas();

    }

    @Override
    protected void sortDatas() {
        mDatas.clear();

        mDatas = new ArrayList();
        for (int i = 0; i < mObjects.size(); i++) {
            final Team t = (Team) mObjects.get(i);
            int value = 0;
            int value1 = 0;
            int value2 = 0;
            int value3 = 0;
            int value4 = 0;
            int value5 = 0;

            value += getValue(t, mCriteria, mSubtype);

            TeamMatch tm = null;
            Round round = Tournament.getTournament().getRound(this.getRound());
            for (int j = 0; j < round.getMatchsCount(); j++) {
                TeamMatch tmp = (TeamMatch) round.getMatch(j);
                if ((tmp.getCompetitor1() == t) || (tmp.getCompetitor2() == t)) {
                    tm = tmp;
                    break;
                }
            }
            
            final Criteria c1 = getCriteriaByValue(mRankingType1);
            final int subType1 = getSubtypeByValue(mRankingType1);
            if (c1 == null) {
                value1 = getValue(t,tm, mRankingType1, 0, Tournament.getTournament().getParams().isTeamVictoryOnly());
            } else {
                value1 += getValue(t, c1, subType1);
            }

            final Criteria c2 = getCriteriaByValue(mRankingType2);
            final int subType2 = getSubtypeByValue(mRankingType2);
            if (c2 == null) {
                value2 = getValue(t,tm, mRankingType2, 0, Tournament.getTournament().getParams().isTeamVictoryOnly());
            } else {
                value2 += getValue(t, c2, subType2);
            }

            final Criteria c3 = getCriteriaByValue(mRankingType3);
            final int subType3 = getSubtypeByValue(mRankingType3);
            if (c3 == null) {
                value3 = getValue(t, tm,mRankingType3, 0, Tournament.getTournament().getParams().isTeamVictoryOnly());
            } else {
                value3 += getValue(t, c3, subType3);
            }

            final Criteria c4 = getCriteriaByValue(mRankingType4);
            final int subType4 = getSubtypeByValue(mRankingType4);
            if (c4 == null) {
                value4 = getValue(t,tm, mRankingType4, 0, Tournament.getTournament().getParams().isTeamVictoryOnly());
            } else {
                value4 += getValue(t, c4, subType4);
            }

            final Criteria c5 = getCriteriaByValue(mRankingType5);
            final int subType5 = getSubtypeByValue(mRankingType5);
            if (c5 == null) {
                value5 = getValue(t, tm,mRankingType5, 0, Tournament.getTournament().getParams().isTeamVictoryOnly());
            } else {
                value5 += getValue(t, c5, subType5);
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
        String val = java.util.ResourceBundle.getBundle("tourma/languages/language").getString("");
        switch (col) {
            case 0:
                val = java.util.ResourceBundle.getBundle("tourma/languages/language").getString("#");
                break;
            case 1:
                val = java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("ClanKey");
                break;
            case 2:
                if (mSubtype == 0) {
                    val = mCriteria.getName() + java.text.MessageFormat.format(" {0}", new Object[]{java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString(StringConstants.CS_COACH)});
                } else {
                    if (mSubtype == 1) {
                        val = mCriteria.getName() + java.text.MessageFormat.format(" {0}", new Object[]{java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("Opponent")});
                    } else {
                        val = mCriteria.getName() + java.text.MessageFormat.format(" {0}", new Object[]{java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("Difference")});
                    }
                }
                break;
            default:
                break;
        }
        return val;
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
                val = ((IWithNameAndPicture) obj.getObject()).getName();
                break;
            case 2:
                val = obj.getValue();
                break;
            default:
        }
        return val;
    }

    @Override
    public Component getTableCellRendererComponent(final JTable table, final Object value, final boolean isSelected, final boolean hasFocus, final int row, final int column) {
        JLabel obj = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        if (Tournament.getTournament().getParams().isUseImage()) {
            if (column == 1) {
                Team t = (Team) mObjects.get(row);
                if (t.getPicture() != null) {
                    ImageIcon icon = ImageTreatment.resize(new ImageIcon(t.getPicture()), 30, 30);
                    obj.setIcon(icon);
                }
            }
        }

        return obj;
    }
}
