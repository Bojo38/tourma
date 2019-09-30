/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import java.util.ArrayList;

/**
 *
 * @author WFMJ7631
 */
public class CupTable {

    public ArrayList<CupRound> getCupRounds() {
        return mCupRounds;
    }

    public void setCupRounds(ArrayList<CupRound> mCupRounds) {
        this.mCupRounds = mCupRounds;
    }
    ArrayList<CupRound> mCupRounds;
        
    
    public CupTable(int cupRoundsCount, boolean third_place)
    {
        for (int i=0; i<cupRoundsCount; i++)
        {
            int nbMatchs=(int)Math.round(Math.pow(2, cupRoundsCount-i-1));
            
            if ((i==cupRoundsCount-1)&&(third_place))
            {
                mCupRounds.add(new CupRound(nbMatchs+1));
            }
            else
            {
                mCupRounds.add(new CupRound(nbMatchs));
            }            
        }
    }
}
