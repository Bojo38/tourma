/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.views.fullscreen;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagLayout;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.net.Socket;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import tourma.data.Category;
import tourma.data.Coach;
import tourma.data.Group;
import tourma.data.Pool;
import tourma.data.Ranking;
import tourma.data.Tournament;
import tourma.languages.Translate;
import tourma.tableModel.MjtRanking;
import tourma.tableModel.MjtRankingIndiv;
import tourma.utils.display.TourmaProtocol;
import tourma.utils.display.IRanked;

/**
 *
 * @author WFMJ7631
 */
public final class JFullScreenIndivRank extends JFullScreen {

    private static final long serialVersionUID = 11L;

    public final static int C_GENERAL = 0;
    public final static int C_CATEGORY = 1;
    public final static int C_GROUP = 2;
    public final static int C_POOL = 3;

    private int round;
    private boolean loopStop = false;
    private int indivRankType = C_GENERAL;

    private final static String CS_Pool = "Pool";
    private final static String CS_Team = "Team";
    private final static String CS_Clan = "Clan";
    private final static String CS_Coach = "Coach";
    private final static String CS_Roster = "Roster";
    private final static String CS_RosterName = "RosterName";

    public JFullScreenIndivRank(Socket s) throws IOException {
        this(s, C_GENERAL);
    }

    public JFullScreenIndivRank(Socket s, int type) throws IOException {
        super(s);
        initComponents();
        loopStop = false;
        indivRankType = type;
        semStart.release();
    }

    @Override
    protected void clientLoop(int screen) throws InterruptedException {

        semStart.acquire();

        try {

            Font font = Font.createFont(Font.TRUETYPE_FONT, this.getClass().getResourceAsStream("/tourma/languages/calibri.ttf"));

            int width = mSelectedGD.getDisplayMode().getWidth();
            int height = mSelectedGD.getDisplayMode().getHeight();

            float size = (float) height / 50;
            Font f0 = font.deriveFont(Font.ITALIC, size);
            Font f1 = font.deriveFont(Font.BOLD, size);
            Font f = font.deriveFont(Font.PLAIN, size);

            int computed_height = height / 20;

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            if (indivRankType == C_GENERAL) {
                out.println(TourmaProtocol.TKey.INDIVIDUAL_RANK.toString());
            } else {
                if (indivRankType == C_GROUP) {
                    out.println(TourmaProtocol.TKey.GROUP_RANK.toString());
                } else {
                    if (indivRankType == C_CATEGORY) {
                        out.println(TourmaProtocol.TKey.CATEGORY_RANK.toString());
                    } else {
                        if (indivRankType == C_POOL) {
                            out.println(TourmaProtocol.TKey.POOL_INDIV_RANK.toString());
                        }
                    }
                }
            }
            out.println(TourmaProtocol.TKey.END.toString());

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            socket.getInputStream()));

