/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.views.fullscreen;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import tourma.data.Coach;
import tourma.data.Criteria;
import tourma.data.ObjectAnnexRanking;
import tourma.data.ObjectRanking;
import tourma.data.Tournament;
import tourma.tableModel.MjtAnnexRankIndiv;

/**
 *
 * @author WFMJ7631
 */
public final class JFullScreenIndivAnnex extends JFullScreen {

    private int round;

    /**
     *
     * @param r
     * @param full
     * @throws IOException
     */
    @SuppressWarnings("LeakingThisInConstructor")
    public JFullScreenIndivAnnex(int r, boolean full) throws IOException {
        super();
        initComponents();
        try {
            round = r;

//            Criteria td = Tournament.getTournament().getParams().getCriteria(0);
            Font font = Font.createFont(Font.TRUETYPE_FONT, this.getClass().getResourceAsStream("/tourma/languages/calibri.ttf"));

            int height = getHeight();
            int width = getWidth();

            float size = (float) height / 50;
//            Font f0 = font.deriveFont(Font.ITALIC, height / 50);
//            Font f1 = font.deriveFont(Font.BOLD, size);
            Font f = font.deriveFont(Font.PLAIN, size);

            int computed_height = height / 20;
            int computed_width = width / 6;

            final boolean forPool = (Tournament.getTournament().getPoolCount() > 0) && (!Tournament.getTournament().getRound(r).isCup());

            int line = 0;

            for (int i = 0; i < Tournament.getTournament().getParams().getCriteriaCount(); i++) {
                Criteria crit = Tournament.getTournament().getParams().getCriteria(i);

                int column = 0;
                // Title

                JLabel titleNumber = new JLabel("#");
                titleNumber.setHorizontalAlignment(JLabel.CENTER);
                titleNumber.setBackground(Color.BLACK);
                titleNumber.setForeground(Color.WHITE);
                titleNumber.setOpaque(true);
                jpnContent.add(titleNumber, getGridbBagConstraints(column, line, 1, 1));
                column++;

                if (Tournament.getTournament().getParams().isTeamTournament()) {
                    JLabel titleTeam = new JLabel(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("Team"));
                    titleTeam.setHorizontalAlignment(JLabel.CENTER);
                    titleTeam.setBackground(Color.BLACK);
                    titleTeam.setForeground(Color.WHITE);
                    titleTeam.setOpaque(true);
                    jpnContent.add(titleTeam, getGridbBagConstraints(column, line, 1, 5));
                    column += 5;
                }

                if (Tournament.getTournament().getClansCount() > 1) {
                    JLabel titleTeam = new JLabel(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("Clan"));
                    titleTeam.setHorizontalAlignment(JLabel.CENTER);
                    titleTeam.setBackground(Color.BLACK);
                    titleTeam.setForeground(Color.WHITE);
                    titleTeam.setOpaque(true);
                    jpnContent.add(titleTeam, getGridbBagConstraints(column, line, 1, 5));
                    column += 5;
                }

                JLabel titleCoach = new JLabel(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("Coach"));
                titleCoach.setHorizontalAlignment(JLabel.CENTER);
                titleCoach.setBackground(Color.BLACK);
                titleCoach.setForeground(Color.WHITE);
                titleCoach.setOpaque(true);
                jpnContent.add(titleCoach, getGridbBagConstraints(column, line, 1, 5));
                column += 5;

                JLabel titleValue = new JLabel("+ " + crit.getName());
                titleValue.setHorizontalAlignment(JLabel.CENTER);
                titleValue.setBackground(Color.BLACK);
                titleValue.setForeground(Color.WHITE);
                titleValue.setOpaque(true);
                jpnContent.add(titleValue, getGridbBagConstraints(column, line, 1, 5));
                column += 5;

                JLabel emptyT = new JLabel("");
                emptyT.setBackground(Color.WHITE);
                emptyT.setOpaque(true);
                jpnContent.add(emptyT, getGridbBagConstraints(column, line, 1, 1));
                column++;

                JLabel titleNumber2 = new JLabel("#");
                titleNumber2.setHorizontalAlignment(JLabel.CENTER);
                titleNumber2.setBackground(Color.BLACK);
                titleNumber2.setForeground(Color.WHITE);
                titleNumber2.setOpaque(true);
                jpnContent.add(titleNumber2, getGridbBagConstraints(column, line, 1, 1));
                column++;

                if (Tournament.getTournament().getParams().isTeamTournament()) {
                    JLabel titleTeam2 = new JLabel(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("Team"));
                    titleTeam2.setHorizontalAlignment(JLabel.CENTER);
                    titleTeam2.setBackground(Color.BLACK);
                    titleTeam2.setForeground(Color.WHITE);
                    titleTeam2.setOpaque(true);
                    jpnContent.add(titleTeam2, getGridbBagConstraints(column, line, 1, 5));
                    column += 5;
                }

                if (Tournament.getTournament().getClansCount() > 1) {
                    JLabel titleTeam2 = new JLabel(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("Clan"));
                    titleTeam2.setHorizontalAlignment(JLabel.CENTER);
                    titleTeam2.setBackground(Color.BLACK);
                    titleTeam2.setForeground(Color.WHITE);
                    titleTeam2.setOpaque(true);
                    jpnContent.add(titleTeam2, getGridbBagConstraints(column, line, 1, 5));
                    column += 5;
                }

                JLabel titleCoach2 = new JLabel(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("Coach"));
                titleCoach2.setHorizontalAlignment(JLabel.CENTER);
                titleCoach2.setBackground(Color.BLACK);
                titleCoach2.setForeground(Color.WHITE);
                titleCoach2.setOpaque(true);
                jpnContent.add(titleCoach2, getGridbBagConstraints(column, line, 1, 5));
                column += 5;

                JLabel titleValue2 = new JLabel("- " + crit.getName());
                titleValue2.setHorizontalAlignment(JLabel.CENTER);
                titleValue2.setBackground(Color.BLACK);
                titleValue2.setForeground(Color.WHITE);
                titleValue2.setOpaque(true);
                jpnContent.add(titleValue2, getGridbBagConstraints(column, line, 1, 5));
                column += 5;

                line++;

                final ArrayList<Coach> coaches = new ArrayList<>();
                for (int cpt = 0; cpt < Tournament.getTournament().getCoachsCount(); cpt++) {
                    coaches.add(Tournament.getTournament().getCoach(cpt));
                }
                MjtAnnexRankIndiv annexRank = new MjtAnnexRankIndiv(round, crit, 0,
                        coaches, full, Tournament.getTournament().getParams().getRankingIndiv1(), Tournament.getTournament().getParams().getRankingIndiv2(), Tournament.getTournament().getParams().getRankingIndiv3(), Tournament.getTournament().getParams().getRankingIndiv4(), Tournament.getTournament().getParams().getRankingIndiv5(), Tournament.getTournament().getParams().isTeamTournament(),
                        false);

                int line_pos = line;
                int line_neg = line;
                int nb_col = column;
                for (int j = 0; j < annexRank.getRowCount(); j++) {
                    Color bkg = new Color(255, 255, 255);
                    if (j % 2 != 0) {
                        bkg = new Color(220, 220, 220);
                    }
                    column = 0;
                    ObjectRanking obj = annexRank.getSortedObject(j);
                    if (obj instanceof ObjectAnnexRanking) {
                        ObjectAnnexRanking annex = (ObjectAnnexRanking) obj;
                        Coach coach = (Coach) annex.getObject();

                        JLabel number = new JLabel("" + (j + 1));
                        number.setBackground(bkg);
                        number.setOpaque(true);
                        jpnContent.add(number, getGridbBagConstraints(column, line_pos, 1, 1));
                        column++;

                        if (Tournament.getTournament().getParams().isTeamTournament()) {
                            JLabel team = getLabelForObject(coach.getTeamMates(), computed_height, computed_width, f, bkg);
                            jpnContent.add(team, getGridbBagConstraints(column, line_pos, 1, 5));
                            column += 5;
                        }

                        if (Tournament.getTournament().getClansCount() > 1) {
                            JLabel team = getLabelForObject(coach.getClan(), computed_height, computed_width, f, bkg);
                            jpnContent.add(team, getGridbBagConstraints(column, line_pos, 1, 5));
                            column += 5;
                        }

                        JLabel lcoach = getLabelForObject(coach, computed_height, computed_width, f, bkg);
                        jpnContent.add(lcoach, getGridbBagConstraints(column, line_pos, 1, 5));
                        column += 5;

                        JLabel value = new JLabel(crit.getName() + ": + " + annex.getValue());
                        value.setFont(f);
                        value.setOpaque(true);
                        value.setBackground(bkg);
                        jpnContent.add(value, getGridbBagConstraints(column, line_pos, 1, 5));
                        column += 5;

                        line_pos++;
                    }
                }

                annexRank = new MjtAnnexRankIndiv(round, crit, 1,
                        coaches, full, Tournament.getTournament().getParams().getRankingIndiv1(), Tournament.getTournament().getParams().getRankingIndiv2(), Tournament.getTournament().getParams().getRankingIndiv3(), Tournament.getTournament().getParams().getRankingIndiv4(), Tournament.getTournament().getParams().getRankingIndiv5(), Tournament.getTournament().getParams().isTeamTournament(),
                        false);

                for (int j = 0; j < annexRank.getRowCount(); j++) {
                    Color bkg = new Color(255, 255, 255);
                    if (j % 2 == 0) {
                        bkg = new Color(220, 220, 220);
                    }
                    column = nb_col / 2;

                    JLabel empty = new JLabel("");
                    empty.setBackground(Color.WHITE);
                    empty.setOpaque(true);
                    jpnContent.add(empty, getGridbBagConstraints(column, line_neg, 1, 1));
                    column++;

                    ObjectRanking obj = annexRank.getSortedObject(j);
                    if (obj instanceof ObjectAnnexRanking) {
                        ObjectAnnexRanking annex = (ObjectAnnexRanking) obj;
                        Coach coach = (Coach) annex.getObject();

                        JLabel number = new JLabel("" + (j + 1));
                        number.setBackground(bkg);
                        number.setOpaque(true);
                        jpnContent.add(number, getGridbBagConstraints(column, line_neg, 1, 1));
                        column++;

                        if (Tournament.getTournament().getParams().isTeamTournament()) {
                            JLabel team = getLabelForObject(coach.getTeamMates(), computed_height, computed_width, f, bkg);
                            jpnContent.add(team, getGridbBagConstraints(column, line_neg, 1, 5));
                            column += 5;
                        }

                        if (Tournament.getTournament().getClansCount() > 1) {
                            JLabel team = getLabelForObject(coach.getClan(), computed_height, computed_width, f, bkg);
                            jpnContent.add(team, getGridbBagConstraints(column, line_neg, 1, 5));
                            column += 5;
                        }

                        JLabel lcoach = getLabelForObject(coach, computed_height, computed_width, f, bkg);
                        jpnContent.add(lcoach, getGridbBagConstraints(column, line_neg, 1, 5));
                        column += 5;

                        JLabel value = new JLabel(crit.getName() + ": - " + annex.getValue());
                        value.setFont(f);
                        value.setOpaque(true);
                        value.setBackground(bkg);
                        jpnContent.add(value, getGridbBagConstraints(column, line_neg, 1, 5));
                        column += 5;

                        line_neg++;
                    }
                }
                line = Math.max(line_pos, line_neg);
            }

        } catch (FontFormatException ex) {
            Logger.getLogger(JFullScreenIndivAnnex.class.getName()).log(Level.SEVERE, null, ex);
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
        setUndecorated(true);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    private static final Logger LOG = Logger.getLogger(JFullScreenIndivAnnex.class.getName());
}
