package graphics;

import local.Fs;
<<<<<<< HEAD
import main.GUI;
import main.Main;
=======
>>>>>>> 27ac273bccb8f6631ba15695ee7c897493fa6b69
import movement.*;
import gameobjects.Figure;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import java.awt.event.KeyEvent;
import java.util.Scanner;

public class Paint extends JComponent{

    //public JFrame frame;
    //  public JPanel pframe;
	//JInternalFrame renderFrame;
    private Image backBuffer;           
    private Image myImage;
    // more code to write here ...
    long delta;
    long last;
    long fps;
    
    Figure temp;
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
    	Keyboard eingabe = new Keyboard();       // EventListener for Keyboard
        Main.renderFrame.addKeyListener(eingabe); 
    }
    
    private void createBackBuffer(){
<<<<<<< HEAD
        backBuffer = Main.renderFrame.createImage(1024,768);
=======
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
>>>>>>> 27ac273bccb8f6631ba15695ee7c897493fa6b69
        
    }
    
    
    public void paintComponent(Graphics g){
        updateScreen();
    }
        
    public void renderScreen(){
        // if backBuffer doesn't exist, create one
        if (backBuffer == null){
        	this.createBackBuffer();
        }
        
        Graphics g = backBuffer.getGraphics();
        
        
        
        
        //g.drawImage(player,figurx,figury,this);               //faengt Spielfigur ein       
        
        // Wenn der Hintergrund gerendert wird hat Martin seine Klasse zum Laufen gebracht
        
    	for(int i=0; i<Main.obj_list.size();i++){
    		
    		if((temp = Main.obj_list.get(i)) != null)
    			g.drawImage(temp.image,temp.pos_x,temp.pos_y,this);
    			
    			//System.out.println("Hase"+temp.pos_x+temp.pos_y);
    	}
            
        
        /*if(figurx>610 && figurx<810 && figury>310 && figury<482){              //Wenn Player auf der Baumkrone ist wird dieser durchsichtig
        g.drawImage(baumD,650,350,this);    
        }
        else{
            g.drawImage(baum,650,350,this);
        }
       
    /*    if(x>1024){                                         //Player geht rechts aus dem Bild und kommt oben wieder heraus (theoretisch) 
             g.drawImage(player,500,100,this);
             y+=2;
        }*/
    
        //g.setColor(Color.white);
        this.frameRate();
        //g.drawString("FPS: "+ Long.toString(fps), 50, 50);
        
    }
    
    public void updateScreen(){
        Graphics g = Main.renderFrame.getGraphics();
        
		if (g != null){
            // is there a backBuffer to draw?
            if (backBuffer != null) g.drawImage(backBuffer, 0, 0, null);
            else{
                // if not, create one and render on it
                this.createBackBuffer();
                this.renderScreen();
            }
        }
    }

    void setFrame(JInternalFrame renderFrame) {  
       //frame = subFrame;
       //frame.setVisible(false);
       //frame.setSize(1024, 768);
       //frame.setSize(200,100);
       //frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
       //frame.setVisible(true);
       //Keyboard eingabe = new Keyboard();       // EventListener for Keyboard
       //frame.addKeyListener(eingabe);           
    }
    /*
    void setPanel(JPanel mainPanel){
       pframe = mainPanel;
       pframe.setSize(1024, 768);
       pframe.
       pframe.setVisible(true);
       
       Keyboard eingabe = new Keyboard();       // EventListener for Keyboard
    }
    */
    
       
    
    
    private void frameRate(){
        delta = System.nanoTime() - last;
        last = System.nanoTime();
        
        fps = ((long) 1e9)/delta;
    }

}
    

