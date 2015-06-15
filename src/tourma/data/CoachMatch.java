/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.jdom2.Attribute;
import org.jdom2.DataConversionException;
import org.jdom2.Element;
import tourma.MainFrame;
import tourma.utility.StringConstants;

/**
 *
 * @author Frederic Berger
 */
public class CoachMatch extends Match {

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
    private final HashMap<Criteria, Value> mValues;

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

        final int size = Tournament.getTournament().getParams().getCriteriaCount();
        for (int i = 0; i < size; i++) {
            final Criteria crit = Tournament.getTournament().getParams().getCriteria(i);
            final Value val = new Value(crit);
            if (i == 0) {
                val.setValue1(-1);
                val.setValue2(-1);
            } else {
                val.setValue1(0);
                val.setValue2(0);
            }
            mValues.put(crit, val);
        }

        setRound(round);
    }

    /**
     *
     * @return
     */
    @Override
    public Element getXMLElement() {
        final Element match = new Element(StringConstants.CS_MATCH);
        match.setAttribute(StringConstants.CS_COACH+1 , this.getCompetitor1().getName());
        match.setAttribute(StringConstants.CS_COACH+2, this.getCompetitor2().getName());

        match.setAttribute(StringConstants.CS_REFUSED_BY+1, Boolean.toString(isRefusedBy1()));
        match.setAttribute(StringConstants.CS_REFUSED_BY+2, Boolean.toString(isRefusedBy2()));
        match.setAttribute(StringConstants.CS_CONCEEDED_BY+1, Boolean.toString(isConcedeedBy1()));
        match.setAttribute(StringConstants.CS_CONCEEDED_BY+2, Boolean.toString(isConcedeedBy2()));

        for (int k = 0; k < Tournament.getTournament().getParams().getCriteriaCount(); k++) {
            final Value val = this.getValue(Tournament.getTournament().getParams().getCriteria(k));
            final Element value = new Element(StringConstants.CS_VALUE);
            value.setAttribute(StringConstants.CS_NAME, val.getCriteria().getName());
            value.setAttribute(StringConstants.CS_VALUE+1, Integer.toString(val.getValue1()));
            value.setAttribute(StringConstants.CS_VALUE+2, Integer.toString(val.getValue2()));

            match.addContent(value);
        }

        if (this.getRoster1() != null) {
            match.setAttribute(StringConstants.CS_ROSTER+1, this.getRoster1().getName());
        }
        if (this.getRoster2() != null) {
            match.setAttribute(StringConstants.CS_ROSTER+2, this.getRoster2().getName());
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
            final String c1 = match.getAttribute(StringConstants.CS_COACH+1).getValue();
            final String c2 = match.getAttribute(StringConstants.CS_COACH+2).getValue();
            this.setCompetitor1(Coach.getCoach(c1));
            this.setCompetitor2(Coach.getCoach(c2));
            if (this.getCompetitor1() == null) {
                this.setCompetitor1(Coach.getNullCoach());
            }
            if (this.getCompetitor2() == null) {
                this.setCompetitor2(Coach.getNullCoach());
            }

            try {
                setRefusedBy1(match.getAttribute(StringConstants.CS_REFUSED_BY+1).getBooleanValue());
                setRefusedBy2(match.getAttribute(StringConstants.CS_REFUSED_BY+2).getBooleanValue());
                setConcedeedBy1(match.getAttribute(StringConstants.CS_CONCEEDED_BY+1).getBooleanValue());
                setConcedeedBy2(match.getAttribute(StringConstants.CS_CONCEEDED_BY+2).getBooleanValue());
            } catch (DataConversionException | NullPointerException e) {
                LOG.log(Level.FINE, e.getLocalizedMessage());
                setRefusedBy1(false);
                setRefusedBy2(false);
                setConcedeedBy1(false);
                setConcedeedBy2(concedeedBy2);
            }

            if (((Coach) getCompetitor1()) != null) {
                if (getCompetitor1().isMatchsNotNull()) {
                    getCompetitor1().addMatch(this);
                }
            } else {
                setCompetitor1(Coach.getNullCoach());
            }

            if (((Coach) getCompetitor2()) != null) {
                if (getCompetitor2().isMatchsNotNull()) {
                    getCompetitor2().addMatch(this);
                }
            } else {
                setCompetitor2(Coach.getNullCoach());
            }

            final List<Element> values = match.getChildren(StringConstants.CS_VALUE);
            final Iterator<Element> v = values.iterator();

            while (v.hasNext()) {
                final Element val =  v.next();
                Criteria crit = null;

                for (int cpt = 0; cpt < Tournament.getTournament().getParams().getCriteriaCount(); cpt++) {
                    final Criteria criteria = Tournament.getTournament().getParams().getCriteria(cpt);
                    final String tmp = val.getAttribute(StringConstants.CS_NAME).getValue();

                    if (criteria.getName().equals(tmp)) {
                        crit = criteria;
                    }
                }
                final Value value = new Value(crit);
                value.setValue1(val.getAttribute(StringConstants.CS_VALUE+1).getIntValue());
                value.setValue2(val.getAttribute(StringConstants.CS_VALUE+2).getIntValue());
                this.putValue(crit, value);
            }


            Attribute att1 = match.getAttribute(StringConstants.CS_ROSTER+1);
            if (att1 != null) {
                this.setRoster1(RosterType.getRosterType(att1.getValue()));
            }
            Attribute att2 = match.getAttribute(StringConstants.CS_ROSTER+2);
            if (att2 != null) {
                this.setRoster2(RosterType.getRosterType(att2.getValue()));
            }

            final List<Element> subs = match.getChildren(StringConstants.CS_SUBSTITUTION);
            final Iterator<Element> it = subs.iterator();
            while (it.hasNext()) {
                final Element sub = it.next();
                Substitute s = new Substitute();
                s.setXMLElement(sub);
                s.setMatch(this);
                if (s.getTitular() == getCompetitor1()) {
                    this.setSubstitute1(s);
                }
                if (s.getTitular() == getCompetitor2()) {
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
        if (super.getWinner() == null) {
            if (getCompetitor1() == Coach.getNullCoach()) {
                super.setWinner(getCompetitor2());
                super.setLooser(getCompetitor1());
            } else {
                if (getCompetitor2() == Coach.getNullCoach()) {
                    super.setWinner(getCompetitor1());
                    super.setLooser(getCompetitor2());
                } else {
                    for (int i = 0; i < Tournament.getTournament().getParams().getCriteriaCount(); i++) {
                        Criteria crit = Tournament.getTournament().getParams().getCriteria(i);
                        if (getValue(crit).getValue1() > getValue(crit).getValue2()) {
                            super.setWinner(getCompetitor1());
                            super.setLooser(getCompetitor2());
                            break;
                        }
                        if (getValue(crit).getValue1() < getValue(crit).getValue2()) {
                            super.setWinner(getCompetitor2());
                            super.setLooser(getCompetitor1());
                            break;
                        }
                    }
                    if (super.getWinner() == null) {
                        Random ran = new Random();
                        final int r = ran.nextInt() % 2;
                        if (r % 2 == 0) {
                            super.setWinner(getCompetitor2());
                            super.setLooser(getCompetitor1());
                        } else {
                            super.setWinner(getCompetitor1());
                            super.setLooser(getCompetitor2());
                        }
                    }
                }
            }
        }

        return super.getWinner();

    }

    /**
     *
     * @return
     */
    @Override
    public Competitor getLooser() {
        if (super.getLooser() == null) {
            if (getCompetitor1() == Coach.getNullCoach()) {
                super.setWinner(getCompetitor2());
                super.setLooser(getCompetitor1());
            } else {
                if (getCompetitor2() == Coach.getNullCoach()) {
                    super.setWinner(getCompetitor1());
                    super.setLooser(getCompetitor2());
                } else {

                    for (int i = 0; i < Tournament.getTournament().getParams().getCriteriaCount(); i++) {
                        Criteria crit = Tournament.getTournament().getParams().getCriteria(i);
                        if (getValue(crit).getValue1() < getValue(crit).getValue2()) {
                            super.setWinner(getCompetitor2());
                            super.setLooser(getCompetitor1());
                            break;
                        }
                        if (getValue(crit).getValue1() > getValue(crit).getValue2()) {
                            super.setWinner(getCompetitor1());
                            super.setLooser(getCompetitor2());
                            break;
                        }
                    }

                    if (getLooser() == null) {
                        Random ran = new Random();
                        final int r = ran.nextInt() % 2;
                        if (r % 2 == 0) {
                            super.setWinner(getCompetitor1());
                            super.setLooser(getCompetitor2());
                        } else {
                            super.setWinner(getCompetitor2());
                            super.setLooser(getCompetitor1());
                        }
                    }
                }
            }
        }

        return super.getLooser();
    }

    /**
     *
     */
    @Override
    public void resetWL() {
        super.setWinner(null);
        super.setLooser(null);
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

    /**
     *
     * @param c
     * @return
     */
    public Value getValue(Criteria c) {
        return mValues.get(c);
    }

    /**
     *
     * @return
     */
    public int getValueCount() {
        return mValues.size();
    }

    /**
     *
     * @param c
     * @param v
     */
    public void putValue(Criteria c, Value v) {
        mValues.put(c, v);
    }

    /**
     *
     * @param c
     */
    public void removeValue(Criteria c) {
        mValues.remove(c);
    }

    @Override
    public Element getXMLElementForDisplay() {
        Element match = getXMLElement();
        Element c1 = ((XMLExport) getCompetitor1()).getXMLElement();
        Element c2 = ((XMLExport) getCompetitor2()).getXMLElement();
        match.addContent(c1);
        match.addContent(c2);
        return match;
    }

    @Override
    public void setXMLElementForDisplay(Element element) {

        List<Element> elts = element.getChildren(StringConstants.CS_COACH);
        if (elts.size() == 2) {
            Element c1 = elts.get(0);
            Coach coach1 = new Coach();
            coach1.setXMLElement(c1);
            if (Coach.getCoach(coach1.getName()) == null) {
                Tournament.getTournament().addCoach(coach1);
            }
            
            Element c2 = elts.get(1);
            Coach coach2 = new Coach();
            coach2.setXMLElement(c2);
            if (Coach.getCoach(coach2.getName()) == null) {
                Tournament.getTournament().addCoach(coach2);
            }
        }

        setXMLElement(element);
    }
    
    public boolean isFullNaf()
    {
        Coach c1=(Coach)getCompetitor1();
        Coach c2=(Coach)getCompetitor2();
        return (c1.getNaf()>0)&&(c2.getNaf()>0);
    }
}
