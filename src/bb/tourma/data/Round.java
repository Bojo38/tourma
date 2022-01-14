/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bb.tourma.data;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom.Element;
import bb.tourma.languages.Translate;
import bb.tourma.utility.StringConstants;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import org.apache.xerces.impl.dv.util.Base64;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Frederic Berger
 */
public class Round implements IXMLExport, Serializable {

    private static final Logger LOG = Logger.getLogger(Round.class.getName());

    protected static AtomicInteger sGenUID = new AtomicInteger(0);
    protected int UID = sGenUID.incrementAndGet();

    public int getUID() {
        return UID;
    }

    public void setUID(int UID) {
        this.UID = UID;
    }

    protected LocalDateTime createDateTime;

    protected LocalDateTime updateDateTime;

    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(LocalDateTime createDateTime) {
        this.createDateTime = createDateTime;
    }

    public LocalDateTime getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime(LocalDateTime updateDateTime) {
        this.updateDateTime = updateDateTime;
    }
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
    //private boolean mLooserCup = false;
    //private boolean mThirdPlace = false;
    private double mMinBonus = 1.0;
    private double mMaxBonus = 1.0;

    boolean mRankingUpdated = false;

    public boolean isRankingUpdated() {
        return mRankingUpdated;
    }

    public Rankings getRankings(boolean ro) {
        if (mRankingToUpdate) {
            mRankings.update();
            mRankingsRoundOnly.update();
            mRankingToUpdate = false;
            mRankingUpdated = true;
        }
        if (ro) {
            return mRankingsRoundOnly;
        } else {
            return mRankings;
        }
    }
    /**
     * Rankings
     */
    Rankings mRankings = new Rankings(false);
    Rankings mRankingsRoundOnly = new Rankings(true);

    protected boolean mFastCompare = true;

    public boolean isFastCompare() {
        return mFastCompare;
    }

    public void setFastCompare(boolean mFastCompare) {
        this.mFastCompare = mFastCompare;
    }

    /**
     * Default constructor
     */
    public Round(int rNumber, Tournament tour) {
        mMatchs = new ArrayList<>();

        try {
            mRankings.createRankings(rNumber, tour);
            mRankingsRoundOnly.createRankings(rNumber, tour);
        } catch (java.lang.IndexOutOfBoundsException ite) {
            ite.printStackTrace();
        }
    }

    @Override
    public String toString() {
        final int index = Tournament.getTournament().getRoundIndex(this);
        return Translate.translate(Translate.CS_Round_) + (index + 1);
    }

    public double getMinBonus() {
        return mMinBonus;
    }

    public double getMaxBonus() {
        return mMaxBonus;
    }

    public void setMinBonus(double v) {
        mMinBonus = v;
        updated = true;
    }

    public void setMaxBonus(double v) {
        mMaxBonus = v;
        updated = true;
    }

    public double getCoef(Match m) {
        double coef;
        int index = this.indexOf(m) + 1;
        double gap = this.getMaxBonus() - this.getMinBonus();
        double steps = gap / this.getMatchsCount();
        coef = this.getMinBonus() + steps * index;
        return coef;
    }

    public void pull(Round round) {
        this.UID = round.UID;
        this.mCup = round.mCup;
        this.mCupMaxTour = round.mCupMaxTour;
        this.mHour = round.mHour;
//        this.mLooserCup = round.mLooserCup;
        this.mMaxBonus = round.mMaxBonus;
        this.mMinBonus = round.mMinBonus;
//        this.mThirdPlace = round.mThirdPlace;

        for (Match match : round.mMatchs) {
            boolean bFound = false;
            for (Match local : mMatchs) {
                if (match.getUID() == local.getUID()) {
                    local.pull(match);
                    bFound = true;
                    break;
                }
            }

            if (!bFound) {
                Match local = null;
                if (match instanceof TeamMatch) {
                    local = new TeamMatch(this);
                }
                if (match instanceof CoachMatch) {
                    local = new CoachMatch(this);
                }
                if (local != null) {
                    local.pull(match);
                    mMatchs.add(local);
                }
            }
        }
    }

    public boolean isUpdated() {

        for (Match m : mMatchs) {
            if (m.isUpdated()) {
                updated = true;
                break;
            }
        }

        return updated;
    }

    public void setUpdated(boolean updated) {
        this.updated = updated;
        for (Match m : mMatchs) {
            m.setUpdated(updated);
        }
    }
    protected boolean updated = false;

