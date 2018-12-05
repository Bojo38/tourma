/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.jdom.DataConversionException;
import org.jdom.Element;
import tourma.MainFrame;
import tourma.utility.StringConstants;

/**
 *
 * @author Administrateur
 */
public class Criteria implements IXMLExport, Serializable {

    protected static AtomicInteger sGenUID = new AtomicInteger(0);
    protected int UID = sGenUID.incrementAndGet();

    public int getUID() {
        return UID;
    }

    public void setUID(int UID) {
        this.UID = UID;
    }

    public void pull(Criteria crit) {
        this.UID = crit.UID;
        this.mCriticalValueThreshold = crit.mCriticalValueThreshold;
        this.mName = crit.mName;
        this.mPointsAgainst = crit.mPointsAgainst;
        this.mPointsFor = crit.mPointsFor;
        this.mPointsTeamAgainst = crit.mPointsTeamAgainst;
        this.mPointsTeamFor = crit.mPointsTeamFor;
    }

    protected static final Logger LOG = Logger.getLogger(Criteria.class.getName());

    /**
     * Name of the criteria
     */
    protected String mName;
    /**
     * Accronym of the criteria
     */
    protected String mAccronym;

    public String getAccronym() {
        if (mAccronym==null)
        {
            return mName;
        }
        if (mAccronym.equals(""))
        {
            return mName;
        }
        return mAccronym;
    }

    public void setAccronym(String accronym) {
        this.mAccronym = accronym;
    }
    /**
     * Points for
     */
    protected int mPointsFor;
    /**
     * Points against
     */
    protected int mPointsAgainst;
    /**
     * Team Points for
     */
    protected int mPointsTeamFor;
    /**
     * Team Points against
     */
    protected int mPointsTeamAgainst;
    /**
     * Threshold above which the value may be erroneous.
     */
    protected int mCriticalValueThreshold;

    /**
     * Threshold above between for and against for adding offensive bonus if
     * (for-against)> mOffensiveDiffThreshold then add mOffensiveDiffBonuses
     */
    protected int mOffensiveDiffThreshold;
    /**
     * threshold above between against - for and for > agaisnt for adding
     * defensive threshold if (for-against) < mDefensiveDiffThreshold and for
     * against then add mDefensiveDiffBonuses
     */
    protected int mDefensiveDiffThreshold;

    
    /**
     * Value if offensive diff bonus
     */
    protected int mOffensiveDiffBonuses;
    /**
     * Value if defensive diff bonus
     */
    protected int mDefensiveDiffBonuses;

    /**
     * If threshold above is reached additional point is added.
     */
    protected int mOffensiveThreshold;
    /**
     * Value if offensive bonus
     */
    protected int mOffensiveBonuses;

    /**
     * Threshold above between for and against for adding offensive bonus if
     * (for-against)> mOffensiveDiffThreshold then add mOffensiveDiffBonuses
     */
    protected int mOffensiveDiffThresholdByTeam;
    /**
     * threshold above between against - for and for > agaisnt for adding
     * defensive threshold if (for-against) < mDefensiveDiffThreshold and for
     * against then add mDefensiveDiffBonuses
     */
    protected int mDefensiveDiffThresholdByTeam;
    /**
     * Value if offensive diff bonus
     */
    protected int mOffensiveDiffBonusesByTeam;
    /**
     * Value if defensive diff bonus
     */
    protected int mDefensiveDiffBonusesByTeam;

    /**
     * If threshold above is reached additional point is added.
     */
    protected int mOffensiveThresholdByTeam;
    /**
     * Value if offensive bonus
     */
    protected int mOffensiveBonusesByTeam;
    
    /**
     * Value if offensive diff bonus
     */
    protected int mOffensiveDiffBonusesForTeam;
    /**
     * Value if defensive diff bonus
     */
    protected int mDefensiveDiffBonusesForTeam;

    /**
     * Value if offensive bonus
     */
    protected int mOffensiveBonusesForTeam;

    
    /**
     *
     * @param name
     */
    public Criteria(final String name) {
        mName = name;
        mPointsFor = 0;
        mPointsAgainst = 0;
        mPointsTeamFor = 0;
        mPointsTeamAgainst = 0;
        mCriticalValueThreshold = 10;
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
        if (obj instanceof Criteria) {
            Criteria crit = (Criteria) obj;
            result = this.getName().equals(crit.getName());
            result &= this.getPointsAgainst() == crit.getPointsAgainst();
            result &= this.getPointsFor() == crit.getPointsFor();
            result &= this.getPointsTeamFor() == crit.getPointsTeamFor();
            result &= this.getPointsTeamAgainst() == crit.getPointsTeamAgainst();
        }
        return result;
    }

    public int getCriticalThreshold() {
        return mCriticalValueThreshold;
    }

