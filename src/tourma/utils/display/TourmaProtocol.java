/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.utils.display;

import java.rmi.RemoteException;
import java.util.ArrayList;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;
import tourma.data.Category;
import tourma.data.Clan;
import tourma.data.Coach;
import tourma.data.Criteria;
import tourma.data.Group;
import tourma.data.Parameters;
import tourma.data.Pool;
import tourma.data.Ranking;
import tourma.data.RosterType;
import tourma.data.Round;
import tourma.data.Team;
import tourma.data.Tournament;
import tourma.languages.Translate;
import tourma.tableModel.MjtAnnexRankClan;
import tourma.tableModel.MjtAnnexRankIndiv;
import tourma.tableModel.MjtAnnexRankTeam;
import tourma.tableModel.MjtRankingClan;
import tourma.tableModel.MjtRankingIndiv;
import tourma.tableModel.MjtRankingTeam;
import tourma.utility.StringConstants;
import tourma.data.IXMLExport;

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
        INDIVIDUAL_ANNEX,
        TEAM_ANNEX,
        CLAN_ANNEX,
        GROUP_RANK,
        CATEGORY_RANK,
        GROUP_ANNEX,
        CATEGORY_ANNEX,
        POOL_INDIV_RANK,
        POOL_TEAM_RANK,
        POOL_INDIV_ANNEX,
        POOL_TEAM_ANNEX,
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
            case "CLAN_RANK":
                return TKey.CLAN_RANK;
            case "INDIVIDUAL_ANNEX":
                return TKey.INDIVIDUAL_ANNEX;
            case "TEAM_ANNEX":
                return TKey.TEAM_ANNEX;
            case "CLAN_ANNEX":
                return TKey.CLAN_ANNEX;
            case "GROUP_RANK":
                return TKey.GROUP_RANK;
            case "CATEGORY_RANK":
                return TKey.CATEGORY_RANK;
            case "GROUP_ANNEX":
                return TKey.GROUP_ANNEX;
            case "CATEGORY_ANNEX":
                return TKey.CATEGORY_ANNEX;
            case "POOL_INDIV_RANK":
                return TKey.POOL_INDIV_RANK;
            case "POOL_TEAM_RANK":
                return TKey.POOL_TEAM_RANK;
            case "POOL_INDIV_ANNEX":
                return TKey.POOL_INDIV_ANNEX;
            case "POOL_TEAM_ANNEX":
                return TKey.POOL_TEAM_ANNEX;
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
        ArrayList array = null;
        Ranking r = null;
        Round round = null;
        Parameters params = null;
        if (object instanceof String) {
            TKey k = getKey((String) object);
            try {
                switch (k) {
                    case INDIVIDUAL_RANK: {
                        ArrayList<Coach> coachs = new ArrayList<>();
                        for (int i = 0; i < Tournament.getTournament().getCoachsCount(); i++) {
                            coachs.add(Tournament.getTournament().getCoach(i));
                        }
                        r = new Ranking(Ranking.CS_Individual,
                                Ranking.CS_General,
                                StringConstants.CS_NULL,
                                new MjtRankingIndiv(
                                        Tournament.getTournament().getRoundsCount() - 1,
                                        Tournament.getTournament().getParams().getRankingIndiv1(),
                                        Tournament.getTournament().getParams().getRankingIndiv2(),
                                        Tournament.getTournament().getParams().getRankingIndiv3(),
                                        Tournament.getTournament().getParams().getRankingIndiv4(),
                                        Tournament.getTournament().getParams().getRankingIndiv5(),
                                        coachs, false, false, Tournament.getTournament().getPoolCount() > 0,
                                Tournament.getTournament().getRound(Tournament.getTournament().getRoundsCount() - 1).isCup()),
                                Tournament.getTournament().getRankingTypes(false)
                        );
                    }
                    break;
                    case TEAM_RANK: {
                        ArrayList<Team> teams = new ArrayList<>();
                        for (int i = 0; i < Tournament.getTournament().getTeamsCount(); i++) {
                            teams.add(Tournament.getTournament().getTeam(i));
                        }
                        r = new Ranking(Ranking.CS_Team,
                                Ranking.CS_General,
                                StringConstants.CS_NULL,
                                new MjtRankingTeam(
                                        Tournament.getTournament().getParams().isTeamVictoryOnly(),
                                        Tournament.getTournament().getRoundsCount() - 1,
                                        teams, false),
                                Tournament.getTournament().getRankingTypes(Tournament.getTournament().getParams().isTeamVictoryOnly())
                        );
                    }
                    break;
                    case MATCHS:
                        params = Tournament.getTournament().getParams();
                        round = Tournament.getTournament().getRound(Tournament.getTournament().getRoundsCount() - 1);
                        break;
                    case CLAN_RANK: {
                        ArrayList<Clan> clans = new ArrayList<>();
                        for (int i = 0; i < Tournament.getTournament().getClansCount(); i++) {
                            clans.add(Tournament.getTournament().getClan(i));
                        }
                        r = new Ranking(Ranking.CS_Clan,
                                Ranking.CS_General,
                                StringConstants.CS_NULL,
                                new MjtRankingClan(
                                        Tournament.getTournament().getRoundsCount() - 1,
                                        clans, false),
                                Tournament.getTournament().getRankingTypes(Tournament.getTournament().getParams().isTeamVictoryOnly())
                        );
                    }
                    break;
                    case INDIVIDUAL_ANNEX: {
                        ArrayList<Coach> coachs = new ArrayList<>();
                        for (int i = 0; i < Tournament.getTournament().getCoachsCount(); i++) {
                            coachs.add(Tournament.getTournament().getCoach(i));
                        }
                        array = new ArrayList();
                        for (int i = 0; i < Tournament.getTournament().getParams().getCriteriaCount(); i++) {
                            Criteria crit = Tournament.getTournament().getParams().getCriteria(i);
                            r = new Ranking(Ranking.CS_Individual_Annex,
                                    crit.getName(),
                                    Ranking.CS_Positive,
                                    new MjtAnnexRankIndiv(
                                            Tournament.getTournament().getRoundsCount() - 1, crit, Parameters.C_RANKING_SUBTYPE_POSITIVE,
                                            coachs, true,
                                            Tournament.getTournament().getParams().getRankingIndiv1(),
                                            Tournament.getTournament().getParams().getRankingIndiv2(),
                                            Tournament.getTournament().getParams().getRankingIndiv3(),
                                            Tournament.getTournament().getParams().getRankingIndiv4(),
                                            Tournament.getTournament().getParams().getRankingIndiv5(),
                                            Tournament.getTournament().getParams().isTeamTournament(),
                                            false),
                                    Tournament.getTournament().getRankingTypes(false)
                            );
                            r.setCriteria(crit);
                            array.add(r);

                            r = new Ranking(Ranking.CS_Individual_Annex,
                                    crit.getName(),
                                    Ranking.CS_Negative,
                                    new MjtAnnexRankIndiv(
                                            Tournament.getTournament().getRoundsCount() - 1, crit, Parameters.C_RANKING_SUBTYPE_NEGATIVE,
                                            coachs, true,
                                            Tournament.getTournament().getParams().getRankingIndiv1(),
                                            Tournament.getTournament().getParams().getRankingIndiv2(),
                                            Tournament.getTournament().getParams().getRankingIndiv3(),
                                            Tournament.getTournament().getParams().getRankingIndiv4(),
                                            Tournament.getTournament().getParams().getRankingIndiv5(),
                                            Tournament.getTournament().getParams().isTeamTournament(),
                                            false),
                                    Tournament.getTournament().getRankingTypes(false)
                            );
                            r.setCriteria(crit);
                            array.add(r);

                            r = new Ranking(Ranking.CS_Individual_Annex,
                                    crit.getName(),
                                    Translate.CS_Difference,
                                    new MjtAnnexRankIndiv(
                                            Tournament.getTournament().getRoundsCount() - 1, crit, Parameters.C_RANKING_SUBTYPE_DIFFERENCE,
                                            coachs, true,
                                            Tournament.getTournament().getParams().getRankingIndiv1(),
                                            Tournament.getTournament().getParams().getRankingIndiv2(),
                                            Tournament.getTournament().getParams().getRankingIndiv3(),
                                            Tournament.getTournament().getParams().getRankingIndiv4(),
                                            Tournament.getTournament().getParams().getRankingIndiv5(),
                                            Tournament.getTournament().getParams().isTeamTournament(),
                                            false),
                                    Tournament.getTournament().getRankingTypes(false)
                            );
                            r.setCriteria(crit);
                            array.add(r);
                        }
                    }
                    break;
                    case TEAM_ANNEX: {
                        ArrayList<Team> teams = new ArrayList<>();
                        for (int i = 0; i < Tournament.getTournament().getTeamsCount(); i++) {
                            teams.add(Tournament.getTournament().getTeam(i));
                        }
                        array = new ArrayList();
                        for (int i = 0; i < Tournament.getTournament().getParams().getCriteriaCount(); i++) {
                            Criteria crit = Tournament.getTournament().getParams().getCriteria(i);
                            r = new Ranking(Ranking.CS_Team_Annex,
                                    crit.getName(),
                                    Ranking.CS_Positive,
                                    new MjtAnnexRankTeam(
                                            Tournament.getTournament().getRoundsCount() - 1, crit, Parameters.C_RANKING_SUBTYPE_POSITIVE,
                                            teams, true,
                                            Tournament.getTournament().getParams().getRankingTeam1(),
                                            Tournament.getTournament().getParams().getRankingTeam2(),
                                            Tournament.getTournament().getParams().getRankingTeam3(),
                                            Tournament.getTournament().getParams().getRankingTeam4(),
                                            Tournament.getTournament().getParams().getRankingTeam5(),
                                            false),
                                    Tournament.getTournament().getRankingTypes(false)
                            );
                            r.setCriteria(crit);
                            array.add(r);

                            r = new Ranking(Ranking.CS_Team_Annex,
                                    crit.getName(),
                                    Ranking.CS_Negative,
                                    new MjtAnnexRankTeam(
                                            Tournament.getTournament().getRoundsCount() - 1, crit, Parameters.C_RANKING_SUBTYPE_NEGATIVE,
                                            teams, true,
                                            Tournament.getTournament().getParams().getRankingTeam1(),
                                            Tournament.getTournament().getParams().getRankingTeam2(),
                                            Tournament.getTournament().getParams().getRankingTeam3(),
                                            Tournament.getTournament().getParams().getRankingTeam4(),
                                            Tournament.getTournament().getParams().getRankingTeam5(),
                                            false),
                                    Tournament.getTournament().getRankingTypes(false)
                            );
                            r.setCriteria(crit);
                            array.add(r);

                            r = new Ranking(Ranking.CS_Team_Annex,
                                    crit.getName(),
                                    Translate.CS_Difference,
                                    new MjtAnnexRankTeam(
                                            Tournament.getTournament().getRoundsCount() - 1, crit, Parameters.C_RANKING_SUBTYPE_DIFFERENCE,
                                            teams, true,
                                            Tournament.getTournament().getParams().getRankingTeam1(),
                                            Tournament.getTournament().getParams().getRankingTeam2(),
                                            Tournament.getTournament().getParams().getRankingTeam3(),
                                            Tournament.getTournament().getParams().getRankingTeam4(),
                                            Tournament.getTournament().getParams().getRankingTeam5(),
                                            false),
                                    Tournament.getTournament().getRankingTypes(false)
                            );
                            r.setCriteria(crit);
                            array.add(r);
                        }
                    }
                    break;
                    case CLAN_ANNEX: {
                        ArrayList<Clan> clans = new ArrayList<>();
                        for (int i = 0; i < Tournament.getTournament().getClansCount(); i++) {
                            clans.add(Tournament.getTournament().getClan(i));
                        }
                        array = new ArrayList();
                        for (int i = 0; i < Tournament.getTournament().getParams().getCriteriaCount(); i++) {
                            Criteria crit = Tournament.getTournament().getParams().getCriteria(i);
                            r = new Ranking(Ranking.CS_Clan_Annex,
                                    crit.getName(),
                                    Ranking.CS_Positive,
                                    new MjtAnnexRankClan(
                                            Tournament.getTournament().getRoundsCount() - 1, crit, Parameters.C_RANKING_SUBTYPE_POSITIVE,
                                            true, clans, false),
                                    Tournament.getTournament().getRankingTypes(false)
                            );
                            r.setCriteria(crit);
                            array.add(r);

                            r = new Ranking(Ranking.CS_Team_Annex,
                                    crit.getName(),
                                    Ranking.CS_Negative,
                                    new MjtAnnexRankClan(
                                            Tournament.getTournament().getRoundsCount() - 1, crit, Parameters.C_RANKING_SUBTYPE_NEGATIVE,
                                            true, clans, false),
                                    Tournament.getTournament().getRankingTypes(false)
                            );
                            r.setCriteria(crit);
                            array.add(r);

                            r = new Ranking(Ranking.CS_Team_Annex,
                                    crit.getName(),
                                    Translate.CS_Difference,
                                    new MjtAnnexRankClan(
                                            Tournament.getTournament().getRoundsCount() - 1, crit, Parameters.C_RANKING_SUBTYPE_DIFFERENCE,
                                            true, clans, false),
                                    Tournament.getTournament().getRankingTypes(false)
                            );
                            r.setCriteria(crit);
                            array.add(r);
                        }
                    }
                    break;
                    case GROUP_RANK: {
                        array = new ArrayList<>();
                        for (int cpt = 0; cpt < Tournament.getTournament().getGroupsCount(); cpt++) {
                            Group g = Tournament.getTournament().getGroup(cpt);

                            ArrayList<Coach> coachs = new ArrayList<>();
                            for (int i = 0; i < Tournament.getTournament().getCoachsCount(); i++) {
                                Coach coach = Tournament.getTournament().getCoach(i);
                                if (g.containsRoster(coach.getRoster())) {
                                    coachs.add(coach);
                                }
                            }
                            r = new Ranking(Ranking.CS_Individual,
                                    g.getName(),
                                    StringConstants.CS_NULL,
                                    new MjtRankingIndiv(
                                            Tournament.getTournament().getRoundsCount() - 1,
                                            Tournament.getTournament().getParams().getRankingIndiv1(),
                                            Tournament.getTournament().getParams().getRankingIndiv2(),
                                            Tournament.getTournament().getParams().getRankingIndiv3(),
                                            Tournament.getTournament().getParams().getRankingIndiv4(),
                                            Tournament.getTournament().getParams().getRankingIndiv5(),
                                            coachs, false, false, Tournament.getTournament().getPoolCount() > 0,
                                    Tournament.getTournament().getRound(Tournament.getTournament().getRoundsCount() - 1).isCup()),
                                    Tournament.getTournament().getRankingTypes(false)
                            );
                            array.add(r);
                        }
                    }
                    break;
                    case CATEGORY_RANK: {
                        array = new ArrayList<>();
                        for (int cpt = 0; cpt < Tournament.getTournament().getCategoriesCount(); cpt++) {
                            Category cat = Tournament.getTournament().getCategory(cpt);

                            ArrayList<Coach> coachs = new ArrayList<>();
                            for (int i = 0; i < Tournament.getTournament().getCoachsCount(); i++) {
                                Coach coach = Tournament.getTournament().getCoach(i);
                                if (coach.containsCategory(cat)) {
                                    coachs.add(coach);
                                }
                            }
                            r = new Ranking(Ranking.CS_Individual,
                                    cat.getName(),
                                    StringConstants.CS_NULL,
                                    new MjtRankingIndiv(
                                            Tournament.getTournament().getRoundsCount() - 1,
                                            Tournament.getTournament().getParams().getRankingIndiv1(),
                                            Tournament.getTournament().getParams().getRankingIndiv2(),
                                            Tournament.getTournament().getParams().getRankingIndiv3(),
                                            Tournament.getTournament().getParams().getRankingIndiv4(),
                                            Tournament.getTournament().getParams().getRankingIndiv5(),
                                            coachs, false, false, Tournament.getTournament().getPoolCount() > 0,
                                    Tournament.getTournament().getRound(Tournament.getTournament().getRoundsCount() - 1).isCup()),
                                    
                                    Tournament.getTournament().getRankingTypes(false)
                            );
                            array.add(r);
                        }
                    }
                    break;
                    case GROUP_ANNEX: {
                        array = new ArrayList<>();
                        for (int cpt = 0; cpt < Tournament.getTournament().getGroupsCount(); cpt++) {
                            Group g = Tournament.getTournament().getGroup(cpt);

                            ArrayList<Coach> coachs = new ArrayList<>();
                            for (int i = 0; i < Tournament.getTournament().getCoachsCount(); i++) {
                                Coach coach = Tournament.getTournament().getCoach(i);
                                if (g.containsRoster(coach.getRoster())) {
                                    coachs.add(coach);
                                }
                            }
                            for (int i = 0; i < Tournament.getTournament().getParams().getCriteriaCount(); i++) {
                                Criteria crit = Tournament.getTournament().getParams().getCriteria(i);
                                r = new Ranking(
                                        Ranking.CS_Individual_Annex,
                                        crit.getName() + "-" + g.getName(),
                                        Ranking.CS_Positive,
                                        new MjtAnnexRankIndiv(
                                                Tournament.getTournament().getRoundsCount() - 1, crit, Parameters.C_RANKING_SUBTYPE_POSITIVE,
                                                coachs, true,
                                                Tournament.getTournament().getParams().getRankingIndiv1(),
                                                Tournament.getTournament().getParams().getRankingIndiv2(),
                                                Tournament.getTournament().getParams().getRankingIndiv3(),
                                                Tournament.getTournament().getParams().getRankingIndiv4(),
                                                Tournament.getTournament().getParams().getRankingIndiv5(),
                                                Tournament.getTournament().getParams().isTeamTournament(),
                                                false),
                                        Tournament.getTournament().getRankingTypes(false)
                                );
                                r.setCriteria(crit);
                                array.add(r);

                                r = new Ranking(
                                        Ranking.CS_Individual_Annex,
                                        crit.getName() + "-" + g.getName(),
                                        Ranking.CS_Negative,
                                        new MjtAnnexRankIndiv(
                                                Tournament.getTournament().getRoundsCount() - 1, crit, Parameters.C_RANKING_SUBTYPE_NEGATIVE,
                                                coachs, true,
                                                Tournament.getTournament().getParams().getRankingIndiv1(),
                                                Tournament.getTournament().getParams().getRankingIndiv2(),
                                                Tournament.getTournament().getParams().getRankingIndiv3(),
                                                Tournament.getTournament().getParams().getRankingIndiv4(),
                                                Tournament.getTournament().getParams().getRankingIndiv5(),
                                                Tournament.getTournament().getParams().isTeamTournament(),
                                                false),
                                        Tournament.getTournament().getRankingTypes(false)
                                );
                                r.setCriteria(crit);
                                array.add(r);

                                r = new Ranking(
                                        Ranking.CS_Individual_Annex,
                                        crit.getName() + "-" + g.getName(),
                                        Translate.CS_Difference,
                                        new MjtAnnexRankIndiv(
                                                Tournament.getTournament().getRoundsCount() - 1, crit, Parameters.C_RANKING_SUBTYPE_DIFFERENCE,
                                                coachs, true,
                                                Tournament.getTournament().getParams().getRankingIndiv1(),
                                                Tournament.getTournament().getParams().getRankingIndiv2(),
                                                Tournament.getTournament().getParams().getRankingIndiv3(),
                                                Tournament.getTournament().getParams().getRankingIndiv4(),
                                                Tournament.getTournament().getParams().getRankingIndiv5(),
                                                Tournament.getTournament().getParams().isTeamTournament(),
                                                false),
                                        Tournament.getTournament().getRankingTypes(false)
                                );
                                r.setCriteria(crit);
                                array.add(r);
                            }
                        }
                    }
                    break;
                    case CATEGORY_ANNEX: {
                        array = new ArrayList<>();
                        for (int cpt = 0; cpt < Tournament.getTournament().getCategoriesCount(); cpt++) {
                            Category cat = Tournament.getTournament().getCategory(cpt);

                            ArrayList<Coach> coachs = new ArrayList<>();
                            for (int i = 0; i < Tournament.getTournament().getCoachsCount(); i++) {
                                Coach coach = Tournament.getTournament().getCoach(i);
                                if (coach.containsCategory(cat)) {
                                    coachs.add(coach);
                                }
                            }
                            for (int i = 0; i < Tournament.getTournament().getParams().getCriteriaCount(); i++) {
                                Criteria crit = Tournament.getTournament().getParams().getCriteria(i);
                                r = new Ranking(
                                        Ranking.CS_Individual_Annex,
                                        crit.getName() + "-" + cat.getName(),
                                        Ranking.CS_Positive,
                                        new MjtAnnexRankIndiv(
                                                Tournament.getTournament().getRoundsCount() - 1, crit, Parameters.C_RANKING_SUBTYPE_POSITIVE,
                                                coachs, true,
                                                Tournament.getTournament().getParams().getRankingIndiv1(),
                                                Tournament.getTournament().getParams().getRankingIndiv2(),
                                                Tournament.getTournament().getParams().getRankingIndiv3(),
                                                Tournament.getTournament().getParams().getRankingIndiv4(),
                                                Tournament.getTournament().getParams().getRankingIndiv5(),
                                                Tournament.getTournament().getParams().isTeamTournament(),
                                                false),
                                        Tournament.getTournament().getRankingTypes(false)
                                );
                                r.setCriteria(crit);
                                array.add(r);

                                r = new Ranking(
                                        Ranking.CS_Individual_Annex,
                                        crit.getName() + "-" + cat.getName(),
                                        Ranking.CS_Negative,
                                        new MjtAnnexRankIndiv(
                                                Tournament.getTournament().getRoundsCount() - 1, crit, Parameters.C_RANKING_SUBTYPE_NEGATIVE,
                                                coachs, true,
                                                Tournament.getTournament().getParams().getRankingIndiv1(),
                                                Tournament.getTournament().getParams().getRankingIndiv2(),
                                                Tournament.getTournament().getParams().getRankingIndiv3(),
                                                Tournament.getTournament().getParams().getRankingIndiv4(),
                                                Tournament.getTournament().getParams().getRankingIndiv5(),
                                                Tournament.getTournament().getParams().isTeamTournament(),
                                                false),
                                        Tournament.getTournament().getRankingTypes(false)
                                );
                                r.setCriteria(crit);
                                array.add(r);

                                r = new Ranking(
                                        Ranking.CS_Individual_Annex,
                                        crit.getName() + "-" + cat.getName(),
                                        Translate.CS_Difference,
                                        new MjtAnnexRankIndiv(
                                                Tournament.getTournament().getRoundsCount() - 1, crit, Parameters.C_RANKING_SUBTYPE_DIFFERENCE,
                                                coachs, true,
                                                Tournament.getTournament().getParams().getRankingIndiv1(),
                                                Tournament.getTournament().getParams().getRankingIndiv2(),
                                                Tournament.getTournament().getParams().getRankingIndiv3(),
                                                Tournament.getTournament().getParams().getRankingIndiv4(),
                                                Tournament.getTournament().getParams().getRankingIndiv5(),
                                                Tournament.getTournament().getParams().isTeamTournament(),
                                                false),
                                        Tournament.getTournament().getRankingTypes(false)
                                );
                                r.setCriteria(crit);
                                array.add(r);
                            }
                        }
                    }
                    break;
                    case POOL_INDIV_RANK: {
                        array = new ArrayList<>();
                        for (int cpt = 0; cpt < Tournament.getTournament().getPoolCount(); cpt++) {
                            Pool p = Tournament.getTournament().getPool(cpt);

                            r = new Ranking(
                                    Ranking.CS_Individual,
                                    Integer.toString(cpt + 1),
                                    StringConstants.CS_NULL,
                                    new MjtRankingIndiv(
                                            Tournament.getTournament().getRoundsCount() - 1,
                                            Tournament.getTournament().getParams().getRankingIndiv1(),
                                            Tournament.getTournament().getParams().getRankingIndiv2(),
                                            Tournament.getTournament().getParams().getRankingIndiv3(),
                                            Tournament.getTournament().getParams().getRankingIndiv4(),
                                            Tournament.getTournament().getParams().getRankingIndiv5(),
                                            p.getCompetitors(), false, false, Tournament.getTournament().getPoolCount() > 0,
                                    Tournament.getTournament().getRound(Tournament.getTournament().getRoundsCount() - 1).isCup()),
                                    Tournament.getTournament().getRankingTypes(false)
                            );
                            array.add(r);
                        }
                    }
                    break;
                    case POOL_TEAM_RANK: {
                        array = new ArrayList<>();
                        for (int cpt = 0; cpt < Tournament.getTournament().getPoolCount(); cpt++) {
                            Pool p = Tournament.getTournament().getPool(cpt);

                            r = new Ranking(
                                    Translate.CS_Team,
                                    Integer.toString(cpt + 1),
                                    StringConstants.CS_NULL,
                                    new MjtRankingTeam(
                                            Tournament.getTournament().getParams().isTeamVictoryOnly(),
                                            Tournament.getTournament().getRoundsCount() - 1,
                                            p.getCompetitors(), false),
                                    Tournament.getTournament().getRankingTypes(Tournament.getTournament().getParams().isTeamVictoryOnly())
                            );
                            array.add(r);
                        }
                    }
                    break;
                }

                if (array != null) {
                    Element main = new Element(Ranking.CS_array);
                    for (int i = 0; i < array.size(); i++) {
                        Element element = ((IXMLExport) array.get(i)).getXMLElement();
                        main.addContent(element);
                    }
                    XMLOutputter outp = new XMLOutputter();
                    return outp.outputString(main);
                }

                if (r != null) {
                    Element element = r.getXMLElement();
                    XMLOutputter outp = new XMLOutputter();
                    return outp.outputString(element);
                }
                if ((round != null) && (params != null)) {
                    XMLOutputter outp = new XMLOutputter();
                    Element matchs = new Element(TourmaProtocol.TKey.MATCHS.toString());
                    for (int i = 0; i < RosterType.getRostersNamesCount(); i++) {
                        final Element ros = new Element(
                                StringConstants.CS_ROSTER);
                        ros.setAttribute(StringConstants.CS_NAME, RosterType.getRostersName(i));
                        matchs.addContent(ros);
                    }
                    matchs.addContent(params.getXMLElement());
                    matchs.addContent(round.getXMLElementForDisplay());
                    String buffer = outp.outputString(matchs);
                    return buffer;
                }
            } catch (RemoteException re) {
                re.printStackTrace();
            }
        } else {
            return StringConstants.CS_NULL;
        }
        return StringConstants.CS_NULL;
    }

}
