/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import org.jdom.Element;

/**
 *
 * @author Frederic Berger
 */
public class Team implements Comparable, XMLExport {

    public Vector<Coach> _coachs;
    public String _name;

    public Team() {
        _coachs = new Vector();
    }

    public int compareTo(Object obj) {
        if (obj instanceof Coach) {
            return _name.compareTo(((Coach) obj)._name);
        } else {
            return -1;
        }
    }

    public int getActivePlayerNumber() {
        int nb = 0;

        for (int i = 0; i < _coachs.size(); i++) {
            if (_coachs.get(i)._active) {
                nb++;
            }
        }
        return nb;
    }

    public Vector<Coach> getActivePlayers() {
        Vector<Coach> v = new Vector<Coach>();

        for (int i = 0; i < _coachs.size(); i++) {
            if (_coachs.get(i)._active) {
                v.add(_coachs.get(i));
            }
        }
        return v;
    }

    public Element getXMLElement() {
        Element team = new Element("Team");
        team.setAttribute("Name", this._name);
        for (int j = 0; j < this._coachs.size(); j++) {
            Element coach = new Element("Coach");
            coach.setAttribute("Name", this._coachs.get(j)._name);
            team.addContent(coach);
        }
        return team;
    }

    public void setXMLElement(Element team) {
        this._name = team.getAttributeValue("Name");
        List coachs2 = team.getChildren("Coach");
        Iterator m = coachs2.iterator();
        this._coachs.clear();

        while (m.hasNext()) {
            Element coach = (Element) m.next();
            Coach c = Coach._map.get(coach.getAttribute("Name").getValue());
            c._teamMates = this;
            this._coachs.add(c);
        }
    }
}
