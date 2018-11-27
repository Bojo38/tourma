/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.utils.web;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.StringEscapeUtils;
import tourma.data.Category;
import tourma.data.Clan;
import tourma.data.Coach;
import tourma.data.Competitor;
import tourma.data.Criteria;
import tourma.data.Group;
import tourma.data.GroupPoints;
import tourma.data.Parameters;
import tourma.data.Pool;
import tourma.data.Ranking;
import tourma.data.RosterType;
import tourma.data.Team;
import tourma.data.Tournament;
import tourma.languages.Translate;
import static tourma.languages.Translate.CS_Team;
import tourma.tableModel.MjtRanking;

/**
 *
 * @author WFMJ7631
 */
public class WebRules {

    private static final Logger LOG = Logger.getLogger(Tournament.class.getName());

    private final static String CS_Criterias = "Criterias";
    private final static String CS_Individual_Points = "IndividualPoints";
    private final static String CS_LargeVictory = "LargeVictory";
    private final static String CS_LargeVictoryGap = "LargeVictoryGap";
    private final static String CS_Victory = "Victory";
    private final static String CS_TeamVictoryBonus = "TeamVictoryBonus";
    private final static String CS_TeamDrawBonus = "TeamDrawBonus";
    private final static String CS_Draw = "Draw";
    private final static String CS_Lost = "Lost";
    private final static String CS_LittleLost = "LittleLost";
    private final static String CS_LittleLostGap = "LittleLostGap";
    private final static String CS_Order = "Order";
    private final static String CS_Options = "Options";
    private final static String CS_ExceptBestAndWorst = "ExceptBestAndWorst";
    private final static String CS_UseBestResults = "UseBestResults";
    private final static String CS_ExceptBestAndWorstForAnnex = "ExceptBestAndWorstForAnnex";

    private final static String CS_NumberOfPlayersForClan = "NumberOfPlayersForClan";
    private final static String CS_AvoidFirstRoundMatchClan = "AvoidFirstRoundMatchClan";
    private final static String CS_AvoidRoundMatchClan = "AvoidRoundMatchClan";
    private final static String CS_Categories = "Categories";
    private final static String CS_Groups = "Groups";

    private final static String CS_MatchAdditional = "Match additional points";

