/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.TextAnchor;
import org.jfree.util.Rotation;
import org.jfree.util.SortOrder;
import tourma.data.Coach;
import tourma.data.Team;
import tourma.data.Criteria;
import tourma.data.Group;
import tourma.data.CoachMatch;
import tourma.data.RosterType;
import tourma.data.Round;
import tourma.data.Tournament;
import tourma.data.Value;
import tourma.tableModel.mjtRanking;
import tourma.tableModel.mjtRankingIndiv;
import tourma.tableModel.mjtRankingTeam;

/**
 *
 * @author WFMJ7631
 */
public class JPNStatistics extends javax.swing.JPanel {

    Tournament mTournament;
    ArrayList<HashMap<String, Integer>> mHpositions = new ArrayList<>();
    ArrayList<HashMap<String, Integer>> mHTeampositions = new ArrayList<>();
    ChartPanel cpPositions = null;
    ChartPanel cpTeamPositions = null;   
    ChartPanel cpBalancedTeam = null;
    ChartPanel cpBalancedIndiv = null;
    JPanel jpnPositions = new JPanel(new BorderLayout());
    JPanel jpnTeamPositions = new JPanel(new BorderLayout());
    JList jlsPositions = null;
    JList jlsTeamPositions = null;
    JPanel jpnBalancedTeam = new JPanel(new BorderLayout());
    JList jlsBalancedTeam = null;
    ArrayList<HashMap<String, Integer>> mHTeamBalanced = new ArrayList<>();
    JPanel jpnBalancedIndiv = new JPanel(new BorderLayout());
    JList jlsBalancedIndiv = null;
    ArrayList<HashMap<String, Integer>> mHIndivBalanced = new ArrayList<>();

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
        if (mTournament.getParams().mTeamTournament) {
            addTeamPositions();
            if (mTournament.getParams().mTeamPairing == 0) {
                addTeamBalanced();
                addIndivBalanced();
            }
        }

