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
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import org.jdom.DataConversionException;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import tourma.tableModel.mjtAnnexRankClan;
import tourma.tableModel.mjtAnnexRankIndiv;
import tourma.tableModel.mjtAnnexRankTeam;

import tourma.tableModel.mjtRankingClan;
import tourma.tableModel.mjtRankingIndiv;
import tourma.tableModel.mjtRankingTeam;
import tourma.utility.StringConstants;
import tourma.MainFrame;

/**
 *
 * @author Frederic Berger
 */
public class Tournament {

    protected ArrayList<Round> mRounds;
    protected ArrayList<Coach> mCoachs;
    protected ArrayList<Team> mTeams;
    protected ArrayList<Pool> mPools;
    protected Parameters mParams;
    protected static Tournament mSingleton;
    public boolean mRoundRobin = false;
    /**
     * Clans used in the tournement
     */
    protected ArrayList<Clan> mClans;
    /**
     * mRostersNames Groups
     */
    protected ArrayList<Group> mGroups;

    private Tournament() {
        mParams = new Parameters();
        mRounds = new ArrayList<Round>();
        mCoachs = new ArrayList<Coach>();
        mClans = new ArrayList<Clan>();
        mTeams = new ArrayList();
        mGroups = new ArrayList<Group>();
        mPools = new ArrayList<Pool>();

        final java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE); // NOI18N
        mClans.add(new Clan(bundle.getString("NoneKey")));
        final Group group = new Group(bundle.getString("NoneKey"));
        mGroups.add(group);

