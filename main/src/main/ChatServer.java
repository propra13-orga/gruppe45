package main;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import main.Multiplayer;


import javax.swing.JOptionPane;



public class ChatServer {
	
	private String message="";
	ServerSocket serverSocket;
	Socket client;
	Scanner in;
	PrintWriter out;
	Multiplayer gui;
	Boolean go=true;
	
	public ChatServer(Multiplayer gui){
		this.gui = gui;
		
		try
		{	//opens new ServerSocket that listens on 6666
			serverSocket = new ServerSocket(6666);
		} catch (Exception ex) 	{ 
			System.out.println("Server kann keine Verbindung herstellen");
		}
		
		while (go){
			Socket client = null;
			try{ //accepts incomming connections
				client = serverSocket.accept();
				readMessage(client);
			}
			catch (Exception e)
			{	message = "eingehende Verbindung kann nicht akzeptiert werden";
			}}
		
		
			
		
	}
	
	public void readMessage(Socket client)
		{
			try{
				in = new Scanner (client.getInputStream());

				System.out.println(in.nextLine());
			//	gui.chatArea.append(message+"\n");
				}catch (Exception e){
								System.out.println("Fehler in read Message");	}
			if (message.equals("exit")){
				try {client.close();}catch(Exception e){System.out.println("Fehler"); go = false;}
			}
		}
	


}
