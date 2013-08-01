/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import org.jdom.Attribute;
import org.jdom.Element;

/**
 *
 * @author Frederic Berger
 */
public class ObjectRanking implements Comparable, XMLExport {

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

    /*public Element getXMLElement(int index) {
        Element ic = new Element("Position");
        ic.setAttribute(new Attribute("pos", Integer.toString(index)));

        if (_object instanceof Coach) {
            Coach c = (Coach) _object;
            ic.setAttribute(new Attribute("coach", c._name));
            ic.setAttribute(new Attribute("team", c._team));
            ic.setAttribute(new Attribute("clan", c._clan._name));
            ic.setAttribute(new Attribute("roster", c._roster._name));
        }

        if (_object instanceof Team) {
            Team t = (Team) _object;
            ic.setAttribute(new Attribute("name", t._name));
            for (int k = 0; k < t._coachs.size(); k++) {
                Element c = new Element("member");
                c.setAttribute("name", t._coachs.get(k)._name);
                ic.addContent(c);
            }
        }

        if (_object instanceof Clan) {
            Clan t = (Clan) _object;
            ic.setAttribute(new Attribute("clan", t._name));
            for (int k = 0; k < Tournament.getTournament().getCoachs().size(); k++) {
                if (Tournament.getTournament().getCoachs().get(k)._clan == t) {
                    Element m = new Element("member");
                    m.setAttribute("name", Tournament.getTournament().getCoachs().get(k)._name);
                    ic.addContent(m);
                }
            }
        }
        ic.setAttribute(new Attribute("rank1", Integer.toString(_value1)));
        ic.setAttribute(new Attribute("rank2", Integer.toString(_value2)));
        ic.setAttribute(new Attribute("rank3", Integer.toString(_value3)));
        ic.setAttribute(new Attribute("rank4", Integer.toString(_value4)));
        ic.setAttribute(new Attribute("rank5", Integer.toString(_value5)));

        return ic;
    }*/
    
    public Element getXMLElement() {
        Element ic = new Element("Position");
        //ic.setAttribute(new Attribute("pos", Integer.toString(index)));

        if (_object instanceof Coach) {
            Coach c = (Coach) _object;
            ic.setAttribute(new Attribute("coach", c._name));
            ic.setAttribute(new Attribute("team", c._team));
            ic.setAttribute(new Attribute("clan", c._clan._name));
            ic.setAttribute(new Attribute("roster", c._roster._name));
        }

        if (_object instanceof Team) {
            Team t = (Team) _object;
            ic.setAttribute(new Attribute("name", t._name));
            for (int k = 0; k < t._coachs.size(); k++) {
                Element c = new Element("member");
                c.setAttribute("name", t._coachs.get(k)._name);
                ic.addContent(c);
            }
        }

        if (_object instanceof Clan) {
            Clan t = (Clan) _object;
            ic.setAttribute(new Attribute("clan", t._name));
            for (int k = 0; k < Tournament.getTournament().getCoachs().size(); k++) {
                if (Tournament.getTournament().getCoachs().get(k)._clan == t) {
                    Element m = new Element("member");
                    m.setAttribute("name", Tournament.getTournament().getCoachs().get(k)._name);
                    ic.addContent(m);
                }
            }
        }
        ic.setAttribute(new Attribute("rank1", Integer.toString(_value1)));
        ic.setAttribute(new Attribute("rank2", Integer.toString(_value2)));
        ic.setAttribute(new Attribute("rank3", Integer.toString(_value3)));
        ic.setAttribute(new Attribute("rank4", Integer.toString(_value4)));
        ic.setAttribute(new Attribute("rank5", Integer.toString(_value5)));

        return ic;
    }
    
    @Override
    public void setXMLElement(Element e) {
        
    }
}
