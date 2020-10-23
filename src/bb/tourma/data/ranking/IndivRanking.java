/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bb.tourma.data.ranking;

import java.util.ArrayList;
import java.util.Collections;
import org.jdom.DataConversionException;
import org.jdom.Element;
import bb.tourma.data.Coach;
import bb.tourma.data.CoachMatch;
import bb.tourma.data.Criterion;
import bb.tourma.data.ObjectRanking;
import bb.tourma.data.Parameters;
import bb.tourma.data.Pool;
import bb.tourma.data.Round;
import bb.tourma.data.Tournament;
import bb.tourma.data.Value;
import bb.tourma.utility.StringConstants;

/**
 *
 * @author WFMJ7631
 */
public class IndivRanking extends Ranking {

    public IndivRanking(Element e) {
        super(e);
        setXMLElement(e);
    }

    public IndivRanking(final int round,
            Parameters params,
            final ArrayList objects,
            final boolean teamTour,
            final boolean roundOnly,
            final boolean forPool,
            final boolean forCup) {

        super(round,
                params.getRankingIndiv1(),
                params.getRankingIndiv2(),
                params.getRankingIndiv3(),
                params.getRankingIndiv4(),
                params.getRankingIndiv5(),
                objects,
                roundOnly);
        mTeamTournament = teamTour;
        mForCup = forCup;
        mForPool = forPool;
   //     sortDatas();

    }

    public IndivRanking(final int round,
            final int ranking_type1,
            final int ranking_type2,
            final int ranking_type3,
            final int ranking_type4,
            final int ranking_type5,
            final ArrayList objects,
            final boolean teamTour,
            final boolean roundOnly,
            final boolean forPool,
            final boolean forCup) {

        super(round,
                ranking_type1,
                ranking_type2,
                ranking_type3,
                ranking_type4,
                ranking_type5,
                objects,
                roundOnly);
        mTeamTournament = teamTour;
        mForCup = forCup;
        mForPool = forPool;
//        sortDatas();
    }

    protected boolean mTeamTournament;

    public boolean isTeamTournament() {
        return mTeamTournament;
    }

    public void setTeamTournament(boolean mTeamTournament) {
        this.mTeamTournament = mTeamTournament;
    }

    public boolean isForPool() {
        return mForPool;
    }

    public void setForPool(boolean mForPool) {
        this.mForPool = mForPool;
    }

    public boolean isForCup() {
        return mForCup;
    }

    public void setForCup(boolean mForCup) {
        this.mForCup = mForCup;
    }
    protected boolean mForPool;
    protected boolean mForCup;

