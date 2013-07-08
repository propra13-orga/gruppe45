package main;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;




public class ChatServer extends Thread {
	
	private String message="", clientIPraw, clientIP="";
	protected ServerSocket serverSocket;
	private InputStream in;
	//private Scanner in;
	protected Boolean go=true;
	protected Multiplayer gui;
	
	public ChatServer(Multiplayer gui)
	{
		this.gui = gui;
		
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
				clientIPraw = client.getInetAddress().toString();
				formatIP();


	//			ServerRead input = new ServerRead();
	//			input.run( client, this.gui);
				readMessage(client, gui);
			
			}
			catch (Exception e)
			{	message = "eingehende Verbindung kann nicht akzeptiert werden";
			}}
	}
	
	public void formatIP(){
		//remove 1st slash from IP
		char[] stringArray = clientIPraw.toCharArray();
		clientIP="";
		for(int i=0; i< (stringArray.length-1); i++){
			clientIP += stringArray[i+1];
		}
		clientIP = clientIPraw;
	}
	
	public void readMessage(Socket client, Multiplayer gui) 
		{
		BufferedReader br = null;
		
			try{
				 br = new BufferedReader(new InputStreamReader(client.getInputStream()));
				 String line;
				 while ((line = br.readLine()) != null)
				 { 	
					 System.out.println(line);
					 gui.chatArea.append(line+"\n");
				 }
				}//end of try block
			catch (Exception e)
						{
								System.out.println("Fehler in read Message");	
							}
				}
	


}
