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
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
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
import org.jfree.data.UnknownKeyException;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.TextAnchor;
import org.jfree.util.Rotation;
import tourma.data.Coach;
import tourma.data.CoachMatch;
import tourma.data.Criteria;
import tourma.data.ETeamPairing;
import tourma.data.Group;
import tourma.data.Match;
import tourma.data.RosterType;
import tourma.data.Round;
import tourma.data.Team;
import tourma.data.Tournament;
import tourma.data.Value;
import tourma.languages.Translate;
import tourma.tableModel.MjtRankingIndiv;
import tourma.tableModel.MjtRankingTeam;

/**
 *
 * @author WFMJ7631
 */
public final class JPNStatistics extends javax.swing.JPanel {

    private Tournament mTournament;
    private final ArrayList<HashMap<String, Integer>> mHpositions = new ArrayList<>();
    private final ArrayList<HashMap<String, Integer>> mHTeampositions = new ArrayList<>();
    private ChartPanel cpPositions = null;
    private ChartPanel cpTeamPositions = null;
    private ChartPanel cpBalancedTeam = null;
    private ChartPanel cpBalancedIndiv = null;
    private final JPanel jpnPositions = new JPanel(new BorderLayout());
    private final JPanel jpnTeamPositions = new JPanel(new BorderLayout());
    private JList jlsPositions = null;
    private JList jlsTeamPositions = null;
    private final JPanel jpnBalancedTeam = new JPanel(new BorderLayout());
    private JList jlsBalancedTeam = null;
    private final ArrayList<HashMap<String, Integer>> mHTeamBalanced = new ArrayList<>();
    private final JPanel jpnBalancedIndiv = new JPanel(new BorderLayout());
    private JList jlsBalancedIndiv = null;
    private final ArrayList<HashMap<String, Integer>> mHIndivBalanced = new ArrayList<>();

    public JTabbedPane getTabbedPane() {
        return jtpStatistics;
    }

    /**
     * Creates new form JPNStatistics
     */
    public JPNStatistics() {

        mTournament = Tournament.getTournament();
        initComponents();

        addRosterPie();
        
        if (mTournament.getGroupsCount() > 1) {
            addGroupPie();
        }
        if (mTournament.getParams().isTeamTournament()) {
            addTeamPositions();
            if (mTournament.getParams().getTeamPairing() == ETeamPairing.INDIVIDUAL_PAIRING) {
                addTeamBalanced();
                addIndivBalanced();
            }
        }

        addWinLoss();
        addCounterPerRoster();
        addPointsAverage();
        addPositions();
    }

    private final static String CS_OpponentByTeam = "OPPONENT BY TEAM";
    private final static String CS_OpponentByCoach = "OPPOSITIONS PAR JOUEUR";

