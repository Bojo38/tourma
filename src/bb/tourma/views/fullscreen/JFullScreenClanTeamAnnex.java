/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bb.tourma.views.fullscreen;

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
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import bb.tourma.data.Clan;
import bb.tourma.data.Criterion;
import bb.tourma.data.Formula;
import bb.tourma.data.IWithNameAndPicture;
import bb.tourma.data.ObjectAnnexRanking;
import bb.tourma.data.ObjectRanking;
import bb.tourma.data.Parameters;
import bb.tourma.data.Pool;
import bb.tourma.data.RankingForExport;
import bb.tourma.data.Round;
import bb.tourma.data.Team;
import bb.tourma.data.Tournament;
import bb.tourma.data.ranking.AnnexClanRanking;
import bb.tourma.data.ranking.AnnexRanking;
import bb.tourma.data.ranking.AnnexTeamRanking;
import bb.tourma.languages.Translate;
import bb.tourma.tableModel.MjtAnnexRank;
import bb.tourma.tableModel.MjtAnnexRankClan;
import bb.tourma.tableModel.MjtAnnexRankTeam;
import bb.tourma.utils.display.TourmaProtocol;
import bb.tourma.utils.display.IRanked;

/**
 *
 * @author WFMJ7631
 */
public final class JFullScreenClanTeamAnnex extends JFullScreen {

    private int round;
    private Round mRound;

    private boolean loopStop = false;
    private boolean team;

    private boolean forPool = false;

    public JFullScreenClanTeamAnnex(Socket s, boolean team) throws IOException {
        this(s, team, false);
    }

    public JFullScreenClanTeamAnnex(Socket s, boolean team, boolean pool) throws IOException {
        super(s);
        initComponents();
        this.team = team;
        loopStop = false;
        forPool = pool;
        // Synchronized the end of constructor with the client thread
        semStart.release();

    }

    @Override
    protected void clientLoop(int screen) throws InterruptedException {
        // Synchronized the end of constructor with the client thread
        semStart.acquire();

        try {

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            if (team) {
                if (!forPool) {
                    out.println(TourmaProtocol.TKey.TEAM_ANNEX.toString());
                } else {
                    out.println(TourmaProtocol.TKey.POOL_TEAM_ANNEX.toString());
                }
            } else {
                out.println(TourmaProtocol.TKey.CLAN_ANNEX.toString());
            }
            out.println(TourmaProtocol.TKey.END.toString());

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            socket.getInputStream()));

            RankingForExport r;
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
                                ArrayList<IRanked> rs = new ArrayList<>();

