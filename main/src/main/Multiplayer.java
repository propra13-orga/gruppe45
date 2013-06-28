package main;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.util.Collections;
import java.util.Enumeration;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class Multiplayer extends JFrame {
	
	private String hostName, ip4 ;
	private InetAddress ip;
	JLabel hostLabel, ipLabel;
	
	JLayeredPane pane2;
	
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
	ip4 = String.valueOf(ip);
	
	//remove 1st slash from IP
	char[] stringArray = ip4.toCharArray();
	String tmp="";
	for(int i=0; i< (stringArray.length-1); i++){
		tmp += stringArray[i+1];
	}
	ip4 = tmp;	
		
	}

	public class netPanel extends JPanel{
		
		public netPanel(){
			
			hostLabel = new JLabel("Hostname: "+hostName);
			hostLabel.setBounds(100, 100, 250, 30);
			
			ipLabel = new JLabel("LAN Adresse: "+ip4);
			ipLabel.setBounds(100, 150, 250, 30);
			
			
		}
	}
}
