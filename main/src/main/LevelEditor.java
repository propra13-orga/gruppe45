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
	JLabel debug_lbl;
	JLabel bg, frm_lvl, frm_room, frm_selected_lvl, frm_selected_room, frm_selection_lvl, frm_selection_room ;
	JLabel [] figure = new JLabel[50];
	JLabel [] frm_lvl_list = new JLabel[3];
	JLabel [] frm_room_list = new JLabel[3];
	JButton btn_lvl_up, btn_room_up, btn_lvl_down, btn_room_down, btn_room_del, btn_lvl_del, btn_room_show;
	JButton [] btn_gameobjects = new JButton[20];
	int [] gameobjects_max = new int[20];
	JButton [] btn_menu = new JButton[20];
	String [] menu_text = {"Speichern", "Löschen", "Abbrechen", "Zum Hauptmenü"};
	ImageIcon img;
	int img_index=0;
	boolean[][] rooms = new boolean[20][20];
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
	int selected_lvl=2, selected_room=2, act_lvl=2, act_room=2, max_lvl, max_room;
	boolean debug=true;
	
	int max_x_blocks = Main.board_width/local.Create.block_groesse;
	int max_y_blocks = Main.board_height/local.Create.block_groesse;
	
	int blockgroesse_x = ed_x_size/max_x_blocks;
	int blockgroesse_y = ed_y_size/max_y_blocks;
	
	
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
 		bg.setBounds(x, 150, ed_x_size, ed_y_size);
 		bg.setBorder(LineBorder.createBlackLineBorder());
 		bg.setBackground(Color.black);
 		bg.setForeground(Color.black);
 		
 		
 		y = 10;
 		w=20;
 		h=20;
 		x=80;
 		
 		// Scroll Buttons
 		btn_lvl_up= new JButton("up");
 		btn_room_up= new JButton("up");
 		btn_lvl_up.setBounds(x, y, w, h);
 		btn_align_center(btn_lvl_up);
 		btn_room_up.setBounds(x+170, y, w, h);
 		btn_align_center(btn_room_up);
 		
 		y+=100;
 		btn_lvl_down= new JButton("V");
 		btn_room_down= new JButton("V");
 		btn_lvl_down.setBounds(x, y, w, h);
 		btn_align_center(btn_lvl_down);
 		btn_room_down.setBounds(x+170, y, w, h);
 		btn_align_center(btn_room_down);
 		
 		// Delete Buttons
 		x=25;
 		y=65;
 		w=20;
 		h=25;
 		btn_lvl_del= new JButton("X");
 		btn_room_del= new JButton("X");
 		btn_lvl_del.setBounds(x, y, w, h);
 		btn_align_center(btn_lvl_del);
 		btn_room_del.setBounds(x+170, y, w, h);
 		btn_align_center(btn_room_del);
 		
 		// create Level button
 		x=120;
 		y=65;
 		w=20;
 		h=25;
 		// show Level in Editor button
 		btn_room_show= new JButton(">");
 		btn_room_show.setBounds(x+170, y, w, h);
 		btn_align_center(btn_room_show);
 		
 		
 		y=30;
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

		
		// Level Auswahl
		x=30;
		y=20;
		w=80;
		h=15;
		frm_lvl = new JLabel("");
		frm_lvl.setBounds(x, y, 100, 100);
		frm_lvl.setBorder(LineBorder.createBlackLineBorder());
		frm_lvl.setBackground(Color.black);
		frm_lvl.setForeground(Color.black);
		frm_lvl.setVerticalTextPosition(SwingConstants.TOP);
		frm_lvl.setHorizontalTextPosition(SwingConstants.LEFT);
		frm_lvl.setVerticalAlignment(SwingConstants.TOP);
		frm_lvl.setText("  Level");
		x+=20;
		String txt;
		for (int i=1; i<4; i++) {
			y+=25;
			txt = "neu";
			if (i%2==0) {
				txt = "Level " + String.valueOf(i/2);	
			}
			System.out.println(txt);
			frm_lvl_list[i-1] = new JLabel(txt);
			frm_lvl_list[i-1].setBounds(x, y, w, h);

		}
		frm_selection_lvl = new JLabel("");
		frm_selection_lvl.setBounds(x-10, y-30, w, h+10);
		frm_selection_lvl.setBorder(LineBorder.createBlackLineBorder());
		
		// Raum Auswahl
 		x+=150;
 		y=20;
		frm_room = new JLabel("");
		frm_room.setBounds(x, y, 100, 100);
		frm_room.setBorder(LineBorder.createBlackLineBorder());
		frm_room.setBackground(Color.black);
		frm_room.setForeground(Color.black);
		frm_room.setVerticalTextPosition(SwingConstants.TOP);
		frm_room.setHorizontalTextPosition(SwingConstants.LEFT);
		frm_room.setVerticalAlignment(SwingConstants.TOP);
		frm_room.setText("  Raum");
		x+=20;
		for (int i=1; i<4; i++) {
			y+=25;
			txt = "neu";
			if (i%2==0) {
				txt = "Raum " + String.valueOf(i/2);	
			}
			frm_room_list[i-1] = new JLabel(txt);
			frm_room_list[i-1].setBounds(x, y, w-10, h);
		}
		frm_selection_room = new JLabel("");
		frm_selection_room.setBounds(x-10, y-30, w, h+10);
		frm_selection_room.setBorder(LineBorder.createBlackLineBorder());
		
		if (debug) {
			debug_lbl = new JLabel("");
			debug_lbl.setBounds(400, 120, 200, 30);
			debug_lbl.setBorder(LineBorder.createBlackLineBorder());
		}
		
		lpane =  getLayeredPane();
		if  (debug) {
			lpane.add(debug_lbl);		
		}
		lpane.add(btn_lvl_up);
		lpane.add(btn_room_up);
		lpane.add(btn_lvl_down);
		lpane.add(btn_room_down);
		lpane.add(btn_room_del);
		lpane.add(btn_lvl_del);
		lpane.add(btn_room_show);
		lpane.add(bg);
		lpane.add(frm_room);
		lpane.add(frm_lvl);
		for (JLabel lvl_list: frm_lvl_list){
			lpane.add(lvl_list);
		}
		for (JLabel room_list: frm_room_list){
			lpane.add(room_list);
		}		
		
		for (int i=0; i<z;i++) {
			lpane.add(btn_gameobjects[i],10);
		}
		lpane.add(frm_selection_lvl);
		lpane.add(frm_selection_room);
		this.setVisible(true);
		
		// debug Ausgabe
		//
		debug_lbl.setText(String.valueOf(selected_lvl));
		
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
		
		// level up
		btn_lvl_up.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	    		selected_lvl--;
	    		if (selected_lvl<1) { selected_lvl=1;}
	    		write_label_txt("Level", frm_lvl_list, selected_lvl);
	    		if(debug) {
	    			debug_lbl.setText(String.valueOf(selected_lvl));
	    		}
	    	}
	    });
		
		// level down
		btn_lvl_down.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	    		selected_lvl++;
	    		if (selected_lvl>max_lvl*2+1) { selected_lvl=max_lvl*2+1;}
	    		write_label_txt("Level", frm_lvl_list, selected_lvl);
	    		if(debug) {
	    			debug_lbl.setText(String.valueOf(selected_lvl));
	    		}
	    	}
	    });
		
		// room up
		btn_room_up.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	    		selected_room--;
	    		if (selected_room<1) { selected_room=1;}
	    		write_label_txt("Raum ", frm_room_list, selected_room);
	    		if(debug) {
	    			//debug_lbl.setText(String.valueOf(selected_room));
	    		}
	    	}
	    });
		
		// room down
		btn_room_down.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	    		selected_room++;
	    		if (selected_room>max_room*2+1) { selected_room=max_room*2+1;}
	    		write_label_txt("Raum ", frm_room_list, selected_room);
	    		if(debug) {
	    			debug_lbl.setText(String.valueOf(selected_room));
	    		}
	    	}
	    });
		
		// show room
	}
	
	private void write_label_txt(String s, JLabel [] label_list, int act_selection){
		int z=0;
		int max=max_room;
		for (int i=act_selection-1; i<act_selection+2; i++) {
			String txt;
			txt = "neu";
			if (i%2==0) {
				txt = s + " " + String.valueOf(i/2);	
			}
			if (s=="Level") {
				 max=max_lvl;
			}
			if (i==0 || i>max*2+1) {txt="";}
			label_list[z].setText(txt);
			z++;
		}
		if (s=="Level") {
			if (selected_lvl%2==0) {
				show_room_selection(true);
				btn_lvl_del.setVisible(true);
				if (selected_room%2==0) {
					btn_room_del.setVisible(true);
					btn_room_show.setVisible(true);
				} else {
					btn_room_del.setVisible(false);
					btn_room_show.setVisible(false);
				}
				set_max_room(selected_lvl);
				if (selected_room>max_room*2+1) {selected_room=max_room*2+1;}
				write_label_txt("Raum", frm_room_list, selected_room);
			} else {
				show_room_selection(false);
				btn_lvl_del.setVisible(false);
				btn_room_del.setVisible(false);
				btn_room_show.setVisible(false);
			}
		} else {
			if (selected_room%2==0) {
				btn_room_del.setVisible(true);
				btn_room_show.setVisible(true);
			} else {
				btn_room_del.setVisible(false);
				btn_room_show.setVisible(false);
			}
		}
	}
	
	private void show_room_selection(boolean hide){
		frm_room.setVisible(hide);
		frm_selection_room.setVisible(hide);
		for(JLabel act_label: frm_room_list) {
			act_label.setVisible(hide);
		}
		btn_room_up.setVisible(hide);
		btn_room_down.setVisible(hide);
	}
	
	private void set_max_room(int selected_lvl) {
		int i=1;
		act_lvl= (selected_lvl+1)/2;
		while (rooms[act_lvl][i]) {
			max_room=i;
			i++;
		}
		if (debug){
			debug_lbl.setText(String.valueOf(max_room));
		}
	}
	
	private void btn_align_center(JButton jb) {
		jb.setBorder(LineBorder.createBlackLineBorder());
		jb.setVerticalTextPosition(SwingConstants.CENTER);
		jb.setHorizontalTextPosition(SwingConstants.CENTER);
		jb.setHorizontalAlignment(SwingConstants.CENTER);
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
		max_lvl=0;
		max_room=0;
		// Array = false
		for (boolean[] w:rooms){
			for (boolean z:w) {
				z=false;
			}
		}
		for (String room_file: room) {
			String[] t,tt;
			int l;
			int r;
			t = room_file.split("_");
			l = Integer.parseInt(t[0]);
			tt = t[1].split(".txt");
			r = Integer.parseInt(tt[0]);
			rooms[l][r] = true;
			if (l==1 && r>max_room) {max_room=r;}
			if (l==1 && r>max_lvl) {max_lvl=r;}
		}
	}
}