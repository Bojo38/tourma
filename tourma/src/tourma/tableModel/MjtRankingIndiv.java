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
import tourma.data.Round;
import tourma.data.Tournament;
import tourma.utility.StringConstants;
import tourma.utils.ImageTreatment;

/**
 *
 * @author Frederic Berger
 */
public final class MjtRankingIndiv extends MjtRanking {
    private static final Logger LOG = Logger.getLogger(MjtRankingIndiv.class.getName());

    private final boolean mTeamTournament;
    private boolean mPositive;
    private final boolean mForPool;

    
    public MjtRankingIndiv(final int round, 
            final int ranking_type1,
            final int ranking_type2,
            final int ranking_type3,
            final int ranking_type4,
            final int ranking_type5,
            final ArrayList coachs, final boolean tournament, final boolean round_only, final boolean forPool) {
        super(round, ranking_type1, ranking_type2, ranking_type3, ranking_type4, ranking_type5, coachs,round_only);
        mTeamTournament = tournament;
        mForPool = forPool;
        sortDatas();
    }

    /**
     *
     */
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
            final Coach c = (Coach) mObjects.get(i);
            if (c.getMatchCount() > 0) {
                int value1 = 0;
                int value2 = 0;
                int value3 = 0;
                int value4 = 0;
                int value5 = 0;

                for (int j = 0; j <= c.getMatchCount() - 1; j++) {

                    final CoachMatch m = (CoachMatch) c.getMatch(j);
                    boolean bFound = false;
                    for (int l = 0; (l < rounds.size()) && (!bFound); l++) {
                        final Round r = rounds.get(l);
                        if (r.getCoachMatchs().contains(m)) {
                            bFound = true;
                        }
                    }
                    // test if match is in round
                    if (bFound) {
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
                            value2 += getValue(c, m, c1, subType2);
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

                        /*value1 = getValue(c, matchs, mRankingType1, _rounds);
                         value2 = getValue(c, matchs, mRankingType2, _rounds);
                         value3 = getValue(c, matchs, mRankingType3, _rounds);
                         value4 = getValue(c, matchs, mRankingType4, _rounds);
                         value5 = getValue(c, matchs, mRankingType5, _rounds);*/
                    }
                }
                mDatas.add(new ObjectRanking(c, value1, value2, value3, value4, value5));
            }
        }

        Collections.sort(mDatas);

        final Tournament tour = Tournament.getTournament();

        // On ajuste le tri par poule si nécessaire pour que
        // l'écart minimum entre 2 membres de la même poule
        // soit le nombre de joueurs de la poule
        if ((mForPool) && (!tour.getParams().isTeamTournament())) {
            if (mObjects.size() > tour.getPool(0).getCompetitorCount()) {
                final int nbPool = tour.getPoolCount();
                Pool p;
                final ArrayList<MjtRankingIndiv> pRank = new ArrayList<>();
                for (int j = 0; j < nbPool; j++) {
                    p = tour.getPool(j);
                    final MjtRankingIndiv mjtr = new MjtRankingIndiv(mRound, mRankingType1, mRankingType2, mRankingType3, mRankingType4, mRankingType5, p.getCompetitors(), mTeamTournament, mRoundOnly, false);
                    pRank.add(mjtr);
                }


                final ArrayList datas = new ArrayList();

                for (int i = 0; i < tour.getPool(0).getCompetitorCount(); i++) {
                    final ArrayList<Coach> rank = new ArrayList<>();
                    for (int j = 0; j < nbPool; j++) {
                        final ObjectRanking obj = (ObjectRanking) pRank.get(j).mDatas.get(i);
                        rank.add((Coach) obj.getObject());
                    }
                    final MjtRankingIndiv mjtr = new MjtRankingIndiv(mRound, mRankingType1, mRankingType2, mRankingType3, mRankingType4, mRankingType5, rank, mTeamTournament, mRoundOnly, false);

                    for (int j = 0; j < mjtr.mDatas.size(); j++) {
                        datas.add(mjtr.mDatas.get(j));
                    }
                }

                final ArrayList<Coach> rank = new ArrayList<>();
                for (int i = 0; i < tour.getCoachsCount(); i++) {

                    if (!tour.getCoach(i).isActive()) {
                        rank.add(tour.getCoach(i));
                    }
                }
                final MjtRankingIndiv mjtr = new MjtRankingIndiv(mRound, mRankingType1, mRankingType2, mRankingType3, mRankingType4, mRankingType5, rank, mTeamTournament, mRoundOnly, false);

                for (int j = 0; j < mjtr.mDatas.size(); j++) {
                    datas.add(mjtr.mDatas.get(j));
                }

                mDatas = datas;
            }
        }
    }

    /*protected int getValues(Coach c, int ranking_type, int round) {
     int value = 0;

     Criteria criteria=getCriteriaByValue(ranking_type);
     if (criteria!=null)
     {
     getValue(c, c.mMatchs.get(round), ranking_type, round);
     }
     else
     {
     getValue(c, c.mMatchs.get(round), criteria, mPositive);
     }

     return value;
     }*/
    @Override
    public int getColumnCount() {
        int result = 9;
        Parameters params = Tournament.getTournament().getParams();
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
        if (mTeamTournament) {
            result++;
        }
        return result;
    }

    @Override
    public String getColumnName(final int col) {

        String result = java.util.ResourceBundle.getBundle("tourma/languages/language").getString("");
        int cl = col;
        if (mTeamTournament) {
            if (col > 1) {
                cl = col - 1;
            }
            if (col == 1) {
                result = java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("Team");
            }
        }

        switch (cl) {
            case 0:
                result = java.util.ResourceBundle.getBundle("tourma/languages/language").getString("#");
                break;
            case 1:
                if (mTeamTournament) {
                    if (col != 1) {
                        result = java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("Team");
                    }
                } else {
                    result = java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("Team");
                }
                break;
            case 2:
                result = java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString(StringConstants.CS_COACH);
                break;
            case 3:
                result = java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("Roster");
                break;
            case 4:
                result = getRankingString(mRankingType1);
                break;
            case 5:
                result = getRankingString(mRankingType2);
                break;
            case 6:
                result = getRankingString(mRankingType3);
                break;
            case 7:
                result = getRankingString(mRankingType4);
                break;
            case 8:
                result = getRankingString(mRankingType5);
                break;
            default:
        }
        return result;
    }

    @Override
    public Object getValueAt(final int row, final int col) {
        Object object = "";
        if (mDatas.size() > row) {
            final ObjectRanking obj = (ObjectRanking) mDatas.get(row);
            int cl = col;
            if (mTeamTournament) {
                if (col > 1) {
                    cl = col - 1;
                }
                if (col == 1) {
                    object = ((Coach) obj.getObject()).getTeamMates().getName();
                }
            }

            switch (cl) {
                case 0:
                    object = row + 1;
                    break;
                case 1:
                    if (mTeamTournament) {
                        if (col != 1) {
                            object = ((Coach) obj.getObject()).getTeam();
                        }
                    } else {
                        object = ((Coach) obj.getObject()).getTeam();
                    }
                    break;
                case 2:
                    object = ((IWithNameAndPicture) obj.getObject()).getName();
                    break;
                case 3:
                    object = ((Coach) obj.getObject()).getStringRoster();
                    break;
                case 4:
                    object = obj.getValue1();
                    break;
                case 5:
                    object = obj.getValue2();
                    break;
                case 6:
                    object = obj.getValue3();
                    break;
                case 7:
                    object = obj.getValue4();
                    break;
                case 8:
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

        if (Tournament.getTournament().getParams().isUseImage())
        {
        if ((column == 1) && mTeamTournament) {
            Coach c = (Coach)mObjects.get(row);
            if (c.getTeamMates().getPicture() != null) {
                ImageIcon icon = ImageTreatment.resize(new ImageIcon(c.getTeamMates().getPicture()), 30, 30);
                obj.setIcon(icon);
                obj.setOpaque(true);
                return obj;
            }
        }
        }
        return obj;
    }
}