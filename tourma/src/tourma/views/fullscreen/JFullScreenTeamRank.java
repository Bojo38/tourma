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
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import tourma.data.Team;
import tourma.data.Tournament;
import tourma.tableModel.MjtRanking;
import tourma.tableModel.MjtRankingTeam;

/**
 *
 * @author WFMJ7631
 */
public final class JFullScreenTeamRank extends JFullScreen {

    private int round;

    /**
     *
     * @param r
     * @throws IOException
     */
    @SuppressWarnings("LeakingThisInConstructor")
    public JFullScreenTeamRank(int r) throws IOException {
        super();
        initComponents();
        try {
            round = r;

//            Criteria td = Tournament.getTournament().getParams().getCriteria(0);
            Font font = Font.createFont(Font.TRUETYPE_FONT, this.getClass().getResourceAsStream("/tourma/languages/calibri.ttf"));

           GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
            int width = gd.getDisplayMode().getWidth();
            int height = gd.getDisplayMode().getHeight();

            Font f0 = font.deriveFont(Font.ITALIC,(float) height / 50);
            Font f1 = font.deriveFont(Font.BOLD,(float) height / 50);
            Font f = font.deriveFont(Font.PLAIN, (float)height / 50);

            int computed_height = height / 20;

            final boolean forPool = (Tournament.getTournament().getPoolCount() > 0) && (!Tournament.getTournament().getRound(r).isCup());

            ArrayList<Team> teams = new ArrayList<>();
            for (int cpt = 0; cpt < Tournament.getTournament().getTeamsCount(); cpt++) {
                teams.add(Tournament.getTournament().getTeam(cpt));
            }
            MjtRankingTeam ranking = new MjtRankingTeam(
                    Tournament.getTournament().getParams().isTeamVictoryOnly(),
                    round,
                    teams,
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
                if (i % 2 != 0) {
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

                Team team = (Team) ranking.getSortedObject(i).getObject();
                JLabel jlbCoach = getLabelForObject(team, computed_height, computed_width, currentFont, bkg);
                jpnContent.add(jlbCoach, getGridbBagConstraints(index, i + 1, 1, 5));
                index += 5;

                for (int j = 0; j < Tournament.getTournament().getParams().getTeamRankingNumber(); j++) {
                    int rankingType = Tournament.getTournament().getParams().getTeamRankingType(j);
                    int value ;
                    value = ranking.getSortedValue(i, j + 1);
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
        this.getGraphicsConfiguration().getDevice().setFullScreenWindow(this);
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
    private static final Logger LOG = Logger.getLogger(JFullScreenTeamRank.class.getName());
}
