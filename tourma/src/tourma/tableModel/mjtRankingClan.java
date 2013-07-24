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
import tourma.data.Criteria;
import tourma.data.Tournament;

/**
 *
 * @author Frederic Berger
 */
public class mjtRankingClan extends mjtRanking {

    boolean _round_only=false;
    
    public mjtRankingClan(int round, int ranking_type1, int ranking_type2, int ranking_type3, int ranking_type4, int ranking_type5, Vector<Clan> clans, boolean round_only) {
        super(round, ranking_type1, ranking_type2, ranking_type3, ranking_type4, ranking_type5, clans);
        _round_only=round_only;
        sortDatas();
    }

    protected void sortDatas() {

        _datas.clear();
        _datas = new Vector<ObjectRanking>();
        Vector<Coach> coaches = Tournament.getTournament().getCoachs();
        Vector<Clan> clans = (Vector<Clan>) _objects;

        for (int i = 0; i < clans.size(); i++) {
            Vector<Integer> Vvalue1 = new Vector<Integer>();
            Vector<Integer> Vvalue2 = new Vector<Integer>();
            Vector<Integer> Vvalue3 = new Vector<Integer>();
            Vector<Integer> Vvalue4 = new Vector<Integer>();
            Vector<Integer> Vvalue5 = new Vector<Integer>();

            int cvalue1 = 0;
            int cvalue2 = 0;
            int cvalue3 = 0;
            int cvalue4 = 0;
            int cvalue5 = 0;

            for (int k = 0; k < coaches.size(); k++) {
                Coach c = coaches.get(k);

                if (c._clan == clans.get(i)) {
                    int value1 = 0;
                    int value2 = 0;
                    int value3 = 0;
                    int value4 = 0;
                    int value5 = 0;
                    
                    int j=0;
                    
                    if (_round_only)
                    {
                        j= _round;
                    }
                    
                    while (j <= Math.min(c._matchs.size(),_round)) {
                    //for (int j = 0; j <= Math.min(c._matchs.size(),_round); j++) {
                        Match m = c._matchs.get(j);

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
                        j++;
                    }
                    Vvalue1.add(value1);
                    Vvalue2.add(value2);
                    Vvalue3.add(value3);
                    Vvalue4.add(value4);
                    Vvalue5.add(value5);
                }

                while (Vvalue1.size() > Tournament.getTournament().getParams()._teamMatesClansNumber) {
                    int currentValue = Vvalue1.get(0);
                    // Search minimum and remove it
                    for (int j = 1; j < Vvalue1.size(); j++) {
                        currentValue = Math.min(currentValue, Vvalue1.get(j));
                    }
                    for (int j = 0; j < Vvalue1.size(); j++) {
                        if (Vvalue1.get(j) == currentValue) {
                            Vvalue1.remove(j);
                            Vvalue2.remove(j);
                            Vvalue3.remove(j);
                            Vvalue4.remove(j);
                            Vvalue5.remove(j);
                            break;
                        }
                    }
                }

            }
            for (int j = 0; j < Vvalue1.size(); j++) {
                cvalue1 += Vvalue1.get(j);
                cvalue2 += Vvalue2.get(j);
                cvalue3 += Vvalue3.get(j);
                cvalue4 += Vvalue4.get(j);
                cvalue5 += Vvalue5.get(j);
            }
            _datas.add(new ObjectRanking(clans.get(i), cvalue1, cvalue2, cvalue3, cvalue4, cvalue5));
        }

        /*_datas.clear();
        _datas = new Vector<ObjectRanking>();
        for (int i = 0; i < _objects.size(); i++) {
        Clan cl = (Clan) _objects.get(i);

        Vector<Coach> coaches = Tournament.getTournament().getCoachs();

        //Loop on first round for unique coach instance

        HashMap<Coach, Integer> values1Map = new HashMap<Coach, Integer>();
        HashMap<Coach, Integer> values2Map = new HashMap<Coach, Integer>();
        HashMap<Coach, Integer> values3Map = new HashMap<Coach, Integer>();
        HashMap<Coach, Integer> values4Map = new HashMap<Coach, Integer>();
        HashMap<Coach, Integer> values5Map = new HashMap<Coach, Integer>();

        for (int k = 0; k < coaches.size(); k++) {
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

        Criteria c1 = getCriteriaByValue(_ranking_type1);
        int subType1 = getSubtypeByValue(_ranking_type1);
        if (c1 == null) {
        values1Map.put(c, values1Map.get(c) + getValue(c, m, _ranking_type1, round));
        } else {
        values1Map.put(c, values1Map.get(c) + getValue(c, m, c1, subType1));
        }

        Criteria c2 = getCriteriaByValue(_ranking_type2);
        int subType2 = getSubtypeByValue(_ranking_type2);
        if (c2 == null) {
        values2Map.put(c, values2Map.get(c) + getValue(c, m, _ranking_type2, round));
        } else {
        values2Map.put(c, values2Map.get(c) + getValue(c, m, c2, subType2));
        }

        Criteria c3 = getCriteriaByValue(_ranking_type3);
        int subType3 = getSubtypeByValue(_ranking_type3);
        if (c3 == null) {
        values3Map.put(c, values3Map.get(c) + getValue(c, m, _ranking_type3, round));
        } else {
        values3Map.put(c, values3Map.get(c) + getValue(c, m, c3, subType3));
        }

        Criteria c4 = getCriteriaByValue(_ranking_type4);
        int subType4 = getSubtypeByValue(_ranking_type4);
        if (c4 == null) {
        values4Map.put(c, values4Map.get(c) + getValue(c, m, _ranking_type4, round));
        } else {
        values4Map.put(c, values4Map.get(c) + getValue(c, m, c4, subType4));
        }

        Criteria c5 = getCriteriaByValue(_ranking_type5);
        int subType5 = getSubtypeByValue(_ranking_type5);
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

        _datas.add(new ObjectRanking(cl, value1, value2, value3, value4, value5));
        }
         */

        Collections.sort(_datas);
    }

