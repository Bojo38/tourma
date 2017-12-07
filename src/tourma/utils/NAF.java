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
import javax.swing.text.AbstractDocument.Content;
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
            URL url = new URL("https://member.thenaf.net/index.php?module=NAF&type=tournamentinfo&uname=" + Name);
            is = url.openConnection().getInputStream();
            isr = new InputStreamReader(is, Charset.defaultCharset());
            reader = new BufferedReader(isr);

            ArrayList<String> rosters = new ArrayList<>();
            ArrayList<Double> ranks = new ArrayList<>();

            String line = reader.readLine();
            while (line != null) {
                if (line.contains("pn-maincontent")) {

                    String toFind = "Tournament info for ";
                    int pos = line.indexOf(toFind);
                    if (pos >= 0) {
                        String buffer = line.substring(pos);
                        // pos = buffer.indexOf("</table>");
                        buffer = buffer.substring(toFind.length());
                        buffer = buffer.substring(Name.length() + 2);
                        pos = buffer.indexOf(")");
                        buffer = buffer.substring(0, pos);
                        coach.setNaf(Integer.parseInt(buffer));

                        final SAXBuilder sxb = new SAXBuilder();
                        int end_table = line.indexOf("</table>");
                        if (end_table != -1) {
                            buffer = line.substring(end_table);
                            int posRV_begin = buffer.indexOf("<table");
                            buffer = buffer.substring(posRV_begin);
                            int posRV_end = buffer.indexOf("</table>");

                            String new_line = "";

                            while ((posRV_end == -1) && (new_line != null)) {
                                new_line = reader.readLine();
                                buffer = buffer + new_line;
                                posRV_end = buffer.indexOf("</table>");
                            }

                            buffer = buffer.substring(0, posRV_end + 8);
                            buffer = buffer.replace("&nbsp;", "");
//                        System.out.println(buffer);
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
                                            roster = td.getText();
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
                                System.err.println(e.getLocalizedMessage());
                            }
                        }
                    } else {
                        System.err.println("NAF indication not found for " + Name);
                    }
                }
                line = reader.readLine();
            }

            double moy = 0;
            for (int i = 0; i < rosters.size() - 1; i++) {
                String name = coach.getRoster().getName();
                String tmpName = RosterType.getRosterTranslation(name);
                String name2 = rosters.get(i);
                moy += naf = ranks.get(i);
                if (name2.equals(tmpName)) {
                    naf = ranks.get(i);
                    coach.setNafRank(naf);
                }
            }

            if (moy == 0) {
                moy = 150.0;
            } else {
                moy = moy / ranks.size();
            }
            coach.setNafAvg(moy);

        } catch (IOException | NumberFormatException exc) {
            LOG.log(Level.INFO, exc.getLocalizedMessage());
            System.err.println(exc.getLocalizedMessage());
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
