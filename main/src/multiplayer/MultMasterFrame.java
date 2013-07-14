package multiplayer;



import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.LinkedList;

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
	
	public static int posA_X=50; 
	public static int posA_Y= (Main.board_height)/2; 
	public static int posB_X= (Main.board_width)-100; 
	public static int posB_Y= (Main.board_height)/2; 
	
	//public static ArrayList<Weapon> weapons = new ArrayList<Weapon>();
	public static LinkedList<Weapon> weapons = new LinkedList<Weapon>();
	
	public static int countA = 0;	//indicates if weapon arrylist is filled
	public static int countB = 0;	//indicates if weapon arrylist is filled
	
	public static int lives1 = 3;
	public static int lives2 = 3;
	
	public static int hp1 = 100;
	public static int hp2 = 100;

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
		
		

	}


	//Method to start Window
	public void run()
	{
		gamePanel.drawStuff();
	}

	
	
	//new Panel with Game
	public class GamePanel extends JPanel
	{
		
		
		public void drawStuff()
		{
			Font heading = new Font("Arial",Font.BOLD,18);				//Font for heading
			Font regular = new Font("Arial",Font.PLAIN,12);				//Font for regular text
			
			Image fireball1 = local.Pics.fireball_l; 
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
					
					for(int i=0; i<weapons.size();i++)
					{
						if (weapons.get(i).direction==1)
						{
							g.drawImage(fireball1, weapons.get(i).posX,weapons.get(i).posY,this);
							weapons.get(i).posX +=5;
						//	if (weapons.get(i).posX >=800 )
						//		{weapons.remove(i);	}//weapon out of range
						}
						if (weapons.get(i).direction==2)
						{
							g.drawImage(fireball2, weapons.get(i).posX,weapons.get(i).posY,this);
							weapons.get(i).posX -=5;
						//	if (weapons.get(i).posX <=10)
						//		{weapons.remove(i);	}
						}
					}
					
					

					
	/* end of mircale--------------------------------------------------------------------------------*/					
					
					Toolkit.getDefaultToolkit().sync();
/*Left Player 1-------------------------------------------------------------------------------------*/ 					
					//upper left side life status
					g.setFont(heading);
					g.drawString("Player 1 ", width-1010 , height-726);
					g.setFont(regular);
					g.drawString("Leben: ", width-1010 , height-709);
					g.drawString("Lebenspunkt: "+hp1, width-1010 , height-670);
					
					switch(lives1){//paints amount of lives
					case 5:g.drawImage(Pics.life,posX+68,height-705,this);
					case 4:g.drawImage(Pics.life,posX+51,height-705,this);
					case 3:g.drawImage(Pics.life,posX+34,height-705,this);
					case 2:g.drawImage(Pics.life,posX+17,height-705,this);
					case 1:g.drawImage(Pics.life,posX,height-705,this);
					case 0: break;}
				
/*Right Player 2-------------------------------------------------------------------------------------*/ 						
					//upper right side life status
					g.setFont(heading);
					g.drawString("Player 2 ", width-114 , height-726);
					g.setFont(regular);
					g.drawString("Leben: ", width-114 , height-709);
					g.drawString("Lebenspunkt: "+hp2, width-114 , height-670);
					
					switch(lives2){//paints amount of lives
					case 5:g.drawImage(Pics.life,width-46,height-705,this);
					case 4:g.drawImage(Pics.life,width-63,height-705,this);
					case 3:g.drawImage(Pics.life,width-80,height-705,this);
					case 2:g.drawImage(Pics.life,width-97,height-705,this);
					case 1:g.drawImage(Pics.life,width-114,height-705,this);
					case 0: break;}
				
					
				
					
					
			
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
