/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.utils.web;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import org.apache.commons.lang3.StringEscapeUtils;
import tourma.data.Category;
import tourma.data.Clan;
import tourma.data.Coach;
import tourma.data.CoachMatch;
import tourma.data.Competitor;
import tourma.data.Criteria;
import tourma.data.ETeamPairing;
import tourma.data.Group;
import tourma.data.Match;
import tourma.data.ObjectAnnexRanking;
import tourma.data.Parameters;
import tourma.data.Pool;
import tourma.data.Ranking;
import tourma.data.Round;
import tourma.data.Team;
import tourma.data.TeamMatch;
import tourma.data.Tournament;
import tourma.data.Value;
import tourma.languages.Translate;
import static tourma.languages.Translate.CS_Team;
import tourma.tableModel.MjtAnnexRankClan;
import tourma.tableModel.MjtAnnexRankIndiv;
import tourma.tableModel.MjtAnnexRankTeam;
import tourma.tableModel.MjtRanking;
import tourma.tableModel.MjtRankingClan;
import tourma.tableModel.MjtRankingIndiv;
import tourma.tableModel.MjtRankingTeam;
import tourma.utility.StringConstants;

/**
 *
 * @author WFMJ7631
 */
public class WebRound {

    private static final Logger LOG = Logger.getLogger(Tournament.class.getName());

    public static String getMenu(int nb, boolean withExtension) {
        StringBuilder menu = new StringBuilder();
        String ext = "";
        String pre="/";
        if (withExtension) {
            ext = ".html";
            pre="";
        }
        

        Round r = Tournament.getTournament().getRound(nb - 1);

        menu.append("<ul>");
        menu.append("<li><a href='"+pre+"round" + (nb) + "_matchs"+ext+"'><span>" + StringEscapeUtils.escapeHtml4(Translate.translate(Ranking.CS_Matchs)) + "</span></a></li>");
        menu.append("<li><a href='"+pre+"round" + (nb) + "_indiv"+ext+"'><span>" + StringEscapeUtils.escapeHtml4(Translate.translate(Ranking.CS_Individual)) + "</span></a></li>");
        for (int i = 0; i < Tournament.getTournament().getParams().getCriteriaCount(); i++) {
            Criteria c = Tournament.getTournament().getParams().getCriteria(i);
            menu.append("<li><a href='"+pre+"round" + (nb) + "_indiv_crit"+i+ext + "'><span>" + StringEscapeUtils.escapeHtml4(Translate.translate(Ranking.CS_Individual) + " " + c.getName()) + "</span></a></li>");
        }

        if (Tournament.getTournament().getParams().isTeamTournament()) {
            if (Tournament.getTournament().getParams().getTeamPairing() == ETeamPairing.TEAM_PAIRING) {
                menu.append("<li><a href='"+pre+"round" + (nb) + "_team_matchs"+ext+"'><span>" + StringEscapeUtils.escapeHtml4(Translate.translate(Translate.CS_Team) + " " + Translate.translate(Ranking.CS_Matchs)) + "</span></a></li>");
            }

            menu.append("<li><a href='"+pre+"round" + (nb) + "_teams"+ext+"'><span>" + StringEscapeUtils.escapeHtml4(Translate.translate(Ranking.CS_Team)) + "</span></a></li>");
            for (int i = 0; i < Tournament.getTournament().getParams().getCriteriaCount(); i++) {
                Criteria c = Tournament.getTournament().getParams().getCriteria(i);
                menu.append("<li><a href='"+pre+"round" + (nb) + "_team_crit" + i +ext+"'><span>" + StringEscapeUtils.escapeHtml4(Translate.translate(Ranking.CS_Team) + " " + c.getName()) + "</span></a></li>");
            }
        }

        if (Tournament.getTournament().getClansCount() > 1) {
            menu.append("<li><a href='"+pre+"round" + (nb) + "_clans"+ext+"'><span>" + StringEscapeUtils.escapeHtml4(Translate.translate(Ranking.CS_Clan)) + "</span></a></li>");
            for (int i = 0; i < Tournament.getTournament().getParams().getCriteriaCount(); i++) {
                Criteria c = Tournament.getTournament().getParams().getCriteria(i);
                menu.append("<li><a href='"+pre+"round" + (nb) + "_clan_crit"+i+ext+ "'><span>" + StringEscapeUtils.escapeHtml4(Translate.translate(Ranking.CS_Clan) + " " + c.getName()) + "</span></a></li>");
            }
        }

        if (Tournament.getTournament().getCategoriesCount() > 1) {
            for (int i = 0; i < Tournament.getTournament().getCategoriesCount(); i++) {

                Category c = Tournament.getTournament().getCategory(i);
                menu.append("<li><a href='"+pre+"round" + (nb) + "_cat" + i + ext+"'><span>" + StringEscapeUtils.escapeHtml4(Translate.translate(StringConstants.CS_CATEGORY) + " " + c.getName()) + "</span></a></li>");
            }
        }

        if (Tournament.getTournament().getGroupsCount() > 1) {
            for (int i = 0; i < Tournament.getTournament().getGroupsCount(); i++) {

                Group c = Tournament.getTournament().getGroup(i);
                menu.append("<li><a href='"+pre+"round" + (nb) + "_group" + i +ext+"'><span>" + StringEscapeUtils.escapeHtml4(Translate.translate(Ranking.CS_Group) + " " + c.getName()) + "</span></a></li>");
            }
        }

        if (Tournament.getTournament().getPoolCount() > 0) {

            for (int i = 0; i < Tournament.getTournament().getPoolCount(); i++) {

                Pool p = Tournament.getTournament().getPool(i);
                menu.append("<li><a href='"+pre+"round" + (nb) + "_pool" + i +ext+"'><span>" + StringEscapeUtils.escapeHtml4(Translate.translate(Translate.CS_Pool) + " " + (i + 1)) + "</span></a></li>");
            }
        }
        menu.append("</ul>");
        return menu.toString();
    }

    public static String getHTML(int nb, String command) {
        StringBuilder round = new StringBuilder();

        Round r = Tournament.getTournament().getRound(nb - 1);

        if (command.equals("matchs")) {
            round.append(createIndividualMatch(r));
        }
        if (command.equals("indiv")) {
            round.append(createIndividualRanking(r));
        }
        if (command.startsWith("indiv_crit")) {
            String tmp = command.replace("indiv_crit", "");
            int nb_crit = Integer.parseInt(tmp);
            Criteria crit = Tournament.getTournament().getParams().getCriteria(nb_crit);
            round.append(createIndividualCriteria(r, crit));
        }
        if (command.equals("team_matchs")) {
            round.append(createTeamMatchs(r));
        }
        if (command.equals("teams")) {
            round.append(createTeamRanking(r));
        }
        if (command.startsWith("team_crit")) {
            String tmp = command.replace("team_crit", "");
            int nb_crit = Integer.parseInt(tmp);
            Criteria crit = Tournament.getTournament().getParams().getCriteria(nb_crit);
            String tmp_str=createTeamCriteria(r, crit);
            round.append(tmp_str);
        }

        if (command.equals("clans")) {
            round.append(createClanRanking(r));
        }
        if (command.startsWith("clan_crit")) {
            String tmp = command.replace("clan_crit", "");
            int nb_crit = Integer.parseInt(tmp);
            Criteria crit = Tournament.getTournament().getParams().getCriteria(nb_crit);
            round.append(createClanCriteria(r, crit));
        }

        if (command.startsWith("cat")) {
            String tmp = command.replace("cat", "");
            int nb_cat = Integer.parseInt(tmp);
            Category cat = Tournament.getTournament().getCategory(nb_cat);
            round.append(createCategoryRanking(r, cat));
        }

        if (command.startsWith("group")) {
            String tmp = command.replace("group", "");
            int nb_grp = Integer.parseInt(tmp);
            Group group = Tournament.getTournament().getGroup(nb_grp);
            round.append(createGroupRanking(r, group));
        }

        if (command.startsWith("pool")) {
            String tmp = command.replace("pool", "");
            int nb_pool = Integer.parseInt(tmp);
            Pool p = Tournament.getTournament().getPool(nb_pool);
            round.append(createPoolRanking(r, p));
        }
        return round.toString();
    }

