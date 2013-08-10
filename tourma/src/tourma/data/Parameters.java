/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import javax.swing.JOptionPane;
import org.jdom.DataConversionException;
import org.jdom.Element;
import tourma.MainFrame;
import tourma.utility.StringConstants;

/**
 *
 * @author Frederic Berger
 */
public class Parameters implements XMLExport {

    public int mGame = RosterType.C_BLOOD_BOWL;
    public int mPointsIndivVictory = 1000;
    public int mPointsIndivLargeVictory = 1100;
    public int mPointsIndivDraw = 400;
    public int mPointsIndivLittleLost = 100;
    public int mPointsIndivLost = 0;
    public boolean mTeamVictoryOnly = false;
    public int mGapLargeVictory = 3;
    public int mGapLittleLost = 1;
    public boolean mSubstitutes = false;
    public ArrayList<Criteria> mCriterias;
    public String mTournamentName;
    public String mTournamentOrga;
    public String mPlace;
    public Date mDate = new Date();
    public int mRankingIndiv1 = 1;
    public int mRankingIndiv2 = 2;
    public int mRankingIndiv3 = 3;
    public int mRankingIndiv4 = 4;
    public int mRankingIndiv5 = 5;
    public boolean mTeamTournament = false;
    /* Pairing: 0: Individuel
     1: ByTeam
     */
    public int mTeamPairing = 0;
    /* IndivPairing:
     0: Classement
     1: Libre
     2: Aléatoire
     */
    public int mTeamIndivPairing = 0;
    public int mTeamMatesNumber = 1;
    public int mPointsTeamVictory = 1000;
    public int mPointsTeamDraw = 400;
    public int mPointsTeamLost = 0;
    public int mRankingTeam1 = 1;
    public int mRankingTeam2 = 2;
    public int mRankingTeam3 = 3;
    public int mRankingTeam4 = 4;
    public int mRankingTeam5 = 5;
    public int mPointsTeamVictoryBonus = 0;
    public int mPointsTeamDrawBonus = 0;
    /**
     * *********************
     * Roster group management *********************
     */
    public boolean mGroupsEnable = false;
    /**
     * *********************
     * Clan management *********************
     */
    /**
     * Activate clan management
     */
    public boolean mEnableClans = false;
    /**
     * Avoid match between members of a clan
     */
    public boolean mAvoidClansFirstMatch = true;
    /**
     * Avoid match between members of the same clan
     */
    public boolean mAvoidClansMatch = false;
    /**
     * Number of player of one clan used for clan ranking
     */
    public int mTeamMatesClansNumber = 3;
    public static final int C_RANKING_SUBTYPE_POSITIVE = 0;
    public static final int C_RANKING_SUBTYPE_NEGATIVE = 1;
    public static final int C_RANKING_SUBTYPE_DIFFERENCE = 2;
    public static final int C_RANKING_NONE = 0;
    public static final int C_RANKING_POINTS = 1;
    public static final int C_RANKING_OPP_POINTS = 2;
    public static final int C_RANKING_VND = 3;
    public static final int C_MAX_RANKING = 3;

    /*public static final int C_RANKING_TD = 3;
     public static final int C_RANKING_SOR = 4;
     public static final int C_RANKING_FOUL = 5;
     public static final int C_RANKING_DIFF_TD = 6;
     public static final int C_RANKING_DIFF_SOR = 7;
     public static final int C_RANKING_DIFF_FOUL = 8;
    
     public static final int C_RANKING_PAS = 10;
     public static final int C_RANKING_INT = 11;
     public static final int C_RANKING_DIFF_PAS = 12;
     public static final int C_RANKING_DIFF_INT = 13;*/
    public Parameters() {
        mTournamentName = "";
        mTournamentOrga = "";
        mCriterias = new ArrayList<Criteria>();

        Criteria c = new Criteria(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("Touchdowns"));
        c.mPointsFor = 2;
        mCriterias.add(c);
        c = new Criteria(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("Injuries"));
        c.mPointsFor = 1;
        mCriterias.add(c);
    }

