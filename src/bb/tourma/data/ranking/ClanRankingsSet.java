/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bb.tourma.data.ranking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.jdom.Element;
import bb.tourma.data.Clan;
import bb.tourma.data.Criterion;
import bb.tourma.data.Formula;
import bb.tourma.data.IXMLExport;
import bb.tourma.data.Parameters;
import bb.tourma.data.Tournament;
import bb.tourma.data.ranking.ClanRanking;
import bb.tourma.utility.StringConstants;

/**
 *
 * @author WFMJ7631
 */
public class ClanRankingsSet implements IXMLExport {

    
    public void setRoundIndex(int index)
    {
        mRanking.setRoundIndex(index);

        if (mAnnexPosRanking != null) {
            for (Criterion c : mAnnexPosRanking.keySet()) {
                mAnnexPosRanking.get(c).setRoundIndex(index);
            }
        }
        if (mAnnexNegRanking != null) {
            for (Criterion c : mAnnexNegRanking.keySet()) {
                mAnnexNegRanking.get(c).setRoundIndex(index);
            }
        }
        if (mAnnexDifRanking != null) {
            for (Criterion c : mAnnexDifRanking.keySet()) {
                mAnnexDifRanking.get(c).setRoundIndex(index);
            }
        }
        if (mAnnexFormRanking != null) {
            for (Formula c : mAnnexFormRanking.keySet()) {
                mAnnexFormRanking.get(c).setRoundIndex(index);
            }
        }
    }
    
