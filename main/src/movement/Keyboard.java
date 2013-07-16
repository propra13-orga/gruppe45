package movement;

import local.Create;
import main.*;

import graphics.Npc;
import graphics.ShopFrame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Catches all KeyEvents, users main interface while playing
 * @author Andreas Roth
 */
public class Keyboard implements KeyListener {

	int key;
	int[] bunny = Main.obj_list.get(2);
	int[] hedgehog = Main.obj_list.get(3);
	
	/**
	 * Reacts if key is pressed
	 */
	public void keyPressed (KeyEvent e){
		key = e.getKeyCode();
		
		if (Main.ingame) { // game is running
			switch(key) {
				case KeyEvent.VK_W: // up
					Move.keys[0] = true;
					break;
					
				case KeyEvent.VK_A: // right
					Move.keys[1] = true;
					break;
					
				case KeyEvent.VK_S: // down
					Move.keys[2] = true;
					break;
					
				case KeyEvent.VK_D:	// left
					Move.keys[3] = true;
					break;
					
				case KeyEvent.VK_UP:
					Move.keys[4] = true;
					break;
					
				case KeyEvent.VK_LEFT:
					Move.keys[5] = true;
					break;
					
				case KeyEvent.VK_DOWN:
					Move.keys[6] = true;
					break;
					
				case KeyEvent.VK_RIGHT:
					Move.keys[7] = true;
					break;
					
				case KeyEvent.VK_ESCAPE:
					Main.ingame = false;
					break;
					
				case KeyEvent.VK_SPACE:
					if(Main.shop)
					{
						@SuppressWarnings("unused")
						ShopFrame shop = new ShopFrame();
						Main.ingame = false;
					}
					else if(Main.npc)
					{
						@SuppressWarnings("unused")
						Npc npc = new Npc();
						Main.ingame = false;	
					}
					break;
					
				case KeyEvent.VK_Q:
					Create.hero1.cast_Fireball(Main.obj_list.get(2)[5],Main.obj_list.get(2)[2],Main.obj_list.get(2)[3]);
					break;
					
				case KeyEvent.VK_H:
					Create.hero2.cast_Fireball(Main.obj_list.get(3)[5],Main.obj_list.get(3)[2],Main.obj_list.get(3)[3]);
					break;
					
				case KeyEvent.VK_E:
					Create.hero1.cast_Blob(Main.obj_list.get(2)[5],Main.obj_list.get(2)[2],Main.obj_list.get(2)[3]);
					break;
					
				case KeyEvent.VK_J:
					Create.hero2.cast_Blob(Main.obj_list.get(3)[5],Main.obj_list.get(3)[2],Main.obj_list.get(3)[3]);
					break;
					
				case KeyEvent.VK_F:
					Create.hero1.use_hp_pot();
					break;
					
				case KeyEvent.VK_K:
					Create.hero2.use_hp_pot();
					break;
					
				case KeyEvent.VK_G:
					Create.hero1.use_mp_pot();
					break;
					
				case KeyEvent.VK_L:
					Create.hero2.use_mp_pot();
					break;
			}
		}
		
		else
		{
			switch(key){
				case KeyEvent.VK_ESCAPE:
					Main.ingame = true;
					break;
					
				case KeyEvent.VK_B:
					System.exit(0);
					break;
					
				case KeyEvent.VK_DELETE:
					if(Main.music) Main.music = false;
					else Main.music = true;
					break;
			}
		}
	}
	
	/**
	 * Reacts if key is released
	 */
	public void keyReleased(KeyEvent e){
		key = e.getKeyCode();
		
		if (Main.ingame) { // game is running
			switch(key) {
				case KeyEvent.VK_W: // up
					Move.keys[0] = false;
					break;
					
				case KeyEvent.VK_A: // right
					Move.keys[1] = false;
					break;
					
				case KeyEvent.VK_S: // down
					Move.keys[2] = false;
					break;
					
				case KeyEvent.VK_D:	// left
					Move.keys[3] = false;
					break;
					
				case KeyEvent.VK_UP:
					Move.keys[4] = false;
					break;
					
				case KeyEvent.VK_LEFT:
					Move.keys[5] = false;
					break;
					
				case KeyEvent.VK_DOWN:
					Move.keys[6] = false;
					break;
					
				case KeyEvent.VK_RIGHT:
					Move.keys[7] = false;
					break;
					
			}
		}
	}
	
	/**
	 * Reacts if key is typed
	 */
	public void keyTyped(KeyEvent e) {
	}

}