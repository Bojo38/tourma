/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import org.jdom2.Element;
import tourma.utility.StringConstants;

/**
 *
 * @author Administrateur
 */
public class RosterType implements XMLExport {

    private static ResourceBundle sBundle = null;

    /**
     *
     */
    public static final int C_BLOOD_BOWL = 1;

    /**
     *
     */
    public static final int C_DREAD_BALL = 2;
    /*public static String[] p_Rosters={
     "Amazone", "Bas-Fonds", "Chaos", "Elfe", "Elfe Sylvain", "Elfe Noir",
     "Gobelin", "Halfling", "Haut Elfe", "Homme lézard", "Humain", "Khemri",
     "Mort-Vivant", "Nain", "Nain du chaos", "Necromantique", "Nordique",
     "Nurgle", "Ogre", "Orque", "Pacte Chaotique", "Skaven", "Slann", "Vampire"
     };*/

    /**
     *
     */
    private static ArrayList<String> mRostersNames = new ArrayList<>();

    /**
     *
     */
    private static HashMap<String, RosterType> mRosterTypes = new HashMap<>();

    private static final Logger LOG = Logger.getLogger(RosterType.class.getName());

    public static String translate(String key) {
        if (sBundle == null) {
            sBundle = java.util.ResourceBundle.getBundle("tourma/languages/rosters");
        }

        String name = "";
        try {
            name = sBundle.getString(key);
        } catch (Exception e) {

        } finally {

        }

        return name;
    }

    /**
     * Initializes collection
     */
    public static void initCollection() {
        mRostersNames.clear();
        mRostersNames.add(translate("AmazonKey"));
        mRostersNames.add(translate("UnderworldKey"));
        mRostersNames.add(translate("ChaosKey"));
        mRostersNames.add(translate("ElfKey"));
        mRostersNames.add(translate("WoodElfKey"));
        mRostersNames.add(translate("DarkElfKey"));
        mRostersNames.add(translate("GoblinKey"));
        mRostersNames.add(translate("HalflingKey"));
        mRostersNames.add(translate("HighElfKey"));
        mRostersNames.add(translate("LizardmenKey"));
        mRostersNames.add(translate("HumanKey"));
        mRostersNames.add(translate("KhemriKey"));
        mRostersNames.add(translate("KhorneKey"));
        mRostersNames.add(translate("UndeadKey"));
        mRostersNames.add(translate("DwarfKey"));
        mRostersNames.add(translate("ChaosDwarfKey"));
        mRostersNames.add(translate("NecromanticKey"));
        mRostersNames.add(translate("NorseKey"));
        mRostersNames.add(translate("NurgleKey"));
        mRostersNames.add(translate("OgreKey"));
        mRostersNames.add(translate("OrcKey"));
        mRostersNames.add(translate("ChaosPactKey"));
        mRostersNames.add(translate("SkavenKey"));
        mRostersNames.add(translate("SlannKey"));
        mRostersNames.add(translate("VampireKey"));

        mRosterTypes.clear();
        mRosterTypes.put(translate("AmazonKey"), new RosterType(translate("AmazonKey")));
        mRosterTypes.put(translate("UnderworldKey"), new RosterType(translate("UnderworldKey")));
        mRosterTypes.put(translate("ChaosKey"), new RosterType(translate("ChaosKey")));
        mRosterTypes.put(translate("ElfKey"), new RosterType(translate("ElfKey")));
        mRosterTypes.put(translate("WoodElfKey"), new RosterType(translate("WoodElfKey")));
        mRosterTypes.put(translate("DarkElfKey"), new RosterType(translate("DarkElfKey")));
        mRosterTypes.put(translate("GoblinKey"), new RosterType(translate("GoblinKey")));
        mRosterTypes.put(translate("HalflingKey"), new RosterType(translate("HalflingKey")));
        mRosterTypes.put(translate("HighElfKey"), new RosterType(translate("HighElfKey")));
        mRosterTypes.put(translate("LizardmenKey"), new RosterType(translate("LizardmenKey")));
        mRosterTypes.put(translate("HumanKey"), new RosterType(translate("HumanKey")));
        mRosterTypes.put(translate("KhemriKey"), new RosterType(translate("KhemriKey")));
        mRosterTypes.put(translate("KhorneKey"), new RosterType(translate("KhorneKey")));
        mRosterTypes.put(translate("UndeadKey"), new RosterType(translate("UndeadKey")));
        mRosterTypes.put(translate("DwarfKey"), new RosterType(translate("DwarfKey")));
        mRosterTypes.put(translate("ChaosDwarfKey"), new RosterType(translate("ChaosDwarfKey")));
        mRosterTypes.put(translate("NecromanticKey"), new RosterType(translate("NecromanticKey")));
        mRosterTypes.put(translate("NorseKey"), new RosterType(translate("NorseKey")));
        mRosterTypes.put(translate("NurgleKey"), new RosterType(translate("NurgleKey")));
        mRosterTypes.put(translate("OgreKey"), new RosterType(translate("OgreKey")));
        mRosterTypes.put(translate("OrcKey"), new RosterType(translate("OrcKey")));
        mRosterTypes.put(translate("ChaosPactKey"), new RosterType(translate("ChaosPactKey")));
        mRosterTypes.put(translate("SkavenKey"), new RosterType(translate("SkavenKey")));
        mRosterTypes.put(translate("SlannKey"), new RosterType(translate("SlannKey")));
        mRosterTypes.put(translate("VampireKey"), new RosterType(translate("VampireKey")));
    }

