package main;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;


public class ChatClient {
	
	private String message, ip;

	protected Socket server;
	private PrintWriter out,out2;
		
	public ChatClient(Multiplayer gui){
		

		
		try
	    {
			ip = gui.connectIP; //gets inserted server ip from Multiplayer
			server = new Socket (ip,6666);	// creates socket to call server
			gui.verbindenButton.setText("Verbindung aktiv");
			gui.verbindenButton.setEnabled(false);
	    

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
			out.println(message);
			out.flush();
		}
		catch (Exception e){
			System.out.println("Client kann Nachricht nicht senden");
			}
		
	}

}