                                List<Element> elements = element.getChildren();
                                Iterator<Element> it = elements.iterator();
                                while (it.hasNext()) {
                                    Element el = it.next();
                                    r = new RankingForExport(el);
                                    rs.add(r);
                                }
                                buildPanel(rs);
                            }
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
                        if (team) {
                            if (!forPool) {
                                out.println(TourmaProtocol.TKey.TEAM_ANNEX.toString());
                            } else {
                                out.println(TourmaProtocol.TKey.POOL_TEAM_ANNEX.toString());
                            }
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

    public JFullScreenClanTeamAnnex(int r, boolean full, boolean team
    ) throws IOException {
        this(r, full, team, false);
    }

    /**
     *
     * @param r
     * @param full
     * @param team
     * @throws IOException
     */
    @SuppressWarnings("LeakingThisInConstructor")
    public JFullScreenClanTeamAnnex(int r, boolean full, boolean team, boolean pool) throws IOException {
        super();
        initComponents();
        this.team = team;
        forPool = pool;
        round = r;
        mRound = Tournament.getTournament().getRound(r);

        try {

            if (team) {
                ArrayList<IRanked> rs = new ArrayList<>();

                if (!pool) {

                    for (int i = 0; i < Tournament.getTournament().getParams().getCriteriaCount(); i++) {
                        Criterion crit = Tournament.getTournament().getParams().getCriterion(i);
                        AnnexTeamRanking annexRank0 = mRound.getRankings(false).getTeamRankingSet().getAnnexPosRanking().get(crit);
                        AnnexTeamRanking annexRank1 = mRound.getRankings(false).getTeamRankingSet().getAnnexNegRanking().get(crit);
                        AnnexTeamRanking annexRank2 = mRound.getRankings(false).getTeamRankingSet().getAnnexDifRanking().get(crit);

                        rs.add(annexRank0);
                        rs.add(annexRank1);
                        rs.add(annexRank2);
                    }

                    for (int i = 0; i < Tournament.getTournament().getParams().getFormulaCount(); i++) {
                        Formula form = Tournament.getTournament().getParams().getFormula(i);
                        AnnexTeamRanking annexRank0 = mRound.getRankings(false).getTeamRankingSet().getAnnexFormRanking().get(form);
                        rs.add(annexRank0);

                    }
                } else {
                    for (int cpt = 0; cpt < Tournament.getTournament().getPoolCount(); cpt++) {
                        Pool p = Tournament.getTournament().getPool(cpt);

                        for (int i = 0; i < Tournament.getTournament().getParams().getCriteriaCount(); i++) {
                            Criterion crit = Tournament.getTournament().getParams().getCriterion(i);

                            AnnexTeamRanking annexRank0 = mRound.getRankings(false).getPoolTeamRankings().get(p).getAnnexPosRanking().get(crit);
                            AnnexTeamRanking annexRank1 = mRound.getRankings(false).getPoolTeamRankings().get(p).getAnnexNegRanking().get(crit);
                            AnnexTeamRanking annexRank2 = mRound.getRankings(false).getPoolTeamRankings().get(p).getAnnexDifRanking().get(crit);

                            annexRank0.setDetail(Integer.toString(cpt + 1));
                            annexRank1.setDetail(Integer.toString(cpt + 1));
                            annexRank2.setDetail(Integer.toString(cpt + 1));

                            rs.add(annexRank0);
                            rs.add(annexRank1);
                            rs.add(annexRank2);
                        }
                    }
                }
                buildPanel(rs);

            } else {

                ArrayList<IRanked> rs = new ArrayList<>();
                for (int i = 0; i < Tournament.getTournament().getParams().getCriteriaCount(); i++) {
                    Criterion crit = Tournament.getTournament().getParams().getCriterion(i);
                     AnnexClanRanking annexRank0 = mRound.getRankings(false).getClanRankingSet().getAnnexPosRanking().get(crit);
                     AnnexClanRanking annexRank1 = mRound.getRankings(false).getClanRankingSet().getAnnexNegRanking().get(crit);
                     AnnexClanRanking annexRank2 = mRound.getRankings(false).getClanRankingSet().getAnnexDifRanking().get(crit);

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

    private final static String CS_Clan = "Clan";
    private final static String CS_Team = "Team";
    private final static String CS_Pool = "Pool";

    private void buildPanel(ArrayList<IRanked> rankeds) throws FontFormatException {

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

        int width = mSelectedGD.getDisplayMode().getWidth();
        int height = mSelectedGD.getDisplayMode().getHeight();

        float size = (float) height / 50;
        Font f = font.deriveFont(Font.PLAIN, size);

        int computed_height = height / 20;
        int computed_width = width / 6;

        int line = 0;

        for (int i = 0; i < rankeds.size() / 3; i++) {
            IRanked ranked0 = rankeds.get(3 * i);
            IRanked ranked1 = rankeds.get(3 * i + 1);
            IRanked ranked2 = rankeds.get(3 * i + 2);
            try {
                Criterion crit;
                Formula form;
                if (ranked0 instanceof AnnexRanking) {
                    crit = ((AnnexRanking) ranked0).getCriterion();
                    if (crit == null) {
                        form = ((AnnexRanking) ranked0).getFormula();
                    }
                } else {
                    if (ranked0 instanceof RankingForExport) {
                        crit = ((RankingForExport) ranked0).getCriterion();
                        if (crit == null) {
                            form = ((RankingForExport) ranked0).getFormula();
                        }
                    } else {
                        crit = new Criterion("???");
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

                if (forPool) {
                    JLabel titlePool = new JLabel(Translate.translate(CS_Pool));
                    titlePool.setHorizontalAlignment(JLabel.CENTER);
                    titlePool.setBackground(Color.BLACK);
                    titlePool.setForeground(Color.WHITE);
                    titlePool.setOpaque(true);
                    jpn.add(titlePool, getGridbBagConstraints(column, line, 1, 1));
                    column++;
                }

                JLabel titleCoach = new JLabel(Translate.translate(CS_Team));
                if (!team) {
                    titleCoach.setText(Translate.translate(CS_Clan));
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

                if (forPool) {
                    JLabel titlePool2 = new JLabel(Translate.translate(CS_Pool));
                    titlePool2.setHorizontalAlignment(JLabel.CENTER);
                    titlePool2.setBackground(Color.BLACK);
                    titlePool2.setForeground(Color.WHITE);
                    titlePool2.setOpaque(true);
                    jpn.add(titlePool2, getGridbBagConstraints(column, line, 1, 1));
                    column++;
                }

                JLabel titleCoach2 = new JLabel(Translate.translate(CS_Team));
                if (!team) {
                    titleCoach2.setText(Translate.translate(CS_Clan));
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

                if (forPool) {
                    JLabel titlePool3 = new JLabel(Translate.translate(CS_Pool));
                    titlePool3.setHorizontalAlignment(JLabel.CENTER);
                    titlePool3.setBackground(Color.BLACK);
                    titlePool3.setForeground(Color.WHITE);
                    titlePool3.setOpaque(true);
                    jpn.add(titlePool3, getGridbBagConstraints(column, line, 1, 1));
                    column++;
                }

                JLabel titleCoach3 = new JLabel(Translate.translate(CS_Team));
                if (!team) {
                    titleCoach3.setText(Translate.translate(CS_Clan));
                }
                titleCoach3.setHorizontalAlignment(JLabel.CENTER);
                titleCoach3.setBackground(Color.BLACK);
                titleCoach3.setForeground(Color.WHITE);
                titleCoach3.setOpaque(true);
                jpn.add(titleCoach3, getGridbBagConstraints(column, line, 1, 5));
                column += 5;

                JLabel titleValue3 = new JLabel("+/- " + crit.getName());
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

                        if (forPool) {
                            JLabel pool = new JLabel(ranked0.getDetail());
                            pool.setBackground(bkg);
                            pool.setOpaque(true);
                            jpn.add(pool, getGridbBagConstraints(column, line_pos, 1, 1));
                            column++;
                        }

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

                        if (forPool) {
                            JLabel pool = new JLabel(ranked1.getDetail());
                            pool.setBackground(bkg);
                            pool.setOpaque(true);
                            jpn.add(pool, getGridbBagConstraints(column, line_neg, 1, 1));
                            column++;
                        }

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

                        if (forPool) {
                            JLabel pool = new JLabel(ranked2.getDetail());
                            pool.setBackground(bkg);
                            pool.setOpaque(true);
                            jpn.add(pool, getGridbBagConstraints(column, line_diff, 1, 1));
                            column++;
                        }

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
            } catch (RemoteException re) {
                re.printStackTrace();
            }
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