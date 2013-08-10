/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import java.util.ArrayList;
import org.jdom.Attribute;
import org.jdom.Element;
import tourma.tableModel.mjtRanking;

/**
 *
 * @author WFMJ7631
 */
public class Ranking implements XMLExport {

    public mjtRanking mRank;
    public String mName;
    public String mType;
    public String mValueType;

    public Ranking(final String name, final String type, final String valueType,final  mjtRanking rank) {
        mRank = rank;
        mName = name;
        mType = type;
        mValueType = valueType;
    }

    public Element getXMLElement() {
        final Element rank = new Element("Ranking");
        rank.setAttribute(new Attribute("name", mName));
        rank.setAttribute(new Attribute("type", mType));
        rank.setAttribute(new Attribute("order", mValueType));
        final ArrayList<ObjectRanking> datas = mRank.getSortedDatas();
        for (int k = 0; k < datas.size(); k++) {
            final Element ic = datas.get(k).getXMLElement();
            ic.setAttribute(new Attribute("pos", Integer.toString(k + 1)));
            rank.addContent(ic);
        }
        return rank;
    }

    public void setXMLElement(final Element e) {
        //  Declared only for interface
    }
}
