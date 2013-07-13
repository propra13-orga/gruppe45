package multiplayer;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import main.Gui;

public class Multiplayer extends JFrame {
	
	private InetAddress ip;
	private JLayeredPane pane2;
	
	protected Gui menu;
	protected JLabel hostLabel, ipLabel, connectLabel, chatInputLabel, readyLabel,optLabel,live1Label,live2Label; 
	protected JButton verbindenButton, sendenButton, hostButton, multiGoButton, exitButton;
	protected JComboBox mapBox,live1Box,live2Box;
	protected String hostName, ipV4, connectIP; 
	protected JTextField chatField, ip1, ip2, ip3, ip4;
	protected JTextArea chatArea;
	protected ChatServer server;
	protected ChatClient client;
	protected boolean go = false;
	protected netPanel panel;
	protected MultiGame gameClient, gameServer;
	protected String[] maps = {"Bitte Karte auswählen","Bunny Paradies","Im Abyss","Todesstachel","Matrix"};
	protected String[] lives = {"1 Leben","2 Leben","3 Leben","4 Leben","5 Leben"};
	public static int map = 0;
	protected int instanz = 0;

	
	public static boolean isServer = true;


	
	public Multiplayer(final Gui menu){
		
		super("Netzwerkmodus");
		getIP();			// gets own ip adress value needs to be reworked not direct usable
		this.menu = menu;
		//creates visible panel elements   
		panel = new netPanel();
		
		//window frame option
		this.setSize(1000,700);
		this.setResizable(false);
		this.setAlwaysOnTop(true);
		this.setVisible(true);
		
		//creates content pane of visible icons with layers
		pane2 =  getLayeredPane();
		pane2.add(hostLabel, 10);
		pane2.add(ipLabel, 10);
		pane2.add(verbindenButton, 10);
		pane2.add(sendenButton, 10);
		pane2.add(ip1,10);
		pane2.add(ip2, 10);
		pane2.add(ip3, 10);
		pane2.add(ip4, 10);
		pane2.add(connectLabel, 10);
		pane2.add(chatArea, 10);		
		pane2.add(exitButton,10);
		pane2.add(chatInputLabel, 10 );
		pane2.add(multiGoButton, 10);
		pane2.add(chatField, 10);
		pane2.add(readyLabel,0);
		pane2.add(mapBox, 10);
		pane2.add(optLabel,10);
		pane2.add(live1Box,10);
		pane2.add(live2Box,10);
		pane2.add(live1Label,10);
		pane2.add(live2Label,10);

		
		//start chat server to listen to port
		server = new ChatServer(Multiplayer.this);
		

		
		//Connect button event listener and tries to connect to open port, port needs to be opened by server
		verbindenButton.addActionListener(new ActionListener(){
			   	public void actionPerformed(ActionEvent e){				
	
			   		
			   	//validates if number could possibly an ip number
			   		if 	(   ( ( (Integer.valueOf(ip1.getText()) )>=0) && ( (Integer.valueOf(ip1.getText()) )<255) ) &&
			  			    ( ( (Integer.valueOf(ip2.getText()) )>=0) && ( (Integer.valueOf(ip2.getText()) )<255) ) &&
			 		        ( ( (Integer.valueOf(ip3.getText()) )>=0) && ( (Integer.valueOf(ip3.getText()) )<255) ) &&
			   		        ( ( (Integer.valueOf(ip4.getText()) )>=0) && ( (Integer.valueOf(ip4.getText()) )<255) ) ){
			  			connectIP =  ip1.getText()+"."+ip2.getText()+"."+ip3.getText()+"."+ip4.getText(); //creates entered ip adress
			   			//create ChatClient and copy reference of chat window
			  			sendenButton.setEnabled(true);
			  			multiGoButton.setEnabled(true);
			   			client = new ChatClient(Multiplayer.this);		
				   		}
	 	   		else { //ip is not valid pop up prompting to enter valid ip
						 JOptionPane.showMessageDialog(null, "Bitte gültige IP Adresse eingeben!"); 
			   			}
			   		}
			});
		
/**chat Button button event listener
  decides if player is server or client and create appropriate object  **/
		sendenButton.addActionListener(new ActionListener(){
			   	public void actionPerformed(ActionEvent e)
			   	{
			   		if (isServer == false)
			   			{
			   				client.sendMessage(Multiplayer.this);
			   			}
			   		if (isServer == true)
			   			{
			   				server.sendMessage(Multiplayer.this);
			   			}
			   	  	}
			});
		
		mapBox.addActionListener(new ActionListener(){
		   	public void actionPerformed(ActionEvent e)
		   	{
		   			//action  when map is choosen

		   	  	}
		});
		
		
		//Eventlistener for exit button
		exitButton.addActionListener(new ActionListener(){
		   	public void actionPerformed(ActionEvent e){
		   		//checks if any open connection and closes

		   		/** this is client side, client closes its sockets **/
   		   		if (isServer == true)
   		   		{
		   			if (server.serverSocket.isClosed() == false){	
		   				try{
		   					server.serverSocket.close();
		   					}
		   				catch (Exception ex)
		   				{
		   						chatArea.append("Verbindung kann nicht geschlossen werden");
		   					}
		   				}
		   			}
		   		else{  //closes socket on client side
		   			if (client.serverSocket.isClosed() == false){	
		   				try{
		   				 		client.serverSocket.close();
			   				}
		   				catch (Exception ex)
		   				{
		   						chatArea.append("Verbindung kann nicht geschlossen werden");
		   					}
		   			}}
		   		Multiplayer.this.dispose(); 		//closes multiplayer gui
		   		server.isConnected = false;			//sets boolean to announce disconnection
		   	   	}
		});
		
		//Multiplayer start Button button event listener
				multiGoButton.addActionListener(new ActionListener(){
					   	public void actionPerformed(ActionEvent e){
					   		if (isServer == false)//player is Client
					   		{
					   			gameClient = new MultiGame(client.server);
					   			gameClient.sendMessage(client.server, hostName , "ready");
					   			readyLabel.setVisible(true);
					   			SendPosition send = new SendPosition(gameClient,client.server);
					   			Thread sent = new Thread(send);
		   						sent.start();
					   		
					   			
					   		}
					   		if (isServer == true)//player is Server
					   		{   //checks if in and out socket is connected if not connects additional socket
					   			if (server.isConnected == false)
					   				{server.connectToClient();}
					   			
					   			if (server.clientReady==true)
					   				{
					   					map = mapBox.getSelectedIndex();
					   					if (map==0)
					   					{
					   						JOptionPane.showMessageDialog(null, "Bitte Karte auswählen");
					   					}
					   					else
					   					{
					   						gameServer = new MultiGame(server.client);		//start gameServer general tag send method
					   						gameServer.sendMessage(server.client,Integer.toString(map),"map");
					   						gameServer.sendMessage(server.client, "", "start");			//sends client method that game is going to start
					   						gameServer.sendMessage(server.client,Integer.toString(live1Box.getSelectedIndex()+1) , "live1");			//sends client method that game is going to start
					   						gameServer.sendMessage(server.client,Integer.toString(live2Box.getSelectedIndex()+1), "live2");			//sends client method that game is going to start
					   						MultMasterFrame.lives1= live1Box.getSelectedIndex()+1;
					   						MultMasterFrame.lives2= live2Box.getSelectedIndex()+1;
					   						Multiplayer.this.dispose();									//closes multiplayer gui
					   						menu.dispose();												//closes game gui
					   						main.Main.window.dispose();
					   						startGame();
					   						SendPosition send = new SendPosition(gameServer,server.client);	
					   						Thread sent = new Thread(send);
					   						sent.start();
					   						
					   						
					     				}
					   				}
					   			else{
									JOptionPane.showMessageDialog(null, "Spieler 2 noch nicht bereit! \n Spieler muss erst bestätigen");
					   			}
					   				
					   		}
					   	   	}
					});
			
		
	}//end multiplayer	

		
	public void getIP(){	
	
	try {	
			//get local IP address
			Socket s = new Socket("www", 80);
			ip = s.getLocalAddress();
			s.close();
									
			//get local Hostname
	        InetAddress thisIp = InetAddress.getLocalHost();  			//gets hostname
	        hostName = InetAddress.getLocalHost().getHostName();		//prints hostname
	        
	        
	    } catch (Exception e) {
	       System.out.println("Fehler in IP resolve");
	    }
	ipV4 = String.valueOf(ip);
	
	//remove 1st slash from IP
	char[] stringArray = ipV4.toCharArray();
	String tmp="";
	for(int i=0; i< (stringArray.length-1); i++){
		tmp += stringArray[i+1];
	}
	ipV4 = tmp;	
		
	}

