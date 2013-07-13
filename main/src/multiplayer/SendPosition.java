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
		}}
	else{
		while(true)
		{	
			sendmethod.sendMessage(partner, Integer.toString(MultMasterFrame.posB_Y) , "yPos");
		}}
		
	}

}