    /**
     *
     * @param game
     */
    public static void initCollection(final int game) {

        mRostersNames.clear();

        switch (game) {
            case C_BLOOD_BOWL:
                initCollection();
                break;
            case C_DREAD_BALL:
                mRostersNames.clear();
                mRostersNames.add(translate("CORPORATION"));
                mRostersNames.add(translate("ORX"));
                mRostersNames.add(translate("VER-MYNS"));
                mRostersNames.add(translate("FORGE FATHERS"));
                mRostersNames.add(translate("JUDWANS"));
                mRostersNames.add(translate("Z'ZORS"));
                mRostersNames.add(translate("ROBOTS"));
                mRostersNames.add(translate("FEMALES CORPORATION"));
                mRostersNames.add(translate("ZEES"));
                mRostersNames.add(translate("ASTERIANS"));
                mRostersNames.add(translate("NAMELESS"));
                mRostersNames.add(translate("TARATONS"));
                break;
            default:
                initCollection();
                break;
        }
    }

    /**
     *
     * @param name
     * @return
     */
    public static String getRosterName(String name) {
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
                result = translate("AmazonKey");
                break;
            case "Bas-Fonds":
                result = translate("UnderworldKey");
                break;
            case "Chaos":
                result = translate("ChaosKey");
                break;
            case "Elfe":
                result = translate("ElfKey");
                break;
            case "Elfe sylvain":
                result = translate("WoodElfKey");
                break;
            case "Elfe noir":
                result = translate("DarkElfKey");
                break;
            case "Gobelin":
                result = translate("GoblinKey");
                break;
            case "Halfling":
                result = translate("HalflingKey");
                break;
            case "Haut Elfe":
                result = translate("HighElfKey");
                break;
            case "Homme lézard":
                result = translate("LizardmenKey");
                break;
            case "Humain":
                result = translate("HumanKey");
                break;
            case "Khemri":
                result = translate("KhemriKey");
                break;
            case "Mort-Vivant":
                result = translate("UndeadKey");
                break;
            case "Nain":
                result = translate("DwarfKey");
                break;
            case "Nain du chaos":
                result = translate("ChaosDwarfKey");
                break;
            case "Necromantique":
                result = translate("NecromanticKey");
                break;
            case "Nordique":
                result = translate("NorseKey");
                break;
            case "Nurgle":
                result = translate("NurgleKey");
                break;
            case "Ogre":
                result = translate("OgreKey");
                break;
            case "Orque":
                result = translate("OrcKey");
                break;
            case "Pacte Chaotique":
                result = translate("ChaosPactKey");
                break;
            case "Skaven":
                result = translate("SkavenKey");
                break;
            case "Slann":
                result = translate("SlannKey");
                break;
            case "Vampire":
                result = translate("VampireKey");
                break;
        }
        return result;
    }

    /**
     * @param r
     * @return the mRosterTypes
     */
    /*public static HashMap<String,RosterType> getRosterTypes() {
     return mRosterTypes;
     }*/
    /**
     *
     * @param r
     * @return
     */
    public static RosterType getRosterType(String r) {
        return mRosterTypes.get(r);
    }

    /**
     *
     * @param i
     * @return
     */
    public static RosterType getRosterType(int i) {
        if (i < mRosterTypes.size()) {
            String name=mRostersNames.get(i);
            return (RosterType) mRosterTypes.get(name);
        }
        return null;
    }

    /**
     *
     * @param n
     * @param r
     */
    public static void putRosterType(String n, RosterType r) {
        mRosterTypes.put(n, r);
    }

    /**
     * @param n
     */
    public static void addRosterName(String n) {
        mRostersNames.add(n);
    }

    /**
     * @return the mRostersNames
     */
    public static int getRostersNamesCount() {
        return mRostersNames.size();
    }

    /**
     *
     * @param i
     * @return
     */
    public static String getRostersName(int i) {
        return mRostersNames.get(i);
    }

    /*public static ArrayList<String> getRostersNames() {
     return mRostersNames;
     }*/
    /**
     * @param amRostersNames the mRostersNames to set
     */
    /*public static void setRostersNames(ArrayList<String> amRostersNames) {
     mRostersNames = amRostersNames;
     }*/
    /**
     * Reate new roster names array
     */
    public static void newRostersNames() {
        mRostersNames = new ArrayList<>();
    }

    /**
     * Create new roster type hashmap
     */
    public static void newRostersTypes() {
        mRosterTypes = new HashMap<>();
    }

    /**
     *
     * @return
     */
    public static DefaultComboBoxModel<String> getRostersNamesModel() {
        return new DefaultComboBoxModel<>(getRostersNames());
    }

    /**
     *
     * @return
     */
    public static String[] getRostersNames() {
        String[] s = new String[mRostersNames.size()];
        for (int i = 0; i < mRostersNames.size(); i++) {
            s[i] = mRostersNames.get(i);
        }
        return s;
    }

    /**
     *
     * @param source
     * @return
     */
    public static String getRosterTranslation(final String source) {
        String result = translate("UNKNOWN");
        if (source.equals(translate("AmazonKey"))) {
            result = translate("AMAZONS");
        }
        if (translate("UnderworldKey").equals(source)) {
            result = translate("UNDERWORLD");
        }
        if (translate("ChaosKey").equals(source)) {
            result = translate("CHAOS");
        }
        if (source.equals(translate("ElfKey"))) {
            result = translate("ELVES");
        }
        if (source.equals(translate("WoodElfKey"))) {
            result = translate("WOOD ELVES");
        }
        if (source.equals(translate("DarkElfKey"))) {
            result = translate("DARK ELVES");
        }
        if (source.equals(translate("GoblinKey"))) {
            result = translate("GOBLINS");
        }
        if (source.equals(translate("HalflingKey"))) {
            result = translate("HALFLINGS");
        }
        if (source.equals(translate("HighElfKey"))) {
            result = translate("HIGH ELVES");
        }
        if (source.equals(translate("LizardmenKey"))) {
            result = translate("LIZARDMEN");
        }
        if (source.equals(translate("HumanKey"))) {
            result = translate("HUMANS");
        }
        if (source.equals(translate("KhemriKey"))) {
            result = translate("KHEMRI");
        }
        if (source.equals(translate("UndeadKey"))) {
            result = translate("UNDEAD");
        }
        if (source.equals(translate("DwarfKey"))) {
            result = translate("DWARVES");
        }
        if (source.equals(translate("ChaosDwarfKey"))) {
            result = translate("CHAOS DWARVES");
        }
        if (source.equals(translate("NecromanticKey"))) {
            result = translate("NECROMANTIC");
        }
        if (source.equals(translate("NorseKey"))) {
            result = translate("NORSE");
        }
        if (source.equals(translate("NurgleKey"))) {
            result = translate("NURGLE'S ROTTERS");
        }
        if (source.equals(translate("OgreKey"))) {
            result = translate("OGRES");
        }
        if (source.equals(translate("OrcKey"))) {
            result = translate("ORC");
        }
        if (source.equals(translate("ChaosPactKey"))) {
            result = translate("CHAOS PACT");
        }
        if (source.equals(translate("SkavenKey"))) {
            result = translate("SKAVEN");
        }
        if (source.equals(translate("SlannKey"))) {
            result = translate("SLANN");
        }
        if (source.equals(translate("VampireKey"))) {
            result = translate("VAMPIRES");
        }
        return result;
    }

    /**
     *
     */
    private String mName = StringConstants.CS_NULL;

    /**
     *
     * @param i
     */
    public RosterType(final int i) {
        mName = mRostersNames.get(i);
    }

    /**
     *
     * @param name
     */
    public RosterType(final String name) {
        mName = name;
    }

    /**
     *
     * @return
     */
    @Override
    public Element getXMLElement() {
        final Element elt = new Element(StringConstants.CS_ROSTER);
        elt.setAttribute(StringConstants.CS_NAME, this.getName());
        return elt;
    }

    /**
     *
     * @param e
     */
    @Override
    public void setXMLElement(Element e) {
        this.setName(e.getAttributeValue(StringConstants.CS_NAME));
    }

    /**
     * @return the mName
     */
    public String getName() {
        return mName;
    }

    /**
     * @param mName the mName to set
     */
    public void setName(String mName) {
        this.mName = mName;
    }

}