    /* public static String getHTML(int nb) {
        Round r = Tournament.getTournament().getRound(nb - 1);
        StringBuilder round = new StringBuilder();
        StringBuilder round2 = new StringBuilder();

        LOG.log(Level.FINE, "Create Round");

        round2.append("    </ul>\n"
                + " \n"
                + "    <div class=\"tab-content\">\n");
        round.append("<div class=\"tabs\">\n"
                + "    <ul class=\"tab-links\">\n"
                + "        <li class=\"active\"><a href=\"#tab1\">"
                + StringEscapeUtils.escapeHtml4(Translate.translate(Ranking.CS_Matchs))
                + "</a></li>\n"
                + "        <li><a href=\"#tab2\">"
                + StringEscapeUtils.escapeHtml4(Translate.translate(Ranking.CS_Individual))
                + "</a></li>\n");

        round2.append("        <div id=\"tab1\" class=\"tab active\">\n"
                + "            <p>" + Translate.translate(Ranking.CS_Matchs) + "</p>\n"
                + createIndividualMatch(r)
                + "        </div>\n"
                + " \n"
                + "        <div id=\"tab2\" class=\"tab\">\n"
                + "            <p>" + StringEscapeUtils.escapeHtml4(Translate.translate(Ranking.CS_Individual)) + "</p>\n"
                + "            <p>" + createIndividualRanking(r) + "</p>\n"
                + "        </div>\n"
                + " \n");

        LOG.log(Level.FINE, "Basics created");

        int index = 3;
        LOG.log(Level.INFO, "Loop on criterias Indiv");
        for (int i = 0; i < Tournament.getTournament().getParams().getCriteriaCount(); i++) {
            Criteria c = Tournament.getTournament().getParams().getCriteria(i);
            round.append("<li><a href=\"#tab" + index + "\">"
                    + StringEscapeUtils.escapeHtml4(c.getName())
                    + "</a></li>\n");
            round2.append("        <div id=\"tab" + index + "\" class=\"tab\">\n"
                    + "            <p>" + StringEscapeUtils.escapeHtml4(c.getName()) + "</p>\n"
                    + createIndividualCriteria(r, c)
                    + "        </div>\n"
                    + " \n");
            index++;
        }
        LOG.log(Level.INFO, "If team Tournament");
        if (Tournament.getTournament().getParams().isTeamTournament()) {
            if (Tournament.getTournament().getParams().getTeamPairing() == ETeamPairing.TEAM_PAIRING) {
                LOG.log(Level.INFO, "Team Matchs");
                round.append("<li><a href=\"#tab" + index + "\">"
                        + StringEscapeUtils.escapeHtml4(Translate.translate(Translate.CS_Team) + " " + Translate.translate(Ranking.CS_Matchs))
                        + "</a></li>\n");
                round2.append("        <div id=\"tab" + index + "\" class=\"tab\">\n"
                        + "            <p>" + StringEscapeUtils.escapeHtml4(Translate.translate(Translate.CS_Team) + " " + Translate.translate(Ranking.CS_Matchs)) + "</p>\n"
                        + createTeamMatchs(r)
                        + "        </div>\n"
                        + " \n");
                index++;
            }
            LOG.log(Level.FINE, "Team Rank");
            round.append("<li><a href=\"#tab" + index + "\">"
                    + StringEscapeUtils.escapeHtml4(Translate.translate(Ranking.CS_Team))
                    + "</a></li>\n");
            round2.append("        <div id=\"tab" + index + "\" class=\"tab\">\n"
                    + "            <p>" + StringEscapeUtils.escapeHtml4(Translate.translate(Translate.CS_Team)) + "</p>\n"
                    + createTeamRanking(r)
                    + "        </div>\n"
                    + " \n");
            index++;
            LOG.log(Level.FINE, "Loop on criterias team");
            for (int i = 0; i < Tournament.getTournament().getParams().getCriteriaCount(); i++) {
                Criteria c = Tournament.getTournament().getParams().getCriteria(i);
                LOG.log(Level.FINE, "Criteria " + i + " : " + c.getName());
                round.append("<li><a href=\"#tab" + index + "\">"
                        + StringEscapeUtils.escapeHtml4(c.getName())
                        + "</a></li>\n");
                round2.append("        <div id=\"tab" + index + "\" class=\"tab\">\n"
                        + "            <p>" + StringEscapeUtils.escapeHtml4(Translate.translate(Translate.CS_Team)) + "</p>\n"
                        + createTeamCriteria(r, c)
                        + "        </div>\n"
                        + " \n");
                index++;
            }

        }
        LOG.log(Level.FINE, "Clans");
        if (Tournament.getTournament().getClansCount() > 1) {
            round.append("<li><a href=\"#tab" + index + "\">"
                    + StringEscapeUtils.escapeHtml4(Translate.translate(Ranking.CS_Clan))
                    + "</a></li>\n");
            round2.append("        <div id=\"tab" + index + "\" class=\"tab\">\n"
                    + "            <p>" + StringEscapeUtils.escapeHtml4(Translate.translate(Translate.CS_Clan)) + "</p>\n"
                    + createClanRanking(r)
                    + "        </div>\n"
                    + " \n");
            index++;
            for (int i = 0; i < Tournament.getTournament().getParams().getCriteriaCount(); i++) {

                Criteria c = Tournament.getTournament().getParams().getCriteria(i);
                round.append("<li><a href=\"#tab" + index + "\">"
                        + StringEscapeUtils.escapeHtml4(c.getName())
                        + "</a></li>\n");
                round2.append("        <div id=\"tab" + index + "\" class=\"tab\">\n"
                        + "            <p>" + StringEscapeUtils.escapeHtml4(Translate.translate(Translate.CS_Clan)) + "</p>\n"
                        + createClanCriteria(r, c)
                        + "        </div>\n"
                        + " \n");
                index++;
            }
        }

        LOG.log(Level.FINE, "Categories");
        if (Tournament.getTournament().getCategoriesCount() > 0) {
            for (int i = 0; i < Tournament.getTournament().getCategoriesCount(); i++) {

                Category c = Tournament.getTournament().getCategory(i);
                round.append("<li><a href=\"#tab" + index + "\">"
                        + StringEscapeUtils.escapeHtml4(Translate.translate(StringConstants.CS_CATEGORY) + " " + c.getName())
                        + "</a></li>\n");
                round2.append("        <div id=\"tab" + index + "\" class=\"tab\">\n"
                        + "            <p>" + StringEscapeUtils.escapeHtml4(Translate.translate(StringConstants.CS_CATEGORY) + " " + c.getName()) + "</p>\n"
                        + createCategoryRanking(r, c)
                        + "        </div>\n"
                        + " \n");
                index++;
            }
        }

        LOG.log(Level.FINE, "Groups");
        if (Tournament.getTournament().getGroupsCount() > 1) {

            for (int i = 0; i < Tournament.getTournament().getGroupsCount(); i++) {

                Group c = Tournament.getTournament().getGroup(i);
                round.append("<li><a href=\"#tab" + index + "\">"
                        + StringEscapeUtils.escapeHtml4(Translate.translate(Ranking.CS_Group) + " " + c.getName())
                        + "</a></li>\n");
                round2.append("        <div id=\"tab" + index + "\" class=\"tab\">\n"
                        + "            <p>" + StringEscapeUtils.escapeHtml4(Translate.translate(Ranking.CS_Group) + " " + c.getName()) + "</p>\n"
                        + createGroupRanking(r, c)
                        + "        </div>\n"
                        + " \n");
                index++;
            }
        }

        LOG.log(Level.FINE, "Pool");
        if (Tournament.getTournament().getPoolCount() > 0) {

            for (int i = 0; i < Tournament.getTournament().getPoolCount(); i++) {

                Pool p = Tournament.getTournament().getPool(i);
                round.append("<li><a href=\"#tab" + index + "\">"
                        + StringEscapeUtils.escapeHtml4(Translate.translate(Translate.CS_Pool) + " " + p.getName())
                        + "</a></li>\n");
                round2.append("        <div id=\"tab" + index + "\" class=\"tab\">\n"
                        + "            <p>" + StringEscapeUtils.escapeHtml4(Translate.translate(Translate.CS_Pool) + " " + p.getName()) + "</p>\n"
                        + createPoolRanking(r, p)
                        + "        </div>\n"
                        + " \n");
                index++;

            }
        }

        LOG.log(Level.FINE, "End");
        round2.append("    </div>\n"
                + "</div>");

        round.append(round2.toString());

        return round.toString();

    }*/
    protected static String createIndividualMatch(Round r) {
        StringBuilder s = new StringBuilder();
        s.append("<br>");
        s.append("<div class=\"section\"><CENTER>");

        s.append("<table\n"
                + "            style=\"border-width:0px; text-align: left; margin-left: auto; margin-right: auto;text-align:center;\"\n"
                + "            border=\"1\" cellpadding=\"0\" cellspacing=\"0\">");
        s.append("<tbody>");
        s.append("<tr>");
        s.append("<td class=\"tab_titre\">#</td>\n");
        s.append("<td class=\"tab_titre\">" + StringEscapeUtils.escapeHtml4(Translate.translate(Translate.CS_Coach)) + " 1</td>\n");
        s.append("<td colspan=\"2\" class=\"tab_titre\">" + StringEscapeUtils.escapeHtml4(Translate.translate(Translate.CS_Score)) + "</td>\n");
        s.append("<td class=\"tab_titre\">" + StringEscapeUtils.escapeHtml4(Translate.translate(Translate.CS_Coach)) + " 2</td>\n");
        for (int i = 1; i < Tournament.getTournament().getParams().getCriteriaCount(); i++) {
            Criteria c = Tournament.getTournament().getParams().getCriteria(i);
            s.append("<td colspan=\"2\" class=\"tab_titre\">" + StringEscapeUtils.escapeHtml4(c.getName()) + "</td>\n");
        }
        s.append("</tr>");

        Criteria td = Tournament.getTournament().getParams().getCriteria(0);
        for (int j = 0; j < r.getCoachMatchs().size(); j++) {
            CoachMatch cm = r.getCoachMatchs().get(j);
            Value val = cm.getValue(td);
            String style1 = "";
            String style2 = "";

            if (val.getValue1() == val.getValue2()) {
                style1 = "draw";
                style2 = "draw";
            } else if (val.getValue1() < val.getValue2()) {
                style1 = "looser";
                style2 = "winner";
            } else if (val.getValue1() > val.getValue2()) {
                style2 = "looser";
                style1 = "winner";
            } else {
                style1 = "draw";
                style2 = "draw";
            }
            s.append("<tr>");
            String bg = "background-color:#eeeeee;";
            if (j % 2 == 0) {
                bg = "background-color:#ffffff;";
            }
            Coach c1 = (Coach) cm.getCompetitor1();
            Coach c2 = (Coach) cm.getCompetitor2();

            String img1 = "";
            String img2 = "";

            if (Tournament.getTournament().getParams().isUseImage()) {
                ImageIcon pic1 = null;
                ImageIcon pic2 = null;
                if (Tournament.getTournament().getParams().isTeamTournament()) {
                    if (c1.getTeamMates() != null) {
                        pic1 = c1.getTeamMates().getPicture();
                    }
                    if (c2.getTeamMates() != null) {
                        pic2 = c2.getTeamMates().getPicture();
                    }
                }
                if ((pic1 == null) && (c1.getClan() != null)) {
                    pic1 = c1.getClan().getPicture();
                }

                if ((pic2 == null) && (c2.getClan() != null)) {
                    pic2 = c2.getClan().getPicture();
                }
                if (pic1 == null) {
                    pic1 = c1.getPicture();
                }
                if (pic2 == null) {
                    pic2 = c2.getPicture();
                }

                if (pic1 != null) {
                    img1 = WebPicture.getPictureAsHTML(pic1, 16, 16);
                }
                if (pic2 != null) {
                    img2 = WebPicture.getPictureAsHTML(pic2, 16, 16);
                }
            }
            String val1 = "";
            if (val.getValue1() >= 0) {
                val1 = Integer.toString(val.getValue1());
            }
            String val2 = "";
            if (val.getValue2() >= 0) {
                val2 = Integer.toString(val.getValue2());
            }
            s.append(" <td class=\"tab_pos\" style=\"font-size:16px;" + bg + "\">" + (j + 1) + "</td>\n");
            s.append(" <td class=\"" + style1 + "\" style=\"font-size:16px;text-align:right;" + bg + "\">" + img1 + StringEscapeUtils.escapeHtml4(c1.getDecoratedName()) + " </td>\n");
            s.append("<td class=\"" + style1 + "\" style=\"font-size:14px;" + bg + "\">" + val1 + "</td>\n");
            s.append("<td class=\"" + style2 + "\" style=\"font-size:14px;" + bg + "\">" + val2 + "</td>\n");
            s.append("<td class=\"" + style2 + "\" style=\"font-size:16px;text-align:left;" + bg + "\">" + StringEscapeUtils.escapeHtml4(c2.getDecoratedName()) + img2 + "</td>\n");
            for (int k = 1; k < Tournament.getTournament().getParams().getCriteriaCount(); k++) {
                Criteria crit = Tournament.getTournament().getParams().getCriteria(k);
                Value v = cm.getValue(crit);
                s.append("<td class=\"" + style1 + "\" style=\"font-size:14px;" + bg + "\">" + v.getValue1() + "</td>\n");
                s.append("<td class=\"" + style2 + "\" style=\"font-size:14px;" + bg + "\">" + v.getValue2() + "</td>\n");
            }
            s.append("</tr>");
        }
        s.append("</tbody>\n"
                + "        </table></CENTER></div>");
        return s.toString();
    }

