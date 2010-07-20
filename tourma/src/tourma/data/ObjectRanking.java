/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;


/**
 *
 * @author Frederic Berger
 */
public class ObjectRanking implements Comparable {

    Comparable _object;
    int _value1;
    int _value2;
    int _value3;
    int _value4;
    int _value5;

    public Comparable getObject() {
        return _object;
    }

    public int getValue1() {
        return _value1;
    }

    public int getValue2() {
        return _value2;
    }

    public int getValue3() {
        return _value3;
    }

    public int getValue4() {
        return _value4;
    }

    public int getValue5() {
        return _value5;
    }

    public ObjectRanking(Comparable c, int value1, int value2, int value3, int value4, int value5) {
        _object = c;
        _value1 = value1;
        _value2 = value2;
        _value3 = value3;
        _value4 = value4;
        _value5 = value5;
    }

    public int compareTo(Object o) {
        if (o instanceof ObjectRanking) {
            if (((ObjectRanking) o)._value1 == _value1) {
                if (((ObjectRanking) o)._value2 == _value2) {
                    if (((ObjectRanking) o)._value3 == _value3) {
                        if (((ObjectRanking) o)._value4 == _value4) {
                            return ((ObjectRanking) o)._value5 - _value5;
                        } else {
                            return ((ObjectRanking) o)._value4 - _value4;
                        }
                    } else {
                        return ((ObjectRanking) o)._value3 - _value3;
                    }

                } else {
                    return ((ObjectRanking) o)._value2 - _value2;
                }

            } else {
                return ((ObjectRanking) o)._value1 - _value1;
            }
        } else {
            return -65535;
        }
    }
}
