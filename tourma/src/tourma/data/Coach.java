/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tourma.data;

/**
 *
 * @author Frederic Berger
 */
public class Coach implements Comparable{
    public String _name;
    public String _team;
    public String _roster;
    public int _naf;
    public int _rank;

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
