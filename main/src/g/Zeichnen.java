package g;

import o.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import l.*;
import o.*;
import main.Tastatur;

import java.awt.event.KeyEvent;
import java.util.Scanner;

/**
 * @author ProgPra
 */

public class Zeichnen extends JComponent{    
    
    public JFrame frame;
    private Image backBuffer;           
    long delta;
    long last;
    long fps;
    int x = 545;
    int y = 330;
    int bf = 0;
    public spielfeld board;
    String[] held = { "Meister Lampe", "Peter Hase"};
    
    Zeichnen(spielfeld board){ // Konstruktor
    	this.board = board;
    }
    
    private void createBackBuffer(){
        backBuffer = frame.createImage(1024,768);
    }
    
    public void paintComponent(Graphics g){
        updateScreen();
    }
        
    public void renderScreen(){
    	int block_start;
    	int block_ende;
        if (backBuffer == null) 
            this.createBackBuffer();
        Graphics g = backBuffer.getGraphics();
        g.drawImage(board.bg_image,0,0, this);
        for (int block=0; block<board.max_y_blocks;block++) {
        	block_start = block*board.block_groesse;
        	block_ende = (block+1)*board.block_groesse-1;
	        for (spielfeld.wall wand: board.walls){
	        	if (wand.pos_y>=block_start && wand.pos_y<= block_ende) {
	        		g.drawImage(wand.image, wand.pos_x, wand.pos_y, this);
	        	}
	        }
	        for (spielfeld.killerbunny hase: board.killers){
	        	if (hase.pos_y>=block_start && hase.pos_y<= block_ende) {
	        		g.drawImage(hase.image, hase.pos_x, hase.pos_y, this);
	        	}
	        }
	        for (spielfeld.todesbaum plant: board.plants){
	        	if (plant.pos_y>=block_start && plant.pos_y<= block_ende) {
	        		g.drawImage(plant.image, plant.pos_x, plant.pos_y, this);
	        	}
	        }
	        int z = 0;
	        for (spielfeld.hero hase: board.heros){
	        	if (hase.pos_y>=block_start && hase.pos_y<= block_ende) {
		        	g.drawImage(hase.image, hase.pos_x, hase.pos_y, this);
		        	g.setColor(Color.white);
		        	g.drawString(held[z] + " - Lebenspunkte: "+ Integer.toString(hase.leben_punkte), 30, 50+z*30);
	        	}
		        z++;        	
	        }
        }
        g.setColor(Color.white);
       this.frameRate();
      g.drawString("FPS: "+ Long.toString(fps), 50, 150);
    }
    
    public void updateScreen(){
        Graphics g = frame.getGraphics();
        
        if (g != null) { // prüfen auf BackBuffer
            if (backBuffer != null) g.drawImage(backBuffer, 0, 0, null);
        }else{
            // wenn nicht da, erstellen
            this.createBackBuffer();
            this.renderScreen();
        }
    }

    void setFrame(JFrame mainFrame) { //Fenster starten, Größe setzen, 
       frame = mainFrame;
       frame.setSize(1024, 728);
       frame.setLayout(new BorderLayout());
       frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
       frame.setVisible(true);
       Tastatur eingabe = new Tastatur();       // EventListener für Keyboard
       frame.addKeyListener(eingabe);
       
    }
    
    private void frameRate(){
        delta = System.nanoTime() - last;
        last = System.nanoTime();
        fps = ((long) 1e9)/delta;
    }

}
    

