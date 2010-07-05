/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tourma.data;

import tourma.data.Match;
import java.util.Date;
import java.util.Vector;

/**
 *
 * @author Frederic Berger
 */
public class Round {
    Vector<Match> _matchs;
    Date _heure;

    public Round()
    {
        
        _matchs=new Vector<Match>();
    }

    public Vector<Match> getMatchs()
    {
        return _matchs;
    }

    public Date getHeure()
    {
        return _heure;
    }

    public void setHeure(Date heure)
    {
        _heure=heure;
    }
}
