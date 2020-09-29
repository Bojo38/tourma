/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.jdom.Element;
import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import tourma.MainFrame;
import tourma.data.Coach;
import tourma.data.RosterType;

/**
 *
 * @author Perso
 */
public final class NAF {

    protected static ArrayList<NAFCoach> coachs = new ArrayList<>();
    protected static boolean _sIgnoreCaps = true;

    public static void setIgnoreCaps(boolean b) {
        _sIgnoreCaps = b;
    }

    public static ArrayList<NAFCoach> getCoachs() {
        return coachs;
    }

    public static NAFCoach getCoachByName(String name) {
        NAFCoach c = null;
        for (NAFCoach tmp : coachs) {
            if (tmp.getName().equals(name)) {
                c = tmp;
                break;
            } else {
                if (_sIgnoreCaps) {
                    if (tmp.getName().toLowerCase().equals(name.toLowerCase())) {
                        c = tmp;
                        break;
                    }
                }
            }
        }
        return c;
    }

    public static NAFCoach getCoachById(int id) {
        NAFCoach c = null;
        for (NAFCoach tmp : coachs) {
            if (tmp.getId() == id) {
                c = tmp;
                break;
            }
        }
        return c;
    }

    private static NAF _singleton = new NAF();

    public static ArrayList<File> getFileList() {
        boolean stop = false;
        ArrayList<File> list = new ArrayList<>();
        int i = 1;
        try {
            while (!stop) {
                URL url = _singleton.getClass().getResource("/tourma/naf/naf_id" + i + ".xml");
                if (url == null) {
                    stop = true;
                    break;
                }
                //System.out.println("Open " + url.getPath());
                if (url.getPath().contains("jar!")) {
                    try {
                        File tempFile;
                        OutputStream out;
                        // Was the resource found?
                        try ( // Read the file we're looking for
                                InputStream fileStream = _singleton.getClass().getResourceAsStream("/tourma/naf/naf_id" + i + ".xml")) {
                            // Was the resource found?
                            if (fileStream == null) {
                                stop = true;
                            }   // Grab the file name
                            String[] chopped = url.getPath().split("\\/");
                            String fileName = chopped[chopped.length - 1];
                            // Create our temp file (first param is just random bits)
                            tempFile = File.createTempFile("asdf", fileName);
                            // Set this file to be deleted on VM exit
                            tempFile.deleteOnExit();
                            // Create an output stream to barf to the temp file
                            out = new FileOutputStream(tempFile);
                            // Write the file to the temp file
                            byte[] buffer = new byte[1024];
                            int len = fileStream.read(buffer);
                            while (len != -1) {
                                out.write(buffer, 0, len);
                                len = fileStream.read(buffer);
                            }
                            // Close the streams
                        }
                        out.close();

                        // Store this file in the cache list
                        list.add(tempFile);

                    } catch (IOException e) {
                        stop = true;
                    }
                } else {
                    File file = new File(url.toURI());

                    if (file.exists()) {
                        list.add(file);
                    } else {
                        stop = true;
                    }
                }
                i++;
            }
        } catch (URISyntaxException e) {
            System.err.println(e.getLocalizedMessage());
        }
        return list;
    }

