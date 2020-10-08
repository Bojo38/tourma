/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data.ranking;

import java.util.ArrayList;
import java.util.Collections;
import org.jdom.Element;
import tourma.data.Coach;
import tourma.data.CoachMatch;
import tourma.data.Criterion;
import tourma.data.ETeamPairing;
import tourma.data.Formula;
import tourma.data.ObjectAnnexRanking;
import tourma.data.Parameters;
import tourma.data.Round;
import tourma.data.Team;
import tourma.data.TeamMatch;
import tourma.data.Tournament;

/**
 *
 * @author WFMJ7631
 */
public class AnnexTeamRanking extends AnnexRanking {
    
    public AnnexTeamRanking(Element e)
    {
        super(e);
        setXMLElement(e);
    }
    
    public AnnexTeamRanking(final int round, final Criterion criteria, final int subtype, final ArrayList<Team> teams, Parameters param, final boolean round_only) {
        super(round, criteria, subtype, teams, param.getRankingTeam1(), param.getRankingTeam2(), param.getRankingTeam3(), param.getRankingTeam4(), param.getRankingTeam5(), round_only);
        sortDatas();
    }
    
    public AnnexTeamRanking(final int round, final Formula formula, final int subtype, final ArrayList<Team> teams, Parameters param, final boolean round_only) {
        super(round, formula, subtype, teams, param.getRankingTeam1(), param.getRankingTeam2(), param.getRankingTeam3(), param.getRankingTeam4(), param.getRankingTeam5(), round_only);
        sortDatas();
    }
    
    public AnnexTeamRanking(final int round, final Criterion criteria, final int subtype, final ArrayList<Team> teams, final int ranking_type1, final int ranking_type2, final int ranking_type3, final int ranking_type4, final int ranking_type5, final boolean round_only) {
        super(round, criteria, subtype, teams, ranking_type1, ranking_type2, ranking_type3, ranking_type4, ranking_type5, round_only);
        sortDatas();
    }

