package multiplayer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;


public class ChatClient extends Thread{
	
	private String message, ip;
	protected ServerSocket serverSocket;
	protected Socket server;
	protected PrintWriter out;
	protected Multiplayer gui;
	protected Boolean go=true;
	protected BufferedReader in;
		
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
			out.close();
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
					 String value = line.replace("<.*>", "");
					 /**JOptionPane.showMessageDialog(null, "Es wurde ein Tag versendet: "+tag+" Mit dem Wert: "+value);
------------------------------------------------------------------------------------------------------------------------------*/					 
					 //from here on client instructions can be received by tag and value
					 if (tag.equals("start")){
						 gui.dispose();
/**---------------------------------------------------------------------------------------------------------------------------*/						 
					 }
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

}
