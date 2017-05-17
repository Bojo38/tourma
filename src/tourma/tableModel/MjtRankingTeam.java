/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.tableModel;

import java.awt.Component;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import tourma.data.Coach;
import tourma.data.CoachMatch;
import tourma.data.Criteria;
import tourma.data.Tournament;
import tourma.data.IWithNameAndPicture;
import tourma.data.ObjectRanking;
import tourma.data.Parameters;
import tourma.data.Pool;
import tourma.data.Round;
import tourma.data.Team;
import tourma.data.TeamMatch;
import tourma.data.Tournament;
import tourma.data.Value;
import tourma.languages.Translate;
import tourma.utility.StringConstants;
import tourma.utils.ImageTreatment;

/**
 *
 * @author Frederic Berger
 */
public final class MjtRankingTeam extends MjtRanking {

    private static final Logger LOG = Logger.getLogger(MjtRankingTeam.class.getName());

    /**
     *
     * @param t
     * @param v
     * @return
     */
    private static int getTeamVND(final Team t, final ArrayList<CoachMatch> v) {
        int value = 0;

        int nbVictory = 0;
        int nbLost = 0;
        Criteria td = Tournament.getTournament().getParams().getCriteria(0);
        for (int i = 0; i < v.size(); i++) {
            final CoachMatch m = v.get(i);
            final Value val = m.getValue(td);
            if (((Coach) m.getCompetitor1()).getTeamMates() == t) {
                if (val.getValue1() > val.getValue2()) {
                    nbVictory++;
                } else {
                    if (val.getValue1() < val.getValue2()) {
                        nbLost++;
                    }
                }
            }
            if (((Coach) m.getCompetitor2()).getTeamMates() == t) {
                if (val.getValue1() < val.getValue2()) {
                    nbVictory++;
                } else {
                    if (val.getValue1() > val.getValue2()) {
                        nbLost++;
                    }
                }
            }
        }
        if (nbVictory > nbLost) {
            value += 1000000;
        } else {
            if (nbVictory < nbLost) {
                value += 1;
            } else {
                value += 1000;
            }
        }
        return value;
    }

    /**
     *
     * @param t
     * @param v
     * @return
     */
    private static int getTeamPoints(final Team t, final ArrayList<CoachMatch> v) {
        int value = 0;

        int nbVictory = 0;
        int nbLost = 0;
        Criteria td = Tournament.getTournament().getParams().getCriteria(0);
        for (int i = 0; i < v.size(); i++) {
            final CoachMatch m = v.get(i);
            final Value val = m.getValue(td);
            if (((Coach) m.getCompetitor1()).getTeamMates() == t) {
                if (val.getValue1() > val.getValue2()) {
                    nbVictory++;
                } else {
                    if (val.getValue1() < val.getValue2()) {
                        nbLost++;
                    }
                }

                for (int j = 0; j < Tournament.getTournament().getParams().getCriteriaCount(); j++) {

                    final Criteria cri = Tournament.getTournament().getParams().getCriteria(j);
                    final Value va = m.getValue(cri);
                    value += va.getValue1() + cri.getPointsFor();
                    value += va.getValue2() + cri.getPointsAgainst();
                }

            }
            if (((Coach) m.getCompetitor2()).getTeamMates() == t) {
                if (val.getValue1() < val.getValue2()) {
                    nbVictory++;
                } else {
                    if (val.getValue1() > val.getValue2()) {
                        nbLost++;
                    }
                }
                for (int j = 0; j < Tournament.getTournament().getParams().getCriteriaCount(); j++) {
                    final Criteria cri = Tournament.getTournament().getParams().getCriteria(j);
                    final Value va = m.getValue(cri);
                    value += va.getValue2() + cri.getPointsFor();
                    value += va.getValue1() + cri.getPointsAgainst();
                }
            }
        }

        if (nbVictory > nbLost) {
            value += Tournament.getTournament().getParams().getPointsTeamVictory();
        } else {
            if (nbVictory < nbLost) {
                value += Tournament.getTournament().getParams().getPointsTeamLost();
            } else {
                value += Tournament.getTournament().getParams().getPointsTeamDraw();
            }
        }
        return value;
    }

    private final boolean mTeamVictory;

    private MjtRankingTeam(final boolean teamVictory, final int round, final int ranking_type1, final int ranking_type2, final int ranking_type3, final int ranking_type4, final int ranking_type5, final ArrayList teams, final boolean round_only) {
        super(round, ranking_type1, ranking_type2, ranking_type3, ranking_type4, ranking_type5, teams, round_only);
        mTeamVictory = teamVictory;
        sortDatas();
    }

