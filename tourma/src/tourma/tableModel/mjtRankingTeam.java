/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.tableModel;

import tourma.data.ObjectRanking;
import tourma.data.Parameters;
import tourma.data.Value;
import tourma.data.Round;
import tourma.data.Tournament;
import tourma.data.Match;
import tourma.data.Coach;
import java.util.Collections;
import java.util.Vector;
import tourma.data.Criteria;
import tourma.data.Pool;
import tourma.data.Team;

/**
 *
 * @author Frederic Berger
 */
public class mjtRankingTeam extends mjtRanking {

    boolean _teamVictory;
    boolean _round_only = false;

    public mjtRankingTeam(boolean teamVictory, int round, int ranking_type1, int ranking_type2, int ranking_type3, int ranking_type4, int ranking_type5, Vector<Team> teams, boolean round_only) {
        super(round, ranking_type1, ranking_type2, ranking_type3, ranking_type4, ranking_type5, teams);
        _teamVictory = teamVictory;
        _round_only = round_only;
        sortDatas();
    }

    int getPointsByTeam(Team t) {

        int value = 0;
        int countTeamVictories = 0;
        int countTeamLoss = 0;
        int countTeamDraw = 0;

        int i = 0;
        if (_round_only) {
            i = _round;
        }

        while (i <= _round) {
            //for (int i = 0; i <= _round; i++) {
            int victories = 0;
            int draw = 0;
            int loss = 0;

            for (int j = 0; j < t._coachs.size(); j++) {
                Coach c = t._coachs.get(j);
                if (c._matchs.size() > i) {
                    Match m = c._matchs.get(i);
                    Criteria crit = Tournament.getTournament().getParams()._criterias.get(0);
                    Value val = m._values.get(crit);
                    if (m._coach1 == c) {
                        if (val._value1 > val._value2) {
                            victories++;
                        } else {
                            if (val._value1 < val._value2) {
                                loss++;
                            } else {
                                draw++;
                            }
                        }
                        for (int k = 0; k < Tournament.getTournament().getParams()._criterias.size(); k++) {
                            Criteria criteria = Tournament.getTournament().getParams()._criterias.get(k);
                            value += Math.max(m._values.get(criteria)._value1, 0) * criteria._pointsTeamFor;
                            value += Math.max(m._values.get(criteria)._value2, 0) * criteria._pointsTeamAgainst;
                        }

                    } else {
                        if (val._value1 < val._value2) {
                            victories++;
                        } else {
                            if (val._value1 > val._value2) {
                                loss++;
                            } else {
                                draw++;
                            }
                        }
                        for (int k = 0; k < Tournament.getTournament().getParams()._criterias.size(); k++) {
                            Criteria criteria = Tournament.getTournament().getParams()._criterias.get(k);
                            value += Math.max(m._values.get(criteria)._value2, 0) * criteria._pointsTeamFor;
                            value += Math.max(m._values.get(criteria)._value1, 0) * criteria._pointsTeamAgainst;
                        }
                    }
                }
            }
            if (victories > loss) {
                countTeamVictories++;
            } else {
                if (victories < loss) {
                    countTeamLoss++;
                } else {
                    countTeamDraw++;
                }
            }
            i++;
        }

        value += countTeamVictories * Tournament.getTournament().getParams()._victory_points_team;
        value += countTeamLoss * Tournament.getTournament().getParams()._lost_points_team;
        value += countTeamDraw * Tournament.getTournament().getParams()._draw_points_team;


        return value;
    }

