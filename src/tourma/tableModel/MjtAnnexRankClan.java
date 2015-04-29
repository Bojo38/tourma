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
import tourma.data.Clan;
import tourma.data.Coach;
import tourma.data.CoachMatch;
import tourma.data.Criteria;
import tourma.data.IWithNameAndPicture;
import tourma.data.ObjectAnnexRanking;
import tourma.data.Round;
import tourma.data.Team;
import tourma.data.TeamMatch;
import tourma.data.Tournament;
import static tourma.tableModel.MjtRanking.getCriteriaByValue;
import static tourma.tableModel.MjtRanking.getSubtypeByValue;
import static tourma.tableModel.MjtRanking.getValue;
import tourma.utility.StringConstants;
import tourma.utils.ImageTreatment;

/**
 *
 * @author Frederic Berger
 */
public class MjtAnnexRankClan extends MjtAnnexRank {
    private static final Logger LOG = Logger.getLogger(MjtAnnexRankClan.class.getName());

    
    
    
    /**
     *
     * @param round
     * @param criteria
     * @param subtype
     * @param clans
     * @param full
     * @param ranking_type1
     * @param ranking_type2
     * @param ranking_type3
     * @param ranking_type4
     * @param ranking_type5
     * @param round_only
     */
    public MjtAnnexRankClan(final int round,
            final Criteria criteria,
            final int subtype, final ArrayList<Clan> clans,
            final boolean full,
            final int ranking_type1,
            final int ranking_type2,
            final int ranking_type3,
            final int ranking_type4,
            final int ranking_type5,
            final boolean round_only) {
        super(round, criteria, subtype, clans, full, ranking_type1, ranking_type2, ranking_type3, ranking_type4, ranking_type5,round_only);
    }

