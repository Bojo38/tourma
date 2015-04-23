/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.jdom2.DataConversionException;
import org.jdom2.Element;
import tourma.MainFrame;

/**
 *
 * @author Administrateur
 */
public class Criteria implements XMLExport {
    private static final Logger LOG = Logger.getLogger(Criteria.class.getName());

    /**
     * Name of the criteria
     */
    private String mName;
    /**
     * Points for
     */
    private int mPointsFor;
    /**
     * Points against
     */
    private int mPointsAgainst;
    /**
     * Team Points for
     */
    private int mPointsTeamFor;
    /**
     * Team Points against
     */
    private int mPointsTeamAgainst;

    /**
     *
     * @param name
     */
    public Criteria(final String name) {
        mName = name;
        mPointsFor = 0;
        mPointsAgainst = 0;
        mPointsTeamFor = 0;
        mPointsTeamAgainst = 0;
    }

    /**
     *
     * @return
     */
    @Override
    public Element getXMLElement() {
        final Element crit = new Element(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("CRITERIA"));
        crit.setAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("NAME"), this.getName());
        crit.setAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("POINTSFOR"), Integer.toString(this.getPointsFor()));
        crit.setAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("POINTSTEAMFOR"), Integer.toString(this.getPointsTeamFor()));
        crit.setAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("POINTSAGAINST"), Integer.toString(this.getPointsAgainst()));
        crit.setAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("POINTSTEAMAGAINST"), Integer.toString(this.getPointsTeamAgainst()));
        return crit;
    }

    /**
     *
     * @param criteria
     */
    @Override
    public void setXMLElement(final Element criteria) {
        try {
            this.setName(criteria.getAttributeValue(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("NAME")));
            this.setPointsFor(criteria.getAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("POINTSFOR")).getIntValue());
            this.setPointsTeamFor(criteria.getAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("POINTSTEAMFOR")).getIntValue());
            this.setPointsAgainst(criteria.getAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("POINTSAGAINST")).getIntValue());
            this.setPointsTeamAgainst(criteria.getAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("POINTSTEAMAGAINST")).getIntValue());
        } catch (DataConversionException dce) {
            JOptionPane.showMessageDialog(MainFrame.getMainFrame(), dce.getLocalizedMessage());
        }
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
     * @return the mPointsFor
     */
    public int getPointsFor() {
        return mPointsFor;
    }

    /**
     * @param mPointsFor the mPointsFor to set
     */
    public void setPointsFor(int mPointsFor) {
        this.mPointsFor = mPointsFor;
    }

    /**
     * @return the mPointsAgainst
     */
    public int getPointsAgainst() {
        return mPointsAgainst;
    }

    /**
     * @param mPointsAgainst the mPointsAgainst to set
     */
    public void setPointsAgainst(int mPointsAgainst) {
        this.mPointsAgainst = mPointsAgainst;
    }

    /**
     * @return the mPointsTeamFor
     */
    public int getPointsTeamFor() {
        return mPointsTeamFor;
    }

    /**
     * @param mPointsTeamFor the mPointsTeamFor to set
     */
    public void setPointsTeamFor(int mPointsTeamFor) {
        this.mPointsTeamFor = mPointsTeamFor;
    }

    /**
     * @return the mPointsTeamAgainst
     */
    public int getPointsTeamAgainst() {
        return mPointsTeamAgainst;
    }

    /**
     * @param mPointsTeamAgainst the mPointsTeamAgainst to set
     */
    public void setPointsTeamAgainst(int mPointsTeamAgainst) {
        this.mPointsTeamAgainst = mPointsTeamAgainst;
    }
}