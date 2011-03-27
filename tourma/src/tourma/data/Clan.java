/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tourma.data;

/**
 * This class contains data relative to a clan
 * @author Administrateur
 */
public class Clan implements Comparable {

    /**
     * Name of the clan
     */
    public String _name;

    /**
     * Constructor by name
     * @param name Name of the clan
     */
    public Clan(String name)
    {
        _name=name;
    }

     public int compareTo(Object obj)
    {
        if (obj instanceof Coach)
        {
            return _name.compareTo(((Clan)obj)._name);
        }
        else
            return -1;
    }

}
