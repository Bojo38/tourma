/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.views.fullscreen;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import tourma.data.Criteria;
import tourma.data.Team;
import tourma.data.Tournament;
import tourma.tableModel.MjtRanking;
import tourma.tableModel.MjtRankingTeam;

/**
 *
 * @author WFMJ7631
 */
public class JFullScreenTeamRank extends JFullScreen {

    int round;

    /**
     *
     * @param r
     * @throws IOException
     */
    public JFullScreenTeamRank(int r) throws IOException {
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

            MjtRankingTeam ranking = new MjtRankingTeam(
                    Tournament.getTournament().getParams().mTeamVictoryOnly,
                    round,
                    Tournament.getTournament().getTeams(),
                    false);

            int nbCols = Tournament.getTournament().getParams().getTeamRankingNumber() + 3;
            int computed_width = width / nbCols;

            // Number
            JLabel jlbTNum = new JLabel("#");
            jlbTNum.setFont(f1);
            jlbTNum.setOpaque(true);
            jlbTNum.setBackground(Color.BLACK);
            jlbTNum.setForeground(Color.WHITE);

            jpnContent.add(jlbTNum, getGridbBagConstraints(0, 0, 1, 1));

            int index = 1;


            JLabel jlbTCoach = new JLabel(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("Team"));
            jlbTCoach.setFont(f1);
            jlbTCoach.setOpaque(true);
            jlbTCoach.setBackground(Color.BLACK);
            jlbTCoach.setForeground(Color.WHITE);
            jpnContent.add(jlbTCoach, getGridbBagConstraints(index, 0, 1, 5));
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
                    jpnContent.add(jlbRank, getGridbBagConstraints(index, 0, 1, 2));
                    index += 2;
                }
            }


            int nbRows = ranking.getRowCount();
            for (int i = 0; i < nbRows; i++) {
                Color bkg = new Color(255, 255, 255);
                if (i % 2 !=0) {
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

                Team team = (Team) ranking.getSortedDatas().get(i).getObject();
                JLabel jlbCoach = getLabelForObject(team, computed_height, computed_width, currentFont, bkg);
                jpnContent.add(jlbCoach, getGridbBagConstraints(index, i + 1, 1, 5));
                index += 5;

                for (int j = 0; j < Tournament.getTournament().getParams().getTeamRankingNumber(); j++) {
                    int rankingType = Tournament.getTournament().getParams().getTeamRankingType(j);
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
            Logger.getLogger(JFullScreenTeamRank.class.getName()).log(Level.SEVERE, null, ex);
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
    private static final Logger LOG = Logger.getLogger(JFullScreenTeamRank.class.getName());
}