    /**
     *
     */
    @Override
    public void sortDatas() {
        mDatas.clear();
        mDatas = new ArrayList();

        Tournament tour=Tournament.getTournament();
        final ArrayList<Round> rounds = new ArrayList<>();

        if (mRoundOnly) {
            if (mRound < tour.getRoundsCount()) {
                rounds.add(tour.getRound(mRound));
            }

        } else {
            for (int l = 0; (l <= mRound); l++) {
                if (l < tour.getRoundsCount()) {
                    rounds.add(tour.getRound(l));
                }
            }
        }

        for (int i = 0; i < mObjects.size(); i++) {
            final Coach c = (Coach) mObjects.get(i);
            if (c.isActive()) {
                int value1 = 0;
                int value2 = 0;
                int value3 = 0;
                int value4 = 0;
                int value5 = 0;

                ArrayList<Integer> aValue1 = new ArrayList<>();
                ArrayList<Integer> aValue2 = new ArrayList<>();
                ArrayList<Integer> aValue3 = new ArrayList<>();
                ArrayList<Integer> aValue4 = new ArrayList<>();
                ArrayList<Integer> aValue5 = new ArrayList<>();

                if (c.getMatchCount() > 0) {
                    for (int j = 0; j <= c.getMatchCount() - 1; j++) {

                        final CoachMatch m = (CoachMatch) c.getMatch(j);
                        if (!m.isValues_computed()) {
                            m.recomputeValues();
                        }
                        for (int l = 0; l < rounds.size(); l++) {
                            final Round r = rounds.get(l);
                            if (r.getCoachMatchs().contains(m)) {
                                aValue1.add(m.getValue(1, c));
                                aValue2.add(m.getValue(2, c));
                                aValue3.add(m.getValue(3, c));
                                aValue4.add(m.getValue(4, c));
                                aValue5.add(m.getValue(5, c));
                            }
                        }
                    }

                    if (Tournament.getTournament().getParams().isUseBestResultIndiv()) {
                        while (aValue1.size() > Tournament.getTournament().getParams().getBestResultIndiv()) {
                            removeMinValue(aValue1);
                        }
                        while (aValue2.size() > Tournament.getTournament().getParams().getBestResultIndiv()) {
                            removeMinValue(aValue2);
                        }
                        while (aValue3.size() > Tournament.getTournament().getParams().getBestResultIndiv()) {
                            removeMinValue(aValue3);
                        }
                        while (aValue4.size() > Tournament.getTournament().getParams().getBestResultIndiv()) {
                            removeMinValue(aValue4);
                        }
                        while (aValue5.size() > Tournament.getTournament().getParams().getBestResultIndiv()) {
                            removeMinValue(aValue5);
                        }
                    } else {
                        if (Tournament.getTournament().getParams().isExceptBestAndWorstIndiv()) {
                            removeMaxValue(aValue1);
                            removeMinValue(aValue1);
                            removeMaxValue(aValue2);
                            removeMinValue(aValue2);
                            removeMaxValue(aValue3);
                            removeMinValue(aValue3);
                            removeMaxValue(aValue4);
                            removeMinValue(aValue4);
                            removeMaxValue(aValue5);
                            removeMinValue(aValue5);
                        }
                    }

                    value1 = getValueFromArray(mRankingType1, aValue1);
                    value2 = getValueFromArray(mRankingType2, aValue2);
                    value3 = getValueFromArray(mRankingType3, aValue3);
                    value4 = getValueFromArray(mRankingType4, aValue4);
                    value5 = getValueFromArray(mRankingType5, aValue5);
                }
                mDatas.add(new ObjectRanking(c, value1, value2, value3, value4, value5));
            }
        }

        // If one of sorting parameteetr is head by head
        for (int i = 1; i < 5; i++) {
            if (Tournament.getTournament().getParams().getIndivRankingType(i) == Parameters.C_RANKING_HEAD_BY_HEAD) {
                // Loop on ranking to sort according to own match of the coaches who are ties.
                // The head by head ranking is nonsense a first ranking type.

                // Now check equalities
                for (int j = 0; j < mDatas.size(); j++) {
                    ObjectRanking or = (ObjectRanking) mDatas.get(j);

                    switch (i) {
                        case 1:
                            or.setValue2(0);
                            break;
                        case 2:
                            or.setValue3(0);
                            break;
                        case 3:
                            or.setValue4(0);
                            break;
                        case 4:
                            or.setValue5(0);
                            break;
                    }
                }
                for (int j = 0; j < mDatas.size(); j++) {
                    ObjectRanking or = (ObjectRanking) mDatas.get(j);

                    // find objects with same rankings
                    for (int k = j + 1; k < mDatas.size(); k++) {
                        ObjectRanking or2 = (ObjectRanking) mDatas.get(k);

                        // If previous ranking is the same;
                        if (i == 1) {
                            if (or.getValue1() == or2.getValue1()) {
                                updateHeadByHeadValue(mRound, i, or, or2);
                            }
                        }
                        if (i == 2) {
                            if ((or.getValue1() == or2.getValue1()) && (or.getValue2() == or2.getValue2())) {
                                updateHeadByHeadValue(mRound, i, or, or2);
                            }
                        }
                        if (i == 3) {
                            if ((or.getValue1() == or2.getValue1()) && (or.getValue2() == or2.getValue2()) && (or.getValue3() == or2.getValue3())) {
                                updateHeadByHeadValue(mRound, i, or, or2);
                            }
                        }
                        if (i == 4) {
                            if ((or.getValue1() == or2.getValue1()) && (or.getValue2() == or2.getValue2()) && (or.getValue3() == or2.getValue3()) && (or.getValue4() == or2.getValue4())) {
                                updateHeadByHeadValue(mRound, i, or, or2);
                            }
                        }
                    }
                }

                // Now checking that all the competitors hava played against all the other ones
                for (int j = 0; j < mDatas.size(); j++) {
                    ObjectRanking or = (ObjectRanking) mDatas.get(j);
                    if (or.getValue(i) == -1) {
                        continue;
                    }
                    ArrayList<ObjectRanking> ors = new ArrayList<>();
                    // find objects with same rankings
                    for (int k = 0; k < mDatas.size(); k++) {
                        ObjectRanking or2 = (ObjectRanking) mDatas.get(k);
                        if (or2 != or) {
                            // If previous ranking is the same;
                            if (i == 1) {
                                if (or.getValue1() == or2.getValue1()) {
                                    ors.add(or2);
                                }
                            }
                            if (i == 2) {
                                if ((or.getValue1() == or2.getValue1()) && (or.getValue2() == or2.getValue2())) {
                                    ors.add(or2);
                                }
                            }
                            if (i == 3) {
                                if ((or.getValue1() == or2.getValue1()) && (or.getValue2() == or2.getValue2()) && (or.getValue3() == or2.getValue3())) {
                                    ors.add(or2);
                                }
                            }
                            if (i == 4) {
                                if ((or.getValue1() == or2.getValue1()) && (or.getValue2() == or2.getValue2()) && (or.getValue3() == or2.getValue3()) && (or.getValue4() == or2.getValue4())) {
                                    ors.add(or2);
                                }
                            }
                        }
                    }
                    if (ors.size() > 0) {
                        // 2 points per match
                        // size+1 competitors
                        int nb_theoric = 0;
                        for (int k = ors.size(); k > 0; k--) {
                            nb_theoric += k;
                        }
                        // practical Sum 
                        int r_value = or.getValue(i);
                        for (ObjectRanking or_tmp : ors) {
                            r_value += or_tmp.getValue(i);
                        }

                        if (r_value != nb_theoric * 2) {
                            or.setValue(i, -1);
                            for (ObjectRanking or_tmp : ors) {
                                or_tmp.setValue(i, -1);
                            }
                        }
                    }
                }

            }
        }

        Collections.sort(mDatas);

        

        // On ajuste le tri par poule si nécessaire pour que
        // l'écart minimum entre 2 membres de la même poule
        // soit le nombre de joueurs de la poule
        if ((mForPool) && (!tour.getParams()
                .isTeamTournament())&&(tour.getPoolCount()>0)) {
            if (mObjects.size() > tour.getPool(0).getCompetitorCount()) {
                final int nbPool = tour.getPoolCount();
                Pool p;
                final ArrayList<IndivRanking> pRank = new ArrayList<>();
                for (int j = 0; j < nbPool; j++) {
                    p = tour.getPool(j);
                    IndivRanking mjtr = new IndivRanking(mRound, mRankingType1, mRankingType2, mRankingType3, mRankingType4, mRankingType5, p.getCompetitors(), mTeamTournament, mRoundOnly, false, false);
                    pRank.add(mjtr);
                }

                final ArrayList datas = new ArrayList();

                for (int i = 0; i < tour.getPool(0).getCompetitorCount(); i++) {
                    final ArrayList<Coach> rank = new ArrayList<>();
                    for (int j = 0; j < nbPool; j++) {
                        try {
                            final ObjectRanking obj = (ObjectRanking) pRank.get(j).mDatas.get(i);
                            rank.add((Coach) obj.getObject());
                        } catch (IndexOutOfBoundsException ioob) {

                        }
                    }
                    IndivRanking mjtr = new IndivRanking(mRound, mRankingType1, mRankingType2, mRankingType3, mRankingType4, mRankingType5, rank, mTeamTournament, mRoundOnly, false, false);

                    for (int j = 0; j < mjtr.mDatas.size(); j++) {
                        datas.add(mjtr.mDatas.get(j));
                    }
                }

                final ArrayList<Coach> rank = new ArrayList<>();
                for (int i = 0; i < tour.getCoachsCount(); i++) {

                    if (!tour.getCoach(i).isActive()) {
                        rank.add(tour.getCoach(i));
                    }
                }
                IndivRanking mjtr = new IndivRanking(mRound, mRankingType1, mRankingType2, mRankingType3, mRankingType4, mRankingType5, rank, mTeamTournament, mRoundOnly, false, false);

                for (int j = 0; j < mjtr.mDatas.size(); j++) {
                    datas.add(mjtr.mDatas.get(j));
                }

                mDatas = datas;
            }
        }
    }

