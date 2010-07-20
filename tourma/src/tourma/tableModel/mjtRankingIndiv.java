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
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author Frederic Berger
 */
public class mjtRankingIndiv extends mjtRanking {

    boolean _teamTournament;

    public mjtRankingIndiv(Vector<Round> rounds, int ranking_type1, int ranking_type2, int ranking_type3, int ranking_type4, int ranking_type5, Vector<Coach> coachs, boolean tournament) {
        super(rounds, ranking_type1, ranking_type2, ranking_type3, ranking_type4, ranking_type5,coachs);
        _teamTournament = tournament;
        sortDatas();
    }

    protected void sortDatas()
    {
        Vector<Match> matchs = new Vector<Match>();
        for (int i = 0; i < _rounds.size(); i++) {
            for (int j = 0; j < _rounds.get(i).getMatchs().size(); j++) {
                matchs.add(_rounds.get(i).getMatchs().get(j));
            }
        }

        for (int i = 0; i < _objects.size(); i++) {
            Coach c =(Coach) _objects.get(i);
            int value1 = 0;
            int value2 = 0;
            int value3 = 0;
            int value4 = 0;
            int value5 = 0;
            for (int j = 0; j < matchs.size(); j++) {
                Match m = matchs.get(j);
                value1 += getValue(c, m, _ranking_type1, _rounds);
                value2 += getValue(c, m, _ranking_type2, _rounds);
                value3 += getValue(c, m, _ranking_type3, _rounds);
                value4 += getValue(c, m, _ranking_type4, _rounds);
                value5 += getValue(c, m, _ranking_type5, _rounds);
            }
            _datas.add(new ObjectRanking(c, value1, value2, value3, value4, value5));
        }

        Collections.sort(_datas);
    }
    
    public int getColumnCount() {
        if (_teamTournament) {
            return 10;
        }
        return 9;
    }

    
@Override
    public String getColumnName(int col) {

        int cl = col;
        if (_teamTournament) {
            if (col > 1) {
                cl = col - 1;
            }
            if (col == 1) {
                return "Clan";
            }
        }

        switch (cl) {
            case 0:
                return "N";
            case 1:
                return "Equipe";
            case 2:
                return "Coach";
            case 3:
                return "Roster";
            case 4:
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
                }
                break;
            case 5:
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
                }
                break;
            case 6:
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
                }
                break;
            case 7:
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
                }
                break;
            case 8:
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
                }
                break;
        }
        return "";
    }

    public Object getValueAt(int row, int col) {

        ObjectRanking obj=(ObjectRanking)_datas.get(row);

        int cl = col;
        if (_teamTournament) {
            if (col > 1) {
                cl = col - 1;
            }
            if (col == 1) {
                return ((Coach) obj.getObject())._teamMates._name;
            }
        }

        switch (cl) {
            case 0:
                return row + 1;
            case 1:
                return ((Coach) obj.getObject())._team;
            case 2:
                return ((Coach) obj.getObject())._name;
            case 3:
                return ((Coach) obj.getObject())._roster;
            case 4:
                return obj.getValue1();
            case 5:
                return obj.getValue2();
            case 6:
                return obj.getValue3();
            case 7:
                return obj.getValue4();
            case 8:
                return obj.getValue5();
        }



        return "";
    }


}
