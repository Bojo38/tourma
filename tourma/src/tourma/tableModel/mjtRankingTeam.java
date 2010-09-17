/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.tableModel;

import tourma.data.ObjectRanking;
import tourma.data.Parameters;
import tourma.data.Round;
import tourma.data.Tournament;
import tourma.data.Match;
import tourma.data.Coach;
import java.util.Collections;
import java.util.Vector;
import tourma.data.Team;

/**
 *
 * @author Frederic Berger
 */
public class mjtRankingTeam extends mjtRanking {

    public mjtRankingTeam(Vector<Round> rounds, int ranking_type1, int ranking_type2, int ranking_type3, int ranking_type4, int ranking_type5, Vector<Team> teams) {
        super(rounds, ranking_type1, ranking_type2, ranking_type3, ranking_type4, ranking_type5, teams);
        sortDatas();
    }

    protected void sortDatas() {

        _datas.clear();
        if (Tournament.getTournament().getParams()._teamPairing == 1) {

            for (int k = 0; k < _objects.size(); k++) {
                Team teamref = (Team) _objects.get(k);
                Vector<Match> matchs = new Vector<Match>();
                int value1 = 0;
                int value2 = 0;
                int value3 = 0;
                int value4 = 0;
                int value5 = 0;
                for (int i = 0; i < _rounds.size(); i++) {
                    for (int j = 0; j < _rounds.get(i).getMatchs().size(); j++) {
                        Match m = _rounds.get(i).getMatchs().get(j);
                        if ((m._coach1._teamMates == teamref) || (m._coach2._teamMates == teamref)) {
                            matchs.add(m);
                        }
                    }
                    value1 += getValue(teamref, matchs, _ranking_type1, _rounds);
                    value2 += getValue(teamref, matchs, _ranking_type2, _rounds);
                    value3 += getValue(teamref, matchs, _ranking_type3, _rounds);
                    value4 += getValue(teamref, matchs, _ranking_type4, _rounds);
                    value5 += getValue(teamref, matchs, _ranking_type5, _rounds);
                }
                _datas.add(new ObjectRanking(teamref, value1, value2, value3, value4, value5));
            }
        } else {
            Vector<Match> matchs = new Vector<Match>();
            for (int i = 0; i < _rounds.size(); i++) {
                for (int j = 0; j < _rounds.get(i).getMatchs().size(); j++) {
                    matchs.add(_rounds.get(i).getMatchs().get(j));
                }
            }
            for (int i = 0; i < _objects.size(); i++) {
                Team t = (Team) _objects.get(i);
                int value1 = 0;
                int value2 = 0;
                int value3 = 0;
                int value4 = 0;
                int value5 = 0;
                for (int j = 0; j < matchs.size(); j++) {
                    Match m = matchs.get(j);
                    for (int k = 0; k < t._coachs.size(); k++) {
                        Coach c = t._coachs.get(k);
                        value1 += getValue(c, m, _ranking_type1, _rounds);
                        value2 += getValue(c, m, _ranking_type2, _rounds);
                        value3 += getValue(c, m, _ranking_type3, _rounds);
                        value4 += getValue(c, m, _ranking_type4, _rounds);
                        value5 += getValue(c, m, _ranking_type5, _rounds);
                    }
                }
                _datas.add(new ObjectRanking(t, value1, value2, value3, value4, value5));
            }
        }

        Collections.sort(_datas);
    }

    public int getColumnCount() {
        return 7;
    }