    private void updateHeadByHeadValue(int round_index, int valueIndex, ObjectRanking or1, ObjectRanking or2) {
        Coach c = (Coach) or1.getObject();
        Coach c2 = (Coach) or2.getObject();
        Criterion Tds = Tournament.getTournament().getParams().getCriterion(0);

        for (int l = 0; l < c.getMatchCount(); l++) {
            CoachMatch cm = (CoachMatch) c.getMatch(l);
            Round round = cm.getRound();
            int r_index = Tournament.getTournament().getRoundIndex(round);
            if (r_index <= round_index) {
                if ((cm.getCompetitor1() == c) && (cm.getCompetitor2() == c2)) {
                    Value val = cm.getValue(Tds);
                    if (val.getValue1() > val.getValue2()) {
                        or1.setValue(valueIndex, or1.getValue(valueIndex) + 2);
                    }
                    if (val.getValue1() == val.getValue2()) {
                        or1.setValue(valueIndex, or1.getValue(valueIndex) + 1);
                        or2.setValue(valueIndex, or2.getValue(valueIndex) + 1);
                    }
                    if (val.getValue1() < val.getValue2()) {
                        or2.setValue(valueIndex, or2.getValue(valueIndex) + 2);
                    }
                }
                if ((cm.getCompetitor1() == c2) && (cm.getCompetitor2() == c)) {
                    Value val = cm.getValue(Tds);
                    if (val.getValue1() < val.getValue2()) {
                        or1.setValue(valueIndex, or1.getValue(valueIndex) + 2);
                    }
                    if (val.getValue1() == val.getValue2()) {
                        or1.setValue(valueIndex, or1.getValue(valueIndex) + 1);
                        or2.setValue(valueIndex, or2.getValue(valueIndex) + 1);
                    }
                    if (val.getValue1() > val.getValue2()) {
                        or2.setValue(valueIndex, or2.getValue(valueIndex) + 2);
                    }
                }
            }
        }
    }

    @Override
    public Element getXMLElement() {
        Element e = super.getXMLElement();
        e.setName(StringConstants.CS_INDIV_RANKING);

        e.setAttribute(StringConstants.CS_BYTEAM, Boolean.toString(mTeamTournament));
        e.setAttribute(StringConstants.CS_POOL, Boolean.toString(mForPool));
        e.setAttribute(StringConstants.CS_CUP, Boolean.toString(mForCup));
        return e;
    }

    @Override
    public void setXMLElement(Element e) {
        super.setXMLElement(e);

        try {
            mTeamTournament = e.getAttribute(StringConstants.CS_BYTEAM).getBooleanValue();
            mForPool = e.getAttribute(StringConstants.CS_POOL).getBooleanValue();
            mForCup = e.getAttribute(StringConstants.CS_CUP).getBooleanValue();
        } catch (DataConversionException dce) {
            dce.printStackTrace();
        }
    }
}
