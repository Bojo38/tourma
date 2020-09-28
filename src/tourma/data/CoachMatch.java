/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom.Attribute;
import org.jdom.DataConversionException;
import org.jdom.Element;
import tourma.tableModel.MjtRanking;
import tourma.utility.StringConstants;

/**
 *
 * @author Frederic Berger
 */
public class CoachMatch extends Match implements Serializable {

    /**
     * UID Seed
     */
    protected static AtomicInteger sGenUID = new AtomicInteger(0);
    /**
     * UNique ID
     */
    protected int UID = sGenUID.incrementAndGet();

    /**
     * Remotely updated
     */
    protected boolean remotely = false;

    /**
     * Is remotely
     *
     * @return
     */
    public boolean isRemotely() {
        return remotely;
    }

    /**
     * Set remotely
     *
     * @param isRemotely
     */
    public void setRemotely(boolean isRemotely) {
        this.remotely = isRemotely;
    }

    /**
     * Unique ID Getter
     *
     * @return
     */
    public int getUID() {
        return UID;
    }

    /**
     * Unique ID Setter
     *
     * @param UID
     */
    public void setUID(int UID) {
        this.UID = UID;
    }

    /**
     * Is match updated
     *
     * @return
     */
    public boolean isUpdated() {
        for (Value val : mValues.values()) {
            if (val.isUpdated()) {
                updated = true;
                break;
            }
        }
        return updated;
    }

    /**
     * Coach Match Puller
     *
     * @param match
     */
    public void pull(Match match) {
        if (match instanceof CoachMatch) {
            CoachMatch coachmatch = (CoachMatch) match;
            this.UID = coachmatch.UID;
            this.c1value1 = coachmatch.c1value1;
            this.c1value2 = coachmatch.c1value2;
            this.c1value3 = coachmatch.c1value3;
            this.c1value4 = coachmatch.c1value4;
            this.c1value5 = coachmatch.c1value5;
            this.c1value1 = coachmatch.c1value1;
            this.c2value2 = coachmatch.c2value2;
            this.c2value3 = coachmatch.c2value3;
            this.c2value4 = coachmatch.c2value4;
            this.c2value5 = coachmatch.c2value5;
            this.concedeedBy1 = coachmatch.concedeedBy1;
            this.concedeedBy2 = coachmatch.concedeedBy2;

            this.mCompetitor1 = Tournament.getTournament().getCoach(coachmatch.getCompetitor1().getName());
            this.mCompetitor2 = Tournament.getTournament().getCoach(coachmatch.getCompetitor2().getName());

            if (coachmatch.getRoster1() != null) {
                this.mRoster1 = RosterType.getRosterType(coachmatch.getRoster1().getName());
            }
            if (coachmatch.getRoster2() != null) {
                this.mRoster2 = RosterType.getRosterType(coachmatch.getRoster2().getName());
            }
            if (coachmatch.mSubstitute1 != null) {
                this.mSubstitute1 = new Substitute();
                this.mSubstitute1.UID = coachmatch.mSubstitute1.UID;
                this.mSubstitute1.setMatch(this);
                this.mSubstitute1.setSubstitute(Tournament.getTournament().getCoach(coachmatch.getSubstitute1().getSubstitute().getName()));
                this.mSubstitute1.setTitular(Tournament.getTournament().getCoach(coachmatch.getSubstitute1().getTitular().getName()));
            } else {
                this.mSubstitute1 = null;
            }

            if (coachmatch.mSubstitute2 != null) {
                this.mSubstitute2 = new Substitute();
                this.mSubstitute2.UID = coachmatch.mSubstitute2.UID;
                this.mSubstitute2.setMatch(this);
                this.mSubstitute2.setSubstitute(Tournament.getTournament().getCoach(coachmatch.getSubstitute2().getSubstitute().getName()));
                this.mSubstitute2.setTitular(Tournament.getTournament().getCoach(coachmatch.getSubstitute2().getTitular().getName()));
            } else {
                this.mSubstitute2 = null;
            }

            for (Criteria crit : coachmatch.mValues.keySet()) {
                Criteria criteria = Tournament.getTournament().getParams().getCriteria(crit.getName());
                if (criteria == null) {
                    criteria = new Criteria(crit.getName());
                    criteria.pull(crit);
                    Tournament.getTournament().getParams().addCriteria(criteria);
                }
                Value val = this.mValues.get(criteria);
                if (val == null) {
                    val = new Value(criteria);
                    mValues.put(criteria, val);
                }
                val.pull(coachmatch.mValues.get(crit));
            }

            this.refusedBy1 = coachmatch.refusedBy1;
            this.refusedBy2 = coachmatch.refusedBy2;
            this.values_computed = coachmatch.values_computed;

            // add Match to competitor
            boolean bFound = false;
            for (int i = 0; i < mCompetitor1.getMatchCount(); i++) {
                Match m = mCompetitor1.getMatch(i);
                if (m.getUID() == UID) {
                    bFound = true;
                    break;
                }
            }
            if (!bFound) {
                mCompetitor1.addMatch(this);
            }

            bFound = false;
            for (int i = 0; i < mCompetitor2.getMatchCount(); i++) {
                Match m = mCompetitor2.getMatch(i);
                if (m.getUID() == UID) {
                    bFound = true;
                    break;
                }
            }
            if (!bFound) {
                mCompetitor2.addMatch(this);
            }
        }
    }