	public class netPanel extends JPanel{ //panel with buttons and stuff
		
		public netPanel(){
			
			//Label that displays local ip address
			ipLabel = new JLabel("LAN : "+ipV4);
			ipLabel.setBounds(50, 620, 250, 30);
			
			//Label that displays hostname 
			hostLabel = new JLabel("Hostname: "+hostName);
			hostLabel.setBounds(50, 600, 250, 30);
			
			//send button
			sendenButton = new JButton ("Chat Senden");
			sendenButton.setBounds(400,530,250,30);
			sendenButton.setEnabled(false);
			
			//connect button (act as client)
			verbindenButton = new JButton("Verbinden");
			verbindenButton.setBounds(400,565,250,30);
			
			//host button to act as server
			multiGoButton = new JButton("Multiplayer Spiel starten");
			multiGoButton.setBounds(400, 600, 250, 30);
			multiGoButton.setEnabled(false);
			
			//start Button for Multiplayer
			exitButton = new JButton("Abbrechen");
			exitButton.setBounds(400, 635, 250, 30);
			
			//just information label
			chatInputLabel = new JLabel("Chat Eingabefeld:");
			chatInputLabel.setBounds(50,479,200,30);
			
			//Chat input field
			chatField = new JTextField();
			chatField.setBounds(50, 500, 600 , 30);
			
			//chat output field
			chatArea = new JTextArea();
			chatArea.setBounds(51, 51, 599, 399);
			chatArea.setEditable(false);
				
			//just label to enter ip address
			connectLabel = new JLabel("Host eingeben:");
			connectLabel.setBounds(50,570, 200,30);
			
			//first part of ipV4 address
			ip1 = new JTextField("192");
			ip1.setBounds(230,575,30,20);
			
			//second part of ipV4 address
			ip2 = new JTextField("168");
			ip2.setBounds(265,575,30,20);
			
			//third part of ipV4 address
			ip3 = new JTextField("1");
			ip3.setBounds(300,575,30,20);
			
			//fourth part of ipV4 address
			ip4 = new JTextField("34");
			ip4.setBounds(335,575,30,20);
		
			optLabel = new JLabel("Multiplayer Optionen:");
			optLabel.setBounds(700,51,250,30);

			mapBox = new JComboBox(maps);
			mapBox.setBounds(700, 91, 190, 30);
			mapBox.setSelectedItem("Bitte Karte auswählen");
			mapBox.setEditable(false);
			mapBox.setEnabled(false);
			
			live1Label = new JLabel("Leben Spieler1:");
			live1Label.setBounds(700, 141, 190, 20);
			live1Box	= new JComboBox(lives);
			live1Box.setBounds(700, 161, 190,30);
			live1Box.setEditable(false);
			live1Box.setEnabled(false);
			live1Box.setSelectedIndex(2);
			
			live2Label = new JLabel("Leben Spieler2:");
			live2Label.setBounds(700, 191, 190, 20);
			live2Box	= new JComboBox(lives);
			live2Box.setBounds(700, 211, 190,30);
			live2Box.setEditable(false);
			live2Box.setEnabled(false);
			live2Box.setSelectedIndex(2);
			
			//player ready label
			readyLabel = new JLabel("Spieler 2 bereit");
			readyLabel.setForeground(Color.RED);
			readyLabel.setBounds(700, 300, 150, 30);
			readyLabel.setVisible(false);
			
						
			
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
