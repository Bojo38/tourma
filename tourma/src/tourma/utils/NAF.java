/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import tourma.data.Coach;
import tourma.data.Tournament;

/**
 *
 * @author Perso
 */
public class NAF {

    public static double GetRanking(String Name, Coach coach) {
        double naf = 150;
        try {
            URL url = new URL("http://member.thenaf.net/index.php?module=NAF&type=coachpage&coach=" + Name);
            InputStream is = url.openConnection().getInputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(is));

            ArrayList<String> rosters = new ArrayList<String>();
            ArrayList<Double> ranks = new ArrayList<Double>();

            String line = null;
            while ((line = reader.readLine()) != null) {
                if (line.contains("pn-maincontent")) {


                    int pos = line.indexOf("<table bgcolor=\"#858390\" cellpadding=\"2\" cellspacing=\"1\" border=\"0\"><tr><th bgcolor=\"#D9D8D0\" colspan=\"7\">Rankings</th></tr>");
                    String buffer = line.substring(pos);
                    pos = buffer.indexOf("</table>");
                    buffer = buffer.substring(0, pos + 8);


                    System.out.println(buffer);
                    final SAXBuilder sxb = new SAXBuilder();

                    pos = buffer.indexOf("coach=");
                    String subBuff = buffer.substring(pos + 6);
                    pos = subBuff.indexOf("&amp;");
                    subBuff = subBuff.substring(0, pos);

                    coach.mNaf = Integer.parseInt(subBuff);

                    try {
                        StringReader Sreader = new StringReader(buffer);
                        final org.jdom.Document document = sxb.build(Sreader);
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
                                        ranks.add(new Double(rank));
                                    } else {
                                        break;
                                    }
                                }
                                index++;
                            }

                            if (roster.equals(Tournament.getRosterTranslation(Name))) {
                                naf = rank;
                                coach.mNafRank = naf;
                                break;
                            }
                        }
                    } catch (Exception e) {
                    }
                }
            }

            for (int i = 0; i < rosters.size(); i++) {
                Name = Tournament.getRosterTranslation(coach.mRoster.mName);
                String name2=rosters.get(i);
                if (name2.equals(Name)) {
                    naf = ranks.get(i);
                    coach.mNafRank = naf;
                    break;
                }
            }
            reader.close();
        } catch (Exception exc) {
            if (exc instanceof MalformedURLException) {
            }
        }
        return naf;
    }
}
