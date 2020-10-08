/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import tourma.data.ranking.sets.ClanRankingsSet;
import tourma.data.ranking.sets.TeamRankingsSet;
import tourma.data.ranking.sets.IndivRankingsSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.jdom.Element;
import tourma.data.ranking.AnnexClanRanking;
import tourma.data.ranking.AnnexIndivRanking;
import tourma.data.ranking.AnnexTeamRanking;
import tourma.data.ranking.IndivRanking;
import tourma.data.ranking.TeamRanking;
import tourma.utility.StringConstants;

/**
 *
 * @author WFMJ7631
 */
public class Rankings implements IXMLExport {

    public Rankings() {
        mIndivRankingSet = new IndivRankingsSet();
    }

    IndivRankingsSet mIndivRankingSet = null;
    TeamRankingsSet mTeamRankingSet = null;
    ClanRankingsSet mClanRankingSet = null;

    HashMap<Group, IndivRanking> mGroupRanking = null;
    HashMap<Category, IndivRanking> mCategoryIndivRanking = null;
    HashMap<Category, TeamRanking> mCategoryTeamRanking = null;

    HashMap<Pool, IndivRankingsSet> mPoolIndivRankings = null;
    HashMap<Pool, TeamRankingsSet> mPoolTeamRankings = null;

    @Override
    public Element getXMLElement() {
        final Element rankings = new Element(StringConstants.CS_RANKINGS);

        if (mIndivRankingSet != null) {
            rankings.addContent(mIndivRankingSet.getXMLElement());
        }
        if (mTeamRankingSet != null) {
            rankings.addContent(mTeamRankingSet.getXMLElement());
        }
        if (mClanRankingSet != null) {
            rankings.addContent(mClanRankingSet.getXMLElement());
        }

        if (mGroupRanking != null) {
            Set<Group> groups = mGroupRanking.keySet();
            for (Group g : groups) {
                Element granking = new Element(StringConstants.CS_GROUP_RANKING);
                granking.setAttribute(StringConstants.CS_GROUP, g.getName());

                granking.addContent(mGroupRanking.get(g).getXMLElement());
                rankings.addContent(granking);
            }

        }

        if (mCategoryIndivRanking != null) {
            Set<Category> categories = mCategoryIndivRanking.keySet();
            for (Category c : categories) {
                Element granking = new Element(StringConstants.CS_CATEGORY_INDIV_RANKING);
                granking.setAttribute(StringConstants.CS_CATEGORY, c.getName());

                granking.addContent(mCategoryIndivRanking.get(c).getXMLElement());
                rankings.addContent(granking);
            }
        }

        if (mCategoryTeamRanking != null) {
            Set<Category> categories = mCategoryTeamRanking.keySet();
            for (Category c : categories) {
                Element granking = new Element(StringConstants.CS_CATEGORY_TEAM_RANKING);
                granking.setAttribute(StringConstants.CS_CATEGORY, c.getName());

                granking.addContent(mCategoryTeamRanking.get(c).getXMLElement());
                rankings.addContent(granking);
            }
        }

        if (mPoolIndivRankings != null) {
            Set<Pool> pools = mPoolIndivRankings.keySet();
            for (Pool p : pools) {
                Element granking = new Element(StringConstants.CS_POOL_INDIV_RANKING);
                granking.setAttribute(StringConstants.CS_POOL, p.getName());

                granking.addContent(mPoolIndivRankings.get(p).getXMLElement());
                rankings.addContent(granking);
            }
        }

        if (mPoolTeamRankings != null) {
            Set<Pool> pools = mPoolTeamRankings.keySet();
            for (Pool p : pools) {
                Element granking = new Element(StringConstants.CS_POOL_TEAM_RANKING);
                granking.setAttribute(StringConstants.CS_POOL, p.getName());

                granking.addContent(mPoolTeamRankings.get(p).getXMLElement());
                rankings.addContent(granking);
            }
        }
        return rankings;
    }

    @Override
    public void setXMLElement(Element e) {

        final List<Element> childs = e.getChildren();
        final Iterator<Element> k = childs.iterator();
        while (k.hasNext()) {
            final Element child = k.next();

            String name = child.getName();
            switch (name) {
                case StringConstants.CS_POOL_TEAM_RANKING: {
                    if (mPoolTeamRankings == null) {
                        mPoolTeamRankings = new HashMap<>();
                    }
                    Pool p = Tournament.getTournament().getPool(mPoolTeamRankings.size());
                    TeamRankingsSet r = new TeamRankingsSet(child);
                    mPoolTeamRankings.put(p, r);
                }
                break;
                case StringConstants.CS_POOL_INDIV_RANKING: {
                    if (mPoolIndivRankings == null) {
                        mPoolIndivRankings = new HashMap<>();
                    }
                    Pool p = Tournament.getTournament().getPool(mPoolIndivRankings.size());
                    IndivRankingsSet r = new IndivRankingsSet(child);
                    mPoolIndivRankings.put(p, r);
                }
                break;
                case StringConstants.CS_CATEGORY_TEAM_RANKING: {
                    if (mCategoryTeamRanking == null) {
                        mCategoryTeamRanking = new HashMap<>();
                    }
                    Category c = Tournament.getTournament().getCategory(child.getAttributeValue(StringConstants.CS_CATEGORY));
                    TeamRanking r = new TeamRanking(child);
                    mCategoryTeamRanking.put(c, r);
                }
                case StringConstants.CS_CATEGORY_INDIV_RANKING: {
                    if (mCategoryIndivRanking == null) {
                        mCategoryIndivRanking = new HashMap<>();
                    }
                    Category c = Tournament.getTournament().getCategory(child.getAttributeValue(StringConstants.CS_CATEGORY));
                    IndivRanking r = new IndivRanking(child);
                    mCategoryIndivRanking.put(c, r);
                }
                break;
                case StringConstants.CS_GROUP_RANKING: {
                    if (mGroupRanking == null) {
                        mGroupRanking = new HashMap<>();
                    }
                    Group g = Tournament.getTournament().getGroup(child.getAttributeValue(StringConstants.CS_GROUP));
                    IndivRanking r = new IndivRanking(child);
                    mGroupRanking.put(g, r);
                }
                break;
                case StringConstants.CS_CLAN_RANKING_SET:
                    mClanRankingSet = new ClanRankingsSet(child);
                    break;
                case StringConstants.CS_TEAM_RANKING_SET:
                    mTeamRankingSet = new TeamRankingsSet(child);
                    break;
                case StringConstants.CS_INDIV_RANKING_SET:
                    mIndivRankingSet = new IndivRankingsSet(child);
                    break;
            }
        }
    }

}
