/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data.ranking;

import java.rmi.RemoteException;
import java.util.ArrayList;
import tourma.data.Criterion;
import tourma.data.Criterion;
import tourma.data.Formula;
import tourma.data.Formula;
import tourma.data.ObjectRanking;
import tourma.data.ObjectRanking;
import tourma.data.Parameters;
import tourma.data.Parameters;
import tourma.data.Tournament;
import tourma.data.Tournament;
import tourma.languages.Translate;
import tourma.tableModel.MjtRanking;
import tourma.utility.StringConstants;
import tourma.utils.display.IRanked;

/**
 *
 * @author WFMJ7631
 */
abstract public class Ranking implements IRanked {

    String mDetail="";
    
    public String getDetail()
    {
        return mDetail;
    }
    
    public void setDetail(String d)
    {
        mDetail=d;
    }
    
    public Ranking(final int round, final int ranking_type1, final int ranking_type2, final int ranking_type3, final int ranking_type4, final int ranking_type5, final ArrayList objects, final boolean roundOnly) {
        mRound = round;
        mRankingType1 = ranking_type1;
        mRankingType2 = ranking_type2;
        mRankingType3 = ranking_type3;
        mRankingType4 = ranking_type4;
        mRankingType5 = ranking_type5;
        mObjects = objects;
        mRoundOnly = roundOnly;
    }

    @Override
    public int getRowCount() {
        return getCount();
    }
    /**
     * Rank only on current round ?
     */
    @SuppressWarnings("ProtectedField")
    protected boolean mRoundOnly = false;

    /**
     *
     * @param valueType
     * @return
     */
    public static int getSubtypeByValue(final int valueType) {
        int subType = -1;
        if (valueType > Parameters.C_MAX_RANKING) {
            final int value = valueType - Parameters.C_MAX_RANKING - 1;
            subType = value % 3;
        }
        return subType;
    }

    public static String convertVND(int value) {
        String vnd = "";
        int nb_vict = value / 1000000;
        int nb_draw = (value % 1000000) / 1000;
        int nb_loss = (value % 1000000) % 1000;
        vnd = Integer.toString(nb_vict) + "/" + Integer.toString(nb_draw) + "/" + Integer.toString(nb_loss);
        return vnd;
    }

    public static Formula getFormulaByValue(final int valueType) {
        Formula formula = null;

        if (valueType > Parameters.C_MAX_RANKING) {
            final int value = valueType - Parameters.C_MAX_RANKING - 1;

            if (value / 3 >= Tournament.getTournament().getParams().getCriteriaCount()) {
                Parameters params = Tournament.getTournament().getParams();
                formula = params.getFormula(value / 3 - Tournament.getTournament().getParams().getCriteriaCount());
            }
        }
        return formula;
    }

    /**
     *
     * @param valueType
     * @return
     */
    public static Criterion getCriteriaByValue(final int valueType) {
        Criterion criteria = null;

        if (valueType > Parameters.C_MAX_RANKING) {
            final int value = valueType - Parameters.C_MAX_RANKING - 1;

            if (value / 3 < Tournament.getTournament().getParams().getCriteriaCount()) {
                Parameters params = Tournament.getTournament().getParams();
                criteria = params.getCriteria(value / 3);
            }

        }
        return criteria;
    }