    public void setRoundOnly(boolean roundOnly) {

        mRanking.setRoundOnly(roundOnly);

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

    public ClanRankingsSet(Element e) {
        setXMLElement(e);
    }

    public ClanRankingsSet() {
    }

    public void createRanking(int rNumber, Tournament tour, boolean roundOnly) {
        createRanking(rNumber, tour, roundOnly, tour.getClans());
    }

    public ClanRanking getRanking() {
        return mRanking;
    }

    public HashMap<Criterion, AnnexClanRanking> getAnnexPosRanking() {
        return mAnnexPosRanking;
    }

    public HashMap<Criterion, AnnexClanRanking> getAnnexNegRanking() {
        return mAnnexNegRanking;
    }

    public HashMap<Criterion, AnnexClanRanking> getAnnexDifRanking() {
        return mAnnexDifRanking;
    }

    public HashMap<Formula, AnnexClanRanking> getAnnexFormRanking() {
        if (mAnnexFormRanking==null)
        {
            mAnnexFormRanking=new HashMap<>();
        }        
        return mAnnexFormRanking;
    }

    public void createRanking(int rNumber, Tournament tour, boolean roundOnly, ArrayList<Clan> clans) {
        mRanking = new ClanRanking(rNumber, tour.getParams(), clans, roundOnly);

        mAnnexPosRanking = new HashMap<>();
        mAnnexNegRanking = new HashMap<>();
        mAnnexDifRanking = new HashMap<>();

        for (int i = 0; i < tour.getParams().getCriteriaCount(); i++) {
            Criterion crit = tour.getParams().getCriterion(i);

            AnnexClanRanking annexPos = new AnnexClanRanking(rNumber, crit, Parameters.C_RANKING_SUBTYPE_POSITIVE, clans,
                    tour.getParams(), roundOnly);

            AnnexClanRanking annexNeg = new AnnexClanRanking(rNumber, crit, Parameters.C_RANKING_SUBTYPE_NEGATIVE, clans,
                    tour.getParams(), roundOnly);

            AnnexClanRanking annexDif = new AnnexClanRanking(rNumber, crit, Parameters.C_RANKING_SUBTYPE_DIFFERENCE, clans,
                    tour.getParams(), roundOnly);

            mAnnexPosRanking.put(crit, annexPos);
            mAnnexNegRanking.put(crit, annexNeg);
            mAnnexDifRanking.put(crit, annexDif);
        }

        mAnnexFormRanking = new HashMap<>();

        for (int i = 0; i < tour.getParams().getFormulaCount(); i++) {
            Formula form = tour.getParams().getFormula(i);

            AnnexClanRanking annex = new AnnexClanRanking(rNumber, form, 0, clans,
                    tour.getParams(), roundOnly);

            mAnnexFormRanking.put(form, annex);
        }
    }
    ClanRanking mRanking;
    HashMap<Criterion, AnnexClanRanking> mAnnexPosRanking;
    HashMap<Criterion, AnnexClanRanking> mAnnexNegRanking;
    HashMap<Criterion, AnnexClanRanking> mAnnexDifRanking;
    HashMap<Formula, AnnexClanRanking> mAnnexFormRanking;

    public void update() {
        mRanking.sortDatas();

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

    @Override
    public Element getXMLElement() {
        final Element rankings = new Element(StringConstants.CS_CLAN_RANKING_SET);

        if (mRanking != null) {
            rankings.addContent(mRanking.getXMLElement());
        }

        if (mAnnexPosRanking != null) {
            Set<Criterion> crits = mAnnexPosRanking.keySet();
            for (Criterion c : crits) {
                Element granking = new Element(StringConstants.CS_CLAN_ANNEX_RANKING);
                granking.setAttribute(StringConstants.CS_CRITERION, c.getName());
                granking.setAttribute(StringConstants.CS_SUBTYPE, "Pos");
                granking.addContent(mAnnexPosRanking.get(c).getXMLElement());
                rankings.addContent(granking);
            }
        }

        if (mAnnexNegRanking != null) {
            Set<Criterion> crits = mAnnexNegRanking.keySet();
            for (Criterion c : crits) {
                Element granking = new Element(StringConstants.CS_CLAN_ANNEX_RANKING);
                granking.setAttribute(StringConstants.CS_CRITERION, c.getName());
                granking.setAttribute(StringConstants.CS_SUBTYPE, "Neg");
                granking.addContent(mAnnexNegRanking.get(c).getXMLElement());
                rankings.addContent(granking);
            }
        }

        if (mAnnexDifRanking != null) {
            Set<Criterion> crits = mAnnexDifRanking.keySet();
            for (Criterion c : crits) {
                Element granking = new Element(StringConstants.CS_CLAN_ANNEX_RANKING);
                granking.setAttribute(StringConstants.CS_CRITERION, c.getName());
                granking.setAttribute(StringConstants.CS_SUBTYPE, "Dif");
                granking.addContent(mAnnexDifRanking.get(c).getXMLElement());
                rankings.addContent(granking);
            }
        }

        if (mAnnexFormRanking != null) {
            Set<Formula> forms = mAnnexFormRanking.keySet();
            for (Formula f : forms) {
                Element granking = new Element(StringConstants.CS_CLAN_ANNEX_RANKING);
                granking.setAttribute(StringConstants.CS_FORMULA, f.getName());
                granking.addContent(mAnnexFormRanking.get(f).getXMLElement());
                rankings.addContent(granking);
            }
        }
        

        return rankings;
    }

    @Override
    public void setXMLElement(Element e) {
        final List<Element> childs = e.getChildren(StringConstants.CS_CLAN_ANNEX_RANKING);
        final Iterator<Element> k = childs.iterator();

        while (k.hasNext()) {
            final Element child = k.next();
            String criterion_name = child.getAttributeValue(StringConstants.CS_CRITERION);

            if (criterion_name != null) {
                Criterion crit = Tournament.getTournament().getParams().getCriterion(criterion_name);

                String subtype = child.getAttributeValue(StringConstants.CS_SUBTYPE);

                switch (subtype) {
                    case "Pos": {
                        if (mAnnexPosRanking == null) {
                            mAnnexPosRanking = new HashMap<>();
                        }
                        AnnexClanRanking ranking = new AnnexClanRanking(child.getChild(StringConstants.CS_ANNEX_CLAN_RANKING));
                        ranking.setCriterion(crit);
                        ranking.setSubtype(Parameters.C_RANKING_SUBTYPE_POSITIVE);
                        mAnnexPosRanking.put(crit, ranking);
                    }
                    break;
                    case "Neg": {
                        if (mAnnexNegRanking == null) {
                            mAnnexNegRanking = new HashMap<>();
                        }
                        AnnexClanRanking ranking = new AnnexClanRanking(child.getChild(StringConstants.CS_ANNEX_CLAN_RANKING));
                        ranking.setCriterion(crit);
                        ranking.setSubtype(Parameters.C_RANKING_SUBTYPE_NEGATIVE);
                        mAnnexNegRanking.put(crit, ranking);
                    }
                    break;
                    case "Dif": {
                        if (mAnnexDifRanking == null) {
                            mAnnexDifRanking = new HashMap<>();
                        }
                        AnnexClanRanking ranking = new AnnexClanRanking(child.getChild(StringConstants.CS_ANNEX_CLAN_RANKING));
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
                AnnexClanRanking ranking = new AnnexClanRanking(child.getChild(StringConstants.CS_ANNEX_CLAN_RANKING));
                ranking.setFormula(form);
                mAnnexFormRanking.put(form, ranking);
            }

        }

        final Element child = e.getChild(StringConstants.CS_CLAN_RANKING);
        mRanking = new ClanRanking(child);
    }

}
