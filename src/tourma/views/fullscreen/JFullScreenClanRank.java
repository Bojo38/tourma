/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.views.fullscreen;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridBagLayout;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.net.Socket;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import tourma.data.Clan;
import tourma.data.Coach;
import tourma.data.Ranking;
import tourma.data.Tournament;
import tourma.languages.Translate;
import tourma.tableModel.MjtRanking;
import tourma.tableModel.MjtRankingClan;
import tourma.utils.display.TourmaProtocol;
import tourma.utils.display.IRanked;

/**
 *
 * @author WFMJ7631
 */
public final class JFullScreenClanRank extends JFullScreen {

    private static final long serialVersionUID = 10L;

    private int round;

    private boolean loopStop = false;

    public JFullScreenClanRank(Socket s) throws IOException {
        super(s);
        initComponents();
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
            out.println(TourmaProtocol.TKey.CLAN_RANK.toString());
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
                            mSelectedGD.setFullScreenWindow(this);

                        } catch (JDOMException ex) {
                            Logger.getLogger(JFullScreenIndivRank.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        try {
                            Thread.sleep(10000);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(JFullScreenIndivRank.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        out.println(TourmaProtocol.TKey.CLAN_RANK.toString());
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
     * @throws IOException
     */
    @SuppressWarnings("LeakingThisInConstructor")
    public JFullScreenClanRank(int r) throws IOException {
        super();
        initComponents();
        try {
            round = r;
            ArrayList<Clan> teams = new ArrayList<>();
            for (int cpt = 0; cpt < Tournament.getTournament().getClansCount(); cpt++) {
                teams.add(Tournament.getTournament().getClan(cpt));
            }

            MjtRankingClan ranking = new MjtRankingClan(
                    round,
                    teams,
                    false);
            buildPanel(ranking);
        } catch (FontFormatException ex) {
            Logger.getLogger(JFullScreenTeamRank.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.getGraphicsConfiguration().getDevice().setFullScreenWindow(this);
        repaint();
    }

    private static final String CS_Clan = "Clan";
    private static final String CS_Members = "Members";

    private void buildPanel(IRanked ranked) throws FontFormatException {

        Font font;

        JPanel jpn = new JPanel();
        GridBagLayout gbl = new GridBagLayout();
        jpn.setLayout(gbl);

        try {
            //Criteria td = Tournament.getTournament().getParams().getCriteria(0);
            font = Font.createFont(Font.TRUETYPE_FONT, this.getClass().getResourceAsStream("/tourma/languages/calibri.ttf"));
        } catch (IOException ex) {
            Logger.getLogger(JFullScreenClanRank.class.getName()).log(Level.SEVERE, null, ex);
            font = this.getFont();
        }

        int width = mSelectedGD.getDisplayMode().getWidth();
        int height = mSelectedGD.getDisplayMode().getHeight();

        float size = height / ((float) 50.0);
        Font f0 = font.deriveFont(Font.ITALIC, size);
        Font f1 = font.deriveFont(Font.BOLD, size);
        Font f = font.deriveFont(Font.PLAIN, size);

        int computed_height = height / 20;

        int nbCols = 0;
        try {
            if (Tournament.getTournament().getParams().isTeamTournament()) {
                nbCols = Tournament.getTournament().getParams().getTeamRankingNumber() + 3;
            } else {
                nbCols = Tournament.getTournament().getParams().getIndivRankingNumber() + 3;
            }

            ArrayList<Integer> rankings = new ArrayList<>();
            for (int i = 0; i < nbCols - 3; i++) {
                if (Tournament.getTournament().getParams().isTeamTournament()) {
                    rankings.add(Tournament.getTournament().getParams().getTeamRankingType(i));
                } else {
                    rankings.add(Tournament.getTournament().getParams().getIndivRankingType(i));
                }
            }

            int computed_width = width / nbCols;

            // Number
            JLabel jlbTNum = new JLabel("#");
            jlbTNum.setFont(f1);
            jlbTNum.setOpaque(true);
            jlbTNum.setBackground(Color.BLACK);
            jlbTNum.setForeground(Color.WHITE);

            jpn.add(jlbTNum, getGridbBagConstraints(0, 0, 1, 1));

            int index = 1;

            JLabel jlbTCoach = new JLabel(Translate.translate(CS_Clan));
            jlbTCoach.setFont(f1);
            jlbTCoach.setOpaque(true);
            jlbTCoach.setBackground(Color.BLACK);
            jlbTCoach.setForeground(Color.WHITE);
            jpn.add(jlbTCoach, getGridbBagConstraints(index, 0, 1, 5));
            index += 5;

            JLabel jlbTCoachs = new JLabel(Translate.translate(CS_Members));
            jlbTCoachs.setFont(f1);
            jlbTCoachs.setOpaque(true);
            jlbTCoachs.setBackground(Color.BLACK);
            jlbTCoachs.setForeground(Color.WHITE);
            jpn.add(jlbTCoachs, getGridbBagConstraints(index, 0, 1, 5));
            index += 5;

            for (int j = 0; j < rankings.size(); j++) {
                int rankingType = (Integer) rankings.get(j);
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
            int clanIndex = 0;
            Clan NoClan = Tournament.getTournament().getClan(0);
            for (int i = 0; i < nbRows; i++) {
                Clan clan = (Clan) ranked.getSortedObject(i).getObject();
                if (clan == NoClan) {
                    continue;
                }

                Color bkg = new Color(255, 255, 255);
                if (clanIndex % 2 != 0) {
                    bkg = new Color(220, 220, 220);
                }

                // Set font
                Font currentFont = f;
                if (clanIndex == 0) {
                    currentFont = f1;
                }
                if ((clanIndex == 2) || (clanIndex == 1)) {
                    currentFont = f0;
                }

                // Number
                JLabel jlbNum = new JLabel("" + (clanIndex + 1));
                jlbNum.setFont(currentFont);
                jlbNum.setOpaque(true);
                jlbNum.setBackground(bkg);
                jpn.add(jlbNum, getGridbBagConstraints(0, clanIndex + 1, 1, 1));

                index = 1;

                JLabel jlbCoach = getLabelForObject(clan, computed_height, computed_width, currentFont, bkg);
                jpn.add(jlbCoach, getGridbBagConstraints(index, clanIndex + 1, 1, 5));
                index += 5;

                StringBuilder members = new StringBuilder("");
                for (int j = 0; j < Tournament.getTournament().getCoachsCount(); j++) {
                    Coach c = Tournament.getTournament().getCoach(j);
                    if (c.getClan() == clan) {
                        if (!members.toString().isEmpty()) {
                            members.append(" / ");
                        }
                        members.append(c.getName());
                    }
                }
                JLabel jlbMembers = new JLabel(members.toString());
                jlbMembers.setFont(currentFont);
                jlbMembers.setOpaque(true);
                jlbMembers.setBackground(bkg);
                jpn.add(jlbMembers, getGridbBagConstraints(index, clanIndex + 1, 1, 5));
                index += 5;

                for (int j = 0; j < rankings.size(); j++) {
                    int rankingType = (Integer) rankings.get(j);
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
                        jpn.add(jlbRank, getGridbBagConstraints(index, clanIndex + 1, 1, 2));
                        index += 2;
                    }

                }
                clanIndex++;
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
