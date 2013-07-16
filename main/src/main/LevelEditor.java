package main;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.BorderFactory;
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
import local.Pics;
import movement.Move;


public class LevelEditor extends JFrame {
	
	private class board_object {
		Image img;
		int max=1;
		int min=1;
		int x=0;
		int y=0;
		int w=0;
		int h=0;
		String chr="";
		boolean border;
		JLabel boj;
		
		board_object(Image img, int max, int min, int x, int y, String chr) {
			this.img = img;
			this.max=max;
			this.min=min;
			this.x = x;
			this.y = y;
			this.chr = chr;
			border=false;
		}

	}

	//create Gui Elements
	JLayeredPane lpane;
	JTextArea screen;
	JLabel actual_figure;
	JLabel debug_lbl;
	JLabel bg, frm_lvl, frm_room, frm_selected_lvl, frm_selected_room, frm_selection_lvl, frm_selection_room ;
	JLabel [] frm_lvl_list = new JLabel[3];
	JLabel [] frm_room_list = new JLabel[3];
	JButton btn_lvl_up, btn_room_up, btn_lvl_down, btn_room_down, btn_room_del, btn_lvl_del, btn_room_show;
	JButton btn_obj_up, btn_obj_down, btn_obj_left, btn_obj_right, btn_room_save, btn_room_reset, btn_exit;
	JButton [] btn_gameobjects = new JButton[20];
	int [] gameobjects_max = new int[20];
	JButton [] btn_menu = new JButton[20];
	String [] menu_text = {"Speichern", "Löschen", "Abbrechen", "Zum Hauptmenü"};
	ImageIcon img;
	int img_index=0;
	boolean[][] rooms = new boolean[20][20];
	ArrayList<Image> bg_image = new ArrayList<Image>();
	ArrayList<String> room = new ArrayList<String>();
	ArrayList<JLabel> figure = new ArrayList<JLabel>();
	ArrayList<String> room_line = new ArrayList<String>();
	board_object act_object;
	
	
	int x =30;
	int y =620;
	int w=50;
	int h=50;
	int step=0;
	int ed_win_x_size=800;
	int ed_win_y_size=700;
	int ed_x_start=50;
	int ed_y_start=150;
	
	int ed_x_size=ed_win_x_size-(2*x);
	int ed_y_size=ed_win_y_size-220;
	int selected_lvl=2, selected_room=2, act_lvl=2, act_room=2, max_lvl, max_room;
	boolean debug=true;
	
	int max_x_blocks = (Main.board_width/local.Create.block_groesse);
	int max_y_blocks = (Main.board_height/local.Create.block_groesse);
	
	int blockgroesse_x = ed_x_size/ max_x_blocks;
	int blockgroesse_y =ed_y_size / max_y_blocks;
	
	float faktor_x= (float)Main.board_width/ed_x_size;
	float faktor_y= (float)Main.board_height/ed_y_size;

	private ArrayList <board_object> editor_objects = new ArrayList<board_object>();
	
