package multiplayer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.regex.Pattern;


public class ChatClient extends Thread{
	
	private String message, ip;
	protected ServerSocket serverSocket;
	protected Socket server;
	protected PrintWriter out;
	protected Multiplayer gui;
	protected Boolean go=true;
	protected BufferedReader in;
	protected int instanz = 0;
		
	public ChatClient(Multiplayer gui){
		

		this.gui = gui;
		try
	    {
			ip = gui.connectIP; //gets inserted server ip from Multiplayer
			server = new Socket (ip,6666);	// creates socket to call server
			gui.verbindenButton.setText("Verbindung aktiv");
			gui.verbindenButton.setEnabled(false);
			gui.multiGoButton.setText("bereit Spiel starten");
			serverSocket = new ServerSocket(6667);
			Multiplayer.isServer = false;
			this.start();
		
	    

	    }
	    catch (Exception ex)
	    {
	       message = "keine Verbindung zum Server möglich";
	    }
		
		try
		{
			out = new PrintWriter(server.getOutputStream(),true);		//object needed for socket message
			message = gui.hostName+" verbunden";		//creates chatmessage with hostname
			gui.chatArea.append(message+"\n");							//displays chat message on client screen
			out.println(message);										//sends message to server
		}
		catch (Exception e){
		System.out.println("Client kann keine Verbindung aufbauen");}
		}
		
	
	public void sendMessage(Multiplayer gui){
		try
	    {
			ip = gui.connectIP; //gets inserted server ip from Multiplayer
		 }
	    catch (Exception ex)
	    {
	       message = "Nachricht kann nicht versendet werden";
	    }
		try{
			System.out.println("Server ist closed? : "+ server.isClosed());
			out = new PrintWriter(server.getOutputStream(),true);
			message = gui.hostName+": "+gui.chatField.getText();
			gui.chatArea.append(message+"\n");							//displays chat message on client screen
			gui.chatField.setText("");
			out.println(message);
			out.flush();
			//out.close();
		}
		catch (Exception e){
			System.out.println("Client kann Nachricht nicht senden");
			}
		
	}
	public void run(){
		while (go){
			Socket client = null;
			
			try{ //accepts incomming connections
				
				client = serverSocket.accept();
				readMessage(client, gui);
			
			}
			catch (Exception e)
			{	message = "eingehende Verbindung kann nicht akzeptiert werden";
			}}
	}
	
	
	public void readMessage(Socket client, Multiplayer gui) 
	{
	 in = null;
	while (true){
		try{
			 in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			 String line;
			 while ((line = in.readLine()) != null)
			 { 		
				 //parses input  stream if tag with instructions (values) is received
				 if (Pattern.matches("<.*>.*", line))
				 {	
					 String tag = line.replaceAll(">.*","");
					 tag = tag.replaceAll("<", "");
					 String value = line.replaceAll("<.*>", "");
					 /**JOptionPane.showMessageDialog(null, "Es wurde ein Tag versendet: "+tag+" Mit dem Wert: "+value);
------------------------------------------------------------------------------------------------------------------------------*/					 
					 //from here on client instructions can be received by tag and value
					 if (tag.equals("map")){
						 Multiplayer.map = Integer.parseInt(value);
					 }
					 
					 if (tag.equals("live1")){
						 MultMasterFrame.lives1=Integer.parseInt(value);
					 }
					 
					 if (tag.equals("live2")){
						 MultMasterFrame.lives2=Integer.parseInt(value);
					 }
					 
					 if (tag.equals("hp1")){
						 MultMasterFrame.hp1=Integer.parseInt(value);
					 }
					 
					 if (tag.equals("hp2")){
						 MultMasterFrame.hp2=Integer.parseInt(value);
					 }
					 
					 if (tag.equals("start")){
						 gui.dispose();			//closes multiplayer gui
						 gui.menu.dispose();	//closes game gui
						 main.Main.window.dispose();
						 startGame();
						 }
					 
					 if (tag.equals("yPos")){
						 MultMasterFrame.posA_Y = Integer.valueOf(value);
					 	}
					 if (tag.equals("weap"))
					 {
						 //System.out.println("value empfangen: "+value);
						 String[] values = value.split(Pattern.quote("|"));
						 
						 /*asks if fireball is of player 2*/
						 if ((values[4].equals("2")))//reuse array space 
						 {
							 MultMasterFrame.weapons.get(Integer.valueOf(values[2])).direction = 2;
							 if (values[3].equals("false"))
									 {
								 		MultMasterFrame.weapons.get(Integer.valueOf(values[2])).used = false;
									 }
							
						 }else
						 {
							 MultMasterFrame.weapons.get(Integer.valueOf(values[2])).direction = 2; 
						 }
				
						 MultMasterFrame.weapons.get(Integer.valueOf(values[2])).posX = Integer.valueOf(values[0]);
						 MultMasterFrame.weapons.get(Integer.valueOf(values[2])).posY = Integer.valueOf(values[1]);
					 }
					 
						if (tag.equals("hit"))
						{
							MultMasterFrame.hit = true;
						}
						
						if (tag.equals("poison"))
						{
							String[] values = value.split(Pattern.quote("|"));
							MultMasterFrame.poisonX = Integer.valueOf(values[0]);
							MultMasterFrame.poisonY = Integer.valueOf(values[1]);
							MultMasterFrame.poisonSet = true;
							
						}
						if (tag.equals("poisonGone"))
						{
							MultMasterFrame.poisonSet = false;
						}
						
						if (tag.equals("lost1"))
						{
							MultMasterFrame.lost1 = true;
						}
						
						if (tag.equals("lost2"))
						{
							MultMasterFrame.lost2 = true;
						}
						
						if (tag.equals("carrot"))
						{
							MultMasterFrame.carrot = true;
						}
						
										
						
/**---------------------------------------------------------------------------------------------------------------------------*/						 
					 
				 }
				 else
				 {
					 System.out.println(line);
					 gui.chatArea.append(line+"\n");
				 }	 
			 }
			}//end of try block
		catch (Exception e)
					{
							System.out.println("Fehler in read Message");	
						} 
			}
	
	} 
	public void startGame(){
		if (instanz != 1){
		 instanz ++;	
		 MultMasterFrame window = new MultMasterFrame();
		 Thread draw = new Thread(window);
		 draw.start();}
	}
}
