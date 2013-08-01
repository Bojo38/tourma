/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;
import org.jdom.DataConversionException;
import org.jdom.Element;
import tourma.MainFrame;
import tourma.data.Coach;

/**
 *
 * @author Frederic Berger
 */
public class Match implements XMLExport {

    public Coach _coach1;
    public Coach _coach2;
    public HashMap<Criteria, Value> _values;

    public Match() {
        _values = new HashMap<Criteria, Value>();

        int size = Tournament.getTournament()._params._criterias.size();
        for (int i = 0; i < size; i++) {
            Criteria crit = Tournament.getTournament()._params._criterias.get(i);
            Value val = new Value(crit);
            if (i == 0) {
                val._value1 = -1;
                val._value2 = -1;
            } else {
                val._value1 = 0;
                val._value2 = 0;
            }
            _values.put(crit, val);
        }
    }

    public Element getXMLElement() {
        Element match = new Element("Match");
        match.setAttribute("Coach1", this._coach1._name);
        match.setAttribute("Coach2", this._coach2._name);

        for (int k = 0; k < Tournament.getTournament().getParams()._criterias.size(); k++) {
            Value val = this._values.get(Tournament.getTournament().getParams()._criterias.get(k));
            Element value = new Element("Value");
            value.setAttribute("Name", val._criteria._name);
            value.setAttribute("Value1", Integer.toString(val._value1));
            value.setAttribute("Value2", Integer.toString(val._value2));
            match.addContent(value);
        }
        return match;
    }

    public void setXMLElement(Element match) {
        try {
            String c1 = match.getAttribute("Coach1").getValue();
            String c2 = match.getAttribute("Coach2").getValue();
            this._coach1 = Coach._map.get(c1);
            this._coach2 = Coach._map.get(c2);

            /*for (int cpt = 0; cpt
             < _coachs.size(); cpt++) {
             if (c1.equals(_coachs.get(cpt)._name)) {
             this._coach1 = _coachs.get(cpt);
             break;
             }
             }
             for (int cpt = 0; cpt
             < _coachs.size(); cpt++) {
             if (c2.equals(_coachs.get(cpt)._name)) {
             this._coach2 = _coachs.get(cpt);
             break;
             }
             }*/

            this._coach1._matchs.add(this);
            this._coach2._matchs.add(this);

            List values = match.getChildren("Value");
            Iterator v = values.iterator();

            while (v.hasNext()) {
                Element val = (Element) v.next();
                Criteria crit = null;

                for (int cpt = 0; cpt < Tournament.getTournament().getParams()._criterias.size(); cpt++) {
                    Criteria criteria = Tournament.getTournament().getParams()._criterias.get(cpt);
                    String tmp = val.getAttribute("Name").getValue();

                    if (criteria._name.equals(tmp)) {
                        crit = criteria;
                    }
                }
                Value value = new Value(crit);
                value._value1 = val.getAttribute("Value1").getIntValue();
                value._value2 = val.getAttribute("Value2").getIntValue();
                this._values.put(crit, value);

            }
        } catch (DataConversionException dce) {
            JOptionPane.showMessageDialog(MainFrame.getMainFrame(), dce.getLocalizedMessage());
        }
    }
}
