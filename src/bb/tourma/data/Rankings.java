/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bb.tourma.data;

import java.util.ArrayList;
import bb.tourma.data.ranking.ClanRankingsSet;
import bb.tourma.data.ranking.TeamRankingsSet;
import bb.tourma.data.ranking.IndivRankingsSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.jdom.DataConversionException;
import org.jdom.Element;
import bb.tourma.data.ranking.IndivRanking;
import bb.tourma.data.ranking.TeamRanking;
import bb.tourma.utility.StringConstants;

/**
 *
 * @author WFMJ7631
 */
public class Rankings implements IXMLExport {

    boolean mRoundOnly = false;

    public void setRoundOnly(boolean roundOnly) {
        this.mRoundOnly = roundOnly;

        if (mTeamRankingSet != null) {
            mTeamRankingSet.setRoundOnly(roundOnly);
        }
        if (mClanRankingSet != null) {
            mClanRankingSet.setRoundOnly(roundOnly);
        }
        if (mGroupRanking != null) {
            for (Group g : mGroupRanking.keySet()) {
                mGroupRanking.get(g).setRoundOnly(roundOnly);
            }
        }

        if (mCategoryIndivRanking != null) {
            for (Category g : mCategoryIndivRanking.keySet()) {
                mCategoryIndivRanking.get(g).setRoundOnly(roundOnly);
            }
        }

        if (mCategoryTeamRanking != null) {
            for (Category g : mCategoryTeamRanking.keySet()) {
                mCategoryTeamRanking.get(g).setRoundOnly(roundOnly);
            }
        }

        if (mPoolIndivRankings != null) {
            for (Pool g : mPoolIndivRankings.keySet()) {
                mPoolIndivRankings.get(g).setRoundOnly(roundOnly);
            }
        }

        if (mPoolTeamRankings != null) {
            for (Pool g : mPoolTeamRankings.keySet()) {
                mPoolTeamRankings.get(g).setRoundOnly(roundOnly);
            }
        }

    }

    public TeamRankingsSet getTeamRankingSet() {
        return mTeamRankingSet;
    }

    public void setTeamRankingSet(TeamRankingsSet mTeamRankingSet) {
        this.mTeamRankingSet = mTeamRankingSet;
    }

    public ClanRankingsSet getClanRankingSet() {
        return mClanRankingSet;
    }

    public void setClanRankingSet(ClanRankingsSet mClanRankingSet) {
        this.mClanRankingSet = mClanRankingSet;
    }

    public HashMap<Group, IndivRanking> getGroupRanking() {
        return mGroupRanking;
    }

    public void setGroupRanking(HashMap<Group, IndivRanking> mGroupRanking) {
        this.mGroupRanking = mGroupRanking;
    }

    public HashMap<Category, IndivRanking> getCategoryIndivRanking() {
        return mCategoryIndivRanking;
    }

    public void setCategoryIndivRanking(HashMap<Category, IndivRanking> mCategoryIndivRanking) {
        this.mCategoryIndivRanking = mCategoryIndivRanking;
    }

    public HashMap<Category, TeamRanking> getCategoryTeamRanking() {
        return mCategoryTeamRanking;
    }

    public void setCategoryTeamRanking(HashMap<Category, TeamRanking> mCategoryTeamRanking) {
        this.mCategoryTeamRanking = mCategoryTeamRanking;
    }

    public HashMap<Pool, IndivRankingsSet> getPoolIndivRankings() {
        return mPoolIndivRankings;
    }

    public void setPoolIndivRankings(HashMap<Pool, IndivRankingsSet> mPoolIndivRankings) {
        this.mPoolIndivRankings = mPoolIndivRankings;
    }

    public HashMap<Pool, TeamRankingsSet> getPoolTeamRankings() {
        return mPoolTeamRankings;
    }

    public void setPoolTeamRankings(HashMap<Pool, TeamRankingsSet> mPoolTeamRankings) {
        this.mPoolTeamRankings = mPoolTeamRankings;
    }

    public void update() {
        mIndivRankingSet.update();
        if (mTeamRankingSet != null) {
            mTeamRankingSet.update();
        }
        if (mClanRankingSet != null) {
            mClanRankingSet.update();
        }
        if (mGroupRanking != null) {
            for (Group g : mGroupRanking.keySet()) {
                mGroupRanking.get(g).sortDatas();
            }
        }

        if (mCategoryIndivRanking != null) {
            for (Category g : mCategoryIndivRanking.keySet()) {
                mCategoryIndivRanking.get(g).sortDatas();
            }
        }

        if (mCategoryTeamRanking != null) {
            for (Category g : mCategoryTeamRanking.keySet()) {
                mCategoryTeamRanking.get(g).sortDatas();
            }
        }

        if (mPoolIndivRankings != null) {
            for (Pool g : mPoolIndivRankings.keySet()) {
                mPoolIndivRankings.get(g).update();
            }
        }

        if (mPoolTeamRankings != null) {
            for (Pool g : mPoolTeamRankings.keySet()) {
                mPoolTeamRankings.get(g).update();
            }
        }

    }