    protected static String createIndividualRanking(Round r, ArrayList<Coach> coachs, String rankName) {
        StringBuilder s = new StringBuilder();
        MjtRankingIndiv ranking = new MjtRankingIndiv(Tournament.getTournament().getRoundIndex(r),
                Tournament.getTournament().getParams().getRankingIndiv1(),
                Tournament.getTournament().getParams().getRankingIndiv2(),
                Tournament.getTournament().getParams().getRankingIndiv3(),
                Tournament.getTournament().getParams().getRankingIndiv4(),
                Tournament.getTournament().getParams().getRankingIndiv5(),
                coachs,
                Tournament.getTournament().getParams().isTeamTournament(),
                false,
                false,false);

        s.append("<div class=\"section\"><table\n"
                + "             style = \"border-width:0px; margin-left: auto; margin-right: auto;text-align:center;\"\n"
                + "        border = \"1\" cellpadding = \"0\" cellspacing = \"0\"\n"
                + "                > <tbody>\n"
                + "                <tr>");

        int nbCol = 4;
        if (Tournament.getTournament().getParams().isTeamTournament()) {
            nbCol++;
        }
        if (Tournament.getTournament().getClansCount() > 1) {
            nbCol++;
        }
        nbCol += Tournament.getTournament().getParams().getIndivRankingNumber();
        s.append("<tr><td class=\"tab_titre\" colspan=\"" + nbCol + "\">" + StringEscapeUtils.escapeHtml4(rankName) + "</td></tr>");
        s.append("<td class=\"tab_titre\">" + StringEscapeUtils.escapeHtml4("#") + "</ td>\n");

        if (Tournament.getTournament().getParams().isTeamTournament()) {
            s.append("<td class=\"tab_titre\">" + StringEscapeUtils.escapeHtml4(Translate.translate(CS_Team)) + "</ td>\n");
        }
        if (Tournament.getTournament().getClansCount() > 1) {
            s.append("<td class=\"tab_titre\">" + StringEscapeUtils.escapeHtml4(Translate.translate(Translate.CS_Clan)) + "</ td>\n");
        }

        s.append("<td class=\"tab_titre\">" + StringEscapeUtils.escapeHtml4(Translate.translate(Translate.CS_Coach)) + "</ td>\n");
        s.append("<td class=\"tab_titre\">" + StringEscapeUtils.escapeHtml4(Translate.translate(Translate.CS_Roster)) + "</ td>\n");
        s.append("<td class=\"tab_titre\">" + StringEscapeUtils.escapeHtml4(Translate.translate(Translate.CS_RosterName)) + "</ td>\n");

        for (int j = 0; j < Tournament.getTournament().getParams().getIndivRankingNumber(); j++) {
            int rankingType = Tournament.getTournament().getParams().getIndivRankingType(j);
            String name = MjtRanking.getRankingString(rankingType);
            if (rankingType == 0) {
                break;
            } else {
                s.append("<td class=\"tab_titre\">" + StringEscapeUtils.escapeHtml4(name) + "</ td>\n");
            }
        }

        s.append("</tr>");
        for (int row = 0; row < ranking.getRowCount(); row++) {
            String suffix = "";
            s.append("<tr>");
            if (row == 0) {
                suffix = "_1";
            }
            if (row == ranking.getRowCount() - 1) {
                suffix = "_last";
            }
            String bg = "background-color:#ffffff;";
            if (row % 2 == 1) {
                bg = "background-color:#eeeeee;";
            }

            s.append("<td class=\"tab_result" + suffix + "\" style=\"" + bg + "\">" + (row + 1) + "</td>\n");
            Coach coach = (Coach) ranking.getSortedObject(row).getObject();

            if (Tournament.getTournament().getParams().isTeamTournament()) {
                Team t = coach.getTeamMates();
                s.append("<td class=\"tab_result" + suffix + "\" style=\"" + bg + "\">"
                        + WebPicture.getPictureAsHTML(t.getPicture(), 20, 20)
                        + StringEscapeUtils.escapeHtml4(t.getName())
                        + "</td>\n");
            }

            if (Tournament.getTournament().getClansCount() > 1) {
                Clan c = coach.getClan();
                s.append("<td class=\"tab_result" + suffix + "\" style=\"" + bg + "\">"
                        + WebPicture.getPictureAsHTML(c.getPicture(), 20, 20)
                        + StringEscapeUtils.escapeHtml4(c.getName())
                        + "</td>\n");
            }

            s.append("<td class=\"tab_result" + suffix + "\" style=\"" + bg + "\">"
                    + WebPicture.getPictureAsHTML(coach.getPicture(), 20, 20)
                    + StringEscapeUtils.escapeHtml4(coach.getName())
                    + "</td>\n");

            s.append("<td class=\"tab_result" + suffix + "\" style=\"" + bg + "\">"
                    + StringEscapeUtils.escapeHtml4(coach.getRoster().getName())
                    + "</td>\n"
            );

            s.append("<td class=\"tab_result" + suffix + "\" style=\"" + bg + "\">"
                    + StringEscapeUtils.escapeHtml4(coach.getTeam())
                    + "</td>\n"
            );

            for (int j = 0; j < Tournament.getTournament().getParams().getIndivRankingNumber(); j++) {
                int rankingType = Tournament.getTournament().getParams().getIndivRankingType(j);
                int value;
                value = ranking.getSortedValue(row, j + 1);

                String name = Integer.toString(value);
                if (rankingType == 0) {
                    break;
                } else {
                    s.append("<td class=\"tab_result" + suffix + "\" style=\"" + bg + "\">"
                            + name
                            + "</td>\n"
                    );
                }
            }

            s.append("</tr>");
        }
        s.append("</table></div>");
        return s.toString();
    }

