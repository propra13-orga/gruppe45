package movement;

import local.Fs;
import main.*;

import gameobjects.Methods;
import gameobjects.Spell;
import graphics.ShopFrame;

import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {

	int key;
	
	int STEP = 10;
	
	//moves the rabbit
	//Move: moving figure and testing collisions
	//Spielfeld.obj_list.get(2) = hase
		public void keyPressed (KeyEvent e){
		key = e.getKeyCode();
		if (Main.ingame) { // game is running
			switch(key) {
				
				case KeyEvent.VK_LEFT:// int 37
					Main.obj_list.get(2).direction = 'l';
					Main.obj_list.get(2).image = Toolkit.getDefaultToolkit().getImage(Fs.img_pfad+"bunny_l_motion.png");
					Move.left(Main.obj_list.get(2) , STEP);
					break;
				
				case KeyEvent.VK_RIGHT: // int 18
					Main.obj_list.get(2).direction = 'r';
					Main.obj_list.get(2).image = Toolkit.getDefaultToolkit().getImage(Fs.img_pfad+"bunny_r_motion.png");
					Move.right(Main.obj_list.get(2) , STEP);
					break;
				
				case KeyEvent.VK_UP: // int 38
					Main.obj_list.get(2).direction = 'u';
					Move.up(Main.obj_list.get(2) , STEP);
					break;
				
				case KeyEvent.VK_DOWN: // int 40
					Main.obj_list.get(2).direction = 'd';
					Move.down(Main.obj_list.get(2) , STEP);
					break;
				
				case KeyEvent.VK_SPACE:
					if((Main.obj_list.get(2).mp -= 10) >= 0)
					{
						int[] pos = Methods.getPosition_Spell(Main.obj_list.get(2));
						@SuppressWarnings("unused")
						Spell fireball = new Spell(	pos[0],
													pos[1],
													Toolkit.getDefaultToolkit().getImage(Fs.img_pfad +"fireball_" + Main.obj_list.get(2).direction + ".png"),
													Main.obj_list.get(2).direction,
													Main.obj_list.get(2).level * Main.obj_list.get(2).dmg );
					}
					else Main.obj_list.get(2).mp = 0;
					break;
				
				case KeyEvent.VK_ESCAPE:
					Main.ingame = false;
					System.exit(0);	//change later
					break;	
				
				case KeyEvent.VK_S:
					ShopFrame shop = new ShopFrame();
					Main.ingame = false;
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
		if (key == KeyEvent.VK_LEFT)Main.obj_list.get(2).image = Toolkit.getDefaultToolkit().getImage(Fs.img_pfad+"bunny_l.png");
		if (key == KeyEvent.VK_RIGHT)Main.obj_list.get(2).image = Toolkit.getDefaultToolkit().getImage(Fs.img_pfad+"bunny_r.png");
		// if (key == KeyEvent.VK_UP)
		// if (key == KeyEvent.VK_DOWN)
	}
	
	public void keyTyped(java.awt.event.KeyEvent e) {
		// notwendig, da abstract inherited
	}

}