    /**
     *
     * @param round
     * @param criteria
     * @param subtype
     * @param full
     * @param clans
     * @param round_only
     */
    public MjtAnnexRankClan(final int round, final Criteria criteria, final int subtype, boolean full, final ArrayList clans, final boolean round_only) {

        this(round, criteria, subtype, clans, full, Tournament.getTournament().getParams().getRankingIndiv1(), Tournament.getTournament().getParams().getRankingIndiv2(), Tournament.getTournament().getParams().getRankingIndiv3(), Tournament.getTournament().getParams().getRankingIndiv4(), Tournament.getTournament().getParams().getRankingIndiv5(),
                round_only);

    }
    
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
     * Sort teams data
     */
    protected void sortDatasTeam() {

        ArrayList<Team> teams=new ArrayList<>();
        for (int cpt=0; cpt<Tournament.getTournament().getTeamsCount(); cpt++)
        {
            teams.add(Tournament.getTournament().getTeam(cpt));
        }
        final ArrayList<Clan> clans = mObjects;

        for (int i = 0; i < clans.size(); i++) {
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

            for (int k = 0; k < teams.size(); k++) {
                final Team t = teams.get(k);

                if (t.getClan() == clans.get(i)) {
                    int value = 0;
                    int value1 = 0;
                    int value2 = 0;
                    int value3 = 0;
                    int value4 = 0;
                    int value5 = 0;

                    int j = 0;

                    if (mRoundOnly) {
                        j = mRound;
                    }

                    TeamMatch tm = null;
                    Round round = Tournament.getTournament().getRound(this.getRound());
                    for (int l = 0; l < round.getMatchsCount(); l++) {
                        TeamMatch tmp = (TeamMatch) round.getMatch(l);
                        if ((tmp.getCompetitor1() == t) || (tmp.getCompetitor2() == t)) {
                            tm = tmp;
                            break;
                        }
                    }
                    
                    while (j <= Math.min(t.getMatchCount() - 1, mRound)) {

                         value = getValue(t, mCriteria, mSubtype);
                        
                        final Criteria c1 = getCriteriaByValue(mRankingType1);
                        final int subType1 = getSubtypeByValue(mRankingType1);
                        if (c1 == null) {
                            value1 = getValue(t, tm,mRankingType1, 0, Tournament.getTournament().getParams().isTeamVictoryOnly());
                        } else {
                            value1 += getValue(t, c1, subType1);
                        }

                        final Criteria c2 = getCriteriaByValue(mRankingType2);
                        final int subType2 = getSubtypeByValue(mRankingType2);
                        if (c2 == null) {
                            value2 = getValue(t, tm,mRankingType2, 0, Tournament.getTournament().getParams().isTeamVictoryOnly());
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
                            value4 = getValue(t, tm,mRankingType4, 0, Tournament.getTournament().getParams().isTeamVictoryOnly());
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
                        j++;
                    }
                     Vvalue.add(value);
                    Vvalue1.add(value1);
                    Vvalue2.add(value2);
                    Vvalue3.add(value3);
                    Vvalue4.add(value4);
                    Vvalue5.add(value5);
                }

                while (Vvalue1.size() > Tournament.getTournament().getParams().getTeamMatesClansNumber()) {
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

            }
            for (int j = 0; j < Vvalue1.size(); j++) {
                cvalue += Vvalue.get(j);
                cvalue1 += Vvalue1.get(j);
                cvalue2 += Vvalue2.get(j);
                cvalue3 += Vvalue3.get(j);
                cvalue4 += Vvalue4.get(j);
                cvalue5 += Vvalue5.get(j);
            }
            mDatas.add(new ObjectAnnexRanking(clans.get(i), cvalue,cvalue1, cvalue2, cvalue3, cvalue4, cvalue5));
        }
    }
    
    /**
     * Sort data Coach
     */
    protected void sortDatasCoach() {
        final ArrayList<Coach>     coaches = new ArrayList<>();
        for (int i=0; i<Tournament.getTournament().getCoachsCount(); i++)
        {
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
                    int value1 = 0;
                    int value2 = 0;
                    int value3 = 0;
                    int value4 = 0;
                    int value5 = 0;
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
                                bFound = true;
                            }
                            l++;
                        }
                        // test if match is in round
                        if (bFound) {

                            value = getValue(c, m, mCriteria, mSubtype);

                            final Criteria c1 = getCriteriaByValue(mRankingType1);
                            final int subType1 = getSubtypeByValue(mRankingType1);
                            if (c1 == null) {
                                value1 = getValue(c, m, mRankingType1, value1);
                            } else {
                                value1 += getValue(c, m, c1, subType1);
                            }

                            final Criteria c2 = getCriteriaByValue(mRankingType2);
                            final int subType2 = getSubtypeByValue(mRankingType2);
                            if (c2 == null) {
                                value2 = getValue(c, m, mRankingType2, value2);
                            } else {
                                value2 += getValue(c, m, c2, subType2);
                            }

                            final Criteria c3 = getCriteriaByValue(mRankingType3);
                            final int subType3 = getSubtypeByValue(mRankingType3);
                            if (c3 == null) {
                                value3 = getValue(c, m, mRankingType3, value3);
                            } else {
                                value3 += getValue(c, m, c3, subType3);
                            }

                            final Criteria c4 = getCriteriaByValue(mRankingType4);
                            final int subType4 = getSubtypeByValue(mRankingType4);
                            if (c4 == null) {
                                value4 = getValue(c, m, mRankingType4, value4);
                            } else {
                                value4 += getValue(c, m, c4, subType4);
                            }

                            final Criteria c5 = getCriteriaByValue(mRankingType5);
                            final int subType5 = getSubtypeByValue(mRankingType5);
                            if (c5 == null) {
                                value5 = getValue(c, m, mRankingType5, value2);
                            } else {
                                value5 += getValue(c, m, c5, subType5);
                            }
                        }

                        Vvalue.add(value);
                        Vvalue1.add(value1);
                        Vvalue2.add(value2);
                        Vvalue3.add(value3);
                        Vvalue4.add(value4);
                        Vvalue5.add(value5);
                    }
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
    public int getColumnCount() {
        return 3;
    }

    @Override
    public String getColumnName(final int col) {
        String result = java.util.ResourceBundle.getBundle("tourma/languages/language").getString("");
        switch (col) {
            case 0:
                result = java.util.ResourceBundle.getBundle("tourma/languages/language").getString("#");
                break;
            case 1:
                result = java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("ClanKey");
                break;
            case 2:
                result = mCriteria.getName();
                break;
            default:

        }
        return result;
    }

    @Override
    public Object getValueAt(final int row, final int col) {

        final ObjectAnnexRanking obj = (ObjectAnnexRanking) mDatas.get(row);
        String result = java.util.ResourceBundle.getBundle("tourma/languages/language").getString("");
        switch (col) {
            case 0:
                result = Integer.toString(row + 1);
                break;
            case 1:
                result = ((IWithNameAndPicture) obj.getObject()).getName();
                break;
            case 2:
                result = Integer.toString(obj.getValue());
                break;
            default:
        }
        return result;
    }

    @Override
    public Component getTableCellRendererComponent(final JTable table, final Object value, final boolean isSelected, final boolean hasFocus, final int row, final int column) {
        JLabel obj = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        if (Tournament.getTournament().getParams().isUseImage()) {
            if (column == 1) {
                Clan t = (Clan) mObjects.get(row);
                if (t.getPicture() != null) {
                    ImageIcon icon = ImageTreatment.resize(new ImageIcon(t.getPicture()), 30, 30);
                    obj.setIcon(icon);
                }
            }
        }

        return obj;
    }
    
    
}
