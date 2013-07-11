package main;
import java.awt.Color;
import java.awt.Image;


import javax.swing.ImageIcon;
import javax.swing.JButton;
//import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JTextArea;

import local.Create;


public class LevelEditor extends JFrame {
	
	//create Gui Elements
	JLayeredPane lpane;
	JLabel text;
	JTextArea screen;
	JButton [] btn_gameobjects = new JButton[20];
	JButton [] btn_menu = new JButton[20];
	String [] menu_text = {"Speichern", "Löschen", "Abbrechen", "Zum Hauptmenü"};
	ImageIcon img;
	
	public LevelEditor(){
		
		setTitle("Du wolltest ein Fenster");
		setSize(700, 700);							//Window Size
		setResizable(false);						//Window frame not resizable
		setAlwaysOnTop(true);
		this.setLayout(null);
		this.setBackground(Color.green);

 		int x =50;
 		int y =620;
 		int w=50;
 		int h=50;
 		int step=50;
 				
 		//editor display field
 		screen = new JTextArea();
 		screen.setBounds(x, 200, 600, 400);
 		screen.setBackground(Color.white);
 		screen.setForeground(Color.black);
 		
 		int img_breite=30;
 		int img_hoehe=0;
 		int z=0;
 		float img_faktor=0;
 		w=50;
 		h=150;
 		System.out.println("Anzahl: "+Create.gameobjects.length);
 		for (int i=0; i<Create.gameobjects.length;i++) {
 			img = new ImageIcon(Create.gameobjects[i].getPic(1));
 			img_faktor= (int) (img.getImage().getWidth(null)/img_breite);
 			img_hoehe = (int) (img.getImage().getHeight(null)/img_faktor);
 			System.out.println(i);
 			System.out.println(img_hoehe);
 			if (img_hoehe>0 && img_hoehe<1000) {
 				img.setImage(img.getImage().getScaledInstance(img_breite,img_hoehe,Image.SCALE_DEFAULT)); 
 				btn_gameobjects[z] = new JButton (img);
 				btn_gameobjects[z].setBounds(x+step*z, 40, w, h);
 				z++;
 			}
 		}

 		
		lpane =  getLayeredPane();
		lpane.add(screen, 10);
		for (int i=0; i<z;i++) {
			lpane.add(btn_gameobjects[i],10);
		}
		this.setVisible(true);
	}
}