package graphics;

import gameobjects.Figure;

import java.awt.Color;
import java.awt.Graphics2D;

import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import javax.swing.*;


import main.Game;
import main.Main;
import movement.Keyboard;
import movement.Move;


//creates frame and game panel 

public class MasterFrame extends JFrame {
	 Figure localFigure;
	 BufferStrategy bs;
	 GamePanel gamePanel = new GamePanel();				//Game Panel created
	 JLayeredPane pane;
	 PlayerLeft left = new PlayerLeft();
	 
	//configuration of main Frame
	public MasterFrame()
	{
		setTitle("Lucky Bunny");						//create MasterFrame Window
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1024,800);								//Window Size
		setResizable(false);							//Window frame not resizable
		setVisible(true);	
		createBufferStrategy(2);						//BufferedStrategy active rendering
		bs = getBufferStrategy();						//setup buffer
			
		pane = getLayeredPane();
		pane.add(gamePanel, new Integer(100));

		//getContentPane().add(gamePanel);				//Container for Frame Objects
	
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
			while(true)
			{
				try														//try block to avoid deadlock
				{
					Graphics2D g = (Graphics2D)bs.getDrawGraphics();	//instance of new graphics object
				
					for(int i=0; i<Main.obj_list.size();i++)			//every object in arraylist obj_list
					{
					   if((localFigure = Main.obj_list.get(i)) != null) //validation no empty field in arraylist painted
		    			{
		    				g.drawImage(localFigure.image,localFigure.pos_x,localFigure.pos_y,this);//draws each element from list
		    			}
		    			Toolkit.getDefaultToolkit().sync();				//checks from OS if repaint is neccessary 
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
