/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.tableModel;

import java.awt.Component;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import tourma.data.Coach;
import tourma.data.CoachMatch;
import tourma.data.Criterion;
import tourma.data.IWithNameAndPicture;
import tourma.data.ObjectRanking;
import tourma.data.Parameters;
import tourma.data.Pool;
import tourma.data.Round;
import tourma.data.Tournament;
import tourma.data.Value;
import tourma.data.ranking.IndivRanking;
import tourma.data.ranking.Ranking;
import tourma.languages.Translate;
import tourma.utility.StringConstants;
import tourma.utils.ImageTreatment;

/**
 *
 * @author Frederic Berger
 */
@SuppressWarnings("serial")
public final class MjtRankingIndiv extends MjtRanking {
    
    public MjtRankingIndiv(IndivRanking ranking) {
        
        super(ranking,0,ranking.getCount());
    }
    
    public MjtRankingIndiv(IndivRanking ranking,int min, int max) {
        super(ranking,min,max);
 
        ranking.sortDatas();
    }

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
        if ( ((IndivRanking)mRanking).isTeamTournament()) {
            result++;
        }
        return result;
    }

    @Override
    public String getColumnName(final int col) {

        String result = StringConstants.CS_NULL;
        int cl = col;
        if (((IndivRanking)mRanking).isTeamTournament()) {
            if (col > 1) {
                cl = col - 1;
            }
            if (col == 1) {
                result = Translate.translate(Translate.CS_Team);
            }
        }

        switch (cl) {
            case 0:
                result = StringConstants.CS_HASH;
                break;
            case 1:
                if (((IndivRanking)mRanking).isTeamTournament()) {
                    if (col != 1) {
                        result = Translate.translate(Translate.CS_Team);
                    }
                } else {
                    result = Translate.translate(Translate.CS_Team);
                }
                break;
            case 2:
                result = Translate.translate(Translate.CS_Coach);
                break;
            case 3:
                result = Translate.translate(Translate.CS_Roster);
                break;
            case 4:
                result = Ranking.getRankingString(mRanking.getRankingType1());
                break;
            case 5:
                result =  Ranking.getRankingString(mRanking.getRankingType2());
                break;
            case 6:
                result =  Ranking.getRankingString(mRanking.getRankingType3());
                break;
            case 7:
                result =  Ranking.getRankingString(mRanking.getRankingType4());
                break;
            case 8:
                result =  Ranking.getRankingString(mRanking.getRankingType5());
                break;
            default:
        }
        return result;
    }

    @Override
    public Object getValueAt(final int row, final int col) {
        Object object = "";
        try {
            if (mRanking.getCount() > row+mMin) {
                final ObjectRanking obj = (ObjectRanking) mRanking.getSortedObject(row+mMin);
                int cl = col;
                if (((IndivRanking)mRanking).isTeamTournament()) {
                    if (col > 1) {
                        cl = col - 1;
                    }
                    if (col == 1) {
                        object = ((Coach) obj.getObject()).getTeamMates().getName();
                    }
                }

                switch (cl) {
                    case 0:
                        object = row +mMin+ 1;
                        break;
                    case 1:
                        if (((IndivRanking)mRanking).isTeamTournament()) {
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
                        if ((Tournament.getTournament().getParams().getIndivRankingType(0) == Parameters.C_RANKING_VND)
                                ||(Tournament.getTournament().getParams().getIndivRankingType(0) == Parameters.C_RANKING_TEAMMATES_VND)) {
                            object = Ranking.convertVND(obj.getValue1());
                        } else {
                            object = obj.getValue1();
                        }
                        break;
                    case 5:
                        if ((Tournament.getTournament().getParams().getIndivRankingType(1) == Parameters.C_RANKING_VND)
                                ||(Tournament.getTournament().getParams().getIndivRankingType(1) == Parameters.C_RANKING_TEAMMATES_VND)){
                            object = Ranking.convertVND(obj.getValue2());
                        } else {
                            object = obj.getValue2();
                        }
                        break;
                    case 6:
                        if ((Tournament.getTournament().getParams().getIndivRankingType(2) == Parameters.C_RANKING_VND)
                                ||(Tournament.getTournament().getParams().getIndivRankingType(2) == Parameters.C_RANKING_TEAMMATES_VND)){
                            object = Ranking.convertVND(obj.getValue3());
                        } else {
                            object = obj.getValue3();
                        }
                        break;
                    case 7:
                        if ((Tournament.getTournament().getParams().getIndivRankingType(3) == Parameters.C_RANKING_VND)
                                ||(Tournament.getTournament().getParams().getIndivRankingType(3) == Parameters.C_RANKING_TEAMMATES_VND)){
                            object = Ranking.convertVND(obj.getValue4());
                        } else {
                            object = obj.getValue4();
                        }
                        break;
                    case 8:
                        if ((Tournament.getTournament().getParams().getIndivRankingType(4) == Parameters.C_RANKING_VND)
                                ||(Tournament.getTournament().getParams().getIndivRankingType(4) == Parameters.C_RANKING_TEAMMATES_VND)){
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
    public Component getTableCellRendererComponent(final JTable table, final Object value, final boolean isSelected, final boolean hasFocus, final int row, final int column) {
        JLabel obj = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row+mMin, column);
        if (Tournament.getTournament().getParams().isUseImage()) {
            if ((column == 1) && ((IndivRanking)mRanking).isTeamTournament()) {
                Coach c = (Coach) mRanking.getObject(row+mMin);
                if (c.getTeamMates().getPicture() != null) {
                    ImageIcon icon = ImageTreatment.resize(c.getTeamMates().getPicture(), 30, 30);
                    obj.setIcon(icon);
                    obj.setOpaque(true);
                    return obj;
                }
            }
        }
        return obj;
    }
}
