/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma;

import java.awt.Color;
import java.text.NumberFormat;
import java.util.HashMap;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.TextAnchor;
import org.jfree.util.Rotation;
import tourma.data.Coach;
import tourma.data.Criteria;
import tourma.data.Match;
import tourma.data.RosterType;
import tourma.data.Round;
import tourma.data.Tournament;
import tourma.data.Value;

/**
 *
 * @author WFMJ7631
 */
public class JPNStatistics extends javax.swing.JPanel {

    Tournament mTournament;

    /**
     * Creates new form JPNStatistics
     */
    public JPNStatistics() {
        mTournament = Tournament.getTournament();
        initComponents();

        addRosterPie();
        addWinLoss();
    }

    protected void addWinLoss() {

        HashMap<String, Integer> victories = new HashMap<String, Integer>();
        HashMap<String, Integer> draw = new HashMap<String, Integer>();
        HashMap<String, Integer> loss = new HashMap<String, Integer>();

        for (int i = 0; i < RosterType.mRostersNames.size(); i++) {
            victories.put(RosterType.mRostersNames.get(i), 0);
            draw.put(RosterType.mRostersNames.get(i), 0);
            loss.put(RosterType.mRostersNames.get(i), 0);
        }


        Criteria Td = mTournament.getParams().mCriterias.get(0);
        for (int i = 0; i < mTournament.getRounds().size(); i++) {
            final Round r = mTournament.getRounds().get(i);
            for (int j = 0; j < r.getMatchs().size(); j++) {
                final Match m = r.getMatchs().get(j);
                if ((m.mCoach1 != Coach.sNullCoach) && (m.mCoach2 != Coach.sNullCoach)) {
                    Value values = m.mValues.get(Td);
                    if (values.mValue1 > values.mValue2) {
                        int v = victories.get(m.mCoach1.mRoster.mName).intValue();
                        int l = loss.get(m.mCoach2.mRoster.mName).intValue();
                        v++;
                        l++;

                        victories.put(m.mCoach1.mRoster.mName, v);
                        loss.put(m.mCoach2.mRoster.mName, l);
                    }
                    if (values.mValue1 < values.mValue2) {
                        int v = victories.get(m.mCoach2.mRoster.mName).intValue();
                        int l = loss.get(m.mCoach1.mRoster.mName).intValue();
                        v++;
                        l++;

                        victories.put(m.mCoach2.mRoster.mName, v);
                        loss.put(m.mCoach1.mRoster.mName, l);
                    }
                    if (values.mValue1 == values.mValue2) {
                        int d = draw.get(m.mCoach2.mRoster.mName).intValue();
                        d++;
                        draw.put(m.mCoach2.mRoster.mName, d);

                        d = draw.get(m.mCoach1.mRoster.mName).intValue();
                        d++;
                        draw.put(m.mCoach1.mRoster.mName, d);
                    }
                }
            }
        }
        DefaultCategoryDataset datas = new DefaultCategoryDataset();

        for (int i = 0; i < RosterType.mRostersNames.size(); i++) {

            String name = RosterType.mRostersNames.get(i);
            int v = victories.get(name);
            int d = draw.get(name);
            int l = loss.get(name);
            if ((v != 0) || (d != 0) || (l != 0)) {
                datas.addValue((Number) v, "Victoires", name);
                datas.addValue((Number) d, "Nuls", name);
                datas.addValue((Number) l, "Défaites", name);
            }
        }

        JFreeChart chart = ChartFactory.createStackedBarChart("Resultats par Roster", "Roster", "Number", datas, PlotOrientation.VERTICAL, true, true, false);

        CategoryPlot plot = chart.getCategoryPlot();
        BarRenderer br=(BarRenderer)plot.getRenderer();
        br.setBaseItemLabelsVisible(true,true);
        br.setSeriesPaint(0, new Color(0, 184, 0));
        br.setSeriesPaint(1, new Color(0, 0, 184));
        br.setSeriesPaint(2, new Color(184, 0, 0));
        br.setBarPainter(new StandardBarPainter());

        
        BarRenderer barrenderer = (BarRenderer) plot.getRenderer();
    barrenderer.setDrawBarOutline(false);
    barrenderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
    barrenderer.setBaseItemLabelsVisible(true);
    barrenderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.CENTER, TextAnchor.CENTER));
    barrenderer.setBaseNegativeItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.CENTER, TextAnchor.CENTER));
    barrenderer.setBaseToolTipGenerator(new StandardCategoryToolTipGenerator(
            "{0}/{1}: {2}", NumberFormat.getInstance()));
        
        ChartPanel chartPanel = new ChartPanel(chart);
        jtpStatistics.addTab("Resultats", chartPanel);

    }

    protected void addRosterPie() {

        DefaultPieDataset datas = new DefaultPieDataset();


        for (int i = 0; i < RosterType.mRostersNames.size(); i++) {
            datas.setValue(RosterType.mRostersNames.get(i), 0);
        }

        for (int i = 0; i < mTournament.getCoachs().size(); i++) {
            Coach c = mTournament.getCoachs().get(i);
            if (c != Coach.sNullCoach) {
                String rName = c.mRoster.mName;
                Number value = datas.getValue(rName);
                int v = 0;
                if (value != null) {
                    v = value.intValue();
                }
                v++;
                datas.setValue(rName, v);
            }
        }


        for (int i = 0; i < datas.getItemCount(); i++) {
            Number value = datas.getValue(i);
            if (value.intValue() == 0) {
                datas.remove(datas.getKey(i));
                i--;
            }
        }

        JFreeChart chart = ChartFactory.createPieChart("Rosters", datas, true, true, false);

        PiePlot plot = (PiePlot) chart.getPlot();
        StandardPieSectionLabelGenerator label = new StandardPieSectionLabelGenerator("{0}: {1} ({2})");
        plot.setLegendLabelGenerator(label);
        plot.setLabelGenerator(label);
        plot.setStartAngle(290);
        plot.setDirection(Rotation.CLOCKWISE);
        plot.setForegroundAlpha(0.5f);

        ChartPanel chartPanel = new ChartPanel(chart);
        jtpStatistics.addTab("Rosters", chartPanel);
    }

    public void update() {
        //
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jtpStatistics = new javax.swing.JTabbedPane();

        setLayout(new java.awt.BorderLayout());
        add(jtpStatistics, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane jtpStatistics;
    // End of variables declaration//GEN-END:variables
}
