package graphics;

import gameobjects.Figure;
import gameobjects.Hero;
import java.awt.Color;
import java.awt.Graphics2D;

import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.io.IOException;
import java.awt.*;
import javax.swing.*;
import javax.imageio.ImageIO;

import main.Main;

import movement.Keyboard;



//creates frame and game panel 

public class MasterFrame extends JFrame {
	
	 int ep = 0;	
	 Figure localFigure;
	 Hero localHero;
	 BufferStrategy bs;
	 GamePanel gamePanel = new GamePanel();				//Game Panel created
	 JLayeredPane pane;
         
	 int width = Main.board_width;
	 int height = Main.board_height;
	 
	 
	 Image icon1, icon2, life, weapon, bug;

	 
	//configuration of main Frame
	public MasterFrame()
	{
		
		pane = getLayeredPane();
		
		loadImage();
		setTitle("Lucky Bunny");						//create MasterFrame Window
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(width, height);							//Window Size
		setResizable(false);							//Window frame not resizable
		setVisible(true);	
		createBufferStrategy(2);						//BufferedStrategy active rendering
		bs = getBufferStrategy();						//setup buffer
	
		
		pane.add(gamePanel, new Integer(100));

		//getContentPane().add(gamePanel);				//Container for Frame Objects
	
		gamePanel.setIgnoreRepaint(true);				//no automatic repaint, OS will decide
		Keyboard keyboard = new Keyboard();				//create KeyListener
		addKeyListener(keyboard);						//connect KeyListener to Frame



	}
// loads Icons for Statusbar	
public void loadImage(){
		
		try
		{
			icon1 = ImageIO.read(new File(local.Fs.img_pfad+"icon_bunny.png"));
			weapon = ImageIO.read(new File(local.Fs.img_pfad+"icon_fireball.png"));
			icon2 = ImageIO.read(new File(local.Fs.img_pfad+"bunny_l.png"));
			life = ImageIO.read(new File (local.Fs.img_pfad+"heart.png"));
			bug = ImageIO.read(new File (local.Fs.img_pfad+"bug.png"));}
			
		catch(IOException e){	//TODO Auto-generated block		}
			System.out.println("Bild aus MasterFrame.loadImage() kann nicht eingelesen werden");
		 }}

	//Method to start Window
	public void startNow()
	{
		gamePanel.drawStuff();
	}
	
	
	//new Panel with Game
	public class GamePanel extends JPanel
	{
		public void drawStuff()
		{
			Figure player1 = Main.obj_list.get(2);						//local variable Player1
			Figure player2 = Main.obj_list.get(3);						//local variable Player2
			
			Font heading = new Font("Arial",Font.BOLD,18);				//Font for heading
			Font regular = new Font("Arial",Font.PLAIN,12);				//Font for regular text
			
			int posX = width-960;
			
			while(true)
			{
				int lives = player1.lives;
				try														//try block to avoid deadlock
				{
					Graphics2D g = (Graphics2D)bs.getDrawGraphics();	//instance of new graphics object
									
					for(int i=0; i<Main.obj_list.size();i++)			//every object in arraylist obj_list
					{
					   if((localFigure = Main.obj_list.get(i)) != null) //validation no empty field in arraylist painted
		    			{	//draw board and all objects
		    				g.drawImage(localFigure.image,localFigure.pos_x,localFigure.pos_y,this);//draws each element from list
		    				if (i==2){
		    					g.drawString("HP: "+player1.hp,player1.pos_x,player1.pos_y-25);
		    					g.drawString("EP: "+player1.ep,player1.pos_x,player1.pos_y-15);
		    				}
		    				if ((localFigure.type == 3)){//draw level and hp of enemy
		    					g.drawString("HP: "+localFigure.hp,localFigure.pos_x,localFigure.pos_y-25);
		    					g.drawString("Level: "+localFigure.level,localFigure.pos_x,localFigure.pos_y-15);
		    				}
		    			}
		    			Toolkit.getDefaultToolkit().sync();					//checks from OS if repaint is neccessary 
		    			
                     }//end of array list
					
					//backround Image
					g.setColor(new Color(47, 118, 19));						//Hex: 2f 76 13			
					g.fillRect(width-1024,height-80,300,80);		
					g.setColor(new Color(0, 0, 0));
					
					//String Labels
					g.drawString("HP: ", width-800 , height-58);
    				g.drawString("MP: ", width-800, height-46);
    				g.drawString("EP: ", width-800, height-34);
    				g.drawImage(bug, width-801, height-30,this);			//bugs
    				g.drawString(""+player1.bugs, width-760, height-18);	//bugs counter
    			
    				//Status Values
					g.drawString(""+player1.hp , width-760 , height-58);
    				g.drawString(""+player1.mp , width-760, height-46);
    				g.drawString(""+ep , width-760, height-34);
					
    				//Icon Player
					g.setColor(new Color(0, 0, 0));
					g.drawImage(icon1,width-1010,height-70,this);
					g.drawString(player1.name, width-1010, height-6);
					
					//Weappon Icon
					g.fillRect(width-935, height-70, 50, 50);//Weapen Icon Ofensive
					g.drawImage(weapon,width-935,height-70,this);
					g.drawString("Weapon", width-935, height-6);
					
					//Armor Icon
					g.fillRect(width-860, height-70, 50, 50);//Armor Icon Defensive
					g.drawString("Armor", width-860, height-6);
					
					//upper left side life status
					g.setFont(heading);
					g.drawString("Player 1 ", width-1010 , height-726);
					g.setFont(regular);
					g.drawString("Leben: ", width-1010 , height-709);
					
					//Level Information
					g.drawString("(Map "+Main.room+"/3)", width-1010, height-694);
					g.drawString("Level "+Main.level, width-1010, height-679);
					
					switch(lives){//paints amount of lives
						case 5:g.drawImage(life,posX+68,height-721,this);
						case 4:g.drawImage(life,posX+51,height-721,this);
						case 3:g.drawImage(life,posX+34,height-721,this);
						case 2:g.drawImage(life,posX+17,height-721,this);
						case 1:g.drawImage(life,posX,height-721,this);
						case 0: break;
					}
				}
	
				catch (Exception e)//error notification
				{
					System.out.println("Fehler in der drawStuff Methode");
				}
		
				bs.show();												//draw image from buffer on screen
			}
						
		}
	}
	
	
	
	
}
