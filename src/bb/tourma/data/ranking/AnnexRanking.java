/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bb.tourma.data.ranking;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jdom.Attribute;
import org.jdom.DataConversionException;
import org.jdom.Element;
import bb.tourma.data.Clan;
import bb.tourma.data.Coach;
import bb.tourma.data.Criterion;
import bb.tourma.data.Formula;
import bb.tourma.data.ObjectAnnexRanking;
import bb.tourma.data.ObjectRanking;
import bb.tourma.data.Team;
import bb.tourma.data.Tournament;
import bb.tourma.utility.StringConstants;

/**
 *
 * @author WFMJ7631
 */
abstract public class AnnexRanking extends Ranking {

    public AnnexRanking(Element e) {
        super(e);
        setXMLElement(e);
    }

    public AnnexRanking(
            final int round,
            final Criterion criteria,
            final int subtype,
            final ArrayList objects,
            final int ranking_type1,
            final int ranking_type2,
            final int ranking_type3,
            final int ranking_type4,
            final int ranking_type5,
            final boolean roundOnly) {
        super(round,
                ranking_type1,
                ranking_type2,
                ranking_type3,
                ranking_type4,
                ranking_type5,
                objects,
                roundOnly);

        mCriterion = criteria;
        mFormula = null;
        mSubtype = subtype;
        sortDatas();

    }

    ;
    public AnnexRanking(
            final int round,
            final Formula formula,
            final int subtype,
            final ArrayList objects,
            final int ranking_type1,
            final int ranking_type2,
            final int ranking_type3,
            final int ranking_type4,
            final int ranking_type5,
            final boolean roundOnly) {
        super(round,
                ranking_type1,
                ranking_type2,
                ranking_type3,
                ranking_type4,
                ranking_type5,
                objects,
                roundOnly);

        mCriterion = null;
        mFormula = formula;
        mSubtype = subtype;
        //_ranking_type = ranking_type;
        sortDatas();
    }

    @Override
    abstract protected void sortDatas();

    public Criterion getCriterion() {
        return mCriterion;
    }

    public void setCriterion(Criterion c) {
        mCriterion = c;
    }

    public Formula getFormula() {
        return mFormula;
    }

    public void setFormula(Formula f) {
        mFormula = f;
    }

    protected String mType;

    /**
     *
     */
    @SuppressWarnings("ProtectedField")
    protected Criterion mCriterion;
    protected Formula mFormula;
    /**
     *
     */
    @SuppressWarnings("ProtectedField")
    protected int mSubtype;

    public void setSubtype(int mSubtype) {
        this.mSubtype = mSubtype;
    }

    public int getSubtype() {
        return mSubtype;
    }

    @Override
    public Element getXMLElement() {

        Element e = super.getXMLElement();
        e.setName(StringConstants.CS_ANNEX_RANKING);

        if (mCriterion != null) {
            e.setAttribute(StringConstants.CS_CRITERION, mCriterion.getName());
        }

        if (mFormula != null) {
            e.setAttribute(StringConstants.CS_FORMULA, mFormula.getName());
        }

        e.setAttribute(StringConstants.CS_SUBTYPE, Integer.toString(mSubtype));
        if (mType==null)
        {
            mType="";
        }
        e.setAttribute(StringConstants.CS_TYPE, mType);

        /*e.removeChildren(StringConstants.CS_POSITION);

         if (mDatas != null) {
            for (int i = 0; i < mDatas.size(); i++) {
                ObjectRanking obj = mDatas.get(i);
                if (obj instanceof ObjectAnnexRanking) {
                    Element or = ((ObjectAnnexRanking) obj).getXMLElement();
                    or.setAttribute(StringConstants.CS_RANK, Integer.toString(i + 1));
                    e.addContent(or);
                }
            }
        }*/

        return e;
    }

    @Override
    public void setXMLElement(Element e) {

        try {
            mRankingType1 = e.getAttribute(StringConstants.CS_RANK + 1).getIntValue();
            mRankingType2 = e.getAttribute(StringConstants.CS_RANK + 2).getIntValue();
            mRankingType3 = e.getAttribute(StringConstants.CS_RANK + 3).getIntValue();
            mRankingType4 = e.getAttribute(StringConstants.CS_RANK + 4).getIntValue();
            mRankingType5 = e.getAttribute(StringConstants.CS_RANK + 5).getIntValue();

            mRound = e.getAttribute(StringConstants.CS_ROUND).getIntValue();

            mRoundOnly = e.getAttribute(StringConstants.CS_ROUNDONLY).getBooleanValue();

            mDetail = e.getAttribute(StringConstants.CS_DETAILS).getValue();

            /**
             * mDatas
             */
            final List<Element> positions = e.getChildren(StringConstants.CS_POSITION);
            final Iterator<Element> k = positions.iterator();
            mDatas.clear();
            mObjects=new ArrayList();
            while (k.hasNext()) {
                Element pos = k.next();
                Comparable<Object> obj = null;

                Attribute att = pos.getAttribute(StringConstants.CS_COACH);
                if (att != null) {
                    String name = att.getValue();
                    Coach c = Tournament.getTournament().getCoach(name);
                    obj = c;
                }
                att = pos.getAttribute(StringConstants.CS_TEAM);
                if (att != null) {
                    String name = att.getValue();
                    Team t = Tournament.getTournament().getTeam(name);
                    obj = t;
                }
                att = pos.getAttribute(StringConstants.CS_CLAN);
                if (att != null) {
                    String name = att.getValue();
                    Clan c = Tournament.getTournament().getClan(name);
                    obj = c;
                }

                if (obj != null) {

                    int val1 = e.getAttribute(StringConstants.CS_RANK + 1).getIntValue();
                    int val2 = e.getAttribute(StringConstants.CS_RANK + 2).getIntValue();
                    int val3 = e.getAttribute(StringConstants.CS_RANK + 3).getIntValue();
                    int val4 = e.getAttribute(StringConstants.CS_RANK + 4).getIntValue();
                    int val5 = e.getAttribute(StringConstants.CS_RANK + 5).getIntValue();

                    ObjectRanking or = new ObjectRanking(obj, val1, val2, val3, val4, val5);
                    mDatas.add(or);
                    mObjects.add(obj);
                }
            }

        } catch (DataConversionException dce) {
            dce.printStackTrace();;
        }
    }
}
