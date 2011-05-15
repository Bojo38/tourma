/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tourma.data;

/**
 *
 * @author Administrateur
 */
public class Value {
    /**
     * Name of the criteria
     */
    public Criteria _criteria;
    /**
     * Value for the coach 1
     */
    public int _value1;
    /**
     * Value for the coach 2
     */
    public int _value2;

    public Value(Criteria criteria)
    {
        _criteria=criteria;
    }

}
