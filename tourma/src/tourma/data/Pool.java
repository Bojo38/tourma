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
 * @author WFMJ7631
 */
public class Pool implements XMLExport {

    public Vector<Coach> _coachs = new Vector<Coach>();
    public Vector<Team> _teams = new Vector<Team>();
    public String _name = "";

    public Element getXMLElement() {
        Element pool = new Element("Pool");
        pool.setAttribute("Name", this._name);
        for (int j = 0; j < this._coachs.size(); j++) {
            Element coach = new Element("Coach");
            coach.setAttribute("Name", this._coachs.get(j)._name);
            pool.addContent(coach);
        }
        for (int j = 0; j < this._teams.size(); j++) {
            Element team = new Element("Team");
            team.setAttribute("Name", this._teams.get(j)._name);
            pool.addContent(team);
        }
        return pool;
    }

    public void setXMLElement(Element pool) {
       _name = pool.getAttributeValue("Name");
        
       _coachs.removeAllElements();
        List coachs = pool.getChildren("Coach");
        Iterator ro = coachs.iterator();
        while (ro.hasNext()) {
            Element coach = (Element) ro.next();
            String name=coach.getAttributeValue("Name");
            this._coachs.add(Coach._map.get(name));
        }
        
        _teams.removeAllElements();
        List teams = pool.getChildren("Team");
        ro = teams.iterator();
        while (ro.hasNext()) {
            Element team = (Element) ro.next();
            String name=team.getAttributeValue("Name");
            this._teams.add(Team._map.get(name));
        }
    }
}
