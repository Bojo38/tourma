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
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import tourma.data.Clan;
import tourma.data.Criteria;
import tourma.data.IWithNameAndPicture;
import tourma.data.ObjectAnnexRanking;
import tourma.data.ObjectRanking;
import tourma.data.Ranking;
import tourma.data.Team;
import tourma.data.Tournament;
import tourma.tableModel.MjtAnnexRank;
import tourma.tableModel.MjtAnnexRankClan;
import tourma.tableModel.MjtAnnexRankTeam;
import tourma.utils.Ranked;
import tourma.utils.TourmaProtocol;

/**
 *
 * @author WFMJ7631
 */
public final class JFullScreenClanTeamAnnex extends JFullScreen {

    private int round;

    private boolean loopStop = false;
    private boolean team;
    

    public JFullScreenClanTeamAnnex(Socket s, boolean team) throws IOException {
        super(s);
        initComponents();
        this.team = team;
        loopStop = false;
        
        // Synchronized the end of constructor with the client thread
        semStart.release();

    }

    @Override
    protected void clientLoop() throws InterruptedException{
        // Synchronized the end of constructor with the client thread
        semStart.acquire();

        try {

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            if (team) {
                out.println(TourmaProtocol.TKey.TEAM_ANNEX.toString());
            } else {
                out.println(TourmaProtocol.TKey.CLAN_ANNEX.toString());
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
                            if (!buffer.equals("")) {
                                Document doc = sb.build(new StringReader(buffer));
                                Element element = doc.getRootElement();
                                ArrayList<Ranked> rs = new ArrayList<>();

                                List<Element> elements = element.getChildren();
                                Iterator<Element> it = elements.iterator();
                                while (it.hasNext()) {
                                    Element el = it.next();
                                    r = new Ranking(el);
                                    rs.add(r);
                                }
                                buildPanel(rs);
                            }
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
                        if (team) {
                            out.println(TourmaProtocol.TKey.TEAM_ANNEX.toString());
                        } else {
                            out.println(TourmaProtocol.TKey.CLAN_ANNEX.toString());
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

    /**
     *
     * @param r
     * @param full
     * @param team
     * @throws IOException
     */
    @SuppressWarnings("LeakingThisInConstructor")
    public JFullScreenClanTeamAnnex(int r, boolean full, boolean team) throws IOException {
        super();
        initComponents();
        this.team = team;

        round = r;

        try {

            if (team) {
                final ArrayList<Team> teams = new ArrayList<>();
                for (int cpt = 0; cpt < Tournament.getTournament().getTeamsCount(); cpt++) {
                    teams.add(Tournament.getTournament().getTeam(cpt));
                }

                ArrayList<Ranked> rs = new ArrayList<>();
                for (int i = 0; i < Tournament.getTournament().getParams().getCriteriaCount(); i++) {
                    Criteria crit = Tournament.getTournament().getParams().getCriteria(i);

                    MjtAnnexRankTeam annexRank0 = new MjtAnnexRankTeam(Tournament.getTournament().getParams().isTeamVictoryOnly(),
                            round, crit, 0, teams, full, false);
                    MjtAnnexRankTeam annexRank1 = new MjtAnnexRankTeam(Tournament.getTournament().getParams().isTeamVictoryOnly(),
                            round, crit, 1,
                            teams, full, false);
                    MjtAnnexRankTeam annexRank2 = new MjtAnnexRankTeam(Tournament.getTournament().getParams().isTeamVictoryOnly(),
                            round, crit, 2,
                            teams, full, false);

                    rs.add(annexRank0);
                    rs.add(annexRank1);
                    rs.add(annexRank2);
                }
                buildPanel(rs);

            } else {
                final ArrayList<Clan> clans = new ArrayList<>();
                for (int cpt = 0; cpt < Tournament.getTournament().getClansCount(); cpt++) {
                    clans.add(Tournament.getTournament().getClan(cpt));
                }

                ArrayList<Ranked> rs = new ArrayList<>();
                for (int i = 0; i < Tournament.getTournament().getParams().getCriteriaCount(); i++) {
                    Criteria crit = Tournament.getTournament().getParams().getCriteria(i);

                    MjtAnnexRankClan annexRank0 = new MjtAnnexRankClan(round, crit, 0,
                            full, clans, false);
                    MjtAnnexRankClan annexRank1 = new MjtAnnexRankClan(round, crit, 1,
                            full, clans, false);
                    MjtAnnexRankClan annexRank2 = new MjtAnnexRankClan(round, crit, 2,
                            full, clans, false);

                    rs.add(annexRank0);
                    rs.add(annexRank1);
                    rs.add(annexRank2);
                }
                buildPanel(rs);
            }

        } catch (FontFormatException ex) {
            Logger.getLogger(JFullScreenIndivRank.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.getGraphicsConfiguration().getDevice().setFullScreenWindow(this);
    }

    private void buildPanel(ArrayList<Ranked> rankeds) throws FontFormatException {

        JPanel jpn = new JPanel();
        GridBagLayout gbl = new GridBagLayout();
        jpn.setLayout(gbl);

        Font font;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, this.getClass().getResourceAsStream("/tourma/languages/calibri.ttf"));
        } catch (IOException ex) {
            Logger.getLogger(JFullScreenIndivRank.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            font = this.getFont();
        }

        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int width = gd.getDisplayMode().getWidth();
        int height = gd.getDisplayMode().getHeight();

        float size = (float) height / 50;
        Font f = font.deriveFont(Font.PLAIN, size);

        int computed_height = height / 20;
        int computed_width = width / 6;

        int line = 0;

        for (int i = 0; i < rankeds.size() / 3; i++) {
            Ranked ranked0 = rankeds.get(3 * i);
            Ranked ranked1 = rankeds.get(3 * i + 1);
            Ranked ranked2 = rankeds.get(3 * i + 2);

            Criteria crit;
            if (ranked0 instanceof MjtAnnexRank) {
                crit = ((MjtAnnexRank) ranked0).getCriteria();
            } else {
                if (ranked0 instanceof Ranking) {
                    crit = ((Ranking) ranked0).getCriteria();
                } else {
                    crit = new Criteria("???");
                }
            }

            int column = 0;
            // Title

            JLabel titleNumber = new JLabel("#");
            titleNumber.setHorizontalAlignment(JLabel.CENTER);
            titleNumber.setBackground(Color.BLACK);
            titleNumber.setForeground(Color.WHITE);
            titleNumber.setOpaque(true);
            jpn.add(titleNumber, getGridbBagConstraints(column, line, 1, 1));
            column++;

            JLabel titleCoach = new JLabel(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("Team"));
            if (!team) {
                titleCoach.setText(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("Clan"));
            }

            titleCoach.setHorizontalAlignment(JLabel.CENTER);
            titleCoach.setBackground(Color.BLACK);
            titleCoach.setForeground(Color.WHITE);
            titleCoach.setOpaque(true);
            jpn.add(titleCoach, getGridbBagConstraints(column, line, 1, 5));
            column += 5;

            JLabel titleValue = new JLabel("+ " + crit.getName());
            titleValue.setHorizontalAlignment(JLabel.CENTER);
            titleValue.setBackground(Color.BLACK);
            titleValue.setForeground(Color.WHITE);
            titleValue.setOpaque(true);
            jpn.add(titleValue, getGridbBagConstraints(column, line, 1, 5));
            column += 5;

            JLabel emptyT = new JLabel("");
            emptyT.setBackground(Color.WHITE);
            emptyT.setOpaque(true);
            jpn.add(emptyT, getGridbBagConstraints(column, line, 1, 1));
            column++;

            JLabel titleNumber2 = new JLabel("#");
            titleNumber2.setHorizontalAlignment(JLabel.CENTER);
            titleNumber2.setBackground(Color.BLACK);
            titleNumber2.setForeground(Color.WHITE);
            titleNumber2.setOpaque(true);
            jpn.add(titleNumber2, getGridbBagConstraints(column, line, 1, 1));
            column++;

            JLabel titleCoach2 = new JLabel(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("Team"));
            if (!team) {
                titleCoach2.setText(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("Clan"));
            }

            titleCoach2.setHorizontalAlignment(JLabel.CENTER);
            titleCoach2.setBackground(Color.BLACK);
            titleCoach2.setForeground(Color.WHITE);
            titleCoach2.setOpaque(true);
            jpn.add(titleCoach2, getGridbBagConstraints(column, line, 1, 5));
            column += 5;

            JLabel titleValue2 = new JLabel("- " + crit.getName());
            titleValue2.setHorizontalAlignment(JLabel.CENTER);
            titleValue2.setBackground(Color.BLACK);
            titleValue2.setForeground(Color.WHITE);
            titleValue2.setOpaque(true);
            jpn.add(titleValue2, getGridbBagConstraints(column, line, 1, 5));
            column += 5;

            JLabel emptyT3 = new JLabel("");
            emptyT3.setBackground(Color.WHITE);
            emptyT3.setOpaque(true);
            jpn.add(emptyT3, getGridbBagConstraints(column, line, 1, 1));
            column++;

            JLabel titleNumber3 = new JLabel("#");
            titleNumber3.setHorizontalAlignment(JLabel.CENTER);
            titleNumber3.setBackground(Color.BLACK);
            titleNumber3.setForeground(Color.WHITE);
            titleNumber3.setOpaque(true);
            jpn.add(titleNumber3, getGridbBagConstraints(column, line, 1, 1));
            column++;

            JLabel titleCoach3 = new JLabel(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("Team"));
            if (!team) {
                titleCoach3.setText(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("Clan"));
            }
            titleCoach3.setHorizontalAlignment(JLabel.CENTER);
            titleCoach3.setBackground(Color.BLACK);
            titleCoach3.setForeground(Color.WHITE);
            titleCoach3.setOpaque(true);
            jpn.add(titleCoach3, getGridbBagConstraints(column, line, 1, 5));
            column += 5;

            JLabel titleValue3 = new JLabel("diff " + crit.getName());
            titleValue3.setHorizontalAlignment(JLabel.CENTER);
            titleValue3.setBackground(Color.BLACK);
            titleValue3.setForeground(Color.WHITE);
            titleValue3.setOpaque(true);
            jpn.add(titleValue3, getGridbBagConstraints(column, line, 1, 5));
            column += 5;

            line++;

            int line_pos = line;
            int line_diff = line;
            int line_neg = line;
            int nb_col = column;

            for (int j = 0; j < ranked0.getRowCount(); j++) {
                Color bkg = new Color(255, 255, 255);
                if (j % 2 == 0) {
                    bkg = new Color(220, 220, 220);
                }
                column = 0;
                ObjectRanking obj = ranked0.getSortedObject(j);
                if (obj instanceof ObjectAnnexRanking) {
                    ObjectAnnexRanking annex = (ObjectAnnexRanking) obj;
                    IWithNameAndPicture objAnnex = (IWithNameAndPicture) annex.getObject();

                    JLabel number = new JLabel("" + (j + 1));
                    number.setBackground(bkg);
                    number.setOpaque(true);
                    jpn.add(number, getGridbBagConstraints(column, line_pos, 1, 1));
                    column++;

                    JLabel lcoach = getLabelForObject(objAnnex, computed_height, computed_width, f, bkg);
                    jpn.add(lcoach, getGridbBagConstraints(column, line_pos, 1, 5));
                    column += 5;

                    JLabel value = new JLabel(crit.getName() + ": + " + annex.getValue());
                    value.setFont(f);
                    value.setOpaque(true);
                    value.setBackground(bkg);
                    jpn.add(value, getGridbBagConstraints(column, line_pos, 1, 5));
                    column += 5;

                    line_pos++;
                }
            }

            for (int j = 0; j < ranked1.getRowCount(); j++) {
                Color bkg = new Color(255, 255, 255);
                if (j % 2 == 0) {
                    bkg = new Color(220, 220, 220);
                }
                column = nb_col / 3;

                JLabel empty = new JLabel("");
                empty.setBackground(Color.WHITE);
                empty.setOpaque(true);
                jpn.add(empty, getGridbBagConstraints(column, line_neg, 1, 1));
                column++;

                ObjectRanking obj = ranked1.getSortedObject(j);
                if (obj instanceof ObjectAnnexRanking) {
                    ObjectAnnexRanking annex = (ObjectAnnexRanking) obj;
                    IWithNameAndPicture coach = (IWithNameAndPicture) annex.getObject();

                    JLabel number = new JLabel("" + (j + 1));
                    number.setBackground(bkg);
                    number.setOpaque(true);
                    jpn.add(number, getGridbBagConstraints(column, line_neg, 1, 1));
                    column++;

                    JLabel lcoach = getLabelForObject(coach, computed_height, computed_width, f, bkg);
                    jpn.add(lcoach, getGridbBagConstraints(column, line_neg, 1, 5));
                    column += 5;

                    JLabel value = new JLabel(crit.getName() + ": - " + annex.getValue());
                    value.setFont(f);
                    value.setOpaque(true);
                    value.setBackground(bkg);
                    jpn.add(value, getGridbBagConstraints(column, line_neg, 1, 5));
                    column += 5;

                    line_neg++;
                }
            }

            for (int j = 0; j < ranked2.getRowCount(); j++) {
                Color bkg = new Color(255, 255, 255);
                if (j % 2 == 0) {
                    bkg = new Color(220, 220, 220);
                }
                column = 2 * nb_col / 3;

                JLabel empty = new JLabel("");
                empty.setBackground(Color.WHITE);
                empty.setOpaque(true);
                jpn.add(empty, getGridbBagConstraints(column, line_diff, 1, 1));
                column++;

                ObjectRanking obj = ranked2.getSortedObject(j);
                if (obj instanceof ObjectAnnexRanking) {
                    ObjectAnnexRanking annex = (ObjectAnnexRanking) obj;
                    IWithNameAndPicture coach = (IWithNameAndPicture) annex.getObject();

                    JLabel number = new JLabel("" + (j + 1));
                    number.setBackground(bkg);
                    number.setOpaque(true);
                    jpn.add(number, getGridbBagConstraints(column, line_diff, 1, 1));
                    column++;

                    JLabel lcoach = getLabelForObject(coach, computed_height, computed_width, f, bkg);
                    jpn.add(lcoach, getGridbBagConstraints(column, line_diff, 1, 5));
                    column += 5;

                    JLabel value = new JLabel(crit.getName() + ": +/- " + annex.getValue());
                    value.setFont(f);
                    value.setOpaque(true);
                    value.setBackground(bkg);
                    jpn.add(value, getGridbBagConstraints(column, line_diff, 1, 5));
                    column += 5;

                    line_diff++;
                }
            }
            line = Math.max(line_pos, line_neg);
            line = Math.max(line, line_diff);
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
    private static final Logger LOG = Logger.getLogger(JFullScreenClanTeamAnnex.class.getName());
}
