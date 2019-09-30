/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.jdom.DataConversionException;
import org.jdom.Element;
import tourma.utility.StringConstants;

/**
 *
 * @author WFMJ7631
 */
public class Cup implements IXMLExport, Serializable {

    ArrayList<CupTable> mTables = null;

    public Cup(ROUND_TYPE type,
            int roundsCount,
            boolean swissForLoosers,
            boolean shuffle,
            INITIAL_DRAW initial_draw) {
        mType = type;
        mRoundsCount = roundsCount;
        mSwissForLoosers = swissForLoosers;
        mShuffle = shuffle;
        mInitialDraw=initial_draw;
        /*
    The number of fixtures depends on roundIndex and the kind of cup
    If single Cup, only 1 fixture
    If looser Cup, 2 fixtures
    If ranking Games, RoundIndex+1 fixtures
         */
        // Generate fixtures according to supplides Data.
        switch (mType) {
            case CLASSIC:
                mTables.add(new CupTable(roundsCount, false));
                break;
            case CLASSIC_THIRD:
                mTables.add(new CupTable(roundsCount, true));
                break;
            case LOOSER:
                mTables.add(new CupTable(roundsCount, false));
                mTables.add(new CupTable(roundsCount - 1, false));
                break;
            case RANKING_MATCHES:
                for (int i = 0; i < roundsCount; i++) {
                    mTables.add(new CupTable(roundsCount - i, false));
                }
                break;
        }
    }

    public Cup() {
        mRoundsCount = 1;
        mSwissForLoosers = true;
        mShuffle = true;
        mType = ROUND_TYPE.RANKING_MATCHES;
    }

    @Override
    public Element getXMLElement() {
        final Element cup = new Element(StringConstants.CS_CUP);

        switch (mInitialDraw) {
            case RANDOM:
                cup.setAttribute(StringConstants.CS_CUP_INITIAL_DRAW, Integer.toString(0));
                break;
            case RANKING:
                cup.setAttribute(StringConstants.CS_CUP_INITIAL_DRAW, Integer.toString(1));
                break;
            case MANUAL:
                cup.setAttribute(StringConstants.CS_CUP_INITIAL_DRAW, Integer.toString(2));
                break;
            default:
                cup.setAttribute(StringConstants.CS_CUP_INITIAL_DRAW, Integer.toString(0));
        }
        
        switch (mType) {
            case CLASSIC:
                cup.setAttribute(StringConstants.CS_CUP_TYPE, Integer.toString(0));
                break;
            case CLASSIC_THIRD:
                cup.setAttribute(StringConstants.CS_CUP_TYPE, Integer.toString(1));
                break;
            case LOOSER:
                cup.setAttribute(StringConstants.CS_CUP_TYPE, Integer.toString(2));
                break;
            case RANKING_MATCHES:
                cup.setAttribute(StringConstants.CS_CUP_TYPE, Integer.toString(3));
                break;
            default:
                cup.setAttribute(StringConstants.CS_CUP_TYPE, Integer.toString(0));
        }

        cup.setAttribute(StringConstants.CS_CUP_ROUNDS_COUNT, Integer.toString(mRoundsCount));
        cup.setAttribute(StringConstants.CS_CUP_SWISS_FOR_LOOSERS, Boolean.toString(mSwissForLoosers));
        cup.setAttribute(StringConstants.CS_CUP_SHUFFLE, Boolean.toString(mShuffle));

        return cup;
    }

