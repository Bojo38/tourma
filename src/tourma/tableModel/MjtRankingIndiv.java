/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.tableModel;

import java.awt.Component;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import tourma.data.Coach;
import tourma.data.CoachMatch;
import tourma.data.Criteria;
import tourma.data.IWithNameAndPicture;
import tourma.data.ObjectRanking;
import tourma.data.Parameters;
import tourma.data.Pool;
import tourma.data.Round;
import tourma.data.Tournament;
import tourma.data.Value;
import tourma.languages.Translate;
import tourma.utility.StringConstants;
import tourma.utils.ImageTreatment;

/**
 *
 * @author Frederic Berger
 */
@SuppressWarnings("serial")
public final class MjtRankingIndiv extends MjtRanking {

    private final boolean mTeamTournament;
    private final boolean mForPool;
    private final boolean mForCup;

    public MjtRankingIndiv(final int round,
            final int ranking_type1,
            final int ranking_type2,
            final int ranking_type3,
            final int ranking_type4,
            final int ranking_type5,
            final ArrayList coachs, final boolean tournament, final boolean round_only, final boolean forPool,boolean forCup) {
        super(round, ranking_type1, ranking_type2, ranking_type3, ranking_type4, ranking_type5, coachs, round_only);
        mTeamTournament = tournament;
        mForPool = forPool;
        mForCup = forCup;
        sortDatas();
    }

    public MjtRankingIndiv(final int round, final ArrayList coachs, boolean teamTournament, final boolean round_only) {

        this(round,
                Tournament.getTournament().getParams().getRankingIndiv1(),
                Tournament.getTournament().getParams().getRankingIndiv2(),
                Tournament.getTournament().getParams().getRankingIndiv3(),
                Tournament.getTournament().getParams().getRankingIndiv4(),
                Tournament.getTournament().getParams().getRankingIndiv5(),
                coachs, teamTournament, round_only, false, false);
    }

    /**
     *
     */
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

        final Tournament tour = Tournament.getTournament();

        // On ajuste le tri par poule si nécessaire pour que
        // l'écart minimum entre 2 membres de la même poule
        // soit le nombre de joueurs de la poule
        if ((mForPool) && (!tour.getParams()
                .isTeamTournament())) {
            if (mObjects.size() > tour.getPool(0).getCompetitorCount()) {
                final int nbPool = tour.getPoolCount();
                Pool p;
                final ArrayList<MjtRankingIndiv> pRank = new ArrayList<>();
                for (int j = 0; j < nbPool; j++) {
                    p = tour.getPool(j);
                    final MjtRankingIndiv mjtr = new MjtRankingIndiv(mRound, mRankingType1, mRankingType2, mRankingType3, mRankingType4, mRankingType5, p.getCompetitors(), mTeamTournament, mRoundOnly, false,false);
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
                    final MjtRankingIndiv mjtr = new MjtRankingIndiv(mRound, mRankingType1, mRankingType2, mRankingType3, mRankingType4, mRankingType5, rank, mTeamTournament, mRoundOnly, false,false);

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
                final MjtRankingIndiv mjtr = new MjtRankingIndiv(mRound, mRankingType1, mRankingType2, mRankingType3, mRankingType4, mRankingType5, rank, mTeamTournament, mRoundOnly, false,false);

                for (int j = 0; j < mjtr.mDatas.size(); j++) {
                    datas.add(mjtr.mDatas.get(j));
                }

                mDatas = datas;
            }
        }
    }

