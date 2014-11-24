/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom2.Element;
import tourma.utility.StringConstants;

/**
 *
 * @author Frederic Berger
 */
public class Round implements XMLExport {

    private static final Logger LOG = Logger.getLogger(Round.class.getName());

    /**
     *
     */
    private final ArrayList<Match> mMatchs;

    /**
     *
     */
    private Date mHour;

    /**
     *
     */
    private boolean mCup = false;

    /**
     *
     */
    private int mCupTour = 0;

    /**
     *
     */
    private int mCupMaxTour = 0;

    /**
     *
     */
    private boolean mLooserCup = false;

    /**
     * Default constructor
     */
    public Round() {
        mMatchs = new ArrayList<>();
    }

    @Override
    public String toString() {
        final int index = Tournament.getTournament().indexOfRound(this);
        return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("ROUND ") + (index + 1);
    }

    /**
     *
     * @param i
     * @return
     */
    public Match getMatch(int i) {
        return mMatchs.get(i);
    }
    
    /**
     * 
     * 
     * @return 
     */
    public int getMatchsCount() {
        return mMatchs.size();
    }
    
    /**
     * 
     * @param m 
     */
    public void addMatch(Match m) {
        mMatchs.add(m);
    }

    /**
     * Shuffle the matchs
     */
    public void shuffleMatchs()
    {
        Collections.shuffle(mMatchs);
    }
    
    /**
     * 
     * @param m
     * @return 
     */
    public boolean containsMatch(Match m)
    {
        return mMatchs.contains(m);
    }
    
    /**
     * Clear The match Array
     */
    public void clearMatchs()
    {
        mMatchs.clear();
    }
    
    /**
     *
     * @return
     */
    public ArrayList<CoachMatch> getCoachMatchs() {
        ArrayList<CoachMatch> tmp;
        tmp = new ArrayList<>();
        if (mMatchs.size() > 0) {
            if (mMatchs.get(0) instanceof CoachMatch) {
                for (Match mMatch : mMatchs) {
                    CoachMatch m;
                    m = (CoachMatch) mMatch;
                    tmp.add(m);
                }
            } else {
                for (Match mMatch : mMatchs) {
                    TeamMatch m;
                    m = (TeamMatch) mMatch;
                    for (int cpt = 0; cpt < m.getMatchCount(); cpt++) {
                        CoachMatch mMatch1 = m.getMatch(cpt);
                        tmp.add(mMatch1);
                    }
                }
            }
        }
        return tmp;
    }

    /**
     *
     * @param data
     */
    public void setHour(String data) {
        final SimpleDateFormat format = new SimpleDateFormat(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("DD/MM/YYYY HH:MM:SS"), Locale.getDefault());
        try {
            mHour = format.parse(data);
        } catch (ParseException ex) {
            Logger.getLogger(Round.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Set round hour as current one
     */
    public void setCurrentHour() {
       final Calendar cal = Calendar.getInstance();
        mHour=cal.getTime();
    }

    /**
     *
     * @return
     */
    @SuppressWarnings("ReturnOfDateField")
    //@SuppressFBWarnings({"",""})
    public Date getHour() {
        return (Date)mHour.clone();
    }

    /**
     *
     * @return
     */
    @Override
    public Element getXMLElement() {
        final SimpleDateFormat format = new SimpleDateFormat(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("DD/MM/YYYY HH:MM:SS"), Locale.getDefault());
        final Element round = new Element(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("ROUND"));
        round.setAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("DATE"), format.format(this.getHour()));

        round.setAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("LOOSERCUP"), Boolean.toString(isLooserCup()));
        round.setAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("CUP"), Boolean.toString(isCup()));
        round.setAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("TOUR"), Integer.toString(getCupTour()));
        round.setAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("MAXTOUR"), Integer.toString(getCupMaxTour()));

        for (Match mMatch : this.mMatchs) {
            final Element match = mMatch.getXMLElement();
            round.addContent(match);
        }

        return round;
    }

    /**
     *
     * @param round
     */
    @Override
    public void setXMLElement(final Element round) {
        final SimpleDateFormat format = new SimpleDateFormat(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("DD/MM/YYYY HH:MM:SS"), Locale.getDefault());

        final String date = round.getAttributeValue(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("DATE"));
        try {
            mHour=format.parse(date);

        } catch (ParseException e) {
            LOG.log(Level.FINE, e.getLocalizedMessage());
        }

        try {
            setLooserCup(Boolean.parseBoolean(round.getAttributeValue(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("LOOSERCUP"))));
            setCup(Boolean.parseBoolean(round.getAttributeValue(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("CUP"))));
            setCupTour(Integer.parseInt(round.getAttributeValue(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("TOUR"))));
            setCupMaxTour(Integer.parseInt(round.getAttributeValue(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("MAXTOUR"))));
        } catch (NumberFormatException e) {
            LOG.log(Level.FINE, e.getLocalizedMessage());
        }

        final List<Element> matchs = round.getChildren(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("MATCH"));
        final Iterator<Element> k = matchs.iterator();
        this.mMatchs.clear();

        while (k.hasNext()) {
            final Element match = k.next();
            Match m;
            if ((Tournament.getTournament().getParams().isTeamTournament())
                    && (Tournament.getTournament().getParams().getTeamPairing() == ETeamPairing.TEAM_PAIRING)) {
                m = new TeamMatch(this);
            } else {
                m = new CoachMatch(this);
            }
            m.setXMLElement(match);
            this.mMatchs.add(m);
        }
    }

    /**
     * @return the mCup
     */
    public boolean isCup() {
        return mCup;
    }

    /**
     * @param mCup the mCup to set
     */
    public void setCup(boolean mCup) {
        this.mCup = mCup;
    }

    /**
     * @return the mCupTour
     */
    public int getCupTour() {
        return mCupTour;
    }

    /**
     * @param mCupTour the mCupTour to set
     */
    public void setCupTour(int mCupTour) {
        this.mCupTour = mCupTour;
    }

    /**
     * @return the mCupMaxTour
     */
    public int getCupMaxTour() {
        return mCupMaxTour;
    }

    /**
     * @param mCupMaxTour the mCupMaxTour to set
     */
    public void setCupMaxTour(int mCupMaxTour) {
        this.mCupMaxTour = mCupMaxTour;
    }

    /**
     * @return the mLooserCup
     */
    public boolean isLooserCup() {
        return mLooserCup;
    }

    /**
     * @param mLooserCup the mLooserCup to set
     */
    public void setLooserCup(boolean mLooserCup) {
        this.mLooserCup = mLooserCup;
    }

    /**
     * 
     * @param i 
     */
    public void removeMatch(int i)
    {
       mMatchs.remove(i);
    }
    
    /**
     * 
     * @param i 
     */
    public void removeMatch(Match i)
    {
       mMatchs.remove(i);
    }
 
}
