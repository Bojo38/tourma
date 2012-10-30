/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tourma.data;

import java.util.Vector;

/**
 *
 * @author Administrateur
 */
public class Group {

    public String _name="";

    public Vector<RosterType> _rosters;

    public Group(String name)
    {
        _name=name;
        _rosters=new Vector<RosterType>();
    }

}