    public int getColumnCount() {
        return 7;


    }

    public String getColumnName(int col) {
        switch (col) {
            case 0:
                return "#";
            case 1:
                return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("ClanKey");
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

    /*public static int getValue(Clan c, Vector<Match> v, int valueType, Vector<Round> rounds) {

    Vector<Coach> coaches = Tournament.getTournament().getCoachs();


    HashMap<Coach, Integer> valuesMap = new HashMap<Coach, Integer>();



    switch (valueType) {
    case Parameters.C_RANKING_POINTS:
    for (int i = 0; i
    < coaches.size(); i++) {
    for (int j = 0; j
    < v.size(); j++) {
    valuesMap.put(coaches.get(i), valuesMap.get(coaches.get(i)) + getPointsByCoach(coaches.get(i), v.get(j)));


    }
    }
    break;



    case Parameters.C_RANKING_NONE:
    for (int i = 0; i
    < coaches.size(); i++) {
    valuesMap.put(coaches.get(i), 0);


    }
    break;


    case Parameters.C_RANKING_OPP_POINTS:
    for (int i = 0; i
    < coaches.size(); i++) {
    for (int j = 0; j
    < v.size(); j++) {
    valuesMap.put(coaches.get(i), valuesMap.get(coaches.get(i)) + getOppPointsByCoach(coaches.get(i), rounds));


    }
    }
    break;



    case Parameters.C_RANKING_VND:
    for (int i = 0; i
    < coaches.size(); i++) {
    for (int j = 0; j
    < v.size(); j++) {
    valuesMap.put(coaches.get(i), valuesMap.get(coaches.get(i)) + getVNDByCoach(coaches.get(i), v.get(j)));


    }
    }

    break;


    }

    Vector<Integer> Values = new Vector<Integer>();


    for (int i = 0; i
    < coaches.size(); i++) {
    Values.add(valuesMap.get(coaches.get(i)));


    }

    int value = 0;


    int maximum = 0;
    Collections.sort(Values, Collections.reverseOrder());


    for (int i = 0; i
    < Math.min(Tournament.getTournament().getParams()._teamMatesClansNumber, coaches.size()); i++) {
    value += Values.get(i);


    }
    return value;


    }*/
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
