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
					if (MultMasterFrame.posA_Y > 10)
					{	//avoid rabbit running out of screen
						MultMasterFrame.posA_Y -=15;	
						}break;

					
				
				case KeyEvent.VK_DOWN: 
					if (MultMasterFrame.posA_Y < 678)
					{   //avoid rabbit running out of screen
						MultMasterFrame.posA_Y +=15;	
					}	break;
					
				case KeyEvent.VK_SPACE:	
					{

						Weapon weapon = new Weapon(MultMasterFrame.posA_X,MultMasterFrame.posA_Y, 1);
						MultMasterFrame.weapons.add(weapon);
						
					}
					break;
																
				default:
				// nix
			}	
		} else{
			switch(key) 
			{
				
				case KeyEvent.VK_UP: 
					if (MultMasterFrame.posB_Y > 10)
					{	//avoid hedgehog running out of screen
						MultMasterFrame.posB_Y -=15;	
					}	break;
		
				case KeyEvent.VK_DOWN: 
					if (MultMasterFrame.posB_Y < 678)
					{	//avoid hedgehog running out of screen
						MultMasterFrame.posB_Y +=15;	
					}	break;
					
					case KeyEvent.VK_SPACE:	
					{

						Weapon weapon = new Weapon(MultMasterFrame.posB_X,MultMasterFrame.posB_Y, 2);
						MultMasterFrame.weapons.add(weapon);
						
					}break;
			
			}}
	}
	
	
	public void keyReleased(KeyEvent e){
		key = e.getKeyCode();
		
	}
	
	public void keyTyped(java.awt.event.KeyEvent e) {
		// notwendig, da abstract inherited
	}

}
