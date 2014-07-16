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
    public Competitor mCompetitor1;
    public Competitor mCompetitor2;
    
    public Round mRound;

    protected Competitor mWinner = null;
    protected Competitor mLooser = null;

    public Match(final Round round) {
        mRound = round;
    }
   
    public abstract Competitor getWinner();
    public abstract Competitor  getLooser();

    public void resetWL() {
        mWinner = null;
        mLooser = null;
    }

    
}
