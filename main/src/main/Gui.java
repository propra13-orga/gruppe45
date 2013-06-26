package main;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JFrame;
import main.Main;

import java.awt.Color;
import java.awt.event.*;
import java.awt.Font;
import javax.swing.JLayeredPane;

public class Gui extends JFrame implements ActionListener{
	
	private int height = Main.board_height;
	private int width = Main.board_width;
		
	startPanel start = new startPanel();				//create Gui Elements
	
	JLabel foxLabel, treeLabel1,treeLabel2, treeLabel3, backLabel, titleLabel, bunnyLabel, hedgeLabel, shopLabel;
	JLabel messageLabel, messageLabelIn;
	
	JButton go, exit, person, option, backButton, editorButton, multyButton;
	JLayeredPane pane;
	
	public Gui(){
		
		Main.ingame = false;
		
		setTitle("Lucky Bunny");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(width, height);							//Window Size
		setResizable(false);							//Window frame not resizable
		this.setAlwaysOnTop(true);
		this.setUndecorated(true);
		pane =  getLayeredPane();
		
		this.setLayout(null);
		
		pane.add(go, 			 new Integer(10));
		pane.add(exit, 			 new Integer(10));
		pane.add(foxLabel, 		 new Integer(10));
		pane.add(treeLabel1, 	 new Integer(10));
		pane.add(treeLabel2, 	 new Integer(10));
		pane.add(treeLabel3, 	 new Integer(10));
		pane.add(titleLabel, 	 new Integer(100));		//Game title
		pane.add(backLabel, 	 new Integer(1));		//background image
		pane.add(bunnyLabel,	 new Integer(10));
		pane.add(hedgeLabel,	 new Integer(10));
		pane.add(shopLabel,		 new Integer(10));
		pane.add(person ,		 new Integer(10));
		pane.add(option,		 new Integer(10));
		pane.add(messageLabel,	 new Integer(0));
		pane.add(messageLabelIn, new Integer(0));
		pane.add(backButton,      new Integer(0));
		pane.add(editorButton,    new Integer(10));
		pane.add(multyButton,    new Integer(10));
		
		//pane.setVisible(true);
		
		go.addActionListener(this);						//adds key Listener to Gui
		
		this.setVisible(true);
		
		//exit button
		exit.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	     		System.exit(EXIT_ON_CLOSE);
	    	}
	    });
		
		//option button event listener
		option.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	     		pane.setLayer(messageLabel , 500);
	     		pane.setLayer(messageLabelIn , 510);
	    		pane.setLayer(person , 0);
		   		pane.setLayer(option , 0);
		  		pane.setLayer(go , 0);
		   		pane.setLayer(exit , 0);
		   		pane.setLayer(editorButton, 0);
	     		pane.setLayer(backButton, 515);
	    	}
	    });
		
		//Mitwirkenden button event listener
		person.addActionListener(new ActionListener(){
		   	public void actionPerformed(ActionEvent e){
		   		pane.setLayer(messageLabel , 500);
		   		pane.setLayer(messageLabelIn , 510);
		 		pane.setLayer(person , 0);
		 		pane.setLayer(option , 0);
		  		pane.setLayer(go , 0);
		  		pane.setLayer(exit , 0);
		   		pane.setLayer(backButton, 515);
		   		pane.setLayer(editorButton, 0);
		   	}
		});
		
		//Zurück button event listener
		backButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				pane.setLayer(messageLabel , 0);
		   		pane.setLayer(messageLabelIn , 0);
		  		pane.setLayer(person , 10);
		   		pane.setLayer(option , 10);
		   		pane.setLayer(go , 10);
		   		pane.setLayer(exit , 10);
		   		pane.setLayer(backButton, 0);
		   		pane.setLayer(editorButton, 10);
				   			
				   	}
				});
		
		//option button event listener
				editorButton.addActionListener(new ActionListener(){
			    	public void actionPerformed(ActionEvent e){
			    			LevelEditor editor = new LevelEditor();
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
	 	//define Colors
	 	Color lightGreen = (new Color(47, 118, 19));	
	 	public startPanel()
	 	{	
	 		//start button
	 		go = new JButton("Neues Spiel");
	 		go.setBounds(width-320,height-440,200,30);
	 		go.setForeground(Color.white );
	 		go.setBackground(Color.black);
	 		
	 		//Optionen
	 		option = new JButton("Optionen");
	 		option.setBounds(width-320,height-400,200,30);
	 		option.setForeground(Color.white );
	 		option.setBackground(Color.black);
	 		
	 		//Multiplayer Button
	 		multyButton = new JButton("Multiplayer");
	 		multyButton.setBounds(width-320,height-360,200,30);
	 		multyButton.setOpaque(true);
	 		multyButton.setBackground(Color.black);
	 		multyButton.setForeground(Color.white);
	 		
	 		//mitwirkenden
	 		person = new JButton("Mitwirkenden");
	 		person.setBounds(width-320,height-320,200,30);
	 		person.setForeground(Color.white );
	 		person.setBackground(Color.black);
	 		
	 		//Leveleditor Button
	 		editorButton = new JButton("Level Editor");
	 		editorButton.setBounds(width-320,height-280,200,30);
	 		editorButton.setOpaque(true);
	 		editorButton.setBackground(Color.black);
	 		editorButton.setForeground(Color.white);

	 		//exit button
	 		exit = new JButton("Beenden");
	 		exit.setBounds(width-320,height-240,200,30);
	 		exit.setForeground(Color.white );
	 		exit.setBackground(Color.black);
			 		
	 		backButton = new JButton("Zurück");
	 		backButton.setBounds(400,600,200,30);
	 		backButton.setOpaque(true);
	 		backButton.setBackground(Color.black);
	 		backButton.setForeground(Color.white);
	 		
	 		titleLabel = new JLabel("Lucky Bunny");
	 		titleLabel.setBounds(width-500,height-700,200,200);
	 		titleLabel.setFont(new Font(titleLabel.getName(), Font.PLAIN, 30));
	 		
	 		
	 		foxLabel = new JLabel(new ImageIcon(local.Pics.fox_r));
	 		foxLabel.setBounds(width-950,height-550,300,200);
	 		
	 		treeLabel1 = new JLabel(new ImageIcon(local.Pics.tree));
	 		treeLabel1.setBounds(width-1000,height-700,200,200);
	 		treeLabel2 = new JLabel(new ImageIcon(local.Pics.tree));
	 		treeLabel2.setBounds(width-900,height-780,200,200);
	 		treeLabel3 = new JLabel(new ImageIcon(local.Pics.tree));
	 		treeLabel3.setBounds(width-800,height-760,200,200);
	 		
	 		backLabel = new JLabel(new ImageIcon(local.Pics.back));
	 		backLabel.setBounds(0,0,1024,768);
	 		
	 		bunnyLabel = new JLabel(new ImageIcon(local.Pics.bunny_r));
	 		bunnyLabel.setBounds(width-500, height-500, 100, 300);
	 		
	 		shopLabel = new JLabel(new ImageIcon(local.Pics.mole));
	 		shopLabel.setBounds(width-900, height-300, 200, 200);
	 		
	 		hedgeLabel = new JLabel(new ImageIcon(local.Pics.hedgehog));
	 		hedgeLabel.setBounds(width-500, height-300, 100, 300);
	 		
	 		messageLabel = new JLabel();
	 		messageLabel.setBounds((width/2)-300,(height/2)-300,600,600);
	 		messageLabel.setOpaque(true);
	 		messageLabel.setBackground(lightGreen);
	 		
	 		messageLabelIn = new JLabel();
	 		messageLabelIn.setBounds((width/2)-250,(height/2)-250,500,500);
	 		messageLabelIn.setOpaque(true);
	 		messageLabelIn.setBackground(Color.white);
	 		
	 	}
	
	 }
}
