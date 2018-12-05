/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.utils.web;

import java.util.Map;
import org.apache.commons.lang3.StringEscapeUtils;
import tourma.MainFrame;
import tourma.data.Coach;
import tourma.data.CoachMatch;
import tourma.data.Criteria;
import tourma.data.Round;
import tourma.data.Tournament;
import tourma.data.Value;
import tourma.languages.Translate;

/**
 *
 * @author WFMJ7631
 */
public class WebMatchResult {

    public final static String CS_ENTER_YOUR_PIN_CODE = "EnterYouPinCode";
    public final static String CS_SelectYourName = "SelectYourName";

    public static String getHTML() {
        StringBuilder sb = new StringBuilder("");

        Round r = Tournament.getTournament().getRound(Tournament.getTournament().getRoundsCount() - 1);

        sb.append("<center>");
        sb.append("<form action=\"/enter_match_result\" method=\"POST\">");
        sb.append(StringEscapeUtils.escapeHtml4(Translate.translate(CS_SelectYourName)) + ": " + "<select name=\"name\" size=\"1\" >");
        for (CoachMatch cm : r.getCoachMatchs()) {
            sb.append("<option value=\"" + cm.getCompetitor1().getName() + "\">" + StringEscapeUtils.escapeHtml4(cm.getCompetitor1().getName()) + "</option>");
            sb.append("<option value=\"" + cm.getCompetitor2().getName() + "\">" + StringEscapeUtils.escapeHtml4(cm.getCompetitor2().getName()) + "</option>");
        }
        sb.append("</select>");
        sb.append("<br>" + StringEscapeUtils.escapeHtml4(Translate.translate(CS_ENTER_YOUR_PIN_CODE)) + ": " + "<input type=\"number\" name=\"pin\"/>");
        sb.append("<br>" + StringEscapeUtils.escapeHtml4(Translate.translate(CS_ENTER_YOUR_PIN_CODE)) + ": " + " <input type=\"submit\" value=\"OK\">");
        sb.append("</form>");
        sb.append("</center>");

        return sb.toString();
    }
    public final static String CS_PIN_CODE = "PinCode";
    public final static String CS_WRONG_PIN_CODE = "WrongPinCode";
    public final static String CS_COACH_NOT_FOUND = "CoachNotFound";
    public final static String CS_MATCH_NOT_FOUND_FOR = "MatchNotFoundFor";
    public final static String CS_ENTER_OPPONENT_PIN_CODE = "EnterOpponentPinCode";

