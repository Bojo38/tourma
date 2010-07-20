/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

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
}
