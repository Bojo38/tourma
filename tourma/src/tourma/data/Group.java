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
 * @author Administrateur
 */
public class Group implements XMLExport {

    public String _name = "";
    public Vector<RosterType> _rosters;

    public Group(String name) {
        _name = name;
        _rosters = new Vector<RosterType>();
    }

    public Element getXMLElement() {
        Element group = new Element("Group");
        group.setAttribute("Name", this._name);
        for (int j = 0; j < this._rosters.size(); j++) {
            Element roster = new Element("Roster");
            roster.setAttribute("Name", this._rosters.get(j)._name);
            group.addContent(roster);
        }
        return group;
    }

    public void setXMLElement(Element group) {
        _name = group.getAttributeValue("Name");

        List rosters = group.getChildren("Roster");
        Iterator ro = rosters.iterator();
        while (ro.hasNext()) {
            Element roster = (Element) ro.next();
            RosterType rost = new RosterType(roster.getAttributeValue("Name"));
            this._rosters.add(rost);
        }
    }
}
