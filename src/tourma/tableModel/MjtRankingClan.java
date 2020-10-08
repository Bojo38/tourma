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
import tourma.data.Clan;
import tourma.data.Coach;
import tourma.data.CoachMatch;
import tourma.data.IWithNameAndPicture;
import tourma.data.ObjectRanking;
import tourma.data.Round;
import tourma.data.Team;
import tourma.data.TeamMatch;
import tourma.data.Tournament;
import tourma.data.ranking.ClanRanking;
import tourma.data.ranking.Ranking;
import tourma.languages.Translate;
import tourma.utility.StringConstants;
import tourma.utils.ImageTreatment;

/**
 *
 * @author Frederic Berger
 */
@SuppressWarnings("serial")
public final class MjtRankingClan extends MjtRanking {

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
    public MjtRankingClan(ClanRanking ranking,int min, int max) {
        super(ranking,min,max);

    }

     public MjtRankingClan(ClanRanking ranking){
          this(ranking,0,ranking.getCount());
     }
 
   
    @Override
    public int getColumnCount() {
        int result = 7;
        //Parameters params = Tournament.getTournament().getParams();
        if (mRanking.getRankingType5() == 0) {
            result--;
            if (mRanking.getRankingType4() == 0) {
                result--;
                if (mRanking.getRankingType3() == 0) {
                    result--;
                    if (mRanking.getRankingType2() == 0) {
                        result--;
                        if (mRanking.getRankingType1() == 0) {
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
        String result = StringConstants.CS_NULL;
        switch (col) {
            case 0:
                result = StringConstants.CS_HASH;
                break;
            case 1:
                result = Translate.translate(Translate.CS_Clan);
                break;
            case 2:
                result = Ranking.getRankingString(mRanking.getRankingType1());
                break;
            case 3:
                result = Ranking.getRankingString(mRanking.getRankingType2());
                break;
            case 4:
                result = Ranking.getRankingString(mRanking.getRankingType3());
                break;
            case 5:
                result = Ranking.getRankingString(mRanking.getRankingType4());
                break;
            case 6:
                result = Ranking.getRankingString(mRanking.getRankingType5());
                break;
            default:
        }
        return result;

    }

    @Override
    public Object getValueAt(final int row, final int col) {
        Object object = StringConstants.CS_NULL;
        final ObjectRanking obj = (ObjectRanking) mRanking.getSortedObject(row+mMin);
        try {
            switch (col) {
                case 0:
                    object = row +mMin+ 1;
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
        } catch (RemoteException re) {
            re.printStackTrace();
        }
        return object;

    }

    @Override
    public Component getTableCellRendererComponent(final JTable table, final Object value, final boolean isSelected, final boolean hasFocus, final int row, final int column) {
        JLabel obj = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row+mMin, column);
        if (Tournament.getTournament().getParams().isUseImage()) {
            if (column == 1) {
                Clan t = (Clan) mRanking.getObject(row+mMin);
                if (t.getPicture() != null) {
                    ImageIcon icon = ImageTreatment.resize(t.getPicture(), 30, 30);
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
