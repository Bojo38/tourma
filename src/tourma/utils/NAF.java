/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import tourma.data.Coach;
import tourma.data.RosterType;

/**
 *
 * @author Perso
 */
public final class NAF {

    private static final Logger LOG = Logger.getLogger(NAF.class.getName());

    /**
     *
     * @param Name
     * @param coach
     * @return
     */
    public static double getRanking(String Name, Coach coach) {
        double naf = 150;
        InputStream is = null;
        InputStreamReader isr = null;
        BufferedReader reader = null;
        try {                               
            URL url = new URL("https://member.thenaf.net/index.php?module=NAF&type=coachpage&coach=" + Name);
            is = url.openConnection().getInputStream();
            isr = new InputStreamReader(is, Charset.defaultCharset());
            reader = new BufferedReader(isr);

            ArrayList<String> rosters = new ArrayList<>();
            ArrayList<Double> ranks = new ArrayList<>();

            String line = reader.readLine();
            while (line != null) {
                if (line.contains("pn-maincontent")) {

                    int pos = line.indexOf("<table bgcolor=\"#858390\" cellpadding=\"2\" cellspacing=\"1\" border=\"0\"><tr><th bgcolor=\"#D9D8D0\" colspan=\"7\">Rankings</th></tr>");
                    if (pos >= 0) {
                        String buffer = line.substring(pos);
                        pos = buffer.indexOf("</table>");
                        buffer = buffer.substring(0, pos + 8);

                        final SAXBuilder sxb = new SAXBuilder();

                        pos = buffer.indexOf("coach=");
                        String subBuff = buffer.substring(pos + 6);
                        pos = subBuff.indexOf("&amp;");
                        subBuff = subBuff.substring(0, pos);

                        coach.setNaf(Integer.parseInt(subBuff));

                        try {
                            StringReader Sreader = new StringReader(buffer);
                            final org.jdom2.Document document = sxb.build(Sreader);
                            final Element racine = document.getRootElement();

                            List trs = racine.getChildren("tr");
                            final Iterator i = trs.iterator();
                            String roster = "";
                            double rank = 150;

                            while (i.hasNext()) {
                                final Element tr = (Element) i.next();
                                List tds = tr.getChildren("td");
                                final Iterator j = tds.iterator();
                                int index = 0;

                                while (j.hasNext()) {
                                    final Element td = (Element) j.next();
                                    if (index == 0) {
                                        // Roster
                                        Element a = td.getChild("a");
                                        roster = a.getText();
                                        rosters.add(roster);
                                    } else {
                                        if (index == 1) {
                                            // Roster
                                            rank = Double.parseDouble(td.getText());
                                            ranks.add(rank);
                                        } else {
                                            break;
                                        }
                                    }
                                    index++;
                                }
                                                                

                                if (roster.equals(RosterType.getRosterTranslation(Name))) {
                                    naf = rank;
                                    coach.setNafRank(naf);
                                    break;
                                }
                            }
                        } catch (JDOMException | IOException | NumberFormatException e) {
                            LOG.log(Level.FINE, e.getLocalizedMessage());
                        }
                    }
                }
                line = reader.readLine();
            }

            double moy=0;
            for (int i = 0; i < rosters.size(); i++) {
                String name=coach.getRoster().getName();
                String tmpName = RosterType.getRosterTranslation(coach.getRoster().getName());
                String name2 = rosters.get(i);
                moy+=naf = ranks.get(i);
                if (name2.equals(tmpName)) {
                    naf = ranks.get(i);
                    coach.setNafRank(naf);
                }
            }
            
            if (moy==0)
            {
                moy=150.0;
            }
            else
            {
                moy=moy/ranks.size();
            }
            coach.setNafAvg(moy);

        } catch (IOException | NumberFormatException exc) {
            LOG.log(Level.INFO, exc.getLocalizedMessage());
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {

                }
            }
            if (isr != null) {
                try {
                    isr.close();
                } catch (IOException e) {

                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {

                }
            }
        }
        return naf;
    }

    private NAF() {
    }
}
