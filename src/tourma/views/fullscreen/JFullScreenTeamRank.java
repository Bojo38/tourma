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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import tourma.data.Ranking;
import tourma.data.Team;
import tourma.data.Tournament;
import tourma.tableModel.MjtRanking;
import tourma.tableModel.MjtRankingTeam;
import tourma.utils.Ranked;
import tourma.utils.TourmaProtocol;

/**
 *
 * @author WFMJ7631
 */
public final class JFullScreenTeamRank extends JFullScreen {

    private int round;

    private boolean loopStop = false;

    public JFullScreenTeamRank(Socket s) throws IOException {
        super(s);
        initComponents();
        loopStop = false;
    }

    protected void clientLoop() {
        try {

            Font font = Font.createFont(Font.TRUETYPE_FONT, this.getClass().getResourceAsStream("/tourma/languages/calibri.ttf"));

            GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
            int width = gd.getDisplayMode().getWidth();
            int height = gd.getDisplayMode().getHeight();

            float size = (float) height / 50;
            Font f0 = font.deriveFont(Font.ITALIC, size);
            Font f1 = font.deriveFont(Font.BOLD, size);
            Font f = font.deriveFont(Font.PLAIN, size);

            int computed_height = height / 20;

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println(TourmaProtocol.TKey.TEAM_RANK.toString());
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
                            Element element = doc.getRootElement();
                            r = new Ranking(element);

                            buildPanel(r);

                            semAnimate.release();
                            this.getGraphicsConfiguration().getDevice().setFullScreenWindow(this);

                        } catch (JDOMException ex) {
                            Logger.getLogger(JFullScreenIndivRank.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        try {
                            Thread.sleep(10000);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(JFullScreenIndivRank.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        out.println(TourmaProtocol.TKey.TEAM_RANK.toString());
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
    }

    private void buildPanel(Ranked ranked) throws FontFormatException {

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

        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int width = gd.getDisplayMode().getWidth();
        int height = gd.getDisplayMode().getHeight();

        Font f0 = font.deriveFont(Font.ITALIC, (float) height / 50);
        Font f1 = font.deriveFont(Font.BOLD, (float) height / 50);
        Font f = font.deriveFont(Font.PLAIN, (float) height / 50);

        int computed_height = height / 20;

        //final boolean forPool = (Tournament.getTournament().getPoolCount() > 0) && (!Tournament.getTournament().getRound(r).isCup());
        int nbCols = Tournament.getTournament().getParams().getTeamRankingNumber() + 3;
        int computed_width = width / nbCols;

        // Number
        JLabel jlbTNum = new JLabel("#");
        jlbTNum.setFont(f1);
        jlbTNum.setOpaque(true);
        jlbTNum.setBackground(Color.BLACK);
        jlbTNum.setForeground(Color.WHITE);

        jpn.add(jlbTNum, getGridbBagConstraints(0, 0, 1, 1));

        int index = 1;

        JLabel jlbTCoach = new JLabel(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("Team"));
        jlbTCoach.setFont(f1);
        jlbTCoach.setOpaque(true);
        jlbTCoach.setBackground(Color.BLACK);
        jlbTCoach.setForeground(Color.WHITE);
        jpn.add(jlbTCoach, getGridbBagConstraints(index, 0, 1, 5));
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
                jpn.add(jlbRank, getGridbBagConstraints(index, 0, 1, 2));
                index += 2;
            }
        }

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
            jpn.add(jlbNum, getGridbBagConstraints(0, i + 1, 1, 1));

            index = 1;

            Team team = (Team) ranked.getSortedObject(i).getObject();
            JLabel jlbCoach = getLabelForObject(team, computed_height, computed_width, currentFont, bkg);
            jpn.add(jlbCoach, getGridbBagConstraints(index, i + 1, 1, 5));
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
                    jpn.add(jlbRank, getGridbBagConstraints(index, i + 1, 1, 2));
                    index += 2;
                }
            }
        }
        jscrp.setViewportView(jpn);
        jpnContent = jpn;
        this.repaint();
    }

    protected void setStop(boolean s) {
        loopStop = true;
    }

    /**
     *
     * @param r
     * @throws IOException
     */
    @SuppressWarnings("LeakingThisInConstructor")
    public JFullScreenTeamRank(int r) throws IOException {
        super();
        initComponents();
        try {
            round = r;
            ArrayList<Team> teams = new ArrayList<>();
            for (int cpt = 0; cpt < Tournament.getTournament().getTeamsCount(); cpt++) {
                teams.add(Tournament.getTournament().getTeam(cpt));
            }

            MjtRankingTeam ranking = new MjtRankingTeam(
                    Tournament.getTournament().getParams().isTeamVictoryOnly(),
                    round,
                    teams,
                    false);
            buildPanel(ranking);
        } catch (FontFormatException ex) {
            Logger.getLogger(JFullScreenTeamRank.class.getName()).log(Level.SEVERE, null, ex);
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
    private static final Logger LOG = Logger.getLogger(JFullScreenTeamRank.class.getName());
}
