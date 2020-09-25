/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package teamma.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Logger;
import teamma.languages.Translate;

/**
 *
 * @author WFMJ7631
 */
public class RosterType implements Serializable {

    teamma.data.LRB.E_Version version=LRB.E_Version.BB2016;
    
    public LRB.E_Version getVersion()
    {
        return version;
    }
    
    public void setVersion(LRB.E_Version v)
    {
        version=v;
    }
    
    /**
     *
     */
    private static int extraRerollCost = 100000;
    /**
     *
     */
    private static int _babe_cost = 50000;
    /**
     *
     */
    private static int _wizard_cost = 150000;
    
    private static int _chaos_wizard_cost = 150000;
    /**
     *
     */
    private static int _local_apo_cost = 100000;
    /**
     *
     */
    private static int _igor_cost = 100000;
    

    /**
     *
     */
    private static int _assistant_cost = 10000;
    /**
     *
     */
    private static int _fan_factor_cost = 10000;
    /**
     *
     */
    private static int _cheerleader_cost = 10000;
    /**
     *
     */
    private static int _apothecary_cost = 50000;
    private static final Logger LOG = Logger.getLogger(RosterType.class.getName());

    private ArrayList<InducementType> _inducements=new ArrayList<>();
    
    /**
     * @return the extraRerollCost
     */
    public static int getExtraRerollCost() {
        return extraRerollCost;
    }

    /**
     * @param aExtra_reroll_cost the extraRerollCost to set
     */
    public static void setExtraRerollCost(int aExtra_reroll_cost) {
        extraRerollCost = aExtra_reroll_cost;
    }

    /**
     * @return the _babe_cost
     */
    public static int getBabe_cost() {
        return _babe_cost;
    }

    /**
     * @param aBabe_cost the _babe_cost to set
     */
    public static void setBabe_cost(int aBabe_cost) {
        _babe_cost = aBabe_cost;
    }

    /**
     * @return the _wizard_cost
     */
    public static int getWizard_cost() {
        return _wizard_cost;
    }
    
 
    /**
     * @param aWizard_cost the _wizard_cost to set
     */
    public static void setWizard_cost(int aWizard_cost) {
        _wizard_cost = aWizard_cost;
    }

    /**
     * @return the _local_apo_cost
     */
    public static int getLocal_apo_cost() {
        return _local_apo_cost;
    }

    /**
     * @param aLocal_apo_cost the _local_apo_cost to set
     */
    public static void setLocal_apo_cost(int aLocal_apo_cost) {
        _local_apo_cost = aLocal_apo_cost;
    }

    /**
     * @return the _igor_cost
     */
    public static int getIgor_cost() {
        return _igor_cost;
    }

    /**
     * @param aIgor_cost the _igor_cost to set
     */
    public static void setIgor_cost(int aIgor_cost) {
        _igor_cost = aIgor_cost;
    }

    /**
     * @return the _assistant_cost
     */
    public static int getAssistant_cost() {
        return _assistant_cost;
    }

    /**
     * @param aAssistant_cost the _assistant_cost to set
     */
    public static void setAssistant_cost(int aAssistant_cost) {
        _assistant_cost = aAssistant_cost;
    }

    /**
     * @return the _fan_factor_cost
     */
    public static int getFan_factor_cost() {
        return _fan_factor_cost;
    }

    /**
     * @param aFan_factor_cost the _fan_factor_cost to set
     */
    public static void setFan_factor_cost(int aFan_factor_cost) {
        _fan_factor_cost = aFan_factor_cost;
    }

    /**
     * @return the _cheerleader_cost
     */
    public static int getCheerleader_cost() {
        return _cheerleader_cost;
    }

    /**
     * @param aCheerleader_cost the _cheerleader_cost to set
     */
    public static void setCheerleader_cost(int aCheerleader_cost) {
        _cheerleader_cost = aCheerleader_cost;
    }

    /**
     * @return the _apothecary_cost
     */
    public static int getApothecary_cost() {
        return _apothecary_cost;
    }

    /**
     * @param aApothecary_cost the _apothecary_cost to set
     */
    public static void setApothecary_cost(int aApothecary_cost) {
        _apothecary_cost = aApothecary_cost;
    }
    /**
     *
     */
    private String _name;
    /**
     *
     */
    private int _reroll_cost;
    /**
     *
     */
    private boolean _apothecary;
    /**
     *
     */
    private int _bribe_cost;
    /**
     *
     */
    private int _chef_cost;
    /**
     *
     */
    private boolean _igor;

