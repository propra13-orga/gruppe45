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


	public void run() {
		String value="";
	if (Multiplayer.isServer == true)	{
		while(true)
		{
			sendmethod.sendMessage(partner, Integer.toString(MultMasterFrame.posA_Y) , "yPos");
			
			//hp
			sendmethod.sendMessage(partner, Integer.toString(MultMasterFrame.hp1) , "hp1");
			sendmethod.sendMessage(partner, Integer.toString(MultMasterFrame.hp2) , "hp2");

			//live
			sendmethod.sendMessage(partner, Integer.toString(MultMasterFrame.lives1) , "live1");
			sendmethod.sendMessage(partner, Integer.toString(MultMasterFrame.lives2) , "live2");

			
			
			for(int i=0; i<10;i++)
			{	
			
					String X = Integer.toString(MultMasterFrame.weapons.get(i).posX);
					String Y = Integer.toString(MultMasterFrame.weapons.get(i).posY);
					String pos = Integer.toString(i);
					String use = new Boolean (MultMasterFrame.weapons.get(i).used).toString();
					if (i>4)
					{
						value = X+"|"+Y+"|"+pos+"|"+use+"|2";
					}else 
					{
						value = X+"|"+Y+"|"+pos+"|"+use+"|1";
						}
					sendmethod.sendMessage(partner, value,"weap");
					//System.out.println(value);
		
			}
			
			if (MultMasterFrame.hit)
			{
				sendmethod.sendMessage(partner, "", "hit");
			}
			
			if (MultMasterFrame.poisonSet)
			{
				value =Integer.toString(MultMasterFrame.poisonX)+"|"+Integer.toString(MultMasterFrame.poisonY);
				sendmethod.sendMessage(partner,value , "poison");
			}
			if (!MultMasterFrame.poisonSet)
			{
				sendmethod.sendMessage(partner,value , "poisonGone");
			}
			
			if (MultMasterFrame.lost1)
			{
				sendmethod.sendMessage(partner,"" , "lost1");
			}
			
			if (MultMasterFrame.lost2)
			{
				sendmethod.sendMessage(partner,"" , "lost2");
			}
			
			if (MultMasterFrame.carrot)
			{
				sendmethod.sendMessage(partner,"" , "carrot");
			}
			
			
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			System.out.println("sleep in sendPosition not possible");
		}
			
		}}
	else{
		while(true)
		{	
			sendmethod.sendMessage(partner, Integer.toString(MultMasterFrame.posB_Y) , "yPos");
		

			if (MultMasterFrame.isStarted){	//indicates if Array already created
			for (int i=5; i<10;i++)
			{
				if ((MultMasterFrame.weapons.get(i).used == true)&&(!(MultMasterFrame.weapons.get(i).isSent)))
				{
					String X = Integer.toString(MultMasterFrame.weapons.get(i).posX);
					String Y = Integer.toString(MultMasterFrame.weapons.get(i).posY);
					String pos = Integer.toString(i);
					
					value = X +"|"+ Y+"|"+pos;
					sendmethod.sendMessage(partner, value,"weap");
					MultMasterFrame.weapons.get(i).isSent = true;
//					System.out.println("tag versendet");
				}
			}}
			
			
			
		/*	try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		}/*while true ende*/}/*else ende*/
		
	}
}