    @Override
    public void setXMLElement(Element cup) {
        if (cup.getAttribute(StringConstants.CS_CUP_TYPE) != null) {
            try {
                int type = cup.getAttribute(StringConstants.CS_CUP_TYPE).getIntValue();
                switch (type) {
                    case 0:
                        mType = ROUND_TYPE.CLASSIC;
                        break;
                    case 1:
                        mType = ROUND_TYPE.CLASSIC_THIRD;
                        break;
                    case 2:
                        mType = ROUND_TYPE.LOOSER;
                        break;
                    case 3:
                        mType = ROUND_TYPE.RANKING_MATCHES;
                        break;
                    default:
                        mType = ROUND_TYPE.CLASSIC;
                }
            } catch (DataConversionException dce) {
                System.err.println(dce.getLocalizedMessage());
            }

        } else {
            mType = ROUND_TYPE.CLASSIC;
        }
        
        if (cup.getAttribute(StringConstants.CS_CUP_INITIAL_DRAW) != null) {
            try {
                int type = cup.getAttribute(StringConstants.CS_CUP_INITIAL_DRAW).getIntValue();
                switch (type) {
                    case 0:
                        mInitialDraw = INITIAL_DRAW.RANDOM;
                        break;
                    case 1:
                        mInitialDraw = INITIAL_DRAW.RANKING;
                        break;
                    case 2:
                        mInitialDraw = INITIAL_DRAW.MANUAL;
                        break;
                    default:
                        mInitialDraw = INITIAL_DRAW.RANDOM;
                }
            } catch (DataConversionException dce) {
                System.err.println(dce.getLocalizedMessage());
            }

        } else {
            mType = ROUND_TYPE.CLASSIC;
        }

        if (cup.getAttribute(StringConstants.CS_CUP_ROUNDS_COUNT) != null) {
            try {
                mRoundsCount = cup.getAttribute(StringConstants.CS_CUP_ROUNDS_COUNT).getIntValue();
            } catch (DataConversionException dce) {
                System.err.println(dce.getLocalizedMessage());
            }
        } else {
            mRoundsCount = 3;
        }

        if (cup.getAttribute(StringConstants.CS_CUP_SWISS_FOR_LOOSERS) != null) {
            try {
                mSwissForLoosers = cup.getAttribute(StringConstants.CS_CUP_SWISS_FOR_LOOSERS).getBooleanValue();
            } catch (DataConversionException dce) {
                System.err.println(dce.getLocalizedMessage());
            }
        } else {
            mSwissForLoosers = true;
        }

        if (cup.getAttribute(StringConstants.CS_CUP_SHUFFLE) != null) {
            try {
                mShuffle = cup.getAttribute(StringConstants.CS_CUP_SHUFFLE).getBooleanValue();
            } catch (DataConversionException dce) {
                System.err.println(dce.getLocalizedMessage());
            }
        } else {
            mShuffle = true;
        }
    }

    public enum ROUND_TYPE {
        CLASSIC,
        CLASSIC_THIRD,
        LOOSER,
        RANKING_MATCHES
    }

    
    public enum INITIAL_DRAW {
        RANDOM,
        RANKING,
        MANUAL
    }
    
    private INITIAL_DRAW mInitialDraw;

    public INITIAL_DRAW getInitialDraw() {
        return mInitialDraw;
    }

    public void setInitialDraw(INITIAL_DRAW mInitialDraw) {
        this.mInitialDraw = mInitialDraw;
    }
    
    // Cup type
    private ROUND_TYPE mType;
    /* Number of rounds
    if 1 => only final (2 participants)
    if 2 => Semi-final and final (4 participants)
    if 3 => quarter-final and final (8 participants)
    if N => 2^N participants
     */
    private int mRoundsCount;
    private boolean mSwissForLoosers;
    private boolean mShuffle;

    public boolean isShuffle() {
        return mShuffle;
    }

    public void setShuffle(boolean mShuffle) {
        this.mShuffle = mShuffle;
    }

    public ROUND_TYPE getType() {
        return mType;
    }

    public void setType(ROUND_TYPE mType) {
        this.mType = mType;
    }

    public int getRoundsCount() {
        return mRoundsCount;
    }

    public void setRoundsCount(int mRoundsCount) {
        this.mRoundsCount = mRoundsCount;
    }

    public boolean isSwissForLoosers() {
        return mSwissForLoosers;
    }

    public void setSwissForLoosers(boolean mSwissForLoosers) {
        this.mSwissForLoosers = mSwissForLoosers;
    }

    public ArrayList<Match> generateMatches(int roundIndex, Round r, ArrayList<Match> previousRoundMatches, ArrayList<Competitor> competitors) {
        ArrayList<Match> matches = null;
        // First case: First Round
        // (If Swiss for looser => Generate for remaining coaches but exported to class Generation)
        if (roundIndex == 0) {
            matches = generateMatchesFirstRound(competitors);
        } // Second case: Last Round
        // Generate final
        // If Third => Generate third place
        // If Looser => Generate second third place
        // If Ranking => Generate the X last games
        // (If Swiss for looser => Generate for remaining coaches but exported to class Generation)
        else {
            if (roundIndex == mRoundsCount - 1) {
                matches = generateMatchesLastRound(competitors, previousRoundMatches);
            } // Third case: intermediate round
            // Generate Classic
            // If Looser => Generate second tab
            // If Ranking => Generate the X other tabs
            // (If Swiss for looser => Generate for remaining coaches but exported to class Generation)
            else {
                matches = generateMatchesRound(roundIndex, competitors, previousRoundMatches);
            }
        }
        return matches;
    }

