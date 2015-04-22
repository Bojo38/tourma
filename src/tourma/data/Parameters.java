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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.jdom2.Attribute;
import org.jdom2.DataConversionException;
import org.jdom2.Element;
import tourma.utility.StringConstants;

/**
 *
 * @author Frederic Berger
 */
public class Parameters implements XMLExport {

    /**
     *
     */
    public static final int C_RANKING_SUBTYPE_POSITIVE = 0;
    /**
     *
     */
    public static final int C_RANKING_SUBTYPE_NEGATIVE = 1;
    /**
     *
     */
    public static final int C_RANKING_SUBTYPE_DIFFERENCE = 2;

    /**
     * 
     */
    public static final int C_RANKING_NONE = 0;
    /**
     * 
     */
    public static final int C_RANKING_POINTS = 1;
    /**
     * 
     */
    public static final int C_RANKING_OPP_POINTS = 2;
    /**
     * 
     */
    public static final int C_RANKING_VND = 3;
    /**
     * 
     */
    public static final int C_RANKING_ELO = 4;
    /**
     * 
     */
    public static final int C_RANKING_ELO_OPP = 5;
    /**
     * 
     */
    public static final int C_RANKING_NB_MATCHS = 6;
    /**
     *
     */
    public static final int C_MAX_RANKING = 6;
    /**
     *
     */
    protected static final ResourceBundle sbundle = java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE);
    /**
     *
     */
    private static final Logger LOG = Logger.getLogger(Parameters.class.getName());

    /**
     *
     */
    private int mGame = RosterType.C_BLOOD_BOWL;

    /**
     *
     */
    private int mPointsIndivVictory = 1000;

    /**
     *
     */
    private int mPointsIndivLargeVictory = 1100;

    /**
     *
     */
    private int mPointsIndivDraw = 400;

    /**
     *
     */
    private int mPointsIndivLittleLost = 100;

    /**
     *
     */
    private int mPointsIndivLost = 0;

    /**
     *
     */
    private int mPointsRefused = 0;

    /**
     *
     */
    private int mPointsConcedeed = 0;

    /**
     *
     */
    private boolean mTeamVictoryOnly = false;

    /**
     *
     */
    private boolean mPortugal = false;

    /**
     *
     */
    private int mGapLargeVictory = 3;

    /**
     *
     */
    private int mGapLittleLost = 1;

    /**
     *
     */
    private boolean mSubstitutes = false;

    /**
     *
     */
    private final ArrayList<Criteria> mCriterias;

    /**
     *
     */
    private String mTournamentName;

    /**
     *
     */
    private String mTournamentOrga;

    /**
     *
     */
    private String mPlace;

    /**
     *
     */
    private final Date mDate = new Date();

    /**
     *
     */
    private int mRankingIndiv1 = C_RANKING_POINTS;

    /**
     *
     */
    private int mRankingIndiv2 = C_RANKING_OPP_POINTS;

    /**
     *
     */
    private int mRankingIndiv3 = C_RANKING_NONE;

    /**
     *
     */
    private int mRankingIndiv4 = C_RANKING_NONE;

    /**
     *
     */
    private int mRankingIndiv5 = C_RANKING_NONE;

    /**
     *
     */
    private boolean mTeamTournament = false;

    /**
     *
     */
    private boolean mMultiRoster = false;


    /* Pairing: 0: Individuel
     1: ByTeam
     */
    private ETeamPairing mTeamPairing = ETeamPairing.TEAM_PAIRING;

    /**
     *
     */
    private EIndivPairing mTeamIndivPairing = EIndivPairing.RANKING;

    /**
     *
     */
    private int mTeamMatesNumber = 1;

    /**
     *
     */
    private int mClansMembersNumber = 1;

    /**
     *
     */
    private boolean mIndivPairingTeamBalanced = false;

    /**
     *
     */
    private boolean mIndivPairingIndivBalanced = false;

    /**
     *
     */
    private int mPointsTeamVictory = 1000;

    /**
     *
     */
    private int mPointsTeamDraw = 400;

    /**
     *
     */
    private int mPointsTeamLost = 0;

    /**
     *
     */
    private int mRankingTeam1 = C_RANKING_POINTS;

    /**
     *
     */
    private int mRankingTeam2 = C_RANKING_OPP_POINTS;

    /**
     *
     */
    private int mRankingTeam3 = C_RANKING_NONE;

    /**
     *
     */
    private int mRankingTeam4 = C_RANKING_NONE;

    /**
     *
     */
    private int mRankingTeam5 = C_RANKING_NONE;

    /**
     *
     */
    private int mPointsTeamVictoryBonus = 0;

    /**
     *
     */
    private int mPointsTeamDrawBonus = 0;
    /**
     * *********************
     * Roster group management *********************
     */
    private boolean mGroupsEnable = false;
    /**
     * *********************
     * Clan management *********************
     */
    /**
     * Activate clan management
     */
    private boolean mEnableClans = false;
    /**
     * Avoid match between members of a clan
     */
    private boolean mAvoidClansFirstMatch = true;
    /**
     * Avoid match between members of the same clan
     */
    private boolean mAvoidClansMatch = false;
    /**
     * Number of player of one clan used for clan ranking
     */
    private int mTeamMatesClansNumber = 3;

    /**
     * Use the color to displlay Match/rank
     */
    private boolean useColor = true;

    /**
     *
     */
    private boolean useImage = false;

    /**
     * Parameters defautl constructor
     */
    public Parameters() {
        mTournamentName = sbundle.getString("");
        mTournamentOrga = sbundle.getString("");
        mCriterias = new ArrayList<>();

        Criteria c = new Criteria(sbundle.getString("Touchdowns"));
        c.setPointsFor(2);
        mCriterias.add(c);
        c = new Criteria(sbundle.getString("Injuries"));
        c.setPointsFor(1);
        mCriterias.add(c);
    }

    /**
     *
     * @return
     */
    @Override
    public Element getXMLElement() {
        final SimpleDateFormat format = new SimpleDateFormat(sbundle.getString("DD/MM/YYYY HH:MM:SS"), Locale.getDefault());
        final Element params = new Element(sbundle.getString("PARAMETERS"));
        params.setAttribute(sbundle.getString("ORGANIZER"), this.getTournamentOrga());
        params.setAttribute(sbundle.getString("NAME"), this.getTournamentName());
        params.setAttribute(sbundle.getString("DATE"), getStringDate(format));
        params.setAttribute(sbundle.getString("PLACE"), this.getTournamentName());

        for (int i = 0; i < this.getCriteriaCount(); i++) {
            final Element crit = getCriteria(i).getXMLElement();
            params.addContent(crit);
        }

        params.setAttribute(sbundle.getString("VICTORY"), Integer.toString(this.getPointsIndivVictory()));
        params.setAttribute(sbundle.getString("LARGE_VICTORY"), Integer.toString(this.getPointsIndivLargeVictory()));
        params.setAttribute(sbundle.getString("DRAW"), Integer.toString(this.getPointsIndivDraw()));
        params.setAttribute(sbundle.getString("LOST"), Integer.toString(this.getPointsIndivLost()));
        try {
            params.setAttribute("Refused", Integer.toString(this.getPointsRefused()));
            params.setAttribute("Conceeded", Integer.toString(this.getPointsConcedeed()));
        } catch (Exception e) {
            LOG.log(Level.FINE,e.getLocalizedMessage());
        }
        params.setAttribute(sbundle.getString("LITTLE_LOST"), Integer.toString(this.getPointsIndivLittleLost()));
        params.setAttribute("Portugal", Boolean.toString(this.isPortugal()));

        params.setAttribute(sbundle.getString("VICTORY_TEAM"), Integer.toString(this.getPointsTeamVictory()));
        params.setAttribute(sbundle.getString("DRAW_TEAM"), Integer.toString(this.getPointsTeamDraw()));
        params.setAttribute(sbundle.getString("LOST_TEAM"), Integer.toString(this.getPointsTeamLost()));

        params.setAttribute(sbundle.getString("LARGE_VICTORY_GAP"), Integer.toString(this.getGapLargeVictory()));
        params.setAttribute(sbundle.getString("LITTLE_LOST_GAP"), Integer.toString(this.getGapLittleLost()));

        params.setAttribute(sbundle.getString("RANK1"), Integer.toString(this.getRankingIndiv1()));
        params.setAttribute(sbundle.getString("RANK2"), Integer.toString(this.getRankingIndiv2()));
        params.setAttribute(sbundle.getString("RANK3"), Integer.toString(this.getRankingIndiv3()));
        params.setAttribute(sbundle.getString("RANK4"), Integer.toString(this.getRankingIndiv4()));
        params.setAttribute(sbundle.getString("RANK5"), Integer.toString(this.getRankingIndiv5()));

        params.setAttribute(sbundle.getString("RANK1_TEAM"), Integer.toString(this.getRankingTeam1()));
        params.setAttribute(sbundle.getString("RANK2_TEAM"), Integer.toString(this.gemRankingTeam2()));
        params.setAttribute(sbundle.getString("RANK3_TEAM"), Integer.toString(this.getRankingTeam3()));
        params.setAttribute(sbundle.getString("RANK4_TEAM"), Integer.toString(this.getRankingTeam4()));
        params.setAttribute(sbundle.getString("RANK5_TEAM"), Integer.toString(this.getRankingTeam5()));

        params.setAttribute(sbundle.getString("BYTEAM"), Boolean.toString(this.isTeamTournament()));
        params.setAttribute(sbundle.getString("TEAMMATES"), Integer.toString(this.getTeamMatesNumber()));
        switch (this.getTeamPairing()) {
            case INDIVIDUAL_PAIRING:
                params.setAttribute(sbundle.getString("TEAMPAIRING"), "0");
                break;
            case TEAM_PAIRING:
                params.setAttribute(sbundle.getString("TEAMPAIRING"), "1");
                break;
        }

        switch (this.getTeamIndivPairing()) {
            case RANKING:
                params.setAttribute(sbundle.getString("TEAMINDIVPAIRING"), "0");
                break;
            case FREE:
                params.setAttribute(sbundle.getString("TEAMINDIVPAIRING"), "1");
                break;
            case RANDOM:
                params.setAttribute(sbundle.getString("TEAMINDIVPAIRING"), "2");
                break;
            case NAF:
                params.setAttribute(sbundle.getString("TEAMINDIVPAIRING"), "3");
                break;
        }

        params.setAttribute(sbundle.getString("TEAMVICTORYPOINTS"), Integer.toString(this.getPointsTeamVictoryBonus()));
        params.setAttribute(sbundle.getString("TEAMDRAWPOINTS"), Integer.toString(this.getPointsTeamDrawBonus()));
        params.setAttribute(sbundle.getString("TEAMVICTORYONLY"), Boolean.toString(this.isTeamVictoryOnly()));

        params.setAttribute(sbundle.getString("GROUPENABLE"), Boolean.toString(this.isGroupsEnable()));
        params.setAttribute(sbundle.getString("SUBSTITUTES"), Boolean.toString(this.isSubstitutes()));
        params.setAttribute(sbundle.getString("GAMETYPE"), Integer.toString(this.getGame()));

        params.setAttribute(sbundle.getString("ACTVATECLANS"), Boolean.toString(this.isEnableClans()));
        params.setAttribute(sbundle.getString("AVOIDFIRSTMATCH"), Boolean.toString(this.isAvoidClansFirstMatch()));
        params.setAttribute(sbundle.getString("AVOIDMATCH"), Boolean.toString(this.isAvoidClansMatch()));

        params.setAttribute(sbundle.getString("CLANTEAMMATESNUMBER"), Integer.toString(this.getTeamMatesClansNumber()));

        params.setAttribute(sbundle.getString("MULTIROSTER"), Boolean.toString(this.isMultiRoster()));

        params.setAttribute(sbundle.getString("INDIVBALANCED"), Boolean.toString(this.isIndivPairingIndivBalanced()));
        params.setAttribute(sbundle.getString("TEAMBALANCED"), Boolean.toString(this.isIndivPairingTeamBalanced()));

        params.setAttribute("Portugal", Boolean.toString(this.isPortugal()));
        params.setAttribute("Color", Boolean.toString(this.isUseColor()));
        params.setAttribute("Image", Boolean.toString(this.isUseImage()));
        
        return params;
    }

    /**
     *
     * @param params
     */
    @Override
    public void setXMLElement(final Element params) {
        final SimpleDateFormat format = new SimpleDateFormat(sbundle.getString("DD/MM/YYYY HH:MM:SS"), Locale.getDefault());

        try {
            this.semTournamentOrga(params.getAttribute(sbundle.getString("ORGANIZER")).getValue());
            this.setTournamentName(params.getAttribute(sbundle.getString("NAME")).getValue());

            this.setPointsIndivVictory(params.getAttribute(sbundle.getString("VICTORY")).getIntValue());
            this.setPointsIndivLargeVictory(params.getAttribute(sbundle.getString("LARGE_VICTORY")).getIntValue());
            this.setPointsIndivDraw(params.getAttribute(sbundle.getString("DRAW")).getIntValue());
            this.setPointsIndivLost(params.getAttribute(sbundle.getString("LOST")).getIntValue());
            try {
                this.setPointsRefused(params.getAttribute("Refused").getIntValue());
                this.setPointsConcedeed(params.getAttribute("Concedeed").getIntValue());
            } catch (DataConversionException e) {
                LOG.log(Level.FINE,e.getLocalizedMessage());
            }
             catch (NullPointerException e) {
                this.setPointsRefused(0);
                this.setPointsConcedeed(0);
            }
            this.setPointsIndivLittleLost(params.getAttribute(sbundle.getString("LITTLE_LOST")).getIntValue());

            Attribute r1 = params.getAttribute("Rank1");
            this.setRankingIndiv1((r1.getIntValue()));
            this.setRankingIndiv2((params.getAttribute("Rank2").getIntValue()));
            this.setRankingIndiv3((params.getAttribute("Rank3").getIntValue()));
            this.setRankingIndiv4((params.getAttribute("Rank4").getIntValue()));
            this.setRankingIndiv5((params.getAttribute("Rank5").getIntValue()));

            try {
                this.setGapLargeVictory(params.getAttribute(sbundle.getString("LARGE_VICTORY_GAP")).getIntValue());
                this.setGapLittleLost(params.getAttribute(sbundle.getString("LITTLE_LOST_GAP")).getIntValue());
                this.setPlace(params.getAttribute(sbundle.getString("PLACE")).getValue());
                this.setTeamTournament(params.getAttribute(sbundle.getString("BYTEAM")).getBooleanValue());

                this.setTeamMatesNumber(params.getAttribute(sbundle.getString("TEAMMATES")).getIntValue());
                int val = params.getAttribute(sbundle.getString("TEAMPAIRING")).getIntValue();
                switch (val) {
                    case (0):
                        this.setTeamPairing(ETeamPairing.INDIVIDUAL_PAIRING);
                        break;
                    case (1):
                        this.setTeamPairing(ETeamPairing.TEAM_PAIRING);
                        break;
                    default:
                        this.setTeamPairing(ETeamPairing.TEAM_PAIRING);
                        break;
                }

                this.setPointsTeamVictoryBonus(params.getAttribute(sbundle.getString("TEAMVICTORYPOINTS")).getIntValue());
                val = params.getAttribute(sbundle.getString("TEAMINDIVPAIRING")).getIntValue();
                switch (val) {
                    case (0):
                        this.setTeamIndivPairing(EIndivPairing.RANKING);
                        break;
                    case (1):
                        this.setTeamIndivPairing(EIndivPairing.FREE);
                        break;
                    case (2):
                        this.setTeamIndivPairing(EIndivPairing.RANDOM);
                        break;
                    case (3):
                        this.setTeamIndivPairing(EIndivPairing.NAF);
                        break;
                    default:
                        this.setTeamIndivPairing(EIndivPairing.RANKING);
                        break;
                        
                        
                }
                this.setTeamVictoryOnly(params.getAttribute(sbundle.getString("TEAMVICTORYONLY")).getBooleanValue());
                try {
                    this.setPointsTeamDrawBonus(params.getAttribute(sbundle.getString("TEAMDRAWPOINTS")).getIntValue());
                } catch (DataConversionException e) {
                    this.setPointsTeamDrawBonus(0);
                    LOG.log(Level.FINE,e.getLocalizedMessage());
                }

                try {
                    this.setDate(format.parse(params.getAttribute(sbundle.getString("DATE")).getValue()));
                } catch (ParseException pe) {
                }

                try {
                    this.setGame(params.getAttribute(sbundle.getString("GAMETYPE")).getIntValue());
                } catch (DataConversionException pe) {
                    this.setGame(1);
                    LOG.log(Level.FINE,pe.getLocalizedMessage());
                }

                this.setGroupsEnable(params.getAttribute(sbundle.getString("GROUPENABLE")).getBooleanValue());

            } catch (NullPointerException ne) {
                this.setGapLargeVictory(3);
                this.setGapLittleLost(1);
                this.setPlace(sbundle.getString(""));
                this.setTeamTournament(false);
                this.setTeamMatesNumber(6);
                this.setTeamPairing(ETeamPairing.TEAM_PAIRING);
                this.setTeamIndivPairing(EIndivPairing.RANKING);
            }
            try {
                this.setPointsTeamVictory(params.getAttribute(sbundle.getString("VICTORY_TEAM")).getIntValue());
                this.setPointsTeamDraw(params.getAttribute(sbundle.getString("DRAW_TEAM")).getIntValue());
                this.setPointsTeamLost(params.getAttribute(sbundle.getString("LOST_TEAM")).getIntValue());
                this.setRankingTeam1(params.getAttribute(sbundle.getString("RANK1_TEAM")).getIntValue());
                this.setRankingTeam2(params.getAttribute(sbundle.getString("RANK2_TEAM")).getIntValue());
                this.setRankingTeam3(params.getAttribute(sbundle.getString("RANK3_TEAM")).getIntValue());
                this.setRankingTeam4(params.getAttribute(sbundle.getString("RANK4_TEAM")).getIntValue());
                this.setRankingTeam5(params.getAttribute(sbundle.getString("RANK5_TEAM")).getIntValue());
            } catch (NullPointerException ne2) {
                JOptionPane.showMessageDialog(null, ne2.getLocalizedMessage());
            }

            try {
                this.setEnableClans(params.getAttribute(sbundle.getString("ACTVATECLANS")).getBooleanValue());
                this.setAvoidClansFirstMatch(params.getAttribute(sbundle.getString("AVOIDFIRSTMATCH")).getBooleanValue());
                this.setAvoidClansMatch(params.getAttribute(sbundle.getString("AVOIDMATCH")).getBooleanValue());
                this.setSubstitutes(params.getAttribute(sbundle.getString("SUBSTITUTES")).getBooleanValue());
                this.setMultiRoster(params.getAttribute(sbundle.getString("MULTIROSTER")).getBooleanValue());
                this.setPortugal(params.getAttribute("Portugal").getBooleanValue());
                this.setUseColor(params.getAttribute("Color").getBooleanValue());
                this.setUseImage(params.getAttribute("Image").getBooleanValue());

            } catch (NullPointerException ne3) {
                //JOptionPane.showMessageDialog(null, ne3.getLocalizedMessage());
            }
            try {
                this.setIndivPairingIndivBalanced(params.getAttribute(sbundle.getString("INDIVBALANCED")).getBooleanValue());
                this.setIndivPairingTeamBalanced(params.getAttribute(sbundle.getString("TEAMBALANCED")).getBooleanValue());

                this.setClansMembersNumber(params.getAttribute(sbundle.getString("CLANTEAMMATESNUMBER")).getIntValue());

            } catch (NullPointerException ne4) {
                //JOptionPane.showMessageDialog(null, ne4.getLocalizedMessage());
            }

            final List criterias = params.getChildren(sbundle.getString("CRITERIA"));
            final Iterator cr = criterias.iterator();

            this.clearCiterias();

            while (cr.hasNext()) {
                final Element criteria = (Element) cr.next();
                final Criteria crit = new Criteria(criteria.getAttributeValue(sbundle.getString("NAME")));
                crit.setXMLElement(criteria);
                this.addCriteria(crit);
            }
        } catch (DataConversionException dce) {
            JOptionPane.showMessageDialog(null, dce.getLocalizedMessage());
        }
    }

    @Override
    public String toString() {
        return sbundle.getString("PARAMÈTRES");
    }

    /**
     *
     * @param j
     * @return
     */
    public int getTeamRankingType(int j) {
        int rank ;
        switch (j) {
            case 0:
                rank = getRankingTeam1();
                break;
            case 1:
                rank = gemRankingTeam2();
                break;
            case 2:
                rank = getRankingTeam3();
                break;
            case 3:
                rank = getRankingTeam4();
                break;

            case 4:
                rank = getRankingTeam5();
                break;
            default:
                rank = C_RANKING_NONE;
                break;

        }
        return rank;
    }

    /**
     *
     * @param j
     * @return
     */
    public int getIndivRankingType(int j) {
        int rank;
        switch (j) {
            case 0:
                rank = getRankingIndiv1();
                break;
            case 1:
                rank = getRankingIndiv2();
                break;
            case 2:
                rank = getRankingIndiv3();
                break;
            case 3:
                rank = getRankingIndiv4();
                break;

            case 4:
                rank = getRankingIndiv5();
                break;
            default:
                rank = C_RANKING_NONE;
                break;

        }
        return rank;
    }

    /**
     *
     * @return
     */
    public int getIndivRankingNumber() {
        int nb = 0;
        if (getRankingIndiv1() != C_RANKING_NONE) {
            nb++;
            if (getRankingIndiv2() != C_RANKING_NONE) {
                nb++;
                if (getRankingIndiv3() != C_RANKING_NONE) {
                    nb++;
                    if (getRankingIndiv4() != C_RANKING_NONE) {
                        nb++;
                        if (getRankingIndiv5() != C_RANKING_NONE) {
                            nb++;
                        }
                    }
                }
            }
        }
        return nb;
    }

    /**
     *
     * @return
     */
    public int getTeamRankingNumber() {
        int nb = 0;
        if (getRankingTeam1() != C_RANKING_NONE) {
            nb++;
            if (gemRankingTeam2() != C_RANKING_NONE) {
                nb++;
                if (getRankingTeam3() != C_RANKING_NONE) {
                    nb++;
                    if (getRankingTeam4() != C_RANKING_NONE) {
                        nb++;
                        if (getRankingTeam5() != C_RANKING_NONE) {
                            nb++;
                        }
                    }
                }
            }
        }
        return nb;
    }

    /**
     * @return the mCriterias
     */
    public int getCriteriaCount() {
        return mCriterias.size();
    }

    /**
     * @param i
     * @return the mCriterias
     */
    public Criteria getCriteria(int i) {
        return mCriterias.get(i);
    }

    /**
     * Clear the criterias array
     */
    public void clearCiterias() {
        mCriterias.clear();
    }

    /**
     *
     * @param c
     */
    public void addCriteria(Criteria c) {
        mCriterias.add(c);
    }

    /**
     *
     * @param c
     */
    public void removeCriteria(int c) {
        mCriterias.remove(c);
    }

    /**
     * @return the mGame
     */
    public int getGame() {
        return mGame;
    }

    /**
     * @param mGame the mGame to set
     */
    public void setGame(int mGame) {
        this.mGame = mGame;
    }

    /**
     * @return the mPointsIndivVictory
     */
    public int getPointsIndivVictory() {
        return mPointsIndivVictory;
    }

    /**
     * @param mPointsIndivVictory the mPointsIndivVictory to set
     */
    public void setPointsIndivVictory(int mPointsIndivVictory) {
        this.mPointsIndivVictory = mPointsIndivVictory;
    }

    /**
     * @return the mPointsIndivLargeVictory
     */
    public int getPointsIndivLargeVictory() {
        return mPointsIndivLargeVictory;
    }

    /**
     * @param mPointsIndivLargeVictory the mPointsIndivLargeVictory to set
     */
    public void setPointsIndivLargeVictory(int mPointsIndivLargeVictory) {
        this.mPointsIndivLargeVictory = mPointsIndivLargeVictory;
    }

    /**
     * @return the mPointsIndivDraw
     */
    public int getPointsIndivDraw() {
        return mPointsIndivDraw;
    }

    /**
     * @param mPointsIndivDraw the mPointsIndivDraw to set
     */
    public void setPointsIndivDraw(int mPointsIndivDraw) {
        this.mPointsIndivDraw = mPointsIndivDraw;
    }

    /**
     * @return the mPointsIndivLittleLost
     */
    public int getPointsIndivLittleLost() {
        return mPointsIndivLittleLost;
    }

    /**
     * @param mPointsIndivLittleLost the mPointsIndivLittleLost to set
     */
    public void setPointsIndivLittleLost(int mPointsIndivLittleLost) {
        this.mPointsIndivLittleLost = mPointsIndivLittleLost;
    }

    /**
     * @return the mPointsIndivLost
     */
    public int getPointsIndivLost() {
        return mPointsIndivLost;
    }

    /**
     * @param mPointsIndivLost the mPointsIndivLost to set
     */
    public void setPointsIndivLost(int mPointsIndivLost) {
        this.mPointsIndivLost = mPointsIndivLost;
    }

    /**
     * @return the mPointsRefused
     */
    public int getPointsRefused() {
        return mPointsRefused;
    }

    /**
     * @param mPointsRefused the mPointsRefused to set
     */
    public void setPointsRefused(int mPointsRefused) {
        this.mPointsRefused = mPointsRefused;
    }

    /**
     * @return the mPointsConcedeed
     */
    public int getPointsConcedeed() {
        return mPointsConcedeed;
    }

    /**
     * @param mPointsConcedeed the mPointsConcedeed to set
     */
    public void setPointsConcedeed(int mPointsConcedeed) {
        this.mPointsConcedeed = mPointsConcedeed;
    }

    /**
     * @return the mTeamVictoryOnly
     */
    public boolean isTeamVictoryOnly() {
        return mTeamVictoryOnly;
    }

    /**
     * @param mTeamVictoryOnly the mTeamVictoryOnly to set
     */
    public void setTeamVictoryOnly(boolean mTeamVictoryOnly) {
        this.mTeamVictoryOnly = mTeamVictoryOnly;
    }

    /**
     * @return the mPortugal
     */
    public boolean isPortugal() {
        return mPortugal;
    }

    /**
     * @param mPortugal the mPortugal to set
     */
    public void setPortugal(boolean mPortugal) {
        this.mPortugal = mPortugal;
    }

    /**
     * @return the mGapLargeVictory
     */
    public int getGapLargeVictory() {
        return mGapLargeVictory;
    }

    /**
     * @param mGapLargeVictory the mGapLargeVictory to set
     */
    public void setGapLargeVictory(int mGapLargeVictory) {
        this.mGapLargeVictory = mGapLargeVictory;
    }

    /**
     * @return the mGapLittleLost
     */
    public int getGapLittleLost() {
        return mGapLittleLost;
    }

    /**
     * @param mGapLittleLost the mGapLittleLost to set
     */
    public void setGapLittleLost(int mGapLittleLost) {
        this.mGapLittleLost = mGapLittleLost;
    }

    /**
     * @return the mSubstitutes
     */
    public boolean isSubstitutes() {
        return mSubstitutes;
    }

    /**
     * @param mSubstitutes the mSubstitutes to set
     */
    public void setSubstitutes(boolean mSubstitutes) {
        this.mSubstitutes = mSubstitutes;
    }

    /**
     * @return the mTournamentName
     */
    public String getTournamentName() {
        return mTournamentName;
    }

    /**
     * @param mTournamentName the mTournamentName to set
     */
    public void setTournamentName(String mTournamentName) {
        this.mTournamentName = mTournamentName;
    }

    /**
     * @return the mTournamentOrga
     */
    public String getTournamentOrga() {
        return mTournamentOrga;
    }

    /**
     * @param mTournamentOrga the mTournamentOrga to set
     */
    public void semTournamentOrga(String mTournamentOrga) {
        this.mTournamentOrga = mTournamentOrga;
    }

    /**
     * @return the mPlace
     */
    public String getPlace() {
        return mPlace;
    }

    /**
     * @param mPlace the mPlace to set
     */
    public void setPlace(String mPlace) {
        this.mPlace = mPlace;
    }

 
    /**
     * 
     * @param format
     * @return 
     */
    public String getStringDate(SimpleDateFormat format)
    {
        return format.format(mDate);
    }

       /**
     * 
     * @return 
     */
    public long getDateTime()
    {
        return mDate.getTime();
    }
    
    /**
     * @param mDate the mDate to set
     */
    public void setDate(Date mDate) {
        this.mDate.setTime(mDate.getTime());
    }

    /**
     * @return the mRankingIndiv1
     */
    public int getRankingIndiv1() {
        return mRankingIndiv1;
    }

    /**
     * @param mRankingIndiv1 the mRankingIndiv1 to set
     */
    public void setRankingIndiv1(int mRankingIndiv1) {
        this.mRankingIndiv1 = mRankingIndiv1;
    }

    /**
     * @return the mRankingIndiv2
     */
    public int getRankingIndiv2() {
        return mRankingIndiv2;
    }

    /**
     * @param mRankingIndiv2 the mRankingIndiv2 to set
     */
    public void setRankingIndiv2(int mRankingIndiv2) {
        this.mRankingIndiv2 = mRankingIndiv2;
    }

    /**
     * @return the mRankingIndiv3
     */
    public int getRankingIndiv3() {
        return mRankingIndiv3;
    }

    /**
     * @param mRankingIndiv3 the mRankingIndiv3 to set
     */
    public void setRankingIndiv3(int mRankingIndiv3) {
        this.mRankingIndiv3 = mRankingIndiv3;
    }

    /**
     * @return the mRankingIndiv4
     */
    public int getRankingIndiv4() {
        return mRankingIndiv4;
    }

    /**
     * @param mRankingIndiv4 the mRankingIndiv4 to set
     */
    public void setRankingIndiv4(int mRankingIndiv4) {
        this.mRankingIndiv4 = mRankingIndiv4;
    }

    /**
     * @return the mRankingIndiv5
     */
    public int getRankingIndiv5() {
        return mRankingIndiv5;
    }

    /**
     * @param mRankingIndiv5 the mRankingIndiv5 to set
     */
    public void setRankingIndiv5(int mRankingIndiv5) {
        this.mRankingIndiv5 = mRankingIndiv5;
    }

    /**
     * @return the mTeamTournament
     */
    public boolean isTeamTournament() {
        return mTeamTournament;
    }

    /**
     * @param mTeamTournament the mTeamTournament to set
     */
    public void setTeamTournament(boolean mTeamTournament) {
        this.mTeamTournament = mTeamTournament;
    }

    /**
     * @return the mMultiRoster
     */
    public boolean isMultiRoster() {
        return mMultiRoster;
    }

    /**
     * @param mMultiRoster the mMultiRoster to set
     */
    public void setMultiRoster(boolean mMultiRoster) {
        this.mMultiRoster = mMultiRoster;
    }

    /**
     * @return the mTeamPairing
     */
    public ETeamPairing getTeamPairing() {
        return mTeamPairing;
    }

    /**
     * @param mTeamPairing the mTeamPairing to set
     */
    public void setTeamPairing(ETeamPairing mTeamPairing) {
        this.mTeamPairing = mTeamPairing;
    }

    /**
     * @return the mTeamIndivPairing
     */
    public EIndivPairing getTeamIndivPairing() {
        return mTeamIndivPairing;
    }

    /**
     * @param mTeamIndivPairing the mTeamIndivPairing to set
     */
    public void setTeamIndivPairing(EIndivPairing mTeamIndivPairing) {
        this.mTeamIndivPairing = mTeamIndivPairing;
    }

    /**
     * @return the mTeamMatesNumber
     */
    public int getTeamMatesNumber() {
        return mTeamMatesNumber;
    }

    /**
     * @param mTeamMatesNumber the mTeamMatesNumber to set
     */
    public void setTeamMatesNumber(int mTeamMatesNumber) {
        this.mTeamMatesNumber = mTeamMatesNumber;
    }

    /**
     * @return the mClansMembersNumber
     */
    public int getClansMembersNumber() {
        return mClansMembersNumber;
    }

    /**
     * @param mClansMembersNumber the mClansMembersNumber to set
     */
    public void setClansMembersNumber(int mClansMembersNumber) {
        this.mClansMembersNumber = mClansMembersNumber;
    }

    /**
     * @return the mIndivPairingTeamBalanced
     */
    public boolean isIndivPairingTeamBalanced() {
        return mIndivPairingTeamBalanced;
    }

    /**
     * @param mIndivPairingTeamBalanced the mIndivPairingTeamBalanced to set
     */
    public void setIndivPairingTeamBalanced(boolean mIndivPairingTeamBalanced) {
        this.mIndivPairingTeamBalanced = mIndivPairingTeamBalanced;
    }

    /**
     * @return the mIndivPairingIndivBalanced
     */
    public boolean isIndivPairingIndivBalanced() {
        return mIndivPairingIndivBalanced;
    }

    /**
     * @param mIndivPairingIndivBalanced the mIndivPairingIndivBalanced to set
     */
    public void setIndivPairingIndivBalanced(boolean mIndivPairingIndivBalanced) {
        this.mIndivPairingIndivBalanced = mIndivPairingIndivBalanced;
    }

    /**
     * @return the mPointsTeamVictory
     */
    public int getPointsTeamVictory() {
        return mPointsTeamVictory;
    }

    /**
     * @param mPointsTeamVictory the mPointsTeamVictory to set
     */
    public void setPointsTeamVictory(int mPointsTeamVictory) {
        this.mPointsTeamVictory = mPointsTeamVictory;
    }

    /**
     * @return the mPointsTeamDraw
     */
    public int getPointsTeamDraw() {
        return mPointsTeamDraw;
    }

    /**
     * @param mPointsTeamDraw the mPointsTeamDraw to set
     */
    public void setPointsTeamDraw(int mPointsTeamDraw) {
        this.mPointsTeamDraw = mPointsTeamDraw;
    }

    /**
     * @return the mPointsTeamLost
     */
    public int getPointsTeamLost() {
        return mPointsTeamLost;
    }

    /**
     * @param mPointsTeamLost the mPointsTeamLost to set
     */
    public void setPointsTeamLost(int mPointsTeamLost) {
        this.mPointsTeamLost = mPointsTeamLost;
    }

    /**
     * @return the mRankingTeam1
     */
    public int getRankingTeam1() {
        return mRankingTeam1;
    }
    
    public int getRankingTeam2() {
        return mRankingTeam2;
    }

    /**
     * @param mRankingTeam1 the mRankingTeam1 to set
     */
    public void setRankingTeam1(int mRankingTeam1) {
        this.mRankingTeam1 = mRankingTeam1;
    }

    /**
     * @return the mRankingTeam2
     */
    public int gemRankingTeam2() {
        return mRankingTeam2;
    }

    /**
     * @param mRankingTeam2 the mRankingTeam2 to set
     */
    public void setRankingTeam2(int mRankingTeam2) {
        this.mRankingTeam2 = mRankingTeam2;
    }

    /**
     * @return the mRankingTeam3
     */
    public int getRankingTeam3() {
        return mRankingTeam3;
    }

    /**
     * @param mRankingTeam3 the mRankingTeam3 to set
     */
    public void setRankingTeam3(int mRankingTeam3) {
        this.mRankingTeam3 = mRankingTeam3;
    }

    /**
     * @return the mRankingTeam4
     */
    public int getRankingTeam4() {
        return mRankingTeam4;
    }

    /**
     * @param mRankingTeam4 the mRankingTeam4 to set
     */
    public void setRankingTeam4(int mRankingTeam4) {
        this.mRankingTeam4 = mRankingTeam4;
    }

    /**
     * @return the mRankingTeam5
     */
    public int getRankingTeam5() {
        return mRankingTeam5;
    }

    /**
     * @param mRankingTeam5 the mRankingTeam5 to set
     */
    public void setRankingTeam5(int mRankingTeam5) {
        this.mRankingTeam5 = mRankingTeam5;
    }

    /**
     * @return the mPointsTeamVictoryBonus
     */
    public int getPointsTeamVictoryBonus() {
        return mPointsTeamVictoryBonus;
    }

    /**
     * @param mPointsTeamVictoryBonus the mPointsTeamVictoryBonus to set
     */
    public void setPointsTeamVictoryBonus(int mPointsTeamVictoryBonus) {
        this.mPointsTeamVictoryBonus = mPointsTeamVictoryBonus;
    }

    /**
     * @return the mPointsTeamDrawBonus
     */
    public int getPointsTeamDrawBonus() {
        return mPointsTeamDrawBonus;
    }

    /**
     * @param mPointsTeamDrawBonus the mPointsTeamDrawBonus to set
     */
    public void setPointsTeamDrawBonus(int mPointsTeamDrawBonus) {
        this.mPointsTeamDrawBonus = mPointsTeamDrawBonus;
    }

    /**
     * @return the mGroupsEnable
     */
    public boolean isGroupsEnable() {
        return mGroupsEnable;
    }

    /**
     * @param mGroupsEnable the mGroupsEnable to set
     */
    public void setGroupsEnable(boolean mGroupsEnable) {
        this.mGroupsEnable = mGroupsEnable;
    }

    /**
     * @return the mEnableClans
     */
    public boolean isEnableClans() {
        return mEnableClans;
    }

    /**
     * @param mEnableClans the mEnableClans to set
     */
    public void setEnableClans(boolean mEnableClans) {
        this.mEnableClans = mEnableClans;
    }

    /**
     * @return the mAvoidClansFirstMatch
     */
    public boolean isAvoidClansFirstMatch() {
        return mAvoidClansFirstMatch;
    }

    /**
     * @param mAvoidClansFirstMatch the mAvoidClansFirstMatch to set
     */
    public void setAvoidClansFirstMatch(boolean mAvoidClansFirstMatch) {
        this.mAvoidClansFirstMatch = mAvoidClansFirstMatch;
    }

    /**
     * @return the mAvoidClansMatch
     */
    public boolean isAvoidClansMatch() {
        return mAvoidClansMatch;
    }

    /**
     * @param mAvoidClansMatch the mAvoidClansMatch to set
     */
    public void setAvoidClansMatch(boolean mAvoidClansMatch) {
        this.mAvoidClansMatch = mAvoidClansMatch;
    }

    /**
     * @return the mTeamMatesClansNumber
     */
    public int getTeamMatesClansNumber() {
        return mTeamMatesClansNumber;
    }

    /**
     * @param mTeamMatesClansNumber the mTeamMatesClansNumber to set
     */
    public void setTeamMatesClansNumber(int mTeamMatesClansNumber) {
        this.mTeamMatesClansNumber = mTeamMatesClansNumber;
    }

    /**
     * @return the useColor
     */
    public boolean isUseColor() {
        return useColor;
    }

    /**
     * @param useColor the useColor to set
     */
    public void setUseColor(boolean useColor) {
        this.useColor = useColor;
    }

    /**
     * @return the useImage
     */
    public boolean isUseImage() {
        return useImage;
    }

    /**
     * @param useImage the useImage to set
     */
    public void setUseImage(boolean useImage) {
        this.useImage = useImage;
    }



}
