package multiplayer;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

/**
 * Creates server to connect to client
 * @author JessicaBa
 *
 */


public class ChatServer extends Thread {
	
	private String message="", clientIPraw, clientIP="";
	protected ServerSocket serverSocket;
	protected BufferedReader in;
	protected Boolean go=true;
	protected Multiplayer gui;
	protected boolean connected = false; //indicates if server is running on current instance, true = this is server
	protected PrintWriter out;
	protected boolean isConnected= false; //states if server out is connected
	protected Socket client;
	protected boolean clientReady = false;
	
	/**
	 * creates socket
	 * @param gui - multiplayer gui
	 */
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
	
	/**
	 * Thread for incomming clients
	 */
	public void run(){
		while (go){
			Socket client = null;
			
			try{ //accepts incomming connections
				client = serverSocket.accept();
				clientIPraw = client.getInetAddress().toString();
				formatIP();
				Multiplayer.isServer = true;
				readMessage(client, gui);
			
			}
			catch (Exception e)
			{	message = "eingehende Verbindung kann nicht akzeptiert werden";
			}}
	}
	
	public void formatIP(){
		gui.verbindenButton.setText("Client verbunden");
		gui.multiGoButton.setEnabled(true);
		connected = true;
		gui.sendenButton.setEnabled(true);
		gui.verbindenButton.setEnabled(false);				//connection already received preventing additional out connect
		gui.mapBox.setEnabled(true);
		gui.live1Box.setEnabled(true);
		gui.live2Box.setEnabled(true);
		gui.hp1Box.setEnabled(true);
		gui.hp2Box.setEnabled(true);
		//remove 1st slash from IP
		char[] stringArray = clientIPraw.toCharArray();
		clientIP="";
		for(int i=0; i< (stringArray.length-1); i++){
			clientIP += stringArray[i+1];
		}
	//	this.clientIP = clientIPraw;
	}
	
	/**
	 * receives incomming requests and messanges from client
	 * @param client - client socket
	 * @param gui - multiplayer gui
	 */
	public void readMessage(Socket client, Multiplayer gui) 
		{
		in = null;
	
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
					/**	 JOptionPane.showMessageDialog(null, "Es wurde ein Tag versendet: "+tag+" Mit dem Wert: "+value);
------------------------------------------------------------------------------------------------------------------------------*/					 
						 //from here on server instructions can be received by tag and value
						 
						 //System.out.println(tag);
						 if (tag.equals("ready")){
							 //player is ready for play
							 gui.readyLabel.setVisible(true);
							 gui.chatArea.append(value+" ist bereit. Spiel kann beginnen \n");
							 clientReady = true; }
							 
						 if (tag.equals("yPos")){
							 MultMasterFrame.posB_Y = Integer.valueOf(value);
						 	}
			
						 if (tag.equals("weap")){

							 String[] values = value.split(Pattern.quote("|"));
							 MultMasterFrame.weapons.get(Integer.valueOf(values[2])).posX = Integer.valueOf(values[0]);
							 MultMasterFrame.weapons.get(Integer.valueOf(values[2])).posY = Integer.valueOf(values[1]);
							 MultMasterFrame.weapons.get(Integer.valueOf(values[2])).direction = 2;
							 MultMasterFrame.weapons.get(Integer.valueOf(values[2])).used = true;
						//	 System.out.println("Empfangenes value: "+value);
							 
						 }
						 
/**---------------------------------------------------------------------------------------------------------------------------*/						 
						 
					 }
					 else
					 {
					 				
					 System.out.println(line);
					 gui.chatArea.append(line+"\n");
					 }
				 }
				 in.close();
				}//end of try block
			catch (Exception e)
						{
								System.out.println("Fehler in read Message");	
							}
		}	
	//opens new socket to client
	/**
	 * sends messanges to client
	 * @param gui - multiplayer gui
	 */
	public void sendMessage(Multiplayer gui){
		
		if (isConnected == false)
		{
			connectToClient();
		}
		
	
		try{
			out = new PrintWriter(client.getOutputStream(),true);
		
			message = gui.hostName+": "+gui.chatField.getText();
			gui.chatArea.append(message+"\n");							//displays chat message on client screen
			gui.chatField.setText("");
			out.println(message);
			out.flush();
			//out.close();
			
		}
		catch (Exception e){
			System.out.println("Server kann Nachricht nicht senden");
			}
		//System.out.println("Server ist closed? : "+ client.isClosed());
	}
	
	/*
	 * connects to client to send messanges
	 */
	public void connectToClient()
	{
		try{	
			client = new Socket(clientIP,6667);
			isConnected = true;
			}
		catch(Exception i) {System.out.println("Fehler in ChatServer.sendMessage");}
		
	}
	


}
