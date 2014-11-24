/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

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
        rank.setAttribute(new Attribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("NAME"), getName()));
        rank.setAttribute(new Attribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("TYPE"), getType()));
        rank.setAttribute(new Attribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("ORDER"), getValueType()));
        //final ArrayList<ObjectRanking> datas = getRank().getSortedDatas();
        for (int k = 0; k < getRank().getRowCount(); k++) {
            final Element ic = getRank().getSortedObject(k) .getXMLElement();
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
}
