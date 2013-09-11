/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import java.util.ArrayList;
import org.jdom2.Attribute;
import org.jdom2.Element;
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
        final Element rank = new Element(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("RANKING"));
        rank.setAttribute(new Attribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("NAME"), mName));
        rank.setAttribute(new Attribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("TYPE"), mType));
        rank.setAttribute(new Attribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("ORDER"), mValueType));
        final ArrayList<ObjectRanking> datas = mRank.getSortedDatas();
        for (int k = 0; k < datas.size(); k++) {
            final Element ic = datas.get(k).getXMLElement();
            ic.setAttribute(new Attribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("POS"), Integer.toString(k + 1)));
            rank.addContent(ic);
        }
        return rank;
    }

    public void setXMLElement(final Element e) {
        //  Declared only for interface
    }
}
