package movement;

import main.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard2 implements KeyListener {

	int key;
	//Move: moving figure and testing collisions
	//Spielfeld.obj_list.get(1) = hase
	public void keyPressed (KeyEvent e){
		key = e.getKeyCode();
		if (Game.ingame) { // game is running
			switch(key) {
				case KeyEvent.VK_A:	// left
					Move.left(Main.obj_list.get(3));
					break;
				case KeyEvent.VK_D: // right
					Move.right(Main.obj_list.get(3));
					break;
				case KeyEvent.VK_W: // up
					Move.up(Main.obj_list.get(3));
					break;
				case KeyEvent.VK_S: // down
					Move.down(Main.obj_list.get(3));
					break;
				case KeyEvent.VK_ESCAPE:
					Game.ingame = false;
					break;					
				default:
					// nix			
			}
		} else { // in menu
			switch(key) {
			case KeyEvent.VK_N: // int 83
				//calc.neues_spiel = true;
				break;
			case KeyEvent.VK_B: // 
				System.exit(0);
				break;
			case KeyEvent.VK_ESCAPE:
				Game.ingame = true;
				break;
			default:
				// nothing
			}
		}
	}
	
	//Angleichen, je nach endgültigem Bewegungssystem
	public void keyReleased(KeyEvent e){
		/*key = e.getKeyCode();
	    if (key == KeyEvent.VK_LEFT) calc.P1_richtung_x = 0;
	    if (key == KeyEvent.VK_RIGHT) calc.P1_richtung_x = 0;
	    if (key == KeyEvent.VK_UP) calc.P1_richtung_y = 0;
	    if (key == KeyEvent.VK_DOWN) calc.P1_richtung_y = 0;*/
	}

	  public void keyTyped(java.awt.event.KeyEvent e) {
		        // notwendig, da abstract inherited
	  }

}