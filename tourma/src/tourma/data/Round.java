/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.jdom.Element;
import tourma.utility.StringConstants;



/**
 *
 * @author Frederic Berger
 */
public class Round implements XMLExport {

    protected ArrayList<Match> mMatchs;
    protected Date mHour;
    
    public boolean mCup=false;
    public int mCupTour=0;
    public int mCupMaxTour=0;
    public boolean mLooserCup=false;
    
    public Round() {
        mMatchs = new ArrayList<Match>();
    }

    public ArrayList<Match> getMatchs() {
        return mMatchs;
    }

    public void setHour(final Date heure)
    {
        mHour=heure;
    }
    
    public Date getHour() {
        return mHour;
    }

    public Element getXMLElement() {
        final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss",Locale.getDefault());
        final Element round = new Element(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("Round"));
        round.setAttribute("Date", format.format(this.mHour));
        
        round.setAttribute("LooserCup", Boolean.toString(mLooserCup));
        round.setAttribute("Cup", Boolean.toString(mCup));
        round.setAttribute("Tour", Integer.toString(mCupTour));
        round.setAttribute("maxTour", Integer.toString(mCupMaxTour));
        

        for (int j = 0; j < this.mMatchs.size(); j++) {
            final Element match = mMatchs.get(j).getXMLElement();
            round.addContent(match);
        }   

        return round;
    }

    public void setXMLElement(final Element round) {
        final SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss",Locale.getDefault());

        final String date = round.getAttributeValue("Date");
        try {
            this.mHour = format.parse(date);

        } catch (ParseException e) {
        }
        
        try {
             mLooserCup=Boolean.parseBoolean(round.getAttributeValue("LooserCup"));
             mCup=Boolean.parseBoolean(round.getAttributeValue("Cup"));
              mCupTour=Integer.parseInt(round.getAttributeValue("Tour"));
             mCupMaxTour=Integer.parseInt(round.getAttributeValue("maxTour"));
        }
        catch(Exception e)
        {
            
        }

        final List matchs = round.getChildren("Match");
        final Iterator k = matchs.iterator();
        this.mMatchs.clear();

        while (k.hasNext()) {
            final Element match = (Element) k.next();
            final Match m = new Match();
            m.setXMLElement(match);
            this.mMatchs.add(m);
        }
    }
}
