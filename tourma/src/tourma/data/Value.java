/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tourma.data;

import java.util.logging.Logger;

/**
 *
 * @author Administrateur
 */
public class Value {
    private static final Logger LOG = Logger.getLogger(Value.class.getName());
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

    /**
     *
     * @param criteria
     */
    public Value(final Criteria criteria)
    {
        mCriteria=criteria;
    }

}
