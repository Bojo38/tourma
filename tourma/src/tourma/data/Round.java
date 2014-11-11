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
    protected ArrayList<Match> mMatchs;

    /**
     *
     */
    protected Date mHour;

    /**
     *
     */
    public boolean mCup = false;

    /**
     *
     */
    public int mCupTour = 0;

    /**
     *
     */
    public int mCupMaxTour = 0;

    /**
     *
     */
    public boolean mLooserCup = false;

    /**
     *
     */
    public Round() {
        mMatchs = new ArrayList<>();
    }

    @Override
    public String toString() {
        final int index = Tournament.getTournament().getRounds().indexOf(this);
        return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("ROUND ") + (index + 1);
    }

    /**
     *
     * @return
     */
    public ArrayList<Match> getMatchs() {
        return mMatchs;
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
                for (int i = 0; i < mMatchs.size(); i++) {
                    CoachMatch m;
                    m = (CoachMatch) mMatchs.get(i);
                    tmp.add(m);
                }
            } else {
                for (int i = 0; i < mMatchs.size(); i++) {
                    TeamMatch m;
                    m = (TeamMatch) mMatchs.get(i);
                    for (int j = 0; j < m.mMatchs.size(); j++) {
                        tmp.add(m.mMatchs.get(j));
                    }
                }
            }
        }
        return tmp;
    }

    /**
     *
     * @param heure
     */
    public void setHour(final Date heure) {
        mHour= heure;
    }

    /**
     *
     * @return
     */
    public Date getHour() {
        return mHour;
    }

    /**
     *
     * @return
     */
    @Override
    public Element getXMLElement() {
        final SimpleDateFormat format = new SimpleDateFormat(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("DD/MM/YYYY HH:MM:SS"), Locale.getDefault());
        final Element round = new Element(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("ROUND"));
        round.setAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("DATE"), format.format(this.mHour));

        round.setAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("LOOSERCUP"), Boolean.toString(mLooserCup));
        round.setAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("CUP"), Boolean.toString(mCup));
        round.setAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("TOUR"), Integer.toString(mCupTour));
        round.setAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("MAXTOUR"), Integer.toString(mCupMaxTour));


        for (int j = 0; j < this.mMatchs.size(); j++) {
            final Element match = mMatchs.get(j).getXMLElement();
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
            this.mHour = format.parse(date);

        } catch (ParseException e) {
        }

        try {
            mLooserCup = Boolean.parseBoolean(round.getAttributeValue(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("LOOSERCUP")));
            mCup = Boolean.parseBoolean(round.getAttributeValue(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("CUP")));
            mCupTour = Integer.parseInt(round.getAttributeValue(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("TOUR")));
            mCupMaxTour = Integer.parseInt(round.getAttributeValue(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("MAXTOUR")));
        } catch (Exception e) {
        }

        final List matchs = round.getChildren(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("MATCH"));
        final Iterator k = matchs.iterator();
        this.mMatchs.clear();

        while (k.hasNext()) {
            final Element match = (Element) k.next();
            Match m;
            if ((Tournament.getTournament().getParams().mTeamTournament)
                    && (Tournament.getTournament().getParams().mTeamPairing == 1)) {
                m = new TeamMatch(this);
            } else {
                m = new CoachMatch(this);
            }
            m.setXMLElement(match);
            this.mMatchs.add(m);
        }
    }
}
