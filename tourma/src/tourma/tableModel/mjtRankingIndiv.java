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
import tourma.data.Criteria;
import tourma.data.Tournament;

/**
 *
 * @author Frederic Berger
 */
public class mjtRankingIndiv extends mjtRanking {

    boolean _teamTournament;
    boolean _positive;

    public mjtRankingIndiv(int round, int ranking_type1, int ranking_type2, int ranking_type3, int ranking_type4, int ranking_type5, Vector<Coach> coachs, boolean tournament) {
        super(round, ranking_type1, ranking_type2, ranking_type3, ranking_type4, ranking_type5, coachs);
        _teamTournament = tournament;
        sortDatas();
    }

    protected void sortDatas() {
        _datas.clear();
        _datas = new Vector<ObjectRanking>();

        Vector<Round> rounds = Tournament.getTournament().getRounds();

        for (int i = 0; i < _objects.size(); i++) {
            Coach c = (Coach) _objects.get(i);
            int value1 = 0;
            int value2 = 0;
            int value3 = 0;
            int value4 = 0;
            int value5 = 0;

            for (int j = 0; j <= c._matchs.size() - 1; j++) {

                Match m = c._matchs.get(j);
                boolean bFound = false;
                for (int l = 0; (l <= _round) && (!bFound); l++) {
                    Round r = rounds.get(l);
                    if (r.getMatchs().contains(m)) {
                        bFound = true;
                    }
                }
                // test if match is in round
                if (bFound) {
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
                        value2 += getValue(c, m, c1, subType2);
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


                    /*value1 = getValue(c, matchs, _ranking_type1, _rounds);
                    value2 = getValue(c, matchs, _ranking_type2, _rounds);
                    value3 = getValue(c, matchs, _ranking_type3, _rounds);
                    value4 = getValue(c, matchs, _ranking_type4, _rounds);
                    value5 = getValue(c, matchs, _ranking_type5, _rounds);*/
                }
            }
            _datas.add(new ObjectRanking(c, value1, value2, value3, value4, value5));
        }

        Collections.sort(_datas);
    }

    /*protected int getValues(Coach c, int ranking_type, int round) {
    int value = 0;

    Criteria criteria=getCriteriaByValue(ranking_type);
    if (criteria!=null)
    {
    getValue(c, c._matchs.get(round), ranking_type, round);
    }
    else
    {
    getValue(c, c._matchs.get(round), criteria, _positive);
    }

    return value;
    }*/
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
                return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("ClanKey");
            }
        }

        switch (cl) {
            case 0:
                return "#";
            case 1:
                return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("Team");
            case 2:
                return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("Coach");
            case 3:
                return java.util.ResourceBundle.getBundle("tourma/languages/language").getString("Roster");
            case 4:
                return getRankingString(_ranking_type1);
            case 5:
                return getRankingString(_ranking_type2);
            case 6:
                return getRankingString(_ranking_type3);
            case 7:
                return getRankingString(_ranking_type4);
            case 8:
                return getRankingString(_ranking_type5);
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
                return ((Coach) obj.getObject())._roster._name;
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
