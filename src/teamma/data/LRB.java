/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package teamma.data;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import tourma.MainFrame;

/**
 *
 * @author WFMJ7631
 */
public final class LRB {

    /**
     *
     */
    private static LRB _singleton = null;
    /**
     *
     */
    private static final Object myLock = new Object();
    private static final Logger LOG = Logger.getLogger(LRB.class.getName());

    /**
     *
     * @return
     */
    public static LRB getLRB() {
        synchronized (LRB.myLock) {
            if (_singleton == null) {
                _singleton = new LRB();
            }
        }
        return _singleton;
    }

    /**
     *
     */
    private ArrayList<RosterType> _rosterTypes = null;
    /**
     *
     */
    private ArrayList<StarPlayer> _starPlayers = null;
    /**
     *
     */
    private ArrayList<SkillType> _skillTypes = null;
    /**
     *
     */
    private String _name;
    /**
     *
     */
    private boolean _allowSpecialSkills = false;

    /**
     *
     */
    private LRB() {
        _rosterTypes = new ArrayList<>();
        _starPlayers = new ArrayList<>();
        _skillTypes = new ArrayList<>();

        /* URL url;
         url = getClass().getResource("/teamma/rules/rules.xml");*/
        InputStream is = getClass().getResourceAsStream("/teamma/rules/rules.xml");
        loadLRB(is);
        try {
            is.close();
        } catch (IOException e) {
            LOG.severe(e.getLocalizedMessage());
        }
    }

    /**
     *
     * @param file
     */
    private void loadLRB(InputStream file) {
        try {
            SAXBuilder sxb = new SAXBuilder();
            org.jdom2.Document document = sxb.build(file);
            Element racine = document.getRootElement();

            /*
             * Get LRB name
             */
            Element e_name = racine.getChild("name");
            setName(e_name.getValue());

            /*
             * Get Skill file name
             */
            Element e_skillfile = racine.getChild("skills");
            String skillfile = e_skillfile.getValue();

            LOG.log(Level.FINE, "loading {0} file", skillfile);
            loadSkills(getClass().getResourceAsStream("/teamma/rules/" + skillfile));

            Element e_teams = racine.getChild("teams");
            List<Element> l_teams = e_teams.getChildren("team");
            Iterator<Element> cr = l_teams.iterator();
            this.clearRosterTypes();

            while (cr.hasNext()) {
                Element e_team = cr.next();
                String teamfile = e_team.getValue();
                String imagename = e_team.getAttribute("image").getValue();
                LOG.log(Level.FINE, "loading {0} file", teamfile);
                loadTeam(getClass().getResourceAsStream("/teamma/rules/" + teamfile), imagename);
            }
            /*
             * Get Star Players file name
             */
            Element e_starfile = racine.getChild("starplayers");
            String starfile = e_starfile.getValue();
            loadStarPlayers(getClass().getResourceAsStream("/teamma/rules/" + starfile));

        } catch (JDOMException | IOException jdomexception) {
            JOptionPane.showMessageDialog(MainFrame.getMainFrame(), jdomexception.getLocalizedMessage());
        }
    }

    /**
     *
     * @param file
     */
    private void loadSkills(InputStream file) {
        try {
            SAXBuilder sxb = new SAXBuilder();
            org.jdom2.Document document = sxb.build(file);
            Element racine = document.getRootElement();

            List<Element> l_categories = racine.getChildren("category");
            Iterator<Element> cr = l_categories.iterator();
            clearSkillTypes();

            while (cr.hasNext()) {
                Element e_skillType = cr.next();

                Element e_name = e_skillType.getChild("name");
                String st_name = e_name.getValue();

                Element e_accro = e_skillType.getChild("accronym");
                String st_accro = e_accro.getValue();

                SkillType st = new SkillType(st_name, st_accro);

                Element e_special = e_skillType.getChild("special");
                st.setSpecial(Boolean.parseBoolean(e_special.getValue()));
                List<Element> l_skills = e_skillType.getChildren("skill");
                Iterator<Element> i = l_skills.iterator();

                while (i.hasNext()) {
                    Element e_skill = i.next();
                    Skill s = new Skill(e_skill.getValue(), st);
                    st.addSkill(s);
                }
                addSkillType(st);
            }

        } catch (JDOMException | IOException jdomexception) {
            JOptionPane.showMessageDialog(MainFrame.getMainFrame(), jdomexception.getLocalizedMessage());
        }
    }

