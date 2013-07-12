package main;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.ImageIcon;
import javax.swing.JButton;
//import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import local.Create;
import local.Fs;


public class LevelEditor extends JFrame {
	
	//create Gui Elements
	JLayeredPane lpane;
	JTextArea screen;
	JLabel actual_figure;
	JLabel bg;
	JLabel [] figure = new JLabel[50];
	JButton btn_room_left, btn_room_right;
	JButton [] btn_gameobjects = new JButton[20];
	int [] btn_gameobjects_max = new int[20];
	JButton [] btn_menu = new JButton[20];
	String [] menu_text = {"Speichern", "Löschen", "Abbrechen", "Zum Hauptmenü"};
	ImageIcon img;
	int img_index=0;
	ArrayList<Image> bg_image = new ArrayList<Image>();
	ArrayList<String> room = new ArrayList<String>();
	
	int x =30;
	int y =620;
	int w=50;
	int h=50;
	int step=0;
	int ed_win_x_size=800;
	int ed_win_y_size=700;
	int ed_x_size=ed_win_x_size-(2*x);
	int ed_y_size=ed_win_x_size-300;

	
	int max_x_blocks = Main.board_width/local.Create.block_groesse;
	int max_y_blocks = Main.board_height/local.Create.block_groesse;
				
	public LevelEditor(){
		
		setTitle("Du wolltest ein Fenster");
		setSize(ed_win_x_size, ed_win_y_size);							//Window Size
		setResizable(false);						//Window frame not resizable
		setAlwaysOnTop(true);
		this.setLayout(null);
 			
 		get_bg_images();
 		get_level_rooms();
 		//editor display field
 		
 		bg = new JLabel(get_bg_image());
 		bg.setBounds(x, 110, ed_x_size, ed_y_size);
 		bg.setBorder(LineBorder.createBlackLineBorder());
 		bg.setBackground(Color.black);
 		bg.setForeground(Color.black);
 		
 		
 		y = 375;
 		w=30;
 		h=30;
 		x=10;
 		btn_room_left= new JButton("<");
 		btn_room_right= new JButton(">");
 		btn_room_left.setBounds(x, y, w, h);
 		btn_room_left.setBorder(LineBorder.createBlackLineBorder());
 		btn_room_left.setVerticalTextPosition(SwingConstants.CENTER);
 		btn_room_left.setHorizontalTextPosition(SwingConstants.CENTER);
 		btn_room_left.setHorizontalAlignment(SwingConstants.CENTER);
 		btn_room_right.setBounds(x+745, y, w, h);
 		btn_room_right.setBorder(LineBorder.createBlackLineBorder());
 		btn_room_right.setVerticalTextPosition(SwingConstants.CENTER);
 		btn_room_right.setHorizontalTextPosition(SwingConstants.CENTER);
 		btn_room_right.setHorizontalAlignment(SwingConstants.CENTER);
 		
 		y = 30;
 		x=400;
 		w=50;
 		h=70;
 		step=w+5;
 		int img_breite=w-10;
 		int img_hoehe=0;
 		int z=0;
 		float img_faktor=0;
 		// System.out.println("Anzahl: "+Create.gameobjects.length);
 		for (int i=0; i<Create.gameobjects.length-4;i++) {
 			img = new ImageIcon(Create.gameobjects[i].getPic(1));
 			img.getImage(); // noch notwendig wegen lazy loading beim Hero. Kann später raus.
 			img_faktor= (int) (img.getImage().getWidth(null)/img_breite);
 			img_hoehe = (int) (img.getImage().getHeight(null)/img_faktor);
 			if (img_hoehe>0 && img_hoehe<1000 && (i<1 || i>3)) {
 				img.setImage(img.getImage().getScaledInstance(img_breite,img_hoehe,Image.SCALE_DEFAULT)); 
 				btn_gameobjects[z] = new JButton (img);
 				btn_gameobjects[z].setBounds(x+step*z, y, w, h);
 				z++;
 			}
 		}
 		
		btn_gameobjects[0].setVerticalTextPosition(SwingConstants.BOTTOM);
		btn_gameobjects[0].setHorizontalTextPosition(SwingConstants.CENTER);
		btn_gameobjects[0].setText(String.valueOf(img_index+1));

 		
		lpane =  getLayeredPane();
		lpane.add(btn_room_left, 10);
		lpane.add(btn_room_right, 10);
		lpane.add(bg, 15);
		for (int i=0; i<z;i++) {
			lpane.add(btn_gameobjects[i],10);
		}
		this.setVisible(true);
		
		// button background image
		btn_gameobjects[0].addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	    		img_index++;
	    		if (img_index>=bg_image.size()) {
	    			img_index=0;
	    		}
	    		bg.setIcon(get_bg_image());
	    		btn_gameobjects[0].setText(String.valueOf(img_index+1));
	    		
	    	}
	    });
	}
	
	private ImageIcon get_bg_image(){
		ImageIcon bg_icon;
		bg_icon= new ImageIcon(bg_image.get(img_index).getScaledInstance(ed_x_size,ed_y_size,Image.SCALE_DEFAULT));
		return bg_icon;			
	}
	
	private void get_bg_images(){
		String path = local.Fs.img_pfad+"editor_templates"+local.Fs.os_slash;
		File folder = new File(path);
		for(File file : folder.listFiles())  {
		  if (file.isFile() && file.getName().startsWith("bg_")) {
		    bg_image.add(Toolkit.getDefaultToolkit().getImage(path+file.getName()));
		  } 
		}
	}
	
	private void get_level_rooms () {
		String path = local.Fs.data_pfad;
		File folder = new File(path);
		for(File file : folder.listFiles())  {
		  if (file.isFile() && file.getName().matches("[1-9]_[1-9].txt")) {
		    room.add(file.getName());
		  } 
		}
		Collections.sort(room);
	}
}