    protected static String createIndividualRanking(Round r) {
        return createIndividualRanking(r, Tournament.getTournament().getActiveCoaches(), Translate.translate(Ranking.CS_Individual));
    }

    protected static String createIndividualCriteria(Round r, Criteria crit) {
        StringBuilder s = new StringBuilder();
        for (int subtype = 0; subtype < 3; subtype++) {
            MjtAnnexRankIndiv ranking = new MjtAnnexRankIndiv(Tournament.getTournament().getRoundIndex(r),
                    crit,
                    subtype,
                    Tournament.getTournament().getActiveCoaches(),
                    true,
                    Tournament.getTournament().getParams().getRankingIndiv1(),
                    Tournament.getTournament().getParams().getRankingIndiv2(),
                    Tournament.getTournament().getParams().getRankingIndiv3(),
                    Tournament.getTournament().getParams().getRankingIndiv4(),
                    Tournament.getTournament().getParams().getRankingIndiv5(),
                    Tournament.getTournament().getParams().isTeamTournament(),
                    false);

            String subTypeName = "";
            switch (subtype) {
                case 0:
                    subTypeName = "+";
                    break;
                case 1:
                    subTypeName = "-";
                    break;
                case 2:
                    subTypeName = "+/-";
                    break;
            }
            s.append("<div class=\"section\">");
            String rank_name = crit.getName() + " " + subTypeName;

            //s.append("<div class=\"title\">" + StringEscapeUtils.escapeHtml4(rank_name) + "</div>");
            s.append("<table\n"
                    + "             style = \"border-width:0px; margin-left: auto; margin-right: auto;text-align:center;\"\n"
                    + "        border = \"1\" cellpadding = \"0\" cellspacing = \"0\"\n"
                    + "                > <tbody>\n"
                    + "                <t >");

            int nbCol = Tournament.getTournament().getClansCount() > 1 ? 6 : 5;
            if (Tournament.getTournament().getParams().isTeamTournament()) {
                nbCol++;
            }
            s.append("<tr><td class=\"tab_titre\" colspan=\"" + nbCol + "\">" + StringEscapeUtils.escapeHtml4(rank_name) + "</td><tr>");

            s.append("<td class=\"tab_titre\">" + StringEscapeUtils.escapeHtml4("#") + "</ td>\n");

            if (Tournament.getTournament().getParams().isTeamTournament()) {
                s.append("<td class=\"tab_titre\">" + StringEscapeUtils.escapeHtml4(Translate.translate(CS_Team)) + "</ td>\n");
            }
            if (Tournament.getTournament().getClansCount() > 1) {
                s.append("<td class=\"tab_titre\">" + StringEscapeUtils.escapeHtml4(Translate.translate(Translate.CS_Clan)) + "</ td>\n");
            }

            s.append("<td class=\"tab_titre\">" + StringEscapeUtils.escapeHtml4(Translate.translate(Translate.CS_Coach)) + "</ td>\n");
            s.append("<td class=\"tab_titre\">" + StringEscapeUtils.escapeHtml4(Translate.translate(Translate.CS_Roster)) + "</ td>\n");
            s.append("<td class=\"tab_titre\">" + StringEscapeUtils.escapeHtml4(Translate.translate(Translate.CS_RosterName)) + "</ td>\n");

            s.append("<td class=\"tab_titre\">" + StringEscapeUtils.escapeHtml4(rank_name) + "</ td>\n");

            s.append("</tr>");
            for (int row = 0; row < ranking.getRowCount(); row++) {
                String suffix = "";
                s.append("<tr>");
                if (row == 0) {
                    suffix = "_1";
                }
                if (row == ranking.getRowCount() - 1) {
                    suffix = "_last";
                }
                String bg = "background-color:#ffffff;";
                if (row % 2 == 1) {
                    bg = "background-color:#eeeeee;";
                }

                s.append("<td class=\"tab_result" + suffix + "\" style=\"" + bg + "\">" + (row + 1) + "</td>\n");
                Coach coach = (Coach) ranking.getSortedObject(row).getObject();

                if (Tournament.getTournament().getParams().isTeamTournament()) {
                    Team t = coach.getTeamMates();
                    s.append("<td class=\"tab_result" + suffix + "\" style=\"" + bg + "\">"
                            + WebPicture.getPictureAsHTML(t.getPicture(), 20, 20)
                            + StringEscapeUtils.escapeHtml4(t.getName())
                            + "</td>\n");
                }

                if (Tournament.getTournament().getClansCount() > 1) {
                    Clan c = coach.getClan();
                    s.append("<td class=\"tab_result" + suffix + "\" style=\"" + bg + "\">"
                            + WebPicture.getPictureAsHTML(c.getPicture(), 20, 20)
                            + StringEscapeUtils.escapeHtml4(c.getName())
                            + "</td>\n");
                }

                s.append("<td class=\"tab_result" + suffix + "\" style=\"" + bg + "\">"
                        + WebPicture.getPictureAsHTML(coach.getPicture(), 20, 20)
                        + StringEscapeUtils.escapeHtml4(coach.getName())
                        + "</td>\n");

                s.append("<td class=\"tab_result" + suffix + "\" style=\"" + bg + "\">"
                        + StringEscapeUtils.escapeHtml4(coach.getRoster().getName())
                        + "</td>\n"
                );

                s.append("<td class=\"tab_result" + suffix + "\" style=\"" + bg + "\">"
                        + StringEscapeUtils.escapeHtml4(coach.getTeam())
                        + "</td>\n"
                );

                int value;

                value = ((ObjectAnnexRanking) ranking.getSortedObject(row)).getValue();

                String name = Integer.toString(value);
                s.append("<td class=\"tab_result" + suffix + "\" style=\"" + bg + "\">"
                        + name
                        + "</td>\n"
                );
                s.append("</tr>");
            }
            s.append("</table></div>");
        }
        return s.toString();
    }

