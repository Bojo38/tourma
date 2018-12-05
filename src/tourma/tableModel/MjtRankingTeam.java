/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.tableModel;

import java.awt.Component;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import tourma.data.Coach;
import tourma.data.CoachMatch;
import tourma.data.Criteria;
import tourma.data.ETeamPairing;
import tourma.data.IWithNameAndPicture;
import tourma.data.ObjectRanking;
import tourma.data.Parameters;
import tourma.data.Pool;
import tourma.data.Round;
import tourma.data.Team;
import tourma.data.TeamMatch;
import tourma.data.Tournament;
import tourma.data.Value;
import tourma.languages.Translate;
import tourma.utility.StringConstants;
import tourma.utils.ImageTreatment;

/**
 *
 * @author Frederic Berger
 */
public final class MjtRankingTeam extends MjtRanking {

    private static final Logger LOG = Logger.getLogger(MjtRankingTeam.class.getName());

    /**
     *
     * @param t
     * @param v
     * @return
     */
    private static int getTeamVND(final Team t, final ArrayList<CoachMatch> v) {
        int value = 0;

        int nbVictory = 0;
        int nbLost = 0;
        Criteria td = Tournament.getTournament().getParams().getCriteria(0);
        for (int i = 0; i < v.size(); i++) {
            final CoachMatch m = v.get(i);
            final Value val = m.getValue(td);
            if (((Coach) m.getCompetitor1()).getTeamMates() == t) {
                if (val.getValue1() > val.getValue2()) {
                    nbVictory++;
                } else {
                    if (val.getValue1() < val.getValue2()) {
                        nbLost++;
                    }
                }
            }
            if (((Coach) m.getCompetitor2()).getTeamMates() == t) {
                if (val.getValue1() < val.getValue2()) {
                    nbVictory++;
                } else {
                    if (val.getValue1() > val.getValue2()) {
                        nbLost++;
                    }
                }
            }
        }
        if (nbVictory > nbLost) {
            value += 1000000;
        } else {
            if (nbVictory < nbLost) {
                value += 1;
            } else {
                value += 1000;
            }
        }
        return value;
    }

    /**
     *
     * @param t
     * @param v
     * @return
     */
    private static int getTeamPoints(final Team t, final ArrayList<CoachMatch> v) {
        int value = 0;

        int nbVictory = 0;
        int nbLost = 0;
        Criteria td = Tournament.getTournament().getParams().getCriteria(0);
        for (int i = 0; i < v.size(); i++) {
            final CoachMatch m = v.get(i);
            final Value val = m.getValue(td);
            if (((Coach) m.getCompetitor1()).getTeamMates() == t) {
                if (val.getValue1() > val.getValue2()) {
                    nbVictory++;
                } else {
                    if (val.getValue1() < val.getValue2()) {
                        nbLost++;
                    }
                }

                for (int j = 0; j < Tournament.getTournament().getParams().getCriteriaCount(); j++) {

                    final Criteria cri = Tournament.getTournament().getParams().getCriteria(j);
                    final Value va = m.getValue(cri);
                    value += va.getValue1() + cri.getPointsFor();
                    value += va.getValue2() + cri.getPointsAgainst();
                }

            }
            if (((Coach) m.getCompetitor2()).getTeamMates() == t) {
                if (val.getValue1() < val.getValue2()) {
                    nbVictory++;
                } else {
                    if (val.getValue1() > val.getValue2()) {
                        nbLost++;
                    }
                }
                for (int j = 0; j < Tournament.getTournament().getParams().getCriteriaCount(); j++) {
                    final Criteria cri = Tournament.getTournament().getParams().getCriteria(j);
                    final Value va = m.getValue(cri);
                    value += va.getValue2() + cri.getPointsFor();
                    value += va.getValue1() + cri.getPointsAgainst();
                }
            }
        }

        if (nbVictory > nbLost) {
            if (Tournament.getTournament().getParams().isUseLargeVictory()) {
                if (nbVictory - nbLost >= Tournament.getTournament().getParams().getGapTeamLargeVictory()) {
                    value += Tournament.getTournament().getParams().getPointsTeamLargeVictory();
                } else {
                    value += Tournament.getTournament().getParams().getPointsTeamVictory();
                }
            } else {
                value += Tournament.getTournament().getParams().getPointsTeamVictory();
            }
        } else {
            if (nbVictory < nbLost) {
                if (Tournament.getTournament().getParams().isUseLittleLoss()) {
                    if (nbLost - nbVictory <= Tournament.getTournament().getParams().getGapTeamLittleLost()) {
                        value += Tournament.getTournament().getParams().getPointsTeamLittleLost();
                    } else {
                        value += Tournament.getTournament().getParams().getPointsTeamLost();
                    }
                } else {
                    value += Tournament.getTournament().getParams().getPointsTeamLost();
                }
            } else {
                value += Tournament.getTournament().getParams().getPointsTeamDraw();
            }
        }
        return value;
    }

