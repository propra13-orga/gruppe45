package multiplayer;

import main.Main;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MultiKeyboard implements KeyListener {

	int key;
	int[] figure;
	
	int STEP = 10;
	
	//moves the rabbit
	//Move: moving figure and testing collisions
	//Main.obj_list.get(2) = bunny
		public void keyPressed (KeyEvent e){
		key = e.getKeyCode();
		figure = Main.obj_list.get(2);
		
		if (Multiplayer.isServer==true) 
		{ 
			switch(key) 
			{
				
				case KeyEvent.VK_UP: 
					MultMasterFrame.posA_Y -=10;	break;
				
				case KeyEvent.VK_DOWN: MultMasterFrame.posA_Y +=10;	break;
						
				case KeyEvent.VK_SPACE:	break;
																
				default:
				// nix
			}	
		} else{
			switch(key) 
			{
				
					case KeyEvent.VK_UP: MultMasterFrame.posB_Y -=10;	break;
		
					case KeyEvent.VK_DOWN: MultMasterFrame.posB_Y +=10;	break;
				
					case KeyEvent.VK_SPACE:	break;
			
			}}
	}
	
	
	public void keyReleased(KeyEvent e){
		key = e.getKeyCode();
		
	}
	
	public void keyTyped(java.awt.event.KeyEvent e) {
		// notwendig, da abstract inherited
	}

}