package main;

import java.io.PrintWriter;
import java.net.Socket;


public class ChatClient {
	
	private String message, ip;

	private Socket server;
	private PrintWriter out;
		
	public ChatClient(Multiplayer gui){
		

		
		try
	    {
			ip = gui.connectIP; //gets inserted server ip from Multiplayer
			server = new Socket (ip,6666);	// creates socket to call server
	    

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
		System.out.println("Client kann keine Verbindung aufbauen");
		}
		
	}//end ChatClient
	
	public void sendMessage(Multiplayer gui){
		try{
		//	out = new PrintWriter(server.getOutputStream(),true);
			message = gui.hostName+": "+gui.chatField.getText();
			gui.chatArea.append(message+"\n");							//displays chat message on client screen
			out.println(message);
		}
		catch (Exception e){
			System.out.println("Client kann Nachricht nicht senden");
			}
	}

}