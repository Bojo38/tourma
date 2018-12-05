/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import org.apache.xerces.impl.dv.util.Base64;
import org.jdom.Attribute;
import org.jdom.DataConversionException;
import org.jdom.Element;
import tourma.tableModel.MjtRanking;
import tourma.utility.StringConstants;
import tourma.utils.display.IRanked;

/**
 *
 * @author WFMJ7631
 */
public final class Ranking implements IXMLExport, IRanked, Serializable {

    private static final Logger LOG = Logger.getLogger(Ranking.class.getName());
    public static final String CS_Individual_Annex = "INDIVIDUAL_ANNEX";
    public static final String CS_Team_Annex = "TEAM_ANNEX";
    public static final String CS_Clan_Annex = "CLAN_ANNEX";
    public static final String CS_Individual = "INDIVIDUAL";
    public static final String CS_General = "General";
    public static final String CS_Clan = "Clan";
    public static final String CS_Group = "Group";
    public static final String CS_Positive="Positive";
    public static final String CS_Negative="Negative";
    public static final String CS_Team="Team";
    public static final String CS_array="array";
    public static final String CS_Matchs="Matchs";

    
        protected static AtomicInteger sGenUID=new AtomicInteger(0);
    protected int UID=sGenUID.incrementAndGet();

    public int getUID() {
        return UID;
    }

    public void setUID(int UID) {
        this.UID = UID;
    }

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

    private Criteria mCriteria = null;

    /**
     *
     * @param name
     * @param type
     * @param valueType
     * @param rank
     * @param rankings
     */
    public Ranking(final String name, final String type, final String valueType, final MjtRanking rank, ArrayList<Integer> rankings)  {
        mRank = rank;
        mName = name;
        mType = type;
        mValueType = valueType;
        mRankings = new ArrayList<>(rankings);
    }

    public Ranking(Element e) {
        this.setXMLElement(e);
    }

    public Criteria getCriteria() {
        if (mCriteria == null) {
            mCriteria = new Criteria("???");
        }
        return mCriteria;
    }

    public void setCriteria(Criteria c) {
        mCriteria = c;
    }

