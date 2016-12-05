/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import java.io.Serializable;
import java.rmi.RemoteException;
import org.jdom2.Element;

/**
 *
 * @author WFMJ7631
 */
public abstract class Match implements IXMLExport, Serializable {

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

    /**
     *
     * @return
     */
    public Competitor getWinner() throws RemoteException{
        return mWinner;
    }

    /**
     *
     * @return
     */
    public Competitor getLooser() throws RemoteException{
        return mLooser;
    }

    /**
     * Reset Winner/Looser
     */
    public void resetWL() throws RemoteException{
        setWinner(null);
        setLooser(null);
    }

    /**
     * @return the mCompetitor1
     */
    public Competitor getCompetitor1() throws RemoteException{
        return mCompetitor1;
    }

    /**
     * @param mCompetitor1 the mCompetitor1 to set
     */
    public void setCompetitor1(Competitor mCompetitor1) throws RemoteException{
        this.mCompetitor1 = mCompetitor1;
    }

    /**
     * @return the mCompetitor2
     */
    public Competitor getCompetitor2() throws RemoteException{
        return mCompetitor2;
    }

    /**
     * @param mCompetitor2 the mCompetitor2 to set
     */
    public void setCompetitor2(Competitor mCompetitor2) throws RemoteException{
        this.mCompetitor2 = mCompetitor2;
    }

    /**
     * @return the mRound
     */
    public Round getRound() throws RemoteException{
        return mRound;
    }

    /**
     * @param mRound the mRound to set
     */
    public void setRound(Round mRound) throws RemoteException{
        this.mRound = mRound;
    }

    /**
     * @param mWinner the mWinner to set
     */
    public void setWinner(Competitor mWinner) throws RemoteException{
        this.mWinner = mWinner;
    }

    /**
     * @param mLooser the mLooser to set
     */
    public void setLooser(Competitor mLooser) throws RemoteException{
        this.mLooser = mLooser;
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

    /**
     * Recalculate the values fot this match
     */
    public abstract void recomputeValues()throws RemoteException;

    /**
     * Returns the curent value for display
     *
     * @param index Index of the value (1..5)
     * @return an integer
     */
    public int getValue(int indexvalue, Competitor c) throws RemoteException{
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
    
    public abstract int getValue(Criteria crit, int subtype,Competitor c)throws RemoteException;

    public abstract Element getXMLElementForDisplay()throws RemoteException;

    public abstract void setXMLElementForDisplay(Element element)throws RemoteException;
    
    public abstract boolean isEntered()throws RemoteException;
}
