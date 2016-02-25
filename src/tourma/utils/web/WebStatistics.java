/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.utils.web;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import org.jfree.chart.ChartPanel;
import tourma.views.JPNStatistics;

/**
 *
 * @author WFMJ7631
 */
public class WebStatistics {
    /**
     * 
     */
    public static String getHTML() {
        StringBuffer stats = new StringBuffer("");

        JPNStatistics jpn = new JPNStatistics();
        jpn.setSize(640, 480);
        JTabbedPane jtp = jpn.getTabbedPane();
        for (int i = 0; i < jtp.getTabCount(); i++) {
            Component comp = jtp.getComponent(i);

            if (comp instanceof ChartPanel) {
                ChartPanel panel = (ChartPanel) comp;
                panel.setSize(640, 480);
                BufferedImage buf = new BufferedImage(640, 480, BufferedImage.TYPE_INT_ARGB);
                Graphics g = buf.createGraphics();
                panel.print(g);
                g.dispose();

                //BufferedImage buf = toBufferedImage(img, 640, 480);
                String img_str = WebPicture.getPictureAsHTML(buf, 640, 480,true);
                stats.append(img_str);
            }

            if (comp instanceof JPanel) {
                // Find JList, Select All then Find ChartPanel
                JPanel pane = (JPanel) comp;
                ChartPanel panel = null;
                JList list = null;
                for (int j = 0; j < pane.getComponentCount(); j++) {
                    Component c = pane.getComponent(j);
                    if (c instanceof JScrollPane) {
                        for (int k = 0; k < ((JScrollPane) c).getViewport().getComponentCount(); k++) {
                            Component c2 = ((JScrollPane) c).getViewport().getComponent(k);
                            if (c2 instanceof JList) {
                                list = (JList) c2;
                            }

                        }
                    }
                }

                if (list != null) {

                    int start = 0;
                    int end = list.getModel().getSize() - 1;
                    if (end >= 0) {
                        list.setSelectionInterval(start, end);
                    }

                    jpn.updatePositions();
                }
                for (int j = 0; j < pane.getComponentCount(); j++) {
                    Component c = pane.getComponent(j);
                    if (c instanceof ChartPanel) {
                        panel = (ChartPanel) c;
                    }
                }

                if (panel != null) {
                    panel.setSize(640, 480);
                    BufferedImage buf = new BufferedImage(640, 480, BufferedImage.TYPE_INT_ARGB);
                    Graphics g = buf.createGraphics();
                    panel.print(g);
                    g.dispose();

                    //BufferedImage buf = toBufferedImage(img, 640, 480);
                    String img_str = WebPicture.getPictureAsHTML(buf, 640, 480,true);
                    stats.append(img_str);
                }
            }
        }

        return stats.toString();
    }
}