    public Rankings(boolean roundOnly) {
        mIndivRankingSet = new IndivRankingsSet();
        mRoundOnly = roundOnly;
    }

    public void setRoundIndex(Tournament tour, int index) {
        mIndivRankingSet.setRoundIndex(index);

        if (tour.getParams().isTeamTournament()) {
            if (mTeamRankingSet == null) {
                mTeamRankingSet = new TeamRankingsSet();
            }
            mTeamRankingSet.setRoundIndex(index);
        }

        if (tour.getClansCount() > 1) {
            if (mClanRankingSet == null) {
                mClanRankingSet = new ClanRankingsSet();
            }
            mClanRankingSet.setRoundIndex(index);
        }

        if (tour.getGroupsCount() > 1) {
            if (mGroupRanking == null) {
                mGroupRanking = new HashMap<>();
            }
            for (int i = 0; i < tour.getGroupsCount(); i++) {

                Group g = tour.getGroup(i);
                mGroupRanking.get(g).setRoundIndex(index);
            }
        }

        if (tour.getCategoriesCount() > 1) {
            if (mCategoryIndivRanking == null) {
                mCategoryIndivRanking = new HashMap<>();
            }

            if (mCategoryTeamRanking == null) {
                mCategoryTeamRanking = new HashMap<>();
            }
            for (int i = 0; i < tour.getCategoriesCount(); i++) {
                Category cat = tour.getCategory(i);
                mCategoryIndivRanking.get(cat).setRoundIndex(index);
                mCategoryTeamRanking.get(cat).setRoundIndex(index);
            }
        }
        if (mPoolIndivRankings == null) {
            mPoolIndivRankings = new HashMap<>();
        }
        if (mPoolTeamRankings == null) {
            mPoolTeamRankings = new HashMap<>();
        }
        for (int i = 0; i < tour.getPoolCount(); i++) {
            Pool p = tour.getPool(i);
            ArrayList<Coach> listeC = new ArrayList<>();
            ArrayList<Team> listeT = new ArrayList<>();
            for (int j = 0; j < p.getCompetitorCount(); j++) {
                Competitor comp = p.getCompetitor(j);
                if (comp instanceof Coach) {
                    listeC.add((Coach) comp);
                }
                if (comp instanceof Team) {
                    listeT.add((Team) comp);
                }
            }

            if (listeC.size() > 0) {
                mPoolIndivRankings.get(p).setRoundIndex(index);
            }

            if (listeT.size() > 0) {
                mPoolTeamRankings.get(p).setRoundIndex(index);
            }
        }
    }

