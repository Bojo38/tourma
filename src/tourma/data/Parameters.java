/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import java.awt.Color;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.jdom.Attribute;
import org.jdom.DataConversionException;
import org.jdom.Element;
import tourma.languages.Translate;
import static tourma.languages.Translate.CS_Injuries;
import static tourma.languages.Translate.CS_Touchdowns;
import tourma.utility.StringConstants;

/**
 *
 * @author Frederic Berger
 */
public class Parameters implements IXMLExport, Serializable {

    protected static AtomicInteger sGenUID = new AtomicInteger(0);
    protected int UID = sGenUID.incrementAndGet();

    public int getUID() {
        return UID;
    }

    public void setUID(int UID) {
        this.UID = UID;
    }

    public Criteria getCriteria(String name) {
        for (Criteria criteria : mCriterias) {
            if (criteria.getName().equals(name) || criteria.getAccronym().equals(name)) {
                return criteria;
            }
        }
        return null;
    }
    
     public Formula getFormula(String name) {
        for (Formula formula : mFormulas) {
            if (formula.getName().equals(name) ) {
                return formula;
            }
        }
        return null;
    }

    boolean mDisplayRoster = true;

    public boolean isDisplayRoster() {
        return mDisplayRoster;
    }

    public void setDisplayRoster(boolean d) {
        mDisplayRoster = d;
    }

    public void pull(Parameters params) {
        this.UID = params.UID;
        this._webport = params._webport;
        this.mApplyToAnnexIndiv = params.mApplyToAnnexIndiv;
        this.mApplyToAnnexTeam = params.mApplyToAnnexTeam;
        this.mAvoidClansFirstMatch = params.mAvoidClansFirstMatch;
        this.mAvoidClansMatch = params.mAvoidClansMatch;
        this.mBestResultsIndiv = params.mBestResultsIndiv;
        this.mBestResultsTeam = params.mBestResultsTeam;
        this.mBorderColor = params.mBorderColor;
        this.mClansMembersNumber = params.mClansMembersNumber;
        this.mColor1 = params.mColor1;
        this.mColor2 = params.mColor2;

        //
        // loop to find crieria
        boolean bFound = false;
        for (int i = 0; i < params.getCriteriaCount(); i++) {
            bFound = false;
            Criteria crit = params.getCriteria(i);
            for (Criteria c : mCriterias) {
                if ((crit.getUID() == c.UID) || (crit.getName().equals(c.getName()))) {
                    bFound = true;
                    c.pull(crit);
                }
            }
            if (!bFound) {
                Criteria c = new Criteria(crit.getName());
                c.pull(crit);
                mCriterias.add(c);
            }
        }
        
          bFound = false;
        for (int i = 0; i < params.getFormulaCount(); i++) {
            bFound = false;
            Formula form = params.getFormula(i);
            for (Formula f : mFormulas) {
                if ((form.getUID() == f.UID) || (form.getName().equals(f.getName()))) {
                    bFound = true;
                    f.pull(form);
                }
            }
            if (!bFound) {
                Formula f = new Formula(form.getName());
                f.pull(form);
                mFormulas.add(f);
            }
        }

        this.mDate = params.mDate;
        this.mEnableClans = params.mEnableClans;
        this.mExceptBestAndWorstIndiv = params.mExceptBestAndWorstIndiv;
        this.mExceptBestAndWorstTeam = params.mExceptBestAndWorstTeam;
        this.mForeColor = params.mForeColor;

        this.mGapLargeVictory = params.mGapLargeVictory;
        this.mGapLittleLost = params.mGapLittleLost;
        this.mGroupsEnable = params.mGroupsEnable;
        this.mIndivPairingIndivBalanced = params.mIndivPairingIndivBalanced;
        this.mIndivPairingTeamBalanced = params.mIndivPairingTeamBalanced;
        this.mMultiRoster = params.mMultiRoster;
        this.mPlace = params.mPlace;
        this.mPointsConcedeed = params.mPointsConcedeed;
        this.mPointsIndivDraw = params.mPointsIndivDraw;
        this.mPointsIndivLargeVictory = params.mPointsIndivLargeVictory;
        this.mPointsIndivLittleLost = params.mPointsIndivLittleLost;
        this.mPointsIndivLost = params.mPointsIndivLost;
        this.mPointsIndivVictory = params.mPointsIndivVictory;
        this.mPointsRefused = params.mPointsRefused;
        this.mPointsTeamDraw = params.mPointsTeamDraw;
        this.mPointsTeamDrawBonus = params.mPointsTeamDrawBonus;
        this.mPointsTeamLost = params.mPointsTeamLost;
        this.mPointsTeamVictory = params.mPointsTeamVictory;
        this.mPointsTeamVictoryBonus = params.mPointsTeamVictoryBonus;
        this.mPortugal = params.mPortugal;

        this.mRankingIndiv1 = params.mRankingIndiv1;
        this.mRankingIndiv2 = params.mRankingIndiv2;
        this.mRankingIndiv3 = params.mRankingIndiv3;
        this.mRankingIndiv4 = params.mRankingIndiv4;
        this.mRankingIndiv5 = params.mRankingIndiv5;

        this.mRankingTeam1 = params.mRankingTeam1;
        this.mRankingTeam2 = params.mRankingTeam2;
        this.mRankingTeam3 = params.mRankingTeam3;
        this.mRankingTeam4 = params.mRankingTeam4;
        this.mRankingTeam5 = params.mRankingTeam5;

        this.mSubstitutes = params.mSubstitutes;
        this.mTableBonus = params.mTableBonus;
        this.mTableBonusCoef = params.mTableBonusCoef;
        this.mTableBonusPerRound = params.mTableBonusPerRound;
        this.mTeamIndivPairing = params.mTeamIndivPairing;
        this.mTeamMatesClansNumber = params.mTeamMatesClansNumber;
        this.mTeamMatesNumber = params.mTeamMatesNumber;
        this.mTeamPairing = params.mTeamPairing;
        this.mTeamTournament = params.mTeamTournament;
        this.mTeamVictoryOnly = params.mTeamVictoryOnly;
        this.mTournamentName = params.mTournamentName;
        this.mTournamentOrga = params.mTournamentOrga;

        this.mUseBestResultsIndiv = params.mUseBestResultsIndiv;
        this.mUseBestResultsTeam = params.mUseBestResultsTeam;
        this.mUseLargeVictory = params.mUseLargeVictory;
        this.mUseTeamHugeVictory = params.mUseTeamHugeVictory;
        this.mUseLittleLoss = params.mUseLittleLoss;

        this.mWebEdit = params.mWebEdit;

        this.mCrossPoolMatch = params.mCrossPoolMatch;

    }

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
    public static final int C_RANKING_OPP_POINTS_OTHER_MATCHS = 7;
    /**
     *
     */
    public static final int C_RANKING_TABLES = 8;
    /**
     *
     */
    public static final int C_RANKING_POINTS_WITHOUT_BONUS = 9;
    /**
     *
     */
    public static final int C_RANKING_BONUS_POINTS = 10;
    /**
     *
     */
    public static final int C_RANKING_HEAD_BY_HEAD = 11;
    /**
     *
     */
    public static final int C_RANKING_TIER = 12;

