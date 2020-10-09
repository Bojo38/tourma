/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data.ranking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.jdom.Element;
import tourma.data.Coach;
import tourma.data.Criterion;
import tourma.data.Formula;
import tourma.data.IXMLExport;
import tourma.data.Parameters;
import tourma.data.Tournament;
import tourma.utility.StringConstants;

/**
 *
 * @author WFMJ7631
 */
public class IndivRankingsSet implements IXMLExport {


    public void setRoundOnly(boolean roundOnly) {

        mRanking.setRoundOnly(roundOnly);

        if (mRankingForCup != null) {
            mRankingForCup.setRoundOnly(roundOnly);
        }
        if (mRankingForPool != null) {
            mRankingForPool.setRoundOnly(roundOnly);
        }

        if (mAnnexPosRanking != null) {
            for (Criterion c : mAnnexPosRanking.keySet()) {
                mAnnexPosRanking.get(c).setRoundOnly(roundOnly);
            }
        }
        if (mAnnexNegRanking != null) {
            for (Criterion c : mAnnexNegRanking.keySet()) {
                mAnnexNegRanking.get(c).setRoundOnly(roundOnly);
            }
        }
        if (mAnnexDifRanking != null) {
            for (Criterion c : mAnnexDifRanking.keySet()) {
                mAnnexDifRanking.get(c).setRoundOnly(roundOnly);
            }
        }
        if (mAnnexFormRanking != null) {
            for (Formula c : mAnnexFormRanking.keySet()) {
                mAnnexFormRanking.get(c).setRoundOnly(roundOnly);
            }
        }
    }

    public IndivRankingsSet() {

    }

    public void update() {
        mRanking.sortDatas();

        if (mRankingForCup != null) {
            mRankingForCup.sortDatas();
        }
        if (mRankingForPool != null) {
            mRankingForPool.sortDatas();
        }

        if (mAnnexPosRanking != null) {
            for (Criterion c : mAnnexPosRanking.keySet()) {
                mAnnexPosRanking.get(c).sortDatas();
            }
        }
        if (mAnnexNegRanking != null) {
            for (Criterion c : mAnnexNegRanking.keySet()) {
                mAnnexNegRanking.get(c).sortDatas();
            }
        }
        if (mAnnexDifRanking != null) {
            for (Criterion c : mAnnexDifRanking.keySet()) {
                mAnnexDifRanking.get(c).sortDatas();
            }
        }
        if (mAnnexFormRanking != null) {
            for (Formula c : mAnnexFormRanking.keySet()) {
                mAnnexFormRanking.get(c).sortDatas();
            }
        }
    }

    public void createRanking(int rNumber, Tournament tour, boolean roundOnly) {
        createRanking(rNumber, tour, roundOnly, tour.getActiveCoaches());
    }

