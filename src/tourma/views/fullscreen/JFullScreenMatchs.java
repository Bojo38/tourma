/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.views.fullscreen;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import tourma.data.Coach;
import tourma.data.CoachMatch;
import tourma.data.Competitor;
import tourma.data.Criteria;
import tourma.data.EIndivPairing;
import tourma.data.Match;
import tourma.data.Round;
import tourma.data.Team;
import tourma.data.TeamMatch;
import tourma.data.Tournament;
import tourma.languages.Translate;
import tourma.utility.Sleeping;
import tourma.utility.StringConstants;
import tourma.utility.Suspendable;
import tourma.utils.ImageTreatment;
import tourma.utils.display.TourmaProtocol;

/**
 *
 * @author WFMJ7631
 */
public final class JFullScreenMatchs extends JFullScreen {

    private Round round;
    private boolean loopStop = false;
    private boolean clash = false;
    private int _screenIndex = 0;

    public JFullScreenMatchs(Socket s) throws IOException {
        this(s, false);
//        this.setState(JFrame.MAXIMIZED_BOTH);
//        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
    }

    public JFullScreenMatchs(Socket s, boolean clash) throws IOException {
        super(s);
        initComponents();
        loopStop = false;
        this.clash = clash;
        semStart.release();
        //      this.setState(JFrame.MAXIMIZED_BOTH);
        //      setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
    }

    @Override
    protected void clientLoop(int screen) throws InterruptedException {
        semStart.acquire();
        try {

            Font font = Font.createFont(Font.TRUETYPE_FONT, this.getClass().getResourceAsStream("/tourma/languages/calibri.ttf"));

            GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[screen];
            _screenIndex = screen;
            int width = gd.getDisplayMode().getWidth();
            int height = gd.getDisplayMode().getHeight();

            float size = (float) height / 50;
            Font f0 = font.deriveFont(Font.ITALIC, size);
            Font f1 = font.deriveFont(Font.BOLD, size);
            Font f = font.deriveFont(Font.PLAIN, size);

            int computed_height = height / 20;

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println(TourmaProtocol.TKey.MATCHS.toString());
            out.println(TourmaProtocol.TKey.END.toString());

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            socket.getInputStream()));

            Round r;
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

