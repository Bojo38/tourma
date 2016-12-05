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
public interface ITournament extends Remote, IContainCoachs {
        
    public void recomputeAll() throws RemoteException;
  
    public int getRoundIndex(Round round) throws RemoteException;

    /**
     *
     * @return
     */
    public int getClansCount() throws RemoteException;

    /**
     *
     * @param i
     * @return
     */
    public Clan getClan(int i) throws RemoteException;

    /**
     *
     * @param c
     */
    public void addClan(Clan c) throws RemoteException;

    /**
     *
     * @param c
     */
    public void removeClan(Clan c) throws RemoteException;

    /**
     *
     * @param c
     */
    public void removeClan(int c) throws RemoteException;

    /**
     * Clear the Clans array
     */
    public void clearClans() throws RemoteException;

    /**
     *
     * @return
     */
    public int getCategoriesCount() throws RemoteException;

    /**
     *
     * @param i
     * @return
     */
    public Category getCategory(int i) throws RemoteException;

    /**
     *
     * @param c
     */
    public void addCategory(Category c) throws RemoteException;

    /**
     *
     * @param c
     */
    public void removeCategory(Category c) throws RemoteException;

    /**
     *
     * @param c
     */
    public void removeCategory(int c) throws RemoteException;
    /**
     * Clear the Clans array
     */
    public void clearCategories() throws RemoteException;
    /**
     *
     * @return
     */
    public int getTeamsCount() throws RemoteException;

    /**
     *
     * @param i
     * @return
     */
    public Team getTeam(int i) throws RemoteException;

    /**
     *
     * @param c
     */
    public void addTeam(Team c) throws RemoteException;

    /**
     *
     * @param c
     */
    public void removeTeam(Team c) throws RemoteException;

    /**
     *
     * @param t
     * @return
     */
    public boolean containsTeam(Team t) throws RemoteException;

    public boolean containsTeam(String name) throws RemoteException;

    public boolean containsCoach(String name) throws RemoteException;

    public int getTeamIndex(String name) throws RemoteException;

    public Team getTeam(String name) throws RemoteException;

    public Coach getCoach(String name) throws RemoteException;

    /**
     *
     * @param c
     */
    public void removeTeam(int c) throws RemoteException;
    /**
     * Clear the Team array
     */
    public void clearTeams() throws RemoteException;

    /**
     *
     * @return
     */
    public ArrayList<Clan> getDisplayClans() throws RemoteException;

    /**
     *
     * @return
     */
    public ArrayList<Category> getDisplayCategories() throws RemoteException;

    /**
     *
     * @return
     */
    public Parameters getParams() throws RemoteException;

    /**
     *
     * @return
     */

    public boolean containsCoach(Coach c) throws RemoteException;

    public boolean containsClan(String c) throws RemoteException;

    public Clan getClan(String name) throws RemoteException;

    /**
     *
     * @param i
     */
    public void removeCoach(Coach i) throws RemoteException;
    /**
     *
     * @param i
     */

    public void removeCoach(int i) throws RemoteException;

    /**
     *
     */

    public void clearCoachs() throws RemoteException;

    /**
     *
     * @param c
     */

    public void addCoach(Coach c) throws RemoteException;

    /**
     *
     * @return
     */
    public int getCoachsCount()throws RemoteException;

    /**
     *
     * @param i
     * @return
     */

    public Coach getCoach(int i) throws RemoteException;

    /**
     *
     * @param g
     */
    public void addGroup(Group g) throws RemoteException;

    /**
     *
     * @param g
     */
    public void removeGroup(Group g) throws RemoteException ;

    /**
     *
     * @param g
     */
    public void removeGroup(int g)throws RemoteException;

    /**
     *
     * @param g
     * @return
     */
    public boolean containsGroup(Group g) throws RemoteException;

    /**
     *
     * Clear the groups array
     */
    public void clearGroups() throws RemoteException;

    /**
     *
     * @param i
     * @return
     */
    public Group getGroup(int i) throws RemoteException;

    public Group getGroup(Coach C) throws RemoteException;

    /**
     *
     * @param n
     * @return
     */
    public Group getGroup(String n) throws RemoteException;

    /**
     *
     * @return
     */
    public int getGroupsCount() throws RemoteException;

    /**
     *
     * @return
     */
    public int getRoundsCount() throws RemoteException;

    /**
     *
     * @param i
     * @return
     */
    public Round getRound(int i) throws RemoteException;

    /**
     * clear the round aarray
     */
    public void clearRounds() throws RemoteException;

    /**
     *
     * @param r
     */
    public void addRound(Round r) throws RemoteException;

   

    public ArrayList<Integer> getRankingTypes(boolean byTeam) throws RemoteException;

  

    /**
     *
     * @return
     */
    public int getActiveCoachNumber() throws RemoteException;

    /**
     *
     * @return
     */
    public ArrayList<Coach> getActiveCoaches() throws RemoteException;

    /**
     *
     * @return
     */
    public int getPoolCount()throws RemoteException ;
    /**
     *
     * @param p
     */
    public void addPool(Pool p) throws RemoteException;

    /**
     * Clear the Pool array
     */
    public void clearPools() throws RemoteException;

    /**
     *
     * @param i
     * @return
     */
    public Pool getPool(int i) throws RemoteException;

    /**
     * @param mParams the mParams to set
     */
    public void setParams(Parameters mParams) throws RemoteException;

    /**
     * @return the mRoundRobin
     */
    public boolean isRoundRobin() throws RemoteException;

    /**
     * @param mRoundRobin the mRoundRobin to set
     */
    public void setRoundRobin(boolean mRoundRobin) throws RemoteException;

    /**
     *
     * @param r
     */
    public void removeRound(Round r)throws RemoteException ;

    /**
     *
     * @param r
     */
    public void removeRound(int r)throws RemoteException;
    

    public String getDescription()throws RemoteException;

    public void setDescription(String tmp) throws RemoteException;
}
