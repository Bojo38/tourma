/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import org.apache.xerces.impl.dv.util.Base64;
import org.jdom2.Attribute;
import org.jdom2.DataConversionException;
import org.jdom2.Element;
import tourma.tableModel.MjtRanking;
import tourma.utility.StringConstants;
import tourma.utils.Ranked;

/**
 *
 * @author WFMJ7631
 */
public class Ranking implements XMLExport, Ranked {

    private static final Logger LOG = Logger.getLogger(Ranking.class.getName());

    /**
     *
     */
    private MjtRanking mRank;

    /**
     *
     */
    private String mName;

    /**
     *
     */
    private String mType;

    /**
     *
     */
    private String mValueType;

    private ArrayList<Integer> mRankings;
    private ArrayList<String> mCriterias;
    private ArrayList<ObjectRanking> mObjectsRanked;

    private int mRoundIndex = -1;

    /**
     *
     * @param name
     * @param type
     * @param valueType
     * @param rank
     * @param rankings
     */
    public Ranking(final String name, final String type, final String valueType, final MjtRanking rank, ArrayList<Integer> rankings) {
        mRank = rank;
        mName = name;
        mType = type;
        mValueType = valueType;
        mRankings = new ArrayList<>(rankings);
    }

    public Ranking(Element e) {
        this.setXMLElement(e);
    }

    /**
     *
     * @return
     */
    @Override
    public Element getXMLElement() {
        ResourceBundle bundle = java.util.ResourceBundle.getBundle("tourma/languages/language");

        final Element rank = new Element(bundle.getString("RANKING"));
        rank.setAttribute(new Attribute(bundle.getString("NAME"), getName()));
        rank.setAttribute(new Attribute(bundle.getString("TYPE"), getType()));
        rank.setAttribute(new Attribute(bundle.getString("ORDER"), getValueType()));
        rank.setAttribute(new Attribute(bundle.getString("ROUND"), Integer.toString(getRank().getRound())));
        rank.setAttribute(new Attribute("BYTEAM", Boolean.toString(Tournament.getTournament().getParams().isTeamTournament())));
        rank.setAttribute("USEPICTURE", Boolean.toString(Tournament.getTournament().getParams().isUseImage()));
        for (int i = 0; i < mRankings.size(); i++) {
            int rankType = mRankings.get(i).intValue();
            rank.setAttribute(new Attribute("R" + (i + 1), MjtRanking.getRankingString(rankType)));
        }

        for (int i = 0; i < Tournament.getTournament().getParams().getCriteriaCount(); i++) {
            Criteria crit = Tournament.getTournament().getParams().getCriteria(i);
            rank.setAttribute(new Attribute("C" + (i + 1), crit.getName()));
        }

        //final ArrayList<ObjectRanking> datas = getRank().getSortedDatas();
        for (int k = 0; k < getRank().getRowCount(); k++) {
            final Element ic = getRank().getSortedObject(k).getXMLElement();
            ic.setAttribute(new Attribute(bundle.getString("POS"), Integer.toString(k + 1)));
            rank.addContent(ic);
        }
        return rank;
    }

