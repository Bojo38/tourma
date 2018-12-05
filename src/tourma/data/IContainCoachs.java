/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

/**
 *
 * @author WFMJ7631
 */
public interface IContainCoachs {
    
    /**
     * 
     * @param i
     * @return 
     */
     public Coach getCoach(int i) ;
    
     /**
     * @return the mCoachs
     */
    public int getCoachsCount() ;
    
    
    /**
     * 
     * @param c
     * @return 
     */
    public boolean containsCoach(Coach c) ;
    
    /**
     * 
     * @param c 
     */
    public void addCoach(Coach c) ;
    
    /**
     * 
     * @param i
     */
    public void removeCoach(int i) ;
    
    /**
     * Clear the Coach list
     */
   public void clearCoachs() ;
}
