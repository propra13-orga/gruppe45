package main;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JFrame;
import main.Main;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.awt.*;

import local.Fs;

import javax.swing.JLayeredPane;
//import java.io.IOException;

public class Gui extends JFrame implements ActionListener{
	
	private int height = Main.board_height;
	private int width = Main.board_width;
	
	Image fox, tree, back, bunny, hedgehog, shop;
	
	startPanel start = new startPanel();				//create Gui Elements
	
	JLabel foxLabel, treeLabel1,treeLabel2, treeLabel3, backLabel, titleLabel, bunnyLabel, hedgeLabel, shopLabel;
	JLabel messageLabel, messageLabelIn;
	
	JButton go, exit, person, option;
	JLayeredPane pane;
	
	public Gui(){
		
		setTitle("Lucky Bunny");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(width, height);							//Window Size
		setResizable(false);							//Window frame not resizable
		
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
	     //		pane.setLayer(person , 0);
		   //		pane.setLayer(option , 0);
		   //		pane.setLayer(go , 0);
		   //		pane.setLayer(exit , 0);
	    	}
	    });
		
		//Mitwirkenden button event listener
		person.addActionListener(new ActionListener(){
		   	public void actionPerformed(ActionEvent e){
		   		pane.setLayer(messageLabel , 500);
		   		pane.setLayer(messageLabelIn , 510);
		   //		pane.setLayer(person , 0);
		   //		pane.setLayer(option , 0);
		   //		pane.setLayer(go , 0);
		   //		pane.setLayer(exit , 0);
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
	 		loadIcon();									//loads images
	 		
	 		//start button
	 		go = new JButton("Neues Spiel");
	 		go.setBounds(width-320,height-440,200,30);
	 		go.setForeground(Color.white );
	 		go.setBackground(Color.black);
	 		
	 		//mitwirkenden
	 		person = new JButton("Mitwirkenden");
	 		person.setBounds(width-320,height-360,200,30);
	 		person.setForeground(Color.white );
	 		person.setBackground(Color.black);
	 		
	 		//Optionen
	 		option = new JButton("Optionen");
	 		option.setBounds(width-320,height-400,200,30);
	 		option.setForeground(Color.white );
	 		option.setBackground(Color.black);
	 		
	 		//exit button
	 		exit = new JButton("Beenden");
	 		exit.setBounds(width-320,height-320,200,30);
	 		exit.setForeground(Color.white );
	 		exit.setBackground(Color.black);

	 		
	 		titleLabel = new JLabel("Lucky Bunny");
	 		titleLabel.setBounds(width-500,height-700,200,200);
	 		titleLabel.setFont(new Font(titleLabel.getName(), Font.PLAIN, 30));
	 		
	 		foxLabel = new JLabel(new ImageIcon(fox));
	 		foxLabel.setBounds(width-950,height-550,300,200);
	 		
	 		treeLabel1 = new JLabel(new ImageIcon(tree));
	 		treeLabel1.setBounds(width-1000,height-700,200,200);
	 		treeLabel2 = new JLabel(new ImageIcon(tree));
	 		treeLabel2.setBounds(width-900,height-780,200,200);
	 		treeLabel3 = new JLabel(new ImageIcon(tree));
	 		treeLabel3.setBounds(width-800,height-760,200,200);
	 		
	 		backLabel = new JLabel(new ImageIcon(back));
	 		backLabel.setBounds(0,0,1024,768);
	 		
	 		bunnyLabel = new JLabel(new ImageIcon(bunny));
	 		bunnyLabel.setBounds(width-500, height-500, 100, 300);
	 		
	 		shopLabel = new JLabel(new ImageIcon(shop));
	 		shopLabel.setBounds(width-900, height-300, 200, 200);
	 		
	 		hedgeLabel = new JLabel(new ImageIcon(hedgehog));
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
	 	
	 	public void loadIcon(){
			
			try
				{
					fox = ImageIO.read(new File(local.Fs.img_pfad+"fox_r.png"));		
					tree = ImageIO.read(new File(local.Fs.img_pfad+"wall_1.png"));
					back = ImageIO.read(new File (local.Fs.img_pfad+"bg_1_1.png"));
					bunny = ImageIO.read(new File (local.Fs.img_pfad+"bunny_r.png"));
					hedgehog = ImageIO.read(new File (local.Fs.img_pfad+"hedgehog_l.png"));
					shop = ImageIO.read(new File (local.Fs.img_pfad+"shop_active.png"));
					
				}
			catch(IOException e)
				{	
				System.out.println("Fehler in Gui loadImage");	
				}
			 }
	 }
}
