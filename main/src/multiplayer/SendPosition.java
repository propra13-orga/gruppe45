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
		
	
		while(true)
		{
			if (Multiplayer.isServer == true)	{
			sendmethod.sendMessage(partner, Integer.toString(MultMasterFrame.posA_Y) , "yPos");}
		
			else{
			sendmethod.sendMessage(partner, Integer.toString(MultMasterFrame.posB_Y) , "yPos");}
		}
		
	}

}
