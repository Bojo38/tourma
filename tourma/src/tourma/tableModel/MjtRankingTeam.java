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
import tourma.data.Criteria;
import tourma.data.CoachMatch;
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
public class MjtRankingTeam extends MjtRanking {
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
            final Value val = m.mValues.get(Tournament.getTournament().getParams().mCriterias.get(0));
            if (((Coach) m.mCompetitor1).getTeamMates() == t) {
                if (val.mValue1 > val.mValue2) {
                    nbVictory++;
                } else {
                    if (val.mValue1 < val.mValue2) {
                        nbLost++;
                    }
                }
            }
            if (((Coach) m.mCompetitor2).getTeamMates() == t) {
                if (val.mValue1 < val.mValue2) {
                    nbVictory++;
                } else {
                    if (val.mValue1 > val.mValue2) {
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
            final Value val = m.mValues.get(Tournament.getTournament().getParams().mCriterias.get(0));
            if (((Coach) m.mCompetitor1).getTeamMates() == t) {
                if (val.mValue1 > val.mValue2) {
                    nbVictory++;
                } else {
                    if (val.mValue1 < val.mValue2) {
                        nbLost++;
                    }
                }
                
                for (int j = 0; j < Tournament.getTournament().getParams().mCriterias.size(); j++) {
                    
                    final Criteria cri = Tournament.getTournament().getParams().mCriterias.get(j);
                    final Value va = m.mValues.get(cri);
                    value += va.mValue1 + cri.mPointsFor;
                    value += va.mValue2 + cri.mPointsAgainst;
                }
                
            }
            if (((Coach) m.mCompetitor2).getTeamMates() == t) {
                if (val.mValue1 < val.mValue2) {
                    nbVictory++;
                } else {
                    if (val.mValue1 > val.mValue2) {
                        nbLost++;
                    }
                }
                for (int j = 0; j < Tournament.getTournament().getParams().mCriterias.size(); j++) {
                    final Criteria cri = Tournament.getTournament().getParams().mCriterias.get(j);
                    final Value va = m.mValues.get(cri);
                    value += va.mValue2 + cri.mPointsFor;
                    value += va.mValue1 + cri.mPointsAgainst;
                }
            }
        }
        
        if (nbVictory > nbLost) {
            value += Tournament.getTournament().getParams().mPointsTeamVictory;
        } else {
            if (nbVictory < nbLost) {
                value += Tournament.getTournament().getParams().mPointsTeamLost;
            } else {
                value += Tournament.getTournament().getParams().mPointsTeamDraw;
            }
        }
        return value;
    }

    boolean mTeamVictory;

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
        
            super(
                    round, Tournament.getTournament().getParams().mRankingTeam1,
                    Tournament.getTournament().getParams().mRankingTeam2,
                    Tournament.getTournament().getParams().mRankingTeam3,
                    Tournament.getTournament().getParams().mRankingTeam4,
                    Tournament.getTournament().getParams().mRankingTeam5,
                     teams,round_only);
        if (!teamVictory)
        {
            this.mRankingType1=Tournament.getTournament().getParams().mRankingIndiv1;
            this.mRankingType2=Tournament.getTournament().getParams().mRankingIndiv2;
            this.mRankingType3=Tournament.getTournament().getParams().mRankingIndiv3;
            this.mRankingType4=Tournament.getTournament().getParams().mRankingIndiv4;
            this.mRankingType5=Tournament.getTournament().getParams().mRankingIndiv5;
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
        if ((tour.getPools().size() > 0) && (tour.getRounds().size() > 0) && (!tour.getRounds().get(mRound).mCup)) {
            if (mObjects.size() > tour.getPools().get(0).mCompetitors.size()) {
                final int nbPool = tour.getPools().size();
                Pool p;
                final ArrayList<MjtRankingTeam> pRank = new ArrayList<>();
                for (int j = 0; j < nbPool; j++) {
                    p = tour.getPools().get(j);
                    final MjtRankingTeam mjtr = new MjtRankingTeam(mTeamVictory, mRound, mRankingType1, mRankingType2, mRankingType3, mRankingType4, mRankingType5, p.mCompetitors, mRoundOnly);
                    pRank.add(mjtr);
                }

                final ArrayList datas = new ArrayList();

                for (int i = 0; i < tour.getPools().get(0).mCompetitors.size(); i++) {
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
        if (params.mTeamVictoryOnly) {
            if (params.mRankingTeam5 == 0) {
                result--;
                if (params.mRankingTeam4 == 0) {
                    result--;
                    if (params.mRankingTeam3 == 0) {
                        result--;
                        if (params.mRankingTeam2 == 0) {
                            result--;
                            if (params.mRankingTeam1 == 0) {
                                result--;
                            }
                        }
                    }
                }
            }
        } else {
            if (params.mRankingIndiv5 == 0) {
                result--;
                if (params.mRankingIndiv4 == 0) {
                    result--;
                    if (params.mRankingIndiv3 == 0) {
                        result--;
                        if (params.mRankingIndiv2 == 0) {
                            result--;
                            if (params.mRankingIndiv1 == 0) {
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
                    object = ((Team) obj.getObject()).mName;
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
        if (Tournament.getTournament().getParams().useImage) {
            if (column == 1) {
                Team t = (Team) mObjects.get(row);
                if (t.picture != null) {
                    ImageIcon icon = ImageTreatment.resize(new ImageIcon(t.picture), 30, 30);
                    obj.setIcon(icon);
                    obj.setHorizontalAlignment(JLabel.CENTER);                    
                    return obj;
                }
            }
        }
        return obj;
    }

}
