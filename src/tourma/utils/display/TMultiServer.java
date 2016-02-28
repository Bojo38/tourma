/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.utils.display;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author WFMJ7631
 */
public class TMultiServer extends Thread {

    private boolean stop = false;
    private int _port = 2017;
    ServerSocket serverSocket;
    ArrayList<TServerThread> threads = new ArrayList();

    /*public TMultiServer(int port) {
        _port = port;
    }*/
    public void stopServer() {
        stop = true;
        if (this.isAlive()) {

            for (TServerThread thread : threads) {
                try {
                    thread.getSocket().close();
                } catch (SocketException se) {
                    Logger.getLogger(TMultiServer.class.getName()).log(Level.INFO, null, se);
                } catch (IOException ioe) {
                    Logger.getLogger(TMultiServer.class.getName()).log(Level.SEVERE, null, ioe);
                }
            }

            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (SocketException se) {
                    Logger.getLogger(TMultiServer.class.getName()).log(Level.INFO, null, se);
                } catch (IOException ioe) {
                    Logger.getLogger(TMultiServer.class.getName()).log(Level.SEVERE, null, ioe);
                }
            }
        }

    }

    public void run() {
        try {
            serverSocket = new ServerSocket(_port);
            while (!stop) {
                Socket socket = serverSocket.accept();
                TServerThread tst = new TServerThread(socket);
                threads.add(tst);
                tst.start();
            }
        } catch (IOException e) {
            //Logger.getLogger(TMultiServer.class.getName()).log(Level.SEVERE, null, e);
        }

    }
}
