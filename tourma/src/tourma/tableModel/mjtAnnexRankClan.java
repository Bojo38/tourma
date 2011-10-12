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
import tourma.data.Criteria;
import tourma.data.ObjectAnnexRanking;
import tourma.data.Tournament;

/**
 *
 * @author Frederic Berger
 */
public class mjtAnnexRankClan extends mjtAnnexRank {

    public mjtAnnexRankClan(int round, Criteria criteria, int subtype, Vector<Clan> clans, boolean full, int ranking_type1, int ranking_type2, int ranking_type3, int ranking_type4, int ranking_type5) {
        super(round, criteria, subtype, clans, full, ranking_type1, ranking_type2, ranking_type3, ranking_type4, ranking_type5);
    }

    protected void sortDatas() {

        _datas.clear();
        _datas = new Vector<ObjectAnnexRanking>();
        Vector<Coach> coaches = Tournament.getTournament().getCoachs();
        Vector<Clan> clans = (Vector<Clan>) _objects;

        for (int i = 0; i < clans.size(); i++) {
            Vector<Integer> Vvalue = new Vector<Integer>();
            Vector<Integer> Vvalue1 = new Vector<Integer>();
            Vector<Integer> Vvalue2 = new Vector<Integer>();
            Vector<Integer> Vvalue3 = new Vector<Integer>();
            Vector<Integer> Vvalue4 = new Vector<Integer>();
            Vector<Integer> Vvalue5 = new Vector<Integer>();

            int cvalue = 0;
            int cvalue1 = 0;
            int cvalue2 = 0;
            int cvalue3 = 0;
            int cvalue4 = 0;
            int cvalue5 = 0;

            for (int k = 0; k < coaches.size(); k++) {
                Coach c = coaches.get(k);

                if (c._clan == clans.get(i)) {
                    int value = 0;
                    int value1 = 0;
                    int value2 = 0;
                    int value3 = 0;
                    int value4 = 0;
                    int value5 = 0;
                    for (int j = 0; j <= c._matchs.size() - 1; j++) {

                        Match m = c._matchs.get(j);
                        boolean bFound = false;
                        for (int l = 0; (l <= _round) && (!bFound); l++) {
                            Round r = Tournament.getTournament().getRounds().get(l);
                            if (r.getMatchs().contains(m)) {
                                bFound = true;
                            }
                        }
                        // test if match is in round
                        if (bFound) {

                            value += getValue(c, m, _criteria, _subtype);

                            Criteria c1 = getCriteriaByValue(_ranking_type1);
                            int subType1 = getSubtypeByValue(_ranking_type1);
                            if (c1 == null) {
                                value1 += getValue(c, m, _ranking_type1);
                            } else {
                                value1 += getValue(c, m, c1, subType1);
                            }

                            Criteria c2 = getCriteriaByValue(_ranking_type2);
                            int subType2 = getSubtypeByValue(_ranking_type2);
                            if (c2 == null) {
                                value2 += getValue(c, m, _ranking_type2);
                            } else {
                                value2 += getValue(c, m, c2, subType2);
                            }

                            Criteria c3 = getCriteriaByValue(_ranking_type3);
                            int subType3 = getSubtypeByValue(_ranking_type3);
                            if (c3 == null) {
                                value3 += getValue(c, m, _ranking_type3);
                            } else {
                                value3 += getValue(c, m, c3, subType3);
                            }

                            Criteria c4 = getCriteriaByValue(_ranking_type4);
                            int subType4 = getSubtypeByValue(_ranking_type4);
                            if (c4 == null) {
                                value4 += getValue(c, m, _ranking_type4);
                            } else {
                                value4 += getValue(c, m, c4, subType4);
                            }

                            Criteria c5 = getCriteriaByValue(_ranking_type5);
                            int subType5 = getSubtypeByValue(_ranking_type5);
                            if (c5 == null) {
                                value5 += getValue(c, m, _ranking_type5);
                            } else {
                                value5 += getValue(c, m, c5, subType5);
                            }
                        }

                        Vvalue.add(value);
                        Vvalue1.add(value1);
                        Vvalue2.add(value2);
                        Vvalue3.add(value3);
                        Vvalue4.add(value4);
                        Vvalue5.add(value5);
                    }
                }
            }

            while (Vvalue.size() > Tournament.getTournament().getParams()._teamMatesClansNumber) {
                int currentValue = Vvalue.get(0);
                // Search minimum and remove it
                for (int j = 1; j < Vvalue.size(); j++) {
                    currentValue = Math.min(currentValue, Vvalue.get(j));
                }
                for (int j = 0; j < Vvalue.size(); j++) {
                    if (Vvalue.get(j) == currentValue) {
                        Vvalue.remove(j);
                        Vvalue1.remove(j);
                        Vvalue2.remove(j);
                        Vvalue3.remove(j);
                        Vvalue4.remove(j);
                        Vvalue5.remove(j);
                        break;
                    }
                }
            }

            for (int j = 0; j < Vvalue.size(); j++) {
                cvalue += Vvalue.get(j);
                cvalue1 += Vvalue1.get(j);
                cvalue2 += Vvalue2.get(j);
                cvalue3 += Vvalue3.get(j);
                cvalue4 += Vvalue4.get(j);
                cvalue5 += Vvalue5.get(j);
            }

            _datas.add(new ObjectAnnexRanking(clans.get(i), cvalue, cvalue1, cvalue2, cvalue3, cvalue4, cvalue5));

        }

        /*_datas.clear();

        _datas = new Vector<ObjectAnnexRanking>();
        for (int i = 0; i < _objects.size(); i++) {
        Clan cl = (Clan) _objects.get(i);

        Vector<Coach> coaches = Tournament.getTournament().getCoachs();

        // Loop on first round for unique coach instance

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

        for (int k = 0; k < coaches.size(); k++) {
        Coach c = coaches.get(k);

        Match m = c._matchs.get(_round);

        Round round = Tournament.getTournament().getRounds().get(_round);

        int tmp = valuesMap.get(c);
        tmp += getValue(c, m, _criteria, _subtype);
        valuesMap.put(c, tmp);

        Criteria c1 = getCriteriaByValue(_ranking_type1);
        int subType1=getSubtypeByValue(_ranking_type1);
        if (c1 == null) {
        values1Map.put(c, values1Map.get(c) + getValue(c, m, _ranking_type1, round));
        } else {
        values1Map.put(c, values1Map.get(c) + getValue(c, m, c1, subType1));
        }

        Criteria c2 = getCriteriaByValue(_ranking_type2);
        int subType2=getSubtypeByValue(_ranking_type2);
        if (c2 == null) {
        values2Map.put(c, values2Map.get(c) + getValue(c, m, _ranking_type2, round));
        } else {
        values2Map.put(c, values2Map.get(c) + getValue(c, m, c2, subType2));
        }

        Criteria c3 = getCriteriaByValue(_ranking_type3);
        int subType3=getSubtypeByValue(_ranking_type3);
        if (c3 == null) {
        values3Map.put(c, values3Map.get(c) + getValue(c, m, _ranking_type3, round));
        } else {
        values3Map.put(c, values3Map.get(c) + getValue(c, m, c3, subType3));
        }

        Criteria c4 = getCriteriaByValue(_ranking_type4);
        int subType4=getSubtypeByValue(_ranking_type4);
        if (c4 == null) {
        values4Map.put(c, values4Map.get(c) + getValue(c, m, _ranking_type4, round));
        } else {
        values4Map.put(c, values4Map.get(c) + getValue(c, m, c4, subType4));
        }

        Criteria c5 = getCriteriaByValue(_ranking_type5);
        int subType5=getSubtypeByValue(_ranking_type5);
        if (c5 == null) {
        values5Map.put(c, values5Map.get(c) + getValue(c, m, _ranking_type5, round));
        } else {
        values5Map.put(c, values5Map.get(c) + getValue(c, m, c5, subType5));
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
        }*/
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
                return "#";
            case 1:
                return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("ClanKey");
            case 2:
                return _criteria._name;
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
