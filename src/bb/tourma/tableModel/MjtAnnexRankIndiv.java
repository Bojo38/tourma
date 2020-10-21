/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bb.tourma.tableModel;

import bb.tourma.data.Coach;
import bb.tourma.data.Competitor;
import bb.tourma.data.ObjectAnnexRanking;
import bb.tourma.data.ranking.AnnexIndivRanking;
import bb.tourma.data.ranking.AnnexRanking;
import bb.tourma.languages.Translate;
import bb.tourma.utility.StringConstants;

/**
 *
 * @author Frederic Berger
 */
@SuppressWarnings("serial")
public class MjtAnnexRankIndiv extends MjtAnnexRank {


    /**
     *
     * @param round
     * @param criteria
     * @param subtype
     * @param coachs
     * @param full
     * @param ranking_type1
     * @param ranking_type2
     * @param ranking_type3
     * @param ranking_type4
     * @param ranking_type5
     * @param teamTournament
     * @param round_only
     */
    public MjtAnnexRankIndiv(AnnexIndivRanking ranking , final boolean full,  int min, int max) {
        super(ranking,full, min, max);
    }

    public MjtAnnexRankIndiv(AnnexIndivRanking ranking , final boolean full) {
        super(ranking,full,0,ranking.getCount());
    }

   

    @Override
    public int getColumnCount() {

        return 5;
    }

    @Override
    public String getColumnName(final int col) {
        String result = StringConstants.CS_NULL;
        switch (col) {
            case 0:
                result = StringConstants.CS_HASH;
                break;
            case 1:
                result = Translate.translate(Translate.CS_Team);
                break;
            case 2:
                result = Translate.translate(Translate.CS_Coach);
                break;
            case 3:
                result = Translate.translate(Translate.CS_Roster);
                break;
            case 4:
                if (((AnnexRanking) mRanking).getCriterion() != null) {
                    if (((AnnexRanking) mRanking).getSubtype() == 0) {
                        result = ((AnnexRanking) mRanking).getCriterion().getName() + " " + Translate.translate(Translate.CS_Coach);
                    } else if (((AnnexRanking) mRanking).getSubtype() == 1) {
                        result = ((AnnexRanking) mRanking).getCriterion().getName() + " " + Translate.translate(Translate.CS_Opponent);
                    } else {
                        result = ((AnnexRanking) mRanking).getCriterion().getName() + " " + Translate.translate(Translate.CS_Difference);
                    }
                } else {
                    result = ((AnnexRanking) mRanking).getFormula().getName();
                }
                break;
            default:
        }
        return result;
    }

    @Override
    public Object getValueAt(final int row, final int col) {

        Object val = StringConstants.CS_NULL;

        final ObjectAnnexRanking obj = (ObjectAnnexRanking) mRanking.getSortedObject(row + mMin);
        switch (col) {
            case 0:
                val = row + mMin + 1;
                break;
            case 1:
                val = ((Coach) obj.getObject()).getTeam();
                break;
            case 2:
                val = ((Competitor) obj.getObject()).getDecoratedName();
                break;
            case 3:
                val = ((Coach) obj.getObject()).getStringRoster();
                break;
            case 4:
                val = obj.getValue();
                break;
            default:
        }
        return val;

    }
}