    protected static String createTeamMatchs(Round r) {
        StringBuilder s = new StringBuilder();
        s.append("<STYLE type=\"text/css\">\n"
                + "            td.tab_titre {\n"
                + "                padding: 7px 10px;\n"
                + "                border-style: solid;\n"
                + "                border-width: 1px 1px 0px 0px;\n"
                + "                border-color: #d9d9d9;\n"
                + "                background-color: #444444;\n"
                + "                color: #cccccc;\n"
                + "                text-align:center;\n"
                + "                font-size:14px;\n"
                + "            }\n"
                + "\n"
                + "            td.tab_pos {\n"
                + "                padding: 7px 10px;\n"
                + "                border-style: solid;\n"
                + "                border-width: 1px 1px 0px 0px;\n"
                + "                border-color: #444444;\n"
                + "                background-color: #cccccc;\n"
                + "                color: #444444;\n"
                + "                text-align:center;\n"
                + "            }\n"
                + "\n"
                + "            td.winner {\n"
                + "                padding: 7px 10px;\n"
                + "                border-style: solid;\n"
                + "                border-width: 1px 1px 0px 0px ;\n"
                + "                border-color: rgb(68,68,68) rgb(68,68,68);\n"
                + "                background-color: #eeeeee;\n"
                + "                color: rgb(0,0,88);\n"
                + "                font-weight: 700;\n"
                + "                text-align:center;\n"
                + "            }\n"
                + "\n"
                + "            td.looser {\n"
                + "                padding: 7px 10px;\n"
                + "                border-style: solid;\n"
                + "                border-width: 1px 1px 0px 0px ;\n"
                + "                border-color:  #444444;\n"
                + "                background-color: #eeeeee;\n"
                + "                color: #880000;\n"
                + "                text-align:center;\n"
                + "            }\n"
                + "\n"
                + "            td.draw {\n"
                + "                padding: 7px 10px;\n"
                + "                border-style: solid;\n"
                + "                border-width: 1px 1px 0px 0px ;\n"
                + "                border-color:  #444444;\n"
                + "                background-color: #eeeeee;\n"
                + "                color: #444444;\n"
                + "                font-style: italic;\n"
                + "                text-align:center;\n"
                + "            }\n"
                + "        </style>");
        s.append("<br>");
        s.append("<div class=\"section\"><table\n"
                + "            style=\"border-width:0px; text-align: left; margin-left: auto; margin-right: auto;text-align:center;\"\n"
                + "            border=\"1\" cellpadding=\"0\" cellspacing=\"0\">");
        s.append("<tbody>");
        s.append("<tr>");
        s.append("<td class=\"tab_titre\">#</td>\n");
        s.append("<td class=\"tab_titre\">" + StringEscapeUtils.escapeHtml4(Translate.translate(Translate.CS_Coach)) + " 1</td>\n");
        s.append("<td class=\"tab_titre\">" + StringEscapeUtils.escapeHtml4(Translate.translate(Translate.CS_ACCR_Victory1)) + "</td>\n");
        s.append("<td class=\"tab_titre\">" + StringEscapeUtils.escapeHtml4(Translate.translate(Translate.CS_ACCR_Drawn)) + "</td>\n");
        s.append("<td  class=\"tab_titre\">" + StringEscapeUtils.escapeHtml4(Translate.translate(Translate.CS_ACCR_Victory2)) + "</td>\n");
        s.append("<td class=\"tab_titre\">" + StringEscapeUtils.escapeHtml4(Translate.translate(Translate.CS_Coach)) + " 2</td>\n");
        for (int i = 0; i < Tournament.getTournament().getParams().getCriteriaCount(); i++) {
            Criteria c = Tournament.getTournament().getParams().getCriteria(i);
            s.append("<td colspan=\"2\" class=\"tab_titre\">" + StringEscapeUtils.escapeHtml4(c.getName()) + "</td>\n");
        }
        s.append("</tr>");

        for (int j = 0; j < r.getMatchsCount(); j++) {
            Match m = r.getMatch(j);
            if (m instanceof TeamMatch) {
                TeamMatch tm = (TeamMatch) m;
                int val1 = tm.getVictories((Team) tm.getCompetitor1());
                int val2 = tm.getVictories((Team) tm.getCompetitor2());
                String style1 = "";
                String style2 = "";

                if (val1 == val2) {
                    style1 = "draw";
                    style2 = "draw";
                } else if (val1 < val2) {
                    style1 = "looser";
                    style2 = "winner";
                } else if (val1 > val2) {
                    style2 = "looser";
                    style1 = "winner";
                } else {
                    style1 = "draw";
                    style2 = "draw";
                }
                s.append("<tr>");
                String bg = "background-color:#eeeeee;";
                if (j % 2 == 0) {
                    bg = "background-color:#ffffff;";
                }
                Team t1 = (Team) tm.getCompetitor1();
                Team t2 = (Team) tm.getCompetitor2();

                String img1 = "";
                String img2 = "";

                if (Tournament.getTournament().getParams().isUseImage()) {
                    ImageIcon pic1 = null;
                    ImageIcon pic2 = null;
                    if ((pic1 == null) && (t1.getClan() != null)) {
                        pic1 = t1.getClan().getPicture();
                    }

                    if ((pic2 == null) && (t2.getClan() != null)) {
                        pic2 = t2.getClan().getPicture();
                    }
                    if (pic1 == null) {
                        pic1 = t1.getPicture();
                    }
                    if (pic2 == null) {
                        pic2 = t2.getPicture();
                    }

                    if (pic1 != null) {
                        img1 = WebPicture.getPictureAsHTML(pic1, 16, 16);
                    }
                    if (pic2 != null) {
                        img2 = WebPicture.getPictureAsHTML(pic2, 16, 16);
                    }
                }
                s.append(" <td class=\"tab_pos\" style=\"font-size:16px;" + bg + "\">" + (j + 1) + "</td>\n");
                s.append(" <td class=\"" + style1 + "\" style=\"font-size:16px;text-align:right;" + bg + "\">" + img1 + StringEscapeUtils.escapeHtml4(t1.getDecoratedName()) + " </td>\n");
                s.append("<td class=\"" + style1 + "\" style=\"font-size:14px;" + bg + "\">" + val1 + "</td>\n");
                s.append("<td class=\"" + style1 + "\" style=\"font-size:14px;" + bg + "\">" + tm.getDraw(t1) + "</td>\n");
                s.append("<td class=\"" + style2 + "\" style=\"font-size:14px;" + bg + "\">" + val2 + "</td>\n");
                s.append("<td class=\"" + style2 + "\" style=\"font-size:16px;text-align:left;" + bg + "\">" + StringEscapeUtils.escapeHtml4(t2.getDecoratedName()) + img2 + "</td>\n");
                for (int k = 0; k < Tournament.getTournament().getParams().getCriteriaCount(); k++) {
                    Criteria crit = Tournament.getTournament().getParams().getCriteria(k);
                    val1 = 0;
                    val2 = 0;
                    for (int l = 0; l < tm.getMatchCount(); l++) {
                        CoachMatch cm = tm.getMatch(l);
                        Value v = cm.getValue(crit);
                        if (v.getValue1() > 0) {
                            val1 += v.getValue1();
                        }
                        if (v.getValue2() > 0) {
                            val2 += v.getValue2();
                        }
                    }
                    s.append("<td class=\"" + style1 + "\" style=\"font-size:14px;" + bg + "\">" + val1 + "</td>\n");
                    s.append("<td class=\"" + style2 + "\" style=\"font-size:14px;" + bg + "\">" + val2 + "</td>\n");
                }
                s.append("</tr>");
            }
        }
        s.append("</tbody>\n"
                + "        </table></div>");
        return s.toString();
    }

