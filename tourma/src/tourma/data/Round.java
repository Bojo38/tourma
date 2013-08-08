/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import tourma.data.Match;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import javax.swing.JOptionPane;
import org.jdom.Attribute;
import org.jdom.DataConversionException;
import org.jdom.Element;
import tourma.MainFrame;


/**
 *
 * @author Frederic Berger
 */
public class Round implements XMLExport {

    Vector<Match> _matchs;
    Date _heure;
    
    public boolean _cup=false;
    public int _cup_tour=0;
    public int _cup_max_tour=0;
    public boolean _looser_cup=false;
    
    public Round() {
        _matchs = new Vector<Match>();
    }

    public Vector<Match> getMatchs() {
        return _matchs;
    }

    public Date getHeure() {
        return _heure;
    }

    public void setHeure(Date heure) {
        _heure = heure;
    }

    public Element getXMLElement() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Element round = new Element(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("Round"));
        round.setAttribute("Date", format.format(this._heure));
        
        round.setAttribute("LooserCup", Boolean.toString(_looser_cup));
        round.setAttribute("Cup", Boolean.toString(_cup));
        round.setAttribute("Tour", Integer.toString(_cup_tour));
        round.setAttribute("maxTour", Integer.toString(_cup_max_tour));
        

        for (int j = 0; j < this._matchs.size(); j++) {
            Element match = _matchs.get(j).getXMLElement();
            round.addContent(match);
        }   

        return round;
    }

    public void setXMLElement(Element round) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        String date = round.getAttributeValue("Date");
        try {
            this._heure = format.parse(date);

        } catch (ParseException e) {
        }
        
        try {
             _looser_cup=Boolean.parseBoolean(round.getAttributeValue("LooserCup"));
             _cup=Boolean.parseBoolean(round.getAttributeValue("Cup"));
              _cup_tour=Integer.parseInt(round.getAttributeValue("Tour"));
             _cup_max_tour=Integer.parseInt(round.getAttributeValue("maxTour"));
        }
        catch(Exception e)
        {
            
        }

        List matchs = round.getChildren("Match");
        Iterator k = matchs.iterator();
        this._matchs.clear();

        while (k.hasNext()) {
            Element match = (Element) k.next();
            Match m = new Match();
            m.setXMLElement(match);
            this._matchs.add(m);
        }
    }
}
