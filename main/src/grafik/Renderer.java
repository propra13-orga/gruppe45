package grafik;

import javax.swing.JFrame;

import main.calc;
import spielobjekte.*;

/**
 * @author ProgPra
 */
public class Renderer extends Thread{
    
	private spielfeld board = new spielfeld("1");
	calc logik = new main.calc(board);
    private Zeichnen display = new Zeichnen(board);
    
    long next_game_tick = System.currentTimeMillis();
    int FRAMES_PER_SECOND = 25;
    int SKIP_TICKS = 1000 / FRAMES_PER_SECOND;
    long sleepTime = 1;
    
    public void run(){
         
        while(calc.ingame){
        	logik.updateData();
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
        }
    }
    public void setFrame(JFrame mainFrame) {
         display.setFrame(mainFrame);
    }
}