/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package teamma.data;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jdom.Element;
import tourma.data.IXMLExport;

/**
 *
 * @author WFMJ7631
 */
public class Roster implements IXMLExport, Serializable {

    private final static String CS_LRB = "LRB";
    private final static String CS_Roster = "Roster";
    private final static String CS_Composition = "Composition";
    private final static String CS_Apothecary = "Apothecary";
    private final static String CS_Assistants = "Assistants";
    private final static String CS_Cheerleaders = "Cheerleaders";
    private final static String CS_FanFactor = "FanFactor";
    private final static String CS_Rerolls = "Rerolls";
    private final static String CS_Inducements = "Inducements";
    private final static String CS_Inducement = "Inducement";
     private final static String CS_Nb = "Nb";
      private final static String CS_Type = "Type";
    private final static String CS_Chef = "Chef";
    private final static String CS_Igor = "Igor";
    private final static String CS_Wizard = "Wizard";
    private final static String CS_Babes = "Babes";
    private final static String CS_Cards = "Cards";
    private final static String CS_Bribe = "Bribe";
    private final static String CS_ExtraRerolls = "ExtraRerolls";
    private final static String CS_LocalApothecary = "LocalApothecary";

    private final static String CS_StarPlayer = "StarPlayer";
    private final static String CS_Name = "Name";
    private final static String CS_Player = "Player";
    private final static String CS_Position = "Position";
    private final static String CS_Skill = "Skill";
    private final static String CS_Color = "Color";
    /**
     * Pointer to coach owning the team
     */
    private RosterType _roster;
    /**
     * Standard creation team
     */
    private final ArrayList<Player> _players;

    private final ArrayList<Inducement> _inducements;

    /**
     *
     */
    private int _rerolls;
    /**
     *
     */
    private boolean _apothecary;
    /**
     *
     */
    private int _fanfactor;
    /**
     *
     */
    private int _cheerleaders;
    /**
     *
     */
    private int _assistants;
    /*
     * Inducements
     */
   

   
    /*
     * No mercenary ?
     */
    /**
     *
     */
    private final ArrayList<StarPlayer> _champions;

    LRB.E_Version _version;

    public LRB.E_Version getVersion() {
        return _version;
    }

    public void setVersion(LRB.E_Version _version) {
        this._version = _version;
    }

    /**
     * Default Constructor
     */
    public Roster() {
        _players = new ArrayList<>();
        _champions = new ArrayList<>();
        _inducements = new ArrayList<>();
    }

    public void pull(Roster roster) {
        this._apothecary = roster._apothecary;
        this._assistants = roster._assistants;

        this._cheerleaders = roster._cheerleaders;

        this._fanfactor = roster._fanfactor;

        this._rerolls = roster._rerolls;


        this._roster = LRB.getLRB(roster.getRoster().getVersion()).getRosterType(roster.getRoster().getName());

        this._players.clear();
        for (int i = 0; i < roster.getPlayerCount(); i++) {
            PlayerType pt = LRB.getLRB(roster.getRoster().getVersion()).getRosterType(roster.getRoster().getName()).getPlayerType(roster.getPlayer(i).getName(), false);
            Player p = new Player(pt);
            p.pull(roster.getPlayer(i), roster.getRoster().getVersion());
            _players.add(p);
        }

        this._champions.clear();
        for (int i = 0; i < roster.getChampionCount(); i++) {
            StarPlayer sp = LRB.getLRB(roster.getRoster().getVersion()).getStarPlayer(roster.getChampion(i).getName());
            _champions.add(sp);
        }

    }

    public int getInducementsSize() {
        return _inducements.size();
    }

    public Inducement getInducement(int i) {
        return _inducements.get(i);
    }

    public void setInducement(InducementType it, int nb) {
        boolean found = false;
        for (int cpt = 0; cpt < _inducements.size(); cpt++) {
            Inducement induc = _inducements.get(cpt);
            if (induc.getType() == it) {
                induc.setNb(nb);
                found = true;
            }
        }
        if (!found) {
            Inducement induc = new Inducement();
            induc.setType(it);
            induc.setNb(nb);
            _inducements.add(induc);
        }
    }

    public int getNbInduc(InducementType it) {
        int value = 0;
        for (int cpt = 0; cpt < _inducements.size(); cpt++) {
            Inducement induc = _inducements.get(cpt);
            if (induc.getType() == it) {
                value = induc.getNb();
            }
        }
        return value;
    }

