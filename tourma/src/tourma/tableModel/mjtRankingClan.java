/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.tableModel;

import tourma.data.ObjectRanking;
import tourma.data.Parameters;
import tourma.data.Round;
import tourma.data.Match;
import java.util.Collections;
import java.util.HashMap;
import java.util.Vector;
import tourma.data.Clan;
import tourma.data.Coach;
import tourma.data.Tournament;

/**
 *
 * @author Frederic Berger
 */
public class mjtRankingClan extends mjtRanking {

    public mjtRankingClan(Vector<Round> rounds, int ranking_type1, int ranking_type2, int ranking_type3, int ranking_type4, int ranking_type5, Vector<Clan> clans) {
        super(rounds, ranking_type1, ranking_type2, ranking_type3, ranking_type4, ranking_type5, clans);
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
            Clan c = (Clan) _objects.get(i);
            int value1 = 0;
            int value2 = 0;
            int value3 = 0;
            int value4 = 0;
            int value5 = 0;
            for (int j = 0; j < matchs.size(); j++) {
                Match m = matchs.get(j);
                if (m._coach1._clan == c) {
                    value1 += getValue(m._coach1, m, _ranking_type1, _rounds);
                    value2 += getValue(m._coach1, m, _ranking_type2, _rounds);
                    value3 += getValue(m._coach1, m, _ranking_type3, _rounds);
                    value4 += getValue(m._coach1, m, _ranking_type4, _rounds);
                    value5 += getValue(m._coach1, m, _ranking_type5, _rounds);
                }
                if (m._coach2._clan == c) {
                    value1 += getValue(m._coach2, m, _ranking_type1, _rounds);
                    value2 += getValue(m._coach2, m, _ranking_type2, _rounds);
                    value3 += getValue(m._coach2, m, _ranking_type3, _rounds);
                    value4 += getValue(m._coach2, m, _ranking_type4, _rounds);
                    value5 += getValue(m._coach2, m, _ranking_type5, _rounds);
                }
            }
            _datas.add(new ObjectRanking(c, value1, value2, value3, value4, value5));
        }
        Collections.sort(_datas);
    }

    public int getColumnCount() {
        return 7;
    }

    public String getColumnName(int col) {
        switch (col) {
            case 0:
                return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("N");
            case 1:
                return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("ClanKey");
            case 2:
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
            case 3:
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
            case 4:
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
            case 5:
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
            case 6:
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

    public static int getValue(Clan c, Vector<Match> v, int valueType, Vector<Round> rounds) {
        /*
         * Build Coach List with value hash map
         */
        Vector<Coach> coaches = new Vector<Coach>();
        /**
         * Loop on first round for unique coahc instance
         */
        for (int i = 0; i < rounds.get(0).getMatchs().size(); i++) {
            Match m = rounds.get(0).getMatchs().get(i);
            if (m._coach1._clan == c) {
                coaches.addElement(m._coach1);
            }
            if (m._coach2._clan == c) {
                coaches.addElement(m._coach2);
            }
        }

        HashMap<Coach, Integer> valuesMap = new HashMap<Coach, Integer>();

        switch (valueType) {
            case Parameters.C_RANKING_POINTS:
                for (int i = 0; i < coaches.size(); i++) {
                    for (int j = 0; j < v.size(); j++) {
                        valuesMap.put(coaches.get(i), valuesMap.get(coaches.get(i)) + getPointsByCoach(coaches.get(i), v.get(j)));
                    }
                }
                break;
            case Parameters.C_RANKING_DIFF_FOUL:
                for (int i = 0; i < coaches.size(); i++) {
                    for (int j = 0; j < v.size(); j++) {
                        valuesMap.put(coaches.get(i), valuesMap.get(coaches.get(i)) + getDiffFoulByCoach(coaches.get(i), v.get(j)));
                    }
                }
                break;
            case Parameters.C_RANKING_DIFF_SOR:
                for (int i = 0; i < coaches.size(); i++) {
                    for (int j = 0; j < v.size(); j++) {
                        valuesMap.put(coaches.get(i), valuesMap.get(coaches.get(i)) + getDiffSorByCoach(coaches.get(i), v.get(j)));
                    }
                }
                break;
            case Parameters.C_RANKING_DIFF_TD:
                for (int i = 0; i < coaches.size(); i++) {
                    for (int j = 0; j < v.size(); j++) {
                        valuesMap.put(coaches.get(i), valuesMap.get(coaches.get(i)) + getDiffTdByCoach(coaches.get(i), v.get(j)));
                    }
                }
                break;
            case Parameters.C_RANKING_DIFF_INT:
                for (int i = 0; i < coaches.size(); i++) {
                    for (int j = 0; j < v.size(); j++) {
                        valuesMap.put(coaches.get(i), valuesMap.get(coaches.get(i)) + getDiffIntByCoach(coaches.get(i), v.get(j)));
                    }
                }
                break;
            case Parameters.C_RANKING_DIFF_PAS:
                for (int i = 0; i < coaches.size(); i++) {
                    for (int j = 0; j < v.size(); j++) {
                        valuesMap.put(coaches.get(i), valuesMap.get(coaches.get(i)) + getDiffPasByCoach(coaches.get(i), v.get(j)));
                    }
                }
                break;
            case Parameters.C_RANKING_FOUL:
                for (int i = 0; i < coaches.size(); i++) {
                    for (int j = 0; j < v.size(); j++) {
                        valuesMap.put(coaches.get(i), valuesMap.get(coaches.get(i)) + getFoulByCoach(coaches.get(i), v.get(j)));
                    }
                }
                break;
            case Parameters.C_RANKING_NONE:
                for (int i = 0; i < coaches.size(); i++) {
                    valuesMap.put(coaches.get(i), 0);
                }
                break;
            case Parameters.C_RANKING_OPP_POINTS:
                for (int i = 0; i < coaches.size(); i++) {
                    for (int j = 0; j < v.size(); j++) {
                        valuesMap.put(coaches.get(i), valuesMap.get(coaches.get(i)) + getOppPointsByCoach(coaches.get(i), rounds));
                    }
                }
                break;
            case Parameters.C_RANKING_SOR:
                for (int i = 0; i < coaches.size(); i++) {
                    for (int j = 0; j < v.size(); j++) {
                        valuesMap.put(coaches.get(i), valuesMap.get(coaches.get(i)) + getSorByCoach(coaches.get(i), v.get(j)));
                    }
                }
                break;
            case Parameters.C_RANKING_PAS:
                for (int i = 0; i < coaches.size(); i++) {
                    for (int j = 0; j < v.size(); j++) {
                        valuesMap.put(coaches.get(i), valuesMap.get(coaches.get(i)) + getPasByCoach(coaches.get(i), v.get(j)));
                    }
                }
                break;
            case Parameters.C_RANKING_INT:
                for (int i = 0; i < coaches.size(); i++) {
                    for (int j = 0; j < v.size(); j++) {
                        valuesMap.put(coaches.get(i), valuesMap.get(coaches.get(i)) + getIntByCoach(coaches.get(i), v.get(j)));
                    }
                }
                break;
            case Parameters.C_RANKING_TD:
                for (int i = 0; i < coaches.size(); i++) {
                    for (int j = 0; j < v.size(); j++) {
                        valuesMap.put(coaches.get(i), valuesMap.get(coaches.get(i)) + getTdByCoach(coaches.get(i), v.get(j)));
                    }
                }
                break;
            case Parameters.C_RANKING_VND:
                for (int i = 0; i < coaches.size(); i++) {
                    for (int j = 0; j < v.size(); j++) {
                        valuesMap.put(coaches.get(i), valuesMap.get(coaches.get(i)) + getVNDByCoach(coaches.get(i), v.get(j)));
                    }
                }

                break;
        }

        /**
         * Compute the best X values for clan
         */
        Vector<Integer> Values = new Vector<Integer>();
        for (int i = 0; i < coaches.size(); i++) {
            Values.add(valuesMap.get(coaches.get(i)));
        }

        int value=0;
        int maximum=0;
        Collections.sort(Values,Collections.reverseOrder());
        for (int i=0; i<Math.min(Tournament.getTournament().getParams()._teamMatesClansNumber,coaches.size()); i++)
        {
            value+=Values.get(i);
        }
        return value;
    }

    public Object getValueAt(int row, int col) {

        ObjectRanking obj = (ObjectRanking) _datas.get(row);
        switch (col) {
            case 0:
                return row + 1;
            case 1:
                return ((Clan) obj.getObject())._name;
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
