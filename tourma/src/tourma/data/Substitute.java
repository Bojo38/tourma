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
    public CoachMatch mMatch;

    /**
     *
     */
    public Coach mSubstitute;

    /**
     *
     */
    public Coach mTitular;

    /**
     *
     * @return
     */
    @Override
    public Element getXMLElement() {

        final Element coach = new Element("Subtitution");
        coach.setAttribute("Titular", mTitular.mName);
        coach.setAttribute("Subtitute", mSubstitute.mName);
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
            mTitular=Coach.getCoach(Tit);
            mSubstitute=Coach.getCoach(Sub);            
        }
    }
}
