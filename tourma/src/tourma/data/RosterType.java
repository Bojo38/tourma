/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import java.util.Vector;
import javax.swing.JOptionPane;
import tourma.MainFrame;

/**
 *
 * @author Administrateur
 */
public class RosterType {

    public static final int C_BLOOD_BOWL = 1;
    public static final int C_DREAD_BALL = 2;
    /*public static String[] p_Rosters={
     "Amazone", "Bas Fonds", "Chaos", "Elfe", "Elfe sylvain", "Elfe noir",
     "Gobelin", "Halfling", "Haut Elfe", "Homme lézard", "Humain", "Khemri",
     "Mort-Vivant", "Nain", "Nain du chaos", "Necromantique", "Nordique",
     "Nurgle", "Ogre", "Orque", "Pacte Chaotique", "Skaven", "Slann", "Vampire"
     };*/
    public static Vector<String> RostersNames = new Vector<String>();
    public static Vector<RosterType> RosterTypes = new Vector<RosterType>();
    public String _name = "";

    public static void initCollection() {
        RostersNames.clear();
        RostersNames.add(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("AmazonKey"));
        RostersNames.add(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("UnderworldKey"));
        RostersNames.add(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("ChaosKey"));
        RostersNames.add(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("ElfKey"));
        RostersNames.add(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("WoodElfKey"));
        RostersNames.add(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("DarkElfKey"));
        RostersNames.add(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("GoblinKey"));
        RostersNames.add(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("HalflingKey"));
        RostersNames.add(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("HighElfKey"));
        RostersNames.add(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("LizardmenKey"));
        RostersNames.add(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("HumanKey"));
        RostersNames.add(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("KhemriKey"));
        RostersNames.add(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("KhorneKey"));
        RostersNames.add(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("UndeadKey"));
        RostersNames.add(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("DwarfKey"));
        RostersNames.add(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("ChaosDwarfKey"));
        RostersNames.add(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("NecromanticKey"));
        RostersNames.add(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("NorseKey"));
        RostersNames.add(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("NurgleKey"));
        RostersNames.add(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("OgreKey"));
        RostersNames.add(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("OrcKey"));
        RostersNames.add(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("ChaosPactKey"));
        RostersNames.add(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("SkavenKey"));
        RostersNames.add(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("SlannKey"));
        RostersNames.add(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("VampireKey"));
    }

    public static void initCollection(int game) {
        
        RostersNames.clear();
        
        switch (game) {
            case C_BLOOD_BOWL:
                initCollection();
                break;
            case C_DREAD_BALL:
                RostersNames.clear();
                RostersNames.add("Corporation");
                RostersNames.add("Orx");
                RostersNames.add("Ver-myns");
                RostersNames.add("Forge Fathers");
                RostersNames.add("Judwans");
                RostersNames.add("Z'Zors");
                RostersNames.add("Robots");
                RostersNames.add("Females Corporation");
                RostersNames.add("Zees");
                RostersNames.add("Asterians");
                RostersNames.add("Nameless");
                RostersNames.add("Taratons");
                break;
            default:
                initCollection();
                break;
        }
    }

    public RosterType(int i) {
        _name = RostersNames.get(i);
    }

    public RosterType(String name) {
        _name = name;
    }
}
