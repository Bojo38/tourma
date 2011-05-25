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
import tourma.data.Criteria;
import tourma.data.ObjectAnnexRanking;
import tourma.data.Team;
import tourma.data.Tournament;

/**
 *
 * @author Frederic Berger
 */
public class mjtAnnexRankTeam extends mjtAnnexRank {

    public mjtAnnexRankTeam(int round, Criteria criteria, int subtype, Vector<Team> teams, boolean full, int ranking_type1, int ranking_type2, int ranking_type3, int ranking_type4, int ranking_type5) {
        super(round, criteria, subtype, teams, full, ranking_type1, ranking_type2, ranking_type3, ranking_type4, ranking_type5);
    }

    protected void sortDatas() {
        _datas.clear();

        _datas = new Vector<ObjectAnnexRanking>();
        for (int i = 0; i < _objects.size(); i++) {
            Team t = (Team) _objects.get(i);
            int value = 0;
            int value1 = 0;
            int value2 = 0;
            int value3 = 0;
            int value4 = 0;
            int value5 = 0;
            for (int k = 0; k < t._coachs.size(); k++) {
                Coach c = t._coachs.get(k);
                for (int j = 0; j <= Math.min(c._matchs.size(),_round); j++) {
                    Match m = c._matchs.get(j);

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
                if (_subtype == 0) {
                    return _criteria._name + " Coach";
                } else {
                    if (_subtype == 1) {
                        return _criteria._name + " Adversaire";
                    } else {
                        return _criteria._name + " Difference";
                    }
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