    public String getColumnName(int col) {
        switch (col) {
            case 0:
                return "N";
            case 1:
                return "Equipe";
            case 2:
                switch (_ranking_type1) {
                    case Parameters.C_RANKING_POINTS:
                        return "Points";
                    case Parameters.C_RANKING_DIFF_FOUL:
                        return "Diff aggr";
                    case Parameters.C_RANKING_DIFF_SOR:
                        return "Diff sor";
                    case Parameters.C_RANKING_DIFF_TD:
                        return "Diff tds";
                    case Parameters.C_RANKING_FOUL:
                        return "Nb aggr";
                    case Parameters.C_RANKING_NONE:
                        return "Rien";
                    case Parameters.C_RANKING_OPP_POINTS:
                        return "Pts adv";
                    case Parameters.C_RANKING_SOR:
                        return "Nb sor";
                    case Parameters.C_RANKING_TD:
                        return "Nb tds";
                    case Parameters.C_RANKING_VND:
                        return "V/N/D";
                    case Parameters.C_RANKING_PAS:
                        return "Passes";
                    case Parameters.C_RANKING_DIFF_PAS:
                        return "Diff Pas";
                    case Parameters.C_RANKING_INT:
                        return "Inter.";
                    case Parameters.C_RANKING_DIFF_INT:
                        return "Diff Int";
                }
                break;
            case 3:
                switch (_ranking_type2) {
                    case Parameters.C_RANKING_POINTS:
                        return "Points";
                    case Parameters.C_RANKING_DIFF_FOUL:
                        return "Diff aggr";
                    case Parameters.C_RANKING_DIFF_SOR:
                        return "Diff sor";
                    case Parameters.C_RANKING_DIFF_TD:
                        return "Diff tds";
                    case Parameters.C_RANKING_FOUL:
                        return "Nb aggr";
                    case Parameters.C_RANKING_NONE:
                        return "Rien";
                    case Parameters.C_RANKING_OPP_POINTS:
                        return "Pts adv";
                    case Parameters.C_RANKING_SOR:
                        return "Nb sor";
                    case Parameters.C_RANKING_TD:
                        return "Nb tds";
                    case Parameters.C_RANKING_VND:
                        return "V/N/D";
                    case Parameters.C_RANKING_PAS:
                        return "Passes";
                    case Parameters.C_RANKING_DIFF_PAS:
                        return "Diff Pas";
                    case Parameters.C_RANKING_INT:
                        return "Inter.";
                    case Parameters.C_RANKING_DIFF_INT:
                        return "Diff Int";
                }
                break;
            case 4:
                switch (_ranking_type3) {
                    case Parameters.C_RANKING_POINTS:
                        return "Points";
                    case Parameters.C_RANKING_DIFF_FOUL:
                        return "Diff aggr";
                    case Parameters.C_RANKING_DIFF_SOR:
                        return "Diff sor";
                    case Parameters.C_RANKING_DIFF_TD:
                        return "Diff tds";
                    case Parameters.C_RANKING_FOUL:
                        return "Nb aggr";
                    case Parameters.C_RANKING_NONE:
                        return "Rien";
                    case Parameters.C_RANKING_OPP_POINTS:
                        return "Pts adv";
                    case Parameters.C_RANKING_SOR:
                        return "Nb sor";
                    case Parameters.C_RANKING_TD:
                        return "Nb tds";
                    case Parameters.C_RANKING_VND:
                        return "V/N/D";
                    case Parameters.C_RANKING_PAS:
                        return "Passes";
                    case Parameters.C_RANKING_DIFF_PAS:
                        return "Diff Pas";
                    case Parameters.C_RANKING_INT:
                        return "Inter.";
                    case Parameters.C_RANKING_DIFF_INT:
                        return "Diff Int";
                }
                break;
            case 5:
                switch (_ranking_type4) {
                    case Parameters.C_RANKING_POINTS:
                        return "Points";
                    case Parameters.C_RANKING_DIFF_FOUL:
                        return "Diff aggr";
                    case Parameters.C_RANKING_DIFF_SOR:
                        return "Diff sor";
                    case Parameters.C_RANKING_DIFF_TD:
                        return "Diff tds";
                    case Parameters.C_RANKING_FOUL:
                        return "Nb aggr";
                    case Parameters.C_RANKING_NONE:
                        return "Rien";
                    case Parameters.C_RANKING_OPP_POINTS:
                        return "Pts adv";
                    case Parameters.C_RANKING_SOR:
                        return "Nb sor";
                    case Parameters.C_RANKING_TD:
                        return "Nb tds";
                    case Parameters.C_RANKING_VND:
                        return "V/N/D";
                    case Parameters.C_RANKING_PAS:
                        return "Passes";
                    case Parameters.C_RANKING_DIFF_PAS:
                        return "Diff Pas";
                    case Parameters.C_RANKING_INT:
                        return "Inter.";
                    case Parameters.C_RANKING_DIFF_INT:
                        return "Diff Int";
                }
                break;
            case 6:
                switch (_ranking_type5) {
                    case Parameters.C_RANKING_POINTS:
                        return "Points";
                    case Parameters.C_RANKING_DIFF_FOUL:
                        return "Diff aggr";
                    case Parameters.C_RANKING_DIFF_SOR:
                        return "Diff sor";
                    case Parameters.C_RANKING_DIFF_TD:
                        return "Diff tds";
                    case Parameters.C_RANKING_FOUL:
                        return "Nb aggr";
                    case Parameters.C_RANKING_NONE:
                        return "Rien";
                    case Parameters.C_RANKING_OPP_POINTS:
                        return "Pts adv";
                    case Parameters.C_RANKING_SOR:
                        return "Nb sor";
                    case Parameters.C_RANKING_TD:
                        return "Nb tds";
                    case Parameters.C_RANKING_VND:
                        return "V/N/D";
                    case Parameters.C_RANKING_PAS:
                        return "Passes";
                    case Parameters.C_RANKING_DIFF_PAS:
                        return "Diff Pas";
                    case Parameters.C_RANKING_INT:
                        return "Inter.";
                    case Parameters.C_RANKING_DIFF_INT:
                        return "Diff Int";
                }
                break;
        }
        return "";
    }

