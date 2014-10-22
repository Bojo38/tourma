/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
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

    public RosterType mRoster1;
    public RosterType mRoster2;
    public HashMap<Criteria, Value> mValues;
    public Substitute mSubstitute1;
    public Substitute mSubstitute2;
    public boolean refusedBy1 = false;
    public boolean refusedBy2 = false;
    public boolean concedeedBy1 = false;
    public boolean concedeedBy2 = false;

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

    @Override
    public Element getXMLElement() {
        final Element match = new Element(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("MATCH"));
        match.setAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("COACH1"), this.mCompetitor1.mName);
        match.setAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("COACH2"), this.mCompetitor2.mName);

        match.setAttribute("RefusedBy1", Boolean.toString(refusedBy1));
        match.setAttribute("RefusedBy2", Boolean.toString(refusedBy2));
        match.setAttribute("ConcedeedBy1", Boolean.toString(concedeedBy1));
        match.setAttribute("ConcedeedBy2", Boolean.toString(concedeedBy2));

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

        if (this.mRoster1 != null) {
            String key1 = java.util.ResourceBundle.getBundle("tourma/languages/language").getString("ROSTER1");
            match.setAttribute(key1, this.mRoster1.mName);
        }
        if (this.mRoster2 != null) {
            String key2 = java.util.ResourceBundle.getBundle("tourma/languages/language").getString("ROSTER2");
            match.setAttribute(key2, this.mRoster2.mName);
        }

        if (mSubstitute1 != null) {
            match.addContent(mSubstitute1.getXMLElement());
        }
        if (mSubstitute2 != null) {
            match.addContent(mSubstitute2.getXMLElement());
        }
        return match;
    }

    @Override
    public void setXMLElement(final Element match) {
        try {
            final String c1 = match.getAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("COACH1")).getValue();
            final String c2 = match.getAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("COACH2")).getValue();
            this.mCompetitor1 = Coach.sCoachMap.get(c1);
            this.mCompetitor2 = Coach.sCoachMap.get(c2);
            if (this.mCompetitor1==null)
            {
                this.mCompetitor1=Coach.sNullCoach;
            }
            if (this.mCompetitor2==null)
            {
                this.mCompetitor2=Coach.sNullCoach;
            }

            try {
                refusedBy1 = match.getAttribute("RefusedBy1").getBooleanValue();
                refusedBy2 = match.getAttribute("RefusedBy2").getBooleanValue();
                concedeedBy1 = match.getAttribute("ConcedeedBy1").getBooleanValue();
                concedeedBy2 = match.getAttribute("ConcedeedBy2").getBooleanValue();
            } catch (Exception e) {
            }

            if (((Coach) mCompetitor1)!=null)
            {
                if (((Coach) mCompetitor1).mMatchs!=null)
                {
                ((Coach) mCompetitor1).mMatchs.add(this);
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
                this.mRoster1 = RosterType.mRosterTypes.get(att1.getValue());
            }
            String key2 = java.util.ResourceBundle.getBundle("tourma/languages/language").getString("ROSTER2");
            Attribute att2 = match.getAttribute(key2);
            if (att2 != null) {
                this.mRoster2 = RosterType.mRosterTypes.get(att2.getValue());
            }

            final List subs = match.getChildren("Subtitution");
            final Iterator it = subs.iterator();
            while (it.hasNext()) {
                final Element sub = (Element) it.next();
                Substitute s = new Substitute();
                s.setXMLElement(sub);
                s.mMatch = this;
                if (s.mTitular == mCompetitor1) {
                    this.mSubstitute1 = s;
                }
                if (s.mTitular == mCompetitor2) {
                    this.mSubstitute2 = s;
                }
            }

        } catch (DataConversionException dce) {
            JOptionPane.showMessageDialog(MainFrame.getMainFrame(), dce.getLocalizedMessage());
        }
    }

    public Competitor getWinner() {
        if (mWinner == null) {
            if (mCompetitor1 == Coach.sNullCoach) {
                mWinner = mCompetitor2;
                mLooser = mCompetitor1;
            } else {
                if (mCompetitor2 == Coach.sNullCoach) {
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
                        final int r = (int) Math.random();
                        if (r % 2 == 0) {
                            mWinner = mCompetitor2;
                            mLooser = mCompetitor1;
                        } else {
                            mWinner = mCompetitor2;
                            mLooser = mCompetitor1;
                        }
                    }
                }
            }
        }

        return mWinner;


    }

    public Competitor getLooser() {
        if (mLooser == null) {
            if (mCompetitor1 == Coach.sNullCoach) {
                mWinner = mCompetitor2;
                mLooser = mCompetitor1;
            } else {
                if (mCompetitor2 == Coach.sNullCoach) {
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
                        final int r = (int) Math.random();
                        if (r % 2 == 0) {
                            mWinner = mCompetitor2;
                            mLooser = mCompetitor1;
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

    public void resetWL() {
        mWinner = null;
        mLooser = null;
    }

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
}
