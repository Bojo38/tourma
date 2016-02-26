/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.utils.display;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import tourma.views.fullscreen.JFullScreenClanRank;

/**
 *
 * @author WFMJ7631
 */
public class TServerThread extends Thread {

    private Socket _socket;

    public Socket getSocket()
    {
        return _socket;
    }
    
    public TServerThread(Socket socket) {
        _socket = socket;
    }

    public void run() {

        try (
                PrintWriter out = new PrintWriter(_socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(
                                _socket.getInputStream()));) {
            String inputLine, outputLine;
            TourmaProtocol tp = new TourmaProtocol();
            outputLine = tp.processInput(null);
            out.println(outputLine);

            String buffer = "";
            inputLine = in.readLine();
            while (inputLine != null) {
                if (inputLine.equals(TourmaProtocol.TKey.END.toString())) {
                    outputLine = tp.processInput(buffer);
                    out.println(outputLine);
                    out.println(TourmaProtocol.TKey.END);
                    buffer = "";
                } else {
                    buffer += inputLine;
                }
                inputLine = in.readLine();
            }
            _socket.close();
        } catch (IOException se) {
            LOG.log(Level.INFO, "Socket reseted, connection probably closed: " + se.getLocalizedMessage());
        }
    }

    private static final Logger LOG = Logger.getLogger(JFullScreenClanRank.class.getName());

}
