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
   
    private  int _maxBigGuys = 1;
    /**
     *
     */
    private static int _assistant_cost = 10000;

    public  int getMaxBigGuys() {
        return _maxBigGuys;
    }

    public  void setMaxBigGuys(int maxBigGuys) {
        _maxBigGuys = maxBigGuys;
    }
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
