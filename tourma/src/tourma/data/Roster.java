/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tourma.data;

import java.util.Vector;

/**
 *
 * @author Administrateur
 */
public class Roster {

    /*public static String[] p_Rosters={
        "Amazone", "Bas Fonds", "Chaos", "Elfe", "Elfe sylvain", "Elfe noir",
        "Gobelin", "Halfling", "Haut Elfe", "Homme lézard", "Humain", "Khemri",
        "Mort-Vivant", "Nain", "Nain du chaos", "Necromantique", "Nordique",
        "Nurgle", "Ogre", "Orque", "Pacte Chaotique", "Skaven", "Slann", "Vampire"
    };*/

    public static Vector<String> Rosters=new Vector<String>();

    public String _name="";

    public static void initCollection()
    {
        Rosters.add(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("AmazonKey"));
        Rosters.add(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("UnderworldKey"));
        Rosters.add(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("ChaosKey"));
        Rosters.add(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("ElfKey"));
        Rosters.add(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("WoodElfKey"));
        Rosters.add(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("DarkElfKey"));
        Rosters.add(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("GoblinKey"));
        Rosters.add(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("HalflingKey"));
        Rosters.add(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("HighElfKey"));
        Rosters.add(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("LizardmenKey"));
        Rosters.add(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("HumanKey"));
        Rosters.add(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("KhemriKey"));
        Rosters.add(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("UndeadKey"));
        Rosters.add(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("DwarfKey"));
        Rosters.add(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("ChaosDwarfKey"));
        Rosters.add(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("NecromanticKey"));
        Rosters.add(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("NorseKey"));
        Rosters.add(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("NurgleKey"));
        Rosters.add(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("OgreKey"));
        Rosters.add(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("OrcKey"));
        Rosters.add(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("ChaosPactKey"));
        Rosters.add(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("SkavenKey"));
        Rosters.add(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("SlannKey"));
        Rosters.add(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("VampireKey"));
    }

    public Roster(int i)
    {
        _name=Rosters.get(i);
    }

    public Roster(String name)
    {
        _name=name;
    }

}