    protected static String createTeamRanking(Round r) {
        ArrayList<Team> teams = new ArrayList<>();
        for (int i = 0; i < Tournament.getTournament().getTeamsCount(); i++) {
            teams.add(Tournament.getTournament().getTeam(i));
        }

        return createTeamRanking(r, teams, Translate.translate(Ranking.CS_Team));
    }

    protected static String createTeamRanking(Round r, ArrayList<Team> teams, String rankName) {
        StringBuilder s = new StringBuilder();

        MjtRankingTeam ranking = new MjtRankingTeam(
                Tournament.getTournament().getParams().isTeamVictoryOnly(),
                Tournament.getTournament().getRoundIndex(r),
                teams,
                false);

        s.append("<div class=\"title\">" + StringEscapeUtils.escapeHtml4(rankName) + "</dev>");
        s.append("<div class=\"section\"><table\n"
                + "             style = \"border-width:0px; margin-left: auto; margin-right: auto;text-align:center;\"\n"
                + "        border = \"1\" cellpadding = \"0\" cellspacing = \"0\"\n"
                + "                > <tbody>\n"
                + "                <tr>");

        s.append("<td class=\"tab_titre\">" + StringEscapeUtils.escapeHtml4("#") + "</ td>\n");

        if (Tournament.getTournament().getClansCount() > 1) {
            s.append("<td class=\"tab_titre\">" + StringEscapeUtils.escapeHtml4(Translate.translate(Translate.CS_Clan)) + "</ td>\n");
        }

        s.append("<td class=\"tab_titre\">" + StringEscapeUtils.escapeHtml4(Translate.translate(Translate.CS_Team)) + "</ td>\n");

        boolean bTeamVictoryOnly = Tournament.getTournament().getParams().isTeamVictoryOnly();

        if (bTeamVictoryOnly) {
            for (int j = 0; j < Tournament.getTournament().getParams().getTeamRankingNumber(); j++) {
                int rankingType = Tournament.getTournament().getParams().getTeamRankingType(j);
                String name = MjtRanking.getRankingString(rankingType);
                if (rankingType == 0) {
                    break;
                } else {
                    s.append("<td class=\"tab_titre\">" + StringEscapeUtils.escapeHtml4(name) + "</ td>\n");
                }
            }
        } else {
            for (int j = 0; j < Tournament.getTournament().getParams().getIndivRankingNumber(); j++) {
                int rankingType = Tournament.getTournament().getParams().getIndivRankingType(j);
                String name = MjtRanking.getRankingString(rankingType);
                if (rankingType == 0) {
                    break;
                } else {
                    s.append("<td class=\"tab_titre\">" + StringEscapeUtils.escapeHtml4(name) + "</ td>\n");
                }
            }
        }

        s.append("</tr>");
        for (int row = 0; row < ranking.getRowCount(); row++) {
            String suffix = "";
            s.append("<tr>");
            if (row == 0) {
                suffix = "_1";
            }
            if (row == ranking.getRowCount() - 1) {
                suffix = "_last";
            }
            String bg = "background-color:#ffffff;";
            if (row % 2 == 1) {
                bg = "background-color:#eeeeee;";
            }

            s.append("<td class=\"tab_result" + suffix + "\" style=\"" + bg + "\">" + (row + 1) + "</td>\n");
            Team team = (Team) ranking.getSortedObject(row).getObject();

            if (Tournament.getTournament().getClansCount() > 1) {
                Clan c = team.getClan();
                s.append("<td class=\"tab_result" + suffix + "\" style=\"" + bg + "\">"
                        + WebPicture.getPictureAsHTML(c.getPicture(), 20, 20)
                        + StringEscapeUtils.escapeHtml4(c.getName())
                        + "</td>\n");
            }

            s.append("<td class=\"tab_result" + suffix + "\" style=\"" + bg + "\">"
                    + WebPicture.getPictureAsHTML(team.getPicture(), 20, 20)
                    + StringEscapeUtils.escapeHtml4(team.getName())
                    + "</td>\n");

            int nbRanking = 0;

            if (bTeamVictoryOnly) {
                nbRanking = Tournament.getTournament().getParams().getTeamRankingNumber();
            } else {
                nbRanking = Tournament.getTournament().getParams().getIndivRankingNumber();
            }

            for (int j = 0; j < nbRanking; j++) {
                int rankingType = Parameters.C_RANKING_NONE;
                if (bTeamVictoryOnly) {
                    rankingType = Tournament.getTournament().getParams().getTeamRankingType(j);
                } else {
                    rankingType = Tournament.getTournament().getParams().getIndivRankingType(j);
                }
                int value;
                value = ranking.getSortedValue(row, j + 1);

                String name = Integer.toString(value);
                if (rankingType == 0) {
                    break;
                } else {
                    s.append("<td class=\"tab_result" + suffix + "\" style=\"" + bg + "\">"
                            + name
                            + "</td>\n"
                    );
                }
            }

            s.append("</tr>");
        }
        s.append("</table></div>");

        return s.toString();
    }

