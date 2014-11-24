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
public class RosterType implements XMLExport{

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
    private static HashMap<String,RosterType> mRosterTypes = new HashMap<>();

    private static final Logger LOG = Logger.getLogger(RosterType.class.getName());

    /**
     * Initializes collection
     */
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
        mRosterTypes.put(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("AmazonKey"),new RosterType(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("AmazonKey")));
        mRosterTypes.put(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("UnderworldKey"),new RosterType(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("UnderworldKey")));
        mRosterTypes.put(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("ChaosKey"),new RosterType(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("ChaosKey")));
        mRosterTypes.put(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("ElfKey"),new RosterType(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("ElfKey")));
        mRosterTypes.put(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("WoodElfKey"),new RosterType(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("WoodElfKey")));
        mRosterTypes.put(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("DarkElfKey"),new RosterType(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("DarkElfKey")));
        mRosterTypes.put(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("GoblinKey"),new RosterType(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("GoblinKey")));
        mRosterTypes.put(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("HalflingKey"),new RosterType(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("HalflingKey")));
        mRosterTypes.put(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("HighElfKey"),new RosterType(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("HighElfKey")));
        mRosterTypes.put(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("LizardmenKey"),new RosterType(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("LizardmenKey")));
        mRosterTypes.put(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("HumanKey"),new RosterType(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("HumanKey")));
        mRosterTypes.put(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("KhemriKey"),new RosterType(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("KhemriKey")));
        mRosterTypes.put(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("KhorneKey"),new RosterType(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("KhorneKey")));
        mRosterTypes.put(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("UndeadKey"),new RosterType(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("UndeadKey")));
        mRosterTypes.put(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("DwarfKey"),new RosterType(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("DwarfKey")));
        mRosterTypes.put(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("ChaosDwarfKey"),new RosterType(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("ChaosDwarfKey")));
        mRosterTypes.put(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("NecromanticKey"),new RosterType(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("NecromanticKey")));
        mRosterTypes.put(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("NorseKey"),new RosterType(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("NorseKey")));
        mRosterTypes.put(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("NurgleKey"),new RosterType(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("NurgleKey")));
        mRosterTypes.put(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("OgreKey"),new RosterType(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("OgreKey")));
        mRosterTypes.put(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("OrcKey"),new RosterType(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("OrcKey")));
        mRosterTypes.put(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("ChaosPactKey"),new RosterType(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("ChaosPactKey")));
        mRosterTypes.put(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("SkavenKey"),new RosterType(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("SkavenKey")));
        mRosterTypes.put(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("SlannKey"),new RosterType(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("SlannKey")));
        mRosterTypes.put(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("VampireKey"),new RosterType(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString("VampireKey")));
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

    /**
     *
     * @param name
     * @return
     */
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
        return (RosterType) mRosterTypes.values().toArray()[i];
    }
    
    /**
     * 
     * @param n
     * @param r
     */
    public static void putRosterType(String n,RosterType r)
    {
        mRosterTypes.put(n, r);
    }

 
    /**
     * @param n
     */
    public static void addRosterName(String n)
    {
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
         String[] s=new String[mRostersNames.size()];
        for(int i=0; i<mRostersNames.size(); i++ )
        {
               s[i]= mRostersNames.get(i);
        }
        return s;
    }

    
    /**
     *
     */
    private String mName = java.util.ResourceBundle.getBundle("tourma/languages/language").getString("");

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
    public RosterType(final String name)
    {
        mName = name;
    }

    /**
     *
     * @return
     */
    @Override
public Element getXMLElement() {
    final Element elt = new Element("Roster");
    elt.setAttribute("Name", this.getName());
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