    /**
     *
     * @return
     */
    @Override
    public Element getXMLElement() {

        final Element rank = new Element(StringConstants.CS_RANKING);
        rank.setAttribute(new Attribute(StringConstants.CS_NAME, getName()));
        rank.setAttribute(new Attribute(StringConstants.CS_TYPE, getType()));
        rank.setAttribute(new Attribute(StringConstants.CS_ORDER, getValueType()));
        rank.setAttribute(new Attribute(StringConstants.CS_ROUND, Integer.toString(getRank().getRound())));
        rank.setAttribute(new Attribute(StringConstants.CS_BYTEAM, Boolean.toString(Tournament.getTournament().getParams().isTeamTournament())));
        rank.setAttribute(StringConstants.CS_USE_IMAGE, Boolean.toString(Tournament.getTournament().getParams().isUseImage()));
        for (int i = 0; i < mRankings.size(); i++) {
            int rankType = mRankings.get(i);
            rank.setAttribute(new Attribute("R" + (i + 1), MjtRanking.getRankingString(rankType)));
        }

        for (int i = 0; i < Tournament.getTournament().getParams().getCriteriaCount(); i++) {
            Criteria crit = Tournament.getTournament().getParams().getCriteria(i);
            rank.setAttribute(new Attribute("C" + (i + 1), crit.getName()));
        }

        //final ArrayList<ObjectRanking> datas = getRank().getSortedDatas();
        for (int k = 0; k < getRank().getRowCount(); k++) {

            Element ic;
            Object obj = getRank().getSortedObject(k);
            if (obj instanceof ObjectAnnexRanking) {
                ic = ((IXMLExport) obj).getXMLElement();
            } else {
                ic = ((IXMLExport) obj).getXMLElement();
            }
            ic.setAttribute(new Attribute(StringConstants.CS_POS, Integer.toString(k + 1)));
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

        mObjectsRanked = new ArrayList<>();
        boolean annex = false;
        int round = -1;
        if (e.getName().equals(StringConstants.CS_RANKING)) {
            this.mName = e.getAttributeValue(StringConstants.CS_NAME);
            this.mType = e.getAttributeValue(StringConstants.CS_TYPE);
            this.mValueType = e.getAttributeValue(StringConstants.CS_ORDER);

            if (getName().equals(CS_Individual_Annex)
                    || getName().equals(CS_Team_Annex)
                    || getName().equals(CS_Clan_Annex)) {
                mCriteria = new Criteria(mType);
                annex = true;
            }

            try {
                Tournament.getTournament().getParams().setTeamTournament(e.getAttribute(StringConstants.CS_BYTEAM).getBooleanValue());
            } catch (DataConversionException ex) {
                Logger.getLogger(Ranking.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                Tournament.getTournament().getParams().setUseImage(e.getAttribute(StringConstants.CS_USE_IMAGE).getBooleanValue());
            } catch (DataConversionException ex) {
                Logger.getLogger(Ranking.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                round = e.getAttribute(StringConstants.CS_ROUND).getIntValue();
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
            Iterator<Element> it = objs.iterator();
            while (it.hasNext()) {
                try {
                    Element obj = it.next();
                    int value = 0;
                    if (annex) {
                        value = obj.getAttribute(StringConstants.CS_VALUE).getIntValue();
                    }
                    int value1 = 0;
                    int value2 = 0;
                    int value3 = 0;
                    int value4 = 0;
                    int value5 = 0;

                    if (!annex) {
                        value1 = obj.getAttribute(StringConstants.CS_RANK + 1).getIntValue();
                        value2 = obj.getAttribute(StringConstants.CS_RANK + 2).getIntValue();
                        value3 = obj.getAttribute(StringConstants.CS_RANK + 3).getIntValue();
                        value4 = obj.getAttribute(StringConstants.CS_RANK + 4).getIntValue();
                        value5 = obj.getAttribute(StringConstants.CS_RANK + 5).getIntValue();
                    }

                    Element pict = obj.getChild(StringConstants.CS_PICTURE);
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
                        c.setTeam(obj.getAttribute(StringConstants.CS_TEAM).getValue());
                        String clanName = obj.getAttribute(StringConstants.CS_CLAN).getValue();
                        String teamName = obj.getAttribute(StringConstants.CS_TEAMMATES).getValue();

                        Clan cl;
                        if (!Tournament.getTournament().containsClan(clanName)) {
                            cl = new Clan(clanName);
                            Tournament.getTournament().addClan(cl);
                        } else {
                            cl = Tournament.getTournament().getClan(clanName);
                        }
                        c.setClan(cl);

                        if (!teamName.isEmpty()) {
                            Team tm;
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
                        c.setTeam(obj.getAttribute(StringConstants.CS_TEAM).getValue());
                        if (annex) {
                            so = new ObjectAnnexRanking(c, value, value1, value2, value3, value4, value5);
                        } else {
                            so = new ObjectRanking(c, value1, value2, value3, value4, value5);
                        }

                        RosterType rt = new RosterType(StringConstants.CS_ROSTER);
                        c.setRoster(rt);
                        if (bi != null) {
                            c.setPicture(new ImageIcon(bi));
                        }

                    } else {
                        att = obj.getAttribute(StringConstants.CS_CLAN);
                        if (att != null) {
                            String clanName = att.getValue();
                            Clan cl = Tournament.getTournament().getClan(clanName);
                            if (cl == null) {
                                cl = new Clan(clanName);
                                Tournament.getTournament().addClan(cl);
                            }
                            if (bi != null) {
                                cl.setPicture(new ImageIcon(bi));
                            }
                            List<Element> members = obj.getChildren(StringConstants.CS_MEMBER);
                            Iterator<Element> itm = members.iterator();
                            while (itm.hasNext()) {
                                Element em = itm.next();
                                if (Tournament.getTournament().getParams().isTeamTournament()) {
                                    Team t = new Team();
                                    String name = em.getAttributeValue(StringConstants.CS_NAME);
                                    if (!Tournament.getTournament().containsTeam(name)) {
                                        Tournament.getTournament().addTeam(t);
                                        t.setName(name);
                                    } else {
                                        t = Tournament.getTournament().getTeam(name);
                                    }
                                    t.setClan(cl);
                                } else {
                                    Coach c = new Coach();
                                    String name = em.getAttributeValue(StringConstants.CS_NAME);
                                    if (!Tournament.getTournament().containsCoach(name)) {
                                        Tournament.getTournament().addCoach(c);
                                        c.setName(name);
                                    } else {
                                        c = Tournament.getTournament().getCoach(name);
                                    }
                                    c.setClan(cl);
                                }
                            }

                            if (annex) {
                                so = new ObjectAnnexRanking(cl, value, value1, value2, value3, value4, value5);
                            } else {
                                so = new ObjectRanking(cl, value1, value2, value3, value4, value5);
                            }
                        } else {
                            att = obj.getAttribute(StringConstants.CS_NAME);
                            if (att != null) {
                                String name = att.getValue();
                                Team t = new Team();
                                t.setName(name);
                                if (bi != null) {
                                    t.setPicture(new ImageIcon(bi));
                                }
                                if (annex) {
                                    so = new ObjectAnnexRanking(t, value, value1, value2, value3, value4, value5);
                                } else {
                                    so = new ObjectRanking(t, value1, value2, value3, value4, value5);
                                }
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

            if (mName.equals(CS_Individual)) {
                if (mType.equals(CS_General)) {
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

            Tournament.getTournament()
                    .getParams().clearCiterias();
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

    @Override
    public String getDetail() {
        return mType;
    }

    @Override
    public void setDetail(String d) {
        mType = d;
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
