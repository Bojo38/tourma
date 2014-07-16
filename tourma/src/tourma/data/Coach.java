/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import java.awt.Color;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.xml.bind.DatatypeConverter;
import org.jdom2.Content;
import org.jdom2.DataConversionException;
import org.jdom2.Element;
import teamma.data.Player;
import teamma.data.StarPlayer;
import teamma.data.lrb;
import tourma.MainFrame;
import tourma.utility.StringConstants;
import tourma.utils.Balancing;

/**
 * This class contains data relative to coach
 *
 * @author Frederic Berger
 */
public final class Coach extends Competitor implements XMLExport {

    protected static Coach sNullCoach = null;
    public static HashMap<String, Coach> sCoachMap = new HashMap<>();
    /**
     * Clan
     */
    public Clan mClan;
    public Category mCategory;
    public String mTeam;
    public RosterType mRoster;
    public int mNaf;
    public int mRank;
    public boolean mActive = true;
    public Team mTeamMates = null;
    public ArrayList<teamma.data.Roster> mCompositions;
    public double mNafRank = 150.0;
    public int mHandicap = 0;

    public Coach() {
        super();
        mActive = true;
        mCompositions = new ArrayList<>();
    }

    public Coach(final String name) {
        super(name);
        mActive = false;
        mTeam = StringConstants.CS_NONE;
        mCompositions = new ArrayList<>();
        mRoster = new RosterType(StringConstants.CS_NONE);
        mTeamMates = null;
    }

    public static Coach getNullCoach() {
        if (sNullCoach == null) {
            sNullCoach = new Coach(StringConstants.CS_NONE);
        }
        if ((Team.getNullTeam() != null) && ((sNullCoach.mTeam == null))) {
            sNullCoach.mTeamMates = Team.getNullTeam();
        }
        return sNullCoach;
    }

    @Override
    public int compareTo(final Object obj) {
        int result = -1;

        if (obj instanceof Coach) {
            result = ((Double) mNafRank).compareTo(((Coach) obj).mNafRank);
            if (result == 0) {
                result = mName.compareTo(((Coach) obj).mName);
            }
        }
        return result;
    }