    public void createRanking(int rNumber, Tournament tour, boolean roundOnly, ArrayList<Coach> coachs) {
        mRanking = new IndivRanking(rNumber,
                tour.getParams().getRankingIndiv1(),
                tour.getParams().getRankingIndiv2(),
                tour.getParams().getRankingIndiv3(),
                tour.getParams().getRankingIndiv4(),
                tour.getParams().getRankingIndiv5(),
                coachs, tour.getParams().isTeamTournament(), roundOnly, false, false);

        mRankingForCup = new IndivRanking(rNumber,
                tour.getParams().getRankingIndiv1(),
                tour.getParams().getRankingIndiv2(),
                tour.getParams().getRankingIndiv3(),
                tour.getParams().getRankingIndiv4(),
                tour.getParams().getRankingIndiv5(),
                coachs, tour.getParams().isTeamTournament(), roundOnly, false, true);

        if (tour.getPoolCount() > 0) {
            /**
             * @TODO prendre en compte le forPool
             */
            mRankingForPool = new IndivRanking(rNumber,
                    tour.getParams().getRankingIndiv1(),
                    tour.getParams().getRankingIndiv2(),
                    tour.getParams().getRankingIndiv3(),
                    tour.getParams().getRankingIndiv4(),
                    tour.getParams().getRankingIndiv5(),
                    coachs, tour.getParams().isTeamTournament(), roundOnly, false, false);
        }

        mAnnexPosRanking = new HashMap<>();
        mAnnexNegRanking = new HashMap<>();
        mAnnexDifRanking = new HashMap<>();

        for (int i = 0; i < tour.getParams().getCriteriaCount(); i++) {
            Criterion crit = tour.getParams().getCriteria(i);

            AnnexIndivRanking annexPos = new AnnexIndivRanking(rNumber, crit, Parameters.C_RANKING_SUBTYPE_POSITIVE, coachs,
                    tour.getParams().getRankingIndiv1(),
                    tour.getParams().getRankingIndiv2(),
                    tour.getParams().getRankingIndiv3(),
                    tour.getParams().getRankingIndiv4(),
                    tour.getParams().getRankingIndiv5(),
                    tour.getParams().isTeamTournament(), roundOnly);

            AnnexIndivRanking annexNeg = new AnnexIndivRanking(rNumber, crit, Parameters.C_RANKING_SUBTYPE_NEGATIVE, coachs,
                    tour.getParams().getRankingIndiv1(),
                    tour.getParams().getRankingIndiv2(),
                    tour.getParams().getRankingIndiv3(),
                    tour.getParams().getRankingIndiv4(),
                    tour.getParams().getRankingIndiv5(),
                    tour.getParams().isTeamTournament(), roundOnly);

            AnnexIndivRanking annexDif = new AnnexIndivRanking(rNumber, crit, Parameters.C_RANKING_SUBTYPE_DIFFERENCE, coachs,
                    tour.getParams().getRankingIndiv1(),
                    tour.getParams().getRankingIndiv2(),
                    tour.getParams().getRankingIndiv3(),
                    tour.getParams().getRankingIndiv4(),
                    tour.getParams().getRankingIndiv5(),
                    tour.getParams().isTeamTournament(), roundOnly);

            mAnnexPosRanking.put(crit, annexPos);
            mAnnexNegRanking.put(crit, annexNeg);
            mAnnexDifRanking.put(crit, annexDif);
        }

        mAnnexFormRanking = new HashMap<>();

        for (int i = 0; i < tour.getParams().getFormulaCount(); i++) {
            Formula form = tour.getParams().getFormula(i);

            AnnexIndivRanking annex = new AnnexIndivRanking(rNumber, form, 0, coachs,
                    tour.getParams().getRankingIndiv1(),
                    tour.getParams().getRankingIndiv2(),
                    tour.getParams().getRankingIndiv3(),
                    tour.getParams().getRankingIndiv4(),
                    tour.getParams().getRankingIndiv5(),
                    tour.getParams().isTeamTournament(), roundOnly);

            mAnnexFormRanking.put(form, annex);
        }

    }

    public IndivRankingsSet(Element e) {
        setXMLElement(e);
    }

    public IndivRanking getRanking() {
        return mRanking;
    }

    public HashMap<Criterion, AnnexIndivRanking> getAnnexPosRanking() {
        return mAnnexPosRanking;
    }

    public HashMap<Criterion, AnnexIndivRanking> getAnnexNegRanking() {
        return mAnnexNegRanking;
    }

    public HashMap<Criterion, AnnexIndivRanking> getAnnexDifRanking() {
        return mAnnexDifRanking;
    }

    public HashMap<Formula, AnnexIndivRanking> getAnnexFormRanking() {
        return mAnnexFormRanking;
    }

    IndivRanking mRanking;
    IndivRanking mRankingForPool;
    IndivRanking mRankingForCup;

    public IndivRanking getRankingForPool() {
        return mRankingForPool;
    }

    public IndivRanking getRankingForCup() {
        return mRankingForCup;
    }

    HashMap<Criterion, AnnexIndivRanking> mAnnexPosRanking;
    HashMap<Criterion, AnnexIndivRanking> mAnnexNegRanking;
    HashMap<Criterion, AnnexIndivRanking> mAnnexDifRanking;
    HashMap<Formula, AnnexIndivRanking> mAnnexFormRanking;