    public static int getRankingFromString(String ranking, ArrayList<String> criterias) {

        if (ranking.equals(Translate.translate(Translate.CS_Points))) {
            return Parameters.C_RANKING_POINTS;
        }
        if (ranking.equals(Translate.translate(Translate.CS_Points_Without_Bonus))) {
            return Parameters.C_RANKING_POINTS_WITHOUT_BONUS;
        }
        if (ranking.equals(Translate.translate(Translate.CS_Bonus_Points))) {
            return Parameters.C_RANKING_BONUS_POINTS;
        }

        if (ranking.equals(Translate.translate(Translate.CS_Nothing))) {
            return Parameters.C_RANKING_NONE;
        }
        if (ranking.equals(Translate.translate(Translate.CS_ACCR_Opponent_Points))) {
            return Parameters.C_RANKING_OPP_POINTS;
        }
        if (ranking.equals(Translate.translate(Translate.CS_ACCR_Opponent_Points_Without_Bonus))) {
            return Parameters.C_RANKING_OPP_POINTS_OTHER_MATCHS;
        }
        if (ranking.equals(Translate.translate(Translate.CS_ACCR_Victory_Drawn_Lost))) {
            return Parameters.C_RANKING_VND;
        }
        if (ranking.equals(Translate.translate(Translate.CS_ELO))) {
            return Parameters.C_RANKING_ELO;
        }

        if (ranking.equals(Translate.translate(Translate.CS_OpponentsElo))) {
            return Parameters.C_RANKING_ELO_OPP;
        }
        if (ranking.equals(Translate.translate(Translate.CS_MatchCount))) {
            return Parameters.C_RANKING_NB_MATCHS;
        }
        if (ranking.equals(Translate.translate(Translate.CS_TablesPoints))) {
            return Parameters.C_RANKING_TABLES;
        }
        if (ranking.equals(Translate.translate(Translate.CS_HeadByHead))) {
            return Parameters.C_RANKING_HEAD_BY_HEAD;
        }

        if (ranking.endsWith(" " + Translate.translate(Translate.CS_Coach))) {
            String tmp = ranking.replace(" " + Translate.translate(Translate.CS_Coach), StringConstants.CS_NULL);
            int i = criterias.indexOf(tmp);
            return Parameters.C_MAX_RANKING + 1 + (i * 3);

        }
        if (ranking.endsWith(" " + Translate.translate(Translate.CS_Opponent))) {
            String tmp = ranking.replace(" " + Translate.translate(Translate.CS_Opponent), StringConstants.CS_NULL);
            int i = criterias.indexOf(tmp);
            return Parameters.C_MAX_RANKING + 1 + (i * 3) + 1;
        }
        if (ranking.endsWith(" " + Translate.translate(Translate.CS_Difference))) {
            String tmp = ranking.replace(" " + Translate.translate(Translate.CS_Difference), StringConstants.CS_NULL);
            int i = criterias.indexOf(tmp);
            return Parameters.C_MAX_RANKING + 1 + (i * 3) + 2;
        }

        return Parameters.C_RANKING_NONE;
    }

    /**
     *
     * @param rankingType
     * @return
     */
    public static String getRankingString(final int rankingType) {
        String result = StringConstants.CS_NULL;
        final Criterion c = getCriteriaByValue(rankingType);
        if (c == null) {
            Formula f = getFormulaByValue(rankingType);
            if (f == null) {
                switch (rankingType) {
                    case Parameters.C_RANKING_POINTS:
                        result = Translate.translate(Translate.CS_Points);
                        break;
                    case Parameters.C_RANKING_POINTS_WITHOUT_BONUS:
                        result = Translate.translate(Translate.CS_Points_Without_Bonus);
                        break;
                    case Parameters.C_RANKING_BONUS_POINTS:
                        result = Translate.translate(Translate.CS_Bonus_Points);
                        break;
                    case Parameters.C_RANKING_NONE:
                        result = Translate.translate(Translate.CS_Nothing);
                        break;
                    case Parameters.C_RANKING_OPP_POINTS:
                        result = Translate.translate(Translate.CS_ACCR_Opponent_Points);
                        break;
                    case Parameters.C_RANKING_OPP_POINTS_OTHER_MATCHS:
                        result = Translate.translate(Translate.CS_ACCR_Opponent_Points_Without_Bonus);
                        break;
                    case Parameters.C_RANKING_VND:
                        result = Translate.translate(Translate.CS_ACCR_Victory_Drawn_Lost);
                        break;
                    case Parameters.C_RANKING_ELO:
                        result = Translate.translate(Translate.CS_ELO);
                        break;
                    case Parameters.C_RANKING_ELO_OPP:
                        result = Translate.translate(Translate.CS_OpponentsElo);
                        break;
                    case Parameters.C_RANKING_NB_MATCHS:
                        result = Translate.translate(Translate.CS_MatchCount);
                        break;
                    case Parameters.C_RANKING_TABLES:
                        result = Translate.translate(Translate.CS_TablesPoints);
                        break;
                    case Parameters.C_RANKING_HEAD_BY_HEAD:
                        result = Translate.translate(Translate.CS_HeadByHead);
                        break;
                    case Parameters.C_RANKING_TIER:
                        result = Translate.translate(Translate.CS_Tier);
                        break;
                    case Parameters.C_RANKING_TEAMMATES_POINTS:
                        result = Translate.translate(Translate.CS_Teammates_Points);
                        break;
                    case Parameters.C_RANKING_TEAMMATES_VND:
                        result = Translate.translate(Translate.CS_Teammates_VND);
                        break;
                    default:
                }
            } else {
                result = f.getName();
            }
        } else {
            final int subRanking = getSubtypeByValue(rankingType);
            switch (subRanking) {
                case Parameters.C_RANKING_SUBTYPE_POSITIVE:
                    result = c.getName() + " " + Translate.translate(Translate.CS_Coach);
                    break;
                case Parameters.C_RANKING_SUBTYPE_NEGATIVE:
                    result = c.getName() + " " + Translate.translate(Translate.CS_Opponent);
                    break;
                case Parameters.C_RANKING_SUBTYPE_DIFFERENCE:
                    result = c.getName() + " " + Translate.translate(Translate.CS_Difference);
                    break;
                default:
            }
        }
        return result;
    }

