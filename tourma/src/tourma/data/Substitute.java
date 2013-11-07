/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import org.jdom2.Element;
import tourma.utility.StringConstants;

/**
 *
 * @author WFMJ7631
 */
public class Substitute implements XMLExport {

    public CoachMatch mMatch;
    public Coach mSubstitute;
    public Coach mTitular;

    @Override
    public Element getXMLElement() {

        final Element coach = new Element("Subtitution");
        coach.setAttribute("Titular", mTitular.mName);
        coach.setAttribute("Subtitute", mSubstitute.mName);
        return coach;
    }

    @Override
    public void setXMLElement(Element e) {
        if (e != null) {
            String Sub = e.getAttributeValue("Subtitute");
            String Tit = e.getAttributeValue("Titular");
            mTitular=Coach.sCoachMap.get(Tit);
            mSubstitute=Coach.sCoachMap.get(Sub);            
        }
    }
}