        addWinLoss();
        addCounterPerRoster();
        addPointsAverage();
        addPositions();
    }

    protected void addTeamBalanced() {

        ArrayList<Team> teams = mTournament.getTeams();
        DefaultListModel model = new DefaultListModel();
        for (int i = 0; i < teams.size(); i++) {
            model.addElement(teams.get(i).mName);
        }
        JScrollPane jspBalancedTeam = new JScrollPane();
        jlsBalancedTeam = new JList(model);
        jlsBalancedTeam.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        jspBalancedTeam.getViewport().add(jlsBalancedTeam);

        jpnBalancedTeam.add(jspBalancedTeam, BorderLayout.WEST);

        // creation des callbacks
        jlsBalancedTeam.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                updateBalancedTeam();
            }
        });

        // creation des listes de données
        for (int i = 0; i < mTournament.getTeams().size(); i++) {
            Team t = mTournament.getTeams().get(i);

            HashMap<String, Integer> hm = new HashMap<>();
            for (int j = 0; j < mTournament.getTeams().size(); j++) {
                Team opp = mTournament.getTeams().get(j);
                hm.put(opp.mName, 0);
            }

            for (int j = 0; j < t.mCoachs.size(); j++) {
                Coach c = t.mCoachs.get(j);
                for (int k = 0; k < c.mMatchs.size(); k++) {
                    CoachMatch m = (CoachMatch) c.mMatchs.get(k);
                    Coach opp = null;
                    if (m.mCompetitor1 == c) {
                        opp = (Coach) m.mCompetitor2;
                    } else {
                        opp = (Coach) m.mCompetitor1;
                    }
                    Team other = opp.mTeamMates;
                    int nb = hm.get(other.mName);
                    nb = nb + 1;
                    hm.put(other.mName, nb);
                }
            }
            mHTeamBalanced.add(hm);
        }

        // update positions
        updateBalancedTeam();

        jtpStatistics.addTab(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("OPPONENT BY TEAM"), jpnBalancedTeam);

    }

    protected void addIndivBalanced() {

        ArrayList<Coach> coachs = mTournament.getCoachs();
        DefaultListModel model = new DefaultListModel();
        for (int i = 0; i < coachs.size(); i++) {
            model.addElement(coachs.get(i).mName);
        }

        JScrollPane jspBalancedIndiv = new JScrollPane();
        jlsBalancedIndiv = new JList(model);
        jlsBalancedIndiv.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        jspBalancedIndiv.getViewport().add(jlsBalancedIndiv);

        jpnBalancedIndiv.add(jspBalancedIndiv, BorderLayout.WEST);

        // creation des callbacks
        jlsBalancedIndiv.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                updateBalancedIndiv();
            }
        });

        // creation des listes de données
        for (int i = 0; i < mTournament.getCoachs().size(); i++) {
            Coach c = mTournament.getCoachs().get(i);

            HashMap<String, Integer> hm = new HashMap<>();
            for (int j = 0; j < mTournament.getTeams().size(); j++) {
                Team opp = mTournament.getTeams().get(j);
                hm.put(opp.mName, 0);
            }


            for (int k = 0; k < c.mMatchs.size(); k++) {
                CoachMatch m = (CoachMatch) c.mMatchs.get(k);
                Coach opp = null;
                if (m.mCompetitor1 == c) {
                    opp = (Coach) m.mCompetitor2;
                } else {
                    opp = (Coach) m.mCompetitor1;
                }
                Team other = opp.mTeamMates;
                int nb = hm.get(other.mName);
                nb = nb + 1;
                hm.put(other.mName, nb);
            }

            mHIndivBalanced.add(hm);
        }

        // update positions
        updateBalancedIndiv();

        jtpStatistics.addTab(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("OPPOSITIONS PAR JOUEUR"), jpnBalancedIndiv);

    }

    protected void addCounterPerRoster() {
        for (int i = 0; i < mTournament.getParams().mCriterias.size(); i++) {
            Criteria crit = mTournament.getParams().mCriterias.get(i);
            final HashMap<String, Double> plus = new HashMap<>();
            final HashMap<String, Double> minus = new HashMap<>();
            final HashMap<String, Double> total = new HashMap<>();

            for (int j = 0; j < RosterType.mRostersNames.size(); j++) {
                plus.put(RosterType.mRostersNames.get(j), 0.0);
                minus.put(RosterType.mRostersNames.get(j), 0.0);
                total.put(RosterType.mRostersNames.get(j), 0.0);

            }

            for (int j = 0; j < mTournament.getRounds().size(); j++) {
                final Round r = mTournament.getRounds().get(j);
                for (int k = 0; k < r.getMatchs().size(); k++) {
                    final CoachMatch m = r.getCoachMatchs().get(k);
                    if ((m.mCompetitor1 != Coach.getNullCoach()) && (m.mCompetitor2 != Coach.getNullCoach())) {
                        final Value values = m.mValues.get(crit);

                        String name1;
                        String name2;
                        if (m.mRoster1 == null) {
                            name1 = ((Coach) m.mCompetitor1).mRoster.mName;
                        } else {
                            name1 = m.mRoster1.mName;
                        }

                        if (m.mRoster2 == null) {
                            name2 = ((Coach) m.mCompetitor2).mRoster.mName;
                        } else {
                            name2 = m.mRoster2.mName;
                        }

                        double pt = plus.get(name1).doubleValue();
                        double tot = total.get(name1).doubleValue();
                        double mt = minus.get(name1).doubleValue();
                        pt += values.mValue1;
                        mt -= values.mValue2;
                        tot += 1.0;


                        plus.put(name1, pt);
                        minus.put(name1, mt);
                        total.put(name1, tot);

                        pt = plus.get(name2).doubleValue();
                        mt = minus.get(name2).doubleValue();
                        tot = total.get(name2).doubleValue();
                        pt += values.mValue2;
                        mt -= values.mValue1;
                        tot += 1.0;

                        plus.put(name2, pt);
                        minus.put(name2, mt);
                        total.put(name2, tot);
                    }
                }
            }

            final DefaultCategoryDataset datas = new DefaultCategoryDataset();

            for (int j = 0; j < RosterType.mRostersNames.size(); j++) {
                final String name = RosterType.mRostersNames.get(j);
                final double pt = plus.get(name);
                final double mt = minus.get(name);
                final double tot = total.get(name);
                if (((pt != 0) || (mt != 0)) && (tot != 0)) {
                    datas.addValue(pt / tot, java.util.ResourceBundle.getBundle("tourma/languages/language").getString("RÉALISÉS"), name);
                    datas.addValue(mt / tot, java.util.ResourceBundle.getBundle("tourma/languages/language").getString("SUBIS"), name);
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
                    "{0}/{1}: {2}", NumberFormat.getInstance()));

            final ChartPanel chartPanel = new ChartPanel(chart);
            jtpStatistics.addTab(crit.mName, chartPanel);
        }
    }

    /**
     *
     */
    protected void addWinLoss() {

        final HashMap<String, Double> victories = new HashMap<>();
        final HashMap<String, Double> draw = new HashMap<>();
        final HashMap<String, Double> loss = new HashMap<>();
        final HashMap<String, Double> total = new HashMap<>();

        for (int i = 0; i < RosterType.mRostersNames.size(); i++) {
            victories.put(RosterType.mRostersNames.get(i), 0.0);
            draw.put(RosterType.mRostersNames.get(i), 0.0);
            loss.put(RosterType.mRostersNames.get(i), 0.0);
            total.put(RosterType.mRostersNames.get(i), 0.0);
        }


        final Criteria Td = mTournament.getParams().mCriterias.get(0);
        for (int i = 0; i < mTournament.getRounds().size(); i++) {
            final Round r = mTournament.getRounds().get(i);
            for (int j = 0; j < r.getMatchs().size(); j++) {
                final CoachMatch m = r.getCoachMatchs().get(j);
                if ((m.mCompetitor1 != Coach.getNullCoach()) && (m.mCompetitor2 != Coach.getNullCoach())) {
                    final Value values = m.mValues.get(Td);

                    String name1;
                    String name2;
                    if (m.mRoster1 == null) {
                        name1 = ((Coach) m.mCompetitor1).mRoster.mName;
                    } else {
                        name1 = m.mRoster1.mName;
                    }

                    if (m.mRoster2 == null) {
                        name2 = ((Coach) m.mCompetitor2).mRoster.mName;
                    } else {
                        name2 = m.mRoster2.mName;
                    }

                    double t = total.get(name2).intValue();
                    t += 1.0;
                    total.put(name2, t);
                    t = total.get(name1).intValue();
                    t += 1.0;
                    total.put(name1, t);
                    if (values.mValue1 > values.mValue2) {
                        double v = victories.get(name1).doubleValue();
                        double l = loss.get(name2).doubleValue();
                        v++;
                        l++;
                        victories.put(name1, v);
                        loss.put(name2, l);
                    }
                    if (values.mValue1 < values.mValue2) {
                        double v = victories.get(name2).doubleValue();
                        double l = loss.get(name1).doubleValue();
                        v++;
                        l++;

                        victories.put(name2, v);
                        loss.put(name1, l);
                    }
                    if (values.mValue1 == values.mValue2) {
                        double d = draw.get(name2).doubleValue();
                        d++;
                        draw.put(name2, d);

                        d = draw.get(name1).doubleValue();
                        d++;
                        draw.put(name1, d);
                    }
                }
            }
        }
        final DefaultCategoryDataset datas = new DefaultCategoryDataset();

        for (int i = 0; i < RosterType.mRostersNames.size(); i++) {

            final String name = RosterType.mRostersNames.get(i);
            final double v = victories.get(name);
            final double d = draw.get(name);
            final double l = loss.get(name);
            final double t = total.get(name);
            if (((v != 0) || (d != 0) || (l != 0)) && (t != 0.0)) {
                datas.addValue((Double) v / t, java.util.ResourceBundle.getBundle("tourma/languages/language").getString("VICTOIRES"), name);
                datas.addValue((Double) d / t, java.util.ResourceBundle.getBundle("tourma/languages/language").getString("NULS"), name);
                datas.addValue((Double) l / t, java.util.ResourceBundle.getBundle("tourma/languages/language").getString("DÉFAITES"), name);
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
        StandardCategoryItemLabelGenerator std = new StandardCategoryItemLabelGenerator();

        barrenderer.setBaseItemLabelGenerator(std);
        barrenderer.setBaseItemLabelsVisible(true);

        barrenderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.CENTER, TextAnchor.CENTER));
        barrenderer.setBaseNegativeItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.CENTER, TextAnchor.CENTER));
        barrenderer.setBaseToolTipGenerator(new StandardCategoryToolTipGenerator(
                "{0}/{1}: {2}", NumberFormat.getInstance()));

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
            if (c != Coach.getNullCoach()) {
                ArrayList<String> names = new ArrayList<String>();
                for (int j = 0; j < c.mMatchs.size(); j++) {
                    CoachMatch m = (CoachMatch) c.mMatchs.get(j);
                    RosterType r;
                    if (c == m.mCompetitor1) {
                        r = m.mRoster1;
                    } else {
                        r = m.mRoster2;
                    }
                    if (r == null) {
                        r = c.mRoster;
                    }

                    if (!names.contains(r.mName)) {
                        names.add(r.mName);
                    }
                }

                for (int j = 0; j < names.size(); j++) {
                    final String rName = names.get(j);
                    final Number value = datas.getValue(rName);
                    int v = 0;
                    if (value != null) {
                        v = value.intValue();
                    }
                    v++;
                    datas.setValue(rName, v);
                }
            }
        }


        for (int i = 0; i < datas.getItemCount(); i++) {
            final Number value = datas.getValue(i);
            if (value.intValue() == 0) {
                datas.remove(datas.getKey(i));
                i--;
            } else {
                if (!Tournament.getTournament().getParams().mMultiRoster) {
                    double d = value.doubleValue();
                    d = d / Tournament.getTournament().getRounds().size();
                    datas.setValue(datas.getKey(i), d);
                }
            }
        }

        final JFreeChart chart = ChartFactory.createPieChart(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("ROSTERS"), datas, true, true, false);

        final PiePlot plot = (PiePlot) chart.getPlot();
        final StandardPieSectionLabelGenerator label = new StandardPieSectionLabelGenerator(
                "{0}: {1} ({2})");
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
                final Coach c = mTournament.getCoachs().get(j);
                if (c != Coach.getNullCoach()) {
                    for (int k = 0; k < g.mRosters.size(); k++) {
                        if (c.mRoster != null) {
                            if (g.mRosters.get(k).mName.equals(c.mRoster.mName)) {
                                value++;
                            }
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
        final StandardPieSectionLabelGenerator label = new StandardPieSectionLabelGenerator(
                "{0}: {1} ({2})");
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

    protected void addPointsAverage() {

        final HashMap<String, Double> avg = new HashMap<>();
        final HashMap<String, Double> avg_opp = new HashMap<>();
        final HashMap<String, Double> count = new HashMap<>();

        for (int i = 0; i < RosterType.mRostersNames.size(); i++) {
            avg.put(RosterType.mRostersNames.get(i), 0.0);
            avg_opp.put(RosterType.mRostersNames.get(i), 0.0);
            count.put(RosterType.mRostersNames.get(i), 0.0);
        }

        // Récupération des matchs
        for (int i = 0; i < mTournament.getRounds().size(); i++) {
            ArrayList<CoachMatch> acm = mTournament.getRounds().get(i).getCoachMatchs();
            for (int j = 0; j < acm.size(); j++) {
                CoachMatch cm = acm.get(j);
                double p1 = mjtRanking.getPointsByCoach((Coach) cm.mCompetitor1, cm);
                double p2 = mjtRanking.getPointsByCoach((Coach) cm.mCompetitor2, cm);

                double avg_value = avg.get(((Coach) cm.mCompetitor1).mRoster.mName);
                double avg_opp_value = avg_opp.get(((Coach) cm.mCompetitor1).mRoster.mName);
                double count_value = count.get(((Coach) cm.mCompetitor1).mRoster.mName);

                avg_value = (avg_value * count_value + p1) / (count_value + 1.0);
                avg_opp_value = (avg_opp_value * count_value + p2) / (count_value + 1.0);
                count_value = count_value + 1;

                avg.put(((Coach) cm.mCompetitor1).mRoster.mName, avg_value);
                avg_opp.put(((Coach) cm.mCompetitor1).mRoster.mName, avg_opp_value);
                count.put(((Coach) cm.mCompetitor1).mRoster.mName, count_value);

                avg_value = avg.get(((Coach) cm.mCompetitor2).mRoster.mName);
                avg_opp_value = avg_opp.get(((Coach) cm.mCompetitor2).mRoster.mName);
                count_value = count.get(((Coach) cm.mCompetitor2).mRoster.mName);

                avg_value = (avg_value * count_value + p2) / (count_value + 1.0);
                avg_opp_value = (avg_opp_value * count_value + p1) / (count_value + 1.0);
                count_value = count_value + 1;

                avg.put(((Coach) cm.mCompetitor2).mRoster.mName, avg_value);
                avg_opp.put(((Coach) cm.mCompetitor2).mRoster.mName, avg_opp_value);
                count.put(((Coach) cm.mCompetitor2).mRoster.mName, count_value);
            }
        }

        // Affichage du graphique
        final DefaultCategoryDataset datas = new DefaultCategoryDataset();

        for (int i = 0; i < RosterType.mRostersNames.size(); i++) {

            final String name = RosterType.mRostersNames.get(i);
            final double avg_value = avg.get(name);
            final double avg_opp_value = avg_opp.get(name);
            if ((avg_value != 0) && (avg_opp_value != 0)) {
                datas.addValue(avg_value, java.util.ResourceBundle.getBundle("tourma/languages/language").getString("POINTS"), name);
                datas.addValue(avg_opp_value, java.util.ResourceBundle.getBundle("tourma/languages/language").getString("POINTS ADVERSAIRES"), name);
            }
        }

        final JFreeChart chart = ChartFactory.createStackedBarChart(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("RESULTATS PAR ROSTER"), java.util.ResourceBundle.getBundle("tourma/languages/language").getString("ROSTER"), java.util.ResourceBundle.getBundle("tourma/languages/language").getString("NUMBER"), datas, PlotOrientation.VERTICAL, true, true, false);

        final CategoryPlot plot = chart.getCategoryPlot();
        final BarRenderer br = (BarRenderer) plot.getRenderer();
        br.setBaseItemLabelsVisible(true, true);
        br.setSeriesPaint(0, new Color(0, 184, 0));
        br.setSeriesPaint(1, new Color(0, 0, 184));
        br.setBarPainter(new StandardBarPainter());


        final BarRenderer barrenderer = (BarRenderer) plot.getRenderer();
        barrenderer.setDrawBarOutline(false);
        StandardCategoryItemLabelGenerator std = new StandardCategoryItemLabelGenerator();

        barrenderer.setBaseItemLabelGenerator(std);
        barrenderer.setBaseItemLabelsVisible(true);

        barrenderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.CENTER, TextAnchor.CENTER));
        barrenderer.setBaseNegativeItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.CENTER, TextAnchor.CENTER));
        barrenderer.setBaseToolTipGenerator(new StandardCategoryToolTipGenerator(
                "{0}/{1}: {2}", NumberFormat.getInstance()));

        final ChartPanel chartPanel = new ChartPanel(chart);
        jtpStatistics.addTab(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("POINTS"), chartPanel);

    }

    protected void addPositions() {
        // creation et partage du panel

        // creation de la list de checkbox
        ArrayList<Coach> coach = mTournament.getCoachs();
        DefaultListModel model = new DefaultListModel();
        for (int i = 0; i < coach.size(); i++) {
            model.addElement(coach.get(i).mName);
        }
        JScrollPane jsp = new JScrollPane();
        jlsPositions = new JList(model);
        jlsPositions.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        jsp.getViewport().add(jlsPositions);

        jpnPositions.add(jsp, BorderLayout.WEST);

        // creation des callbacks
        jlsPositions.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                updatePositions();
            }
        });

        // creation des listes de données
        int column_name = 1;
        if (mTournament.getParams().mTeamTournament) {
            column_name = 2;
        }
        for (int i = 0; i < mTournament.getRounds().size(); i++) {
            Round r = mTournament.getRounds().get(i);
            mjtRankingIndiv ranking = new mjtRankingIndiv(i,
                    mTournament.getParams().mRankingIndiv1,
                    mTournament.getParams().mRankingIndiv2,
                    mTournament.getParams().mRankingIndiv3,
                    mTournament.getParams().mRankingIndiv4,
                    mTournament.getParams().mRankingIndiv5,
                    coach,
                    mTournament.getParams().mTeamTournament,
                    false,
                    false);

            ArrayList<String> coach_names = new ArrayList<>();
            int count = ranking.getRowCount();
            for (int j = 0; j < count; j++) {
                coach_names.add((String) ranking.getValueAt(j, column_name));
            }
            HashMap<String, Integer> hm = new HashMap<>();
            for (int j = 0; j < coach.size(); j++) {
                Coach c = coach.get(j);
                hm.put(c.mName, coach_names.indexOf(c.mName));

            }
            mHpositions.add(hm);
        }

        // update positions
        updatePositions();

        jtpStatistics.addTab(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("POSITIONS"), jpnPositions);

    }

    protected void addTeamPositions() {
        // creation et partage du panel

        // creation de la list de checkbox
        ArrayList<Team> team = mTournament.getTeams();
        DefaultListModel model = new DefaultListModel();
        for (int i = 0; i < team.size(); i++) {
            model.addElement(team.get(i).mName);
        }
        JScrollPane jsp = new JScrollPane();
        jlsTeamPositions = new JList(model);
        jlsTeamPositions.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        jsp.getViewport().add(jlsTeamPositions);

        jpnTeamPositions.add(jsp, BorderLayout.WEST);

        // creation des callbacks
        jlsTeamPositions.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                updateTeamPositions();
            }
        });

        // creation des listes de données
        int column_name = 1;
        if (mTournament.getParams().mTeamTournament) {
            for (int i = 0; i < mTournament.getRounds().size(); i++) {
                Round r = mTournament.getRounds().get(i);
                mjtRankingTeam ranking = new mjtRankingTeam(
                        mTournament.getParams().mTeamVictoryOnly,
                        i,
                        mTournament.getTeams(),
                        false);

                ArrayList<String> team_names = new ArrayList<>();
                int count = ranking.getRowCount();
                for (int j = 0; j < count; j++) {
                    team_names.add((String) ranking.getValueAt(j, column_name));
                }
                HashMap<String, Integer> hm = new HashMap<>();
                for (int j = 0; j < team.size(); j++) {
                    Team c = team.get(j);
                    hm.put(c.mName, team_names.indexOf(c.mName));

                }
                mHTeampositions.add(hm);
            }
        }

        // update positions
        updateTeamPositions();

        jtpStatistics.addTab(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("TEAM POSITIONS"), jpnTeamPositions);

    }

    protected void updatePositions() {
        if (cpPositions != null) {
            jpnPositions.remove(cpPositions);
        }

        final XYSeriesCollection datas = new XYSeriesCollection();

        List values = jlsPositions.getSelectedValuesList();
        for (int i = 0; i < values.size(); i++) {
            String selection = (String) values.get(i);
            XYSeries serie = new XYSeries(selection);
            for (int j = 0; j < this.mHpositions.size(); j++) {
                HashMap<String, Integer> hm = mHpositions.get(j);
                int value = hm.get(selection);
                serie.add(j + 1, value + 1);
            }
            datas.addSeries(serie);
        }

        JFreeChart chart = ChartFactory.createXYLineChart(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("POSITIONS"), java.util.ResourceBundle.getBundle("tourma/languages/language").getString("ROUND"), java.util.ResourceBundle.getBundle("tourma/languages/language").getString("POSITION"), datas, PlotOrientation.VERTICAL, true, true, true);
        XYPlot plot = chart.getXYPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer(true, true);
        plot.setRenderer(renderer);
        NumberAxis axis = (NumberAxis) plot.getRangeAxis();
        //axis.setRange(1,mHpositions.size());
        axis.setInverted(true);
        axis.setTickUnit(new NumberTickUnit(1));
        axis = (NumberAxis) plot.getDomainAxis();
        axis.setTickUnit(new NumberTickUnit(1));

        cpPositions = new ChartPanel(chart);

        jpnPositions.add(cpPositions, BorderLayout.CENTER);
        repaint();
    }

    protected void updateBalancedTeam() {
        if (cpBalancedTeam != null) {
            jpnBalancedTeam.remove(cpBalancedTeam);
        }

        final DefaultCategoryDataset datas = new DefaultCategoryDataset();


        int[] indices = jlsBalancedTeam.getSelectedIndices();
        for (int i = 0; i < indices.length; i++) {
            for (int j = 0; j < mHTeamBalanced.size(); j++) {
                if (indices[i] == j) {
                    Iterator it = mHTeamBalanced.get(j).keySet().iterator();
                    int minimum = 65535;
                    while (it.hasNext()) {
                        String en = (String) it.next();
                        int nb = mHTeamBalanced.get(j).get(en);
                        datas.addValue(nb, jlsBalancedTeam.getSelectedValuesList().get(i).toString(), en);
                    }
                }
            }
        }

        JFreeChart chart = ChartFactory.createBarChart(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("ADVERSAIRES PAR ÉQUIPE"), java.util.ResourceBundle.getBundle("tourma/languages/language").getString("ADVERSAIRES"), java.util.ResourceBundle.getBundle("tourma/languages/language").getString("NOMBRE DE MATCHS"), datas, PlotOrientation.VERTICAL, true, true, true);

        final CategoryPlot plot = chart.getCategoryPlot();

        final BarRenderer barrenderer = (BarRenderer) plot.getRenderer();
        barrenderer.setDrawBarOutline(false);
        StandardCategoryItemLabelGenerator std = new StandardCategoryItemLabelGenerator();

        barrenderer.setBaseItemLabelGenerator(std);
        barrenderer.setBaseItemLabelsVisible(true);

        barrenderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.CENTER, TextAnchor.CENTER));
        barrenderer.setBaseNegativeItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.CENTER, TextAnchor.CENTER));
        barrenderer.setBaseToolTipGenerator(new StandardCategoryToolTipGenerator(
                "{1} VS {0}: {2}", NumberFormat.getInstance()));

        plot.setRenderer(barrenderer);
        NumberAxis axis = (NumberAxis) plot.getRangeAxis();
        //axis.setRange(1,mHpositions.size());
        axis.setInverted(true);
        axis.setTickUnit(new NumberTickUnit(1));


        cpBalancedTeam = new ChartPanel(chart);
        jpnBalancedTeam.add(cpBalancedTeam, BorderLayout.CENTER);
        repaint();
    }
    
    
    protected void updateBalancedIndiv() {
        if (cpBalancedIndiv != null) {
            jpnBalancedIndiv.remove(cpBalancedIndiv);
        }

        final DefaultCategoryDataset datas = new DefaultCategoryDataset();


        int[] indices = jlsBalancedIndiv.getSelectedIndices();
        for (int i = 0; i < indices.length; i++) {
            for (int j = 0; j < mHIndivBalanced.size(); j++) {
                if (indices[i] == j) {
                    Iterator it = mHIndivBalanced.get(j).keySet().iterator();
                    int minimum = 65535;
                    while (it.hasNext()) {
                        String en = (String) it.next();
                        int nb = mHIndivBalanced.get(j).get(en);
                        datas.addValue(nb, en,jlsBalancedIndiv.getSelectedValuesList().get(i).toString());
                    }
                }
            }
        }

        JFreeChart chart = ChartFactory.createBarChart(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("ADVERSAIRES PAR ÉQUIPE"), java.util.ResourceBundle.getBundle("tourma/languages/language").getString("ADVERSAIRES"), java.util.ResourceBundle.getBundle("tourma/languages/language").getString("NOMBRE DE MATCHS"), datas, PlotOrientation.VERTICAL, true, true, true);

        final CategoryPlot plot = chart.getCategoryPlot();

        final BarRenderer barrenderer = (BarRenderer) plot.getRenderer();
        barrenderer.setDrawBarOutline(false);
        StandardCategoryItemLabelGenerator std = new StandardCategoryItemLabelGenerator();

        barrenderer.setBaseItemLabelGenerator(std);
        barrenderer.setBaseItemLabelsVisible(true);

        barrenderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.CENTER, TextAnchor.CENTER));
        barrenderer.setBaseNegativeItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.CENTER, TextAnchor.CENTER));
        barrenderer.setBaseToolTipGenerator(new StandardCategoryToolTipGenerator(
                "{1} VS {0}: {2}", NumberFormat.getInstance()));

        plot.setRenderer(barrenderer);
        NumberAxis axis = (NumberAxis) plot.getRangeAxis();
        //axis.setRange(1,mHpositions.size());
        axis.setInverted(true);
        axis.setTickUnit(new NumberTickUnit(1));


        cpBalancedIndiv = new ChartPanel(chart);
        jpnBalancedIndiv.add(cpBalancedIndiv, BorderLayout.CENTER);
        repaint();
    }

    protected void updateTeamPositions() {
        if (cpTeamPositions != null) {
            jpnTeamPositions.remove(cpTeamPositions);
        }

        final XYSeriesCollection datas = new XYSeriesCollection();

        List values = jlsTeamPositions.getSelectedValuesList();
        for (int i = 0; i < values.size(); i++) {
            String selection = (String) values.get(i);
            XYSeries serie = new XYSeries(selection);
            for (int j = 0; j < this.mHTeampositions.size(); j++) {
                HashMap<String, Integer> hm = mHTeampositions.get(j);
                int value = hm.get(selection);
                serie.add(j + 1, value + 1);
            }
            datas.addSeries(serie);
        }

        JFreeChart chart = ChartFactory.createXYLineChart(java.util.ResourceBundle.getBundle("tourma/languages/language").getString("POSITIONS"), java.util.ResourceBundle.getBundle("tourma/languages/language").getString("ROUND"), java.util.ResourceBundle.getBundle("tourma/languages/language").getString("POSITION"), datas, PlotOrientation.VERTICAL, true, true, true);
        XYPlot plot = chart.getXYPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer(true, true);
        plot.setRenderer(renderer);
        NumberAxis axis = (NumberAxis) plot.getRangeAxis();
        //axis.setRange(1,mHpositions.size());
        axis.setInverted(true);
        axis.setTickUnit(new NumberTickUnit(1));
        axis = (NumberAxis) plot.getDomainAxis();
        axis.setTickUnit(new NumberTickUnit(1));

        cpTeamPositions = new ChartPanel(chart);

        jpnTeamPositions.add(cpTeamPositions, BorderLayout.CENTER);
        repaint();
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
