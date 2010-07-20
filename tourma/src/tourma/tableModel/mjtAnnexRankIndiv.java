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

/**
 *
 * @author Frederic Berger
 */
public class mjtAnnexRankIndiv extends mjtAnnexRank {

    boolean _teamTournament;
    public mjtAnnexRankIndiv(Vector<Round> rounds, int ranking_type, Vector<Coach> coachs, boolean full, int ranking_type1, int ranking_type2, int ranking_type3, int ranking_type4, int ranking_type5, boolean teamTournament) {
        super(rounds, ranking_type, coachs, full, ranking_type1, ranking_type2, ranking_type3, ranking_type4, ranking_type5);
        _teamTournament=teamTournament;
    }

   protected void sortDatas()
   {
       _datas.clear();
       Vector<Match> matchs = new Vector<Match>();
        for (int i = 0; i < _rounds.size(); i++) {
            for (int j = 0; j < _rounds.get(i).getMatchs().size(); j++) {
                matchs.add(_rounds.get(i).getMatchs().get(j));
            }
        }

        _datas = new Vector<ObjectAnnexRanking>();

        for (int i = 0; i < _objects.size(); i++) {
            Coach c = (Coach)_objects.get(i);
            int value = 0;
            int value1 = 0;
            int value2 = 0;
            int value3 = 0;
            int value4 = 0;
            int value5 = 0;
            for (int j = 0; j < matchs.size(); j++) {
                Match m = matchs.get(j);
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
                    }
                }
                if (m._coach2 == c) {
                    switch (_ranking_type) {
                        case C_MOST_TD_POS:
                            value += m._td2;
                            break;
                        case C_MOST_TD_NEG:
                            value += m._td1;
                            ;
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
                    }
                }
                value1 += getValue(c, m, _ranking_type1,_rounds);
                value2 += getValue(c, m, _ranking_type2,_rounds);
                value3 += getValue(c, m, _ranking_type3,_rounds);
                value4 += getValue(c, m, _ranking_type4,_rounds);
                value5 += getValue(c, m, _ranking_type5,_rounds);
            }
            _datas.add(new ObjectAnnexRanking(c, value,value1,value2,value3,value4,value5));
        }

        Collections.sort(_datas);
   }
    
    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public String getColumnName(int col) {
        switch (col) {
            case 0:
                return "N";
            case 1:
                return "Equipe";
            case 2:
                return "Coach";
            case 3:
                return "Roster";
            case 4:
                switch (_ranking_type) {
                    case C_MOST_TD_POS:
                        return "Tds";
                    case C_MOST_TD_NEG:
                        return "Tds";
                    case C_MOST_SOR_POS:
                        return "Sor";
                    case C_MOST_SOR_NEG:
                        return "Sor";
                    case C_MOST_FOUL_POS:
                        return "Foul";
                    case C_MOST_FOUL_NEG:
                        return "Foul";
                }
        }
        return "";
    }

    @Override
    public Object getValueAt(int row, int col) {
        
        ObjectAnnexRanking obj=(ObjectAnnexRanking) _datas.get(row);

        switch (col) {
            case 0:
                return row + 1;
            case 1:
                return ((Coach)obj.getObject())._team;
            case 2:
                return ((Coach)obj.getObject())._name;
            case 3:
                return ((Coach)obj.getObject())._roster;
            case 4:
                return obj.getValue();
        }
        return "";
    }


}
