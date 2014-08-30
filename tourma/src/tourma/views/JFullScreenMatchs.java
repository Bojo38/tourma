/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import tourma.data.Coach;
import tourma.data.CoachMatch;
import tourma.data.Competitor;
import tourma.data.Criteria;
import tourma.data.Match;
import tourma.data.Round;
import tourma.data.Team;
import tourma.data.TeamMatch;
import tourma.data.Tournament;
import tourma.utils.ImageTreatment;

/**
 *
 * @author WFMJ7631
 */
public class JFullScreenMatchs extends JFullScreen {

    Round round;

    public JFullScreenMatchs(Round r) throws IOException {
        super();
        
        try {
            round = r;

            Criteria td = Tournament.getTournament().getParams().mCriterias.get(0);
            Font font = Font.createFont(Font.TRUETYPE_FONT, this.getClass().getResourceAsStream("/tourma/languages/calibri.ttf"));

            int height = getHeight();
            int width = getWidth();

            Font f0 = font.deriveFont(Font.ITALIC, height / 50);
            Font f1 = font.deriveFont(Font.BOLD, height / 50);
            Font f1Winner = font.deriveFont(Font.BOLD, height / 50);
            Font f1Draw = font.deriveFont(Font.ITALIC, height / 50);
            Font f1Looser = font.deriveFont(Font.PLAIN, height / 50);
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
                        JLabel ClanIcon1 = getLabelForObject(((Coach) (cm.mCompetitor1)).mClan, computed_height, computed_width, f0, bkg);
                        JLabel ClanIcon2 = getLabelForObject(((Coach) (cm.mCompetitor2)).mClan, computed_height, computed_width, f0, bkg);

                        ClanIcon1.setHorizontalTextPosition(JLabel.LEADING);
                        ClanIcon1.setHorizontalAlignment(JLabel.RIGHT);
                        ClanIcon2.setHorizontalAlignment(JLabel.LEFT);

                        jpnContent.add(ClanIcon1, getGridbBagConstraints(0, i * (rowspan + 1), rowspan + 1, 1));
                        jpnContent.add(ClanIcon2, getGridbBagConstraints(nbCols - 1, i * (rowspan + 1), rowspan + 1, 1));
                    }
                    if (Tournament.getTournament().getParams().mTeamTournament && (Tournament.getTournament().getParams().mTeamIndivPairing == 0)) {
                        nbCols = 5;
                        computed_width = width / nbCols;
                        colIndex = 1;

                        JLabel TeamIcon1 = getLabelForObject(((Coach) (cm.mCompetitor1)).mTeamMates, computed_height, computed_width, f0, bkg);
                        JLabel TeamIcon2 = getLabelForObject(((Coach) (cm.mCompetitor2)).mTeamMates, computed_height, computed_width, f0, bkg);
                        TeamIcon1.setHorizontalTextPosition(JLabel.LEADING);
                        TeamIcon1.setHorizontalAlignment(JLabel.RIGHT);
                        TeamIcon2.setHorizontalAlignment(JLabel.LEFT);

                        jpnContent.add(TeamIcon1, getGridbBagConstraints(0, i * (rowspan + 1), rowspan + 1, 1));
                        jpnContent.add(TeamIcon2, getGridbBagConstraints(nbCols - 1, i * (rowspan + 1), rowspan + 1, 1));
                    }

                    JLabel CoachIcon1 = getLabelForObject(cm.mCompetitor1, computed_height, computed_width, getCoachMatchFont(cm, cm.mCompetitor1, f1Winner, f1Looser, f1Draw, f1), bkg);
                    JLabel CoachIcon2 = getLabelForObject(cm.mCompetitor2, computed_height, computed_width, getCoachMatchFont(cm, cm.mCompetitor2, f1Winner, f1Looser, f1Draw, f1), bkg);

                    CoachIcon1.setHorizontalAlignment(JLabel.CENTER);
                    CoachIcon2.setHorizontalAlignment(JLabel.CENTER);

                    jpnContent.add(CoachIcon1, getGridbBagConstraints(colIndex, i * (rowspan + 1), rowspan, 1));
                    jpnContent.add(CoachIcon2, getGridbBagConstraints(nbCols - colIndex - 1, i * (rowspan + 1), rowspan , 1));


                    int value1 = cm.mValues.get(td).mValue1;
                    int value2 = cm.mValues.get(td).mValue2;
                    String sV1 = value1 >= 0 ? Integer.toString(value1) : " - ";
                    String sV2 = value2 >= 0 ? Integer.toString(value2) : " - ";


                    JLabel CoachScore1 = new JLabel();
                    CoachScore1.setSize(computed_width, computed_height);
                    CoachScore1.setFont(getCoachMatchFont(cm, cm.mCompetitor1, f1Winner, f1Looser, f1Draw, f1));

                    JLabel CoachScore2 = new JLabel();
                    CoachScore2.setSize(computed_width, computed_height);
                    CoachScore2.setFont(getCoachMatchFont(cm, cm.mCompetitor2, f1Winner, f1Looser, f1Draw, f1));

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

                    JLabel score = new JLabel(sV1 + " " + td.mName + " " + sV2);

                    score.setHorizontalAlignment(JLabel.CENTER);
                    score.setOpaque(true);
                    score.setBackground(bkg);
                    score.setFont(f1);

                    jpnContent.add(score, getGridbBagConstraints(colIndex + 1, i * (rowspan + 1), 2, 1));


                    Font f2 = font.deriveFont(Font.ITALIC, height / 75);
                    for (int j = 1; j < Tournament.getTournament().getParams().mCriterias.size(); j++) {
                        Criteria crit = Tournament.getTournament().getParams().mCriterias.get(j);
                        value1 = cm.mValues.get(crit).mValue1;
                        value2 = cm.mValues.get(crit).mValue2;
                        JLabel tmp = new JLabel("" + value1 + " " + crit.mName + " " + value2);
                        tmp.setBackground(bkg);
                        tmp.setOpaque(true);
                        tmp.setFont(f2);
                        tmp.setHorizontalAlignment(JLabel.CENTER);
                        jpnContent.add(tmp, getGridbBagConstraints(colIndex + 1, i * (rowspan + 1) + j + 1, 1, 1));
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

                    JLabel TeamIcon1 = getLabelForObject(tm.mCompetitor1, computed_height, computed_width, f0, bkg);
                    JLabel TeamIcon2 = getLabelForObject(tm.mCompetitor2, computed_height, computed_width, f0, bkg);

                    TeamIcon1.setHorizontalTextPosition(JLabel.LEADING);
                    TeamIcon1.setHorizontalAlignment(JLabel.RIGHT);
                    TeamIcon2.setHorizontalAlignment(JLabel.LEFT);

                    jpnContent.add(TeamIcon1, getGridbBagConstraints(0, i * NbLinesPerTeamMatch, NbLinesPerTeamMatch, 1));
                    jpnContent.add(TeamIcon2, getGridbBagConstraints(nbCols - 1, i * NbLinesPerTeamMatch, NbLinesPerTeamMatch, 1));

                    JLabel v = new JLabel("");
                    v.setIcon(ImageTreatment.resize(new ImageIcon(tm.mCompetitor1.getPicture()), computed_height, computed_height));
                    v.setFont(f1);
                    v.setOpaque(true);
                    v.setBackground(bkg);
                    v.setHorizontalAlignment(JLabel.CENTER);

                    jpnContent.add(v, getGridbBagConstraints(1, i * NbLinesPerTeamMatch, 1, 1));

                    JLabel n = new JLabel("" + tm.getVictories((Team) tm.mCompetitor1) + " - " + tm.getDraw((Team) tm.mCompetitor1) + " - " + tm.getVictories((Team) tm.mCompetitor2));
                    n.setFont(fBig);
                    n.setBackground(bkg);
                    n.setOpaque(true);
                    n.setHorizontalAlignment(JLabel.CENTER);
                    jpnContent.add(n, getGridbBagConstraints(2, i * NbLinesPerTeamMatch, 1, 1));

                    JLabel l = new JLabel("");
                    l.setFont(f1);
                    l.setIcon(ImageTreatment.resize(new ImageIcon(tm.mCompetitor2.getPicture()), computed_height, computed_height));
                    l.setHorizontalTextPosition(JLabel.LEFT);
                    l.setBackground(bkg);
                    l.setOpaque(true);
                    l.setHorizontalAlignment(JLabel.CENTER);
                    jpnContent.add(l, getGridbBagConstraints(3, i * NbLinesPerTeamMatch, 1, 1));

                    for (int j = 0; j < tm.mMatchs.size(); j++) {

                        Color cmBkg = bkg;
                        if (j % 2 == 0) {
                            cmBkg = new Color(bkg.getRed() - 10, bkg.getGreen() - 10, bkg.getBlue() - 10);
                        }
                        CoachMatch cm = tm.mMatchs.get(j);

                        JLabel CoachIcon1 = getLabelForObject(cm.mCompetitor1, computed_height, computed_width, getCoachMatchFont(cm, cm.mCompetitor1, f1Winner, f1Looser, f1Draw, f1), cmBkg);
                        JLabel CoachIcon2 = getLabelForObject(cm.mCompetitor2, computed_height, computed_width, getCoachMatchFont(cm, cm.mCompetitor2, f1Winner, f1Looser, f1Draw, f1), cmBkg);
                        CoachIcon1.setHorizontalAlignment(JLabel.CENTER);
                        CoachIcon2.setHorizontalAlignment(JLabel.CENTER);
                       
                        jpnContent.add(CoachIcon1, getGridbBagConstraints(colIndex, i * NbLinesPerTeamMatch + 1 + j * NbLinesPerCoachMatch, nbValues, 1));
                        jpnContent.add(CoachIcon2, getGridbBagConstraints(nbCols - colIndex - 1, i * NbLinesPerTeamMatch + 1 + j * NbLinesPerCoachMatch, nbValues, 1));

                        int value1 = cm.mValues.get(td).mValue1;
                        int value2 = cm.mValues.get(td).mValue2;
                        String sV1 = value1 >= 0 ? Integer.toString(value1) : " - ";
                        String sV2 = value2 >= 0 ? Integer.toString(value2) : " - ";

                        JLabel CoachScore1 = new JLabel();
                        CoachScore1.setSize(computed_width, computed_height);
                        CoachScore1.setFont(getCoachMatchFont(cm, cm.mCompetitor1, f1Winner, f1Looser, f1Draw, f1));

                        JLabel CoachScore2 = new JLabel();
                        CoachScore2.setSize(computed_width, computed_height);
                        CoachScore2.setFont(getCoachMatchFont(cm, cm.mCompetitor2, f1Winner, f1Looser, f1Draw, f1));

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

                        JLabel score = new JLabel(sV1 + " " + td.mName + " " + sV2);

                        score.setHorizontalAlignment(JLabel.CENTER);
                        score.setOpaque(true);
                        score.setBackground(cmBkg);
                        score.setFont(f1);
                        score.setHorizontalAlignment(JLabel.CENTER);
                        jpnContent.add(score, getGridbBagConstraints(colIndex + 1, i * NbLinesPerTeamMatch + j * NbLinesPerCoachMatch + 1, 2, 1));

                        Font f2 = font.deriveFont(Font.ITALIC, height / 75);
                        for (int k = 1; k < Tournament.getTournament().getParams().mCriterias.size(); k++) {
                            Criteria crit = Tournament.getTournament().getParams().mCriterias.get(k);
                            value1 = cm.mValues.get(crit).mValue1;
                            value2 = cm.mValues.get(crit).mValue2;
                            JLabel tmp = new JLabel("" + value1 + " " + crit.mName + " " + value2);
                            tmp.setBackground(cmBkg);
                            tmp.setOpaque(true);
                            tmp.setFont(f2);
                            tmp.setHorizontalAlignment(JLabel.CENTER);
                            jpnContent.add(tmp, getGridbBagConstraints(colIndex + 1, i * NbLinesPerTeamMatch + j * NbLinesPerCoachMatch + k + 2, 1, 1));
                        }
                    }

                }

            }
        } catch (FontFormatException ex) {
            Logger.getLogger(JFullScreenMatchs.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Font getCoachMatchFont(CoachMatch cm, Competitor comp, Font winner, Font looser, Font draw, Font def) {
        Font f;
        if (comp.equals(cm.getWinner())) {
            f = winner;
        } else {
            if (comp.equals(cm.getLooser())) {
                f = looser;
            } else {
                Criteria td = Tournament.getTournament().getParams().mCriterias.get(0);
                if ((cm.mValues.get(td).mValue1 == -1) || (cm.mValues.get(td).mValue2 == -1)) {
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
        setUndecorated(true);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    boolean animationStarted = false;
    Animation animation;

    public class Animation extends Thread {

        public void run() {
            long computedTime = getHeight() / 100;
            int blockIncrement = jscrp.getVerticalScrollBar().getBlockIncrement();

            System.out.println("Screen Height: " + getHeight() + " ScrollBar size: " + (jscrp.getVerticalScrollBar().getMaximum() - jscrp.getVerticalScrollBar().getMinimum()) + " Computed Time: " + computedTime);
            int lastValue = 0;
            while (animationStarted) {
                int value = jscrp.getVerticalScrollBar().getValue();
                value = value + 1;
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
                System.out.println("Current value: " + value + " last Value: " + lastValue);
                lastValue = value;
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
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