    public static void initCoachs(File file) {
        Reader fileReader = null;

        try {
           
            InputStream is = new FileInputStream(file);
            if (null != is) {
                fileReader = new InputStreamReader(is);

                final SAXBuilder sxb = new SAXBuilder();

                try {
                    final Document document = sxb.build(fileReader);
                    final Element racine = document.getRootElement();

                    try {
                        final List<Element> xcoachs = racine.getChildren("coach");
                        final Iterator<Element> xcoach_it = xcoachs.iterator();

                        while (xcoach_it.hasNext()) {
                            final Element xcoach = xcoach_it.next();
                            final NAFCoach coach = new NAFCoach();
                            coach.setId(xcoach.getAttribute("id").getIntValue());
                            coach.setName(xcoach.getAttribute("name").getValue());
                            coachs.add(coach);
                        }

                        fileReader.close();
                    } catch (NullPointerException npe) {
                    }

                } catch (JDOMException e) {
                    JOptionPane.showMessageDialog(null, e.getLocalizedMessage() +" Reading file: "+ file.getAbsolutePath());

                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage()+" Reading file: "+ file.getAbsolutePath());
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println(e.getLocalizedMessage());
        }
    }

    private static final Logger LOG = Logger.getLogger(NAF.class.getName());

    public static double getRanking(int id, Coach coach) {
        if (netIsAvailable()) {
            try {
                URL url = new URL("https://member.thenaf.net/index.php?module=NAF&type=tournamentinfo&uid=" + id);
                return getRanking(url, coach);
            } catch (MalformedURLException e) {

            }
        }
        return 0.0;
    }

    public static double getRanking(String Name, Coach coach) {
        if (netIsAvailable()) {
            try {
                String tmp = Name;
                if (Name.contains(" ")) {
                    Name = "\"" + Name + "\"";
                }
                URL url = new URL("https://member.thenaf.net/index.php?module=NAF&type=tournamentinfo&uname=" + Name);
                return getRanking(url, coach);
            } catch (MalformedURLException e) {

            }
        }
        return 0.0;
    }

    /**
     *
     * @param Name
     * @param coach
     * @return
     */
    public static double getRanking(URL url, Coach coach) {
        double naf = 150;
        InputStream is = null;
        InputStreamReader isr = null;
        BufferedReader reader = null;
        try {

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
                        int parenthesis = buffer.indexOf("(");
                        String name = buffer.substring(0, parenthesis - 1);
                        coach.setName(name);
                        buffer = buffer.substring(parenthesis + 1);
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
                                        if (!td.getText().contains("/")) {
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
                                        }
                                        index++;
                                    }

                                    if (coach.getRoster() != null) {
                                        if (roster.equals(RosterType.getRosterTranslation(coach.getRoster().getName()))) {
                                            naf = rank;
                                            coach.setNafRank(naf);
                                            break;
                                        }
                                    }
                                }
                            } catch (JDOMException | IOException | NumberFormatException e) {
                                LOG.log(Level.FINE, e.getLocalizedMessage());
                                System.err.println(e.getLocalizedMessage());
                            }
                        }
                    } else {
                        System.err.println("NAF indication not found");
                    }
                }
                line = reader.readLine();
            }

            double moy = 0;
            if (coach.getRoster() != null) {
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

    private static boolean netIsAvailable() {
        try {
            final URL url = new URL("http://www.google.com");
            final URLConnection conn = url.openConnection();
            conn.connect();
            return true;
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            return false;
        }
    }

    public static void updateCoachID(Coach coach) {
        for (NAFCoach c : coachs) {
            if (c.getName().equals(coach.getName())) {
                coach.setNaf(c.getId());
            }
        }

        if (netIsAvailable()) {
            getRanking(coach.getName(), coach);
        }
    }

    private NAF() {
    }

    public static ArrayList<String> getNameProposals(String name) {
        float min1 = Float.MAX_VALUE;
        float min2 = Float.MAX_VALUE;
        float min3 = Float.MAX_VALUE;
        float min4 = Float.MAX_VALUE;
        float min5 = Float.MAX_VALUE;

        String c1 = "";
        String c2 = "";
        String c3 = "";
        String c4 = "";
        String c5 = "";

        HashMap<String, Float> distances = new HashMap<>();
        for (NAFCoach c : coachs) {
            float dist = (float) 1 - getDistance(name, c.getName());
            distances.put(c.getName(), dist);

            if (dist < min5) {
                if (dist < min4) {
                    if (dist < min3) {
                        if (dist < min2) {
                            if (dist < min1) {
                                min5 = min4;
                                c5 = c4;
                                min4 = min3;
                                c4 = c3;
                                min3 = min2;
                                c3 = c2;
                                min2 = min1;
                                c2 = c1;
                                min1 = dist;
                                c1 = c.getName();
                            } else {
                                min5 = min4;
                                c5 = c4;
                                min4 = min3;
                                c4 = c3;
                                min3 = min2;
                                c3 = c2;
                                min2 = dist;
                                c2 = c.getName();
                            }

                        } else {
                            min5 = min4;
                            c5 = c4;
                            min4 = min3;
                            c4 = c3;
                            min3 = dist;
                            c3 = c.getName();
                        }

                    } else {
                        min5 = min4;
                        c5 = c4;
                        min4 = dist;
                        c4 = c.getName();
                    }
                } else {
                    min5 = dist;
                    c5 = c.getName();
                }
            }
        }
        ArrayList<String> proposals = new ArrayList<>();
        {
            proposals.add(c1);
            proposals.add(c2);
            proposals.add(c3);
            proposals.add(c4);
            proposals.add(c5);
        }
        return proposals;
    }

    //*****************************
    // Compute Levenshtein distance: see org.apache.commons.lang.StringUtils#getLevenshteinDistance(String, String)
    //*****************************
    public static float getDistance(String target, String other) {
        char[] sa;
        int n;
        int p[]; //'previous' cost array, horizontally
        int d[]; // cost array, horizontally
        int _d[]; //placeholder to assist in swapping p and d

        /*
           The difference between this impl. and the previous is that, rather
           than creating and retaining a matrix of size s.length()+1 by t.length()+1,
           we maintain two single-dimensional arrays of length s.length()+1.  The first, d,
           is the 'current working' distance array that maintains the newest distance cost
           counts as we iterate through the characters of String s.  Each time we increment
           the index of String t we are comparing, d is copied to p, the second int[].  Doing so
           allows us to retain the previous cost counts as required by the algorithm (taking
           the minimum of the cost count to the left, up one, and diagonally up and to the left
           of the current cost count being calculated).  (Note that the arrays aren't really
           copied anymore, just switched...this is clearly much better than cloning an array
           or doing a System.arraycopy() each time  through the outer loop.)

           Effectively, the difference between the two implementations is this one does not
           cause an out of memory condition when calculating the LD over two very large strings.
         */
        sa = target.toCharArray();
        n = sa.length;
        p = new int[n + 1];
        d = new int[n + 1];

        final int m = other.length();
        if (n == 0 || m == 0) {
            if (n == m) {
                return 1;
            } else {
                return 0;
            }
        }

        // indexes into strings s and t
        int i; // iterates through s
        int j; // iterates through t

        char t_j; // jth character of t

        int cost; // cost

        for (i = 0; i <= n; i++) {
            p[i] = i;
        }

        for (j = 1; j <= m; j++) {
            t_j = other.charAt(j - 1);
            d[0] = j;

            for (i = 1; i <= n; i++) {
                cost = sa[i - 1] == t_j ? 0 : 1;
                // minimum of cell to the left+1, to the top+1, diagonally left and up +cost
                d[i] = Math.min(Math.min(d[i - 1] + 1, p[i] + 1), p[i - 1] + cost);
            }

            // copy current distance counts to 'previous row' distance counts
            _d = p;
            p = d;
            d = _d;
        }

        // our last action in the above loop was to switch d and p, so p now
        // actually has the most recent cost counts
        return 1.0f - ((float) p[n] / Math.max(other.length(), sa.length));
    }
}