    public static String getHTML() {

        StringBuilder rules = new StringBuilder("");

        LOG.log(Level.FINE, "Create Rules");
        Parameters params = Tournament.getTournament().getParams();

        // Criterias
        rules.append("<div id=\"criterias\" class=\"section\">");

        rules.append("<table  style = \"border-width:0px; margin-left: auto; margin-right: auto;text-align:center;\"\n"
                + "        border = \"0\" cellpadding = \"0\" cellspacing = \"0\">");
        int nb_cols = params.isTeamTournament() ? 5 : 3;
        rules.append("<tr><td class=\"tab_titre\" colspan=\"" + nb_cols + "\">");
        rules.append("<center>" + StringEscapeUtils.escapeHtml4(Translate.translate(CS_Criterias)) + "</center>");
        rules.append("</td></tr>");
        rules.append("<tr><td class=\"tab_titre\">&nbsp;</td>");
        //rules.append("<td>" + StringEscapeUtils.escapeHtml4(Translate.translate(Translate.CS_Name)) + "</td>");
        rules.append("<td class=\"tab_titre\">" + StringEscapeUtils.escapeHtml4(Translate.translate(Translate.CS_Points_Plus)) + "</td>");
        rules.append("<td class=\"tab_titre\">" + StringEscapeUtils.escapeHtml4(Translate.translate(Translate.CS_Points_Minus)) + "</td>");
        if (params.isTeamTournament()) {
            rules.append("<td class=\"tab_titre\">" + StringEscapeUtils.escapeHtml4(Translate.translate(Translate.CS_Points_Team_Plus)) + "</td>");
            rules.append("<td class=\"tab_titre\">" + StringEscapeUtils.escapeHtml4(Translate.translate(Translate.CS_Points_Team_Minus)) + "</td>");
        }
        rules.append("</tr>");
        for (int i = 0; i < params.getCriteriaCount(); i++) {
            Criteria crit = params.getCriteria(i);
            rules.append("<tr>");
            rules.append("<td class=\"tab_result\">" + StringEscapeUtils.escapeHtml4(crit.getName()) + "</td>");
            rules.append("<td class=\"tab_result\">" + crit.getPointsFor() + "</td>");
            rules.append("<td class=\"tab_result\">" + crit.getPointsAgainst() + "</td>");
            if (params.isTeamTournament()) {
                rules.append("<td class=\"tab_result\">" + crit.getPointsTeamFor() + "</td>");
                rules.append("<td class=\"tab_result\">" + crit.getPointsTeamAgainst() + "</td>");
            }
            rules.append("</tr>");
        }
        rules.append("</table></div>\n");

        // Create individual
        rules.append("<div id=\"indiv_points\" class=\"section\">");
        //rules.append("<center>" + StringEscapeUtils.escapeHtml4(Translate.translate(CS_Individual_Points)) + "</center>");
        rules.append("<table  style = \"border-width:0px; margin-left: auto; margin-right: auto;text-align:center;\"\n"
                + "        border = \"0\" cellpadding = \"0\" cellspacing = \"0\">");
        nb_cols = 2;
        rules.append("<tr><td class=\"tab_titre\" colspan=\"" + nb_cols + "\">");
        rules.append("<center>" + StringEscapeUtils.escapeHtml4(Translate.translate(CS_Individual_Points)) + "</center>");
        rules.append("</td></tr>");
        // Victory
        rules.append("<tr>");
        rules.append("<td  class=\"tab_titre\">" + StringEscapeUtils.escapeHtml4(Translate.translate(CS_Order)) + "</td>");
        rules.append("<td class=\"tab_titre\">" + StringEscapeUtils.escapeHtml4(Translate.translate(Translate.CS_Ranking)) + "</td>");
        rules.append("</tr>");
        for (int i = 0; i < params.getIndivRankingNumber(); i++) {
            rules.append("<tr><td class=\"tab_result\">" + (i + 1) + "</td>");
            int r = 0;
            switch (i) {
                case 0:
                    r = params.getRankingIndiv1();
                    break;
                case 1:
                    r = params.getRankingIndiv2();
                    break;
                case 2:
                    r = params.getRankingIndiv3();
                    break;
                case 3:
                    r = params.getRankingIndiv4();
                    break;
                case 4:
                    r = params.getRankingIndiv5();
                    break;
            }

            String s = MjtRanking.getRankingString(r);
            rules.append("<td class=\"tab_result\">" + StringEscapeUtils.escapeHtml4(s) + "</td></tr>");
        }

        rules.append("<tr>");
        rules.append("<td class=\"tab_titre\">" + StringEscapeUtils.escapeHtml4(Translate.translate(CS_Options)) + "</td>");
        rules.append("<td class=\"tab_titre\">" + StringEscapeUtils.escapeHtml4(" ") + "</td>");
        rules.append("</tr>");

        if (params.isUseBestResultIndiv()) {
            rules.append("<tr>");
            rules.append("<td class=\"tab_result\">" + StringEscapeUtils.escapeHtml4(Translate.translate(CS_UseBestResults)) + "</td>");
            rules.append("<td class=\"tab_result\">" + params.getBestResultIndiv() + "</td>");
            rules.append("</tr>");
        }
        if (params.isExceptBestAndWorstIndiv()) {
            rules.append("<tr>");
            rules.append("<td class=\"tab_result\">" + StringEscapeUtils.escapeHtml4(Translate.translate(CS_ExceptBestAndWorst)) + "</td>");
            rules.append("<td class=\"tab_result\">" + "&nbsp;" + "</td>");
            rules.append("</tr>");
        }
        if (params.isApplyToAnnexIndiv()) {
            rules.append("<tr>");
            rules.append("<td class=\"tab_result\">" + StringEscapeUtils.escapeHtml4(Translate.translate(CS_ExceptBestAndWorstForAnnex)) + "</td>");
            rules.append("<td class=\"tab_result\">" + "&nbsp;" + "</td>");
            rules.append("</tr>");
        }

        // Large Victory
        if (params.isUseLargeVictory()) {
            rules.append("<tr><td class=\"tab_result\">" + StringEscapeUtils.escapeHtml4(Translate.translate(CS_LargeVictory)) + "</td>");
            rules.append("<td class=\"tab_result\">" + params.getPointsIndivLargeVictory() + "</td></tr>");

            rules.append("<tr><td class=\"tab_result\">" + StringEscapeUtils.escapeHtml4(Translate.translate(CS_LargeVictoryGap)) + "</td>");
            rules.append("<td class=\"tab_result\">" + params.getGapLargeVictory() + "</td></tr>");
        }

        // Victory
        rules.append("<tr><td class=\"tab_result\">" + StringEscapeUtils.escapeHtml4(Translate.translate(CS_Victory)) + "</td>");
        rules.append("<td class=\"tab_result\">" + params.getPointsIndivVictory() + "</td></tr>");

        // Draw
        rules.append("<tr><td class=\"tab_result\">" + StringEscapeUtils.escapeHtml4(Translate.translate(CS_Draw)) + "</td>");
        rules.append("<td class=\"tab_result\">" + params.getPointsIndivDraw() + "</td></tr>");

        //Lost
        rules.append("<tr><td class=\"tab_result\">" + StringEscapeUtils.escapeHtml4(Translate.translate(CS_Lost)) + "</td>");
        rules.append("<td class=\"tab_result\">" + params.getPointsIndivLost() + "</td></tr>");

        if (params.isUseLittleLoss()) {
            rules.append("<tr><td class=\"tab_result\">" + StringEscapeUtils.escapeHtml4(Translate.translate(CS_LittleLost)) + "</td>");
            rules.append("<td class=\"tab_result\">" + params.getPointsIndivLittleLost() + "</td></tr>");

            rules.append("<tr><td class=\"tab_result\">" + StringEscapeUtils.escapeHtml4(Translate.translate(CS_LittleLostGap)) + "</td>");
            rules.append("<td class=\"tab_result\">" + params.getGapLittleLost() + "</td></tr>");
        }

        //Conceed
        rules.append("<tr><td class=\"tab_result\">" + StringEscapeUtils.escapeHtml4(Translate.translate(Translate.CS_Conceeded)) + "</td>");
        rules.append("<td class=\"tab_result\">" + params.getPointsConcedeed() + "</td></tr>");

        //Refuse
        rules.append("<tr><td class=\"tab_result\">" + StringEscapeUtils.escapeHtml4(Translate.translate(Translate.CS_Refused)) + "</td>");
        rules.append("<td class=\"tab_result\">" + params.getPointsRefused() + "</td></tr>");

        rules.append("<tr><td class=\"tab_result\">" + StringEscapeUtils.escapeHtml4(Translate.translate(Translate.CS_TablesPoints)) + "</td>");
        rules.append("<td class=\"tab_result\">" + (Tournament.getTournament().getParams().isTableBonus() ? "yes" : "no") + "</td></tr>");

        rules.append("</table></div>");

        // Create Team if Team
        if (params.isTeamTournament()) {

            rules.append("<div id=\"team_points\" class=\"section\">");
            //    rules.append("<center>" + StringEscapeUtils.escapeHtml4(Translate.translate(CS_Team)) + "</center>");
            rules.append("<table style = \"border-width:0px; margin-left: auto; margin-right: auto;text-align:center;\"\n"
                    + "        border = \"1\" cellpadding = \"0\" cellspacing = \"0\">");
            nb_cols = 2;
            rules.append("<tr><td class=\"tab_titre\" colspan=\"" + nb_cols + "\">");
            rules.append("<center>" + StringEscapeUtils.escapeHtml4(Translate.translate(CS_Team)) + "</center>");
            rules.append("</td></tr>");

            // Victory
            rules.append("<tr>");
            rules.append("<td  class=\"tab_titre\">" + StringEscapeUtils.escapeHtml4(Translate.translate(CS_Order)) + "</td>");
            rules.append("<td  class=\"tab_titre\">" + StringEscapeUtils.escapeHtml4(Translate.translate(Translate.CS_Ranking)) + "</td>");
            rules.append("</tr>");
            for (int i = 0; i < params.getTeamRankingNumber(); i++) {
                rules.append("<tr><td class=\"tab_result\">" + (i + 1) + "</td>");
                int r = 0;
                switch (i) {
                    case 0:
                        r = params.getRankingTeam1();
                        break;
                    case 1:
                        r = params.getRankingTeam2();
                        break;
                    case 2:
                        r = params.getRankingTeam3();
                        break;
                    case 3:
                        r = params.getRankingTeam4();
                        break;
                    case 4:
                        r = params.getRankingTeam5();
                        break;
                }

                String s = MjtRanking.getRankingString(r);
                rules.append("<td class=\"tab_result\">" + StringEscapeUtils.escapeHtml4(s) + "</td></tr>");
            }

            rules.append("<tr>");
            rules.append("<td class=\"tab_titre\">" + StringEscapeUtils.escapeHtml4(Translate.translate(CS_Options)) + "</td>");
            rules.append("<td class=\"tab_result\">" + StringEscapeUtils.escapeHtml4(" ") + "</td>");
            rules.append("</tr>");

            if (params.isUseBestResultTeam()) {
                rules.append("<tr>");
                rules.append("<td class=\"tab_result\">" + StringEscapeUtils.escapeHtml4(Translate.translate(CS_UseBestResults)) + "</td>");
                rules.append("<td class=\"tab_result\">" + params.getBestResultTeam() + "</td>");
                rules.append("</tr>");
            }
            if (params.isExceptBestAndWorstTeam()) {
                rules.append("<tr>");
                rules.append("<td class=\"tab_result\">" + StringEscapeUtils.escapeHtml4(Translate.translate(CS_ExceptBestAndWorst)) + "</td>");
                rules.append("<td class=\"tab_result\">" + "&nbsp;" + "</td>");
                rules.append("</tr>");
            }
            if (params.isApplyToAnnexTeam()) {
                rules.append("<tr>");
                rules.append("<td class=\"tab_result\">" + StringEscapeUtils.escapeHtml4(Translate.translate(CS_ExceptBestAndWorstForAnnex)) + "</td>");
                rules.append("<td class=\"tab_result\">" + "&nbsp;" + "</td>");
                rules.append("</tr>");
            }

            if (params.isTeamVictoryOnly()) {
                // Victory
                rules.append("<tr><td class=\"tab_result\">" + StringEscapeUtils.escapeHtml4(Translate.translate(CS_Victory)) + "</td>");
                rules.append("<td class=\"tab_result\">" + params.getPointsTeamVictory() + "</td></tr>");

                // Draw
                rules.append("<tr><td class=\"tab_result\">" + StringEscapeUtils.escapeHtml4(Translate.translate(CS_Draw)) + "</td>");
                rules.append("<td class=\"tab_result\">" + params.getPointsTeamDraw() + "</td></tr>");

                //Lost
                rules.append("<tr><td class=\"tab_result\">" + StringEscapeUtils.escapeHtml4(Translate.translate(CS_Lost)) + "</td>");
                rules.append("<td class=\"tab_result\">" + params.getPointsTeamLost() + "</td></tr>");

            } else {
                rules.append("<tr><td class=\"tab_result\">" + StringEscapeUtils.escapeHtml4(Translate.translate(CS_TeamVictoryBonus)) + "</td>");
                rules.append("<td class=\"tab_result\">" + params.getPointsTeamVictoryBonus() + "</td></tr>");

                rules.append("<tr><td class=\"tab_result\">" + StringEscapeUtils.escapeHtml4(Translate.translate(CS_TeamDrawBonus)) + "</td>");
                rules.append("<td class=\"tab_result\">" + params.getPointsTeamDrawBonus() + "</td></tr>");
            }

            rules.append("</table></div>");
        }

        // Create Clan if Clan
        if (params.isEnableClans()) {

            rules.append("<div id=\"clans\" class=\"section\">");
            //rules.append("<center>" + StringEscapeUtils.escapeHtml4(Translate.translate(Ranking.CS_Clan)) + "</center>");

            rules.append("<table style = \"border-width:0px; margin-left: auto; margin-right: auto;text-align:center;\"\n"
                    + "        border = \"0\" cellpadding = \"0\" cellspacing = \"0\">");

            nb_cols = 2;
            rules.append("<tr><td class=\"tab_titre\" colspan=\"" + nb_cols + "\">");
            rules.append("<center>" + StringEscapeUtils.escapeHtml4(Translate.translate(Ranking.CS_Clan)) + "</center>");
            rules.append("</td></tr>");

            rules.append("<tr><td class=\"tab_result\">" + StringEscapeUtils.escapeHtml4(Translate.translate(CS_NumberOfPlayersForClan)) + "</td><td class=\"tab_result\">" + params.getClansMembersNumber() + "</td></tr>");
            rules.append("<tr><td class=\"tab_result\">" + StringEscapeUtils.escapeHtml4(Translate.translate(CS_AvoidFirstRoundMatchClan)) + "</td><td class=\"tab_result\">" + Translate.translate(Boolean.toString(params.isAvoidClansFirstMatch())) + "</td></tr>");
            rules.append("<tr><td class=\"tab_result\">" + StringEscapeUtils.escapeHtml4(Translate.translate(CS_AvoidRoundMatchClan)) + "</td><td class=\"tab_result\">" + Translate.translate(Boolean.toString(params.isAvoidClansMatch())) + "</td></tr>");
            //rules.append("</table>");

            if (Tournament.getTournament().getClansCount() > 1) {

                /* rules.append("<table  style = \"border-width:0px; margin-left: auto; margin-right: auto;text-align:center;\"\n"
                + "        border = \"0\" cellpadding = \"0\" cellspacing = \"0\">");*/
                rules.append("<tr><td  class=\"tab_titre\" >" + StringEscapeUtils.escapeHtml4(Translate.translate(Translate.CS_Clan)) + "</td>");
                rules.append("<td  class=\"tab_titre\" >" + StringEscapeUtils.escapeHtml4(Translate.translate(Translate.CS_Coach)) + "</td></tr>");

                for (int i = 0; i < Tournament.getTournament().getClansCount(); i++) {
                    Clan c = Tournament.getTournament().getClan(i);

                    /*rules.append("<table  style = \"border-width:0px; margin-left: auto; margin-right: auto;text-align:center;\"\n"
                + "        border = \"0\" cellpadding = \"0\" cellspacing = \"0\">");*/
                    rules.append("<tr><td  class=\"tab_titre\" >" + StringEscapeUtils.escapeHtml4(c.getName()) + "</td><td  class=\"tab_result\" >");
                    if (params.isTeamTournament()) {
                        for (int j = 0; j < Tournament.getTournament().getTeamsCount(); j++) {
                            Team t = Tournament.getTournament().getTeam(j);
                            if (t.getClan() == c) {
                                rules.append(StringEscapeUtils.escapeHtml4(t.getName()));
                                if (j != (Tournament.getTournament().getTeamsCount() - 1)) {
                                    rules.append(", ");
                                }
                            }
                        }
                    } else {
                        for (int j = 0; j < Tournament.getTournament().getCoachsCount(); j++) {
                            Coach t = Tournament.getTournament().getCoach(j);
                            if (t.getClan() == c) {
                                rules.append(StringEscapeUtils.escapeHtml4(t.getName()));
                                if (j != (Tournament.getTournament().getCoachsCount() - 1)) {
                                    rules.append(", ");
                                }
                            }
                        }
                    }
                    rules.append("</td>");
                    //rules.append("</table>");
                }
                rules.append("</table></div>");
            }
        }

        // Create Groups if groups
        if ((Tournament.getTournament().getGroupsCount() > 1) && (!params.isMultiRoster())) {

            rules.append("<div id=\"groups\" class=\"section\">");
            //rules.append("<center>" + StringEscapeUtils.escapeHtml4(Translate.translate(CS_Groups)) + "</center>");

            rules.append("<table  style = \"border-width:0px; margin-left: auto; margin-right: auto;text-align:center;\"\n"
                    + "        border = \"1\" cellpadding = \"0\" cellspacing = \"0\">");

            nb_cols = 3;
            rules.append("<tr><td class=\"tab_titre\" colspan=\"" + nb_cols + "\">");
            rules.append("<center>" + StringEscapeUtils.escapeHtml4(Translate.translate(CS_Groups)) + "</center>");
            rules.append("</td></tr>");

            for (int i = 0; i < Tournament.getTournament().getGroupsCount(); i++) {
                Group g = Tournament.getTournament().getGroup(i);
                rules.append("<tr><td  class=\"tab_titre\" >" + StringEscapeUtils.escapeHtml4(g.getName()) + "</td>");

                rules.append("<td class=\"tab_result\">");
                for (int j = 0; j < g.getRosterCount(); j++) {
                    RosterType rt = g.getRoster(j);
                    if (rt.getName() != null) {
                        rules.append(StringEscapeUtils.escapeHtml4(rt.getName()) + "<br>");
                    }
                }
                rules.append("</td>");

                // Tableaux de Bonus de groupes
                StringBuilder gpPoints = new StringBuilder("");
                for (int j = 0; j < Tournament.getTournament().getGroupsCount(); j++) {
                    Group opp = Tournament.getTournament().getGroup(j);
                    if (opp != g) {
                        GroupPoints gp = g.getOpponentModificationPoints(opp);
                        if (gp != null) {
                            gpPoints.append("<tr>");
                            gpPoints.append("<td class=\"tab_titre\" >" + StringEscapeUtils.escapeHtml4(opp.getName()) + "</td>");
                            gpPoints.append("<td class=\"tab_result\">" + gp.getVictoryPoints() + "</td>");
                            gpPoints.append("<td class=\"tab_result\">" + gp.getDrawPoints() + "</td>");
                            gpPoints.append("<td class=\"tab_result\">" + gp.getLossPoints() + "</td>");
                            gpPoints.append("</tr>");
                        } else {
                            gpPoints.append("<tr>");
                            gpPoints.append("<td class=\"tab_titre\">" + StringEscapeUtils.escapeHtml4(opp.getName()) + "</td>");
                            gpPoints.append("<td class=\"tab_result\">" + 0 + "</td>");
                            gpPoints.append("<td class=\"tab_result\">" + 0 + "</td>");
                            gpPoints.append("<td class=\"tab_result\">" + 0 + "</td>");
                            gpPoints.append("</tr>");
                        }
                    }
                }

                rules.append("<td><table  style = \"border-width:0px; margin-left: auto; margin-right: auto;text-align:center;\"\n"
                        + "        border = \"0\" cellpadding = \"0\" cellspacing = \"0\">");
                rules.append("<tr><td class=\"tab_titre\" colspan=\"4\">" + StringEscapeUtils.escapeHtml4(Translate.translate(CS_MatchAdditional)) + "</td></tr>");
                rules.append("<tr><td class=\"tab_titre\">&nbsp</td>");
                rules.append("<td class=\"tab_titre\">" + StringEscapeUtils.escapeHtml4(Translate.translate(CS_Victory)) + "</td>");
                rules.append("<td class=\"tab_titre\">" + StringEscapeUtils.escapeHtml4(Translate.translate(CS_Draw)) + "</td>");
                rules.append("<td class=\"tab_titre\">" + StringEscapeUtils.escapeHtml4(Translate.translate(CS_Lost)) + "</td>");
                rules.append("</tr>");
                rules.append(gpPoints.toString());
                rules.append("</table></td>");

                rules.append("</tr>");
            }
            rules.append("</table></div>");

        }

        // Create Categories if categories
        if (Tournament.getTournament().getCategoriesCount() > 1) {

            rules.append("<div id=\"categories\" class=\"section\">");
            //rules.append("<center>" + StringEscapeUtils.escapeHtml4(Translate.translate(CS_Categories)) + "</center>");

            rules.append("<table  style = \"border-width:0px; margin-left: auto; margin-right: auto;text-align:center;\"\n"
                    + "        border = \"1\" cellpadding = \"0\" cellspacing = \"0\">");
            nb_cols = params.isTeamTournament() ? 3 : 2;
            rules.append("<tr><td class=\"tab_titre\" colspan=\"" + nb_cols + "\">");
            rules.append("<center>" + StringEscapeUtils.escapeHtml4(Translate.translate(CS_Categories)) + "</center>");
            rules.append("</td></tr>");
            for (int i = 0; i < Tournament.getTournament().getCategoriesCount(); i++) {
                Category cat = Tournament.getTournament().getCategory(i);
                rules.append("<tr><td  class=\"tab_titre\">" + StringEscapeUtils.escapeHtml4(cat.getName()) + "</td>");
                if (params.isTeamTournament()) {
                    rules.append("<td class=\"tab_result\" >");
                    for (int j = 0; j < Tournament.getTournament().getTeamsCount(); j++) {
                        Team c = Tournament.getTournament().getTeam(j);
                        if (c.containsCategory(cat)) {
                            rules.append(StringEscapeUtils.escapeHtml4(c.getName()) + "<br>");
                        }
                    }
                    rules.append("</td>");
                }
                rules.append("<td class=\"tab_result\" >");
                for (int j = 0; j < Tournament.getTournament().getCoachsCount(); j++) {
                    Coach c = Tournament.getTournament().getCoach(j);
                    if (c.containsCategory(cat)) {
                        rules.append(StringEscapeUtils.escapeHtml4(c.getName()) + "<br>");
                    }
                }
                rules.append("</td>");
                rules.append("</tr>");
            }
            rules.append("</table></div>");
        }

        // Create Pool if Pool
        if (Tournament.getTournament().getPoolCount() > 0) {

            rules.append("<div id=\"pools\" class=\"section\">");

            rules.append("<table  style = \"border-width:0px; margin-left: auto; margin-right: auto;text-align:center;\"\n"
                    + "        border = \"1\" cellpadding = \"0\" cellspacing = \"0\">");
            nb_cols = Tournament.getTournament().getPoolCount();
            rules.append("<tr><td class=\"tab_titre\" colspan=\"" + nb_cols + "\">");
            rules.append("<center>" + StringEscapeUtils.escapeHtml4(Translate.translate(Translate.CS_Pool)) + "</center>");
            rules.append("</td></tr>");

            //rules.append("<center>" + StringEscapeUtils.escapeHtml4(Translate.translate(Translate.CS_Pool)) + "</center>");
            rules.append("<tr>");
            for (int i = 0; i < Tournament.getTournament().getPoolCount(); i++) {
                Pool p = Tournament.getTournament().getPool(i);
                rules.append("<td class=\"tab_titre\">" + StringEscapeUtils.escapeHtml4(p.getName()) + "</td>");
            }
            rules.append("</tr>");

            rules.append("<tr>");
            for (int i = 0; i < Tournament.getTournament().getPoolCount(); i++) {
                Pool p = Tournament.getTournament().getPool(i);
                rules.append("<td class=\"tab_result\">");
                for (int j = 0; j < p.getCompetitorCount(); j++) {
                    Competitor c = p.getCompetitor(j);
                    rules.append(StringEscapeUtils.escapeHtml4(c.getName()));
                    if (j != p.getCompetitorCount() - 1) {
                        rules.append("<br>");
                    }
                }
                rules.append("</td>");
            }
            rules.append("</tr>");
            rules.append("</table></div>");
        }

        rules.append("</center>");
        return rules.toString();
    }
}
