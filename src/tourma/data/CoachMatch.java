/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.jdom2.Attribute;
import org.jdom2.DataConversionException;
import org.jdom2.Element;
import tourma.MainFrame;
import tourma.tableModel.MjtRanking;
import tourma.utility.StringConstants;

/**
 *
 * @author Frederic Berger
 */
public class CoachMatch extends Match implements Serializable {

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

    public HashMap<Criteria, Value> getValues() {
        return mValues;
    }

    /**
     *
     * @param round
     */
    public CoachMatch(Round round)  throws RemoteException{
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

    @Override
    public boolean equals(Object c) {
        if (this == c) {
            return true;
        }
        
        try
        {
             
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
        }
        catch (RemoteException re)
        {
            JOptionPane.showMessageDialog(null, re.getLocalizedMessage());
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
    public Element getXMLElement() throws RemoteException{
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
    )throws RemoteException {
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
                final Element val = v.next();
                Criteria crit = null;

                for (int cpt = 0; cpt < Tournament.getTournament().getParams().getCriteriaCount(); cpt++) {
                    final Criteria criteria = Tournament.getTournament().getParams().getCriteria(cpt);
                    final String tmp = val.getAttribute(StringConstants.CS_NAME).getValue();

                    if (criteria.getName().equals(tmp)) {
                        crit = criteria;
                    }
                }
                final Value value = new Value(crit);
                value.setValue1(val.getAttribute(StringConstants.CS_VALUE + 1).getIntValue());
                value.setValue2(val.getAttribute(StringConstants.CS_VALUE + 2).getIntValue());
                this.putValue(crit, value);
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
            JOptionPane.showMessageDialog(MainFrame.getMainFrame(), dce.getLocalizedMessage());
        }
    }

    /**
     *
     * @return
     */
    @Override
    public Competitor getWinner() throws RemoteException{
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
    public Competitor getLooser() throws RemoteException{
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
    public void resetWL() throws RemoteException{
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
    public void setRoster1(RosterType mRoster1) throws RemoteException{
        this.mRoster1 = mRoster1;
    }

    /**
     * @return the mRoster2
     */
    public RosterType getRoster2() throws RemoteException{
        return mRoster2;
    }

    /**
     * @param mRoster2 the mRoster2 to set
     */
    public void setRoster2(RosterType mRoster2) throws RemoteException{
        this.mRoster2 = mRoster2;
    }

    /**
     * @return the mSubstitute1
     */
    public Substitute getSubstitute1() throws RemoteException{
        return mSubstitute1;
    }

    /**
     * @param mSubstitute1 the mSubstitute1 to set
     */
    public void setSubstitute1(Substitute mSubstitute1) throws RemoteException{
        this.mSubstitute1 = mSubstitute1;
    }

    /**
     * @return the mSubstitute2
     */
    public Substitute getSubstitute2() throws RemoteException{
        return mSubstitute2;
    }

    /**
     * @param mSubstitute2 the mSubstitute2 to set
     */
    public void setSubstitute2(Substitute mSubstitute2) throws RemoteException{
        this.mSubstitute2 = mSubstitute2;
    }

    /**
     * @return the refusedBy1
     */
    public boolean isRefusedBy1() throws RemoteException{
        return refusedBy1;
    }

    /**
     * @param refusedBy1 the refusedBy1 to set
     */
    public void setRefusedBy1(boolean refusedBy1) throws RemoteException{
        this.refusedBy1 = refusedBy1;
    }

    /**
     * @return the refusedBy2
     */
    public boolean isRefusedBy2() throws RemoteException{
        return refusedBy2;
    }

    /**
     * @param refusedBy2 the refusedBy2 to set
     */
    public void setRefusedBy2(boolean refusedBy2) throws RemoteException{
        this.refusedBy2 = refusedBy2;
    }

    /**
     * @return the concedeedBy1
     */
    public boolean isConcedeedBy1() throws RemoteException{
        return concedeedBy1;
    }

    /**
     * @param concedeedBy1 the concedeedBy1 to set
     */
    public void setConcedeedBy1(boolean concedeedBy1) throws RemoteException{
        this.concedeedBy1 = concedeedBy1;
    }

    /**
     * @return the concedeedBy2
     */
    public boolean isConcedeedBy2() throws RemoteException{
        return concedeedBy2;
    }

    /**
     * @param concedeedBy2 the concedeedBy2 to set
     */
    public void setConcedeedBy2(boolean concedeedBy2) throws RemoteException{
        this.concedeedBy2 = concedeedBy2;
    }

    private static int getGroupModifier(Coach c, CoachMatch m) throws RemoteException{
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
    public Value getValue(Criteria c) throws RemoteException{
        return mValues.get(c);
    }

    /**
     *
     * @return
     */
    public int getValueCount() throws RemoteException{
        return mValues.size();
    }

    /**
     *
     * @param c
     * @param v
     */
    public void putValue(Criteria c, Value v) throws RemoteException{
        mValues.put(c, v);
    }

    /**
     *
     * @param c
     */
    public void removeValue(Criteria c) throws RemoteException{
        mValues.remove(c);
    }

    @Override
    public Element getXMLElementForDisplay() throws RemoteException{
        Element match = getXMLElement();
        Element c1 = ((IXMLExport) getCompetitor1()).getXMLElement();
        Element c2 = ((IXMLExport) getCompetitor2()).getXMLElement();
        match.addContent(c1);
        match.addContent(c2);
        return match;
    }

    @Override
    public void setXMLElementForDisplay(Element element) throws RemoteException{

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

    public boolean isFullNaf() throws RemoteException{
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
    public void recomputeValues() throws RemoteException{
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
    }

    /**
     *
     * @param valueType
     * @return
     */
    public static int getSubtypeByValue(final int valueType) throws RemoteException{
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
    public static Criteria getCriteriaByValue(final int valueType) throws RemoteException{
        Criteria criteria = null;

        if (valueType > Parameters.C_MAX_RANKING) {
            final int value = valueType - Parameters.C_MAX_RANKING - 1;
            criteria = Tournament.getTournament().getParams().getCriteria(value / 3);
        }
        return criteria;
    }

    protected int recomputeValue(int index, Competitor c) throws RemoteException{
        int value = 0;
        int valueType = Parameters.C_RANKING_NONE;
        valueType = Tournament.getTournament().getParams().getIndivRankingType(index - 1);
        if (valueType < Parameters.C_MAX_RANKING) {
            value = getValue((Coach) c, valueType);
        } else {
            value = getValue(MjtRanking.getCriteriaByValue(valueType), MjtRanking.getSubtypeByValue(valueType), c);
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
    public int getValue(final Coach c, final int valueType) throws RemoteException{
        int value;

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
            default:
                value = 0;
                break;
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
    public static int getOppPointsByCoach(final Coach c, final CoachMatch m, boolean includeCurrent) throws RemoteException{
        int index = 0;
        CoachMatch tmp_m = (CoachMatch) c.getMatch(index);
        while (tmp_m != m) {
            index++;
            tmp_m = (CoachMatch) c.getMatch(index);
        }

        int value = 0;
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
        }

        return value;
    }

    /**
     *
     * @param c
     * @param m
     * @return
     */
    public static int getCoachNbMatchs(final Coach c, final CoachMatch m) throws RemoteException{
        int index = c.matchIndex(m);
        return index + 1;
    }

    public static int getCoachTablePoints(final Coach c, final CoachMatch m) throws RemoteException{
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
    public static int getOppELOByCoach(final Coach c, final CoachMatch m) throws RemoteException{
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
    public static int getVNDByCoach(final Coach c, final CoachMatch m) throws RemoteException{
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
    public static int getELOByCoach(final Coach c, final CoachMatch m) throws RemoteException{
        int value = 0;

        Value val = m.getValue(Tournament.getTournament().getParams().getCriteria(0));
        if (val.getValue1() >= 0) {
            // Get current Round index
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
                    if (m.getCompetitor1() == c) {
                        SA += val.getValue1() * crit.getPointsFor();
                        SA -= val.getValue2() * crit.getPointsFor();

                        SA -= val.getValue1() * crit.getPointsAgainst();
                        SA += val.getValue2() * crit.getPointsAgainst();
                    }
                    if (m.getCompetitor2() == c) {
                        SA -= val.getValue1() * crit.getPointsFor();
                        SA += val.getValue2() * crit.getPointsFor();

                        SA -= val.getValue2() * crit.getPointsAgainst();
                        SA += val.getValue1() * crit.getPointsAgainst();
                    }
                }
                double diff = SA / 1000 - EA;
                value = Math.round((float) (lastPlayerRank + C_ELO_K * diff));
            }
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
    public static int getPointsByCoach(final Coach c, final CoachMatch m, final boolean withMainPoints, final boolean withBonusPOints) throws RemoteException{
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
                                bonus += va.getValue1() * cri.getPointsFor();
                                bonus += va.getValue2() * cri.getPointsAgainst();
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
                                bonus += va.getValue2() * cri.getPointsFor();
                                bonus += va.getValue1() * cri.getPointsAgainst();
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
    public int getValue(Criteria crit, int subtype, Competitor c) throws RemoteException{
        int value = 0;
        Value v = getValue(crit);
        if (v != null) {
            if (c == mCompetitor1) {
                switch (subtype) {
                    case Parameters.C_RANKING_SUBTYPE_POSITIVE:
                        value = v.getValue1();
                        break;
                    case Parameters.C_RANKING_SUBTYPE_NEGATIVE:
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
                        break;
                    case Parameters.C_RANKING_SUBTYPE_NEGATIVE:
                        value = v.getValue1();
                        break;
                    case Parameters.C_RANKING_SUBTYPE_DIFFERENCE:
                        value = v.getValue2() - v.getValue1();
                        break;
                }
            }
        }
        return value;
    }

    public boolean isEntered() throws RemoteException{
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
}
