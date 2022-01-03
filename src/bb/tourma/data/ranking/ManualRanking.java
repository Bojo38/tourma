/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bb.tourma.data.ranking;

import java.util.ArrayList;
import org.jdom.Element;
import bb.tourma.data.Competitor;
import bb.tourma.data.ObjectRanking;
import bb.tourma.utility.StringConstants;
import org.json.JSONObject;

/**
 *
 * @author WFMJ7631
 */
public class ManualRanking extends Ranking {

    public ManualRanking(final int round,
            final int ranking_type1,
            final int ranking_type2,
            final int ranking_type3,
            final int ranking_type4,
            final int ranking_type5,
            final ArrayList competitors, boolean round_only) {
        super(round, ranking_type1, ranking_type2, ranking_type3, ranking_type4, ranking_type5, competitors, round_only);
        sortDatas();
    }

    /**
     *
     */
    @Override
    public void sortDatas() {
        mDatas.clear();
        mDatas = new ArrayList();

        addDatas(mObjects);

    }

    protected void addData(Competitor obj) {
        if (!mObjects.contains(obj)) {
            mObjects.add(obj);
        }
        ObjectRanking o = new ObjectRanking(obj, mDatas.size(), 0, 0, 0, 0);
        mDatas.add(o);

    }

    protected void delData(Competitor obj) {
        if (mObjects.contains(obj)) {
            mObjects.remove(obj);

            for (Object o : mDatas) {
                if (o instanceof ObjectRanking) {
                    if (((ObjectRanking) o).getObject().equals(obj)) {
                        mDatas.remove(o);
                        break;
                    }
                }
            }

        }

    }

    protected void addDatas(ArrayList<Competitor> objs) {
        for (Competitor obj : objs) {
            addData(obj);
        }

    }

    @Override
    public Element getXMLElement() {
        Element e = super.getXMLElement();
        e.setName(StringConstants.CS_MANUAL_RANKING);

        return e;
    }

    @Override
    public void setXMLElement(Element e) {
        super.setXMLElement(e);
    }

    @Override
    public JSONObject getJSON() {

        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }

    @Override
    public void updateFromJSON(JSONObject object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
