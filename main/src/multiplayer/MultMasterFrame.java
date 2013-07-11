package multiplayer;



import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import local.Pics;
import main.Main;
import movement.Keyboard;

public class MultMasterFrame extends JFrame implements Runnable
{

	BufferStrategy bs;
	GamePanel gamePanel = new GamePanel();				//Game Panel created
	JLayeredPane pane;
	Image map;
	
	public static boolean set = false;

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
								
					
				
					//backround Image

					g.drawImage(map, 0, 0,this);
					g.drawImage(local.Pics.bunny_r, 50, height/2,this);
					g.drawImage(local.Pics.hedgehog_l, width-100, height/2, this);
					
					
					Toolkit.getDefaultToolkit().sync();
					
				
					
				
					
					
			
				}
				
				catch (Exception e)//error notification
				{
					System.out.println("Fehler in der drawStuff Methode");
				}
		
				bs.show();												//draw image from buffer on screen
				try{
					Thread.sleep(1000);}
					catch (Exception s){ System.out.println("Thread could not sleep");}
				}
			}
						
		}
	
	
	
	
	
}