    protected static String createTeamCriteria(Round r, Criteria crit) {
        StringBuilder s = new StringBuilder();

        ArrayList<Team> teams = new ArrayList<>();
        for (int i = 0; i < Tournament.getTournament().getTeamsCount(); i++) {
            teams.add(Tournament.getTournament().getTeam(i));
        }

        for (int subtype = 0; subtype < 3; subtype++) {
            MjtAnnexRankTeam ranking = new MjtAnnexRankTeam(Tournament.getTournament().getParams().isTeamVictoryOnly(),
                    Tournament.getTournament().getRoundIndex(r),
                    crit,
                    subtype,
                    teams,
                    true,
                    false);

            String subTypeName = "";
            switch (subtype) {
                case 0:
                    subTypeName = "+";
                    break;
                case 1:
                    subTypeName = "-";
                    break;
                case 2:
                    subTypeName = "+/-";
                    break;
            }
            String rank_name = crit.getName() + " " + subTypeName;
            //s.append("<div class=\"title\">" + StringEscapeUtils.escapeHtml4(rank_name) + "</div>");

            s.append("<div class=\"section\"><table\n"
                    + "             style = \"border-width:0px; margin-left: auto; margin-right: auto;text-align:center;\"\n"
                    + "        border = \"1\" cellpadding = \"0\" cellspacing = \"0\"\n"
                    + "                > <tbody>\n"
                    + "                <t >");

            int nbCol = Tournament.getTournament().getClansCount() > 1 ? 4 : 3;

            s.append("<tr><td class=\"tab_titre\" colspan=\"" + nbCol + "\">" + StringEscapeUtils.escapeHtml4(rank_name) + "</td><tr>");
            s.append("<td class=\"tab_titre\">" + StringEscapeUtils.escapeHtml4("#") + "</ td>\n");

            if (Tournament.getTournament().getClansCount() > 1) {
                s.append("<td class=\"tab_titre\">" + StringEscapeUtils.escapeHtml4(Translate.translate(Translate.CS_Clan)) + "</ td>\n");
            }

            s.append("<td class=\"tab_titre\">" + StringEscapeUtils.escapeHtml4(Translate.translate(Translate.CS_Team)) + "</ td>\n");

            s.append("<td class=\"tab_titre\">" + StringEscapeUtils.escapeHtml4(rank_name) + "</ td>\n");

            s.append("</tr>");
            for (int row = 0; row < ranking.getRowCount(); row++) {
                String suffix = "";
                s.append("<tr>");
                if (row == 0) {
                    suffix = "_1";
                }
                if (row == ranking.getRowCount() - 1) {
                    suffix = "_last";
                }
                String bg = "background-color:#ffffff;";
                if (row % 2 == 1) {
                    bg = "background-color:#eeeeee;";
                }

                s.append("<td class=\"tab_result" + suffix + "\" style=\"" + bg + "\">" + (row + 1) + "</td>\n");
                Team team = (Team) ranking.getSortedObject(row).getObject();

                if (Tournament.getTournament().getClansCount() > 1) {
                    Clan c = team.getClan();
                    s.append("<td class=\"tab_result" + suffix + "\" style=\"" + bg + "\">"
                            + WebPicture.getPictureAsHTML(c.getPicture(), 20, 20)
                            + StringEscapeUtils.escapeHtml4(c.getName())
                            + "</td>\n");
                }

                s.append("<td class=\"tab_result" + suffix + "\" style=\"" + bg + "\">"
                        + WebPicture.getPictureAsHTML(team.getPicture(), 20, 20)
                        + StringEscapeUtils.escapeHtml4(team.getName())
                        + "</td>\n");

                int value;

                value = ((ObjectAnnexRanking) ranking.getSortedObject(row)).getValue();

                String name = Integer.toString(value);
                s.append("<td class=\"tab_result" + suffix + "\" style=\"" + bg + "\">"
                        + name
                        + "</td>\n"
                );
                s.append("</tr>");
            }
            s.append("</table></div>");
        }

        return s.toString();
    }

    // @TODO
    protected static String createClanRanking(Round r) {
        StringBuilder s = new StringBuilder();

        try {
            ArrayList<Clan> clans = new ArrayList<>();
            for (int i = 0; i < Tournament.getTournament().getClansCount(); i++) {
                clans.add(Tournament.getTournament().getClan(i));
            }

            MjtRankingClan ranking = new MjtRankingClan(Tournament.getTournament().getRoundIndex(r), clans, false);

            s.append("<div class=\"section\"><table\n"
                    + "             style = \"border-width:0px;  margin-left: auto; margin-right: auto;text-align:center;\"\n"
                    + "        border = \"1\" cellpadding = \"0\" cellspacing = \"0\"\n"
                    + "                > ");

            int nbCol = 2;
            boolean bTeamVictoryOnly = Tournament.getTournament().getParams().isTeamVictoryOnly();

            boolean indiv = true;
            if (Tournament.getTournament().getParams().isTeamTournament() && Tournament.getTournament().getParams().getTeamPairing() == ETeamPairing.TEAM_PAIRING && bTeamVictoryOnly) {
                nbCol += Tournament.getTournament().getParams().getTeamRankingNumber();
                indiv = false;
            } else {
                nbCol += Tournament.getTournament().getParams().getIndivRankingNumber();
            }

            s.append("<tr><td class=\"tab_titre\" colspan=\"" + nbCol + "\" >" + StringEscapeUtils.escapeHtml4(Translate.translate(Ranking.CS_Clan)) + "</td></tr>");

            s.append("<td class=\"tab_titre\">" + StringEscapeUtils.escapeHtml4("#") + "</ td>\n");

            s.append("<td class=\"tab_titre\">" + StringEscapeUtils.escapeHtml4(Translate.translate(Translate.CS_Clan)) + "</ td>\n");

            if (!indiv) {
                for (int j = 0; j < Tournament.getTournament().getParams().getTeamRankingNumber(); j++) {
                    int rankingType = Tournament.getTournament().getParams().getTeamRankingType(j);
                    String name = MjtRanking.getRankingString(rankingType);
                    if (rankingType == 0) {
                        break;
                    } else {
                        s.append("<td class=\"tab_titre\">" + StringEscapeUtils.escapeHtml4(name) + "</ td>\n");
                    }
                }
            } else {
                for (int j = 0; j < Tournament.getTournament().getParams().getIndivRankingNumber(); j++) {
                    int rankingType = Tournament.getTournament().getParams().getIndivRankingType(j);
                    String name = MjtRanking.getRankingString(rankingType);
                    if (rankingType == 0) {
                        break;
                    } else {
                        s.append("<td class=\"tab_titre\">" + StringEscapeUtils.escapeHtml4(name) + "</ td>\n");
                    }
                }
            }

            s.append("</tr>");
            for (int row = 0; row < ranking.getRowCount(); row++) {
                String suffix = "";
                s.append("<tr>");
                if (row == 0) {
                    suffix = "_1";
                }
                if (row == ranking.getRowCount() - 1) {
                    suffix = "_last";
                }
                String bg = "background-color:#ffffff;";
                if (row % 2 == 1) {
                    bg = "background-color:#eeeeee;";
                }

                s.append("<td class=\"tab_result" + suffix + "\" style=\"" + bg + "\">" + (row + 1) + "</td>\n");
                Clan clan = (Clan) ranking.getSortedObject(row).getObject();

                s.append("<td class=\"tab_result" + suffix + "\" style=\"" + bg + "\">"
                        + WebPicture.getPictureAsHTML(clan.getPicture(), 20, 20)
                        + StringEscapeUtils.escapeHtml4(clan.getName())
                        + "</td>\n");

                int nbRanking = 0;

                if (!indiv) {
                    nbRanking = Tournament.getTournament().getParams().getTeamRankingNumber();
                } else {
                    nbRanking = Tournament.getTournament().getParams().getIndivRankingNumber();
                }

                for (int j = 0; j < nbRanking; j++) {
                    int rankingType = Parameters.C_RANKING_NONE;
                    if (!indiv) {
                        rankingType = Tournament.getTournament().getParams().getTeamRankingType(j);
                    } else {
                        rankingType = Tournament.getTournament().getParams().getIndivRankingType(j);
                    }

                    int value;
                    value = ranking.getSortedValue(row, j + 1);

                    String name = Integer.toString(value);
                    if (rankingType == 0) {
                        break;
                    } else {
                        s.append("<td class=\"tab_result" + suffix + "\" style=\"" + bg + "\">"
                                + name
                                + "</td>\n"
                        );
                    }
                }

                s.append("</tr>");
            }
            s.append("</table></div>");
        } catch (RemoteException re) {
            re.printStackTrace();
        }
        return s.toString();
    }

