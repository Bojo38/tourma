/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import java.util.ArrayList;
import java.util.ResourceBundle;
import tourma.utility.StringConstants;

/**
 *
 * @author Administrateur
 */
public class RosterType {

    public static final int C_BLOOD_BOWL = 1;
    public static final int C_DREAD_BALL = 2;
    /*public static String[] p_Rosters={
     "Amazone", "Bas-Fonds", "Chaos", "Elfe", "Elfe Sylvain", "Elfe Noir",
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

        mRosterTypes.clear();
        mRosterTypes.add(new RosterType(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("AmazonKey")));
        mRosterTypes.add(new RosterType(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("UnderworldKey")));
        mRosterTypes.add(new RosterType(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("ChaosKey")));
        mRosterTypes.add(new RosterType(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("ElfKey")));
        mRosterTypes.add(new RosterType(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("WoodElfKey")));
        mRosterTypes.add(new RosterType(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("DarkElfKey")));
        mRosterTypes.add(new RosterType(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("GoblinKey")));
        mRosterTypes.add(new RosterType(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("HalflingKey")));
        mRosterTypes.add(new RosterType(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("HighElfKey")));
        mRosterTypes.add(new RosterType(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("LizardmenKey")));
        mRosterTypes.add(new RosterType(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("HumanKey")));
        mRosterTypes.add(new RosterType(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("KhemriKey")));
        mRosterTypes.add(new RosterType(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("KhorneKey")));
        mRosterTypes.add(new RosterType(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("UndeadKey")));
        mRosterTypes.add(new RosterType(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("DwarfKey")));
        mRosterTypes.add(new RosterType(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("ChaosDwarfKey")));
        mRosterTypes.add(new RosterType(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("NecromanticKey")));
        mRosterTypes.add(new RosterType(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("NorseKey")));
        mRosterTypes.add(new RosterType(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("NurgleKey")));
        mRosterTypes.add(new RosterType(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("OgreKey")));
        mRosterTypes.add(new RosterType(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("OrcKey")));
        mRosterTypes.add(new RosterType(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("ChaosPactKey")));
        mRosterTypes.add(new RosterType(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("SkavenKey")));
        mRosterTypes.add(new RosterType(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("SlannKey")));
        mRosterTypes.add(new RosterType(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("VampireKey")));
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

    public static String getRosterName(String name) {
        ResourceBundle bundle = java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE);
        String result = name;
        // Patching roster name for older versions
        // Amazone, 
        // Bas-Fonds, 
        // Chaos, 
        // Elfe, 
        // Elfe sylvain, 
        // Elfe noir, 
        // Gobelin, 
        // Halfling,
        // Haut Elfe, 
        // Homme lézard, 
        // Humain, 
        // Khemri, 
        // Mort-Vivant, 
        // Nain, 
        // Nain du chaos,
        // Necromantique, 
        // Nordique,
        // Nurgle,
        // Ogre, 
        // Orque, 
        // Pacte Chaotique, 
        // Skaven,
        // Slann, 
        // Vampire
        switch (name) {
            case "Amazone":
                result = bundle.getString("AmazonKey");
                break;
            case "Bas-Fonds":
                result = bundle.getString("UnderworldKey");
                break;
            case "Chaos":
                result = bundle.getString("ChaosKey");
                break;
            case "Elfe":
                result = bundle.getString("ElfKey");
                break;
            case "Elfe sylvain":
                result = bundle.getString("WoodElfKey");
                break;
            case "Elfe noir":
                result = bundle.getString("DarkElfKey");
                break;
            case "Gobelin":
                result = bundle.getString("GoblinKey");
                break;
            case "Halfling":
                result = bundle.getString("HalflingKey");
                break;
            case "Haut Elfe":
                result = bundle.getString("HighElfKey");
                break;
            case "Homme lézard":
                result = bundle.getString("LizardmenKey");
                break;
            case "Humain":
                result = bundle.getString("HumanKey");
                break;
            case "Khemri":
                result = bundle.getString("KhemriKey");
                break;
            case "Mort-Vivant":
                result = bundle.getString("UndeadKey");
                break;
            case "Nain":
                result = bundle.getString("DwarfKey");
                break;
            case "Nain du chaos":
                result = bundle.getString("ChaosDwarfKey");
                break;
            case "Necromantique":
                result = bundle.getString("NecromanticKey");
                break;
            case "Nordique":
                result = bundle.getString("NorseKey");
                break;
            case "Nurgle":
                result = bundle.getString("NurgleKey");
                break;
            case "Ogre":
                result = bundle.getString("OgreKey");
                break;
            case "Orque":
                result = bundle.getString("OrcKey");
                break;
            case "Pacte Chaotique":
                result = bundle.getString("ChaosPactKey");
                break;
            case "Skaven":
                result = bundle.getString("SkavenKey");
                break;
            case "Slann":
                result = bundle.getString("SlannKey");
                break;
            case "Vampire":
                result = bundle.getString("VampireKey");
                break;
        }
        return result;
    }
}