    public void setCriticalThreshold(int value) {
        mCriticalValueThreshold = value;
    }

    /**
     *
     * @return
     */
    @Override
    public Element getXMLElement() {
        final Element crit = new Element(StringConstants.CS_CRITERIA);
        String key = StringConstants.CS_CRITERIA;
        crit.setAttribute(key, this.getName());
        key = StringConstants.CS_CRITERIA_ACCRONYM;
        crit.setAttribute(key, this.getAccronym());
        crit.setAttribute(StringConstants.CS_POINTS_FOR, Integer.toString(this.getPointsFor()));
        crit.setAttribute(StringConstants.CS_POINTS_AGAINST, Integer.toString(this.getPointsAgainst()));
        crit.setAttribute(StringConstants.CS_TEAM_POINTS_FOR, Integer.toString(this.getPointsTeamFor()));
        crit.setAttribute(StringConstants.CS_TEAM_POINTS_AGAINST, Integer.toString(this.getPointsTeamAgainst()));
        crit.setAttribute(StringConstants.CS_CRITICAL_THRESHOLD, Integer.toString(this.getCriticalThreshold()));

        crit.setAttribute(StringConstants.CS_DIFF_DEFENSIVE_BONUS, Integer.toString(this.getDefensiveDiffBonuses()));
        crit.setAttribute(StringConstants.CS_OFFENSIVE_BONUS, Integer.toString(this.getOffensiveBonuses()));
        crit.setAttribute(StringConstants.CS_DIFF_OFFENSIVE_BONUS, Integer.toString(this.getOffensiveDiffBonuses()));

        crit.setAttribute(StringConstants.CS_DIFF_DEFENSIVE_BONUS_THRESHOLD, Integer.toString(this.getDefensiveDiffThreshold()));
        crit.setAttribute(StringConstants.CS_OFFENSIVE_BONUS_THRESHOLD, Integer.toString(this.getOffensiveThreshold()));
        crit.setAttribute(StringConstants.CS_DIFF_OFFENSIVE_BONUS_THRESHOLD, Integer.toString(this.getOffensiveDiffThreshold()));
        
        crit.setAttribute(StringConstants.CS_FOR_TEAM_DIFF_DEFENSIVE_BONUS, Integer.toString(this.getDefensiveDiffBonusesForTeam()));
        crit.setAttribute(StringConstants.CS_FOR_TEAM_OFFENSIVE_BONUS, Integer.toString(this.getOffensiveBonusesForTeam()));
        crit.setAttribute(StringConstants.CS_FOR_TEAM_DIFF_OFFENSIVE_BONUS, Integer.toString(this.getOffensiveDiffBonusesForTeam()));


        crit.setAttribute(StringConstants.CS_TEAM_DIFF_DEFENSIVE_BONUS, Integer.toString(this.getDefensiveDiffBonusesByTeam()));
        crit.setAttribute(StringConstants.CS_TEAM_OFFENSIVE_BONUS, Integer.toString(this.getOffensiveBonusesByTeam()));
        crit.setAttribute(StringConstants.CS_TEAM_DIFF_OFFENSIVE_BONUS, Integer.toString(this.getOffensiveDiffBonusesByTeam()));

        crit.setAttribute(StringConstants.CS_TEAM_DIFF_DEFENSIVE_BONUS_THRESHOLD, Integer.toString(this.getDefensiveDiffThresholdByTeam()));
        crit.setAttribute(StringConstants.CS_TEAM_OFFENSIVE_BONUS_THRESHOLD, Integer.toString(this.getOffensiveThresholdByTeam()));
        crit.setAttribute(StringConstants.CS_TEAM_DIFF_OFFENSIVE_BONUS_THRESHOLD, Integer.toString(this.getOffensiveDiffThresholdByTeam()));

        return crit;
    }

