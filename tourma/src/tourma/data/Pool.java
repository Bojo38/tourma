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


    /**
     *
     */
        private final ArrayList<Competitor> mCompetitors=new ArrayList<>();

    /**
     *
     */
    private String mName = "";

    /**
     *
     * @return
     */
    @Override
    public Element getXMLElement() {
        final Element pool = new Element(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("POOL"));
        pool.setAttribute(StringConstants.CS_NAME, this.getName());
        for (Competitor mCompetitor : mCompetitors) {
            final Element coach = new Element(StringConstants.CS_COACH);
            coach.setAttribute(StringConstants.CS_NAME, mCompetitor.getName());
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
        setName(pool.getAttributeValue(StringConstants.CS_NAME));
        
        mCompetitors.clear();
        final List<Element> coachs = pool.getChildren(StringConstants.CS_COACH);
        Iterator<Element> ro = coachs.iterator();
        while (ro.hasNext()) {
            final Element competitor = ro.next();
            final String name=competitor.getAttributeValue(StringConstants.CS_NAME);
            Competitor c=Coach.getCoach(name);
            if (c==null)
            {
                c=Team.getTeam(name);
            }
            mCompetitors.add(c);
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
     * @return the mCompetitors
     */
    public Competitor getCompetitor(int i) {
        return mCompetitors.get(i);
    }
    
    /**
     * @param c
     */
    public void addCompetitor(Competitor c) {
         mCompetitors.add(c);
    }
    
    /**
     * @return the mCompetitors
     */
    public int getCompetitorCount() {
        return mCompetitors.size();
    }
    
    /**
     * @return the mCompetitors
     */
    @SuppressWarnings("ReturnOfCollectionOrArrayField")
    public ArrayList<Competitor> getCompetitors(){
        return mCompetitors;
    }

    /**
     * @param mCompetitors the mCompetitors to set
     */
    /*public void setCompetitors(ArrayList<Competitor> mCompetitors) {
        this.mCompetitors = mCompetitors;
    }*/
}