    /**
     *
     */
    public static final int C_RANKING_TEAMMATES_POINTS = 13;

    /**
     *
     */
    public static final int C_RANKING_TEAMMATES_VND = 14;

    /**
     *
     */
    public static final int C_MAX_RANKING = 14;
    /**
     *
     */
    private static final Logger LOG = Logger.getLogger(Parameters.class.getName());


    public boolean isCrossPoolMatch() {
        return mCrossPoolMatch;
    }

    /**
     *
     */
    public void setCrossPoolMatch(boolean crossPoolMatch) {
        this.mCrossPoolMatch = crossPoolMatch;
    }

    private int mPointsIndivVictory = 1000;

    private boolean mCrossPoolMatch = false;

    /**
     *
     */
    private int mPointsIndivLargeVictory = 1000;
    private int mPointsTeamLargeVictory = 1000;
    private int mPointsTeamHugeVictory = 1100;
    /**
     *
     */
    private boolean mUseLargeVictory = false;
    private boolean mUseTeamLargeVictory = false;

    private boolean mUseTeamHugeVictory = false;
    /**
     *
     */
    private boolean mUseLittleLoss = false;
    private boolean mUseTeamLittleLoss = false;
    private boolean mUseTeamHugeLoss = false;

    /**
     *
     */
    private int mPointsIndivDraw = 400;

    /**
     *
     */
    private int mPointsIndivLittleLost = 100;
    private int mPointsTeamLittleLost = 100;
    private int mPointsTeamHugeLost = 100;

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
    private float mGapTeamLargeVictory = 2;
    private float mGapTeamHugeVictory = 4;

    /**
     *
     */
    private int mGapLittleLost = 1;
    private float mGapTeamLittleLost = 1;
    private float mGapTeamHugeLost = 2;

    /**
     *
     */
    private boolean mSubstitutes = false;

    /**
     * Ranking criterias
     */
    private final ArrayList<Criteria> mCriterias;
    /**
     * Ranking Formulas
     */
    private final ArrayList<Formula> mFormulas;

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
    private Date mDate = new Date();

    /**
     *
     */
    private int mRankingIndiv1 = C_RANKING_POINTS;

    /**
     *
     */
    private int mRankingIndiv2 = C_RANKING_OPP_POINTS_OTHER_MATCHS;

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

    /**
     *
     */
    private boolean mTableBonus = false;

    /**
     *
     */
    private double mTableBonusCoef = 1.0;

    /**
     *
     */
    private boolean mTableBonusPerRound = false;


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
    private int mRankingTeam2 = C_RANKING_OPP_POINTS_OTHER_MATCHS;

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

    private boolean mUseBestResultsIndiv = false;
    private boolean mUseBestResultsTeam = false;
    private int mBestResultsIndiv = 5;
    private int mBestResultsTeam = 5;

    private boolean mExceptBestAndWorstIndiv = false;
    private boolean mApplyToAnnexIndiv = false;
    private boolean mExceptBestAndWorstTeam = false;
    private boolean mApplyToAnnexTeam = false;

    /**
     * Parameters defautl constructor
     */
    public Parameters() {
        mTournamentName = StringConstants.CS_NULL;
        mTournamentOrga = StringConstants.CS_NULL;
        mCriterias = new ArrayList<>();
         mFormulas = new ArrayList<>();

        Criteria c = new Criteria(Translate.translate(CS_Touchdowns));
        c.setPointsFor(2);
        mCriterias.add(c);
        c = new Criteria(Translate.translate(CS_Injuries));
        c.setPointsFor(1);
        mCriterias.add(c);
    }

    public boolean isApplyToAnnexIndiv() {
        return mApplyToAnnexIndiv;
    }

    public void setApplyToAnnexIndiv(boolean b) {
        mApplyToAnnexIndiv = b;
    }

    public boolean isExceptBestAndWorstIndiv() {
        return mExceptBestAndWorstIndiv;
    }

    public void setExceptBestAndWorstIndiv(boolean b) {
        mExceptBestAndWorstIndiv = b;
    }

    public boolean isApplyToAnnexTeam() {
        return mApplyToAnnexTeam;
    }

    public void setApplyToAnnexTeam(boolean b) {
        mApplyToAnnexTeam = b;
    }

    public boolean isExceptBestAndWorstTeam() {
        return mExceptBestAndWorstTeam;
    }

    public void setExceptBestAndWorstTeam(boolean b) {
        mExceptBestAndWorstTeam = b;
    }

    public boolean isUseBestResultIndiv() {
        return mUseBestResultsIndiv;
    }

    public boolean isUseBestResultTeam() {
        return mUseBestResultsTeam;
    }

    public void setUseBestResultIndiv(boolean b) {
        mUseBestResultsIndiv = b;
    }

    public void setUseBestResultTeam(boolean b) {
        mUseBestResultsTeam = b;
    }

    public int getBestResultIndiv() {
        return mBestResultsIndiv;
    }

    public int getBestResultTeam() {
        return mBestResultsTeam;
    }

    public void setBestResultIndiv(int i) {
        mBestResultsIndiv = i;
    }

    public void setBestResultTeam(int i) {
        mBestResultsTeam = i;
    }

