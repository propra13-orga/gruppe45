package main;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JFrame;
import main.Main;
import java.awt.event.*;
import javax.swing.JLayeredPane;
//import java.io.IOException;

public class Gui extends JFrame implements ActionListener{
	
	private int height = Main.board_height;
	private int width = Main.board_width;
	
	
	startPanel start = new startPanel();				//create Gui Elements
	
	JButton go,exit;
	
	public Gui(){
		
		setTitle("Lucky Bunny");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(width, height);							//Window Size
		setResizable(false);							//Window frame not resizable
		
		
		this.setLayout(null);
		this.add(go);
		this.add(exit);
		this.setVisible(true);
		go.addActionListener(this);	
	    
		//exit button
		exit.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	     		System.exit(EXIT_ON_CLOSE);
	    	}
	    });
	}
	
	


//starts new game when clicked			 
 public void actionPerformed(ActionEvent e) 
 	{
	     dispose();                        			//close Window
	     Main.ingame = true;						//set game logic on
	     Main.go = true;							//gives boolean to main to start game
	   
 	}
  
//Gui Panel 
 public class startPanel extends JPanel
 	{
	 	public startPanel()
	 	{	
	 		//start button
	 		go = new JButton("Neues Spiel");
	 		go.setBounds(width-300,height-400,200,30);
	 		//exit button
	 		exit = new JButton("Beenden");
	 		exit.setBounds(width-300,height-360,200,30);
	 	}
	 }
}