    int getVNDByTeam(Team t) {

        int value = 0;
        int countTeamVictories = 0;
        int countTeamLoss = 0;
        int countTeamDraw = 0;

        int i = 0;
        if (_round_only) {
            i = _round;
        }

        while (i <= _round) {
            //for (int i = 0; i <= _round; i++) {
            int victories = 0;
            int draw = 0;
            int loss = 0;

            for (int j = 0; j < t._coachs.size(); j++) {
                Coach c = t._coachs.get(j);
                if (c._matchs.size() > i) {
                    Match m = c._matchs.get(i);
                    Criteria crit = Tournament.getTournament().getParams()._criterias.get(0);
                    Value val = m._values.get(crit);
                    if (m._coach1 == c) {
                        if (val._value1 > val._value2) {
                            victories++;
                        } else {
                            if (val._value1 < val._value2) {
                                loss++;
                            } else {
                                draw++;
                            }
                        }
                    } else {
                        if (val._value1 < val._value2) {
                            victories++;
                        } else {
                            if (val._value1 > val._value2) {
                                loss++;
                            } else {
                                draw++;
                            }
                        }
                    }
                }
            }
            if (victories > loss) {
                countTeamVictories++;
            } else {
                if (victories < loss) {
                    countTeamLoss++;
                } else {
                    countTeamDraw++;
                }
            }
            i++;
        }

        value += countTeamVictories * 1000000;
        value += countTeamLoss * 1;
        value += countTeamDraw * 1000;
        return value;
    }

    int getOppPointsByTeam(Team t) {

        int value = 0;
        Coach c = t._coachs.get(0);
        //Vector<Team> opponents = new Vector<Team>();
        if (_round <= c._matchs.size()) {

            int i = 0;
            if (_round_only) {
                i = _round;
            }

            while (i <= _round) {
                //for (int i = 0; i <= _round; i++) {
                Match m = c._matchs.get(i);
                if (m._coach1 == c) {
                    value += getPointsByTeam(m._coach2._teamMates);
                } else {
                    value += getPointsByTeam(m._coach1._teamMates);
                }
                i++;
            }
        }
        return value;
    }

    int getValue(Team t, int rankingType) {
        int value = 0;

        // Find opposing team in using first Coach

        if (_teamVictory) {
            switch (rankingType) {
                case Parameters.C_RANKING_POINTS:
                    value += getPointsByTeam(t);
                    break;
                case Parameters.C_RANKING_NONE:
                    value = 0;
                    break;
                case Parameters.C_RANKING_OPP_POINTS:
                    value += getOppPointsByTeam(t);
                    break;
                case Parameters.C_RANKING_VND:
                    value += getVNDByTeam(t);
                    break;
            }
        } else {
            for (int i = 0; i < t._coachs.size(); i++) {
                Coach c = t._coachs.get(i);
                for (int j = 0; j < c._matchs.size(); j++) {
                    Match m = c._matchs.get(j);
                    switch (rankingType) {
                        case Parameters.C_RANKING_POINTS:
                            value += getPointsByCoach(c, m);
                            break;
                        case Parameters.C_RANKING_NONE:
                            value += 0;
                            break;
                        case Parameters.C_RANKING_OPP_POINTS:
                            value += getOppPointsByCoach(c, m);
                            break;
                        case Parameters.C_RANKING_VND:
                            value += getVNDByCoach(c, m);
                            break;
                    }
                }
            }
            switch (rankingType) {
                case Parameters.C_RANKING_POINTS:
                    value += (getVNDByTeam(t) / 1000000) * Tournament.getTournament().getParams()._team_victory_points;
                    value += ((getVNDByTeam(t) % 1000000) / 1000) * Tournament.getTournament().getParams()._team_draw_points;
                    break;
                case Parameters.C_RANKING_OPP_POINTS:
                    Coach c = t._coachs.get(0);

                    int i = 0;
                    if (_round_only) {
                        i = _round;
                    }

                    while (i <= _round) {

                        //for (int i = 0; i <= _round; i++) {
                        if (c._matchs.size() > i) {
                            Match m = c._matchs.get(i);
                            if (m._coach1 == c) {
                                value += (getVNDByTeam(m._coach2._teamMates) / 1000000) * Tournament.getTournament().getParams()._team_victory_points;
                                value += ((getVNDByTeam(m._coach2._teamMates) % 1000000) / 1000) * Tournament.getTournament().getParams()._team_draw_points;
                            } else {
                                value += (getVNDByTeam(m._coach1._teamMates) / 1000000) * Tournament.getTournament().getParams()._team_victory_points;
                                value += ((getVNDByTeam(m._coach1._teamMates) % 1000000) / 1000) * Tournament.getTournament().getParams()._team_draw_points;
                            }
                        }
                        i++;
                    }
                    break;
            }
        }
        return value;
    }

