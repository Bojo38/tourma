/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Vector;
import javax.swing.JOptionPane;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

/**
 *
 * @author Frederic Berger
 */
public class Tournament {

    protected Vector<Round> _rounds;
    protected Vector<Coach> _coachs;
    protected Parameters _params;
    protected static Tournament _singleton;

    private Tournament() {
        _params = new Parameters();
        _rounds = new Vector<Round>();
        _coachs = new Vector<Coach>();
    }

    public static Tournament getTournament() {
        if (_singleton == null) {
            _singleton = new Tournament();
        }
        return _singleton;
    }

    public void saveXML(java.io.File file) {
        Element document = new Element("Tournament");
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:MM:SS");

        Element params = new Element("Parameters");
        params.setAttribute("Organizer", _params._tournament_orga);
        params.setAttribute("Name", _params._tournament_name);
        params.setAttribute("Bonus_Neg_Foul", Integer.toString(_params._bonus_neg_foul_points));
        params.setAttribute("Bonus_Pos_Foul", Integer.toString(_params._bonus_foul_points));
        params.setAttribute("Bonus_Neg_Td", Integer.toString(_params._bonus_neg_td_points));
        params.setAttribute("Bonus_Pos_Td", Integer.toString(_params._bonus_td_points));
        params.setAttribute("Bonus_Neg_Sor", Integer.toString(_params._bonus_neg_sor_points));
        params.setAttribute("Bonus_Pos_Sor", Integer.toString(_params._bonus_sor_points));

        params.setAttribute("Victory", Integer.toString(_params._victory_points));
        params.setAttribute("Large_Victory", Integer.toString(_params._large_victory_points));
        params.setAttribute("Draw", Integer.toString(_params._draw_points));
        params.setAttribute("Lost", Integer.toString(_params._lost_points));
        params.setAttribute("Little_Lost", Integer.toString(_params._little_lost_points));

        params.setAttribute("Rank1", Integer.toString(_params._ranking1));
        params.setAttribute("Rank2", Integer.toString(_params._ranking2));
        params.setAttribute("Rank3", Integer.toString(_params._ranking3));
        params.setAttribute("Rank4", Integer.toString(_params._ranking4));
        params.setAttribute("Rank5", Integer.toString(_params._ranking5));

        document.addContent(params);

        for (int i = 0; i < _coachs.size(); i++) {
            Element coach = new Element("Coach");
            coach.setAttribute("Name", _coachs.get(i)._name);
            coach.setAttribute("Team", _coachs.get(i)._team);
            coach.setAttribute("Roster", _coachs.get(i)._roster);
            coach.setAttribute("NAF", Integer.toString(_coachs.get(i)._naf));
            document.addContent(coach);
        }


        for (int i = 0; i < _rounds.size(); i++) {
            Element round = new Element("Round");
            round.setAttribute("Date", format.format(_rounds.get(i)._heure));

            for (int j = 0; j < _rounds.get(i)._matchs.size(); j++) {
                Element match = new Element("Match");
                match.setAttribute("Coach1", _rounds.get(i)._matchs.get(j)._coach1._name);
                match.setAttribute("Coach2", _rounds.get(i)._matchs.get(j)._coach2._name);
                match.setAttribute("Td1", Integer.toString(_rounds.get(i)._matchs.get(j)._td1));
                match.setAttribute("Td2", Integer.toString(_rounds.get(i)._matchs.get(j)._td2));
                match.setAttribute("Sor1", Integer.toString(_rounds.get(i)._matchs.get(j)._sor1));
                match.setAttribute("Sor2", Integer.toString(_rounds.get(i)._matchs.get(j)._sor2));
                match.setAttribute("Foul1", Integer.toString(_rounds.get(i)._matchs.get(j)._foul1));
                match.setAttribute("Foul2", Integer.toString(_rounds.get(i)._matchs.get(j)._foul2));
                round.addContent(match);
            }
            document.addContent(round);
        }

        try {
            XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
            sortie.output(document, new FileOutputStream(file));
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(MainFrame.getMainFrame(), e.getMessage());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(MainFrame.getMainFrame(), e.getMessage());
        }
    }

