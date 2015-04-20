/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import org.jdom2.Element;

/**
 *
 * @author WFMJ7631
 */
public abstract class Match implements XMLExport {

    /**
     *
     */
    private Competitor mCompetitor1;

    /**
     *
     */
    private Competitor mCompetitor2;
    
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
   
    /**
     *
     * @return
     */
    public Competitor getWinner()
    {
        return mWinner;
    }

    /**
     *
     * @return
     */
    public Competitor  getLooser()
    {
        return mLooser;
    }

    /**
     * Reset Winner/Looser
     */
    public void resetWL() {
        setWinner(null);
        setLooser(null);
    }

    /**
     * @return the mCompetitor1
     */
    public Competitor getCompetitor1() {
        return mCompetitor1;
    }

    /**
     * @param mCompetitor1 the mCompetitor1 to set
     */
    public void setCompetitor1(Competitor mCompetitor1) {
        this.mCompetitor1 = mCompetitor1;
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
    }

    public abstract Element getXMLElementForDisplay();
    public abstract void setXMLElementForDisplay(Element element);
}