    public void push(Round round) {
        if (round.isUpdated()) {
            this.UID = round.UID;

            for (Match match : round.mMatchs) {
                boolean bFound = false;
                for (Match local : mMatchs) {
                    if (match.getUID() == local.getUID()) {
                        local.push(match);
                        bFound = true;
                        break;
                    }
                }

                /*if (!bFound) {
                Match local=null;
                if (match instanceof TeamMatch) {
                    local = new TeamMatch(this);
                }
                if (match instanceof CoachMatch) {
                    local = new CoachMatch(this);
                }
                if (local != null) {
                    local.pull(match);
                    mMatchs.add(local);
                }
            }*/
            }
        }
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
        updated = true;
    }

    public int indexOf(Match m) {
        for (int i = 0; i < mMatchs.size(); i++) {
            Match match = mMatchs.get(i);
            if (match instanceof TeamMatch) {
                if (m instanceof TeamMatch) {
                    if (m == match) {
                        return i;
                    }
                }
                if (m instanceof CoachMatch) {
                    if (((TeamMatch) match).containsMatch((CoachMatch) m)) {
                        return i;
                    }
                }
            }
            if (match instanceof CoachMatch) {
                if (match == m) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * Shuffle the matchs
     */
    public void shuffleMatchs() {
        Collections.shuffle(mMatchs);
    }

    /**
     *
     * @param m
     * @return
     */
    public boolean containsMatch(Match m) {

        if (m instanceof CoachMatch) {
            return getCoachMatchs().contains((CoachMatch) m);
        } else {
            return mMatchs.contains(m);
        }
    }

    public boolean containsCoachMatch(CoachMatch m) {
        for (Match match : mMatchs) {
            if (match instanceof CoachMatch) {
                if (m == match) {
                    return true;
                }
            } else {
                if (match instanceof TeamMatch) {
                    if (((TeamMatch) match).containsMatch(m)) {
                        return true;
                    }
                }
            }

        }
        return false;
    }

    /**
     * Clear The match Array
     */
    public void clearMatchs() {
        mMatchs.clear();
        updated = true;
    }

    ArrayList<CoachMatch> tmpCoachMatches = null;

    public void resetTmpCoachMatches() {
        tmpCoachMatches = null;
    }

    /**
     *
     * @return
     */
    public ArrayList<CoachMatch> getCoachMatchs() {

        if (mMatchs.size() > 0) {
            if (mMatchs.get(0) instanceof CoachMatch) {
                if (tmpCoachMatches != null) {
                    if (mMatchs.size() != tmpCoachMatches.size()) {
                        tmpCoachMatches = null;
                    }
                }
            } else {
                if (tmpCoachMatches != null) {
                    if (mMatchs.size() * Tournament.getTournament().getParams().getTeamMatesNumber() != tmpCoachMatches.size()) {
                        tmpCoachMatches = null;
                    }
                }
            }
        }

        if (tmpCoachMatches == null) {
            tmpCoachMatches = new ArrayList<>();
            if (mMatchs.size() > 0) {
                if (mMatchs.get(0) instanceof CoachMatch) {
                    for (Match mMatch : mMatchs) {
                        CoachMatch m;
                        m = (CoachMatch) mMatch;
                        tmpCoachMatches.add(m);
                    }
                } else {
                    for (Match mMatch : mMatchs) {
                        TeamMatch m;
                        m = (TeamMatch) mMatch;
                        for (int cpt = 0; cpt < m.getMatchCount(); cpt++) {
                            CoachMatch mMatch1 = m.getMatch(cpt);
                            tmpCoachMatches.add(mMatch1);
                        }
                    }
                }
            }
        }
        return tmpCoachMatches;
    }

    /**
     *
     * @param data
     */
    public void setHour(String data) {
        final SimpleDateFormat format = new SimpleDateFormat(Translate.translate("DD/MM/YYYY HH:MM:SS"), Locale.getDefault());
        try {
            mHour = format.parse(data);
        } catch (ParseException ex) {
            Logger.getLogger(Round.class.getName()).log(Level.SEVERE, null, ex);
        }
        updated = true;
    }

    /**
     * Set round hour as current one
     */
    public void setCurrentHour() {
        final Calendar cal = Calendar.getInstance();
        mHour = cal.getTime();
        updated = true;
    }

    /**
     *
     * @return
     */
    @SuppressWarnings("ReturnOfDateField")
    //@SuppressFBWarnings({"",""})
    public Date getHour() {
        return (Date) mHour.clone();
    }

    public void setRoundIndex(Tournament tour, int i) {
        mRankings.setRoundIndex(tour, i);
        mRankingsRoundOnly.setRoundIndex(tour, i);
    }

    /**
     *
     * @return
     */
    @Override
    public Element getXMLElement() {

        if (mRankingToUpdate) {
            mRankings.update();
            mRankingsRoundOnly.update();
        }

        final SimpleDateFormat format = new SimpleDateFormat(Translate.translate("DD/MM/YYYY HH:MM:SS"), Locale.getDefault());
        final Element round = new Element(StringConstants.CS_ROUND);
        if (mHour == null) {
            Calendar cal = Calendar.getInstance();
            mHour = cal.getTime();
        }
        round.setAttribute(StringConstants.CS_DATE, format.format(this.getHour()));

        round.setAttribute(StringConstants.CS_CUP, Boolean.toString(isCup()));
        round.setAttribute(StringConstants.CS_TOUR, Integer.toString(getCupTour()));
        round.setAttribute(StringConstants.CS_MAXTOUR, Integer.toString(getCupMaxTour()));

        round.setAttribute(StringConstants.CS_MINCOEF, Double.toString(getMinBonus()));
        round.setAttribute(StringConstants.CS_MAXCOEF, Double.toString(getMaxBonus()));

        for (Match mMatch : this.mMatchs) {
            final Element match = mMatch.getXMLElement();
            round.addContent(match);
        }

        round.addContent(mRankings.getXMLElement());
        mRankingsRoundOnly.setRoundOnly(true);
        round.addContent(mRankingsRoundOnly.getXMLElement());

        return round;
    }

    /**
     *
     * @return
     */
    public Element getXMLElementForDisplay() {
        final SimpleDateFormat format = new SimpleDateFormat(Translate.translate("DD/MM/YYYY HH:MM:SS"), Locale.getDefault());
        final Element round = new Element(StringConstants.CS_ROUND);
        round.setAttribute(StringConstants.CS_DATE, format.format(this.getHour()));

//        round.setAttribute(StringConstants.CS_LOOSERCUP, Boolean.toString(isLooserCup()));
//        round.setAttribute(StringConstants.CS_THIRDPLACE, Boolean.toString(isThirdPlace()));
        round.setAttribute(StringConstants.CS_CUP, Boolean.toString(isCup()));
        round.setAttribute(StringConstants.CS_TOUR, Integer.toString(getCupTour()));
        round.setAttribute(StringConstants.CS_MAXTOUR, Integer.toString(getCupMaxTour()));
        round.setAttribute(StringConstants.CS_INDEX, Integer.toString(Tournament.getTournament().getRoundIndex(this)));
        round.setAttribute(StringConstants.CS_MINCOEF, Double.toString(getMinBonus()));
        round.setAttribute(StringConstants.CS_MAXCOEF, Double.toString(getMaxBonus()));

        for (Match mMatch : this.mMatchs) {
            final Element match = mMatch.getXMLElementForDisplay();
            round.addContent(match);
        }

        round.addContent(mRankings.getXMLElement());

        return round;
    }

    public void update() {
        mRankings.setRoundOnly(false);
        mRankings.update();
        mRankingsRoundOnly.setRoundOnly(true);
        mRankingsRoundOnly.update();

        mRankingToUpdate = false;
        mRankingUpdated = true;
    }

    /**
     *
     * @param round
     */
    @Override
    public void setXMLElement(final Element round) {
        final SimpleDateFormat format = new SimpleDateFormat(Translate.translate("DD/MM/YYYY HH:MM:SS"), Locale.getDefault());

        final String date = round.getAttributeValue(StringConstants.CS_DATE);
        try {
            mHour = format.parse(date);

        } catch (ParseException e) {
            LOG.log(Level.FINE, e.getLocalizedMessage());
        }

        try {
            setCup(Boolean.parseBoolean(round.getAttributeValue(StringConstants.CS_CUP)));
            setCupTour(Integer.parseInt(round.getAttributeValue(StringConstants.CS_TOUR)));
            setCupMaxTour(Integer.parseInt(round.getAttributeValue(StringConstants.CS_MAXTOUR)));
        } catch (NumberFormatException e) {
            LOG.log(Level.FINE, e.getLocalizedMessage());
        }

        final List<Element> matchs = round.getChildren(StringConstants.CS_MATCH);
        final Iterator<Element> k = matchs.iterator();
        this.mMatchs.clear();

        while (k.hasNext()) {
            final Element match = k.next();
            Match m = null;
            boolean teamTour = Tournament.getTournament().getParams().isTeamTournament();
            ETeamPairing pairing = Tournament.getTournament().getParams().getTeamPairing();
            try {
                if (teamTour
                        && (pairing == ETeamPairing.TEAM_PAIRING)) {
                    m = new TeamMatch(this);
                } else {
                    m = new CoachMatch(this);
                }
                m.setXMLElement(match);
                this.mMatchs.add(m);
            } catch (NullPointerException ne) {
                if (m instanceof TeamMatch) {
                    // CoachMatches without TeamMatch (Old Format)
                    m = new CoachMatch(this);
                    m.setXMLElement(match);

                    // Try to find a TeamMatch among already stored match
                    boolean found = false;
                    for (Match m_cpt : this.mMatchs) {
                        if (m_cpt instanceof TeamMatch) {
                            TeamMatch tm = (TeamMatch) m_cpt;
                            Coach c1 = (Coach) m.getCompetitor1();
                            Coach c2 = (Coach) m.getCompetitor2();
                            if (tm.getCompetitor1().equals(c1.getTeamMates())) {
                                found = true;
                                tm.addMatch((CoachMatch) m);
                                tm.recomputeValues();
                                break;
                            }
                            if (tm.getCompetitor1().equals(c2.getTeamMates())) {
                                // Swap C1 and C2
                                ((CoachMatch) m).switchCoachs();
                                found = true;
                                tm.addMatch((CoachMatch) m);
                                tm.recomputeValues();
                                break;
                            }
                        }
                    }
                    if (!found) {
                        TeamMatch tm = new TeamMatch(this);
                        Team t1 = ((Coach) m.getCompetitor1()).getTeamMates();
                        Team t2 = ((Coach) m.getCompetitor2()).getTeamMates();
                        tm.setCompetitor1(t1);
                        tm.setCompetitor2(t2);
                        t1.addMatch(tm);
                        t2.addMatch(tm);
                        tm.addMatch((CoachMatch) m);
                        tm.recomputeValues();
                        mMatchs.add(tm);
                    }
                }
            }
        }

        try {
            mRankings.setXMLElement(round.getChild(StringConstants.CS_RANKINGS));
            mRankings.setRoundOnly(false);
            mRankingsRoundOnly.setXMLElement(round.getChild(StringConstants.CS_RANKINGS_RO));
            mRankingsRoundOnly.setRoundOnly(true);

        } catch (NullPointerException ne) {
            ne.printStackTrace();
        }

        for (Match match : mMatchs) {
            match.recomputeValues();
        }
    }

    /**
     *
     * @param round
     */
    public void setXMLElementForDisplay(final Element round) {
        final SimpleDateFormat format = new SimpleDateFormat(Translate.translate("DD/MM/YYYY HH:MM:SS"), Locale.getDefault());

        final String date = round.getAttributeValue(StringConstants.CS_DATE);
        try {
            mHour = format.parse(date);

        } catch (ParseException e) {
            LOG.log(Level.FINE, e.getLocalizedMessage());
        }

        try {
//            setLooserCup(Boolean.parseBoolean(round.getAttributeValue(StringConstants.CS_LOOSERCUP)));
            setCup(Boolean.parseBoolean(round.getAttributeValue(StringConstants.CS_CUP)));
            setCupTour(Integer.parseInt(round.getAttributeValue(StringConstants.CS_TOUR)));
            setCupMaxTour(Integer.parseInt(round.getAttributeValue(StringConstants.CS_MAXTOUR)));
            setMinBonus(Double.parseDouble(round.getAttributeValue(StringConstants.CS_MINCOEF)));
            setMaxBonus(Double.parseDouble(round.getAttributeValue(StringConstants.CS_MAXCOEF)));
//            setThirdPlace(Boolean.parseBoolean(round.getAttributeValue(StringConstants.CS_THIRDPLACE)));
        } catch (NumberFormatException e) {
            LOG.log(Level.FINE, e.getLocalizedMessage());
        }

        int roundIndex = Integer.parseInt(round.getAttributeValue(StringConstants.CS_INDEX));
        int i = 0;
        Tournament tour = Tournament.getTournament();
        while (tour.getRoundsCount() > 0) {
            tour.removeRound(0);
        }
        while (i < roundIndex) {
            tour.addRound(new Round(tour.getRoundsCount(), tour));
            i++;
        }

        final List<Element> matchs = round.getChildren(StringConstants.CS_MATCH);
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
            m.setXMLElementForDisplay(match);
            this.mMatchs.add(m);
        }

        try {
            mRankings.setXMLElement(round.getChild(StringConstants.CS_RANKINGS));
        } catch (NullPointerException ne) {
            ne.printStackTrace();
        }

        Tournament.getTournament().addRound(this);
    }

    boolean mRankingToUpdate = false;

    public void setRankingsToUpdate() {
        mRankingToUpdate = true;
        mRankingUpdated = false;
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
        updated = true;
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
        updated = true;
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
        updated = true;
    }

    /**
     * @return the mLooserCup
     */
    /*public boolean isLooserCup() {
        return mLooserCup;
    }*/
    /**
     * @param mLooserCup the mLooserCup to set
     */
    /*public void setLooserCup(boolean mLooserCup) {
        this.mLooserCup = mLooserCup;
        updated = true;
    }*/
    /**
     *
     * @param i
     */
    public void removeMatch(int i) {
        mMatchs.remove(i);
        updated = true;
    }

    /**
     *
     * @param i
     */
    public void removeMatch(Match i) {
        mMatchs.remove(i);
        updated = true;
    }

    public boolean equals(final Object obj) {

        boolean result;
        result = false;
        if (obj instanceof Round) {
            Round r = (Round) obj;
            result = this.mCup == r.mCup;
            result &= this.mCupTour == r.mCupTour;
            result &= this.mCupMaxTour == r.mCupMaxTour;
            result &= this.mHour.toString().equals(r.mHour.toString());
            //result &= this.mLooserCup == r.mLooserCup;
            result &= Math.abs(this.mMaxBonus - r.mMaxBonus) < 0.0001;
            result &= Math.abs(this.mMinBonus - r.mMinBonus) < 0.0001;
            result &= this.mMatchs.size() == r.mMatchs.size();
            if (result) {
                for (int i = 0; i < this.mMatchs.size(); i++) {
                    Match m = mMatchs.get(i);
                    Match mr = r.mMatchs.get(i);
                    boolean tmp = m.isFastCompare();
                    m.setFastCompare(mFastCompare);
                    result &= m.equals(mr);
                    m.setFastCompare(tmp);

                }
            }
        }
        return result;
    }

    /*public boolean isThirdPlace() {
        return mThirdPlace;
    }

    public void setThirdPlace(boolean b) {
        mThirdPlace = b;
        updated = true;
    }*/
    public void recomputeMatchs() {
        for (int i = 0; i < mMatchs.size(); i++) {
            mMatchs.get(i).recomputeValues();
        }
    }

    public boolean allMatchesEntered() {
        // Shall check if all the matches hav been filled, conceeded or refused
        for (int i = 0; i < getMatchsCount(); i++) {
            Match m = getMatch(i);
            if (!m.isEntered()) {
                return false;
            }
        }
        return true;

    }

    public void setMatchPosition(Match m, int position) {
        mMatchs.remove(m);
        if (position > mMatchs.size()) {
            mMatchs.add(m);
        } else {
            mMatchs.add(position, m);
        }
    }

    boolean mLocked = false;

    public void updateFromJSON(JSONObject object) throws IOException {

        Object obj = object.get("createDateTime");
        if (obj != JSONObject.NULL) {
            createDateTime = LocalDateTime.parse(object.get("createDateTime").toString());
        }
        obj = object.get("updateDateTime");
        if (obj != JSONObject.NULL) {
            updateDateTime = LocalDateTime.parse(object.get("updateDateTime").toString());
        }

        this.updated = object.getBoolean("updated");
        this.mCup = object.getBoolean("cup");
        this.mLocked = object.getBoolean("locked");
        this.mRankingUpdated = object.getBoolean("rankingUpdated");
        this.mFastCompare = object.getBoolean("fastCompare");

        this.mCupTour = object.getInt("cupTour");
        this.mCupMaxTour = object.getInt("cupMaxTour");

        this.mMaxBonus = object.getInt("maxBonus");
        this.mMinBonus = object.getInt("minBonus");

        obj = object.get("hour");
        if (obj != JSONObject.NULL) {
            String d=obj.toString();
            d=d.substring(0, d.indexOf("+"));
            LocalDateTime ldt=LocalDateTime.parse(d);
            mHour = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
        }

    }

    public JSONObject getJSON() throws IOException {

        JSONObject json = new JSONObject();
        if (createDateTime == null) {
            createDateTime = LocalDateTime.now();
        }
        if (updateDateTime == null) {
            updateDateTime = LocalDateTime.now();
        }

        json.put("createDateTime", createDateTime.toString());
        json.put("updateDateTime", updateDateTime.toString());

        json.put("updated", updated);
        json.put("cup", mCup);
        json.put("locked", mLocked);

        json.put("rankingUpdated", mRankingUpdated);
        json.put("fastCompare", mFastCompare);

        json.put("cupTour", mCupTour);
        json.put("cupMaxTour", mCupMaxTour);

        json.put("maxBonus", mMaxBonus);
        json.put("minBonus", mMinBonus);

        json.put("hour", mHour);

        return json;
    }
}
