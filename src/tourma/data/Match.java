/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import java.io.Serializable;
import org.jdom.Element;

/**
 *
 * @author WFMJ7631
 */
public abstract class Match implements IXMLExport, Serializable {

    public boolean isUpdated() {
        return updated;
    }

    public void setUpdated(boolean updated) {
        this.updated = updated;
    }
    protected boolean updated = false;

    /**
     *
     */
    protected Competitor mCompetitor1;

    /**
     *
     */
    protected Competitor mCompetitor2;

    /**
     *
     */
    private Round mRound;

    /**
     *
     */
    private Competitor mWinner = null;

    /**
     *
     */
    private Competitor mLooser = null;

    /**
     *
     * @param round
     */
    public Match(final Round round) {
        mRound = round;
    }

    public abstract int getUID();

    public abstract void setUID(int UID);

    public abstract void pull(Match match);

    public abstract void push(Match match);

    /**
     *
     * @return
     */
    public Competitor getWinner() {
        return mWinner;
    }

    /**
     *
     * @return
     */
    public Competitor getLooser() {
        return mLooser;
    }

    /**
     * Reset Winner/Looser
     */
    public void resetWL() {
        setWinner(null);
        setLooser(null);
        updated=true;
    }

    /**
     * @return the mCompetitor1
     */
    public Competitor getCompetitor1() {
        return mCompetitor1;
    }

    public boolean equals(final Object obj) {
        boolean result = false;
        if (obj instanceof Match) {
            Match m = (Match) obj;
            result = true;
            result &= (mCompetitor1 == m.getCompetitor1());
            result &= (mCompetitor2 == m.getCompetitor2());
        }
        return result;
    }

    /**
     * @param mCompetitor1 the mCompetitor1 to set
     */
    public void setCompetitor1(Competitor mCompetitor1) {
        this.mCompetitor1 = mCompetitor1;
        updated=true;
    }

    /**
     * @return the mCompetitor2
     */
    public Competitor getCompetitor2() {
        return mCompetitor2;
    }

    /**
     * @param mCompetitor2 the mCompetitor2 to set
     */
    public void setCompetitor2(Competitor mCompetitor2) {
        this.mCompetitor2 = mCompetitor2;
        updated=true;
    }

    /**
     * @return the mRound
     */
    public Round getRound() {
        return mRound;
    }

    /**
     * @param mRound the mRound to set
     */
    public void setRound(Round mRound) {
        this.mRound = mRound;
        updated=true;
    }

    /**
     * @param mWinner the mWinner to set
     */
    public void setWinner(Competitor mWinner) {
        this.mWinner = mWinner;
    }

    /**
     * @param mLooser the mLooser to set
     */
    public void setLooser(Competitor mLooser) {
        this.mLooser = mLooser;
        updated=true;
    }

    /**
     * Points of this match for ranking value1
     */
    int c1value1;
    /**
     * Points of this match for ranking value2
     */
    int c1value2;
    /**
     * Points of this match for ranking value3
     */

    int c1value3;
    /**
     * Points of this match for ranking value4
     */

    int c1value4;
    /**
     * Points of this match for ranking value5
     */
    int c1value5;
    /**
     * Points of this match for ranking value1
     */
    int c2value1;
    /**
     * Points of this match for ranking value2
     */
    int c2value2;
    /**
     * Points of this match for ranking value3
     */

    int c2value3;
    /**
     * Points of this match for ranking value4
     */

    int c2value4;
    /**
     * Points of this match for ranking value5
     */
    int c2value5;

    /**
     * Are the points for this match already computed
     */
    boolean values_computed;

    public boolean isValues_computed() {
        return values_computed;
    }

    public void setValues_computed(boolean values_computed) {
        this.values_computed = values_computed;
    }

    /**
     * Recalculate the values fot this match
     */
    public abstract void recomputeValues();

    /**
     * Returns the curent value for display
     *
     * @param index Index of the value (1..5)
     * @return an integer
     */
    public int getValue(int indexvalue, Competitor c) {
        int value = 0;
        if (!values_computed) {
            recomputeValues();
        }
        if (c == mCompetitor1) {
            switch (indexvalue) {
                case 1:
                    value = c1value1;
                    break;
                case 2:
                    value = c1value2;
                    break;
                case 3:
                    value = c1value3;
                    break;
                case 4:
                    value = c1value4;
                    break;
                case 5:
                    value = c1value5;
                    break;
            }
        }
        if (c == mCompetitor2) {
            switch (indexvalue) {
                case 1:
                    value = c2value1;
                    break;
                case 2:
                    value = c2value2;
                    break;
                case 3:
                    value = c2value3;
                    break;
                case 4:
                    value = c2value4;
                    break;
                case 5:
                    value = c2value5;
                    break;
            }
        }
        return value;
    }

    public abstract int getValue(Criteria crit, int subtype, Competitor c);

    public abstract Element getXMLElementForDisplay();

    public abstract void setXMLElementForDisplay(Element element);

    public abstract boolean isEntered();
}
