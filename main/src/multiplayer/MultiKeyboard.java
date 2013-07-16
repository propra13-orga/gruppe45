package multiplayer;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class MultiKeyboard implements KeyListener {

	int key;
		

		public void keyPressed (KeyEvent e){
		key = e.getKeyCode();
		if (MultMasterFrame.game){
		if (Multiplayer.isServer==true) 
		{ 
			switch(key) 
			{
				
				case KeyEvent.VK_UP: 
					if (MultMasterFrame.posA_Y > 10)
					{	//avoid rabbit running out of screen
						MultMasterFrame.posA_Y -=30;	
						}break;

					
				
				case KeyEvent.VK_DOWN: 
					if (MultMasterFrame.posA_Y < 678)
					{   //avoid rabbit running out of screen
						MultMasterFrame.posA_Y +=30;	
					}	break;
					
				case KeyEvent.VK_SPACE:	
					{
						boolean shot1 = false;
						for(int i=0; i<5;i++)
						{
								
							if ((MultMasterFrame.weapons.get(i).used ==false)&&(shot1 == false))
							{	
								MultMasterFrame.weapons.get(i).posX=(MultMasterFrame.posA_X);
								MultMasterFrame.weapons.get(i).posY=(MultMasterFrame.posA_Y);
								MultMasterFrame.weapons.get(i).direction =1;
								MultMasterFrame.weapons.get(i).used = true;
								shot1 = true;
							}	
						}
					}
					break;
																
				default:
				// nix
			}	
		}/*if server ende*/ else
		{
			switch(key) 
			{
				case KeyEvent.VK_UP: 
					if (MultMasterFrame.posB_Y > 10)
					{	//avoid hedgehog running out of screen
						MultMasterFrame.posB_Y -=30;	
					}	break;
		
				case KeyEvent.VK_DOWN: 
					if (MultMasterFrame.posB_Y < 678)
					{	//avoid hedgehog running out of screen
						MultMasterFrame.posB_Y +=30;	
					}	break;
					
				case KeyEvent.VK_SPACE:	
					{
						boolean shot2 = false;
						for(int i=5; i<10;i++)
						{
							if ((MultMasterFrame.weapons.get(i).used==false) &&( shot2 == false ))
							{	
								MultMasterFrame.weapons.get(i).posX=(MultMasterFrame.posB_X);
								MultMasterFrame.weapons.get(i).posY=(MultMasterFrame.posB_Y);
								MultMasterFrame.weapons.get(i).direction =2;
								MultMasterFrame.weapons.get(i).used = true;
								MultMasterFrame.weapons.get(i).isSent = false;
								shot2 = true;
							}
						}
					}/*case space ende*/   break;
					
				default: //nix
			}/*switch key ende*/
			}/*else ende*/
	}/*key pressed ende*/
		}
	
	public void keyReleased(KeyEvent e){
		key = e.getKeyCode();
		
	}
	
	public void keyTyped(java.awt.event.KeyEvent e) {
		// notwendig, da abstract inherited
	}

}
