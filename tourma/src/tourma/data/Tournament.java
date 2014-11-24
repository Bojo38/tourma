/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import com.google.zxing.WriterException;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.encoder.Encoder;
import com.google.zxing.qrcode.encoder.QRCode;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import org.jdom2.DataConversionException;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import tourma.MainFrame;
import static tourma.data.Parameters.sbundle;
import tourma.tableModel.MjtAnnexRankClan;
import tourma.tableModel.MjtAnnexRankIndiv;
import tourma.tableModel.MjtAnnexRankTeam;
import tourma.tableModel.MjtRankingClan;
import tourma.tableModel.MjtRankingIndiv;
import tourma.tableModel.MjtRankingTeam;
import tourma.utility.StringConstants;

/**
 *
 * @author Frederic Berger
 */
public class Tournament implements IContainCoachs {

    /**
     *
     */
    private static Tournament mSingleton;
    private static final Object myLock = new Object();
    private static final Logger LOG = Logger.getLogger(Tournament.class.getName());

    /**
     *
     * @return
     */
    public static Tournament resetTournament() {
        mSingleton = new Tournament();
        return mSingleton;
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

    /**
     *
     * @param source
     * @return
     */
    public static String getRosterTranslation(final String source) {
        String result = java.util.ResourceBundle.getBundle("tourma/languages/language").getString("UNKNOWN");
        if (source.equals(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("AmazonKey"))) {
            result = java.util.ResourceBundle.getBundle("tourma/languages/language").getString("AMAZONS");
        }
        if (java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("UnderworldKey").equals(source)) {
            result = java.util.ResourceBundle.getBundle("tourma/languages/language").getString("UNDERWORLD");
        }
        if (java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("ChaosKey").equals(source)) {
            result = java.util.ResourceBundle.getBundle("tourma/languages/language").getString("CHAOS");
        }
        if (source.equals(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("ElfKey"))) {
            result = java.util.ResourceBundle.getBundle("tourma/languages/language").getString("ELVES");
        }
        if (source.equals(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("WoodElfKey"))) {
            result = java.util.ResourceBundle.getBundle("tourma/languages/language").getString("WOOD ELVES");
        }
        if (source.equals(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("DarkElfKey"))) {
            result = java.util.ResourceBundle.getBundle("tourma/languages/language").getString("DARK ELVES");
        }
        if (source.equals(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("GoblinKey"))) {
            result = java.util.ResourceBundle.getBundle("tourma/languages/language").getString("GOBLINS");
        }
        if (source.equals(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("HalflingKey"))) {
            result = java.util.ResourceBundle.getBundle("tourma/languages/language").getString("HALFLINGS");
        }
        if (source.equals(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("HighElfKey"))) {
            result = java.util.ResourceBundle.getBundle("tourma/languages/language").getString("HIGH ELVES");
        }
        if (source.equals(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("LizardmenKey"))) {
            result = java.util.ResourceBundle.getBundle("tourma/languages/language").getString("LIZARDMEN");
        }
        if (source.equals(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("HumanKey"))) {
            result = java.util.ResourceBundle.getBundle("tourma/languages/language").getString("HUMANS");
        }
        if (source.equals(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("KhemriKey"))) {
            result = java.util.ResourceBundle.getBundle("tourma/languages/language").getString("KHEMRI");
        }
        if (source.equals(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("UndeadKey"))) {
            result = java.util.ResourceBundle.getBundle("tourma/languages/language").getString("UNDEAD");
        }
        if (source.equals(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("DwarfKey"))) {
            result = java.util.ResourceBundle.getBundle("tourma/languages/language").getString("DWARVES");
        }
        if (source.equals(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("ChaosDwarfKey"))) {
            result = java.util.ResourceBundle.getBundle("tourma/languages/language").getString("CHAOS DWARVES");
        }
        if (source.equals(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("NecromanticKey"))) {
            result = java.util.ResourceBundle.getBundle("tourma/languages/language").getString("NECROMANTIC");
        }
        if (source.equals(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("NorseKey"))) {
            result = java.util.ResourceBundle.getBundle("tourma/languages/language").getString("NORSE");
        }
        if (source.equals(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("NurgleKey"))) {
            result = java.util.ResourceBundle.getBundle("tourma/languages/language").getString("NURGLE'S ROTTERS");
        }
        if (source.equals(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("OgreKey"))) {
            result = java.util.ResourceBundle.getBundle("tourma/languages/language").getString("OGRES");
        }
        if (source.equals(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("OrcKey"))) {
            result = java.util.ResourceBundle.getBundle("tourma/languages/language").getString("ORC");
        }
        if (source.equals(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("ChaosPactKey"))) {
            result = java.util.ResourceBundle.getBundle("tourma/languages/language").getString("CHAOS PACT");
        }
        if (source.equals(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("SkavenKey"))) {
            result = java.util.ResourceBundle.getBundle("tourma/languages/language").getString("SKAVEN");
        }
        if (source.equals(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("SlannKey"))) {
            result = java.util.ResourceBundle.getBundle("tourma/languages/language").getString("SLANN");
        }
        if (source.equals(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("VampireKey"))) {
            result = java.util.ResourceBundle.getBundle("tourma/languages/language").getString("VAMPIRES");
        }
        return result;
    }

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

    private Tournament() {
        mParams = new Parameters();
        mRounds = new ArrayList<>();
        mCoachs = new ArrayList<>();
        mClans = new ArrayList<>();
        mTeams = new ArrayList<>();
        mGroups = new ArrayList<>();
        mPools = new ArrayList<>();
        mCategories = new ArrayList<>();

        final java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE); // NOI18N
        mClans.add(new Clan(bundle.getString("NoneKey")));
        mCategories.add(new Category(bundle.getString("NoneKey")));
        final Group group = new Group(bundle.getString("NoneKey"));
        mGroups.add(group);

        for (int i = 0; i < RosterType.getRostersNamesCount(); i++) {
            group.addRoster(RosterType.getRosterType(RosterType.getRostersName(i)));
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
    }
    
    /**
     * 
     * @param c 
     */
    public void removeClan(Clan c) {
        mClans.remove(c);
    }
    
    /**
     * 
     * @param c 
     */
    public void removeClan(int c) {
        mClans.remove(c);
    }
    
    /**
     * Clear the Clans array
     */
    public void clearClans() {
        mClans.clear();
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
    
    /**
     * 
     * @param c 
     */
    public void addCategory(Category c) {
        mCategories.add(c);
    }
    
    /**
     * 
     * @param c 
     */
    public void removeCategory(Category c) {
        mCategories.remove(c);
    }
    
    /**
     * 
     * @param c 
     */
    public void removeCategory(int c) {
        mCategories.remove(c);
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
    }
    
    /**
     * 
     * @param c 
     */
    public void removeTeam(Team c) {
        mTeams.remove(c);
    }
    
    /**
     * 
     * @param t
     * @return 
     */
    public boolean containsTeam(Team t)
    {
        return mTeams.contains(t);
    }
    
    /**
     * 
     * @param c 
     */
    public void removeTeam(int c) {
        mTeams.remove(c);
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
        if (getParams().isTeamTournament()) {
            for (Team c : mTeams) {
                counts.put(c.getClan(), counts.get(c.getClan()) + 1);
            }
        } else {
            for (Coach c : mCoachs) {
                counts.put(c.getClan(), counts.get(c.getClan()) + 1);
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

        final HashMap<Category, Integer> counts = new HashMap();
        for (Category mCategorie : mCategories) {
            counts.put(mCategorie, 0);
        }
        final ArrayList<Category> categories = new ArrayList<>();
        for (Coach c : mCoachs) {
            counts.put(c.getCategory(), counts.get(c.getCategory()) + 1);
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
     * @param input
     * @return
     */
    public Coach getCoach(final String input) {
        Coach result = null;
        for (Coach c : mCoachs) {
            if (c.getName().equals(input)) {
                result = c;
                break;
            }
        }
        return result;
    }

    /**
     *
     * @return
     */
    public Parameters getParams() {
        return mParams;
    }

    /**
     *
     * @return
     */
    @Override
    public boolean containsCoach(Coach c) {
        return mCoachs.contains(c);
    }
    
    /**
     *
     * @param i
     */
    public void removeCoach(Coach i) {
        mCoachs.remove(i);

    }
   /**
    * 
    * @param i 
    */
    @Override
    public void removeCoach(int i) {
         mCoachs.remove(i);

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
    public boolean containsGroups(Group g) {
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
    public void saveXML(final java.io.File file, final boolean withRanking) {
        final Element document = new Element(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("TOURNAMENT"));

        document.setAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("VERSION"), java.util.ResourceBundle.getBundle("tourma/languages/language").getString("3"));
        document.setAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("ROUNDROBIN"), Boolean.toString(isRoundRobin()));

        // Tounament data
        for (int i = 0; i
                < RosterType.getRostersNamesCount(); i++) {
            final Element ros = new Element(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("ROSTER"));
            ros.setAttribute(StringConstants.CS_NAME, RosterType.getRostersName(i));
            document.addContent(ros);
        }

        // Save parameters
        final Element params = getParams().getXMLElement();
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

        // Save rounds
        for (int i = 0; i < mRounds.size(); i++) {

            final Element round = mRounds.get(i).getXMLElement();

            if (withRanking) {

                // Build list of rankings
                final ArrayList<Ranking> rankings = new ArrayList<>();
                final boolean forPool = (mPools.size() > 0) && (!mRounds.get(i).isCup());
                rankings.add(
                        new Ranking(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("INDIVIDUAL"), java.util.ResourceBundle.getBundle("tourma/languages/language").getString("GENERAL"), java.util.ResourceBundle.getBundle("tourma/languages/language").getString(""), new MjtRankingIndiv(i, this.getParams().getRankingIndiv1(), this.getParams().getRankingIndiv2(), this.getParams().getRankingIndiv3(), this.getParams().getRankingIndiv4(), this.getParams().getRankingIndiv5(), mCoachs, false, false, forPool)));
                if (this.getParams().isTeamTournament()) {
                    rankings.add(new Ranking(StringConstants.CS_TEAM, java.util.ResourceBundle.getBundle("tourma/languages/language").getString("GENERAL"),
                            java.util.ResourceBundle.getBundle("tourma/languages/language").getString(""),
                            new MjtRankingTeam(this.getParams().isTeamVictoryOnly(), i,
                                    mTeams, false)));
                }
                if (getParams().isEnableClans()) {
                    rankings.add(new Ranking(StringConstants.CS_CLAN, java.util.ResourceBundle.getBundle("tourma/languages/language").getString("GENERAL"), java.util.ResourceBundle.getBundle("tourma/languages/language").getString(""), new MjtRankingClan(i, this.getParams().getRankingIndiv1(), this.getParams().getRankingIndiv2(), this.getParams().getRankingIndiv3(), this.getParams().getRankingIndiv4(), this.getParams().getRankingIndiv5(), this.getDisplayClans(), false)));

                }
                if (getParams().isGroupsEnable()) {
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
                        rankings.add(new Ranking(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("GROUP"), g.getName(), java.util.ResourceBundle.getBundle("tourma/languages/language").getString(""), new MjtRankingIndiv(i, this.getParams().getRankingIndiv1(), this.getParams().getRankingIndiv2(), this.getParams().getRankingIndiv3(), this.getParams().getRankingIndiv4(), this.getParams().getRankingIndiv5(), list, false, false, false)));
                    }
                }
                // Annex ranking
                for (int j = 0; j < getParams().getCriteriaCount(); j++) {
                    final Criteria criteria = getParams().getCriteria(j);
                    rankings.add(new Ranking(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("INDIVIDUAL"), criteria.getName(), java.util.ResourceBundle.getBundle("tourma/languages/language").getString("POSITIVE"), 
                            new MjtAnnexRankIndiv(i, criteria, Parameters.C_RANKING_SUBTYPE_POSITIVE, mCoachs, true, this.getParams().getRankingIndiv1(), this.getParams().getRankingIndiv2(), this.getParams().getRankingIndiv3(), this.getParams().getRankingIndiv4(), this.getParams().getRankingIndiv5(), getParams().isTeamTournament(), false)));
                    rankings.add(new Ranking(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("INDIVIDUAL"), criteria.getName(), java.util.ResourceBundle.getBundle("tourma/languages/language").getString("NEGATIVE"), new MjtAnnexRankIndiv(i, criteria, Parameters.C_RANKING_SUBTYPE_NEGATIVE, mCoachs, true, this.getParams().getRankingIndiv1(), this.getParams().getRankingIndiv2(), this.getParams().getRankingIndiv3(), this.getParams().getRankingIndiv4(), this.getParams().getRankingIndiv5(), getParams().isTeamTournament(), false)));

                    if (getParams().isTeamTournament()) {
                        rankings.add(new Ranking(StringConstants.CS_TEAM, criteria.getName(), java.util.ResourceBundle.getBundle("tourma/languages/language").getString("POSITIVE"), new MjtAnnexRankTeam(i, criteria, Parameters.C_RANKING_SUBTYPE_POSITIVE, mTeams, true, this.getParams().getRankingIndiv1(), this.getParams().getRankingIndiv2(), this.getParams().getRankingIndiv3(), this.getParams().getRankingIndiv4(), this.getParams().getRankingIndiv5(), false)));
                        rankings.add(new Ranking(StringConstants.CS_TEAM, criteria.getName(), java.util.ResourceBundle.getBundle("tourma/languages/language").getString("NEGATIVS"), new MjtAnnexRankTeam(i, criteria, Parameters.C_RANKING_SUBTYPE_NEGATIVE, mTeams ,true, this.getParams().getRankingIndiv1(), this.getParams().getRankingIndiv2(), this.getParams().getRankingIndiv3(), this.getParams().getRankingIndiv4(), this.getParams().getRankingIndiv5(), false)));
                    }

                    if (getParams().isEnableClans()) {
                        rankings.add(new Ranking(StringConstants.CS_CLAN, criteria.getName(), java.util.ResourceBundle.getBundle("tourma/languages/language").getString("POSITIVE"), new MjtAnnexRankClan(i, criteria, Parameters.C_RANKING_SUBTYPE_POSITIVE, mClans, true, this.getParams().getRankingIndiv1(), this.getParams().getRankingIndiv2(), this.getParams().getRankingIndiv3(), this.getParams().getRankingIndiv4(), this.getParams().getRankingIndiv5(), false)));
                        rankings.add(new Ranking(StringConstants.CS_CLAN, criteria.getName(), java.util.ResourceBundle.getBundle("tourma/languages/language").getString("NEGATIVE"), new MjtAnnexRankClan(i, criteria, Parameters.C_RANKING_SUBTYPE_NEGATIVE, mClans, true, this.getParams().getRankingIndiv1(), this.getParams().getRankingIndiv2(), this.getParams().getRankingIndiv3(), this.getParams().getRankingIndiv4(), this.getParams().getRankingIndiv5(), false)));
                    }
                }

                // Store rankings
                for (Ranking ranking : rankings) {
                    final Element rank = ranking.getXMLElement();
                    round.addContent(rank);
                }
            }

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
                     LOG.log(Level.INFO,e.getLocalizedMessage());
                }
            }

        }
    }

    /**
     *
     * @param round
     * @param withRoster
     * @param withNaf
     * @return
     */
    protected String generateCSVRanking(final int round, final boolean withRoster, final boolean withNaf) {
        final StringBuilder a = new StringBuilder(this.getParams().getTournamentName());
        a.append(";");
        a.append(this.getParams().getStringDate(new SimpleDateFormat(sbundle.getString("DD/MM/YYYY HH:MM:SS"), Locale.getDefault())));
        a.append(";");
        a.append(this.getParams().isTeamTournament());
        a.append("\n");
        a.append(";\n");

        if (this.getParams().isTeamTournament()) {
            final MjtRankingTeam rt = new MjtRankingTeam(getParams().isTeamVictoryOnly(), round - 1,
                    mTeams, false);

            for (int i = 0; i < rt.getRowCount(); i++) {
                final String team = (String) rt.getValueAt(i, 1);
                a.append(Integer.toString(i + 1));
                a.append(";");
                a.append(team);
                for (Team mTeam : mTeams) {
                    if (mTeam.getName().equals(team)) {
                        for (int k = 0; k < mTeam.getCoachCount(); k++) {
                            a.append(";");
                            a.append(mTeam.getCoach(k).getName());
                        }
                    }
                }
                a.append("\n");
            }

            a.append(";\n");
        }

        final boolean forPool = (mPools.size() > 0) && (!mRounds.get(round).isCup());
        final MjtRankingIndiv ri = new MjtRankingIndiv(round, getParams().getRankingIndiv1(), getParams().getRankingIndiv2(), getParams().getRankingIndiv3(), getParams().getRankingIndiv4(), getParams().getRankingIndiv5(),
                mCoachs, false, false, forPool);
        for (int i = 0; i < ri.getRowCount(); i++) {
            final String coach = (String) ri.getValueAt(i, 2);
            a.append(Integer.toString(i + 1));
            a.append(";");
            a.append(coach);
            for (Coach mCoach : mCoachs) {
                if (mCoach.getName().equals(coach)) {
                    if (withNaf) {
                        a.append(";");
                        a.append(Integer.toString(mCoach.getNaf()));
                    }
                    if (withRoster) {
                        a.append(";");
                        a.append(mCoach.getRoster().getName());
                    }
                }
            }
            a.append("\n");
        }
        return a.toString();
    }

    /**
     *
     * @param round
     * @return
     */
    public RenderedImage generateRankingQRCode(final int round) {
        RenderedImage image;
        final String s = generateCSVRanking(round, false, false);
        QRCode qrcode;
        try {
            qrcode = Encoder.encode(s, ErrorCorrectionLevel.H);

            final int magnify = 10; //The resolution of the QRCode 
            final byte[][] matrix = qrcode.getMatrix().getArray();
            final int size = qrcode.getMatrix().getWidth() * magnify;

            //Make the BufferedImage that are to hold the QRCode 
            final BufferedImage im = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
            im.createGraphics();
            final Graphics2D g = (Graphics2D) im.getGraphics();
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, size * magnify, size * magnify);

            //paint the image using the ByteMatrik 
            for (int h = 0; h < qrcode.getMatrix().getHeight(); h++) {
                for (int w = 0; w < qrcode.getMatrix().getWidth(); w++) {
                    //Find the colour of the dot 
                    if (matrix[h][w] == 0) {
                        g.setColor(Color.WHITE);
                    } else {
                        g.setColor(Color.BLACK);
                    }

                    //Paint the dot 
                    g.fillRect(h * magnify, w * magnify, magnify, magnify);
                }
            }
            image = im;

        } catch (WriterException e) {

            JOptionPane.showMessageDialog(MainFrame.getMainFrame(), e.getMessage());
            image = null;
        }
        return image;
    }

    /**
     *
     * @param file
     */
    public void exportFullFBB(final java.io.File file) {
        this.saveXML(file, true);
    }

    /**
     *
     * @param file
     */
    public void exportFBB(final java.io.File file) {
        PrintWriter writer = null;
        BufferedWriter bw = null;
        OutputStreamWriter fw = null;
        try {
            fw = new OutputStreamWriter(new FileOutputStream(file),Charset.defaultCharset());
            bw = new BufferedWriter(fw);
            writer = new PrintWriter(bw);
            final String s = generateCSVRanking(mRounds.size(), true, true);
            String s_tmp = s;
            while (s_tmp.length() > 0) {
                writer.print(s_tmp.substring(0, Math.min(255, s_tmp.length() - 1)));
                s_tmp = s_tmp.substring(Math.min(256, s_tmp.length()));
            }

            final RenderedImage im = generateRankingQRCode(mRounds.size());

            try {
                ImageIO.write(im, java.util.ResourceBundle.getBundle("tourma/languages/language").getString("PNG"), new File(file.getAbsoluteFile() + java.util.ResourceBundle.getBundle("tourma/languages/language").getString(".PNG")));
            } catch (IOException e) {
                JOptionPane.showMessageDialog(MainFrame.getMainFrame(), e.getMessage());
            }
            writer.close();

        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(MainFrame.getMainFrame(), e.getMessage());
        } finally {
            if (writer != null) {
                writer.close();
            }
            if (bw != null) {
                try {
                    bw.close();

                } catch (IOException e) {
                    LOG.log(Level.FINE, e.getLocalizedMessage());
                }
            }
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    LOG.log(Level.FINE,e.getLocalizedMessage());
                }
            }

        }
    }