    /**
     *
     * @param file
     * @param image
     */
    private void loadTeam(InputStream file, String image) {
        try {
            SAXBuilder sxb = new SAXBuilder();
            org.jdom2.Document document = sxb.build(file);
            Element racine = document.getRootElement();

            Element e_name = racine.getChild("name");
            //RosterType rt = new RosterType(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString(e_name.getValue()));
            String n = tourma.data.RosterType.getRosterName(e_name.getValue());
            if (n == null) {
                n = tourma.data.RosterType.translate(e_name.getValue());

            }
            RosterType rt = new RosterType(n);
            rt.setImage(image);
            Element e_reroll_cost = racine.getChild("reroll");
            rt.setReroll_cost(Integer.parseInt(e_reroll_cost.getValue()));

            Element e_apo = racine.getChild("apothecary");
            rt.setApothecary(Boolean.parseBoolean(e_apo.getValue()));

            Element e_chef_cost = racine.getChild("chef");
            rt.setChef_cost(Integer.parseInt(e_chef_cost.getValue()));

            Element e_igor = racine.getChild("igor");
            rt.setIgor(Boolean.parseBoolean(e_igor.getValue()));

            Element e_bribe_cost = racine.getChild("bribe");
            rt.setBribe_cost(Integer.parseInt(e_bribe_cost.getValue()));

            Element e_playerType = racine.getChild("playertypes");
            List<Element> l_players = e_playerType.getChildren("playerType");
            Iterator<Element> cr = l_players.iterator();
            rt.clearPlayerType();

            while (cr.hasNext()) {
                Element e_player = cr.next();

                Element e_position = e_player.getChild("position");
                PlayerType pt = new PlayerType(e_position.getValue());
                Element e_limit = e_player.getChild("limit");
                pt.setLimit(Integer.parseInt(e_limit.getValue()));
                Element e_movement = e_player.getChild("movement");
                pt.setMovement(Integer.parseInt(e_movement.getValue()));
                Element e_strength = e_player.getChild("strength");
                pt.setStrength(Integer.parseInt(e_strength.getValue()));
                Element e_agility = e_player.getChild("agility");
                pt.setAgility(Integer.parseInt(e_agility.getValue()));
                Element e_armor = e_player.getChild("armor");
                pt.setArmor(Integer.parseInt(e_armor.getValue()));
                Element e_cost = e_player.getChild("cost");
                pt.setCost(Integer.parseInt(e_cost.getValue()));

                Element e_skills = e_player.getChild("skills");
                List<Element> l_skills = e_skills.getChildren("skill");
                Iterator<Element> i = l_skills.iterator();
                while (i.hasNext()) {
                    Element e_skill = i.next();
                    Skill s = getSkill(e_skill.getValue());
                    if (s == null) {
                        JOptionPane.showMessageDialog(MainFrame.getMainFrame(), "Skill not found: " + e_skill.getValue() + " for player " + pt.getPosition());
                    } else {
                        pt.addSkill(s);
                    }
                }

                Element e_single = e_player.getChild("single");
                List<Element> l_singleskilltypes = e_single.getChildren("skillType");
                Iterator<Element> j = l_singleskilltypes.iterator();
                while (j.hasNext()) {
                    Element e_skilltype = j.next();
                    SkillType st = getSkillType(e_skilltype.getValue());
                    if (st == null) {
                        JOptionPane.showMessageDialog(MainFrame.getMainFrame(), "Skill Type not found: " + e_skilltype.getValue() + " for player " + pt.getPosition());
                    } else {
                        pt.addSingle(st);
                    }
                }

                Element e_double = e_player.getChild("double");
                List<Element> l_doubleskilltypes = e_double.getChildren("skillType");
                j = l_doubleskilltypes.iterator();
                while (j.hasNext()) {
                    Element e_skilltype = j.next();
                    SkillType st = getSkillType(e_skilltype.getValue());
                    if (st == null) {
                        JOptionPane.showMessageDialog(MainFrame.getMainFrame(), "Skill Type not found: " + e_skilltype.getValue() + " for player " + pt.getPosition());
                    } else {
                        pt.addDouble(st);
                    }
                }
                rt.addPlayerType(pt);
            }

            addRosterType(rt);
        } catch (JDOMException | IOException jdomexception) {
            JOptionPane.showMessageDialog(MainFrame.getMainFrame(), jdomexception.getLocalizedMessage());
        }
    }