    @Override
    public Element getXMLElement() {

        final Element coach = new Element(StringConstants.CS_COACH);
        coach.setAttribute(StringConstants.CS_NAME, this.mName);
        coach.setAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("TEAM"), this.mTeam);
        coach.setAttribute(StringConstants.CS_ROSTER, this.mRoster.mName);
        coach.setAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("NAF"), Integer.toString(this.mNaf));
        coach.setAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("RANK"), Integer.toString(this.mRank));
        coach.setAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("CLAN"), this.mClan.mName);
        if (this.mCategory != null) {
            coach.setAttribute(StringConstants.CS_CATEGORY, this.mCategory.mName);
        } else {
            coach.setAttribute(StringConstants.CS_CATEGORY, StringConstants.CS_NONE);
        }
        coach.setAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("ACTIVE"), Boolean.toString(this.mActive));

        coach.setAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("HANDICAP"), Integer.toString(this.mHandicap));

        for (int i = 0; i < mCompositions.size(); i++) {
            final Element compo = this.mCompositions.get(i).getXMLElement();
            coach.addContent(compo);
        }

        Element image = new Element("Picture");

        if (picture != null) {
            try {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(picture, "png", baos);
                baos.flush();
                String encodedImage = DatatypeConverter.printBase64Binary(baos.toByteArray());
                baos.close(); // should be inside a finally block
                image.addContent(encodedImage);
                coach.addContent(image);
            } catch (IOException e) {
            }
        }

        return coach;
    }

    public String getStringRoster() {
        if (this.mMatchs.size() == 0) {
            return mRoster.mName;
        }
        ArrayList<RosterType> rosters = new ArrayList<>();
        for (int i = 0; i < mMatchs.size(); i++) {
            Match m = mMatchs.get(i);
            if (this == m.mCompetitor1) {
                if (((CoachMatch) m).mRoster1 != null) {
                    if (!rosters.contains(((CoachMatch) m).mRoster1)) {
                        rosters.add(((CoachMatch) m).mRoster1);
                    }
                } else {
                    if (!rosters.contains(this.mRoster)) {
                        rosters.add(this.mRoster);
                    }
                }
            }

            if (this == m.mCompetitor2) {
                if (((CoachMatch) m).mRoster2 != null) {
                    if (!rosters.contains(((CoachMatch) m).mRoster2)) {
                        rosters.add(((CoachMatch) m).mRoster2);
                    }
                } else {
                    if (!rosters.contains(this.mRoster)) {
                        rosters.add(this.mRoster);
                    }
                }
            }
        }

        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < rosters.size(); i++) {
            if (i > 0) {
                buf.append(" / ");
            }
            buf.append(rosters.get(i).mName);
        }

        return buf.toString();
    }

    @Override
    public void setXMLElement(final Element coach) {
        try {
            this.mName = coach.getAttributeValue(StringConstants.CS_NAME);
            this.mTeam = coach.getAttributeValue(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("TEAM"));
            String rName = coach.getAttributeValue(StringConstants.CS_ROSTER);
            String rosterName = RosterType.getRosterName(rName);
            this.mRoster = RosterType.mRosterTypes.get(rosterName);

            this.mNaf = coach.getAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("NAF")).getIntValue();
            this.mRank = coach.getAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("RANK")).getIntValue();
            this.mClan = Clan.sClanMap.get(coach.getAttributeValue(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("CLAN")));
            if (coach.getAttributeValue(StringConstants.CS_CATEGORY) != null) {
                this.mCategory = Category.sCategoryMap.get(coach.getAttributeValue(StringConstants.CS_CATEGORY));
            } else {
                this.mCategory = Category.sCategoryMap.get(coach.getAttributeValue(StringConstants.CS_NONE));
            }
            Coach.sCoachMap.put(mName, this);

            try {
                this.mActive = coach.getAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("ACTIVE")).getBooleanValue();
                this.mHandicap = coach.getAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("HANDICAP")).getIntValue();
            } catch (NullPointerException npe) {
                // Do nothing
            }

            if (this.mClan == null) {
                if (Tournament.getTournament().getClans().isEmpty()) {
                    final java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE); // NOI18N
                    Tournament.getTournament().getClans().add(new Clan(bundle.getString("NoneKey")));

                }
                this.mClan = Tournament.getTournament().getClans().get(0);
            }

            final List compos = coach.getChildren(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("COMPOSITION"));
            final Iterator itC = compos.iterator();
            while (itC.hasNext()) {
                Element compo = (Element) itC.next();
                teamma.data.Roster c = new teamma.data.Roster();
                c.setXMLElement(compo);
                this.mCompositions.add(c);
            }

        } catch (DataConversionException dce) {
            JOptionPane.showMessageDialog(MainFrame.getMainFrame(), dce.getLocalizedMessage());
        }


            try {
                Element image = coach.getChild("Picture");
                String encodedImage = image.getText();
                byte[] bytes = DatatypeConverter.parseBase64Binary(encodedImage);
                picture = ImageIO.read(new ByteArrayInputStream(bytes));
            } catch (IOException e) {
            } catch (Exception e1) {
            }
        
    }

    @Override
    public void AddMatch(Competitor opponent, Round r) {
        CoachMatch m = new CoachMatch(r);
        m.mCompetitor1 = this;
        m.mCompetitor2 = opponent;
        r.mMatchs.add(m);
    }

    public CoachMatch CreateMatch(Competitor opponent, Round r) {
        CoachMatch m = new CoachMatch(r);
        m.mCompetitor1 = this;
        m.mCompetitor2 = opponent;
        return m;
    }

    public boolean havePlayed(Competitor opponent) {
        boolean have_played = false;
        for (int i = 0; i < mMatchs.size(); i++) {
            if ((mMatchs.get(i).mCompetitor1 == opponent) || (mMatchs.get(i).mCompetitor2 == opponent)) {
                have_played = true;
                break;
            }
        }
        return have_played;
    }
    /*
     public ArrayList<Competitor> getPossibleOpponents(ArrayList<Competitor> opponents, Round r) {
     Tournament tour = Tournament.getTournament();
     ArrayList<Clan> clans = tour.getClans();
     Parameters params = tour.getParams();
     ArrayList<Competitor> possible = new ArrayList<Competitor>(opponents);

     if (this.mClan != clans.get(0)) {
     if ((params.mEnableClans) && ((params.mAvoidClansFirstMatch) || (params.mAvoidClansMatch))) {
     for (int i = 0; i
     < possible.size(); i++) {
     if (((Coach) possible.get(i)).mClan.mName.equals(this.mClan.mName)) {
     possible.remove(i);
     i--;
     }
     }
     }
     }

     if ((params.mTeamTournament) && (params.mTeamPairing == 0)) {
     for (int i = 0; i < possible.size(); i++) {
     if (((Coach) possible.get(i)).mTeamMates.getActivePlayers().contains(this)) {
     possible.remove(i);
     i--;
     }
     }

     for (int i = 0; i < possible.size(); i++) {
     if (possible.get(i).havePlayed(this)) {
     possible.remove(i);
     i--;
     }
     }

     ArrayList<Competitor> balanced = new ArrayList<>(possible);
     ArrayList<Team> oppteam = new ArrayList<>();
     HashMap<Team, Integer> map = new HashMap<>();
     // Build opponents map
     for (int i = 0; i < Tournament.getTournament().getTeams().size(); i++) {
     Team t = Tournament.getTournament().getTeams().get(i);
     if (t != this.mTeamMates) {
     map.put(t, 0);
     oppteam.add(t);
     }
     }


     if (params.mIndivPairingTeamBalanced) {
     // compute the number of matches between the coach team
     // and the other teams
     // Compute the number of match per opponent
     Team OwnTeam = this.mTeamMates;
     for (int i = 0; i < OwnTeam.mCoachs.size(); i++) {
     Coach c = OwnTeam.mCoachs.get(i);
     for (int j = 0; j < c.mMatchs.size(); j++) {
     CoachMatch m = (CoachMatch) c.mMatchs.get(j);
     Coach opp;
     if (c == m.mCompetitor1) {
     opp = (Coach) m.mCompetitor2;
     } else {
     opp = (Coach) m.mCompetitor1;
     }
     Team Other = opp.mTeamMates;

     int nb = map.get(Other);
     nb = nb + 1;
     map.put(Other, nb);
     }
     }

     // Take account current round
     for (int i = 0; i < r.mMatchs.size(); i++) {
     Match m = r.mMatchs.get(i);
     if (m instanceof CoachMatch) {
     Team Other = null;
     if (this.mTeamMates == ((Coach) m.mCompetitor1).mTeamMates) {
     Other = ((Coach) m.mCompetitor2).mTeamMates;
     }
     if (this.mTeamMates == ((Coach) m.mCompetitor2).mTeamMates) {
     Other = ((Coach) m.mCompetitor1).mTeamMates;
     }
     if (Other != null) {
     int nb = map.get(Other);
     nb = nb + 1;
     map.put(Other, nb);
     }
     }
     }

     // Compute the minimum
     Iterator it = map.keySet().iterator();
     int minimum = 65535;
     while (it.hasNext()) {
     Team en = (Team) it.next();
     int nb = map.get(en);
     if (nb < minimum) {
     minimum = nb;
     }
     }

     // Remove the teams which are not corresondig to minimum
     it = map.keySet().iterator();
     while (it.hasNext()) {
     Team en = (Team) it.next();
     int nb = map.get(en);
     if (nb > minimum) {
     System.out.println("Remove " + en.getDecoratedName() + " from possible opponents for " + getDecoratedName() + "remaining " + (oppteam.size() - 1));
     oppteam.remove(en);
     }
     }
     }

     ArrayList<Competitor> indivbalanced = new ArrayList<>(balanced);
     if (params.mIndivPairingIndivBalanced) {
     // compute the number of matches between the coach and the other teams
     map = new HashMap<>();
     // Build opponents map
     for (int i = 0; i < Tournament.getTournament().getTeams().size(); i++) {
     Team t = Tournament.getTournament().getTeams().get(i);
     if (t != this.mTeamMates) {
     map.put(t, 0);
     }
     }

     // Compute the number of match per opponent
     for (int j = 0; j < mMatchs.size(); j++) {
     CoachMatch m = (CoachMatch) mMatchs.get(j);
     Coach opp;
     if (this == m.mCompetitor1) {
     opp = (Coach) m.mCompetitor2;
     } else {
     opp = (Coach) m.mCompetitor1;
     }
     Team Other = opp.mTeamMates;

     int nb = map.get(Other);
     nb = nb + 1;
     map.put(Other, nb);
     }


     // Compute the minimum
     Iterator it = map.keySet().iterator();
     int minimum = 65535;
     while (it.hasNext()) {
     Team en = (Team) it.next();
     int nb = map.get(en);
     if (nb < minimum) {
     minimum = nb;
     }
     }

     // Remove the teams which are not corresondig to minimum
     it = map.keySet().iterator();
     while (it.hasNext()) {
     Team en = (Team) it.next();
     int nb = map.get(en);
     if (nb > minimum) {
     System.out.println("Remove " + en.getDecoratedName() + " from possible opponents for " + getDecoratedName() + "remaining " + (oppteam.size() - 1));
     oppteam.remove(en);
     }
     }
     }

     // Keep only the players in possible list who are in team
     // which are still in the map
     ArrayList<Competitor> buffer = new ArrayList<>(balanced);
     for (int i = 0; i < buffer.size(); i++) {
     Coach c = (Coach) buffer.get(i);
     if (!oppteam.contains(c.mTeamMates)) {
     balanced.remove(c);
     }
     }

     if (balanced.isEmpty()) {
     balanced = indivbalanced;
     }

     if (!balanced.isEmpty()) {
     possible = balanced;
     }
     }

     if (possible.isEmpty()) {
     if (params.mEnableClans) {
     JOptionPane.showMessageDialog(MainFrame.getMainFrame(), java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("OnlyOneClanCoachKey"));
     }
     possible = new ArrayList<>(opponents);
     }
     return possible;
     }*/

    /*
     * @Override*/
    public ArrayList<Competitor> getPossibleOpponents(ArrayList<Competitor> opponents, Round r) {

        Tournament tour = Tournament.getTournament();
        ArrayList<Clan> clans = tour.getClans();
        Parameters params = tour.getParams();
        ArrayList<Competitor> possible = new ArrayList<>(opponents);

        if (this.mClan != clans.get(0)) {
            if ((params.mEnableClans) && ((params.mAvoidClansFirstMatch) || (params.mAvoidClansMatch))) {
                for (int i = 0; i
                        < possible.size(); i++) {
                    if (((Coach) possible.get(i)).mClan.mName.equals(this.mClan.mName)) {
                        possible.remove(i);
                        i--;
                    }
                }
            }
        }

        if ((params.mTeamTournament) && (params.mTeamPairing == 0)) {

            for (int i = 0; i < possible.size(); i++) {
                if (((Coach) possible.get(i)).mTeamMates.mCoachs.contains(this)) {
                    possible.remove(i);
                    i--;
                }
            }

            for (int i = 0; i < possible.size(); i++) {
                if (possible.get(i).havePlayed(this)) {
                    possible.remove(i);
                    i--;
                }
            }


            ArrayList<Team> oppteam = getPossibleTeams(r, null);

            ArrayList<Competitor> balanced = new ArrayList<>(possible);

            // Keep only the players in possible list who are in team
            // which are still in the map
            ArrayList<Competitor> buffer = new ArrayList<>(balanced);
            for (int i = 0; i < buffer.size(); i++) {
                Coach c = (Coach) buffer.get(i);
                if (!oppteam.contains(c.mTeamMates)) {
                    balanced.remove(c);
                }
            }


            if (params.mIndivPairingIndivBalanced) {
                ArrayList<Competitor> indivbalanced = new ArrayList<>(balanced);

                // Keep only the players in possible list who are in team
                // which are still in the map
                buffer = new ArrayList<>(indivbalanced);
                for (int i = 0; i < buffer.size(); i++) {
                    Coach c = (Coach) buffer.get(i);

                    ArrayList<Team> oppteam2 = c.getPossibleTeams(r, null);
                    if (!oppteam2.contains(this.mTeamMates)) {
                        //System.out.println("Remove " + c.getDecoratedName() + " from possible opponents for " + getDecoratedName() + " remaining " + (oppteam.size() - 1));
                        indivbalanced.remove(c);
                    }

                }

                if (!indivbalanced.isEmpty()) {
                    balanced = indivbalanced;
                } else {
                    System.out.println("Individual balanced empty, using only team balanced");
                }
            }


            if (!balanced.isEmpty()) {
                possible = balanced;
            } else {
                System.out.println("Balanced empty, using only possible");
            }
        }

        if (possible.isEmpty()) {
            if (params.mEnableClans) {
                JOptionPane.showMessageDialog(MainFrame.getMainFrame(), java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("OnlyOneClanCoachKey"));
            }
            possible = new ArrayList<>(opponents);
        }
        return possible;
    }

    protected ArrayList<Team> getPossibleTeams(Round current, Coach currentOpponent) {
        ArrayList<Team> oppteam = new ArrayList<>(Tournament.getTournament().getTeams());
        oppteam.remove(this.mTeamMates);

        HashMap<Team, Integer> map;

        if (Tournament.getTournament().getParams().mIndivPairingTeamBalanced) {

            map = this.mTeamMates.getTeamOppositionCount(oppteam, current);

            // Compute the minimum
            int minimum = getMinimumFromHash(new HashMap<Competitor, Integer>(map));

            // Remove the teams which are not corresondig to minimum
            Iterator it = map.keySet().iterator();
            while (it.hasNext()) {
                Team en = (Team) it.next();
                int nb = map.get(en);
                if (currentOpponent != null) {
                    if (en.equals(currentOpponent.mTeamMates)) {
                        nb--;
                    }
                }

                if (nb > minimum) {
                    //System.out.println("Remove " + en.getDecoratedName() + " from possible opponents for " + getDecoratedName() + "remaining " + oppteam.size() );
                    oppteam.remove(en);
                }
                /*else {
                 ArrayList<Team> oppteam2 = new ArrayList<>(Tournament.getTournament().getTeams());
                 oppteam2.remove(en);
                 HashMap<Team, Integer> map2 = en.getTeamOppositionCount(oppteam2, r);

                 int minimum2 = getMinimumFromHash(new HashMap<Competitor, Integer>(map2));

                 int nb2 = map2.get(this.mTeamMates);
                 if (nb2 > minimum2) {
                 oppteam.remove(en);
                 }
                 }*/
            }
        }

        if (Tournament.getTournament().getParams().mIndivPairingIndivBalanced) {
            // compute the number of matches between the coach and the other teams
            map = getTeamOppositionCount(oppteam, current);
            // Build opponents map

            // Compute the minimum
            int minimum = getMinimumFromHash(new HashMap<Competitor, Integer>(map));

            // Remove the teams which are not corresondig to minimum
            Iterator it = map.keySet().iterator();
            while (it.hasNext()) {
                Team en = (Team) it.next();
                int nb = map.get(en);
                if (currentOpponent != null) {
                    if (en.equals(currentOpponent.mTeamMates)) {
                        nb--;
                    }
                }
                if (nb > minimum) {
                    //System.out.println("Remove " + en.getDecoratedName() + "(" + nb + ">" + minimum + " matchs) from possible opponents for " + getDecoratedName() + " remaining " + (oppteam.size() - 1));
                    oppteam.remove(en);
                }
            }
        }

        return oppteam;
    }

    protected int getMinimumFromHash(HashMap<Competitor, Integer> hash) {
        Iterator it2 = hash.keySet().iterator();
        int minimum2 = 65535;
        while (it2.hasNext()) {
            Competitor en2 = (Competitor) it2.next();
            int nb2 = hash.get(en2);
            if (nb2 < minimum2) {
                minimum2 = nb2;
            }
        }
        return minimum2;
    }

    /* protected HashMap<Team, Integer> getTeamOppositionCount(Team current, ArrayList<Team> oppteam, Round r) {

     HashMap<Team, Integer> map = new HashMap<>();

     // Build opponents map
     for (int i = 0; i < oppteam.size(); i++) {
     Team t = oppteam.get(i);
     if (!t.mName.equals(current.mName)) {
     map.put(t, 0);
     }
     }

     // compute the number of matches between the coach team and the other teams
     // Compute the number of match per opponent
     for (int i = 0; i < current.mCoachs.size(); i++) {
     Coach coach = current.mCoachs.get(i);
     for (int j = 0; j < coach.mMatchs.size(); j++) {
     CoachMatch m = (CoachMatch) coach.mMatchs.get(j);
     Coach opp;
     if (coach.mName.equals(m.mCompetitor1.mName)) {
     opp = (Coach) m.mCompetitor2;
     } else {
     opp = (Coach) m.mCompetitor1;
     }
     Team Other = opp.mTeamMates;
     if (oppteam.contains(Other)) {
     try {
     int nb = map.get(Other);
     nb = nb + 1;
     map.put(Other, nb);
     } catch (NullPointerException npe) {
     System.out.println("Impossible to manage " + current.mName + " vs " + Other.mName);
     }
     }
     }
     }

     // Take account current round
     for (int i = 0; i < r.mMatchs.size(); i++) {
     Match m = r.mMatchs.get(i);
     if (m instanceof CoachMatch) {
     Team Other = null;
     if (current.mName.equals(((Coach) m.mCompetitor1).mTeamMates.mName)) {
     Other = ((Coach) m.mCompetitor2).mTeamMates;
     }
     if (current.mName.equals(((Coach) m.mCompetitor2).mTeamMates.mName)) {
     Other = ((Coach) m.mCompetitor1).mTeamMates;
     }
     if (Other != null) {
     try {
     int nb = map.get(Other);
     nb = nb + 1;
     map.put(Other, nb);
     } catch (NullPointerException npe) {
     System.out.println("Impossible to manage " + current.mName + " vs " + Other.mName);
     }
     }
     }
     }
     return map;
     }

     protected HashMap<Team, Integer> getTeamOppositionCount(Coach current, ArrayList<Team> oppteam, Round r) {

     HashMap<Team, Integer> map = new HashMap<>();

     // Build opponents map
     for (int i = 0; i < oppteam.size(); i++) {
     Team t = oppteam.get(i);
     if (!t.mName.equals(current.mTeamMates.mName)) {
     map.put(t, 0);
     }
     }

     // Compute the number of match per opponent
     for (int j = 0; j < mMatchs.size(); j++) {
     CoachMatch m = (CoachMatch) mMatchs.get(j);
     Coach opp;
     if (this == m.mCompetitor1) {
     opp = (Coach) m.mCompetitor2;
     } else {
     opp = (Coach) m.mCompetitor1;
     }
     Team Other = opp.mTeamMates;

     if (oppteam.contains(Other)) {
     int nb = map.get(Other);
     nb = nb + 1;
     map.put(Other, nb);
     }
     }

     return map;
     }
     */
    public HashMap<Team, Integer> getTeamOppositionCount(ArrayList<Team> teams, Round r) {

        HashMap<Team, Integer> map = new HashMap<>();

        // Build opponents map
        for (int i = 0; i < teams.size(); i++) {
            Team t = teams.get(i);
            if (!t.mName.equals(mTeamMates.mName)) {
                map.put(t, 0);
            }
        }

        // Compute the number of match per opponent
        for (int j = 0; j < mMatchs.size(); j++) {
            CoachMatch m = (CoachMatch) mMatchs.get(j);
            Coach opp;
            if (this == m.mCompetitor1) {
                opp = (Coach) m.mCompetitor2;
            } else {
                opp = (Coach) m.mCompetitor1;
            }
            Team Other = opp.mTeamMates;

            int nb = 0;
            if (map.get(Other) != null) {
                nb = map.get(Other);
            }
            nb = nb + 1;
            map.put(Other, nb);
        }

        if (r != null) {
            for (int i = 0; i < r.getCoachMatchs().size(); i++) {
                CoachMatch cm = r.getCoachMatchs().get(i);
                Coach c1 = (Coach) cm.mCompetitor1;
                Coach c2 = (Coach) cm.mCompetitor2;

                Coach opp = null;
                if (this == cm.mCompetitor1) {
                    opp = (Coach) cm.mCompetitor2;
                }
                if (this == cm.mCompetitor2) {
                    opp = (Coach) cm.mCompetitor1;
                }
                if (opp != null) {
                    Team Other = opp.mTeamMates;

                    int nb = 0;
                    if (map.get(Other) != null) {
                        nb = map.get(Other);
                    }
                    nb = nb + 1;
                    map.put(Other, nb);
                }
            }
        }

        return map;
    }

    public String getDecoratedName() {
        String tmp = mName;
        Tournament tour = Tournament.getTournament();
        ArrayList<Clan> clans = tour.getClans();
        Parameters params = tour.getParams();
        if (params.mEnableClans) {
            tmp = mName + " / " + mClan.mName;
        }
        if (params.mTeamTournament) {
            tmp = mName + " / " + mTeamMates.mName;
        }
        return tmp;
    }

    @Override
    public void AddMatchRoundRobin(Competitor c, Round r) {
        AddMatch(c, r);
    }

    protected boolean isBalanced(Coach opp, Round round) {

        boolean balanced = true;
        ArrayList<Team> teams = new ArrayList<>();

        if (this.mTeamMates == opp.mTeamMates) {
            return false;
        }

        for (int i = 0; i < round.mMatchs.size(); i++) {
            CoachMatch m = (CoachMatch) round.mMatchs.get(i);
            Coach c1 = (Coach) m.mCompetitor1;
            Coach c2 = (Coach) m.mCompetitor2;
            if (c1.mTeamMates != this.mTeamMates) {
                if (!teams.contains(c1.mTeamMates)) {
                    teams.add(c1.mTeamMates);
                }
            }
            if (c2.mTeamMates != this.mTeamMates) {
                if (!teams.contains(c2.mTeamMates)) {
                    teams.add(c2.mTeamMates);
                }
            }
        }

        HashMap<Team, Integer> hash = this.getTeamOppositionCount(teams, round);

        Iterator it2 = hash.keySet().iterator();
        int minimum = 65535;
        int maximum = 0;
        while (it2.hasNext()) {
            Competitor en2 = (Competitor) it2.next();
            int nb2 = hash.get(en2);
            if (nb2 < minimum) {
                minimum = nb2;
            }
            if (nb2 > maximum) {
                maximum = nb2;
            }
        }

        int nb = hash.get(opp.mTeamMates);

        if ((maximum == nb) && (maximum - minimum > 1)) {
            balanced = false;
        }
        return balanced;
    }

    protected ArrayList<Team> getMinimumTeamsBalanced(Round round) {

        ArrayList<Team> possible = new ArrayList();

        ArrayList<Team> teams = new ArrayList<>();
        for (int i = 0; i < round.mMatchs.size(); i++) {
            CoachMatch m = (CoachMatch) round.mMatchs.get(i);
            Coach c1 = (Coach) m.mCompetitor1;
            Coach c2 = (Coach) m.mCompetitor2;
            if (c1.mTeamMates != this.mTeamMates) {
                if (!teams.contains(c2.mTeamMates)) {
                    teams.add(c2.mTeamMates);
                }
            }
            if (c2.mTeamMates != this.mTeamMates) {
                if (!teams.contains(c1.mTeamMates)) {
                    teams.add(c1.mTeamMates);
                }
            }
        }

        HashMap<Team, Integer> hash = this.getTeamOppositionCount(teams, round);

        Iterator it2 = hash.keySet().iterator();
        int minimum = 65535;
        int maximum = 0;
        while (it2.hasNext()) {
            Competitor en2 = (Competitor) it2.next();
            int nb2 = hash.get(en2);
            if (nb2 < minimum) {
                minimum = nb2;
            }
            if (nb2 > maximum) {
                maximum = nb2;
            }
        }

        it2 = hash.keySet().iterator();
        while (it2.hasNext()) {
            Team en2 = (Team) it2.next();
            int nb2 = hash.get(en2);
            if (nb2 == minimum) {
                possible.add(en2);
            }
        }

        return possible;
    }

    protected boolean canMatch(Coach Opponent, Coach opponentOpponent, Coach currentOpp, Round current) {
        boolean canMatch = true;


        // Already played
        boolean have_played = havePlayed(Opponent);

        Tournament tour = Tournament.getTournament();
        canMatch = !have_played;

        // Same clan 
        if ((tour.getParams().mEnableClans) && (tour.getParams().mAvoidClansMatch)) {
            if (!Opponent.mClan.mName.equals(StringConstants.CS_NONE)) {
                if (mClan == Opponent.mClan) {
                    canMatch = false;
                }
            }
            if ((!opponentOpponent.mClan.mName.equals(StringConstants.CS_NONE)) && (!currentOpp.mClan.mName.equals(StringConstants.CS_NONE))) {
                if (opponentOpponent.mClan == currentOpp.mClan) {
                    canMatch = false;
                }
            }
        }

        // Same team
        if ((tour.getParams().mTeamTournament) && (tour.getParams().mTeamPairing == 0)) {
            if (Opponent.mTeamMates != Team.getNullTeam()) {
                if (mTeamMates == Opponent.mTeamMates) {
                    canMatch = false;
                }
            }
            if ((!opponentOpponent.mTeamMates.mName.equals(StringConstants.CS_NONE)) && (!currentOpp.mTeamMates.mName.equals(StringConstants.CS_NONE))) {
                if (opponentOpponent.mTeamMates == currentOpp.mTeamMates) {
                    canMatch = false;
                }
            }
        }

        // Balancing pairing
        if ((tour.getParams().mTeamTournament) && (tour.getParams().mTeamPairing == 0)) {

            ArrayList<Team> teams = new ArrayList<>();
            for (int i = 0; i < current.mMatchs.size(); i++) {
                CoachMatch m = (CoachMatch) current.mMatchs.get(i);
                Coach c1 = (Coach) m.mCompetitor1;
                Coach c2 = (Coach) m.mCompetitor2;
                if (c1.mTeamMates != this.mTeamMates) {
                    if (!teams.contains(c2.mTeamMates)) {
                        teams.add(c2.mTeamMates);
                    }
                }
                if (c2.mTeamMates != this.mTeamMates) {
                    if (!teams.contains(c1.mTeamMates)) {
                        teams.add(c1.mTeamMates);
                    }
                }
            }
            teams.remove(mTeamMates);

            // Team balancing
            if (tour.getParams().mIndivPairingTeamBalanced) {
                HashMap<Team, Integer> hash = mTeamMates.getTeamOppositionCount(teams, current);

                Iterator it2 = hash.keySet().iterator();
                int minimum = 65535;
                int maximum = 0;
                while (it2.hasNext()) {
                    Competitor en2 = (Competitor) it2.next();
                    int nb2 = hash.get(en2);
                    if (nb2 < minimum) {
                        minimum = nb2;
                    }
                    if (nb2 > maximum) {
                        maximum = nb2;
                    }
                }

                // Check if team is perfectly balanced. If yes, the opponent can only 
                // be among teammates opponents
                if (maximum - minimum == 0) {
                    if (!mTeamMates.mCoachs.contains(opponentOpponent)) {
                        canMatch = false;
                    }
                }


                // Check if team is balanced, by One. If yes, the opponent can  
                // be among teammates opponents or among minimum times encountered teams
                if (maximum - minimum == 1) {
                    if (!mTeamMates.mCoachs.contains(opponentOpponent)) {
                        // search for minimum teams
                        if (hash.get(Opponent.mTeamMates) > minimum) {
                            canMatch = false;
                        }
                    }
                }

                if (maximum - minimum > 1) {
                    canMatch = false;
                }
            }

            /*if (canMatch) {
             if (tour.getParams().mIndivPairingIndivBalanced) {

             // Search teams against which the player has played less match
             ArrayList<Team> possible = getPossibleTeams(current, currentOpp);
             if (!possible.contains(Opponent.mTeamMates)) {
             canMatch = false;
             }

             if (canMatch) {
             possible = Opponent.getPossibleTeams(current, opponentOpponent);
             if (!possible.contains(mTeamMates)) {
             canMatch = false;
             }
             }

             if (canMatch) {

             if (tour.getParams().mIndivPairingTeamBalanced) {
             HashMap<Team, Integer> hash = mTeamMates.getTeamOppositionCount(teams, current);

             Iterator it2 = hash.keySet().iterator();
             int minimum = 65535;
             int maximum = 0;
             while (it2.hasNext()) {
             Competitor en2 = (Competitor) it2.next();
             int nb2 = hash.get(en2);
             if (nb2 < minimum) {
             minimum = nb2;
             }
             if (nb2 > maximum) {
             maximum = nb2;
             }
             }

             if (hash.get(Opponent.mTeamMates) == minimum) {
             if (maximum - minimum == 0) {
             if (opponentOpponent.mTeamMates != mTeamMates) {
             canMatch = false;
             }
             }
             } else {
             canMatch = false;
             }
             }
             }
             }
             }*/
        }

        return canMatch;
    }

    /**
     * This method arrange matchs to avoid double meet and balanced matches
     *
     * @param r
     */
    @Override
    @SuppressWarnings("empty-statement")
    public void RoundCheck(Round round) {

        Tournament tour = Tournament.getTournament();
        ArrayList<Match> matchs = round.getMatchs();

        int balancingTries = 10000;
        int triesThreshold = 10;
        boolean totallyBalanced = false;
        while ((!totallyBalanced) && (balancingTries > 0)) {
            totallyBalanced = true;
            balancingTries--;


            if (balancingTries % triesThreshold == 0) {
                Collections.shuffle(matchs);
            }
            System.out.println("Remaining tries: " + balancingTries);

            boolean balanced = true;
            for (int i = matchs.size() - 1; i > 0; i--) {

                Match current = matchs.get(i);
                final Coach c1 = (Coach) current.mCompetitor1;
                final Coach c2 = (Coach) current.mCompetitor2;
                boolean have_played = c1.havePlayed(c2);

                balanced = true;
                boolean loop = false;
                if ((tour.getParams().mTeamTournament)
                        && (tour.getParams().mTeamPairing == 0)) {
                    if ((tour.getParams().mIndivPairingTeamBalanced) || (tour.getParams().mIndivPairingIndivBalanced)) {
                        balanced = c1.isBalanced(c2, round) && c2.isBalanced(c1, round);
                        if (!balanced) {
                            System.out.println(c1.mName + " is not balanced");
                            totallyBalanced = false;
                        }
                    }
                }

                if ((have_played) || (!balanced)) {
                    //ArrayList<Competitor> possible = new ArrayList<>();
                    for (int k = i - 1; k >= 0; k--) {

                        // Get previous match opponent
                        Coach c1_tmp = (Coach) matchs.get(k).mCompetitor1;
                        Coach c2_tmp = (Coach) matchs.get(k).mCompetitor2;

                        //System.out.println("Testing " + c1_tmp.mName + " vs " + c2_tmp.mName);

                        boolean canMatch = c1.canMatch(c2_tmp, c1_tmp, c2, round);
                        if (loop) {
                            canMatch = canMatch && c2.canMatch(c1_tmp, c2_tmp, c1, round);
                        }

                        if (canMatch) {
                            matchs.get(i).mCompetitor2 = c2_tmp;
                            matchs.get(k).mCompetitor2 = c2;

                            System.out.println(c1.mName + " vs " + c2.mName + " becomes " + c1.mName + " vs " + c2_tmp.mName);
                            System.out.println("And " + c1_tmp.mName + " vs " + c2_tmp.mName + " becomes " + c1_tmp.mName + " vs " + c2.mName);

                            break;
                        } else {
                            canMatch = c1.canMatch(c1_tmp, c2_tmp, c2, round);
                            if (loop) {
                                canMatch = canMatch && c2.canMatch(c2_tmp, c1_tmp, c1, round);
                            }

                            if (canMatch) {
                                matchs.get(i).mCompetitor2 = c1_tmp;
                                matchs.get(k).mCompetitor1 = c2;

                                System.out.println(c1.mName + " vs " + c2.mName + " becomes " + c1.mName + " vs " + c1_tmp.mName);
                                System.out.println("And " + c1_tmp.mName + " vs " + c2_tmp.mName + " becomes " + c2.mName + " vs " + c2_tmp.mName);
                                break;
                            }
                        }

                        if (k == 0) {
                            if ((tour.getParams().mTeamTournament)
                                    && (tour.getParams().mTeamPairing == 0)) {
                                if ((tour.getParams().mIndivPairingTeamBalanced) || (tour.getParams().mIndivPairingIndivBalanced)) {
                                    balanced = c1.isBalanced(c2, round) && c2.isBalanced(c1, round);
                                    if (!balanced) {
                                        System.out.println(c1.mName + " is still not balanced, loop");
                                        if (!loop) {
                                            k = matchs.size() - 1;
                                            loop = true;
                                        }
                                    }
                                }
                            }
                        }

                    }
                }
            }


            if ((tour.getParams().mTeamTournament)
                    && (tour.getParams().mTeamPairing == 0)) {
                Match cm = matchs.get(0);
                if ((tour.getParams().mIndivPairingTeamBalanced) || (tour.getParams().mIndivPairingIndivBalanced)) {
                    Coach c1 = (Coach) cm.mCompetitor1;
                    Coach c2 = (Coach) cm.mCompetitor2;
                    balanced = c1.isBalanced(c2, round) && c2.isBalanced(c1, round);
                    if (!balanced) {
                        System.out.println(c1.mName + " is not balanced");
                        totallyBalanced = false;
                    }
                }
            }

            if ((!totallyBalanced)
                    && (balancingTries == 0)) {
                int answer = JOptionPane.showConfirmDialog(MainFrame.getMainFrame(), "Le calcul de la ronde n'a pas abouti à un résultat satisfaisant\n"
                        + "Le calcul de ce type de ronde nécessite énromément de puissance\n"
                        + "de calcul. Voulez-vous continuer ?", "Ronde non satisfaisante", JOptionPane.YES_NO_OPTION);
                if (answer == JOptionPane.YES_OPTION) {
                    balancingTries = 10000;
                }
            }
        }
    }
}
