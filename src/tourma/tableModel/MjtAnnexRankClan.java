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
        super(round, criteria, subtype, clans, full, ranking_type1, ranking_type2, ranking_type3, ranking_type4, ranking_type5, round_only);
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
                                bFound = true;
                            }
                            l++;
                        }
                        // test if match is in round
                        if (bFound) {
                            aValue.add(getValue(t, tm, mCriteria, mSubtype));

                            aValue1.add(getValueByRankingType(mRankingType1, t, tm));
                            aValue2.add(getValueByRankingType(mRankingType2, t, tm));
                            aValue3.add(getValueByRankingType(mRankingType3, t, tm));
                            aValue4.add(getValueByRankingType(mRankingType4, t, tm));
                            aValue5.add(getValueByRankingType(mRankingType5, t, tm));
                        }

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
    protected void sortDatasCoach() {
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