    public void loadXML(java.io.File file) {
        SAXBuilder sxb = new SAXBuilder();
        try {
            org.jdom.Document document = sxb.build(file);
            Element racine = document.getRootElement();

            /* Utilisateur */
            Element params = racine.getChild("Parameters");
            if (params != null) {
                _params._tournament_orga = params.getAttribute("Organizer").getValue();
                _params._tournament_name = params.getAttribute("Name").getValue();
                _params._bonus_foul_points = params.getAttribute("Bonus_Pos_Foul").getIntValue();
                _params._bonus_neg_foul_points = params.getAttribute("Bonus_Neg_Foul").getIntValue();
                _params._bonus_td_points = params.getAttribute("Bonus_Pos_Td").getIntValue();
                _params._bonus_neg_td_points = params.getAttribute("Bonus_Neg_Td").getIntValue();
                _params._bonus_sor_points = params.getAttribute("Bonus_Pos_Sor").getIntValue();
                _params._bonus_neg_sor_points = params.getAttribute("Bonus_Neg_Sor").getIntValue();
                _params._victory_points = params.getAttribute("Victory").getIntValue();
                _params._large_victory_points = params.getAttribute("Large_Victory").getIntValue();
                _params._draw_points = params.getAttribute("Draw").getIntValue();
                _params._lost_points = params.getAttribute("Lost").getIntValue();
                _params._little_lost_points = params.getAttribute("Little_Lost").getIntValue();
                _params._ranking1 = params.getAttribute("Rank1").getIntValue();
                _params._ranking2 = params.getAttribute("Rank2").getIntValue();
                _params._ranking3 = params.getAttribute("Rank3").getIntValue();
                _params._ranking4 = params.getAttribute("Rank4").getIntValue();
                _params._ranking5 = params.getAttribute("Rank5").getIntValue();
            }

            List coachs = racine.getChildren("Coach");
            Iterator i = coachs.iterator();
            _coachs.clear();
            while (i.hasNext()) {
                Element coach = (Element) i.next();
                Coach c = new Coach();
                c._name = coach.getAttributeValue("Name");
                c._team = coach.getAttributeValue("Team");
                c._roster = coach.getAttributeValue("Roster");
                c._naf = coach.getAttribute("NAF").getIntValue();
                _coachs.add(c);
            }

            List rounds = racine.getChildren("Round");
            Iterator j = rounds.iterator();
            _rounds.clear();
            while (j.hasNext()) {
                Element round = (Element) j.next();
                Round r = new Round();
                String date = round.getAttributeValue("Date");
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:MM:SS");
                try {
                    r._heure = format.parse(date);
                } catch (ParseException e) {
                }

                List matchs = round.getChildren("Match");
                Iterator k = matchs.iterator();
                r._matchs.clear();
                while (k.hasNext()) {
                    Element match = (Element) k.next();
                    Match m = new Match();
                    String c1 = match.getAttribute("Coach1").getValue();
                    String c2 = match.getAttribute("Coach2").getValue();
                    for (int cpt = 0; cpt < _coachs.size(); cpt++) {
                        if (c1.equals(_coachs.get(cpt)._name)) {
                            m._coach1 = _coachs.get(cpt);
                            break;
                        }
                    }
                    for (int cpt = 0; cpt < _coachs.size(); cpt++) {
                        if (c2.equals(_coachs.get(cpt)._name)) {
                            m._coach2 = _coachs.get(cpt);
                            break;
                        }
                    }
                    m._foul1=match.getAttribute("Foul1").getIntValue();
                    m._foul2=match.getAttribute("Foul2").getIntValue();
                    m._sor1=match.getAttribute("Sor1").getIntValue();
                    m._sor2=match.getAttribute("Sor2").getIntValue();
                    m._td1=match.getAttribute("Td1").getIntValue();
                    m._td2=match.getAttribute("Td2").getIntValue();
                    r._matchs.add(m);
                }

                _rounds.add(r);
            }

        } catch (JDOMException e) {
            JOptionPane.showMessageDialog(MainFrame.getMainFrame(), e.getLocalizedMessage());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(MainFrame.getMainFrame(), e.getMessage());
        }
    }

    public void generateFirstRound() {
        _rounds.clear();
        Round r = new Round();
        Calendar cal = Calendar.getInstance();
        r._heure = cal.getTime();
        Vector<Coach> shuffle = new Vector<Coach>(_coachs);

        Collections.shuffle(shuffle);

        for (int i = 0; i < shuffle.size() / 2; i++) {
            Match m = new Match();
            m._coach1 = shuffle.get(2 * i);
            m._coach2 = shuffle.get(2 * i + 1);
            r._matchs.add(m);
        }
        _rounds.add(r);
    }
}