    @Override
    public Element getXMLElement() {
        final Element rankings = new Element(StringConstants.CS_INDIV_RANKING_SET);

        if (mRanking != null) {
            rankings.addContent(mRanking.getXMLElement());
        }

        /**
         * @TODO forCup & forPool
         */
        if (mAnnexPosRanking != null) {
            Set<Criterion> crits = mAnnexPosRanking.keySet();
            for (Criterion c : crits) {
                Element granking = new Element(StringConstants.CS_INDIV_ANNEX_RANKING);
                granking.setAttribute(StringConstants.CS_CRITERION, c.getName());
                granking.setAttribute(StringConstants.CS_SUBTYPE, "Pos");
                granking.addContent(mAnnexPosRanking.get(c).getXMLElement());
                rankings.addContent(granking);
            }
        }

        if (mAnnexNegRanking != null) {
            Set<Criterion> crits = mAnnexNegRanking.keySet();
            for (Criterion c : crits) {
                Element granking = new Element(StringConstants.CS_INDIV_ANNEX_RANKING);
                granking.setAttribute(StringConstants.CS_CRITERION, c.getName());
                granking.setAttribute(StringConstants.CS_SUBTYPE, "Neg");
                granking.addContent(mAnnexNegRanking.get(c).getXMLElement());
                rankings.addContent(granking);
            }
        }

        if (mAnnexDifRanking != null) {
            Set<Criterion> crits = mAnnexDifRanking.keySet();
            for (Criterion c : crits) {
                Element granking = new Element(StringConstants.CS_INDIV_ANNEX_RANKING);
                granking.setAttribute(StringConstants.CS_CRITERION, c.getName());
                granking.setAttribute(StringConstants.CS_SUBTYPE, "Dif");
                granking.addContent(mAnnexDifRanking.get(c).getXMLElement());
                rankings.addContent(granking);
            }
        }

        if (mAnnexFormRanking != null) {
            Set<Formula> forms = mAnnexFormRanking.keySet();
            for (Formula f : forms) {
                Element granking = new Element(StringConstants.CS_INDIV_ANNEX_RANKING);
                granking.setAttribute(StringConstants.CS_FORMULA, f.getName());
                granking.addContent(mAnnexFormRanking.get(f).getXMLElement());
                rankings.addContent(granking);
            }
        }

        return rankings;
    }

    @Override
    public void setXMLElement(Element e) {
        final List<Element> childs = e.getChildren(StringConstants.CS_INDIV_ANNEX_RANKING);
        final Iterator<Element> k = childs.iterator();

        while (k.hasNext()) {
            final Element child = k.next();
            String criterion_name = child.getAttributeValue(StringConstants.CS_CRITERION);

            if (criterion_name != null) {
                Criterion crit = Tournament.getTournament().getParams().getCriteria(criterion_name);

                String subtype = child.getAttributeValue(StringConstants.CS_SUBTYPE);

                switch (subtype) {
                    case "Pos": {
                        if (mAnnexPosRanking == null) {
                            mAnnexPosRanking = new HashMap<>();
                        }

                        AnnexIndivRanking ranking = new AnnexIndivRanking(child.getChild(StringConstants.CS_ANNEX_INDIV_RANKING));
                        ranking.setCriterion(crit);
                        ranking.setSubtype(Parameters.C_RANKING_SUBTYPE_POSITIVE);
                        mAnnexPosRanking.put(crit, ranking);
                    }
                    break;
                    case "Neg": {
                        if (mAnnexNegRanking == null) {
                            mAnnexNegRanking = new HashMap<>();
                        }
                        AnnexIndivRanking ranking = new AnnexIndivRanking(child.getChild(StringConstants.CS_ANNEX_INDIV_RANKING));
                        ranking.setCriterion(crit);
                        ranking.setSubtype(Parameters.C_RANKING_SUBTYPE_NEGATIVE);
                        mAnnexNegRanking.put(crit, ranking);
                    }
                    break;
                    case "Dif": {
                        if (mAnnexDifRanking == null) {
                            mAnnexDifRanking = new HashMap<>();
                        }
                        AnnexIndivRanking ranking = new AnnexIndivRanking(child.getChild(StringConstants.CS_ANNEX_INDIV_RANKING));
                        ranking.setCriterion(crit);
                        ranking.setSubtype(Parameters.C_RANKING_SUBTYPE_DIFFERENCE);
                        mAnnexDifRanking.put(crit, ranking);
                    }
                    break;
                }
            }

            String formula_name = child.getAttributeValue(StringConstants.CS_FORMULA);
            if (formula_name != null) {
                Formula form = Tournament.getTournament().getParams().getFormula(formula_name);
                if (mAnnexFormRanking == null) {
                    mAnnexFormRanking = new HashMap<>();
                }
                AnnexIndivRanking ranking = new AnnexIndivRanking(child.getChild(StringConstants.CS_ANNEX_INDIV_RANKING));
                ranking.setFormula(form);

                mAnnexFormRanking.put(form, ranking);
            }

        }

        /**
         * @TODO forCup & forPool
         */
        final Element child = e.getChild(StringConstants.CS_INDIV_RANKING);
        mRanking = new IndivRanking(child);
    }

}
