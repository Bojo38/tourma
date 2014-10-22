/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package teamma.data;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import tourma.MainFrame;
import tourma.utility.StringConstants;

/**
 *
 * @author WFMJ7631
 */
public class lrb {

    public ArrayList<RosterType> _rosterTypes = null;
    public ArrayList<StarPlayer> _starPlayers = null;
    public ArrayList<SkillType> _skillTypes = null;
    private static lrb _singleton = null;
    public String _name;
    public boolean _allowSpecialSkills=false;

    private lrb() {
        _rosterTypes = new ArrayList<RosterType>();
        _starPlayers = new ArrayList<StarPlayer>();
        _skillTypes = new ArrayList<SkillType>();

        URL url = getClass().getResource("/teamma/rules/rules.xml");
        loadLRB(getClass().getResourceAsStream("/teamma/rules/rules.xml"));
    }

    public static lrb getLRB() {
        if (_singleton == null) {
            _singleton = new lrb();
        }
        return _singleton;
    }

    private void loadLRB(InputStream file) {
        try {
            SAXBuilder sxb = new SAXBuilder();
            org.jdom2.Document document = sxb.build(file);
            Element racine = document.getRootElement();

            /*
             * Get LRB name
             */
            Element e_name = racine.getChild("name");
            _name = e_name.getValue();

            /*
             * Get Skill file name
             */
            Element e_skillfile = racine.getChild("skills");
            String skillfile = e_skillfile.getValue();

            System.out.println("loading " + skillfile + " file");
            loadSkills(getClass().getResourceAsStream("/teamma/rules/" + skillfile));

            Element e_teams = racine.getChild("teams");
            List l_teams = e_teams.getChildren("team");
            Iterator cr = l_teams.iterator();
            _rosterTypes.clear();

            while (cr.hasNext()) {
                Element e_team = (Element) cr.next();
                String teamfile = e_team.getValue();
                String imagename=e_team.getAttribute("image").getValue();
                System.out.println("loading " + teamfile + " file");
                loadTeam(getClass().getResourceAsStream("/teamma/rules/" + teamfile),imagename);
            }
            /*
             * Get Star Players file name
             */
            Element e_starfile = racine.getChild("starplayers");
            String starfile = e_starfile.getValue();
            System.out.println("loading " + starfile + " file");
            loadStarPlayers(getClass().getResourceAsStream("/teamma/rules/" + starfile));


        } catch (JDOMException jdomexception) {
            JOptionPane.showMessageDialog(MainFrame.getMainFrame(), jdomexception.getLocalizedMessage());
        } catch (IOException ioexception) {
            JOptionPane.showMessageDialog(MainFrame.getMainFrame(), ioexception.getLocalizedMessage());
        }
    }

    private void loadSkills(InputStream file) {
        try {
            SAXBuilder sxb = new SAXBuilder();
            org.jdom2.Document document = sxb.build(file);
            Element racine = document.getRootElement();

            List l_categories = racine.getChildren("category");
            Iterator cr = l_categories.iterator();
            _skillTypes.clear();

            while (cr.hasNext()) {
                Element e_skillType = (Element) cr.next();

                Element e_name = e_skillType.getChild("name");
                String st_name = e_name.getValue();

                Element e_accro = e_skillType.getChild("accronym");
                String st_accro = e_accro.getValue();

                
                SkillType st = new SkillType(st_name, st_accro);

                Element e_special = e_skillType.getChild("special");
                st._special=Boolean.parseBoolean(e_special.getValue());
                List l_skills = e_skillType.getChildren("skill");
                Iterator i = l_skills.iterator();

                while (i.hasNext()) {
                    Element e_skill = (Element) i.next();
                    Skill s = new Skill(e_skill.getValue(), st);
                    st._skills.add(s);
                }
                _skillTypes.add(st);
            }


        } catch (JDOMException jdomexception) {
            JOptionPane.showMessageDialog(MainFrame.getMainFrame(), jdomexception.getLocalizedMessage());
        } catch (IOException ioexception) {
            JOptionPane.showMessageDialog(MainFrame.getMainFrame(), ioexception.getLocalizedMessage());
        }
    }

