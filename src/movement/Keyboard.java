package movement;

import local.Create;
import main.Main;
import graphics.Npc;
import graphics.ShopFrame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {

	int key;
	int[] figure;
	
	int STEP = 10;
	
	//moves the rabbit
	//Move: moving figure and testing collisions
	//Spielfeld.obj_list.get(2) = hase
		public void keyPressed (KeyEvent e){
		key = e.getKeyCode();
		figure = Main.obj_list.get(2);
		
		if (Main.ingame) { // game is running
			switch(key) {
				
				case KeyEvent.VK_UP: // int 38
					figure[5] = 1;
					Create.hero1.moving = true;
					Move.up(figure , STEP);
					break;
				
				case KeyEvent.VK_RIGHT: // int 18
					figure[5] = 2;
					Create.hero1.moving = true;
					Move.right(figure , STEP);
					break;
				
				case KeyEvent.VK_DOWN: // int 40
					figure[5] = 3;
					Create.hero1.moving = true;
					Move.down(figure , STEP);
					break;
				
				case KeyEvent.VK_LEFT:// int 37
					figure[5] = 4;
					Create.hero1.moving = true;
					Move.left(figure , STEP);
					break;
				
				case KeyEvent.VK_SPACE:								//cast Spell
					Create.hero1.cast_Spell(figure[5],figure[2],figure[3]);
					break;
				
				case KeyEvent.VK_ESCAPE:							//open menu
					Main.ingame = false;
					break;	
				
				case KeyEvent.VK_S:									//open shop
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
					
				case KeyEvent.VK_H:
					Create.hero1.use_hp_pot();
					break;
					
				case KeyEvent.VK_M:
					Create.hero1.use_mp_pot();
					break;
					
				case KeyEvent.VK_DELETE:
					if(Main.music == true) Main.music = false;
					else Main.music = true;
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
					Main.ingame = true;
					break;
				default:
				// nothing
			}
		}
	}
	
	//Angleichen, je nach endgültigem Bewegungssystem
	public void keyReleased(KeyEvent e){
		key = e.getKeyCode();
		Create.hero1.moving = false;
	}
	
	public void keyTyped(java.awt.event.KeyEvent e) {
		// notwendig, da abstract inherited
	}

}