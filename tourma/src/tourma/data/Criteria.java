/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

/**
 *
 * @author Administrateur
 */
public class Criteria {

    /**
     * Name of the criteria
     */
    public String _name;
    /**
     * Points for
     */
    public int _pointsFor;
    /**
     * Points against
     */
    public int _pointsAgainst;
    /**
     * Team Points for
     */
    public int _pointsTeamFor;
    /**
     * Team Points against
     */
    public int _pointsTeamAgainst;


    public Criteria(String name) {
        _name = name;
        _pointsFor = 0;
        _pointsAgainst = 0;
        _pointsTeamFor = 0;
        _pointsTeamAgainst = 0;
    }
}
