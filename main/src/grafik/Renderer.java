package grafik;

import main.calc;
import javax.swing.JFrame;

import main.calc;
import spielobjekte.*;

/**
 * @author ProgPra
 */
public class Renderer extends Thread{
    
	private spielfeld board = new spielfeld("1");
	private calc spiellogik = new calc(board);
    private Zeichnen display = new Zeichnen(board);
    
    long next_game_tick = System.currentTimeMillis();
    int FRAMES_PER_SECOND = 25;
    int SKIP_TICKS = 1000 / FRAMES_PER_SECOND;
    long sleepTime = 1;
    
    public void run(){
         
        while(true){ 
	        	spiellogik.updateData(); // Kollisionen berechnen
	            display.renderScreen(); // rendern
	            display.updateScreen(); // backBuffer auf den Bildschirm            
	            next_game_tick += SKIP_TICKS;               //Begrenzung der Framerate
	            sleepTime = next_game_tick - System.currentTimeMillis();
	            if(sleepTime>=0) {
	            
	                try{
	                	Thread.sleep(20L);
	                }
	                catch (InterruptedException ex) {}
	            }
	        while (calc.neues_spiel){
	        	calc.ingame = true;
	        	calc.neues_spiel = false;
	    		board.naechster_raum=1;
	    		spiellogik.naechster_Raum();
	        }
        }
    }
    public void setFrame(JFrame mainFrame) {
         display.setFrame(mainFrame);
    }
}