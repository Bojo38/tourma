/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tourma.data;

/**
 *
 * @author Administrateur
 */
public class Roster {

    public static String[] Rosters={
        "Amazone", "Bas Fonds", "Chaos", "Elfe", "Elfe sylvain", "Elfe noir",
        "Gobelin", "Halfling", "Haut Elfe", "Homme lézard", "Humain", "Khemri",
        "Mort-Vivant", "Nain", "Nain du chaos", "Necromantique", "Nordique",
        "Nurgle", "Ogre", "Orque", "Pacte Chaotique", "Skaven", "Slann", "Vampire"
    };

    public String _name="";

    public Roster(int i)
    {
        _name=Rosters[i];
    }

    public Roster(String name)
    {
        _name=name;
    }

}