    /**
     *
     * @param criteria
     */
    @Override
    public void setXMLElement(final Element criteria) {
        try {
            this.setName(criteria.getAttributeValue(StringConstants.CS_CRITERIA));
            if (getName() == null) {
                this.setName(criteria.getAttributeValue(StringConstants.CS_NAME));
            }
            this.setPointsFor(criteria.getAttribute(StringConstants.CS_POINTS_FOR).getIntValue());
            this.setPointsTeamFor(criteria.getAttribute(StringConstants.CS_TEAM_POINTS_FOR).getIntValue());
            this.setPointsAgainst(criteria.getAttribute(StringConstants.CS_POINTS_AGAINST).getIntValue());
            this.setPointsTeamAgainst(criteria.getAttribute(StringConstants.CS_TEAM_POINTS_AGAINST).getIntValue());
            try {
                this.setCriticalThreshold(criteria.getAttribute(StringConstants.CS_CRITICAL_THRESHOLD).getIntValue());
                
            } catch (NullPointerException npe) {
                this.setCriticalThreshold(10);
            }
             try {
                this.setAccronym(criteria.getAttributeValue(StringConstants.CS_CRITERIA_ACCRONYM));
            } catch (NullPointerException npe) {
                this.setCriticalThreshold(10);
            }

            try {

                this.setDefensiveDiffBonuses(criteria.getAttribute(StringConstants.CS_DIFF_DEFENSIVE_BONUS).getIntValue());
                this.setDefensiveDiffBonusesForTeam(criteria.getAttribute(StringConstants.CS_FOR_TEAM_DIFF_DEFENSIVE_BONUS).getIntValue());
                this.setDefensiveDiffBonusesByTeam(criteria.getAttribute(StringConstants.CS_TEAM_DIFF_DEFENSIVE_BONUS).getIntValue());
                
                this.setDefensiveDiffThreshold(criteria.getAttribute(StringConstants.CS_DIFF_DEFENSIVE_BONUS_THRESHOLD).getIntValue());
                this.setDefensiveDiffThresholdByTeam(criteria.getAttribute(StringConstants.CS_TEAM_DIFF_DEFENSIVE_BONUS_THRESHOLD).getIntValue());
                
                this.setOffensiveBonuses(criteria.getAttribute(StringConstants.CS_OFFENSIVE_BONUS).getIntValue());
                this.setOffensiveBonusesForTeam(criteria.getAttribute(StringConstants.CS_FOR_TEAM_OFFENSIVE_BONUS).getIntValue());
                this.setOffensiveBonusesByTeam(criteria.getAttribute(StringConstants.CS_TEAM_OFFENSIVE_BONUS).getIntValue());
                
                this.setOffensiveDiffBonuses(criteria.getAttribute(StringConstants.CS_DIFF_OFFENSIVE_BONUS).getIntValue());
                this.setOffensiveDiffBonusesForTeam(criteria.getAttribute(StringConstants.CS_FOR_TEAM_DIFF_OFFENSIVE_BONUS).getIntValue());                
                this.setOffensiveDiffBonusesByTeam(criteria.getAttribute(StringConstants.CS_TEAM_DIFF_OFFENSIVE_BONUS).getIntValue());                
                
                this.setOffensiveDiffThreshold(criteria.getAttribute(StringConstants.CS_DIFF_OFFENSIVE_BONUS_THRESHOLD).getIntValue());
                this.setOffensiveDiffThresholdByTeam(criteria.getAttribute(StringConstants.CS_TEAM_DIFF_OFFENSIVE_BONUS_THRESHOLD).getIntValue());
                
                this.setOffensiveThreshold(criteria.getAttribute(StringConstants.CS_OFFENSIVE_BONUS_THRESHOLD).getIntValue());
                this.setOffensiveThresholdByTeam(criteria.getAttribute(StringConstants.CS_TEAM_OFFENSIVE_BONUS_THRESHOLD).getIntValue());

            } catch (NullPointerException npe) {
                this.setDefensiveDiffBonuses(0);
                this.setDefensiveDiffBonusesByTeam(0);
                this.setDefensiveDiffThreshold(0);
                this.setDefensiveDiffThresholdByTeam(0);
                this.setOffensiveBonuses(0);
                this.setOffensiveBonusesByTeam(0);
                this.setOffensiveDiffBonuses(0);
                this.setOffensiveDiffBonusesByTeam(0);
                this.setOffensiveDiffThreshold(0);
                this.setOffensiveDiffThresholdByTeam(0);
                this.setOffensiveThreshold(0);
                this.setOffensiveThresholdByTeam(0);

            }
        } catch (DataConversionException dce) {
            JOptionPane.showMessageDialog(MainFrame.getMainFrame(), dce.getLocalizedMessage());
        }
    }

    /**
     * @return the mName
     */
    public String getName() {
        return mName;
    }

    /**
     * @param mName the mName to set
     */
    public void setName(String mName) {
        this.mName = mName;
    }

    /**
     * @return the mPointsFor
     */
    public int getPointsFor() {
        return mPointsFor;
    }

    /**
     * @param mPointsFor the mPointsFor to set
     */
    public void setPointsFor(int mPointsFor) {
        this.mPointsFor = mPointsFor;
    }

    /**
     * @return the mPointsAgainst
     */
    public int getPointsAgainst() {
        return mPointsAgainst;
    }

    /**
     * @param mPointsAgainst the mPointsAgainst to set
     */
    public void setPointsAgainst(int mPointsAgainst) {
        this.mPointsAgainst = mPointsAgainst;
    }

    /**
     * @return the mPointsTeamFor
     */
    public int getPointsTeamFor() {
        return mPointsTeamFor;
    }

    /**
     * @param mPointsTeamFor the mPointsTeamFor to set
     */
    public void setPointsTeamFor(int mPointsTeamFor) {
        this.mPointsTeamFor = mPointsTeamFor;
    }

