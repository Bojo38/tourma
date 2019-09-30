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
public class CupRound {

    public int getNbMatchs() {
        return mNbMatchs;
    }

    public void setNbMatchs(int mNbMatchs) {
        this.mNbMatchs = mNbMatchs;
    }

    public ArrayList<Match> getMatchs() {
        return mMatchs;
    }

    public void setMatchs(ArrayList<Match> mMatchs) {
        this.mMatchs = mMatchs;
    }
    
    protected int mNbMatchs;
    protected ArrayList<Match> mMatchs=null;
    
    public CupRound(int nbMatches)
    {
        mNbMatchs=nbMatches;
        generateEmptyMatchs();
    }
    
    public void generateEmptyMatchs()
    {
        if (mMatchs!=null)
        {
            mMatchs.clear();
        }
        mMatchs=new ArrayList<>();
        
        boolean byTeam=(Tournament.getTournament().getParams().isTeamTournament())&&
                (Tournament.getTournament().getParams().getTeamPairing()==ETeamPairing.TEAM_PAIRING);
        
        for (int i=0; i<mNbMatchs; i++)
        {                        
            if (byTeam)
            {                
                TeamMatch tm=new TeamMatch(null);
                mMatchs.add(tm);
            }
           else
            {
                CoachMatch cm=new CoachMatch(null);
                mMatchs.add(cm);
            }
        }
    }
    
}
