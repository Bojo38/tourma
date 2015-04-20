/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.views.fullscreen;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
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
import tourma.utils.ImageTreatment;
import tourma.utils.TourmaProtocol;

/**
 *
 * @author WFMJ7631
 */
public final class JFullScreenMatchs extends JFullScreen {

    private Round round;

    private boolean loopStop = false;

    public JFullScreenMatchs(Socket s) throws IOException {
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
                            Document doc = sb.build(new StringReader(buffer));
                            Tournament.getTournament().loadRosters(doc.getRootElement());
                            Element element = doc.getRootElement().getChild("Parameters");
                            Tournament.getTournament().getParams().setXMLElement(element);
                            element = doc.getRootElement().getChild("Round");
                            r = new Round();
                            r.setXMLElementForDisplay(element);

                            buildPanel(r);

                            this.getGraphicsConfiguration().getDevice().setFullScreenWindow(this);

                        } catch (JDOMException ex) {
                            Logger.getLogger(JFullScreenIndivRank.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        try {
                            Thread.sleep(10000);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(JFullScreenIndivRank.class.getName()).log(Level.SEVERE, null, ex);
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
    }

    private void buildPanel(Round r) {

        Font font;

        JPanel jpn = new JPanel();
        GridBagLayout gbl = new GridBagLayout();
        jpn.setLayout(gbl);

        try {
            font = Font.createFont(Font.TRUETYPE_FONT, this.getClass().getResourceAsStream("/tourma/languages/calibri.ttf"));
        } catch (FontFormatException| IOException ex) {
            Logger.getLogger(JFullScreenIndivRank.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            font = this.getFont();
        }

        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int width = gd.getDisplayMode().getWidth();
        int height = gd.getDisplayMode().getHeight();

        Criteria td = Tournament.getTournament().getParams().getCriteria(0);

        float size = (float) height / 50;
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

                    jpnContent.add(ClanIcon1, getGridbBagConstraints(0, i * (rowspan + 1), rowspan + 1, 1));
                    jpnContent.add(ClanIcon2, getGridbBagConstraints(nbCols - 1, i * (rowspan + 1), rowspan + 1, 1));
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

                    jpnContent.add(TeamIcon1, getGridbBagConstraints(0, i * (rowspan + 1), rowspan + 1, 1));
                    jpnContent.add(TeamIcon2, getGridbBagConstraints(nbCols - 1, i * (rowspan + 1), rowspan + 1, 1));
                }

                JLabel CoachIcon1 = getLabelForObject(cm.getCompetitor1(), computed_height, computed_width, getCoachMatchFont(cm, cm.getCompetitor1(), f1Winner, f1Looser, f1Draw, f1), bkg);
                JLabel CoachIcon2 = getLabelForObject(cm.getCompetitor2(), computed_height, computed_width, getCoachMatchFont(cm, cm.getCompetitor2(), f1Winner, f1Looser, f1Draw, f1), bkg);

                CoachIcon1.setHorizontalAlignment(JLabel.CENTER);
                CoachIcon2.setHorizontalAlignment(JLabel.CENTER);

                jpnContent.add(CoachIcon1, getGridbBagConstraints(colIndex, i * (rowspan + 1), rowspan, 1));
                jpnContent.add(CoachIcon2, getGridbBagConstraints(nbCols - colIndex - 1, i * (rowspan + 1), rowspan, 1));

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

                jpnContent.add(CoachScore1, getGridbBagConstraints(colIndex, (i + 1) * (rowspan + 1) - 1, 1, 1));
                jpnContent.add(CoachScore2, getGridbBagConstraints(nbCols - colIndex - 1, (i + 1) * (rowspan + 1) - 1, 1, 1));

                // Score                    
                JLabel score = new JLabel(sV1 + " " + td.getName() + " " + sV2);

                score.setHorizontalAlignment(JLabel.CENTER);
                score.setOpaque(true);
                score.setBackground(bkg);
                score.setFont(f1);

                jpnContent.add(score, getGridbBagConstraints(colIndex + 1, i * (rowspan + 1), 2, 1));

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
                    jpnContent.add(tmp, getGridbBagConstraints(colIndex + 1, i * (rowspan + 1) + j + 1, 1, 1));
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

                jpnContent.add(TeamIcon1, getGridbBagConstraints(0, i * NbLinesPerTeamMatch, NbLinesPerTeamMatch, 1));
                jpnContent.add(TeamIcon2, getGridbBagConstraints(nbCols - 1, i * NbLinesPerTeamMatch, NbLinesPerTeamMatch, 1));

                JLabel v = new JLabel("");
                if ((tm.getCompetitor1().getPicture() != null) && Tournament.getTournament().getParams().isUseImage()) {
                    v.setIcon(ImageTreatment.resize(new ImageIcon(tm.getCompetitor1().getPicture()), computed_height, computed_height));
                }
                v.setFont(f1);
                v.setOpaque(true);
                v.setBackground(bkg);
                v.setHorizontalAlignment(JLabel.CENTER);

                jpnContent.add(v, getGridbBagConstraints(1, i * NbLinesPerTeamMatch, 1, 1));

                JLabel n = new JLabel("" + tm.getVictories((Team) tm.getCompetitor1()) + " - " + tm.getDraw((Team) tm.getCompetitor1()) + " - " + tm.getVictories((Team) tm.getCompetitor2()));
                n.setFont(fBig);
                n.setBackground(bkg);
                n.setOpaque(true);
                n.setHorizontalAlignment(JLabel.CENTER);
                jpnContent.add(n, getGridbBagConstraints(2, i * NbLinesPerTeamMatch, 1, 1));

                JLabel l = new JLabel("");
                l.setFont(f1);
                if ((tm.getCompetitor2().getPicture() != null) && Tournament.getTournament().getParams().isUseImage()) {
                    l.setIcon(ImageTreatment.resize(new ImageIcon(tm.getCompetitor2().getPicture()), computed_height, computed_height));
                }
                l.setHorizontalTextPosition(JLabel.LEFT);
                l.setBackground(bkg);
                l.setOpaque(true);
                l.setHorizontalAlignment(JLabel.CENTER);
                jpnContent.add(l, getGridbBagConstraints(3, i * NbLinesPerTeamMatch, 1, 1));

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

                    jpnContent.add(CoachIcon1, getGridbBagConstraints(colIndex, i * NbLinesPerTeamMatch + 1 + j * NbLinesPerCoachMatch, nbValues, 1));
                    jpnContent.add(CoachIcon2, getGridbBagConstraints(nbCols - colIndex - 1, i * NbLinesPerTeamMatch + 1 + j * NbLinesPerCoachMatch, nbValues, 1));

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

                    jpnContent.add(CoachScore1, getGridbBagConstraints(colIndex, i * NbLinesPerTeamMatch + (j + 1) * NbLinesPerCoachMatch, 1, 1));
                    jpnContent.add(CoachScore2, getGridbBagConstraints(nbCols - colIndex - 1, i * NbLinesPerTeamMatch + (j + 1) * NbLinesPerCoachMatch, 1, 1));

                    // Score                    
                    JLabel score = new JLabel(sV1 + " " + td.getName() + " " + sV2);

                    score.setHorizontalAlignment(JLabel.CENTER);
                    score.setOpaque(true);
                    score.setBackground(cmBkg);
                    score.setFont(f1);
                    score.setHorizontalAlignment(JLabel.CENTER);
                    jpnContent.add(score, getGridbBagConstraints(colIndex + 1, i * NbLinesPerTeamMatch + j * NbLinesPerCoachMatch + 1, 2, 1));

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
                        jpnContent.add(tmp, getGridbBagConstraints(colIndex + 1, i * NbLinesPerTeamMatch + j * NbLinesPerCoachMatch + k + 2, 1, 1));
                    }
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
    public JFullScreenMatchs(Round r) throws IOException {
        super();
        initComponents();

        round = r;

        buildPanel(r);

        this.getGraphicsConfiguration().getDevice().setFullScreenWindow(this);
    }

    private Font getCoachMatchFont(CoachMatch cm, Competitor comp, Font winner, Font looser, Font draw, Font def) {
        Font f;
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
        setName("FullScreen Tourma"); // NOI18N

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private boolean animationStarted = false;
    private Animation animation;

    /**
     * Internal class for animation
     */
    private class Animation extends Thread {

        @SuppressFBWarnings(value = "SWL_SLEEP_WITH_LOCK_HELD", justification = "Sleep is used for animation")
        @Override
        @SuppressWarnings("SleepWhileInLoop")
        public void run() {
            long computedTime = getHeight() / 100;
            //int blockIncrement = jscrp.getVerticalScrollBar().getBlockIncrement();

            LOG.log(Level.FINEST, "Screen Height: {0} ScrollBar size: {1} Computed Time: {2}", new Object[]{getHeight(), jscrp.getVerticalScrollBar().getMaximum() - jscrp.getVerticalScrollBar().getMinimum(), computedTime});
            int lastValue = 0;
            while (animationStarted) {
                int value = jscrp.getVerticalScrollBar().getValue();
                value += 1;
                if (value <= lastValue) {
                    value = 0;
                    try {
                        sleep(10000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(JFullScreenMatchs.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    jscrp.getVerticalScrollBar().setValue(jscrp.getVerticalScrollBar().getMinimum());
                } else {
                    jscrp.getVerticalScrollBar().setValue(value);
                }

                try {
                    sleep(computedTime);
                } catch (InterruptedException ex) {
                    Logger.getLogger(JFullScreenMatchs.class.getName()).log(Level.SEVERE, null, ex);
                }
                LOG.log(Level.FINEST, "Current value: {0} last Value: {1}", new Object[]{value, lastValue});
                lastValue = value;
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    private static final Logger LOG = Logger.getLogger(JFullScreenMatchs.class.getName());

    private void writeObject(java.io.ObjectOutputStream stream) throws java.io.IOException {
        throw new java.io.NotSerializableException(getClass().getName());
    }

    private void readObject(java.io.ObjectInputStream stream) throws java.io.IOException, ClassNotFoundException {
        throw new java.io.NotSerializableException(getClass().getName());
    }
}