    int getValue(Team t, Criteria crit,
            int subtype) {
        int value = 0;
        for (int i = 0; i < t._coachs.size(); i++) {
            Coach c = t._coachs.get(i);
            for (int j = 0; j < c._matchs.size(); j++) {
                value += getValue(c, c._matchs.get(j), crit, subtype);
            }
        }
        return value;
    }

    int getValue(Team t, int rankingType, int round) {
        int value = 0;
        Criteria c = getCriteriaByValue(rankingType);
        int subType = getSubtypeByValue(rankingType);
        if (c == null) {
            value += getValue(t, rankingType);
        } else {
            value += getValue(t, c, subType);
        }
        return value;
    }

    protected void sortDatas() {

        _datas.clear();
        _datas = new Vector<ObjectRanking>();
        for (int i = 0; i < _objects.size(); i++) {
            Team t = (Team) _objects.get(i);
            int value1 = getValue(t, _ranking_type1, _round);
            int value2 = getValue(t, _ranking_type2, _round);
            int value3 = getValue(t, _ranking_type3, _round);
            int value4 = getValue(t, _ranking_type4, _round);
            int value5 = getValue(t, _ranking_type5, _round);

            /*for (int k = 0; k < t._coachs.size(); k++) {
             Coach c = t._coachs.get(k);
             Match m = c._matchs.get(_round);

             Round round = Tournament.getTournament().getRounds().get(_round);

             Criteria c1 = getCriteriaByValue(_ranking_type1);
             int subType1 = getSubtypeByValue(_ranking_type1);
             if (c1 == null) {
             value1 += getValue(c, m, _ranking_type1, round);
             } else {
             value1 += getValue(c, m, c1, subType1);
             }

             Criteria c2 = getCriteriaByValue(_ranking_type2);
             int subType2 = getSubtypeByValue(_ranking_type2);
             if (c2 == null) {
             value2 += getValue(c, m, _ranking_type2, round);
             } else {
             value2 += getValue(c, m, c2, subType2);
             }

             Criteria c3 = getCriteriaByValue(_ranking_type3);
             int subType3 = getSubtypeByValue(_ranking_type3);
             if (c3 == null) {
             value3 += getValue(c, m, _ranking_type3, round);
             } else {
             value3 += getValue(c, m, c3, subType3);
             }

             Criteria c4 = getCriteriaByValue(_ranking_type4);
             int subType4 = getSubtypeByValue(_ranking_type4);
             if (c4 == null) {
             value4 += getValue(c, m, _ranking_type4, round);
             } else {
             value4 += getValue(c, m, c4, subType4);
             }

             Criteria c5 = getCriteriaByValue(_ranking_type5);
             int subType5 = getSubtypeByValue(_ranking_type5);
             if (c5 == null) {
             value5 += getValue(c, m, _ranking_type5, round);
             } else {
             value5 += getValue(c, m, c5, subType5);
             }
             }*/
            _datas.add(new ObjectRanking(t, value1, value2, value3, value4, value5));
        }

        Collections.sort(_datas);

        /*_datas.clear();
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

         Collections.sort(_datas);*/


        Tournament tour = Tournament.getTournament();

        // On ajuste le tri par poule si nécessaire pour que
        // l'écart minimum entre 2 membres de la même poule
        // soit le nombre de joueurs de la poule
        if ((tour.getPools().size() > 0) && (!tour.getRounds().get(_round)._cup)) {
            if (_objects.size() > tour.getPools().get(0)._teams.size()) {
                int nbPool = tour.getPools().size();
                Pool p = null;
                Vector<mjtRankingTeam> pRank = new Vector<mjtRankingTeam>();
                for (int j = 0; j < nbPool; j++) {
                    p = tour.getPools().get(j);
                    mjtRankingTeam mjtr = new mjtRankingTeam(_teamVictory, _round, _ranking_type1, _ranking_type2, _ranking_type3, _ranking_type4, _ranking_type5, p._teams, _round_only);
                    pRank.add(mjtr);
                }


                Vector datas = new Vector<ObjectRanking>();

                for (int i = 0; i < tour.getPools().get(0)._teams.size(); i++) {
                    Vector<Team> rank = new Vector<Team>();
                    for (int j = 0; j < nbPool; j++) {
                        ObjectRanking obj = (ObjectRanking) pRank.get(j)._datas.get(i);
                        rank.add((Team) obj.getObject());
                    }
                    mjtRankingTeam mjtr = new mjtRankingTeam(_teamVictory, _round, _ranking_type1, _ranking_type2, _ranking_type3, _ranking_type4, _ranking_type5, rank, _round_only);

                    for (int j = 0; j < mjtr._datas.size(); j++) {
                        datas.add(mjtr._datas.get(j));
                    }
                }

                _datas = datas;
            }
        }
    }

