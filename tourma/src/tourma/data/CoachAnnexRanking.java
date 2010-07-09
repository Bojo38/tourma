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
public class CoachAnnexRanking implements Comparable {

    public Coach _coach;
    public int _value;
    public int _value1;
    public int _value2;
    public int _value3;
    public int _value4;
    public int _value5;

    public Coach getCoach() {
        return _coach;
    }

    public int getValue() {
        return _value;
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

    public CoachAnnexRanking(Coach c, int value, int value1, int value2, int value3, int value4, int value5) {
        _coach = c;
        _value = value;
        _value1 = value1;
        _value2 = value2;
        _value3 = value3;
        _value4 = value4;
        _value5 = value5;
    }

    public int compareTo(Object o) {
        if (o instanceof CoachAnnexRanking) {
            if (((CoachAnnexRanking) o)._value == _value) {
                if (((CoachAnnexRanking) o)._value1 == _value1) {
                    if (((CoachAnnexRanking) o)._value2 == _value2) {
                        if (((CoachAnnexRanking) o)._value3 == _value3) {
                            if (((CoachAnnexRanking) o)._value4 == _value4) {
                                return ((CoachAnnexRanking) o)._value5 - _value5;
                            } else {
                                return ((CoachAnnexRanking) o)._value4 - _value4;
                            }
                        } else {
                            return ((CoachAnnexRanking) o)._value3 - _value3;
                        }

                    } else {
                        return ((CoachAnnexRanking) o)._value2 - _value2;
                    }

                } else {
                    return ((CoachAnnexRanking) o)._value1 - _value1;
                }
            } else {
                return ((CoachAnnexRanking) o)._value - _value;
            }
        } else {
            return -65535;
        }
    }
}
