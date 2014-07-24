/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma;

import java.awt.Color;
import java.awt.DisplayMode;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.ScrollPane;
import java.awt.Scrollbar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ScrollPaneConstants;
import tourma.data.Clan;
import tourma.data.Coach;
import tourma.data.CoachMatch;
import tourma.data.Criteria;
import tourma.data.Match;
import tourma.data.Round;
import tourma.data.Team;
import tourma.data.TeamMatch;
import tourma.data.Tournament;
import tourma.utils.ImageTreatment;
import tourma.views.GraphicalMatch;

/**
 *
 * @author WFMJ7631
 */
public class FullScreen extends javax.swing.JFrame {

    /**
     * Creates new form FullScreen
     */
    public FullScreen() {
        super();
        initComponents();
        this.getGraphicsConfiguration().getDevice().setFullScreenWindow(this);
    }
    Round round;

    public FullScreen(Round r) throws IOException {
        this();
        try {
            round = r;
            GridBagLayout gbl = new GridBagLayout();
            jpnContent.setLayout(gbl);

            Font font = Font.createFont(Font.TRUETYPE_FONT, this.getClass().getResourceAsStream("/tourma/languages/calibri.ttf"));

            int height = getHeight();
            int width = getWidth();

            Font f0 = font.deriveFont(Font.ITALIC, height / 50);
            Font f1 = font.deriveFont(Font.BOLD, height / 50);
            Font f1 = font.deriveFont(Font.BOLD, height / 50);
            Font fBig = font.deriveFont(Font.BOLD, height / 20);

            int computed_height = height / 10;
            int nbCols = 3;
            int computed_width = width / nbCols;

            for (int i = 0; i < r.getMatchs().size(); i++) {
                Color bkg = new Color(255, 255, 255);
                if (i % 2 == 1) {
                    bkg = new Color(220, 220, 220);
                }
                Match m = (Match) r.getMatchs().get(i);
                if (m instanceof CoachMatch) {
                    CoachMatch cm = (CoachMatch) m;

                    int colIndex = 0;
                    int rowspan = cm.mValues.size();
                    if (Tournament.getTournament().getClans().size() > 1) {
                        nbCols = 5;
                        computed_width = width / nbCols;
                        colIndex = 1;
                        JLabel ClanIcon1 = new JLabel();
                        ClanIcon1.setFont(f0);
                        ClanIcon1.setSize(computed_width, computed_height);
                        JLabel ClanIcon2 = new JLabel();
                        ClanIcon2.setSize(computed_width, computed_height);
                        ClanIcon2.setFont(f0);

                        Clan clan1 = ((Coach) (cm.mCompetitor1)).mClan;
                        if (clan1.picture != null) {
                            ClanIcon1.setIcon(ImageTreatment.resize(new ImageIcon(clan1.picture), computed_height, computed_height));
                        } else {
                            ClanIcon1.setIcon(new ImageIcon(new BufferedImage(computed_height, computed_height, BufferedImage.TYPE_4BYTE_ABGR)));
                        }
                        ClanIcon1.setText(clan1.mName);
                        Clan clan2 = ((Coach) (cm.mCompetitor2)).mClan;
                        if (clan2.picture != null) {
                            ClanIcon2.setIcon(ImageTreatment.resize(new ImageIcon(clan2.picture), computed_height, computed_height));
                        } else {
                            ClanIcon2.setIcon(new ImageIcon(new BufferedImage(computed_height, computed_height, BufferedImage.TYPE_4BYTE_ABGR)));
                        }
                        ClanIcon2.setText(clan2.mName);
                        ClanIcon1.setHorizontalTextPosition(JLabel.LEADING);
                        ClanIcon1.setHorizontalAlignment(JLabel.RIGHT);
                        ClanIcon2.setHorizontalAlignment(JLabel.LEFT);
                        ClanIcon1.setOpaque(true);
                        ClanIcon1.setBackground(bkg);
                        ClanIcon2.setOpaque(true);
                        ClanIcon2.setBackground(bkg);

                        GridBagConstraints gbc1 = new GridBagConstraints();
                        GridBagConstraints gbc2 = new GridBagConstraints();
                        gbc1.gridx = 0;
                        gbc1.gridy = i * (rowspan + 1);
                        gbc1.fill = GridBagConstraints.BOTH;
                        gbc1.weightx = 1.0;
                        gbc1.weighty = 2.0;
                        gbc1.gridheight = rowspan + 1;
                        jpnContent.add(ClanIcon1, gbc1);
                        gbc2.gridx = nbCols - 1;
                        gbc2.gridy = i * (rowspan + 1);
                        gbc2.fill = GridBagConstraints.BOTH;
                        gbc2.weightx = 1.0;
                        gbc2.weighty = 1.0;
                        gbc2.gridheight = rowspan + 1;
                        jpnContent.add(ClanIcon2, gbc2);
                    }
                    if (Tournament.getTournament().getParams().mTeamTournament && (Tournament.getTournament().getParams().mTeamIndivPairing == 0)) {
                        nbCols = 5;
                        computed_width = width / nbCols;
                        colIndex = 1;
                        JLabel TeamIcon1 = new JLabel();
                        TeamIcon1.setFont(f0);
                        TeamIcon1.setSize(computed_width, computed_height);
                        JLabel TeamIcon2 = new JLabel();
                        TeamIcon2.setSize(computed_width, computed_height);
                        TeamIcon2.setFont(f0);

                        Team team1 = ((Coach) (cm.mCompetitor1)).mTeamMates;
                        if (team1.picture != null) {
                            TeamIcon1.setIcon(ImageTreatment.resize(new ImageIcon(team1.picture), computed_height, computed_height));
                        } else {
                            TeamIcon1.setIcon(new ImageIcon(new BufferedImage(computed_height, computed_height, BufferedImage.TYPE_4BYTE_ABGR)));
                        }
                        TeamIcon1.setText(team1.mName);
                        Team team2 = ((Coach) (cm.mCompetitor2)).mTeamMates;
                        if (team2.picture != null) {
                            TeamIcon2.setIcon(ImageTreatment.resize(new ImageIcon(team2.picture), computed_height, computed_height));
                        } else {
                            TeamIcon2.setIcon(new ImageIcon(new BufferedImage(computed_height, computed_height, BufferedImage.TYPE_4BYTE_ABGR)));
                        }
                        TeamIcon2.setText(team2.mName);
                        TeamIcon1.setHorizontalTextPosition(JLabel.LEADING);
                        TeamIcon1.setHorizontalAlignment(JLabel.RIGHT);
                        TeamIcon2.setHorizontalAlignment(JLabel.LEFT);
                        TeamIcon1.setOpaque(true);
                        TeamIcon1.setBackground(bkg);
                        TeamIcon2.setOpaque(true);
                        TeamIcon2.setBackground(bkg);

                        GridBagConstraints gbc1 = new GridBagConstraints();
                        GridBagConstraints gbc2 = new GridBagConstraints();
                        gbc1.gridx = 0;
                        gbc1.gridy = i * (rowspan + 1);
                        gbc1.fill = GridBagConstraints.BOTH;
                        gbc1.weightx = 1.0;
                        gbc1.weighty = 2.0;
                        gbc1.gridheight = rowspan + 1;
                        jpnContent.add(TeamIcon1, gbc1);
                        gbc2.gridx = nbCols - 1;
                        gbc2.gridy = i * (rowspan + 1);
                        gbc2.fill = GridBagConstraints.BOTH;
                        gbc2.weightx = 1.0;
                        gbc2.weighty = 1.0;
                        gbc2.gridheight = rowspan + 1;
                        jpnContent.add(TeamIcon2, gbc2);
                    }

                    JLabel CoachIcon1 = new JLabel();
                    CoachIcon1.setSize(computed_width, computed_height);
                    CoachIcon1.setFont(f1);
                    JLabel CoachIcon2 = new JLabel();
                    CoachIcon2.setSize(computed_width, computed_height);

                    if (cm.mCompetitor1.picture != null) {
                        CoachIcon1.setIcon(ImageTreatment.resize(new ImageIcon(cm.mCompetitor1.picture), computed_height, computed_height));
                    }
                    CoachIcon1.setText(cm.mCompetitor1.mName);
                    CoachIcon2.setFont(f1);
                    if (cm.mCompetitor2.picture != null) {
                        CoachIcon2.setIcon(ImageTreatment.resize(new ImageIcon(cm.mCompetitor2.picture), computed_height, computed_height));
                    }
                    CoachIcon2.setText(cm.mCompetitor2.mName);
                    CoachIcon1.setHorizontalAlignment(JLabel.CENTER);
                    CoachIcon1.setBackground(bkg);
                    CoachIcon1.setOpaque(true);
                    CoachIcon2.setBackground(bkg);
                    CoachIcon2.setOpaque(true);
                    CoachIcon2.setHorizontalAlignment(JLabel.CENTER);

                    GridBagConstraints gbc1 = new GridBagConstraints();
                    GridBagConstraints gbc2 = new GridBagConstraints();
                    gbc1.gridx = colIndex;
                    gbc1.gridy = i * (rowspan + 1);
                    gbc1.fill = GridBagConstraints.BOTH;
                    gbc1.weightx = 1.0;
                    gbc1.weighty = 1.0;
                    gbc1.gridheight = rowspan;
                    jpnContent.add(CoachIcon1, gbc1);
                    gbc2.gridx = nbCols - colIndex - 1;
                    gbc2.gridy = i * (rowspan + 1);
                    gbc2.fill = GridBagConstraints.BOTH;
                    gbc2.weightx = 1.0;
                    gbc2.weighty = 1.0;
                    gbc2.gridheight = rowspan;
                    jpnContent.add(CoachIcon2, gbc2);

                    Criteria td = Tournament.getTournament().getParams().mCriterias.get(0);
                    int value1 = cm.mValues.get(td).mValue1;
                    int value2 = cm.mValues.get(td).mValue2;
                    String sV1 = value1 >= 0 ? Integer.toString(value1) : " - ";
                    String sV2 = value2 >= 0 ? Integer.toString(value2) : " - ";


                    JLabel CoachScore1 = new JLabel();
                    CoachScore1.setSize(computed_width, computed_height);
                    CoachScore1.setFont(f1);

                    JLabel CoachScore2 = new JLabel();
                    CoachScore2.setSize(computed_width, computed_height);
                    CoachScore2.setFont(f1);

                    CoachScore1.setText(sV1);
                    CoachScore2.setText(sV2);

                    CoachScore1.setHorizontalAlignment(JLabel.CENTER);
                    CoachScore1.setBackground(bkg);
                    CoachScore1.setOpaque(true);
                    CoachScore2.setBackground(bkg);
                    CoachScore2.setOpaque(true);
                    CoachScore2.setHorizontalAlignment(JLabel.CENTER);

                    GridBagConstraints gbcs1 = new GridBagConstraints();
                    GridBagConstraints gbcs2 = new GridBagConstraints();
                    gbcs1.gridx = colIndex;
                    gbcs1.gridy = (i + 1) * (rowspan + 1) - 1;
                    gbcs1.fill = GridBagConstraints.BOTH;
                    gbcs1.weightx = 1.0;
                    gbcs1.weighty = 1.0;

                    jpnContent.add(CoachScore1, gbcs1);

                    gbcs2.gridx = nbCols - colIndex - 1;
                    gbcs2.gridy = (i + 1) * (rowspan + 1) - 1;
                    gbcs2.fill = GridBagConstraints.BOTH;
                    gbcs2.weightx = 1.0;
                    gbcs2.weighty = 1.0;
                    jpnContent.add(CoachScore2, gbcs2);

                    // Score                    

                    JLabel score = new JLabel(sV1 + " " + td.mName + " " + sV2);

                    score.setHorizontalAlignment(JLabel.CENTER);
                    score.setOpaque(true);
                    score.setBackground(bkg);
                    score.setFont(f1);
                    score.setHorizontalAlignment(JLabel.CENTER);
                    GridBagConstraints gbcs = new GridBagConstraints();
                    gbcs.gridx = colIndex + 1;
                    gbcs.gridy = i * (rowspan + 1);
                    gbcs.fill = GridBagConstraints.BOTH;
                    gbcs.weightx = 1.0;
                    gbcs.weighty = 1.0;
                    gbcs.gridheight = 2;
                    gbcs.gridwidth = 1;

                    jpnContent.add(score, gbcs);


                    Font f2 = font.deriveFont(Font.ITALIC, height / 75);
                    for (int j = 1; j < Tournament.getTournament().getParams().mCriterias.size(); j++) {
                        Criteria crit = Tournament.getTournament().getParams().mCriterias.get(j);
                        value1 = cm.mValues.get(crit).mValue1;
                        value2 = cm.mValues.get(crit).mValue2;
                        JLabel tmp = new JLabel("" + value1 + " " + crit.mName + " " + value2);
                        GridBagConstraints gbc = new GridBagConstraints();
                        tmp.setBackground(bkg);
                        tmp.setOpaque(true);
                        tmp.setFont(f2);
                        tmp.setHorizontalAlignment(JLabel.CENTER);
                        gbc.gridx = colIndex + 1;
                        gbc.gridy = i * (rowspan + 1) + j + 1;
                        gbc.fill = GridBagConstraints.BOTH;
                        gbc.weightx = 1.0;
                        gbc.weighty = 1.0;
                        gbc.gridheight = 1;
                        gbc.gridwidth = 1;
                        jpnContent.add(tmp, gbc);
                    }
                }

                if (m instanceof TeamMatch) {
                    TeamMatch tm = (TeamMatch) m;

                    int colIndex = 0;
                    nbCols = 5;
                    int nbValues = tm.mMatchs.get(0).mValues.size();
                    int NbLinesPerCoachMatch = nbValues + 1;
                    int nbMatchs = tm.mMatchs.size();
                    int NbLinesPerTeamMatch = NbLinesPerCoachMatch * nbMatchs + 1;

                    computed_width = width / nbCols;
                    colIndex = 1;
                    JLabel TeamIcon1 = new JLabel();
                    TeamIcon1.setFont(f0);
                    TeamIcon1.setSize(computed_width, computed_height);
                    JLabel TeamIcon2 = new JLabel();
                    TeamIcon2.setSize(computed_width, computed_height);
                    TeamIcon2.setFont(f0);

                    Team team1 = (Team) (tm.mCompetitor1);
                    if (team1.picture != null) {
                        TeamIcon1.setIcon(ImageTreatment.resize(new ImageIcon(team1.picture), computed_height, computed_height));
                    } else {
                        TeamIcon1.setIcon(new ImageIcon(new BufferedImage(computed_height, computed_height, BufferedImage.TYPE_4BYTE_ABGR)));
                    }
                    TeamIcon1.setText(team1.mName);
                    Team team2 = (Team) (tm.mCompetitor2);
                    if (team2.picture != null) {
                        TeamIcon2.setIcon(ImageTreatment.resize(new ImageIcon(team2.picture), computed_height, computed_height));
                    } else {
                        TeamIcon2.setIcon(new ImageIcon(new BufferedImage(computed_height, computed_height, BufferedImage.TYPE_4BYTE_ABGR)));
                    }
                    TeamIcon2.setText(team2.mName);
                    TeamIcon1.setHorizontalTextPosition(JLabel.LEADING);
                    TeamIcon1.setHorizontalAlignment(JLabel.RIGHT);
                    TeamIcon2.setHorizontalAlignment(JLabel.LEFT);
                    TeamIcon1.setOpaque(true);
                    TeamIcon1.setBackground(bkg);
                    TeamIcon2.setOpaque(true);
                    TeamIcon2.setBackground(bkg);

                    GridBagConstraints gbc1 = new GridBagConstraints();
                    GridBagConstraints gbc2 = new GridBagConstraints();
                    gbc1.gridx = 0;
                    gbc1.gridy = i * NbLinesPerTeamMatch;
                    gbc1.fill = GridBagConstraints.BOTH;
                    gbc1.weightx = 1.0;
                    gbc1.weighty = 1.0;
                    gbc1.gridheight = NbLinesPerTeamMatch;
                    jpnContent.add(TeamIcon1, gbc1);
                    gbc2.gridx = nbCols - 1;
                    gbc2.gridy = i * NbLinesPerTeamMatch;
                    gbc2.fill = GridBagConstraints.BOTH;
                    gbc2.weightx = 1.0;
                    gbc2.weighty = 1.0;
                    gbc2.gridheight = NbLinesPerTeamMatch;
                    jpnContent.add(TeamIcon2, gbc2);

                    JLabel v = new JLabel("");
                    v.setIcon(ImageTreatment.resize(new ImageIcon(team1.picture), computed_height, computed_height));
                    v.setFont(f1);
                    v.setOpaque(true);
                    v.setBackground(bkg);
                    v.setHorizontalAlignment(JLabel.CENTER);
                    GridBagConstraints gbcv = new GridBagConstraints();
                    gbcv.gridx = 1;
                    gbcv.gridy = i * NbLinesPerTeamMatch;
                    gbcv.fill = GridBagConstraints.BOTH;
                    gbcv.weightx = 1.0;
                    gbcv.weighty = 1.0;
                    gbcv.gridheight = 1;
                    jpnContent.add(v, gbcv);

                    JLabel n = new JLabel("" + tm.getVictories(team1) + " - " + tm.getDraw(team1) + " - " + tm.getVictories(team2));
                    n.setFont(fBig);
                    n.setBackground(bkg);
                    n.setOpaque(true);
                    n.setHorizontalAlignment(JLabel.CENTER);
                    GridBagConstraints gbcn = new GridBagConstraints();
                    gbcn.gridx = 2;
                    gbcn.gridy = i * NbLinesPerTeamMatch;
                    gbcn.fill = GridBagConstraints.BOTH;
                    gbcn.weightx = 1.0;
                    gbcn.weighty = 1.0;
                    gbcn.gridheight = 1;
                    jpnContent.add(n, gbcn);

                    JLabel l = new JLabel("");
                    l.setFont(f1);
                    l.setIcon(ImageTreatment.resize(new ImageIcon(team2.picture), computed_height, computed_height));
                    l.setHorizontalTextPosition(JLabel.LEFT);
                    l.setBackground(bkg);
                    l.setOpaque(true);
                    l.setHorizontalAlignment(JLabel.CENTER);
                    GridBagConstraints gbcl = new GridBagConstraints();
                    gbcl.gridx = 3;
                    gbcl.gridy = i * NbLinesPerTeamMatch;
                    gbcl.fill = GridBagConstraints.BOTH;
                    gbcl.weightx = 1.0;
                    gbcl.weighty = 1.0;
                    gbcl.gridheight = 1;
                    jpnContent.add(l, gbcl);

                    for (int j = 0; j < tm.mMatchs.size(); j++) {

                        Color cmBkg = bkg;
                        if (j % 2 == 0) {
                            cmBkg = new Color(bkg.getRed() - 10, bkg.getGreen() - 10, bkg.getBlue() - 10);
                        }
                        CoachMatch cm = tm.mMatchs.get(j);

                        JLabel CoachIcon1 = new JLabel();
                        CoachIcon1.setSize(computed_width, computed_height);
                        CoachIcon1.setFont(f1);
                        JLabel CoachIcon2 = new JLabel();
                        CoachIcon2.setSize(computed_width, computed_height);

                        if (cm.mCompetitor1.picture != null) {
                            CoachIcon1.setIcon(ImageTreatment.resize(new ImageIcon(cm.mCompetitor1.picture), computed_height, computed_height));
                        }
                        CoachIcon1.setText(cm.mCompetitor1.mName);
                        CoachIcon2.setFont(f1);
                        if (cm.mCompetitor2.picture != null) {
                            CoachIcon2.setIcon(ImageTreatment.resize(new ImageIcon(cm.mCompetitor2.picture), computed_height, computed_height));
                        }
                        CoachIcon2.setText(cm.mCompetitor2.mName);
                        CoachIcon1.setHorizontalAlignment(JLabel.CENTER);
                        CoachIcon1.setBackground(cmBkg);
                        CoachIcon1.setOpaque(true);
                        CoachIcon2.setBackground(cmBkg);
                        CoachIcon2.setOpaque(true);
                        CoachIcon2.setHorizontalAlignment(JLabel.CENTER);

                        gbc1 = new GridBagConstraints();
                        gbc2 = new GridBagConstraints();
                        gbc1.gridx = colIndex;
                        gbc1.gridy = i * NbLinesPerTeamMatch + 1 + j * NbLinesPerCoachMatch;
                        gbc1.fill = GridBagConstraints.BOTH;
                        gbc1.weightx = 1.0;
                        gbc1.weighty = 1.0;
                        gbc1.gridheight = nbValues;
                        jpnContent.add(CoachIcon1, gbc1);
                        gbc2.gridx = nbCols - colIndex - 1;
                        gbc2.gridy = i * NbLinesPerTeamMatch + 1 + j * NbLinesPerCoachMatch;
                        gbc2.fill = GridBagConstraints.BOTH;
                        gbc2.weightx = 1.0;
                        gbc2.weighty = 1.0;
                        gbc2.gridheight = nbValues;
                        jpnContent.add(CoachIcon2, gbc2);

                        Criteria td = Tournament.getTournament().getParams().mCriterias.get(0);
                        int value1 = cm.mValues.get(td).mValue1;
                        int value2 = cm.mValues.get(td).mValue2;
                        String sV1 = value1 >= 0 ? Integer.toString(value1) : " - ";
                        String sV2 = value2 >= 0 ? Integer.toString(value2) : " - ";

                        JLabel CoachScore1 = new JLabel();
                        CoachScore1.setSize(computed_width, computed_height);
                        CoachScore1.setFont(f1);

                        JLabel CoachScore2 = new JLabel();
                        CoachScore2.setSize(computed_width, computed_height);
                        CoachScore2.setFont(f1);

                        CoachScore1.setText(sV1);
                        CoachScore2.setText(sV2);

                        CoachScore1.setHorizontalAlignment(JLabel.CENTER);
                        CoachScore1.setBackground(cmBkg);
                        CoachScore1.setOpaque(true);
                        CoachScore2.setBackground(cmBkg);
                        CoachScore2.setOpaque(true);
                        CoachScore2.setHorizontalAlignment(JLabel.CENTER);

                        GridBagConstraints gbcs1 = new GridBagConstraints();
                        GridBagConstraints gbcs2 = new GridBagConstraints();
                        gbcs1.gridx = colIndex;
                        gbcs1.gridy = i * NbLinesPerTeamMatch + (j + 1) * NbLinesPerCoachMatch;
                        gbcs1.fill = GridBagConstraints.BOTH;
                        gbcs1.weightx = 1.0;
                        gbcs1.weighty = 1.0;

                        jpnContent.add(CoachScore1, gbcs1);

                        gbcs2.gridx = nbCols - colIndex - 1;
                        gbcs2.gridy = i * NbLinesPerTeamMatch + (j + 1) * NbLinesPerCoachMatch;
                        gbcs2.fill = GridBagConstraints.BOTH;
                        gbcs2.weightx = 1.0;
                        gbcs2.weighty = 1.0;
                        jpnContent.add(CoachScore2, gbcs2);

                        // Score                    

                        JLabel score = new JLabel(sV1 + " " + td.mName + " " + sV2);

                        score.setHorizontalAlignment(JLabel.CENTER);
                        score.setOpaque(true);
                        score.setBackground(cmBkg);
                        score.setFont(f1);
                        score.setHorizontalAlignment(JLabel.CENTER);
                        GridBagConstraints gbcs = new GridBagConstraints();
                        gbcs.gridx = colIndex + 1;
                        gbcs.gridy = i * NbLinesPerTeamMatch + j * NbLinesPerCoachMatch + 1;
                        gbcs.fill = GridBagConstraints.BOTH;
                        gbcs.weightx = 1.0;
                        gbcs.weighty = 1.0;
                        gbcs.gridheight = 2;
                        gbcs.gridwidth = 1;

                        jpnContent.add(score, gbcs);


                        Font f2 = font.deriveFont(Font.ITALIC, height / 75);
                        for (int k = 1; k < Tournament.getTournament().getParams().mCriterias.size(); k++) {
                            Criteria crit = Tournament.getTournament().getParams().mCriterias.get(k);
                            value1 = cm.mValues.get(crit).mValue1;
                            value2 = cm.mValues.get(crit).mValue2;
                            JLabel tmp = new JLabel("" + value1 + " " + crit.mName + " " + value2);
                            GridBagConstraints gbc = new GridBagConstraints();
                            tmp.setBackground(cmBkg);
                            tmp.setOpaque(true);
                            tmp.setFont(f2);
                            tmp.setHorizontalAlignment(JLabel.CENTER);
                            gbc.gridx = colIndex + 1;
                            gbc.gridy = i * NbLinesPerTeamMatch + j * NbLinesPerCoachMatch + k + 2;
                            gbc.fill = GridBagConstraints.BOTH;
                            gbc.weightx = 1.0;
                            gbc.weighty = 1.0;
                            gbc.gridheight = 1;
                            gbc.gridwidth = 1;
                            jpnContent.add(tmp, gbc);
                        }
                    }

                }

            }
        } catch (FontFormatException ex) {
            Logger.getLogger(FullScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jscrp = new javax.swing.JScrollPane();
        jpnContent = new javax.swing.JPanel();

        setAlwaysOnTop(true);
        setName("FullScreen Tourma"); // NOI18N
        setUndecorated(true);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jpnContentLayout = new javax.swing.GroupLayout(jpnContent);
        jpnContent.setLayout(jpnContentLayout);
        jpnContentLayout.setHorizontalGroup(
            jpnContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 398, Short.MAX_VALUE)
        );
        jpnContentLayout.setVerticalGroup(
            jpnContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 298, Short.MAX_VALUE)
        );

        jscrp.setViewportView(jpnContent);

        getContentPane().add(jscrp, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    boolean animationStarted = false;
    Animation animation;
    
    public class Animation extends Thread{
        

        public void run ()
        {               
            long computedTime=getHeight()/100;   
            int blockIncrement=jscrp.getVerticalScrollBar().getBlockIncrement();
            
            System.out.println("Screen Height: "+getHeight()+" ScrollBar size: "+(jscrp.getVerticalScrollBar().getMaximum()-jscrp.getVerticalScrollBar().getMinimum())+" Computed Time: "+ computedTime);
            int lastValue=0;
            while(animationStarted)
            {
                int value=jscrp.getVerticalScrollBar().getValue();
                value=value+1;                
                if (value<=lastValue)
                {
                    value=0;
                    try {
                        sleep(10000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(FullScreen.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    jscrp.getVerticalScrollBar().setValue(jscrp.getVerticalScrollBar().getMinimum());
                }
                else
                {
                    jscrp.getVerticalScrollBar().setValue(value);
                }
                
                try {
                    sleep(computedTime);
                } catch (InterruptedException ex) {
                    Logger.getLogger(FullScreen.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println("Current value: "+value+" last Value: "+lastValue);                
                lastValue=value;
            }
        }
    }
    
    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            this.dispose();
        }
        if (evt.getKeyCode() == KeyEvent.VK_S) {
            if (animationStarted) {
                jscrp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
                jscrp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                animationStarted=false;
                if (animation.isAlive())
                {
                    try {
                        animation.join();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(FullScreen.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } else {
                jscrp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
                jscrp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                animationStarted=true;
                animation=new Animation();
                animation.start();
            }
            
        }
    }//GEN-LAST:event_formKeyPressed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jpnContent;
    private javax.swing.JScrollPane jscrp;
    // End of variables declaration//GEN-END:variables

    /*private class FullScreenEffect implements ActionListener {

     int PrevX=0;
     int PrevY=0;
     int PrevWidth=0;
     int PrevHeight=0;
        
     boolean Am_I_In_FullScreen=false;
        
     @Override
     public void actionPerformed(ActionEvent arg0) {
     // TODO Auto-generated method stub

     if (Am_I_In_FullScreen == false) {

     PrevX = getX();
     PrevY = getY();
     PrevWidth = getWidth();
     PrevHeight = getHeight();

     dispose(); //Destroys the whole JFrame but keeps organized every Component                               
     //Needed if you want to use Undecorated JFrame
     //dispose() is the reason that this trick doesn't work with videos
     setUndecorated(true);

     setBounds(0, 0, getToolkit().getScreenSize().width, getToolkit().getScreenSize().height);
     setVisible(true);
     Am_I_In_FullScreen = true;
     } else {
     setVisible(true);

     setBounds(PrevX, PrevY, PrevWidth, PrevHeight);
     dispose();
     setUndecorated(false);
     setVisible(true);
     Am_I_In_FullScreen = false;
     }
     }
     }*/
}
