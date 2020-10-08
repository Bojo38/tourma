/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data.ranking.sets;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.jdom.Element;
import tourma.data.Criterion;
import tourma.data.Criterion;
import tourma.data.Formula;
import tourma.data.Formula;
import tourma.data.IXMLExport;
import tourma.data.Tournament;
import tourma.data.ranking.AnnexIndivRanking;
import tourma.data.ranking.AnnexTeamRanking;
import tourma.data.ranking.IndivRanking;
import tourma.data.ranking.TeamRanking;
import tourma.utility.StringConstants;

/**
 *
 * @author WFMJ7631
 */
public class TeamRankingsSet implements IXMLExport {

    public TeamRankingsSet(Element e) {
        setXMLElement(e);
    }

    TeamRanking mRanking;
    HashMap<Criterion, AnnexTeamRanking> mAnnexPosRanking;
    HashMap<Criterion, AnnexTeamRanking> mAnnexNegRanking;
    HashMap<Criterion, AnnexTeamRanking> mAnnexDifRanking;
    HashMap<Formula, AnnexTeamRanking> mAnnexFormRanking;

    @Override
    public Element getXMLElement() {
       final Element rankings = new Element(StringConstants.CS_TEAM_RANKING_SET);

        if (mRanking != null) {
            rankings.addContent(mRanking.getXMLElement());
        }
        
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
                        AnnexTeamRanking ranking = new AnnexTeamRanking(child);
                        mAnnexPosRanking.put(crit, ranking);
                    }
                    break;
                    case "Neg": {
                        if (mAnnexNegRanking == null) {
                            mAnnexNegRanking = new HashMap<>();
                        }
                        AnnexTeamRanking ranking = new AnnexTeamRanking(child);
                        mAnnexNegRanking.put(crit, ranking);
                    }
                    break;
                    case "Dif": {
                        if (mAnnexDifRanking == null) {
                            mAnnexDifRanking = new HashMap<>();
                        }
                        AnnexTeamRanking ranking = new AnnexTeamRanking(child);
                        mAnnexDifRanking.put(crit, ranking);
                    }
                    break;
                }
            }

            String formula_name = child.getAttributeValue(StringConstants.CS_FORMULA);
            if (criterion_name != null) {
                Formula form = Tournament.getTournament().getParams().getFormula(formula_name);
                if (mAnnexFormRanking == null) {
                    mAnnexFormRanking = new HashMap<>();
                }
                AnnexTeamRanking ranking = new AnnexTeamRanking(child);
                mAnnexFormRanking.put(form, ranking);
            }

        }
        final Element child = e.getChild(StringConstants.CS_TEAM_RANKING);
        mRanking = new TeamRanking(child);
    }

}
