package grafik;

import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import spielobjekte.*;
import lokal.fs;
import main.Main;
import main.Tastatur;
import main.calc;

import java.io.File;
import java.io.IOException;

/**
 * only drawing board and objects
 */

public class Zeichnen extends JComponent{    
    
    public JFrame frame;
    private Image backBuffer;
    private Image menu;
    long delta; //frame rate
    long last; //frame rate
    long fps; //frame rate
    // int bf = 0;
    Font schrift_klein;
    Font schrift_gross;
    public spielfeld board;
    String[] held = { "Meister Lampe", "Peter Hase"};
    
    Zeichnen(spielfeld board){  // constructor pointer to board, settings for board messages
    	this.board = board;
    	try {
			this.menu = ImageIO.read(new File(fs.img_pfad+"start.png")); //loads board menu
		} catch (IOException e) {
			System.out.println("Message Fenster kann nicht geladen werden");
		}
    	this.schrift_klein = new Font("Arial",Font.PLAIN, 12); //status message level, life points...
    	this.schrift_gross = new Font("Arial",Font.BOLD, 24); //message when event
    }
    
    private void createBackBuffer(){
    	backBuffer = frame.createImage(Main.FRAMESIZE_X,Main.FRAMESIZE_Y);
    }
    
    public void paintComponent(Graphics g){
        updateScreen();
    }
        
    public void renderScreen(){ //paints back buffer 	
    	int block_start;// x start position of active block 
    	int block_ende; // x end position of active block 
        if (backBuffer == null) 
            this.createBackBuffer();
        Graphics g = backBuffer.getGraphics();
        g.drawImage(board.bg_image,0,0, this); //loads bg image to back buffer
        g.setFont(this.schrift_klein);
        
        // for-next-loop:
        // paints board line by line
        // last painted object in foreground
        for (int block=0; block<board.max_y_blocks;block++) {
        	block_start = block*board.block_groesse;
        	block_ende = (block+1)*board.block_groesse-1;
        	
        	// paints walls and trees
	        for (spielfeld.wall wand: board.walls){
	        	if (wand.pos_y>=block_start && wand.pos_y<= block_ende) {
	        		g.drawImage(wand.image, wand.pos_x, wand.pos_y, this);
	        	}
	        }

	        // paints killerbunnies (evil bunny)
	        for (spielfeld.killerbunny hase: board.killers){
	        	if (hase.pos_y>=block_start && hase.pos_y<= block_ende) {
	        		g.drawImage(hase.image, hase.pos_x, hase.pos_y, this);
	        	}
	        }
	        // paints malicious trees
	        for (spielfeld.todesbaum plant: board.plants){
	        	if (plant.pos_y>=block_start && plant.pos_y<= block_ende) {
	        		g.drawImage(plant.image, plant.pos_x, plant.pos_y, this);
	        	}
	        }
	        // paints finish line
	        for (spielfeld.ziel ziel: board.ziele){
	        	if (ziel.pos_y>=block_start && ziel.pos_y<= block_ende) {
	        		g.drawImage(ziel.image, ziel.pos_x, ziel.pos_y, this);
	        	}
	        }
	        int held_index = 0;
	        // paints heros (when playing)
	        if (calc.ingame){  // boolean for active game
		        for (spielfeld.hero hase: board.heros){ // individual list of objects
		        	if (hase.pos_y>=block_start && hase.pos_y<= block_ende) { // if hero in actual block -> paint
		        		if (hase.leben_punkte>0) {
				        	g.drawImage(hase.get_hero_image(), hase.pos_x, hase.pos_y, this);
				        	g.setColor(Color.white);
				        	// paints hero name + life points
				        	g.drawImage(hase.get_lebensbalken_image(), 25, 90, hase.leben_punkte, 15, this);
				        	g.draw3DRect(25, 90, hase.start_leben_punkte, 15, true);
				        	for (int i=0;i<hase.anz_leben; i++) {
				        		g.drawImage(hase.leben_img, 30+ i*30, 52, this);
				        	}
				        	g.drawString(held[held_index], 30, 102+held_index*30);
		        		}
		        	}
			        held_index++;        	
		        }
	        }
        }
        // paints level #  in upper left corner
        g.setColor(Color.white);
        g.drawString("Level "+ Integer.toString(board.naechster_raum-1), 30, 40);
 
        // paints message in upper left corner (win, end of game, injury...)
        if (board.nachricht.length()>0) {
        	g.setFont(this.schrift_gross);
            g.setColor(Color.red);
            g.drawString(board.nachricht, 30,120);
        	g.setFont(this.schrift_klein);
        }
        
        if (calc.ingame == false) { // Male Menu
        	g.drawImage(menu, 400, 300, this);
        }
//		paints frames per second on screen         
//        g.setColor(Color.white);
//        this.frameRate();
//        g.drawString("FPS: "+ Long.toString(fps), 50, 150);
    }
    
    public void updateScreen(){
        Graphics g = frame.getGraphics();
        
        if (g != null) { // check if back buffer available
            if (backBuffer != null) g.drawImage(backBuffer, 0, 0, null);
        }else{
        	// if not create
            this.createBackBuffer();
            this.renderScreen();
        }
    }

    void setFrame(JFrame mainFrame) { //inits window 
       frame = mainFrame;
       frame.setSize(Main.FRAMESIZE_X, Main.FRAMESIZE_Y);
       frame.setLayout(new BorderLayout());
       frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
       frame.setVisible(true);
       Tastatur eingabe = new Tastatur();  // EventListener for keyboard
       frame.addKeyListener(eingabe);       
    }
    
//    private void frameRate(){
//        delta = System.nanoTime() - last;
//        last = System.nanoTime();
//        fps = ((long) 1e9)/delta;
//    }

}
    