    public AnnexTeamRanking(final int round, final Formula formula, final int subtype, final ArrayList<Team> teams, final int ranking_type1, final int ranking_type2, final int ranking_type3, final int ranking_type4, final int ranking_type5, final boolean round_only) {
        super(round, formula, subtype, teams, ranking_type1, ranking_type2, ranking_type3, ranking_type4, ranking_type5, round_only);
        sortDatas();
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void sortDatas() {
        mDatas.clear();
        mDatas = new ArrayList<>();
        @SuppressWarnings("unchecked")
        final ArrayList<Team> teams = mObjects;
        for (int k = 0; k < teams.size(); k++) {
            final Team t = teams.get(k);
            int value = 0;
            int value1 = 0;
            int value2 = 0;
            int value3 = 0;
            int value4 = 0;
            int value5 = 0;

            // If Team Tournament and team pairing
            if (t.getMatchCount() > 0) {
                ArrayList<Integer> aValue = new ArrayList<>();
                ArrayList<Integer> aValue1 = new ArrayList<>();
                ArrayList<Integer> aValue2 = new ArrayList<>();
                ArrayList<Integer> aValue3 = new ArrayList<>();
                ArrayList<Integer> aValue4 = new ArrayList<>();
                ArrayList<Integer> aValue5 = new ArrayList<>();

                final ArrayList<Round> rounds = new ArrayList<>();

                if (mRoundOnly) {
                    rounds.add(Tournament.getTournament().getRound(mRound));
                } else {
                    for (int l = 0; (l <= mRound); l++) {
                        rounds.add(Tournament.getTournament().getRound(l));
                    }
                }

                for (int j = 0; j <= t.getMatchCount() - 1; j++) {

                    final TeamMatch tm = (TeamMatch) t.getMatch(j);

                    boolean bFound = false;
                    for (int i = 0; (i < rounds.size()) && (!bFound); i++) {
                        final Round r = rounds.get(i);
                        if (tm.getRound() == r) /*if (r.containsMatch(tm))*/ {
                            if (getCriterion() != null) {
                                aValue.add(tm.getValue(getCriterion(), mSubtype, t));
                            } else {
                                aValue.add(tm.getValue(mFormula, t));
                            }
                            aValue1.add(tm.getValue(1, t));
                            aValue1.add(tm.getValue(2, t));
                            aValue1.add(tm.getValue(3, t));
                            aValue1.add(tm.getValue(4, t));
                            aValue1.add(tm.getValue(5, t));
                        }
                    }
                }

                if (Tournament.getTournament().getParams().isApplyToAnnexTeam()) {
                    removeMaxValue(aValue);
                    removeMinValue(aValue);
                }
                for (Integer i : aValue) {
                    value += i;
                }
                if (Tournament.getTournament().getParams().isUseBestResultTeam()) {
                    while (aValue1.size() > Tournament.getTournament().getParams().getBestResultTeam()) {
                        removeMinValue(aValue1);
                    }
                    while (aValue2.size() > Tournament.getTournament().getParams().getBestResultTeam()) {
                        removeMinValue(aValue2);
                    }
                    while (aValue3.size() > Tournament.getTournament().getParams().getBestResultTeam()) {
                        removeMinValue(aValue3);
                    }
                    while (aValue4.size() > Tournament.getTournament().getParams().getBestResultTeam()) {
                        removeMinValue(aValue4);
                    }
                    while (aValue5.size() > Tournament.getTournament().getParams().getBestResultTeam()) {
                        removeMinValue(aValue5);
                    }
                } else {
                    if (Tournament.getTournament().getParams().isExceptBestAndWorstTeam()) {
                        removeMinValue(aValue1);
                        removeMinValue(aValue2);
                        removeMinValue(aValue3);
                        removeMinValue(aValue4);
                        removeMinValue(aValue5);
                        removeMaxValue(aValue1);
                        removeMaxValue(aValue2);
                        removeMaxValue(aValue3);
                        removeMaxValue(aValue4);
                        removeMaxValue(aValue5);
                    }
                }

                value1 = getValueFromArray(mRankingType1, aValue1);
                value2 = getValueFromArray(mRankingType2, aValue2);
                value3 = getValueFromArray(mRankingType3, aValue3);
                value4 = getValueFromArray(mRankingType4, aValue4);
                value5 = getValueFromArray(mRankingType5, aValue5);
            } // If team tournament with indiv pairing
            else {
                ArrayList<Integer> aValue = new ArrayList<>();
                ArrayList<Integer> aValue1 = new ArrayList<>();
                ArrayList<Integer> aValue2 = new ArrayList<>();
                ArrayList<Integer> aValue3 = new ArrayList<>();
                ArrayList<Integer> aValue4 = new ArrayList<>();
                ArrayList<Integer> aValue5 = new ArrayList<>();

                final ArrayList<Round> rounds = new ArrayList<>();

                if (mRoundOnly) {
                    rounds.add(Tournament.getTournament().getRound(mRound));
                } else {
                    for (int l = 0; (l <= mRound); l++) {
                        rounds.add(Tournament.getTournament().getRound(l));
                    }
                }

                for (int m = 0; m < t.getCoachsCount(); m++) {
                    Coach c = t.getCoach(m);
                    aValue = new ArrayList<>();
                    for (int j = 0; j <= c.getMatchCount() - 1; j++) {

                        final CoachMatch cm = (CoachMatch) c.getMatch(j);
                        boolean bFound = false;
                        for (int i = 0; (i < rounds.size()) && (!bFound); i++) {
                            final Round r = rounds.get(i);
                            if (r == cm.getRound()) /*if (r.containsMatch(cm))*/ {
                                bFound = true;
                            }
                        }

                        if (bFound) {
                            if (getCriterion() != null) {
                                aValue.add(cm.getValue(getCriterion(), mSubtype, c));
                            } else {
                                aValue.add(cm.getValue(mFormula, c));
                            }
                            aValue1.add(cm.getValue(1, c));
                            aValue1.add(cm.getValue(2, c));
                            aValue1.add(cm.getValue(3, c));
                            aValue1.add(cm.getValue(4, c));
                            aValue1.add(cm.getValue(5, c));
                        }

                    }

                    if (Tournament.getTournament().getParams().isApplyToAnnexTeam()) {
                        removeMaxValue(aValue);
                        removeMinValue(aValue);
                    }
                    for (Integer i : aValue) {
                        value += i;
                    }
                    if (Tournament.getTournament().getParams().isUseBestResultTeam()) {
                        while (aValue1.size() > Tournament.getTournament().getParams().getBestResultTeam()) {
                            removeMinValue(aValue1);
                        }
                        while (aValue2.size() > Tournament.getTournament().getParams().getBestResultTeam()) {
                            removeMinValue(aValue2);
                        }
                        while (aValue3.size() > Tournament.getTournament().getParams().getBestResultTeam()) {
                            removeMinValue(aValue3);
                        }
                        while (aValue4.size() > Tournament.getTournament().getParams().getBestResultTeam()) {
                            removeMinValue(aValue4);
                        }
                        while (aValue5.size() > Tournament.getTournament().getParams().getBestResultTeam()) {
                            removeMinValue(aValue5);
                        }
                    } else {
                        if (Tournament.getTournament().getParams().isExceptBestAndWorstTeam()) {
                            removeMinValue(aValue1);
                            removeMinValue(aValue2);
                            removeMinValue(aValue3);
                            removeMinValue(aValue4);
                            removeMinValue(aValue5);
                            removeMaxValue(aValue1);
                            removeMaxValue(aValue2);
                            removeMaxValue(aValue3);
                            removeMaxValue(aValue4);
                            removeMaxValue(aValue5);
                        }
                    }
                }
                value1 = getValueFromArray(mRankingType1, aValue1);
                value2 = getValueFromArray(mRankingType2, aValue2);
                value3 = getValueFromArray(mRankingType3, aValue3);
                value4 = getValueFromArray(mRankingType4, aValue4);
                value5 = getValueFromArray(mRankingType5, aValue5);
            }
            mDatas.add(new ObjectAnnexRanking(t, value, value1, value2, value3, value4, value5));
        }

        final Tournament tour = Tournament.getTournament();

        // if Head by Head
        if (tour.getParams().getTeamPairing() == ETeamPairing.TEAM_PAIRING) {
            // If one of sorting parameteetr is head by head
            for (int i = 1; i < 5; i++) {
                if (Tournament.getTournament().getParams().getTeamRankingType(i) == Parameters.C_RANKING_HEAD_BY_HEAD) {
                    // Loop on ranking to sort according to own match of the coaches who are ties.
                    // The head by head ranking is nonsense a first ranking type.

                    // Now check equalities
                    for (int j = 0; j < mDatas.size(); j++) {
                        ObjectAnnexRanking or = (ObjectAnnexRanking) mDatas.get(j);

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
                        ObjectAnnexRanking or = (ObjectAnnexRanking) mDatas.get(j);

                        // find objects with same rankings
                        for (int k = j + 1; k < mDatas.size(); k++) {
                            ObjectAnnexRanking or2 = (ObjectAnnexRanking) mDatas.get(k);

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
                        ObjectAnnexRanking or = (ObjectAnnexRanking) mDatas.get(j);
                        if (or.getValue(i) == -1) {
                            continue;
                        }
                        ArrayList<ObjectAnnexRanking> ors = new ArrayList<>();
                        // find objects with same rankings
                        for (int k = 0; k < mDatas.size(); k++) {
                            ObjectAnnexRanking or2 = (ObjectAnnexRanking) mDatas.get(k);
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
                            for (ObjectAnnexRanking or_tmp : ors) {
                                r_value += or_tmp.getValue(i);
                            }

                            if (r_value != nb_theoric * 2) {
                                or.setValue(i, -1);
                                for (ObjectAnnexRanking or_tmp : ors) {
                                    or_tmp.setValue(i, -1);
                                }
                            }
                        }
                    }
                }
            }
        }

        Collections.sort(mDatas);
    }

    protected void updateHeadByHeadValue(int round_index, int valueIndex, ObjectAnnexRanking or1, ObjectAnnexRanking or2) {

        if (or1.getObject() instanceof Team) {
            Team t = (Team) or1.getObject();
            Team t2 = (Team) or2.getObject();

            for (int l = 0; l < t.getMatchCount(); l++) {
                TeamMatch tm = (TeamMatch) t.getMatch(l);
                Round round = tm.getRound();
                int r_index = Tournament.getTournament().getRoundIndex(round);
                if (r_index <= round_index) {
                    if ((tm.getCompetitor1() == t) && (tm.getCompetitor2() == t2)) {
                        int nb_vic1 = tm.getVictories(t);
                        int nb_vic2 = tm.getVictories(t2);
                        if (nb_vic1 > nb_vic2) {
                            or1.setValue(valueIndex, or1.getValue(valueIndex) + 2);
                        }
                        if (nb_vic1 == nb_vic2) {
                            or1.setValue(valueIndex, or1.getValue(valueIndex) + 1);
                            or2.setValue(valueIndex, or2.getValue(valueIndex) + 1);
                        }
                        if (nb_vic1 < nb_vic2) {
                            or2.setValue(valueIndex, or2.getValue(valueIndex) + 2);
                        }
                    }
                    if ((tm.getCompetitor1() == t2) && (tm.getCompetitor2() == t)) {
                        int nb_vic1 = tm.getVictories(t);
                        int nb_vic2 = tm.getVictories(t2);
                        if (nb_vic1 < nb_vic2) {
                            or1.setValue(valueIndex, or1.getValue(valueIndex) + 2);
                        }
                        if (nb_vic1 == nb_vic2) {
                            or1.setValue(valueIndex, or1.getValue(valueIndex) + 1);
                            or2.setValue(valueIndex, or2.getValue(valueIndex) + 1);
                        }
                        if (nb_vic1 > nb_vic2) {
                            or2.setValue(valueIndex, or2.getValue(valueIndex) + 2);
                        }
                    }
                }
            }
        }

    }
  @Override
    public Element getXMLElement() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setXMLElement(Element e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