    /**
     * Match Pusher
     *
     * @param match
     */
    public void push(Match match) {
        if (match.isUpdated()) {
            if (match instanceof CoachMatch) {
                CoachMatch coachmatch = (CoachMatch) match;
                this.UID = coachmatch.UID;
                this.c1value1 = coachmatch.c1value1;
                this.c1value2 = coachmatch.c1value2;
                this.c1value3 = coachmatch.c1value3;
                this.c1value4 = coachmatch.c1value4;
                this.c1value5 = coachmatch.c1value5;
                this.c1value1 = coachmatch.c1value1;
                this.c2value2 = coachmatch.c2value2;
                this.c2value3 = coachmatch.c2value3;
                this.c2value4 = coachmatch.c2value4;
                this.c2value5 = coachmatch.c2value5;
                this.concedeedBy1 = coachmatch.concedeedBy1;
                this.concedeedBy2 = coachmatch.concedeedBy2;

                this.mCompetitor1 = Tournament.getTournament().getCoach(coachmatch.getCompetitor1().getName());
                this.mCompetitor2 = Tournament.getTournament().getCoach(coachmatch.getCompetitor2().getName());

                if (coachmatch.getRoster1() != null) {
                    this.mRoster1 = RosterType.getRosterType(coachmatch.getRoster1().getName());
                }
                if (coachmatch.getRoster1() != null) {
                    this.mRoster2 = RosterType.getRosterType(coachmatch.getRoster2().getName());
                }
                if (coachmatch.mSubstitute1 != null) {
                    this.mSubstitute1 = new Substitute();
                    this.mSubstitute1.UID = coachmatch.mSubstitute1.UID;
                    this.mSubstitute1.setMatch(this);
                    this.mSubstitute1.setSubstitute(Tournament.getTournament().getCoach(coachmatch.getSubstitute1().getSubstitute().getName()));
                    this.mSubstitute1.setTitular(Tournament.getTournament().getCoach(coachmatch.getSubstitute1().getTitular().getName()));
                } else {
                    this.mSubstitute1 = null;
                }

                if (coachmatch.mSubstitute2 != null) {
                    this.mSubstitute2 = new Substitute();
                    this.mSubstitute2.UID = coachmatch.mSubstitute2.UID;
                    this.mSubstitute2.setMatch(this);
                    this.mSubstitute2.setSubstitute(Tournament.getTournament().getCoach(coachmatch.getSubstitute2().getSubstitute().getName()));
                    this.mSubstitute2.setTitular(Tournament.getTournament().getCoach(coachmatch.getSubstitute2().getTitular().getName()));
                } else {
                    this.mSubstitute2 = null;
                }

                Criteria touchdowns = null;
                Criteria lTouchdowns = Tournament.getTournament().getParams().getCriteria(0);
                for (Criteria remote : coachmatch.mValues.keySet()) {
                    if ((remote.getName().equals(lTouchdowns.getName())) && (remote.getUID() == lTouchdowns.getUID())) {
                        touchdowns = remote;
                        break;
                    }
                }
                Value val_td = coachmatch.mValues.get(touchdowns);
                // If match values are correct
                if ((val_td.getValue1() > -1) && (val_td.getValue2() > -1)) {
                    for (Criteria crit : coachmatch.mValues.keySet()) {
                        Criteria criteria = Tournament.getTournament().getParams().getCriteria(crit.getName());
                        if (criteria == null) {
                            criteria = new Criteria(crit.getName());
                            criteria.pull(crit);
                            Tournament.getTournament().getParams().addCriteria(criteria);
                        }

                        Value val = this.mValues.get(criteria);
                        if (val == null) {
                            val = new Value(criteria);
                        }
                        val.push(coachmatch.mValues.get(crit));
                    }
                }

                this.refusedBy1 = coachmatch.refusedBy1;
                this.refusedBy2 = coachmatch.refusedBy2;
                this.values_computed = coachmatch.values_computed;

                // add Match to competitor
                boolean bFound = false;
                for (int i = 0; i < mCompetitor1.getMatchCount(); i++) {
                    Match m = mCompetitor1.getMatch(i);
                    if (m.getUID() == UID) {
                        bFound = true;
                        break;
                    }
                }
                /*if (!bFound) {
                mCompetitor1.addMatch(this);
            }*/

                bFound = false;
                for (int i = 0; i < mCompetitor2.getMatchCount(); i++) {
                    Match m = mCompetitor2.getMatch(i);
                    if (m.getUID() == UID) {
                        bFound = true;
                        break;
                    }
                }
                /*if (!bFound) {
                mCompetitor2.addMatch(this);
            }*/
            }
        }
    }

    /**
     * Logger
     */
    private static final Logger LOG = Logger.getLogger(CoachMatch.class.getName());

    @Override
    public void setUpdated(boolean updated) {
        super.setUpdated(updated);
        for (Value v : mValues.values()) {
            v.setUpdated(updated);
        }
    }
    /**
     *
     */
    protected RosterType mRoster1;

    /**
     *
     */
    protected RosterType mRoster2;

    /**
     *
     */
    protected final HashMap<Criteria, Value> mValues;
    protected final HashMap<Formula, Value> mComputedValues;

    /**
     *
     */
    protected Substitute mSubstitute1;

    /**
     *
     */
    protected Substitute mSubstitute2;

    /**
     *
     */
    protected boolean refusedBy1 = false;

    /**
     *
     */
    protected boolean refusedBy2 = false;

    /**
     *
     */
    protected boolean concedeedBy1 = false;

    /**
     *
     */
    protected boolean concedeedBy2 = false;

    public HashMap<Criteria, Value> getValues() {
        return mValues;
    }

    /**
     *
     * @param round
     */
    public CoachMatch(Round round) {
        super(round);
        mValues = new HashMap<>();
        mComputedValues = new HashMap<>();

        int size = Tournament.getTournament().getParams().getCriteriaCount();
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

        size = Tournament.getTournament().getParams().getFormulaCount();
        for (int i = 0; i < size; i++) {
            final Formula form = Tournament.getTournament().getParams().getFormula(i);
            final Value val = new Value(form);
            if (i == 0) {
                val.setValue1(-1);
                val.setValue2(-1);
            } else {
                val.setValue1(0);
                val.setValue2(0);
            }
            mComputedValues.put(form, val);
        }

        setRound(round);
    }