    /**
     *
     * @param teamVictory
     * @param round
     * @param teams
     * @param round_only
     */
    public MjtRankingTeam(final boolean teamVictory, final int round, final ArrayList teams, final boolean round_only) {

        super(round, Tournament.getTournament().getParams().getRankingTeam1(), Tournament.getTournament().getParams().gemRankingTeam2(), Tournament.getTournament().getParams().getRankingTeam3(), Tournament.getTournament().getParams().getRankingTeam4(), Tournament.getTournament().getParams().getRankingTeam5(),
                teams, round_only);
        if (!teamVictory) {
            this.mRankingType1 = Tournament.getTournament().getParams().getRankingIndiv1();
            this.mRankingType2 = Tournament.getTournament().getParams().getRankingIndiv2();
            this.mRankingType3 = Tournament.getTournament().getParams().getRankingIndiv3();
            this.mRankingType4 = Tournament.getTournament().getParams().getRankingIndiv4();
            this.mRankingType5 = Tournament.getTournament().getParams().getRankingIndiv5();
        }
        mTeamVictory = teamVictory;
        sortDatas();

    }

    @Override
    protected void sortDatas() {

        mDatas.clear();
        mDatas = new ArrayList();

        final ArrayList<Round> rounds = new ArrayList<>();

        if (mRoundOnly) {
            rounds.add(Tournament.getTournament().getRound(mRound));
        } else {
            for (int l = 0; (l <= mRound); l++) {
                rounds.add(Tournament.getTournament().getRound(l));
            }
        }

        for (int i = 0; i < mObjects.size(); i++) {
            final Team t = (Team) mObjects.get(i);

            int value1;
            int value2;
            int value3;
            int value4;
            int value5;

            ArrayList<Integer> aValue1 = new ArrayList<>();
            ArrayList<Integer> aValue2 = new ArrayList<>();
            ArrayList<Integer> aValue3 = new ArrayList<>();
            ArrayList<Integer> aValue4 = new ArrayList<>();
            ArrayList<Integer> aValue5 = new ArrayList<>();

            if (t.getMatchCount() > 0) {

                for (int j = 0; j <= t.getMatchCount() - 1; j++) {

                    final TeamMatch tm = (TeamMatch) t.getMatch(j);
                    boolean bFound = false;
                    for (int l = 0; (l < rounds.size()) && (!bFound); l++) {
                        final Round r = rounds.get(l);
                        if (r.containsMatch(tm)) {
                            bFound = true;
                        }
                    }
                    // test if match is in round

                    if (bFound) {
                        aValue1.add(tm.getValue(1, t));
                        aValue2.add(tm.getValue(2, t));
                        aValue3.add(tm.getValue(3, t));
                        aValue4.add(tm.getValue(4, t));
                        aValue5.add(tm.getValue(5, t));
                    }

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
                        removeMinValue(aValue1);
                        removeMinValue(aValue2);
                        removeMinValue(aValue3);
                        removeMinValue(aValue4);
                        removeMinValue(aValue5);
                        removeMaxValue(aValue1);
                        removeMaxValue(aValue2);
                        removeMaxValue(aValue3);
                        removeMaxValue(aValue4);
                        removeMaxValue(aValue5);
                    }
                }

                value1 = getValueFromArray(mRankingType1, aValue1);
                value2 = getValueFromArray(mRankingType2, aValue2);
                value3 = getValueFromArray(mRankingType3, aValue3);
                value4 = getValueFromArray(mRankingType4, aValue4);
                value5 = getValueFromArray(mRankingType5, aValue5);

                mDatas.add(new ObjectRanking(t, value1, value2, value3, value4, value5));
            }
        }

        Collections.sort(mDatas);

        final Tournament tour = Tournament.getTournament();

