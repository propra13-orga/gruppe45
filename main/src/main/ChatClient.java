package main;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import main.Multiplayer;

public class ChatClient {
	
	private String message="";
	Socket server;
	Multiplayer gui;
	PrintWriter out;
		
	public ChatClient(Multiplayer gui){
		
		this.gui = gui; //copies reference to Multiplayer object to work with
		
		try
	    {
	      //Verbindung zu Port 13000 auf localhost aufbauen:
			//localhost = Servernamen
	      server = new Socket ("superloser",6666);
	    }
	    catch (Exception ex)
	    {
	       message = "keine Verbindung zum Server möglich";
	    }
		
		try
		{
			out = new PrintWriter(server.getOutputStream(),true);

			out.println("Dies ist eine Nachricht vom Client");
		}
		catch (Exception e){
			System.out.println("Es ist ein Fehler aufgetreten");
		}
		
		
		
		
		try{ //closes socket
		server.close();}
		catch (IOException e){
			System.out.println("Socket kann nicht geschlossen werden.");
		}
		
	}

}
