/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;
import org.jdom2.Element;
import tourma.utility.StringConstants;

/**
 *
 * @author Administrateur
 */
public class Group implements XMLExport {
    private static final Logger LOG = Logger.getLogger(Group.class.getName());

    /**
     *
     */
    private String mName = java.util.ResourceBundle.getBundle("tourma/languages/language").getString("");

    /**
     *
     */
    private ArrayList<RosterType> mRosters;

    /**
     *
     * @param name
     */
    public Group(final String name) {
        mName = name;
        mRosters = new ArrayList<>();
    }

    /**
     *
     * @return
     */
    @Override
    public Element getXMLElement() {
        final Element group = new Element(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("GROUP"));
        group.setAttribute(StringConstants.CS_NAME, this.getName());
        for (int j = 0; j < this.getRosterCount(); j++) {
            final Element roster = new Element(StringConstants.CS_ROSTER);
            roster.setAttribute(StringConstants.CS_NAME, this.getRoster(j).getName());
            group.addContent(roster);
        }
        return group;
    }

    /**
     *
     * @param group
     */
    @Override
    public void setXMLElement(final Element group) {
        setName(group.getAttributeValue(StringConstants.CS_NAME));

        final List rosters = group.getChildren(StringConstants.CS_ROSTER);
        final Iterator ro = rosters.iterator();
        while (ro.hasNext()) {
            final Element roster = (Element) ro.next();
             String name=roster.getAttributeValue(StringConstants.CS_NAME);
                        name=RosterType.getRosterName(name);
            final RosterType rost = RosterType.getRosterType(name);
            this.addRoster(rost);
        }
    }

    /**
     * @return the mName
     */
    public String getName() {
        return mName;
    }

    /**
     * @param mName the mName to set
     */
    public void setName(String mName) {
        this.mName = mName;
    }

    /**
     * @param i
     * @return the mRosters
     */
    public RosterType getRoster(int i) {
        return mRosters.get(i);
    }
 
            
    /**
     *
     * @return 
     */
    public int getRosterCount() {
        return mRosters.size();
    }
    
    /**
     * 
     * @param rt 
     */
    public void addRoster(RosterType rt) {
        mRosters.add(rt);
    }
    
    /**
     * 
     * @param rt 
     */
    public void removeRoster(RosterType rt) {
        mRosters.remove(rt);
    }

    /**
     * New roster array
     */
    public void newRosters() {
        this.mRosters = new ArrayList<>();
    }
}
