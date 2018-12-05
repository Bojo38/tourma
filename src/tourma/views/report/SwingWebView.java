/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourma.views.report;

import com.sun.javafx.application.PlatformImpl;
import java.awt.BorderLayout;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 *
 * @author WFMJ7631
 */
public class SwingWebView extends JFXPanel {
   
    private Stage stage;  
    private WebView browser;  
    //private JFXPanel jfxPanel;  
//    private JButton swingButton;  
    private WebEngine webEngine;  
  
    public SwingWebView(){  
        initComponents();  
    }  
   
     
    private void initComponents(){  
         
      //  jfxPanel = new JFXPanel();  
        createScene();  
         
        setLayout(new BorderLayout());  
        //add(jfxPanel, BorderLayout.CENTER);  
         
  /*      swingButton = new JButton();  
        swingButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Platform.runLater(new Runnable() {

                    @Override
                    public void run() {
                        webEngine.reload();
                    }
                });
            }
        });  
        swingButton.setText("Reload");  
         
        add(swingButton, BorderLayout.SOUTH);  */
    }     
     
    /** 
     * createScene 
     * 
     * Note: Key is that Scene needs to be created and run on "FX user thread" 
     *       NOT on the AWT-EventQueue Thread 
     * 
     */  
    private void createScene() {  
        PlatformImpl.startup(new Runnable() {  
            @Override
            public void run() {  
                 
                stage = new Stage();  
                 
                stage.setTitle("Hello Java FX");  
                stage.setResizable(true);  
   
                Group root = new Group();  
                StackPane sp=new StackPane();
                
                Scene scene = new Scene(sp,80,20);  
                root.setAutoSizeChildren(true);
                stage.setScene(scene);  
                 
                // Set up the embedded browser:
                browser = new WebView();
                webEngine = browser.getEngine();
                //webEngine.load("http://www.google.com");
                
                ObservableList<Node> children = sp.getChildren();
                children.add(browser);                     
                 
                setScene(scene);  
            }  
        });  
    }

    private String mUrl=null;
    
    public void setURL(String url)
    {
        if (url!=null)
        {
            mUrl=url;
            Platform.runLater(new Runnable(){
                @Override
                public void run() {
                    webEngine.load(mUrl);
                    //webEngine.load("file:///C:/test.tmp");                    
                }
            });
        }
    }
}
