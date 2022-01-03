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
import org.json.JSONObject;

/**
 *
 * @author WFMJ7631
 */
abstract public class AnnexRanking extends Ranking {

    public enum SubType {
        POSITIVE, NEGATIVE, DIFFERENCE, FORMULA
    }

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
    abstract public void sortDatas();

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
        if (mType == null) {
            mType = "";
        }
        e.setAttribute(StringConstants.CS_TYPE, mType);

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
            try {
                String crit = e.getAttribute(StringConstants.CS_CRITERION).getValue();
                this.mCriterion = Tournament.getTournament().getParams().getCriterion(crit);
            } catch (NullPointerException npe) {

            }
            try {
                String form = e.getAttribute(StringConstants.CS_FORMULA).getValue();
                this.mFormula = Tournament.getTournament().getParams().getFormula(form);
            } catch (NullPointerException npe) {

            }
            /**
             * mDatas
             */
            final List<Element> positions = e.getChildren(StringConstants.CS_POSITION);
            final Iterator<Element> k = positions.iterator();
            mDatas.clear();
            mObjects = new ArrayList();
            while (k.hasNext()) {
                Element pos = k.next();
                Comparable<Object> obj = null;

                Attribute att = pos.getAttribute(StringConstants.CS_COACH);
                if (att != null) {
                    String name = att.getValue();
                    Coach coach = Tournament.getTournament().getCoach(name);
                    obj = coach;
                } else {
                    att = pos.getAttribute(StringConstants.CS_TEAM);
                    if (att != null) {
                        String name = att.getValue();
                        Team t = Tournament.getTournament().getTeam(name);
                        obj = t;
                    } else {
                        att = pos.getAttribute(StringConstants.CS_CLAN);
                        if (att != null) {
                            String name = att.getValue();
                            Clan clan = Tournament.getTournament().getClan(name);
                            obj = clan;
                        }
                    }
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

    @Override
    public JSONObject getJSON() {

        JSONObject json = super.getJSON();

        if (mCriterion != null) {
            json.put("criterionName", this.mCriterion.getName());
            json.put("criterionAccronym", this.mCriterion.getAccronym());
        } else {
            if (mFormula != null) {
                json.put("formulaName", this.mFormula.getName());
            }
        }
        switch (this.mSubtype) {
            case 0:
                json.put("subtypeString", "+");
                break;
            case 1:
                json.put("subtypeString", "-");
                break;
            case 2:
                json.put("subtypeString", "=");
                break;
        }

        return json;
    }

    @Override
    public void updateFromJSON(JSONObject object) {
        super.updateFromJSON(object);

        if (object.get("critetrionName") != JSONObject.NULL) {
            String tmp = object.getString("criterionName");

            Criterion crit = Tournament.getTournament().getParams().getCriterion(tmp);
            this.mCriterion = crit;
        }

        if (object.get("formulaName") != JSONObject.NULL) {
            String tmp = object.getString("formulaName");

            Formula form = Tournament.getTournament().getParams().getFormula(tmp);
            this.mFormula = form;
        }

        String tmp = object.getString("subtypeString");
        switch (tmp) {
            case "+":
                this.mSubtype = 0;
                break;
            case "-":
                this.mSubtype = 1;
                break;
            case "=":
                this.mSubtype = 2;
                break;
        }

    }
}
