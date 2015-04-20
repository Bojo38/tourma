/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.utils;

import java.util.ArrayList;
import org.jdom2.Element;
import org.jdom2.output.XMLOutputter;
import tourma.data.Coach;
import tourma.data.Parameters;
import tourma.data.Ranking;
import tourma.data.RosterType;
import tourma.data.Round;
import tourma.data.Team;
import tourma.data.Tournament;
import tourma.tableModel.MjtRankingIndiv;
import tourma.tableModel.MjtRankingTeam;
import tourma.utility.StringConstants;

/**
 *
 * @author WFMJ7631
 */
public class TourmaProtocol {

    public enum TKey {

        INDIVIDUAL_RANK,
        TEAM_RANK,
        MATCHS,
        CLAN_RANK,
        ROLLING, RANK,
        LAST_ACTION,
        END,
        UNKNOWN
    };

    TKey getKey(String k) {
        switch (k) {
            case "INDIVIDUAL_RANK":
                return TKey.INDIVIDUAL_RANK;
            case "TEAM_RANK":
                return TKey.TEAM_RANK;
            case "MATCHS":
                return TKey.MATCHS;

            case "END":
                return TKey.END;
            default:
                return TKey.UNKNOWN;
        }
    }

    String processInput(Object object) {
        if (object == null) {
            return "";
        }
        Ranking r = null;
        Round round = null;
        Parameters params = null;
        if (object instanceof String) {
            TKey k = getKey((String) object);

            switch (k) {
                case INDIVIDUAL_RANK:
                    ArrayList<Coach> coachs = new ArrayList<>();
                    for (int i = 0; i < Tournament.getTournament().getCoachCount(); i++) {
                        coachs.add(Tournament.getTournament().getCoach(i));
                    }
                    r = new Ranking(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("INDIVIDUAL"),
                            java.util.ResourceBundle.getBundle("tourma/languages/language").getString("GENERAL"),
                            java.util.ResourceBundle.getBundle("tourma/languages/language").getString(""),
                            new MjtRankingIndiv(
                                    Tournament.getTournament().getRoundsCount() - 1,
                                    Tournament.getTournament().getParams().getRankingIndiv1(),
                                    Tournament.getTournament().getParams().getRankingIndiv2(),
                                    Tournament.getTournament().getParams().getRankingIndiv3(),
                                    Tournament.getTournament().getParams().getRankingIndiv4(),
                                    Tournament.getTournament().getParams().getRankingIndiv5(),
                                    coachs, false, false, Tournament.getTournament().getPoolCount() > 0),
                            Tournament.getTournament().getRankingTypes(false)
                    );
                    break;
                case TEAM_RANK:
                    ArrayList<Team> teams = new ArrayList<>();
                    for (int i = 0; i < Tournament.getTournament().getTeamsCount(); i++) {
                        teams.add(Tournament.getTournament().getTeam(i));
                    }
                    r = new Ranking(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("TEAM"),
                            java.util.ResourceBundle.getBundle("tourma/languages/language").getString("GENERAL"),
                            java.util.ResourceBundle.getBundle("tourma/languages/language").getString(""),
                            new MjtRankingTeam(
                                    Tournament.getTournament().getParams().isTeamVictoryOnly(),
                                    Tournament.getTournament().getRoundsCount() - 1,
                                    teams, false),
                            Tournament.getTournament().getRankingTypes(Tournament.getTournament().getParams().isTeamVictoryOnly())
                    );
                    break;
                case MATCHS:
                    params = Tournament.getTournament().getParams();
                    round = Tournament.getTournament().getRound(Tournament.getTournament().getRoundsCount() - 1);
                    break;
            }

            if (r != null) {
                Element element = r.getXMLElement();
                XMLOutputter outp = new XMLOutputter();
                return outp.outputString(element);
            }
            if ((round != null) && (params != null)) {
                XMLOutputter outp = new XMLOutputter();
                Element matchs=new Element("Matchs");
                for (int i = 0; i < RosterType.getRostersNamesCount(); i++) {
                    final Element ros = new Element(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("ROSTER"));
                    ros.setAttribute(StringConstants.CS_NAME, RosterType.getRostersName(i));
                    matchs.addContent(ros);
                }
                matchs.addContent(params.getXMLElement());                
                matchs.addContent(round.getXMLElementForDisplay());
                String buffer =  outp.outputString(matchs);
                return buffer;
            }
        } else {
            return "";
        }
        return "";
    }

}