    private void loadTeam(InputStream file,String image) {
        try {
            SAXBuilder sxb = new SAXBuilder();
            org.jdom2.Document document = sxb.build(file);
            Element racine = document.getRootElement();

            Element e_name = racine.getChild("name");
            RosterType rt = new RosterType(java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString( e_name.getValue()));
            rt._image=image;
            Element e_reroll_cost = racine.getChild("reroll");
            rt._reroll_cost = Integer.parseInt(e_reroll_cost.getValue());

            Element e_apo = racine.getChild("apothecary");
            rt._apothecary = Boolean.parseBoolean(e_apo.getValue());

            Element e_chef_cost = racine.getChild("chef");
            rt._chef_cost = Integer.parseInt(e_chef_cost.getValue());

            Element e_igor = racine.getChild("igor");
            rt._igor = Boolean.parseBoolean(e_igor.getValue());

            Element e_bribe_cost = racine.getChild("bribe");
            rt._bribe_cost = Integer.parseInt(e_bribe_cost.getValue());

            Element e_playerType = racine.getChild("playertypes");
            List l_players = e_playerType.getChildren("playerType");
            Iterator cr = l_players.iterator();
            rt._player_types.clear();

            while (cr.hasNext()) {
                Element e_player = (Element) cr.next();

                Element e_position = e_player.getChild("position");
                PlayerType pt = new PlayerType(e_position.getValue());
                Element e_limit = e_player.getChild("limit");
                pt._limit = Integer.parseInt(e_limit.getValue());
                Element e_movement = e_player.getChild("movement");
                pt._movement = Integer.parseInt(e_movement.getValue());
                Element e_strength = e_player.getChild("strength");
                pt._strength = Integer.parseInt(e_strength.getValue());
                Element e_agility = e_player.getChild("agility");
                pt._agility = Integer.parseInt(e_agility.getValue());
                Element e_armor = e_player.getChild("armor");
                pt._armor = Integer.parseInt(e_armor.getValue());
                Element e_cost = e_player.getChild("cost");
                pt._cost = Integer.parseInt(e_cost.getValue());

                Element e_skills = e_player.getChild("skills");
                List l_skills = e_skills.getChildren("skill");
                Iterator i = l_skills.iterator();
                while (i.hasNext()) {
                    Element e_skill = (Element) i.next();
                    Skill s = getSkill(e_skill.getValue());
                    if (s == null) {
                        JOptionPane.showMessageDialog(MainFrame.getMainFrame(), "Skill not found: " + e_skill.getValue() + " for player " + pt._position);
                    } else {
                        pt._skills.add(s);
                    }
                }

                Element e_single = e_player.getChild("single");
                List l_singleskilltypes = e_single.getChildren("skillType");
                Iterator j = l_singleskilltypes.iterator();
                while (j.hasNext()) {
                    Element e_skilltype = (Element) j.next();
                    SkillType st = getSkillType(e_skilltype.getValue());
                    if (st == null) {
                        JOptionPane.showMessageDialog(MainFrame.getMainFrame(), "Skill Type not found: " + e_skilltype.getValue() + " for player " + pt._position);
                    } else {
                        pt._single.add(st);
                    }
                }

                Element e_double = e_player.getChild("double");
                List l_doubleskilltypes = e_double.getChildren("skillType");
                j = l_doubleskilltypes.iterator();
                while (j.hasNext()) {
                    Element e_skilltype = (Element) j.next();
                    SkillType st = getSkillType(e_skilltype.getValue());
                    if (st == null) {
                        JOptionPane.showMessageDialog(MainFrame.getMainFrame(), "Skill Type not found: " + e_skilltype.getValue() + " for player " + pt._position);
                    } else {
                        pt._double.add(st);
                    }
                }
                rt._player_types.add(pt);
            }

            _rosterTypes.add(rt);
        } catch (JDOMException jdomexception) {
            JOptionPane.showMessageDialog(MainFrame.getMainFrame(), jdomexception.getLocalizedMessage());
        } catch (IOException ioexception) {
            JOptionPane.showMessageDialog(MainFrame.getMainFrame(), ioexception.getLocalizedMessage());
        }
    }