    /**
     * @return the mPointsTeamAgainst
     */
    public int getPointsTeamAgainst() {
        return mPointsTeamAgainst;
    }

    /**
     * @param mPointsTeamAgainst the mPointsTeamAgainst to set
     */
    public void setPointsTeamAgainst(int mPointsTeamAgainst) {
        this.mPointsTeamAgainst = mPointsTeamAgainst;
    }
    
    
    public int getOffensiveDiffThreshold() {
        return mOffensiveDiffThreshold;
    }

    public void setOffensiveDiffThreshold(int mOffensiveDiffThreshold) {
        this.mOffensiveDiffThreshold = mOffensiveDiffThreshold;
    }

    public int getDefensiveDiffThreshold() {
        return mDefensiveDiffThreshold;
    }

    public void setDefensiveDiffThreshold(int mDefensiveDiffThreshold) {
        this.mDefensiveDiffThreshold = mDefensiveDiffThreshold;
    }

    public int getOffensiveDiffBonuses() {
        return mOffensiveDiffBonuses;
    }

    public void setOffensiveDiffBonuses(int mOffensiveDiffBonuses) {
        this.mOffensiveDiffBonuses = mOffensiveDiffBonuses;
    }

    public int getDefensiveDiffBonuses() {
        return mDefensiveDiffBonuses;
    }

    public void setDefensiveDiffBonuses(int mDefensiveDiffBonuses) {
        this.mDefensiveDiffBonuses = mDefensiveDiffBonuses;
    }

    public int getOffensiveThreshold() {
        return mOffensiveThreshold;
    }

    public void setOffensiveThreshold(int mOffensiveThreshold) {
        this.mOffensiveThreshold = mOffensiveThreshold;
    }

    public int getOffensiveBonuses() {
        return mOffensiveBonuses;
    }

    public int getOffensiveBonusesByTeam() {
        return mOffensiveBonusesByTeam;
    }

    public void setOffensiveBonuses(int mOffensiveBonuses) {
        this.mOffensiveBonuses = mOffensiveBonuses;
    }

    public void setOffensiveBonusesByTeam(int mOffensiveBonuses) {
        this.mOffensiveBonusesByTeam = mOffensiveBonuses;
    }

    public int getOffensiveDiffThresholdByTeam() {
        return mOffensiveDiffThresholdByTeam;
    }

    public void setOffensiveDiffThresholdByTeam(int mOffensiveDiffThresholdByTeam) {
        this.mOffensiveDiffThresholdByTeam = mOffensiveDiffThresholdByTeam;
    }

    public int getDefensiveDiffThresholdByTeam() {
        return mDefensiveDiffThresholdByTeam;
    }

    public void setDefensiveDiffThresholdByTeam(int mDefensiveDiffThresholdByTeam) {
        this.mDefensiveDiffThresholdByTeam = mDefensiveDiffThresholdByTeam;
    }

    public int getOffensiveDiffBonusesByTeam() {
        return mOffensiveDiffBonusesByTeam;
    }

    public void setOffensiveDiffBonusesByTeam(int mOffensiveDiffBonusesByTeam) {
        this.mOffensiveDiffBonusesByTeam = mOffensiveDiffBonusesByTeam;
    }

    public int getDefensiveDiffBonusesByTeam() {
        return mDefensiveDiffBonusesByTeam;
    }

    public void setDefensiveDiffBonusesByTeam(int mDefensiveDiffBonusesByTeam) {
        this.mDefensiveDiffBonusesByTeam = mDefensiveDiffBonusesByTeam;
    }

    public int getOffensiveThresholdByTeam() {
        return mOffensiveThresholdByTeam;
    }

    public void setOffensiveThresholdByTeam(int mOffensiveThresholdByTeam) {
        this.mOffensiveThresholdByTeam = mOffensiveThresholdByTeam;
    }


    public int getOffensiveDiffBonusesForTeam() {
        return mOffensiveDiffBonusesForTeam;
    }

    public void setOffensiveDiffBonusesForTeam(int mOffensiveDiffBonusesForTeam) {
        this.mOffensiveDiffBonusesForTeam = mOffensiveDiffBonusesForTeam;
    }

    public int getDefensiveDiffBonusesForTeam() {
        return mDefensiveDiffBonusesForTeam;
    }

    public void setDefensiveDiffBonusesForTeam(int mDefensiveDiffBonusesForTeam) {
        this.mDefensiveDiffBonusesForTeam = mDefensiveDiffBonusesForTeam;
    } 

    public int getOffensiveBonusesForTeam() {
        return mOffensiveBonusesForTeam;
    }

    public void setOffensiveBonusesForTeam(int mOffensiveBonusesForTeam) {
        this.mOffensiveBonusesForTeam = mOffensiveBonusesForTeam;
    }
    
    
}