    /**
     *
     * @return
     */
    @Override
    public Element getXMLElement() {
        final SimpleDateFormat format = new SimpleDateFormat(Translate.translate("DD/MM/YYYY HH:MM:SS"), Locale.getDefault());
        final Element params = new Element(StringConstants.CS_PARAMETERS);
        params.setAttribute(StringConstants.CS_ORGANIZER, this.getTournamentOrga());
        params.setAttribute(StringConstants.CS_NAME, this.getTournamentName());
        params.setAttribute(StringConstants.CS_DATE, getStringDate(format));
        params.setAttribute(StringConstants.CS_PLACE, this.getTournamentName());

        for (int i = 0; i < this.getCriteriaCount(); i++) {
            final Element crit = getCriteria(i).getXMLElement();
            params.addContent(crit);
        }
        
        for (int i = 0; i < this.getFormulaCount(); i++) {
            final Element form = getFormula(i).getXMLElement();
            params.addContent(form);
        }

        params.setAttribute(StringConstants.CS_VICTORY, Integer.toString(this.getPointsIndivVictory()));
        params.setAttribute(StringConstants.CS_LARGE_VICTORY, Integer.toString(this.getPointsIndivLargeVictory()));

        params.setAttribute(StringConstants.CS_DRAW, Integer.toString(this.getPointsIndivDraw()));
        params.setAttribute(StringConstants.CS_LOST, Integer.toString(this.getPointsIndivLost()));
        try {
            params.setAttribute(StringConstants.CS_REFUSED, Integer.toString(this.getPointsRefused()));
            params.setAttribute(StringConstants.CS_CONCEEDED, Integer.toString(this.getPointsConcedeed()));
            params.setAttribute(StringConstants.CS_TEAM_LARGE_VICTORY, Integer.toString(this.getPointsTeamLargeVictory()));
            params.setAttribute(StringConstants.CS_TEAM_LITTLE_LOST, Integer.toString(this.getPointsTeamLittleLost()));
            params.setAttribute(StringConstants.CS_TEAM_HUGE_VICTORY, Integer.toString(this.getPointsTeamHugeVictory()));
        } catch (Exception e) {
            LOG.log(Level.FINE, e.getLocalizedMessage());
        }
        params.setAttribute(StringConstants.CS_LITTLE_LOST, Integer.toString(this.getPointsIndivLittleLost()));
        //params.setAttribute(StringConstants.CS_PORTUGAL, Boolean.toString(this.isPortugal()));

        params.setAttribute(StringConstants.CS_VICTORY_TEAM, Integer.toString(this.getPointsTeamVictory()));
        params.setAttribute(StringConstants.CS_DRAW_TEAM, Integer.toString(this.getPointsTeamDraw()));
        params.setAttribute(StringConstants.CS_TEAM_LOST, Integer.toString(this.getPointsTeamLost()));
        params.setAttribute(StringConstants.CS_HUGE_LOST_TEAM, Integer.toString(this.getPointsTeamHugeLost()));
        params.setAttribute(StringConstants.CS_TEAM_LITTLE_LOST, Integer.toString(this.getPointsTeamLittleLost()));

        params.setAttribute(StringConstants.CS_LARGE_VICTORY_GAP, Integer.toString(this.getGapLargeVictory()));
        params.setAttribute(StringConstants.CS_LITTLE_LOST_GAP, Integer.toString(this.getGapLittleLost()));

        params.setAttribute(StringConstants.CS_TEAM_LARGE_VICTORY_GAP, Float.toString(this.getGapTeamLargeVictory()));
        params.setAttribute(StringConstants.CS_TEAM_HUGE_VICTORY_GAP, Float.toString(this.getGapTeamHugeVictory()));
        params.setAttribute(StringConstants.CS_TEAM_LITTLE_LOST_GAP, Float.toString(this.getGapTeamLittleLost()));
        params.setAttribute(StringConstants.CS_TEAM_HUGE_LOST_GAP, Float.toString(this.getGapTeamHugeLost()));

        params.setAttribute(StringConstants.CS_RANK + 1, Integer.toString(this.getRankingIndiv1()));
        params.setAttribute(StringConstants.CS_RANK + 2, Integer.toString(this.getRankingIndiv2()));
        params.setAttribute(StringConstants.CS_RANK + 3, Integer.toString(this.getRankingIndiv3()));
        params.setAttribute(StringConstants.CS_RANK + 4, Integer.toString(this.getRankingIndiv4()));
        params.setAttribute(StringConstants.CS_RANK + 5, Integer.toString(this.getRankingIndiv5()));

        params.setAttribute(StringConstants.CS_RANK + 1 + "_" + StringConstants.CS_TEAM, Integer.toString(this.getRankingTeam1()));
        params.setAttribute(StringConstants.CS_RANK + 2 + "_" + StringConstants.CS_TEAM, Integer.toString(this.gemRankingTeam2()));
        params.setAttribute(StringConstants.CS_RANK + 3 + "_" + StringConstants.CS_TEAM, Integer.toString(this.getRankingTeam3()));
        params.setAttribute(StringConstants.CS_RANK + 4 + "_" + StringConstants.CS_TEAM, Integer.toString(this.getRankingTeam4()));
        params.setAttribute(StringConstants.CS_RANK + 5 + "_" + StringConstants.CS_TEAM, Integer.toString(this.getRankingTeam5()));

        params.setAttribute(StringConstants.CS_BYTEAM, Boolean.toString(this.isTeamTournament()));
        params.setAttribute(StringConstants.CS_TEAMMATES, Integer.toString(this.getTeamMatesNumber()));
        switch (this.getTeamPairing()) {
            case INDIVIDUAL_PAIRING:
                params.setAttribute(StringConstants.CS_TEAMPAIRING, "0");
                break;
            case TEAM_PAIRING:
                params.setAttribute(StringConstants.CS_TEAMPAIRING, "1");
                break;
        }

        switch (this.getTeamIndivPairing()) {
            case RANKING:
                params.setAttribute(StringConstants.CS_TEAMINDIVPAIRING, "0");
                break;
            case FREE:
                params.setAttribute(StringConstants.CS_TEAMINDIVPAIRING, "1");
                break;
            case RANDOM:
                params.setAttribute(StringConstants.CS_TEAMINDIVPAIRING, "2");
                break;
            case NAF:
                params.setAttribute(StringConstants.CS_TEAMINDIVPAIRING, "3");
                break;
        }

        params.setAttribute(StringConstants.CS_TEAMVICTORYPOINTS, Integer.toString(this.getPointsTeamVictoryBonus()));
        params.setAttribute(StringConstants.CS_TEAMDRAWPOINTS, Integer.toString(this.getPointsTeamDrawBonus()));
        params.setAttribute(StringConstants.CS_TEAMVICTORYONLY, Boolean.toString(this.isTeamVictoryOnly()));

        params.setAttribute(StringConstants.CS_GROUPENABLE, Boolean.toString(this.isGroupsEnable()));
        params.setAttribute(StringConstants.CS_SUBSTITUTES, Boolean.toString(this.isSubstitutes()));

        params.setAttribute(StringConstants.CS_ACTVATECLANS, Boolean.toString(this.isEnableClans()));
        params.setAttribute(StringConstants.CS_AVOIDFIRSTMATCH, Boolean.toString(this.isAvoidClansFirstMatch()));
        params.setAttribute(StringConstants.CS_AVOIDMATCH, Boolean.toString(this.isAvoidClansMatch()));

        params.setAttribute(StringConstants.CS_CLANTEAMMATESNUMBER, Integer.toString(this.getTeamMatesClansNumber()));

        params.setAttribute(StringConstants.CS_MULTIROSTER, Boolean.toString(this.isMultiRoster()));

        params.setAttribute(StringConstants.CS_INDIVBALANCED, Boolean.toString(this.isIndivPairingIndivBalanced()));
        params.setAttribute(StringConstants.CS_TEAMBALANCED, Boolean.toString(this.isIndivPairingTeamBalanced()));

        params.setAttribute(StringConstants.CS_PORTUGAL, Boolean.toString(this.isPortugal()));
        params.setAttribute(StringConstants.CS_COLOR, Boolean.toString(this.isUseColor()));
        params.setAttribute(StringConstants.CS_USE_IMAGE, Boolean.toString(this.isUseImage()));

        params.setAttribute(StringConstants.CS_USE_LARGE_VICTORY, Boolean.toString(this.isUseLargeVictory()));
        params.setAttribute(StringConstants.CS_USE_LITTLE_LOST, Boolean.toString(this.isUseLittleLoss()));
        params.setAttribute(StringConstants.CS_USE_TEAM_LARGE_VICTORY, Boolean.toString(this.isUseTeamLargeVictory()));
        params.setAttribute(StringConstants.CS_USE_TEAM_HUGE_VICTORY, Boolean.toString(this.isUseTeamHugeVictory()));
        params.setAttribute(StringConstants.CS_USE_TEAM_LITTLE_LOST, Boolean.toString(this.isUseTeamLittleLoss()));
        params.setAttribute(StringConstants.CS_USE_TEAM_HUGE_LOST, Boolean.toString(this.isUseTeamHugeLoss()));

        params.setAttribute(StringConstants.CS_TABLEBONUS, Boolean.toString(this.isTableBonus()));
        params.setAttribute(StringConstants.CS_TABLEBONUSPERROUND, Boolean.toString(this.isTableBonusPerRound()));
        params.setAttribute(StringConstants.CS_TABLEBONUSCOEF, Double.toString(this.getTableBonusCoef()));

        params.setAttribute(StringConstants.CS_USE_BEST_RESULT_INDIV, Boolean.toString(this.isUseBestResultIndiv()));
        params.setAttribute(StringConstants.CS_USE_BEST_RESULT_TEAM, Boolean.toString(this.isUseBestResultTeam()));

        params.setAttribute(StringConstants.CS_BEST_RESULT_INDIV, Integer.toString(this.getBestResultIndiv()));
        params.setAttribute(StringConstants.CS_BEST_RESULT_TEAM, Integer.toString(this.getBestResultTeam()));

        params.setAttribute(StringConstants.CS_APPLY_TO_ANNEX_TEAM, Boolean.toString(this.isApplyToAnnexTeam()));
        params.setAttribute(StringConstants.CS_APPLY_TO_ANNEX_INDIV, Boolean.toString(this.isApplyToAnnexIndiv()));

        params.setAttribute(StringConstants.CS_EXCEPT_BEST_AND_WORST_INDIV, Boolean.toString(this.isExceptBestAndWorstIndiv()));
        params.setAttribute(StringConstants.CS_EXCEPT_BEST_AND_WORST_TEAM, Boolean.toString(this.isExceptBestAndWorstTeam()));

        params.setAttribute(StringConstants.CS_WEBEDIT, Boolean.toString(mWebEdit));
        params.setAttribute(StringConstants.CS_WEB_PORT, Integer.toString(_webport));

        params.setAttribute(StringConstants.CS_WEB_COLOR1, Integer.toString(mColor1.getRGB()));
        params.setAttribute(StringConstants.CS_WEB_COLOR2, Integer.toString(mColor2.getRGB()));
        params.setAttribute(StringConstants.CS_WEB_FORECOLOR, Integer.toString(mForeColor.getRGB()));
        params.setAttribute(StringConstants.CS_WEB_BORDERCOLOR, Integer.toString(mBorderColor.getRGB()));
        
        params.setAttribute(StringConstants.CS_CROSSMATCHPOOL, Boolean.toString(mCrossPoolMatch));

        return params;
    }

