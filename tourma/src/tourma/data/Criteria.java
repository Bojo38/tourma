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
        final Element crit = new Element(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("CRITERIA"));
        crit.setAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("NAME"), this.mName);
        crit.setAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("POINTSFOR"), Integer.toString(this.mPointsFor));
        crit.setAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("POINTSTEAMFOR"), Integer.toString(this.mPointsTeamFor));
        crit.setAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("POINTSAGAINST"), Integer.toString(this.mPointsAgainst));
        crit.setAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("POINTSTEAMAGAINST"), Integer.toString(this.mPointsTeamAgainst));
        return crit;
    }

    public void setXMLElement(final Element criteria) {
        try {
            this.mName = criteria.getAttributeValue(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("NAME"));
            this.mPointsFor = criteria.getAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("POINTSFOR")).getIntValue();
            this.mPointsTeamFor = criteria.getAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("POINTSTEAMFOR")).getIntValue();
            this.mPointsAgainst = criteria.getAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("POINTSAGAINST")).getIntValue();
            this.mPointsTeamAgainst = criteria.getAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("POINTSTEAMAGAINST")).getIntValue();
        } catch (DataConversionException dce) {
            JOptionPane.showMessageDialog(MainFrame.getMainFrame(), dce.getLocalizedMessage());
        }
    }
}
