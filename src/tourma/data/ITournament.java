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
public interface ITournament extends Remote, IContainCoachs{
        
    public void recomputeAll();
  
    public int getRoundIndex(Round round);

    /**
     *
     * @return
     */
    public int getClansCount();

    /**
     *
     * @param i
     * @return
     */
    public Clan getClan(int i);

    /**
     *
     * @param c
     */
    public void addClan(Clan c);

    /**
     *
     * @param c
     */
    public void removeClan(Clan c);

    /**
     *
     * @param c
     */
    public void removeClan(int c);

    /**
     * Clear the Clans array
     */
    public void clearClans() ;

    /**
     *
     * @return
     */
    public int getCategoriesCount();

    /**
     *
     * @param i
     * @return
     */
    public Category getCategory(int i);

    /**
     *
     * @param c
     */
    public void addCategory(Category c);

    /**
     *
     * @param c
     */
    public void removeCategory(Category c);

    /**
     *
     * @param c
     */
    public void removeCategory(int c);
    /**
     * Clear the Clans array
     */
    public void clearCategories();
    /**
     *
     * @return
     */
    public int getTeamsCount();

    /**
     *
     * @param i
     * @return
     */
    public Team getTeam(int i);

    /**
     *
     * @param c
     */
    public void addTeam(Team c) ;

    /**
     *
     * @param c
     */
    public void removeTeam(Team c) ;

    /**
     *
     * @param t
     * @return
     */
    public boolean containsTeam(Team t);

    public boolean containsTeam(String name) ;

    public boolean containsCoach(String name);

    public int getTeamIndex(String name);

    public Team getTeam(String name) ;

    public Coach getCoach(String name) ;

    /**
     *
     * @param c
     */
    public void removeTeam(int c);
    /**
     * Clear the Team array
     */
    public void clearTeams() ;

    /**
     *
     * @return
     */
    public ArrayList<Clan> getDisplayClans() ;

    /**
     *
     * @return
     */
    public ArrayList<Category> getDisplayCategories();

    /**
     *
     * @return
     */
    public Parameters getParams();

    /**
     *
     * @return
     */

    public boolean containsCoach(Coach c);

    public boolean containsClan(String c);

    public Clan getClan(String name) ;

    /**
     *
     * @param i
     */
    public void removeCoach(Coach i) ;
    /**
     *
     * @param i
     */

    public void removeCoach(int i);

    /**
     *
     */

    public void clearCoachs();

    /**
     *
     * @param c
     */

    public void addCoach(Coach c) ;

    /**
     *
     * @return
     */
    public int getCoachsCount() ;

    /**
     *
     * @param i
     * @return
     */

    public Coach getCoach(int i) ;

    /**
     *
     * @param g
     */
    public void addGroup(Group g);

    /**
     *
     * @param g
     */
    public void removeGroup(Group g);

    /**
     *
     * @param g
     */
    public void removeGroup(int g);

    /**
     *
     * @param g
     * @return
     */
    public boolean containsGroup(Group g);

    /**
     *
     * Clear the groups array
     */
    public void clearGroups();

    /**
     *
     * @param i
     * @return
     */
    public Group getGroup(int i);

    public Group getGroup(Coach C);

    /**
     *
     * @param n
     * @return
     */
    public Group getGroup(String n);

    /**
     *
     * @return
     */
    public int getGroupsCount();

    /**
     *
     * @return
     */
    public int getRoundsCount();

    /**
     *
     * @param i
     * @return
     */
    public Round getRound(int i);

    /**
     * clear the round aarray
     */
    public void clearRounds();

    /**
     *
     * @param r
     */
    public void addRound(Round r);

   

    public ArrayList<Integer> getRankingTypes(boolean byTeam) ;

  

    /**
     *
     * @return
     */
    public int getActiveCoachNumber() ;

    /**
     *
     * @return
     */
    public ArrayList<Coach> getActiveCoaches() ;

    /**
     *
     * @return
     */
    public int getPoolCount() ;
    /**
     *
     * @param p
     */
    public void addPool(Pool p);

    /**
     * Clear the Pool array
     */
    public void clearPools();

    /**
     *
     * @param i
     * @return
     */
    public Pool getPool(int i) ;

    /**
     * @param mParams the mParams to set
     */
    public void setParams(Parameters mParams);

    /**
     * @return the mRoundRobin
     */
    public boolean isRoundRobin();

    /**
     * @param mRoundRobin the mRoundRobin to set
     */
    public void setRoundRobin(boolean mRoundRobin) ;

    /**
     *
     * @param r
     */
    public void removeRound(Round r) ;

    /**
     *
     * @param r
     */
    public void removeRound(int r);
    

    public String getDescription();

    public void setDescription(String tmp) ;
}
