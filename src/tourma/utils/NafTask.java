/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.utils;

import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ProgressMonitor;
import javax.swing.SwingWorker;
import tourma.data.Coach;
import tourma.data.Tournament;
import tourma.languages.Translate;
import tourma.views.parameters.JPNParameters;

/**
 *
 * @author WFMJ7631
 */
public class NafTask extends SwingWorker<Void, Void> {

    private ProgressMonitor _progressMonitor;
    private JPanel _jpn;

    public NafTask(ProgressMonitor progressMonitor) {
        _progressMonitor = progressMonitor;
    }

    private static final String CS_DownloadFromNAF = "DownloadFromNAF";
    private static final String CS_Downloading = "Downloading";
    private static final String CS_Download = "Download";

    @Override
    public Void doInBackground() {

        setProgress(0);
        try {
            Thread.sleep(100);

            for (int i = 0; (i < Tournament.getTournament().getCoachsCount()) && (!isCancelled()); i++) {
                Coach c = Tournament.getTournament().getCoach(i);
                _progressMonitor.setNote(
                        Translate.translate(CS_Download)
                        + " " + c.getName());
                boolean valid = false;
                String nick=c.getName();
                NAFCoach nafc = NAF.getCoachByName(nick);
                while (!valid) {

                    if (nafc != null) {
                        c.setNaf(nafc.getId());
                        valid = true;
                    } else {
                        ArrayList<String> proposals = NAF.getNameProposals(nick);

                        proposals.add("Download " + nick);
                        proposals.add("Other");
                        proposals.add(nick + " is non NAF");

                        Object option = JOptionPane.showOptionDialog(null, nick + " is not found. Our proposals are: ", "Not found", JOptionPane.OK_OPTION,
                                JOptionPane.QUESTION_MESSAGE, null, proposals.toArray(), proposals.get(0));

                        int indexOption = (int) option;
                        if (indexOption < 5) {
                            NAFCoach nafc2 = NAF.getCoachByName(proposals.get(indexOption));
                            c.setNaf(nafc2.getId());
                            c.setName(nafc2.getName());
                            valid = true;
                        } else {
                            if (indexOption == 5) {
                                c.setNafRank(NAF.getRanking(c.getName(), c));
                                valid = true;
                            } else {
                                if (indexOption == 6) {
                                    Object new_nick = JOptionPane.showInputDialog("Enter correct nickname", c.getName());
                                    nick=(String)new_nick;
                                    nafc = NAF.getCoachByName(nick);
                                    valid = false;
                                }
                                else
                                {
                                    valid=true;
                                }
                            }

                        }
                    }

                }
                _progressMonitor.setProgress(i + 1);
            }

        } catch (InterruptedException ignore) {
        }
        return null;
    }

    @Override
    public void done() {
        Toolkit.getDefaultToolkit().beep();

        _progressMonitor.setProgress(0);
        _progressMonitor.close();

        if (_jpn instanceof JPNParameters) {
            ((JPNParameters) _jpn).update();
        }
    }

}
