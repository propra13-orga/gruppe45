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
import javax.swing.JComboBox;
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
		int index=0;
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
			this.w = img.getWidth(null)/50;
			this.h = img.getHeight(null)/50;
			border=false;
		}

		public void set_wh(){
			this.w = img.getWidth(null)/50;
			this.h = img.getHeight(null)/50;
		}
	}

	//create Gui Elements
	JLayeredPane lpane;
	JTextArea screen;
	JLabel actual_figure;
	JLabel bg, lbl_sel_lvl, lbl_sel_room, message ;
	JButton btn_room_show, btn_del_obj;
	JButton btn_obj_up, btn_obj_down, btn_obj_left, btn_obj_right, btn_room_save, btn_exit;
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
	ArrayList<board_object> button_objects = new ArrayList<board_object>();
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
	int selected_lvl=1, selected_room=1, act_lvl=1, act_room=1, max_lvl, max_room;
	
	int max_x_blocks = (Main.board_width/local.Create.block_groesse);
	int max_y_blocks = (Main.board_height/local.Create.block_groesse);
	
	int blockgroesse_x = ed_x_size/ max_x_blocks;
	int blockgroesse_y =ed_y_size / max_y_blocks;
	
	float faktor_x= (float)Main.board_width/ed_x_size;
	float faktor_y= (float)Main.board_height/ed_y_size;

	private ArrayList <board_object> editor_objects = new ArrayList<board_object>();
	
	public LevelEditor(){
		
		max_x_blocks -=2;
		max_y_blocks -=1;
		setTitle("Level-Editor");
		setSize(ed_win_x_size, ed_win_y_size);							//Window Size
		setResizable(false);						//Window frame not resizable
		setAlwaysOnTop(true);
		this.setLayout(null);
 			
 		get_bg_images();
 		//get_level_rooms();
 		//editor display field
 		
 		bg = new JLabel(get_index_next_bg_image());
 		bg.setBounds(x, 150, ed_x_size, ed_y_size);
 		bg.setBorder(LineBorder.createBlackLineBorder());
 		bg.setBackground(Color.black);
 		bg.setForeground(Color.black);
		
 		// Figur bewegen buttons
 		x=250;
 		y=40;
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
 		
 		 
	    // ComboBox Level / Room selection

 		x=40;
 		y=40;
 		w=50;
 		h=20;
 		String[] sel = {"1", "2", "3"};
	    final JComboBox sel_lvl = new JComboBox(sel);
	    final JComboBox sel_room = new JComboBox(sel);
	    
    	sel_lvl.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	    		selected_lvl = sel_lvl.getSelectedIndex()+1;
	    		selected_room = sel_room.getSelectedIndex()+1;
	    		hide_del_button(act_object);
	    		act_object=null;
	    		editor_show_room(selected_lvl,selected_room);
	    	}
	    });
    	sel_room.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	    		selected_lvl = sel_lvl.getSelectedIndex()+1;
	    		selected_room = sel_room.getSelectedIndex()+1;
	    		hide_del_button(act_object);
	    		act_object=null;
	    		editor_show_room(selected_lvl,selected_room);
	    	}
	    });
	    sel_lvl.setBounds(x, y, w, h);
	    sel_room.setBounds(x, y+40, w, h);

	    // Label sel_lvl, sel_room
	    
	    lbl_sel_lvl = new JLabel("Level:");
	    lbl_sel_lvl.setBounds(x, y-18, w,h);
 		lbl_sel_room = new JLabel("Raum:");
 		lbl_sel_room.setBounds(x, y+22, w,h);
	    		
 		// save room button
 		x=30;
 		y=640;
 		w=380;
 		h=20;
 		btn_room_save= new JButton("Speichern");
 		btn_room_save.setBounds(x, y, w, h);
 		btn_room_save.setToolTipText("Raum speichern");
 		btn_align_center(btn_room_save);
 		
 		// exit button
 		x=620;
 		w=150;
 		btn_exit= new JButton("Verlassen");
 		btn_exit.setBounds(x, y, w, h);
 		btn_exit.setToolTipText("Editor verlassen");
 		btn_align_center(btn_exit);
 		
 		// del object button
 		btn_del_obj = new JButton("x");
 		btn_del_obj.setBounds(0, 0, 15, 15);
 		btn_del_obj.setVisible(false);
 		btn_del_obj.setToolTipText("Figur löschen");
 		btn_align_center(btn_del_obj);
 		
 		// add object buttons
 		y=30;
 		x=380;
 		w=60;
 		h=70;
 		step=w+5;
 		int img_breite=w-10;
 		int img_hoehe=0;
 		int z=0;
 		float img_faktor=0;
 		String chr="_";
 		for (int i=0; i<Create.gameobjects.length-5;i++) {
 			img = new ImageIcon(Create.gameobjects[i].getPic(1));
 			
 			img.getImage(); // noch notwendig wegen lazy loading beim Hero. Kann später raus.
 			img_faktor= (int) (img.getImage().getWidth(null)/img_breite);
 			img_hoehe = (int) (img.getImage().getHeight(null)/img_faktor);
 			if (img_hoehe>0 && img_hoehe<1000 && (i<1 || i>3)) {
 				img.setImage(img.getImage().getScaledInstance(img_breite,img_hoehe,Image.SCALE_DEFAULT)); 
 				btn_gameobjects[z] = new JButton (img);
 				btn_gameobjects[z].setBounds(x+step*z, y, w, h);
 				switch (i){
 					case 4: chr="w";break;
 					case 5: chr="t";break;	
 					case 6: chr="k";break;	
 					case 7: chr="b";break;	
 					case 8: chr="s";break;	
 					
 				}
 				board_object bo = new board_object(img.getImage(),1,1,1,1,chr);
 				bo.index=z;
 				bo.img = Create.gameobjects[i].getPic(1);
 				bo.set_wh();
 				button_objects.add(bo);
 				z++;
 			}
 		}
 		
 		// status message
 		message = new JLabel("");
 		message.setBounds(ed_x_start, ed_y_start-20, 200,20);
 		message.setForeground(java.awt.Color.red);
 		
		btn_gameobjects[0].setVerticalTextPosition(SwingConstants.BOTTOM);
		btn_gameobjects[0].setHorizontalTextPosition(SwingConstants.CENTER);
		btn_gameobjects[0].setText(String.valueOf(img_index+1));

		lpane =  getLayeredPane();
		lpane.add(bg,-200);
		for (int i=0; i<z;i++) {
			lpane.add(btn_gameobjects[i],i);
		}
		lpane.add(btn_obj_up,21);
		lpane.add(btn_obj_down,22);
		lpane.add(btn_obj_left,23);
		lpane.add(btn_obj_right,24);
		lpane.add(btn_room_save,25);
		lpane.add(btn_exit,27);
		lpane.add(sel_lvl,29);
		lpane.add(sel_room,30);
		lpane.add(lbl_sel_lvl,31);
		lpane.add(lbl_sel_room,32);
		lpane.add(btn_del_obj,0);
		lpane.add(message,1);
		
		this.setVisible(true);
		editor_show_room(1,1);
		
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
		// Hinzufügen von objekten über Buttons
		for (int zz=1; zz<z;zz++) {
			final int zy = zz;
			btn_gameobjects[zy].addActionListener(new ActionListener(){
		    	public void actionPerformed(ActionEvent e){
		    		final board_object bo = new board_object(button_objects.get(zy).img,button_objects.get(zy).max, button_objects.get(zy).min, button_objects.get(zy).x, button_objects.get(zy).y, button_objects.get(zy).chr);
		    		//bo = button_objects.get(zy);
		    		w = (int) (bo.img.getWidth(null)/faktor_x);
					h = (int) (bo.img.getHeight(null)/faktor_y);
					ImageIcon bgi= new ImageIcon(bo.img.getScaledInstance(w,h,Image.SCALE_DEFAULT));
					JLabel new_bo = new JLabel(bgi);
					new_bo.setBounds(ed_x_start+blockgroesse_x*bo.x, ed_y_start+blockgroesse_y*bo.y, w, h);
					bo.boj =new_bo;
					lpane.add(bo.boj,5);
					editor_objects.add(bo);
					figure.add(bo.boj);
					bo.boj.addMouseListener(new MouseAdapter() {
						  public void mousePressed(MouseEvent e) {
							  for(JLabel jl:figure){
								  jl.setBorder(BorderFactory.createEmptyBorder());
							  }
							bo.boj.setBorder(LineBorder.createBlackLineBorder());
							act_object=bo;
							show_del_button(act_object);
							message.setText("");
						  }
						});
		    	}
		    });
		}
		
		// bewege objekt im Editor um 1 block nach rechts
		btn_obj_right.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	    		if (act_object!=null) {
	    			if (act_object.x+act_object.w<max_x_blocks) {
		    			act_object.x+=1;
			    		act_object.boj.setLocation(ed_x_start+act_object.x*blockgroesse_x, ed_y_start+act_object.y*blockgroesse_y);
			    		show_del_button(act_object);
			    		message.setText("");
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
			    		show_del_button(act_object);
			    		message.setText("");
	    			}
	    		}
	    	}
	    });
		
		// bewege objekt im Editor um 1 block nach unten
		btn_obj_down.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	    		if (act_object!=null) {
	    			if (act_object.y+act_object.h<max_y_blocks) {
		    			act_object.y+=1;
			    		act_object.boj.setLocation(ed_x_start+act_object.x*blockgroesse_x, ed_y_start+act_object.y*blockgroesse_y);
			    		show_del_button(act_object);
			    		message.setText("");
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
			    		show_del_button(act_object);
			    		message.setText("");
	    			}
	    		}
	    	}
	    });
		
		// Objekt Löschen Button
		btn_del_obj.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	    		if (act_object!=null) {
	    			hide_del_button(act_object);
	    			act_object.boj.setVisible(false);
	    			figure.remove(act_object.boj);
	    			editor_objects.remove(act_object);
	    			message.setText("Objekt gelöscht");
	    		}
	    	}
	    });
		
		// Save
		btn_room_save.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	    		room_line = get_room_from_editor();
	    		save_as(selected_lvl, selected_room);
	    		
	    		message.setText("Level "+selected_lvl+" Raum "+selected_room+" gespeichert");
	    	}
	    });
		
		// verlassen (sollte eigentlich zum Hauptmenue gehen)
		btn_exit.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	    		System.exit(0);
	    	}
	    });
		
	}
	
	private void show_del_button(board_object act_object) {
		if (act_object.chr != "1" && act_object.chr != "2" && act_object.chr != "z") {
			btn_del_obj.setLocation(ed_x_start+act_object.x*blockgroesse_x, ed_y_start+act_object.y*blockgroesse_y);
			btn_del_obj.setVisible(true);
		} else {
			btn_del_obj.setVisible(false);
		}
	}
	
	private void hide_del_button(board_object act_object) {
		btn_del_obj.setLocation(0, 0);
		btn_del_obj.setVisible(false);
	}
	
	// lese Datei und positioniere Raum-Objekte auf dem Editor-Fenster
	private void editor_show_room(int lvl, int room) {
		int w,h;
		bg.setIcon(get_lvl_bg_image());
		for (JLabel jl: figure) {
			jl.setVisible(false);
			lpane.remove(jl);
		}
		figure.clear();
		get_room_objects(lvl, room);
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
					show_del_button(act_object);
				  }
				});
			act_object=null;
		}
	}
	
	
	// schreibe Raum-Objekte aus Datei in editor_objects
	private void get_room_objects(int lvl, int room) {
		String dat_name;
		int zeile=0;
		int zeilenlaenge =0;
		boolean feld=false;
		editor_objects.clear();
		String zeileninhalt ="";
		dat_name = "c_"+lvl+"_"+room;
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
		            	  editor_objects.add(new board_object(Pics.carrot,1,0,spalte, zeile,"m"));
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
	
	private void set_max_room(int selected_lvl) {
		max_room=3;
		max_lvl=3;
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
		String file = "bg_"+(selected_lvl)+"_"+(selected_room)+".png";
		Image lvl_bg = Toolkit.getDefaultToolkit().getImage(path+file);
		ImageIcon bg_icon;
		bg_icon= new ImageIcon(lvl_bg.getScaledInstance(ed_x_size,ed_y_size,Image.SCALE_DEFAULT));
		return bg_icon;			
	}
	
	private void get_bg_images(){
		String path = local.Fs.img_pfad+local.Fs.os_slash;
		File folder = new File(path);
		for(File file : folder.listFiles())  {
		  if (file.isFile() && file.getName().startsWith("c_bg_1")) {
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
			editor_line.set(eo.y, editor_line.get(eo.y).substring(0,eo.x)+eo.chr+editor_line.get(eo.y).substring(eo.x+1));
		}
		return editor_line;
	}
	
	private void save_as(int lvl, int room) {
		String file = "c_"+lvl+"_"+room+".txt";
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