/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package teamma.data;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jdom2.Element;
import tourma.data.XMLExport;
import tourma.utility.StringConstants;

/**
 *
 * @author WFMJ7631
 */
public class Roster implements XMLExport {
    /*
     * Pointer to coach owning the team
     */

    public RosterType _roster;
    /**
     * Standard creation team
     */
    public ArrayList<Player> _players;
    public int _rerolls;
    public boolean _apothecary;
    public int _fanfactor;
    public int _cheerleaders;
    public int _assistants;
    /**
     * Inducements
     */
    public int _extrarerolls;
    public int _localapothecary;
    public int _bloodweiserbabes;
    public int _corruptions;
    public boolean _chef;
    public boolean _igor;
    public boolean _wizard;
    public int _cards;
    /*
     * No mercenary ?
     */
    public ArrayList<StarPlayer> _champions;

    public Roster() {
        _players = new ArrayList<Player>();
        _champions = new ArrayList<StarPlayer>();
    }

    public int getValue(boolean bWithSkill) {
        int cost = 0;
        /**
         * Add player cost
         */
        for (int i = 0; i < _players.size(); i++) {


            cost += _players.get(i).getValue(bWithSkill);
        }


        /**
         * Add Team Goods
         */
        cost += _assistants * RosterType._assistant_cost;
        cost += _cheerleaders * RosterType._cheerleader_cost;
        cost += _fanfactor * RosterType._fan_factor_cost;
        cost += _apothecary ? RosterType._apothecary_cost : 0;
        cost += _roster != null ? _rerolls * _roster._reroll_cost : 0;

        /**
         * Add Inducements
         */
        for (int i = 0; i < _champions.size(); i++) {
            cost += _champions.get(i)._cost;
        }

        cost += _bloodweiserbabes * RosterType._babe_cost;
        cost += _cards;
        cost += _roster != null ? _corruptions * _roster._bribe_cost : 0;
        cost += _extrarerolls * RosterType._extra_reroll_cost;
        cost += _localapothecary * RosterType._local_apo_cost;
        cost += (_chef) && (_roster != null) ? _roster._chef_cost : 0;
        cost += _igor ? RosterType._igor_cost : 0;
        cost += _wizard ? RosterType._wizard_cost : 0;

        return cost;
    }

    @Override
    public Element getXMLElement() {
        Element compo = new Element(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("COMPOSITION"));

        compo.setAttribute(StringConstants.CS_ROSTER, this._roster._name);
        compo.setAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("APOTHECARY"), Boolean.toString(this._apothecary));
        compo.setAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("ASSISTANTS"), Integer.toString(this._assistants));
        compo.setAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("CHEERLEADERS"), Integer.toString(this._cheerleaders));
        compo.setAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("FANFACTOR"), Integer.toString(this._fanfactor));
        compo.setAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("REROLLS"), Integer.toString(this._rerolls));

        final Element inducements = new Element(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("INDUCEMENTS"));
        inducements.setAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("CHEF"), Boolean.toString(this._chef));
        inducements.setAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("IGOR"), Boolean.toString(this._igor));
        inducements.setAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("WIZARD"), Boolean.toString(this._wizard));
        inducements.setAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("BABES"), Integer.toString(this._bloodweiserbabes));
        inducements.setAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("CARDS"), Integer.toString(this._cards));
        inducements.setAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("BRIBE"), Integer.toString(this._corruptions));
        inducements.setAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("EXTRAREROLLS"), Integer.toString(this._extrarerolls));
        inducements.setAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("LOCALAPOTHECARY"), Integer.toString(this._localapothecary));


        for (int j = 0; j < this._champions.size(); j++) {
            final Element st = new Element(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("STARPLAYER"));
            st.setAttribute(StringConstants.CS_NAME, this._champions.get(j)._name);
            inducements.addContent(st);
        }

        compo.addContent(inducements);

        for (int j = 0; j < this._players.size(); j++) {
            final Element p = new Element(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("PLAYER"));
            final teamma.data.Player pl = this._players.get(j);
            p.setAttribute(StringConstants.CS_NAME, pl._name);
            p.setAttribute(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("POSITION"), pl._playertype._position);

            for (int k = 0; k < pl._skills.size(); k++) {
                final Element s = new Element(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("SKILL"));
                final teamma.data.Skill sk = pl._skills.get(k);
                s.setAttribute(StringConstants.CS_NAME, sk.mName);
                s.setAttribute("Color",Integer.toString(sk.mColor.getRGB()));
                p.addContent(s);
            }
            compo.addContent(p);
        }

        return compo;

    }

    @Override
    public void setXMLElement(Element e) {
        this._roster = teamma.data.lrb.getLRB().getRosterType(e.getAttributeValue(StringConstants.CS_ROSTER));
        this._apothecary = Boolean.parseBoolean(e.getAttributeValue(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("APOTHECARY")));
        this._assistants = Integer.parseInt(e.getAttributeValue(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("ASSISTANTS")));
        this._cheerleaders = Integer.parseInt(e.getAttributeValue(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("CHEERLEADERS")));
        this._fanfactor = Integer.parseInt(e.getAttributeValue(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("FANFACTOR")));
        this._rerolls = Integer.parseInt(e.getAttributeValue(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("REROLLS")));

        final Element inducements = e.getChild(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("INDUCEMENTS"));
        if (inducements != null) {
            this._bloodweiserbabes = Integer.parseInt(inducements.getAttributeValue(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("BABES")));
            this._cards = Integer.parseInt(inducements.getAttributeValue(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("CARDS")));
            this._chef = Boolean.parseBoolean(inducements.getAttributeValue(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("CHEF")));
            this._corruptions = Integer.parseInt(inducements.getAttributeValue(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("BRIBE")));
            this._extrarerolls = Integer.parseInt(inducements.getAttributeValue(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("EXTRAREROLLS")));
            this._igor = Boolean.parseBoolean(inducements.getAttributeValue(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("IGOR")));
            this._localapothecary = Integer.parseInt(inducements.getAttributeValue(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("LOCALAPOTHECARY")));
            this._wizard = Boolean.parseBoolean(inducements.getAttributeValue(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("WIZARD")));

            final List stars = inducements.getChildren(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("STARPLAYERS"));
            final Iterator s = stars.iterator();
            while (s.hasNext()) {
                final Element star = (Element) s.next();
                final StarPlayer t = lrb.getLRB().getStarPlayer(star.getAttributeValue(StringConstants.CS_NAME));
                this._champions.add(t);
            }
        }

        final List players = e.getChildren(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("PLAYER"));
        final Iterator ip = players.iterator();
        while (ip.hasNext()) {
            final Element p = (Element) ip.next();

            final teamma.data.Player pl = new Player(this._roster.getPlayerType(p.getAttributeValue(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("POSITION"))));
            pl._name = p.getAttributeValue(StringConstants.CS_NAME);

            final List skills = p.getChildren(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("SKILL"));
            final Iterator is = skills.iterator();
            while (is.hasNext()) {
                final Element s = (Element) is.next();

                final teamma.data.Skill sl = lrb.getLRB().getSkill(s.getAttributeValue(StringConstants.CS_NAME));
                sl.mColor= Color.decode(s.getAttributeValue("Color"));
                pl._skills.add(sl);
            }

            this._players.add(pl);
        }
    }
}
