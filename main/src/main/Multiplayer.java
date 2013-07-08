package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.DefaultCaret;

public class Multiplayer extends JFrame {
	
	private InetAddress ip;
	private JLayeredPane pane2;
	
	protected JLabel hostLabel, ipLabel, connectLabel, chatInputLabel; 
	protected JButton verbindenButton, sendenButton, hostButton, multiGoButton, exitButton;
	protected String hostName, ipV4, connectIP; 
	protected JTextField chatField, ip1, ip2, ip3, ip4;
	protected JTextArea chatArea;
	protected ChatServer server;
	protected ChatClient client;
	protected boolean go = true;


	
	public Multiplayer(){
		
		super("Netzwerkmodus");
		getIP();			// gets own ip adress value needs to be reworked not direct usable
		
		//creates visible panel elements   
		netPanel panel = new netPanel();
		
		//window frame option
		this.setSize(700,700);
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

		
		//start chat server to listen to port
		server = new ChatServer(Multiplayer.this);
		
		//Multiplayer button event listener and tries to connect to open port, port needs to be opened by server
		verbindenButton.addActionListener(new ActionListener(){
			   	public void actionPerformed(ActionEvent e){				
	
			   		
			   	//validates if number could possibly an ip number
			   		if 	(   ( ( (Integer.valueOf(ip1.getText()) )>0) && ( (Integer.valueOf(ip1.getText()) )<255) ) &&
			  			    ( ( (Integer.valueOf(ip2.getText()) )>0) && ( (Integer.valueOf(ip2.getText()) )<255) ) &&
			 		        ( ( (Integer.valueOf(ip3.getText()) )>0) && ( (Integer.valueOf(ip3.getText()) )<255) ) &&
			   		        ( ( (Integer.valueOf(ip4.getText()) )>0) && ( (Integer.valueOf(ip4.getText()) )<255) ) ){
			  			connectIP =  ip1.getText()+"."+ip2.getText()+"."+ip3.getText()+"."+ip4.getText(); //creates entered ip adress
			   	//		chatArea.append("verbinden mit "+connectIP+"\n" );	//puts ip connect message int textfield	
			   			//create ChatClient and copy reference of chat window
			   			client = new ChatClient(Multiplayer.this);		
				   		}
	 	   		else { //ip is not valid pop up prompting to enter valid ip
						 JOptionPane.showMessageDialog(null, "Bitte gültige IP Adresse eingeben!"); 
			   			}
			   		}
			});
		
		//chat Button button event listener
		sendenButton.addActionListener(new ActionListener(){
			   	public void actionPerformed(ActionEvent e){
			   		chatArea.append(hostName+": " +chatField.getText()+"\n");
			   		//chatField.setText("");
			   		client.sendMessage(Multiplayer.this);
			   		
			   	   	}
			});
	
		//Eventlistener for exit button
		exitButton.addActionListener(new ActionListener(){
		   	public void actionPerformed(ActionEvent e){
		   		//checks if any open connection and closes
		   		if (server.serverSocket.isClosed() == false){	
		   			try{
		   				server.serverSocket.close();
		   				verbindenButton.setText("Verbinden");
		   				verbindenButton.setEnabled(true); 				//reactivate button
		   				}
		   				catch (Exception ex){
		   					chatArea.append("Verbindung kann nicht geschlossen werden");
		   					}
		   			}
		   		server.go = false;			//quiets server listening on input stream
		   		Multiplayer.this.dispose();
		   		
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
			
			//connect button (act as client)
			verbindenButton = new JButton("Verbinden");
			verbindenButton.setBounds(400,565,250,30);
			
			//host button to act as server
			multiGoButton = new JButton("Multiplayer Spiel starten");
			multiGoButton.setBounds(400, 600, 250, 30);
			
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


				
			//just label to enter ip address
			connectLabel = new JLabel("Host eingeben:");
			connectLabel.setBounds(50,570, 200,30);
			
			//first part of ipV4 address
			ip1 = new JTextField();
			ip1.setBounds(230,575,30,20);
			
			//second part of ipV4 address
			ip2 = new JTextField();
			ip2.setBounds(265,575,30,20);
			
			//third part of ipV4 address
			ip3 = new JTextField();
			ip3.setBounds(300,575,30,20);
			
			//fourth part of ipV4 address
			ip4 = new JTextField();
			ip4.setBounds(335,575,30,20);
			
						
			
		}
	}
}
