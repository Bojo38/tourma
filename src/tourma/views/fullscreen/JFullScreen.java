/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.views.fullscreen;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.Socket;
import java.rmi.RemoteException;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ScrollPaneConstants;
import tourma.data.Coach;
import tourma.data.IWithNameAndPicture;
import tourma.data.Tournament;
import tourma.utility.Sleeping;
import tourma.utility.Suspendable;
import tourma.utils.ImageTreatment;

/**
 *
 * @author WFMJ7631
 */
public abstract class JFullScreen extends javax.swing.JDialog {

    protected Socket socket;
    protected ClientLoop cl;
    protected Semaphore semStart = new Semaphore(1);

    private static final Logger LOG = Logger.getLogger(JFullScreenMatchs.class.getName());
    protected GraphicsDevice mSelectedGD;
    
    public JFullScreen(Socket s) throws IOException {
        super();
        try {
            semStart.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(JFullScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
        //      this.setUndecorated(true);
        //      this.setState(JFrame.MAXIMIZED_BOTH);

        //setExtendedState();
        initComponents();
        GridBagLayout gbl = new GridBagLayout();
        jpnContent.setLayout(gbl);

        socket = s;

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] gs = ge.getScreenDevices();
        int screen = 0;
        if (gs.length > 1) {
            Integer options[] = new Integer[gs.length];
            for (int i = 0; i < gs.length; i++) {
                options[i] = i;
            }
            Object val = JOptionPane.showOptionDialog(null, "Please Select a screen index", "Screen Selection", JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            if (val instanceof Integer) {
                screen = ((Integer) val).intValue();
                mSelectedGD=gs[screen];
            }
            else
            {
                mSelectedGD=gs[0];
            }
        }

        cl = new ClientLoop(this, screen);
        cl.start();
    }

    /**
     * Creates new form FullScreen
     */
    public JFullScreen() {
        super();

        this.setUndecorated(true);
        //this.setState(JDialog.);
        this.setAlwaysOnTop(true);
        
        mSelectedGD=GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        
        initComponents();
        GridBagLayout gbl = new GridBagLayout();
        jpnContent.setLayout(gbl);

    }

    protected JLabel getLabelForObject(IWithNameAndPicture object, int height, int width, Font f, Color bkg) {
        return getLabelForObject(object, height, width, f, bkg, false, 0);
    }

    /**
     *
     * @param object
     * @param height
     * @param width
     * @param f
     * @param bkg
     * @param right
     * @return
     */
    @SuppressWarnings("SuspiciousNameCombination")
    protected JLabel getLabelForObject(IWithNameAndPicture object, int height, int width, Font f, Color bkg, boolean right, int matchIndex) {

        JLabel l = new JLabel();
        try {
            if ((object.getPicture() != null) && (Tournament.getTournament().getParams().isUseImage())) {
                l.setIcon(ImageTreatment.resize(object.getPicture(), height, height));
            } else {
                if (!(object instanceof Coach)) {
                    l.setIcon(new ImageIcon(new BufferedImage(height, height, BufferedImage.TYPE_4BYTE_ABGR)));
                }
            }
        } catch (RemoteException re) {
            re.printStackTrace();
        }

        //l.setPreferredSize(new Dimension(width, height));
        l.setFont(f);
        l.setOpaque(true);
        l.setBackground(bkg);

        try {
            String text = object.getName();
            if ((object instanceof Coach) && (Tournament.getTournament().getParams().isDisplayRoster())) {
                Coach c = (Coach) object;
                if (right) {
                    l.setText("<HTML>" + c.getName() + " <i>(" + c.getMatchRoster(matchIndex) + ")</i></HTML>");
                } else {
                    l.setText("<HTML><i>(" + c.getMatchRoster(matchIndex) + ")</i> " + c.getName() + "</HTML>");
                }
            } else {
                l.setText(text);
            }
        } catch (RemoteException re) {
            re.printStackTrace();
        }
        return l;
    }

    /**
     *
     * @param x
     * @param y
     * @param height
     * @param width
     * @return
     */
    protected GridBagConstraints getGridbBagConstraints(int x, int y, int height, int width) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.gridheight = height;
        gbc.gridwidth = width;
        return gbc;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jscrp = new javax.swing.JScrollPane();
        jpnContent = new javax.swing.JPanel();
        jpnNorth = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setAlwaysOnTop(true);
        setAutoRequestFocus(false);
        setName("FullScreen Tourma"); // NOI18N
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jpnContentLayout = new javax.swing.GroupLayout(jpnContent);
        jpnContent.setLayout(jpnContentLayout);
        jpnContentLayout.setHorizontalGroup(
            jpnContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 398, Short.MAX_VALUE)
        );
        jpnContentLayout.setVerticalGroup(
            jpnContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 298, Short.MAX_VALUE)
        );

        jscrp.setViewportView(jpnContent);

        getContentPane().add(jscrp, java.awt.BorderLayout.CENTER);

        jpnNorth.setLayout(new java.awt.GridBagLayout());
        getContentPane().add(jpnNorth, java.awt.BorderLayout.PAGE_START);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    protected boolean animationStarted = false;
    protected Animation animation;

    /**
     * Animation inner class
     */
    protected class Animation extends Thread implements Suspendable {

