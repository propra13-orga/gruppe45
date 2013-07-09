package main;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JTextArea;


public class LevelEditor extends JFrame {
	
	editorPanel start = new editorPanel();				//create Gui Elements
	JLayeredPane lpane;
	JLabel text;
	JTextArea screen;
	JButton save, ok, abort, tree, poisontree, newButton, icon1, icon2, icon3,icon4, icon5, icon6, icon7, icon8, icon9, icon10;
	
	
	public LevelEditor(){
		
		setTitle("Du wolltest ein Fenster");
		setSize(700, 700);							//Window Size
		setResizable(false);						//Window frame not resizable
		setAlwaysOnTop(true);
		this.setLayout(null);
		this.setBackground(Color.green);
		

		lpane =  getLayeredPane();
		lpane.add(text, 			 new Integer(10));
		lpane.add(screen, 10);
		lpane.add(save, 10);
		lpane.add(abort,10);
		lpane.add(ok,10);
		lpane.add(tree,10);
		lpane.add(poisontree, 10);
		lpane.add(newButton, 10);
		lpane.add(icon1,10);
		lpane.add(icon2,10);
		lpane.add(icon3,10);
		lpane.add(icon4,10);
		lpane.add(icon5,10);
		lpane.add(icon6,10);
		lpane.add(icon7,10);
		lpane.add(icon8,10);
		lpane.add(icon9,10);
		lpane.add(icon10,10);
		
		this.setVisible(true);
	}
 
 public class editorPanel extends JPanel
 	{
	 	public editorPanel()
	 	{	
	 		text = new JLabel("Hier ist Dein Fenster ;-) ");
	 		text.setBounds(100, 200, 200, 30);
	 		
	 		//editor display field
	 		screen = new JTextArea();
	 		screen.setBounds(50, 200, 600, 400);
	 		screen.setBackground(Color.white);
	 		screen.setForeground(Color.black);
	 		
	 		
	 		//save level button
	 		save = new JButton("Speichern");
	 		save.setBounds(50, 620, 150, 30);
	 		
	 		//abort button
	 		abort= new JButton("Abbrechen");
	 		abort.setBounds(210, 620, 150, 30);
	 		
	 		//ok button
	 		ok= new JButton("Ok");
	 		ok.setBounds(500, 620, 150, 30);
	 		
	 		//icon10
	 		icon10 = new JButton ("10");
	 		icon10.setBounds(50, 150, 50, 50);
	 		
	 		//icon9
	 		icon9 = new JButton ("9");
	 		icon9.setBounds(100, 150, 50, 50);
	 		
	 		//icon8
	 		icon8 = new JButton ("8");
	 		icon8.setBounds(150, 150, 50, 50);
	 		
	 		//icon7
	 		icon7 = new JButton ("7");
	 		icon7.setBounds(200, 150, 50, 50);
	 		
	 		//icon6
	 		icon6 = new JButton ("6");
	 		icon6.setBounds(250, 150, 50, 50);
	 		
	 		//icon5
	 		icon5 = new JButton ("5");
	 		icon5.setBounds(300, 150, 50, 50);
	 		
	 		//icon4
	 		icon4 = new JButton ("4");
	 		icon4.setBounds(350, 150, 50, 50);
	 		
	 		//icon3
	 		icon3 = new JButton ("3");
	 		icon3.setBounds(400, 150, 50, 50);
	 		
	 		//icon2
	 		icon2 = new JButton ("2");
	 		icon2.setBounds(450, 150, 50, 50);
	 		
	 		//icon1
	 		icon1 = new JButton ("1");
	 		icon1.setBounds(500, 150, 50, 50);
	 		
	 		//treeButton
	 		tree = new JButton (new ImageIcon(local.Pics.tree));
	 		tree.setBounds(550, 150, 50, 50);
	 		
	 		//pioson tree Button
	 		poisontree = new JButton (new ImageIcon(local.Pics.poisonous_tree));
	 		poisontree.setBounds(600, 150, 50, 50);
	 		
	 		
	 		
	 		//new Button
	 		newButton = new JButton ("Neu");
	 		newButton.setBounds(50,50,150,30);
	 		
	 		
	 }
 	}
}
