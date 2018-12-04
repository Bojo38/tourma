/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom.DataConversionException;
import org.jdom.Element;
import tourma.utility.StringConstants;

/**
 *
 * @author Administrateur
 */
public class Group implements IXMLExport, Serializable {

    protected static AtomicInteger sGenUID = new AtomicInteger(0);
    protected int UID = sGenUID.incrementAndGet();

    public int getUID() {
        return UID;
    }

    public void setUID(int UID) {
        this.UID = UID;
    }

    int _points;

    public int getPoints() {
        return _points;
    }

    public void setPoints(int _points) {
        this._points = _points;
    }

    private static final Logger LOG = Logger.getLogger(Group.class.getName());

    /**
     *
     */
    private String mName = StringConstants.CS_NULL;

    /**
     *
     */
    private ArrayList<RosterType> mRosters;

    /**
     *
     */
    private final HashMap<Group, GroupPoints> opponentModificationPoints = new HashMap<>();

    public void pull(Group group) {
        this.UID = group.UID;
        this.mName = group.mName;
        for (RosterType rt : group.mRosters) {
            RosterType roster = RosterType.getRosterType(rt.getName());
            if (roster != null) {
                boolean bFound = false;
                for (RosterType local : mRosters) {
                    if (local.getUID() == roster.getUID()) {
                        bFound = true;
                        break;
                    }
                }
                if (!bFound) {
                    mRosters.add(roster);
                }

            }
        }
        if (mRosters.size() != group.getRosterCount()) {
            mRosters.clear();
            pull(group);
        }
    }

    public void pullOpponentGroupModifierPoints(Group group) {
        for (Group opponent : group.opponentModificationPoints.keySet()) {
            // find corresponding local group
            Group localOpp = Tournament.getTournament().getGroup(opponent.getName());

            if (localOpp != null) {
                GroupPoints gp = this.opponentModificationPoints.get(localOpp);
                if (gp == null) {
                    gp = new GroupPoints();
                }
                gp.pull(group.opponentModificationPoints.get(opponent));

                opponentModificationPoints.put(localOpp, gp);

                // pull points modifiers
            }
        }

    }

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
        final Element group = new Element(StringConstants.CS_GROUP);
        group.setAttribute(StringConstants.CS_NAME, this.getName());
        group.setAttribute(StringConstants.CS_POINTS, Integer.toString(this.getPoints()));
        for (int j = 0; j < this.getRosterCount(); j++) {
            final Element roster = new Element(StringConstants.CS_ROSTER);
            if (this.getRoster(j) != null) {
                if (this.getRoster(j).getName() == null) {
                    this.getRoster(j).setName("Unknown");
                }
                roster.setAttribute(StringConstants.CS_NAME, this.getRoster(j).getName());
                group.addContent(roster);
            }

        }
        return group;
    }

    public Element getXMLElementForPoints() {
        final Element group = new Element(StringConstants.CS_GROUP_MODIFIER_POINTS);
        group.setAttribute(StringConstants.CS_NAME, this.getName());

        for (Group g : opponentModificationPoints.keySet()) {
            final Element ge = new Element(StringConstants.CS_GROUP);
            ge.setAttribute(StringConstants.CS_NAME, g.getName());
            GroupPoints gp = opponentModificationPoints.get(g);
            ge.setAttribute(StringConstants.CS_VICTORY, Integer.toString(gp.getVictoryPoints()));
            ge.setAttribute(StringConstants.CS_DRAW, Integer.toString(gp.getDrawPoints()));
            ge.setAttribute(StringConstants.CS_LOST, Integer.toString(gp.getLossPoints()));
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

        try {
            setPoints(group.getAttribute(StringConstants.CS_POINTS).getIntValue());
        } catch (DataConversionException | NullPointerException ex) {

        }

        final List<Element> rosters = group.getChildren(StringConstants.CS_ROSTER);
        final Iterator<Element> ro = rosters.iterator();
        while (ro.hasNext()) {
            final Element roster = ro.next();
            String name = roster.getAttributeValue(StringConstants.CS_NAME);
            name = RosterType.getRosterName(name);
            final RosterType rost = RosterType.getRosterType(name);
            this.addRoster(rost);
        }
    }

    public void setXMLElementForPoints(final Element group) {

        final List<Element> groups = group.getChildren(StringConstants.CS_GROUP);
        final Iterator<Element> gro = groups.iterator();
        while (gro.hasNext()) {
            try {
                final Element g = gro.next();
                String name = g.getAttributeValue(StringConstants.CS_NAME);
                Group ge = Tournament.getTournament().getGroup(name);
                GroupPoints gp = new GroupPoints();
                gp.setDrawPoints(g.getAttribute(StringConstants.CS_DRAW).getIntValue());
                gp.setVictoryPoints(g.getAttribute(StringConstants.CS_VICTORY).getIntValue());
                gp.setLossPoints(g.getAttribute(StringConstants.CS_LOST).getIntValue());
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
        if (i < mRosters.size()) {
            return mRosters.get(i);
        } else {
            return null;
        }
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
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(final Object obj) {

        boolean result;
        result = false;
        if (obj instanceof Group) {
            Group g = (Group) obj;
            result = this.getName().equals(g.getName());
            result &= this.getRosterCount() == g.getRosterCount();
            for (RosterType rt : mRosters) {
                result &= g.containsRoster(rt);
            }
            for (Group og : opponentModificationPoints.keySet()) {
                result &= getOpponentModificationPoints(og).equals(g.getOpponentModificationPoints(og));;
            }
        }

        return result;

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
    public boolean containsRoster(RosterType rt) {
        return mRosters.contains(rt);
    }

}