    protected int getValueFromArray(int rt, ArrayList<Integer> av) {
        int value = 0;

        if (av.size() > 0) {
            if ((rt == Parameters.C_RANKING_ELO)
                    || (rt == Parameters.C_RANKING_NB_MATCHS)) {
                value = av.get(av.size() - 1);
            } else {
                for (Integer i : av) {
                    value += i;
                }
            }
        }

        return value;
    }

    protected void removeMinValue(ArrayList<Integer> aValue) {
        int min = Integer.MAX_VALUE;
        int index = 0;
        if (aValue.size() > 0) {
            for (int k = 0; k < aValue.size(); k++) {
                min = Math.min(min, aValue.get(k));
                if (min == aValue.get(k)) {
                    index = k;
                }
            }
            aValue.remove(index);
        }
    }

    protected void removeMaxValue(ArrayList<Integer> aValue) {
        int max = Integer.MIN_VALUE;
        int index = 0;
        if (aValue.size() > 0) {
            for (int k = 0; k < aValue.size(); k++) {
                max = Math.max(max, aValue.get(k));
                if (max == aValue.get(k)) {
                    index = k;
                }
            }
            aValue.remove(index);
        }
    }

    public int getRound() {
        return mRound;
    }

    /**
     *
     * @param index
     * @param valIndex
     * @return
     */
    @Override
    public int getSortedValue(int index, int valIndex) {
        ObjectRanking obj = (ObjectRanking) mDatas.get(index);

        switch (valIndex) {

            case 1:
                return obj.getValue1();
            case 2:
                return obj.getValue2();
            case 3:
                return obj.getValue3();
            case 4:
                return obj.getValue4();
            case 5:
                return obj.getValue5();
            default:
                return 0;
        }

    }

    /**
     *
     * @param index
     * @return
     */
    @Override
    public ObjectRanking getSortedObject(int index) {
        ObjectRanking obj = (ObjectRanking) mDatas.get(index);
        return obj;
    }

    /**
     * Abstract sortDatas
     */
    protected abstract void sortDatas() throws RemoteException;
    /**
     * Current Round
     */
    @SuppressWarnings("ProtectedField")
    protected int mRound;
    /**
     * Objects to sort
     */
    @SuppressWarnings("ProtectedField")
    protected ArrayList mObjects;

    public int getRankingType1() {
        return mRankingType1;
    }

    public int getRankingType2() {
        return mRankingType2;
    }

    public int getRankingType3() {
        return mRankingType3;
    }

    public int getRankingType4() {
        return mRankingType4;
    }

    public Object getObject(int i) {
        return mObjects.get(i);
    }

    /**
     * Ranking type #1
     */
    public int getRankingType5() {
        return mRankingType5;
    }

    @SuppressWarnings("ProtectedField")
    protected int mRankingType1;
    /**
     * Ranking type #2
     */
    @SuppressWarnings("ProtectedField")
    protected int mRankingType2;
    /**
     * Ranking type #3
     */
    @SuppressWarnings("ProtectedField")
    protected int mRankingType3;
    /**
     * Ranking type #4
     */
    @SuppressWarnings("ProtectedField")
    protected int mRankingType4;
    /**
     * Ranking type #5
     */
    @SuppressWarnings("ProtectedField")
    protected int mRankingType5;
    /**
     * IRanked datas
     */
    @SuppressWarnings("ProtectedField")
    protected ArrayList mDatas = new ArrayList();

    public int getCount() {
        return mDatas.size();
    }

}
