/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.jdom2.Element;
import tourma.utility.StringConstants;

/**
 *
 * @author Frederic Berger
 */
public class Team implements Comparable, XMLExport {

    public static Team sNullTeam = new Team(StringConstants.CS_NONE);
    public ArrayList<Coach> mCoachs;
    public String mName;
    public static HashMap<String, Team> sTeamMap = new HashMap<String, Team>();

    public Team() {
        mCoachs = new ArrayList<Coach>();
    }

    public Team(final String name) {
        mName = name;
        mCoachs = new ArrayList<Coach>();
    }

    public int compareTo(final Object obj) {
        int result = -1;
        if (obj instanceof Team) {
            
            double rank=0;
            for (int i=0; i<this.mCoachs.size(); i++)
            {
                rank+=mCoachs.get(i).mNafRank;
            }
            rank=rank/mCoachs.size();
            
             double rankobj=0;
            for (int i=0; i<((Team)obj).mCoachs.size(); i++)
            {
                rankobj+=((Team)obj).mCoachs.get(i).mNafRank;
            }
            rankobj=rankobj/((Team)obj).mCoachs.size();
            
            result=((Double)rank).compareTo(rankobj); 
        }
        return result;
    }

    public int getActivePlayerNumber() {
        int nb = 0;

        for (int i = 0; i < mCoachs.size(); i++) {
            if (mCoachs.get(i).mActive) {
                nb++;
            }
        }
        return nb;
    }

    public ArrayList<Coach> getActivePlayers() {
        final ArrayList<Coach> v = new ArrayList<Coach>();
        if (this == sNullTeam) {
            for (int i = 0; i < Tournament.getTournament().getParams().mTeamMatesNumber; i++) {
                v.add(Coach.sNullCoach);
            }
        } else {
            for (int i = 0; i < mCoachs.size(); i++) {
                if (mCoachs.get(i).mActive) {
                    v.add(mCoachs.get(i));
                }
            }
        }
        return v;
    }

    public Element getXMLElement() {
        final Element team = new Element(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("TEAM"));
        team.setAttribute(StringConstants.CS_NAME, this.mName);
        for (int j = 0; j < this.mCoachs.size(); j++) {
            final Element coach = new Element(StringConstants.CS_COACH);
            coach.setAttribute(StringConstants.CS_NAME, this.mCoachs.get(j).mName);
            team.addContent(coach);
        }
        return team;
    }

    public void setXMLElement(final Element team) {
        this.mName = team.getAttributeValue(StringConstants.CS_NAME);
        final List coachs2 = team.getChildren(StringConstants.CS_COACH);
        final Iterator m = coachs2.iterator();
        this.mCoachs.clear();

        Team.sTeamMap.put(mName, this);
        while (m.hasNext()) {
            final Element coach = (Element) m.next();
            final Coach c = Coach.sCoachMap.get(coach.getAttribute(StringConstants.CS_NAME).getValue());
            c.mTeamMates = this;
            this.mCoachs.add(c);
        }
    }
}
