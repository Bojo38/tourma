/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

/** IndivPairing:
0: Classement
1: Libre
2: Aléatoire
3: Naf
 */
public enum EIndivPairing {
    /**
     * Pairing by ranking
     */
    RANKING, 
    /**
     * Free pairing
     */
    FREE, 
    /**
     * Random pairing
     */
    RANDOM, 
    /**
     * Pairing by NAF ranking
     */
    NAF
    
}
