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
import tourma.data.ETeamPairing;
import tourma.data.Formula;
import tourma.data.IWithNameAndPicture;
import tourma.data.ObjectAnnexRanking;
import tourma.data.Parameters;
import tourma.data.Round;
import tourma.data.Team;
import tourma.data.TeamMatch;
import tourma.data.Tournament;
import tourma.data.ranking.AnnexRanking;
import tourma.data.ranking.AnnexTeamRanking;
import tourma.languages.Translate;
import tourma.utility.StringConstants;
import tourma.utils.ImageTreatment;

/**
 *
 * @author Frederic Berger
 */
@SuppressWarnings({"serial", "ClassWithoutLogger"})
public final class MjtAnnexRankTeam extends MjtAnnexRank {

    public MjtAnnexRankTeam(AnnexTeamRanking ranking, final boolean full, int min, int max) {
        super(ranking, full, min, max);
    }

    public MjtAnnexRankTeam(AnnexTeamRanking ranking, final boolean full) {
        super(ranking, full, 0, ranking.getCount());

    }

    @Override
    public int getColumnCount() {
        return 3;
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
                if (((AnnexRanking)mRanking).getCriterion() != null) {
                    if (((AnnexRanking)mRanking).getSubtype()== 0) {
                        val = ((AnnexRanking)mRanking).getCriterion().getName() + " " + Translate.translate(Translate.CS_Team);
                    } else {
                        if (((AnnexRanking)mRanking).getSubtype() == 1) {
                            val = ((AnnexRanking)mRanking).getCriterion().getName() + " " + Translate.translate(Translate.CS_Opponent);
                        } else {
                            val = ((AnnexRanking)mRanking).getCriterion().getName() + " " + Translate.translate(Translate.CS_Difference);
                        }
                    }
                } else {
                    val = ((AnnexRanking)mRanking).getFormula().getName();
                }
                break;
            default:
                break;
        }
        return val;
    }

    @Override
    public Object getValueAt(final int row, final int col
    ) {

        Object val = StringConstants.CS_NULL;
        try {
            final ObjectAnnexRanking obj = (ObjectAnnexRanking) mRanking.getSortedObject(row + mMin);

            switch (col) {
                case 0:
                    val = row + mMin + 1;
                    break;
                case 1:
                    val = ((IWithNameAndPicture) obj.getObject()).getName();
                    break;
                case 2:
                    val = obj.getValue();
                    break;
                default:
            }
        } catch (RemoteException re) {
            re.printStackTrace();
        }
        return val;
    }

    @Override
    public Component getTableCellRendererComponent(final JTable table, final Object value, final boolean isSelected, final boolean hasFocus, final int row, final int column
    ) {
        JLabel obj = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row + mMin, column);
        if (Tournament.getTournament().getParams().isUseImage()) {
            if (column == 1) {
                Team t = (Team) mRanking.getObject(row + mMin);
                if (t.getPicture() != null) {
                    ImageIcon icon = ImageTreatment.resize(t.getPicture(), 30, 30);
                    obj.setIcon(icon);
                }
            }
        }

        return obj;
    }
}