        for (int i = 0; i < RosterType.mRostersNames.size(); i++) {
            group.mRosters.add(new RosterType(RosterType.mRostersNames.get(i)));
        }
    }

    public static Tournament resetTournament() {
        mSingleton = new Tournament();
        return mSingleton;
    }

    public static Tournament getTournament() {
        if (mSingleton == null) {
            mSingleton = new Tournament();
        }
        return mSingleton;
    }

    public ArrayList<Clan> getClans() {
        return mClans;
    }

    public ArrayList<Clan> getDisplayClans() {

        // Remove clans which do not have members
        final HashMap<Clan, Integer> counts = new HashMap();
        for (int i = 0; i < mClans.size(); i++) {
            counts.put(mClans.get(i), 0);
        }
        final ArrayList<Clan> clans = new ArrayList<Clan>();
        for (int i = 0; i < mCoachs.size(); i++) {
            final Coach c = mCoachs.get(i);
            counts.put(c.mClan, counts.get(c.mClan) + 1);
        }

        for (int i = 0; i < mClans.size(); i++) {
            if (counts.get(mClans.get(i)) > 0) {
                clans.add(mClans.get(i));
            }

        }

        return clans;
    }

    public Coach getCoach(final String input) {
        Coach result = null;
        for (int i = 0; i < mCoachs.size(); i++) {
            if (mCoachs.get(i).equals(input)) {
                result = mCoachs.get(i);
                break;
            }
        }
        return result;
    }

    public Parameters getParams() {
        return mParams;




    }

    public ArrayList<Coach> getCoachs() {
        return mCoachs;




    }

    public ArrayList<Group> getGroups() {
        return mGroups;
    }

    public ArrayList<Team> getTeams() {
        return mTeams;
    }

    public ArrayList<Round> getRounds() {
        return mRounds;
    }

    public void saveXML(final java.io.File file) {
        this.saveXML(file, false);
    }

    /*    public ArrayList<Group> getGroups() {
     return mGroups;
     }*/
    public void saveXML(final java.io.File file, final boolean withRanking) {
        final Element document = new Element("Tournament");

        document.setAttribute("Version", "3");
        document.setAttribute("RoundRobin", Boolean.toString(mRoundRobin));

        // Tounament data
        for (int i = 0; i
                < RosterType.mRostersNames.size(); i++) {
            final Element ros = new Element("Roster");
            ros.setAttribute(StringConstants.CS_NAME, RosterType.mRostersNames.get(i));
            document.addContent(ros);
        }

        // Save parameters
        final Element params = mParams.getXMLElement();
        document.addContent(params);

        // Save Clans
        for (int i = 0; i < mClans.size(); i++) {
            final Element clan = mClans.get(i).getXMLElement();
            document.addContent(clan);
        }

        // Save roster groups
        for (int i = 0; i < mGroups.size(); i++) {
            final Element group = mGroups.get(i).getXMLElement();
            document.addContent(group);
        }

        // Save Pool 
        for (int i = 0; i < mPools.size(); i++) {
            final Element pool = mPools.get(i).getXMLElement();
            document.addContent(pool);
        }

        // Save coachs
        for (int i = 0; i < mCoachs.size(); i++) {
            final Element coach = mCoachs.get(i).getXMLElement();
            document.addContent(coach);
        }

        // Save teams
        for (int i = 0; i
                < mTeams.size(); i++) {
            final Element team = mTeams.get(i).getXMLElement();
            document.addContent(team);
        }

        // Save rounds
        for (int i = 0; i < mRounds.size(); i++) {

            final Element round = mRounds.get(i).getXMLElement();

            if (withRanking) {

                // Build list of rankings
                final ArrayList<Ranking> rankings = new ArrayList<Ranking>();
                rankings.add(new Ranking("Individual", "general", "", new mjtRankingIndiv(i, this.getParams().mRankingIndiv1, this.getParams().mRankingIndiv2, this.getParams().mRankingIndiv3, this.getParams().mRankingIndiv4, this.getParams().mRankingIndiv5, this.getCoachs(), false, false)));
                if (this.getParams().mTeamTournament) {
                    rankings.add(new Ranking(StringConstants.CS_TEAM, "general", "", new mjtRankingTeam(this.getParams().mTeamVictoryOnly, i, this.getParams().mRankingTeam1, this.getParams().mRankingTeam2, this.getParams().mRankingTeam3, this.getParams().mRankingTeam4, this.getParams().mRankingTeam5, this.getTeams(), false)));
                }
                if (mParams.mEnableClans) {
                    rankings.add(new Ranking(StringConstants.CS_CLAN, "general", "", new mjtRankingClan(i, this.getParams().mRankingIndiv1, this.getParams().mRankingIndiv2, this.getParams().mRankingIndiv3, this.getParams().mRankingIndiv4, this.getParams().mRankingIndiv5, this.getDisplayClans(), false)));

                }
                if (mParams.mGroupsEnable) {
                    for (int j = 0; j < mGroups.size(); j++) {
                        final Group g = mGroups.get(j);
                        final ArrayList<Coach> ArrayList = new ArrayList<Coach>();
                        for (int k = 0; k < this.getCoachs().size(); k++) {
                            final Coach c = this.getCoachs().get(k);
                            for (int l = 0; l < g.mRosters.size(); l++) {
                                if (g.mRosters.get(l).mName.equals(c.mRoster.mName)) {
                                    ArrayList.add(c);
                                    break;
                                }
                            }
                        }
                        rankings.add(new Ranking("Group", g.mName, "", new mjtRankingIndiv(i, this.getParams().mRankingIndiv1, this.getParams().mRankingIndiv2, this.getParams().mRankingIndiv3, this.getParams().mRankingIndiv4, this.getParams().mRankingIndiv5, ArrayList, false, false)));
                    }
                }
                // Annex ranking
                for (int j = 0; j < mParams.mCriterias.size(); j++) {
                    final Criteria criteria = mParams.mCriterias.get(j);
                    rankings.add(new Ranking("Individual", criteria.mName, "positive", new mjtAnnexRankIndiv(i, criteria, Parameters.C_RANKING_SUBTYPE_POSITIVE, getCoachs(), true, this.getParams().mRankingIndiv1, this.getParams().mRankingIndiv2, this.getParams().mRankingIndiv3, this.getParams().mRankingIndiv4, this.getParams().mRankingIndiv5, mParams.mTeamTournament, false)));
                    rankings.add(new Ranking("Individual", criteria.mName, "negative", new mjtAnnexRankIndiv(i, criteria, Parameters.C_RANKING_SUBTYPE_NEGATIVE, getCoachs(), true, this.getParams().mRankingIndiv1, this.getParams().mRankingIndiv2, this.getParams().mRankingIndiv3, this.getParams().mRankingIndiv4, this.getParams().mRankingIndiv5, mParams.mTeamTournament, false)));

                    if (mParams.mTeamTournament) {
                        rankings.add(new Ranking(StringConstants.CS_TEAM, criteria.mName, "positive", new mjtAnnexRankTeam(i, criteria, Parameters.C_RANKING_SUBTYPE_POSITIVE, getTeams(), true, this.getParams().mRankingIndiv1, this.getParams().mRankingIndiv2, this.getParams().mRankingIndiv3, this.getParams().mRankingIndiv4, this.getParams().mRankingIndiv5, false)));
                        rankings.add(new Ranking(StringConstants.CS_TEAM, criteria.mName, "negativs", new mjtAnnexRankTeam(i, criteria, Parameters.C_RANKING_SUBTYPE_NEGATIVE, getTeams(), true, this.getParams().mRankingIndiv1, this.getParams().mRankingIndiv2, this.getParams().mRankingIndiv3, this.getParams().mRankingIndiv4, this.getParams().mRankingIndiv5, false)));
                    }

                    if (mParams.mEnableClans) {
                        rankings.add(new Ranking(StringConstants.CS_CLAN, criteria.mName, "positive", new mjtAnnexRankClan(i, criteria, Parameters.C_RANKING_SUBTYPE_POSITIVE, getClans(), true, this.getParams().mRankingIndiv1, this.getParams().mRankingIndiv2, this.getParams().mRankingIndiv3, this.getParams().mRankingIndiv4, this.getParams().mRankingIndiv5, false)));
                        rankings.add(new Ranking(StringConstants.CS_CLAN, criteria.mName, "negative", new mjtAnnexRankClan(i, criteria, Parameters.C_RANKING_SUBTYPE_NEGATIVE, getClans(), true, this.getParams().mRankingIndiv1, this.getParams().mRankingIndiv2, this.getParams().mRankingIndiv3, this.getParams().mRankingIndiv4, this.getParams().mRankingIndiv5, false)));
                    }
                }

                // Store rankings
                for (int j = 0; j < rankings.size(); j++) {
                    final Element rank = rankings.get(j).getXMLElement();
                    round.addContent(rank);
                }
            }

            document.addContent(round);
        }

        try {
            final XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
            final FileOutputStream os = new FileOutputStream(file);
            sortie.output(document, os);
            os.close();


        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(MainFrame.getMainFrame(), e.getMessage());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(MainFrame.getMainFrame(), e.getMessage());
        }
    }

    private String getRosterTranslation(final String source) {
        String result = "Unknown";
        if (source.equals(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("AmazonKey"))) {
            result = "Amazons";
        }
        if (source.equals(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("UnderworldKey"))) {
            result = "Underworld";
        }
        if (source.equals(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("ChaosKey"))) {
            result = "Chaos";
        }
        if (source.equals(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("ElfKey"))) {
            result = "Elves";
        }
        if (source.equals(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("WoodElfKey"))) {
            result = "Wood Elves";
        }
        if (source.equals(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("DarkElfKey"))) {
            result = "Dark Elves";
        }
        if (source.equals(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("GoblinKey"))) {
            result = "Goblins";
        }
        if (source.equals(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("HalflingKey"))) {
            result = "Halflings";
        }
        if (source.equals(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("HighElfKey"))) {
            result = "High Elves";
        }
        if (source.equals(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("LizardmenKey"))) {
            result = "Lizardmen";
        }
        if (source.equals(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("HumanKey"))) {
            result = "Humans";
        }
        if (source.equals(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("KhemriKey"))) {
            result = "Khemri";
        }
        if (source.equals(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("UndeadKey"))) {
            result = "Undead";
        }
        if (source.equals(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("DwarfKey"))) {
            result = "Dwarves";
        }
        if (source.equals(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("ChaosDwarfKey"))) {
            result = "Chaos Dwarves";
        }
        if (source.equals(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("NecromanticKey"))) {
            result = "Necromantic";
        }
        if (source.equals(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("NorseKey"))) {
            result = "Norse";
        }
        if (source.equals(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("NurgleKey"))) {
            result = "Nurgle's Rotters";
        }
        if (source.equals(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("OgreKey"))) {
            result = "Ogres";
        }
        if (source.equals(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("OrcKey"))) {
            result = "Orc";
        }
        if (source.equals(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("ChaosPactKey"))) {
            result = "Chaos Pact";
        }
        if (source.equals(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("SkavenKey"))) {
            result = "Skaven";
        }
        if (source.equals(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("SlannKey"))) {
            result = "Slann";
        }
        if (source.equals(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("VampireKey"))) {
            result = "Vampires";
        }
        return result;
    }

    protected String generateCSVRanking(final int round, final boolean withRoster, final boolean withNaf) {
        final StringBuilder a = new StringBuilder(this.mParams.mTournamentName);
        a.append(";");
        a.append(this.mParams.mDate.toString());
        a.append(";");
        a.append(this.mParams.mTeamTournament);
        a.append("\n");
        a.append(";\n");

        if (this.mParams.mTeamTournament) {
            final mjtRankingTeam rt = new mjtRankingTeam(true, round - 1,
                    mParams.mRankingTeam1,
                    mParams.mRankingTeam2,
                    mParams.mRankingTeam3,
                    mParams.mRankingTeam4,
                    mParams.mRankingTeam5,
                    mTeams, false);

            for (int i = 0; i < rt.getRowCount(); i++) {
                final String team = (String) rt.getValueAt(i, 1);
                a.append(Integer.toString(i + 1));
                a.append(";");
                a.append(team);
                for (int j = 0; j < mTeams.size(); j++) {
                    if (mTeams.get(j).mName.equals(team)) {
                        for (int k = 0; k < mTeams.get(j).mCoachs.size(); k++) {
                            a.append(";");
                            a.append(mTeams.get(j).mCoachs.get(k).mName);
                        }
                    }
                }
                a.append("\n");
            }

            a.append(";\n");
        }

        final mjtRankingIndiv ri = new mjtRankingIndiv(round,
                mParams.mRankingIndiv1,
                mParams.mRankingIndiv2,
                mParams.mRankingIndiv3,
                mParams.mRankingIndiv4,
                mParams.mRankingIndiv5,
                mCoachs, false, false);
        for (int i = 0; i < ri.getRowCount(); i++) {
            final String coach = (String) ri.getValueAt(i, 2);
            a.append(Integer.toString(i + 1));
            a.append(";");
            a.append(coach);
            for (int j = 0; j < mCoachs.size(); j++) {
                if (mCoachs.get(j).mName.equals(coach)) {
                    if (withNaf) {
                        a.append(";");
                        a.append(Integer.toString(mCoachs.get(j).mNaf));
                    }
                    if (withRoster) {
                        a.append(";");
                        a.append(mCoachs.get(j).mRoster.mName);                        
                    }
                }
            }
            a.append("\n");
        }
        return a.toString();
    }

    public RenderedImage generateRankingQRCode(final int round) {
        RenderedImage image = null;
        final String s = generateCSVRanking(round, false, false);
        QRCode qrcode = null;
        try {
            qrcode = Encoder.encode(s, ErrorCorrectionLevel.H);

            final int magnify = 10; //The resolution of the QRCode 
            final byte[][] matrix = qrcode.getMatrix().getArray();
            final int size = qrcode.getMatrix().getWidth() * magnify;

            //Make the BufferedImage that are to hold the QRCode 
            final BufferedImage im = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
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

    public void exportFullFBB(final java.io.File file) {
        this.saveXML(file, true);
    }

    public void exportFBB(final java.io.File file) {
        try {
            final PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(file)));
            final String s = generateCSVRanking(mRounds.size(), true, true);
            String s_tmp = s;
            while (s_tmp.length() > 0) {
                writer.print(s_tmp.substring(0, Math.min(255, s_tmp.length() - 1)));
                s_tmp = s_tmp.substring(Math.min(256, s_tmp.length()));
            }

            writer.close();

            final RenderedImage im = generateRankingQRCode(mRounds.size());

            try {
                ImageIO.write(im, "png", new File(file.getAbsoluteFile() + ".png"));
            } catch (IOException e) {
                JOptionPane.showMessageDialog(MainFrame.getMainFrame(), e.getMessage());
            }

            /*writer.println(this.mParams.mTournamentName + ";" + this.mParams.mDate.toString() + ";" + this.mParams.mTeamTournament);
             writer.println(";");
             if (this.mParams.mTeamTournament) {
             mjtRankingTeam rt = new mjtRankingTeam(true, this.mRounds.size() - 1,
             mParams.mRankingTeam1,
             mParams.mRankingTeam2,
             mParams.mRankingTeam3,
             mParams.mRankingTeam4,
             mParams.mRankingTeam5,
             mTeams);

             for (int i = 0; i < rt.getRowCount(); i++) {
             String team = (String) rt.getValueAt(i, 1);
             writer.print((i + 1) + ";" + team);
             for (int j = 0; j < mTeams.size(); j++) {
             if (mTeams.get(j).mName.equals(team)) {
             for (int k = 0; k < mTeams.get(j).mCoachs.size(); k++) {
             writer.print(";" + mTeams.get(j).mCoachs.get(k).mName);
             }
             }
             }
             writer.print("\n");
             }

             writer.println(";");
             }

             mjtRankingIndiv ri = new mjtRankingIndiv(mRounds.size(),
             mParams.mRankingIndiv1,
             mParams.mRankingIndiv2,
             mParams.mRankingIndiv3,
             mParams.mRankingIndiv4,
             mParams.mRankingIndiv5,
             mCoachs, false);
             for (int i = 0; i < ri.getRowCount(); i++) {
             String coach = (String) ri.getValueAt(i, 2);
             writer.print((i + 1) + ";" + coach);
             for (int j = 0; j < mCoachs.size(); j++) {
             if (mCoachs.get(j).mName.equals(coach)) {
             writer.print(";" + mCoachs.get(j).mNaf);
             writer.print(";" + mCoachs.get(j).mRoster.mName);
             }
             }
             writer.print("\n");
             }*/

        } catch (IOException e) {
            JOptionPane.showMessageDialog(MainFrame.getMainFrame(), e.getMessage());
        }
    }

    public void exportResults(final java.io.File file) {
        final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:MM:SS", Locale.getDefault());

        Criteria critTd = null;
        Criteria critInj = null;

        for (int i = 0; i < Tournament.getTournament().mParams.mCriterias.size(); i++) {
            final Criteria crit = Tournament.getTournament().mParams.mCriterias.get(i);
            if (i == 0) {
                critTd = crit;
            }
            if (i == 1) {
                critInj = crit;
            }
        }

        try {
            final PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(file)));

            writer.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            writer.println("<nafReport xmlns='http://www.bloodbowl.net' xsi:schemaLocation='http://www.bloodbowl.net ../../../test/naf.xsd'>");
            writer.println("<organiser>" + mParams.mTournamentOrga + "</organiser>");
            writer.println("<coaches>");
            for (int i = 0; i < mCoachs.size(); i++) {
                if (mCoachs.get(i).mNaf > 0) {
                    writer.println("<coach>");
                    writer.println("<name>" + mCoachs.get(i).mName + "</name>");
                    writer.println("<number>" + mCoachs.get(i).mNaf + "</number>");
                    writer.println("<team>" + getRosterTranslation(mCoachs.get(i).mRoster.mName) + "</team>");
                    writer.println("</coach>");
                }
            }
            writer.println("</coaches>");

            for (int i = 0; i
                    < mRounds.size(); i++) {
                for (int j = 0; j
                        < mRounds.get(i).mMatchs.size(); j++) {
                    if ((mRounds.get(i).mMatchs.get(j).mCoach1.mNaf > 0)
                            && (mRounds.get(i).mMatchs.get(j).mCoach2.mNaf > 0)) {
                        writer.println("<game>");
                        final Match m = mRounds.get(i).mMatchs.get(j);
                        writer.println("<timeStamp>" + format.format(mRounds.get(i).mHour) + "</timeStamp>");
                        Coach p = m.mCoach1;
                        writer.println("<playerRecord>");
                        writer.println("<name>" + p.mName + "</name>");
                        writer.println("<number>" + p.mNaf + "</number>");
                        writer.println("<teamRating>" + p.mRank + "</teamRating>");
                        writer.println("<touchDowns>" + m.mValues.get(critTd).mValue1 + "</touchDowns>");
                        writer.println("<badlyHurt>" + m.mValues.get(critInj).mValue1 + "</badlyHurt>");
                        writer.println("<seriouslyInjured></seriouslyInjured>");
                        writer.println("<dead></dead>");
                        writer.println("<winnings></winnings>");
                        writer.println("</playerRecord>");
                        p = m.mCoach2;
                        writer.println("<playerRecord>");
                        writer.println("<name>" + p.mName + "</name>");
                        writer.println("<number>" + p.mNaf + "</number>");
                        writer.println("<teamRating>" + p.mRank + "</teamRating>");
                        writer.println("<touchDowns>" + m.mValues.get(critTd).mValue2 + "</touchDowns>");
                        writer.println("<badlyHurt>" + m.mValues.get(critInj).mValue2 + "</badlyHurt>");
                        writer.println("<seriouslyInjured></seriouslyInjured>");
                        writer.println("<dead></dead>");
                        writer.println("<winnings></winnings>");
                        writer.println("</playerRecord>");
                        writer.println("<gate></gate>");
                        writer.println("</game>");
                    }
                }
            }
            writer.println("</nafReport>");
            writer.close();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(MainFrame.getMainFrame(), e.getMessage());
        }


    }

    protected void LoadXMLv2(final Element Root) {
        final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault());
        /* Utilisateur */
        final Element params = Root.getChild("Parameters");
        final Criteria c1 = new Criteria("Touchdowns");
        final Criteria c2 = new Criteria("Sorties");
        final Criteria c3 = new Criteria("Fouls");
        final Criteria c4 = new Criteria("Passes");
        final Criteria c5 = new Criteria("Interceptions");

        try {
            if (params != null) {
                mParams.mTournamentOrga = params.getAttribute("Organizer").getValue();
                mParams.mTournamentName = params.getAttribute(StringConstants.CS_NAME).getValue();

                mParams.mCriterias.clear();
                c1.mPointsFor = params.getAttribute("Bonus_Pos_Td").getIntValue();
                c1.mPointsAgainst = params.getAttribute("Bonus_Neg_Td").getIntValue();
                c2.mPointsFor = params.getAttribute("Bonus_Pos_Sor").getIntValue();
                c2.mPointsAgainst = params.getAttribute("Bonus_Neg_Sor").getIntValue();
                c3.mPointsFor = params.getAttribute("Bonus_Pos_Foul").getIntValue();
                c3.mPointsAgainst = params.getAttribute("Bonus_Neg_Foul").getIntValue();


                try {
                    c4.mPointsFor = params.getAttribute("Bonus_Pos_Pas").getIntValue();
                    c4.mPointsAgainst = params.getAttribute("Bonus_Neg_Pas").getIntValue();

                    c5.mPointsFor = params.getAttribute("Bonus_Pos_Int").getIntValue();
                    c5.mPointsAgainst = params.getAttribute("Bonus_Neg_Int").getIntValue();


                } catch (NullPointerException npe) {
                }


                mParams.mCriterias.add(c1);
                mParams.mCriterias.add(c2);
                mParams.mCriterias.add(c3);
                mParams.mCriterias.add(c4);
                mParams.mCriterias.add(c5);

                mParams.mPointsIndivVictory = params.getAttribute("Victory").getIntValue();
                mParams.mPointsIndivLargeVictory = params.getAttribute("Large_Victory").getIntValue();
                mParams.mPointsIndivDraw = params.getAttribute("Draw").getIntValue();
                mParams.mPointsIndivLost = params.getAttribute("Lost").getIntValue();
                mParams.mPointsIndivLittleLost = params.getAttribute("Little_Lost").getIntValue();
                mParams.mRankingIndiv1 = params.getAttribute("Rank1").getIntValue();
                mParams.mRankingIndiv2 = params.getAttribute("Rank2").getIntValue();
                mParams.mRankingIndiv3 = params.getAttribute("Rank3").getIntValue();
                mParams.mRankingIndiv4 = params.getAttribute("Rank4").getIntValue();
                mParams.mRankingIndiv5 = params.getAttribute("Rank5").getIntValue();





                try {
                    mParams.mGapLargeVictory = params.getAttribute("Large_Victory_Gap").getIntValue();
                    mParams.mGapLittleLost = params.getAttribute("Little_Lost_Gap").getIntValue();
                    mParams.mPlace = params.getAttribute("Place").getValue();
                    mParams.mTeamTournament = params.getAttribute("ByTeam").getBooleanValue();
                    mParams.mTeamMatesNumber = params.getAttribute("TeamMates").getIntValue();
                    mParams.mTeamPairing = params.getAttribute("TeamPairing").getIntValue();
                    mParams.mTeamIndivPairing = params.getAttribute("TeamIndivPairing").getIntValue();
                    mParams.mPointsTeamVictoryBonus = params.getAttribute("TeamVictoryPoints").getIntValue();
                    mParams.mTeamIndivPairing = params.getAttribute("TeamIndivPairing").getIntValue();
                    mParams.mTeamVictoryOnly = params.getAttribute("TeamVictoryOnly").getBooleanValue();





                    try {
                        mParams.mDate = format.parse(params.getAttribute("Date").getValue());




                    } catch (ParseException pe) {
                    }

                } catch (NullPointerException ne) {
                    mParams.mGapLargeVictory = 3;
                    mParams.mGapLittleLost = 1;
                    mParams.mPlace = "";
                    mParams.mTeamTournament = false;
                    mParams.mTeamMatesNumber = 6;
                    mParams.mTeamPairing = 1;
                    mParams.mTeamIndivPairing = 0;




                }
                try {

                    c1.mPointsTeamFor = params.getAttribute("Bonus_Pos_Td_Team").getIntValue();
                    c1.mPointsTeamAgainst = params.getAttribute("Bonus_Neg_Td_Team").getIntValue();
                    c2.mPointsTeamFor = params.getAttribute("Bonus_Pos_Sor_Team").getIntValue();
                    c2.mPointsTeamAgainst = params.getAttribute("Bonus_Neg_Sor_Team").getIntValue();
                    c3.mPointsTeamFor = params.getAttribute("Bonus_Pos_Foul_Team").getIntValue();
                    c3.mPointsTeamAgainst = params.getAttribute("Bonus_Neg_Foul_Team").getIntValue();





                    try {
                        c4.mPointsTeamFor = params.getAttribute("Bonus_Pos_Pas_Team").getIntValue();
                        c4.mPointsTeamAgainst = params.getAttribute("Bonus_Neg_Pas_Team").getIntValue();

                        c5.mPointsTeamFor = params.getAttribute("Bonus_Pos_Int_Team").getIntValue();
                        c5.mPointsTeamAgainst = params.getAttribute("Bonus_Neg_Int_Team").getIntValue();





                    } catch (NullPointerException npe) {
                    }

                    mParams.mPointsTeamVictory = params.getAttribute("Victory_Team").getIntValue();
                    mParams.mPointsTeamDraw = params.getAttribute("Draw_Team").getIntValue();
                    mParams.mPointsTeamLost = params.getAttribute("Lost_Team").getIntValue();
                    mParams.mRankingTeam1 = params.getAttribute("Rank1_Team").getIntValue();
                    mParams.mRankingTeam2 = params.getAttribute("Rank2_Team").getIntValue();
                    mParams.mRankingTeam3 = params.getAttribute("Rank3_Team").getIntValue();
                    mParams.mRankingTeam4 = params.getAttribute("Rank4_Team").getIntValue();
                    mParams.mRankingTeam5 = params.getAttribute("Rank5_Team").getIntValue();




                } catch (NullPointerException ne2) {
                }
                try {
                    mParams.mEnableClans = params.getAttribute("ActvateClans").getBooleanValue();
                    mParams.mAvoidClansFirstMatch = params.getAttribute("AvoidFirstMatch").getBooleanValue();
                    mParams.mAvoidClansMatch = params.getAttribute("AvoidMatch").getBooleanValue();
                    mParams.mTeamMatesClansNumber = params.getAttribute("ClanTeammatesNumber").getIntValue();




                } catch (NullPointerException ne3) {
                }
            }

            final List clans = Root.getChildren(StringConstants.CS_CLAN);
            final Iterator h = clans.iterator();
            mClans.clear();
            Clan.sClanMap = new HashMap();




            while (h.hasNext()) {
                final Element clan = (Element) h.next();
                final Clan c = new Clan(clan.getAttributeValue(StringConstants.CS_NAME));
                mClans.add(c);
                Clan.sClanMap.put(c.mName, c);
            }

            final List coachs = Root.getChildren(StringConstants.CS_COACH);
            final Iterator i = coachs.iterator();
            mCoachs.clear();
            final HashMap<String, Coach> map = new HashMap();

            while (i.hasNext()) {
                final Element coach = (Element) i.next();
                final Coach c = new Coach();
                c.mName = coach.getAttributeValue(StringConstants.CS_NAME);
                c.mTeam = coach.getAttributeValue(StringConstants.CS_TEAM);
                c.mRoster = new RosterType(coach.getAttributeValue("Roster"));
                c.mNaf = coach.getAttribute("NAF").getIntValue();
                c.mRank = coach.getAttribute("Rank").getIntValue();
                c.mClan = Clan.sClanMap.get(coach.getAttributeValue(StringConstants.CS_CLAN));

                if (c.mClan == null) {
                    if (mClans.size() == 0) {
                        final java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE); // NOI18N
                        mClans.add(new Clan(bundle.getString("NoneKey")));
                    }
                    c.mClan = mClans.get(0);

                }
                mCoachs.add(c);
                map.put(c.mName, c);
            }

            final List teams = Root.getChildren(StringConstants.CS_TEAM);
            final Iterator l = teams.iterator();
            mTeams.clear();

            while (l.hasNext()) {
                final Element team = (Element) l.next();
                final Team t = new Team();
                t.mName = team.getAttributeValue(StringConstants.CS_NAME);
                final List coachs2 = team.getChildren(StringConstants.CS_COACH);
                final Iterator m = coachs2.iterator();
                t.mCoachs.clear();

                while (m.hasNext()) {
                    final Element coach = (Element) m.next();
                    final Coach c = map.get(coach.getAttribute(StringConstants.CS_NAME).getValue());
                    c.mTeamMates = t;
                    t.mCoachs.add(c);

                }
                mTeams.add(t);




            }

            final List rounds = Root.getChildren(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("ROUND"));
            final Iterator j = rounds.iterator();
            mRounds.clear();




            while (j.hasNext()) {
                final Element round = (Element) j.next();
                final Round r = new Round();
                final String date = round.getAttributeValue("Date");
                try {
                    r.mHour = format.parse(date);
                } catch (ParseException e) {
                }
                final List matchs = round.getChildren("Match");
                final Iterator k = matchs.iterator();
                r.mMatchs.clear();
                while (k.hasNext()) {
                    final Element match = (Element) k.next();
                    final Match m = new Match();
                    final String coach1 = match.getAttribute("Coach1").getValue();
                    final String coach2 = match.getAttribute("Coach2").getValue();
                    m.mCoach1 = map.get(coach1);
                    m.mCoach2 = map.get(coach2);
                    for (int cpt = 0; cpt
                            < mCoachs.size(); cpt++) {
                        if (c1.equals(mCoachs.get(cpt).mName)) {
                            m.mCoach1 = mCoachs.get(cpt);
                            break;
                        }
                    }
                    for (int cpt = 0; cpt
                            < mCoachs.size(); cpt++) {
                        if (c2.equals(mCoachs.get(cpt).mName)) {
                            m.mCoach2 = mCoachs.get(cpt);
                            break;

                        }
                    }
                    final Value v1 = new Value(c1);
                    v1.mValue1 = match.getAttribute("Td1").getIntValue();
                    v1.mValue2 = match.getAttribute("Td2").getIntValue();
                    m.mValues.put(c1, v1);

                    final Value v2 = new Value(c2);
                    v2.mValue1 = match.getAttribute("Sor1").getIntValue();
                    v2.mValue2 = match.getAttribute("Sor2").getIntValue();
                    m.mValues.put(c2, v2);

                    final Value v3 = new Value(c3);
                    v3.mValue1 = match.getAttribute("Foul1").getIntValue();
                    v3.mValue2 = match.getAttribute("Foul2").getIntValue();
                    m.mValues.put(c3, v3);

                    final Value v4 = new Value(c4);
                    final Value v5 = new Value(c5);

                    try {
                        v4.mValue1 = match.getAttribute("Pas1").getIntValue();
                        v4.mValue2 = match.getAttribute("Pas2").getIntValue();

                        v5.mValue1 = match.getAttribute("Int1").getIntValue();
                        v5.mValue2 = match.getAttribute("Int2").getIntValue();


                    } catch (NullPointerException npe) {
                    }
                    m.mValues.put(c4, v4);
                    m.mValues.put(c5, v5);

                    m.mCoach1.mMatchs.add(m);
                    m.mCoach2.mMatchs.add(m);

                    r.mMatchs.add(m);




                }

                mRounds.add(r);




            }
        } catch (DataConversionException dce) {
            JOptionPane.showMessageDialog(MainFrame.getMainFrame(), dce.getLocalizedMessage());




        }
    }

    protected void LoadXMLv3(final Element racine) {
        try {
            mRoundRobin = Boolean.parseBoolean(racine.getAttributeValue("RoundRobin"));
        } catch (Exception e) {
            mRoundRobin = false;
        }

        try {
            final List ros = racine.getChildren("Roster");
            if (ros != null) {
                if (ros.size() > 0) {
                    RosterType.mRostersNames = new ArrayList<String>();
                    final Iterator it_ros = ros.iterator();
                    while (it_ros.hasNext()) {
                        final Element r = (Element) it_ros.next();
                        RosterType.mRostersNames.add(r.getAttributeValue(StringConstants.CS_NAME));
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
        final Element params = racine.getChild("Parameters");
        mParams.setXMLElement(params);

        /* Groups */
        try {
            final List groups = racine.getChildren("Group");
            final Iterator gr = groups.iterator();
            mGroups.clear();

            while (gr.hasNext()) {
                final Element group = (Element) gr.next();
                final Group groupe = new Group(group.getAttributeValue(StringConstants.CS_NAME));
                groupe.setXMLElement(group);
                mGroups.add(groupe);
            }
        } catch (NullPointerException npe) {
        }

        /* Clans */
        final List clans = racine.getChildren(StringConstants.CS_CLAN);
        final Iterator h = clans.iterator();
        mClans.clear();
        Clan.sClanMap = new HashMap();

        while (h.hasNext()) {
            final Element clan = (Element) h.next();
            final Clan c = new Clan(clan.getAttributeValue(StringConstants.CS_NAME));
            c.setXMLElement(clan);
            mClans.add(c);
        }

        /* Coachs */
        final List coachs = racine.getChildren(StringConstants.CS_COACH);
        final Iterator i = coachs.iterator();
        mCoachs.clear();
        Coach.sCoachMap = new HashMap();

        while (i.hasNext()) {
            final Element coach = (Element) i.next();
            final Coach c = new Coach();
            c.setXMLElement(coach);
            mCoachs.add(c);
            c.mMatchs.clear();
        }

        /* Teams */
        final List teams = racine.getChildren(StringConstants.CS_TEAM);
        Team.sTeamMap = new HashMap();
        final Iterator l = teams.iterator();
        mTeams.clear();
        while (l.hasNext()) {
            final Element team = (Element) l.next();
            final Team t = new Team();
            t.setXMLElement(team);
            mTeams.add(t);
        }

        /* Pools */
        final List pools = racine.getChildren("Pool");
        final Iterator p = pools.iterator();
        mPools.clear();
        while (p.hasNext()) {
            final Element pool = (Element) p.next();
            final Pool po = new Pool();
            po.setXMLElement(pool);
            mPools.add(po);
        }

        /* Rounds */
        final List rounds = racine.getChildren(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("ROUND"));
        final Iterator j = rounds.iterator();
        mRounds.clear();

        while (j.hasNext()) {
            final Element round = (Element) j.next();
            final Round r = new Round();
            r.setXMLElement(round);
            mRounds.add(r);
        }
    }

    public void loadXML(final java.io.File file) {
        final SAXBuilder sxb = new SAXBuilder();

        try {
            final org.jdom.Document document = sxb.build(file);
            final Element racine = document.getRootElement();

            try {
                final String version = racine.getAttributeValue("Version");
                if (Integer.parseInt(version) == 3) {
                    LoadXMLv3(racine);

                }
            } catch (NumberFormatException npe) {
                LoadXMLv2(racine);
            }

        } catch (JDOMException e) {
            JOptionPane.showMessageDialog(MainFrame.getMainFrame(), e.getLocalizedMessage());

        } catch (IOException e) {
            JOptionPane.showMessageDialog(MainFrame.getMainFrame(), e.getMessage());
        }
    }

    public int GetActiveCoachNumber() {
        int nb = 0;
        for (int i = 0; i < mCoachs.size(); i++) {
            if (mCoachs.get(i).mActive) {
                nb++;
            }
        }
        return nb;
    }

    public ArrayList<Coach> GetActiveCoaches() {
        final ArrayList<Coach> v = new ArrayList<Coach>();

        for (int i = 0; i < mCoachs.size(); i++) {
            if (mCoachs.get(i).mActive) {
                v.add(mCoachs.get(i));
            }
        }
        return v;
    }

    public ArrayList<Pool> getPools() {
        return mPools;
    }
}