    private void loadStarPlayers(InputStream file) {
        try {
            SAXBuilder sxb = new SAXBuilder();
            org.jdom2.Document document = sxb.build(file);
            Element racine = document.getRootElement();

            _starPlayers.clear();

            List stars = racine.getChildren("starplayer");
            Iterator j = stars.iterator();
            while (j.hasNext()) {
                Element e_star = (Element) j.next();

                Element e_name = e_star.getChild("name");
                StarPlayer sp = new StarPlayer(e_name.getValue());

                sp._position = e_star.getChild("position").getValue();
                sp._movement = Integer.parseInt(e_star.getChild("movement").getValue());
                sp._strength = Integer.parseInt(e_star.getChild("strength").getValue());
                sp._agility = Integer.parseInt(e_star.getChild("agility").getValue());
                sp._armor = Integer.parseInt(e_star.getChild("armor").getValue());
                sp._cost = Integer.parseInt(e_star.getChild("cost").getValue());

                List skilllist = e_star.getChild("skills").getChildren("skill");
                Iterator i = skilllist.iterator();
                while (i.hasNext()) {
                    Element e_skill = (Element) i.next();
                    Skill s = getSkill(e_skill.getValue());
                    if (s == null) {
                        JOptionPane.showMessageDialog(MainFrame.getMainFrame(), "Skill not found: " + e_skill.getValue() + " for player " + sp._name);
                    } else {
                        sp._skills.add(s);
                    }
                }

                List rosterlist = e_star.getChildren("team");
                i = rosterlist.iterator();
                while (i.hasNext()) {
                    Element e_team = (Element) i.next();
                    RosterType rt = getRosterType( java.util.ResourceBundle.getBundle(StringConstants.CS_LANGUAGE_RESOURCE).getString(e_team.getValue()));
                    if (rt == null) {
                        JOptionPane.showMessageDialog(MainFrame.getMainFrame(), "RosterType not found: " + e_team.getValue() + " for player " + sp._name);
                    } else {
                        sp._rosters.add(rt);
                        rt._available_starplayers.add(sp);
                    }
                }
                
                this._starPlayers.add(sp);

            }

        } catch (JDOMException jdomexception) {
            JOptionPane.showMessageDialog(MainFrame.getMainFrame(), jdomexception.getLocalizedMessage());
        } catch (IOException ioexception) {
            JOptionPane.showMessageDialog(MainFrame.getMainFrame(), ioexception.getLocalizedMessage());
        }
    }

    public Skill getSkill(String name) {
        int i, j;
        for (i = 0; i < _skillTypes.size(); i++) {
            SkillType st = _skillTypes.get(i);
            for (j = 0; j < st._skills.size(); j++) {
                Skill s = st._skills.get(j);
                if (name.equals(s.mName)) {
                    return s;
                }
            }
        }
        return null;
    }

    public SkillType getSkillType(String name) {
        int i;
        for (i = 0; i < _skillTypes.size(); i++) {
            SkillType st = _skillTypes.get(i);

            if (name.equals(st._name)) {
                return st;
            }

        }
        return null;
    }

    public RosterType getRosterType(String name) {
        int i;
        for (i = 0; i < _rosterTypes.size(); i++) {
            RosterType rt = _rosterTypes.get(i);

            if (name.equals(rt._name)) {
                return rt;
            }
        }
        return null;
    }
    
     public StarPlayer getStarPlayer(String name) {
        int i;
        for (i = 0; i < _starPlayers.size(); i++) {
            StarPlayer rt = _starPlayers.get(i);

            if (name.equals(rt._name)) {
                return rt;
            }
        }
        return null;
    }
    
    public ArrayList<String> getRosterTypeListAsString()
    {
        int i;
        ArrayList<String> res=new ArrayList<String>();
        for (i=0; i<_rosterTypes.size(); i++)
        {
            res.add(_rosterTypes.get(i)._name);
        }
        
        return res;
    }
    
    
}
