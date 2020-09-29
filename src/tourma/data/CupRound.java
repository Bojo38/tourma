/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
public class CupRound implements IXMLExport {

    public int getNbMatchs() {
        return mNbMatchs;
    }

    public void setNbMatchs(int mNbMatchs) {
        this.mNbMatchs = mNbMatchs;
    }

    public ArrayList<Match> getMatchs() {
        return mMatchs;
    }

    public void setMatchs(ArrayList<Match> mMatchs) {
        this.mMatchs = mMatchs;
    }

    protected int mNbMatchs;
    protected ArrayList<Match> mMatchs = null;

    public CupRound(int nbMatches) {
        mNbMatchs = nbMatches;
        generateEmptyMatchs();
    }

    public CupRound() {
        mNbMatchs = 0;
    }

    public void generateEmptyMatchs() {
        if (mMatchs != null) {
            mMatchs.clear();
        }
        mMatchs = new ArrayList<>();

        boolean byTeam = (Tournament.getTournament().getParams().isTeamTournament())
                && (Tournament.getTournament().getParams().getTeamPairing() == ETeamPairing.TEAM_PAIRING);

        for (int i = 0; i < mNbMatchs; i++) {
            if (byTeam) {
                TeamMatch tm = new TeamMatch(null);
                mMatchs.add(tm);
            } else {
                CoachMatch cm = new CoachMatch(null);
                mMatchs.add(cm);
            }
        }
    }

    @Override
    public Element getXMLElement() {
        final Element round = new Element(StringConstants.CS_CUP_ROUND);
       
        for (Match mMatch : this.mMatchs) {
            final Element match = new Element(StringConstants.CS_CUP_MATCH);
            String name1="";
            String name2="";
            if(mMatch.getCompetitor1()!=null)
            {
                name1=mMatch.getCompetitor1().getName();
            }
            if(mMatch.getCompetitor2()!=null)
            {
                name2=mMatch.getCompetitor2().getName();
            }
            match.setAttribute("c1",name1);
            match.setAttribute("c2",name2);
            round.addContent(match);
        }
        return round;
    }

    @Override
    public void setXMLElement(Element e) {
        List<Element> matchs = e.getChildren(StringConstants.CS_CUP_MATCH);
        final Iterator<Element> k = matchs.iterator();
        this.mMatchs = new ArrayList<>();

        boolean teamTour = Tournament.getTournament().getParams().isTeamTournament();
        ETeamPairing pairing = Tournament.getTournament().getParams().getTeamPairing();

        while (k.hasNext()) {
            Element m = k.next();
            Match match;
            try {

                String name1=m.getAttributeValue("c1");
                String name2=m.getAttributeValue("c2");
                
                if (teamTour
                        && (pairing == ETeamPairing.TEAM_PAIRING)) {
                    match = new TeamMatch(null);
                    if (!name1.equals(""))
                    {
                        Competitor c1=Tournament.getTournament().getTeam(name1);
                        if (c1!=null)
                        {
                            match.setCompetitor1(c1);
                        }
                    }
                    if (!name2.equals(""))
                    {
                        Competitor c2=Tournament.getTournament().getTeam(name2);
                        if (c2!=null)
                        {
                            match.setCompetitor2(c2);
                        }
                    }
                } else {
                    match = new CoachMatch(null);
                    if (!name1.equals(""))
                    {
                        Competitor c1=Tournament.getTournament().getCoach(name1);
                        if (c1!=null)
                        {
                            match.setCompetitor1(c1);
                        }
                    }
                    if (!name2.equals(""))
                    {
                        Competitor c2=Tournament.getTournament().getCoach(name2);
                        if (c2!=null)
                        {
                            match.setCompetitor2(c2);
                        }
                    }
                }
                
                this.mMatchs.add(match);
                
            } catch (NullPointerException ne) {
            }
            this.mNbMatchs=mMatchs.size();
        }
    }

}