    protected static String createClanCriteria(Round r, Criteria crit) {
        StringBuilder s = new StringBuilder();

        ArrayList<Clan> clans = new ArrayList<>();
        for (int i = 0; i < Tournament.getTournament().getClansCount(); i++) {
            clans.add(Tournament.getTournament().getClan(i));
        }

        for (int subtype = 0; subtype < 3; subtype++) {
            MjtAnnexRankClan ranking = new MjtAnnexRankClan(
                    Tournament.getTournament().getRoundIndex(r),
                    crit,
                    subtype,
                    clans,
                    true,
                    Tournament.getTournament().getParams().getRankingIndiv1(),
                    Tournament.getTournament().getParams().getRankingIndiv2(),
                    Tournament.getTournament().getParams().getRankingIndiv3(),
                    Tournament.getTournament().getParams().getRankingIndiv4(),
                    Tournament.getTournament().getParams().getRankingIndiv5(), false);

            String subTypeName = "";
            switch (subtype) {
                case 0:
                    subTypeName = "+";
                    break;
                case 1:
                    subTypeName = "-";
                    break;
                case 2:
                    subTypeName = "+/-";
                    break;
            }
            String rank_name = crit.getName() + " " + subTypeName;

            s.append("<div class=\"section\"><table\n"
                    + "             style = \"border-width:0px; margin-left: auto; margin-right: auto;text-align:center;\"\n"
                    + "        border = \"1\" cellpadding = \"0\" cellspacing = \"0\"\n"
                    + "                > <tbody>\n"
                    + "                <tr>");

            s.append("<tr><td class=\"tab_titre\" colspan=\"3\">" + StringEscapeUtils.escapeHtml4(Translate.translate(Ranking.CS_Clan) + " " + rank_name) + "</td><tr>");
            s.append("<td class=\"tab_titre\">" + StringEscapeUtils.escapeHtml4("#") + "</ td>\n");

            s.append("<td class=\"tab_titre\">" + StringEscapeUtils.escapeHtml4(Translate.translate(Translate.CS_Team)) + "</ td>\n");

            s.append("<td class=\"tab_titre\">" + StringEscapeUtils.escapeHtml4(rank_name) + "</ td>\n");

            s.append("</tr>");
            for (int row = 0; row < ranking.getRowCount(); row++) {
                String suffix = "";
                s.append("<tr>");
                if (row == 0) {
                    suffix = "_1";
                }
                if (row == ranking.getRowCount() - 1) {
                    suffix = "_last";
                }
                String bg = "background-color:#ffffff;";
                if (row % 2 == 1) {
                    bg = "background-color:#eeeeee;";
                }

                s.append("<td class=\"tab_result" + suffix + "\" style=\"" + bg + "\">" + (row + 1) + "</td>\n");
                Clan clan = (Clan) ranking.getSortedObject(row).getObject();

                s.append("<td class=\"tab_result" + suffix + "\" style=\"" + bg + "\">"
                        + WebPicture.getPictureAsHTML(clan.getPicture(), 20, 20)
                        + StringEscapeUtils.escapeHtml4(clan.getName())
                        + "</td>\n");

                int value;

                value = ((ObjectAnnexRanking) ranking.getSortedObject(row)).getValue();

                String name = Integer.toString(value);
                s.append("<td class=\"tab_result" + suffix + "\" style=\"" + bg + "\">"
                        + name
                        + "</td>\n"
                );
                s.append("</tr>");
            }
            s.append("</table></div>");
        }

        return s.toString();
    }

    protected static String createGroupRanking(Round r, Group g) {
        ArrayList<Coach> coachs = new ArrayList<>();
        for (Coach c : Tournament.getTournament().getActiveCoaches()) {
            if (g.containsRoster(c.getRoster())) {
                coachs.add(c);
            }
        }
        return createIndividualRanking(r, coachs, Translate.translate(Ranking.CS_Group) + " " + g.getName());

    }

    protected static String createCategoryRanking(Round r, Category cat) {
        StringBuilder sb = new StringBuilder("");

        // Find Coachs of the Category
        ArrayList<Coach> coachs = new ArrayList<>();
        for (int i = 0; i < Tournament.getTournament().getCoachsCount(); i++) {
            Coach c = Tournament.getTournament().getCoach(i);
            if (c.containsCategory(cat)) {
                coachs.add(c);
            }
        }
        if (!coachs.isEmpty()) {
            sb.append(createIndividualRanking(r, coachs, cat.getName()));
        }

        // Find Teams of the Category
        ArrayList<Team> teams = new ArrayList<>();
        for (int i = 0; i < Tournament.getTournament().getTeamsCount(); i++) {
            Team t = Tournament.getTournament().getTeam(i);
            if (t.containsCategory(cat)) {
                teams.add(t);
            }
        }
        if (!teams.isEmpty()) {
            sb.append(createTeamRanking(r, teams, cat.getName()));
        }
        return sb.toString();
    }

    protected static String createPoolRanking(Round r, Pool p) {
        StringBuilder sb = new StringBuilder("");
        if ((Tournament.getTournament().getParams().isTeamTournament()) && (Tournament.getTournament().getParams().getTeamPairing() == ETeamPairing.TEAM_PAIRING)) {
            // Find Teams of the Pool
            ArrayList<Team> teams = new ArrayList<>();
            for (int i = 0; i < p.getCompetitorCount(); i++) {
                Competitor comp = p.getCompetitor(i);
                if (comp instanceof Team) {
                    teams.add((Team) comp);
                }
            }
            if (!teams.isEmpty()) {
                sb.append(createTeamRanking(r, teams, p.getName()));
            }

        } else {

            // Find Coachs of the Pool
            ArrayList<Coach> coachs = new ArrayList<>();
            for (int i = 0; i < p.getCompetitorCount(); i++) {
                Competitor comp = p.getCompetitor(i);
                if (comp instanceof Coach) {
                    coachs.add((Coach) comp);
                }
            }
            if (!coachs.isEmpty()) {
                sb.append(createIndividualRanking(r, coachs, p.getName()));
            }

        }

        return sb.toString();
    }

}
