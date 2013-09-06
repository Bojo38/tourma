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
import tourma.data.Group;
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
        if (mTournament.getGroups().size() > 1) {
            addGroupPie();
        }
        addWinLoss();
        addCounterPerRoster();
    }

    protected void addCounterPerRoster() {
        for (int i = 0; i < mTournament.getParams().mCriterias.size(); i++) {
            Criteria crit = mTournament.getParams().mCriterias.get(i);
            final HashMap<String, Double> plus = new HashMap<String, Double>();
            final HashMap<String, Double> minus = new HashMap<String, Double>();

            for (int j = 0; j < RosterType.mRostersNames.size(); j++) {
                plus.put(RosterType.mRostersNames.get(j), 0.0);
                minus.put(RosterType.mRostersNames.get(j), 0.0);

            }

            for (int j = 0; j < mTournament.getRounds().size(); j++) {
                final Round r = mTournament.getRounds().get(j);
                for (int k = 0; k < r.getMatchs().size(); k++) {
                    final Match m = r.getMatchs().get(k);
                    if ((m.mCoach1 != Coach.sNullCoach) && (m.mCoach2 != Coach.sNullCoach)) {
                        final Value values = m.mValues.get(crit);

                        double pt = plus.get(m.mCoach1.mRoster.mName).doubleValue();
                        double mt = minus.get(m.mCoach1.mRoster.mName).doubleValue();
                        pt += values.mValue1;
                        mt -= values.mValue2;

                        plus.put(m.mCoach1.mRoster.mName, pt);
                        minus.put(m.mCoach1.mRoster.mName, mt);

                        pt = plus.get(m.mCoach2.mRoster.mName).doubleValue();
                        mt = minus.get(m.mCoach2.mRoster.mName).doubleValue();
                        pt += values.mValue2;
                        mt -= values.mValue1;

                        plus.put(m.mCoach2.mRoster.mName, pt);
                        minus.put(m.mCoach2.mRoster.mName, mt);
                    }
                }
            }


            for (int j = 0; j < RosterType.mRostersNames.size(); j++) {

                int nb = 0;
                String name = RosterType.mRostersNames.get(j);
                for (int k = 0; k < mTournament.getCoachs().size(); k++) {
                    if (mTournament.getCoachs().get(k).mRoster.mName.equals(name)) {
                        nb++;
                    }
                }

                if (nb != 0) {
                    double tmp = plus.get(name).doubleValue();
                    plus.put(name, tmp / nb);

                    tmp = minus.get(name).doubleValue();
                    minus.put(name, tmp / nb);
                }

            }

            final DefaultCategoryDataset datas = new DefaultCategoryDataset();

            for (int j = 0; j < RosterType.mRostersNames.size(); j++) {
                final String name = RosterType.mRostersNames.get(j);
                final double pt = plus.get(name);
                final double mt = minus.get(name);
                if ((pt != 0) || (mt != 0)) {
                    datas.addValue(pt, java.util.ResourceBundle.getBundle("tourma/languages/language").getString("RÉALISÉS"), name);
                    datas.addValue(mt, java.util.ResourceBundle.getBundle("tourma/languages/language").getString("SUBIS"), name);
                }
            }

            final JFreeChart chart = ChartFactory.createStackedBarChart(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("VALUE PAR ROSTER"), java.util.ResourceBundle.getBundle("tourma/languages/language").getString("ROSTER"), java.util.ResourceBundle.getBundle("tourma/languages/language").getString("NUMBER"), datas, PlotOrientation.HORIZONTAL, true, true, false);

            final CategoryPlot plot = chart.getCategoryPlot();
            final BarRenderer br = (BarRenderer) plot.getRenderer();
            br.setBaseItemLabelsVisible(true, true);
            br.setSeriesPaint(0, new Color(0, 184, 0));
            br.setSeriesPaint(1, new Color(184, 0, 184));
            br.setBarPainter(new StandardBarPainter());


            final BarRenderer barrenderer = (BarRenderer) plot.getRenderer();
            barrenderer.setDrawBarOutline(false);
            barrenderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
            barrenderer.setBaseItemLabelsVisible(true);
            barrenderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.CENTER, TextAnchor.CENTER));
            barrenderer.setBaseNegativeItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.CENTER, TextAnchor.CENTER));
            barrenderer.setBaseToolTipGenerator(new StandardCategoryToolTipGenerator(
                    java.util.ResourceBundle.getBundle("tourma/languages/language").getString("{0}/{1}: {2}"), NumberFormat.getInstance()));

            final ChartPanel chartPanel = new ChartPanel(chart);
            jtpStatistics.addTab(crit.mName, chartPanel);
        }
    }

    protected void addWinLoss() {

        final HashMap<String, Integer> victories = new HashMap<String, Integer>();
        final HashMap<String, Integer> draw = new HashMap<String, Integer>();
        final HashMap<String, Integer> loss = new HashMap<String, Integer>();

        for (int i = 0; i < RosterType.mRostersNames.size(); i++) {
            victories.put(RosterType.mRostersNames.get(i), 0);
            draw.put(RosterType.mRostersNames.get(i), 0);
            loss.put(RosterType.mRostersNames.get(i), 0);
        }


        final Criteria Td = mTournament.getParams().mCriterias.get(0);
        for (int i = 0; i < mTournament.getRounds().size(); i++) {
            final Round r = mTournament.getRounds().get(i);
            for (int j = 0; j < r.getMatchs().size(); j++) {
                final Match m = r.getMatchs().get(j);
                if ((m.mCoach1 != Coach.sNullCoach) && (m.mCoach2 != Coach.sNullCoach)) {
                    final Value values = m.mValues.get(Td);
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
        final DefaultCategoryDataset datas = new DefaultCategoryDataset();

        for (int i = 0; i < RosterType.mRostersNames.size(); i++) {

            final String name = RosterType.mRostersNames.get(i);
            final int v = victories.get(name);
            final int d = draw.get(name);
            final int l = loss.get(name);
            if ((v != 0) || (d != 0) || (l != 0)) {
                datas.addValue((Number) v, java.util.ResourceBundle.getBundle("tourma/languages/language").getString("VICTOIRES"), name);
                datas.addValue((Number) d, java.util.ResourceBundle.getBundle("tourma/languages/language").getString("NULS"), name);
                datas.addValue((Number) l, java.util.ResourceBundle.getBundle("tourma/languages/language").getString("DÉFAITES"), name);
            }
        }

        final JFreeChart chart = ChartFactory.createStackedBarChart(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("RESULTATS PAR ROSTER"), java.util.ResourceBundle.getBundle("tourma/languages/language").getString("ROSTER"), java.util.ResourceBundle.getBundle("tourma/languages/language").getString("NUMBER"), datas, PlotOrientation.VERTICAL, true, true, false);

        final CategoryPlot plot = chart.getCategoryPlot();
        final BarRenderer br = (BarRenderer) plot.getRenderer();
        br.setBaseItemLabelsVisible(true, true);
        br.setSeriesPaint(0, new Color(0, 184, 0));
        br.setSeriesPaint(1, new Color(0, 0, 184));
        br.setSeriesPaint(2, new Color(184, 0, 0));
        br.setBarPainter(new StandardBarPainter());


        final BarRenderer barrenderer = (BarRenderer) plot.getRenderer();
        barrenderer.setDrawBarOutline(false);
        barrenderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        barrenderer.setBaseItemLabelsVisible(true);
        barrenderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.CENTER, TextAnchor.CENTER));
        barrenderer.setBaseNegativeItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.CENTER, TextAnchor.CENTER));
        barrenderer.setBaseToolTipGenerator(new StandardCategoryToolTipGenerator(
                java.util.ResourceBundle.getBundle("tourma/languages/language").getString("{0}/{1}: {2}"), NumberFormat.getInstance()));

        final ChartPanel chartPanel = new ChartPanel(chart);
        jtpStatistics.addTab(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("RESULTATS"), chartPanel);

    }

    protected void addRosterPie() {

        final DefaultPieDataset datas = new DefaultPieDataset();


        for (int i = 0; i < RosterType.mRostersNames.size(); i++) {
            datas.setValue(RosterType.mRostersNames.get(i), 0);
        }

        for (int i = 0; i < mTournament.getCoachs().size(); i++) {
            final Coach c = mTournament.getCoachs().get(i);
            if (c != Coach.sNullCoach) {
                final String rName = c.mRoster.mName;
                final Number value = datas.getValue(rName);
                int v = 0;
                if (value != null) {
                    v = value.intValue();
                }
                v++;
                datas.setValue(rName, v);
            }
        }


        for (int i = 0; i < datas.getItemCount(); i++) {
            final Number value = datas.getValue(i);
            if (value.intValue() == 0) {
                datas.remove(datas.getKey(i));
                i--;
            }
        }

        final JFreeChart chart = ChartFactory.createPieChart(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("ROSTERS"), datas, true, true, false);

        final PiePlot plot = (PiePlot) chart.getPlot();
        final StandardPieSectionLabelGenerator label = new StandardPieSectionLabelGenerator(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("{0}: {1} ({2})"));
        plot.setLegendLabelGenerator(label);
        plot.setLabelGenerator(label);
        plot.setStartAngle(290);
        plot.setDirection(Rotation.CLOCKWISE);
        plot.setForegroundAlpha(0.5f);

        final ChartPanel chartPanel = new ChartPanel(chart);
        jtpStatistics.addTab(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("ROSTERS"), chartPanel);
    }

    protected void addGroupPie() {

        final DefaultPieDataset datas = new DefaultPieDataset();

        for (int i = 0; i < mTournament.getGroups().size(); i++) {
            int value = 0;
            Group g = mTournament.getGroups().get(i);
            for (int j = 0; j < mTournament.getCoachs().size(); j++) {
                final Coach c = mTournament.getCoachs().get(i);
                if (c != Coach.sNullCoach) {
                    for (int k = 0; k < g.mRosters.size(); k++) {
                        if (g.mRosters.get(k).mName.equals(c.mRoster.mName)) {
                            value++;
                        }
                    }

                }

            }
            if (value > 0) {
                datas.setValue(mTournament.getGroups().get(i).mName, value);
            }
        }


        for (int i = 0; i < datas.getItemCount(); i++) {
            final Number value = datas.getValue(i);
            if (value.intValue() == 0) {
                datas.remove(datas.getKey(i));
                i--;
            }
        }

        final JFreeChart chart = ChartFactory.createPieChart(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("GROUPS"), datas, true, true, false);

        final PiePlot plot = (PiePlot) chart.getPlot();
        final StandardPieSectionLabelGenerator label = new StandardPieSectionLabelGenerator(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("{0}: {1} ({2})"));
        plot.setLegendLabelGenerator(label);
        plot.setLabelGenerator(label);
        plot.setStartAngle(290);
        plot.setDirection(Rotation.CLOCKWISE);
        plot.setForegroundAlpha(0.5f);

        final ChartPanel chartPanel = new ChartPanel(chart);
        jtpStatistics.addTab(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("GROUPS"), chartPanel);
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
