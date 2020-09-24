/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JOptionPane;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import tourma.MainFrame;
import tourma.data.Coach;

/**
 *
 * @author WFMJ7631
 */
public class NAFIdUpdate {

    public static final int MAX_NAF_ID = 30000;
    public static final int MIN_NAF_ID = 22001;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Element document = new Element("root");
        for (int i = MIN_NAF_ID; i <= MAX_NAF_ID; i++) {
            Coach c = new Coach();
            double res = NAF.getRanking(i, c);
            System.out.println("Progress " + i + "/" + MAX_NAF_ID);
            if (res != 0.0) {
                if (c.getName() != null) {
                    Element x_coach = new Element("coach");
                    x_coach.setAttribute("id", Integer.toString(c.getNaf()));
                    x_coach.setAttribute("name", c.getName());
                    document.addContent(x_coach);
                }
            }
            if (i % 1000 == 0) {
                String filename = "naf_id" + (i / 1000) + ".xml";
                File file = new File(filename);
                FileOutputStream os = null;
                try {
                    final XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
                    os = new FileOutputStream(file);
                    sortie.output(document, os);

                } catch (FileNotFoundException e) {
                    JOptionPane.showMessageDialog(MainFrame.getMainFrame(), e.getMessage());
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(MainFrame.getMainFrame(), e.getMessage());
                } finally {
                    if (os != null) {
                        try {
                            os.close();
                        } catch (IOException e) {

                        }
                    }

                }
                document = new Element("root");
            }
        }

        /*final JFileChooser jfc = new JFileChooser();
        final FileFilter filter1 = new ExtensionFileFilter(
                "XML",
                new String[]{StringConstants.CS_XML, StringConstants.CS_MINXML});
        jfc.setFileFilter(filter1);
        if (jfc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            StringBuffer url2 = new StringBuffer(jfc.getSelectedFile().getAbsolutePath());
            String ext = StringConstants.CS_NULL;
            final int i = url2.toString().lastIndexOf('.');
            if (i > 0 && i < url2.length() - 1) {
                ext = url2.substring(i + 1).toLowerCase(Locale.getDefault());
            }

            if (!ext.equals(StringConstants.CS_MINXML)) {
                url2 = url2.append(".XML");
            }
                    }*/
    }

}
