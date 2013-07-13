package multiplayer;

import java.net.Socket;

public class SendPosition implements Runnable {
	
	MultiGame sendmethod;
	Socket partner;
	
	public SendPosition(MultiGame sendmethod, Socket partner)
	{
		this.sendmethod = sendmethod;
		this.partner = partner;
	}

	@Override
	public void run() {
		
	if (Multiplayer.isServer == true)	{
		while(true)
		{
			sendmethod.sendMessage(partner, Integer.toString(MultMasterFrame.posA_Y) , "yPos");
			for(int i=0; i<MultMasterFrame.weapons.size();i++)
				{
					if (( MultMasterFrame.weapons.get(i).direction == 1)&&(MultMasterFrame.weapons.get(i).sent==false))
					{	//sends message fireball shot, only once
						sendmethod.sendMessage(partner, Integer.toString(MultMasterFrame.weapons.get(i).posY),"weap");
						MultMasterFrame.weapons.get(i).sent = true; //sets sent true, so only sent once
					}
				}
			
		}}
	else{
		while(true)
		{	
			sendmethod.sendMessage(partner, Integer.toString(MultMasterFrame.posB_Y) , "yPos");
			for(int i=0; i<MultMasterFrame.weapons.size();i++)
			{
				if (( MultMasterFrame.weapons.get(i).direction == 2)&&(MultMasterFrame.weapons.get(i).sent==false))
				{	//sends message fireball shot, only once
					sendmethod.sendMessage(partner, Integer.toString(MultMasterFrame.weapons.get(i).posY),"weap");
					MultMasterFrame.weapons.get(i).sent = true; //sets sent true, so only sent once
				}
			}
			
		}}
		
	}

}