    @Override
    public boolean equals(Object c) {
        if (this == c) {
            return true;
        }

        if (c instanceof CoachMatch) {
            CoachMatch cm = (CoachMatch) c;
            boolean equality = (this.concedeedBy1 == cm.concedeedBy1);
            equality &= (this.concedeedBy2 == cm.concedeedBy2);
            equality &= (this.refusedBy1 == cm.refusedBy1);
            equality &= (this.refusedBy2 == cm.refusedBy2);
            equality &= (this.mRoster1 == cm.mRoster1);
            equality &= (this.mRoster2 == cm.mRoster2);
            if ((getCompetitor1() != null) && (getCompetitor2() != null)) {
                equality &= (this.getCompetitor1().equals(cm.getCompetitor1()));
                equality &= (this.getCompetitor2().equals(cm.getCompetitor2()));
            }

            for (Criteria crit : this.mValues.keySet()) {
                Value v = cm.getValue(crit);
                if (v == null) {
                    return false;
                }
                equality &= v.getValue1() == this.mValues.get(crit).getValue1();
                equality &= v.getValue2() == this.mValues.get(crit).getValue2();
            }
            //equality &= this.getRound() == cm.getRound();

            return equality;

        }

        return false;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.mRoster1);
        hash = 71 * hash + Objects.hashCode(this.mRoster2);
        hash = 71 * hash + Objects.hashCode(this.mValues);
        hash = 71 * hash + (this.refusedBy1 ? 1 : 0);
        hash = 71 * hash + (this.refusedBy2 ? 1 : 0);
        hash = 71 * hash + (this.concedeedBy1 ? 1 : 0);
        hash = 71 * hash + (this.concedeedBy2 ? 1 : 0);
        return hash;
    }

    /**
     *
     * @return
     */
    @Override
    public Element getXMLElement() {
        final Element match = new Element(StringConstants.CS_MATCH);
        match.setAttribute(StringConstants.CS_COACH + 1, this.getCompetitor1().getName());
        match.setAttribute(StringConstants.CS_COACH + 2, this.getCompetitor2().getName());

        match.setAttribute(StringConstants.CS_REFUSED_BY + 1, Boolean.toString(isRefusedBy1()));
        match.setAttribute(StringConstants.CS_REFUSED_BY + 2, Boolean.toString(isRefusedBy2()));
        match.setAttribute(StringConstants.CS_CONCEEDED_BY + 1, Boolean.toString(isConcedeedBy1()));
        match.setAttribute(StringConstants.CS_CONCEEDED_BY + 2, Boolean.toString(isConcedeedBy2()));

        for (int k = 0; k < Tournament.getTournament().getParams().getCriteriaCount(); k++) {
            final Value val = this.getValue(Tournament.getTournament().getParams().getCriteria(k));
            final Element value = new Element(StringConstants.CS_VALUE);
            value.setAttribute(StringConstants.CS_NAME, val.getCriteria().getName());
            value.setAttribute(StringConstants.CS_VALUE + 1, Integer.toString(val.getValue1()));
            value.setAttribute(StringConstants.CS_VALUE + 2, Integer.toString(val.getValue2()));

            match.addContent(value);
        }

        for (int k = 0; k < Tournament.getTournament().getParams().getFormulaCount(); k++) {
            final Value val = this.getValue(Tournament.getTournament().getParams().getFormula(k));
            final Element value = new Element(StringConstants.CS_VALUE);
            value.setAttribute(StringConstants.CS_NAME, val.getFormula().getName());
            value.setAttribute(StringConstants.CS_VALUE + 1, Integer.toString(val.getValue1()));
            value.setAttribute(StringConstants.CS_VALUE + 2, Integer.toString(val.getValue2()));

            match.addContent(value);
        }

        if (this.getRoster1() != null) {
            match.setAttribute(StringConstants.CS_ROSTER + 1, this.getRoster1().getName());
        }
        if (this.getRoster2() != null) {
            match.setAttribute(StringConstants.CS_ROSTER + 2, this.getRoster2().getName());
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
    public void setXMLElement(final Element match
    ) {
        try {
            final String c1 = match.getAttribute(StringConstants.CS_COACH + 1).getValue();
            final String c2 = match.getAttribute(StringConstants.CS_COACH + 2).getValue();

            this.setCompetitor1(Coach.getCoach(c1));
            this.setCompetitor2(Coach.getCoach(c2));
            if (this.getCompetitor1() == null) {
                this.setCompetitor1(Coach.getNullCoach());
            }
            if (this.getCompetitor2() == null) {
                this.setCompetitor2(Coach.getNullCoach());
            }

            try {
                setRefusedBy1(match.getAttribute(StringConstants.CS_REFUSED_BY + 1).getBooleanValue());
                setRefusedBy2(match.getAttribute(StringConstants.CS_REFUSED_BY + 2).getBooleanValue());
                setConcedeedBy1(match.getAttribute(StringConstants.CS_CONCEEDED_BY + 1).getBooleanValue());
                setConcedeedBy2(match.getAttribute(StringConstants.CS_CONCEEDED_BY + 2).getBooleanValue());
            } catch (DataConversionException | NullPointerException e) {
                LOG.log(Level.FINE, e.getLocalizedMessage());
                setRefusedBy1(false);
                setRefusedBy2(false);
                setConcedeedBy1(false);
                setConcedeedBy2(false);
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
                final Element val = v.next();
                Criteria crit = null;

                for (int cpt = 0; cpt < Tournament.getTournament().getParams().getCriteriaCount(); cpt++) {
                    final Criteria criteria = Tournament.getTournament().getParams().getCriteria(cpt);
                    final String tmp = val.getAttribute(StringConstants.CS_NAME).getValue();
                    if (criteria != null) {
                        if (criteria.getName().equals(tmp)) {
                            crit = criteria;
                            break;
                        }
                    }
                }
                Value value = null;
                if (crit != null) {
                    value = new Value(crit);
                    value.setValue1(val.getAttribute(StringConstants.CS_VALUE + 1).getIntValue());
                    value.setValue2(val.getAttribute(StringConstants.CS_VALUE + 2).getIntValue());
                    this.putValue(crit, value);
                } else {
                    Formula form = null;

                    for (int cpt = 0; cpt < Tournament.getTournament().getParams().getFormulaCount(); cpt++) {
                        final Formula formula = Tournament.getTournament().getParams().getFormula(cpt);
                        final String tmp = val.getAttribute(StringConstants.CS_NAME).getValue();

                        if (formula != null) {
                            if (formula.getName().equals(tmp)) {
                                form = formula;
                                break;
                            }
                        }
                    }

                    if (form != null) {
                        value = new Value(form);
                        value.setValue1(val.getAttribute(StringConstants.CS_VALUE + 1).getIntValue());
                        value.setValue2(val.getAttribute(StringConstants.CS_VALUE + 2).getIntValue());
                        this.putValue(form, value);
                    }
                }
            }

            Attribute att1 = match.getAttribute(StringConstants.CS_ROSTER + 1);
            if (att1 != null) {
                this.setRoster1(RosterType.getRosterType(att1.getValue()));
            }
            Attribute att2 = match.getAttribute(StringConstants.CS_ROSTER + 2);
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
            //JOptionPane.showMessageDialog(MainFrame.getMainFrame(), dce.getLocalizedMessage());
        }
        this.recomputeValues();
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
            } else if (getCompetitor2() == Coach.getNullCoach()) {
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
            } else if (getCompetitor2() == Coach.getNullCoach()) {
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
        updated = true;
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
        updated = true;
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
        updated = true;
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
        updated = true;
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
        updated = true;
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
        updated = true;
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
        updated = true;
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
        updated = true;
    }

    protected static int getGroupModifier(Coach c, CoachMatch m) {
        int value = 0;
        if (Tournament.getTournament().getGroupsCount() > 1) {
            final Criteria td = Tournament.getTournament().getParams().getCriteria(0);
            Group g = Tournament.getTournament().getGroup(c);
            if (g != null) {
                Value v = m.getValue(td);
                if ((v.getValue1() >= 0) && ((v.getValue2() >= 0))) {
                    if (m.getCompetitor1() == c) {

                        Group go = Tournament.getTournament().getGroup((Coach) m.getCompetitor2());
                        GroupPoints gp = g.getOpponentModificationPoints(go);
                        if ((go != null) && (gp != null)) {
                            if (v.getValue1() > v.getValue2()) {
                                value = gp.getVictoryPoints();
                            } else if (v.getValue1() == v.getValue2()) {
                                value = gp.getDrawPoints();
                            } else {
                                value = gp.getLossPoints();
                            }
                        }

                    }
                    if (m.getCompetitor2() == c) {
                        Group go = Tournament.getTournament().getGroup((Coach) m.getCompetitor1());
                        GroupPoints gp = g.getOpponentModificationPoints(go);
                        if ((go != null) && (gp != null)) {
                            if (v.getValue1() < v.getValue2()) {
                                value = gp.getVictoryPoints();
                            } else if (v.getValue1() == v.getValue2()) {
                                value = gp.getDrawPoints();
                            } else {
                                value = gp.getLossPoints();
                            }
                        }
                    }
                }
            }
        }
        return value;
    }

    /**
     *
     * @param c
     * @return
     */
    public Value getValue(Criteria c) {

        Value val = mValues.get(c);
        if (val != null) {
            return val;
        } else {
            // Find criteria ny name
            String criteria = c.getName();

            for (Criteria crit : this.mValues.keySet()) {
                if (crit.getName().equals(criteria)) {
                    return mValues.get(crit);
                }
            }
        }
        return null;
    }

    /**
     *
     * @param c
     * @return
     */
    public Value getValue(Formula f) {

        Value val = mComputedValues.get(f);
        if (val != null) {
            return val;
        } else {
            // Find criteria ny name
            String form = f.getName();

            for (Formula formula : this.mComputedValues.keySet()) {
                if (formula.getName().equals(form)) {
                    return mComputedValues.get(formula);
                }
            }
        }
        return null;
    }

    /**
     *
     * @return
     */
    public int getComputedValueCount() {
        return mComputedValues.size();
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
        updated = true;
    }

    /**
     *
     * @param c
     * @param v
     */
    public void putValue(Formula f, Value v) {
        mComputedValues.put(f, v);
        updated = true;
    }

    /**
     *
     * @param c
     */
    public void removeValue(Criteria c) {
        mValues.remove(c);
        updated = true;
    }

    public void removeValue(Formula f) {
        mComputedValues.remove(f);
        updated = true;
    }

    @Override
    public Element getXMLElementForDisplay() {
        Element match = getXMLElement();
        Element c1 = ((IXMLExport) getCompetitor1()).getXMLElement();
        Element c2 = ((IXMLExport) getCompetitor2()).getXMLElement();
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

    public boolean isFullNaf() {
        Coach c1 = (Coach) getCompetitor1();
        Coach c2 = (Coach) getCompetitor2();
        return (c1.getNaf() > 0) && (c2.getNaf() > 0);
    }

    /**
     *
     */
    public static final int C_STARTING_RANK = 1000;
    /**
     *
     */
    public static final int C_ELO_K = 256;

    /**
     * Recalculate the values fot this match
     */
    public void recomputeValues() {
        this.c1value1 = recomputeValue(1, mCompetitor1);
        this.c2value1 = recomputeValue(1, mCompetitor2);
        this.c1value2 = recomputeValue(2, mCompetitor1);
        this.c2value2 = recomputeValue(2, mCompetitor2);
        this.c1value3 = recomputeValue(3, mCompetitor1);
        this.c2value3 = recomputeValue(3, mCompetitor2);
        this.c1value4 = recomputeValue(4, mCompetitor1);
        this.c2value4 = recomputeValue(4, mCompetitor2);
        this.c1value5 = recomputeValue(5, mCompetitor1);
        this.c2value5 = recomputeValue(5, mCompetitor2);
        this.values_computed = true;
        updated = true;
    }

    /**
     *
     * @param valueType
     * @return
     */
    public static int getSubtypeByValue(final int valueType) {
        int subType = -1;
        if (valueType > Parameters.C_MAX_RANKING) {
            final int value = valueType - Parameters.C_MAX_RANKING - 1;
            subType = value % 3;
        }
        return subType;
    }

    /**
     *
     * @param valueType
     * @return
     */
    public static Criteria getCriteriaByValue(final int valueType) {
        Criteria criteria = null;

        if (valueType > Parameters.C_MAX_RANKING) {
            final int value = valueType - Parameters.C_MAX_RANKING - 1;
            criteria = Tournament.getTournament().getParams().getCriteria(value / 3);
        }
        return criteria;
    }

    protected int recomputeValue(int index, Competitor c) {
        int value = 0;
        int valueType = Parameters.C_RANKING_NONE;
        valueType = Tournament.getTournament().getParams().getIndivRankingType(index - 1);
        if (valueType <= Parameters.C_MAX_RANKING) {
            value = getValue((Coach) c, valueType);
        } else {

            int v = valueType - Parameters.C_MAX_RANKING - 1;
            Criteria criteria = null;
            Parameters params = Tournament.getTournament().getParams();
            if (v / 3 < Tournament.getTournament().getParams().getCriteriaCount()) {
                criteria = params.getCriteria(v / 3);
                value = getValue(criteria, MjtRanking.getSubtypeByValue(valueType), c);
            } else {
                int f = valueType - 3 * Tournament.getTournament().getParams().getCriteriaCount() - Parameters.C_MAX_RANKING - 1;
                Formula formula = Tournament.getTournament().getParams().getFormula(f);
                value = getValue(formula, c);
            }

        }
        return value;
    }

    /**
     * Find value for criteria and the current match
     *
     * @param c
     * @param m
     * @param valueType
     * @return
     */
    public int getValue(final Coach c, final int valueType) {
        int value = 0;

        switch (valueType) {
            case Parameters.C_RANKING_POINTS:
                value = getPointsByCoach(c, this, true, true);
                break;
            case Parameters.C_RANKING_POINTS_WITHOUT_BONUS:
                value = getPointsByCoach(c, this, true, false);
                break;
            case Parameters.C_RANKING_BONUS_POINTS:
                value = getPointsByCoach(c, this, false, true);
                break;
            case Parameters.C_RANKING_NONE:
                value = 0;
                break;
            case Parameters.C_RANKING_OPP_POINTS:
                value = getOppPointsByCoach(c, this, true);
                break;
            case Parameters.C_RANKING_OPP_POINTS_OTHER_MATCHS:
                value = getOppPointsByCoach(c, this, false);
                break;
            case Parameters.C_RANKING_VND:
                value = getVNDByCoach(c, this);
                break;
            case Parameters.C_RANKING_ELO:
                value = getELOByCoach(c, this);
                break;
            case Parameters.C_RANKING_ELO_OPP:
                value = getOppELOByCoach(c, this);
                break;
            case Parameters.C_RANKING_NB_MATCHS:
                value = getCoachNbMatchs(c, this);
                break;
            case Parameters.C_RANKING_TABLES:
                value = getCoachTablePoints(c, this);
                break;
            case Parameters.C_RANKING_HEAD_BY_HEAD:
                value = 0;
                break;
            case Parameters.C_RANKING_TIER:
                value = getCoachRosterGroups(c);
                break;
            default:
                value = 0;
                break;
        }

        return value;
    }

    private int getCoachRosterGroups(Coach c) {
        int value = 0;
        if (this == c.getMatch(0)) {
            RosterType rt = c.getRoster();
            for (int i = 0; i < Tournament.getTournament().getGroupsCount(); i++) {
                Group g = Tournament.getTournament().getGroup(i);
                if (g.containsRoster(rt)) {
                    value = g.getPoints();
                    break;
                }
            }
        }
        return value;
    }

    /**
     *
     * @param c
     * @param m
     * @param criteria
     * @param sub_type
     * @return
     */
    /*public static int getValue(final Coach c, final CoachMatch m, final Criteria criteria, final int sub_type) {
        int value = 0;
        if (sub_type == Parameters.C_RANKING_SUBTYPE_POSITIVE) {
            if (c == m.getCompetitor1()) {
                value += Math.max(m.getValue(criteria).getValue1(), 0);
            } else {
                value += Math.max(m.getValue(criteria).getValue2(), 0);
            }
        } else if (sub_type == Parameters.C_RANKING_SUBTYPE_NEGATIVE) {
            if (c == m.getCompetitor1()) {
                value += Math.max(m.getValue(criteria).getValue2(), 0);
            } else {
                value += Math.max(m.getValue(criteria).getValue1(), 0);
            }
        } else if (c == m.getCompetitor1()) {
            value += m.getValue(criteria).getValue1() - m.getValue(criteria).getValue2();
        } else {
            value += m.getValue(criteria).getValue2() - m.getValue(criteria).getValue1();
        }

        return value;
    }*/
    /**
     *
     * @param c
     * @param m
     * @param includeCurrent
     * @return
     */
    public static int getOppPointsByCoach(final Coach c, final CoachMatch m, boolean includeCurrent) {

        int match_index = 0;
        int value = 0;
        CoachMatch tmp_m = (CoachMatch) c.getMatch(match_index);

        // Loop on opponents from the first match               
        while ((tmp_m != m) && (match_index < c.getMatchCount() - 1)) {
            match_index++;
            tmp_m = (CoachMatch) c.getMatch(match_index);
        }
        if (tmp_m != null) {
            //Get round
            Round round = tmp_m.getRound();

            // List previous Opponents
            ArrayList<Competitor> opponents = new ArrayList<>();
            for (int i = 0; i <= match_index; i++) {
                CoachMatch cm = (CoachMatch) c.getMatch(i);
                if (cm.getCompetitor1() == c) {
                    opponents.add(cm.getCompetitor2());
                }
                if (cm.getCompetitor2() == c) {
                    opponents.add(cm.getCompetitor1());
                }
            }

            // Get Points of each competitor on this round except for the current match       
            for (Competitor cmp : opponents) {
                for (int j = 0; j < cmp.getMatchCount(); j++) {
                    CoachMatch cm = (CoachMatch) cmp.getMatch(j);

                    if (cm.getRound().equals(m.getRound())
                            || m.getCompetitor1().equals(cmp)
                            || m.getCompetitor2().equals(cmp)) {

                        if (cm.getCompetitor1().equals(cmp)) {
                            if ((includeCurrent) || ((!cm.getCompetitor2().equals(c)) && (!includeCurrent))) {
                                value += getPointsByCoach((Coach) cmp, cm, true, true);
                            }
                        }
                        if (cm.getCompetitor2().equals(cmp)) {
                            if ((includeCurrent) || ((!cm.getCompetitor1().equals(c)) && (!includeCurrent))) {
                                value += getPointsByCoach((Coach) cmp, cm, true, true);
                            }
                        }
                        if (cm.getRound() == round) {
                            break;
                        }
                    }
                }
            }
        }

        /*  int value = 0;
        Competitor opponent;
        if (m.getCompetitor1() == c) {
            opponent = m.getCompetitor2();
        } else {
            opponent = m.getCompetitor1();
        }

        if (((Coach) opponent) != null) {
            if (opponent.isMatchsNotNull()) {
                for (int i = 0; i < opponent.getMatchCount(); i++) {
                    CoachMatch om = (CoachMatch) opponent.getMatch(i);
                    if ((includeCurrent) || ((!includeCurrent) && (om != m))) {
                        value += getPointsByCoach((Coach) opponent, om, true, true);
                    }
                    if (om == m) {
                        break;
                    }
                }
            }
        }*/
        return value;
    }

    /**
     *
     * @param c
     * @param m
     * @return
     */
    public static int getCoachNbMatchs(final Coach c, final CoachMatch m) {
        int index = c.matchIndex(m);
        return index + 1;
    }

    public static int getCoachTablePoints(final Coach c, final CoachMatch m) {
        for (int i = 0; i < Tournament.getTournament().getRoundsCount(); i++) {
            Round r = Tournament.getTournament().getRound(i);
            if (r.containsCoachMatch(m)) {
                // No point for first round
                if (i == 0) {
                    return 0;
                } else {
                    int maxvalue = r.getMatchsCount();
                    return maxvalue - r.indexOf(m);
                }
            }
        }
        return 0;
    }

    /**
     *
     * @param c
     * @param m
     * @return
     */
    public static int getOppELOByCoach(final Coach c, final CoachMatch m) {
        int value;
        Competitor opponent;
        if (m.getCompetitor1() == c) {
            opponent = m.getCompetitor2();
        } else {
            opponent = m.getCompetitor1();
        }
        value = getELOByCoach((Coach) opponent, m);

        return value;
    }

    /**
     *
     * @param c
     * @param m
     * @return
     */
    public static int getVNDByCoach(final Coach c, final CoachMatch m) {
        int value = 0;
        final Value val = m.getValue(Tournament.getTournament().getParams().getCriteria(0));
        if (val.getValue1() >= 0) {
            if (m.getCompetitor1() == c) {
                if (val.getValue1() > val.getValue2()) {
                    value += 1000000;
                } else if (val.getValue1() >= 0) {
                    if (val.getValue1() == val.getValue2()) {
                        value += 1000;
                    } else {
                        value += 1;
                    }
                }
            }
            if (m.getCompetitor2() == c) {
                if (val.getValue2() > val.getValue1()) {
                    value += 1000000;
                } else if (val.getValue1() >= 0) {
                    if (val.getValue1() == val.getValue2()) {
                        value += 1000;
                    } else {
                        value += 1;
                    }
                }
            }
        }
        return value;
    }

    /**
     *
     * @param c
     * @param m
     * @return
     */
    public static int getELOByCoach(final Coach c, final CoachMatch m) {
        int value = 0;

        Value val = m.getValue(Tournament.getTournament().getParams().getCriteria(0));
        if (val.getValue1() >= 0) {
            // Get current Round match_index
            int indexRound = -1;
            for (int i = 0; i < Tournament.getTournament().getRoundsCount(); i++) {
                Round r = Tournament.getTournament().getRound(i);
                if (r.getCoachMatchs().contains(m)) {
                    indexRound = i;
                    break;
                }
            }

            int lastPlayerRank = C_STARTING_RANK;
            int lastOppRank = C_STARTING_RANK;
            Coach opp = null;
            int tdPlayer = 0;
            int tdOpp = 0;
            if (m.getCompetitor1() == c) {
                opp = (Coach) m.getCompetitor2();
                tdPlayer = val.getValue1();
                tdOpp = val.getValue2();
            }
            if (m.getCompetitor2() == c) {
                opp = (Coach) m.getCompetitor1();
                tdOpp = val.getValue1();
                tdPlayer = val.getValue2();
            }
            if (opp != null) {
                if (indexRound >= 0) {
                    // Find Previous Match for current player
                    int index = c.matchIndex(m);
                    if (index > 0) {
                        lastPlayerRank = getELOByCoach(c, (CoachMatch) c.getMatch(index - 1));
                    }

                    if (opp.isMatchsNotNull()) {
                        // Find Previous Match for oponnent player
                        index = opp.matchIndex(m);
                        if (index > 0) {
                            lastPlayerRank = getELOByCoach(opp, (CoachMatch) opp.getMatch(index - 1));
                        }
                    }
                }

                double tmp = ((lastOppRank - lastPlayerRank) / (double) 200);
                double EA = 1 / (1 + Math.pow(10.0, tmp));
                // Compute real score
                // Victory is 1000
                // All bonuses to 1
                double SA = 0;
                if (tdPlayer > tdOpp) {
                    SA = 1000;
                }
                if (tdPlayer < tdOpp) {
                    SA = 0;
                }
                if (tdPlayer == tdOpp) {
                    SA = 500;
                }

                // Add/Remove Bonuses
                for (int i = 0; i < Tournament.getTournament().getParams().getCriteriaCount(); i++) {
                    Criteria crit = Tournament.getTournament().getParams().getCriteria(i);
                    val = m.getValue(crit);

                    int value1 = val.getValue1();
                    int value2 = val.getValue2();
                    if ((i == 0) && (value1 == -1)) {
                        value1 = 0;
                    }
                    if ((i == 0) && (value2 == -1)) {
                        value2 = 0;
                    }

                    if (m.getCompetitor1() == c) {
                        SA += value1 * crit.getPointsFor();
                        SA -= value2 * crit.getPointsFor();

                        SA -= value1 * crit.getPointsAgainst();
                        SA += value2 * crit.getPointsAgainst();
                    }
                    if (m.getCompetitor2() == c) {
                        SA -= value1 * crit.getPointsFor();
                        SA += value2 * crit.getPointsFor();

                        SA -= value2 * crit.getPointsAgainst();
                        SA += value1 * crit.getPointsAgainst();
                    }
                }
                double diff = SA / 1000 - EA;
                value = Math.round((float) (lastPlayerRank + C_ELO_K * diff));
            }
        }
        return value;
    }

    protected static int getCriteriaBonusPoints(Coach c, CoachMatch m, Criteria crit) {
        int value = 0;
        Value v = m.getValue(crit);
        if (m.getCompetitor1() == c) {
            if (v.getValue1() >= crit.getOffensiveThreshold()) {
                value += crit.getOffensiveBonuses();
            }

            if (v.getValue1() >= v.getValue2() + crit.getOffensiveDiffThreshold()) {
                value += crit.getOffensiveDiffBonuses();
            }

            if ((v.getValue1() < v.getValue2()) && (v.getValue1() + crit.getDefensiveDiffThreshold() >= v.getValue2())) {
                value += crit.getDefensiveDiffBonuses();
            }
        }
        if (m.getCompetitor2() == c) {
            if (v.getValue2() >= crit.getOffensiveThreshold()) {
                value += crit.getOffensiveBonuses();
            }

            if (v.getValue2() >= v.getValue1() + crit.getOffensiveDiffThreshold()) {
                value += crit.getOffensiveDiffBonuses();
            }

            if ((v.getValue2() < v.getValue1()) && (v.getValue2() + crit.getDefensiveDiffThreshold() >= v.getValue1())) {
                value += crit.getDefensiveDiffBonuses();
            }
        }
        return value;
    }

    protected static int getCriteriasBonusPoints(Coach c, CoachMatch m) {
        int value = 0;
        // loop on Criterias
        for (int i = 0; i < Tournament.getTournament().getParams().getCriteriaCount(); i++) {
            Criteria crit = Tournament.getTournament().getParams().getCriteria(i);
            value += getCriteriaBonusPoints(c, m, crit);
        }
        return value;
    }

    /**
     *
     * @param c
     * @param m
     * @param withMainPoints
     * @param withBonusPOints
     * @return
     */
    public static int getPointsByCoach(final Coach c, final CoachMatch m, final boolean withMainPoints, final boolean withBonusPOints) {
        int value = 0;
        if (withMainPoints) {
            if (c.matchIndex(m) == 0) {
                value += c.getHandicap();
            }
        }
        final Criteria td = Tournament.getTournament().getParams().getCriteria(0);
        final Value val = m.getValue(td);
        if (val != null) {
            if (m.getCompetitor1() == c) {

                value += getCriteriasBonusPoints(c, m);

                if (m.isConcedeedBy1()) {
                    if (withMainPoints) {
                        value += Tournament.getTournament().getParams().getPointsConcedeed();
                    }
                } else if (m.isRefusedBy1()) {
                    if (withMainPoints) {
                        value += Tournament.getTournament().getParams().getPointsRefused();
                    }
                } else {

                    if (val.getValue1() >= 0) {
                        if ((val.getValue1() >= val.getValue2() + Tournament.getTournament().getParams().getGapLargeVictory()) && Tournament.getTournament().getParams().isUseLargeVictory()) {
                            value += Tournament.getTournament().getParams().getPointsIndivLargeVictory();
                        } else if (withMainPoints) {
                            if (val.getValue1() > val.getValue2()) {
                                value += Tournament.getTournament().getParams().getPointsIndivVictory();
                            } else if (val.getValue1() == val.getValue2()) {
                                value += Tournament.getTournament().getParams().getPointsIndivDraw();
                            } else if ((val.getValue1() + Tournament.getTournament().getParams().getGapLittleLost() >= val.getValue2()) && Tournament.getTournament().getParams().isUseLittleLoss()) {
                                value += Tournament.getTournament().getParams().getPointsIndivLittleLost();
                            } else {
                                value += Tournament.getTournament().getParams().getPointsIndivLost();
                            }
                        }

                        if (withBonusPOints) {

                            int bonus = 0;

                            for (int j = 0; j < Tournament.getTournament().getParams().getCriteriaCount(); j++) {
                                final Criteria cri = Tournament.getTournament().getParams().getCriteria(j);
                                final Value va = m.getValue(cri);

                                int value1 = va.getValue1();
                                int value2 = va.getValue2();
                                if ((j == 0) && (value1 == -1)) {
                                    value1 = 0;
                                }
                                if ((j == 0) && (value2 == -1)) {
                                    value2 = 0;
                                }
                                bonus += value1 * cri.getPointsFor();
                                bonus += value2 * cri.getPointsAgainst();
                            }

                            if (Tournament.getTournament().getParams().isTableBonus()) {
                                double coef = Tournament.getTournament().getParams().getTableBonusCoef();

                                bonus += Math.round(getCoachTablePoints(c, m) * coef);
                            }

                            if (Tournament.getTournament().getParams().isTableBonusPerRound()) {
                                // Find Round
                                Round round = null;
                                for (int cpt = 0; cpt < Tournament.getTournament().getRoundsCount(); cpt++) {

                                    Round r = Tournament.getTournament().getRound(cpt);
                                    if (r.containsMatch(m)) {
                                        round = r;
                                    }
                                }
                                if (round != null) {
                                    if (round.getMatchsCount() > 0) {
                                        double fBonus = bonus * round.getCoef(m);
                                        bonus = (int) Math.round(fBonus);
                                    }

                                }
                            }

                            value += bonus;
                        }
                    }
                    if (withMainPoints) {
                        value += getGroupModifier(c, m);
                    }
                }
            }
            if (m.getCompetitor2() == c) {
                value += getCriteriasBonusPoints(c, m);
                if (m.isConcedeedBy2()) {
                    if (withMainPoints) {
                        value += Tournament.getTournament().getParams().getPointsConcedeed();
                    }
                } else if (m.isRefusedBy2()) {
                    if (withMainPoints) {
                        value += Tournament.getTournament().getParams().getPointsRefused();
                    }
                } else {
                    if (val.getValue1() >= 0) {
                        if (withMainPoints) {
                            if ((val.getValue2() >= val.getValue1() + Tournament.getTournament().getParams().getGapLargeVictory()) && Tournament.getTournament().getParams().isUseLargeVictory()) {
                                value += Tournament.getTournament().getParams().getPointsIndivLargeVictory();
                            } else if (val.getValue2() > val.getValue1()) {
                                value += Tournament.getTournament().getParams().getPointsIndivVictory();
                            } else if (val.getValue2() == val.getValue1()) {
                                value += Tournament.getTournament().getParams().getPointsIndivDraw();
                            } else if ((val.getValue2() + Tournament.getTournament().getParams().getGapLittleLost() >= val.getValue1()) && Tournament.getTournament().getParams().isUseLittleLoss()) {
                                value += Tournament.getTournament().getParams().getPointsIndivLittleLost();
                            } else {
                                value += Tournament.getTournament().getParams().getPointsIndivLost();
                            }
                        }
                        if (withBonusPOints) {
                            int bonus = 0;
                            for (int j = 0; j < Tournament.getTournament().getParams().getCriteriaCount(); j++) {

                                final Criteria cri = Tournament.getTournament().getParams().getCriteria(j);
                                final Value va = m.getValue(cri);
                                int value1 = va.getValue1();
                                int value2 = va.getValue2();
                                if ((j == 0) && (value1 == -1)) {
                                    value1 = 0;
                                }
                                if ((j == 0) && (value2 == -1)) {
                                    value2 = 0;
                                }
                                bonus += value2 * cri.getPointsFor();
                                bonus += value1 * cri.getPointsAgainst();

                            }
                            if (Tournament.getTournament().getParams().isTableBonus()) {
                                double coef = Tournament.getTournament().getParams().getTableBonusCoef();
                                bonus += Math.round(getCoachTablePoints(c, m) * coef);
                            }

                            if (Tournament.getTournament().getParams().isTableBonusPerRound()) {
                                // Find Round
                                Round round = null;
                                for (int cpt = 0; cpt < Tournament.getTournament().getRoundsCount(); cpt++) {

                                    Round r = Tournament.getTournament().getRound(cpt);
                                    if (r.containsMatch(m)) {
                                        round = r;
                                    }
                                }
                                if (round != null) {
                                    if (round.getMatchsCount() > 0) {
                                        double fBonus = bonus * round.getCoef(m);
                                        bonus = (int) Math.round(fBonus);
                                    }

                                }
                            }

                            value += bonus;
                        }
                    }
                    if (withMainPoints) {
                        value += getGroupModifier(c, m);
                    }
                }
            }
        }
        return value;
    }

    /**
     * Returns current value of a criteria for this match
     *
     * @param crit Selected criteria
     * @param subtype Subtype 0: POSITIVE, 1: NEGATIVE 2: DIFFERENCE
     * @param c Competitor
     * @return Integer
     */
    public int getValue(Criteria crit, int subtype, Competitor c) {
        int value = 0;
        Value v = getValue(crit);
        if (v != null) {
            if (c == mCompetitor1) {
                switch (subtype) {
                    case Parameters.C_RANKING_SUBTYPE_POSITIVE:
                        value = v.getValue1();
                        if ((value == -1) && (crit == Tournament.getTournament().getParams().getCriteria(0))) {
                            value = 0;
                        }
                        break;
                    case Parameters.C_RANKING_SUBTYPE_NEGATIVE:
                        if ((value == -1) && (crit == Tournament.getTournament().getParams().getCriteria(0))) {
                            value = 0;
                        }
                        value = v.getValue2();
                        break;
                    case Parameters.C_RANKING_SUBTYPE_DIFFERENCE:
                        value = v.getValue1() - v.getValue2();
                        break;
                }
            }
            if (c == mCompetitor2) {
                switch (subtype) {
                    case Parameters.C_RANKING_SUBTYPE_POSITIVE:
                        value = v.getValue2();
                        if ((value == -1) && (crit == Tournament.getTournament().getParams().getCriteria(0))) {
                            value = 0;
                        }
                        break;
                    case Parameters.C_RANKING_SUBTYPE_NEGATIVE:
                        value = v.getValue1();
                        if ((value == -1) && (crit == Tournament.getTournament().getParams().getCriteria(0))) {
                            value = 0;
                        }
                        break;
                    case Parameters.C_RANKING_SUBTYPE_DIFFERENCE:
                        value = v.getValue2() - v.getValue1();
                        break;
                }
            }
        }
        return value;
    }

    public int getValue(Formula formula,  Competitor c) {
        int value = 0;
        Value v = getValue(formula);
        if (v != null) {
            if (c == mCompetitor1) {
                value = v.getValue1();

            }
            if (c == mCompetitor2) {

                value = v.getValue2();

            }
        }
        return value;
    }

    public boolean isEntered() {
        Criteria td = Tournament.getTournament().getParams().getCriteria(0);
        Value v = getValue(td);

        if (this.isConcedeedBy1() || isConcedeedBy2() || isRefusedBy1() || isConcedeedBy2()) {
            return true;
        }
        if ((v.getValue1() == -1) || (v.getValue2() == -1)) {
            return false;
        }
        return true;
    }

    public void switchCoachs() {
        Competitor c_tmp = mCompetitor1;
        mCompetitor1 = mCompetitor2;
        mCompetitor2 = c_tmp;

        int value_tmp = this.c1value1;
        c1value1 = this.c2value1;
        c2value1 = value_tmp;

        value_tmp = this.c1value2;
        c1value2 = this.c2value2;
        c2value2 = value_tmp;

        value_tmp = this.c1value3;
        c1value3 = this.c2value3;
        c2value3 = value_tmp;

        value_tmp = this.c1value4;
        c1value4 = this.c2value4;
        c2value4 = value_tmp;

        value_tmp = this.c1value5;
        c1value5 = this.c2value5;
        c2value5 = value_tmp;

        boolean b_tmp = this.concedeedBy1;
        concedeedBy1 = this.concedeedBy2;
        concedeedBy2 = b_tmp;

        b_tmp = this.refusedBy1;
        refusedBy1 = this.refusedBy2;
        refusedBy2 = b_tmp;

        RosterType r_tmp = this.mRoster1;
        this.mRoster1 = this.mRoster2;
        this.mRoster2 = r_tmp;

        Substitute s_tmp = this.mSubstitute1;
        this.mSubstitute1 = this.mSubstitute2;
        this.mSubstitute2 = s_tmp;

        for (int i = 0; i < Tournament.getTournament().getParams().getCriteriaCount(); i++) {
            Criteria crit = Tournament.getTournament().getParams().getCriteria(i);
            Value val = mValues.get(crit);
            value_tmp = val.getValue1();
            val.setValue1(val.getValue2());
            val.setValue2(value_tmp);
        }

    }
}