    /**
     *
     */
    private void addTeamBalanced() {

        ArrayList<Team> teams = new ArrayList<>();
        for (int cpt = 0; cpt < Tournament.getTournament().getTeamsCount(); cpt++) {
            teams.add(Tournament.getTournament().getTeam(cpt));
        }
        DefaultListModel model = new DefaultListModel();
        for (Team team : teams) {
            model.addElement(team.getName());
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
        for (int i = 0; i < mTournament.getTeamsCount(); i++) {
            Team t = mTournament.getTeam(i);

            HashMap<String, Integer> hm = new HashMap<>();
            for (int j = 0; j < mTournament.getTeamsCount(); j++) {
                Team opp = mTournament.getTeam(j);
                hm.put(opp.getName(), 0);
            }

            for (int j = 0; j < t.getCoachsCount(); j++) {
                Coach c = t.getCoach(j);
                for (int k = 0; k < c.getMatchCount(); k++) {
                    Match mMatch = c.getMatch(k);
                    CoachMatch m = (CoachMatch) mMatch;
                    Coach opp;
                    if (m.getCompetitor1() == c) {
                        opp = (Coach) m.getCompetitor2();
                    } else {
                        opp = (Coach) m.getCompetitor1();
                    }
                    Team other = opp.getTeamMates();
                    int nb = hm.get(other.getName());
                    nb += 1;
                    hm.put(other.getName(), nb);
                }
            }
            mHTeamBalanced.add(hm);
        }
        // update positions
        updateBalancedTeam();

        jtpStatistics.addTab(
                Translate.translate(CS_OpponentByTeam),
                jpnBalancedTeam);

    }

    /**
     *
     */
    private void addIndivBalanced() {

        final ArrayList<Coach> coachs = new ArrayList<>();

        for (int i = 0; i < Tournament.getTournament().getCoachsCount(); i++) {
            coachs.add(Tournament.getTournament().getCoach(i));
        }
        DefaultListModel model = new DefaultListModel();
        for (Coach coach : coachs) {
            model.addElement(coach.getName());
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
        for (int i = 0; i < mTournament.getCoachsCount(); i++) {
            Coach c = mTournament.getCoach(i);

            HashMap<String, Integer> hm = new HashMap<>();
            for (int j = 0; j < mTournament.getTeamsCount(); j++) {
                Team opp = mTournament.getTeam(j);
                hm.put(opp.getName(), 0);
            }

            for (int j = 0; j < c.getMatchCount(); j++) {
                Match mMatch = c.getMatch(j);
                CoachMatch m = (CoachMatch) mMatch;
                Coach opp;
                if (m.getCompetitor1() == c) {
                    opp = (Coach) m.getCompetitor2();
                } else {
                    opp = (Coach) m.getCompetitor1();
                }
                Team other = opp.getTeamMates();
                int nb = hm.get(other.getName());
                nb += 1;
                hm.put(other.getName(), nb);
            }

            mHIndivBalanced.add(hm);
        }
        // update positions
        updateBalancedIndiv();

        jtpStatistics.addTab(
                Translate.translate(CS_OpponentByCoach),
                jpnBalancedIndiv);

    }

    private static final String CS_Done = "RÉALISÉS";
    private static final String CS_DoneAgainst = "SUBIS";
    private static final String CS_ValueByRoster = "VALUE PAR ROSTER";
    private static final String CS_Roster = "ROSTER";
    private static final String CS_Number = "NUMBER";

    /**
     *
     */
    private void addCounterPerRoster() {

        for (int i = 0; i < mTournament.getParams().getCriteriaCount(); i++) {
            Criteria crit = mTournament.getParams().getCriteria(i);
            final HashMap<String, Double> plus = new HashMap<>();
            final HashMap<String, Double> minus = new HashMap<>();
            final HashMap<String, Double> total = new HashMap<>();

            for (int j = 0; j < RosterType.getRostersNamesCount(); j++) {
                String roster = RosterType.getRostersName(j);
                plus.put(roster, 0.0);
                minus.put(roster, 0.0);
                total.put(roster, 0.0);
            }

            for (int j = 0; j < mTournament.getRoundsCount(); j++) {
                final Round r = mTournament.getRound(j);
                for (int k = 0; k < r.getCoachMatchs().size(); k++) {
                    final CoachMatch m = r.getCoachMatchs().get(k);
                    if ((m.getCompetitor1() != Coach.getNullCoach()) && (m.getCompetitor2() != Coach.getNullCoach())) {
                        final Value values = m.getValue(crit);

                        String name1;
                        String name2;
                        if (m.getRoster1() == null) {
                            name1 = ((Coach) m.getCompetitor1()).getRoster().getName();
                        } else {
                            name1 = m.getRoster1().getName();
                        }

                        if (m.getRoster2() == null) {
                            name2 = ((Coach) m.getCompetitor2()).getRoster().getName();
                        } else {
                            name2 = m.getRoster2().getName();
                        }

                        double pt = plus.get(name1);
                        double tot = total.get(name1);
                        double mt = minus.get(name1);
                        pt += values.getValue1();
                        mt -= values.getValue2();
                        tot += 1.0;

                        plus.put(name1, pt);
                        minus.put(name1, mt);
                        total.put(name1, tot);

                        pt = plus.get(name2);
                        mt = minus.get(name2);
                        tot = total.get(name2);
                        pt += values.getValue2();
                        mt -= values.getValue1();
                        tot += 1.0;

                        plus.put(name2, pt);
                        minus.put(name2, mt);
                        total.put(name2, tot);
                    }
                }
            }

            final DefaultCategoryDataset datas = new DefaultCategoryDataset();

            for (int cpt = 0; cpt < RosterType.getRostersNamesCount(); cpt++) {
                String roster = RosterType.getRostersName(cpt);
                final double pt = plus.get(roster);
                final double mt = minus.get(roster);
                final double tot = total.get(roster);
                if (((pt != 0) || (mt != 0)) && (tot != 0)) {
                    datas.addValue(pt / tot,
                            Translate.translate(CS_Done),
                            roster);
                    datas.addValue(mt / tot,
                            Translate.translate(CS_DoneAgainst),
                            roster);
                }
            }

            final JFreeChart chart = ChartFactory.createStackedBarChart(
                    Translate.translate(CS_ValueByRoster),
                    Translate.translate(CS_Roster),
                    Translate.translate(CS_Number),
                    datas, PlotOrientation.HORIZONTAL, true, true, false);

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
            jtpStatistics.addTab(crit.getName(), chartPanel);
        }

    }

    private static final String CS_Victories = "VICTOIRES";
    private static final String CS_Drawns = "NULS";
    private static final String CS_Losts = "DÉFAITES";
    private static final String CS_ResultsByRoster = "RESULTATS PAR ROSTER";

    /**
     *
     */
    private void addWinLoss() {

        final HashMap<String, Double> victories = new HashMap<>();
        final HashMap<String, Double> draw = new HashMap<>();
        final HashMap<String, Double> loss = new HashMap<>();
        final HashMap<String, Double> total = new HashMap<>();

        for (int cpt = 0; cpt < RosterType.getRostersNamesCount(); cpt++) {
            String roster = RosterType.getRostersName(cpt);
            victories.put(roster, 0.0);
            draw.put(roster, 0.0);
            loss.put(roster, 0.0);
            total.put(roster, 0.0);
        }

        final Criteria Td = mTournament.getParams().getCriteria(0);
        for (int i = 0; i < mTournament.getRoundsCount(); i++) {
            final Round r = mTournament.getRound(i);
            for (int j = 0; j < r.getCoachMatchs().size(); j++) {
                final CoachMatch m = r.getCoachMatchs().get(j);
                if ((m.getCompetitor1() != Coach.getNullCoach()) && (m.getCompetitor2() != Coach.getNullCoach())) {
                    final Value values = m.getValue(Td);

                    String name1;
                    String name2;
                    if (m.getRoster1() == null) {
                        name1 = ((Coach) m.getCompetitor1()).getRoster().getName();
                    } else {
                        name1 = m.getRoster1().getName();
                    }

                    if (m.getRoster2() == null) {
                        name2 = ((Coach) m.getCompetitor2()).getRoster().getName();
                    } else {
                        name2 = m.getRoster2().getName();
                    }

                    try {
                        double t = total.get(name2).intValue();
                        t += 1.0;
                        total.put(name2, t);
                        t = total.get(name1).intValue();
                        t += 1.0;
                        total.put(name1, t);
                        if (values.getValue1() > values.getValue2()) {
                            double v = victories.get(name1);
                            double l = loss.get(name2);
                            v++;
                            l++;
                            victories.put(name1, v);
                            loss.put(name2, l);
                        }
                        if (values.getValue1() < values.getValue2()) {
                            double v = victories.get(name2);
                            double l = loss.get(name1);
                            v++;
                            l++;

                            victories.put(name2, v);
                            loss.put(name1, l);
                        }
                        if (values.getValue1() == values.getValue2()) {
                            double d = draw.get(name2);
                            d++;
                            draw.put(name2, d);

                            d = draw.get(name1);
                            d++;
                            draw.put(name1, d);
                        }
                    } catch (NullPointerException npe) {
                        System.err.println("Unknown roster: " + name2 + " or " + name1);
                    }
                }
            }
        }
        final DefaultCategoryDataset datas = new DefaultCategoryDataset();

        for (int cpt = 0; cpt < RosterType.getRostersNamesCount(); cpt++) {
            String roster = RosterType.getRostersName(cpt);
            final double v = victories.get(roster);
            final double d = draw.get(roster);
            final double l = loss.get(roster);
            final double t = total.get(roster);
            if (((v != 0) || (d != 0) || (l != 0)) && (t != 0.0)) {
                datas.addValue(v / t,
                        Translate.translate(CS_Victories), roster);
                datas.addValue(d / t,
                        Translate.translate(CS_Drawns), roster);
                datas.addValue(l / t,
                        Translate.translate(CS_Losts), roster);
            }
        }

        final JFreeChart chart = ChartFactory.createStackedBarChart(
                Translate.translate(CS_ResultsByRoster),
                Translate.translate(CS_Roster),
                Translate.translate(CS_Number),
                datas, PlotOrientation.VERTICAL, true, true, false);

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
        jtpStatistics.addTab(Translate.translate(CS_Results), chartPanel);

    }

    private final static String CS_Results = "RESULTATS";

    /**
     *
     */
    private void addRosterPie() {

        final DefaultPieDataset datas = new DefaultPieDataset();

        for (int cpt = 0; cpt < RosterType.getRostersNamesCount(); cpt++) {
            String roster = RosterType.getRostersName(cpt);
            datas.setValue(roster, 0);
        }

        for (int i = 0; i < mTournament.getCoachsCount(); i++) {
            final Coach c = mTournament.getCoach(i);
            if (c != Coach.getNullCoach()) {
                ArrayList<String> names = new ArrayList<>();
                for (int j = 0; j < c.getMatchCount(); j++) {
                    Match mMatch = c.getMatch(j);

                    CoachMatch m = (CoachMatch) mMatch;
                    RosterType r;
                    if (c == m.getCompetitor1()) {
                        r = m.getRoster1();
                    } else {
                        r = m.getRoster2();
                    }
                    if (r == null) {
                        r = c.getRoster();
                    }

                    //if (!names.contains(r.getName())) {
                    names.add(r.getName());

                    //}
                }

                for (String rName : names) {
                    try {
                        final Number value = datas.getValue(rName);

                        int v = 0;
                        if (value != null) {
                            v = value.intValue();
                        }
                        v++;
                        datas.setValue(rName, v);
                    } catch (NullPointerException npe) {

                    } catch (UnknownKeyException uke) {
                        System.out.println("Unknown roster: " + uke.getLocalizedMessage());
                    }

                }
            }
        }

        int i = 0;
        while (i < datas.getItemCount()) {
            final Number value = datas.getValue(i);
            if (value.intValue() == 0) {
                datas.remove(datas.getKey(i));
            } else {
                if (!Tournament.getTournament().getParams().isMultiRoster()) {
                    double d = value.doubleValue();
                    d /= Tournament.getTournament().getRoundsCount();
                    datas.setValue(datas.getKey(i), d);
                }
                i++;
            }
        }
        final JFreeChart chart = ChartFactory.createPieChart(
                Translate.translate(CS_Roster),
                datas, true, true, false);

        final PiePlot plot = (PiePlot) chart.getPlot();
        final StandardPieSectionLabelGenerator label = new StandardPieSectionLabelGenerator(
                "{0}: {1} ({2})");
        plot.setLegendLabelGenerator(label);
        plot.setLabelGenerator(label);
        plot.setStartAngle(290);
        plot.setDirection(Rotation.CLOCKWISE);
        plot.setForegroundAlpha(0.5f);

        final ChartPanel chartPanel = new ChartPanel(chart);

        chartPanel.setName("RosterPie");

        jtpStatistics.addTab(Translate.translate(CS_Roster), chartPanel);
    }

    private final static String CS_Groups = "GROUPS";

    /**
     *
     */
    private void addGroupPie() {

        final DefaultPieDataset datas = new DefaultPieDataset();

        for (int i = 0; i < mTournament.getGroupsCount(); i++) {
            int value = 0;
            Group g = mTournament.getGroup(i);
            for (int j = 0; j < mTournament.getCoachsCount(); j++) {
                final Coach c = mTournament.getCoach(j);
                if (c != Coach.getNullCoach()) {
                    for (int k = 0; k < g.getRosterCount(); k++) {
                        RosterType mRoster = g.getRoster(k);

                        if (c.getRoster() != null) {
                            if (mRoster.getName().equals(c.getRoster().getName())) {
                                value++;
                            }
                        }
                    }

                }

            }
            if (value > 0) {
                datas.setValue(mTournament.getGroup(i).getName(), value);
            }
        }
        int i = 0;
        while (i < datas.getItemCount()) {
            final Number value = datas.getValue(i);
            if (value.intValue() == 0) {
                datas.remove(datas.getKey(i));
            } else {
                i++;
            }
        }

        final JFreeChart chart = ChartFactory.createPieChart(
                Translate.translate(CS_Groups), datas, true, true, false);

        final PiePlot plot = (PiePlot) chart.getPlot();
        final StandardPieSectionLabelGenerator label = new StandardPieSectionLabelGenerator(
                "{0}: {1} ({2})");
        plot.setLegendLabelGenerator(label);
        plot.setLabelGenerator(label);
        plot.setStartAngle(290);
        plot.setDirection(Rotation.CLOCKWISE);
        plot.setForegroundAlpha(0.5f);

        final ChartPanel chartPanel = new ChartPanel(chart);
        jtpStatistics.addTab(Translate.translate(CS_Groups), chartPanel);
    }

    /**
     * Update panel
     */
    public void update() {

        if (mTournament.getParams().isTeamTournament()) {
            updateTeamPositions();
            if (mTournament.getParams().getTeamPairing() == ETeamPairing.INDIVIDUAL_PAIRING) {
                updateBalancedIndiv();
                updateBalancedTeam();
            }
        }
        updatePositions();

    }

    /**
     *
     */
    private void addPointsAverage() {

        final HashMap<String, Double> avg = new HashMap<>();
        final HashMap<String, Double> avg_opp = new HashMap<>();
        final HashMap<String, Double> count = new HashMap<>();

        for (int cpt = 0; cpt < RosterType.getRostersNamesCount(); cpt++) {
            String roster = RosterType.getRostersName(cpt);
            avg.put(roster, 0.0);
            avg_opp.put(roster, 0.0);
            count.put(roster, 0.0);
        }

        // Récupération des matchs
        for (int i = 0; i < mTournament.getRoundsCount(); i++) {
            ArrayList<CoachMatch> acm = mTournament.getRound(i).getCoachMatchs();
            for (CoachMatch cm : acm) {
                if ((cm.getCompetitor1() != Coach.getNullCoach()) && (cm.getCompetitor2() != Coach.getNullCoach())) {

                    double p1 = cm.getPointsByCoach((Coach) cm.getCompetitor1(), cm, true, true);
                    double p2 = cm.getPointsByCoach((Coach) cm.getCompetitor2(), cm, true, true);

                    double avg_value = 0.0;
                    double avg_opp_value = 0.0;
                    double count_value = 0.0;
                    Coach c1 = (Coach) cm.getCompetitor1();
                    Coach c2 = (Coach) cm.getCompetitor2();

                    if (c1 != null) {
                        if (c1.getRoster() != null) {
                            if (c1.getRoster().getName() != null) {
                                avg_value = avg.get(c1.getRoster().getName());
                                avg_opp_value = avg_opp.get(c1.getRoster().getName());
                                count_value = count.get(c1.getRoster().getName());
                            }
                        }
                    }

                    avg_value = (avg_value * count_value + p1) / (count_value + 1.0);
                    avg_opp_value = (avg_opp_value * count_value + p2) / (count_value + 1.0);
                    count_value += 1;

                    if (c1 != null) {
                        if (c1.getRoster() != null) {
                            avg.put(c1.getRoster().getName(), avg_value);
                            avg_opp.put(c1.getRoster().getName(), avg_opp_value);
                            count.put(c1.getRoster().getName(), count_value);
                        }
                    }

                    if (c2 != null) {
                        if (c2.getRoster() != null) {
                            if (c2.getRoster().getName() != null) {
                                avg_value = avg.get(c2.getRoster().getName());
                                avg_opp_value = avg_opp.get(c2.getRoster().getName());
                                count_value = count.get(c2.getRoster().getName());
                            }
                        }
                    }

                    avg_value = (avg_value * count_value + p2) / (count_value + 1.0);
                    avg_opp_value = (avg_opp_value * count_value + p1) / (count_value + 1.0);
                    count_value += 1;

                    if (c2 != null) {
                        if (c2.getRoster() != null) {
                            avg.put(c2.getRoster().getName(), avg_value);
                            avg_opp.put(c2.getRoster().getName(), avg_opp_value);
                            count.put(c2.getRoster().getName(), count_value);
                        }
                    }
                }
            }
        }
        // Affichage du graphique
        final DefaultCategoryDataset datas = new DefaultCategoryDataset();

        for (int cpt = 0; cpt < RosterType.getRostersNamesCount(); cpt++) {
            String roster = RosterType.getRostersName(cpt);
            final double avg_value = avg.get(roster);
            final double avg_opp_value = avg_opp.get(roster);
            if ((avg_value != 0) && (avg_opp_value != 0)) {
                datas.addValue(avg_value,
                        Translate.translate(CS_Points),
                        roster);
                datas.addValue(avg_opp_value,
                        Translate.translate(CS_OpponentsPoints),
                        roster);
            }
        }

        final JFreeChart chart = ChartFactory.createStackedBarChart(
                Translate.translate(CS_ResultsByRoster),
                Translate.translate(CS_Roster),
                Translate.translate(CS_Number),
                datas, PlotOrientation.VERTICAL, true, true, false);

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
        jtpStatistics.addTab(Translate.translate(CS_Points), chartPanel);

    }

    private final static String CS_Points = "POINTS";
    private final static String CS_OpponentsPoints = "POINTS ADVERSAIRES";
    private final static String CS_Rankings = "POSITIONS";
    private final static String CS_TeamRankings = "TEAM POSITIONS";

    /**
     *
     */
    private void addPositions() {
        // creation et partage du panel

        // creation de la list de checkbox
        final ArrayList<Coach> coach = new ArrayList<>();
        for (int i = 0; i < Tournament.getTournament().getCoachsCount(); i++) {
            coach.add(Tournament.getTournament().getCoach(i));
        }
        DefaultListModel model = new DefaultListModel();
        for (Coach coach1 : coach) {
            model.addElement(coach1.getName());
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
        int column_name = 2;
        if (mTournament.getParams().isTeamTournament()) {
            column_name = 3;
        }
        for (int i = 0; i < mTournament.getRoundsCount(); i++) {
            //Round r = mTournament.getRound(i);
            MjtRankingIndiv ranking = new MjtRankingIndiv(i, mTournament.getParams().getRankingIndiv1(), mTournament.getParams().getRankingIndiv2(), mTournament.getParams().getRankingIndiv3(), mTournament.getParams().getRankingIndiv4(), mTournament.getParams().getRankingIndiv5(),
                    coach, mTournament.getParams().isTeamTournament(),
                    false,
                    false,false);

            ArrayList<String> coach_names = new ArrayList<>();
            int count = ranking.getRowCount();
            for (int j = 0; j < count; j++) {
                coach_names.add((String) ranking.getValueAt(j, column_name));
            }
            HashMap<String, Integer> hm = new HashMap<>();
            for (Coach c : coach) {
                hm.put(c.getName(), coach_names.indexOf(c.getName()));
            }
            mHpositions.add(hm);
        }
        // update positions
        updatePositions();

        jtpStatistics.addTab(Translate.translate(CS_Rankings), jpnPositions);

    }

    /**
     *
     */
    private void addTeamPositions() {
        // creation et partage du panel

        // creation de la list de checkbox
        ArrayList<Team> team = new ArrayList<>();
        for (int cpt = 0; cpt < Tournament.getTournament().getTeamsCount(); cpt++) {
            team.add(Tournament.getTournament().getTeam(cpt));
        }

        DefaultListModel model = new DefaultListModel();
        for (Team team1 : team) {
            model.addElement(team1.getName());
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
        if (mTournament.getParams().isTeamTournament()) {
            for (int i = 0; i < mTournament.getRoundsCount(); i++) {

                ArrayList<Team> teams = new ArrayList<>();
                for (int cpt = 0; cpt < Tournament.getTournament().getTeamsCount(); cpt++) {
                    teams.add(Tournament.getTournament().getTeam(cpt));
                }
                MjtRankingTeam ranking = new MjtRankingTeam(
                        mTournament.getParams().isTeamVictoryOnly(),
                        i,
                        teams,
                        false);

                ArrayList<String> team_names = new ArrayList<>();
                int count = ranking.getRowCount();
                for (int j = 0; j < count; j++) {
                    team_names.add((String) ranking.getValueAt(j, column_name));
                }
                HashMap<String, Integer> hm = new HashMap<>();
                for (Team c : team) {
                    hm.put(c.getName(), team_names.indexOf(c.getName()));
                }
                mHTeampositions.add(hm);
            }
        }
        // update positions
        updateTeamPositions();

        jtpStatistics.addTab(Translate.translate(CS_TeamRankings), jpnTeamPositions);

    }

    private final static String CS_Ranking = "Position";
    private final static String CS_Round = "Round";

    /**
     *
     */
    public void updatePositions() {
        if (cpPositions != null) {
            jpnPositions.remove(cpPositions);
        }

        final XYSeriesCollection datas = new XYSeriesCollection();

        List values = jlsPositions.getSelectedValuesList();
        for (Object value1 : values) {
            String selection = (String) value1;
            XYSeries serie = new XYSeries(selection);
            for (int j = 0; j < this.mHpositions.size(); j++) {
                HashMap<String, Integer> hm = mHpositions.get(j);
                int value = hm.get(selection);
                serie.add(j + 1, value + 1);
            }
            datas.addSeries(serie);
        }

        JFreeChart chart = ChartFactory.createXYLineChart(
                Translate.translate(CS_Rankings),
                Translate.translate(CS_Round),
                Translate.translate(CS_Ranking),
                datas, PlotOrientation.VERTICAL, true, true, true);
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

    /**
     *
     */
    public void updateBalancedTeam() {
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

        JFreeChart chart = ChartFactory.createBarChart(
                Translate.translate(CS_OpponentsByTeam),
                Translate.translate(CS_Opponents),
                Translate.translate(CS_MatchsCount),
                datas, PlotOrientation.VERTICAL, true, true, true);

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

    private static final String CS_OpponentsByTeam = "ADVERSAIRES PAR ÉQUIPE";
    private static final String CS_OpponentsByCoach = "ADVERSAIRES COACH";
    private static final String CS_Opponents = "ADVERSAIRES";
    private static final String CS_MatchsCount = "NOMBRE DE MATCHS";

    /**
     * Update individual positions graph
     */
    public void updateBalancedIndiv() {
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
                        datas.addValue(nb, en, jlsBalancedIndiv.getSelectedValuesList().get(i).toString());
                    }
                }
            }
        }

        JFreeChart chart = ChartFactory.createBarChart(
                Translate.translate(CS_OpponentsByCoach),
                Translate.translate(CS_Opponents),
                Translate.translate(CS_MatchsCount),
                datas, PlotOrientation.VERTICAL, true, true, true);

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

    /**
     * Update Teams positions graph
     */
    public void updateTeamPositions() {
        if (cpTeamPositions != null) {
            jpnTeamPositions.remove(cpTeamPositions);
        }

        final XYSeriesCollection datas = new XYSeriesCollection();

        List values = jlsTeamPositions.getSelectedValuesList();
        for (Object value1 : values) {
            String selection = (String) value1;
            XYSeries serie = new XYSeries(selection);
            for (int j = 0; j < this.mHTeampositions.size(); j++) {
                HashMap<String, Integer> hm = mHTeampositions.get(j);
                int value = hm.get(selection);
                serie.add(j + 1, value + 1);
            }
            datas.addSeries(serie);
        }

        JFreeChart chart = ChartFactory.createXYLineChart(
                Translate.translate(CS_Rankings),
                Translate.translate(CS_Round),
                Translate.translate(CS_Ranking),
                datas, PlotOrientation.VERTICAL, true, true, true);
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
    private static final Logger LOG = Logger.getLogger(JPNStatistics.class.getName());

    private void writeObject(java.io.ObjectOutputStream stream) throws java.io.IOException {
        throw new java.io.NotSerializableException(getClass().getName());
    }

    private void readObject(java.io.ObjectInputStream stream) throws java.io.IOException, ClassNotFoundException {
        throw new java.io.NotSerializableException(getClass().getName());
    }
}
