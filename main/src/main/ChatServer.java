package main;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import main.Multiplayer;


public class ChatServer extends Thread {
	
	private String message="";
	private ServerSocket serverSocket;
	private Scanner in;
	private Boolean go=true;
	Multiplayer gui;
	
	public ChatServer(Multiplayer gui)
	{
		try
			{	//opens new ServerSocket that listens on 6666
				System.out.println("Socket geöffnet");
				serverSocket = new ServerSocket(6666);
				} catch (Exception ex) 	
			{ 
				System.out.println("Server kann keine Verbindung herstellen");
				}
		this.start();
	
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
			try{
				in = new Scanner (client.getInputStream());
				message = in.nextLine();
			//	gui.chatArea.append(message); //somehow doesn't work
				System.out.println(message);
		
				}catch (Exception e){
								System.out.println("Fehler in read Message");	}
			if (message.equals("exit")){
				try {client.close();}catch(Exception e){System.out.println("Fehler"); go = false;}
			}
		}
	


}
