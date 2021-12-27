/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bb.tourma.data;

import bb.teamma.data.LRB;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import bb.tourma.MainFrame;
import bb.tourma.languages.Translate;
import bb.tourma.utility.StringConstants;
import bb.tourma.utils.NAF;
import java.time.LocalDateTime;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Frederic Berger
 */
public class Tournament implements IContainCoachs, Serializable {

    /**
     *
     */
    private static Tournament mSingleton;
    private static final Object myLock = new Object();
    private static final Logger LOG = Logger.getLogger(Tournament.class.getName());

    protected LocalDateTime createDateTime;

    protected LocalDateTime updateDateTime;

    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(LocalDateTime createDateTime) {
        this.createDateTime = createDateTime;
    }

    public LocalDateTime getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime(LocalDateTime updateDateTime) {
        this.updateDateTime = updateDateTime;
    }

    boolean mClosed = false;

    public void setClose(boolean b) {
        mClosed = b;
        updateDateTime = LocalDateTime.now();
    }

    public boolean isClosed() {
        return mClosed;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
        updateDateTime = LocalDateTime.now();
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
        updateDateTime = LocalDateTime.now();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        updateDateTime = LocalDateTime.now();
    }

    public long getTournament_id() {
        return tournament_id;
    }

    public void setTournament_id(long tournament_id) {
        this.tournament_id = tournament_id;
        updateDateTime = LocalDateTime.now();
    }

    private String URL = "192.168.1.100:443";
    private String login = "lord_bojo";
    private String password = "7539";
    private long tournament_id = 0;
    protected boolean _synchronized = false;

    public boolean isSynchronized() {
        return _synchronized;
    }

    public void setSynchronized(boolean isSynchronized) {
        this._synchronized = isSynchronized;
        updateDateTime = LocalDateTime.now();
    }

    private boolean isClient = false;

    public boolean isClient() {
        return isClient;
    }

    LRB lrb = LRB.getLRB(LRB.E_Version.BB2020);

    public LRB getLRB() {
        return lrb;
    }

    public void setLRB(LRB rules) {
        lrb = rules;
        updateDateTime = LocalDateTime.now();
    }

    public void setIsClient(boolean isClient) {
        this.isClient = isClient;
        updateDateTime = LocalDateTime.now();
    }

    /**
     *
     * @return
     */
    public static Tournament resetTournament() {
        mSingleton = new Tournament();
        return mSingleton;
    }

    public void recomputeAll() {
        for (int i = 0; i < this.mRounds.size(); i++) {
            mRounds.get(i).recomputeMatchs();
        }
    }

    public ArrayList<Team> getTeams() {
        return mTeams;
    }

    public ArrayList<Clan> getClans() {
        return mClans;
    }

    public ArrayList<Coach> getCoachs() {
        return mCoachs;
    }

    /**
     *
     * @return
     */
    public static Tournament getTournament() {
        synchronized (Tournament.myLock) {
            if (mSingleton == null) {
                mSingleton = new Tournament();
            }
        }
        return mSingleton;
    }

    private String mDescription;

    /**
     *
     */
    private final ArrayList<Round> mRounds;

    /**
     *
     */
    private final ArrayList<Coach> mCoachs;

    /**
     *
     */
    private final ArrayList<Team> mTeams;

    /**
     *
     */
    private final ArrayList<Pool> mPools;

    /**
     *
     */
    private final ArrayList<Category> mCategories;

    /**
     *
     */
    private Parameters mParams;

    /**
     *
     */
    private boolean mRoundRobin = false;
    /**
     * Clans used in the tournement
     */
    private final ArrayList<Clan> mClans;
    /**
     * mRostersNames Groups
     */
    private final ArrayList<Group> mGroups;

    private Cup mCup;

    public Cup getCup() {
        return mCup;
    }

    public void setCup(Cup cup) {
        mCup = cup;
        updateDateTime = LocalDateTime.now();
    }

    private Tournament() {

        RosterType.initCollection(LRB.getLRB(LRB.E_Version.BB2020));
        getRosterType();

        mParams = new Parameters();
        mRounds = new ArrayList<>();
        mCoachs = new ArrayList<>();
        mClans = new ArrayList<>();
        mTeams = new ArrayList<>();
        mGroups = new ArrayList<>();
        mPools = new ArrayList<>();
        mCategories = new ArrayList<>();

        mClans.add(new Clan(Translate.translate(Translate.CS_None)));
        mCategories.add(new Category(Translate.translate(Translate.CS_None)));
        final Group group = new Group(Translate.translate(Translate.CS_None));
        mGroups.add(group);

        for (int i = 0; i < RosterType.getRostersNamesCount(); i++) {
            group.addRoster(RosterType.getRosterType(RosterType.getRostersName(i)));
        }
    }

    public Parameters getParams() {
        return mParams;
    }

    public int getRoundIndex(Round round) {
        if (this.mRounds != null) {
            return mRounds.indexOf(round);
        } else {
            return -1;
        }
    }

    /**
     *
     * @return
     */
    public int getClansCount() {
        return mClans.size();
    }

    /**
     *
     * @param i
     * @return
     */
    public Clan getClan(int i) {
        return mClans.get(i);
    }

    /**
     *
     * @param c
     */
    public void addClan(Clan c) {
        mClans.add(c);
        clansUpdated = true;
        this.updateDateTime = LocalDateTime.now();
    }

    /**
     *
     * @param c
     */
    public void removeClan(Clan c) {
        mClans.remove(c);
        clansUpdated = true;
    }

    /**
     *
     * @param c
     * @throws java.rmi.RemoteException
     */
    public void removeClan(int c) {
        mClans.remove(c);
        clansUpdated = true;
    }

    /**
     * Clear the Clans array
     */
    public void clearClans() {
        mClans.clear();
        clansUpdated = true;
    }

    /**
     *
     * @return
     */
    public int getCategoriesCount() {
        return mCategories.size();
    }

    /**
     *
     * @param i
     * @return
     */
    public Category getCategory(int i) {
        return mCategories.get(i);
    }

    public Category getCategory(String s) {
        for (int i = 0; i < mCategories.size(); i++) {
            if (mCategories.get(i).getName().equals(s)) {
                return mCategories.get(i);
            }
        }
        return null;
    }

    /**
     *
     * @param c
     */
    public void addCategory(Category c) {
        mCategories.add(c);
        this.updateDateTime = LocalDateTime.now();
    }

    /**
     *
     * @param c
     */
    public void removeCategory(Category c) {

        for (Coach coach : mCoachs) {
            coach.delCategory(c);
        }
        for (Team team : mTeams) {
            team.delCategory(c);
        }

        mCategories.remove(c);
    }

    /**
     *
     * @param c
     */
    public void removeCategory(int c) {

        removeCategory(mCategories.get(c));

    }

    /**
     * Clear the Clans array
     */
    public void clearCategories() {
        mCategories.clear();
    }

    /**
     *
     * @return
     */
    public int getTeamsCount() {
        return mTeams.size();
    }

    /**
     *
     * @param i
     * @return
     */
    public Team getTeam(int i) {
        return mTeams.get(i);
    }