    private final boolean mTeamVictory;

    private MjtRankingTeam(final boolean teamVictory, final int round, final int ranking_type1, final int ranking_type2, final int ranking_type3, final int ranking_type4, final int ranking_type5, final ArrayList teams, final boolean round_only) {
        super(round, ranking_type1, ranking_type2, ranking_type3, ranking_type4, ranking_type5, teams, round_only);
        mTeamVictory = teamVictory;
        sortDatas();
    }

    /**
     *
     * @param teamVictory
     * @param round
     * @param teams
     * @param round_only
     */
    public MjtRankingTeam(final boolean teamVictory, final int round, final ArrayList teams, final boolean round_only) {

        super(round, Tournament.getTournament().getParams().getRankingTeam1(), Tournament.getTournament().getParams().gemRankingTeam2(), Tournament.getTournament().getParams().getRankingTeam3(), Tournament.getTournament().getParams().getRankingTeam4(), Tournament.getTournament().getParams().getRankingTeam5(),
                teams, round_only);
        if (!teamVictory) {
            this.mRankingType1 = Tournament.getTournament().getParams().getRankingIndiv1();
            this.mRankingType2 = Tournament.getTournament().getParams().getRankingIndiv2();
            this.mRankingType3 = Tournament.getTournament().getParams().getRankingIndiv3();
            this.mRankingType4 = Tournament.getTournament().getParams().getRankingIndiv4();
            this.mRankingType5 = Tournament.getTournament().getParams().getRankingIndiv5();
        }
        mTeamVictory = teamVictory;
        sortDatas();

    }