    public void createRankings(int rNumber, Tournament tour) {

        mIndivRankingSet.createRanking(rNumber, tour, mRoundOnly);

        if (tour.getParams().isTeamTournament()) {
            if (mTeamRankingSet == null) {
                mTeamRankingSet = new TeamRankingsSet();
            }
            mTeamRankingSet.createRanking(rNumber, tour, mRoundOnly);
        }

        if (tour.getClansCount() > 1) {
            if (mClanRankingSet == null) {
                mClanRankingSet = new ClanRankingsSet();
            }
            mClanRankingSet.createRanking(rNumber, tour, mRoundOnly);
        }

        if (tour.getGroupsCount() > 1) {
            if (mGroupRanking == null) {
                mGroupRanking = new HashMap<>();
            }
            for (int i = 0; i < tour.getGroupsCount(); i++) {

                ArrayList<Coach> liste = new ArrayList<>();
                Group g = tour.getGroup(i);
                for (int j = 0; j < tour.getCoachsCount(); j++) {
                    Coach c = tour.getCoach(j);
                    if (g.isMember(c)) {
                        liste.add(c);
                    }
                }
                IndivRanking ranking = new IndivRanking(rNumber, tour.getParams(), liste, tour.getParams().isTeamTournament(), mRoundOnly, tour.getPoolCount() > 0, false);
                mGroupRanking.put(g, ranking);
            }
        }

        if (tour.getCategoriesCount() > 1) {
            if (mCategoryIndivRanking == null) {
                mCategoryIndivRanking = new HashMap<>();
            }

            if (mCategoryTeamRanking == null) {
                mCategoryTeamRanking = new HashMap<>();
            }
            for (int i = 0; i < tour.getCategoriesCount(); i++) {
                ArrayList<Coach> listeC = new ArrayList<>();
                ArrayList<Team> listeT = new ArrayList<>();
                Category cat = tour.getCategory(i);

                for (int j = 0; j < tour.getCoachsCount(); j++) {
                    Coach c = tour.getCoach(j);
                    if (c.containsCategory(cat)) {
                        listeC.add(c);
                    }
                }

                for (int j = 0; j < tour.getTeamsCount(); j++) {
                    Team t = tour.getTeam(j);
                    if (t.containsCategory(cat)) {
                        listeT.add(t);
                    }
                }
                IndivRanking iranking = new IndivRanking(rNumber, tour.getParams(), listeC, tour.getParams().isTeamTournament(), mRoundOnly, tour.getPoolCount() > 0, false);
                mCategoryIndivRanking.put(cat, iranking);

                TeamRanking tranking = new TeamRanking(tour.getParams().isTeamVictoryOnly(), rNumber, tour.getParams(), listeT, mRoundOnly);
                mCategoryTeamRanking.put(cat, tranking);
            }
        }
        if (mPoolIndivRankings == null) {
            mPoolIndivRankings = new HashMap<>();
        }
        if (mPoolTeamRankings == null) {
            mPoolTeamRankings = new HashMap<>();
        }
        for (int i = 0; i < tour.getPoolCount(); i++) {
            Pool p = tour.getPool(i);
            ArrayList<Coach> listeC = new ArrayList<>();
            ArrayList<Team> listeT = new ArrayList<>();
            for (int j = 0; j < p.getCompetitorCount(); j++) {
                Competitor comp = p.getCompetitor(j);
                if (comp instanceof Coach) {
                    listeC.add((Coach) comp);
                }
                if (comp instanceof Team) {
                    listeT.add((Team) comp);
                }
            }

            if (listeC.size() > 0) {
                IndivRankingsSet irs = new IndivRankingsSet();
                irs.createRanking(rNumber, tour, mRoundOnly, listeC);
                mPoolIndivRankings.put(p, irs);
            }

            if (listeT.size() > 0) {
                TeamRankingsSet trs = new TeamRankingsSet();
                trs.createRanking(rNumber, tour, mRoundOnly, listeT);
                mPoolTeamRankings.put(p, trs);
            }
        }

    }

    public IndivRankingsSet getIndivRankingSet() {
        return mIndivRankingSet;
    }

    public void setIndivRankingSet(IndivRankingsSet mIndivRankingSet) {
        this.mIndivRankingSet = mIndivRankingSet;
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
        final Element rankings;
        if (!mRoundOnly) {
            rankings = new Element(StringConstants.CS_RANKINGS);
        } else {
            rankings = new Element(StringConstants.CS_RANKINGS_RO);
        }

        rankings.setAttribute(StringConstants.CS_ROUNDONLY, Boolean.toString(mRoundOnly));

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

        if (e != null) {
            final List<Element> childs = e.getChildren();

            try {
                mRoundOnly = e.getAttribute(StringConstants.CS_ROUNDONLY).getBooleanValue();
            } catch (DataConversionException dce) {
                dce.printStackTrace();
            }
            final Iterator<Element> k = childs.iterator();
            while (k.hasNext()) {
                final Element child = k.next();

                String name = child.getName();
                switch (name) {
                    case StringConstants.CS_POOL_TEAM_RANKING: {
                        if (mPoolTeamRankings == null) {
                            mPoolTeamRankings = new HashMap<>();
                        }
                        try {
                            int pIndex = child.getAttribute(StringConstants.CS_POOL).getIntValue()-1;
                            TeamRankingsSet r = new TeamRankingsSet(child.getChild(StringConstants.CS_TEAM_RANKING_SET));
                            Pool p = Tournament.getTournament().getPool(pIndex);
                            mPoolTeamRankings.put(p, r);
                        } catch (DataConversionException dte) {
                            dte.printStackTrace();
                        }
                    }
                    break;
                    case StringConstants.CS_POOL_INDIV_RANKING: {
                        try {
                            if (mPoolIndivRankings == null) {
                                mPoolIndivRankings = new HashMap<>();
                            }
                            int pIndex = child.getAttribute(StringConstants.CS_POOL).getIntValue()-1;
                            Pool p = Tournament.getTournament().getPool(pIndex);
                            IndivRankingsSet r = new IndivRankingsSet(child.getChild(StringConstants.CS_INDIV_RANKING_SET));
                            mPoolIndivRankings.put(p, r);
                        } catch (DataConversionException dte) {
                            dte.printStackTrace();
                        }
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
                        IndivRanking r = new IndivRanking(child.getChild(StringConstants.CS_INDIV_RANKING));
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

}