    /**
     *
     * @param file
     */
    private void loadStarPlayers(InputStream file) {
        try {
            SAXBuilder sxb = new SAXBuilder();
            org.jdom2.Document document = sxb.build(file);
            Element racine = document.getRootElement();

            clearStarPlayers();

            List<Element> stars = racine.getChildren("starplayer");
            Iterator<Element> j = stars.iterator();
            while (j.hasNext()) {
                Element e_star = j.next();

                Element e_name = e_star.getChild("name");
                StarPlayer sp = new StarPlayer(e_name.getValue());

                sp.setPosition(e_star.getChild("position").getValue());
                sp.setMovement(Integer.parseInt(e_star.getChild("movement").getValue()));
                sp.setStrength(Integer.parseInt(e_star.getChild("strength").getValue()));
                sp.setAgility(Integer.parseInt(e_star.getChild("agility").getValue()));
                sp.setArmor(Integer.parseInt(e_star.getChild("armor").getValue()));
                sp.setCost(Integer.parseInt(e_star.getChild("cost").getValue()));

                List<Element> skilllist = e_star.getChild("skills").getChildren("skill");
                Iterator<Element> i = skilllist.iterator();
                while (i.hasNext()) {
                    Element e_skill = i.next();
                    Skill s = getSkill(e_skill.getValue());
                    if (s == null) {
                        JOptionPane.showMessageDialog(MainFrame.getMainFrame(), "Skill not found: " + e_skill.getValue() + " for player " + sp.getName());
                    } else {
                        sp.addSkill(s);
                    }
                }

                List<Element> rosterlist = e_star.getChildren("team");
                i = rosterlist.iterator();
                while (i.hasNext()) {
                    Element e_team = i.next();
                    String n = tourma.data.RosterType.getRosterName(e_team.getValue());
                    if (n == null) {
                        n = tourma.data.RosterType.translate(e_team.getValue());

                    }
                    RosterType rt = new RosterType(n);
                    //RosterType rt = getRosterType(tourma.data.RosterType.translate(e_team.getValue()));
                    if (rt == null) {
                        JOptionPane.showMessageDialog(null, "RosterType not found: " + e_team.getValue() + " for player " + sp.getName());
                    } else {
                        sp.addRoster(rt);
                        rt.addAvailableStarPlayer(sp);
                    }
                }

                addStarPlayer(sp);

            }

        } catch (JDOMException | IOException jdomexception) {
            JOptionPane.showMessageDialog(MainFrame.getMainFrame(), jdomexception.getLocalizedMessage());
        }
    }

    /**
     *
     * @param name
     * @return
     */
    public Skill getSkill(String name) {
        int i, j;
        for (i = 0; i < getSkillTypeCount(); i++) {
            SkillType st = getSkillType(i);
            for (j = 0; j < st.getSkillCount(); j++) {
                Skill s = st.getSkill(j);
                if (name.equals(s.getmName())) {
                    return s;
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
    public SkillType getSkillType(String name) {
        int i;
        for (i = 0; i < getSkillTypeCount(); i++) {
            SkillType st = getSkillType(i);

            if (name.equals(st.getName())) {
                return st;
            }

        }
        return null;
    }

    /**
     *
     * @param name
     * @return
     */
    public RosterType getRosterType(String name) {
        int i;
        for (i = 0; i < getRosterTypeCount(); i++) {
            RosterType rt = getRosterType(i);

            if (name.equals(rt.getName())) {
                return rt;
            }
        }
        return null;
    }

    /**
     *
     * @param name
     * @return
     */
    public StarPlayer getStarPlayer(String name) {
        int i;
        for (i = 0; i < getStarPlayerCount(); i++) {
            StarPlayer rt = getStarPlayer(i);

            if (name.equals(rt.getName())) {
                return rt;
            }
        }
        return null;
    }

    /**
     *
     * @return
     */
    public ArrayList<String> getRosterTypeListAsString() {
        int i;
        ArrayList<String> res = new ArrayList<>();
        for (i = 0; i < getRosterTypeCount(); i++) {
            res.add(getRosterType(i).getName());
        }

        return res;
    }

    /**
     * @param i
     * @return the _rosterTypes
     */
    public RosterType getRosterType(int i) {
        return _rosterTypes.get(i);
    }

    /**
     * @return the _rosterTypes
     */
    public int getRosterTypeCount() {
        return _rosterTypes.size();
    }

    /**
     * Clear the roster types list
     */
    public void clearRosterTypes() {
        _rosterTypes.clear();
    }

    /**
     * @param rt *
     */
    public void addRosterType(RosterType rt) {
        _rosterTypes.add(rt);
    }

    /**
     * @return the _starPlayers
     */
    public int getStarPlayerCount() {
        return _starPlayers.size();
    }

    /**
     * @param i
     * @return the _starPlayers
     */
    public StarPlayer getStarPlayer(int i) {
        return _starPlayers.get(i);
    }

    /**
     * Clear the star player list
     */
    public void clearStarPlayers() {
        _starPlayers.clear();
    }

    /**
     * @param sp
     */
    public void addStarPlayer(StarPlayer sp) {
        _starPlayers.add(sp);
    }

    /**
     * @param st
     */
    public void addSkillType(SkillType st) {
        _skillTypes.add(st);
    }

    /**
     * Clear the skill types list
     */
    public void clearSkillTypes() {
        _skillTypes.clear();
    }

    /**
     * @param i
     * @return the _skillTypes
     */
    public SkillType getSkillType(int i) {
        return _skillTypes.get(i);
    }

    /**
     * @return the _skillTypes
     */
    public int getSkillTypeCount() {
        return _skillTypes.size();
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
     * @return the _allowSpecialSkills
     */
    public boolean isAllowSpecialSkills() {
        return _allowSpecialSkills;
    }

    /**
     * @param _allowSpecialSkills the _allowSpecialSkills to set
     */
    public void setAllowSpecialSkills(boolean _allowSpecialSkills) {
        this._allowSpecialSkills = _allowSpecialSkills;
    }

    /**
     * Unload the LRB
     */
    public static void unloadLRB() {
        LRB._singleton = null;
    }

}
