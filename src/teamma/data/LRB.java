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
import teamma.languages.Translate;
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

    private final static String CS_Name = "name";
    private final static String CS_Skills = "skills";
    private final static String CS_Teams = "teams";
    private final static String CS_Team = "team";
    private final static String CS_Picture = "image";
    private final static String CS_Starplayers = "starplayers";

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
            Element e_name = racine.getChild(CS_Name);
            setName(e_name.getValue());

            /*
             * Get Skill file name
             */
            Element e_skillfile = racine.getChild(CS_Skills);
            String skillfile = e_skillfile.getValue();

            LOG.log(Level.FINE, "loading {0} file", skillfile);
            loadSkills(getClass().getResourceAsStream("/teamma/rules/" + skillfile));

            Element e_teams = racine.getChild(CS_Teams);
            List<Element> l_teams = e_teams.getChildren(CS_Team);
            Iterator<Element> cr = l_teams.iterator();
            this.clearRosterTypes();

            while (cr.hasNext()) {
                Element e_team = cr.next();
                String teamfile = e_team.getValue();
                String imagename = e_team.getAttribute(CS_Picture).getValue();
                LOG.log(Level.FINE, "loading {0} file", teamfile);
                loadTeam(getClass().getResourceAsStream("/teamma/rules/" + teamfile), imagename);
            }
            /*
             * Get Star Players file name
             */
            Element e_starfile = racine.getChild(CS_Starplayers);
            String starfile = e_starfile.getValue();
            loadStarPlayers(getClass().getResourceAsStream("/teamma/rules/" + starfile));

        } catch (JDOMException | IOException jdomexception) {
            JOptionPane.showMessageDialog(MainFrame.getMainFrame(), jdomexception.getLocalizedMessage());
        }
    }

    private final static String CS_Category = "category";
    private final static String CS_Accronym = "accronym";
    private final static String CS_Special = "special";
    private final static String CS_Skill = "skill";

    /**
     *
     * @param file
     */
    private void loadSkills(InputStream file) {
        try {
            SAXBuilder sxb = new SAXBuilder();
            org.jdom2.Document document = sxb.build(file);
            Element racine = document.getRootElement();

            List<Element> l_categories = racine.getChildren(CS_Category);
            Iterator<Element> cr = l_categories.iterator();
            clearSkillTypes();

            while (cr.hasNext()) {
                Element e_skillType = cr.next();

                Element e_name = e_skillType.getChild(CS_Name);
                String st_name = e_name.getValue();

                Element e_accro = e_skillType.getChild(CS_Accronym);
                String st_accro = e_accro.getValue();

                SkillType st = new SkillType(st_name, st_accro);

                Element e_special = e_skillType.getChild(CS_Special);
                st.setSpecial(Boolean.parseBoolean(e_special.getValue()));
                List<Element> l_skills = e_skillType.getChildren(CS_Skill);
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

    private final static String CS_Reroll = "reroll";
    private final static String CS_Apothecary = "apothecary";
    private final static String CS_Chef = "chef";
    private final static String CS_Igor = "igor";
    private final static String CS_Bribe = "bribe";
    private final static String CS_PlayerTypes = "playertypes";
    private final static String CS_PlayerType = "playerType";
    private final static String CS_Position = "position";
    private final static String CS_Limit = "limit";
    private final static String CS_Movement = "movement";
    private final static String CS_Strength = "strength";
    private final static String CS_Agility = "agility";
    private final static String CS_Armor = "armor";
    private final static String CS_Cost = "cost";
    private final static String CS_Single = "single";
    private final static String CS_SkillType = "skillType";
    private final static String CS_Double = "double";

    private final static String CS_SkillNotFound = "SkillNotFound";
    private final static String CS_SkillTypeNotFound = "SkillTypeNotFound";
    private final static String CS_RosterTypeNotFound = "RosterTypeNotFound";
    private final static String CS_forThePlayer = "forThePlayer";

    
    
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

            Element e_name = racine.getChild(CS_Name);

            String n = Translate.translate(e_name.getValue());

            RosterType rt = new RosterType(n);
            rt.setImage(image);
            Element e_reroll_cost = racine.getChild(CS_Reroll);
            rt.setReroll_cost(Integer.parseInt(e_reroll_cost.getValue()));

            Element e_apo = racine.getChild(CS_Apothecary);
            rt.setApothecary(Boolean.parseBoolean(e_apo.getValue()));

            Element e_chef_cost = racine.getChild(CS_Chef);
            rt.setChef_cost(Integer.parseInt(e_chef_cost.getValue()));

            Element e_igor = racine.getChild(CS_Igor);
            rt.setIgor(Boolean.parseBoolean(e_igor.getValue()));

            Element e_bribe_cost = racine.getChild(CS_Bribe);
            rt.setBribe_cost(Integer.parseInt(e_bribe_cost.getValue()));

            Element e_playerType = racine.getChild(CS_PlayerTypes);
            List<Element> l_players = e_playerType.getChildren(CS_PlayerType);
            Iterator<Element> cr = l_players.iterator();
            rt.clearPlayerType();

            while (cr.hasNext()) {
                Element e_player = cr.next();

                Element e_position = e_player.getChild(CS_Position);
                PlayerType pt = new PlayerType(e_position.getValue());
                Element e_limit = e_player.getChild(CS_Limit);
                pt.setLimit(Integer.parseInt(e_limit.getValue()));
                Element e_movement = e_player.getChild(CS_Movement);
                pt.setMovement(Integer.parseInt(e_movement.getValue()));
                Element e_strength = e_player.getChild(CS_Strength);
                pt.setStrength(Integer.parseInt(e_strength.getValue()));
                Element e_agility = e_player.getChild(CS_Agility);
                pt.setAgility(Integer.parseInt(e_agility.getValue()));
                Element e_armor = e_player.getChild(CS_Armor);
                pt.setArmor(Integer.parseInt(e_armor.getValue()));
                Element e_cost = e_player.getChild(CS_Cost);
                pt.setCost(Integer.parseInt(e_cost.getValue()));

                Element e_skills = e_player.getChild(CS_Skills);
                List<Element> l_skills = e_skills.getChildren(CS_Skill);
                Iterator<Element> i = l_skills.iterator();
                while (i.hasNext()) {
                    Element e_skill = i.next();
                    Skill s = getSkill(e_skill.getValue());
                    if (s == null) {
                        JOptionPane.showMessageDialog(MainFrame.getMainFrame(), Translate.translate(CS_SkillNotFound) + ": " + e_skill.getValue() + " " + Translate.translate(CS_forThePlayer) + " " + pt.getPosition());
                    } else {
                        pt.addSkill(s);
                    }
                }

                Element e_single = e_player.getChild(CS_Single);
                List<Element> l_singleskilltypes = e_single.getChildren(CS_SkillType);
                Iterator<Element> j = l_singleskilltypes.iterator();
                while (j.hasNext()) {
                    Element e_skilltype = j.next();
                    SkillType st = getSkillType(e_skilltype.getValue());
                    if (st == null) {
                        JOptionPane.showMessageDialog(MainFrame.getMainFrame(), Translate.translate(CS_SkillTypeNotFound) + ": " + e_skilltype.getValue() + " " + Translate.translate(CS_forThePlayer) + " " + pt.getPosition());
                    } else {
                        pt.addSingle(st);
                    }
                }

                Element e_double = e_player.getChild(CS_Double);
                List<Element> l_doubleskilltypes = e_double.getChildren(CS_SkillType);
                j = l_doubleskilltypes.iterator();
                while (j.hasNext()) {
                    Element e_skilltype = j.next();
                    SkillType st = getSkillType(e_skilltype.getValue());
                    if (st == null) {
                        JOptionPane.showMessageDialog(MainFrame.getMainFrame(), Translate.translate(CS_SkillTypeNotFound) + ": " + e_skilltype.getValue() + " " + Translate.translate(CS_forThePlayer) + " " + pt.getPosition());
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

    private final static String CS_Starplayer = "starplayer";

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

            List<Element> stars = racine.getChildren(CS_Starplayer);
            Iterator<Element> j = stars.iterator();
            while (j.hasNext()) {
                Element e_star = j.next();

                Element e_name = e_star.getChild(CS_Name);
                StarPlayer sp = new StarPlayer(e_name.getValue());

                sp.setPosition(e_star.getChild(CS_Position).getValue());
                sp.setMovement(Integer.parseInt(e_star.getChild(CS_Movement).getValue()));
                sp.setStrength(Integer.parseInt(e_star.getChild(CS_Strength).getValue()));
                sp.setAgility(Integer.parseInt(e_star.getChild(CS_Agility).getValue()));
                sp.setArmor(Integer.parseInt(e_star.getChild(CS_Armor).getValue()));
                sp.setCost(Integer.parseInt(e_star.getChild(CS_Cost).getValue()));

                List<Element> skilllist = e_star.getChild(CS_Skills).getChildren(CS_Skill);
                Iterator<Element> i = skilllist.iterator();
                while (i.hasNext()) {
                    Element e_skill = i.next();
                    Skill s = getSkill(e_skill.getValue());
                    if (s == null) {
                        JOptionPane.showMessageDialog(MainFrame.getMainFrame(), Translate.translate(CS_SkillNotFound) + ": " + e_skill.getValue() + " " + Translate.translate(CS_forThePlayer) + " " + sp.getName());
                    } else {
                        sp.addSkill(s);
                    }
                }

                List<Element> rosterlist = e_star.getChildren(CS_Team);
                i = rosterlist.iterator();
                while (i.hasNext()) {
                    Element e_team = i.next();
                    String n = Translate.translate(e_team.getValue());

                    //RosterType rt = new RosterType(n);
                    RosterType rt = getRosterType(n);
                    if (rt == null) {
                        JOptionPane.showMessageDialog(MainFrame.getMainFrame(), Translate.translate(CS_RosterTypeNotFound) + ": " + n + " " + Translate.translate(CS_forThePlayer) + " " + e_name.getValue());
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
    public RosterType getRosterType(String name, boolean translate) {
        int i;
        for (i = 0; i < getRosterTypeCount(); i++) {
            RosterType rt = getRosterType(i);

            if (name.equals(Translate.translate(rt.getName()))) {
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
    public ArrayList<String> getRosterTypeListAsString(boolean translate) {
        int i;
        ArrayList<String> res = new ArrayList<>();
        for (i = 0; i < getRosterTypeCount(); i++) {
            if (translate) {
                res.add(Translate.translate(getRosterType(i).getName()));
            } else {
                res.add(getRosterType(i).getName());
            }
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
    
    public RosterType getRosterType(String name) {
        for (RosterType rt:_rosterTypes)
        {
            if (rt.getName().equals(name))
            {
                return rt;
            }
        }
        return null;
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
