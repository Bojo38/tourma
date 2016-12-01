/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.utility;

import java.util.logging.Logger;

/**
 *
 * @author WFMJ7631
 * @param <A>
 * @param <B>
 */
public class Pair<A, B> {
    private static final Logger LOG = Logger.getLogger(Pair.class.getName());
    private A first;
    private B second;

    /**
     *
     * @param first
     * @param second
     */
    public Pair(A first, B second) {
    	super();
    	this.first = first;
    	this.second = second;
    }

    @Override
    public int hashCode() {
    	int hashFirst = getFirst() != null ? getFirst().hashCode() : 0;
    	int hashSecond = getSecond() != null ? getSecond().hashCode() : 0;

    	return (hashFirst + hashSecond) * hashSecond + hashFirst;
    }

    @Override
    public boolean equals(Object other) {
    	if (other instanceof Pair) {
    		Pair<A,B> otherPair = (Pair<A,B>) other;
    		return 
    		((  this.getFirst() == otherPair.getFirst() ||
    			( this.getFirst() != null && otherPair.getFirst() != null &&
                        this.getFirst().equals(otherPair.getFirst()))) &&
    		 (	this.getSecond() == otherPair.getSecond() ||
                        ( this.getSecond() != null && otherPair.getSecond() != null &&
    			  this.getSecond().equals(otherPair.getSecond()))) );
    	}

    	return false;
    }

    @Override
    public String toString()
    { 
           return "(" + getFirst() + ", " + getSecond() + ")"; 
    }

    /**
     *
     * @return
     */
    public A getFirst() {
    	return first;
    }

    /**
     *
     * @param first
     */
    public void setFirst(A first) {
    	this.first = first;
    }

    /**
     *
     * @return
     */
    public B getSecond() {
    	return second;
    }

    /**
     *
     * @param second
     */
    public void setSecond(B second) {
    	this.second = second;
    }
}