    /**
     *
     * @param params
     */
    @Override
    public void setXMLElement(final Element params) {
        final SimpleDateFormat format = new SimpleDateFormat(Translate.translate("DD/MM/YYYY HH:MM:SS"), Locale.getDefault());

        try {
            this.semTournamentOrga(params.getAttribute(StringConstants.CS_ORGANIZER).getValue());
            this.setTournamentName(params.getAttribute(StringConstants.CS_NAME).getValue());

            this.setPointsIndivVictory(params.getAttribute(StringConstants.CS_VICTORY).getIntValue());
            this.setPointsIndivLargeVictory(params.getAttribute(StringConstants.CS_LARGE_VICTORY).getIntValue());

            this.setPointsIndivDraw(params.getAttribute(StringConstants.CS_DRAW).getIntValue());
            this.setPointsIndivLost(params.getAttribute(StringConstants.CS_LOST).getIntValue());
            try {
                this.setPointsRefused(params.getAttribute(StringConstants.CS_REFUSED).getIntValue());
                this.setPointsConcedeed(params.getAttribute(StringConstants.CS_CONCEEDED).getIntValue());
                this.setPointsTeamLargeVictory(params.getAttribute(StringConstants.CS_TEAM_LARGE_VICTORY).getIntValue());
                this.setPointsTeamHugeVictory(params.getAttribute(StringConstants.CS_TEAM_HUGE_VICTORY).getIntValue());
                this.setPointsTeamLittleLost(params.getAttribute(StringConstants.CS_TEAM_LITTLE_LOST).getIntValue());
                this.setGapTeamLargeVictory(params.getAttribute(StringConstants.CS_TEAM_LARGE_VICTORY_GAP).getFloatValue());
                this.setGapTeamHugeVictory(params.getAttribute(StringConstants.CS_TEAM_HUGE_VICTORY_GAP).getFloatValue());
                this.setGapTeamLittleLost(params.getAttribute(StringConstants.CS_TEAM_LITTLE_LOST_GAP).getFloatValue());
                this.setGapTeamHugeLost(params.getAttribute(StringConstants.CS_TEAM_HUGE_LOST_GAP).getFloatValue());
            } catch (DataConversionException e) {
                LOG.log(Level.FINE, e.getLocalizedMessage());
            } catch (NullPointerException e) {
                this.setPointsRefused(0);
                this.setPointsConcedeed(0);
            }
            this.setPointsIndivLittleLost(params.getAttribute(StringConstants.CS_LITTLE_LOST).getIntValue());

            Attribute r1 = params.getAttribute(StringConstants.CS_RANK + 1);
            this.setRankingIndiv1((r1.getIntValue()));
            this.setRankingIndiv2((params.getAttribute(StringConstants.CS_RANK + 2).getIntValue()));
            this.setRankingIndiv3((params.getAttribute(StringConstants.CS_RANK + 3).getIntValue()));
            this.setRankingIndiv4((params.getAttribute(StringConstants.CS_RANK + 4).getIntValue()));
            this.setRankingIndiv5((params.getAttribute(StringConstants.CS_RANK + 5).getIntValue()));

            try {
                this.setGapLargeVictory(params.getAttribute(StringConstants.CS_LARGE_VICTORY_GAP).getIntValue());
                this.setGapLittleLost(params.getAttribute(StringConstants.CS_LITTLE_LOST_GAP).getIntValue());

                this.setPlace(params.getAttribute(StringConstants.CS_PLACE).getValue());
                this.setTeamTournament(params.getAttribute(StringConstants.CS_BYTEAM).getBooleanValue());

                this.setTeamMatesNumber(params.getAttribute(StringConstants.CS_TEAMMATES).getIntValue());
                int val = params.getAttribute(StringConstants.CS_TEAMPAIRING).getIntValue();
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

                this.setPointsTeamVictoryBonus(params.getAttribute(StringConstants.CS_TEAMVICTORYPOINTS).getIntValue());
                val = params.getAttribute(StringConstants.CS_TEAMINDIVPAIRING).getIntValue();
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
                this.setTeamVictoryOnly(params.getAttribute(StringConstants.CS_TEAMVICTORYONLY).getBooleanValue());
                try {
                    this.setPointsTeamDrawBonus(params.getAttribute(StringConstants.CS_TEAMDRAWPOINTS).getIntValue());

                } catch (DataConversionException | NullPointerException e) {
                    this.setPointsTeamDrawBonus(0);
                    LOG.log(Level.FINE, e.getLocalizedMessage());
                }

                try {
                    this.setDate(format.parse(params.getAttribute(StringConstants.CS_DATE).getValue()));
                } catch (ParseException | NullPointerException pe) {
                }

            } catch (NullPointerException ne) {
                this.setGapLargeVictory(3);
                this.setGapLittleLost(1);
                this.setPlace(StringConstants.CS_NULL);
                this.setTeamTournament(false);
                this.setTeamMatesNumber(6);
                this.setTeamPairing(ETeamPairing.TEAM_PAIRING);
                this.setTeamIndivPairing(EIndivPairing.RANKING);
            }
            try {
                this.setPointsTeamVictory(params.getAttribute(StringConstants.CS_VICTORY_TEAM).getIntValue());
                this.setPointsTeamDraw(params.getAttribute(StringConstants.CS_DRAW_TEAM).getIntValue());
                this.setPointsTeamLost(params.getAttribute(StringConstants.CS_TEAM_LOST).getIntValue());
                this.setPointsTeamHugeLost(params.getAttribute(StringConstants.CS_HUGE_LOST_TEAM).getIntValue());
                this.setRankingTeam1(params.getAttribute(StringConstants.CS_RANK + 1 + "_" + StringConstants.CS_TEAM).getIntValue());
                this.setRankingTeam2(params.getAttribute(StringConstants.CS_RANK + 2 + "_" + StringConstants.CS_TEAM).getIntValue());
                this.setRankingTeam3(params.getAttribute(StringConstants.CS_RANK + 3 + "_" + StringConstants.CS_TEAM).getIntValue());
                this.setRankingTeam4(params.getAttribute(StringConstants.CS_RANK + 4 + "_" + StringConstants.CS_TEAM).getIntValue());
                this.setRankingTeam5(params.getAttribute(StringConstants.CS_RANK + 5 + "_" + StringConstants.CS_TEAM).getIntValue());
            } catch (NullPointerException ne2) {
            //    JOptionPane.showMessageDialog(null, ne2.getLocalizedMessage());
            }

            try {
                this.setEnableClans(params.getAttribute(StringConstants.CS_ACTVATECLANS).getBooleanValue());
                this.setAvoidClansFirstMatch(params.getAttribute(StringConstants.CS_AVOIDFIRSTMATCH).getBooleanValue());
                this.setAvoidClansMatch(params.getAttribute(StringConstants.CS_AVOIDMATCH).getBooleanValue());
                this.setSubstitutes(params.getAttribute(StringConstants.CS_SUBSTITUTES).getBooleanValue());
                this.setMultiRoster(params.getAttribute(StringConstants.CS_MULTIROSTER).getBooleanValue());
                this.setPortugal(params.getAttribute(StringConstants.CS_PORTUGAL).getBooleanValue());
                this.setUseColor(params.getAttribute(StringConstants.CS_COLOR).getBooleanValue());
                this.setUseImage(params.getAttribute(StringConstants.CS_USE_IMAGE).getBooleanValue());
                this.setUseLargeVictory(params.getAttribute(StringConstants.CS_USE_LARGE_VICTORY).getBooleanValue());
                this.setUseLittleLoss(params.getAttribute(StringConstants.CS_USE_LITTLE_LOST).getBooleanValue());
                this.setUseTeamLargeVictory(params.getAttribute(StringConstants.CS_USE_TEAM_LARGE_VICTORY).getBooleanValue());
                this.setUseTeamHugeVictory(params.getAttribute(StringConstants.CS_USE_TEAM_HUGE_VICTORY).getBooleanValue());
                this.setUseTeamLittleLoss(params.getAttribute(StringConstants.CS_USE_TEAM_LITTLE_LOST).getBooleanValue());
                this.setUseTeamHugeLoss(params.getAttribute(StringConstants.CS_USE_TEAM_HUGE_LOST).getBooleanValue());

            } catch (NullPointerException ne3) {
                //JOptionPane.showMessageDialog(null, ne3.getLocalizedMessage());
            }
            try {
                this.setIndivPairingIndivBalanced(params.getAttribute(StringConstants.CS_INDIVBALANCED).getBooleanValue());
                this.setIndivPairingTeamBalanced(params.getAttribute(StringConstants.CS_TEAMBALANCED).getBooleanValue());
                this.setClansMembersNumber(params.getAttribute(StringConstants.CS_CLANTEAMMATESNUMBER).getIntValue());

                this.setUseBestResultIndiv(params.getAttribute(StringConstants.CS_USE_BEST_RESULT_INDIV).getBooleanValue());
                this.setUseBestResultTeam(params.getAttribute(StringConstants.CS_USE_BEST_RESULT_TEAM).getBooleanValue());
                this.setBestResultIndiv(params.getAttribute(StringConstants.CS_BEST_RESULT_INDIV).getIntValue());
                this.setBestResultTeam(params.getAttribute(StringConstants.CS_BEST_RESULT_TEAM).getIntValue());

                this.setApplyToAnnexIndiv(params.getAttribute(StringConstants.CS_APPLY_TO_ANNEX_INDIV).getBooleanValue());
                this.setApplyToAnnexTeam(params.getAttribute(StringConstants.CS_APPLY_TO_ANNEX_TEAM).getBooleanValue());
                this.setExceptBestAndWorstIndiv(params.getAttribute(StringConstants.CS_EXCEPT_BEST_AND_WORST_INDIV).getBooleanValue());
                this.setExceptBestAndWorstTeam(params.getAttribute(StringConstants.CS_EXCEPT_BEST_AND_WORST_TEAM).getBooleanValue());

            } catch (NullPointerException ne4) {
                //JOptionPane.showMessageDialog(null, ne4.getLocalizedMessage());
            }

            try {
                this.setTableBonus(params.getAttribute(StringConstants.CS_TABLEBONUS).getBooleanValue());
                this.setTableBonusPerRound(params.getAttribute(StringConstants.CS_TABLEBONUSPERROUND).getBooleanValue());
                this.setTableBonusCoef(params.getAttribute(StringConstants.CS_TABLEBONUSCOEF).getDoubleValue());

            } catch (NullPointerException ne4) {
                //JOptionPane.showMessageDialog(null, ne4.getLocalizedMessage());
            }

            final List<Element> criterias = params.getChildren(StringConstants.CS_CRITERIA);
            final Iterator<Element> cr = criterias.iterator();

            this.clearCiterias();

            while (cr.hasNext()) {
                final Element criteria = cr.next();
                final Criteria crit = new Criteria(criteria.getAttributeValue(StringConstants.CS_NAME));
                crit.setXMLElement(criteria);
                this.addCriteria(crit);
            }

            final List<Element> formulas = params.getChildren(StringConstants.CS_FORMULA);
            final Iterator<Element> fo = formulas.iterator();

            this.clearFormulas();

            while (fo.hasNext()) {
                final Element formula = fo.next();
                final Formula form = new Formula(formula.getAttributeValue(StringConstants.CS_NAME));
                form.setXMLElement(formula);
                this.addFormula(form);
            }

        } catch (DataConversionException dce) {
           // JOptionPane.showMessageDialog(null, dce.getLocalizedMessage());
        }

        try {
            this.setGroupsEnable(params.getAttribute(StringConstants.CS_GROUPENABLE).getBooleanValue());
            this.setWebEdit(params.getAttribute(StringConstants.CS_WEBEDIT).getBooleanValue());
            this._webport = Integer.parseInt(params.getAttribute(StringConstants.CS_WEB_PORT).getValue());

            this.mColor1 = new Color(Integer.parseInt(params.getAttribute(StringConstants.CS_WEB_COLOR1).getValue()));
            this.mColor2 = new Color(Integer.parseInt(params.getAttribute(StringConstants.CS_WEB_COLOR2).getValue()));
            this.mForeColor = new Color(Integer.parseInt(params.getAttribute(StringConstants.CS_WEB_FORECOLOR).getValue()));
            this.mBorderColor = new Color(Integer.parseInt(params.getAttribute(StringConstants.CS_WEB_BORDERCOLOR).getValue()));

        } catch (NullPointerException npe5) {
            this._webport = 80;
        } catch (DataConversionException dce) {
            this._webport = 80;
        }
        try {
            this.setCrossPoolMatch(params.getAttribute(StringConstants.CS_CROSSMATCHPOOL).getBooleanValue());

        } catch (NullPointerException npe6) {
            this.setCrossPoolMatch(false);
        }
        catch (DataConversionException dce2)
        {
            this.setCrossPoolMatch(false);
        }
    }

