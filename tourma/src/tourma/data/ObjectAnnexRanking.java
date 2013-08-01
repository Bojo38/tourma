/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import org.jdom.Attribute;
import org.jdom.Element;
import tourma.data.Coach;

/**
 *
 * @author Frederic Berger
 */
public class ObjectAnnexRanking extends ObjectRanking {

   
    public int _value;


    public int getValue() {
        return _value;
    }


    public ObjectAnnexRanking(Comparable c, int value, int value1, int value2, int value3, int value4, int value5) {
        super(c,value1,value2,value3,value4,value5);
        _value = value;
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof ObjectAnnexRanking) {
            if (((ObjectAnnexRanking) o)._value == _value) {
                return super.compareTo(o);
            } else {
                return ((ObjectAnnexRanking) o)._value - _value;
            }
        } else {
            return -65535;
        }
    }
    
    @Override
    public Element getXMLElement() {
        Element ic = super.getXMLElement();
        
        ic.setAttribute(new Attribute("value", Integer.toString(_value)));
      
        ic.removeAttribute("rank1");
        ic.removeAttribute("rank2");
        ic.removeAttribute("rank3");
        ic.removeAttribute("rank4");
        ic.removeAttribute("rank5");
        return ic;
    }
    
    @Override
    public void setXMLElement(Element e) {
         super.setXMLElement(e);
    }
}
