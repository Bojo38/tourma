/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;
import org.jdom.Element;
import tourma.utility.StringConstants;

/**
 *
 * @author WFMJ7631
 */
public class Substitute implements IXMLExport, Serializable {
    private static final Logger LOG = Logger.getLogger(Substitute.class.getName());

        protected static AtomicInteger sGenUID=new AtomicInteger(0);
    protected int UID=sGenUID.incrementAndGet();

    public int getUID() {
        return UID;
    }

    public void setUID(int UID) {
        this.UID = UID;
    }

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

        final Element coach = new Element(StringConstants.CS_SUBSTITUTION);
        coach.setAttribute(StringConstants.CS_TITULAR, getTitular().getName());
        coach.setAttribute(StringConstants.CS_SUBSTITUTE, getSubstitute().getName());
        return coach;
    }

    /**
     *
     * @param e
     */
    @Override
    public void setXMLElement(Element e) {
        if (e != null) {
            String Sub = e.getAttributeValue(StringConstants.CS_SUBSTITUTE);
            String Tit = e.getAttributeValue(StringConstants.CS_TITULAR);
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
    
    
     public boolean equals(Object c) {
        if (c instanceof Substitute) {
            Substitute s = (Substitute) c;
            boolean equality = true; //(this.mMatch.equals(s.getMatch()));
            equality &= this.mSubstitute.equals(s.getSubstitute());
            equality &= this.mTitular.equals(s.getTitular());

            return equality;
        }
        return false;
    }
}
