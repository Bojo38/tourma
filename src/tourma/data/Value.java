/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tourma.data;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author Administrateur
 */
public class Value implements Serializable {

        protected static AtomicInteger sGenUID=new AtomicInteger(0);
    protected int UID=sGenUID.incrementAndGet();

    public int getUID() {
        return UID;
    }

    public void setUID(int UID) {
        this.UID = UID;
    }

    public void pull(Value value)
    {
        this.UID=value.UID;
        this.mValue1=value.mValue1;
        this.mValue2=value.mValue2;
    }
    
    /**
     * Name of the criteria
     */
    private Criteria mCriteria;
    /**
     * Value for the coach 1
     */
    private int mValue1;
    /**
     * Value for the coach 2
     */
    private int mValue2;

    /**
     *
     * @param criteria
     */
    public Value(final Criteria criteria)
    {
        mCriteria=criteria;
    }

    /**
     * @return the mCriteria
     */
    public Criteria getCriteria() {
        return mCriteria;
    }

    /**
     * @param mCriteria the mCriteria to set
     */
    public void setCriteria(Criteria mCriteria) {
        this.mCriteria = mCriteria;
    }

    /**
     * @return the mValue1
     */
    public int getValue1() {
        return mValue1;
    }

    /**
     * @param mValue1 the mValue1 to set
     */
    public void setValue1(int mValue1) {
        this.mValue1 = mValue1;
    }

    /**
     * @return the mValue2
     */
    public int getValue2() {
        return mValue2;
    }

    /**
     * @param mValue2 the mValue2 to set
     */
    public void setValue2(int mValue2) {
        this.mValue2 = mValue2;
    }

}