            Ranking r;
            while (!loopStop) {
                String inputLine;
                inputLine = in.readLine();
                String buffer = "";
                while (inputLine != null) {
                    if (inputLine.equals(TourmaProtocol.TKey.END.toString())) {
                        SAXBuilder sb = new SAXBuilder();
                        try {
                            try {
                                semAnimate.acquire();
                            } catch (InterruptedException ex) {
                                Logger.getLogger(JFullScreenMatchs.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            Document doc = sb.build(new StringReader(buffer));
                            ArrayList<IRanked> rs = new ArrayList<>();
                            Element element = doc.getRootElement();
                            if (this.indivRankType == C_GENERAL) {
                                r = new Ranking(element);
                                rs.add(r);

                            } else {
                                List<Element> elements = element.getChildren();
                                Iterator<Element> it = elements.iterator();
                                while (it.hasNext()) {
                                    Element el = it.next();
                                    r = new Ranking(el);
                                    rs.add(r);
                                }
                                buildPanel(rs);
                            }
                            buildPanel(rs);
                            semAnimate.release();
                            mSelectedGD.setFullScreenWindow(this);

                        } catch (JDOMException ex) {
                            Logger.getLogger(JFullScreenIndivRank.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        try {
                            Thread.sleep(10000);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(JFullScreenIndivRank.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        if (indivRankType == C_GENERAL) {
                            out.println(TourmaProtocol.TKey.INDIVIDUAL_RANK.toString());
                        } else {
                            if (indivRankType == C_GROUP) {
                                out.println(TourmaProtocol.TKey.GROUP_RANK.toString());
                            } else {
                                if (indivRankType == C_CATEGORY) {
                                    out.println(TourmaProtocol.TKey.CATEGORY_RANK.toString());
                                } else {
                                    if (indivRankType == C_POOL) {
                                        out.println(TourmaProtocol.TKey.POOL_INDIV_RANK.toString());
                                    }
                                }
                            }
                        }
                        out.println(TourmaProtocol.TKey.END.toString());

                        buffer = "";
                    } else {
                        buffer += inputLine;
                    }
                    inputLine = in.readLine();
                }
            }

        } catch (IOException | FontFormatException e) {
            Logger.getLogger(JFullScreenIndivRank.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                socket.close();
            } catch (IOException ex) {
                Logger.getLogger(JFullScreenIndivRank.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        semStart.release();
    }

    protected void setStop(boolean s) {
        loopStop = true;
    }

    public JFullScreenIndivRank(int r) throws IOException {
        this(r, C_GENERAL);
    }

    /**
     *
     * @param r
     * @throws IOException
     */
    @SuppressWarnings("LeakingThisInConstructor")
    public JFullScreenIndivRank(int r, int type) throws IOException {
        super();
        this.indivRankType = type;
        initComponents();
        try {
            round = r;

            ArrayList<IRanked> rankeds = new ArrayList<>();
            //final boolean forPool = (Tournament.getTournament().getPoolCount() > 0) && (!Tournament.getTournament().getRound(r).isCup());
            if (indivRankType == C_GENERAL) {
                final ArrayList<Coach> coaches = new ArrayList<>();
                for (int cpt = 0; cpt < Tournament.getTournament().getCoachsCount(); cpt++) {
                    coaches.add(Tournament.getTournament().getCoach(cpt));
                }

                MjtRankingIndiv ranking = new MjtRankingIndiv(round, Tournament.getTournament().getParams().getRankingIndiv1(), Tournament.getTournament().getParams().getRankingIndiv2(), Tournament.getTournament().getParams().getRankingIndiv3(), Tournament.getTournament().getParams().getRankingIndiv4(), Tournament.getTournament().getParams().getRankingIndiv5(),
                        coaches, Tournament.getTournament().getParams().isTeamTournament(),
                        false,
                        Tournament.getTournament().getPoolCount() > 0,
                Tournament.getTournament().getRound(round).isCup());
                ranking.setDetail(Ranking.CS_General);
                rankeds.add(ranking);

            }
            if (indivRankType == C_GROUP) {
                for (int i = 0; i < Tournament.getTournament().getGroupsCount(); i++) {
                    Group g = Tournament.getTournament().getGroup(i);

                    final ArrayList<Coach> coaches = new ArrayList<>();
                    for (int cpt = 0; cpt < Tournament.getTournament().getCoachsCount(); cpt++) {
                        Coach coach = Tournament.getTournament().getCoach(cpt);
                        if (g.containsRoster(coach.getRoster())) {
                            coaches.add(coach);
                        }
                    }

                    MjtRankingIndiv ranking = new MjtRankingIndiv(round, Tournament.getTournament().getParams().getRankingIndiv1(), Tournament.getTournament().getParams().getRankingIndiv2(), Tournament.getTournament().getParams().getRankingIndiv3(), Tournament.getTournament().getParams().getRankingIndiv4(), Tournament.getTournament().getParams().getRankingIndiv5(),
                            coaches, Tournament.getTournament().getParams().isTeamTournament(),
                            false,
                            false,false);
                    ranking.setDetail(g.getName());
                    rankeds.add(ranking);
                }

            }
            if (indivRankType == C_CATEGORY) {
                for (int i = 0; i < Tournament.getTournament().getCategoriesCount(); i++) {
                    Category cat = Tournament.getTournament().getCategory(i);

                    final ArrayList<Coach> coaches = new ArrayList<>();
                    for (int cpt = 0; cpt < Tournament.getTournament().getCoachsCount(); cpt++) {
                        Coach coach = Tournament.getTournament().getCoach(cpt);
                        if (coach.containsCategory(cat)) {
                            coaches.add(coach);
                        }
                    }
                    MjtRankingIndiv ranking = new MjtRankingIndiv(round, Tournament.getTournament().getParams().getRankingIndiv1(), Tournament.getTournament().getParams().getRankingIndiv2(), Tournament.getTournament().getParams().getRankingIndiv3(), Tournament.getTournament().getParams().getRankingIndiv4(), Tournament.getTournament().getParams().getRankingIndiv5(),
                            coaches, Tournament.getTournament().getParams().isTeamTournament(),
                            false,
                            false,false);
                    ranking.setDetail(cat.getName());
                    rankeds.add(ranking);
                }

            }
            if (indivRankType == C_POOL) {
                for (int i = 0; i < Tournament.getTournament().getPoolCount(); i++) {
                    Pool p = Tournament.getTournament().getPool(i);

                    MjtRankingIndiv ranking = new MjtRankingIndiv(round, Tournament.getTournament().getParams().getRankingIndiv1(), Tournament.getTournament().getParams().getRankingIndiv2(), Tournament.getTournament().getParams().getRankingIndiv3(), Tournament.getTournament().getParams().getRankingIndiv4(), Tournament.getTournament().getParams().getRankingIndiv5(),
                            p.getCompetitors(), Tournament.getTournament().getParams().isTeamTournament(),
                            false,
                            false,false);
                    ranking.setDetail(Integer.toString(i + 1));
                    rankeds.add(ranking);
                }

            }
            buildPanel(rankeds);

        } catch (FontFormatException ex) {
            Logger.getLogger(JFullScreenIndivRank.class.getName()).log(Level.SEVERE, null, ex);
        }
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] gs = ge.getScreenDevices();
        int screen = 0;
        if (gs.length > 1) {
            Integer options[] = new Integer[gs.length];
            for (int i = 0; i < gs.length; i++) {
                options[i] = i;
                
            }
            Object val = JOptionPane.showOptionDialog(null, "Please Select a screen index", "Screen Selection", JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            if (val instanceof Integer) {
                screen = ((Integer) val).intValue();
            }
        }
        if (screen > -1 && screen < gs.length) {
            gs[screen].setFullScreenWindow(this);
            mSelectedGD=gs[screen];
        } else if (gs.length > 0) {
            gs[0].setFullScreenWindow(this);
            mSelectedGD=gs[0];
        } else {
            throw new RuntimeException("No Screens Found");
        }
    }

    private void buildPanel(ArrayList<IRanked> rankeds) throws FontFormatException {

        Font font;

        JPanel jpn = new JPanel();
        GridBagLayout gbl = new GridBagLayout();
        jpn.setLayout(gbl);

        try {
            font = Font.createFont(Font.TRUETYPE_FONT, this.getClass().getResourceAsStream("/tourma/languages/calibri.ttf"));
        } catch (IOException ex) {
            Logger.getLogger(JFullScreenIndivRank.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            font = this.getFont();
        }

        int width = mSelectedGD.getDisplayMode().getWidth();
        int height = mSelectedGD.getDisplayMode().getHeight();

        float size = (float) height / 50;
        Font f0 = font.deriveFont(Font.ITALIC, size);
        Font f1 = font.deriveFont(Font.BOLD, size);
        Font f = font.deriveFont(Font.PLAIN, size);

        int computed_height = height / 20;

        try {
            int nbCols = Tournament.getTournament().getParams().getIndivRankingNumber() + 4;
            if (Tournament.getTournament().getParams().isTeamTournament()) {
                nbCols++;
            } else {
                if (Tournament.getTournament().getClansCount() > 1) {
                    nbCols++;
                }
            }

            if (indivRankType == C_POOL) {
                nbCols++;
            }

            int computed_width = width / nbCols;

            int line = 0;

            for (int cpt = 0; cpt < rankeds.size(); cpt++) {

                IRanked ranked = rankeds.get(cpt);

                // Number
                JLabel jlbTNum = new JLabel("#");
                jlbTNum.setFont(f1);
                jlbTNum.setOpaque(true);
                jlbTNum.setBackground(Color.BLACK);
                jlbTNum.setForeground(Color.WHITE);

                jpn.add(jlbTNum, getGridbBagConstraints(0, line, 1, 1));

                int index = 1;

                if (indivRankType == C_POOL) {
                    JLabel jlbTPool = new JLabel(Translate.translate(CS_Pool));
                    jlbTPool.setFont(f1);
                    jlbTPool.setOpaque(true);
                    jlbTPool.setBackground(Color.BLACK);
                    jlbTPool.setForeground(Color.WHITE);

                    jpn.add(jlbTPool, getGridbBagConstraints(index, line, 1, 1));
                    index += 1;
                }

                if (Tournament.getTournament().getParams().isTeamTournament()) {
                    JLabel jlbTTeam = new JLabel(Translate.translate(CS_Team));
                    jlbTTeam.setFont(f1);
                    jlbTTeam.setOpaque(true);
                    jlbTTeam.setBackground(Color.BLACK);
                    jlbTTeam.setForeground(Color.WHITE);

                    jpn.add(jlbTTeam, getGridbBagConstraints(index, line, 1, 5));
                    index += 5;
                }
                if (Tournament.getTournament().getClansCount() > 1) {
                    JLabel jlbTClan = new JLabel(Translate.translate(CS_Clan));
                    jlbTClan.setFont(f1);
                    jlbTClan.setOpaque(true);

                    jlbTClan.setBackground(Color.BLACK);
                    jlbTClan.setForeground(Color.WHITE);
                    jpn.add(jlbTClan, getGridbBagConstraints(index, line, 1, 5));
                    index += 5;
                }

                JLabel jlbTCoach = new JLabel(Translate.translate(CS_Coach));
                jlbTCoach.setFont(f1);
                jlbTCoach.setOpaque(true);
                jlbTCoach.setBackground(Color.BLACK);
                jlbTCoach.setForeground(Color.WHITE);
                jpn.add(jlbTCoach, getGridbBagConstraints(index, line, 1, 5));
                index += 5;

                JLabel jlbTRoster = new JLabel(Translate.translate(CS_Roster));
                jlbTRoster.setFont(f1);
                jlbTRoster.setOpaque(true);
                jlbTRoster.setBackground(Color.BLACK);
                jlbTRoster.setForeground(Color.WHITE);
                jpn.add(jlbTRoster, getGridbBagConstraints(index, line, 1, 3));
                index += 3;

                JLabel jlbTRosterName = new JLabel(Translate.translate(CS_RosterName));
                jlbTRosterName.setFont(f1);
                jlbTRosterName.setOpaque(true);
                jlbTRosterName.setBackground(Color.BLACK);
                jlbTRosterName.setForeground(Color.WHITE);
                jpn.add(jlbTRosterName, getGridbBagConstraints(index, line, 1, 3));
                index += 3;

                for (int j = 0; j < Tournament.getTournament().getParams().getIndivRankingNumber(); j++) {
                    int rankingType = Tournament.getTournament().getParams().getIndivRankingType(j);
                    String name = MjtRanking.getRankingString(rankingType);
                    if (rankingType == 0) {
                        break;
                    } else {
                        JLabel jlbRank = new JLabel(name);
                        jlbRank.setFont(f1);
                        jlbRank.setOpaque(true);
                        jlbRank.setBackground(Color.BLACK);
                        jlbRank.setForeground(Color.WHITE);
                        jpn.add(jlbRank, getGridbBagConstraints(index, line, 1, 2));
                        index += 2;
                    }
                }
                line++;
                int nbRows = ranked.getRowCount();
                for (int i = 0; i < nbRows; i++) {
                    Color bkg = new Color(255, 255, 255);
                    if (i % 2 != 0) {
                        bkg = new Color(220, 220, 220);
                    }

                    // Set font
                    Font currentFont = f;
                    if (i == 0) {
                        currentFont = f1;
                    }
                    if ((i == 2) || (i == 1)) {
                        currentFont = f0;
                    }

                    // Number
                    JLabel jlbNum = new JLabel("" + (i + 1));
                    jlbNum.setFont(currentFont);
                    jlbNum.setOpaque(true);
                    jlbNum.setBackground(bkg);
                    jpn.add(jlbNum, getGridbBagConstraints(0, line, 1, 1));

                    index = 1;

                    if (indivRankType == C_POOL) {
                        JLabel jlbPool = new JLabel(ranked.getDetail());
                        jlbNum.setFont(currentFont);
                        jlbNum.setOpaque(true);
                        jlbNum.setBackground(bkg);
                        jpn.add(jlbPool, getGridbBagConstraints(index, line, 1, 1));
                        index += 1;
                    }

                    Coach coach = (Coach) ranked.getSortedObject(i).getObject();
                    if (Tournament.getTournament().getParams().isTeamTournament()) {
                        JLabel jlb = getLabelForObject(coach.getTeamMates(), computed_height, computed_width, currentFont, bkg);
                        jpn.add(jlb, getGridbBagConstraints(index, line, 1, 5));
                        index += 5;
                    }
                    if (Tournament.getTournament().getClansCount() > 1) {
                        JLabel jlb = getLabelForObject(coach.getClan(), computed_height, computed_width, currentFont, bkg);
                        jpn.add(jlb, getGridbBagConstraints(index, line, 1, 5));
                        index += 5;
                    }

                    JLabel jlbCoach = getLabelForObject(coach, computed_height, computed_width, currentFont, bkg);
                    if (indivRankType == C_CATEGORY) {
                        if (ranked.getDetail() != null) {
                            jlbCoach.setText(jlbCoach.getText() + " (" + ranked.getDetail() + ")");
                        }
                    }
                    jpn.add(jlbCoach, getGridbBagConstraints(index, line, 1, 5));
                    index += 5;

                    JLabel jlbRoster = new JLabel(coach.getRoster().getName());
                    if (indivRankType == C_GROUP) {
                        if (ranked.getDetail() != null) {
                            jlbRoster.setText(jlbRoster.getText() + " (" + ranked.getDetail() + ")");
                        }
                    }
                    jlbRoster.setFont(currentFont);
                    jlbRoster.setOpaque(true);
                    jlbRoster.setBackground(bkg);
                    jpn.add(jlbRoster, getGridbBagConstraints(index, line, 1, 3));
                    index += 3;

                    JLabel jlbTeam = new JLabel(coach.getTeam());
                    jlbTeam.setFont(currentFont);
                    jlbTeam.setOpaque(true);
                    jlbTeam.setBackground(bkg);
                    jpn.add(jlbTeam, getGridbBagConstraints(index, line, 1, 3));
                    index += 3;

                    for (int j = 0; j < Tournament.getTournament().getParams().getIndivRankingNumber(); j++) {
                        int rankingType = Tournament.getTournament().getParams().getIndivRankingType(j);
                        int value;
                        value = ranked.getSortedValue(i, j + 1);

                        String name = Integer.toString(value);
                        if (rankingType == 0) {
                            break;
                        } else {
                            JLabel jlbRank = new JLabel(name);
                            jlbRank.setFont(currentFont);
                            jlbRank.setOpaque(true);
                            jlbRank.setBackground(bkg);
                            jpn.add(jlbRank, getGridbBagConstraints(index, line, 1, 2));
                            index += 2;
                        }
                    }
                    line++;
                }
            }
        } catch (RemoteException re) {
            re.printStackTrace();
        }

        jscrp.setViewportView(jpn);
        jpnContent = jpn;
        this.repaint();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setAlwaysOnTop(true);
        setName("FullScreen Tourma"); // NOI18N

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

}
