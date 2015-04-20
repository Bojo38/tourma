/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import java.util.logging.Logger;
import org.jdom2.Element;

/**
 *
 * @author WFMJ7631
 */
public class Substitute implements XMLExport {
    private static final Logger LOG = Logger.getLogger(Substitute.class.getName());

    /**
     *
     */
    private CoachMatch mMatch;

    /**
     *
     */
    private Coach mSubstitute;

    /**
     *
     */
    private Coach mTitular;

    /**
     *
     * @return
     */
    @Override
    public Element getXMLElement() {

        final Element coach = new Element("Subtitution");
        coach.setAttribute("Titular", getTitular().getName());
        coach.setAttribute("Subtitute", getSubstitute().getName());
        return coach;
    }

    /**
     *
     * @param e
     */
    @Override
    public void setXMLElement(Element e) {
        if (e != null) {
            String Sub = e.getAttributeValue("Subtitute");
            String Tit = e.getAttributeValue("Titular");
            setTitular(Coach.getCoach(Tit));
            setSubstitute(Coach.getCoach(Sub));            
        }
    }

    /**
     * @return the mMatch
     */
    public CoachMatch getMatch() {
        return mMatch;
    }

    /**
     * @param mMatch the mMatch to set
     */
    public void setMatch(CoachMatch mMatch) {
        this.mMatch = mMatch;
    }

    /**
     * @return the mSubstitute
     */
    public Coach getSubstitute() {
        return mSubstitute;
    }

    /**
     * @param mSubstitute the mSubstitute to set
     */
    public void setSubstitute(Coach mSubstitute) {
        this.mSubstitute = mSubstitute;
    }

    /**
     * @return the mTitular
     */
    public Coach getTitular() {
        return mTitular;
    }

    /**
     * @param mTitular the mTitular to set
     */
    public void setTitular(Coach mTitular) {
        this.mTitular = mTitular;
    }
}
