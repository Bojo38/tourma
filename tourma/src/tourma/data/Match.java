/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

/**
 *
 * @author WFMJ7631
 */
public abstract class Match implements XMLExport {

    /**
     *
     */
    public Competitor mCompetitor1;

    /**
     *
     */
    public Competitor mCompetitor2;
    
    /**
     *
     */
    public Round mRound;

    /**
     *
     */
    protected Competitor mWinner = null;

    /**
     *
     */
    protected Competitor mLooser = null;

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
    public abstract Competitor getWinner();

    /**
     *
     * @return
     */
    public abstract Competitor  getLooser();

    /**
     *
     */
    public void resetWL() {
        mWinner = null;
        mLooser = null;
    }

    
}
