/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.tableModel;

import tourma.data.Round;
import tourma.data.Match;
import tourma.data.Coach;
import java.util.Collections;
import java.util.Vector;
import tourma.data.ObjectAnnexRanking;
import tourma.data.Team;

/**
 *
 * @author Frederic Berger
 */
public class mjtAnnexRankTeam extends mjtAnnexRank {

    public mjtAnnexRankTeam(Vector<Round> rounds, int ranking_type, Vector<Team> teams, boolean full, int ranking_type1, int ranking_type2, int ranking_type3, int ranking_type4, int ranking_type5) {
        super(rounds, ranking_type, teams, full, ranking_type1, ranking_type2, ranking_type3, ranking_type4, ranking_type5);
    }

    protected void sortDatas() {
        _datas.clear();
        Vector<Match> matchs = new Vector<Match>();
        for (int i = 0; i < _rounds.size(); i++) {
            for (int j = 0; j < _rounds.get(i).getMatchs().size(); j++) {
                matchs.add(_rounds.get(i).getMatchs().get(j));
            }
        }
        _datas = new Vector<ObjectAnnexRanking>();
        for (int i = 0; i < _objects.size(); i++) {
            Team t = (Team) _objects.get(i);
            int value = 0;
            int value1 = 0;
            int value2 = 0;
            int value3 = 0;
            int value4 = 0;
            int value5 = 0;
            for (int j = 0; j < matchs.size(); j++) {
                Match m = matchs.get(j);
                for (int k = 0; k < t._coachs.size(); k++) {
                    Coach c = t._coachs.get(k);
                    if (m._coach1 == c) {
                        switch (_ranking_type) {
                            case C_MOST_TD_POS:
                                value += m._td1;
                                break;
                            case C_MOST_TD_NEG:
                                value += m._td2;
                                break;
                            case C_MOST_SOR_POS:
                                value += m._sor1;
                                break;
                            case C_MOST_SOR_NEG:
                                value += m._sor2;
                                break;
                            case C_MOST_FOUL_POS:
                                value += m._foul1;
                                break;
                            case C_MOST_FOUL_NEG:
                                value += m._foul2;
                                break;
                            case C_MOST_PAS_POS:
                                value += m._pas1;
                                break;
                            case C_MOST_PAS_NEG:
                                value += m._pas2;
                                break;
                            case C_MOST_INT_POS:
                                value += m._int1;
                                break;
                            case C_MOST_INT_NEG:
                                value += m._int2;
                                break;

                        }
                    }
                    if (m._coach2 == c) {
                        switch (_ranking_type) {
                            case C_MOST_TD_POS:
                                value += m._td2;
                                break;
                            case C_MOST_TD_NEG:
                                value += m._td1;
                                break;
                            case C_MOST_SOR_POS:
                                value += m._sor2;
                                break;
                            case C_MOST_SOR_NEG:
                                value += m._sor1;
                                break;
                            case C_MOST_FOUL_POS:
                                value += m._foul2;
                                break;
                            case C_MOST_FOUL_NEG:
                                value += m._foul1;
                                break;
                            case C_MOST_PAS_POS:
                                value += m._pas2;
                                break;
                            case C_MOST_PAS_NEG:
                                value += m._pas1;
                                break;
                            case C_MOST_INT_POS:
                                value += m._int2;
                                break;
                            case C_MOST_INT_NEG:
                                value += m._int1;
                                break;
                        }
                    }
                    value1 += getValue(c, m, _ranking_type1, _rounds);
                    value2 += getValue(c, m, _ranking_type2, _rounds);
                    value3 += getValue(c, m, _ranking_type3, _rounds);
                    value4 += getValue(c, m, _ranking_type4, _rounds);
                    value5 += getValue(c, m, _ranking_type5, _rounds);
                }
            }
            _datas.add(new ObjectAnnexRanking(t, value, value1, value2, value3, value4, value5));
        }

        Collections.sort(_datas);
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public String getColumnName(int col) {
        switch (col) {
            case 0:
                return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("N");
            case 1:
                return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("CLAN");
            case 2:
                switch (_ranking_type) {
                    case C_MOST_TD_POS:
                        return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("TDS");
                    case C_MOST_TD_NEG:
                        return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("TDS");
                    case C_MOST_SOR_POS:
                        return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("SOR");
                    case C_MOST_SOR_NEG:
                        return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("SOR");
                    case C_MOST_FOUL_POS:
                        return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("FOUL");
                    case C_MOST_FOUL_NEG:
                        return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("FOUL");
                    case C_MOST_PAS_POS:
                        return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("PAS");
                    case C_MOST_PAS_NEG:
                        return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("PAS");
                    case C_MOST_INT_POS:
                        return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("INT");
                    case C_MOST_INT_NEG:
                        return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("INT");
                }
        }
        return "";
    }

    @Override
    public Object getValueAt(int row, int col) {

        ObjectAnnexRanking obj = (ObjectAnnexRanking) _datas.get(row);

        switch (col) {
            case 0:
                return row + 1;
            case 1:
                return ((Team) obj.getObject())._name;
            case 2:
                return obj._value;
        }
        return "";
    }
}
