package grafik;

import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import spielobjekte.*;
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
    Font schrift_klein;
    Font schrift_gross;
    public spielfeld board;
    String[] held = { "Meister Lampe", "Peter Hase"};
    
    Zeichnen(spielfeld board){ // Konstruktor
    	this.board = board;
    	this.schrift_klein = new Font("Arial",Font.PLAIN, 12);
    	this.schrift_gross = new Font("Arial",Font.BOLD, 24);
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
        g.setFont(this.schrift_klein);
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
	        for (spielfeld.ziel ziel: board.ziele){
	        	if (ziel.pos_y>=block_start && ziel.pos_y<= block_ende) {
	        		g.drawImage(ziel.image, ziel.pos_x, ziel.pos_y, this);
	        	}
	        }
	        int z = 0;
	        for (spielfeld.hero hase: board.heros){
	        	if (hase.pos_y>=block_start && hase.pos_y<= block_ende) {
	        		if (hase.leben_punkte>0) {
			        	g.drawImage(hase.image, hase.pos_x, hase.pos_y, this);
			        	g.setColor(Color.white);
			        	g.drawString(held[z] + " - Lebenspunkte: "+ Integer.toString(hase.leben_punkte), 30, 60+z*30);
	        	
	        		}
	        	}
		        z++;        	
	        }
        }
        g.setColor(Color.white);
        g.drawString("Level "+ Integer.toString(board.naechster_raum-1), 30, 40);
 
        if (board.nachricht.length()>0) {
        	g.setFont(this.schrift_gross);
            g.setColor(Color.red);
            g.drawString(board.nachricht, 30,120);
        	g.setFont(this.schrift_klein);
        }
//        g.setColor(Color.white);
//        this.frameRate();
//        g.drawString("FPS: "+ Long.toString(fps), 50, 150);
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
    

