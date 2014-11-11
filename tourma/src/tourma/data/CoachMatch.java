/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.jdom2.Attribute;
import org.jdom2.DataConversionException;
import org.jdom2.Element;
import tourma.MainFrame;

/**
 *
 * @author Frederic Berger
 */
public class CoachMatch extends Match {
    /* public static Team getTeamMatchWinner(final int teamMatesNumber, final int matchIndex, final ArrayList<CoachMatch> matchs) {
    final Criteria td = Tournament.getTournament().getParams().mCriterias.get(0);
    CoachMatch m = matchs.get(matchIndex * teamMatesNumber);
    final Team team1 = m.mCoach1.mTeamMates;
    final Team team2 = m.mCoach2.mTeamMates;
    Team winner;
    int nbVictory = 0;
    int nbLost = 0;
    for (int j = 0; j < teamMatesNumber; j++) {
    m = matchs.get(matchIndex * teamMatesNumber + j);
    if (m.mValues.get(td).mValue1 > m.mValues.get(td).mValue2) {
    nbVictory++;
    } else {
    if (m.mValues.get(td).mValue1 < m.mValues.get(td).mValue2) {
    nbLost++;
    }
    }
    }
    if (team1 == Team.sNullTeam) {
    winner = team2;
    } else {
    if (team2 == Team.sNullTeam) {
    winner = team1;
    } else {
    if (nbVictory > nbLost) {
    winner = team1;
    } else {
    if (nbVictory < nbLost) {
    winner = team2;
    } else {
    if (((int) Math.random()) % 2 == 0) {
    winner = team1;
    } else {
    winner = team2;
    }
    }
    }
    }
    }
    return winner;
    }*/
    private static final Logger LOG = Logger.getLogger(CoachMatch.class.getName());

    /**
     *
     */
    private RosterType mRoster1;

    /**
     *
     */
    private RosterType mRoster2;

    /**
     *
     */
    public HashMap<Criteria, Value> mValues;

    /**
     *
     */
    private Substitute mSubstitute1;

    /**
     *
     */
    private Substitute mSubstitute2;

    /**
     *
     */
    private boolean refusedBy1 = false;

    /**
     *
     */
    private boolean refusedBy2 = false;

    /**
     *
     */
    private boolean concedeedBy1 = false;

    /**
     *
     */
    private boolean concedeedBy2 = false;

    /**
     *
     * @param round
     */
    public CoachMatch(Round round) {
        super(round);
        mValues = new HashMap<>();

        final int size = Tournament.getTournament().mParams.mCriterias.size();
        for (int i = 0; i < size; i++) {
            final Criteria crit = Tournament.getTournament().mParams.mCriterias.get(i);
            final Value val = new Value(crit);
            if (i == 0) {
                val.mValue1 = -1;
                val.mValue2 = -1;
            } else {
                val.mValue1 = 0;
                val.mValue2 = 0;
            }
            mValues.put(crit, val);
        }

        mRound = round;
    }