        // On ajuste le tri par poule si nécessaire pour que
        // l'écart minimum entre 2 membres de la même poule
        // soit le nombre de joueurs de la poule
        if ((tour.getPoolCount() > 0) && (tour.getRoundsCount() > 0) && (!tour.getRound(mRound).isCup())) {
            if (mObjects.size() > tour.getPool(0).getCompetitorCount()) {
                final int nbPool = tour.getPoolCount();
                Pool p;
                final ArrayList<MjtRankingTeam> pRank = new ArrayList<>();
                for (int j = 0; j < nbPool; j++) {
                    p = tour.getPool(j);
                    final MjtRankingTeam mjtr = new MjtRankingTeam(mTeamVictory, mRound, mRankingType1, mRankingType2, mRankingType3, mRankingType4, mRankingType5, p.getCompetitors(), mRoundOnly);
                    pRank.add(mjtr);
                }

                final ArrayList datas = new ArrayList();

                for (int i = 0; i < tour.getPool(0).getCompetitorCount(); i++) {
                    final ArrayList<Team> rank = new ArrayList<>();
                    for (int j = 0; j < nbPool; j++) {
                        final ObjectRanking obj = (ObjectRanking) pRank.get(j).mDatas.get(i);
                        rank.add((Team) obj.getObject());
                    }
                    final MjtRankingTeam mjtr = new MjtRankingTeam(mTeamVictory, mRound, mRankingType1, mRankingType2, mRankingType3, mRankingType4, mRankingType5, rank, mRoundOnly);

                    for (int j = 0; j < mjtr.mDatas.size(); j++) {
                        datas.add(mjtr.mDatas.get(j));
                    }
                }

                mDatas = datas;
            }
        }
    }

    @Override
    public int getColumnCount() {
        int result = 7;
        Parameters params = Tournament.getTournament().getParams();
        if (params.isTeamVictoryOnly()) {
            if (params.getRankingTeam5() == 0) {
                result--;
                if (params.getRankingTeam4() == 0) {
                    result--;
                    if (params.getRankingTeam3() == 0) {
                        result--;
                        if (params.gemRankingTeam2() == 0) {
                            result--;
                            if (params.getRankingTeam1() == 0) {
                                result--;
                            }
                        }
                    }
                }
            }
        } else {
            if (params.getRankingIndiv5() == 0) {
                result--;
                if (params.getRankingIndiv4() == 0) {
                    result--;
                    if (params.getRankingIndiv3() == 0) {
                        result--;
                        if (params.getRankingIndiv2() == 0) {
                            result--;
                            if (params.getRankingIndiv1() == 0) {
                                result--;
                            }
                        }
                    }
                }
            }
        }
        return result;
    }

    @Override
    public String getColumnName(final int col
    ) {
        String val = StringConstants.CS_NULL;
        switch (col) {
            case 0:
                val = StringConstants.CS_HASH;
                break;
            case 1:
                val = Translate.translate(Translate.CS_Team);
                break;
            case 2:
                val = getRankingString(mRankingType1);
                break;
            case 3:
                val = getRankingString(mRankingType2);
                break;
            case 4:
                val = getRankingString(mRankingType3);
                break;
            case 5:
                val = getRankingString(mRankingType4);
                break;
            case 6:
                val = getRankingString(mRankingType5);
                break;
            default:
        }
        return val;
    }

    @Override
    public Object getValueAt(final int row,
            final int col
    ) {
        Parameters params = Tournament.getTournament().getParams();
        Object object = StringConstants.CS_NULL;
        try {
            if (mDatas.size() > row) {
                final ObjectRanking obj = (ObjectRanking) mDatas.get(row);
                switch (col) {
                    case 0:
                        object = row + 1;
                        break;
                    case 1:
                        object = ((IWithNameAndPicture) obj.getObject()).getName();
                        break;
                    case 2:
                        if (params.getTeamRankingType(0) == Parameters.C_RANKING_VND) {
                            object = convertVND(obj.getValue1());
                        } else {
                            object = obj.getValue1();
                        }
                        break;
                    case 3:
                        if (params.getTeamRankingType(1) == Parameters.C_RANKING_VND) {
                            object = convertVND(obj.getValue2());
                        } else {
                            object = obj.getValue2();
                        }
                        break;
                    case 4:
                        if (params.getTeamRankingType(2) == Parameters.C_RANKING_VND) {
                            object = convertVND(obj.getValue3());
                        } else {
                            object = obj.getValue3();
                        }
                        break;
                    case 5:
                        if (params.getTeamRankingType(3) == Parameters.C_RANKING_VND) {
                            object = convertVND(obj.getValue4());
                        } else {
                            object = obj.getValue4();
                        }
                        break;
                    case 6:
                        if (params.getTeamRankingType(4) == Parameters.C_RANKING_VND) {
                            object = convertVND(obj.getValue5());
                        } else {
                            object = obj.getValue5();
                        }
                        break;
                    default:
                }
            }
        } catch (RemoteException re) {
            re.printStackTrace();
        }
        return object;
    }

    @Override
    public Component getTableCellRendererComponent(final JTable table,
            final Object value,
            final boolean isSelected,
            final boolean hasFocus,
            final int row,
            final int column
    ) {

        JLabel obj = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        if (Tournament.getTournament().getParams().isUseImage()) {
            if (column == 1) {
                Team t = (Team) mObjects.get(row);
                if (t.getPicture() != null) {
                    ImageIcon icon = ImageTreatment.resize(t.getPicture(), 30, 30);
                    obj.setIcon(icon);
                    obj.setHorizontalAlignment(JLabel.CENTER);
                    return obj;
                }
            }
        }

        return obj;
    }

}