    protected void updateHeadByHeadValue(int round_index, int valueIndex, ObjectRanking or1, ObjectRanking or2) {
        Coach c = (Coach) or1.getObject();
        Coach c2 = (Coach) or2.getObject();
        Criteria Tds = Tournament.getTournament().getParams().getCriteria(0);

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
    public int getColumnCount() {
        int result = 9;
        Parameters params = Tournament.getTournament().getParams();
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
        if (mTeamTournament) {
            result++;
        }
        return result;
    }

    @Override
    public String getColumnName(final int col) {

        String result = StringConstants.CS_NULL;
        int cl = col;
        if (mTeamTournament) {
            if (col > 1) {
                cl = col - 1;
            }
            if (col == 1) {
                result = Translate.translate(Translate.CS_Team);
            }
        }

        switch (cl) {
            case 0:
                result = StringConstants.CS_HASH;
                break;
            case 1:
                if (mTeamTournament) {
                    if (col != 1) {
                        result = Translate.translate(Translate.CS_Team);
                    }
                } else {
                    result = Translate.translate(Translate.CS_Team);
                }
                break;
            case 2:
                result = Translate.translate(Translate.CS_Coach);
                break;
            case 3:
                result = Translate.translate(Translate.CS_Roster);
                break;
            case 4:
                result = getRankingString(mRankingType1);
                break;
            case 5:
                result = getRankingString(mRankingType2);
                break;
            case 6:
                result = getRankingString(mRankingType3);
                break;
            case 7:
                result = getRankingString(mRankingType4);
                break;
            case 8:
                result = getRankingString(mRankingType5);
                break;
            default:
        }
        return result;
    }

    @Override
    public Object getValueAt(final int row, final int col) {
        Object object = "";
        try {
            if (mDatas.size() > row) {
                final ObjectRanking obj = (ObjectRanking) mDatas.get(row);
                int cl = col;
                if (mTeamTournament) {
                    if (col > 1) {
                        cl = col - 1;
                    }
                    if (col == 1) {
                        object = ((Coach) obj.getObject()).getTeamMates().getName();
                    }
                }

                switch (cl) {
                    case 0:
                        object = row + 1;
                        break;
                    case 1:
                        if (mTeamTournament) {
                            if (col != 1) {
                                object = ((Coach) obj.getObject()).getTeam();
                            }
                        } else {
                            object = ((Coach) obj.getObject()).getTeam();
                        }
                        break;
                    case 2:
                        object = ((IWithNameAndPicture) obj.getObject()).getName();
                        break;
                    case 3:
                        object = ((Coach) obj.getObject()).getStringRoster();
                        break;
                    case 4:
                        if ((Tournament.getTournament().getParams().getIndivRankingType(0) == Parameters.C_RANKING_VND)
                                ||(Tournament.getTournament().getParams().getIndivRankingType(0) == Parameters.C_RANKING_TEAMMATES_VND)) {
                            object = convertVND(obj.getValue1());
                        } else {
                            object = obj.getValue1();
                        }
                        break;
                    case 5:
                        if ((Tournament.getTournament().getParams().getIndivRankingType(1) == Parameters.C_RANKING_VND)
                                ||(Tournament.getTournament().getParams().getIndivRankingType(1) == Parameters.C_RANKING_TEAMMATES_VND)){
                            object = convertVND(obj.getValue2());
                        } else {
                            object = obj.getValue2();
                        }
                        break;
                    case 6:
                        if ((Tournament.getTournament().getParams().getIndivRankingType(2) == Parameters.C_RANKING_VND)
                                ||(Tournament.getTournament().getParams().getIndivRankingType(2) == Parameters.C_RANKING_TEAMMATES_VND)){
                            object = convertVND(obj.getValue3());
                        } else {
                            object = obj.getValue3();
                        }
                        break;
                    case 7:
                        if ((Tournament.getTournament().getParams().getIndivRankingType(3) == Parameters.C_RANKING_VND)
                                ||(Tournament.getTournament().getParams().getIndivRankingType(3) == Parameters.C_RANKING_TEAMMATES_VND)){
                            object = convertVND(obj.getValue4());
                        } else {
                            object = obj.getValue4();
                        }
                        break;
                    case 8:
                        if ((Tournament.getTournament().getParams().getIndivRankingType(4) == Parameters.C_RANKING_VND)
                                ||(Tournament.getTournament().getParams().getIndivRankingType(4) == Parameters.C_RANKING_TEAMMATES_VND)){
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
    public Component getTableCellRendererComponent(final JTable table, final Object value, final boolean isSelected, final boolean hasFocus, final int row, final int column) {
        JLabel obj = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if (Tournament.getTournament().getParams().isUseImage()) {
            if ((column == 1) && mTeamTournament) {
                Coach c = (Coach) mObjects.get(row);
                if (c.getTeamMates().getPicture() != null) {
                    ImageIcon icon = ImageTreatment.resize(c.getTeamMates().getPicture(), 30, 30);
                    obj.setIcon(icon);
                    obj.setOpaque(true);
                    return obj;
                }
            }
        }
        return obj;
    }
}
