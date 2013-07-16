package multiplayer;



import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import local.Pics;
import main.Main;

public class MultMasterFrame extends JFrame implements Runnable
{

	BufferStrategy bs;
	GamePanel gamePanel = new GamePanel();				//Game Panel created
	JLayeredPane pane;
	Image map;

	
	public static boolean set = false;
	
	public static boolean isStarted = false;	//indicates if arraylist is created
	
	public static int posA_X=50; 
	public static int posA_Y= (Main.board_height)/2; 
	public static int posB_X= (Main.board_width)-100; 
	public static int posB_Y= (Main.board_height)/2; 
	
	//public static ArrayList<Weapon> weapons = new ArrayList<Weapon>();
	public static ArrayList<Weapon> weapons = new ArrayList<Weapon>();
	
	public static int countA = 0;	//indicates if weapon arrylist is filled
	public static int countB = 0;	//indicates if weapon arrylist is filled
	
	public static int lives1 = 3;
	public static int lives2 = 3;
	
	public static int hp1 = 100, hp2 = 100;
	public static boolean lost1 = false, lost2 = false;
	
	public static boolean hit = false;
	
	public static boolean poisonSet = false;
	public static int poisonX = 0; 	public static int poisonY = 0;
	
	public static boolean carrot = false;
	public static int carrotX = 0; 	public static int carrotY = 0;
	
	public static boolean game = true;
	


	int width = Main.board_width;
	int height = Main.board_height;
	

	
	//configuration of main Frame
	public MultMasterFrame()
	{
		
		pane = getLayeredPane();
		
		setTitle("Lucky Bunny Battle");						//create MasterFrame Window
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(width, height);							//Window Size
		setResizable(false);							//Window frame not resizable
		this.setUndecorated(false);
		setVisible(true);	
		createBufferStrategy(2);						//BufferedStrategy active rendering
		bs = getBufferStrategy();						//setup buffer
			
		pane.add(gamePanel, new Integer(100));

		gamePanel.setIgnoreRepaint(true);				//no automatic repaint, OS will decide
		
		switch (Multiplayer.map)
		{
		case 1: map = local.Pics.bg_1_1; break;
		case 2: map = local.Pics.abyss; break;
		case 3: map = local.Pics.bg_2_1; break;
		case 4: map = local.Pics.matrix; break;
		default: break;
		}
		
		MultiKeyboard keys = new MultiKeyboard();
		addKeyListener(keys);
		
		for(int i=0; i<10;i++)//init first 10 elements
		{	
			Weapon weapon = new Weapon(-100,-100,0);
			weapons.add(i, weapon);
			weapons.get(i).used=false;
			isStarted = true;
	
		}
		
		MultCol collision = new MultCol();
		Thread colInThread = new Thread(collision);
		colInThread.start();
		
		

	}


	//Method to start Window
	public void run()
	{
		gamePanel.drawStuff();
	}

	
	
