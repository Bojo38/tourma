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
    public String mName;
    /**
     * Points for
     */
    public int mPointsFor;
    /**
     * Points against
     */
    public int mPointsAgainst;
    /**
     * Team Points for
     */
    public int mPointsTeamFor;
    /**
     * Team Points against
     */
    public int mPointsTeamAgainst;

    public Criteria(final String name) {
        mName = name;
        mPointsFor = 0;
        mPointsAgainst = 0;
        mPointsTeamFor = 0;
        mPointsTeamAgainst = 0;
    }

    public Element getXMLElement() {
        final Element crit = new Element("Criteria");
        crit.setAttribute("Name", this.mName);
        crit.setAttribute("PointsFor", Integer.toString(this.mPointsFor));
        crit.setAttribute("PointsTeamFor", Integer.toString(this.mPointsTeamFor));
        crit.setAttribute("PointsAgainst", Integer.toString(this.mPointsAgainst));
        crit.setAttribute("PointsTeamAgainst", Integer.toString(this.mPointsTeamAgainst));
        return crit;
    }

    public void setXMLElement(final Element criteria) {
        try {
            this.mName = criteria.getAttributeValue("Name");
            this.mPointsFor = criteria.getAttribute("PointsFor").getIntValue();
            this.mPointsTeamFor = criteria.getAttribute("PointsTeamFor").getIntValue();
            this.mPointsAgainst = criteria.getAttribute("PointsAgainst").getIntValue();
            this.mPointsTeamAgainst = criteria.getAttribute("PointsTeamAgainst").getIntValue();
        } catch (DataConversionException dce) {
            JOptionPane.showMessageDialog(MainFrame.getMainFrame(), dce.getLocalizedMessage());
        }
    }
}
