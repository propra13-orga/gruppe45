package graphics;

import gameobjects.Figure;
import gameobjects.Bunny;
import gameobjects.Hedgehog;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import local.Create;
import local.Pics;
import main.Main;

import movement.Keyboard;
import movement.Keyboard2;



//creates frame and game panel 

public class MasterFrame extends JFrame {
	
	int ep = 0;	
	int[] localFigure;
	BufferStrategy bs;
	GamePanel gamePanel = new GamePanel();				//Game Panel created
	JLayeredPane pane;
	
	public static boolean set = false;

	int width = Main.board_width;
	int height = Main.board_height;
	
	
	//configuration of main Frame
	public MasterFrame()
	{
		
		pane = getLayeredPane();
		
		setTitle("Lucky Bunny");						//create MasterFrame Window
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(width, height);							//Window Size
		setResizable(false);							//Window frame not resizable
		this.setUndecorated(true);
		setVisible(true);	
		createBufferStrategy(2);						//BufferedStrategy active rendering
		bs = getBufferStrategy();						//setup buffer
			
		pane.add(gamePanel, new Integer(100));

		gamePanel.setIgnoreRepaint(true);				//no automatic repaint, OS will decide
		Keyboard keyboard = new Keyboard();				//create KeyListener
		addKeyListener(keyboard);						//connect KeyListener to Frame

	}


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
			Bunny player1 = Create.hero1;						//local variable Player1
			Hedgehog player2 = Create.hero2;						//local variable Player2
			
			Font heading = new Font("Arial",Font.BOLD,18);				//Font for heading
			Font regular = new Font("Arial",Font.PLAIN,12);				//Font for regular text
			
			int posX = width-1010;
			
