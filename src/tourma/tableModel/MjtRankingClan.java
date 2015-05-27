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
import tourma.data.IWithNameAndPicture;
import tourma.data.ObjectRanking;
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
public final class MjtRankingClan extends MjtRanking {

    private static final Logger LOG = Logger.getLogger(MjtRankingClan.class.getName());

    /**
     *
     * @param round
     * @param ranking_type1
     * @param ranking_type2
     * @param ranking_type3
     * @param ranking_type4
     * @param ranking_type5
     * @param clans
     * @param round_only
     */
    public MjtRankingClan(final int round,
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
     * @param round
     * @param clans
     * @param round_only
     */
    public MjtRankingClan(final int round, final ArrayList<Clan> clans, final boolean round_only) {
        super(round, Tournament.getTournament().getParams().getRankingIndiv1(), Tournament.getTournament().getParams().getRankingIndiv2(), Tournament.getTournament().getParams().getRankingIndiv3(), Tournament.getTournament().getParams().getRankingIndiv4(), Tournament.getTournament().getParams().getRankingIndiv5(), clans, round_only);
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

                    ArrayList<Integer> aValue1 = new ArrayList<>();
                    ArrayList<Integer> aValue2 = new ArrayList<>();
                    ArrayList<Integer> aValue3 = new ArrayList<>();
                    ArrayList<Integer> aValue4 = new ArrayList<>();
                    ArrayList<Integer> aValue5 = new ArrayList<>();

                    while (j <= Math.min(t.getMatchCount() - 1, mRound)) {
                        //for (int j = 0; j <= Math.min(c.mMatchs.size(),mRound); j++) {
                        //final CoachMatch m = (CoachMatch) c.mMatchs.get(j);

                        aValue1.add(getValueByRankingType(mRankingType1, t, tm));
                        aValue2.add(getValueByRankingType(mRankingType2, t, tm));
                        aValue3.add(getValueByRankingType(mRankingType3, t, tm));
                        aValue4.add(getValueByRankingType(mRankingType4, t, tm));
                        aValue5.add(getValueByRankingType(mRankingType5, t, tm));

                        j++;
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
                    int value1 = 0;
                    int value2 = 0;
                    int value3 = 0;
                    int value4 = 0;
                    int value5 = 0;

                    ArrayList<Integer> aValue1 = new ArrayList<>();
                    ArrayList<Integer> aValue2 = new ArrayList<>();
                    ArrayList<Integer> aValue3 = new ArrayList<>();
                    ArrayList<Integer> aValue4 = new ArrayList<>();
                    ArrayList<Integer> aValue5 = new ArrayList<>();

                    int j = 0;

                    if (mRoundOnly) {
                        j = mRound;
                    }

                    while (j <= Math.min(c.getMatchCount() - 1, mRound)) {
                        //for (int j = 0; j <= Math.min(c.mMatchs.size(),mRound); j++) {
                        final CoachMatch m = (CoachMatch) c.getMatch(j);

                        aValue1.add(getValueByRankingType(mRankingType1, c, m));
                        aValue2.add(getValueByRankingType(mRankingType2, c, m));
                        aValue3.add(getValueByRankingType(mRankingType3, c, m));
                        aValue4.add(getValueByRankingType(mRankingType4, c, m));
                        aValue5.add(getValueByRankingType(mRankingType5, c, m));

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

    @Override
    public int getColumnCount() {
        int result = 7;
        //Parameters params = Tournament.getTournament().getParams();
        if (mRankingType5 == 0) {
            result--;
            if (mRankingType4 == 0) {
                result--;
                if (mRankingType3 == 0) {
                    result--;
                    if (mRankingType2 == 0) {
                        result--;
                        if (mRankingType1 == 0) {
                            result--;
                        }
                    }
                }
            }
        }
        return result;

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
                result = getRankingString(mRankingType1);
                break;
            case 3:
                result = getRankingString(mRankingType2);
                break;
            case 4:
                result = getRankingString(mRankingType3);
                break;
            case 5:
                result = getRankingString(mRankingType4);
                break;
            case 6:
                result = getRankingString(mRankingType5);
                break;
            default:
        }
        return result;

    }

    @Override
    public Object getValueAt(final int row, final int col) {
        Object object = java.util.ResourceBundle.getBundle("tourma/languages/language").getString("");
        final ObjectRanking obj = (ObjectRanking) mDatas.get(row);
        switch (col) {
            case 0:
                object = row + 1;
                break;
            case 1:
                object = ((IWithNameAndPicture) obj.getObject()).getName();
                break;
            case 2:
                object = obj.getValue1();
                break;
            case 3:
                object = obj.getValue2();
                break;
            case 4:
                object = obj.getValue3();
                break;
            case 5:
                object = obj.getValue4();
                break;
            case 6:
                object = obj.getValue5();
                break;
            default:
        }
        return object;

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
                    obj.setHorizontalAlignment(JLabel.CENTER);
                    obj.setOpaque(true);
                    return obj;
                }
            }
        }
        return obj;
    }
}
