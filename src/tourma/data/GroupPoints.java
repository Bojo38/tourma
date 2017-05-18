/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author WFMJ7631
 */
public class GroupPoints implements Serializable  {
    
        protected static AtomicInteger sGenUID=new AtomicInteger(0);
    protected int UID=sGenUID.incrementAndGet();

    public int getUID() {
        return UID;
    }

    public void setUID(int UID) {
        this.UID = UID;
    }

    private int victoryPoints=0;
    private int drawPoints=0;
    private int lossPoints=0;

    public void pull(GroupPoints gp)
    {
        this.UID=gp.UID;
        this.victoryPoints=gp.getVictoryPoints();
        this.drawPoints=gp.getDrawPoints();
        this.lossPoints=gp.getLossPoints();
    }
    
     /**
     * 
     * @param obj
     * @return 
     */
    @Override
    public boolean equals(final Object obj) {
        
        boolean result;
        result = false;
        if (obj instanceof GroupPoints) {
            GroupPoints gp=(GroupPoints) obj;
            result=lossPoints==gp.getLossPoints();
            result &=this.victoryPoints==gp.getVictoryPoints();
            result &=this.drawPoints==gp.getDrawPoints();
        } 
        return result;
    }
    
    /**
     * @return the victoryPoints
     */
    public int getVictoryPoints() {
        return victoryPoints;
    }

    /**
     * @param victoryPoints the victoryPoints to set
     */
    public void setVictoryPoints(int victoryPoints) {
        this.victoryPoints = victoryPoints;
    }

    /**
     * @return the drawPoints
     */
    public int getDrawPoints() {
        return drawPoints;
    }

    /**
     * @param drawPoints the drawPoints to set
     */
    public void setDrawPoints(int drawPoints) {
        this.drawPoints = drawPoints;
    }

    /**
     * @return the lossPoints
     */
    public int getLossPoints() {
        return lossPoints;
    }

    /**
     * @param lossPoints the lossPoints to set
     */
    public void setLossPoints(int lossPoints) {
        this.lossPoints = lossPoints;
    }
    
    
    
}
