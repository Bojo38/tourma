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
import tourma.data.ObjectAnnexRanking;
import tourma.data.Parameters;
import tourma.data.Round;
import tourma.data.Team;
import tourma.data.TeamMatch;
import tourma.data.Tournament;
import tourma.tableModel.mjtAnnexRankIndiv;
import tourma.tableModel.mjtRanking;
import static tourma.tableModel.mjtRanking.getSubtypeByValue;
import tourma.tableModel.mjtRankingIndiv;
import tourma.utils.ImageTreatment;
import tourma.views.GraphicalMatch;

/**
 *
 * @author WFMJ7631
 */
public class JFullScreenIndivAnnex extends JFullScreen {

    int round;

    public JFullScreenIndivAnnex(int r, boolean full) throws IOException {
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
            int computed_width = width / 6;


            final boolean forPool = (Tournament.getTournament().getPools().size() > 0) && (!Tournament.getTournament().getRounds().get(r).mCup);

            int line = 0;

            for (int i = 0; i < Tournament.getTournament().getParams().mCriterias.size(); i++) {
                Criteria crit = Tournament.getTournament().getParams().mCriterias.get(i);

                int column = 0;
                // Title

                JLabel titleNumber = new JLabel("#");
                titleNumber.setHorizontalAlignment(JLabel.CENTER);
                titleNumber.setBackground(Color.BLACK);
                titleNumber.setForeground(Color.WHITE);
                titleNumber.setOpaque(true);
                jpnContent.add(titleNumber, getGridbBagConstraints(column, line, 1, 1));
                column++;

                if (Tournament.getTournament().getParams().mTeamTournament) {
                    JLabel titleTeam = new JLabel("Equipe");
                    titleTeam.setHorizontalAlignment(JLabel.CENTER);
                    titleTeam.setBackground(Color.BLACK);
                    titleTeam.setForeground(Color.WHITE);
                    titleTeam.setOpaque(true);
                    jpnContent.add(titleTeam, getGridbBagConstraints(column, line, 1, 5));
                    column += 5;
                }

                if (Tournament.getTournament().getClans().size() > 1) {
                    JLabel titleTeam = new JLabel("Clan");
                    titleTeam.setHorizontalAlignment(JLabel.CENTER);
                    titleTeam.setBackground(Color.BLACK);
                    titleTeam.setForeground(Color.WHITE);
                    titleTeam.setOpaque(true);
                    jpnContent.add(titleTeam, getGridbBagConstraints(column, line, 1, 5));
                    column += 5;
                }

                JLabel titleCoach = new JLabel("Coach");
                titleCoach.setHorizontalAlignment(JLabel.CENTER);
                titleCoach.setBackground(Color.BLACK);
                titleCoach.setForeground(Color.WHITE);
                titleCoach.setOpaque(true);
                jpnContent.add(titleCoach, getGridbBagConstraints(column, line, 1, 5));
                column += 5;

                JLabel titleValue = new JLabel("+ " + crit.mName);
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

                if (Tournament.getTournament().getParams().mTeamTournament) {
                    JLabel titleTeam2 = new JLabel("Equipe");
                    titleTeam2.setHorizontalAlignment(JLabel.CENTER);
                    titleTeam2.setBackground(Color.BLACK);
                    titleTeam2.setForeground(Color.WHITE);
                    titleTeam2.setOpaque(true);
                    jpnContent.add(titleTeam2, getGridbBagConstraints(column, line, 1, 5));
                    column += 5;
                }

                if (Tournament.getTournament().getClans().size() > 1) {
                    JLabel titleTeam2 = new JLabel("Clan");
                    titleTeam2.setHorizontalAlignment(JLabel.CENTER);
                    titleTeam2.setBackground(Color.BLACK);
                    titleTeam2.setForeground(Color.WHITE);
                    titleTeam2.setOpaque(true);
                    jpnContent.add(titleTeam2, getGridbBagConstraints(column, line, 1, 5));
                    column += 5;
                }

                JLabel titleCoach2 = new JLabel("Coach");
                titleCoach2.setHorizontalAlignment(JLabel.CENTER);
                titleCoach2.setBackground(Color.BLACK);
                titleCoach2.setForeground(Color.WHITE);
                titleCoach2.setOpaque(true);
                jpnContent.add(titleCoach2, getGridbBagConstraints(column, line, 1, 5));
                column += 5;

                JLabel titleValue2 = new JLabel("- " + crit.mName);
                titleValue2.setHorizontalAlignment(JLabel.CENTER);
                titleValue2.setBackground(Color.BLACK);
                titleValue2.setForeground(Color.WHITE);
                titleValue2.setOpaque(true);
                jpnContent.add(titleValue2, getGridbBagConstraints(column, line, 1, 5));
                column += 5;

                line++;

                mjtAnnexRankIndiv annexRank = new mjtAnnexRankIndiv(round, crit, 0,
                        Tournament.getTournament().getCoachs(), full,
                        Tournament.getTournament().getParams().mRankingIndiv1,
                        Tournament.getTournament().getParams().mRankingIndiv2,
                        Tournament.getTournament().getParams().mRankingIndiv3,
                        Tournament.getTournament().getParams().mRankingIndiv4,
                        Tournament.getTournament().getParams().mRankingIndiv5,
                        Tournament.getTournament().getParams().mTeamTournament,
                        false);

                int line_pos = line;
                int line_neg = line;
                int nb_col = column;
                for (int j = 0; j < annexRank.getRowCount(); j++) {
                    Color bkg = new Color(255, 255, 255);
                    if (j % 2 == 1) {
                        bkg = new Color(220, 220, 220);
                    }
                    column = 0;
                    ObjectAnnexRanking annex = (ObjectAnnexRanking) annexRank.getSortedDatas().get(j);
                    Coach coach = (Coach) annex.getObject();

                    JLabel number = new JLabel("" + (j + 1));
                    number.setBackground(bkg);
                    number.setOpaque(true);
                    jpnContent.add(number, getGridbBagConstraints(column, line_pos, 1, 1));
                    column++;

                    if (Tournament.getTournament().getParams().mTeamTournament) {
                        JLabel team = getLabelForObject(coach.mTeamMates, computed_height, computed_width, f, bkg);
                        jpnContent.add(team, getGridbBagConstraints(column, line_pos, 1, 5));
                        column += 5;
                    }

                    if (Tournament.getTournament().getClans().size() > 1) {
                        JLabel team = getLabelForObject(coach.mClan, computed_height, computed_width, f, bkg);
                        jpnContent.add(team, getGridbBagConstraints(column, line_pos, 1, 5));
                        column += 5;
                    }

                    JLabel lcoach = getLabelForObject(coach, computed_height, computed_width, f, bkg);
                    jpnContent.add(lcoach, getGridbBagConstraints(column, line_pos, 1, 5));
                    column += 5;

                    JLabel value = new JLabel(crit.mName +": + "+ annex.getValue());
                    value.setFont(f);
                    value.setOpaque(true);
                    value.setBackground(bkg);
                    jpnContent.add(value, getGridbBagConstraints(column, line_pos, 1, 5));
                    column += 5;

                    line_pos++;
                }



                annexRank = new mjtAnnexRankIndiv(round, crit, 1,
                        Tournament.getTournament().getCoachs(), full,
                        Tournament.getTournament().getParams().mRankingIndiv1,
                        Tournament.getTournament().getParams().mRankingIndiv2,
                        Tournament.getTournament().getParams().mRankingIndiv3,
                        Tournament.getTournament().getParams().mRankingIndiv4,
                        Tournament.getTournament().getParams().mRankingIndiv5,
                        Tournament.getTournament().getParams().mTeamTournament,
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

                    ObjectAnnexRanking annex = (ObjectAnnexRanking) annexRank.getSortedDatas().get(j);
                    Coach coach = (Coach) annex.getObject();


                    JLabel number = new JLabel("" + (j + 1));
                    number.setBackground(bkg);
                    number.setOpaque(true);
                    jpnContent.add(number, getGridbBagConstraints(column, line_neg, 1, 1));
                    column++;

                    if (Tournament.getTournament().getParams().mTeamTournament) {
                        JLabel team = getLabelForObject(coach.mTeamMates, computed_height, computed_width, f, bkg);
                        jpnContent.add(team, getGridbBagConstraints(column, line_neg, 1, 5));
                        column += 5;
                    }

                    if (Tournament.getTournament().getClans().size() > 1) {
                        JLabel team = getLabelForObject(coach.mClan, computed_height, computed_width, f, bkg);
                        jpnContent.add(team, getGridbBagConstraints(column, line_neg, 1, 5));
                        column += 5;
                    }

                    JLabel lcoach = getLabelForObject(coach, computed_height, computed_width, f, bkg);
                    jpnContent.add(lcoach, getGridbBagConstraints(column, line_neg, 1, 5));
                    column += 5;

                    JLabel value = new JLabel(crit.mName +": - "+ annex.getValue());
                    value.setFont(f);
                    value.setOpaque(true);
                    value.setBackground(bkg);
                    jpnContent.add(value, getGridbBagConstraints(column, line_neg, 1, 5));
                    column += 5;

                    line_neg++;
                }
                line = Math.max(line_pos, line_neg);
            }

        } catch (FontFormatException ex) {
            Logger.getLogger(JFullScreenIndivAnnex.class.getName()).log(Level.SEVERE, null, ex);
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
