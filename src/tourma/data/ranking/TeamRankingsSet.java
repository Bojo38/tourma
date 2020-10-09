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
import tourma.data.Criterion;
import tourma.data.Formula;
import tourma.data.IXMLExport;
import tourma.data.Parameters;
import tourma.data.Team;
import tourma.data.Tournament;
import tourma.data.ranking.AnnexTeamRanking;
import tourma.data.ranking.TeamRanking;
import tourma.utility.StringConstants;

/**
 *
 * @author WFMJ7631
 */
public class TeamRankingsSet implements IXMLExport {

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
    
    public TeamRankingsSet() {

    }

    public TeamRankingsSet(Element e) {
        setXMLElement(e);
    }

    public void update() {
        mRanking.sortDatas();
        if (mRankingForCup != null) {
            mRankingForCup.sortDatas();
        }

        if (mRankingForPool != null) {
            mRankingForPool.sortDatas();
        }

        for (Criterion c : mAnnexPosRanking.keySet()) {
            mAnnexPosRanking.get(c).sortDatas();
        }
        for (Criterion c : mAnnexNegRanking.keySet()) {
            mAnnexNegRanking.get(c).sortDatas();
        }
        for (Criterion c : mAnnexDifRanking.keySet()) {
            mAnnexDifRanking.get(c).sortDatas();
        }
        if (mAnnexFormRanking != null) {
            for (Formula c : mAnnexFormRanking.keySet()) {
                mAnnexFormRanking.get(c).sortDatas();
            }
        }
    }
    TeamRanking mRanking;
    TeamRanking mRankingForPool;

    public TeamRanking getmRankingForPool() {
        return mRankingForPool;
    }

    public TeamRanking getmRankingForCup() {
        return mRankingForCup;
    }

    TeamRanking mRankingForCup;
    HashMap<Criterion, AnnexTeamRanking> mAnnexPosRanking;
    HashMap<Criterion, AnnexTeamRanking> mAnnexNegRanking;
    HashMap<Criterion, AnnexTeamRanking> mAnnexDifRanking;
    HashMap<Formula, AnnexTeamRanking> mAnnexFormRanking;

    public TeamRanking getRanking() {
        return mRanking;
    }

    public HashMap<Criterion, AnnexTeamRanking> getAnnexPosRanking() {
        return mAnnexPosRanking;
    }

    public HashMap<Criterion, AnnexTeamRanking> getAnnexNegRanking() {
        return mAnnexNegRanking;
    }

    public HashMap<Criterion, AnnexTeamRanking> getAnnexDifRanking() {
        return mAnnexDifRanking;
    }

    public HashMap<Formula, AnnexTeamRanking> getAnnexFormRanking() {
        return mAnnexFormRanking;
    }

    public void createRanking(int rNumber, Tournament tour, boolean roundOnly) {
        createRanking(rNumber, tour, roundOnly, tour.getTeams());
    }

    public void createRanking(int rNumber, Tournament tour, boolean roundOnly, ArrayList<Team> teams) {
        mRanking = new TeamRanking(tour.getParams().isTeamVictoryOnly(), rNumber, tour.getParams(), teams, roundOnly);
        mRankingForCup = new TeamRanking(tour.getParams().isTeamVictoryOnly(), rNumber, tour.getParams(), teams, roundOnly,true,false);
        mRankingForPool = new TeamRanking(tour.getParams().isTeamVictoryOnly(), rNumber, tour.getParams(), teams, roundOnly,false,true);

        mAnnexPosRanking = new HashMap<>();
        mAnnexNegRanking = new HashMap<>();
        mAnnexDifRanking = new HashMap<>();

        for (int i = 0; i < tour.getParams().getCriteriaCount(); i++) {
            Criterion crit = tour.getParams().getCriteria(i);

            AnnexTeamRanking annexPos = new AnnexTeamRanking(rNumber, crit, Parameters.C_RANKING_SUBTYPE_POSITIVE, teams,
                    tour.getParams(), roundOnly);

            AnnexTeamRanking annexNeg = new AnnexTeamRanking(rNumber, crit, Parameters.C_RANKING_SUBTYPE_NEGATIVE, teams,
                    tour.getParams(), roundOnly);

            AnnexTeamRanking annexDif = new AnnexTeamRanking(rNumber, crit, Parameters.C_RANKING_SUBTYPE_DIFFERENCE, teams,
                    tour.getParams(), roundOnly);

            mAnnexPosRanking.put(crit, annexPos);
            mAnnexNegRanking.put(crit, annexNeg);
            mAnnexDifRanking.put(crit, annexDif);
        }

        mAnnexFormRanking = new HashMap<>();

        for (int i = 0; i < tour.getParams().getFormulaCount(); i++) {
            Formula form = tour.getParams().getFormula(i);

            AnnexTeamRanking annex = new AnnexTeamRanking(rNumber, form, 0, teams,
                    tour.getParams(), roundOnly);

            mAnnexFormRanking.put(form, annex);
        }
    }

