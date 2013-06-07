package main;

import javax.swing.JFrame;

/**
 *
 * @author ProgPra
 */
public class Renderer extends Thread{
    
    
    
    private Zeichnen display = new Zeichnen();
    
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
    void setFrame(JFrame mainFrame) {
         display.setFrame(mainFrame);
    }
}