	public LevelEditor(){
		
		max_x_blocks -=2;
		max_y_blocks -=2;
		System.out.println(Main.board_width);
		System.out.println(ed_x_size);
		setTitle("Level-Editor");
		setSize(ed_win_x_size, ed_win_y_size);							//Window Size
		setResizable(false);						//Window frame not resizable
		setAlwaysOnTop(true);
		this.setLayout(null);
 			
 		get_bg_images();
 		get_level_rooms();
 		//editor display field
 		
 		bg = new JLabel(get_index_next_bg_image());
 		bg.setBounds(x, 150, ed_x_size, ed_y_size);
 		bg.setBorder(LineBorder.createBlackLineBorder());
 		bg.setBackground(Color.black);
 		bg.setForeground(Color.black);
 		
 		
 		y=10;
 		w=20;
 		h=20;
 		x=80;
 		
 		// Scroll Buttons
 		btn_lvl_up= new JButton("A");
 		btn_room_up= new JButton("A");
 		btn_lvl_up.setBounds(x, y, w, h);
 		btn_lvl_up.setToolTipText("Level-Liste scrollen");
 		btn_align_center(btn_lvl_up);
 		btn_room_up.setBounds(x+170, y, w, h);
 		btn_room_up.setToolTipText("Raum-Liste scrollen");
 		btn_align_center(btn_room_up);
 		
 		y+=100;
 		btn_lvl_down= new JButton("V");
 		btn_room_down= new JButton("V");
 		btn_lvl_down.setBounds(x, y, w, h);
 		btn_lvl_down.setToolTipText("Level-Liste scrollen");
 		btn_align_center(btn_lvl_down);
 		btn_room_down.setBounds(x+170, y, w, h);
 		btn_room_down.setToolTipText("Raum-Liste scrollen");
 		btn_align_center(btn_room_down);
 		
 		// Figur bewegen
 		x=380;
 		y=15;
 		w=20;
 		h=20;
 		btn_obj_up= new JButton("A");
 		btn_obj_down= new JButton("V");
 		btn_obj_left= new JButton("<");
 		btn_obj_right= new JButton(">");
 		btn_obj_up.setBounds(x, y, w, h);
 		btn_obj_up.setToolTipText("Figur nach oben bewegen");
 		btn_align_center(btn_obj_up);
 		btn_obj_down.setBounds(x, y+50, w, h);
 		btn_obj_down.setToolTipText("Figur nach unten bewegen");
 		btn_align_center(btn_obj_down);		
 		btn_obj_left.setBounds(x-25, y+25, w, h);
 		btn_obj_left.setToolTipText("Figur nach links bewegen");
 		btn_align_center(btn_obj_left);	
 		btn_obj_right.setBounds(x+25, y+25, w, h);
 		btn_obj_right.setToolTipText("Figur nach rechts bewegen");
 		btn_align_center(btn_obj_right);			
 		
 		
 		// Delete Buttons
 		x=25;
 		y=65;
 		w=20;
 		h=25;
 		btn_lvl_del= new JButton("X");
 		btn_room_del= new JButton("X");
 		btn_lvl_del.setBounds(x, y, w, h);
 		btn_lvl_del.setToolTipText("Level löschen");
 		btn_align_center(btn_lvl_del);
 		btn_room_del.setBounds(x+170, y, w, h);
 		btn_room_del.setToolTipText("Raum löschen");
 		btn_align_center(btn_room_del);
 		
 		// create Level button
 		x=120;
 		y=65;
 		w=20;
 		h=25;
 		// show Level in Editor button
 		btn_room_show= new JButton(">");
 		btn_room_show.setBounds(x+170, y, w, h);
 		btn_room_show.setToolTipText("Raum im Editor anzeigen");
 		btn_align_center(btn_room_show);
 		
 		// save room button
 		x=30;
 		y=640;
 		w=380;
 		h=20;
 		btn_room_save= new JButton("Speichern (Level 1 / Raum 1)");
 		btn_room_save.setBounds(x, y, w, h);
 		btn_room_save.setToolTipText("Raum speichern");
 		btn_align_center(btn_room_save);
 		
 		// reset room button
 		x=420;
 		w=180;
 		btn_room_reset= new JButton("Raum zurücksetzen");
 		btn_room_reset.setBounds(x, y, w, h);
 		btn_room_reset.setToolTipText("Raum auf ursprünglichen Zustand zurücksetzen");
 		btn_align_center(btn_room_reset);
 		
 		// exit button
 		x=620;
 		w=150;
 		btn_exit= new JButton("Verlassen");
 		btn_exit.setBounds(x, y, w, h);
 		btn_exit.setToolTipText("Editor verlassen");
 		btn_align_center(btn_exit);
 		
 		
 		y=30;
 		x=450;
 		w=50;
 		h=70;
 		step=w+5;
 		int img_breite=w-10;
 		int img_hoehe=0;
 		int z=0;
 		float img_faktor=0;
 		// System.out.println("Anzahl: "+Create.gameobjects.length);
 		for (int i=0; i<Create.gameobjects.length-5;i++) {
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
		lpane.add(btn_lvl_up,20);
		lpane.add(btn_room_up,21);
		lpane.add(btn_lvl_down,22);
		lpane.add(btn_room_down,23);
		lpane.add(btn_room_del,2);
		lpane.add(btn_lvl_del,2);
		lpane.add(btn_room_show,2);
		lpane.add(bg,-200);
		lpane.add(frm_room,15);
		lpane.add(frm_lvl,16);
		for (JLabel lvl_list: frm_lvl_list){
			lpane.add(lvl_list,17);
		}
		for (JLabel room_list: frm_room_list){
			lpane.add(room_list,18);
		}
		
		for (int i=0; i<z;i++) {
			lpane.add(btn_gameobjects[i],10);
		}
		lpane.add(frm_selection_lvl,19);
		lpane.add(frm_selection_room,20);
		lpane.add(btn_obj_up,21);
		lpane.add(btn_obj_down,22);
		lpane.add(btn_obj_left,23);
		lpane.add(btn_obj_right,24);
		lpane.add(btn_room_save,25);
		lpane.add(btn_room_reset,26);
		lpane.add(btn_exit,27);
		
		this.setVisible(true);
		editor_show_room();
		
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
	    		bg.setIcon(get_index_next_bg_image());
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
		btn_room_show.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	    		editor_show_room();
	    	}
	    });
		
		// bewege objekt im Editor um 1 block nach rechts
		btn_obj_right.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	    		if (act_object!=null) {
	    			if (act_object.x<max_x_blocks) {
		    			act_object.x+=1;
			    		act_object.boj.setLocation(ed_x_start+act_object.x*blockgroesse_x, ed_y_start+act_object.y*blockgroesse_y);
	    			}
	    		}
	    	}
	    });
		
		// bewege objekt im Editor um 1 block nach links
		btn_obj_left.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	    		if (act_object!=null) {
	    			if (act_object.x>1) {
		    			act_object.x-=1;
			    		act_object.boj.setLocation(ed_x_start+act_object.x*blockgroesse_x, ed_y_start+act_object.y*blockgroesse_y);
	    			}
	    		}
	    	}
	    });
		
		// bewege objekt im Editor um 1 block nach unten
		btn_obj_down.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	    		if (act_object!=null) {
	    			if (act_object.y<max_y_blocks) {
		    			act_object.y+=1;
			    		act_object.boj.setLocation(ed_x_start+act_object.x*blockgroesse_x, ed_y_start+act_object.y*blockgroesse_y);
	    			}
	    		}
	    	}
	    });
		
		// bewege objekt im Editor um 1 block nach oben
		btn_obj_up.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	    		if (act_object!=null) {
	    			if (act_object.y>1) {
		    			act_object.y-=1;
			    		act_object.boj.setLocation(ed_x_start+act_object.x*blockgroesse_x, ed_y_start+act_object.y*blockgroesse_y);
	    			}
	    		}
	    	}
	    });
		
		
		// Save
		btn_room_save.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	    		String file ="";
	    		room_line = get_room_from_editor();
	    		if (selected_lvl%2==0) { // Level bereits vorhanden
		    		if (selected_room%2==0) { 
		    			if (selected_room==max_room*2+1) { // Raum hinten anhängen
		    				save_as(selected_lvl/2, selected_room/2+1);
		    				selected_room++;
		    			} else { // Raum vorhanden
		    				save_as(selected_lvl/2, selected_room/2);
		    			}
		    		} else { // Raum nicht vorhanden -> alle dahinter liegenden Räume umbennen
		    			for (int i=max_room; i>= (selected_room+1)/2;i--) {
		    				File oldFile = new File(local.Fs.data_pfad+String.valueOf(selected_lvl/2)+"_"+i+".txt"); 
		    				oldFile.renameTo(new File(local.Fs.data_pfad+String.valueOf((selected_lvl/2)+1)+"_"+i+".txt"));
		    				System.out.println("Umbennen: "+ String.valueOf(selected_lvl/2)+"_"+i+".txt" + " in " + String.valueOf((selected_lvl/2))+"_"+String.valueOf(i+1)+".txt");
		    				rooms[selected_lvl/2][i+1]=true;
		    			}
		    			save_as((selected_lvl/2),(selected_room+1)/2);
		    			selected_room++;
		    			System.out.println("Speichern: "+ String.valueOf(selected_lvl/2)+"_"+(selected_room+1)/2+".txt");
	    				
		    		}
	    		} else { // Level noch nicht vorhanden -> alle dahinter liegenden Level umbennen
	    			file = String.valueOf((selected_lvl+1)/2)+"_1.txt";
	    			for (int i=max_lvl; i>= (selected_lvl+1)/2;i-- ) {
	    				int z =1;
	    				while(rooms[i][z]){
		    				File oldFile = new File(local.Fs.data_pfad+String.valueOf(i)+"_"+z+".txt"); 
		    				oldFile.renameTo(new File(local.Fs.data_pfad+String.valueOf(i+1)+"_"+z+".txt"));
		    				System.out.println("Umbennen:  "+ String.valueOf(i)+"_"+z+".txt" + " in " + String.valueOf(i+1)+"_"+z+".txt");
		    				rooms[i+1][z]=true;
		    				z++;		    				
	    				}
	    				save_as((selected_lvl+1)/2,1);
	    				selected_room=1;
	    				System.out.println("Speichern: "+ String.valueOf((selected_lvl+1)/2)+"_1.txt");
	    				
	    			}
	    		}
	    		System.out.println("------------------");
	    		get_level_rooms();
	    		if (selected_room>max_room*2+1) { selected_room=max_room*2+1;}
	    		write_label_txt("Raum ", frm_room_list, selected_room);
	    	}
	    });
		
		// delete level
		
		btn_lvl_del.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	    		
	    		System.out.println("------------------");
	    		get_level_rooms();
	    	}
	    });
		
		// delete room
		
		btn_room_del.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	    		if(max_room!=1) { // 1 Raum löschen 
	    			if (selected_room!=max_room*2) { // wenn nicht letzter -> dahinterligende Räume dekrementieren
			    		for (int i=selected_room/2; i<= max_room;i++) {
		    				File oldFile = new File(local.Fs.data_pfad+String.valueOf(selected_lvl/2)+"_"+String.valueOf(i+1)+".txt"); 
		    				oldFile.renameTo(new File(local.Fs.data_pfad+String.valueOf(selected_lvl/2)+"_"+i+".txt"));
		    				System.out.println("Umbennen: "+ String.valueOf(selected_lvl/2)+"_"+String.valueOf(i+1)+".txt" + " in " + String.valueOf(selected_lvl/2)+"_"+String.valueOf(i)+".txt");
		    				rooms[selected_lvl/2][i+1]=false;
		    			}
	    			} else { // letzter Raum eines Levels (mit >1 Räumen) -> einfach löschen
	    				System.out.println("Löschen: "+ String.valueOf(selected_lvl/2)+"_" + String.valueOf(selected_room/2)+".txt");
	    				File file = new File(local.Fs.data_pfad+String.valueOf(selected_lvl/2)+"_"+String.valueOf(selected_room/2)+".txt");
	    				file.delete();	
	    			}	    			
	    		} else { // einziger  Raum des Levels -> gesamten Level löschen und folgende Level 'nachschieben'
	    			
	    		}
	    		System.out.println("------------------");
	    		get_level_rooms();
	    		selected_room-=1;
	    		if (selected_room<1) { selected_room=1;}
	    		write_label_txt("Raum ", frm_room_list, selected_room);
	    	}
	    });
		
		// verlassen (sollte eigentlich zum Hauptmenue gehen)
		btn_exit.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	    		System.exit(0);
	    	}
	    });
		
	}
	
	// lese und positioniere Raum-Objekte auf dem Editor-Fenster
	private void editor_show_room() {
		int w,h;
		Main.level = selected_lvl/2;
		Main.room = selected_room/2;
		bg.setIcon(get_lvl_bg_image());
		for (JLabel jl: figure) {
			jl.setVisible(false);
			lpane.remove(jl);
		}
		figure.clear();
		get_room_objects();
		for (final board_object bo : editor_objects) {
			w = (int) (bo.img.getWidth(null)/faktor_x);
			h = (int) (bo.img.getHeight(null)/faktor_y);
			ImageIcon bgi= new ImageIcon(bo.img.getScaledInstance(w,h,Image.SCALE_DEFAULT));
			bo.boj = new JLabel(bgi);
			bo.boj.setBounds(ed_x_start+blockgroesse_x*bo.x, ed_y_start+blockgroesse_y*bo.y, w, h);
			lpane.add(bo.boj,5);
			figure.add(bo.boj);
			bo.boj.addMouseListener(new MouseAdapter() {
				  public void mousePressed(MouseEvent e) {
					  for(JLabel jl:figure){
						  jl.setBorder(BorderFactory.createEmptyBorder());
					  }
					bo.boj.setBorder(LineBorder.createBlackLineBorder());
					act_object=bo;					
				  }
				});
			act_object=null;
		}
	}
	
	
	// schreibe raum-Objekte aus Datei in editor_objects
	private void get_room_objects() {
		String dat_name;
		int zeile=0;
		int zeilenlaenge =0;
		boolean feld=false;
		editor_objects.clear();
		String zeileninhalt ="";
		dat_name = Main.level+"_"+Main.room;
		FileReader fr;
		try {
			fr = new FileReader(Fs.data_pfad+dat_name+".txt");
			BufferedReader br = new BufferedReader(fr);
			do { // reading levelfile line by line
				zeileninhalt = br.readLine(); 
				if (zeileninhalt != null) { // empty line
			        zeilenlaenge=zeileninhalt.length();
			        if (zeileninhalt.charAt(0)=='#') { // reading chars of line
			          feld=true;
			        }
			        if (feld) { // depending on char create objects on board
			          for(int spalte=0; spalte<zeilenlaenge;spalte++){
			            switch(zeileninhalt.charAt(spalte)) { 				
			          case 'w':
			        	  editor_objects.add(new board_object(Pics.tree,99,0,spalte, zeile, "w"));
		                break;
		              case 'k':
		            	  editor_objects.add(new board_object(Pics.fox_l,99,0,spalte, zeile, "k"));
		                break;
		              case 't':
		            	  editor_objects.add(new board_object(Pics.poisonous_tree,99,0,spalte, zeile, "t"));
		                break;
		              case '1':
		            	  editor_objects.add(new board_object(Pics.bunny_l,1,1,spalte, zeile, "1"));
		                break;
		              case '2':
		            	  editor_objects.add(new board_object(Pics.hedgehog_l,1,1, spalte, zeile,"2"));
		                break;
		              case 'b':
		            	  editor_objects.add(new board_object(Pics.boss_l,1,0, spalte, zeile,"b"));
			            break;
		              case 's':
		            	  editor_objects.add(new board_object(Pics.shop_active,1,0, spalte, zeile,"s"));
		                break;
		              case 'c':
		            	  editor_objects.add(new board_object(Pics.sign,1,0,spalte, zeile,"c"));
		                break;
		              case 'm':
		            	  editor_objects.add(new board_object(Pics.carrot_tree,1,0,spalte, zeile,"m"));
		                break;
		              case 'z':
		            	  editor_objects.add(new board_object(Pics.goal,1,1,spalte, zeile, "z"));
		                break;
		              default:
		              // nix
		              }
		          }
		        zeile++; // next line
		        } else {
		        
		        }
			}
	    } while (zeileninhalt != null); // empty line -> EOF (end of file)
			br.close();
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void write_label_txt(String s, JLabel [] label_list, int act_selection){
		int z=0;
		int max=max_room;
		String save_text = "Speichern";
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
		if (selected_lvl%2==0) {
			save_text += " (Level "+String.valueOf(selected_lvl/2);
			if (selected_room%2==0) {
				save_text += " / Raum "+ String.valueOf(selected_room/2)+")";
			}else {
				if (selected_room==1) {
					save_text += " / neuer Raum vor Raum 1)";
				} else if (selected_room==max_room*2+1) {
					save_text += " / neuer Raum hinter Raum "+String.valueOf((selected_room-1)/2)+")";
					
				} else 	{
					save_text += " / neuer Raum zwischen "+String.valueOf((selected_room-1)/2)+" und "+String.valueOf((selected_room+1)/2)+")";
				}
			}
		} else {
			if (selected_lvl==1) {
				save_text += " (neuer Level vor Level 1 / Raum 1)";				
			} else if (selected_lvl==max_lvl*2+1) {
				save_text += " (neuer Level hinter Level "+String.valueOf((selected_lvl-1)/2)+" / Raum 1)";
			}else {
				save_text += " (neuer Level zwischen "+String.valueOf((selected_lvl-1)/2)+" und "+String.valueOf((selected_lvl+1)/2)+" / Raum 1)";
			}
		}
		btn_room_save.setText(save_text);
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
		i=1;
		while (rooms[i][1]) {
			max_lvl=i;
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
	
	private ImageIcon get_index_next_bg_image(){
		ImageIcon bg_icon;
		bg_icon= new ImageIcon(bg_image.get(img_index).getScaledInstance(ed_x_size,ed_y_size,Image.SCALE_DEFAULT));
		return bg_icon;			
	}
	
	private ImageIcon get_lvl_bg_image(){
		String path = local.Fs.img_pfad+local.Fs.os_slash;
		String file = "bg_"+(selected_lvl/2)+"_"+(selected_room/2)+".png";
		Image lvl_bg = Toolkit.getDefaultToolkit().getImage(path+file);
		ImageIcon bg_icon;
		System.out.println(file);
		bg_icon= new ImageIcon(lvl_bg.getScaledInstance(ed_x_size,ed_y_size,Image.SCALE_DEFAULT));
		return bg_icon;			
	}
	
	private void get_bg_images(){
		String path = local.Fs.img_pfad+"editor_templates"+local.Fs.os_slash;
		File folder = new File(path);
		for(File file : folder.listFiles())  {
		  if (file.isFile() && file.getName().startsWith("t_bg_")) {
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
	
	private ArrayList<String> get_room_from_editor() {
		ArrayList<String> editor_line = new ArrayList<String>();
		String e ="_";
		String start="#";
		for (int i = 0; i<max_x_blocks;i++){
			start+=e;
		}
		for (int i=0; i<=max_y_blocks;i++){
			editor_line.add(start);
		}
		for (board_object eo: editor_objects) {
//			System.out.println(eo.chr);
//			System.out.println(eo.x);
//			System.out.println(eo.y);
//			System.out.println("----------");
			
			editor_line.set(eo.y, editor_line.get(eo.y).substring(0,eo.x)+eo.chr+editor_line.get(eo.y).substring(eo.x+1));
		}
//		for (String el: editor_line) {
//			System.out.println(el);
//		}
		
		return editor_line;
	}
	
	private void save_as(int lvl, int room) {
		String file = lvl+"_"+room+".txt";
		try {
			PrintWriter out = new PrintWriter(local.Fs.data_pfad+file);
			for (String rl: room_line) {
				out.println(rl);
			}
            out.close();
      } catch (IOException e){
          e.printStackTrace();
      }
}
}