    public boolean isChaos_wizard() {
        return _chaos_wizard;
    }

    public void setChaos_wizard(boolean _chaos_wizard) {
        this._chaos_wizard = _chaos_wizard;
    }

    public static int getChaos_wizard_cost() {
        return _chaos_wizard_cost;
    }

    public static void setChaos_wizard_cost(int chaos_wizard_cost) {
        _chaos_wizard_cost = chaos_wizard_cost;
    }

    public static int getHoratio_X_Schottenheim_cost() {
        return _Horatio_X_Schottenheim_cost;
    }

    public static void setHoratio_X_Schottenheim_cost(int Horatio_X_Schottenheim_cost) {
        _Horatio_X_Schottenheim_cost = Horatio_X_Schottenheim_cost;
    }

    public boolean isKari_Coldstell() {
        return Kari_Coldstell;
    }

    public void setKari_Coldstell(boolean Kari_Coldstell) {
        this.Kari_Coldstell = Kari_Coldstell;
    }

    public static int getKari_Coldstell_cost() {
        return _Kari_Coldstell_cost;
    }

    public static void setKari_Coldstell_cost(int Kari_Coldstell_cost) {
        _Kari_Coldstell_cost = Kari_Coldstell_cost;
    }

    public boolean isFink_Da_Fixer() {
        return Fink_Da_Fixer;
    }

    public void setFink_Da_Fixer(boolean Fink_Da_Fixer) {
        this.Fink_Da_Fixer = Fink_Da_Fixer;
    }

    public static int getFink_Da_Fixer_cost() {
        return _Fink_Da_Fixer_cost;
    }

    public static void setFink_Da_Fixer_cost(int Fink_Da_Fixer_cost) {
        _Fink_Da_Fixer_cost = Fink_Da_Fixer_cost;
    }

    public boolean isPapa_Skullbones() {
        return Papa_Skullbones;
    }

    public void setPapa_Skullbones(boolean Papa_Skullbones) {
        this.Papa_Skullbones = Papa_Skullbones;
    }

    public static int getPapa_Skullbones_cost() {
        return _Papa_Skullbones_cost;
    }

    public static void setPapa_Skullbones_cost(int Papa_Skullbones_cost) {
        _Papa_Skullbones_cost = Papa_Skullbones_cost;
    }

    public boolean isGalandril_Silverwater() {
        return Galandril_Silverwater;
    }

    public void setGalandril_Silverwater(boolean Galandril_Silverwater) {
        this.Galandril_Silverwater = Galandril_Silverwater;
    }

    public static int getGalandril_Silverwater_cost() {
        return _Galandril_Silverwater_cost;
    }

    public static void setGalandril_Silverwater_cost(int Galandril_Silverwater_cost) {
        _Galandril_Silverwater_cost = Galandril_Silverwater_cost;
    }

    public boolean isKrot_Shockwhisker() {
        return Krot_Shockwhisker;
    }

    public void setKrot_Shockwhisker(boolean Krot_Shockwhisker) {
        this.Krot_Shockwhisker = Krot_Shockwhisker;
    }

    public static int getKrot_Shockwhisker_cost() {
        return _Krot_Shockwhisker_cost;
    }

    /**
     * Chaos Wizard Allowed
     */
    public static void setKrot_Shockwhisker_cost(int Krot_Shockwhisker_cost) {
        _Krot_Shockwhisker_cost = Krot_Shockwhisker_cost;
    }

    private boolean _chaos_wizard;
    /**
     * Chaos Wizard cost
     */    
    
    private static int _Horatio_X_Schottenheim_cost=80000;
    private boolean Kari_Coldstell;
    private static int _Kari_Coldstell_cost=50000;
    
    private boolean Fink_Da_Fixer;
    private static int _Fink_Da_Fixer_cost=50000;
    
    private boolean Papa_Skullbones;
    private static int _Papa_Skullbones_cost=80000;
    
     private boolean Galandril_Silverwater;
    private static int _Galandril_Silverwater_cost=50000;
    
    private boolean Krot_Shockwhisker;
    private static int _Krot_Shockwhisker_cost=80000;
    /**
     *
     */
    private String _image;
    /**
     *
     */
    private final ArrayList<PlayerType> _player_types;
    /**
     *
     */
    private final ArrayList<StarPlayer> _available_starplayers;

