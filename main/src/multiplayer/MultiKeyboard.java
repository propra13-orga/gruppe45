package multiplayer;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class MultiKeyboard implements KeyListener {

	int key;
	
	
		

		public void keyPressed (KeyEvent e){
		key = e.getKeyCode();

		if (Multiplayer.isServer==true) 
		{ 
			switch(key) 
			{
				
				case KeyEvent.VK_UP: 
					MultMasterFrame.posA_Y -=10;	

					break;
				
				case KeyEvent.VK_DOWN: 
					MultMasterFrame.posA_Y +=10;	break;
						
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
