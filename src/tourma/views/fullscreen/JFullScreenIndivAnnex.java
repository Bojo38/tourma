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
import tourma.data.Coach;
import tourma.data.Criteria;
import tourma.data.ObjectAnnexRanking;
import tourma.data.ObjectRanking;
import tourma.data.Pool;
import tourma.data.Ranking;
import tourma.data.Tournament;
import tourma.languages.Translate;
import tourma.tableModel.MjtAnnexRank;
import tourma.tableModel.MjtAnnexRankIndiv;
import tourma.utils.display.TourmaProtocol;
import static tourma.views.fullscreen.JFullScreenIndivRank.C_CATEGORY;
import static tourma.views.fullscreen.JFullScreenIndivRank.C_GENERAL;
import static tourma.views.fullscreen.JFullScreenIndivRank.C_GROUP;
import static tourma.views.fullscreen.JFullScreenIndivRank.C_POOL;
import tourma.utils.display.IRanked;

/**
 *
 * @author WFMJ7631
 */
public final class JFullScreenIndivAnnex extends JFullScreen {

    private int round;
    private int indivRankType = C_GENERAL;
    private boolean loopStop = false;

    public JFullScreenIndivAnnex(Socket s) throws IOException {
        this(s, C_GENERAL);
    }

    public JFullScreenIndivAnnex(Socket s, int type) throws IOException {
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
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            if (indivRankType == C_GENERAL) {
                out.println(TourmaProtocol.TKey.INDIVIDUAL_ANNEX.toString());
            } else {
                if (indivRankType == C_GROUP) {
                    out.println(TourmaProtocol.TKey.GROUP_ANNEX.toString());
                } else {
                    if (indivRankType == C_CATEGORY) {
                        out.println(TourmaProtocol.TKey.CATEGORY_ANNEX.toString());
                    } else {
                        if (indivRankType == C_POOL) {
                            out.println(TourmaProtocol.TKey.CATEGORY_ANNEX.toString());
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
                                Logger.getLogger(JFullScreenTeamRank.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            Document doc = sb.build(new StringReader(buffer));
                            Element element = doc.getRootElement();
                            ArrayList<IRanked> rs = new ArrayList<>();

                            List<Element> elements = element.getChildren();
                            Iterator<Element> it = elements.iterator();
                            while (it.hasNext()) {
                                Element el = it.next();
                                r = new Ranking(el);
                                rs.add(r);
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
                        if (indivRankType == C_GENERAL) {
                            out.println(TourmaProtocol.TKey.INDIVIDUAL_ANNEX.toString());
                        } else {
                            if (indivRankType == C_GROUP) {
                                out.println(TourmaProtocol.TKey.GROUP_ANNEX.toString());
                            } else {
                                if (indivRankType == C_CATEGORY) {
                                    out.println(TourmaProtocol.TKey.CATEGORY_ANNEX.toString());
                                } else {
                                    if (indivRankType == C_POOL) {
                                        out.println(TourmaProtocol.TKey.CATEGORY_ANNEX.toString());
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

    public JFullScreenIndivAnnex(int r, boolean full) throws IOException {
        this(r, full, C_GENERAL);
    }

    /**
     *
     * @param r
     * @param full
     * @throws IOException
     */
    @SuppressWarnings("LeakingThisInConstructor")
    public JFullScreenIndivAnnex(int r, boolean full, int type) throws IOException {
        super();
        initComponents();
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
        this.indivRankType = type;
        try {
            round = r;
            ArrayList<IRanked> rs = new ArrayList<>();
            if (indivRankType != C_POOL) {
                final ArrayList<Coach> coaches = new ArrayList<>();
                for (int cpt = 0; cpt < Tournament.getTournament().getCoachsCount(); cpt++) {
                    coaches.add(Tournament.getTournament().getCoach(cpt));
                }

                for (int i = 0; i < Tournament.getTournament().getParams().getCriteriaCount(); i++) {
                    Criteria crit = Tournament.getTournament().getParams().getCriteria(i);

                    MjtAnnexRankIndiv annexRank0 = new MjtAnnexRankIndiv(round, crit, 0,
                            coaches, full, Tournament.getTournament().getParams().getRankingIndiv1(), Tournament.getTournament().getParams().getRankingIndiv2(), Tournament.getTournament().getParams().getRankingIndiv3(), Tournament.getTournament().getParams().getRankingIndiv4(), Tournament.getTournament().getParams().getRankingIndiv5(), Tournament.getTournament().getParams().isTeamTournament(),
                            false);
                    MjtAnnexRankIndiv annexRank1 = new MjtAnnexRankIndiv(round, crit, 1,
                            coaches, full, Tournament.getTournament().getParams().getRankingIndiv1(), Tournament.getTournament().getParams().getRankingIndiv2(), Tournament.getTournament().getParams().getRankingIndiv3(), Tournament.getTournament().getParams().getRankingIndiv4(), Tournament.getTournament().getParams().getRankingIndiv5(), Tournament.getTournament().getParams().isTeamTournament(),
                            false);
                    MjtAnnexRankIndiv annexRank2 = new MjtAnnexRankIndiv(round, crit, 2,
                            coaches, full, Tournament.getTournament().getParams().getRankingIndiv1(), Tournament.getTournament().getParams().getRankingIndiv2(), Tournament.getTournament().getParams().getRankingIndiv3(), Tournament.getTournament().getParams().getRankingIndiv4(), Tournament.getTournament().getParams().getRankingIndiv5(), Tournament.getTournament().getParams().isTeamTournament(),
                            false);

                    rs.add(annexRank0);
                    rs.add(annexRank1);
                    rs.add(annexRank2);
                }
            } else {
                for (int cpt = 0; cpt < Tournament.getTournament().getPoolCount(); cpt++) {
                    Pool p = Tournament.getTournament().getPool(cpt);
                    final ArrayList<Coach> coaches = new ArrayList<>();
                    for (int cpt2 = 0; cpt2 < p.getCompetitorCount(); cpt2++) {
                        coaches.add((Coach) p.getCompetitor(cpt2));
                    }
                    for (int i = 0; i < Tournament.getTournament().getParams().getCriteriaCount(); i++) {
                        Criteria crit = Tournament.getTournament().getParams().getCriteria(i);

                        MjtAnnexRankIndiv annexRank0 = new MjtAnnexRankIndiv(round, crit, 0,
                                coaches, full, Tournament.getTournament().getParams().getRankingIndiv1(), Tournament.getTournament().getParams().getRankingIndiv2(), Tournament.getTournament().getParams().getRankingIndiv3(), Tournament.getTournament().getParams().getRankingIndiv4(), Tournament.getTournament().getParams().getRankingIndiv5(), Tournament.getTournament().getParams().isTeamTournament(),
                                false);
                        MjtAnnexRankIndiv annexRank1 = new MjtAnnexRankIndiv(round, crit, 1,
                                coaches, full, Tournament.getTournament().getParams().getRankingIndiv1(), Tournament.getTournament().getParams().getRankingIndiv2(), Tournament.getTournament().getParams().getRankingIndiv3(), Tournament.getTournament().getParams().getRankingIndiv4(), Tournament.getTournament().getParams().getRankingIndiv5(), Tournament.getTournament().getParams().isTeamTournament(),
                                false);
                        MjtAnnexRankIndiv annexRank2 = new MjtAnnexRankIndiv(round, crit, 2,
                                coaches, full, Tournament.getTournament().getParams().getRankingIndiv1(), Tournament.getTournament().getParams().getRankingIndiv2(), Tournament.getTournament().getParams().getRankingIndiv3(), Tournament.getTournament().getParams().getRankingIndiv4(), Tournament.getTournament().getParams().getRankingIndiv5(), Tournament.getTournament().getParams().isTeamTournament(),
                                false);
                        annexRank0.setDetail(Integer.toString(cpt + 1));
                        annexRank1.setDetail(Integer.toString(cpt + 1));
                        annexRank2.setDetail(Integer.toString(cpt + 1));
                        rs.add(annexRank0);
                        rs.add(annexRank1);
                        rs.add(annexRank2);
                    }
                }
            }
            buildPanel(rs, screen);

        } catch (FontFormatException ex) {
            Logger.getLogger(JFullScreenIndivRank.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        mSelectedGD.setFullScreenWindow(this);
    }

    private final static String CS_Pool = "Pool";
    private final static String CS_Team = "Team";
    private final static String CS_Clan = "Clan";
    private final static String CS_Coach = "Coach";

    private void buildPanel(ArrayList<IRanked> rankeds, int screen) throws FontFormatException {

        JPanel jpn = new JPanel();
        GridBagLayout gbl = new GridBagLayout();
        jpn.setLayout(gbl);

        Font font;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, this.getClass().getResourceAsStream("/tourma/languages/calibri.ttf"));

        } catch (IOException ex) {
            Logger.getLogger(JFullScreenIndivRank.class
                    .getName()).log(Level.SEVERE, null, ex);
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
            try {
                IRanked ranked0 = rankeds.get(3 * i);
                IRanked ranked1 = rankeds.get(3 * i + 1);
                IRanked ranked2 = rankeds.get(3 * i + 2);

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

                if (indivRankType == C_POOL) {
                    JLabel titlePool = new JLabel(Translate.translate(CS_Pool));
                    titlePool.setHorizontalAlignment(JLabel.CENTER);
                    titlePool.setBackground(Color.BLACK);
                    titlePool.setForeground(Color.WHITE);
                    titlePool.setOpaque(true);
                    jpn.add(titlePool, getGridbBagConstraints(column, line, 1, 1));
                    column++;
                }

                if (Tournament.getTournament().getParams().isTeamTournament()) {
                    JLabel titleTeam = new JLabel(Translate.translate(CS_Team));
                    titleTeam.setHorizontalAlignment(JLabel.CENTER);
                    titleTeam.setBackground(Color.BLACK);
                    titleTeam.setForeground(Color.WHITE);
                    titleTeam.setOpaque(true);
                    jpn.add(titleTeam, getGridbBagConstraints(column, line, 1, 5));
                    column += 5;
                }

                if (Tournament.getTournament().getClansCount() > 1) {
                    JLabel titleTeam = new JLabel(Translate.translate(CS_Clan));
                    titleTeam.setHorizontalAlignment(JLabel.CENTER);
                    titleTeam.setBackground(Color.BLACK);
                    titleTeam.setForeground(Color.WHITE);
                    titleTeam.setOpaque(true);
                    jpn.add(titleTeam, getGridbBagConstraints(column, line, 1, 5));
                    column += 5;
                }

                JLabel titleCoach = new JLabel(Translate.translate(CS_Clan));
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

                if (indivRankType == C_POOL) {
                    JLabel titlePool = new JLabel(Translate.translate(CS_Pool));
                    titlePool.setHorizontalAlignment(JLabel.CENTER);
                    titlePool.setBackground(Color.BLACK);
                    titlePool.setForeground(Color.WHITE);
                    titlePool.setOpaque(true);
                    jpn.add(titlePool, getGridbBagConstraints(column, line, 1, 1));
                    column++;
                }

                if (Tournament.getTournament().getParams().isTeamTournament()) {
                    JLabel titleTeam2 = new JLabel(Translate.translate(CS_Team));
                    titleTeam2.setHorizontalAlignment(JLabel.CENTER);
                    titleTeam2.setBackground(Color.BLACK);
                    titleTeam2.setForeground(Color.WHITE);
                    titleTeam2.setOpaque(true);
                    jpn.add(titleTeam2, getGridbBagConstraints(column, line, 1, 5));
                    column += 5;
                }

                if (Tournament.getTournament().getClansCount() > 1) {
                    JLabel titleTeam2 = new JLabel(Translate.translate(CS_Clan));
                    titleTeam2.setHorizontalAlignment(JLabel.CENTER);
                    titleTeam2.setBackground(Color.BLACK);
                    titleTeam2.setForeground(Color.WHITE);
                    titleTeam2.setOpaque(true);
                    jpn.add(titleTeam2, getGridbBagConstraints(column, line, 1, 5));
                    column += 5;
                }

                JLabel titleCoach2 = new JLabel(Translate.translate(CS_Coach));
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

                if (indivRankType == C_POOL) {
                    JLabel titlePool = new JLabel(Translate.translate(CS_Pool));
                    titlePool.setHorizontalAlignment(JLabel.CENTER);
                    titlePool.setBackground(Color.BLACK);
                    titlePool.setForeground(Color.WHITE);
                    titlePool.setOpaque(true);
                    jpn.add(titlePool, getGridbBagConstraints(column, line, 1, 1));
                    column++;
                }

                if (Tournament.getTournament().getParams().isTeamTournament()) {
                    JLabel titleTeam3 = new JLabel(Translate.translate(CS_Team));
                    titleTeam3.setHorizontalAlignment(JLabel.CENTER);
                    titleTeam3.setBackground(Color.BLACK);
                    titleTeam3.setForeground(Color.WHITE);
                    titleTeam3.setOpaque(true);
                    jpn.add(titleTeam3, getGridbBagConstraints(column, line, 1, 5));
                    column += 5;
                }

                if (Tournament.getTournament().getClansCount() > 1) {
                    JLabel titleTeam3 = new JLabel(Translate.translate(CS_Clan));
                    titleTeam3.setHorizontalAlignment(JLabel.CENTER);
                    titleTeam3.setBackground(Color.BLACK);
                    titleTeam3.setForeground(Color.WHITE);
                    titleTeam3.setOpaque(true);
                    jpn.add(titleTeam3, getGridbBagConstraints(column, line, 1, 5));
                    column += 5;
                }

                JLabel titleCoach3 = new JLabel(Translate.translate(CS_Coach));
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
                        Coach coach = (Coach) annex.getObject();

                        JLabel number = new JLabel("" + (j + 1));
                        number.setBackground(bkg);
                        number.setOpaque(true);
                        jpn.add(number, getGridbBagConstraints(column, line_pos, 1, 1));
                        column++;

                        if (indivRankType == C_POOL) {
                            JLabel pool = new JLabel(ranked0.getDetail());
                            pool.setBackground(bkg);
                            pool.setOpaque(true);
                            jpn.add(pool, getGridbBagConstraints(column, line_pos, 1, 1));
                            column++;
                        }

                        if (Tournament.getTournament().getParams().isTeamTournament()) {
                            JLabel team = getLabelForObject(coach.getTeamMates(), computed_height, computed_width, f, bkg);
                            jpn.add(team, getGridbBagConstraints(column, line_pos, 1, 5));
                            column += 5;
                        }

                        if (Tournament.getTournament().getClansCount() > 1) {
                            JLabel team = getLabelForObject(coach.getClan(), computed_height, computed_width, f, bkg);
                            jpn.add(team, getGridbBagConstraints(column, line_pos, 1, 5));
                            column += 5;
                        }

                        JLabel lcoach = getLabelForObject(coach, computed_height, computed_width, f, bkg);
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
                        Coach coach = (Coach) annex.getObject();

                        JLabel number = new JLabel("" + (j + 1));
                        number.setBackground(bkg);
                        number.setOpaque(true);
                        jpn.add(number, getGridbBagConstraints(column, line_neg, 1, 1));
                        column++;

                        if (indivRankType == C_POOL) {
                            JLabel pool = new JLabel(ranked1.getDetail());
                            pool.setBackground(bkg);
                            pool.setOpaque(true);
                            jpn.add(pool, getGridbBagConstraints(column, line_neg, 1, 1));
                            column++;
                        }

                        if (Tournament.getTournament().getParams().isTeamTournament()) {
                            JLabel team = getLabelForObject(coach.getTeamMates(), computed_height, computed_width, f, bkg);
                            jpn.add(team, getGridbBagConstraints(column, line_neg, 1, 5));
                            column += 5;
                        }

                        if (Tournament.getTournament().getClansCount() > 1) {
                            JLabel team = getLabelForObject(coach.getClan(), computed_height, computed_width, f, bkg);
                            jpn.add(team, getGridbBagConstraints(column, line_neg, 1, 5));
                            column += 5;
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

                    if (indivRankType == C_POOL) {
                        JLabel pool = new JLabel(ranked2.getDetail());
                        pool.setBackground(bkg);
                        pool.setOpaque(true);
                        jpn.add(pool, getGridbBagConstraints(column, line_diff, 1, 1));
                        column++;
                    }

                    ObjectRanking obj = ranked2.getSortedObject(j);
                    if (obj instanceof ObjectAnnexRanking) {
                        ObjectAnnexRanking annex = (ObjectAnnexRanking) obj;
                        Coach coach = (Coach) annex.getObject();

                        JLabel number = new JLabel("" + (j + 1));
                        number.setBackground(bkg);
                        number.setOpaque(true);
                        jpn.add(number, getGridbBagConstraints(column, line_diff, 1, 1));
                        column++;

                        if (Tournament.getTournament().getParams().isTeamTournament()) {
                            JLabel team = getLabelForObject(coach.getTeamMates(), computed_height, computed_width, f, bkg);
                            jpn.add(team, getGridbBagConstraints(column, line_diff, 1, 5));
                            column += 5;
                        }

                        if (Tournament.getTournament().getClansCount() > 1) {
                            JLabel team = getLabelForObject(coach.getClan(), computed_height, computed_width, f, bkg);
                            jpn.add(team, getGridbBagConstraints(column, line_diff, 1, 5));
                            column += 5;
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
    private static final Logger LOG = Logger.getLogger(JFullScreenIndivAnnex.class
            .getName());
}
