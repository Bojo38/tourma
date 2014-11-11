/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import java.util.ArrayList;
import java.util.logging.Logger;
import org.jdom2.Attribute;
import org.jdom2.Element;
import tourma.tableModel.MjtRanking;

/**
 *
 * @author WFMJ7631
 */
public class Ranking implements XMLExport {
    private static final Logger LOG = Logger.getLogger(Ranking.class.getName());

    /**
     *
     */
    public MjtRanking mRank;

    /**
     *
     */
    public String mName;

    /**
     *
     */
    public String mType;

    /**
     *
     */
    public String mValueType;

    /**
     *
     * @param name
     * @param type
     * @param valueType
     * @param rank
     */
    public Ranking(final String name, final String type, final String valueType,final  MjtRanking rank) {
        mRank = rank;
        mName = name;
        mType = type;
        mValueType = valueType;
    }

    /**
     *
     * @return
     */
    @Override
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

    /**
     *
     * @param e
     */
    @Override
    public void setXMLElement(final Element e) {
        //  Declared only for interface
    }
}