    public static String getHTML(String name, String pinCode) {
        StringBuilder sb = new StringBuilder("");
        sb.append("<center>");
        Coach coach = null;

        // Check Coach Pin Code
        for (Coach c : Tournament.getTournament().getActiveCoaches()) {
            String cName = c.getName();
            if (cName.equals(name)) {
                int pin = Integer.parseInt(pinCode);
                if (c.getPinCode() == pin) {
                    coach = c;
                    break;
                }
            }
        }

        if (coach != null) {

            // Find Match
            Round r = Tournament.getTournament().getRound(Tournament.getTournament().getRoundsCount() - 1);
            Coach opponent = null;
            CoachMatch coachmatch = null;
            for (CoachMatch cm : r.getCoachMatchs()) {
                if (cm.getCompetitor1() == coach) {
                    opponent = (Coach) cm.getCompetitor2();
                    coachmatch = cm;
                    break;
                }
                if (cm.getCompetitor2() == coach) {
                    opponent = (Coach) cm.getCompetitor1();
                    coachmatch = cm;
                    break;
                }
            }

            if ((coachmatch != null) && (opponent != null)) {
                // Display Match Form

                sb.append(StringEscapeUtils.escapeHtml4(Translate.translate(Translate.CS_Round)) + " " + (Tournament.getTournament().getRoundIndex(r) + 1));
                sb.append("<form action=\"/test_match_result\" method=\"POST\">");
                sb.append("<table>");
                if (coach == coachmatch.getCompetitor1()) {
                    sb.append("<th>");
                    sb.append("<td class=\"tab_titre\">");
                    sb.append("<input type=\"hidden\" readonly=\"readonly\"  name=\"competitor1\" value=\"" + coachmatch.getCompetitor1().getName() + "\" />");
                    //sb.append("<input type=\"text\" readonly=\"readonly\" name=\"display1\" value=\"" + StringEscapeUtils.escapeHtml4(coachmatch.getCompetitor1().getName()) + "\" /><br>");                    
                    sb.append(StringEscapeUtils.escapeHtml4(coachmatch.getCompetitor1().getName()) + "<br>");
                    sb.append(StringEscapeUtils.escapeHtml4(Translate.translate(CS_PIN_CODE) + ": "));
                    sb.append("<input type=\"password\" readonly=\"readonly\" name=\"pincode1\" value=\"" + pinCode + "\" />");
                    sb.append("</td>");
                    sb.append("<td class=\"tab_titre\">");
                    sb.append("<input type=\"hidden\" readonly=\"readonly\" name=\"competitor2\" value=\"" + coachmatch.getCompetitor2().getName() + "\" />");
                    //sb.append("<input type=\"text\" readonly=\"readonly\" name=\"display2\" value=\"" + StringEscapeUtils.escapeHtml4(coachmatch.getCompetitor2().getName()) + "\" /><br>");
                    sb.append(StringEscapeUtils.escapeHtml4(coachmatch.getCompetitor2().getName()) + "<br>");
                    sb.append(StringEscapeUtils.escapeHtml4(Translate.translate(CS_PIN_CODE) + ": "));
                    sb.append("<input type=\"password\" name=\"pincode2\" />");
                    sb.append("</td>");
                    sb.append("</th>");
                } else {
                    sb.append("<th>");
                    sb.append("<td  class=\"tab_titre\">");
                    sb.append("<input type=\"hidden\" readonly=\"readonly\"  name=\"competitor1\" value=\"" + coachmatch.getCompetitor1().getName() + "\" />");
                    //sb.append("<input type=\"text\" readonly=\"readonly\" name=\"display1\" value=\"" + StringEscapeUtils.escapeHtml4(coachmatch.getCompetitor1().getName()) + "\" />");
                    sb.append(StringEscapeUtils.escapeHtml4(coachmatch.getCompetitor1().getName()) + "<br>");
                    sb.append(StringEscapeUtils.escapeHtml4(Translate.translate(CS_PIN_CODE) + ": "));
                    sb.append("<input type=\"password\" name=\"pincode1\" />");
                    sb.append("</td>");
                    sb.append("<td  class=\"tab_titre\">");
                    sb.append("<input type=\"hidden\" readonly=\"readonly\" name=\"competitor2\" value=\"" + coachmatch.getCompetitor2().getName() + "\" />");
                    //sb.append("<input type=\"text\" readonly=\"readonly\" name=\"display2\" value=\"" + StringEscapeUtils.escapeHtml4(coachmatch.getCompetitor2().getName()) + "\" />");
                    sb.append(StringEscapeUtils.escapeHtml4(coachmatch.getCompetitor2().getName()) + "<br>");
                    sb.append(StringEscapeUtils.escapeHtml4(Translate.translate(CS_PIN_CODE) + ": "));
                    sb.append("<input type=\"password\" readonly=\"readonly\" name=\"pincode2\" value=\"" + pinCode + "\" />");
                    sb.append("</td>");
                    sb.append("</th>");
                }
                for (int i = 0; i < Tournament.getTournament().getParams().getCriteriaCount(); i++) {
                    Criteria crit = Tournament.getTournament().getParams().getCriteria(i);
                    sb.append("<tr>");
                    sb.append("<td  class=\"tab_result\">" + StringEscapeUtils.escapeHtml4(crit.getName()) + "</td>");

                    sb.append("<td  class=\"tab_result\"><input type=\"number\" name=\"" + StringEscapeUtils.escapeHtml4(crit.getName()) + "1\"/></td>");
                    sb.append("<td  class=\"tab_result\"><input type=\"number\" name=\"" + StringEscapeUtils.escapeHtml4(crit.getName()) + "2\"/></td>");
                    sb.append("</tr>");
                }
                sb.append("</table>");
                sb.append("<br>");
                sb.append("<br><input type=\"submit\" value=\"OK\">");
                sb.append("</form>");

            } else {
                sb.append(StringEscapeUtils.escapeHtml4(Translate.translate(CS_MATCH_NOT_FOUND_FOR) + " " + name));
            }

        } else {
            sb.append(StringEscapeUtils.escapeHtml4(Translate.translate(CS_COACH_NOT_FOUND) + " " + name));
        }
        sb.append("</center>");

        return sb.toString();
    }