    public static int getTeamVND(Team t, Vector<Match> v) {
        int value = 0;

        int nbVictory = 0;
        int nbLost = 0;
        for (int i = 0; i < v.size(); i++) {
            Match m = v.get(i);
            if (m._coach1._teamMates == t) {
                if (m._td1 > m._td2) {
                    nbVictory++;
                } else {
                    if (m._td1 < m._td2) {
                        nbLost++;
                    }
                }
            }
            if (m._coach2._teamMates == t) {
                if (m._td1 < m._td2) {
                    nbVictory++;
                } else {
                    if (m._td1 > m._td2) {
                        nbLost++;
                    }
                }
            }
        }
        if (nbVictory > nbLost) {
            value += 1000000;
        } else {
            if (nbVictory < nbLost) {
                value += 1;
            } else {
                value += 1000;
            }
        }
        return value;
    }

    public static int getTeamPoints(Team t, Vector<Match> v) {
        int value = 0;

        int nbVictory = 0;
        int nbLost = 0;
        for (int i = 0; i < v.size(); i++) {
            Match m = v.get(i);
            if (m._coach1._teamMates == t) {
                if (m._td1 > m._td2) {
                    nbVictory++;
                } else {
                    if (m._td1 < m._td2) {
                        nbLost++;
                    }
                }
                value += m._td1 * Tournament.getTournament().getParams()._bonus_td_points_team + m._td2 * Tournament.getTournament().getParams()._bonus_neg_td_points_team;
                value += m._sor1 * Tournament.getTournament().getParams()._bonus_sor_points_team + m._sor2 * Tournament.getTournament().getParams()._bonus_neg_sor_points_team;
                value += m._foul1 * Tournament.getTournament().getParams()._bonus_foul_points_team + m._foul2 * Tournament.getTournament().getParams()._bonus_neg_foul_points_team;
            }
            if (m._coach2._teamMates == t) {
                if (m._td1 < m._td2) {
                    nbVictory++;
                } else {
                    if (m._td1 > m._td2) {
                        nbLost++;
                    }
                }
                value += m._td2 * Tournament.getTournament().getParams()._bonus_td_points_team + m._td1 * Tournament.getTournament().getParams()._bonus_neg_td_points_team;
                value += m._sor2 * Tournament.getTournament().getParams()._bonus_sor_points_team + m._sor1 * Tournament.getTournament().getParams()._bonus_neg_sor_points_team;
                value += m._foul2 * Tournament.getTournament().getParams()._bonus_foul_points_team + m._foul1 * Tournament.getTournament().getParams()._bonus_neg_foul_points_team;
            }
        }

        if (nbVictory > nbLost) {
            value += Tournament.getTournament().getParams()._victory_points_team;
        } else {
            if (nbVictory < nbLost) {
                value += Tournament.getTournament().getParams()._lost_points_team;
            } else {
                value += Tournament.getTournament().getParams()._draw_points_team;
            }
        }
        return value;
    }

    public static int getOppTeamPoints(Team t, Vector<Match> v, Vector<Round> vr) {
        int value = 0;
        Team opponent = null;

        if (v.size() > 0) {
            if (v.get(0)._coach1._teamMates == t) {
                opponent = v.get(0)._coach2._teamMates;
            } else {
                opponent = v.get(0)._coach1._teamMates;
            }

            for (int i = 0; i < vr.size(); i++) {
                Round r = vr.get(i);
                Vector<Match> v2 = r.getMatchs();
                Vector<Match> v_opp = new Vector<Match>();
                for (int j = 0; j < v2.size(); j++) {
                    Match m = v2.get(j);
                    if ((m._coach1._teamMates == opponent) || (m._coach2._teamMates == opponent)) {
                        v_opp.add(m);
                    }
                }
                value += getTeamPoints(t, v_opp);
            }
        }
        return value;
    }

