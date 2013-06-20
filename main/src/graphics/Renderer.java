package graphics;

import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

import main.Game;

public class Renderer extends Thread{
    
    private Paint display = new Paint();
    
    long next_game_tick = System.currentTimeMillis();
    int FRAMES_PER_SECOND = 25;
    int SKIP_TICKS = 1000 / FRAMES_PER_SECOND;
    long sleepTime = 0;
    // more code to write here ...
    
    public void run(){
         
        while(true){
            
            display.renderScreen(); // render component
            display.updateScreen(); // draw backBuffer to screen
            // rest a bit and give time to other Threads
            
           
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
    /*
    public void setPanel(JPanel mainPanel) {
        display.setPanel(mainPanel);
   }
   */
    public void setFrame(JInternalFrame renderFrame) {
         display.setFrame(renderFrame);
    }
    
}