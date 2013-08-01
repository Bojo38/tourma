/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import java.util.Vector;
import org.jdom.Attribute;
import org.jdom.Element;
import tourma.tableModel.mjtRanking;

/**
 *
 * @author WFMJ7631
 */
public class Ranking implements XMLExport {

    public mjtRanking _rank;
    public String _name;
    public String _type;
    public String _valueType;

    public Ranking(String name, String type, String valueType, mjtRanking rank) {
        _rank = rank;
        _name = name;
        _type = type;
        _valueType = valueType;
    }

    public Element getXMLElement() {
        Element rank = new Element("Ranking");
        rank.setAttribute(new Attribute("name", _name));
        rank.setAttribute(new Attribute("type", _type));
        rank.setAttribute(new Attribute("order", _valueType));
        Vector<ObjectRanking> datas = _rank.getSortedDatas();
        for (int k = 0; k < datas.size(); k++) {
            Element ic = datas.get(k).getXMLElement();
            ic.setAttribute(new Attribute("pos", Integer.toString(k + 1)));
            rank.addContent(ic);
        }
        return rank;
    }

    public void setXMLElement(Element e) {
    }
}