    /**
     *
     * @return
     */
    @Override
    public Element getXMLElement() {
        final Element match = new Element(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("MATCH"));
        match.setAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("COACH1"), this.mCompetitor1.mName);
        match.setAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("COACH2"), this.mCompetitor2.mName);

        match.setAttribute("RefusedBy1", Boolean.toString(isRefusedBy1()));
        match.setAttribute("RefusedBy2", Boolean.toString(isRefusedBy2()));
        match.setAttribute("ConcedeedBy1", Boolean.toString(isConcedeedBy1()));
        match.setAttribute("ConcedeedBy2", Boolean.toString(isConcedeedBy2()));

        for (int k = 0; k < Tournament.getTournament().getParams().mCriterias.size(); k++) {
            final Value val = this.mValues.get(Tournament.getTournament().getParams().mCriterias.get(k));
            final Element value = new Element(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("VALUE"));
            value.setAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("NAME"), val.mCriteria.mName);
            value.setAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("VALUE1"), Integer.toString(val.mValue1));
            value.setAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("VALUE2"), Integer.toString(val.mValue2));

            value.setAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("VALUE1"), Integer.toString(val.mValue1));
            value.setAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("VALUE2"), Integer.toString(val.mValue2));
            match.addContent(value);
        }

        if (this.getRoster1() != null) {
            String key1 = java.util.ResourceBundle.getBundle("tourma/languages/language").getString("ROSTER1");
            match.setAttribute(key1, this.getRoster1().mName);
        }
        if (this.getRoster2() != null) {
            String key2 = java.util.ResourceBundle.getBundle("tourma/languages/language").getString("ROSTER2");
            match.setAttribute(key2, this.getRoster2().mName);
        }

        if (getSubstitute1() != null) {
            match.addContent(getSubstitute1().getXMLElement());
        }
        if (getSubstitute2() != null) {
            match.addContent(getSubstitute2().getXMLElement());
        }
        return match;
    }

    /**
     *
     * @param match
     */
    @Override
    public void setXMLElement(final Element match) {
        try {
            final String c1 = match.getAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("COACH1")).getValue();
            final String c2 = match.getAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("COACH2")).getValue();
            this.mCompetitor1 = Coach.getCoach(c1);
            this.mCompetitor2 = Coach.getCoach(c2);
            if (this.mCompetitor1==null)
            {
                this.mCompetitor1=Coach.getNullCoach();
            }
            if (this.mCompetitor2==null)
            {
                this.mCompetitor2=Coach.getNullCoach();
            }

            try {
                setRefusedBy1(match.getAttribute("RefusedBy1").getBooleanValue());
                setRefusedBy2(match.getAttribute("RefusedBy2").getBooleanValue());
                setConcedeedBy1(match.getAttribute("ConcedeedBy1").getBooleanValue());
                setConcedeedBy2(match.getAttribute("ConcedeedBy2").getBooleanValue());
            } catch (Exception e) {
                System.err.println(e.getLocalizedMessage());
            }

            if (((Coach) mCompetitor1)!=null)
            {
                if (mCompetitor1.mMatchs!=null)
                {
                mCompetitor1.mMatchs.add(this);
                }
            }
            else
            {
                mCompetitor1=Coach.getNullCoach();
            }
            
            if (((Coach) mCompetitor2)!=null)
            {
                if (((Coach) mCompetitor2).mMatchs!=null)
                {
                ((Coach) mCompetitor2).mMatchs.add(this);
                }
            }
            else
            {
                mCompetitor2=Coach.getNullCoach();
            }
            

            final List values = match.getChildren(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("VALUE"));
            final Iterator v = values.iterator();

            while (v.hasNext()) {
                final Element val = (Element) v.next();
                Criteria crit = null;

                for (int cpt = 0; cpt < Tournament.getTournament().getParams().mCriterias.size(); cpt++) {
                    final Criteria criteria = Tournament.getTournament().getParams().mCriterias.get(cpt);
                    final String tmp = val.getAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("NAME")).getValue();

                    if (criteria.mName.equals(tmp)) {
                        crit = criteria;
                    }
                }
                final Value value = new Value(crit);
                value.mValue1 = val.getAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("VALUE1")).getIntValue();
                value.mValue2 = val.getAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("VALUE2")).getIntValue();
                this.mValues.put(crit, value);
            }

            String key1 = java.util.ResourceBundle.getBundle("tourma/languages/language").getString("ROSTER1");
            Attribute att1 = match.getAttribute(key1);
            if (att1 != null) {
                this.setRoster1(RosterType.mRosterTypes.get(att1.getValue()));
            }
            String key2 = java.util.ResourceBundle.getBundle("tourma/languages/language").getString("ROSTER2");
            Attribute att2 = match.getAttribute(key2);
            if (att2 != null) {
                this.setRoster2(RosterType.mRosterTypes.get(att2.getValue()));
            }

            final List subs = match.getChildren("Subtitution");
            final Iterator it = subs.iterator();
            while (it.hasNext()) {
                final Element sub = (Element) it.next();
                Substitute s = new Substitute();
                s.setXMLElement(sub);
                s.mMatch = this;
                if (s.mTitular == mCompetitor1) {
                    this.setSubstitute1(s);
                }
                if (s.mTitular == mCompetitor2) {
                    this.setSubstitute2(s);
                }
            }

        } catch (DataConversionException dce) {
            JOptionPane.showMessageDialog(MainFrame.getMainFrame(), dce.getLocalizedMessage());
        }
    }

    /**
     *
     * @return
     */
    @Override
    public Competitor getWinner() {
        if (mWinner == null) {
            if (mCompetitor1 == Coach.getNullCoach()) {
                mWinner = mCompetitor2;
                mLooser = mCompetitor1;
            } else {
                if (mCompetitor2 == Coach.getNullCoach()) {
                    mWinner = mCompetitor1;
                    mLooser = mCompetitor2;
                } else {
                    final ArrayList<Criteria> crits = Tournament.getTournament().getParams().mCriterias;
                    for (int i = 0; i < crits.size(); i++) {
                        if (mValues.get(crits.get(i)).mValue1 > mValues.get(crits.get(i)).mValue2) {
                            mWinner = mCompetitor1;
                            mLooser = mCompetitor2;
                            break;
                        }
                        if (mValues.get(crits.get(i)).mValue1 < mValues.get(crits.get(i)).mValue2) {
                            mWinner = mCompetitor2;
                            mLooser = mCompetitor1;
                            break;
                        }
                    }
                    if (mWinner == null) {
                        Random ran=new Random();
                        final int r = ran.nextInt()%2;
                        if (r % 2 == 0) {
                            mWinner = mCompetitor2;
                            mLooser = mCompetitor1;
                        } else {
                            mWinner = mCompetitor1;
                            mLooser = mCompetitor2;
                        }
                    }
                }
            }
        }

        return mWinner;


    }

    /**
     *
     * @return
     */
    @Override
    public Competitor getLooser() {
        if (mLooser == null) {
            if (mCompetitor1 == Coach.getNullCoach()) {
                mWinner = mCompetitor2;
                mLooser = mCompetitor1;
            } else {
                if (mCompetitor2 == Coach.getNullCoach()) {
                    mWinner = mCompetitor1;
                    mLooser = mCompetitor2;
                } else {
                    final ArrayList<Criteria> crits = Tournament.getTournament().getParams().mCriterias;
                    for (int i = 0; i < crits.size(); i++) {
                        if (mValues.get(crits.get(i)).mValue1 < mValues.get(crits.get(i)).mValue2) {
                            mWinner = mCompetitor1;
                            mLooser = mCompetitor2;
                            break;
                        }
                        if (mValues.get(crits.get(i)).mValue1 > mValues.get(crits.get(i)).mValue2) {
                            mWinner = mCompetitor2;
                            mLooser = mCompetitor1;
                            break;
                        }
                    }

                    if (mLooser == null) {
                        Random ran=new Random();
                        final int r = ran.nextInt()%2;
                        if (r % 2 == 0) {
                            mWinner = mCompetitor1;
                            mLooser = mCompetitor2;
                        } else {
                            mWinner = mCompetitor2;
                            mLooser = mCompetitor1;
                        }
                    }
                }
            }
        }

        return mLooser;
    }

    /**
     *
     */
    @Override
    public void resetWL() {
        mWinner = null;
        mLooser = null;
    }

    /**
     * @return the mRoster1
     */
    public RosterType getRoster1() {
        return mRoster1;
    }

    /**
     * @param mRoster1 the mRoster1 to set
     */
    public void setRoster1(RosterType mRoster1) {
        this.mRoster1 = mRoster1;
    }

    /**
     * @return the mRoster2
     */
    public RosterType getRoster2() {
        return mRoster2;
    }

    /**
     * @param mRoster2 the mRoster2 to set
     */
    public void setRoster2(RosterType mRoster2) {
        this.mRoster2 = mRoster2;
    }

    /**
     * @return the mSubstitute1
     */
    public Substitute getSubstitute1() {
        return mSubstitute1;
    }

    /**
     * @param mSubstitute1 the mSubstitute1 to set
     */
    public void setSubstitute1(Substitute mSubstitute1) {
        this.mSubstitute1 = mSubstitute1;
    }

    /**
     * @return the mSubstitute2
     */
    public Substitute getSubstitute2() {
        return mSubstitute2;
    }

    /**
     * @param mSubstitute2 the mSubstitute2 to set
     */
    public void setSubstitute2(Substitute mSubstitute2) {
        this.mSubstitute2 = mSubstitute2;
    }

    /**
     * @return the refusedBy1
     */
    public boolean isRefusedBy1() {
        return refusedBy1;
    }

    /**
     * @param refusedBy1 the refusedBy1 to set
     */
    public void setRefusedBy1(boolean refusedBy1) {
        this.refusedBy1 = refusedBy1;
    }

    /**
     * @return the refusedBy2
     */
    public boolean isRefusedBy2() {
        return refusedBy2;
    }

    /**
     * @param refusedBy2 the refusedBy2 to set
     */
    public void setRefusedBy2(boolean refusedBy2) {
        this.refusedBy2 = refusedBy2;
    }

    /**
     * @return the concedeedBy1
     */
    public boolean isConcedeedBy1() {
        return concedeedBy1;
    }

    /**
     * @param concedeedBy1 the concedeedBy1 to set
     */
    public void setConcedeedBy1(boolean concedeedBy1) {
        this.concedeedBy1 = concedeedBy1;
    }

    /**
     * @return the concedeedBy2
     */
    public boolean isConcedeedBy2() {
        return concedeedBy2;
    }

    /**
     * @param concedeedBy2 the concedeedBy2 to set
     */
    public void setConcedeedBy2(boolean concedeedBy2) {
        this.concedeedBy2 = concedeedBy2;
    }

}
