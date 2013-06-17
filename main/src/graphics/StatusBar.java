package graphics;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.IOException;


//this class creates Frame and calls playerLeft to create left panel
public class StatusBar extends JFrame 
{
	JLayeredPane layeredPane;
	Image icon1; 
	StatusBar sbar;
	PlayerLeft left; PlayerRight right; 
						
	
	public StatusBar()
	{
		super("Dungeon Crawler");								//super constructor call with parameter of Windows Title
		
		local.Fs.init();												//check OS 
		setSize(1024,768);										//set Window Size
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
								
		layeredPane = getLayeredPane();							//create a layered Pane Container
		
		left = new PlayerLeft();								//create left panel
		right = new PlayerRight();								//create right panel
		content();;	
	
		setResizable(false);
		setVisible(true);										//visibility of frame
						
		
	}
	
	public void content()
		 {
		//Container left Player
		layeredPane.add(left.labelBack, new Integer(10));		
		layeredPane.add(left.button1, new Integer(20));			//add all left elements to Container
		layeredPane.add(left.labelHP, new Integer(30));			//Integer is Index of Element
		layeredPane.add(left.labelMP, new Integer(40));			//make un-visible by index
		layeredPane.add(left.labelEP, new Integer(50));
		layeredPane.add(left.icon, new  Integer(60));
		layeredPane.add(left.iconDef, new Integer(70));
		layeredPane.add(left.iconArm, new Integer(80));
		layeredPane.add(left.life1, new Integer(90));
		layeredPane.add(left.life2, new Integer(100));
		layeredPane.add(left.life3, new Integer(110));
		layeredPane.add(left.life4, new Integer(110));
		layeredPane.add(left.life5, new Integer(110));
		layeredPane.add(left.text1, new Integer(120));
		layeredPane.add(left.text2, new Integer(130));
		layeredPane.add(left.text3, new Integer(140));
		
		//Container right Player
		layeredPane.add(right.labelBack, new Integer(10));
		layeredPane.add(right.button1, new Integer(20));			
		layeredPane.add(right.labelHP, new Integer(30));			
		layeredPane.add(right.labelMP, new Integer(40));			
		layeredPane.add(right.labelEP, new Integer(50));
		layeredPane.add(right.icon, new  Integer(60));
		layeredPane.add(right.iconDef, new Integer(70));
		layeredPane.add(right.iconArm, new Integer(80));
		layeredPane.add(right.life1, new Integer(90));
		layeredPane.add(right.life2, new Integer(100));
		layeredPane.add(right.life3, new Integer(110));
		layeredPane.add(right.life4, new Integer(110));
		layeredPane.add(right.life5, new Integer(110));
		layeredPane.add(right.text1, new Integer(120));
		layeredPane.add(right.text2, new Integer(130));
		layeredPane.add(right.text3, new Integer(140));
	 }			
	
	//external method to change Points Label
	public void setLeftValue(int point, int index){
		switch (index){
		case 1:{												//changes HP Label to new value
				left.setPoints(point, index);
				left.labelHP.setText("HP: "+(String.valueOf(new Integer (left.getPoints(1)))));
				}break;
		case 2:{												////changes MP Label to new value
				left.setPoints(point, index);
				left.labelMP.setText("MP: "+(String.valueOf(new Integer (left.getPoints(2)))));
				}break;
		case 3:{												////changes EP Label to new value
				left.setPoints(point, index);
				left.labelEP.setText("EP: "+(String.valueOf(new Integer (left.getPoints(3)))));
				}break;
			}
	}
	
	public void setRightValue(int point, int index){
		right.setPoints(point, index);
		switch (index){
		case 1:{												//changes HP Label to new value
				right.labelHP.setText("HP: "+(String.valueOf(new Integer (right.getPoints(1)))));
				}break;
		case 2:{												////changes MP Label to new value
				right.labelMP.setText("MP: "+(String.valueOf(new Integer (right.getPoints(2)))));
				}break;
		case 3:{												////changes EP Label to new value
				right.labelEP.setText("EP: "+(String.valueOf(new Integer (right.getPoints(3)))));
				}break;
			}
	}
	
	public void setLeftLife(int life){
		resetLeftLife();
		switch (life){ 		//paints number of lives 
			case 5: {layeredPane.add(left.life5, new Integer(90));}
			case 4: {layeredPane.add(left.life4, new Integer(90));}
			case 3: {layeredPane.add(left.life3, new Integer(90));}
			case 2: {layeredPane.add(left.life2, new Integer(90));}
			case 1: {layeredPane.add(left.life1, new Integer(90));}
			case 0: break;
			default: break;
		}
		
	}
	
	public void setRightLife(int life){
		resetRightLife();
		switch (life){ 		//paints number of lives 
			case 5: {layeredPane.add(right.life5, new Integer(90));}
			case 4: {layeredPane.add(right.life4, new Integer(90));}
			case 3: {layeredPane.add(right.life3, new Integer(90));}
			case 2: {layeredPane.add(right.life2, new Integer(90));}
			case 1: {layeredPane.add(right.life1, new Integer(90));}
			case 0: break;
			default: break;
		}
		
	}
	
	public void resetLeftLife(){  //resets all life Icons paints black
			layeredPane.setLayer(left.life1, 9);
			layeredPane.setLayer(left.life2, 9);
			layeredPane.setLayer(left.life3, 9);
			layeredPane.setLayer(left.life4, 9);
			layeredPane.setLayer(left.life5, 9);
		}
	public void resetRightLife(){  //resets all life Icons paints black
			layeredPane.setLayer(right.life1, 9);
			layeredPane.setLayer(right.life2, 9);
			layeredPane.setLayer(right.life3, 9);
			layeredPane.setLayer(right.life4, 9);
			layeredPane.setLayer(right.life5, 9);
	}

		
	
//	public static void main (String[] args)
//	{				
//		StatusBar start = new StatusBar();
//		start.setLeftValue(888,1);				//testvalues
//		start.setRightValue(666,1);
//		start.setLeftValue(555,2);
//		start.setRightValue(555,2);
//		start.setLeftLife(1);
//		start.setLeftLife(3);
//		start.setRightLife(3);
//		start.setRightLife(5);	
//		
//		
//	}
	
}
	
	