    @Override
    public String toString() {
        return Translate.translate(Translate.CS_Parameters);
    }

    /**
     *
     * @param j
     * @return
     */
    public int getTeamRankingType(int j) {
        int rank;
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

    public int getIndexOfCriteria(Criteria c) {
        return mCriterias.indexOf(c);
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
     * @return the mCriterias
     */
    public int getFormulaCount() {
        return mFormulas.size();
    }

    /**
     * @param i
     * @return the mCriterias
     */
    public Formula getFormula(int i) {
        return mFormulas.get(i);
    }

    public int getIndexOfFormula(Formula f) {
        return mFormulas.indexOf(f);
    }

    /**
     * Clear the criterias array
     */
    public void clearFormulas() {
        mFormulas.clear();
    }

    /**
     *
     * @param c
     */
    public void addFormula(Formula f) {
        mFormulas.add(f);
    }

    /**
     *
     * @param c
     */
    public void removeFormula(int f) {
        mFormulas.remove(f);
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

    public int getPointsTeamLargeVictory() {
        return mPointsTeamLargeVictory;
    }

    public int getPointsTeamHugeVictory() {
        return mPointsTeamHugeVictory;
    }

    /**
     * @param mPointsIndivLargeVictory the mPointsIndivLargeVictory to set
     */
    public void setPointsIndivLargeVictory(int mPointsIndivLargeVictory) {
        this.mPointsIndivLargeVictory = mPointsIndivLargeVictory;
    }

    public void setPointsTeamLargeVictory(int mPointsTeamLargeVictory) {
        this.mPointsTeamLargeVictory = mPointsTeamLargeVictory;
    }

    public void setPointsTeamHugeVictory(int points) {
        this.mPointsTeamHugeVictory = points;
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

    public int getPointsTeamLittleLost() {
        return mPointsTeamLittleLost;
    }

    /**
     * @param mPointsIndivLittleLost the mPointsIndivLittleLost to set
     */
    public void setPointsIndivLittleLost(int mPointsIndivLittleLost) {
        this.mPointsIndivLittleLost = mPointsIndivLittleLost;
    }

    public void setPointsTeamLittleLost(int mPointsTeamLittleLost) {
        this.mPointsTeamLittleLost = mPointsTeamLittleLost;
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

    public float getGapTeamLargeVictory() {
        return mGapTeamLargeVictory;
    }

    public float getGapTeamHugeVictory() {
        return mGapTeamHugeVictory;
    }

    /**
     * @param mGapLargeVictory the mGapLargeVictory to set
     */
    public void setGapLargeVictory(int mGapLargeVictory) {
        this.mGapLargeVictory = mGapLargeVictory;
    }

    public void setGapTeamLargeVictory(float mGapLargeVictory) {
        this.mGapTeamLargeVictory = mGapLargeVictory;
    }

    public void setGapTeamHugeVictory(float mGapLargeVictory) {
        this.mGapTeamHugeVictory = mGapLargeVictory;
    }

    /**
     * @return the mGapLittleLost
     */
    public int getGapLittleLost() {
        return mGapLittleLost;
    }

    public float getGapTeamLittleLost() {
        return mGapTeamLittleLost;
    }

    public float getGapTeamHugeLost() {
        return mGapTeamHugeLost;
    }

    /**
     * @param mGapLittleLost the mGapLittleLost to set
     */
    public void setGapLittleLost(int mGapLittleLost) {
        this.mGapLittleLost = mGapLittleLost;
    }

    public void setGapTeamLittleLost(float mGapLittleLost) {
        this.mGapTeamLittleLost = mGapLittleLost;
    }

    public void setGapTeamHugeLost(float mGapLittleLost) {
        this.mGapTeamHugeLost = mGapLittleLost;
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
    public String getStringDate(SimpleDateFormat format) {
        return format.format(mDate);
    }

    /**
     *
     * @return
     */
    public long getDateTime() {
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

    public boolean isTableBonus() {
        return mTableBonus;
    }

    public boolean isTableBonusPerRound() {
        return mTableBonusPerRound;
    }

    public double getTableBonusCoef() {
        return mTableBonusCoef;
    }

    public void setTableBonus(boolean b) {
        mTableBonus = b;
    }

    public void setTableBonusPerRound(boolean b) {
        mTableBonusPerRound = b;
    }

    public void setTableBonusCoef(double val) {
        mTableBonusCoef = val;
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

    public int getPointsTeamHugeLost() {
        return mPointsTeamHugeLost;
    }

    /**
     * @param mPointsTeamLost the mPointsTeamLost to set
     */
    public void setPointsTeamLost(int mPointsTeamLost) {
        this.mPointsTeamLost = mPointsTeamLost;
    }

    public void setPointsTeamHugeLost(int mPointsTeamLost) {
        this.mPointsTeamHugeLost = mPointsTeamLost;
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

    public void setUseLargeVictory(boolean use) {
        this.mUseLargeVictory = use;
    }

    public void setUseTeamLargeVictory(boolean use) {
        this.mUseTeamLargeVictory = use;
    }

    public void setUseTeamHugeVictory(boolean use) {
        this.mUseTeamHugeVictory = use;
    }

    public void setUseLittleLoss(boolean use) {
        this.mUseLittleLoss = use;
    }

    public void setUseTeamLittleLoss(boolean use) {
        this.mUseTeamLittleLoss = use;
    }

    public void setUseTeamHugeLoss(boolean use) {
        this.mUseTeamHugeLoss = use;
    }

    public boolean isUseLargeVictory() {
        return mUseLargeVictory;
    }

    public boolean isUseTeamLargeVictory() {
        return mUseTeamLargeVictory;
    }

    public boolean isUseTeamHugeVictory() {
        return mUseTeamHugeVictory;
    }

    public boolean isUseLittleLoss() {
        return mUseLittleLoss;
    }

    public boolean isUseTeamLittleLoss() {
        return mUseTeamLittleLoss;
    }

    public boolean isUseTeamHugeLoss() {
        return mUseTeamHugeLoss;
    }

    protected int _webport = 80;

    public int getWebServerPort() {
        return _webport;
    }

    public void setWebServerPort(int port) {
        _webport = port;
    }

    /**
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(final Object obj) {

        boolean result;
        result = false;
        if (obj instanceof Parameters) {
            Parameters params = (Parameters) obj;

            result &= params.mBestResultsIndiv == this.mBestResultsIndiv;
            result &= params.mBestResultsTeam == this.mBestResultsTeam;
            result &= params.mClansMembersNumber == this.mClansMembersNumber;

            result &= params.mGapLargeVictory == this.mGapLargeVictory;
            result &= params.mGapLittleLost == this.mGapLittleLost;
            result &= params.mPointsConcedeed == this.mPointsConcedeed;
            result &= params.mPointsIndivDraw == this.mPointsIndivDraw;
            result &= params.mPointsIndivLargeVictory == this.mPointsIndivLargeVictory;
            result &= params.mPointsIndivLittleLost == this.mPointsIndivLittleLost;
            result &= params.mPointsIndivLost == this.mPointsIndivLost;
            result &= params.mPointsIndivVictory == this.mPointsIndivVictory;
            result &= params.mPointsRefused == this.mPointsRefused;
            result &= params.mPointsTeamDraw == this.mPointsTeamDraw;
            result &= params.mPointsTeamDrawBonus == this.mPointsTeamDrawBonus;
            result &= params.mPointsTeamLost == this.mPointsTeamLost;
            result &= params.mPointsTeamVictory == this.mPointsTeamVictory;
            result &= params.mPointsTeamVictoryBonus == this.mPointsTeamVictoryBonus;
            result &= params.mRankingIndiv1 == this.mRankingIndiv1;
            result &= params.mRankingIndiv2 == this.mRankingIndiv2;
            result &= params.mRankingIndiv3 == this.mRankingIndiv3;
            result &= params.mRankingIndiv4 == this.mRankingIndiv4;
            result &= params.mRankingIndiv5 == this.mRankingIndiv5;

            result &= params.mRankingTeam1 == this.mRankingTeam1;
            result &= params.mRankingTeam2 == this.mRankingTeam2;
            result &= params.mRankingTeam3 == this.mRankingTeam3;
            result &= params.mRankingTeam4 == this.mRankingTeam4;
            result &= params.mRankingTeam5 == this.mRankingTeam5;

            result &= params.mTeamMatesClansNumber == this.mTeamMatesClansNumber;
            result &= params.mTeamMatesNumber == this.mTeamMatesNumber;
            result &= params.mDate.equals(this.mDate);
            result &= params.mPlace.equals(this.mPlace);
            result &= params.mTournamentName.equals(this.mTournamentName);
            result &= params.mTournamentOrga.equals(this.mTournamentOrga);

            result = params.mApplyToAnnexIndiv == this.mApplyToAnnexIndiv;
            result &= params.mApplyToAnnexTeam == this.mApplyToAnnexTeam;
            result &= params.mAvoidClansFirstMatch = this.mAvoidClansFirstMatch;
            result &= params.mAvoidClansMatch == this.mAvoidClansMatch;
            result &= params.mEnableClans == this.mEnableClans;
            result &= params.mExceptBestAndWorstIndiv == this.mExceptBestAndWorstIndiv;
            result &= params.mExceptBestAndWorstTeam == this.mExceptBestAndWorstTeam;
            result &= params.mGroupsEnable == this.mGroupsEnable;
            result &= params.mIndivPairingIndivBalanced == this.mIndivPairingIndivBalanced;
            result &= params.mIndivPairingTeamBalanced == this.mIndivPairingTeamBalanced;
            result &= params.mMultiRoster == this.mMultiRoster;
            result &= params.mPortugal == this.mPortugal;
            result &= params.mSubstitutes == this.mSubstitutes;
            result &= params.mTableBonus == this.mTableBonus;
            result &= params.mTableBonusPerRound == this.mTableBonusPerRound;
            result &= params.mTeamTournament == this.mTeamTournament;
            result &= params.mTeamVictoryOnly == this.mTeamVictoryOnly;
            result &= params.mUseBestResultsIndiv == this.mUseBestResultsIndiv;
            result &= params.mUseBestResultsTeam == this.mUseBestResultsTeam;
            result &= params.mUseLargeVictory == this.mUseLargeVictory;
            result &= params.mUseLittleLoss == this.mUseLittleLoss;

            result &= params.mCrossPoolMatch;

            result &= params.mTeamPairing == this.mTeamPairing;
            result &= params.mTeamIndivPairing == this.mTeamIndivPairing;

            result &= Math.abs(params.mTableBonusCoef - this.mTableBonusCoef) < 0.001;

            result &= params.mCriterias.size() == this.mCriterias.size();
            for (Criteria c : mCriterias) {
                result &= params.mCriterias.contains(c);
            }
            
             result &= params.mFormulas.size() == this.mFormulas.size();
            for (Formula f : mFormulas) {
                result &= params.mFormulas.contains(f);
            }
        }
        return result;
    }

    protected boolean mWebEdit = false;

    protected Color mColor1 = new Color(153, 153, 153);
    protected Color mColor2 = new Color(187, 187, 187);
    protected Color mBorderColor = new Color(51, 51, 51);
    protected Color mForeColor = new Color(255, 255, 255);

    public void setWebEdit(boolean WebEdit) {
        mWebEdit = WebEdit;
    }

    public boolean isWebEdit() {
        return mWebEdit;
    }

    public String getStringColor1() {

        return String.format("%02X", mColor1.getRed()) + String.format("%02X", mColor1.getGreen()) + String.format("%02X", mColor1.getBlue());
    }

    public String getStringColor2() {
        return String.format("%02X", mColor2.getRed()) + String.format("%02X", mColor2.getGreen()) + String.format("%02X", mColor2.getBlue());
    }

    public String getStringBorderColor() {
        return String.format("%02X", mBorderColor.getRed()) + String.format("%02X", mBorderColor.getGreen()) + String.format("%02X", mBorderColor.getBlue());
    }

    public String getStringForeColor() {
        return String.format("%02X", mForeColor.getRed()) + String.format("%02X", mForeColor.getGreen()) + String.format("%02X", mForeColor.getBlue());
    }

    public Color getColor1() {
        return mColor1;
    }

    public Color getColor2() {
        return mColor2;
    }

    public Color getBorderColor() {
        return mBorderColor;
    }

    public Color getForeColor() {
        return mForeColor;
    }

    public void setColor1(Color c) {
        mColor1 = c;
    }

    public void setColor2(Color c) {
        mColor2 = c;
    }

    public void setBorderColor(Color c) {
        mBorderColor = c;
    }

    public void setForeColor(Color c) {
        mForeColor = c;
    }

}