    public int getColumnCount() {
        return 7;
    }

    public String getColumnName(int col) {
        switch (col) {
            case 0:
                return "#";
            case 1:
                return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("Team");
            case 2:
                return getRankingString(_ranking_type1);
            case 3:
                return getRankingString(_ranking_type2);
            case 4:
                return getRankingString(_ranking_type3);
            case 5:
                return getRankingString(_ranking_type4);
            case 6:
                return getRankingString(_ranking_type5);
        }
        return "";
    }

    public static int getTeamVND(Team t, Vector<Match> v) {
        int value = 0;

        int nbVictory = 0;
        int nbLost = 0;
        for (int i = 0; i < v.size(); i++) {
            Match m = v.get(i);
            Value val = m._values.get(Tournament.getTournament().getParams()._criterias.get(0));
            if (m._coach1._teamMates == t) {
                if (val._value1 > val._value2) {
                    nbVictory++;
                } else {
                    if (val._value1 < val._value2) {
                        nbLost++;
                    }
                }
            }
            if (m._coach2._teamMates == t) {
                if (val._value1 < val._value2) {
                    nbVictory++;
                } else {
                    if (val._value1 > val._value2) {
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
            Value val = m._values.get(Tournament.getTournament().getParams()._criterias.get(0));
            if (m._coach1._teamMates == t) {
                if (val._value1 > val._value2) {
                    nbVictory++;
                } else {
                    if (val._value1 < val._value2) {
                        nbLost++;
                    }
                }

                for (int j = 0; j < Tournament.getTournament().getParams()._criterias.size(); j++) {

                    Criteria cri = Tournament.getTournament().getParams()._criterias.get(j);
                    Value va = m._values.get(cri._name);
                    value += va._value1 + cri._pointsFor;
                    value += va._value2 + cri._pointsAgainst;
                }

            }
            if (m._coach2._teamMates == t) {
                if (val._value1 < val._value2) {
                    nbVictory++;
                } else {
                    if (val._value1 > val._value2) {
                        nbLost++;
                    }
                }
                for (int j = 0; j < Tournament.getTournament().getParams()._criterias.size(); j++) {
                    Criteria cri = Tournament.getTournament().getParams()._criterias.get(j);
                    Value va = m._values.get(cri._name);
                    value += va._value2 + cri._pointsFor;
                    value += va._value1 + cri._pointsAgainst;
                }
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

    /*  public static int getOppTeamPoints(Team t, Vector<Match> v, Vector<Round> vr) {
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
     }*/

    /*public static int getValue(Team t, Vector<Match> v, int valueType, Vector<Round> rounds) {
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
     case Parameters.C_RANKING_NONE:
     value = 0;
     break;
     case Parameters.C_RANKING_OPP_POINTS:
     if (Tournament.getTournament().getParams()._team_victory_only) {
     value = getOppTeamPoints(t, v, rounds);
     } else {
     for (int i = 0; i < t._coachs.size(); i++) {
     for (int j = 0; j < v.size(); j++) {
     value += getOppPointsByCoach(t._coachs.get(i), rounds);
     }
     }
     if (getTeamVND(t, v) < 1000000) {
     value += Tournament.getTournament().getParams()._team_victory_points;
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
     }*/
    public Object getValueAt(int row, int col) {

        if (_datas.size() > row) {
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
        }
        return "";
    }
}