    /**
     *
     * @param bWithSkill
     * @return
     */
    public int getValue(boolean bWithSkill) {
        int cost = 0;
        /**
         * Add player cost
         */
        for (int cpt = 0; cpt < getPlayerCount(); cpt++) {
            Player _player = getPlayer(cpt);
            cost += _player.getValue(bWithSkill);
        }

        /**
         * Add Team Goods
         */
        cost += getAssistants() * RosterType.getAssistant_cost();
        cost += getCheerleaders() * RosterType.getCheerleader_cost();
        cost += getFanfactor() * RosterType.getFan_factor_cost();
        cost += isApothecary() ? RosterType.getApothecary_cost() : 0;
        cost += getRoster() != null ? getRerolls() * getRoster().getReroll_cost() : 0;

        /**
         * Add Inducements
         */
        for (int cpt = 0; cpt < getChampionCount(); cpt++) {
            StarPlayer _champion = getChampion(cpt);
            cost += _champion.getCost();
        }

     
        for (Inducement induc:_inducements)
        {
            cost+=induc.getNb()*induc.getType().getCost();
        }
       

        return cost;
    }

    /**
     *
     * @return
     */
    @Override
    public Element getXMLElement() {

        Element compo = new Element(CS_Composition);
        if (this.getRoster() != null) {
            if (this._version == null) {
                this._version = LRB.E_Version.BB2016;
            }
            switch (this._version) {
                case LRB1:
                    compo.setAttribute(CS_LRB, "LRB1");
                    break;
                case LRB2:
                    compo.setAttribute(CS_LRB, "LRB2");
                    break;
                case LRB3:
                    compo.setAttribute(CS_LRB, "LRB3");
                    break;
                case LRB4:
                    compo.setAttribute(CS_LRB, "LRB4");
                    break;
                case LRB5:
                    compo.setAttribute(CS_LRB, "LRB5");
                    break;
                case LRB6:
                    compo.setAttribute(CS_LRB, "LRB6");
                    break;
                case CRP1:
                    compo.setAttribute(CS_LRB, "CRP1");
                    break;
                case BB2016:
                    compo.setAttribute(CS_LRB, "NAF2017");
                    break;

            }

            compo.setAttribute(CS_Roster, this.getRoster().getName());
            compo.setAttribute(CS_Apothecary, Boolean.toString(this.isApothecary()));
            compo.setAttribute(CS_Assistants, Integer.toString(this.getAssistants()));
            compo.setAttribute(CS_Cheerleaders, Integer.toString(this.getCheerleaders()));
            compo.setAttribute(CS_FanFactor, Integer.toString(this.getFanfactor()));
            compo.setAttribute(CS_Rerolls, Integer.toString(this.getRerolls()));

            final Element inducements = new Element(CS_Inducements);
            for (Inducement induc: _inducements)
            {
                Element e_induc=new Element(CS_Inducement);
                e_induc.setAttribute(CS_Nb,Integer.toString(induc._nb));
                e_induc.setAttribute(CS_Type,induc._type.getName());
                inducements.addContent(e_induc);
            }
          

            for (int cpt = 0; cpt < getChampionCount(); cpt++) {
                StarPlayer _champion = getChampion(cpt);
                final Element st = new Element(CS_StarPlayer);
                if (_champion != null) {
                    st.setAttribute(CS_Name, _champion.getName());
                }
                inducements.addContent(st);

            }

            compo.addContent(inducements);

            for (int cpt = 0; cpt < getPlayerCount(); cpt++) {
                Player _player = getPlayer(cpt);
                final Element p = new Element(CS_Player);
                final teamma.data.Player pl = _player;
                p.setAttribute(CS_Name, pl.getName());
                p.setAttribute(CS_Position, pl.getPlayertype().getPosition());
                for (int i = 0; i < pl.getSkillCount(); i++) {
                    Skill _skill = pl.getSkill(i);
                    final Element s = new Element(CS_Skill);
                    final teamma.data.Skill sk = _skill;
                    s.setAttribute(CS_Name, sk.getmName());
                    int rgb = sk.getmColor().getRGB();
                    s.setAttribute(CS_Color, Integer.toString(rgb));
                    p.addContent(s);
                }
                compo.addContent(p);
            }
        }
        return compo;

    }

