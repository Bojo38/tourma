/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.tableModel;

import java.awt.Component;
import java.rmi.RemoteException;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import tourma.data.Clan;
import tourma.data.IWithNameAndPicture;
import tourma.data.ObjectAnnexRanking;
import tourma.data.Tournament;
import tourma.data.ranking.AnnexClanRanking;
import tourma.data.ranking.AnnexRanking;
import tourma.languages.Translate;
import tourma.utility.StringConstants;
import tourma.utils.ImageTreatment;

/**
 *
 * @author Frederic Berger
 */
@SuppressWarnings("serial")
public class MjtAnnexRankClan extends MjtAnnexRank {

    private static final Logger LOG = Logger.getLogger(MjtAnnexRankClan.class.getName());

    /**
     *
     * @param round
     * @param criteria
     * @param subtype
     * @param clans
     * @param full
     * @param ranking_type1
     * @param ranking_type2
     * @param ranking_type3
     * @param ranking_type4
     * @param ranking_type5
     * @param round_only
     */
    public MjtAnnexRankClan(AnnexClanRanking ranking) {
        this(ranking, true, 0, ranking.getCount());
    }

    public MjtAnnexRankClan(AnnexClanRanking ranking,
            final boolean full,
            int min, int max) {
        super(ranking, full, min, max);
    }

    @Override
    public int getColumnCount() {
        return 3;
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
                if (((AnnexRanking)mRanking).getCriterion() != null) {
                    result = ((AnnexRanking)mRanking).getCriterion().getName();
                } else {
                    result = ((AnnexRanking)mRanking).getFormula().getName();
                }

                break;
            default:

        }
        return result;
    }

    @Override
    public Object getValueAt(final int row, final int col) {

        final ObjectAnnexRanking obj = (ObjectAnnexRanking) mRanking.getSortedObject(row + mMin);
        String result = StringConstants.CS_NULL;
        try {
            switch (col) {
                case 0:
                    result = Integer.toString(row + mMin + 1);
                    break;
                case 1:
                    result = ((IWithNameAndPicture) obj.getObject()).getName();
                    break;
                case 2:
                    result = Integer.toString(obj.getValue());
                    break;
                default:
            }
        } catch (RemoteException re) {
            System.out.println(re.getLocalizedMessage());
        }
        return result;
    }

    @Override
    public Component getTableCellRendererComponent(final JTable table, final Object value, final boolean isSelected, final boolean hasFocus, final int row, final int column) {
        JLabel obj = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row + mMin, column);

        if (Tournament.getTournament().getParams().isUseImage()) {
            if (column == 1) {
                Clan t = (Clan) mRanking.getObject(row + mMin);
                if (t.getPicture() != null) {
                    ImageIcon icon = ImageTreatment.resize(t.getPicture(), 30, 30);
                    obj.setIcon(icon);
                }
            }
        }

        return obj;
    }

}
