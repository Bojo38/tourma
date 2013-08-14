/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.tableModel;

import tourma.data.ObjectRanking;
import tourma.data.Match;
import java.util.Collections;
import java.util.ArrayList;
import tourma.data.Clan;
import tourma.data.Coach;
import tourma.data.Criteria;
import tourma.data.Tournament;
import tourma.utility.StringConstants;

/**
 *
 * @author Frederic Berger
 */
public class mjtRankingClan extends mjtRanking {

    boolean mRoundOnly=false;
    
    public mjtRankingClan(final int round, final int ranking_type1, final int ranking_type2, final int ranking_type3,final  int ranking_type4,final  int ranking_type5, final ArrayList<Clan> clans, final boolean round_only) {
        super(round, ranking_type1, ranking_type2, ranking_type3, ranking_type4, ranking_type5, clans);
        mRoundOnly=round_only;
        sortDatas();
    }

    protected void sortDatas() {

        mDatas.clear();
        mDatas = new ArrayList<ObjectRanking>();
        final ArrayList<Coach> coaches = Tournament.getTournament().getCoachs();
        final ArrayList<Clan> clans = (ArrayList<Clan>) mObjects;

        for (int i = 0; i < clans.size(); i++) {
            final ArrayList<Integer> Vvalue1 = new ArrayList<Integer>();
            final ArrayList<Integer> Vvalue2 = new ArrayList<Integer>();
            final ArrayList<Integer> Vvalue3 = new ArrayList<Integer>();
            final ArrayList<Integer> Vvalue4 = new ArrayList<Integer>();
            final ArrayList<Integer> Vvalue5 = new ArrayList<Integer>();

            int cvalue1 = 0;
            int cvalue2 = 0;
            int cvalue3 = 0;
            int cvalue4 = 0;
            int cvalue5 = 0;

            for (int k = 0; k < coaches.size(); k++) {
                final Coach c = coaches.get(k);

                if (c.mClan == clans.get(i)) {
                    int value1 = 0;
                    int value2 = 0;
                    int value3 = 0;
                    int value4 = 0;
                    int value5 = 0;
                    
                    int j=0;
                    
                    if (mRoundOnly)
                    {
                        j= mRound;
                    }
                    
                    while (j <= Math.min(c.mMatchs.size()-1,mRound)) {
                    //for (int j = 0; j <= Math.min(c.mMatchs.size(),mRound); j++) {
                        final Match m = c.mMatchs.get(j);

                        final Criteria c1 = getCriteriaByValue(mRankingType1);
                        final int subType1 = getSubtypeByValue(mRankingType1);
                        if (c1 == null) {
                            value1 += getValue(c, m, mRankingType1);
                        } else {
                            value1 += getValue(c, m, c1, subType1);
                        }

                        final Criteria c2 = getCriteriaByValue(mRankingType2);
                        final int subType2 = getSubtypeByValue(mRankingType2);
                        if (c2 == null) {
                            value2 += getValue(c, m, mRankingType2);
                        } else {
                            value2 += getValue(c, m, c2, subType2);
                        }

                        final Criteria c3 = getCriteriaByValue(mRankingType3);
                        final int subType3 = getSubtypeByValue(mRankingType3);
                        if (c3 == null) {
                            value3 += getValue(c, m, mRankingType3);
                        } else {
                            value3 += getValue(c, m, c3, subType3);
                        }

                        final Criteria c4 = getCriteriaByValue(mRankingType4);
                        final int subType4 = getSubtypeByValue(mRankingType4);
                        if (c4 == null) {
                            value4 += getValue(c, m, mRankingType4);
                        } else {
                            value4 += getValue(c, m, c4, subType4);
                        }

                        final Criteria c5 = getCriteriaByValue(mRankingType5);
                        final int subType5 = getSubtypeByValue(mRankingType5);
                        if (c5 == null) {
                            value5 += getValue(c, m, mRankingType5);
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

                while (Vvalue1.size() > Tournament.getTournament().getParams().mTeamMatesClansNumber) {
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
            mDatas.add(new ObjectRanking(clans.get(i), cvalue1, cvalue2, cvalue3, cvalue4, cvalue5));
        }

        /*mDatas.clear();
        mDatas = new ArrayList<ObjectRanking>();
        for (int i = 0; i < mObjects.size(); i++) {
        Clan cl = (Clan) mObjects.get(i);

        ArrayList<Coach> coaches = Tournament.getTournament().getCoachs();

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

        Match m = c.mMatchs.get(mRound);

        Round round = Tournament.getTournament().getRounds().get(mRound);

        Criteria c1 = getCriteriaByValue(mRankingType1);
        int subType1 = getSubtypeByValue(mRankingType1);
        if (c1 == null) {
        values1Map.put(c, values1Map.get(c) + getValue(c, m, mRankingType1, round));
        } else {
        values1Map.put(c, values1Map.get(c) + getValue(c, m, c1, subType1));
        }

        Criteria c2 = getCriteriaByValue(mRankingType2);
        int subType2 = getSubtypeByValue(mRankingType2);
        if (c2 == null) {
        values2Map.put(c, values2Map.get(c) + getValue(c, m, mRankingType2, round));
        } else {
        values2Map.put(c, values2Map.get(c) + getValue(c, m, c2, subType2));
        }

        Criteria c3 = getCriteriaByValue(mRankingType3);
        int subType3 = getSubtypeByValue(mRankingType3);
        if (c3 == null) {
        values3Map.put(c, values3Map.get(c) + getValue(c, m, mRankingType3, round));
        } else {
        values3Map.put(c, values3Map.get(c) + getValue(c, m, c3, subType3));
        }

        Criteria c4 = getCriteriaByValue(mRankingType4);
        int subType4 = getSubtypeByValue(mRankingType4);
        if (c4 == null) {
        values4Map.put(c, values4Map.get(c) + getValue(c, m, mRankingType4, round));
        } else {
        values4Map.put(c, values4Map.get(c) + getValue(c, m, c4, subType4));
        }

        Criteria c5 = getCriteriaByValue(mRankingType5);
        int subType5 = getSubtypeByValue(mRankingType5);
        if (c5 == null) {
        values5Map.put(c, values5Map.get(c) + getValue(c, m, mRankingType5, round));
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

        ArrayList<Integer> Values = new ArrayList<Integer>();
        ArrayList<Integer> Values1 = new ArrayList<Integer>();
        ArrayList<Integer> Values2 = new ArrayList<Integer>();
        ArrayList<Integer> Values3 = new ArrayList<Integer>();
        ArrayList<Integer> Values4 = new ArrayList<Integer>();
        ArrayList<Integer> Values5 = new ArrayList<Integer>();

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

        for (int k = 0; k < Math.min(Tournament.getTournament().getParams().mTeamMatesClansNumber, coaches.size()); k++) {
        value += Values.get(k);
        value1 += Values1.get(k);
        value2 += Values2.get(k);
        value3 += Values3.get(k);
        value4 += Values4.get(k);
        value5 += Values5.get(k);
        }

        mDatas.add(new ObjectRanking(cl, value1, value2, value3, value4, value5));
        }
         */

        Collections.sort(mDatas);
    }

    public int getColumnCount() {
        return 7;


    }

    public String getColumnName(final int col) {
        String result="";
        switch (col) {
            case 0:
                result= "#";
                break;
            case 1:
                result= java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("ClanKey");
                break;
            case 2:
                result= getRankingString(mRankingType1);
                break;
            case 3:
                result= getRankingString(mRankingType2);
                break;
            case 4:
                result= getRankingString(mRankingType3);
                break;
            case 5:
                result= getRankingString(mRankingType4);
                break;
            case 6:
                result= getRankingString(mRankingType5);
                break;
            default:
        }
        return result;


    }

    /*public static int getValue(Clan c, ArrayList<Match> v, int valueType, ArrayList<Round> rounds) {

    ArrayList<Coach> coaches = Tournament.getTournament().getCoachs();


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

    ArrayList<Integer> Values = new ArrayList<Integer>();


    for (int i = 0; i
    < coaches.size(); i++) {
    Values.add(valuesMap.get(coaches.get(i)));


    }

    int value = 0;


    int maximum = 0;
    Collections.sort(Values, Collections.reverseOrder());


    for (int i = 0; i
    < Math.min(Tournament.getTournament().getParams().mTeamMatesClansNumber, coaches.size()); i++) {
    value += Values.get(i);


    }
    return value;


    }*/
    public Object getValueAt(final int row,final int col) {
        Object object="";
        final ObjectRanking obj = (ObjectRanking) mDatas.get(row);
        switch (col) {
            case 0:
                object= row + 1;
                break;
            case 1:
                object= ((Clan) obj.getObject()).mName;
                break;
            case 2:
                object= obj.getValue1();
                break;
            case 3:
                object= obj.getValue2();
                break;
            case 4:
                object= obj.getValue3();
                break;
            case 5:
                object= obj.getValue4();
                break;
            case 6:
                object= obj.getValue5();
                break;
            default:
        }
        return object;

    }
}
