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
    	int hashFirst = first != null ? first.hashCode() : 0;
    	int hashSecond = second != null ? second.hashCode() : 0;

    	return (hashFirst + hashSecond) * hashSecond + hashFirst;
    }

    @Override
    public boolean equals(Object other) {
    	if (other instanceof Pair) {
    		Pair otherPair = (Pair) other;
    		return 
    		((  this.first == otherPair.first ||
    			( this.first != null && otherPair.first != null &&
    			  this.first.equals(otherPair.first))) &&
    		 (	this.second == otherPair.second ||
    			( this.second != null && otherPair.second != null &&
    			  this.second.equals(otherPair.second))) );
    	}

    	return false;
    }

    @Override
    public String toString()
    { 
           return "(" + first + ", " + second + ")"; 
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