    public static int getValue(Team t, Vector<Match> v, int valueType, Vector<Round> rounds) {
        int value = 0;
        switch (valueType) {
            case Parameters.C_RANKING_POINTS:
                if (Tournament.getTournament().getParams()._team_victory_only) {
                    value = getTeamPoints(t, v);
                } else {
                    for (int i = 0; i < t._coachs.size(); i++) {
                        for (int j = 0; j < v.size(); j++) {
                            value += getPointsByCoach(t._coachs.get(i), v.get(j));
                        }
                    }
                    if (getTeamVND(t, v) >= 1000000) {
                        value += Tournament.getTournament().getParams()._team_victory_points;
                    }
                }
                break;
            case Parameters.C_RANKING_DIFF_FOUL:
                for (int i = 0; i < t._coachs.size(); i++) {
                    for (int j = 0; j < v.size(); j++) {
                        value += getDiffFoulByCoach(t._coachs.get(i), v.get(j));
                    }
                }
                break;
            case Parameters.C_RANKING_DIFF_SOR:
                for (int i = 0; i < t._coachs.size(); i++) {
                    for (int j = 0; j < v.size(); j++) {
                        value += getDiffSorByCoach(t._coachs.get(i), v.get(j));
                    }
                }
                break;
            case Parameters.C_RANKING_DIFF_TD:
                for (int i = 0; i < t._coachs.size(); i++) {
                    for (int j = 0; j < v.size(); j++) {
                        value += getDiffTdByCoach(t._coachs.get(i), v.get(j));
                    }
                }
                break;
            case Parameters.C_RANKING_DIFF_INT:
                for (int i = 0; i < t._coachs.size(); i++) {
                    for (int j = 0; j < v.size(); j++) {
                        value += getDiffIntByCoach(t._coachs.get(i), v.get(j));
                    }
                }
                break;
            case Parameters.C_RANKING_DIFF_PAS:
                for (int i = 0; i < t._coachs.size(); i++) {
                    for (int j = 0; j < v.size(); j++) {
                        value += getDiffPasByCoach(t._coachs.get(i), v.get(j));
                    }
                }
                break;
            case Parameters.C_RANKING_FOUL:
                for (int i = 0; i < t._coachs.size(); i++) {
                    for (int j = 0; j < v.size(); j++) {
                        value += getFoulByCoach(t._coachs.get(i), v.get(j));
                    }
                }
                break;
            case Parameters.C_RANKING_NONE:
                value = 0;
                break;
            case Parameters.C_RANKING_OPP_POINTS:
                if (Tournament.getTournament().getParams()._team_victory_only) {
                    value = getOppTeamPoints(t, v, rounds);
                } else {
                    for (int i = 0; i < t._coachs.size(); i++) {
                        for (int j = 0; j < v.size(); j++) {
                            value += getOppPointsByCoach(t._coachs.get(i), v.get(j), rounds);
                        }
                    }
                    if (getTeamVND(t, v) < 1000000) {
                        value += Tournament.getTournament().getParams()._team_victory_points;
                    }
                }
                break;
            case Parameters.C_RANKING_SOR:
                for (int i = 0; i < t._coachs.size(); i++) {
                    for (int j = 0; j < v.size(); j++) {
                        value += getSorByCoach(t._coachs.get(i), v.get(j));
                    }
                }
                break;
            case Parameters.C_RANKING_PAS:
                for (int i = 0; i < t._coachs.size(); i++) {
                    for (int j = 0; j < v.size(); j++) {
                        value += getPasByCoach(t._coachs.get(i), v.get(j));
                    }
                }
                break;
            case Parameters.C_RANKING_INT:
                for (int i = 0; i < t._coachs.size(); i++) {
                    for (int j = 0; j < v.size(); j++) {
                        value += getIntByCoach(t._coachs.get(i), v.get(j));
                    }
                }
                break;
            case Parameters.C_RANKING_TD:
                for (int i = 0; i < t._coachs.size(); i++) {
                    for (int j = 0; j < v.size(); j++) {
                        value += getTdByCoach(t._coachs.get(i), v.get(j));
                    }
                }
                break;
            case Parameters.C_RANKING_VND:
                if (Tournament.getTournament().getParams()._team_victory_only) {
                    value = getTeamVND(t, v);
                } else {
                    for (int i = 0; i < t._coachs.size(); i++) {
                        for (int j = 0; j < v.size(); j++) {
                            value += getVNDByCoach(t._coachs.get(i), v.get(j));
                        }
                    }
                }
                break;
        }
        return value;
    }

    public Object getValueAt(int row, int col) {

        ObjectRanking obj = (ObjectRanking) _datas.get(row);
        switch (col) {
            case 0:
                return row + 1;
            case 1:
                return ((Team) obj.getObject())._name;
            case 2:
                return obj.getValue1();
            case 3:
                return obj.getValue2();
            case 4:
                return obj.getValue3();
            case 5:
                return obj.getValue4();
            case 6:
                return obj.getValue5();
        }
        return "";
    }
}
