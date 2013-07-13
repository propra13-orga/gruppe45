package multiplayer;



import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

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
					g.drawString("HP: ", width-800 , height-58);
					
					Toolkit.getDefaultToolkit().sync();
					
					
				
					
				
					
					
			
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
