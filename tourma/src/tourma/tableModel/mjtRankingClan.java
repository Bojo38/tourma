/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.tableModel;

import java.awt.Component;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import tourma.data.Clan;
import tourma.data.Coach;
import tourma.data.Criteria;
import tourma.data.CoachMatch;
import tourma.data.ObjectRanking;
import tourma.data.Parameters;
import tourma.data.Team;
import tourma.data.Tournament;
import static tourma.tableModel.mjtRanking.getCriteriaByValue;
import static tourma.tableModel.mjtRanking.getSubtypeByValue;
import static tourma.tableModel.mjtRanking.getValue;
import tourma.utility.StringConstants;
import tourma.utils.ImageTreatment;

/**
 *
 * @author Frederic Berger
 */
public class mjtRankingClan extends mjtRanking {

    public mjtRankingClan(final int round, final int ranking_type1, final int ranking_type2, final int ranking_type3, final int ranking_type4, final int ranking_type5, final ArrayList<Clan> clans, final boolean round_only) {
        super(round, ranking_type1, ranking_type2, ranking_type3, ranking_type4, ranking_type5, clans,round_only);

        sortDatas();
    }

    public mjtRankingClan(final int round, final ArrayList<Clan> clans, final boolean round_only) {
        super(round, Tournament.getTournament().getParams().mRankingIndiv1,
                Tournament.getTournament().getParams().mRankingIndiv2,
                Tournament.getTournament().getParams().mRankingIndiv3,
                Tournament.getTournament().getParams().mRankingIndiv4,
                Tournament.getTournament().getParams().mRankingIndiv5, clans,round_only);
        sortDatas();
    }

    @Override
    protected void sortDatas() {
        mDatas.clear();
        mDatas = new ArrayList<>();
        if (Tournament.getTournament().getParams().mTeamTournament) {
            sortDatasTeam();
        } else {
            sortDatasCoach();
        }
        Collections.sort(mDatas);
    }

    protected void sortDatasTeam() {


        final ArrayList<Team> teams = Tournament.getTournament().getTeams();
        final ArrayList<Clan> clans = (ArrayList<Clan>) mObjects;

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

                if (t.mClan == clans.get(i)) {
                    int value1 = 0;
                    int value2 = 0;
                    int value3 = 0;
                    int value4 = 0;
                    int value5 = 0;

                    int j = 0;

                    if (mRoundOnly) {
                        j = mRound;
                    }

                    while (j <= Math.min(t.mMatchs.size() - 1, mRound)) {
                        //for (int j = 0; j <= Math.min(c.mMatchs.size(),mRound); j++) {
                        //final CoachMatch m = (CoachMatch) c.mMatchs.get(j);

                        final Criteria c1 = getCriteriaByValue(mRankingType1);
                        final int subType1 = getSubtypeByValue(mRankingType1);
                        if (c1 == null) {
                            value1 = getValue(t, mRankingType1, 0,Tournament.getTournament().getParams().mTeamVictoryOnly);
                        } else {
                            value1 += getValue(t, c1, subType1);
                        }

                        final Criteria c2 = getCriteriaByValue(mRankingType2);
                        final int subType2 = getSubtypeByValue(mRankingType2);
                        if (c2 == null) {
                            value2 = getValue(t, mRankingType2, 0,Tournament.getTournament().getParams().mTeamVictoryOnly);
                        } else {
                            value2 += getValue(t, c2, subType2);
                        }

                        final Criteria c3 = getCriteriaByValue(mRankingType3);
                        final int subType3 = getSubtypeByValue(mRankingType3);
                        if (c3 == null) {
                            value3 = getValue(t, mRankingType3, 0,Tournament.getTournament().getParams().mTeamVictoryOnly);
                        } else {
                            value3 += getValue(t, c3, subType3);
                        }

                        final Criteria c4 = getCriteriaByValue(mRankingType4);
                        final int subType4 = getSubtypeByValue(mRankingType4);
                        if (c4 == null) {
                            value4 = getValue(t, mRankingType4, 0,Tournament.getTournament().getParams().mTeamVictoryOnly);
                        } else {
                            value4 += getValue(t, c4, subType4);
                        }

                        final Criteria c5 = getCriteriaByValue(mRankingType5);
                        final int subType5 = getSubtypeByValue(mRankingType5);
                        if (c5 == null) {
                            value5 = getValue(t, mRankingType5, 0,Tournament.getTournament().getParams().mTeamVictoryOnly);
                        } else {
                            value5 += getValue(t, c5, subType5);
                        }
                        j++;
                    }
                    Vvalue1.add(value1);
                    Vvalue2.add(value2);
                    Vvalue3.add(value3);
                    Vvalue4.add(value4);
                    Vvalue5.add(value5);
                }

                while (Vvalue1.size() > Tournament.getTournament().getParams().mTeamMatesClansNumber) {
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
    
    
     protected void sortDatasCoach() {


        final ArrayList<Coach> coaches = Tournament.getTournament().getCoachs();
        final ArrayList<Clan> clans = (ArrayList<Clan>) mObjects;

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

                if (c.mClan == clans.get(i)) {
                    int value1 = 0;
                    int value2 = 0;
                    int value3 = 0;
                    int value4 = 0;
                    int value5 = 0;

                    int j = 0;

                    if (mRoundOnly) {
                        j = mRound;
                    }

                    while (j <= Math.min(c.mMatchs.size() - 1, mRound)) {
                        //for (int j = 0; j <= Math.min(c.mMatchs.size(),mRound); j++) {
                        final CoachMatch m = (CoachMatch) c.mMatchs.get(j);

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
                            value5 = getValue(c, m, mRankingType5, value5);
                        } else {
                            value5 += getValue(c, m, c5, subType5);
                        }
                        j++;
                    }
                    Vvalue1.add(value1);
                    Vvalue2.add(value2);
                    Vvalue3.add(value3);
                    Vvalue4.add(value4);
                    Vvalue5.add(value5);
                }

                while (Vvalue1.size() > Tournament.getTournament().getParams().mTeamMatesClansNumber) {
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
        Parameters params = Tournament.getTournament().getParams();
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
                object = ((Clan) obj.getObject()).mName;
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
        if (Tournament.getTournament().getParams().useImage) {
            if (column == 1) {
                Clan t = (Clan) mObjects.get(row);
                if (t.picture != null) {
                    ImageIcon icon = ImageTreatment.resize(new ImageIcon(t.picture), 30, 30);
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
