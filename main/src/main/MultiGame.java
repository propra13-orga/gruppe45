package main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


/** This class creates a new small deathmatch multiplayer game **/

public class MultiGame {
	
	private PrintWriter out;
	protected Socket client, server;
//	protected ServerSocket server;
	
	public MultiGame(Socket client)
	{
			int zahl = 0;
		//	this.server = server;
			this.client = client;
			String message = "tolle Nachricht";
			while (zahl <100){
				
				sendMessage(client,message);
				zahl ++;
			}
		
		
	}
	
	
	//method to receive instructions during Multiplayer game
	public void readMessage(Socket client) 
	{
	BufferedReader br = null;
	while (true){
		try{
			 br = new BufferedReader(new InputStreamReader(client.getInputStream()));
			 String line;
			 while ((line = br.readLine()) != null)
			 { 	
				 System.out.println(line);
			
			 }
			}//end of try block
		catch (Exception e)
					{
							System.out.println("Fehler in read Message");	
						}
			}}
	
	
	//method to sent instructions during Multiplayer game
	public void sendMessage(Socket client, String message){
		
		try{
			System.out.println("Server ist closed? : "+ client.isClosed());
			
			out = new PrintWriter(client.getOutputStream(),true);
			out.println(message);
			out.flush();
		}
		catch (Exception e){
			System.out.println("MultiGame kann Nachricht nicht senden");
			}
	}
	

}
