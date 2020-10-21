/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bb.tourma.tableModel;

import java.awt.Component;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import bb.tourma.data.Coach;
import bb.tourma.data.CoachMatch;
import bb.tourma.data.Criterion;
import bb.tourma.data.ETeamPairing;
import bb.tourma.data.IWithNameAndPicture;
import bb.tourma.data.ObjectRanking;
import bb.tourma.data.Parameters;
import bb.tourma.data.Pool;
import bb.tourma.data.Round;
import bb.tourma.data.Team;
import bb.tourma.data.TeamMatch;
import bb.tourma.data.Tournament;
import bb.tourma.data.Value;
import bb.tourma.data.ranking.Ranking;
import bb.tourma.data.ranking.TeamRanking;
import bb.tourma.languages.Translate;
import bb.tourma.utility.StringConstants;
import bb.tourma.utils.ImageTreatment;

/**
 *
 * @author Frederic Berger
 */
public final class MjtRankingTeam extends MjtRanking {

    private static final Logger LOG = Logger.getLogger(MjtRankingTeam.class.getName());

    public  MjtRankingTeam(TeamRanking ranking, int min, int max) {
        super(ranking, min, max);
    }

    public MjtRankingTeam(TeamRanking ranking) {
        this(ranking, 0, ranking.getCount());
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
                        if (params.getRankingTeam2() == 0) {
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
                val = Ranking.getRankingString(mRanking.getRankingType1());
                break;
            case 3:
                val = Ranking.getRankingString(mRanking.getRankingType2());
                break;
            case 4:
                val = Ranking.getRankingString(mRanking.getRankingType3());
                break;
            case 5:
                val = Ranking.getRankingString(mRanking.getRankingType4());
                break;
            case 6:
                val = Ranking.getRankingString(mRanking.getRankingType5());
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
            if (mRanking.getCount() > row + mMin) {
                final ObjectRanking obj = (ObjectRanking) mRanking.getSortedObject(row + mMin);
                switch (col) {
                    case 0:
                        object = row + mMin + 1;
                        break;
                    case 1:
                        object = ((IWithNameAndPicture) obj.getObject()).getName();
                        break;
                    case 2:
                        if ((params.isTeamVictoryOnly() && (params.getTeamRankingType(0) == Parameters.C_RANKING_VND))
                                || (!params.isTeamVictoryOnly() && (params.getIndivRankingType(0) == Parameters.C_RANKING_VND))
                                || (params.isTeamVictoryOnly() && (params.getTeamRankingType(0) == Parameters.C_RANKING_TEAMMATES_VND))
                                || (!params.isTeamVictoryOnly() && (params.getIndivRankingType(0) == Parameters.C_RANKING_TEAMMATES_VND))) {
                            object = Ranking.convertVND(obj.getValue1());
                        } else {
                            object = obj.getValue1();
                        }
                        break;
                    case 3:
                        if ((params.isTeamVictoryOnly() && (params.getTeamRankingType(1) == Parameters.C_RANKING_VND))
                                || (!params.isTeamVictoryOnly() && (params.getIndivRankingType(1) == Parameters.C_RANKING_VND))
                                || (params.isTeamVictoryOnly() && (params.getTeamRankingType(1) == Parameters.C_RANKING_TEAMMATES_VND))
                                || (!params.isTeamVictoryOnly() && (params.getIndivRankingType(1) == Parameters.C_RANKING_TEAMMATES_VND))) {
                            object = Ranking.convertVND(obj.getValue2());
                        } else {
                            object = obj.getValue2();
                        }
                        break;
                    case 4:
                        if ((params.isTeamVictoryOnly() && (params.getTeamRankingType(2) == Parameters.C_RANKING_VND))
                                || (!params.isTeamVictoryOnly() && (params.getIndivRankingType(2) == Parameters.C_RANKING_VND))
                                || (params.isTeamVictoryOnly() && (params.getTeamRankingType(2) == Parameters.C_RANKING_TEAMMATES_VND))
                                || (!params.isTeamVictoryOnly() && (params.getIndivRankingType(2) == Parameters.C_RANKING_TEAMMATES_VND))) {
                            object = Ranking.convertVND(obj.getValue3());
                        } else {
                            object = obj.getValue3();
                        }
                        break;
                    case 5:
                        if ((params.isTeamVictoryOnly() && (params.getTeamRankingType(3) == Parameters.C_RANKING_VND))
                                || (!params.isTeamVictoryOnly() && (params.getIndivRankingType(3) == Parameters.C_RANKING_VND))
                                || (params.isTeamVictoryOnly() && (params.getTeamRankingType(3) == Parameters.C_RANKING_TEAMMATES_VND))
                                || (!params.isTeamVictoryOnly() && (params.getIndivRankingType(3) == Parameters.C_RANKING_TEAMMATES_VND))) {
                            object = Ranking.convertVND(obj.getValue4());
                        } else {
                            object = obj.getValue4();
                        }
                        break;
                    case 6:
                        if ((params.isTeamVictoryOnly() && (params.getTeamRankingType(4) == Parameters.C_RANKING_VND))
                                || (!params.isTeamVictoryOnly() && (params.getIndivRankingType(4) == Parameters.C_RANKING_VND))
                                || (params.isTeamVictoryOnly() && (params.getTeamRankingType(4) == Parameters.C_RANKING_TEAMMATES_VND))
                                || (!params.isTeamVictoryOnly() && (params.getIndivRankingType(4) == Parameters.C_RANKING_TEAMMATES_VND))) {
                            object = Ranking.convertVND(obj.getValue5());
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

        JLabel obj = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row + mMin, column);

        if (Tournament.getTournament().getParams().isUseImage()) {
            if (column == 1) {
                Team t = (Team) mRanking.getObject(row + mMin);
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