    @Override
    public Element getXMLElement() {
        final Element rankings = new Element(StringConstants.CS_TEAM_RANKING_SET);

        if (mRanking != null) {
            rankings.addContent(mRanking.getXMLElement());
        }

        /**
         * @TODO Missing For pool and For cup
         */
        
        if (mAnnexPosRanking != null) {
            Set<Criterion> crits = mAnnexPosRanking.keySet();
            for (Criterion c : crits) {
                Element granking = new Element(StringConstants.CS_TEAM_ANNEX_RANKING);
                granking.setAttribute(StringConstants.CS_CRITERION, c.getName());
                granking.setAttribute(StringConstants.CS_SUBTYPE, "Pos");
                granking.addContent(mAnnexPosRanking.get(c).getXMLElement());
                rankings.addContent(granking);
            }
        }

        if (mAnnexNegRanking != null) {
            Set<Criterion> crits = mAnnexNegRanking.keySet();
            for (Criterion c : crits) {
                Element granking = new Element(StringConstants.CS_TEAM_ANNEX_RANKING);
                granking.setAttribute(StringConstants.CS_CRITERION, c.getName());
                granking.setAttribute(StringConstants.CS_SUBTYPE, "Neg");
                granking.addContent(mAnnexNegRanking.get(c).getXMLElement());
                rankings.addContent(granking);
            }
        }

        if (mAnnexDifRanking != null) {
            Set<Criterion> crits = mAnnexDifRanking.keySet();
            for (Criterion c : crits) {
                Element granking = new Element(StringConstants.CS_TEAM_ANNEX_RANKING);
                granking.setAttribute(StringConstants.CS_CRITERION, c.getName());
                granking.setAttribute(StringConstants.CS_SUBTYPE, "Dif");
                granking.addContent(mAnnexDifRanking.get(c).getXMLElement());
                rankings.addContent(granking);
            }
        }

        if (mAnnexFormRanking != null) {
            Set<Formula> forms = mAnnexFormRanking.keySet();
            for (Formula f : forms) {
                Element granking = new Element(StringConstants.CS_TEAM_ANNEX_RANKING);
                granking.setAttribute(StringConstants.CS_FORMULA, f.getName());
                granking.addContent(mAnnexFormRanking.get(f).getXMLElement());
                rankings.addContent(granking);
            }
        }

        return rankings;
    }

    @Override
    public void setXMLElement(Element e) {
        final List<Element> childs = e.getChildren(StringConstants.CS_TEAM_ANNEX_RANKING);
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
                        AnnexTeamRanking ranking = new AnnexTeamRanking(child.getChild(StringConstants.CS_ANNEX_TEAM_RANKING));
                        ranking.setCriterion(crit);
                        ranking.setSubtype(Parameters.C_RANKING_SUBTYPE_POSITIVE);
                        mAnnexPosRanking.put(crit, ranking);
                    }
                    break;
                    case "Neg": {
                        if (mAnnexNegRanking == null) {
                            mAnnexNegRanking = new HashMap<>();
                        }
                        AnnexTeamRanking ranking = new AnnexTeamRanking(child.getChild(StringConstants.CS_ANNEX_TEAM_RANKING));
                        ranking.setCriterion(crit);
                        ranking.setSubtype(Parameters.C_RANKING_SUBTYPE_NEGATIVE);
                        mAnnexNegRanking.put(crit, ranking);
                    }
                    break;
                    case "Dif": {
                        if (mAnnexDifRanking == null) {
                            mAnnexDifRanking = new HashMap<>();
                        }
                        AnnexTeamRanking ranking = new AnnexTeamRanking(child.getChild(StringConstants.CS_ANNEX_TEAM_RANKING));
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
                AnnexTeamRanking ranking = new AnnexTeamRanking(child.getChild(StringConstants.CS_ANNEX_TEAM_RANKING));
                ranking.setFormula(form);
                mAnnexFormRanking.put(form, ranking);
            }

        }
        
        /**
         * @TODO missing ForCup and For Pool
         */
        final Element child = e.getChild(StringConstants.CS_TEAM_RANKING);
        mRanking = new TeamRanking(child);
    }

}
