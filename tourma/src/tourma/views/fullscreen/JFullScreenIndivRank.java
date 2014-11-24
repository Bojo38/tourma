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
import tourma.data.Coach;
import tourma.data.Tournament;
import tourma.tableModel.MjtRanking;
import tourma.tableModel.MjtRankingIndiv;

/**
 *
 * @author WFMJ7631
 */
public final class JFullScreenIndivRank extends JFullScreen {
   
    private static final long serialVersionUID = 11L;

    private int round;

    /**
     *
     * @param r
     * @throws IOException
     */
    @SuppressWarnings("LeakingThisInConstructor")
    public JFullScreenIndivRank(int r) throws IOException {
        super();
        initComponents();
        try {
            round = r;

//            Criteria td = Tournament.getTournament().getParams().getCriteria(0);
            Font font = Font.createFont(Font.TRUETYPE_FONT, this.getClass().getResourceAsStream("/tourma/languages/calibri.ttf"));

            GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
            int width = gd.getDisplayMode().getWidth();
            int height = gd.getDisplayMode().getHeight();

            float size=(float)height / 50;
            Font f0 = font.deriveFont(Font.ITALIC, size);
            Font f1 = font.deriveFont(Font.BOLD, size);
            Font f = font.deriveFont(Font.PLAIN, size);

            int computed_height = height / 20;

            final ArrayList<Coach> coaches = new ArrayList<>();
                for (int cpt = 0; cpt < Tournament.getTournament().getCoachsCount(); cpt++) {
                    coaches.add(Tournament.getTournament().getCoach(cpt));
                }

            final boolean forPool = (Tournament.getTournament().getPoolCount() > 0) && (!Tournament.getTournament().getRound(r).isCup());

            MjtRankingIndiv ranking = new MjtRankingIndiv(round, Tournament.getTournament().getParams().getRankingIndiv1(), Tournament.getTournament().getParams().getRankingIndiv2(), Tournament.getTournament().getParams().getRankingIndiv3(), Tournament.getTournament().getParams().getRankingIndiv4(), Tournament.getTournament().getParams().getRankingIndiv5(),
                    coaches, Tournament.getTournament().getParams().isTeamTournament(),
                    false,
                    forPool);

            int nbCols = Tournament.getTournament().getParams().getIndivRankingNumber() + 4;
//            boolean teamTour = Tournament.getTournament().getParams().isTeamTournament();
//            boolean clanTour = Tournament.getTournament().getClansCount() > 1;
            if (Tournament.getTournament().getParams().isTeamTournament()) {
                nbCols++;
            } else {
                if (Tournament.getTournament().getClansCount() > 1) {
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

            if (Tournament.getTournament().getParams().isTeamTournament()) {
                JLabel jlbTTeam = new JLabel(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("Team"));
                jlbTTeam.setFont(f1);
                jlbTTeam.setOpaque(true);
                jlbTTeam.setBackground(Color.BLACK);
                jlbTTeam.setForeground(Color.WHITE);

                jpnContent.add(jlbTTeam, getGridbBagConstraints(index, 0, 1, 5));
                index += 5;
            }
            if (Tournament.getTournament().getClansCount() > 1) {
                JLabel jlbTClan = new JLabel(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("Clan"));
                jlbTClan.setFont(f1);
                jlbTClan.setOpaque(true);

                jlbTClan.setBackground(Color.BLACK);
                jlbTClan.setForeground(Color.WHITE);
                jpnContent.add(jlbTClan, getGridbBagConstraints(index, 0, 1, 5));
                index += 5;
            }


            JLabel jlbTCoach = new JLabel(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("Coach"));
            jlbTCoach.setFont(f1);
            jlbTCoach.setOpaque(true);
            jlbTCoach.setBackground(Color.BLACK);
            jlbTCoach.setForeground(Color.WHITE);
            jpnContent.add(jlbTCoach, getGridbBagConstraints(index, 0, 1, 5));
            index += 5;

            JLabel jlbTRoster = new JLabel(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("Roster"));
            jlbTRoster.setFont(f1);
            jlbTRoster.setOpaque(true);
            jlbTRoster.setBackground(Color.BLACK);
            jlbTRoster.setForeground(Color.WHITE);
            jpnContent.add(jlbTRoster, getGridbBagConstraints(index, 0, 1, 3));
            index += 3;

            JLabel jlbTRosterName = new JLabel(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("RosterName"));
            jlbTRosterName.setFont(f1);
            jlbTRosterName.setOpaque(true);
            jlbTRosterName.setBackground(Color.BLACK);
            jlbTRosterName.setForeground(Color.WHITE);
            jpnContent.add(jlbTRosterName, getGridbBagConstraints(index, 0, 1, 3));
            index += 3;


            for (int j = 0; j < Tournament.getTournament().getParams().getIndivRankingNumber(); j++) {
                int rankingType = Tournament.getTournament().getParams().getIndivRankingType(j);
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

                Coach coach = (Coach) ranking.getSortedObject(i).getObject();
                if (Tournament.getTournament().getParams().isTeamTournament()) {
                    JLabel jlb = getLabelForObject(coach.getTeamMates(), computed_height, computed_width, currentFont, bkg);
                    jpnContent.add(jlb, getGridbBagConstraints(index, i + 1, 1, 5));
                    index += 5;
                }
                if (Tournament.getTournament().getClansCount() > 1) {
                    JLabel jlb = getLabelForObject(coach.getClan(), computed_height, computed_width, currentFont, bkg);
                    jpnContent.add(jlb, getGridbBagConstraints(index, i + 1, 1, 5));
                    index += 5;
                }
                JLabel jlbCoach = getLabelForObject(coach, computed_height, computed_width, currentFont, bkg);
                jpnContent.add(jlbCoach, getGridbBagConstraints(index, i + 1, 1, 5));
                index += 5;

                JLabel jlbRoster = new JLabel(coach.getRoster().getName());
                jlbRoster.setFont(currentFont);
                jlbRoster.setOpaque(true);
                jlbRoster.setBackground(bkg);
                jpnContent.add(jlbRoster, getGridbBagConstraints(index, i + 1, 1, 3));
                index += 3;

                JLabel jlbTeam = new JLabel(coach.getTeam());
                jlbTeam.setFont(currentFont);
                jlbTeam.setOpaque(true);
                jlbTeam.setBackground(bkg);
                jpnContent.add(jlbTeam, getGridbBagConstraints(index, i + 1, 1, 3));
                index += 3;

                for (int j = 0; j < Tournament.getTournament().getParams().getIndivRankingNumber(); j++) {
                    int rankingType = Tournament.getTournament().getParams().getIndivRankingType(j);
                    int value ;
                    value=ranking.getSortedValue(i, j+1);
                    
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
    private static final Logger LOG = Logger.getLogger(JFullScreenIndivRank.class.getName());
}
