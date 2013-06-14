package graphics;

import local.Fs;
import movement.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.awt.event.KeyEvent;
import java.util.Scanner;

public class Paint extends JComponent{

    public JFrame frame;
    private Image backBuffer;           
    private Image myImage;
    // more code to write here ...
    long delta;
    long last;
    long fps;
    
    Image background;
    Image baum;
    Image baumD;
    Image player;
    int x = 545;
    int y = 330;
    int bf = 0;
    
    static int figurx = 0;              //Startposition X-Achse
    static int figury = 330;            //Startposition Y-Achse
    
    Paint(){
        this.loadTextures();
    }
    
    private void createBackBuffer(){
        backBuffer = frame.createImage(1024,768);
    }
    
    public void loadTextures(){             //Bilder einlesen
        try{
            background =ImageIO.read(new File(Fs.img_pfad+"Su_s BG.png"));          //Pfad der Texturen, muss noch in allgemeinen Ordner verschoben werden!!!
            baum = ImageIO.read(new File(Fs.img_pfad+"baum_k.png"));
            baumD = ImageIO.read(new File(Fs.img_pfad+"baum_k2.png"));
            player = ImageIO.read(new File(Fs.img_pfad+"held_rechts.png"));
        }
        catch (IOException e){              //für den Fall, dass der Pfad falsch ist
            //TODO Auto-generated catch block 
            e.printStackTrace();
        }
        
    }
    
    public void paintComponent(Graphics g){
        updateScreen();
    }
        
    public void renderScreen(){
        // if backBuffer doesn't exist, create one
        if (backBuffer == null) 
            this.createBackBuffer();
        // get Graphics object from backBuffer
        Graphics g = backBuffer.getGraphics();
    //    Graphics g = frame.getGraphics();
     
        // render screen on backBuffer
        g.drawImage(background,0,0,this);                      //fügt Hintergrund ein
        g.drawImage(player,figurx,figury,this);               //fügt Spielfigur ein       
            
        
        if(figurx>610 && figurx<810 && figury>310 && figury<482){              //Wenn Player auf der Baumkrone ist wird dieser durchsichtig
        g.drawImage(baumD,650,350,this);    
        }
        else{
            g.drawImage(baum,650,350,this);
        }
       
    /*    if(x>1024){                                         //Player geht rechts aus dem Bild und kommt oben wieder heraus (theoretisch) 
             g.drawImage(player,500,100,this);
             y+=2;
        }*/
    
        g.setColor(Color.white);
        this.frameRate();
        g.drawString("FPS: "+ Long.toString(fps), 50, 50);
        
          //g.drawLine(0,0,100,200);*/ 
        // ...
    }
    
    public void updateScreen(){
        Graphics g = frame.getGraphics();
        
        if (g != null) /* component already visible?*/{
            // is there a backBuffer to draw?
            if (backBuffer != null) g.drawImage(backBuffer, 0, 0, null);
            else{
                // if not, create one and render on it
                this.createBackBuffer();
                this.renderScreen();
            }
        }
    }

    void setFrame(JFrame mainFrame) {                           //Fenster starten, Größe setzen, 
       frame = mainFrame;
       frame.setSize(1024, 768);
       frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
       frame.setVisible(true);
       
       Keyboard eingabe = new Keyboard();       // EventListener für Keyboard
       frame.addKeyListener(eingabe);           
      
    }
    
    private void frameRate(){
        delta = System.nanoTime() - last;
        last = System.nanoTime();
        
        fps = ((long) 1e9)/delta;
    }

}
    

