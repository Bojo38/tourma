/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import java.util.ArrayList;
import tourma.utility.StringConstants;

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
    public static ArrayList<String> mRostersNames = new ArrayList<String>();
    public static ArrayList<RosterType> mRosterTypes = new ArrayList<RosterType>();
    public String mName = java.util.ResourceBundle.getBundle("tourma/languages/language").getString("");

    public static void initCollection() {
        mRostersNames.clear();
        mRostersNames.add(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("AmazonKey"));
        mRostersNames.add(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("UnderworldKey"));
        mRostersNames.add(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("ChaosKey"));
        mRostersNames.add(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("ElfKey"));
        mRostersNames.add(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("WoodElfKey"));
        mRostersNames.add(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("DarkElfKey"));
        mRostersNames.add(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("GoblinKey"));
        mRostersNames.add(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("HalflingKey"));
        mRostersNames.add(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("HighElfKey"));
        mRostersNames.add(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("LizardmenKey"));
        mRostersNames.add(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("HumanKey"));
        mRostersNames.add(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("KhemriKey"));
        mRostersNames.add(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("KhorneKey"));
        mRostersNames.add(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("UndeadKey"));
        mRostersNames.add(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("DwarfKey"));
        mRostersNames.add(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("ChaosDwarfKey"));
        mRostersNames.add(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("NecromanticKey"));
        mRostersNames.add(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("NorseKey"));
        mRostersNames.add(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("NurgleKey"));
        mRostersNames.add(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("OgreKey"));
        mRostersNames.add(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("OrcKey"));
        mRostersNames.add(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("ChaosPactKey"));
        mRostersNames.add(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("SkavenKey"));
        mRostersNames.add(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("SlannKey"));
        mRostersNames.add(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("VampireKey"));
    }

    public static void initCollection(final int game) {
        
        mRostersNames.clear();
        
        switch (game) {
            case C_BLOOD_BOWL:
                initCollection();
                break;
            case C_DREAD_BALL:
                mRostersNames.clear();
                mRostersNames.add(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("CORPORATION"));
                mRostersNames.add(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("ORX"));
                mRostersNames.add(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("VER-MYNS"));
                mRostersNames.add(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("FORGE FATHERS"));
                mRostersNames.add(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("JUDWANS"));
                mRostersNames.add(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("Z'ZORS"));
                mRostersNames.add(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("ROBOTS"));
                mRostersNames.add(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("FEMALES CORPORATION"));
                mRostersNames.add(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("ZEES"));
                mRostersNames.add(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("ASTERIANS"));
                mRostersNames.add(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("NAMELESS"));
                mRostersNames.add(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("TARATONS"));
                break;
            default:
                initCollection();
                break;
        }
    }

    public RosterType(final int i) {
        mName = mRostersNames.get(i);
    }

    public RosterType(final String name) {
        mName = name;
    }
}
