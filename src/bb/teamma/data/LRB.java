/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bb.teamma.data;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import bb.teamma.languages.Translate;

/**
 *
 * @author WFMJ7631
 */
public final class LRB {

    public enum E_Version {
        LRB1, LRB2, LRB3, LRB4, LRB5, LRB6, CRP1, BB2016, BB2020
    };
    /**
     *
     */
    private static LRB _lrb1 = null;
    private static LRB _lrb2 = null;
    private static LRB _lrb3 = null;
    private static LRB _lrb4 = null;
    private static LRB _lrb5 = null;
    private static LRB _lrb6 = null;
    private static LRB _naf2017 = null;
    private static LRB _crp1 = null;
    private static LRB _bb2020 = null;
    /**
     *
     */
    private static final Object myLock = new Object();
    private static final Logger LOG = Logger.getLogger(LRB.class.getName());

    private E_Version _version = E_Version.BB2020;

    public E_Version getVersion() {
        return _version;
    }

    public boolean isStarplayers_enabled() {
        return _starplayers_enabled;
    }

    public void setStarplayers_enabled(boolean starplayers_enabled) {
        _starplayers_enabled = starplayers_enabled;
    }

    private boolean _starplayers_enabled = true;
    private boolean _check_nb_big_guys = false;

    private int _min_ff = 0;
    private int _max_ff = 18;

    public boolean isCheckNbBigGuys() {
        return _check_nb_big_guys;
    }

    public void setCheckNbBigGuys(boolean check_nb_big_guys) {
        _check_nb_big_guys = check_nb_big_guys;
    }