    public Element getXMLElement() {
        final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss",Locale.getDefault());
        final Element params = new Element("Parameters");
        params.setAttribute("Organizer", this.mTournamentOrga);
        params.setAttribute("Name", this.mTournamentName);
        params.setAttribute("Date", format.format(this.mDate));
        params.setAttribute("Place", this.mTournamentName);

        for (int i = 0; i < this.mCriterias.size(); i++) {
           final Element crit = mCriterias.get(i).getXMLElement();
            params.addContent(crit);
        }

        params.setAttribute("Victory", Integer.toString(this.mPointsIndivVictory));
        params.setAttribute("Large_Victory", Integer.toString(this.mPointsIndivLargeVictory));
        params.setAttribute("Draw", Integer.toString(this.mPointsIndivDraw));
        params.setAttribute("Lost", Integer.toString(this.mPointsIndivLost));
        params.setAttribute("Little_Lost", Integer.toString(this.mPointsIndivLittleLost));

        params.setAttribute("Victory_Team", Integer.toString(this.mPointsTeamVictory));
        params.setAttribute("Draw_Team", Integer.toString(this.mPointsTeamDraw));
        params.setAttribute("Lost_Team", Integer.toString(this.mPointsTeamLost));

        params.setAttribute("Large_Victory_Gap", Integer.toString(this.mGapLargeVictory));
        params.setAttribute("Little_Lost_Gap", Integer.toString(this.mGapLittleLost));

        params.setAttribute("Rank1", Integer.toString(this.mRankingIndiv1));
        params.setAttribute("Rank2", Integer.toString(this.mRankingIndiv2));
        params.setAttribute("Rank3", Integer.toString(this.mRankingIndiv3));
        params.setAttribute("Rank4", Integer.toString(this.mRankingIndiv4));
        params.setAttribute("Rank5", Integer.toString(this.mRankingIndiv5));

        params.setAttribute("Rank1_Team", Integer.toString(this.mRankingTeam1));
        params.setAttribute("Rank2_Team", Integer.toString(this.mRankingTeam2));
        params.setAttribute("Rank3_Team", Integer.toString(this.mRankingTeam3));
        params.setAttribute("Rank4_Team", Integer.toString(this.mRankingTeam4));
        params.setAttribute("Rank5_Team", Integer.toString(this.mRankingTeam5));

        params.setAttribute("ByTeam", Boolean.toString(this.mTeamTournament));
        params.setAttribute("TeamMates", Integer.toString(this.mTeamMatesNumber));
        params.setAttribute("TeamPairing", Integer.toString(this.mTeamPairing));
        params.setAttribute("TeamIndivPairing", Integer.toString(this.mTeamIndivPairing));

        params.setAttribute("TeamVictoryPoints", Integer.toString(this.mPointsTeamVictoryBonus));
        params.setAttribute("TeamDrawPoints", Integer.toString(this.mPointsTeamDrawBonus));
        params.setAttribute("TeamVictoryOnly", Boolean.toString(this.mTeamVictoryOnly));

        params.setAttribute("GroupEnable", Boolean.toString(this.mGroupsEnable));
        params.setAttribute("Substitutes", Boolean.toString(this.mSubstitutes));
        params.setAttribute("GameType", Integer.toString(this.mGame));

        params.setAttribute("ActvateClans", Boolean.toString(this.mEnableClans));
        params.setAttribute("AvoidFirstMatch", Boolean.toString(this.mAvoidClansFirstMatch));
        params.setAttribute("AvoidMatch", Boolean.toString(this.mAvoidClansMatch));
        params.setAttribute("ClanTeammatesNumber", Integer.toString(this.mTeamMatesClansNumber));

        return params;
    }

    public void setXMLElement(final Element params) {
        final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault());