	//new Panel with Game
	public class GamePanel extends JPanel
	{
		
		
		public void drawStuff(){
		
			Font heading = new Font("Arial",Font.BOLD,22);				//Font for heading
			Font regular = new Font("Arial",Font.PLAIN,18);				//Font for regular text
			
			Image fireball1 = local.Pics.fireball_r; 
			Image fireball2 = local.Pics.fireball_l;
			 
			int posX = width-1010;
						
			while(true)
			{
				try														//try block to avoid deadlock
				{
					Graphics2D g = (Graphics2D)bs.getDrawGraphics();	//instance of new graphics object
								
					switch (Multiplayer.map)
					{
					case 1: map = local.Pics.bg_1_1; break;
					case 2: map = local.Pics.abyss; break;
					case 3: map = local.Pics.bg_2_1; break;
					case 4: map = local.Pics.matrix; break;
					default: break;
					}
				
					//backround Image
					
					g.drawImage(map, 0, 0,this);
					g.drawImage(local.Pics.bunny_r, posA_X, posA_Y,this);
					g.drawImage(local.Pics.hedgehog_l, posB_X,posB_Y, this);
					
					
	/* here causes the miracle-----------------------------------------------------------------------*/				
				
					for(int i=0; i<10;i++)
					{ if (weapons.get(i)!= null)
						{
							if (weapons.get(i).direction == 1  )
							{
								g.drawImage(fireball1,weapons.get(i).posX,weapons.get(i).posY,this);
							}
							else
							{
								g.drawImage(fireball2,weapons.get(i).posX,weapons.get(i).posY,this);
							}
						}	
					}
					
					if (Multiplayer.isServer==true)
					{
						for (int i=0; i<10;i++)
						{
							if (weapons.get(i).used){
								if ((weapons.get(i)!=null)&&(weapons.get(i).direction ==1 ))
								{
									weapons.get(i).posX += 20;
									if(weapons.get(i).posX > 1100) //clears ball Player 1
									{
										weapons.get(i).used= false;
									}
								}
								if ((weapons.get(i)!=null)&&(weapons.get(i).direction ==2 ))
								{
								//	System.out.println("Feuer vom Player2");
									weapons.get(i).posX -= 30;
									if(weapons.get(i).posX < -10) //clears ball Player 2
									{
										weapons.get(i).used= false;
									}
								}
						}}
						
					}
					
					if (hit)
					{
	
						
						Random rand = new Random();
						switch (rand.nextInt(7)){
						case 1:g.drawImage(Pics.pow,0, 0,this); System.out.println("1"); break;
						case 2:g.drawImage(Pics.peng,0, 0,this);  System.out.println("2");break;
						case 3:g.drawImage(Pics.kaboom,0, 0,this);  System.out.println("3");break;
						case 4:g.drawImage(Pics.crash,0, 0,this); System.out.println("4"); break;
						case 5:g.drawImage(Pics.boom,0, 0,this); System.out.println("5"); break;
						case 6:g.drawImage(Pics.bang,0, 0,this); System.out.println("6");break;
						case 7:g.drawImage(Pics.bam,0, 0,this); System.out.println("7"); break;
						default: break;
						}
						
						hit = false;
					}
					
					if (poisonSet)
					{
						g.drawImage(Pics.poison, poisonX, poisonY, this);
					}
					
					if (carrot)
					{
						g.drawImage(local.Pics.carrot, carrotX, carrotY,this);
					}
					
	

					
	/* end of mircale--------------------------------------------------------------------------------*/					
					
					Toolkit.getDefaultToolkit().sync();
/*Left Player 1-------------------------------------------------------------------------------------*/ 					
					//upper left side life status
					g.setFont(heading);
					g.drawString("Player 1 ", width-1010 , height-726);
					g.setFont(regular);
					g.drawString("Leben: ", width-1010 , height-699);
					g.drawString("Lebenspunkt: "+hp1, width-1010 , height-660);
					
					switch(lives1){//paints amount of lives
					case 5:g.drawImage(Pics.life,posX+68,height-695,this);
					case 4:g.drawImage(Pics.life,posX+51,height-695,this);
					case 3:g.drawImage(Pics.life,posX+34,height-695,this);
					case 2:g.drawImage(Pics.life,posX+17,height-695,this);
					case 1:g.drawImage(Pics.life,posX,height-695,this);
					case 0: break;}
				
/*Right Player 2-------------------------------------------------------------------------------------*/ 						
					//upper right side life status
					g.setFont(heading);
					g.drawString("Player 2 ", width-164 , height-726);
					g.setFont(regular);
					g.drawString("Leben: ", width-164 , height-699);
					g.drawString("Lebenspunkt: "+hp2, width-164 , height-660);
					
					switch(lives2){//paints amount of lives
					case 5:g.drawImage(Pics.life,width-96,height-695,this);
					case 4:g.drawImage(Pics.life,width-113,height-695,this);
					case 3:g.drawImage(Pics.life,width-130,height-695,this);
					case 2:g.drawImage(Pics.life,width-147,height-695,this);
					case 1:g.drawImage(Pics.life,width-164,height-695,this);
					case 0: break;}

				if (lost1)
				{	game = false;
				//	System.out.print("Spieler 1 verloren");
					if (Multiplayer.isServer==false)
					{	//player 2 won
						g.drawImage(local.Pics.won2,80,0,this);
					}else
					{
						//player 1 game over
						g.setColor(new Color(0, 0, 0));	
						g.fillRect(0, 0, 1200, 768);//Weapen Icon Ofensive
						g.drawImage(local.Pics.gameOver,0,0,this);
					}
					
				}
				
				if (lost2)
				{
				 	game = false;
					//System.out.println("Spieler 2 verloren");
					if (Multiplayer.isServer==true)
					{	//player 1 won
						g.drawImage(local.Pics.won1,100,0,this);
						
					}else
					{	//player 2 game over
						g.setColor(new Color(0, 0, 0));	
						g.fillRect(0, 0, 1200, 768);//Weapen Icon Ofensive
						g.drawImage(local.Pics.gameOver,0,0,this);
					}
				}
				
					
				Thread.sleep(40);
					
					
			
				}
				
				catch (Exception e)//error notification
				{
					System.out.println("Fehler in der drawStuff Methode");
				}
		
				bs.show();												//draw image from buffer on screen
				try{
				//	Thread.sleep(1000);
					}
					catch (Exception s){ System.out.println("Thread could not sleep");}
				}
			}
						
		}
	
	
	
	
	
}