    /**
     *
     * @return
     */
    public static LRB getLRB(E_Version version) {
        synchronized (LRB.myLock) {
            switch (version) {
                case LRB1:
                    if (_lrb1 == null) {
                        _lrb1 = new LRB(version);
                    }
                    return _lrb1;
                case LRB2:
                    if (_lrb2 == null) {
                        _lrb2 = new LRB(version);
                    }
                    return _lrb2;
                case LRB3:
                    if (_lrb3 == null) {
                        _lrb3 = new LRB(version);
                    }
                    return _lrb3;
                case LRB4:
                    if (_lrb4 == null) {
                        _lrb4 = new LRB(version);
                    }
                    return _lrb4;
                case LRB5:
                    if (_lrb5 == null) {
                        _lrb5 = new LRB(version);
                    }
                    return _lrb5;
                case LRB6:
                    if (_lrb6 == null) {
                        _lrb6 = new LRB(version);
                    }
                    return _lrb6;
                case CRP1:
                    if (_crp1 == null) {
                        _crp1 = new LRB(version);
                    }
                    return _crp1;
                case BB2016:
                    if (_naf2017 == null) {
                        _naf2017 = new LRB(version);
                    }
                    return _naf2017;
                case BB2020:
                    if (_bb2020 == null) {
                        _bb2020 = new LRB(version);
                    }
                    return _bb2020;
            }
        }
        return null;
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
    private static boolean _allowSpecialSkills = false;

    /**
     *
     */
    private LRB(E_Version version) {
        _rosterTypes = new ArrayList<>();
        _starPlayers = new ArrayList<>();
        _skillTypes = new ArrayList<>();
        _version = version;
        String path = "";
        switch (version) {
            case LRB1:
                path = "lrb1";
                break;
            case LRB2:
                path = "lrb2";
                break;
            case LRB3:
                path = "lrb3";
                break;
            case LRB4:
                path = "lrb4";
                break;
            case LRB5:
                path = "lrb5";
                break;
            case LRB6:
                path = "lrb6";
                break;
            case CRP1:
                path = "crp1";
                break;
            case BB2016:
                path = "BB2016";
                break;
            case BB2020:
                path = "BB2020";
                break;
        }

        /* URL url;
         url = getClass().getResource("/bb/teamma/rules/rules.xml");*/
        InputStream is = getClass().getResourceAsStream("/bb/teamma/rules/" + path + "/rules.xml");
        loadLRB(is, path);
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
    private final static String CS_Inducements = "inducements";
    private final static String CS_Inducement = "inducement";
    private final static String CS_Mercenaries = "mercenaries";
    private final static String CS_Babes = "babes";
    private final static String CS_Wizard = "wizard";
    private final static String CS_Cards = "cards";
    private final static String CS_Check_nb_big_guys = "check_nb_big_guys";

    private final static String CS_ChaosWizard = "chaos_wizard";
    private final static String CS_KariColdstell = "kari_coldstell";
    private final static String CS_FinkDaFixer = "fink_da_fixer";
    private final static String CS_PapaSkullbones = "papa_skullbones";
    private final static String CS_GalandrilSilverWater = "galandril_silverwater";
    private final static String CS_KrotShockwhisker = "krot_shockwhisker";
    private final static String CS_HoracioXSchottenheim = "horacio_x_schottenheim";

    /**
     *
     * @param file
     */
    private void loadLRB(InputStream file, String path) {
        String filename = "";
        try {
            SAXBuilder sxb = new SAXBuilder();
            org.jdom.Document document = sxb.build(file);
            Element racine = document.getRootElement();

            /*
             * Get LRB name
             */
            Element e_name = racine.getChild(CS_Name);
            setName(e_name.getValue());

            /**
             * Load inducements options
             */
            try {
                Element e_inducements = racine.getChild(CS_Inducements);

                Element e_starplayers = e_inducements.getChild(CS_Starplayers);
                _starplayers_enabled = e_starplayers.getText().equals("yes");

                try {
                    Element e_checkBG = racine.getChild(CS_Check_nb_big_guys);
                    _check_nb_big_guys = e_checkBG.getText().equals("yes");
                } catch (Exception e) {

                }

            } catch (Exception exception) {

            }

            /*
             * Get Skill file name
             */
            Element e_skillfile = racine.getChild(CS_Skills);
            String skillfile = e_skillfile.getValue();

            LOG.log(Level.FINE, "loading {0} file", skillfile);
            filename = "/bb/teamma/rules/" + path + "/" + skillfile;
            loadSkills(getClass().getResourceAsStream("/bb/teamma/rules/" + path + "/" + skillfile));

            Element e_teams = racine.getChild(CS_Teams);
            List<Element> l_teams = e_teams.getChildren(CS_Team);
            Iterator<Element> cr = l_teams.iterator();
            this.clearRosterTypes();

            while (cr.hasNext()) {
                Element e_team = cr.next();
                String teamfile = e_team.getValue();
                String imagename = e_team.getAttribute(CS_Picture).getValue();
                filename = "/bb/teamma/rules/" + path + "/" + teamfile;
                LOG.fine("Loading team: " + teamfile);
                loadTeam(getClass().getResourceAsStream("/bb/teamma/rules/" + path + "/" + teamfile), imagename);
            }
            /*
             * Get Star Players file name
             */
            Element e_starfile = racine.getChild(CS_Starplayers);
            String starfile = e_starfile.getValue();
            filename = "/bb/teamma/rules/" + path + "/" + starfile;
            loadStarPlayers(getClass().getResourceAsStream("/bb/teamma/rules/" + path + "/" + starfile));

        } catch (JDOMException | IOException jdomexception) {
            JOptionPane.showMessageDialog(null, "Loading " + filename + " " + jdomexception.getLocalizedMessage());
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
            org.jdom.Document document = sxb.build(file);
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
                    if (e_skill.getAttribute("random") != null) {
                        s.setRandom(e_skill.getAttribute("random").getBooleanValue());
                    }

                    if (e_skill.getAttribute("forbidden") != null) {
                        s.setForbiddenText(e_skill.getAttribute("forbidden").getValue());
                    }

                    st.addSkill(s);
                }
                addSkillType(st);
            }

            for (SkillType st : _skillTypes) {
                for (int i = 0; i < st.getSkillCount(); i++) {
                    Skill s = st.getSkill(i);
                    String text = s.getForbiddenText();
                    if (!text.equals("")) {
                        String[] separated = text.split(",");

                        for (String sep : separated) {
                            Skill f = this.getSkill(sep);
                            if (f != null) {
                                s.addForbiddenSkill(f);
                            }
                        }
                    }
                }
            }

        } catch (JDOMException | IOException jdomexception) {
            JOptionPane.showMessageDialog(null, "Skills: " + jdomexception.getLocalizedMessage());
        }
    }

    private final static String CS_Reroll = "reroll";
    private final static String CS_Apothecary = "apothecary";
    private final static String CS_MaxBigGuys = "max_big_guy";
    private final static String CS_Chef = "chef";
    private final static String CS_Igor = "igor";
    private final static String CS_Bribe = "bribe";
    private final static String CS_PlayerTypes = "playertypes";
    private final static String CS_PlayerType = "playerType";
    private final static String CS_Position = "position";
    private final static String CS_Limit = "limit";
    private final static String CS_Movement = "movement";
    private final static String CS_Pass = "pass";
    private final static String CS_Strength = "strength";
    private final static String CS_Agility = "agility";
    private final static String CS_Armor = "armor";
    private final static String CS_Cost = "cost";
    private final static String CS_Pair_With = "pair_with";
    private final static String CS_Max = "max";
    private final static String CS_Single = "single";
    private final static String CS_SkillType = "skillType";
    private final static String CS_Double = "double";

