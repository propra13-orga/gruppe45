package movement;

import local.Create;
import main.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard2 implements KeyListener {

	int key;
	int[] figure;
	
	int STEP = 10;
	
	//Move: moving figure and testing collisions
	//Spielfeld.obj_list.get(3) = hedgehog
	public void keyPressed (KeyEvent e){
		key = e.getKeyCode();
		figure = Main.obj_list.get(3);
		
		if (Main.ingame) { // game is running
			switch(key) {
				case KeyEvent.VK_W: // up
					figure[5] = 1;
					Create.hero2.moving = true;
					Move.up(figure , STEP);
					break;
				case KeyEvent.VK_D: // right
					figure[5] = 2;
					Create.hero2.moving = true;
					Move.right(figure , STEP);
					break;
				case KeyEvent.VK_S: // down
					figure[5] = 3;
					Create.hero2.moving = true;
					Move.down(figure , STEP);
					break;
				case KeyEvent.VK_A:	// left
					figure[5] = 4;
					Create.hero2.moving = true;
					Move.left(figure , STEP);
					break;
				default:
					// nix			
			}
		}
	}
	
	public void keyReleased(KeyEvent e){
	key = e.getKeyCode();
	Create.hero2.moving = false;
	}

	  public void keyTyped(java.awt.event.KeyEvent e) {
		        // notwendig, da abstract inherited
	  }

}