package movement;

import main.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {

	
	public Keyboard()
	{
		Main.renderFrame.addKeyListener(this);
	}
	
	int key;
	
	
	//Move: moving figure and testing collisions
	//Spielfeld.obj_list.get(2) = hase
	public void keyPressed (KeyEvent e){
		key = e.getKeyCode();
		if (Main.game.ingame) { // game is running
			switch(key) {
				case KeyEvent.VK_LEFT:// int 37
					Move.left(Main.obj_list.get(2));
					break;
				case KeyEvent.VK_RIGHT: // int 18
					Move.right(Main.obj_list.get(2));
					break;
				case KeyEvent.VK_UP: // int 38
					Move.up(Main.obj_list.get(2));
					break;
				case KeyEvent.VK_DOWN: // int 40
					Move.down(Main.obj_list.get(2));
					break;
				case KeyEvent.VK_ESCAPE:
					Main.game.ingame = false;
					System.exit(0);									//change later
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
				Main.game.ingame = true;
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