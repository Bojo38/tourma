/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package teamma.data;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;
import org.jdom2.Element;
import tourma.data.XMLExport;
import tourma.utility.StringConstants;

/**
 *
 * @author WFMJ7631
 */
public class Roster implements XMLExport {

    private static final Logger LOG = Logger.getLogger(Roster.class.getName());
    /**
     * Pointer to coach owning the team
     */
    private RosterType _roster;
    /**
     * Standard creation team
     */
    private final ArrayList<Player> _players;
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
    /**
     *
     */
    private int _extrarerolls;
    /**
     *
     */
    private int _localapothecary;
    /**
     *
     */
    private int _bloodweiserbabes;
    /**
     *
     */
    private int _corruptions;
    /**
     *
     */
    private boolean _chef;
    /**
     *
     */
    private boolean _igor;
    /**
     *
     */
    private boolean _wizard;
    /**
     *
     */
    private int _cards;
    /*
     * No mercenary ?
     */
    /**
     *
     */
    private final ArrayList<StarPlayer> _champions;

    /**
     * Default Constructor
     */
    public Roster() {
        _players = new ArrayList<>();
        _champions = new ArrayList<>();
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

        cost += getBloodweiserbabes() * RosterType.getBabe_cost();
        cost += getCards();
        cost += getRoster() != null ? getCorruptions() * getRoster().getBribe_cost() : 0;
        cost += getExtrarerolls() * RosterType.getExtraRerollCost();
        cost += getLocalapothecary() * RosterType.getLocal_apo_cost();
        cost += (isChef()) && (getRoster() != null) ? getRoster().getChef_cost() : 0;
        cost += isIgor() ? RosterType.getIgor_cost() : 0;
        cost += isWizard() ? RosterType.getWizard_cost() : 0;

        return cost;
    }