        private boolean suspended = false;
        private long millis = 8000;
        private long computedTime;
        private long ncomputedTime=0;

        synchronized void incrementSync() {
            millis = millis - millis * 10 / 100;
            
            double decr=computedTime*1000000+ncomputedTime;
            decr=decr*0.9;
            computedTime=new Double(Math.floor(decr/1000000)).longValue();
            ncomputedTime=new Double(decr-computedTime*1000000).longValue();
            
            //computedTime=computedTime-decr;
        }

        synchronized void decrementSync() {
            millis = millis + millis * 10 / 100;
           double decr=computedTime*1000000+ncomputedTime;
            decr=decr*1.1;
            computedTime=new Double(Math.floor(decr/1000000)).longValue();
            ncomputedTime=new Double(decr-computedTime*1000000).longValue();
        }

        @Override
        public void setSuspended(boolean s) {
            suspended = s;
        }

        @SuppressFBWarnings(value = "SWL_SLEEP_WITH_LOCK_HELD", justification = "Sleep is used for animation")
        @Override
        @SuppressWarnings("SleepWhileInLoop")
        public void run() {
            computedTime = mSelectedGD.getDisplayMode().getHeight() / 100;
            int lastValue = 0;
            try {
                semAnimate.acquire();
            } catch (InterruptedException ex) {
                Logger.getLogger(JFullScreenMatchs.class.getName()).log(Level.SEVERE, null, ex);
            }

            Sleeping spleeping = new Sleeping(this);
            while (animationStarted) {
                int value = jscrp.getVerticalScrollBar().getValue();
                value += 1;
                if (value <= lastValue) {
                    synchronized (this) {
                        suspended = true;

                        spleeping.sleep(millis, 0);
                        while (suspended && animationStarted) {
                            try {
                                wait();
                            } catch (InterruptedException ex) {
                                Logger.getLogger(JFullScreenMatchs.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                    semAnimate.release();
                    synchronized (this) {
                        suspended = true;

                        spleeping.sleep(millis, 0);
                        while (suspended && animationStarted) {
                            try {
                                wait();
                            } catch (InterruptedException ex) {
                                Logger.getLogger(JFullScreenMatchs.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                    try {
                        semAnimate.acquire();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(JFullScreenMatchs.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    value = 0;
                    jscrp.getVerticalScrollBar().setValue(jscrp.getVerticalScrollBar().getMinimum());
                } else {
                    jscrp.getVerticalScrollBar().setValue(value);
                }

                synchronized (this) {
                    suspended = true;

                    spleeping.sleep(computedTime, (int) ncomputedTime);
                    while (suspended && animationStarted) {
                        try {
                            wait();
                        } catch (InterruptedException ex) {
                            Logger.getLogger(JFullScreenMatchs.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }

                //System.out.println("Current value: " + value + " last Value: " + lastValue);
                lastValue = value;
            }
            semAnimate.release();
        }
    }

    protected abstract void clientLoop(int screen) throws InterruptedException;

    protected abstract void setStop(boolean s);

    protected class ClientLoop extends Thread {

        JFullScreen parentFrame;
        int _screen;

        ClientLoop(JFullScreen fs, int screen) {
            parentFrame = fs;
            screen = screen;
        }

        public void setStop(boolean s) {
            parentFrame.setStop(s);
        }

        public void run() {
            try {
                parentFrame.clientLoop(_screen);
            } catch (InterruptedException ie) {
                LOG.log(Level.INFO, "Sleep interrupted, probably before exiting");
            } finally {
                semAnimate.release();
            }
        }
    }

    protected Semaphore semAnimate = new Semaphore(1);

    protected void keyPressed(KeyEvent evt) {
//        LOG.log(Level.FINE, "KeyPressed: " + evt.getKeyChar());
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            this.dispose();
            if (socket != null) {
                if (cl != null) {
                    cl.setStop(true);
                    cl.interrupt();
                }
                try {
                    socket.close();
                } catch (IOException ex) {
                    Logger.getLogger(JFullScreen.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.exit(0);
            }
        }
        if (evt.getKeyCode() == KeyEvent.VK_S) {
            if (animationStarted) {
                jscrp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
                jscrp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                animationStarted = false;
                if (animation.isAlive()) {
                    try {
                        animation.join();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(JFullScreen.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } else {
                jscrp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
                jscrp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                animationStarted = true;
                animation = new Animation();
                animation.start();
            }
        }
        if ((evt.getKeyCode() == KeyEvent.VK_SUBTRACT) || (evt.getKeyCode() == KeyEvent.VK_M)) {
            if (animationStarted) {
                jscrp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
                jscrp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                if (animation != null) {
                    if (animation.isAlive()) {
                        animation.incrementSync();
                    }
                }
            }
        }
        if ((evt.getKeyCode() == KeyEvent.VK_ADD) || (evt.getKeyCode() == KeyEvent.VK_P)) {
            if (animationStarted) {
                jscrp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
                jscrp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                if (animation != null) {
                    if (animation.isAlive()) {
                        animation.decrementSync();
                    }
                }
            }
        }
    }


    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        keyPressed(evt);
    }//GEN-LAST:event_formKeyPressed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected javax.swing.JPanel jpnContent;
    protected javax.swing.JPanel jpnNorth;
    protected javax.swing.JScrollPane jscrp;
    // End of variables declaration//GEN-END:variables

}