    ArrayList<Match> generateMatchesRound(int roundIndex, ArrayList<Competitor> competitors, ArrayList<Match> previousRoundMatches) {
        // All the table are used
        ArrayList<Match> matches = new ArrayList<>();
        ArrayList<Competitor> comps = new ArrayList<>();

        // Treat each table
        for (int i = 0; i < mTables.size(); i++) {
            comps.clear();
            CupTable table = mTables.get(i);
            int j = roundIndex - i - 1;

            CupRound r = table.mCupRounds.get(j);

            // Build the list of player
            // Winners of the previous round            
            if (j > 0) {
                CupRound r_w = table.mCupRounds.get(i);
                for (Match m : r_w.mMatchs) {
                    Competitor c1 = m.getCompetitor1();
                    Competitor c2 = m.getCompetitor2();
                    if ((c1 != null) && (c2 != null)) {
                        // Look for winner.
                        for (Match m_tmp : previousRoundMatches) {
                            if (m_tmp.getWinner() == c1) {
                                comps.add(c1);
                            } else {
                                comps.add(c2);
                            }
                        }
                    }
                }
            }

            // See Last Round function
            if (mType == ROUND_TYPE.CLASSIC_THIRD) {
                // Nothing
            }

            // Add Loosers of the previous table
            if ((mType == ROUND_TYPE.LOOSER) || (mType == ROUND_TYPE.RANKING_MATCHES)) {
                if (i > 0) {
                    CupTable prev_table = mTables.get(i - 1);
                    CupRound prev_round = prev_table.mCupRounds.get(j);

                    for (Match m : prev_round.mMatchs) {
                        Competitor c1 = m.getCompetitor1();
                        Competitor c2 = m.getCompetitor2();
                        if ((c1 != null) && (c2 != null)) {
                            // Look for winner.
                            for (Match m_tmp : previousRoundMatches) {
                                if (m_tmp.getLooser() == c1) {
                                    comps.add(c1);
                                } else {
                                    comps.add(c2);
                                }
                            }
                        }
                    }
                }
            }

            // Draw table
            if (mShuffle) {
                Collections.shuffle(comps);
            }

            // Get last Round
            CupRound cr = table.getCupRounds().get(roundIndex);

            // If this is the last round, each table owns only 1 match            
            for (Match m : cr.getMatchs()) {
                Competitor c1 = comps.get(0);
                comps.remove(0);
                Competitor c2 = comps.get(0);
                comps.remove(0);

                m.setCompetitor1(c1);
                m.setCompetitor2(c2);

                matches.add(m);
            }
        }

        return matches;
    }

    ArrayList<Match> generateMatchesFirstRound(ArrayList<Competitor> competitors) {
        // Only the first table to use        
        CupTable table = mTables.get(0);
        CupRound cr = table.mCupRounds.get(0);

        ArrayList<Competitor> comps = new ArrayList<>(competitors);

        int i = 0;
        for (Match m : cr.getMatchs()) {
            Competitor c1 = comps.get(0);
            comps.remove(0);
            Competitor c2 = comps.get(0);
            comps.remove(0);

            m.setCompetitor1(c1);
            m.setCompetitor2(c2);
        }
        return cr.getMatchs();
    }

    ArrayList<Match> generateMatchesLastRound(ArrayList<Competitor> competitors, ArrayList<Match> previousRoundMatch) {
        // All the table are used
        ArrayList<Match> matches = new ArrayList<>();
        ArrayList<Competitor> comps = new ArrayList<>();

        // Order competitors according to previous round results        
        for (int i = 0; i < previousRoundMatch.size(); i++) {
            Match m1 = previousRoundMatch.get(2 * i);
            Match m2 = previousRoundMatch.get(2 * i + 1);

            Competitor w1 = m1.getWinner();
            Competitor w2 = m2.getWinner();

            Competitor l1 = m1.getLooser();
            Competitor l2 = m2.getLooser();

            // always add Winners
            comps.add(w1);
            comps.add(w2);

            // Third place match
            if ((mType == ROUND_TYPE.CLASSIC_THIRD) && (i == 0)) {
                comps.add(l1);
                comps.add(l2);
            }

            // If ranking matches
            if (mType == ROUND_TYPE.RANKING_MATCHES) {
                comps.add(l1);
                comps.add(l2);
            }

            // If looser table, Add only the looser of the semi-finals
            if (mType == ROUND_TYPE.LOOSER) {
                if (i < 2) {
                    comps.add(l1);
                    comps.add(l2);
                }
            }
        }

        for (CupTable table : mTables) {
            // Get last Round
            CupRound cr = table.getCupRounds().get(table.getCupRounds().size() - 1);

            // If this is the last round, each table owns only 1 match            
            for (Match m : cr.getMatchs()) {
                Competitor c1 = comps.get(0);
                comps.remove(0);
                Competitor c2 = comps.get(0);
                comps.remove(0);

                m.setCompetitor1(c1);
                m.setCompetitor2(c2);

                matches.add(m);
            }
        }
        return matches;
    }
}