    private final static String CS_SkillNotFound = "SkillNotFound";
    private final static String CS_SkillTypeNotFound = "SkillTypeNotFound";
    private final static String CS_RosterTypeNotFound = "RosterTypeNotFound";
    private final static String CS_forThePlayer = "forThePlayer";

    protected Skill getSkill(String text) {

        for (SkillType st : _skillTypes) {
            for (int i = 0; i < st.getSkillCount(); i++) {
                Skill s = st.getSkill(i);

                if (s.getName().equals(text)) {
                    return s;
                }
            }
        }
        return null;
    }

    /**
     *
     * @param file
     * @param image
     */
    private void loadTeam(InputStream file, String image) {
        try {
            SAXBuilder sxb = new SAXBuilder();
            org.jdom.Document document = sxb.build(file);
            Element racine = document.getRootElement();

            Element e_name = racine.getChild(CS_Name);

            String n = Translate.translate(e_name.getValue());

            RosterType rt = new RosterType(n);
            rt.setImage(image);
            Element e_reroll_cost = racine.getChild(CS_Reroll);
            rt.setReroll_cost(Integer.parseInt(e_reroll_cost.getValue()));

            Element e_apo = racine.getChild(CS_Apothecary);
            rt.setApothecary(Boolean.parseBoolean(e_apo.getValue()));

            try {
                Element e_max_big_guy = racine.getChild(CS_MaxBigGuys);
                rt.setMaxBigGuys(Integer.parseInt(e_max_big_guy.getValue()));
            } catch (Exception e) {

            }

            Element e_inducements = racine.getChild(CS_Inducements);
            if (e_inducements != null) {
                List<Element> l_inducType = e_inducements.getChildren(CS_Inducement);
                Iterator<Element> cr = l_inducType.iterator();
                rt.clearInducementType();

                while (cr.hasNext()) {
                    InducementType it = new InducementType();
                    Element e_it = cr.next();
                    it.setName(e_it.getAttributeValue(CS_Name));
                    it.setNbMax(Integer.parseInt(e_it.getAttributeValue(CS_Max)));
                    it.setCost(Integer.parseInt(e_it.getAttributeValue(CS_Cost)));
                    rt.addInducementType(it);
                }
            }

            Element e_playerType = racine.getChild(CS_PlayerTypes);
            List<Element> l_players = e_playerType.getChildren(CS_PlayerType);
            Iterator<Element> cr = l_players.iterator();
            rt.clearPlayerType();

            while (cr.hasNext()) {
                Element e_player = cr.next();

                Element e_position = e_player.getChild(CS_Position);
                PlayerType pt = new PlayerType(e_position.getValue());
                LOG.finer("Loding player: " + pt.getPosition());
                Element e_limit = e_player.getChild(CS_Limit);
                pt.setLimit(Integer.parseInt(e_limit.getValue()));
                Element e_movement = e_player.getChild(CS_Movement);
                pt.setMovement(Integer.parseInt(e_movement.getValue()));
                try {
                    Element e_pass = e_player.getChild(CS_Pass);
                    pt.setPass(Integer.parseInt(e_pass.getValue()));
                } catch (Exception e) {
                    pt.setPass(-1);
                }
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
                    Skill s = getSkill(e_skill.getValue(), false);
                    if (s == null) {
                        JOptionPane.showMessageDialog(null, Translate.translate(CS_SkillNotFound) + ": " + e_skill.getValue() + " " + Translate.translate(CS_forThePlayer) + " " + pt.getPosition());
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
                        JOptionPane.showMessageDialog(null, Translate.translate(CS_SkillTypeNotFound) + ": " + e_skilltype.getValue() + " " + Translate.translate(CS_forThePlayer) + " " + pt.getPosition());
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
                        JOptionPane.showMessageDialog(null, Translate.translate(CS_SkillTypeNotFound) + ": " + e_skilltype.getValue() + " " + Translate.translate(CS_forThePlayer) + " " + pt.getPosition());
                    } else {
                        pt.addDouble(st);
                    }
                }
                rt.addPlayerType(pt);
            }
            rt.setVersion(this._version);
            addRosterType(rt);
        } catch (JDOMException | IOException jdomexception) {
            JOptionPane.showMessageDialog(null, "Team: from file " + file.toString() + " " + jdomexception.getLocalizedMessage());
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
            org.jdom.Document document = sxb.build(file);
            Element racine = document.getRootElement();

            clearStarPlayers();

            List<Element> stars = racine.getChildren(CS_Starplayer);
            Iterator<Element> j = stars.iterator();
            while (j.hasNext()) {
                Element e_star = j.next();

                Element e_name = e_star.getChild(CS_Name);
                StarPlayer sp = new StarPlayer(e_name.getValue());

                LOG.finer("Loading StarPlayer: " + sp.getName());

                sp.setPosition(e_star.getChild(CS_Position).getValue());
                sp.setMovement(Integer.parseInt(e_star.getChild(CS_Movement).getValue()));
                sp.setStrength(Integer.parseInt(e_star.getChild(CS_Strength).getValue()));
                sp.setAgility(Integer.parseInt(e_star.getChild(CS_Agility).getValue()));
                try {
                    sp.setPass(Integer.parseInt(e_star.getChild(CS_Pass).getValue()));
                } catch (NullPointerException npe) {
                    sp.setPass(-1);
                }
                sp.setArmor(Integer.parseInt(e_star.getChild(CS_Armor).getValue()));
                sp.setCost(Integer.parseInt(e_star.getChild(CS_Cost).getValue()));

                try {
                    sp.setPair_name(e_star.getChild(CS_Pair_With).getValue());
                } catch (Exception e) {

                }

                List<Element> skilllist = e_star.getChild(CS_Skills).getChildren(CS_Skill);
                Iterator<Element> i = skilllist.iterator();
                while (i.hasNext()) {
                    Element e_skill = i.next();
                    Skill s = getSkill(e_skill.getValue(), false);
                    if (s == null) {
                        JOptionPane.showMessageDialog(null, Translate.translate(CS_SkillNotFound) + ": " + e_skill.getValue() + " " + Translate.translate(CS_forThePlayer) + " " + sp.getName());
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
                        JOptionPane.showMessageDialog(null, Translate.translate(CS_RosterTypeNotFound) + ": " + n + " " + Translate.translate(CS_forThePlayer) + " " + e_name.getValue());
                    } else {
                        sp.addRoster(rt);
                        rt.addAvailableStarPlayer(sp);
                    }
                }

                addStarPlayer(sp);
            }

            /**
             * Resolve Pairs
             */
            for (StarPlayer sp : _starPlayers) {
                if (!sp.getPair_name().equals("")) {
                    for (StarPlayer s : _starPlayers) {
                        if (s != sp) {
                            if (s.getName().equals(sp.getPair_name())) {
                                sp.setPair(s);
                                break;
                            }
                        }
                    }
                }
            }

        } catch (JDOMException | IOException jdomexception) {
            JOptionPane.showMessageDialog(null, "Star players: " + jdomexception.getLocalizedMessage());
        }
    }

    /**
     *
     * @param name
     * @return
     */
    public Skill getSkill(String name, boolean translate) {
        int i, j;
        for (i = 0; i < getSkillTypeCount(); i++) {
            SkillType st = getSkillType(i);
            for (j = 0; j < st.getSkillCount(); j++) {
                Skill s = st.getSkill(j);
                if (!translate) {
                    if (name.equals(s.getName())) {
                        return s;
                    }
                } else {
                    if (name.equals(Translate.translate(s.getName()))) {
                        return s;
                    }
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
            if (rt != null) {
                if (rt.getName() != null) {
                    if (translate) {
                        String tr = Translate.translate(name);
                        if (rt.getName().equals(tr)) {
                            return rt;
                        }
                        tr = Translate.translate(rt.getName());
                        if (name.equals(tr)) {
                            return rt;
                        }
                    } else {
                        if (rt.getName().equals(name)) {
                            return rt;
                        }
                    }
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
        for (RosterType rt : _rosterTypes) {
            if (rt.getName().equals(name)) {
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
    public static boolean isAllowSpecialSkills() {
        return _allowSpecialSkills;
    }

    /**
     * @param allowSpecialSkills the _allowSpecialSkills to set
     */
    public static void setAllowSpecialSkills(boolean allowSpecialSkills) {
        _allowSpecialSkills = allowSpecialSkills;
    }

    /**
     * Unload the LRB
     */
    public static void unloadLRB() {
        LRB._lrb4 = null;
        LRB._lrb5 = null;
        LRB._lrb6 = null;
        LRB._naf2017 = null;
    }

}