			while(true)
			{
				int lives1 = player1.lives;
				int lives2 = player2.lives;								//!!!!!!!!!!
				try														//try block to avoid deadlock
				{
					Graphics2D g = (Graphics2D)bs.getDrawGraphics();	//instance of new graphics object
								
					for(int i=0; i<Main.obj_list.size();i++)			//every object in arraylist obj_list
					{
						if((localFigure = Main.obj_list.get(i).clone()) != null) //validation no empty field in arraylist painted
						{	//draw board and all objects
							g.drawImage(Create.gameobjects[localFigure[0]].getPic(localFigure[5]),localFigure[2],localFigure[3],this);//draws each element from list
							if (i==2){
								g.drawString("HP: "+player1.getHp(),localFigure[2],localFigure[3]-25);
								g.drawString("EP: "+player1.ep,localFigure[2],localFigure[3]-15);
							}
							if (i==3 && Main.Nr_of_Players == 2){
								g.drawString("HP: "+player2.getHp(),localFigure[2],localFigure[3]-25);
								g.drawString("EP: "+player2.ep,localFigure[2],localFigure[3]-15);
							}
							if ((localFigure[0] == 6)){//draw level and hp of enemy
								g.drawString("HP: "+localFigure[4],localFigure[2],localFigure[3]-25);
								g.drawString("Level: "+Main.level,localFigure[2],localFigure[3]-15);
							}
							if ((localFigure[0] == 9)&&(set==false)){//draw mark for npc
								g.drawImage(Pics.attention,localFigure[2]-30,localFigure[3],this);
								
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
    				g.drawImage(Pics.bug, width-801, height-30,this);			//bugs
    				g.drawString(""+player1.bugs, width-760, height-18);	//bugs counter
    			
    				//Status Values
					g.drawString(""+player1.hp , width-760 , height-58);
    				g.drawString(""+player1.mp , width-760, height-46);
    				g.drawString(""+ep , width-760, height-34);
					
					//Icon Player
					g.setColor(new Color(0, 0, 0));
					g.drawImage(Pics.icon1,width-1010,height-70,this);
					g.drawString(Main.player1_name, width-1010, height-6);
					
					//Weappon Icon
					g.fillRect(width-935, height-70, 50, 50);//Weapen Icon Ofensive
					//checks if Create.Hero1.defense or Create.Hero1.spell is set to identify available weapons
					if (Create.hero1.spell==true)//draw fireball  
					{
						g.drawImage(Pics.fireball,width-935,height-70,this);//weapon
					}
					else
					{
						if (Create.hero1.attack == true )//draw blob
						{
							g.drawImage(Pics.blob,width-935,height-70,this);//weapon	
						}
						else
						{
							g.drawImage(Pics.peace,width-935,height-70,this);//weapon	
						}
					}
					g.drawString("Weapon", width-935, height-6);
					
					//Armor Icon
					g.fillRect(width-860, height-70, 50, 50);//Armor Icon Defensive
					if(Create.hero1.defense == true){
						g.drawImage(Pics.flower, width-860, height-70,this);
						}
					g.drawString("Armor", width-860, height-6);
					
					//upper left side life status
					g.setFont(heading);
					g.drawString("Player 1 ", width-1010 , height-726);
					g.setFont(regular);
					g.drawString("Leben: ", width-1010 , height-709);
					
					//Level Information
					g.drawString("(Map "+Main.room+"/3)", width-1010, height-675);
					g.drawString("Level "+Main.level, width-1010, height-660);
					g.drawString("Player Level "+Create.hero1.level, width-1010, height-645);	//!!!!!!!!!!
					
					switch(lives1){//paints amount of lives
						case 5:g.drawImage(Pics.life,posX+68,height-705,this);
						case 4:g.drawImage(Pics.life,posX+51,height-705,this);
						case 3:g.drawImage(Pics.life,posX+34,height-705,this);
						case 2:g.drawImage(Pics.life,posX+17,height-705,this);
						case 1:g.drawImage(Pics.life,posX,height-705,this);
						case 0: break;
					}
					
				//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
					if(main.Main.Nr_of_Players==2){							//!!!!!!Für Test muss die Abfrage auf 1 gesetzt werden!!!!
					//backround Image
					g.setColor(new Color(47, 118, 19));						//Hex: 2f 76 13			
					g.fillRect(width-300,height-80,300,80);		
					g.setColor(new Color(0, 0, 0));
					
					//String Labels
					g.drawString("HP: ", width-284 , height-58);
    				g.drawString("MP: ", width-284, height-46);
    				g.drawString("EP: ", width-284, height-34);
    				g.drawImage(Pics.bug, width-283, height-30,this);			//bugs
    				g.drawString(""+player2.bugs, width-244, height-18);	//bugs counter
    			
    				//Status Values
					g.drawString(""+player2.hp , width-244 , height-58);
    				g.drawString(""+player2.mp , width-244, height-46);
    				g.drawString(""+ep , width-244, height-34);
					
    				//Icon Player
					g.setColor(new Color(0, 0, 0));
					g.drawImage(Pics.hedgehog_r,width-64,height-70,this);
					g.drawString(Main.player2_name, width-64, height-6);
					
					//Weappon Icon
					g.fillRect(width-139, height-70, 50, 50);//Weapen Icon Ofensive
					//checks if Create.Hero1.defense or Create.Hero1.spell is set to identify available weapons
					if (Create.hero2.spell==true){//draw fireball  
						g.drawImage(Pics.fireball,width-139,height-70,this);} //weapon
						else{
							if (Create.hero2.attack == true ){//draw blob
								g.drawImage(Pics.blob,width-139,height-70,this);}//weapon	
							
								else{
									g.drawImage(Pics.peace,width-139,height-70,this);}//weapon	
							}
					g.drawString("Weapon", width-139, height-6);
					
					//Armor Icon
					g.fillRect(width-214, height-70, 50, 50);//Armor Icon Defensive
					if(Create.hero2.defense == true){
						g.drawImage(Pics.flower, width-214, height-70,this);
						}
					g.drawString("Armor", width-214, height-6);
					
					//upper left side life status
					g.setFont(heading);
					g.drawString("Player 2 ", width-94 , height-726);
					g.setFont(regular);
					g.drawString("Leben: ", width-94 , height-709);
					
					//Level Information
					g.drawString("(Map "+Main.room+"/3)", width-94, height-675);
					g.drawString("Level "+Main.level, width-94, height-660);
					g.drawString("Player Level "+Create.hero2.level, width-94, height-645);
					
					switch(lives2){//paints amount of lives
						case 5:g.drawImage(Pics.life,width-26,height-705,this);
						case 4:g.drawImage(Pics.life,width-43,height-705,this);
						case 3:g.drawImage(Pics.life,width-60,height-705,this);
						case 2:g.drawImage(Pics.life,width-77,height-705,this);
						case 1:g.drawImage(Pics.life,width-94,height-705,this);
						case 0: break;
					}
					}
				//!!!!!!!!!!!!!!!!!!!!!!!!!!!
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