    /**
     *
     * @return
     */
    @Override
    public Element getXMLElement() {

        Element compo = new Element("Composition");
        if (this.getRoster() != null) {
            compo.setAttribute(StringConstants.CS_ROSTER, this.getRoster().getName());
            compo.setAttribute("Apothecary", Boolean.toString(this.isApothecary()));
            compo.setAttribute("Assistants", Integer.toString(this.getAssistants()));
            compo.setAttribute("Cheerleaders", Integer.toString(this.getCheerleaders()));
            compo.setAttribute("FanFactor", Integer.toString(this.getFanfactor()));
            compo.setAttribute("Rerolls", Integer.toString(this.getRerolls()));

            final Element inducements = new Element("Inducements");
            inducements.setAttribute("Chef", Boolean.toString(this.isChef()));
            inducements.setAttribute("Igor", Boolean.toString(this.isIgor()));
            inducements.setAttribute("Wizard", Boolean.toString(this.isWizard()));
            inducements.setAttribute("Babes", Integer.toString(this.getBloodweiserbabes()));
            inducements.setAttribute("Cards", Integer.toString(this.getCards()));
            inducements.setAttribute("Bribe", Integer.toString(this.getCorruptions()));
            inducements.setAttribute("ExtraRerolls", Integer.toString(this.getExtrarerolls()));
            inducements.setAttribute("LocalApothecary", Integer.toString(this.getLocalapothecary()));

            for (int cpt = 0; cpt < getChampionCount(); cpt++) {
                StarPlayer _champion = getChampion(cpt);
                final Element st = new Element("StarPlayer");
                if (_champion != null) {
                    st.setAttribute(StringConstants.CS_NAME, _champion.getName());
                }
                inducements.addContent(st);

            }

            compo.addContent(inducements);

            for (int cpt = 0; cpt < getPlayerCount(); cpt++) {
            Player _player = getPlayer(cpt);
                final Element p = new Element("Player");
                final teamma.data.Player pl = _player;
                p.setAttribute(StringConstants.CS_NAME, pl.getName());
                p.setAttribute("Position", pl.getPlayertype().getPosition());
                for (int i = 0; i < pl.getSkillCount(); i++) {
                    Skill _skill = pl.getSkill(i);
                    final Element s = new Element("Skill");
                    final teamma.data.Skill sk = _skill;
                    s.setAttribute(StringConstants.CS_NAME, sk.getmName());
                    s.setAttribute("Color", Integer.toString(sk.getmColor().getRGB()));
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
        LRB lrb6 = LRB.getLRB();
        String rosterType = e.getAttributeValue(StringConstants.CS_ROSTER);
        this.setRoster(lrb6.getRosterType(rosterType));
        this.setApothecary(Boolean.parseBoolean(e.getAttributeValue("Apothecary")));
        this.setAssistants(Integer.parseInt(e.getAttributeValue("Assistants")));
        this.setCheerleaders(Integer.parseInt(e.getAttributeValue("Cheerleaders")));
        this.setFanfactor(Integer.parseInt(e.getAttributeValue("FanFactor")));
        this.setRerolls(Integer.parseInt(e.getAttributeValue("Rerolls")));

        final Element inducements = e.getChild("Inducements");
        if (inducements != null) {
            this.setBloodweiserbabes(Integer.parseInt(inducements.getAttributeValue("Babes")));
            this.setCards(Integer.parseInt(inducements.getAttributeValue("Cards")));
            this.setChef(Boolean.parseBoolean(inducements.getAttributeValue("Chef")));
            this.setCorruptions(Integer.parseInt(inducements.getAttributeValue("Bribe")));
            this.setExtrarerolls(Integer.parseInt(inducements.getAttributeValue("ExtraRerolls")));
            this.setIgor(Boolean.parseBoolean(inducements.getAttributeValue("Igor")));
            this.setLocalapothecary(Integer.parseInt(inducements.getAttributeValue("LocalApothecary")));
            this.setWizard(Boolean.parseBoolean(inducements.getAttributeValue("Wizard")));

            final List<Element> stars = inducements.getChildren("StarPlayer");
            final Iterator<Element> s = stars.iterator();
            while (s.hasNext()) {
                final Element star = s.next();
                final String spn = star.getAttributeValue(StringConstants.CS_NAME);
                final StarPlayer t = lrb6.getStarPlayer(spn);
                this.addChampion(t);
            }
        }

        final List<Element> players = e.getChildren("Player");
        final Iterator<Element> ip = players.iterator();

        while (ip.hasNext()) {
            final Element p = ip.next();
            if (this.getRoster() != null) {
                final teamma.data.Player pl = new Player(this.getRoster().getPlayerType(p.getAttributeValue("Position")));
                pl.setName(p.getAttributeValue(StringConstants.CS_NAME));

                final List<Element> skills = p.getChildren("Skill");
                final Iterator<Element> is = skills.iterator();
                while (is.hasNext()) {
                    final Element s = is.next();

                    final teamma.data.Skill sl = LRB.getLRB().getSkill(s.getAttributeValue(StringConstants.CS_NAME));
                    sl.setmColor(Color.decode(s.getAttributeValue("Color")));
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
     * @return the _extrarerolls
     */
    public int getExtrarerolls() {
        return _extrarerolls;
    }

    /**
     * @param _extrarerolls the _extrarerolls to set
     */
    public void setExtrarerolls(int _extrarerolls) {
        this._extrarerolls = _extrarerolls;
    }

    /**
     * @return the _localapothecary
     */
    public int getLocalapothecary() {
        return _localapothecary;
    }

    /**
     * @param _localapothecary the _localapothecary to set
     */
    public void setLocalapothecary(int _localapothecary) {
        this._localapothecary = _localapothecary;
    }

    /**
     * @return the _bloodweiserbabes
     */
    public int getBloodweiserbabes() {
        return _bloodweiserbabes;
    }

    /**
     * @param _bloodweiserbabes the _bloodweiserbabes to set
     */
    public void setBloodweiserbabes(int _bloodweiserbabes) {
        this._bloodweiserbabes = _bloodweiserbabes;
    }

    /**
     * @return the _corruptions
     */
    public int getCorruptions() {
        return _corruptions;
    }

    /**
     * @param _corruptions the _corruptions to set
     */
    public void setCorruptions(int _corruptions) {
        this._corruptions = _corruptions;
    }

    /**
     * @return the _chef
     */
    public boolean isChef() {
        return _chef;
    }

    /**
     * @param _chef the _chef to set
     */
    public void setChef(boolean _chef) {
        this._chef = _chef;
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
     * @return the _wizard
     */
    public boolean isWizard() {
        return _wizard;
    }

    /**
     * @param _wizard the _wizard to set
     */
    public void setWizard(boolean _wizard) {
        this._wizard = _wizard;
    }

    /**
     * @return the _cards
     */
    public int getCards() {
        return _cards;
    }

    /**
     * @param _cards the _cards to set
     */
    public void setCards(int _cards) {
        this._cards = _cards;
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
