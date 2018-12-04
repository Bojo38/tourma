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
import tourma.data.Pool;
import tourma.data.Ranking;
import tourma.data.Team;
import tourma.data.Tournament;
import tourma.languages.Translate;
import tourma.tableModel.MjtRanking;
import tourma.tableModel.MjtRankingTeam;
import tourma.utils.display.TourmaProtocol;
import tourma.utils.display.IRanked;

/**
 *
 * @author WFMJ7631
 */
public final class JFullScreenTeamRank extends JFullScreen {

    private int round;

    private boolean loopStop = false;

    private boolean forPool = false;

    public JFullScreenTeamRank(Socket s) throws IOException {
        this(s, false);
    }

    public JFullScreenTeamRank(Socket s, boolean pool) throws IOException {
        super(s);
        initComponents();
        forPool = pool;
        loopStop = false;
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
            if (forPool) {
                out.println(TourmaProtocol.TKey.POOL_TEAM_RANK.toString());
            } else {
                out.println(TourmaProtocol.TKey.TEAM_RANK.toString());
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
                                Logger.getLogger(JFullScreenTeamRank.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            Document doc = sb.build(new StringReader(buffer));
                            ArrayList<IRanked> rs = new ArrayList<>();
                            Element element = doc.getRootElement();
                            if (!forPool) {
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
                            }
                            buildPanel(rs, screen);

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
                        if (forPool) {
                            out.println(TourmaProtocol.TKey.POOL_TEAM_RANK.toString());
                        } else {
                            out.println(TourmaProtocol.TKey.TEAM_RANK.toString());
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

    private final static String CS_Pool = "Pool";
    private final static String CS_Team = "Team";

    private void buildPanel(ArrayList<IRanked> rankeds, int screen) throws FontFormatException {

        Font font;

        JPanel jpn = new JPanel();
        GridBagLayout gbl = new GridBagLayout();
        jpn.setLayout(gbl);

        try {
            //            Criteria td = Tournament.getTournament().getParams().getCriteria(0);
            font = Font.createFont(Font.TRUETYPE_FONT, this.getClass().getResourceAsStream("/tourma/languages/calibri.ttf"));
        } catch (IOException ex) {
            Logger.getLogger(JFullScreenTeamRank.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            font = this.getFont();
        }

        int width = mSelectedGD.getDisplayMode().getWidth();
        int height = mSelectedGD.getDisplayMode().getHeight();

        Font f0 = font.deriveFont(Font.ITALIC, (float) height / 50);
        Font f1 = font.deriveFont(Font.BOLD, (float) height / 50);
        Font f = font.deriveFont(Font.PLAIN, (float) height / 50);

        int computed_height = height / 20;

        try {
            //final boolean forPool = (Tournament.getTournament().getPoolCount() > 0) && (!Tournament.getTournament().getRound(r).isCup());
            int nbCols = Tournament.getTournament().getParams().getTeamRankingNumber() + 3;
            if (forPool) {
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
                jlbTNum.setHorizontalAlignment(JLabel.CENTER);
                jpn.add(jlbTNum, getGridbBagConstraints(0, line, 1, 1));

                int index = 1;

                if (forPool) {
                    JLabel jlbTPool = new JLabel(Translate.translate(CS_Pool));
                    jlbTPool.setFont(f1);
                    jlbTPool.setOpaque(true);
                    jlbTPool.setBackground(Color.BLACK);
                    jlbTPool.setForeground(Color.WHITE);
                    jlbTPool.setHorizontalAlignment(JLabel.CENTER);
                    jpn.add(jlbTPool, getGridbBagConstraints(index, line, 1, 5));
                    index++;
                }

                JLabel jlbTCoach = new JLabel(Translate.translate(CS_Team));
                jlbTCoach.setFont(f1);
                jlbTCoach.setOpaque(true);
                jlbTCoach.setBackground(Color.BLACK);
                jlbTCoach.setForeground(Color.WHITE);
                jlbTCoach.setHorizontalAlignment(JLabel.CENTER);
                jpn.add(jlbTCoach, getGridbBagConstraints(index, line, 1, 5));
                index += 5;

                for (int j = 0; j < Tournament.getTournament().getParams().getTeamRankingNumber(); j++) {
                    int rankingType = Tournament.getTournament().getParams().getTeamRankingType(j);
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

                    if (forPool) {
                        JLabel jlbPool = new JLabel(ranked.getDetail());
                        jlbPool.setFont(currentFont);
                        jlbPool.setOpaque(true);
                        jlbPool.setBackground(bkg);
                        jlbPool.setHorizontalAlignment(JLabel.CENTER);
                        jpn.add(jlbPool, getGridbBagConstraints(index, line, 1, 1));
                        index++;
                    }

                    Team team = (Team) ranked.getSortedObject(i).getObject();
                    JLabel jlbCoach = getLabelForObject(team, computed_height, computed_width, currentFont, bkg);
                    jpn.add(jlbCoach, getGridbBagConstraints(index, line, 1, 5));
                    index += 5;

                    for (int j = 0; j < Tournament.getTournament().getParams().getTeamRankingNumber(); j++) {
                        int rankingType = Tournament.getTournament().getParams().getTeamRankingType(j);
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

    protected void setStop(boolean s) {
        loopStop = true;
    }

    public JFullScreenTeamRank(int r) throws IOException {
        this(r, false);
    }

    /**
     *
     * @param r
     * @param pool
     * @throws IOException
     */
    @SuppressWarnings("LeakingThisInConstructor")
    public JFullScreenTeamRank(int r, boolean pool) throws IOException {
        super();
        initComponents();
        forPool = pool;

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

        try {
            round = r;
            ArrayList<IRanked> rankeds = new ArrayList<>();

            if (!pool) {
                ArrayList<Team> teams = new ArrayList<>();
                for (int cpt = 0; cpt < Tournament.getTournament().getTeamsCount(); cpt++) {
                    teams.add(Tournament.getTournament().getTeam(cpt));
                }

                MjtRankingTeam ranking = new MjtRankingTeam(
                        Tournament.getTournament().getParams().isTeamVictoryOnly(),
                        round,
                        teams,
                        false);
                rankeds.add(ranking);
            } else {
                for (int i = 0; i < Tournament.getTournament().getPoolCount(); i++) {
                    Pool p = Tournament.getTournament().getPool(i);
                    MjtRankingTeam ranking = new MjtRankingTeam(
                            Tournament.getTournament().getParams().isTeamVictoryOnly(),
                            round,
                            p.getCompetitors(),
                            false);
                    ranking.setDetail(Integer.toString(i + 1));
                    rankeds.add(ranking);
                }
            }
            buildPanel(rankeds, screen);

        } catch (FontFormatException ex) {
            Logger.getLogger(JFullScreenTeamRank.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        this.getGraphicsConfiguration().getDevice().setFullScreenWindow(this);
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
    private static final Logger LOG = Logger.getLogger(JFullScreenTeamRank.class
            .getName());
}
