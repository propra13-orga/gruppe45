package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.util.Collections;
import java.util.Enumeration;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Multiplayer extends JFrame {
	
	private String hostName, ipV4, connectIP ;
	private InetAddress ip;
	
	JLabel hostLabel, ipLabel, connectLabel, chatInputLabel; 
	JButton verbindenButton, sendenButton, hostButton, multiGoButton;
	JLayeredPane pane2;
	JTextField chatField, ip1, ip2, ip3, ip4, chatFieldL;
	JTextArea chatArea;
	
	public Multiplayer(){
		
		super("Netzwerkmodus");
		getIP();
		
		netPanel panel = new netPanel();
		
		this.setSize(700,700);
		this.setResizable(false);
		this.setAlwaysOnTop(true);
		this.setVisible(true);
		
		pane2 =  getLayeredPane();
		pane2.add(hostLabel, 10);
		pane2.add(ipLabel, 10);
		pane2.add(verbindenButton, 10);
		pane2.add(chatField, 10);
		pane2.add(sendenButton, 10);
		pane2.add(ip1,10);
		pane2.add(ip2, 10);
		pane2.add(ip3, 10);
		pane2.add(ip4, 10);
		pane2.add(connectLabel, 10);
		pane2.add(chatArea, 10);		
		pane2.add(hostButton,10);
		pane2.add(chatInputLabel, 10 );
		pane2.add(multiGoButton, 10);
		
		
		//Multiplayer button event listener validates ip adress and tries to connect
		verbindenButton.addActionListener(new ActionListener(){
			   	public void actionPerformed(ActionEvent e){
			   					 
			   try{		//checks if ip is a valid ip
			   		if (((Integer.parseInt(ip1.getText())>=0)&&(Integer.parseInt(ip1.getText())<255))&&
			   			 ((Integer.parseInt(ip2.getText())>=0)&&(Integer.parseInt(ip2.getText())<255))&&
			   				((Integer.parseInt(ip3.getText())>=0)&&(Integer.parseInt(ip3.getText())<255))&&
			   					(Integer.parseInt(ip4.getText())>=0)&&(Integer.parseInt(ip4.getText())<255))
			   		{//valid ip
			   			chatArea.append("verbinden mit "+ip1.getText()+"."+ip2.getText()+"."+ip3.getText()+"."+ip4.getText()+"\n" );	//puts ip connect message int textfield	
			   			//create ChatClient and copy reference of chat window
			   			ChatClient client = new ChatClient(Multiplayer.this);
				   		}
			   		else { //ip is not valid pop up prompting to enter valid ip
						 JOptionPane.showMessageDialog(null, "Bitte gültige IP Adresse eingeben!"); 
			   			}
			   		}//end of try block ip is not valid
			   catch (Exception f)
			   		{		//if ip NaN
				   JOptionPane.showMessageDialog(null, "Bitte gültige IP Adresse eingeben!"); 
			   		}
			   	}
			});
		
		//chat Button button event listener
		sendenButton.addActionListener(new ActionListener(){
			   	public void actionPerformed(ActionEvent e){
			   		chatArea.append(hostName+": " +chatField.getText()+"\n");
			   		chatField.setText("");
			   		
			   	}
			});
		
		//hostButton to start server
		hostButton.addActionListener(new ActionListener(){
		   	public void actionPerformed(ActionEvent e){
		   		chatArea.append(hostName+": game Server wird gestartet..."+"\n");
		   		//create ChatServer and copy reference of chat window
		  		ChatServer server = new ChatServer(Multiplayer.this);
	
		   		
		   		
		   		
		   	}
		});
		

		
		
	}	
	
		
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

	public class netPanel extends JPanel{
		
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
			hostButton = new JButton("Spiel hosten");
			hostButton.setBounds(400, 600, 250, 30);
			
			//start Button for Multiplayer
			multiGoButton = new JButton("Multiplayer Spiel starten");
			multiGoButton.setBounds(400, 635, 250, 30);
			
			//Chat input field
			chatField = new JTextField();
			chatField.setBounds(50, 500, 600 , 30);
			
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
			
			//area big box displaying chat and events
			chatArea = new JTextArea();
			chatArea.setBounds(51, 51, 599, 399);
			
			//just information label
			chatInputLabel = new JLabel("Chat Eingabefeld:");
			chatInputLabel.setBounds(50,479,200,30);
			

			
			

			
		}
	}
}