                            Tournament tour = Tournament.getTournament();
                            if (tour instanceof Tournament) {

                                Document doc = sb.build(new StringReader(buffer));
                                ((Tournament) tour).loadRosters(doc.getRootElement());
                                Element element = doc.getRootElement().getChild(StringConstants.CS_PARAMETERS);
                                ((Tournament) tour).getParams().setXMLElement(element);
                                element = doc.getRootElement().getChild(StringConstants.CS_ROUND);

                                r = new Round();
                                r.setXMLElementForDisplay(element);

                                if (clash) {
                                    this.round = r;
                                    buildClash(screen);
                                } else {
                                    buildPanel(r, screen);
                                }
                                semAnimate.release();
                            }
                            this.getGraphicsConfiguration().getDevice().setFullScreenWindow(this);

                        } catch (JDOMException ex) {
                            Logger.getLogger(JFullScreenIndivRank.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        if (!animationStarted) {
                            try {
                                Thread.sleep(10000);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(JFullScreenIndivRank.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }

                        out.println(TourmaProtocol.TKey.MATCHS.toString());
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
        }
        semStart.release();
    }

    private void buildClash(int screen) {
        Font font;

        JPanel jpn = new JPanel();
        jpn.setLayout(null);

        getContentPane().removeAll();
        jpnContent = jpn;

        this.getContentPane().add(jpn, BorderLayout.CENTER);
        this.repaint();
    }

    JPanel jpn = new JPanel();

    private void buildPanel(Round r, int display) {

        Font font;

        int fontRatio = 50;

        do {
            jpn = new JPanel();
            GridBagLayout gbl = new GridBagLayout();
            jpn.setLayout(gbl);

            try {
                font = Font.createFont(Font.TRUETYPE_FONT, this.getClass().getResourceAsStream("/tourma/languages/calibri.ttf"));
            } catch (FontFormatException | IOException ex) {
                Logger.getLogger(JFullScreenIndivRank.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                font = this.getFont();
            }

            GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[display];
            int width = gd.getDisplayMode().getWidth();
            int height = gd.getDisplayMode().getHeight();

            Criteria td = Tournament.getTournament().getParams().getCriteria(0);

            float size = (float) height / fontRatio;
            Font f0 = font.deriveFont(Font.ITALIC, size);
            Font f1 = font.deriveFont(Font.BOLD, size);
            Font f1Winner = font.deriveFont(Font.BOLD, size);
            Font f1Draw = font.deriveFont(Font.ITALIC, size);
            Font f1Looser = font.deriveFont(Font.PLAIN, size);
            Font fBig = font.deriveFont(Font.BOLD, (float) height / 20);

            int computed_height = height / 10;
            int nbCols = 3;
            int computed_width = width / nbCols;

            for (int i = 0; i < r.getMatchsCount(); i++) {
                Color bkg = new Color(255, 255, 255);
                if (i % 2 != 0) {
                    bkg = new Color(220, 220, 220);
                }
                Match m = r.getMatch(i);
                if (m instanceof CoachMatch) {
                    CoachMatch cm = (CoachMatch) m;

                    int colIndex = 0;
                    int rowspan = cm.getValueCount();
                    if (Tournament.getTournament().getClansCount() > 1) {
                        nbCols = 5;
                        computed_width = width / nbCols;
                        colIndex = 1;
                        JLabel ClanIcon1 = getLabelForObject((cm.getCompetitor1()).getClan(), computed_height, computed_width, f0, bkg);
                        JLabel ClanIcon2 = getLabelForObject((cm.getCompetitor2()).getClan(), computed_height, computed_width, f0, bkg);

                        ClanIcon1.setHorizontalTextPosition(JLabel.LEADING);
                        ClanIcon1.setHorizontalAlignment(JLabel.RIGHT);
                        ClanIcon2.setHorizontalAlignment(JLabel.LEFT);

                        jpn.add(ClanIcon1, getGridbBagConstraints(0, i * (rowspan + 1), rowspan + 1, 1));
                        jpn.add(ClanIcon2, getGridbBagConstraints(nbCols - 1, i * (rowspan + 1), rowspan + 1, 1));
                    }
                    if (Tournament.getTournament().getParams().isTeamTournament() && (Tournament.getTournament().getParams().getTeamIndivPairing() == EIndivPairing.RANKING)) {
                        nbCols = 5;
                        computed_width = width / nbCols;
                        colIndex = 1;

                        JLabel TeamIcon1 = getLabelForObject(((Coach) (cm.getCompetitor1())).getTeamMates(), computed_height, computed_width, f0, bkg);
                        JLabel TeamIcon2 = getLabelForObject(((Coach) (cm.getCompetitor2())).getTeamMates(), computed_height, computed_width, f0, bkg);
                        TeamIcon1.setHorizontalTextPosition(JLabel.LEADING);
                        TeamIcon1.setHorizontalAlignment(JLabel.RIGHT);
                        TeamIcon2.setHorizontalAlignment(JLabel.LEFT);

                        jpn.add(TeamIcon1, getGridbBagConstraints(0, i * (rowspan + 1), rowspan + 1, 1));
                        jpn.add(TeamIcon2, getGridbBagConstraints(nbCols - 1, i * (rowspan + 1), rowspan + 1, 1));
                    }

                    JLabel CoachIcon1 = getLabelForObject(cm.getCompetitor1(), computed_height, computed_width, getCoachMatchFont(cm, cm.getCompetitor1(), f1Winner, f1Looser, f1Draw, f1), bkg);
                    JLabel CoachIcon2 = getLabelForObject(cm.getCompetitor2(), computed_height, computed_width, getCoachMatchFont(cm, cm.getCompetitor2(), f1Winner, f1Looser, f1Draw, f1), bkg);

                    CoachIcon1.setHorizontalAlignment(JLabel.CENTER);
                    CoachIcon2.setHorizontalAlignment(JLabel.CENTER);

                    jpn.add(CoachIcon1, getGridbBagConstraints(colIndex, i * (rowspan + 1), rowspan, 1));
                    jpn.add(CoachIcon2, getGridbBagConstraints(nbCols - colIndex - 1, i * (rowspan + 1), rowspan, 1));

                    int value1 = cm.getValue(td).getValue1();
                    int value2 = cm.getValue(td).getValue2();
                    String sV1 = value1 >= 0 ? Integer.toString(value1) : " - ";
                    String sV2 = value2 >= 0 ? Integer.toString(value2) : " - ";

                    JLabel CoachScore1 = new JLabel();
                    CoachScore1.setSize(computed_width, computed_height);
                    CoachScore1.setFont(getCoachMatchFont(cm, cm.getCompetitor1(), f1Winner, f1Looser, f1Draw, f1));

                    JLabel CoachScore2 = new JLabel();
                    CoachScore2.setSize(computed_width, computed_height);
                    CoachScore2.setFont(getCoachMatchFont(cm, cm.getCompetitor2(), f1Winner, f1Looser, f1Draw, f1));

                    CoachScore1.setText(sV1);
                    CoachScore2.setText(sV2);

                    CoachScore1.setHorizontalAlignment(JLabel.CENTER);
                    CoachScore1.setBackground(bkg);
                    CoachScore1.setOpaque(true);

                    CoachScore2.setBackground(bkg);
                    CoachScore2.setOpaque(true);
                    CoachScore2.setHorizontalAlignment(JLabel.CENTER);

                    jpn.add(CoachScore1, getGridbBagConstraints(colIndex, (i + 1) * (rowspan + 1) - 1, 1, 1));
                    jpn.add(CoachScore2, getGridbBagConstraints(nbCols - colIndex - 1, (i + 1) * (rowspan + 1) - 1, 1, 1));

                    // Score                    
                    JLabel score = new JLabel(sV1 + " " + td.getName() + " " + sV2);

                    score.setHorizontalAlignment(JLabel.CENTER);
                    score.setOpaque(true);
                    score.setBackground(bkg);
                    score.setFont(f1);

                    jpn.add(score, getGridbBagConstraints(colIndex + 1, i * (rowspan + 1), 2, 1));

                    Font f2 = font.deriveFont(Font.ITALIC, (float) height / 75);
                    for (int j = 1; j < Tournament.getTournament().getParams().getCriteriaCount(); j++) {
                        Criteria crit = Tournament.getTournament().getParams().getCriteria(j);
                        value1 = cm.getValue(crit).getValue1();
                        value2 = cm.getValue(crit).getValue2();
                        JLabel tmp = new JLabel("" + value1 + " " + crit.getName() + " " + value2);
                        tmp.setBackground(bkg);
                        tmp.setOpaque(true);
                        tmp.setFont(f2);
                        tmp.setHorizontalAlignment(JLabel.CENTER);
                        jpn.add(tmp, getGridbBagConstraints(colIndex + 1, i * (rowspan + 1) + j + 1, 1, 1));
                    }
                }

                if (m instanceof TeamMatch) {
                    TeamMatch tm = (TeamMatch) m;

                    int colIndex;
                    nbCols = 5;
                    int nbValues = tm.getMatch(0).getValueCount();
                    int NbLinesPerCoachMatch = nbValues + 1;
                    int nbMatchs = tm.getMatchCount();
                    int NbLinesPerTeamMatch = NbLinesPerCoachMatch * nbMatchs + 1;

                    computed_width = width / nbCols;
                    colIndex = 1;

                    JLabel TeamIcon1 = getLabelForObject(tm.getCompetitor1(), computed_height, computed_width, f0, bkg);
                    JLabel TeamIcon2 = getLabelForObject(tm.getCompetitor2(), computed_height, computed_width, f0, bkg);

                    TeamIcon1.setHorizontalTextPosition(JLabel.LEADING);
                    TeamIcon1.setHorizontalAlignment(JLabel.RIGHT);
                    TeamIcon2.setHorizontalAlignment(JLabel.LEFT);

                    jpn.add(TeamIcon1, getGridbBagConstraints(0, i * NbLinesPerTeamMatch, NbLinesPerTeamMatch, 1));
                    jpn.add(TeamIcon2, getGridbBagConstraints(nbCols - 1, i * NbLinesPerTeamMatch, NbLinesPerTeamMatch, 1));

                    JLabel v = new JLabel("");
                    if ((tm.getCompetitor1().getPicture() != null) && Tournament.getTournament().getParams().isUseImage()) {
                        v.setIcon(ImageTreatment.resize(tm.getCompetitor1().getPicture(), computed_height, computed_height));
                    }
                    v.setFont(f1);
                    v.setOpaque(true);
                    v.setBackground(bkg);
                    v.setHorizontalAlignment(JLabel.CENTER);

                    jpn.add(v, getGridbBagConstraints(1, i * NbLinesPerTeamMatch, 1, 1));

                    JLabel n = new JLabel("" + tm.getVictories((Team) tm.getCompetitor1()) + " - " + tm.getDraw((Team) tm.getCompetitor1()) + " - " + tm.getVictories((Team) tm.getCompetitor2()));
                    n.setFont(fBig);
                    n.setBackground(bkg);
                    n.setOpaque(true);
                    n.setHorizontalAlignment(JLabel.CENTER);
                    jpn.add(n, getGridbBagConstraints(2, i * NbLinesPerTeamMatch, 1, 1));

                    JLabel l = new JLabel("");
                    l.setFont(f1);
                    if ((tm.getCompetitor2().getPicture() != null) && Tournament.getTournament().getParams().isUseImage()) {
                        l.setIcon(ImageTreatment.resize(tm.getCompetitor2().getPicture(), computed_height, computed_height));
                    }
                    l.setHorizontalTextPosition(JLabel.LEFT);
                    l.setBackground(bkg);
                    l.setOpaque(true);
                    l.setHorizontalAlignment(JLabel.CENTER);
                    jpn.add(l, getGridbBagConstraints(3, i * NbLinesPerTeamMatch, 1, 1));

                    for (int j = 0; j < tm.getMatchCount(); j++) {

                        Color cmBkg = bkg;
                        if (j % 2 == 0) {
                            cmBkg = new Color(bkg.getRed() - 10, bkg.getGreen() - 10, bkg.getBlue() - 10);
                        }
                        CoachMatch cm = tm.getMatch(j);

                        JLabel CoachIcon1 = getLabelForObject(cm.getCompetitor1(), computed_height, computed_width, getCoachMatchFont(cm, cm.getCompetitor1(), f1Winner, f1Looser, f1Draw, f1), cmBkg);
                        JLabel CoachIcon2 = getLabelForObject(cm.getCompetitor2(), computed_height, computed_width, getCoachMatchFont(cm, cm.getCompetitor2(), f1Winner, f1Looser, f1Draw, f1), cmBkg);
                        CoachIcon1.setHorizontalAlignment(JLabel.CENTER);
                        CoachIcon2.setHorizontalAlignment(JLabel.CENTER);

                        jpn.add(CoachIcon1, getGridbBagConstraints(colIndex, i * NbLinesPerTeamMatch + 1 + j * NbLinesPerCoachMatch, nbValues, 1));
                        jpn.add(CoachIcon2, getGridbBagConstraints(nbCols - colIndex - 1, i * NbLinesPerTeamMatch + 1 + j * NbLinesPerCoachMatch, nbValues, 1));

                        int value1 = cm.getValue(td).getValue1();
                        int value2 = cm.getValue(td).getValue2();
                        String sV1 = value1 >= 0 ? Integer.toString(value1) : " - ";
                        String sV2 = value2 >= 0 ? Integer.toString(value2) : " - ";

                        JLabel CoachScore1 = new JLabel();
                        CoachScore1.setSize(computed_width, computed_height);
                        CoachScore1.setFont(getCoachMatchFont(cm, cm.getCompetitor1(), f1Winner, f1Looser, f1Draw, f1));

                        JLabel CoachScore2 = new JLabel();
                        CoachScore2.setSize(computed_width, computed_height);
                        CoachScore2.setFont(getCoachMatchFont(cm, cm.getCompetitor2(), f1Winner, f1Looser, f1Draw, f1));

                        CoachScore1.setText(sV1);
                        CoachScore2.setText(sV2);

                        CoachScore1.setHorizontalAlignment(JLabel.CENTER);
                        CoachScore1.setBackground(cmBkg);
                        CoachScore1.setOpaque(true);
                        CoachScore2.setBackground(cmBkg);
                        CoachScore2.setOpaque(true);
                        CoachScore2.setHorizontalAlignment(JLabel.CENTER);

                        jpn.add(CoachScore1, getGridbBagConstraints(colIndex, i * NbLinesPerTeamMatch + (j + 1) * NbLinesPerCoachMatch, 1, 1));
                        jpn.add(CoachScore2, getGridbBagConstraints(nbCols - colIndex - 1, i * NbLinesPerTeamMatch + (j + 1) * NbLinesPerCoachMatch, 1, 1));

                        // Score                    
                        JLabel score = new JLabel(sV1 + " " + td.getName() + " " + sV2);

                        score.setHorizontalAlignment(JLabel.CENTER);
                        score.setOpaque(true);
                        score.setBackground(cmBkg);
                        score.setFont(f1);
                        score.setHorizontalAlignment(JLabel.CENTER);
                        jpn.add(score, getGridbBagConstraints(colIndex + 1, i * NbLinesPerTeamMatch + j * NbLinesPerCoachMatch + 1, 2, 1));

                        Font f2 = font.deriveFont(Font.ITALIC, (float) height / 75);
                        for (int k = 1; k < Tournament.getTournament().getParams().getCriteriaCount(); k++) {
                            Criteria crit = Tournament.getTournament().getParams().getCriteria(k);
                            value1 = cm.getValue(crit).getValue1();
                            value2 = cm.getValue(crit).getValue2();
                            JLabel tmp = new JLabel("" + value1 + " " + crit.getName() + " " + value2);
                            tmp.setBackground(cmBkg);
                            tmp.setOpaque(true);
                            tmp.setFont(f2);
                            tmp.setHorizontalAlignment(JLabel.CENTER);
                            jpn.add(tmp, getGridbBagConstraints(colIndex + 1, i * NbLinesPerTeamMatch + j * NbLinesPerCoachMatch + k + 2, 1, 1));
                        }
                    }

                }
            }

            this.getContentPane().removeAll();
            jscrp = new JScrollPane();
            jscrp.setViewportView(jpn);
            jpnContent = jpn;

            this.getContentPane().add(jscrp, BorderLayout.CENTER);
            this.repaint();
            fontRatio = fontRatio * 11 / 10;

 /*           System.out.println(fontRatio);
            System.out.println(jscrp.getComponent(0).getWidth());
            System.out.println(mSelectedGD.getDisplayMode().getWidth());
            System.out.println(jscrp.getPreferredSize().width);
            System.out.println(jscrp.getViewport().getExtentSize().width);
*/
            
            
        } while (jscrp.getPreferredSize().width > mSelectedGD.getDisplayMode().getWidth());
    }

    protected void setStop(boolean s) {
        loopStop = true;
    }

    /**
     *
     * @param r
     * @throws IOException
     */
    public JFullScreenMatchs(Round r) throws IOException {
        this(r, false);
    }

    GraphicsDevice mSelectedGD;

    @SuppressWarnings("LeakingThisInConstructor")
    public JFullScreenMatchs(Round r, boolean clash) throws IOException {
        super();

        initComponents();

        this.clash = clash;
        round = r;

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
            mSelectedGD = gs[screen];
            _screenIndex = screen;
        } else if (gs.length > 0) {
            gs[0].setFullScreenWindow(this);
            mSelectedGD = gs[0];
        } else {
            throw new RuntimeException("No Screens Found");
        }

        if (clash) {
            buildClash(screen);
        } else {
            buildPanel(r, screen);
        }

        //this.getGraphicsConfiguration().getDevice().setFullScreenWindow(this);
        //setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
    }

    private Font getCoachMatchFont(CoachMatch cm, Competitor comp, Font winner, Font looser, Font draw, Font def) {
        Font f = draw;
        if (comp.equals(cm.getWinner())) {
            f = winner;
        } else {
            if (comp.equals(cm.getLooser())) {
                f = looser;
            } else {
                Criteria td = Tournament.getTournament().getParams().getCriteria(0);
                if ((cm.getValue(td).getValue1() == -1) || (cm.getValue(td).getValue2() == -1)) {
                    f = def;
                } else {
                    f = draw;
                }
            }
        }
        return f;
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
        setBackground(new java.awt.Color(255, 255, 255));
        setName("FullScreen Tourma"); // NOI18N

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private JFullScreenMatchs.Animation clashAnim;

    @Override
    protected void keyPressed(KeyEvent evt) {
        LOG.log(Level.FINE, "KeyPressed: " + evt.getKeyChar());
        if (evt.getKeyCode() == KeyEvent.VK_S) {
            if (animationStarted) {
                jscrp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
                jscrp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                animationStarted = false;
                if (clash) {
                    if (clashAnim.isAlive()) {
                        try {
                            clashAnim.join();
                        } catch (InterruptedException ex) {
                            Logger.getLogger(JFullScreenMatchs.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } else {
                    if (animation.isAlive()) {
                        try {
                            animation.join();
                        } catch (InterruptedException ex) {
                            Logger.getLogger(JFullScreen.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            } else {
                animationStarted = true;
                if (clash) {
                    clashAnim = new JFullScreenMatchs.Animation(this._screenIndex);
                    clashAnim.start();
                } else {
                    animation = new JFullScreen.Animation();
                    animation.start();
                }
            }
        }

        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            if (animationStarted) {
                animationStarted = false;
                if (clash) {
                    if (clashAnim.isAlive()) {
                        try {
                            clashAnim.join();
                        } catch (InterruptedException ex) {
                            Logger.getLogger(JFullScreenMatchs.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } else {
                    if (animation.isAlive()) {
                        try {
                            animation.join();
                        } catch (InterruptedException ex) {
                            Logger.getLogger(JFullScreenMatchs.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
                this.dispose();
            }
        }
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            this.dispose();
            if (socket != null) {
                if (cl != null) {
                    cl.setStop(true);
                    cl.interrupt();
                }
                try {
                    socket.close();
                } catch (IOException ex) {
                    Logger.getLogger(JFullScreen.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.exit(0);
            }
        }
        if ((evt.getKeyCode() == KeyEvent.VK_SUBTRACT) || (evt.getKeyCode() == KeyEvent.VK_M)) {
            if (animationStarted) {
                jscrp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
                jscrp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                if (animation != null) {
                    if (animation.isAlive()) {
                        animation.incrementSync();
                    }
                }
            }
            if (clashAnim != null) {
                clashAnim.incrementSync();
            }
        }
        if ((evt.getKeyCode() == KeyEvent.VK_ADD) || (evt.getKeyCode() == KeyEvent.VK_P)) {
            if (animationStarted) {
                jscrp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
                jscrp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                if (animation != null) {
                    if (animation.isAlive()) {
                        animation.decrementSync();
                    }
                }
            }
            if (clashAnim != null) {
                clashAnim.decrementSync();
            }
        }
    }
    //public Animation animation;

    private JFullScreenMatchs me = this;

    public JPanel createClashTeamPane(Team t, TeamMatch tm, boolean right) {

        JPanel p = null;

        if (t != null) {
            int max_width = 0, c_height = 0;

            int width = mSelectedGD.getDisplayMode().getWidth();
            int height = mSelectedGD.getDisplayMode().getHeight();

            p = new JPanel();
            //Compute dimensions
            int nbPlayers = t.getActivePlayerNumber();

            // Compute number of lines: 2x nbPlayers
            int line_height = (height * 6 / 10) / (nbPlayers * 2);
            JLabel jlbTeam = new JLabel();
            jlbTeam.setHorizontalAlignment(JLabel.CENTER);
            Font font = getOptimalFont(width / 4, line_height * nbPlayers / 2, t.getName());

            jlbTeam.setOpaque(true);
            jlbTeam.setBackground(Color.WHITE);

            jlbTeam.setPreferredSize(new Dimension(width / 3, line_height * nbPlayers / 2));
            jlbTeam.setFont(font);
            jlbTeam.setText(t.getName());

            p.setLayout(new BorderLayout());
            p.add(jlbTeam, BorderLayout.CENTER);
            c_height += jlbTeam.getPreferredSize().height;
            max_width = Math.max(max_width, jlbTeam.getPreferredSize().width);

            if (Tournament.getTournament().getParams().isUseImage()) {
                if (t.getPicture() != null) {
                    ImageIcon pict = t.getPicture();
                    JLabel icon = new JLabel();
                    icon.setIcon(ImageTreatment.resize(pict, line_height * nbPlayers / 2, pict.getIconWidth() * (line_height * nbPlayers / 2) / pict.getIconHeight()));
                    icon.setBackground(Color.WHITE);
                    icon.setOpaque(true);
                    icon.setHorizontalAlignment(JLabel.CENTER);
                    p.add(icon, BorderLayout.NORTH);
                    c_height += pict.getIconWidth() * (line_height * nbPlayers / 2) / pict.getIconHeight();
                }
            }

            JPanel players = new JPanel(new GridBagLayout());
            players.setBackground(Color.WHITE);
            players.setOpaque(true);
            for (int i = 0; i < tm.getMatchCount(); i++) {

                Coach c = null;
                int score = -1;
                int p_width = 0;
                if (tm.getCompetitor1() == t) {
                    c = (Coach) tm.getMatch(i).getCompetitor1();
                    score = tm.getMatch(i).getValue(Tournament.getTournament().getParams().getCriteria(0)).getValue1();
                }
                if (tm.getCompetitor2() == t) {
                    c = (Coach) tm.getMatch(i).getCompetitor2();
                    score = tm.getMatch(i).getValue(Tournament.getTournament().getParams().getCriteria(0)).getValue2();
                }

                if (c != null) {

                    JLabel icon = new JLabel();
                    if ((c.getPicture() != null) && Tournament.getTournament().getParams().isUseImage()) {
                        ImageIcon pict = t.getPicture();
                        icon.setIcon(ImageTreatment.resize(pict, line_height * nbPlayers / 2, pict.getIconWidth() * (line_height * nbPlayers / 2) / pict.getIconHeight()));
                        p_width = line_height * nbPlayers / 2;
                    } else {
                        icon.setText(" ");
                    }
                    icon.setBackground(i % 2 == 0 ? Color.LIGHT_GRAY : Color.WHITE);
                    icon.setOpaque(true);

                    JLabel jlbCoach = new JLabel();
                    jlbCoach.setOpaque(true);
                    jlbCoach.setHorizontalAlignment(right ? JLabel.LEFT : JLabel.RIGHT);
                    if (Tournament.getTournament().getParams().isDisplayRoster()) {

                        if (right) {
                            jlbCoach.setText("<HTML> " + c.getName() + " <i>(" + c.getMatchRoster(i) + ")</i></HTML>");
                        } else {
                            jlbCoach.setText("<HTML><i>(" + c.getMatchRoster(i) + ")</i> " + c.getName() + "</HTML>");
                        }
                    } else {
                        if (right) {
                            jlbCoach.setText(" " + c.getName());
                        } else {
                            jlbCoach.setText(c.getName() + " ");
                        }
                    }
                    jlbCoach.setBackground(i % 2 == 0 ? Color.LIGHT_GRAY : Color.WHITE);
                    //jlbCoach.setFont(font.deriveFont((float) line_height));
                    Font font2 = getOptimalFont(width * 2 / 9, line_height, jlbCoach.getText());
                    jlbCoach.setFont(font2);

                    JLabel jlbScore = new JLabel(" ");
                    if (score > -1) {
                        jlbScore.setText(Integer.toString(score));
                    }
                    jlbScore.setOpaque(true);
                    jlbScore.setHorizontalAlignment(JLabel.CENTER);
                    jlbScore.setBackground(i % 2 == 0 ? Color.LIGHT_GRAY : Color.WHITE);
                    jlbScore.setFont(getOptimalFont(width / 9, line_height, jlbScore.getText()));

                    p_width += jlbCoach.getPreferredSize().width;

                    max_width = Math.max(max_width, p_width);
                    if ((c.getPicture() != null) && Tournament.getTournament().getParams().isUseImage()) {
                        c_height += Math.max(jlbCoach.getPreferredSize().height, c.getPicture().getIconWidth() * (line_height * nbPlayers / 2) / c.getPicture().getIconHeight());
                    } else {
                        c_height += jlbCoach.getPreferredSize().height;
                    }

                    if (right) {
                        GridBagConstraints c1 = new GridBagConstraints(0, i, 1, 1, 0.5, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0);
                        GridBagConstraints c2 = new GridBagConstraints(1, i, 1, 1, 1.0, 1.0, GridBagConstraints.LINE_START, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0);
                        GridBagConstraints c3 = new GridBagConstraints(2, i, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0);
                        players.add(jlbScore, c1);
                        players.add(jlbCoach, c2);
                        players.add(icon, c3);
                    } else {
                        GridBagConstraints c1 = new GridBagConstraints(0, i, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0);
                        GridBagConstraints c2 = new GridBagConstraints(1, i, 1, 1, 1.0, 1.0, GridBagConstraints.LINE_END, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0);
                        GridBagConstraints c3 = new GridBagConstraints(2, i, 1, 1, 0.5, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0);
                        players.add(icon, c1);
                        players.add(jlbCoach, c2);
                        players.add(jlbScore, c3);
                    }
                }
            }
            p.setOpaque(true);
            p.setBackground(Color.WHITE);
            p.add(players, BorderLayout.SOUTH);

            p.setSize(max_width, c_height);
        }

        return p;
    }

    private Font getOptimalFont(int width, int height, String s) {
        Font font;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, this.getClass().getResourceAsStream("/tourma/languages/calibri.ttf"));
        } catch (FontFormatException | IOException ex) {
            Logger.getLogger(JFullScreenIndivRank.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            font = this.getFont();
        }

        FontMetrics fm = this.getFontMetrics(font); //this is a JPanel
        do {
            font = font.deriveFont((float) font.getSize() + 1);
            fm = this.getFontMetrics(font);
        } while (fm.stringWidth(s) < width && fm.getHeight() < height);
        return font;
    }

    public JPanel createClashCoachPane(Coach t, int score, boolean right, int matchIndex) {
        JPanel p = null;
        if (t != null) {
            int mwidth = 0;
            int mheight = 0;
            p = new JPanel();

            int width = mSelectedGD.getDisplayMode().getWidth();
            int height = mSelectedGD.getDisplayMode().getHeight();

            //Compute dimensions
            int line_height = (height / 10);
            JLabel jlbCoach = new JLabel();

            jlbCoach.setPreferredSize(new Dimension(width / 4, line_height));
            jlbCoach.setFont(getOptimalFont(width / 6, line_height, t.getName()));

            if (Tournament.getTournament().getParams().isDisplayRoster()) {

                if (right) {
                    jlbCoach.setText("<HTML>" + t.getName() + " <i>(" + t.getMatchRoster(matchIndex) + ")</i></HTML>");
                } else {
                    jlbCoach.setText("<HTML><i>(" + t.getMatchRoster(matchIndex) + ")</i>" + t.getName() + " </HTML>");
                }
            } else {
                if (right) {
                    jlbCoach.setText(" " + t.getName());
                } else {
                    jlbCoach.setText(t.getName() + " ");
                }
            }

            jlbCoach.setOpaque(true);
            jlbCoach.setHorizontalAlignment(JLabel.CENTER);
            jlbCoach.setBackground(Color.WHITE);
            p.setLayout(new BorderLayout());
            p.add(jlbCoach, BorderLayout.CENTER);

            mheight += line_height;
            mwidth += width / 4;

            if (Tournament.getTournament().getParams().isUseImage()) {
                if (t.getPicture() != null) {
                    ImageIcon pict = t.getPicture();
                    JLabel icon = new JLabel();
                    icon.setBackground(Color.WHITE);
                    icon.setIcon(ImageTreatment.resize(pict, pict.getIconWidth() * (line_height) / pict.getIconHeight(), line_height));
                    p.add(icon, right ? BorderLayout.EAST : BorderLayout.WEST);
                    mwidth += icon.getWidth();
                    mheight = Math.max(mheight, icon.getHeight());
                }
            }

            if (t.getTeamMates() != null) {
                JLabel icon = new JLabel();
                icon.setBackground(Color.WHITE);
                if (Tournament.getTournament().getParams().isUseImage()) {
                    if (t.getTeamMates().getPicture() != null) {
                        ImageIcon pict = t.getTeamMates().getPicture();
                        icon.setIcon(ImageTreatment.resize(pict, line_height, pict.getIconWidth() * (line_height) / pict.getIconHeight()));
                    } else {
                        icon.setText(" ");
                        icon.setPreferredSize(new Dimension(line_height, line_height));
                        icon.setHorizontalTextPosition(right ? JLabel.LEADING : JLabel.TRAILING);
                    }
                }
                icon.setText(t.getTeamMates().getName());
                icon.setFont(getOptimalFont(width / 5, line_height, t.getTeamMates().getName()));

                icon.setHorizontalAlignment(JLabel.CENTER);

                mheight += icon.getHeight();
                p.add(icon, BorderLayout.NORTH);
            }

            if (t.getClan() != null) {
                JLabel icon = new JLabel();
                icon.setBackground(Color.WHITE);
                if (Tournament.getTournament().getParams().isUseImage()) {
                    if (t.getClan().getPicture() != null) {
                        ImageIcon pict = t.getClan().getPicture();
                        icon.setIcon(ImageTreatment.resize(pict, line_height, pict.getIconWidth() * (line_height) / pict.getIconHeight()));
                    } else {
                        icon.setText(" ");
                        icon.setPreferredSize(new Dimension(line_height, line_height));
                    }
                }
                if (!t.getClan().getName().equals(Translate.translate(Translate.CS_None))) {
                    icon.setText(t.getClan().getName());
                } else {
                    icon.setForeground(Color.white);
                    icon.setText(Translate.translate(Translate.CS_None));
                }
                icon.setFont(getOptimalFont(width / 5, line_height, t.getClan().getName()));
                icon.setHorizontalAlignment(JLabel.CENTER);
                icon.setHorizontalTextPosition(right ? JLabel.LEADING : JLabel.TRAILING);
                mheight += line_height + line_height / 10;
                p.add(icon, BorderLayout.NORTH);
            }

            JLabel jlbScore = new JLabel();
            jlbScore.setBackground(Color.WHITE);
            jlbScore.setOpaque(true);
            if (score < 0) {
                jlbScore.setText(" ");
            } else {
                jlbScore.setText(Integer.toString(score));
            }
            jlbScore.setPreferredSize(new Dimension(width / 10, line_height));
            jlbScore.setFont(getOptimalFont(width / 16, line_height, jlbScore.getText()));
            jlbScore.setHorizontalAlignment(right ? JLabel.LEADING : JLabel.TRAILING);
            p.add(jlbScore, right ? BorderLayout.WEST : BorderLayout.EAST);

            mwidth += jlbScore.getPreferredSize().width;

            p.setOpaque(true);
            p.setBackground(Color.WHITE);
            p.setSize(width / 3, mheight);
        }
        return p;
    }

    private JPanel jpnClash1 = null;
    private JPanel jpnClash2 = null;
    private JLabel jlbTitle1 = null;
    private JLabel jlbTitle2 = null;
    private int jpn1X = 0;
    private int jpn2X = 0;
    private int jpn1Y = 0;
    private int jpn2Y = 0;

    /**
     * Internal class for animation
     */
    public class Animation extends Thread implements Suspendable {

        private boolean suspended = false;

        private long nanos = 1000000000;
        private int step=1;

        synchronized void incrementSync() {
            nanos = nanos - nanos * 10 / 100;
            System.out.println(nanos);
            step++;
        }

        synchronized void decrementSync() {
            nanos = nanos + nanos * 10 / 100;
            System.out.println(nanos);
            step--;
            if (step<0)
            {
                step=0;
            }
        }

        public void setSuspended(boolean s) {
            suspended = s;
        }

        int _screen = 0;

        public Animation(int screen) {
            _screen = screen;
        }

        private static final String CS_Round = "Round";
        private static final String CS_Table = "Table";

        @SuppressFBWarnings(value = "SWL_SLEEP_WITH_LOCK_HELD", justification = "Sleep is used for animation")
        @Override
        @SuppressWarnings("SleepWhileInLoop")
        public void run() {

            GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[_screen];
            int width = gd.getDisplayMode().getWidth();
            int height = gd.getDisplayMode().getHeight();

            me.setSize(width, height);

            Sleeping spleeping = new Sleeping(this);

            this.setPriority(Thread.MAX_PRIORITY);
            while (animationStarted) {
                try {
                    semAnimate.acquire();
                } catch (InterruptedException ex) {
                    Logger.getLogger(JFullScreenMatchs.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (clash) {
                    for (int i = 0; i < round.getMatchsCount(); i++) {
                        if (!animationStarted) {
                            break;
                        }
                        if (jpnClash1 != null) {
                            jpnContent.remove(jpnClash1);
                        }
                        if (jpnClash2 != null) {
                            jpnContent.remove(jpnClash2);
                        }
                        if (jlbTitle1 != null) {
                            jpnContent.remove(jlbTitle1);
                        }
                        if (jlbTitle2 != null) {
                            jpnContent.remove(jlbTitle2);
                        }

                        Match m = round.getMatch(i);
                        jpnClash1 = null;
                        jpnClash2 = null;
                        if (m instanceof TeamMatch) {
                            jpnClash1 = createClashTeamPane((Team) m.getCompetitor1(), (TeamMatch) m, false);
                            jpnClash2 = createClashTeamPane((Team) m.getCompetitor2(), (TeamMatch) m, true);
                        }
                        if (m instanceof CoachMatch) {
                            jpnClash1 = createClashCoachPane((Coach) m.getCompetitor1(), ((CoachMatch) m).getValue(Tournament.getTournament().getParams().getCriteria(0)).getValue1(), false, i);
                            jpnClash2 = createClashCoachPane((Coach) m.getCompetitor2(), ((CoachMatch) m).getValue(Tournament.getTournament().getParams().getCriteria(0)).getValue2(), true, i);
                        }

                        if ((jpnClash1 != null) && (jpnClash2 != null)) {
                            jlbTitle1 = new JLabel();
                            int line_height = (height * 1 / 10);
                            jlbTitle1.setFont(getFont().deriveFont((float) line_height));
                            jlbTitle1.setText(Translate.translate(CS_Round) + " " + (Tournament.getTournament().getRoundIndex(round) + 1));

                            jlbTitle2 = new JLabel();
                            line_height = (height * 1 / 20);
                            jlbTitle2.setFont(getFont().deriveFont((float) line_height));
                            jlbTitle2.setText(Translate.translate(CS_Table) + " " + (i + 1));

                            jpnContent.add(jlbTitle1);
                            jpnContent.add(jlbTitle2);
                            if ((jpnClash1 != null) && (jpnClash2 != null) && (clash)) {

                                jpnContent.add(jpnClash1);
                                jpnContent.add(jpnClash2);
                                jpnContent.setBackground(Color.WHITE);

                                jpnContent.setSize(width, height);

                                me.getContentPane().validate();

                                me.repaint();
                            }

                            Dimension size1 = jpnClash1.getPreferredSize();
                            Dimension size2 = jpnClash2.getPreferredSize();
                            jpn1Y = (height - size1.height) / 2;
                            jpn2Y = (height - size2.height) / 2;

                            Dimension sizeTitle1 = jlbTitle1.getPreferredSize();
                            jlbTitle1.setBounds((width - sizeTitle1.width) / 2, 0, sizeTitle1.width, sizeTitle1.height);

                            Dimension sizeTitle2 = jlbTitle2.getPreferredSize();
                            jlbTitle2.setBounds((width - sizeTitle2.width) / 2, sizeTitle1.height, sizeTitle2.width, sizeTitle2.height);

                            // 1 seconde <=> lateral margin => timer
                            for (int j = 0; j < width; j += 2) {

                                if (!animationStarted) {
                                    break;
                                }
                                // Clear
                                // Draw vs
                                // Set JPN1 & JPN2 position

                                jpn1X = 0 - size1.width + (j < width / 2 ? j+step : width / 2);
                                jpn2X = width - (j > width / 2 ? j - width / 2-step : 0);

                                j+=step;
                                synchronized (this) {
                                    suspended = true;
                                    long timer = nanos / (width / 2);
                                    long timer1 = timer / 1000000;
                                    long timer2 = timer % 1000000;
                                    spleeping.sleep(timer1, (int) timer2);
                                    //System.out.println("timer1: "+timer1+" timer2: "+ timer2);
                                    //spleeping.sleep(0, (int) 10);
                                    while (suspended && animationStarted) {
                                        try {
                                            wait();
                                        } catch (InterruptedException ex) {
                                            Logger.getLogger(JFullScreenMatchs.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    }
                                }

                                jpnClash1.setBounds(jpn1X, jpn1Y, size1.width, size1.height);
                                jpnClash2.setBounds(jpn2X, jpn2Y, size2.width, size2.height);

                                me.repaint();
                            }
                            if (i <= round.getMatchsCount() - 1) {
                                synchronized (this) {
                                    suspended = true;
                                    spleeping.sleep((2 * nanos) / 1000000, (int) (2 * nanos) % 1000000);
                                    while (suspended && animationStarted) {
                                        try {
                                            wait();
                                        } catch (InterruptedException ex) {
                                            Logger.getLogger(JFullScreenMatchs.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    }
                                }
                            }
                        }
                    }
                    semAnimate.release();
                    synchronized (this) {
                        suspended = true;
                        spleeping.sleep((2 * nanos) / 1000000, (int) (2 * nanos) % 1000000);                        
                        while (suspended && animationStarted) {
                            try {
                                wait();
                            } catch (InterruptedException ex) {
                                Logger.getLogger(JFullScreenMatchs.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                }
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    private static final Logger LOG = Logger.getLogger(JFullScreenMatchs.class.getName());

}