    public static String getHTML(Map<String, String> parms) {
        StringBuilder sb = new StringBuilder("");
        CoachMatch coachmatch = null;
        Round r = null;
        // Test Parameters
        boolean valid = true;
        sb.append("<center>");
        String pinCode1 = parms.get("pincode1");
        String pinCode2 = parms.get("pincode2");

        String competitor1 = parms.get("competitor1");
        String competitor2 = parms.get("competitor2");

        if ((competitor1 == null) || (competitor2 == null) || (pinCode1 == null) || (pinCode2 == null)) {
            sb.append(StringEscapeUtils.escapeHtml4(Translate.translate(CS_COACH_NOT_FOUND)));
            sb.append("<br>");
            valid = false;
        }
        if (valid) {

            // Find Match && // Test Pin Code
            r = Tournament.getTournament().getRound(Tournament.getTournament().getRoundsCount() - 1);
            boolean wrong_pincode = false;
            if (r == null) {
                valid = false;
            } else {

                for (CoachMatch cm : r.getCoachMatchs()) {
                    if (cm != null) {
                        if ((cm.getCompetitor1().getName().equals(competitor1))
                                && (cm.getCompetitor2().getName().equals(competitor2))) {
                            Coach coach1 = null;
                            if (cm.getCompetitor1() instanceof Coach) {
                                coach1 = (Coach) cm.getCompetitor1();
                            }
                            Coach coach2 = null;
                            if (cm.getCompetitor1() instanceof Coach) {
                                coach2 = (Coach) cm.getCompetitor2();
                            }
                            if ((coach1 != null) && (coach2 != null)) {
                                try {
                                    int pin1 = Integer.parseInt(pinCode1);
                                    int pin2 = Integer.parseInt(pinCode2);
                                    if ((coach1.getPinCode() == pin1) || (coach2.getPinCode() == pin2)) {
                                        coachmatch = cm;
                                    } else {
                                        wrong_pincode = true;
                                    }
                                } catch (NumberFormatException nfe) {
                                    valid = false;
                                }
                            }
                            break;
                        }
                    }
                }
            }

            if (coachmatch == null) {
                valid = false;
                if (wrong_pincode) {
                    sb.append(StringEscapeUtils.escapeHtml4(Translate.translate(CS_WRONG_PIN_CODE)) + " " + StringEscapeUtils.escapeHtml4(competitor1) + "/" + StringEscapeUtils.escapeHtml4(competitor2));
                } else {
                    sb.append(StringEscapeUtils.escapeHtml4(Translate.translate(CS_MATCH_NOT_FOUND_FOR)) + " " + StringEscapeUtils.escapeHtml4(competitor1) + "/" + StringEscapeUtils.escapeHtml4(competitor2));
                }
            } else {
                // Enter Values
                for (int i = 0; i < Tournament.getTournament().getParams().getCriteriaCount(); i++) {
                    Criteria crit = Tournament.getTournament().getParams().getCriteria(i);

                    String crit1 = parms.get(crit.getName() + "1");
                    String crit2 = parms.get(crit.getName() + "2");

                    Value val = coachmatch.getValue(crit);
                    coachmatch.setRemotely(true);
                    int val1 = 0;
                    if (crit1 != null) {
                        try {
                            val1 = Integer.parseInt(crit1);
                        } catch (NumberFormatException npe) {
                            val1 = 0;
                        }
                    }
                    val.setValue1(val1);

                    int val2 = 0;
                    if (crit2 != null) {
                        try {
                            val2 = Integer.parseInt(crit2);
                        } catch (NumberFormatException npe) {
                            val2 = 0;
                        }
                    }
                    val.setValue2(val2);
                }
                
                MainFrame.getMainFrame().update();
            }

        }

        sb.append("<br>");

        // Print Summary
        if (valid) {
            sb.append(StringEscapeUtils.escapeHtml4(Translate.translate(Translate.CS_Round)) + " " + (Tournament.getTournament().getRoundIndex(r) + 1));
            sb.append("<table>");

            sb.append("<th>");
            sb.append("<td>");
            sb.append(StringEscapeUtils.escapeHtml4(coachmatch.getCompetitor1().getName()));
            sb.append("</td>");
            sb.append("<td>");
            sb.append(StringEscapeUtils.escapeHtml4(coachmatch.getCompetitor2().getName()));
            sb.append("</td>");
            sb.append("</th>");

            for (int i = 0; i < Tournament.getTournament().getParams().getCriteriaCount(); i++) {
                Criteria crit = Tournament.getTournament().getParams().getCriteria(i);
                Value v = coachmatch.getValue(crit);
                sb.append("<tr>");
                sb.append("<td>" + StringEscapeUtils.escapeHtml4(crit.getName()) + "</td>");

                sb.append("<td>" + Integer.toString(v.getValue1()) + "</td>");
                sb.append("<td>" + Integer.toString(v.getValue2()) + "</td>");
                sb.append("</tr>");
            }
            sb.append("</table>");
            sb.append("<br>");

        }

        sb.append("<center>");
        return sb.toString();
    }
}
