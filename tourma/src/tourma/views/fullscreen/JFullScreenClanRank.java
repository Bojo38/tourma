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
import tourma.data.Clan;
import tourma.data.Coach;
import tourma.data.Criteria;
import tourma.data.Tournament;
import tourma.tableModel.MjtRanking;
import tourma.tableModel.MjtRankingClan;

/**
 *
 * @author WFMJ7631
 */
public class JFullScreenClanRank extends JFullScreen {

    int round;

    /**
     *
     * @param r
     * @throws IOException
     */
    public JFullScreenClanRank(int r) throws IOException {
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

            MjtRankingClan ranking = new MjtRankingClan(
                    round,
                    Tournament.getTournament().getClans(),
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


            JLabel jlbTCoach = new JLabel(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("CLAN"));
            jlbTCoach.setFont(f1);
            jlbTCoach.setOpaque(true);
            jlbTCoach.setBackground(Color.BLACK);
            jlbTCoach.setForeground(Color.WHITE);
            jpnContent.add(jlbTCoach, getGridbBagConstraints(index, 0, 1, 5));
            index += 5;

            JLabel jlbTCoachs = new JLabel(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("Members"));
            jlbTCoachs.setFont(f1);
            jlbTCoachs.setOpaque(true);
            jlbTCoachs.setBackground(Color.BLACK);
            jlbTCoachs.setForeground(Color.WHITE);
            jpnContent.add(jlbTCoachs, getGridbBagConstraints(index, 0, 1, 5));
            index += 5;


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
            int clanIndex=0;
            Clan NoClan=Tournament.getTournament().getClans().get(0);
            for (int i = 0; i < nbRows; i++) {
                Clan clan = (Clan) ranking.getSortedDatas().get(i).getObject();
                if (clan==NoClan)
                {
                    continue;
                }
                
                
                
                
                
                Color bkg = new Color(255, 255, 255);
                if (clanIndex % 2 !=0) {
                    bkg = new Color(220, 220, 220);
                }

                // Set font
                Font currentFont = f;
                if (clanIndex == 0) {
                    currentFont = f1;
                }
                if ((clanIndex == 2) || (clanIndex == 1)) {
                    currentFont = f0;
                }

                // Number
                JLabel jlbNum = new JLabel("" + (clanIndex +1));
                jlbNum.setFont(currentFont);
                jlbNum.setOpaque(true);
                jlbNum.setBackground(bkg);
                jpnContent.add(jlbNum, getGridbBagConstraints(0, clanIndex + 1, 1, 1));

                index = 1;

                
                JLabel jlbCoach = getLabelForObject(clan, computed_height, computed_width, currentFont, bkg);
                jpnContent.add(jlbCoach, getGridbBagConstraints(index, clanIndex + 1, 1, 5));
                index += 5;

                String members = "";
                for (int j = 0; j < Tournament.getTournament().getCoachs().size(); j++) {
                    Coach c = Tournament.getTournament().getCoachs().get(j);
                    if (c.mClan == clan) {
                        if (!members.equals(""))
                        {
                            members+=" / ";
                        }
                        members += c.mName;
                    }
                }
                JLabel jlbMembers = new JLabel(members);
                jlbMembers.setFont(currentFont);
                jlbMembers.setOpaque(true);
                jlbMembers.setBackground(bkg);
                jpnContent.add(jlbMembers, getGridbBagConstraints(index, clanIndex + 1, 1, 5));
                index += 5;


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
                        jpnContent.add(jlbRank, getGridbBagConstraints(index, clanIndex + 1, 1, 2));
                        index += 2;
                    }
                    
                }
                    clanIndex++;
            }
        } catch (FontFormatException ex) {
            Logger.getLogger(JFullScreenClanRank.class.getName()).log(Level.SEVERE, null, ex);
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
    private static final Logger LOG = Logger.getLogger(JFullScreenClanRank.class.getName());
}
