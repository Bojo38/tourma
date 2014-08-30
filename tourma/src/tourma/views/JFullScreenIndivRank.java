/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.views;

import java.awt.Color;
import java.awt.Dimension;
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
import tourma.data.Competitor;
import tourma.data.Criteria;
import tourma.data.IWithNameAndPicture;
import tourma.data.Match;
import tourma.data.Parameters;
import tourma.data.Round;
import tourma.data.Team;
import tourma.data.TeamMatch;
import tourma.data.Tournament;
import tourma.tableModel.mjtRanking;
import static tourma.tableModel.mjtRanking.getSubtypeByValue;
import tourma.tableModel.mjtRankingIndiv;
import tourma.utils.ImageTreatment;
import tourma.views.GraphicalMatch;

/**
 *
 * @author WFMJ7631
 */
public class JFullScreenIndivRank extends JFullScreen {

    int round;

    public JFullScreenIndivRank(int r) throws IOException {
        super();
        try {
            round = r;

            Criteria td = Tournament.getTournament().getParams().mCriterias.get(0);
            Font font = Font.createFont(Font.TRUETYPE_FONT, this.getClass().getResourceAsStream("/tourma/languages/calibri.ttf"));

            int height = getHeight();
            int width = getWidth();

            Font f0 = font.deriveFont(Font.ITALIC, height / 50);
            Font f1 = font.deriveFont(Font.BOLD, height / 50);
            Font f = font.deriveFont(Font.PLAIN, height / 50);

            int computed_height = height / 20;


            final boolean forPool = (Tournament.getTournament().getPools().size() > 0) && (!Tournament.getTournament().getRounds().get(r).mCup);

            mjtRankingIndiv ranking = new mjtRankingIndiv(round,
                    Tournament.getTournament().getParams().mRankingIndiv1,
                    Tournament.getTournament().getParams().mRankingIndiv2,
                    Tournament.getTournament().getParams().mRankingIndiv3,
                    Tournament.getTournament().getParams().mRankingIndiv4,
                    Tournament.getTournament().getParams().mRankingIndiv5,
                    Tournament.getTournament().getCoachs(),
                    Tournament.getTournament().getParams().mTeamTournament,
                    false,
                    forPool);

            int nbCols = Tournament.getTournament().getParams().getIndivRankingNumber() + 4;
            boolean teamTour = Tournament.getTournament().getParams().mTeamTournament;
            boolean clanTour = Tournament.getTournament().getClans().size() > 1;
            if (Tournament.getTournament().getParams().mTeamTournament) {
                nbCols++;
            } else {
                if (Tournament.getTournament().getClans().size() > 1) {
                    nbCols++;
                }
            }
            int computed_width = width / nbCols;


            // Number
            JLabel jlbTNum = new JLabel("#");
            jlbTNum.setFont(f1);
            jlbTNum.setOpaque(true);
            jlbTNum.setBackground(Color.BLACK);
            jlbTNum.setForeground(Color.WHITE);

            jpnContent.add(jlbTNum, getGridbBagConstraints(0, 0, 1, 1));

            int index = 1;

            if (Tournament.getTournament().getParams().mTeamTournament) {
                JLabel jlbTTeam = new JLabel("Team");
                jlbTTeam.setFont(f1);
                jlbTTeam.setOpaque(true);
                jlbTTeam.setBackground(Color.BLACK);
                jlbTTeam.setForeground(Color.WHITE);

                jpnContent.add(jlbTTeam, getGridbBagConstraints(index, 0, 1, 5));
                index += 5;
            }
            if (Tournament.getTournament().getClans().size() > 1) {
                JLabel jlbTClan = new JLabel("Clan");
                jlbTClan.setFont(f1);
                jlbTClan.setOpaque(true);

                jlbTClan.setBackground(Color.BLACK);
                jlbTClan.setForeground(Color.WHITE);
                jpnContent.add(jlbTClan, getGridbBagConstraints(index, 0, 1, 5));
                index += 5;
            }


            JLabel jlbTCoach = new JLabel("Coach");
            jlbTCoach.setFont(f1);
            jlbTCoach.setOpaque(true);
            jlbTCoach.setBackground(Color.BLACK);
            jlbTCoach.setForeground(Color.WHITE);
            jpnContent.add(jlbTCoach, getGridbBagConstraints(index, 0, 1, 5));
            index += 5;

            JLabel jlbTRoster = new JLabel("Roster");
            jlbTRoster.setFont(f1);
            jlbTRoster.setOpaque(true);
            jlbTRoster.setBackground(Color.BLACK);
            jlbTRoster.setForeground(Color.WHITE);
            jpnContent.add(jlbTRoster, getGridbBagConstraints(index, 0, 1, 3));
            index += 3;

            JLabel jlbTRosterName = new JLabel("Roster Name");
            jlbTRosterName.setFont(f1);
            jlbTRosterName.setOpaque(true);
            jlbTRosterName.setBackground(Color.BLACK);
            jlbTRosterName.setForeground(Color.WHITE);
            jpnContent.add(jlbTRosterName, getGridbBagConstraints(index, 0, 1, 3));
            index += 3;


            for (int j = 0; j < Tournament.getTournament().getParams().getIndivRankingNumber(); j++) {
                int rankingType = Tournament.getTournament().getParams().getIndivRankingType(j);
                String name = mjtRanking.getRankingString(rankingType);
                if (rankingType == 0) {
                    break;
                } else {
                    JLabel jlbRank = new JLabel(name);
                    jlbRank.setFont(f1);
                    jlbRank.setOpaque(true);
                    jlbRank.setBackground(Color.BLACK);
                    jlbRank.setForeground(Color.WHITE);
                    jpnContent.add(jlbRank, getGridbBagConstraints(index, 0, 1, 2));
                    index += 2;
                }
            }


            int nbRows = ranking.getRowCount();
            for (int i = 0; i < nbRows; i++) {
                Color bkg = new Color(255, 255, 255);
                if (i % 2 == 1) {
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
                jpnContent.add(jlbNum, getGridbBagConstraints(0, i + 1, 1, 1));

                index = 1;

                Coach coach = (Coach) ranking.getSortedDatas().get(i).getObject();
                if (Tournament.getTournament().getParams().mTeamTournament) {
                    JLabel jlb = getLabelForObject(coach.mTeamMates, computed_height, computed_width, currentFont, bkg);
                    jpnContent.add(jlb, getGridbBagConstraints(index, i + 1, 1, 5));
                    index += 5;
                }
                if (Tournament.getTournament().getClans().size() > 1) {
                    JLabel jlb = getLabelForObject(coach.mClan, computed_height, computed_width, currentFont, bkg);
                    jpnContent.add(jlb, getGridbBagConstraints(index, i + 1, 1, 5));
                    index += 5;
                }
                JLabel jlbCoach = getLabelForObject(coach, computed_height, computed_width, currentFont, bkg);
                jpnContent.add(jlbCoach, getGridbBagConstraints(index, i + 1, 1, 5));
                index += 5;

                JLabel jlbRoster = new JLabel(coach.mRoster.mName);
                jlbRoster.setFont(currentFont);
                jlbRoster.setOpaque(true);
                jlbRoster.setBackground(bkg);
                jpnContent.add(jlbRoster, getGridbBagConstraints(index, i + 1, 1, 3));
                index += 3;

                JLabel jlbTeam = new JLabel(coach.mTeam);
                jlbTeam.setFont(currentFont);
                jlbTeam.setOpaque(true);
                jlbTeam.setBackground(bkg);
                jpnContent.add(jlbTeam, getGridbBagConstraints(index, i + 1, 1, 3));
                index += 3;

                for (int j = 0; j < Tournament.getTournament().getParams().getIndivRankingNumber(); j++) {
                    int rankingType = Tournament.getTournament().getParams().getIndivRankingType(j);
                    int value = 0;
                    switch (j) {
                        case 0:
                            value = (Integer) ranking.getSortedDatas().get(i).getValue1();
                            break;
                        case 1:
                            value = (Integer) ranking.getSortedDatas().get(i).getValue2();
                            break;
                        case 2:
                            value = (Integer) ranking.getSortedDatas().get(i).getValue3();
                            break;
                        case 3:
                            value = (Integer) ranking.getSortedDatas().get(i).getValue4();
                            break;
                        case 4:
                            value = (Integer) ranking.getSortedDatas().get(i).getValue5();
                            break;
                    }
                    String name = Integer.toString(value);
                    if (rankingType == 0) {
                        break;
                    } else {
                        JLabel jlbRank = new JLabel(name);
                        jlbRank.setFont(currentFont);
                        jlbRank.setOpaque(true);
                        jlbRank.setBackground(bkg);
                        jpnContent.add(jlbRank, getGridbBagConstraints(index, i + 1, 1, 2));
                        index += 2;
                    }
                }
            }
        } catch (FontFormatException ex) {
            Logger.getLogger(JFullScreenIndivRank.class.getName()).log(Level.SEVERE, null, ex);
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

        setAlwaysOnTop(true);
        setName("FullScreen Tourma"); // NOI18N
        setUndecorated(true);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