    /**
     *
     * @param name
     */
    public RosterType(String name) {
        _name = name;
        _player_types = new ArrayList<>();
        _available_starplayers = new ArrayList<>();
    }

    /**
     *
     * @param name
     * @return
     */
    public PlayerType getPlayerType(String name, boolean translate) {
        int i;
        for (i = 0; i < getPlayerTypeCount(); i++) {
            PlayerType rt = getPlayerType(i);

            if (translate) {
                
                if (name.equals(Translate.translate(rt.getPosition()))) {
                    return rt;
                }
            } else {
                if (name.equals(rt.getPosition())) {
                    return rt;
                }
            }

        }
        return null;
    }

    /**
     *
     * @param name
     * @return
     */
    public StarPlayer getStarPlayer(String name, boolean translate) {
        int i;
        for (i = 0; i < getAvailableStarplayerCount(); i++) {
            StarPlayer sp = getAvailableStarplayer(i);

            if (translate) {
                if (name.equals(Translate.translate(sp.getName()))) {
                    return sp;
                }
            } else {
                if (name.equals(sp.getName())) {
                    return sp;
                }
            }
        }
        return null;
    }

    /**
     * @return the _name
     */
    public String getName() {
        return _name;
    }

    /**
     * @param _name the _name to set
     */
    public void setName(String _name) {
        this._name = _name;
    }

    /**
     * @return the _reroll_cost
     */
    public int getReroll_cost() {
        return _reroll_cost;
    }

    /**
     * @param _reroll_cost the _reroll_cost to set
     */
    public void setReroll_cost(int _reroll_cost) {
        this._reroll_cost = _reroll_cost;
    }

    /**
     * @return the _apothecary
     */
    public boolean isApothecary() {
        return _apothecary;
    }

    /**
     * @param _apothecary the _apothecary to set
     */
    public void setApothecary(boolean _apothecary) {
        this._apothecary = _apothecary;
    }

    /**
     * @return the _bribe_cost
     */
    public int getBribe_cost() {
        return _bribe_cost;
    }

    /**
     * @param _bribe_cost the _bribe_cost to set
     */
    public void setBribe_cost(int _bribe_cost) {
        this._bribe_cost = _bribe_cost;
    }

    /**
     * @return the _chef_cost
     */
    public int getChef_cost() {
        return _chef_cost;
    }

    /**
     * @param _chef_cost the _chef_cost to set
     */
    public void setChef_cost(int _chef_cost) {
        this._chef_cost = _chef_cost;
    }

    /**
     * @return the _igor
     */
    public boolean isIgor() {
        return _igor;
    }

    /**
     * @param _igor the _igor to set
     */
    public void setIgor(boolean _igor) {
        this._igor = _igor;
    }

    /**
     * @return the _image
     */
    public String getImage() {
        return _image;
    }

    /**
     * @param _image the _image to set
     */
    public void setImage(String _image) {
        this._image = _image;
    }

    /**
     * @param p
     */
    public void addPlayerType(PlayerType p) {
        _player_types.add(p);
    }

    /**
     * Clear the player type list
     */
    public void clearPlayerType() {
        _player_types.clear();
    }
    
     public void clearInducementType() {
        _inducements.clear();
    }
    
    public void clearAvailableStarPlayerType() {
        _available_starplayers.clear();
    }

    public void addInducementType(InducementType it)
    {
        _inducements.add(it);
    }
    
    public int getInducementTypeSize()
    {
        return _inducements.size();
    }
    
    public InducementType getInducementType(int i)
    {
        return _inducements.get(i);
    }
    
    public InducementType getInducementType(String s)
    {
        for (InducementType it:_inducements)
        {
            if (it.getName().equals(s))
            {
                return it;
            }
        }
        return null;
    }
    /**
     * @return the _player_types
     */
    public int getPlayerTypeCount() {
        return _player_types.size();
    }

    /**
     * @param i
     * @return the _player_types
     */
    public PlayerType getPlayerType(int i) {
        return _player_types.get(i);
    }

    /**
     * @param i
     * @return the _available_starplayers
     */
    public StarPlayer getAvailableStarplayer(int i) {
        return _available_starplayers.get(i);
    }

    /**
     * @return the _available_starplayers
     */
    public int getAvailableStarplayerCount() {
        return _available_starplayers.size();
    }

    /**
     *
     * @param s
     */
    public void addAvailableStarPlayer(StarPlayer s) {
        _available_starplayers.add(s);
    }

}
