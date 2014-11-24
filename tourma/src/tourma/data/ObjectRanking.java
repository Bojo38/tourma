/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import java.util.Objects;
import java.util.logging.Logger;
import org.jdom2.Attribute;
import org.jdom2.Element;
import tourma.utility.StringConstants;

/**
 *
 * @author Frederic Berger
 */
public class ObjectRanking implements Comparable, XMLExport {
    private static final Logger LOG = Logger.getLogger(ObjectRanking.class.getName());

    private Comparable mObject;
    private int mValue1;
    private int mValue2;
    private int mValue3;
    private int mValue4;
    private int mValue5;

    /**
     *
     * @param c
     * @param value1
     * @param value2
     * @param value3
     * @param value4
     * @param value5
     */
    public ObjectRanking(final Comparable c, final int value1, final int value2, final int value3, final  int value4, final int value5) {
        mObject = c;
        mValue1 = value1;
        mValue2 = value2;
        mValue3 = value3;
        mValue4 = value4;
        mValue5 = value5;
    }

    /**
     *
     * @return
     */
    public Comparable getObject() {
        return mObject;
    }

    /**
     *
     * @return
     */
    public int getValue1() {
        return mValue1;
    }

    /**
     *
     * @return
     */
    public int getValue2() {
        return mValue2;
    }

    /**
     *
     * @return
     */
    public int getValue3() {
        return mValue3;
    }

    /**
     *
     * @return
     */
    public int getValue4() {
        return mValue4;
    }

    /**
     *
     * @return
     */
    public int getValue5() {
        return mValue5;
    }

    @Override
    public int compareTo(final Object o) {
        int value = -65535;
        if (o instanceof ObjectRanking) {
            if (((ObjectRanking) o).getValue1() == getValue1()) {
                if (((ObjectRanking) o).getValue2() == getValue2()) {
                    if (((ObjectRanking) o).getValue3() == getValue3()) {
                        if (((ObjectRanking) o).getValue4() == getValue4()) {
                            value = ((ObjectRanking) o).getValue5() - getValue5();
                        } else {
                            value = ((ObjectRanking) o).getValue4() - getValue4();
                        }
                    } else {
                        value = ((ObjectRanking) o).getValue3() - getValue3();
                    }

                } else {
                    value = ((ObjectRanking) o).getValue2() - getValue2();
                }

            } else {
                value = ((ObjectRanking) o).getValue1() - getValue1();
            }
        }
        return value;
    }

    /**
     * 
     * @return 
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + Objects.hashCode(this.mObject);
        hash = 17 * hash + this.mValue1;
        hash = 17 * hash + this.mValue2;
        hash = 17 * hash + this.mValue3;
        hash = 17 * hash + this.mValue4;
        hash = 17 * hash + this.mValue5;
        return hash;
    }

    /**
     * 
     * @param o
     * @return 
     */
    @Override
    public boolean equals(Object o)
    {
        if (o instanceof ObjectRanking)
        {
             return (((ObjectRanking)o).getValue5()==getValue5())
            &&(((ObjectRanking)o).getValue4()==getValue4())                    
                    &&(((ObjectRanking)o).getValue1()==getValue1())
                    &&(((ObjectRanking)o).getValue2()==getValue2())
                    &&(((ObjectRanking)o).getValue3()==getValue3());
        }
        return false;
    }
    
    /**
     *
     * @return
     */
    @Override
    public Element getXMLElement() {
        final Element ic = new Element(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("POSITION"));
        //ic.setAttribute(new Attribute("pos", Integer.toString(index)));

        if (getObject() instanceof Coach) {
            final Coach c = (Coach) getObject();
            ic.setAttribute(new Attribute(StringConstants.CS_COACH, c.getName()));
            ic.setAttribute(new Attribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("TEAM"), c.getTeam()));
            ic.setAttribute(new Attribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("CLAN"), c.getClan().getName()));
            ic.setAttribute(new Attribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("ROSTER"), c.getRoster().getName()));
        }

        if (getObject() instanceof Team) {
            final Team t = (Team) getObject();
            ic.setAttribute(new Attribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("NAME"), t.getName()));
            for (int k = 0; k < t.getCoachCount(); k++) {
                final Element c = new Element(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("MEMBER"));
                c.setAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("NAME"), t.getCoach(k).getName());
                ic.addContent(c);
            }
        }

        if (getObject() instanceof Clan) {
            final Clan t = (Clan) getObject();
            ic.setAttribute(new Attribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("CLAN"), t.getName()));
            for (int k = 0; k < Tournament.getTournament().getCoachsCount(); k++) {
                if (Tournament.getTournament().getCoach(k).getClan() == t) {
                    final Element m = new Element(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("MEMBER"));
                    m.setAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("NAME"), Tournament.getTournament().getCoach(k).getName());
                    ic.addContent(m);
                }
            }
        }
        ic.setAttribute(new Attribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("RANK1"), Integer.toString(getValue1())));
        ic.setAttribute(new Attribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("RANK2"), Integer.toString(getValue2())));
        ic.setAttribute(new Attribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("RANK3"), Integer.toString(getValue3())));
        ic.setAttribute(new Attribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("RANK4"), Integer.toString(getValue4())));
        ic.setAttribute(new Attribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("RANK5"), Integer.toString(getValue5())));

        return ic;
    }

    /**
     *
     * @param e
     */
    @Override
    public void setXMLElement(final Element e) {
        // Method only for implementing interface
    }

    /**
     * @param mObject the mObject to set
     */
    public void setObject(Comparable mObject) {
        this.mObject = mObject;
    }

    /**
     * @param mValue1 the mValue1 to set
     */
    public void setValue1(int mValue1) {
        this.mValue1 = mValue1;
    }

    /**
     * @param mValue2 the mValue2 to set
     */
    public void setValue2(int mValue2) {
        this.mValue2 = mValue2;
    }

    /**
     * @param mValue3 the mValue3 to set
     */
    public void setValue3(int mValue3) {
        this.mValue3 = mValue3;
    }

    /**
     * @param mValue4 the mValue4 to set
     */
    public void setValue4(int mValue4) {
        this.mValue4 = mValue4;
    }

    /**
     * @param mValue5 the mValue5 to set
     */
    public void setValue5(int mValue5) {
        this.mValue5 = mValue5;
    }
}
