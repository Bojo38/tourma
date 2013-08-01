/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import javax.swing.JOptionPane;
import org.jdom.DataConversionException;
import org.jdom.Element;
import tourma.MainFrame;

/**
 *
 * @author Administrateur
 */
public class Criteria implements XMLExport {

    /**
     * Name of the criteria
     */
    public String _name;
    /**
     * Points for
     */
    public int _pointsFor;
    /**
     * Points against
     */
    public int _pointsAgainst;
    /**
     * Team Points for
     */
    public int _pointsTeamFor;
    /**
     * Team Points against
     */
    public int _pointsTeamAgainst;

    public Criteria(String name) {
        _name = name;
        _pointsFor = 0;
        _pointsAgainst = 0;
        _pointsTeamFor = 0;
        _pointsTeamAgainst = 0;
    }

    public Element getXMLElement() {
        Element crit = new Element("Criteria");
        crit.setAttribute("Name", this._name);
        crit.setAttribute("PointsFor", Integer.toString(this._pointsFor));
        crit.setAttribute("PointsTeamFor", Integer.toString(this._pointsTeamFor));
        crit.setAttribute("PointsAgainst", Integer.toString(this._pointsAgainst));
        crit.setAttribute("PointsTeamAgainst", Integer.toString(this._pointsTeamAgainst));
        return crit;
    }

    public void setXMLElement(Element criteria) {
        try {
            this._name = criteria.getAttributeValue("Name");
            this._pointsFor = criteria.getAttribute("PointsFor").getIntValue();
            this._pointsTeamFor = criteria.getAttribute("PointsTeamFor").getIntValue();
            this._pointsAgainst = criteria.getAttribute("PointsAgainst").getIntValue();
            this._pointsTeamAgainst = criteria.getAttribute("PointsTeamAgainst").getIntValue();
        } catch (DataConversionException dce) {
            JOptionPane.showMessageDialog(MainFrame.getMainFrame(), dce.getLocalizedMessage());
        }
    }
}
