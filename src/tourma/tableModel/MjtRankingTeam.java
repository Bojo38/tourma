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
import tourma.data.Coach;
import tourma.data.CoachMatch;
import tourma.data.Criteria;
import tourma.data.IWithNameAndPicture;
import tourma.data.ObjectRanking;
import tourma.data.Parameters;
import tourma.data.Pool;
import tourma.data.Team;
import tourma.data.Tournament;
import tourma.data.Value;
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
    public static int getTeamVND(final Team t, final ArrayList<CoachMatch> v) {
        int value = 0;
        
        int nbVictory = 0;
        int nbLost = 0;
        for (int i = 0; i < v.size(); i++) {
            final CoachMatch m = v.get(i);
            final Value val = m.getValue(Tournament.getTournament().getParams().getCriteria(0));
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
    public static int getTeamPoints(final Team t, final ArrayList<CoachMatch> v) {
        int value = 0;
        
        int nbVictory = 0;
        int nbLost = 0;
        for (int i = 0; i < v.size(); i++) {
            final CoachMatch m = v.get(i);
            final Value val = m.getValue(Tournament.getTournament().getParams().getCriteria(0));
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
        super(round, ranking_type1, ranking_type2, ranking_type3, ranking_type4, ranking_type5, teams,round_only);
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
    public MjtRankingTeam(final boolean teamVictory, final int round,  final ArrayList teams, final boolean round_only) {
        
            super(round, Tournament.getTournament().getParams().getRankingTeam1(), Tournament.getTournament().getParams().gemRankingTeam2(), Tournament.getTournament().getParams().getRankingTeam3(), Tournament.getTournament().getParams().getRankingTeam4(), Tournament.getTournament().getParams().getRankingTeam5(),
                     teams,round_only);
        if (!teamVictory)
        {
            this.mRankingType1=Tournament.getTournament().getParams().getRankingIndiv1();
            this.mRankingType2=Tournament.getTournament().getParams().getRankingIndiv2();
            this.mRankingType3=Tournament.getTournament().getParams().getRankingIndiv3();
            this.mRankingType4=Tournament.getTournament().getParams().getRankingIndiv4();
            this.mRankingType5=Tournament.getTournament().getParams().getRankingIndiv5();
        }
        mTeamVictory = teamVictory;
        sortDatas();
        
    }

    /**
     *
     */
    @Override
    protected void sortDatas() {

        mDatas.clear();
        mDatas = new ArrayList();
        for (int i = 0; i < mObjects.size(); i++) {
            final Team t = (Team) mObjects.get(i);
            final int value1 = getValue(t, mRankingType1, 0,mTeamVictory);
            final int value2 = getValue(t, mRankingType2, 0,mTeamVictory);
            final int value3 = getValue(t, mRankingType3, 0,mTeamVictory);
            final int value4 = getValue(t, mRankingType4, 0,mTeamVictory);
            final int value5 = getValue(t, mRankingType5, 0,mTeamVictory);


            mDatas.add(new ObjectRanking(t, value1, value2, value3, value4, value5));
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
    public String getColumnName(final int col) {
        String val = "";
        switch (col) {
            case 0:
                val = "#";
                break;
            case 1:
                val = java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("Team");
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

    /*  public static int getOppTeamPoints(Team t, ArrayList<Match> v, ArrayList<Round> vr) {
    int value = 0;
    Team opponent = null;
    if (v.size() > 0) {
    if (v.get(0).mCompetitor1.mTeamMates == t) {
    opponent = v.get(0).mCompetitor2.mTeamMates;
    } else {
    opponent = v.get(0).mCompetitor1.mTeamMates;
    }
    for (int i = 0; i < vr.size(); i++) {
    Round r = vr.get(i);
    ArrayList<Match> v2 = r.getMatchs();
    ArrayList<Match> v_opp = new ArrayList<Match>();
    for (int j = 0; j < v2.size(); j++) {
    CoachMatch m = v2.get(j);
    if ((m.mCompetitor1.mTeamMates == opponent) || (m.mCompetitor2.mTeamMates == opponent)) {
    v_opp.add(m);
    }
    }
    value += getTeamPoints(t, v_opp);
    }
    }
    return value;
    }*/
    /*public static int getValue(Team t, ArrayList<Match> v, int valueType, ArrayList<Round> rounds) {
    int value = 0;
    switch (valueType) {
    case Parameters.C_RANKING_POINTS:
    if (Tournament.getTournament().getParams().mTeamVictoryOnly) {
    value = getTeamPoints(t, v);
    } else {
    for (int i = 0; i < t.mCoachs.size(); i++) {
    for (int j = 0; j < v.size(); j++) {
    value += getPointsByCoach(t.mCoachs.get(i), v.get(j));
    }
    }
    if (getTeamVND(t, v) >= 1000000) {
    value += Tournament.getTournament().getParams().mPointsTeamVictoryBonus;
    }
    }
    break;
    case Parameters.C_RANKING_NONE:
    value = 0;
    break;
    case Parameters.C_RANKING_OPP_POINTS:
    if (Tournament.getTournament().getParams().mTeamVictoryOnly) {
    value = getOppTeamPoints(t, v, rounds);
    } else {
    for (int i = 0; i < t.mCoachs.size(); i++) {
    for (int j = 0; j < v.size(); j++) {
    value += getOppPointsByCoach(t.mCoachs.get(i), rounds);
    }
    }
    if (getTeamVND(t, v) < 1000000) {
    value += Tournament.getTournament().getParams().mPointsTeamVictoryBonus;
    }
    }
    break;
    case Parameters.C_RANKING_VND:
    if (Tournament.getTournament().getParams().mTeamVictoryOnly) {
    value = getTeamVND(t, v);
    } else {
    for (int i = 0; i < t.mCoachs.size(); i++) {
    for (int j = 0; j < v.size(); j++) {
    value += getVNDByCoach(t.mCoachs.get(i), v.get(j));
    }
    }
    }
    break;
    }
    return value;
    }*/
    @Override
    public Object getValueAt(final int row, final int col) {
        Object object = java.util.ResourceBundle.getBundle("tourma/languages/language").getString("");
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
        }
        return object;
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
                    obj.setHorizontalAlignment(JLabel.CENTER);                    
                    return obj;
                }
            }
        }
        return obj;
    }

}
