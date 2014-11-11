/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

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

    Comparable mObject;
    int mValue1;
    int mValue2;
    int mValue3;
    int mValue4;
    int mValue5;

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
            if (((ObjectRanking) o).mValue1 == mValue1) {
                if (((ObjectRanking) o).mValue2 == mValue2) {
                    if (((ObjectRanking) o).mValue3 == mValue3) {
                        if (((ObjectRanking) o).mValue4 == mValue4) {
                            value = ((ObjectRanking) o).mValue5 - mValue5;
                        } else {
                            value = ((ObjectRanking) o).mValue4 - mValue4;
                        }
                    } else {
                        value = ((ObjectRanking) o).mValue3 - mValue3;
                    }

                } else {
                    value = ((ObjectRanking) o).mValue2 - mValue2;
                }

            } else {
                value = ((ObjectRanking) o).mValue1 - mValue1;
            }
        }
        return value;
    }

    /**
     *
     * @return
     */
    @Override
    public Element getXMLElement() {
        final Element ic = new Element(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("POSITION"));
        //ic.setAttribute(new Attribute("pos", Integer.toString(index)));

        if (mObject instanceof Coach) {
            final Coach c = (Coach) mObject;
            ic.setAttribute(new Attribute(StringConstants.CS_COACH, c.mName));
            ic.setAttribute(new Attribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("TEAM"), c.getTeam()));
            ic.setAttribute(new Attribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("CLAN"), c.mClan.getName()));
            ic.setAttribute(new Attribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("ROSTER"), c.getRoster().mName));
        }

        if (mObject instanceof Team) {
            final Team t = (Team) mObject;
            ic.setAttribute(new Attribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("NAME"), t.mName));
            for (int k = 0; k < t.mCoachs.size(); k++) {
                final Element c = new Element(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("MEMBER"));
                c.setAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("NAME"), t.mCoachs.get(k).mName);
                ic.addContent(c);
            }
        }

        if (mObject instanceof Clan) {
            final Clan t = (Clan) mObject;
            ic.setAttribute(new Attribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("CLAN"), t.getName()));
            for (int k = 0; k < Tournament.getTournament().getCoachs().size(); k++) {
                if (Tournament.getTournament().getCoachs().get(k).mClan == t) {
                    final Element m = new Element(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("MEMBER"));
                    m.setAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("NAME"), Tournament.getTournament().getCoachs().get(k).mName);
                    ic.addContent(m);
                }
            }
        }
        ic.setAttribute(new Attribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("RANK1"), Integer.toString(mValue1)));
        ic.setAttribute(new Attribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("RANK2"), Integer.toString(mValue2)));
        ic.setAttribute(new Attribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("RANK3"), Integer.toString(mValue3)));
        ic.setAttribute(new Attribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("RANK4"), Integer.toString(mValue4)));
        ic.setAttribute(new Attribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("RANK5"), Integer.toString(mValue5)));

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
}
