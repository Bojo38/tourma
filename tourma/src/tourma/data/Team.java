/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tourma.data;

import java.util.Vector;

/**
 *
 * @author Frederic Berger
 */
public class Team implements Comparable{

    public Vector<Coach> _coachs;
    public String _name;

    public Team()
    {
        _coachs=new Vector();
    }

     public int compareTo(Object obj)
    {
        if (obj instanceof Coach)
        {
            return _name.compareTo(((Coach)obj)._name);
        }
        else
            return -1;
    }
}
