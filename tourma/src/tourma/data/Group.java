/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom2.DataConversionException;
import org.jdom2.Element;
import static tourma.data.Parameters.sbundle;
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
     */
    private HashMap<Group, GroupPoints> opponentModificationPoints = new HashMap<>();

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

    public Element getXMLElementForPoints() {
        final Element group = new Element("GROUP_MODIFIER_POINTS");
        group.setAttribute(StringConstants.CS_NAME, this.getName());

        for (Group g : opponentModificationPoints.keySet()) {
            final Element ge = new Element(StringConstants.CS_GROUP);
            ge.setAttribute(StringConstants.CS_NAME, g.getName());
            GroupPoints gp = opponentModificationPoints.get(g);
            ge.setAttribute(sbundle.getString("VICTORY"), Integer.toString(gp.getVictoryPoints()));
            ge.setAttribute(sbundle.getString("DRAW"), Integer.toString(gp.getDrawPoints()));
            ge.setAttribute(sbundle.getString("LOST"), Integer.toString(gp.getLossPoints()));
            group.addContent(ge);
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
            String name = roster.getAttributeValue(StringConstants.CS_NAME);
            name = RosterType.getRosterName(name);
            final RosterType rost = RosterType.getRosterType(name);
            this.addRoster(rost);
        }
    }

    public void setXMLElementForPoints(final Element group) {

        final List groups = group.getChildren(StringConstants.CS_GROUP);
        final Iterator gro = groups.iterator();
        while (gro.hasNext()) {
            try {
                final Element g = (Element) gro.next();
                String name = g.getAttributeValue(StringConstants.CS_NAME);
                Group ge = Tournament.getTournament().getGroup(name);
                GroupPoints gp = new GroupPoints();
                gp.setDrawPoints(g.getAttribute(sbundle.getString("DRAW")).getIntValue());
                gp.setVictoryPoints(g.getAttribute(sbundle.getString("VICTORY")).getIntValue());
                gp.setLossPoints(g.getAttribute(sbundle.getString("LOST")).getIntValue());
                this.opponentModificationPoints.put(ge, gp);
            } catch (DataConversionException ex) {
                Logger.getLogger(Group.class.getName()).log(Level.SEVERE, null, ex);
            }
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

    /**
     * @param g
     * @return the opponentModificationPoints
     */
    public GroupPoints getOpponentModificationPoints(Group g) {
        return opponentModificationPoints.get(g);
    }

    /**
     *
     * @param g
     * @param gp
     */
    public void setOpponentModificationPoints(Group g, GroupPoints gp) {
        opponentModificationPoints.put(g, gp);
    }

    /**
     * 
     * @param g 
     */
    public void delOpponentModificationPoints(Group g) {
        opponentModificationPoints.remove(g);
    }
    
    /**
     * 
     * @param rt
     * @return 
     */
    public boolean containsRoster(RosterType rt)
    {
        return mRosters.contains(rt);
    }
}
