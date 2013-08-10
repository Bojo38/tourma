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
    public Criteria mCriteria;
    /**
     * Value for the coach 1
     */
    public int mValue1;
    /**
     * Value for the coach 2
     */
    public int mValue2;

    public Value(final Criteria criteria)
    {
        mCriteria=criteria;
    }

}
