/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jdom.Element;
import tourma.utility.StringConstants;

/**
 *
 * @author WFMJ7631
 */
public class Pool implements XMLExport {

    public ArrayList<Coach> mCoachs = new ArrayList<Coach>();
    public ArrayList<Team> mTeams = new ArrayList<Team>();
    public String mName = "";

    public Element getXMLElement() {
        final Element pool = new Element("Pool");
        pool.setAttribute(StringConstants.CS_NAME, this.mName);
        for (int j = 0; j < this.mCoachs.size(); j++) {
            final Element coach = new Element(StringConstants.CS_COACH);
            coach.setAttribute(StringConstants.CS_NAME, this.mCoachs.get(j).mName);
            pool.addContent(coach);
        }
        for (int j = 0; j < this.mTeams.size(); j++) {
            final Element team = new Element("Team");
            team.setAttribute(StringConstants.CS_NAME, this.mTeams.get(j).mName);
            pool.addContent(team);
        }
        return pool;
    }

    public void setXMLElement(final Element pool) {
       mName = pool.getAttributeValue(StringConstants.CS_NAME);
        
       mCoachs.clear();
        final List coachs = pool.getChildren(StringConstants.CS_COACH);
        Iterator ro = coachs.iterator();
        while (ro.hasNext()) {
            final Element coach = (Element) ro.next();
            final String name=coach.getAttributeValue(StringConstants.CS_NAME);
            this.mCoachs.add(Coach.sCoachMap.get(name));
        }
        
        mTeams.clear();
        final List teams = pool.getChildren("Team");
        ro = teams.iterator();
        while (ro.hasNext()) {
            final Element team = (Element) ro.next();
            final String name=team.getAttributeValue(StringConstants.CS_NAME);
            this.mTeams.add(Team.sTeamMap.get(name));
        }
    }
}
