/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.tableModel;

import tourma.data.ObjectRanking;
import tourma.data.Parameters;
import tourma.data.Round;
import tourma.data.Match;
import tourma.data.Coach;
import java.util.Collections;
import java.util.Vector;

/**
 *
 * @author Frederic Berger
 */
public class mjtRankingIndiv extends mjtRanking {

    boolean _teamTournament;

    public mjtRankingIndiv(Vector<Round> rounds, int ranking_type1, int ranking_type2, int ranking_type3, int ranking_type4, int ranking_type5, Vector<Coach> coachs, boolean tournament) {
        super(rounds, ranking_type1, ranking_type2, ranking_type3, ranking_type4, ranking_type5, coachs);
        _teamTournament = tournament;
        sortDatas();
    }

    protected void sortDatas() {
        _datas.clear();
        Vector<Match> matchs = new Vector<Match>();
        for (int i = 0; i < _rounds.size(); i++) {
            for (int j = 0; j < _rounds.get(i).getMatchs().size(); j++) {
                matchs.add(_rounds.get(i).getMatchs().get(j));
            }
        }

        for (int i = 0; i < _objects.size(); i++) {
            Coach c = (Coach) _objects.get(i);
            int value1 = 0;
            int value2 = 0;
            int value3 = 0;
            int value4 = 0;
            int value5 = 0;

            value1 = getValues(c, matchs, _ranking_type1, _rounds);
            value2 = getValues(c, matchs, _ranking_type2, _rounds);
            value3 = getValues(c, matchs, _ranking_type3, _rounds);
            value4 = getValues(c, matchs, _ranking_type4, _rounds);
            value5 = getValues(c, matchs, _ranking_type5, _rounds);


            _datas.add(new ObjectRanking(c, value1, value2, value3, value4, value5));
        }

        Collections.sort(_datas);
    }

