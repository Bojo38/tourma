/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import java.util.logging.Logger;
import org.jdom.Attribute;
import org.jdom.Element;
import tourma.utility.StringConstants;

/**
 *
 * @author Frederic Berger
 */
public class ObjectAnnexRanking extends ObjectRanking {

    private static final Logger LOG = Logger.getLogger(ObjectAnnexRanking.class.getName());

    /**
     *
     */
    private int mValue;

    /**
     *
     * @param c
     * @param value
     * @param value1
     * @param value2
     * @param value3
     * @param value4
     * @param value5
     */
    public ObjectAnnexRanking(final Comparable<Object> c, final int value, final int value1, final int value2, final int value3, final int value4, final int value5) {
        super(c, value1, value2, value3, value4, value5);
        mValue = value;
    }

    /**
     *
     * @return
     */
    public int getValue()  {
        return mValue;
    }

    @Override
    public int compareTo(final Object o) {
        int result = -65535;
        if (o instanceof ObjectAnnexRanking) {
            if (((ObjectAnnexRanking) o).getValue() == getValue()) {
                result = super.compareTo(o);
            } else {
                result = ((ObjectAnnexRanking) o).getValue() - getValue();
            }
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
            if (o instanceof ObjectAnnexRanking) {
                if (super.equals(o)) {
                    return (((ObjectAnnexRanking) o).getValue() == getValue());
                }
            }
        return false;
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.mValue;
        return hash;
    }

    /**
     *
     * @return
     */
    @Override
    public Element getXMLElement()  {
        final Element ic = super.getXMLElement();

        ic.setAttribute(new Attribute(StringConstants.CS_VALUE, Integer.toString(getValue())));

        ic.removeAttribute(StringConstants.CS_RANK + 1);
        ic.removeAttribute(StringConstants.CS_RANK + 2);
        ic.removeAttribute(StringConstants.CS_RANK + 3);
        ic.removeAttribute(StringConstants.CS_RANK + 4);
        ic.removeAttribute(StringConstants.CS_RANK + 5);
        return ic;
    }

    /**
     *
     * @param e
     */
    @Override
    public void setXMLElement(final Element e)  {
        super.setXMLElement(e);
    }

    /**
     * @param mValue the mValue to set
     */
    public void setValue(int mValue)  {
        this.mValue = mValue;
    }
}