    @Override
    protected void sortDatas() {

        mDatas.clear();
        mDatas = new ArrayList();

        final ArrayList<Round> rounds = new ArrayList<>();

        if (mRoundOnly) {
            rounds.add(Tournament.getTournament().getRound(mRound));
        } else {
            for (int l = 0; (l <= mRound); l++) {
                rounds.add(Tournament.getTournament().getRound(l));
            }
        }

        for (int i = 0; i < mObjects.size(); i++) {
            final Team t = (Team) mObjects.get(i);

            int value1;
            int value2;
            int value3;
            int value4;
            int value5;

            ArrayList<Integer> aValue1 = new ArrayList<>();
            ArrayList<Integer> aValue2 = new ArrayList<>();
            ArrayList<Integer> aValue3 = new ArrayList<>();
            ArrayList<Integer> aValue4 = new ArrayList<>();
            ArrayList<Integer> aValue5 = new ArrayList<>();

            // If Team pairing
            if (t.getMatchCount() > 0) {

                for (int j = 0; j <= t.getMatchCount() - 1; j++) {

                    final TeamMatch tm = (TeamMatch) t.getMatch(j);
                    if (!tm.isValues_computed()) {
                            tm.recomputeValues();
                    }
                    for (int l = 0; l < rounds.size(); l++) {
                        final Round r = rounds.get(l);
                        if (r.containsMatch(tm)) {
                            aValue1.add(tm.getValue(1, t));
                            aValue2.add(tm.getValue(2, t));
                            aValue3.add(tm.getValue(3, t));
                            aValue4.add(tm.getValue(4, t));
                            aValue5.add(tm.getValue(5, t));
                        }
                    }

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

                mDatas.add(new ObjectRanking(t, value1, value2, value3, value4, value5));

            } // If Indiv pairing
            else {
                for (int k = 0; k < t.getCoachsCount(); k++) {
                    Coach c = t.getCoach(k);
                    for (int j = 0; j <= c.getMatchCount() - 1; j++) {

                        final CoachMatch cm = (CoachMatch) c.getMatch(j);
                        if (!cm.isValues_computed()) {
                            cm.recomputeValues();
                        }
                        boolean bFound = false;
                        for (int l = 0; (l < rounds.size()) && (!bFound); l++) {
                            final Round r = rounds.get(l);
                            if (r.containsMatch(cm)) {
                                bFound = true;
                            }
                        }
                        // test if match is in round

                        if (bFound) {
                            aValue1.add(cm.getValue(1, c));
                            aValue2.add(cm.getValue(2, c));
                            aValue3.add(cm.getValue(3, c));
                            aValue4.add(cm.getValue(4, c));
                            aValue5.add(cm.getValue(5, c));
                        }

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

                mDatas.add(new ObjectRanking(t, value1, value2, value3, value4, value5));
            }
        }
        final Tournament tour = Tournament.getTournament();
        // if Head by Head
        // If not Team pairing, head by head is non-sense
        if (tour.getParams().getTeamPairing() == ETeamPairing.TEAM_PAIRING) {
            // If one of sorting parameteetr is head by head
            for (int i = 1; i < 5; i++) {
                if (Tournament.getTournament().getParams().getTeamRankingType(i) == Parameters.C_RANKING_HEAD_BY_HEAD) {
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
        }

        Collections.sort(mDatas);

        // On ajuste le tri par poule si nécessaire pour que
        // l'écart minimum entre 2 membres de la même poule
        // soit le nombre de joueurs de la poule
        if ((tour.getPoolCount() > 0) && (tour.getRoundsCount() > 0) && (!tour.getRound(mRound).isCup())) {
            if (mObjects.size() > tour.getPool(0).getCompetitorCount()) {
                final int nbPool = tour.getPoolCount();
                Pool p;
                final ArrayList<MjtRankingTeam> pRank = new ArrayList<>();
                for (int j = 0; j < nbPool; j++) {
                    p = tour.getPool(j);
                    final MjtRankingTeam mjtr = new MjtRankingTeam(mTeamVictory, mRound, mRankingType1, mRankingType2, mRankingType3, mRankingType4, mRankingType5, p.getCompetitors(), mRoundOnly);
                    pRank.add(mjtr);
                }

                final ArrayList datas = new ArrayList();

                for (int i = 0; i < tour.getPool(0).getCompetitorCount(); i++) {
                    final ArrayList<Team> rank = new ArrayList<>();
                    for (int j = 0; j < nbPool; j++) {
                        final ObjectRanking obj = (ObjectRanking) pRank.get(j).mDatas.get(i);
                        rank.add((Team) obj.getObject());
                    }
                    final MjtRankingTeam mjtr = new MjtRankingTeam(mTeamVictory, mRound, mRankingType1, mRankingType2, mRankingType3, mRankingType4, mRankingType5, rank, mRoundOnly);

                    for (int j = 0; j < mjtr.mDatas.size(); j++) {
                        datas.add(mjtr.mDatas.get(j));
                    }
                }

                mDatas = datas;
            }
        }
    }

    protected void updateHeadByHeadValue(int round_index, int valueIndex, ObjectRanking or1, ObjectRanking or2) {

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
    public int getColumnCount() {
        int result = 7;
        Parameters params = Tournament.getTournament().getParams();
        if (params.isTeamVictoryOnly()) {
            if (params.getRankingTeam5() == 0) {
                result--;
                if (params.getRankingTeam4() == 0) {
                    result--;
                    if (params.getRankingTeam3() == 0) {
                        result--;
                        if (params.gemRankingTeam2() == 0) {
                            result--;
                            if (params.getRankingTeam1() == 0) {
                                result--;
                            }
                        }
                    }
                }
            }
        } else {
            if (params.getRankingIndiv5() == 0) {
                result--;
                if (params.getRankingIndiv4() == 0) {
                    result--;
                    if (params.getRankingIndiv3() == 0) {
                        result--;
                        if (params.getRankingIndiv2() == 0) {
                            result--;
                            if (params.getRankingIndiv1() == 0) {
                                result--;
                            }
                        }
                    }
                }
            }
        }
        return result;
    }

    @Override
    public String getColumnName(final int col
    ) {
        String val = StringConstants.CS_NULL;
        switch (col) {
            case 0:
                val = StringConstants.CS_HASH;
                break;
            case 1:
                val = Translate.translate(Translate.CS_Team);
                break;
            case 2:
                val = getRankingString(mRankingType1);
                break;
            case 3:
                val = getRankingString(mRankingType2);
                break;
            case 4:
                val = getRankingString(mRankingType3);
                break;
            case 5:
                val = getRankingString(mRankingType4);
                break;
            case 6:
                val = getRankingString(mRankingType5);
                break;
            default:
        }
        return val;
    }

    @Override
    public Object getValueAt(final int row,
            final int col
    ) {
        Parameters params = Tournament.getTournament().getParams();
        Object object = StringConstants.CS_NULL;
        try {
            if (mDatas.size() > row) {
                final ObjectRanking obj = (ObjectRanking) mDatas.get(row);
                switch (col) {
                    case 0:
                        object = row + 1;
                        break;
                    case 1:
                        object = ((IWithNameAndPicture) obj.getObject()).getName();
                        break;
                    case 2:
                        if ((params.isTeamVictoryOnly() && (params.getTeamRankingType(0) == Parameters.C_RANKING_VND))
                                || (!params.isTeamVictoryOnly() && (params.getIndivRankingType(0) == Parameters.C_RANKING_VND))
                                || (params.isTeamVictoryOnly() && (params.getTeamRankingType(0) == Parameters.C_RANKING_TEAMMATES_VND))
                                || (!params.isTeamVictoryOnly() && (params.getIndivRankingType(0) == Parameters.C_RANKING_TEAMMATES_VND))){
                            object = convertVND(obj.getValue1());
                        } else {
                            object = obj.getValue1();
                        }
                        break;
                    case 3:
                        if ((params.isTeamVictoryOnly() && (params.getTeamRankingType(1) == Parameters.C_RANKING_VND))
                                || (!params.isTeamVictoryOnly() && (params.getIndivRankingType(1) == Parameters.C_RANKING_VND))
                                || (params.isTeamVictoryOnly() && (params.getTeamRankingType(1) == Parameters.C_RANKING_TEAMMATES_VND))
                                || (!params.isTeamVictoryOnly() && (params.getIndivRankingType(1) == Parameters.C_RANKING_TEAMMATES_VND))) {
                            object = convertVND(obj.getValue2());
                        } else {
                            object = obj.getValue2();
                        }
                        break;
                    case 4:
                        if ((params.isTeamVictoryOnly() && (params.getTeamRankingType(2) == Parameters.C_RANKING_VND))
                                || (!params.isTeamVictoryOnly() && (params.getIndivRankingType(2) == Parameters.C_RANKING_VND))
                                || (params.isTeamVictoryOnly() && (params.getTeamRankingType(2) == Parameters.C_RANKING_TEAMMATES_VND))
                                || (!params.isTeamVictoryOnly() && (params.getIndivRankingType(2) == Parameters.C_RANKING_TEAMMATES_VND))) {
                            object = convertVND(obj.getValue3());
                        } else {
                            object = obj.getValue3();
                        }
                        break;
                    case 5:
                        if ((params.isTeamVictoryOnly() && (params.getTeamRankingType(3) == Parameters.C_RANKING_VND))
                                || (!params.isTeamVictoryOnly() && (params.getIndivRankingType(3) == Parameters.C_RANKING_VND))
                                || (params.isTeamVictoryOnly() && (params.getTeamRankingType(3) == Parameters.C_RANKING_TEAMMATES_VND))
                                || (!params.isTeamVictoryOnly() && (params.getIndivRankingType(3) == Parameters.C_RANKING_TEAMMATES_VND))) {
                            object = convertVND(obj.getValue4());
                        } else {
                            object = obj.getValue4();
                        }
                        break;
                    case 6:
                        if ((params.isTeamVictoryOnly() && (params.getTeamRankingType(4) == Parameters.C_RANKING_VND))
                                || (!params.isTeamVictoryOnly() && (params.getIndivRankingType(4) == Parameters.C_RANKING_VND))
                                || (params.isTeamVictoryOnly() && (params.getTeamRankingType(4) == Parameters.C_RANKING_TEAMMATES_VND))
                                || (!params.isTeamVictoryOnly() && (params.getIndivRankingType(4) == Parameters.C_RANKING_TEAMMATES_VND))) {
                            object = convertVND(obj.getValue5());
                        } else {
                            object = obj.getValue5();
                        }
                        break;
                    default:
                }
            }
        } catch (RemoteException re) {
            re.printStackTrace();
        }
        return object;
    }

    @Override
    public Component getTableCellRendererComponent(final JTable table,
            final Object value,
            final boolean isSelected,
            final boolean hasFocus,
            final int row,
            final int column
    ) {

        JLabel obj = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        if (Tournament.getTournament().getParams().isUseImage()) {
            if (column == 1) {
                Team t = (Team) mObjects.get(row);
                if (t.getPicture() != null) {
                    ImageIcon icon = ImageTreatment.resize(t.getPicture(), 30, 30);
                    obj.setIcon(icon);
                    obj.setHorizontalAlignment(JLabel.CENTER);
                    return obj;
                }
            }
        }

        return obj;
    }

}
