package grafik;

import javax.swing.JFrame;

import main.calc;
import spielobjekte.*; //package with board, game objects
/**
* @author ProgPra
*/
public class Renderer extends Thread{
    
private Spielfeld board = new Spielfeld("1"); // creates board, game objects and sets their position, life points...
private calc spiellogik = new calc(board);// collision detection, creates layout without objects
    private Zeichnen display = new Zeichnen(board);//paints objects on board, sets frame size,add keylistener
    
    long next_game_tick = System.currentTimeMillis();
    int FRAMES_PER_SECOND = 25;
    int SKIP_TICKS = 1000 / FRAMES_PER_SECOND;
    long sleepTime = 1;
    
    public void run(){ // update data, update screen
         
        while(true){
spiellogik.updateData(); // calculates collisions
display.renderScreen(); // render
display.updateScreen(); // backBuffer on screen
next_game_tick += SKIP_TICKS; //limits frame rate
sleepTime = next_game_tick - System.currentTimeMillis();
if(sleepTime>=0) {

try{
Thread.sleep(20L);
}
catch (InterruptedException ex) {}
}
if (calc.neues_spiel){ // start new game (after winning/loosing)
// resets game to level 1
calc.ingame = true; // true = playing, false = menu/GUI
board.naechster_raum=1;
spiellogik.go_to_next_room(); // resets background image
calc.neues_spiel = false; // sets boolean to avoid resetting
}
        }
    }
    public void setFrame(JFrame mainFrame) { //creates window and size
         display.setFrame(mainFrame);
    }
}