        try {
            this.mTournamentOrga = params.getAttribute("Organizer").getValue();
            this.mTournamentName = params.getAttribute("Name").getValue();

            this.mPointsIndivVictory = params.getAttribute("Victory").getIntValue();
            this.mPointsIndivLargeVictory = params.getAttribute("Large_Victory").getIntValue();
            this.mPointsIndivDraw = params.getAttribute("Draw").getIntValue();
            this.mPointsIndivLost = params.getAttribute("Lost").getIntValue();
            this.mPointsIndivLittleLost = params.getAttribute("Little_Lost").getIntValue();
            this.mRankingIndiv1 = params.getAttribute("Rank1").getIntValue();
            this.mRankingIndiv2 = params.getAttribute("Rank2").getIntValue();
            this.mRankingIndiv3 = params.getAttribute("Rank3").getIntValue();
            this.mRankingIndiv4 = params.getAttribute("Rank4").getIntValue();
            this.mRankingIndiv5 = params.getAttribute("Rank5").getIntValue();

            try {
                this.mGapLargeVictory = params.getAttribute("Large_Victory_Gap").getIntValue();
                this.mGapLittleLost = params.getAttribute("Little_Lost_Gap").getIntValue();
                this.mPlace = params.getAttribute("Place").getValue();
                this.mTeamTournament = params.getAttribute("ByTeam").getBooleanValue();
                this.mTeamMatesNumber = params.getAttribute("TeamMates").getIntValue();
                this.mTeamPairing = params.getAttribute("TeamPairing").getIntValue();
                this.mTeamIndivPairing = params.getAttribute("TeamIndivPairing").getIntValue();
                this.mPointsTeamVictoryBonus = params.getAttribute("TeamVictoryPoints").getIntValue();
                this.mTeamIndivPairing = params.getAttribute("TeamIndivPairing").getIntValue();
                this.mTeamVictoryOnly = params.getAttribute("TeamVictoryOnly").getBooleanValue();
                try {
                    this.mPointsTeamDrawBonus = params.getAttribute("TeamDrawPoints").getIntValue();
                } catch (Exception e) {
                    this.mPointsTeamDrawBonus = 0;
                }

                try {
                    this.mDate = format.parse(params.getAttribute("Date").getValue());
                } catch (ParseException pe) {
                }

                try {
                    this.mGame = params.getAttribute("GameType").getIntValue();
                } catch (Exception pe) {
                    this.mGame = 1;
                }

                this.mGroupsEnable = params.getAttribute("GroupEnable").getBooleanValue();

            } catch (NullPointerException ne) {
                this.mGapLargeVictory = 3;
                this.mGapLittleLost = 1;
                this.mPlace = "";
                this.mTeamTournament = false;
                this.mTeamMatesNumber = 6;
                this.mTeamPairing = 1;
                this.mTeamIndivPairing = 0;
            }
            try {
                this.mPointsTeamVictory = params.getAttribute("Victory_Team").getIntValue();
                this.mPointsTeamDraw = params.getAttribute("Draw_Team").getIntValue();
                this.mPointsTeamLost = params.getAttribute("Lost_Team").getIntValue();
                this.mRankingTeam1 = params.getAttribute("Rank1_Team").getIntValue();
                this.mRankingTeam2 = params.getAttribute("Rank2_Team").getIntValue();
                this.mRankingTeam3 = params.getAttribute("Rank3_Team").getIntValue();
                this.mRankingTeam4 = params.getAttribute("Rank4_Team").getIntValue();
                this.mRankingTeam5 = params.getAttribute("Rank5_Team").getIntValue();
            } catch (NullPointerException ne2) {
                JOptionPane.showMessageDialog(MainFrame.getMainFrame(), ne2.getLocalizedMessage());
            }

            try {
                this.mEnableClans = params.getAttribute("ActvateClans").getBooleanValue();
                this.mAvoidClansFirstMatch = params.getAttribute("AvoidFirstMatch").getBooleanValue();
                this.mAvoidClansMatch = params.getAttribute("AvoidMatch").getBooleanValue();
                this.mTeamMatesClansNumber = params.getAttribute("ClanTeammatesNumber").getIntValue();
                this.mSubstitutes = params.getAttribute("Substitutes").getBooleanValue();

            } catch (NullPointerException ne3) {
                JOptionPane.showMessageDialog(MainFrame.getMainFrame(), ne3.getLocalizedMessage());
            }

            final List criterias = params.getChildren("Criteria");
            final Iterator cr = criterias.iterator();

            this.mCriterias.clear();

            while (cr.hasNext()) {
                final Element criteria = (Element) cr.next();
                final Criteria crit = new Criteria(criteria.getAttributeValue("Name"));
                crit.setXMLElement(criteria);
                this.mCriterias.add(crit);
            }
        } catch (DataConversionException dce) {
            JOptionPane.showMessageDialog(MainFrame.getMainFrame(), dce.getLocalizedMessage());
        }
    }
}