    /**
     *
     * @param file
     */
    public void exportNAF(final java.io.File file) {
        final SimpleDateFormat format = new SimpleDateFormat(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("DD/MM/YYYY HH:MM:SS"), Locale.getDefault());

        Criteria critTd = null;
        Criteria critInj = null;

        for (int i = 0; i < Tournament.getTournament().getParams().getCriteriaCount(); i++) {
            final Criteria crit = Tournament.getTournament().getParams().getCriteria(i);
            if (i == 0) {
                critTd = crit;
            }
            if (i == 1) {
                critInj = crit;
            }
        }

        try {
            try (PrintWriter writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),Charset.defaultCharset())))) {
                writer.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
                writer.println("<nafReport xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' xmlns='http://www.bloodbowl.net' xsi:schemaLocation='http://www.bloodbowl.net ../../../test/naf.xsd'>");
                writer.println(java.text.MessageFormat.format(("<ORGANISER>{0}</ORGANISER>"), new Object[]{getParams().getTournamentOrga()}));
                writer.println(("<COACHES>"));
                for (Coach mCoach : mCoachs) {
                    if (mCoach.getNaf() > 0) {
                        writer.println(("<COACH>"));
                        writer.println(java.text.MessageFormat.format("<NAME>{0}</NAME>", new Object[]{mCoach.getName()}));
                        writer.println(java.text.MessageFormat.format("<NUMBER>{0}</NUMBER>", new Object[]{mCoach.getNaf()}));
                        writer.println(java.text.MessageFormat.format("<TEAM>{0}</TEAM>", new Object[]{getRosterTranslation(mCoach.getRoster().getName())}));
                        writer.println(("</COACH>"));
                    }
                }
                writer.println(("</COACHES>"));
                
                for (Round mRound : mRounds) {
                    for (int j = 0; j < mRound.getMatchsCount(); j++) {
                        if ((((Coach) mRound.getMatch(j).getCompetitor1()).getNaf() > 0) && (((Coach) mRound.getMatch(j).getCompetitor2()).getNaf() > 0)) {
                            writer.println(("<GAME>"));
                            final CoachMatch m = mRound.getCoachMatchs().get(j);
                            writer.println(java.text.MessageFormat.format("<TIMESTAMP>{0}</TIMESTAMP>", new Object[]{format.format(mRound.getHour())}));
                            Coach p;
                            if (m.getSubstitute1() == null) {
                                p = (Coach) m.getCompetitor1();
                            } else {
                                p = m.getSubstitute1().getSubstitute();
                            }
                            writer.println(("<PLAYERRECORD>"));
                            writer.println(java.text.MessageFormat.format(("<NAME>{0}</NAME>"), new Object[]{p.getName()}));
                            writer.println(java.text.MessageFormat.format(("<NUMBER>{0}</NUMBER>"), new Object[]{p.getNaf()}));
                            writer.println(java.text.MessageFormat.format(("<TEAMRATING>{0}</TEAMRATING>"), new Object[]{p.getRank()}));
                            writer.println(java.text.MessageFormat.format(("<TOUCHDOWNS>{0}</TOUCHDOWNS>"), new Object[]{m.getValue(critTd).getValue1()}));
                            writer.println(java.text.MessageFormat.format(("<BADLYHURT>{0}</BADLYHURT>"), new Object[]{m.getValue(critInj).getValue1()}));
                            if (m.getRoster1() != null) {
                                writer.println(java.text.MessageFormat.format(("<TEAM>{0}</TEAM>"), new Object[]{getRosterTranslation(m.getRoster1().getName())}));
                            }
                            writer.println(("<SERIOUSLYINJURED>0</SERIOUSLYINJURED>"));
                            writer.println(("<DEAD>0</DEAD>"));
                            writer.println(("<WINNINGS>0</WINNINGS>"));
                            writer.println(("</PLAYERRECORD>"));
                            if (m.getSubstitute2() == null) {
                                p = (Coach) m.getCompetitor2();
                            } else {
                                p = m.getSubstitute2().getSubstitute();
                            }
                            writer.println(("<PLAYERRECORD>"));
                            writer.println(java.text.MessageFormat.format(("<NAME>{0}</NAME>"), new Object[]{p.getName()}));
                            writer.println(java.text.MessageFormat.format(("<NUMBER>{0}</NUMBER>"), new Object[]{p.getNaf()}));
                            writer.println(java.text.MessageFormat.format(("<TEAMRATING>{0}</TEAMRATING>"), new Object[]{p.getRank()}));
                            writer.println(java.text.MessageFormat.format(("<TOUCHDOWNS>{0}</TOUCHDOWNS>"), new Object[]{m.getValue(critTd).getValue2()}));
                            writer.println(java.text.MessageFormat.format(("<BADLYHURT>{0}</BADLYHURT>"), new Object[]{m.getValue(critInj).getValue2()}));
                            if (m.getRoster2() != null) {
                                writer.println(java.text.MessageFormat.format(("<TEAM>{0}</TEAM>"), new Object[]{getRosterTranslation(m.getRoster2().getName())}));
                            }
                            writer.println(("<SERIOUSLYINJURED>0</SERIOUSLYINJURED>"));
                            writer.println(("<DEAD>0</DEAD>"));
                            writer.println(("<WINNINGS>0</WINNINGS>"));
                            writer.println(("</PLAYERRECORD>"));
                            writer.println(("<GATE>2</GATE>"));
                            writer.println(("</GAME>"));
                        }
                    }
                }
                writer.println(("</NAFREPORT>"));
            }

        } catch (IOException e) {
            LOG.log(Level.WARNING, e.getMessage());
        }

    }

    /**
     *
     * @param Root
     */
    protected void loadXMLv2(final Element Root) {
        final SimpleDateFormat format = new SimpleDateFormat(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("DD/MM/YYYY HH:MM:SS"), Locale.getDefault());
        /* Utilisateur */
        final Element params = Root.getChild(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("PARAMETERS"));
        final Criteria c1 = new Criteria(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("TOUCHDOWNS"));
        final Criteria c2 = new Criteria(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("SORTIES"));
        final Criteria c3 = new Criteria(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("FOULS"));
        final Criteria c4 = new Criteria(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("PASSES"));
        final Criteria c5 = new Criteria(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("INTERCEPTIONS"));

        try {
            if (params != null) {
                getParams().semTournamentOrga(params.getAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("ORGANIZER")).getValue());
                getParams().setTournamentName(params.getAttribute(StringConstants.CS_NAME).getValue());

                getParams().clearCiterias();
                c1.setPointsFor(params.getAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("BONUS_POS_TD")).getIntValue());
                c1.setPointsAgainst(params.getAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("BONUS_NEG_TD")).getIntValue());
                c2.setPointsFor(params.getAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("BONUS_POS_SOR")).getIntValue());
                c2.setPointsAgainst(params.getAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("BONUS_NEG_SOR")).getIntValue());
                c3.setPointsFor(params.getAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("BONUS_POS_FOUL")).getIntValue());
                c3.setPointsAgainst(params.getAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("BONUS_NEG_FOUL")).getIntValue());

                try {
                    c4.setPointsFor(params.getAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("BONUS_POS_PAS")).getIntValue());
                    c4.setPointsAgainst(params.getAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("BONUS_NEG_PAS")).getIntValue());

                    c5.setPointsFor(params.getAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("BONUS_POS_INT")).getIntValue());
                    c5.setPointsAgainst(params.getAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("BONUS_NEG_INT")).getIntValue());

                } catch (NullPointerException npe) {
                }

                getParams().addCriteria(c1);
                getParams().addCriteria(c2);
                getParams().addCriteria(c3);
                getParams().addCriteria(c4);
                getParams().addCriteria(c5);

                getParams().setPointsIndivVictory(params.getAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("VICTORY")).getIntValue());
                getParams().setPointsIndivLargeVictory(params.getAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("LARGE_VICTORY")).getIntValue());
                getParams().setPointsIndivDraw(params.getAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("DRAW")).getIntValue());
                getParams().setPointsIndivLost(params.getAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("LOST")).getIntValue());

                getParams().setPointsIndivLittleLost(params.getAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("LITTLE_LOST")).getIntValue());
                getParams().setRankingIndiv1((params.getAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("RANK1")).getIntValue()));
                getParams().setRankingIndiv2((params.getAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("RANK2")).getIntValue()));
                getParams().setRankingIndiv3((params.getAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("RANK3")).getIntValue()));
                getParams().setRankingIndiv4((params.getAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("RANK4")).getIntValue()));
                getParams().setRankingIndiv5((params.getAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("RANK5")).getIntValue()));

                try {
                    getParams().setGapLargeVictory(params.getAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("LARGE_VICTORY_GAP")).getIntValue());
                    getParams().setGapLittleLost(params.getAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("LITTLE_LOST_GAP")).getIntValue());
                    getParams().setPlace(params.getAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("PLACE")).getValue());
                    getParams().setTeamTournament(params.getAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("BYTEAM")).getBooleanValue());
                    getParams().setTeamMatesNumber(params.getAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("TEAMMATES")).getIntValue());
                    int val = params.getAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("TEAMPAIRING")).getIntValue();
                    switch (val) {
                        case 0:
                            getParams().setTeamPairing(ETeamPairing.INDIVIDUAL_PAIRING);
                            break;
                        case 1:
                        default:
                            getParams().setTeamPairing(ETeamPairing.TEAM_PAIRING);
                            break;
                            
                    }

                    val = params.getAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("TEAMINDIVPAIRING")).getIntValue();
                    switch (val) {
                        case 0:
                            getParams().setTeamIndivPairing(EIndivPairing.RANKING);
                            break;
                        case 1:
                            getParams().setTeamIndivPairing(EIndivPairing.FREE);
                            break;
                        case 2:
                            getParams().setTeamIndivPairing(EIndivPairing.RANDOM);
                            break;
                        case 3:
                            getParams().setTeamIndivPairing(EIndivPairing.NAF);
                            break;
                        default:
                            break;
                    }
                    getParams().setPointsTeamVictoryBonus(params.getAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("TEAMVICTORYPOINTS")).getIntValue());
                    getParams().setTeamVictoryOnly(params.getAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("TEAMVICTORYONLY")).getBooleanValue());

                    try {
                        getParams().setDate(format.parse(params.getAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("DATE")).getValue()));

                    } catch (ParseException pe) {
                    }

                } catch (NullPointerException ne) {
                    getParams().setGapLargeVictory(3);
                    getParams().setGapLittleLost(1);
                    getParams().setPlace(java.util.ResourceBundle.getBundle("tourma/languages/language").getString(""));
                    getParams().setTeamTournament(false);
                    getParams().setTeamMatesNumber(6);
                    getParams().setTeamPairing(ETeamPairing.TEAM_PAIRING);
                    getParams().setTeamIndivPairing(EIndivPairing.RANKING);

                }
                try {

                    c1.setPointsTeamFor(params.getAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("BONUS_POS_TD_TEAM")).getIntValue());
                    c1.setPointsTeamAgainst(params.getAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("BONUS_NEG_TD_TEAM")).getIntValue());
                    c2.setPointsTeamFor(params.getAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("BONUS_POS_SOR_TEAM")).getIntValue());
                    c2.setPointsTeamAgainst(params.getAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("BONUS_NEG_SOR_TEAM")).getIntValue());
                    c3.setPointsTeamFor(params.getAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("BONUS_POS_FOUL_TEAM")).getIntValue());
                    c3.setPointsTeamAgainst(params.getAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("BONUS_NEG_FOUL_TEAM")).getIntValue());

                    try {
                        c4.setPointsTeamFor(params.getAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("BONUS_POS_PAS_TEAM")).getIntValue());
                        c4.setPointsTeamAgainst(params.getAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("BONUS_NEG_PAS_TEAM")).getIntValue());

                        c5.setPointsTeamFor(params.getAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("BONUS_POS_INT_TEAM")).getIntValue());
                        c5.setPointsTeamAgainst(params.getAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("BONUS_NEG_INT_TEAM")).getIntValue());

                    } catch (NullPointerException npe) {
                    }

                    getParams().setPointsTeamVictory(params.getAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("VICTORY_TEAM")).getIntValue());
                    getParams().setPointsTeamDraw(params.getAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("DRAW_TEAM")).getIntValue());
                    getParams().setPointsTeamLost(params.getAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("LOST_TEAM")).getIntValue());
                    getParams().setRankingTeam1((params.getAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("RANK1_TEAM")).getIntValue()));
                    getParams().setRankingTeam2((params.getAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("RANK2_TEAM")).getIntValue()));
                    getParams().setRankingTeam3((params.getAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("RANK3_TEAM")).getIntValue()));
                    getParams().setRankingTeam4((params.getAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("RANK4_TEAM")).getIntValue()));
                    getParams().setRankingTeam5((params.getAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("RANK5_TEAM")).getIntValue()));

                } catch (NullPointerException ne2) {
                }
                try {
                    getParams().setEnableClans(params.getAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("ACTVATECLANS")).getBooleanValue());
                    getParams().setAvoidClansFirstMatch(params.getAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("AVOIDFIRSTMATCH")).getBooleanValue());
                    getParams().setAvoidClansMatch(params.getAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("AVOIDMATCH")).getBooleanValue());
                    getParams().setTeamMatesClansNumber(params.getAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("CLANTEAMMATESNUMBER")).getIntValue());

                } catch (NullPointerException ne3) {
                }
            }

            final List<Element> clans = Root.getChildren(StringConstants.CS_CLAN);
            final Iterator<Element> h = clans.iterator();
            mClans.clear();
            Clan.newClanMap();

            while (h.hasNext()) {
                final Element clan = h.next();
                final Clan c = new Clan(clan.getAttributeValue(StringConstants.CS_NAME));
                mClans.add(c);
                Clan.putClan(c.getName(), c);
            }

            final List<Element> coachs = Root.getChildren(StringConstants.CS_COACH);
            final Iterator<Element> i = coachs.iterator();
            mCoachs.clear();
            final HashMap<String, Coach> map = new HashMap<>();

            while (i.hasNext()) {
                final Element coach = i.next();
                final Coach c = new Coach();
                c.setName(coach.getAttributeValue(StringConstants.CS_NAME));
                c.setTeam(coach.getAttributeValue(StringConstants.CS_TEAM));
                c.setRoster(RosterType.getRosterType(coach.getAttributeValue(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("ROSTER"))));
                c.setNaf(coach.getAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("NAF")).getIntValue());
                c.setRank(coach.getAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("RANK")).getIntValue());
                c.setClan(Clan.getClan(coach.getAttributeValue(StringConstants.CS_CLAN)));

                if (c.getClan() == null) {
                    if (mClans.isEmpty()) {
                        final java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE); // NOI18N
                        mClans.add(new Clan(bundle.getString("NoneKey")));
                    }
                    c.setClan(mClans.get(0));

                }
                mCoachs.add(c);
                map.put(c.getName(), c);
            }

            final List<Element> teams = Root.getChildren(StringConstants.CS_TEAM);
            final Iterator<Element> l = teams.iterator();
            mTeams.clear();

            while (l.hasNext()) {
                final Element team = l.next();
                final Team t = new Team();
                t.setName(team.getAttributeValue(StringConstants.CS_NAME));
                final List<Element> coachs2 = team.getChildren(StringConstants.CS_COACH);
                final Iterator<Element> m = coachs2.iterator();
                t.clearCoachs();

                while (m.hasNext()) {
                    final Element coach = m.next();
                    final Coach c = map.get(coach.getAttribute(StringConstants.CS_NAME).getValue());
                    c.setTeamMates(t);
                    t.addCoach(c);

                }
                mTeams.add(t);

            }

            final List<Element> rounds = Root.getChildren(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("ROUND"));
            final Iterator<Element> j = rounds.iterator();
            mRounds.clear();

            while (j.hasNext()) {
                final Element round = j.next();
                final Round r = new Round();
                final String date = round.getAttributeValue(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("DATE"));
                    r.setHour(date);
                final List<Element> matchs = round.getChildren(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("MATCH"));
                final Iterator<Element> k = matchs.iterator();
                r.clearMatchs();
                while (k.hasNext()) {
                    final Element match = k.next();
                    final CoachMatch m = new CoachMatch(r);
                    final String coach1 = match.getAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("COACH1")).getValue();
                    final String coach2 = match.getAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("COACH2")).getValue();
                    m.setCompetitor1(map.get(coach1));
                    m.setCompetitor2(map.get(coach2));
                    for (Coach mCoach : mCoachs) {
                        if (c1.getName().equals(mCoach.getName())) {
                            m.setCompetitor1(mCoach);
                            break;
                        }
                    }
                    for (Coach mCoach : mCoachs) {
                        if (c2.getName().equals(mCoach.getName())) {
                            m.setCompetitor2(mCoach);
                            break;
                        }
                    }
                    final Value v1 = new Value(c1);
                    v1.setValue1(match.getAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("TD1")).getIntValue());
                    v1.setValue2(match.getAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("TD2")).getIntValue());
                    m.putValue(c1, v1);

                    final Value v2 = new Value(c2);
                    v2.setValue1(match.getAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("SOR1")).getIntValue());
                    v2.setValue2(match.getAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("SOR2")).getIntValue());
                    m.putValue(c2, v2);

                    final Value v3 = new Value(c3);
                    v3.setValue1(match.getAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("FOUL1")).getIntValue());
                    v3.setValue2(match.getAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("FOUL2")).getIntValue());
                    m.putValue(c3, v3);

                    final Value v4 = new Value(c4);
                    final Value v5 = new Value(c5);

                    try {
                        v4.setValue1(match.getAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("PAS1")).getIntValue());
                        v4.setValue2(match.getAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("PAS2")).getIntValue());

                        v5.setValue1(match.getAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("INT1")).getIntValue());
                        v5.setValue2(match.getAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("INT2")).getIntValue());

                    } catch (NullPointerException npe) {
                    }
                    m.putValue(c4, v4);
                    m.putValue(c5, v5);

                    m.getCompetitor1().addMatch(m);
                    m.getCompetitor2().addMatch(m);

                    r.addMatch(m);

                }

                mRounds.add(r);

            }
        } catch (DataConversionException dce) {
            JOptionPane.showMessageDialog(MainFrame.getMainFrame(), dce.getLocalizedMessage());

        }
    }

    /**
     *
     * @param racine
     */
    protected void loadXMLv3(final Element racine) {
        try {
            setRoundRobin(Boolean.parseBoolean(racine.getAttributeValue(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("ROUNDROBIN"))));
        } catch (Exception e) {
            setRoundRobin(false);
        }

        try {
            final List<Element> ros = racine.getChildren(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("ROSTER"));
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
                    RosterType.initCollection();
                }
            } else {
                RosterType.initCollection();
            }
        } catch (NullPointerException ne) {
            RosterType.initCollection();
        }

        /* Parameters */
        final Element params = racine.getChild(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("PARAMETERS"));
        getParams().setXMLElement(params);

        /* Groups */
        try {
            final List<Element> groups = racine.getChildren(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("GROUP"));
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
            final Element coach =  i.next();
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
        final List<Element> pools = racine.getChildren(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("POOL"));
        final Iterator<Element> p = pools.iterator();
        mPools.clear();
        while (p.hasNext()) {
            final Element pool = p.next();
            final Pool po = new Pool();
            po.setXMLElement(pool);
            mPools.add(po);
        }

        /* Rounds */
        List<Element> rounds = racine.getChildren(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("ROUND"));
        if (rounds.isEmpty()) {
            rounds = racine.getChildren("Ronde");
        }
        final Iterator<Element> j = rounds.iterator();
        mRounds.clear();

        while (j.hasNext()) {
            final Element round = j.next();
            final Round r = new Round();
            r.setXMLElement(round);
            mRounds.add(r);
        }
    }

    /**
     *
     * @param file
     */
    public void loadXML(final java.io.File file) {
        final SAXBuilder sxb = new SAXBuilder();

        try {
            final org.jdom2.Document document = sxb.build(file);
            final Element racine = document.getRootElement();

            try {
                final String version = racine.getAttributeValue(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("VERSION"));
                if (Integer.parseInt(version) == 3) {
                    loadXMLv3(racine);

                }
            } catch (NumberFormatException npe) {
                loadXMLv2(racine);
            }

        } catch (JDOMException e) {
            JOptionPane.showMessageDialog(MainFrame.getMainFrame(), e.getLocalizedMessage());

        } catch (IOException e) {
            JOptionPane.showMessageDialog(MainFrame.getMainFrame(), e.getMessage());
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
    public void addPool(Pool p)
    {
        mPools.add(p);
    }
    
    /**
     *  Clear the Pool array
     */
    public void clearPools()
    {
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
     * @return the mCoachs
     */
    @Override
    public int getCoachCount() {
        return mCoachs.size();
    }

  
    /**
     * @param mParams the mParams to set
     */
    public void setParams(Parameters mParams) {
        this.mParams = mParams;
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
    }
    
    /**
     * 
     * @param r
     * @return 
     */
    public int indexOfRound(Round r)
    {
        return mRounds.indexOf(r);
    }
    
    /**
     * 
     * @param r 
     */
    public void removeRound(Round r)
    {
        mRounds.remove(r);
    }
    
    /**
     * 
     * @param r 
     */
    public void removeRound(int r)
    {
        mRounds.remove(r);
    }
}
