/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import java.util.HashMap;
import org.jdom.Element;

/**
 * This class contains data relative to a clan
 *
 * @author Administrateur
 */
public class Clan implements Comparable, XMLExport {

    public static HashMap<String, Clan> _clanMap = new HashMap();
    /**
     * Name of the clan
     */
    public String _name;

    /**
     * Constructor by name
     *
     * @param name Name of the clan
     */
    public Clan(String name) {
        _name = name;
    }

    public int compareTo(Object obj) {
        if (obj instanceof Coach) {
            return _name.compareTo(((Clan) obj)._name);
        } else {
            return -1;
        }
    }

    public Element getXMLElement() {
        Element clan = new Element("Clan");
        clan.setAttribute("Name", _name);
        return clan;
    }

    public void setXMLElement(Element e) {
        this._name=e.getAttributeValue("Name");
        if (_clanMap!=null)
        {
            _clanMap.put(_name, this);
        }
            
        
    }
}
