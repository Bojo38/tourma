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
 * @author WFMJ7631
 */
public class Pool implements XMLExport {
    private static final Logger LOG = Logger.getLogger(Pool.class.getName());

    //public ArrayList<Coach> mCoachs = new ArrayList<>();
    //public ArrayList<Team> mTeams = new ArrayList<>();

    /**
     *
     */
        public ArrayList<Competitor> mCompetitors=new ArrayList<>();

    /**
     *
     */
    public String mName = "";

    /**
     *
     * @return
     */
    @Override
    public Element getXMLElement() {
        final Element pool = new Element(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("POOL"));
        pool.setAttribute(StringConstants.CS_NAME, this.mName);
        for (int j = 0; j < this.mCompetitors.size(); j++) {
            final Element coach = new Element(StringConstants.CS_COACH);
            coach.setAttribute(StringConstants.CS_NAME, this.mCompetitors.get(j).getName());
            pool.addContent(coach);
        }
        /*for (int j = 0; j < this.mTeams.size(); j++) {
            final Element team = new Element(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("TEAM"));
            team.setAttribute(StringConstants.CS_NAME, this.mTeams.get(j).mName);
            pool.addContent(team);
        }*/
        return pool;
    }

    /**
     *
     * @param pool
     */
    @Override
    public void setXMLElement(final Element pool) {
       mName = pool.getAttributeValue(StringConstants.CS_NAME);
        
       mCompetitors.clear();
        final List coachs = pool.getChildren(StringConstants.CS_COACH);
        Iterator ro = coachs.iterator();
        while (ro.hasNext()) {
            final Element competitor = (Element) ro.next();
            final String name=competitor.getAttributeValue(StringConstants.CS_NAME);
            Competitor c=Coach.getCoach(name);
            if (c==null)
            {
                c=Team.sTeamMap.get(name);
            }
            this.mCompetitors.add(c);
        }
        
        /*mTeams.clear();
        final List teams = pool.getChildren(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("TEAM"));
        ro = teams.iterator();
        while (ro.hasNext()) {
            final Element team = (Element) ro.next();
            final String name=team.getAttributeValue(StringConstants.CS_NAME);
            this.mTeams.add(Team.sTeamMap.get(name));
        }*/
    }
}
