/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import org.jdom2.Attribute;
import org.jdom2.Element;

/**
 *
 * @author Frederic Berger
 */
public class ObjectAnnexRanking extends ObjectRanking {

    public int mValue;

    public int getValue() {
        return mValue;
    }

    public ObjectAnnexRanking(final Comparable c, final int value,final  int value1, final int value2,final  int value3,final  int value4, final int value5) {
        super(c, value1, value2, value3, value4, value5);
        mValue = value;
    }

    @Override
    public int compareTo(final Object o) {
        int result = -65535;
        if (o instanceof ObjectAnnexRanking) {
            if (((ObjectAnnexRanking) o).mValue == mValue) {
                result = super.compareTo(o);
            } else {
                result = ((ObjectAnnexRanking) o).mValue - mValue;
            }
        }
        return result;
    }

    @Override
    public Element getXMLElement() {
        final Element ic = super.getXMLElement();

        ic.setAttribute(new Attribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("VALUE"), Integer.toString(mValue)));

        ic.removeAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("RANK1"));
        ic.removeAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("RANK2"));
        ic.removeAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("RANK3"));
        ic.removeAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("RANK4"));
        ic.removeAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("RANK5"));
        return ic;
    }

    @Override
    public void setXMLElement(final Element e) {
        super.setXMLElement(e);
    }
}
