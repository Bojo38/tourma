/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jdom2.Element;
import tourma.utility.StringConstants;

/**
 *
 * @author Administrateur
 */
public class Group implements XMLExport {

    public String mName = java.util.ResourceBundle.getBundle("tourma/languages/language").getString("");
    public ArrayList<RosterType> mRosters;

    public Group(final String name) {
        mName = name;
        mRosters = new ArrayList<>();
    }

    @Override
    public Element getXMLElement() {
        final Element group = new Element(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("GROUP"));
        group.setAttribute(StringConstants.CS_NAME, this.mName);
        for (int j = 0; j < this.mRosters.size(); j++) {
            final Element roster = new Element(StringConstants.CS_ROSTER);
            roster.setAttribute(StringConstants.CS_NAME, this.mRosters.get(j).mName);
            group.addContent(roster);
        }
        return group;
    }

    @Override
    public void setXMLElement(final Element group) {
        mName = group.getAttributeValue(StringConstants.CS_NAME);

        final List rosters = group.getChildren(StringConstants.CS_ROSTER);
        final Iterator ro = rosters.iterator();
        while (ro.hasNext()) {
            final Element roster = (Element) ro.next();
             String name=roster.getAttributeValue(StringConstants.CS_NAME);
                        name=RosterType.getRosterName(name);
            final RosterType rost = RosterType.mRosterTypes.get(name);
            this.mRosters.add(rost);
        }
    }
}
