/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data.ranking;

import java.util.ArrayList;
import org.jdom.Element;
import tourma.data.Criterion;
import tourma.data.Formula;

/**
 *
 * @author WFMJ7631
 */
abstract public class AnnexRanking extends Ranking {

    public AnnexRanking(Element e)
    {
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

    public int getSubtype() {
        return mSubtype;
    }
}