    /**
     *
     * @param e
     */
    @Override
    public void setXMLElement(Element e) {

        String rosterType = e.getAttributeValue(CS_Roster);

        LRB.E_Version version = LRB.E_Version.CRP1;

        try {
            String slrb = e.getAttributeValue(CS_LRB);
            if (slrb.equals("LRB1")) {
                version = LRB.E_Version.LRB1;
            }
            if (slrb.equals("LRB2")) {
                version = LRB.E_Version.LRB2;
            }
            if (slrb.equals("LRB3")) {
                version = LRB.E_Version.LRB3;
            }
            if (slrb.equals("LRB4")) {
                version = LRB.E_Version.LRB4;
            }
            if (slrb.equals("LRB5")) {
                version = LRB.E_Version.LRB5;
            }
            if (slrb.equals("LRB6")) {
                version = LRB.E_Version.LRB6;
            }
            if (slrb.equals("BB2016")) {
                version = LRB.E_Version.CRP1;
            }
        } catch (NullPointerException npe) {

        }

        LRB lrb = LRB.getLRB(version);

        this.setRoster(lrb.getRosterType(rosterType, false));
        this.setApothecary(Boolean.parseBoolean(e.getAttributeValue(CS_Apothecary)));
        this.setAssistants(Integer.parseInt(e.getAttributeValue(CS_Assistants)));
        this.setCheerleaders(Integer.parseInt(e.getAttributeValue(CS_Cheerleaders)));
        this.setFanfactor(Integer.parseInt(e.getAttributeValue(CS_FanFactor)));
        this.setRerolls(Integer.parseInt(e.getAttributeValue(CS_Rerolls)));

        final Element inducements = e.getChild(CS_Inducements);
        if (inducements != null) {
            
            final List<Element> inducs = inducements.getChildren(CS_Inducement);
            final Iterator<Element> i_induc = inducs.iterator();
            while (i_induc.hasNext()) {
                final Element e_induc = i_induc.next();
                final String it_name = e_induc.getAttributeValue(CS_Type);
                InducementType it= this.getRoster().getInducementType(it_name);
                this.setInducement(it, Integer.parseInt( e_induc.getAttributeValue(CS_Nb)));
            }

            final List<Element> stars = inducements.getChildren(CS_StarPlayer);
            final Iterator<Element> s = stars.iterator();
            while (s.hasNext()) {
                final Element star = s.next();
                final String spn = star.getAttributeValue(CS_Name);
                final StarPlayer t = lrb.getStarPlayer(spn);
                this.addChampion(t);
            }
        }

        final List<Element> players = e.getChildren(CS_Player);
        final Iterator<Element> ip = players.iterator();

        while (ip.hasNext()) {
            final Element p = ip.next();
            if (this.getRoster() != null) {
                final teamma.data.Player pl = new Player(this.getRoster().getPlayerType(p.getAttributeValue(CS_Position), false));
                pl.setName(p.getAttributeValue(CS_Name));

                final List<Element> skills = p.getChildren(CS_Skill);
                final Iterator<Element> is = skills.iterator();
                while (is.hasNext()) {
                    final Element s = is.next();
                    final teamma.data.Skill sl = new Skill(LRB.getLRB(version).getSkill(s.getAttributeValue(CS_Name), false));
                    String sColor = s.getAttributeValue(CS_Color);
                    Color col = Color.decode(sColor);
                    sl.setmColor(col);
                    pl.addSkill(sl);
                }

                this.addPlayer(pl);
            }
        }
    }

    /**
     * @return the _cheerleaders
     */
    public int getCheerleaders() {
        return _cheerleaders;
    }

    /**
     * @param _cheerleaders the _cheerleaders to set
     */
    public void setCheerleaders(int _cheerleaders) {
        this._cheerleaders = _cheerleaders;
    }

    /**
     * @return the _assistants
     */
    public int getAssistants() {
        return _assistants;
    }

    /**
     * @param _assistants the _assistants to set
     */
    public void setAssistants(int _assistants) {
        this._assistants = _assistants;
    }

   

    /**
     * @param i
     * @return the _champions
     */
    public StarPlayer getChampion(int i) {
        return _champions.get(i);
    }

    /**
     * @param i
     */
    public void removeChampion(int i) {
        _champions.remove(i);
    }

    /**
     * @param sp
     */
    public void addChampion(StarPlayer sp) {
        _champions.add(sp);
    }

    /**
     * @return the _champions
     */
    public int getChampionCount() {
        return _champions.size();
    }

    /**
     * @return the _roster
     */
    public RosterType getRoster() {
        return _roster;
    }

    /**
     * @param _roster the _roster to set
     */
    public void setRoster(RosterType _roster) {
        this._roster = _roster;
    }

    /**
     * @param i
     * @return the _players
     */
    public Player getPlayer(int i) {
        return _players.get(i);
    }

    /**
     *
     * @param i
     */
    public void removePlayer(int i) {
        _players.remove(i);
    }

    /**
     * @return the _players
     */
    public int getPlayerCount() {
        return _players.size();
    }

    /**
     * @param p
     */
    public void addPlayer(Player p) {
        _players.add(p);
    }

    /**
     * Clear the player list
     */
    public void clearPlayers() {
        _players.clear();
    }

    /**
     * @return the _rerolls
     */
    public int getRerolls() {
        return _rerolls;
    }

    /**
     * @param _rerolls the _rerolls to set
     */
    public void setRerolls(int _rerolls) {
        this._rerolls = _rerolls;
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
     * @return the _fanfactor
     */
    public int getFanfactor() {
        return _fanfactor;
    }

    /**
     * @param _fanfactor the _fanfactor to set
     */
    public void setFanfactor(int _fanfactor) {
        this._fanfactor = _fanfactor;
    }
}