    protected int getValues(Coach c, Vector<Match> matchs, int ranking_type, Vector<Round> rounds) {
        int value = 0;
        if (ranking_type == Parameters.C_RANKING_OPP_POINTS) {
            value += getValue(c, matchs.get(0), ranking_type, rounds);
        } else {
            for (int j = 0; j < matchs.size(); j++) {
                Match m = matchs.get(j);
                value += getValue(c, m, ranking_type, rounds);
            }
        }
        return value;
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
                return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("CLAN");
            }
        }

        switch (cl) {
            case 0:
                return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("N");
            case 1:
                return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("EQUIPE");
            case 2:
                return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("COACH");
            case 3:
                return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("ROSTER");
            case 4:
                switch (_ranking_type1) {
                    case Parameters.C_RANKING_POINTS:
                        return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("POINTS");
                    case Parameters.C_RANKING_DIFF_FOUL:
                        return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("DIFF AGGR");
                    case Parameters.C_RANKING_DIFF_SOR:
                        return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("DIFF SOR");
                    case Parameters.C_RANKING_DIFF_TD:
                        return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("DIFF TDS");
                    case Parameters.C_RANKING_FOUL:
                        return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("NB AGGR");
                    case Parameters.C_RANKING_NONE:
                        return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("RIEN");
                    case Parameters.C_RANKING_OPP_POINTS:
                        return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("PTS ADV");
                    case Parameters.C_RANKING_SOR:
                        return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("NB SOR");
                    case Parameters.C_RANKING_TD:
                        return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("NB TDS");
                    case Parameters.C_RANKING_VND:
                        return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("V/N/D");
                    case Parameters.C_RANKING_PAS:
                        return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("PASSES");
                    case Parameters.C_RANKING_DIFF_PAS:
                        return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("DIFF PAS");
                    case Parameters.C_RANKING_INT:
                        return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("INTER.");
                    case Parameters.C_RANKING_DIFF_INT:
                        return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("DIFF INT");
                }
                break;
            case 5:
                switch (_ranking_type2) {
                    case Parameters.C_RANKING_POINTS:
                        return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("POINTS");
                    case Parameters.C_RANKING_DIFF_FOUL:
                        return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("DIFF AGGR");
                    case Parameters.C_RANKING_DIFF_SOR:
                        return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("DIFF SOR");
                    case Parameters.C_RANKING_DIFF_TD:
                        return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("DIFF TDS");
                    case Parameters.C_RANKING_FOUL:
                        return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("NB AGGR");
                    case Parameters.C_RANKING_NONE:
                        return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("RIEN");
                    case Parameters.C_RANKING_OPP_POINTS:
                        return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("PTS ADV");
                    case Parameters.C_RANKING_SOR:
                        return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("NB SOR");
                    case Parameters.C_RANKING_TD:
                        return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("NB TDS");
                    case Parameters.C_RANKING_VND:
                        return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("V/N/D");
                    case Parameters.C_RANKING_PAS:
                        return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("PASSES");
                    case Parameters.C_RANKING_DIFF_PAS:
                        return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("DIFF PAS");
                    case Parameters.C_RANKING_INT:
                        return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("INTER.");
                    case Parameters.C_RANKING_DIFF_INT:
                        return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("DIFF INT");
                }
                break;
            case 6:
                switch (_ranking_type3) {
                    case Parameters.C_RANKING_POINTS:
                        return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("POINTS");
                    case Parameters.C_RANKING_DIFF_FOUL:
                        return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("DIFF AGGR");
                    case Parameters.C_RANKING_DIFF_SOR:
                        return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("DIFF SOR");
                    case Parameters.C_RANKING_DIFF_TD:
                        return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("DIFF TDS");
                    case Parameters.C_RANKING_FOUL:
                        return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("NB AGGR");
                    case Parameters.C_RANKING_NONE:
                        return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("RIEN");
                    case Parameters.C_RANKING_OPP_POINTS:
                        return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("PTS ADV");
                    case Parameters.C_RANKING_SOR:
                        return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("NB SOR");
                    case Parameters.C_RANKING_TD:
                        return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("NB TDS");
                    case Parameters.C_RANKING_VND:
                        return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("V/N/D");
                    case Parameters.C_RANKING_PAS:
                        return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("PASSES");
                    case Parameters.C_RANKING_DIFF_PAS:
                        return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("DIFF PAS");
                    case Parameters.C_RANKING_INT:
                        return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("INTER.");
                    case Parameters.C_RANKING_DIFF_INT:
                        return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("DIFF INT");
                }
                break;
            case 7:
                switch (_ranking_type4) {
                    case Parameters.C_RANKING_POINTS:
                        return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("POINTS");
                    case Parameters.C_RANKING_DIFF_FOUL:
                        return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("DIFF AGGR");
                    case Parameters.C_RANKING_DIFF_SOR:
                        return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("DIFF SOR");
                    case Parameters.C_RANKING_DIFF_TD:
                        return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("DIFF TDS");
                    case Parameters.C_RANKING_FOUL:
                        return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("NB AGGR");
                    case Parameters.C_RANKING_NONE:
                        return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("RIEN");
                    case Parameters.C_RANKING_OPP_POINTS:
                        return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("PTS ADV");
                    case Parameters.C_RANKING_SOR:
                        return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("NB SOR");
                    case Parameters.C_RANKING_TD:
                        return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("NB TDS");
                    case Parameters.C_RANKING_VND:
                        return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("V/N/D");
                    case Parameters.C_RANKING_PAS:
                        return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("PASSES");
                    case Parameters.C_RANKING_DIFF_PAS:
                        return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("DIFF PAS");
                    case Parameters.C_RANKING_INT:
                        return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("INTER.");
                    case Parameters.C_RANKING_DIFF_INT:
                        return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("DIFF INT");
                }
                break;
            case 8:
                switch (_ranking_type5) {
                    case Parameters.C_RANKING_POINTS:
                        return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("POINTS");
                    case Parameters.C_RANKING_DIFF_FOUL:
                        return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("DIFF AGGR");
                    case Parameters.C_RANKING_DIFF_SOR:
                        return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("DIFF SOR");
                    case Parameters.C_RANKING_DIFF_TD:
                        return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("DIFF TDS");
                    case Parameters.C_RANKING_FOUL:
                        return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("NB AGGR");
                    case Parameters.C_RANKING_NONE:
                        return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("RIEN");
                    case Parameters.C_RANKING_OPP_POINTS:
                        return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("PTS ADV");
                    case Parameters.C_RANKING_SOR:
                        return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("NB SOR");
                    case Parameters.C_RANKING_TD:
                        return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("NB TDS");
                    case Parameters.C_RANKING_VND:
                        return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("V/N/D");
                    case Parameters.C_RANKING_PAS:
                        return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("PASSES");
                    case Parameters.C_RANKING_DIFF_PAS:
                        return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("DIFF PAS");
                    case Parameters.C_RANKING_INT:
                        return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("INTER.");
                    case Parameters.C_RANKING_DIFF_INT:
                        return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("DIFF INT");
                }
                break;
        }
        return "";
    }

    public Object getValueAt(int row, int col) {

        ObjectRanking obj = (ObjectRanking) _datas.get(row);

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
