/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.tableModel;

import tourma.data.Round;
import tourma.data.Match;
import tourma.data.Coach;
import java.util.Collections;
import java.util.HashMap;
import java.util.Vector;
import tourma.data.Clan;
import tourma.data.ObjectAnnexRanking;
import tourma.data.Tournament;

/**
 *
 * @author Frederic Berger
 */
public class mjtAnnexRankClan extends mjtAnnexRank {

    public mjtAnnexRankClan(Vector<Round> rounds, int ranking_type, Vector<Clan> clans, boolean full, int ranking_type1, int ranking_type2, int ranking_type3, int ranking_type4, int ranking_type5) {
        super(rounds, ranking_type, clans, full, ranking_type1, ranking_type2, ranking_type3, ranking_type4, ranking_type5);
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
            Clan cl = (Clan) _objects.get(i);

            /**
             * Build Coach List
             */
            /*
             * Build Coach List with value hash map
             */
            Vector<Coach> coaches = new Vector<Coach>();
            /**
             * Loop on first round for unique coahc instance
             */
            for (int j = 0; j < _rounds.get(0).getMatchs().size(); j++) {
                Match m = _rounds.get(0).getMatchs().get(j);
                if (m._coach1._clan == cl) {
                    coaches.addElement(m._coach1);
                }
                if (m._coach2._clan == cl) {
                    coaches.addElement(m._coach2);
                }
            }
            HashMap<Coach, Integer> valuesMap = new HashMap<Coach, Integer>();
            HashMap<Coach, Integer> values1Map = new HashMap<Coach, Integer>();
            HashMap<Coach, Integer> values2Map = new HashMap<Coach, Integer>();
            HashMap<Coach, Integer> values3Map = new HashMap<Coach, Integer>();
            HashMap<Coach, Integer> values4Map = new HashMap<Coach, Integer>();
            HashMap<Coach, Integer> values5Map = new HashMap<Coach, Integer>();

            for (int k = 0; k < coaches.size(); k++) {
                valuesMap.put(coaches.get(k), 0);
                values1Map.put(coaches.get(k), 0);
                values2Map.put(coaches.get(k), 0);
                values3Map.put(coaches.get(k), 0);
                values4Map.put(coaches.get(k), 0);
                values5Map.put(coaches.get(k), 0);
            }

            for (int j = 0; j < matchs.size(); j++) {
                Match m = matchs.get(j);
                for (int k = 0; k < coaches.size(); k++) {
                    Coach c = coaches.get(k);
                    if (m._coach1 == c) {
                        switch (_ranking_type) {
                            case C_MOST_TD_POS:
                                if (m._td1 >= 0) {
                                    valuesMap.put(c, valuesMap.get(c) + m._td1);
                                }
                                break;
                            case C_MOST_TD_NEG:
                                if (m._td2 >= 0) {
                                    valuesMap.put(c, valuesMap.get(c) + m._td2);
                                }
                                break;
                            case C_MOST_SOR_POS:
                                valuesMap.put(c, valuesMap.get(c) + m._sor1);
                                break;
                            case C_MOST_SOR_NEG:
                                valuesMap.put(c, valuesMap.get(c) + m._sor2);
                                break;
                            case C_MOST_FOUL_POS:
                                valuesMap.put(c, valuesMap.get(c) + m._foul1);
                                break;
                            case C_MOST_FOUL_NEG:
                                valuesMap.put(c, valuesMap.get(c) + m._foul2);
                                break;
                            case C_MOST_PAS_POS:
                                valuesMap.put(c, valuesMap.get(c) + m._pas1);
                                break;
                            case C_MOST_PAS_NEG:
                                valuesMap.put(c, valuesMap.get(c) + m._pas2);
                                break;
                            case C_MOST_INT_POS:
                                valuesMap.put(c, valuesMap.get(c) + m._int1);
                                break;
                            case C_MOST_INT_NEG:
                                valuesMap.put(c, valuesMap.get(c) + m._int2);
                                break;

                        }
                    }
                    if (m._coach2 == c) {
                        switch (_ranking_type) {
                            case C_MOST_TD_POS:
                                if (m._td2 >= 0) {
                                    valuesMap.put(c, valuesMap.get(c) + m._td2);
                                }
                                break;
                            case C_MOST_TD_NEG:
                                if (m._td1 >= 0) {
                                    valuesMap.put(c, valuesMap.get(c) + m._td1);
                                }
                                break;
                            case C_MOST_SOR_POS:
                                valuesMap.put(c, valuesMap.get(c) + m._sor2);
                                break;
                            case C_MOST_SOR_NEG:
                                valuesMap.put(c, valuesMap.get(c) + m._sor1);
                                break;
                            case C_MOST_FOUL_POS:
                                valuesMap.put(c, valuesMap.get(c) + m._foul2);
                                break;
                            case C_MOST_FOUL_NEG:
                                valuesMap.put(c, valuesMap.get(c) + m._foul1);
                                break;
                            case C_MOST_PAS_POS:
                                valuesMap.put(c, valuesMap.get(c) + m._pas2);
                                break;
                            case C_MOST_PAS_NEG:
                                valuesMap.put(c, valuesMap.get(c) + m._pas1);
                                break;
                            case C_MOST_INT_POS:
                                valuesMap.put(c, valuesMap.get(c) + m._int2);
                                break;
                            case C_MOST_INT_NEG:
                                valuesMap.put(c, valuesMap.get(c) + m._int1);
                                break;
                        }
                    }

                    values1Map.put(c, valuesMap.get(c) + getValue(c, m, _ranking_type1, _rounds));
                    values2Map.put(c, valuesMap.get(c) + getValue(c, m, _ranking_type2, _rounds));
                    values3Map.put(c, valuesMap.get(c) + getValue(c, m, _ranking_type3, _rounds));
                    values4Map.put(c, valuesMap.get(c) + getValue(c, m, _ranking_type4, _rounds));
                    values5Map.put(c, valuesMap.get(c) + getValue(c, m, _ranking_type5, _rounds));
                }
            }
            int value = 0;
            int value1 = 0;
            int value2 = 0;
            int value3 = 0;
            int value4 = 0;
            int value5 = 0;

            Vector<Integer> Values = new Vector<Integer>();
            Vector<Integer> Values1 = new Vector<Integer>();
            Vector<Integer> Values2 = new Vector<Integer>();
            Vector<Integer> Values3 = new Vector<Integer>();
            Vector<Integer> Values4 = new Vector<Integer>();
            Vector<Integer> Values5 = new Vector<Integer>();

            for (int k = 0; k < coaches.size(); k++) {
                Values.add(valuesMap.get(coaches.get(k)));
                Values1.add(values1Map.get(coaches.get(k)));
                Values2.add(values2Map.get(coaches.get(k)));
                Values3.add(values3Map.get(coaches.get(k)));
                Values4.add(values4Map.get(coaches.get(k)));
                Values5.add(values5Map.get(coaches.get(k)));
            }

            Collections.sort(Values, Collections.reverseOrder());
            Collections.sort(Values1, Collections.reverseOrder());
            Collections.sort(Values2, Collections.reverseOrder());
            Collections.sort(Values3, Collections.reverseOrder());
            Collections.sort(Values4, Collections.reverseOrder());
            Collections.sort(Values5, Collections.reverseOrder());

            for (int k = 0; k < Math.min(Tournament.getTournament().getParams()._teamMatesClansNumber, coaches.size()); k++) {
                value += Values.get(k);
                value1 += Values1.get(k);
                value2 += Values2.get(k);
                value3 += Values3.get(k);
                value4 += Values4.get(k);
                value5 += Values5.get(k);
            }

            _datas.add(new ObjectAnnexRanking(cl, value, value1, value2, value3, value4, value5));
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
                return ((Clan) obj.getObject())._name;
            case 2:
                return obj._value;
        }
        return "";
    }
}