    /**
     *
     * @param c
     */
    public void addTeam(Team c) {
        mTeams.add(c);
        Team.putTeam(c.getName(), c);
        this.updateDateTime = LocalDateTime.now();
    }

    /**
     *
     * @param c
     */
    public void removeTeam(Team c) {
        mTeams.remove(c);
        teamsUpdated = true;
    }

    /**
     *
     * @param t
     * @return
     */
    public boolean containsTeam(Team t) {
        return mTeams.contains(t);
    }

    public boolean containsTeam(String name) {
        for (int i = 0; i < mTeams.size(); i++) {
            if (mTeams.get(i).getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public boolean containsCoach(String name) {
        for (int i = 0; i < mCoachs.size(); i++) {
            if (mCoachs.get(i).getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public int getTeamIndex(String name) {
        for (int i = 0; i < mTeams.size(); i++) {
            if (mTeams.get(i).getName().equals(name)) {
                return i;
            }
        }
        return -1;
    }

    public Team getTeam(String name) {
        for (int i = 0; i < mTeams.size(); i++) {
            if (mTeams.get(i).getName().equals(name)) {
                return mTeams.get(i);
            }
        }
        return null;
    }

    public Coach getCoach(String name) {
        for (int i = 0; i < mCoachs.size(); i++) {
            if (mCoachs.get(i).getName().equals(name)) {
                return mCoachs.get(i);
            }
        }
        return null;
    }

    /**
     *
     * @param c
     */
    public void removeTeam(int c) {
        mTeams.remove(c);
        teamsUpdated = true;
    }

    /**
     * Clear the Team array
     */
    public void clearTeams() {
        mTeams.clear();
    }

    /**
     *
     * @return
     */
    public ArrayList<Clan> getDisplayClans() {

        // Remove clans which do not have members
        final HashMap<Clan, Integer> counts = new HashMap<>();
        for (Clan mClan : mClans) {
            counts.put(mClan, 0);
        }
        final ArrayList<Clan> clans = new ArrayList<>();
        if (mParams.isTeamTournament()) {
            for (Team c : mTeams) {
                if (c.getClan() != null) {
                    counts.put(c.getClan(), counts.get(c.getClan()) + 1);
                }
            }
        } else {
            for (Coach c : mCoachs) {
                if (c.getClan() != null) {
                    counts.put(c.getClan(), counts.get(c.getClan()) + 1);
                }
            }
        }

        for (Clan mClan : mClans) {
            if (counts.get(mClan) > 0) {
                clans.add(mClan);
            }
        }

        return clans;
    }

    /**
     *
     * @return
     */
    public ArrayList<Category> getDisplayCategories() {

        final HashMap<Category, Integer> counts = new HashMap<>();
        for (Category mCategorie : mCategories) {
            counts.put(mCategorie, 0);
        }
        final ArrayList<Category> categories = new ArrayList<>();

        for (Coach c : mCoachs) {
            for (int i = 0; i < c.getCategoryCount(); i++) {
                Category cat = c.getCategory(i);
                counts.put(cat, counts.get(cat) + 1);
            }
        }

        for (Team c : mTeams) {
            for (int i = 0; i < c.getCategoryCount(); i++) {
                Category cat = c.getCategory(i);
                counts.put(cat, counts.get(cat) + 1);
            }
        }

        for (Category mCategorie : mCategories) {
            if (counts.get(mCategorie) > 0) {
                categories.add(mCategorie);
            }
        }

        return categories;
    }

    /**
     *
     * @return
     */
    @Override
    public boolean containsCoach(Coach c) {
        return mCoachs.contains(c);
    }

    public boolean containsClan(String c) {
        for (Clan cl : mClans) {
            if (cl.getName().equals(c)) {
                return true;
            }
        }
        return false;
    }

    public Clan getClan(String name) {
        for (Clan cl : mClans) {
            if (cl.getName().equals(name)) {
                return cl;
            }
        }
        return null;
    }

    /**
     *
     * @param i
     */
    public void removeCoach(Coach i) {
        mCoachs.remove(i);
        coachsUpdated = true;
    }

    /**
     *
     * @param i
     */
    @Override
    public void removeCoach(int i) {
        mCoachs.remove(i);
        coachsUpdated = true;

    }

    /**
     *
     */
    @Override
    public void clearCoachs() {
        mCoachs.clear();

    }

    /**
     *
     * @param c
     */
    @Override
    public void addCoach(Coach c) {
        mCoachs.add(c);
        Coach.putCoach(c.getName(), c);
        coachsUpdated = true;
        this.updateDateTime = LocalDateTime.now();
    }

    /**
     *
     * @return
     */
    public int getCoachsCount() {
        return mCoachs.size();

    }

    /**
     *
     * @param i
     * @return
     */
    @Override
    public Coach getCoach(int i) {
        return mCoachs.get(i);

    }

    /**
     *
     * @param g
     */
    public void addGroup(Group g) {
        mGroups.add(g);
        this.updateDateTime = LocalDateTime.now();
    }

    /**
     *
     * @param g
     */
    public void removeGroup(Group g) {
        mGroups.remove(g);
    }

    /**
     *
     * @param g
     */
    public void removeGroup(int g) {
        mGroups.remove(g);
    }

    /**
     *
     * @param g
     * @return
     */
    public boolean containsGroup(Group g) {
        return mGroups.contains(g);
    }

    /**
     *
     * Clear the groups array
     */
    public void clearGroups() {
        mGroups.clear();
    }

    /**
     *
     * @param i
     * @return
     */
    public Group getGroup(int i) {
        return mGroups.get(i);
    }

    public Group getGroup(Coach C) {
        for (Group g : mGroups) {
            if (g.containsRoster(C.getRoster())) {
                return g;
            }
        }
        return null;
    }

    /**
     *
     * @param n
     * @return
     */
    public Group getGroup(String n) {
        for (int i = 0; i < mGroups.size(); i++) {
            Group g = mGroups.get(i);
            if (g.getName().equals(n)) {
                return g;
            }
        }
        return null;
    }

    /**
     *
     * @return
     */
    public int getGroupsCount() {
        return mGroups.size();
    }

    /**
     *
     * @return
     */
    public int getRoundsCount() {
        return mRounds.size();
    }

    /**
     *
     * @param i
     * @return
     */
    public Round getRound(int i) {
        return mRounds.get(i);
    }

    /**
     * clear the round aarray
     */
    public void clearRounds() {
        mRounds.clear();
    }

    /**
     *
     * @param r
     */
    public void addRound(Round r) {
        mRounds.add(r);
        this.updateDateTime = LocalDateTime.now();
    }

    /**
     *
     * @param file
     */
    public void saveXML(final java.io.File file) {
        this.saveXML(file, false);
    }

    /*    public ArrayList<Group> getGroups() {
     return mGroups;
     }*/
    /**
     *
     * @param file
     * @param withRanking
     */
    private void saveXML(final java.io.File file, final boolean withRanking) {
        final Element document = new Element(StringConstants.CS_TOURNAMENT);

        document.setAttribute(StringConstants.CS_VERSION, "3");
        document.setAttribute(StringConstants.CS_ROUNDROBIN, Boolean.toString(isRoundRobin()));

        Element description = new Element(StringConstants.CS_DESCRIPTION);
        description.setText(mDescription);
        document.addContent(description);

        Element server = new Element(StringConstants.CS_SERVER);
        server.setAttribute(StringConstants.CS_URL, this.URL);
        server.setAttribute(StringConstants.CS_LOGIN, this.login);
        server.setAttribute(StringConstants.CS_PASSWORD, this.password);
        server.setAttribute(StringConstants.CS_TOUR_ID, Long.toString(tournament_id));

        // Tounament data
        for (int i = 0; i
                < RosterType.getRostersNamesCount(); i++) {
            final Element ros = new Element(StringConstants.CS_ROSTER);
            ros.setAttribute(StringConstants.CS_NAME, RosterType.getRostersName(i));
            document.addContent(ros);
        }

        // Save parameters
        final Element params = mParams.getXMLElement();
        document.addContent(params);

        // Save Clans
        for (Clan mClan : mClans) {
            final Element clan = mClan.getXMLElement();
            document.addContent(clan);
        }

        // Save Categories
        for (Category mCategorie : mCategories) {
            final Element category = mCategorie.getXMLElement();
            document.addContent(category);
        }

        // Save roster groups
        for (Group mGroup : mGroups) {
            final Element group = mGroup.getXMLElement();
            document.addContent(group);
        }

        for (Group mGroup : mGroups) {
            final Element group = mGroup.getXMLElementForPoints();
            document.addContent(group);
        }

        // Save Pool
        for (Pool mPool : mPools) {
            final Element pool = mPool.getXMLElement();
            document.addContent(pool);
        }

        // Save coachs
        for (Coach mCoach : mCoachs) {
            final Element coach = mCoach.getXMLElement();
            document.addContent(coach);
        }

        // Save teams
        for (Team mTeam : mTeams) {
            final Element team = mTeam.getXMLElement();
            document.addContent(team);
        }

        // Save Cup
        if (mCup != null) {
            Element cup = mCup.getXMLElement();
            document.addContent(cup);
        }

        // Save rounds
        for (int i = 0; i < mRounds.size(); i++) {

            mRounds.get(i).setRoundIndex(this, i);
            final Element round = mRounds.get(i).getXMLElement();

            /*if (withRanking) {
                // Build list of rankings
                final ArrayList<RankingForExport> rankings = new ArrayList<>();
                final boolean forPool = (mPools.size() > 0) && (!mRounds.get(i).isCup());
                final boolean forCup = mRounds.get(i).isCup();
                rankings.add(
                        new RankingForExport(
                                RankingForExport.CS_Individual,
                                RankingForExport.CS_General,
                                StringConstants.CS_NULL,
                                new IndivRanking(i,
                                        this.mParams.getRankingIndiv1(),
                                        this.mParams.getRankingIndiv2(),
                                        this.mParams.getRankingIndiv3(),
                                        this.mParams.getRankingIndiv4(),
                                        this.mParams.getRankingIndiv5(),
                                        mCoachs, false, false, forPool, forCup),
                                getRankingTypes(false)));
                if (this.mParams.isTeamTournament()) {
                    rankings.add(
                            new RankingForExport(RankingForExport.CS_Team, RankingForExport.CS_General,
                                    StringConstants.CS_NULL,
                                    new TeamRanking(
                                            this.mParams.isTeamVictoryOnly(),
                                            i,
                                            mParams.getRankingIndiv1(),
                                            mParams.getRankingIndiv2(),
                                            mParams.getRankingIndiv3(),
                                            mParams.getRankingIndiv4(),
                                            mParams.getRankingIndiv5(),
                                            mTeams, false), getRankingTypes(true)));
                }
                if (mParams.isEnableClans()) {
                    rankings.add(new RankingForExport(RankingForExport.CS_Clan,
                            RankingForExport.CS_General,
                            StringConstants.CS_NULL,
                            new ClanRanking(i,
                                    this.mParams.getRankingIndiv1(),
                                    this.mParams.getRankingIndiv2(),
                                    this.mParams.getRankingIndiv3(),
                                    this.mParams.getRankingIndiv4(),
                                    this.mParams.getRankingIndiv5(),
                                    this.getDisplayClans(), false),
                            getRankingTypes(false)));

                }
                if (mParams.isGroupsEnable()) {
                    for (Group g : mGroups) {
                        final ArrayList<Coach> list = new ArrayList<>();
                        for (int k = 0; k < mCoachs.size(); k++) {
                            final Coach c = mCoachs.get(k);
                            for (int l = 0; l < g.getRosterCount(); l++) {
                                if (g.getRoster(l).getName().equals(c.getRoster().getName())) {
                                    list.add(c);
                                    break;
                                }
                            }
                        }
                        rankings.add(
                                new RankingForExport(RankingForExport.CS_Group,
                                        g.getName(),
                                        StringConstants.CS_NULL,
                                        new IndivRanking(i,
                                                this.mParams.getRankingIndiv1(),
                                                this.mParams.getRankingIndiv2(),
                                                this.mParams.getRankingIndiv3(),
                                                this.mParams.getRankingIndiv4(),
                                                this.mParams.getRankingIndiv5(),
                                                list, false, false, false, false),
                                        getRankingTypes(false)));
                    }
                }
                // Annex ranking
                for (int j = 0; j < mParams.getCriteriaCount(); j++) {
                    final Criterion criteria = mParams.getCriterion(j);
                    rankings.add(new RankingForExport(RankingForExport.CS_Individual,
                            criteria.getName(),
                            RankingForExport.CS_Positive,
                            new AnnexIndivRanking(i,
                                    criteria,
                                    Parameters.C_RANKING_SUBTYPE_POSITIVE,
                                    mCoachs,
                                    this.mParams.getRankingIndiv1(),
                                    this.mParams.getRankingIndiv2(),
                                    this.mParams.getRankingIndiv3(),
                                    this.mParams.getRankingIndiv4(),
                                    this.mParams.getRankingIndiv5(),
                                    mParams.isTeamTournament(), false),
                            getRankingTypes(false)));

                    rankings.add(new RankingForExport(RankingForExport.CS_Individual,
                            criteria.getName(),
                            RankingForExport.CS_Negative,
                            new AnnexIndivRanking(i,
                                    criteria,
                                    Parameters.C_RANKING_SUBTYPE_NEGATIVE,
                                    mCoachs,
                                    this.mParams.getRankingIndiv1(),
                                    this.mParams.getRankingIndiv2(),
                                    this.mParams.getRankingIndiv3(),
                                    this.mParams.getRankingIndiv4(),
                                    this.mParams.getRankingIndiv5(),
                                    mParams.isTeamTournament(), false),
                            getRankingTypes(false)));

                    if (mParams.isTeamTournament()) {
                        rankings.add(
                                new RankingForExport(RankingForExport.CS_Team,
                                        criteria.getName(),
                                        RankingForExport.CS_Positive,
                                        new AnnexTeamRanking(i,
                                                criteria,
                                                Parameters.C_RANKING_SUBTYPE_POSITIVE,
                                                mTeams,
                                                this.mParams.getRankingIndiv1(),
                                                this.mParams.getRankingIndiv2(),
                                                this.mParams.getRankingIndiv3(),
                                                this.mParams.getRankingIndiv4(),
                                                this.mParams.getRankingIndiv5(),
                                                false),
                                        getRankingTypes(true)));
                        rankings.add(
                                new RankingForExport(RankingForExport.CS_Team,
                                        criteria.getName(),
                                        RankingForExport.CS_Negative,
                                        new AnnexTeamRanking(i,
                                                criteria,
                                                Parameters.C_RANKING_SUBTYPE_NEGATIVE,
                                                mTeams,
                                                this.mParams.getRankingIndiv1(),
                                                this.mParams.getRankingIndiv2(),
                                                this.mParams.getRankingIndiv3(),
                                                this.mParams.getRankingIndiv4(),
                                                this.mParams.getRankingIndiv5(),
                                                false),
                                        getRankingTypes(true)));
                    }

                    if (mParams.isEnableClans()) {
                        rankings.add(new RankingForExport(RankingForExport.CS_Clan,
                                criteria.getName(),
                                RankingForExport.CS_Positive,
                                new AnnexClanRanking(i,
                                        criteria,
                                        Parameters.C_RANKING_SUBTYPE_POSITIVE,
                                        mClans,
                                        this.mParams.getRankingIndiv1(),
                                        this.mParams.getRankingIndiv2(),
                                        this.mParams.getRankingIndiv3(),
                                        this.mParams.getRankingIndiv4(),
                                        this.mParams.getRankingIndiv5(),
                                        false),
                                getRankingTypes(false)));
                        rankings.add(
                                new RankingForExport(
                                        RankingForExport.CS_Clan,
                                        criteria.getName(),
                                        RankingForExport.CS_Negative,
                                        new AnnexClanRanking(i,
                                                criteria,
                                                Parameters.C_RANKING_SUBTYPE_NEGATIVE,
                                                mClans,
                                                this.mParams.getRankingIndiv1(),
                                                this.mParams.getRankingIndiv2(),
                                                this.mParams.getRankingIndiv3(),
                                                this.mParams.getRankingIndiv4(),
                                                this.mParams.getRankingIndiv5(),
                                                false),
                                        getRankingTypes(false)));
                    }
                }

                // Annex ranking For Formula
                for (int j = 0; j < mParams.getFormulaCount(); j++) {
                    final Formula formula = mParams.getFormula(j);
                    rankings.add(new RankingForExport(RankingForExport.CS_Individual,
                            formula.getName(),
                            RankingForExport.CS_Positive,
                            new AnnexIndivRanking(i,
                                    formula,
                                    Parameters.C_RANKING_SUBTYPE_POSITIVE,
                                    mCoachs,
                                    this.mParams.getRankingIndiv1(),
                                    this.mParams.getRankingIndiv2(),
                                    this.mParams.getRankingIndiv3(),
                                    this.mParams.getRankingIndiv4(),
                                    this.mParams.getRankingIndiv5(),
                                    mParams.isTeamTournament(), false),
                            getRankingTypes(false)));

                    rankings.add(new RankingForExport(RankingForExport.CS_Individual,
                            formula.getName(),
                            RankingForExport.CS_Negative,
                            new AnnexIndivRanking(i,
                                    formula,
                                    Parameters.C_RANKING_SUBTYPE_NEGATIVE,
                                    mCoachs,
                                    this.mParams.getRankingIndiv1(),
                                    this.mParams.getRankingIndiv2(),
                                    this.mParams.getRankingIndiv3(),
                                    this.mParams.getRankingIndiv4(),
                                    this.mParams.getRankingIndiv5(),
                                    mParams.isTeamTournament(), false),
                            getRankingTypes(false)));

                    if (mParams.isTeamTournament()) {
                        rankings.add(
                                new RankingForExport(RankingForExport.CS_Team,
                                        formula.getName(),
                                        RankingForExport.CS_Positive,
                                        new AnnexTeamRanking(i,
                                                formula,
                                                Parameters.C_RANKING_SUBTYPE_POSITIVE,
                                                mTeams,
                                                this.mParams.getRankingIndiv1(),
                                                this.mParams.getRankingIndiv2(),
                                                this.mParams.getRankingIndiv3(),
                                                this.mParams.getRankingIndiv4(),
                                                this.mParams.getRankingIndiv5(),
                                                false),
                                        getRankingTypes(true)));
                        rankings.add(
                                new RankingForExport(RankingForExport.CS_Team,
                                        formula.getName(),
                                        RankingForExport.CS_Negative,
                                        new AnnexTeamRanking(i,
                                                formula,
                                                Parameters.C_RANKING_SUBTYPE_NEGATIVE,
                                                mTeams,
                                                this.mParams.getRankingIndiv1(),
                                                this.mParams.getRankingIndiv2(),
                                                this.mParams.getRankingIndiv3(),
                                                this.mParams.getRankingIndiv4(),
                                                this.mParams.getRankingIndiv5(),
                                                false),
                                        getRankingTypes(true)));
                    }

                    if (mParams.isEnableClans()) {
                        rankings.add(new RankingForExport(RankingForExport.CS_Clan,
                                formula.getName(),
                                RankingForExport.CS_Positive,
                                new AnnexClanRanking(i,
                                        formula,
                                        Parameters.C_RANKING_SUBTYPE_POSITIVE,
                                        mClans,
                                        this.mParams.getRankingIndiv1(),
                                        this.mParams.getRankingIndiv2(),
                                        this.mParams.getRankingIndiv3(),
                                        this.mParams.getRankingIndiv4(),
                                        this.mParams.getRankingIndiv5(),
                                        false),
                                getRankingTypes(false)));
                        rankings.add(
                                new RankingForExport(
                                        RankingForExport.CS_Clan,
                                        formula.getName(),
                                        RankingForExport.CS_Negative,
                                        new AnnexClanRanking(i,
                                                formula,
                                                Parameters.C_RANKING_SUBTYPE_NEGATIVE,
                                                mClans,
                                                this.mParams.getRankingIndiv1(),
                                                this.mParams.getRankingIndiv2(),
                                                this.mParams.getRankingIndiv3(),
                                                this.mParams.getRankingIndiv4(),
                                                this.mParams.getRankingIndiv5(),
                                                false),
                                        getRankingTypes(false)));
                    }
                }

                // Store rankings
                for (RankingForExport ranking : rankings) {
                    final Element rank = ranking.getXMLElement();
                    round.addContent(rank);
                }
            }*/
            document.addContent(round);
        }
        FileOutputStream os = null;
        try {
            final XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
            os = new FileOutputStream(file);
            sortie.output(document, os);

        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(MainFrame.getMainFrame(), e.getMessage());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(MainFrame.getMainFrame(), e.getMessage());
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    LOG.log(Level.INFO, e.getLocalizedMessage());
                }
            }

        }
    }

    public ArrayList<Integer> getRankingTypes(boolean byTeam) {
        ArrayList<Integer> rankingTypes = new ArrayList<>();
        if (!byTeam) {
            if (this.mParams.getRankingIndiv1() != 0) {
                rankingTypes.add(mParams.getRankingIndiv1());
            }
            if (this.mParams.getRankingIndiv2() != 0) {
                rankingTypes.add(mParams.getRankingIndiv2());
            }
            if (this.mParams.getRankingIndiv3() != 0) {
                rankingTypes.add(mParams.getRankingIndiv3());
            }
            if (this.mParams.getRankingIndiv4() != 0) {
                rankingTypes.add(mParams.getRankingIndiv4());
            }
            if (this.mParams.getRankingIndiv5() != 0) {
                rankingTypes.add(mParams.getRankingIndiv5());
            }
        } else {
            if (this.mParams.getRankingTeam1() != 0) {
                rankingTypes.add(mParams.getRankingTeam1());
            }
            if (this.mParams.getRankingTeam2() != 0) {
                rankingTypes.add(mParams.getRankingTeam2());
            }
            if (this.mParams.getRankingTeam3() != 0) {
                rankingTypes.add(mParams.getRankingTeam3());
            }
            if (this.mParams.getRankingTeam4() != 0) {
                rankingTypes.add(mParams.getRankingTeam4());
            }
            if (this.mParams.getRankingTeam5() != 0) {
                rankingTypes.add(mParams.getRankingTeam5());
            }
        }
        return rankingTypes;
    }

    /**
     *
     * @param file
     */
    public void exportFullFBB(final java.io.File file) {
        this.saveXML(file, true);
    }

    public ArrayList<Coach> getBadRosterCoachs() {
        ArrayList<Coach> badRoster = new ArrayList<>();
        for (Coach mCoach : mCoachs) {
            if (mCoach.getNaf() > 0) {
                String naf = Integer.toString(mCoach.getNaf());

                String naf_roster = NAF.getNAFRoster(mCoach.getRoster().getName());
                if (naf_roster.equals(RosterType.translate("UNKNOWN"))) {

                    badRoster.add(mCoach);

                } else {
                    mCoach.setNafRoster(naf_roster);
                }
            }
        }

        return badRoster;
    }

    public ArrayList<Coach> getNonNAFCoachs() {

        ArrayList<Coach> nonNaf = new ArrayList<>();
        for (Coach mCoach : mCoachs) {

            if (mCoach.getNaf() == 0) {
                nonNaf.add(mCoach);

            }
        }
        return nonNaf;
    }

    /**
     *
     * @param file
     */
    public void exportNAF(final java.io.File file) {
        final SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd HH:MM", Locale.getDefault());

        Criterion critTd = null;
        Criterion critInj = null;

        for (int i = 0; i < mParams.getCriteriaCount(); i++) {
            final Criterion crit = mParams.getCriterion(i);
            if (i == 0) {
                critTd = crit;
            }
            if (i == 1) {
                critInj = crit;
            }
        }

        try {
            try (PrintWriter writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8")))) {
                writer.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
                writer.println("<nafReport xmlns:blo=\"http://www.bloodbowl.net\">");
                writer.println(java.text.MessageFormat.format(("<organiser>{0}</organiser>"), new Object[]{mParams.getTournamentOrga()}));
                writer.println(("<coaches>"));
                for (Coach mCoach : mCoachs) {

                    if (mCoach.getNaf() > 0) {
                        String naf = Integer.toString(mCoach.getNaf());

                        String naf_roster = NAF.getNAFRoster(mCoach.getRoster().getName());
                        if (naf_roster.equals(RosterType.translate("UNKNOWN"))) {
                            naf_roster = mCoach.getNafRoster();
                        }
                        if (!naf_roster.equals("None")) {
                            writer.println(("<coach>"));
                            writer.println(java.text.MessageFormat.format("<name>{0}</name>", new Object[]{mCoach.getName()}));
                            writer.println(java.text.MessageFormat.format("<number>{0}</number>", new Object[]{naf}));
                            writer.println(java.text.MessageFormat.format("<team>{0}</team>", new Object[]{naf_roster}));
                            writer.println(("</coach>"));
                        } else {
                            mCoach.setNaf(0);
                        }
                    }
                }
                writer.println(("</coaches>"));

                for (Round mRound : mRounds) {
                    ArrayList<CoachMatch> matches = mRound.getCoachMatchs();
                    for (int j = 0; j < matches.size(); j++) {
                        CoachMatch cm = matches.get(j);
                        if ((((Coach) cm.getCompetitor1()).getNaf() > 0) && (((Coach) cm.getCompetitor2()).getNaf() > 0)) {
                            writer.println(("<game>"));
                            writer.println(java.text.MessageFormat.format("<timeStamp>{0}</timeStamp>", new Object[]{format.format(mRound.getHour())}));
                            Coach p;
                            if (cm.getSubstitute1() == null) {
                                p = (Coach) cm.getCompetitor1();
                            } else {
                                p = cm.getSubstitute1().getSubstitute();
                            }
                            writer.println(("<playerRecord>"));
                            writer.println(java.text.MessageFormat.format(("<name>{0}</name>"), new Object[]{p.getName()}));
                            String nafID = Integer.toString(p.getNaf());
                            nafID = nafID.replace(" ", "");
                            writer.println(java.text.MessageFormat.format(("<number>{0}</number>"), nafID));
                            writer.println(java.text.MessageFormat.format(("<teamRating>{0}</teamRating>"), new Object[]{p.getRank()}));
                            writer.println(java.text.MessageFormat.format(("<touchDowns>{0}</touchDowns>"), new Object[]{cm.getValue(critTd).getValue1()}));
                            writer.println(java.text.MessageFormat.format(("<badlyHurt>{0}</badlyHurt>"), new Object[]{cm.getValue(critInj).getValue1()}));
                            writer.println(("</playerRecord>"));
                            if (cm.getSubstitute2() == null) {
                                p = (Coach) cm.getCompetitor2();
                            } else {
                                p = cm.getSubstitute2().getSubstitute();
                            }
                            writer.println(("<playerRecord>"));
                            writer.println(java.text.MessageFormat.format(("<name>{0}</name>"), new Object[]{p.getName()}));
                            nafID = Integer.toString(p.getNaf());
                            nafID = nafID.replace(" ", "");
                            writer.println(java.text.MessageFormat.format(("<number>{0}</number>"), nafID));
                            writer.println(java.text.MessageFormat.format(("<teamRating>{0}</teamRating>"), new Object[]{p.getRank()}));
                            writer.println(java.text.MessageFormat.format(("<touchDowns>{0}</touchDowns>"), new Object[]{cm.getValue(critTd).getValue2()}));
                            writer.println(java.text.MessageFormat.format(("<badlyHurt>{0}</badlyHurt>"), new Object[]{cm.getValue(critInj).getValue2()}));
                            writer.println(("</playerRecord>"));
                            writer.println(("</game>"));
                        }
                    }
                }
                writer.println(("</nafReport>"));
            }

        } catch (IOException e) {
            LOG.log(Level.WARNING, e.getMessage());
        }

    }

    public void loadRosters(Element racine) {
        LRB lrb = LRB.getLRB(LRB.E_Version.BB2020);
        try {

            String lrb_version = racine.getAttributeValue(StringConstants.CS_LRB);
            if (lrb_version != null) {
                if (lrb_version.equals("BB2020")) {
                    lrb = LRB.getLRB(LRB.E_Version.BB2020);
                }
                if (lrb_version.equals("BB2016")) {
                    lrb = LRB.getLRB(LRB.E_Version.BB2016);
                }
                if (lrb_version.equals("CRP1")) {
                    lrb = LRB.getLRB(LRB.E_Version.CRP1);
                }
                if (lrb_version.equals("LRB6")) {
                    lrb = LRB.getLRB(LRB.E_Version.LRB6);
                }
                if (lrb_version.equals("LRB5")) {
                    lrb = LRB.getLRB(LRB.E_Version.LRB5);
                }
                if (lrb_version.equals("LRB4")) {
                    lrb = LRB.getLRB(LRB.E_Version.LRB4);
                }
                if (lrb_version.equals("LRB3")) {
                    lrb = LRB.getLRB(LRB.E_Version.LRB3);
                }
                if (lrb_version.equals("LRB2")) {
                    lrb = LRB.getLRB(LRB.E_Version.LRB2);
                }
                if (lrb_version.equals("LRB1")) {
                    lrb = LRB.getLRB(LRB.E_Version.LRB1);
                }
            }

            final List<Element> ros = racine.getChildren(StringConstants.CS_ROSTER);
            if (ros != null) {
                if (ros.size() > 0) {
                    RosterType.newRostersNames();
                    RosterType.newRostersTypes();
                    final Iterator<Element> it_ros = ros.iterator();
                    while (it_ros.hasNext()) {
                        final Element r = it_ros.next();
                        String name = r.getAttributeValue(StringConstants.CS_NAME);
                        name = RosterType.getRosterName(name);
                        RosterType.addRosterName(name);
                        RosterType.putRosterType(name, new RosterType(name));
                    }
                } else {
                    RosterType.initCollection(lrb);
                }
            } else {
                RosterType.initCollection(lrb);
            }
        } catch (NullPointerException ne) {
            RosterType.initCollection(lrb);
        }
    }

    /**
     *
     * @param racine
     */
    private void loadXMLv3(final Element racine) {

        try {
            setRoundRobin(Boolean.parseBoolean(racine.getAttributeValue(StringConstants.CS_ROUNDROBIN)));
        } catch (Exception e) {
            setRoundRobin(false);
        }

        Element e = racine.getChild(StringConstants.CS_DESCRIPTION);
        if (e != null) {
            mDescription = e.getText();
        }

        try {
            Element server = racine.getChild(StringConstants.CS_SERVER);
            this.URL = server.getAttributeValue(StringConstants.CS_URL);
            this.login = server.getAttributeValue(StringConstants.CS_LOGIN);
            this.password = server.getAttributeValue(StringConstants.CS_PASSWORD);
            this.tournament_id = server.getAttribute(StringConstants.CS_TOUR_ID).getLongValue();
        } catch (Exception e2) {

        }

        loadRosters(racine);

        /* Parameters */
        final Element params = racine.getChild(StringConstants.CS_PARAMETERS);
        mParams.setXMLElement(params);

        /* Groups */
        try {
            final List<Element> groups = racine.getChildren(StringConstants.CS_GROUP);
            final Iterator<Element> gr = groups.iterator();
            mGroups.clear();

            while (gr.hasNext()) {
                final Element group = gr.next();
                final Group groupe = new Group(group.getAttributeValue(StringConstants.CS_NAME));
                groupe.setXMLElement(group);
                mGroups.add(groupe);
            }

        } catch (NullPointerException npe) {
        }

        try {
            final List<Element> groups = racine.getChildren(StringConstants.CS_GROUP_MODIFIER_POINTS);
            final Iterator<Element> gr = groups.iterator();

            while (gr.hasNext()) {
                final Element group = gr.next();
                String name = group.getAttributeValue(StringConstants.CS_NAME);
                final Group groupe = getGroup(name);
                groupe.setXMLElementForPoints(group);
            }

        } catch (NullPointerException npe) {
        }

        /* Clans */
        final List<Element> clans = racine.getChildren(StringConstants.CS_CLAN);
        final Iterator<Element> h = clans.iterator();
        mClans.clear();
        Clan.newClanMap();

        while (h.hasNext()) {
            final Element clan = h.next();
            final Clan c = new Clan(clan.getAttributeValue(StringConstants.CS_NAME));
            c.setXMLElement(clan);
            mClans.add(c);
        }

        /* Category */
        final List<Element> categories = racine.getChildren(StringConstants.CS_CATEGORY);
        final Iterator<Element> itc = categories.iterator();
        mCategories.clear();
        Category.newCategoryMap();

        while (itc.hasNext()) {
            final Element cat = itc.next();
            final Category c = new Category(cat.getAttributeValue(StringConstants.CS_NAME));
            Category.putCategory(c.getName(), c);
            mCategories.add(c);
        }
        if (mCategories.isEmpty()) {
            Category.putCategory(StringConstants.CS_NONE, new Category(StringConstants.CS_NONE));
        }

        /* Coachs */
        final List<Element> coachs = racine.getChildren(StringConstants.CS_COACH);
        final Iterator<Element> i = coachs.iterator();
        mCoachs.clear();
        Coach.newCoachMap();
        Coach.putCoach(StringConstants.CS_NONE, Coach.getNullCoach());

        while (i.hasNext()) {
            final Element coach = i.next();
            final Coach c = new Coach();
            c.setXMLElement(coach);
            mCoachs.add(c);
            c.clearMatchs();
        }

        /* Teams */
        final List<Element> teams = racine.getChildren(StringConstants.CS_TEAM);
        Team.newTeamMap();
        Team.putTeam(StringConstants.CS_NONE, Team.getNullTeam());
        final Iterator<Element> l = teams.iterator();
        mTeams.clear();
        while (l.hasNext()) {
            final Element team = l.next();
            final Team t = new Team();
            t.setXMLElement(team);
            mTeams.add(t);
        }

        /* Pools */
        final List<Element> pools = racine.getChildren(StringConstants.CS_POOL);
        final Iterator<Element> p = pools.iterator();
        mPools.clear();
        while (p.hasNext()) {
            final Element pool = p.next();
            final Pool po = new Pool();
            po.setXMLElement(pool);
            mPools.add(po);
        }

        /**
         * Cup
         */
        Element cup = racine.getChild(StringConstants.CS_CUP);
        if (cup != null) {
            if (mCup == null) {
                mCup = new Cup();
            }
            mCup.setXMLElement(cup);
        }

        /* Rounds */
        List<Element> rounds = racine.getChildren(StringConstants.CS_ROUND);
        if (rounds.isEmpty()) {
            rounds = racine.getChildren("Ronde");
        }
        final Iterator<Element> j = rounds.iterator();
        mRounds.clear();

        while (j.hasNext()) {
            final Element round = j.next();
            final Round r = new Round(getRoundsCount(), this);
            r.setXMLElement(round);
            r.setRoundIndex(this, getRoundsCount());
            mRounds.add(r);

            r.update();

        }

    }

    public static void clear() {
        mSingleton = null;
    }

    /**
     *
     * @param file
     */
    public void loadXML(final java.io.File file) {

        final SAXBuilder sxb = new SAXBuilder();
        try {
            final org.jdom.Document document = sxb.build(file);
            final Element racine = document.getRootElement();

            try {
                final String version = racine.getAttributeValue(StringConstants.CS_VERSION);
                if (Integer.parseInt(version) == 3) {
                    loadXMLv3(racine);

                }
            } catch (NumberFormatException npe) {
                //        JOptionPane.showMessageDialog(MainFrame.getMainFrame(), "Obsolete Version");
            }

        } catch (JDOMException e) {
            //        JOptionPane.showMessageDialog(MainFrame.getMainFrame(), e.getLocalizedMessage());

        } catch (IOException e) {
            //      JOptionPane.showMessageDialog(MainFrame.getMainFrame(), e.getMessage());
        }
    }

    /**
     *
     * @return
     */
    public int getActiveCoachNumber() {
        int nb = 0;
        for (Coach mCoach : mCoachs) {
            if (mCoach.isActive()) {
                nb++;
            }
        }
        return nb;
    }

    /**
     *
     * @return
     */
    public ArrayList<Coach> getActiveCoaches() {
        final ArrayList<Coach> v = new ArrayList<>();

        for (Coach mCoach : mCoachs) {
            if (mCoach.isActive()) {
                v.add(mCoach);
            }
        }
        return v;
    }

    /**
     *
     * @return
     */
    public int getPoolCount() {
        return mPools.size();
    }

    /**
     *
     * @param p
     */
    public void addPool(Pool p) {
        mPools.add(p);
        this.updateDateTime = LocalDateTime.now();
    }

    /**
     * Clear the Pool array
     */
    public void clearPools() {
        mPools.clear();
    }

    /**
     *
     * @param i
     * @return
     */
    public Pool getPool(int i) {
        return mPools.get(i);
    }

    /**
     * @param mParams the mParams to set
     */
    public void setParams(Parameters mParams) {
        this.mParams = mParams;
        this.updateDateTime = LocalDateTime.now();
    }

    /**
     * @return the mRoundRobin
     */
    public boolean isRoundRobin() {
        return mRoundRobin;
    }

    /**
     * @param mRoundRobin the mRoundRobin to set
     */
    public void setRoundRobin(boolean mRoundRobin) {
        this.mRoundRobin = mRoundRobin;
        this.updateDateTime = LocalDateTime.now();
    }

    /**
     *
     * @param r
     */
    public void removeRound(Round r) {
        mRounds.remove(r);
        roundsUpdated = true;
    }

    /**
     *
     * @param r
     */
    public void removeRound(int r) {
        mRounds.remove(r);
        roundsUpdated = true;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String tmp) {
        mDescription = tmp;
        updateDateTime = LocalDateTime.now();
    }

    // To do : Fill all data from tour (Data from server)
    public static void pull(Tournament tour) {
        // The data comes from server, please copy all parameters

        getTournament().mDescription = tour.mDescription;
        getTournament().mRoundRobin = tour.mRoundRobin;

        // Find Parameters, copy the properties
        getTournament().getParams().pull(tour.getParams());

        // Find Rosters, copy the properties
        //RosterType.pull(tour.getRosterType());
        // Find Clans, copy the properties
        getTournament().pullClans(tour.mClans);

        // Find Groups, copy the properties
        getTournament().pullGroups(tour.mGroups);

        // Find Categories, copy the properties
        getTournament().pullCategories(tour.mCategories);

        // Find teams, copy the properties
        getTournament().pullTeams(tour.mTeams);

        // Find coachs, copy the properties
        getTournament().pullCoachs(tour.mCoachs);

        // Find Round, copy the  properties
        getTournament().pullRounds(tour.mRounds);

        // Update  Screen
        MainFrame.getMainFrame().update();
    }

    public void pullRounds(ArrayList<Round> rounds) {
        for (Round round : rounds) {
            boolean bFound = false;
            for (Round local : mRounds) {
                if (round.getUID() == local.getUID()) {
                    local.pull(round);
                    bFound = true;
                    break;
                }
            }

            if (!bFound) {
                Round local = new Round(this.getRoundsCount(), this);
                local.pull(round);
                mRounds.add(local);
                // Coaches and matches are synchronized later
            }
        }
    }

    public void pullClans(ArrayList<Clan> clans) {
        for (Clan clan : clans) {
            boolean bFound = false;
            for (Clan local : mClans) {
                if (clan.getUID() == local.getUID()) {
                    local.pull(clan);
                    bFound = true;
                    break;
                }
            }

            if (!bFound) {
                Clan local = new Clan(clan.getName());
                local.pull(clan);
                mClans.add(local);
                // Coaches and matches are synchronized later
            }
        }

        if (clans.size() != mClans.size()) {
            mClans.clear();
            pullClans(clans);
        }
    }

    public void pullCategories(ArrayList<Category> categories) {
        for (Category category : categories) {
            boolean bFound = false;
            for (Category local : mCategories) {
                if (category.getUID() == local.getUID()) {
                    local.pull(category);
                    bFound = true;
                    break;
                }
            }

            if (!bFound) {
                Category local = new Category(category.getName());
                local.pull(category);
                mCategories.add(local);
                // Coaches and matches are synchronized later
            }
        }

        if (categories.size() != mCategories.size()) {
            mCategories.clear();
            pullCategories(categories);
        }
    }

    public void pullTeams(ArrayList<Team> teams) {
        for (Team team : teams) {
            boolean bFound = false;
            for (Team local : mTeams) {
                if (team.getUID() == local.getUID()) {
                    local.pull(team);
                    bFound = true;
                    break;
                }
            }

            if (!bFound) {
                Team local = new Team(team.getName());
                local.pull(team);
                mTeams.add(local);
                // Coaches and matches are synchronized later
            }
        }

        if (teams.size() != mTeams.size()) {
            mTeams.clear();
            pullTeams(teams);
        }
    }

    public void pullCoachs(ArrayList<Coach> coachs) {
        for (Coach coach : coachs) {
            boolean bFound = false;
            for (Coach local : mCoachs) {
                if (coach.getUID() == local.getUID()) {
                    local.pull(coach);
                    bFound = true;
                    break;
                }
            }

            if (!bFound) {
                Coach local = new Coach(coach.getName());
                local.pull(coach);
                mCoachs.add(local);
                // matches are synchronized later
            }
        }

        if (coachs.size() != mCoachs.size()) {
            mCoachs.clear();
            pullCoachs(coachs);
        }
    }

    public void pullGroups(ArrayList<Group> groups) {
        for (Group group : groups) {
            boolean bFound = false;
            for (Group local : mGroups) {
                if (group.getUID() == local.getUID()) {
                    local.pull(group);
                    bFound = true;
                    break;
                }
            }

            if (!bFound) {
                Group local = new Group(group.getName());
                local.pull(group);

                mGroups.add(local);
                // Coaches and matches are synchronized later
            }
        }

        if (groups.size() != mGroups.size()) {
            mGroups.clear();
            pullGroups(groups);
        }

        // Update Groups opponent modifier points
        for (Group local : mGroups) {
            for (Group group : groups) {
                if (local.getUID() == group.getUID()) {
                    local.pullOpponentGroupModifierPoints(group);
                }
            }
        }
    }

    private HashMap<String, RosterType> mRosterTypes = null;

    public HashMap<String, RosterType> getRosterType() {
        if (mRosterTypes == null) {
            mRosterTypes = RosterType.getRosters();
        }
        return mRosterTypes;
    }

    // To Do : Fill only coach/team/match data from tour (Data From client)
    public static void push(Tournament tour) {
        // Find Clans, copy the properties        
        if (tour.clansUpdated) {
            getTournament().pushClans(tour.mClans);
        }

        // Find Teams, copy the properties
        if (tour.teamsUpdated) {
            getTournament().pushTeams(tour.mTeams);
        }
        // Find Coachs, copy the properties
        if (tour.coachsUpdated) {
            getTournament().pushCoachs(tour.mCoachs);
        }
        // Find Round, copy the  properties
        if (tour.roundsUpdated) {
            getTournament().pushRounds(tour.mRounds);
        }
    }

    protected boolean clansUpdated = false;
    protected boolean coachsUpdated = false;
    protected boolean teamsUpdated = false;

    public boolean isClansUpdated() {
        return clansUpdated;
    }

    public void setClansUpdated(boolean clansUpdated) {
        this.clansUpdated = clansUpdated;
        updateDateTime = LocalDateTime.now();
    }

    public boolean isCoachsUpdated() {
        return coachsUpdated;
    }

    public void setCoachsUpdated(boolean coachsUpdated) {
        this.coachsUpdated = coachsUpdated;
        updateDateTime = LocalDateTime.now();
    }

    public boolean isTeamsUpdated() {
        return teamsUpdated;
    }

    public void setTeamsUpdated(boolean teamsUpdated) {
        this.teamsUpdated = teamsUpdated;
        updateDateTime = LocalDateTime.now();
    }

    public boolean isRoundsUpdated() {
        return roundsUpdated;
    }

    public void setRoundsUpdated(boolean roundsUpdated) {
        this.roundsUpdated = roundsUpdated;
        this.updateDateTime = LocalDateTime.now();
    }
    protected boolean roundsUpdated = false;

    public void pushClans(ArrayList<Clan> clans) {

        for (Clan clan : clans) {
            boolean bFound = false;
            for (Clan local : mClans) {
                if (clan.getUID() == local.getUID()) {
                    local.push(clan);
                    bFound = true;
                    break;
                }
            }

            if (!bFound) {
                Clan local = new Clan(clan.getName());
                local.push(clan);
                mClans.add(local);
                // Coaches and matches are synchronized later
            }
        }

        if (clans.size() != mClans.size()) {
            mClans.clear();
            pushClans(clans);
        }
    }

    public void pushTeams(ArrayList<Team> teams) {
        for (Team team : teams) {
            boolean bFound = false;
            for (Team local : mTeams) {
                if (team.getUID() == local.getUID()) {
                    local.push(team);
                    bFound = true;
                    break;
                }
            }

            if (!bFound) {
                Team local = new Team(team.getName());
                local.push(team);
                mTeams.add(local);
                // Coaches and matches are synchronized later
            }
        }

        if (teams.size() != mTeams.size()) {
            mTeams.clear();
            pushTeams(teams);
        }
    }

    public void pushCoachs(ArrayList<Coach> coachs) {
        for (Coach coach : coachs) {
            boolean bFound = false;
            for (Coach local : mCoachs) {
                if (coach.getUID() == local.getUID()) {
                    local.push(coach);
                    bFound = true;
                    break;
                }
            }

            if (!bFound) {
                Coach local = new Coach(coach.getName());
                local.push(coach);
                mCoachs.add(local);
                // matches are synchronized later
            }
        }

        if (coachs.size() != mCoachs.size()) {
            mCoachs.clear();
            pushCoachs(coachs);
        }
    }

    public void pushRounds(ArrayList<Round> rounds) {
        for (Round round : rounds) {
            boolean bFound = false;
            for (Round local : mRounds) {
                if (round.getUID() == local.getUID()) {
                    local.push(round);
                    bFound = true;
                    break;
                }
            }

            /*if (!bFound) {
                Round local = new Round();
                local.pull(round);
                mRounds.add(local);
                // Coaches and matches are synchronized later
            }*/
        }
    }

    public void resetUpdated() {
        this.clansUpdated = false;
        this.coachsUpdated = false;
        this.teamsUpdated = false;
        this.roundsUpdated = false;

        for (Round r : mRounds) {
            r.setUpdated(false);
        }

        for (Coach c : mCoachs) {
            c.setUpdated(false);
        }

        for (Team t : mTeams) {
            t.setUpdated(false);
        }

        for (Clan c : mClans) {
            c.setUpdated(false);
        }

    }

    public String[] getTeamsNames() {
        /* ArrayList<String> names = new ArrayList<>();
        for (Team t : mTeams) {
            names.add(t.getName());
        }*/

        String[] str = new String[mTeams.size()];
        for (int i = 0; i < mTeams.size(); i++) {
            str[i] = mTeams.get(i).getName();
        }
        return str;
    }

    public int getActiveCompetitorsCount() {
        if (mParams.isTeamTournament()) {
            if (mParams.getTeamPairing() == ETeamPairing.TEAM_PAIRING) {
                return getTeamsCount();
            } else {
                return getActiveCoachNumber();
            }
        } else {
            return getActiveCoachNumber();
        }
    }

    public JSONObject getJSON() {
        JSONObject json = new JSONObject();
        if (createDateTime == null) {
            createDateTime = LocalDateTime.now();
        }
        if (updateDateTime == null) {
            updateDateTime = LocalDateTime.now();
        }

        json.put("createDateTime", createDateTime.toString());
        json.put("updateDateTime", updateDateTime.toString());
        json.put("description", this.mDescription);
        json.put("closed", mClosed);

        JSONArray array = new JSONArray();
        for (String orga : mParams.getTournamentOrga()) {
            array.put(orga);
        }

        json.put("orgas", array);
        return json;
    }

    public void updateFromJSON(JSONObject object) {
        Object tmp = object.get("createDateTime");
        if (tmp != JSONObject.NULL) {
            createDateTime = LocalDateTime.parse(tmp.toString());
        }
        tmp = object.get("updateDateTime");
        if (tmp != JSONObject.NULL) {
            updateDateTime = LocalDateTime.parse(tmp.toString());
        }
        mDescription = object.get("description").toString();
        mClosed = Boolean.parseBoolean(object.get("closed").toString());
        mRoundRobin = Boolean.parseBoolean(object.get("roundRobin").toString());
        JSONArray array = object.getJSONArray("orgas");
        for (int i = 0; i < array.length(); i++) {
            mParams.addTournamentOrga(array.getString(i));
        }
        //mParams.setTournamentOrga(object.getString("orgas"));
    }

}
