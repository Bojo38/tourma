/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.data;

import com.google.zxing.WriterException;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.encoder.Encoder;
import com.google.zxing.qrcode.encoder.QRCode;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import tourma.MainFrame;
import tourma.languages.Translate;
import tourma.tableModel.MjtAnnexRankClan;
import tourma.tableModel.MjtAnnexRankIndiv;
import tourma.tableModel.MjtAnnexRankTeam;
import tourma.tableModel.MjtRankingClan;
import tourma.tableModel.MjtRankingIndiv;
import tourma.tableModel.MjtRankingTeam;
import tourma.utility.StringConstants;

/**
 *
 * @author WFMJ7631
 */
public interface ITournament extends Remote {
        
    public Tournament getTournament() throws RemoteException ;
    public void setTournament(Tournament object) throws RemoteException ;
}
