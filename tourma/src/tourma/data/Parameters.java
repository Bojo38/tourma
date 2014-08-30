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
import java.util.ResourceBundle;
import javax.swing.JOptionPane;
import org.jdom2.DataConversionException;
import org.jdom2.Element;
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
    public int mPointsRefused = 0;
    public int mPointsConcedeed = 0;
    public boolean mTeamVictoryOnly = false;
    public boolean mPortugal = false;
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
    public int mRankingIndiv3 = 0;
    public int mRankingIndiv4 = 0;
    public int mRankingIndiv5 = 0;
    public boolean mTeamTournament = false;
    public boolean mMultiRoster = false;
    /* Pairing: 0: Individuel
     1: ByTeam
     */
    public int mTeamPairing = 1;
    /* IndivPairing:
     0: Classement
     1: Libre
     2: Aléatoire
     3: Naf
     */
    public int mTeamIndivPairing = 0;
    public int mTeamMatesNumber = 1;
    public int mClansMembersNumber = 1;
    public boolean mIndivPairingTeamBalanced = false;
    public boolean mIndivPairingIndivBalanced = false;
    public int mPointsTeamVictory = 1000;
    public int mPointsTeamDraw = 400;
    public int mPointsTeamLost = 0;
    public int mRankingTeam1 = 1;
    public int mRankingTeam2 = 2;
    public int mRankingTeam3 = 0;
    public int mRankingTeam4 = 0;
    public int mRankingTeam5 = 0;
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
    public static final int C_RANKING_ELO = 4;
    public static final int C_RANKING_ELO_OPP = 5;
    public static final int C_RANKING_NB_MATCHS = 6;
    public static final int C_MAX_RANKING = 6;
    /**
     * Use the color to displlay Match/rank
     */
    public boolean useColor = true;
    public boolean useImage = true;
    protected final static ResourceBundle sbundle = java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE);

    public Parameters() {
        mTournamentName = sbundle.getString("");
        mTournamentOrga = sbundle.getString("");
        mCriterias = new ArrayList<>();

        Criteria c = new Criteria(sbundle.getString("Touchdowns"));
        c.mPointsFor = 2;
        mCriterias.add(c);
        c = new Criteria(sbundle.getString("Injuries"));
        c.mPointsFor = 1;
        mCriterias.add(c);
    }

    @Override
    public Element getXMLElement() {
        final SimpleDateFormat format = new SimpleDateFormat(sbundle.getString("DD/MM/YYYY HH:MM:SS"), Locale.getDefault());
        final Element params = new Element(sbundle.getString("PARAMETERS"));
        params.setAttribute(sbundle.getString("ORGANIZER"), this.mTournamentOrga);
        params.setAttribute(sbundle.getString("NAME"), this.mTournamentName);
        params.setAttribute(sbundle.getString("DATE"), format.format(this.mDate));
        params.setAttribute(sbundle.getString("PLACE"), this.mTournamentName);

        for (int i = 0; i < this.mCriterias.size(); i++) {
            final Element crit = mCriterias.get(i).getXMLElement();
            params.addContent(crit);
        }

        params.setAttribute(sbundle.getString("VICTORY"), Integer.toString(this.mPointsIndivVictory));
        params.setAttribute(sbundle.getString("LARGE_VICTORY"), Integer.toString(this.mPointsIndivLargeVictory));
        params.setAttribute(sbundle.getString("DRAW"), Integer.toString(this.mPointsIndivDraw));
        params.setAttribute(sbundle.getString("LOST"), Integer.toString(this.mPointsIndivLost));
        try {
            params.setAttribute("Refused", Integer.toString(this.mPointsRefused));
            params.setAttribute("Conceeded", Integer.toString(this.mPointsConcedeed));
        } catch (Exception e) {
        }
        params.setAttribute(sbundle.getString("LITTLE_LOST"), Integer.toString(this.mPointsIndivLittleLost));
        params.setAttribute("Portugal", Boolean.toString(this.mPortugal));

        params.setAttribute(sbundle.getString("VICTORY_TEAM"), Integer.toString(this.mPointsTeamVictory));
        params.setAttribute(sbundle.getString("DRAW_TEAM"), Integer.toString(this.mPointsTeamDraw));
        params.setAttribute(sbundle.getString("LOST_TEAM"), Integer.toString(this.mPointsTeamLost));

        params.setAttribute(sbundle.getString("LARGE_VICTORY_GAP"), Integer.toString(this.mGapLargeVictory));
        params.setAttribute(sbundle.getString("LITTLE_LOST_GAP"), Integer.toString(this.mGapLittleLost));

        params.setAttribute(sbundle.getString("RANK1"), Integer.toString(this.mRankingIndiv1));
        params.setAttribute(sbundle.getString("RANK2"), Integer.toString(this.mRankingIndiv2));
        params.setAttribute(sbundle.getString("RANK3"), Integer.toString(this.mRankingIndiv3));
        params.setAttribute(sbundle.getString("RANK4"), Integer.toString(this.mRankingIndiv4));
        params.setAttribute(sbundle.getString("RANK5"), Integer.toString(this.mRankingIndiv5));

        params.setAttribute(sbundle.getString("RANK1_TEAM"), Integer.toString(this.mRankingTeam1));
        params.setAttribute(sbundle.getString("RANK2_TEAM"), Integer.toString(this.mRankingTeam2));
        params.setAttribute(sbundle.getString("RANK3_TEAM"), Integer.toString(this.mRankingTeam3));
        params.setAttribute(sbundle.getString("RANK4_TEAM"), Integer.toString(this.mRankingTeam4));
        params.setAttribute(sbundle.getString("RANK5_TEAM"), Integer.toString(this.mRankingTeam5));

        params.setAttribute(sbundle.getString("BYTEAM"), Boolean.toString(this.mTeamTournament));
        params.setAttribute(sbundle.getString("TEAMMATES"), Integer.toString(this.mTeamMatesNumber));
        params.setAttribute(sbundle.getString("TEAMPAIRING"), Integer.toString(this.mTeamPairing));
        params.setAttribute(sbundle.getString("TEAMINDIVPAIRING"), Integer.toString(this.mTeamIndivPairing));

        params.setAttribute(sbundle.getString("TEAMVICTORYPOINTS"), Integer.toString(this.mPointsTeamVictoryBonus));
        params.setAttribute(sbundle.getString("TEAMDRAWPOINTS"), Integer.toString(this.mPointsTeamDrawBonus));
        params.setAttribute(sbundle.getString("TEAMVICTORYONLY"), Boolean.toString(this.mTeamVictoryOnly));

        params.setAttribute(sbundle.getString("GROUPENABLE"), Boolean.toString(this.mGroupsEnable));
        params.setAttribute(sbundle.getString("SUBSTITUTES"), Boolean.toString(this.mSubstitutes));
        params.setAttribute(sbundle.getString("GAMETYPE"), Integer.toString(this.mGame));

        params.setAttribute(sbundle.getString("ACTVATECLANS"), Boolean.toString(this.mEnableClans));
        params.setAttribute(sbundle.getString("AVOIDFIRSTMATCH"), Boolean.toString(this.mAvoidClansFirstMatch));
        params.setAttribute(sbundle.getString("AVOIDMATCH"), Boolean.toString(this.mAvoidClansMatch));

        params.setAttribute(sbundle.getString("CLANTEAMMATESNUMBER"), Integer.toString(this.mTeamMatesClansNumber));

        params.setAttribute(sbundle.getString("MULTIROSTER"), Boolean.toString(this.mMultiRoster));

        params.setAttribute(sbundle.getString("INDIVBALANCED"), Boolean.toString(this.mIndivPairingIndivBalanced));
        params.setAttribute(sbundle.getString("TEAMBALANCED"), Boolean.toString(this.mIndivPairingTeamBalanced));


        return params;
    }

    @Override
    public void setXMLElement(final Element params) {
        final SimpleDateFormat format = new SimpleDateFormat(sbundle.getString("DD/MM/YYYY HH:MM:SS"), Locale.getDefault());



        try {
            this.mTournamentOrga = params.getAttribute(sbundle.getString("ORGANIZER")).getValue();
            this.mTournamentName = params.getAttribute(sbundle.getString("NAME")).getValue();

            this.mPointsIndivVictory = params.getAttribute(sbundle.getString("VICTORY")).getIntValue();
            this.mPointsIndivLargeVictory = params.getAttribute(sbundle.getString("LARGE_VICTORY")).getIntValue();
            this.mPointsIndivDraw = params.getAttribute(sbundle.getString("DRAW")).getIntValue();
            this.mPointsIndivLost = params.getAttribute(sbundle.getString("LOST")).getIntValue();
            try {
                this.mPointsRefused = params.getAttribute("Refused").getIntValue();
                this.mPointsConcedeed = params.getAttribute("Concedeed").getIntValue();
            } catch (Exception e) {
            }
            this.mPointsIndivLittleLost = params.getAttribute(sbundle.getString("LITTLE_LOST")).getIntValue();

            this.mRankingIndiv1 = params.getAttribute(sbundle.getString("RANK1")).getIntValue();
            this.mRankingIndiv2 = params.getAttribute(sbundle.getString("RANK2")).getIntValue();
            this.mRankingIndiv3 = params.getAttribute(sbundle.getString("RANK3")).getIntValue();
            this.mRankingIndiv4 = params.getAttribute(sbundle.getString("RANK4")).getIntValue();
            this.mRankingIndiv5 = params.getAttribute(sbundle.getString("RANK5")).getIntValue();

            try {
                this.mGapLargeVictory = params.getAttribute(sbundle.getString("LARGE_VICTORY_GAP")).getIntValue();
                this.mGapLittleLost = params.getAttribute(sbundle.getString("LITTLE_LOST_GAP")).getIntValue();
                this.mPlace = params.getAttribute(sbundle.getString("PLACE")).getValue();
                this.mTeamTournament = params.getAttribute(sbundle.getString("BYTEAM")).getBooleanValue();
                
                this.mTeamMatesNumber = params.getAttribute(sbundle.getString("TEAMMATES")).getIntValue();
                this.mTeamPairing = params.getAttribute(sbundle.getString("TEAMPAIRING")).getIntValue();
                this.mTeamIndivPairing = params.getAttribute(sbundle.getString("TEAMINDIVPAIRING")).getIntValue();
                this.mPointsTeamVictoryBonus = params.getAttribute(sbundle.getString("TEAMVICTORYPOINTS")).getIntValue();
                this.mTeamIndivPairing = params.getAttribute(sbundle.getString("TEAMINDIVPAIRING")).getIntValue();
                this.mTeamVictoryOnly = params.getAttribute(sbundle.getString("TEAMVICTORYONLY")).getBooleanValue();
                try {
                    this.mPointsTeamDrawBonus = params.getAttribute(sbundle.getString("TEAMDRAWPOINTS")).getIntValue();
                } catch (Exception e) {
                    this.mPointsTeamDrawBonus = 0;
                }

                try {
                    this.mDate = format.parse(params.getAttribute(sbundle.getString("DATE")).getValue());
                } catch (ParseException pe) {
                }

                try {
                    this.mGame = params.getAttribute(sbundle.getString("GAMETYPE")).getIntValue();
                } catch (Exception pe) {
                    this.mGame = 1;
                }

                this.mGroupsEnable = params.getAttribute(sbundle.getString("GROUPENABLE")).getBooleanValue();

            } catch (NullPointerException ne) {
                this.mGapLargeVictory = 3;
                this.mGapLittleLost = 1;
                this.mPlace = sbundle.getString("");
                this.mTeamTournament = false;
                this.mTeamMatesNumber = 6;
                this.mTeamPairing = 1;
                this.mTeamIndivPairing = 0;
            }
            try {
                this.mPointsTeamVictory = params.getAttribute(sbundle.getString("VICTORY_TEAM")).getIntValue();
                this.mPointsTeamDraw = params.getAttribute(sbundle.getString("DRAW_TEAM")).getIntValue();
                this.mPointsTeamLost = params.getAttribute(sbundle.getString("LOST_TEAM")).getIntValue();
                this.mRankingTeam1 = params.getAttribute(sbundle.getString("RANK1_TEAM")).getIntValue();
                this.mRankingTeam2 = params.getAttribute(sbundle.getString("RANK2_TEAM")).getIntValue();
                this.mRankingTeam3 = params.getAttribute(sbundle.getString("RANK3_TEAM")).getIntValue();
                this.mRankingTeam4 = params.getAttribute(sbundle.getString("RANK4_TEAM")).getIntValue();
                this.mRankingTeam5 = params.getAttribute(sbundle.getString("RANK5_TEAM")).getIntValue();
            } catch (NullPointerException ne2) {
                JOptionPane.showMessageDialog(null, ne2.getLocalizedMessage());
            }

            try {
                this.mEnableClans = params.getAttribute(sbundle.getString("ACTVATECLANS")).getBooleanValue();
                this.mAvoidClansFirstMatch = params.getAttribute(sbundle.getString("AVOIDFIRSTMATCH")).getBooleanValue();
                this.mAvoidClansMatch = params.getAttribute(sbundle.getString("AVOIDMATCH")).getBooleanValue();
                this.mSubstitutes = params.getAttribute(sbundle.getString("SUBSTITUTES")).getBooleanValue();
                this.mMultiRoster = params.getAttribute(sbundle.getString("MULTIROSTER")).getBooleanValue();
                this.mMultiRoster = params.getAttribute("Portugal").getBooleanValue();

            } catch (NullPointerException ne3) {
                //JOptionPane.showMessageDialog(null, ne3.getLocalizedMessage());
            }
            try {
                this.mIndivPairingIndivBalanced = params.getAttribute(sbundle.getString("INDIVBALANCED")).getBooleanValue();
                this.mIndivPairingTeamBalanced = params.getAttribute(sbundle.getString("TEAMBALANCED")).getBooleanValue();
                
                        this.mClansMembersNumber = params.getAttribute(sbundle.getString("CLANTEAMMATESNUMBER")).getIntValue();

            } catch (NullPointerException ne4) {
                //JOptionPane.showMessageDialog(null, ne4.getLocalizedMessage());
            }

            final List criterias = params.getChildren(sbundle.getString("CRITERIA"));
            final Iterator cr = criterias.iterator();

            this.mCriterias.clear();

            while (cr.hasNext()) {
                final Element criteria = (Element) cr.next();
                final Criteria crit = new Criteria(criteria.getAttributeValue(sbundle.getString("NAME")));
                crit.setXMLElement(criteria);
                this.mCriterias.add(crit);
            }
        } catch (DataConversionException dce) {
            JOptionPane.showMessageDialog(null, dce.getLocalizedMessage());
        }
    }

    @Override
    public String toString() {
        return sbundle.getString("PARAMÈTRES");
    }

    public int getTeamRankingType(int j) {
        int rank = 0;
        switch (j) {
            case 0:
                rank = mRankingTeam1;
                break;
            case 1:
                rank = mRankingTeam2;
                break;
            case 2:
                rank = mRankingTeam3;
                break;
            case 3:
                rank = mRankingTeam4;
                break;

            case 4:
                rank = mRankingTeam5;
                break;

        }
        return rank;
    }
    
    public int getIndivRankingType(int j) {
        int rank = 0;
        switch (j) {
            case 0:
                rank = mRankingIndiv1;
                break;
            case 1:
                rank = mRankingIndiv2;
                break;
            case 2:
                rank = mRankingIndiv3;
                break;
            case 3:
                rank = mRankingIndiv4;
                break;

            case 4:
                rank = mRankingIndiv5;
                break;

        }
        return rank;
    }

    public int getIndivRankingNumber() {
        int nb = 0;
        if (mRankingIndiv1 != 0) {
            nb++;
            if (mRankingIndiv2 != 0) {
                nb++;
                if (mRankingIndiv3 != 0) {
                    nb++;
                    if (mRankingIndiv4 != 0) {
                        nb++;
                        if (mRankingIndiv5 != 0) {
                            nb++;
                        }
                    }
                }
            }
        }
        return nb;
    }
    
        public int getTeamRankingNumber() {
        int nb = 0;
        if (mRankingTeam1 != 0) {
            nb++;
            if (mRankingTeam2 != 0) {
                nb++;
                if (mRankingTeam3 != 0) {
                    nb++;
                    if (mRankingTeam4 != 0) {
                        nb++;
                        if (mRankingTeam5 != 0) {
                            nb++;
                        }
                    }
                }
            }
        }
        return nb;
    }
}