    /**
     *
     * @param e
     */
    @Override
    public void setXMLElement(final Element e) {

        ResourceBundle bundle = java.util.ResourceBundle.getBundle("tourma/languages/language");

        mObjectsRanked = new ArrayList();

        int round = -1;

        if (e.getName().equals(bundle.getString("RANKING"))) {
            this.mName = e.getAttributeValue(bundle.getString("NAME"));
            this.mType = e.getAttributeValue(bundle.getString("TYPE"));
            this.mValueType = e.getAttributeValue(bundle.getString("ORDER"));

            try {
                Tournament.getTournament().getParams().setTeamTournament(e.getAttribute("BYTEAM").getBooleanValue());
            } catch (DataConversionException ex) {
                Logger.getLogger(Ranking.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                Tournament.getTournament().getParams().setUseImage(e.getAttribute("USEPICTURE").getBooleanValue());
            } catch (DataConversionException ex) {
                Logger.getLogger(Ranking.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                round = e.getAttribute(bundle.getString("ROUND")).getIntValue();
            } catch (DataConversionException ex) {
                Logger.getLogger(Ranking.class.getName()).log(Level.SEVERE, null, ex);
            }
            int i = 1;
            Attribute a = e.getAttribute("C" + i);
            mCriterias = new ArrayList<>();
            while (a != null) {
                this.mCriterias.add(a.getValue());
                i++;
                a = e.getAttribute("C" + i);
            }

            i = 1;
            a = e.getAttribute("R" + i);
            mRankings = new ArrayList<>();
            while (a != null) {
                this.mRankings.add(MjtRanking.getRankingFromString(a.getValue(), mCriterias));
                i++;
                a = e.getAttribute("" + i);
            }

            List<Element> objs = e.getChildren();
            Iterator it = objs.iterator();
            while (it.hasNext()) {
                try {
                    Element obj = (Element) it.next();
                    int value1 = obj.getAttribute(bundle.getString("RANK1")).getIntValue();
                    int value2 = obj.getAttribute(bundle.getString("RANK2")).getIntValue();
                    int value3 = obj.getAttribute(bundle.getString("RANK3")).getIntValue();
                    int value4 = obj.getAttribute(bundle.getString("RANK4")).getIntValue();
                    int value5 = obj.getAttribute(bundle.getString("RANK5")).getIntValue();

                    Element pict = obj.getChild("Picture");
                    BufferedImage bi = null;
                    if (pict != null) {
                        try {
                            String encodedImage = pict.getText();
                            if (!encodedImage.isEmpty()) {
                                byte[] bytes = Base64.decode(encodedImage);
                                bi = ImageIO.read(new ByteArrayInputStream(bytes));
                            }
                        } catch (IOException ie) {
                            LOG.log(Level.FINE, ie.getLocalizedMessage());
                        }
                    }

                    ObjectRanking so = null;
                    Attribute att = obj.getAttribute(StringConstants.CS_COACH);
                    if (att != null) {
                        Coach c = new Coach();
                        c.setName(att.getValue());
                        c.setTeam(obj.getAttribute(bundle.getString("TEAM")).getValue());
                        String clanName = obj.getAttribute(bundle.getString("CLAN")).getValue();
                        String teamName = obj.getAttribute(bundle.getString("TEAMMATES")).getValue();

                        Clan cl = null;
                        if (!Tournament.getTournament().containsClan(clanName)) {
                            cl = new Clan(clanName);
                            Tournament.getTournament().addClan(cl);
                        } else {
                            cl = Tournament.getTournament().getClan(clanName);
                        }
                        c.setClan(cl);

                        if (!teamName.equals("")) {
                            Team tm = null;
                            if (!Tournament.getTournament().containsTeam(teamName)) {
                                tm = new Team(teamName);
                                Tournament.getTournament().addTeam(tm);
                            } else {
                                tm = Tournament.getTournament().getTeam(teamName);
                            }
                            c.setTeamMates(tm);
                            if (!tm.containsCoach(c)) {
                                tm.addCoach(c);
                            }
                        }

                        if (!Tournament.getTournament().containsClan(clanName)) {
                            cl = new Clan(clanName);
                        } else {
                            cl = Tournament.getTournament().getClan(clanName);
                        }
                        c.setClan(cl);
                        c.setTeam(obj.getAttribute(bundle.getString("TEAM")).getValue());
                        so = new ObjectRanking(c, value1, value2, value3, value4, value5);

                        RosterType rt = new RosterType(bundle.getString("ROSTER"));
                        c.setRoster(rt);
                        if (bi != null) {
                            c.setPicture(bi);
                        }

                    } else {
                        att = obj.getAttribute(bundle.getString("CLAN"));
                        if (att != null) {
                            String clanName = att.getValue();
                            Clan cl = Tournament.getTournament().getClan(clanName);
                            if (cl == null) {
                                cl = new Clan(clanName);
                                Tournament.getTournament().addClan(cl);
                            }
                            if (bi != null) {
                                cl.setPicture(bi);
                            }
                            List<Element> members=obj.getChildren(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("MEMBER"));
                            Iterator<Element> itm = members.iterator();
                            while(itm.hasNext())
                            {
                                Element em=itm.next();
                                if (Tournament.getTournament().getParams().isTeamTournament())
                                {
                                    Team t=new Team();
                                    String name=em.getAttributeValue(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("NAME"));
                                    if(!Tournament.getTournament().containsTeam(name))
                                    {
                                        Tournament.getTournament().addTeam(t);
                                        t.setName(name);
                                    }
                                    else
                                    {
                                        t=Tournament.getTournament().getTeam(name);
                                    }
                                    t.setClan(cl);
                                }
                                else
                                {
                                    Coach c=new Coach();
                                    String name=em.getAttributeValue(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("NAME"));
                                    if(!Tournament.getTournament().containsCoach(name))
                                    {
                                        Tournament.getTournament().addCoach(c);
                                        c.setName(name);
                                    }
                                    else
                                    {
                                        c=Tournament.getTournament().getCoach(name);
                                    }
                                    c.setClan(cl);
                                }
                            }
                            
                            so = new ObjectRanking(cl, value1, value2, value3, value4, value5);
                        } else {
                            att = obj.getAttribute(bundle.getString("NAME"));
                            if (att != null) {
                                String name = att.getValue();
                                Team t = new Team();
                                t.setName(name);
                                if (bi != null) {
                                    t.setPicture(bi);
                                }
                                so = new ObjectRanking(t, value1, value2, value3, value4, value5);
                            }
                        }
                    }

                    if (so != null) {
                        mObjectsRanked.add(so);
                    }
                } catch (DataConversionException ex) {
                    Logger.getLogger(Ranking.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (mName.equals(bundle.getString("INDIVIDUAL"))) {
                if (mType.equals(bundle.getString("GENERAL"))) {
                    /*this.mRank = new MjtRankingIndiv(
                     round,
                     mRankings,
                     mObjectsRanked);*/
                    Tournament.getTournament().getParams().setRankingIndiv1(mRankings.size() > 0 ? mRankings.get(0) : Parameters.C_RANKING_NONE);
                    Tournament.getTournament().getParams().setRankingIndiv2(mRankings.size() > 1 ? mRankings.get(1) : Parameters.C_RANKING_NONE);
                    Tournament.getTournament().getParams().setRankingIndiv3(mRankings.size() > 2 ? mRankings.get(2) : Parameters.C_RANKING_NONE);
                    Tournament.getTournament().getParams().setRankingIndiv4(mRankings.size() > 3 ? mRankings.get(3) : Parameters.C_RANKING_NONE);
                    Tournament.getTournament().getParams().setRankingIndiv5(mRankings.size() > 4 ? mRankings.get(4) : Parameters.C_RANKING_NONE);
                }
            }

            Tournament.getTournament().getParams().clearCiterias();
            for (String crit : mCriterias) {
                Criteria cr = new Criteria(crit);
                Tournament.getTournament().getParams().addCriteria(cr);
            }
        }

    }

    public int getRankingNumber() {
        return mRankings.size();
    }

    /**
     * @return the mRank
     */
    public MjtRanking getRank() {
        return mRank;
    }

    /**
     * @param mRank the mRank to set
     */
    public void setRank(MjtRanking mRank) {
        this.mRank = mRank;
    }

    /**
     * @return the mName
     */
    public String getName() {
        return mName;
    }

    /**
     * @param mName the mName to set
     */
    public void setName(String mName) {
        this.mName = mName;
    }

    /**
     * @return the mType
     */
    public String getType() {
        return mType;
    }

    /**
     * @param mType the mType to set
     */
    public void setType(String mType) {
        this.mType = mType;
    }

    /**
     * @return the mValueType
     */
    public String getValueType() {
        return mValueType;
    }

    /**
     * @param mValueType the mValueType to set
     */
    public void setValueType(String mValueType) {
        this.mValueType = mValueType;
    }

    @Override
    public int getRowCount() {
        if (mObjectsRanked != null) {
            return mObjectsRanked.size();
        } else {
            return 0;
        }

    }

    @Override
    public ObjectRanking getSortedObject(int i) {
        if (mObjectsRanked != null) {
            if (mObjectsRanked.size() > i) {
                return mObjectsRanked.get(i);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    @Override
    public int getSortedValue(int i, int valIndex) {
        if (mObjectsRanked != null) {
            if (mObjectsRanked.size() > i) {
                switch (valIndex) {
                    case 1:
                        return mObjectsRanked.get(i).getValue1();
                    case 2:
                        return mObjectsRanked.get(i).getValue2();
                    case 3:
                        return mObjectsRanked.get(i).getValue3();
                    case 4:
                        return mObjectsRanked.get(i).getValue4();
                    case 5:
                        return mObjectsRanked.get(i).getValue5();
                    default:
                        return 0;
                }
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }
}
