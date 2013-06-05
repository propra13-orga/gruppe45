package movement;

/**
 * implements KeyListener and returns Position
 * @author ProgPra
 */

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import spielobjekte.*;

import main.calc;

public class Tastatur implements KeyListener {

	int key;
	//Move: vorrausschauende Randkollision + Bewegung (allg.Kollision wird noch eingebaut)
	//Spielfeld.obj_list.get(1) = hase
	public void keyPressed (KeyEvent e){
		key = e.getKeyCode();
		if (calc.ingame) { // Spiel läuft
			switch(key) {
				case KeyEvent.VK_LEFT:// int 37
					Move.left(Spielfeld.obj_list.get(1));
					break;
				case KeyEvent.VK_RIGHT: // int 18
					Move.right(Spielfeld.obj_list.get(1));
					break;
				case KeyEvent.VK_UP: // int 38
					Move.up(Spielfeld.obj_list.get(1));
					break;
				case KeyEvent.VK_DOWN: // int 40
					Move.down(Spielfeld.obj_list.get(1));
					break;
				case KeyEvent.VK_S:
				case KeyEvent.VK_ESCAPE:
					calc.ingame = false;
					break;					
				default:
					// nix			
			}
		} else { // Menu wird angezeigt
			switch(key) {
			case KeyEvent.VK_S: // int 83
				calc.neues_spiel = true;
				break;
			case KeyEvent.VK_B: // 
				System.exit(0);
				break;
			default:
				// nix
			}
		}
	}
	
	//Angleichen, je nach endgültigem Bewegungssystem
	public void keyReleased(KeyEvent e){
		key = e.getKeyCode();
	    if (key == KeyEvent.VK_LEFT) calc.P1_richtung_x = 0;
	    if (key == KeyEvent.VK_RIGHT) calc.P1_richtung_x = 0;
	    if (key == KeyEvent.VK_UP) calc.P1_richtung_y = 0;
	    if (key == KeyEvent.VK_DOWN) calc.P1_richtung_y = 0;
	}
	
	  public void keyTyped(java.awt.event.KeyEvent e) {
		        // notwendig, da abstract inherited
	  }
}