package o;


import java.awt.Image;
import javax.imageio.ImageIO;
import java.awt.Toolkit;
import java.io.*;
import java.util.ArrayList;

import l.*;

public class spielfeld {
	
	public int block_groesse=50;
	
	public Image bg_image;
	public String datei;
	private int status = 0;
	public int anz_x_blocks;
	public int anz_y_blocks;	
	public int max_x_blocks;
	public int max_y_blocks;
	public int offset_x;
	public int offset_y;
	public int rand_x = 40;
	public int rand_y = 70;
	public int max_ebene;
	public int naechster_raum;
	public ArrayList<wall> walls = new ArrayList();
	public ArrayList<killerbunny> killers = new ArrayList();
	public ArrayList<todesbaum> plants = new ArrayList();
	public ArrayList<hero> heros = new ArrayList();
	public spielfeld(String file){
		this(file, "");
	}
	
	public spielfeld(String file, String center){
		this.init();
		this.naechster_raum=Integer.parseInt(file)+1;
		this.datei = file;
		this.create_room(this.datei, center);
	}
	
	public spielfeld(int anz_blocks_breite, int anz_blocks_hoehe) {
		this.init();
		this.anz_x_blocks = anz_blocks_breite;
		this.anz_y_blocks = anz_blocks_hoehe;
	}
	
	private void init(){
		String pfad = fs.pfad+fs.os_slash+"img"+fs.os_slash;
		try {
			this.bg_image =ImageIO.read(new File(pfad+"Su_s BG.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		max_x_blocks=14;
		max_y_blocks=15;
		if (anz_x_blocks == 0 || anz_x_blocks>max_x_blocks) {
			anz_x_blocks = max_x_blocks;
		}
		if (anz_y_blocks == 0 || anz_y_blocks>max_y_blocks) {
			anz_y_blocks = max_y_blocks;
		}
		this.max_blocks_berechnen();
		offset_x=0;
		offset_y=0;
	}
	
	public void create_room() {
		this.create_room(0,0, anz_x_blocks, anz_y_blocks);
	}
	
	public void create_room(int x_blocks, int y_blocks, String center) {
		int start_block_x=0;
		int start_block_y=0;
		if (center=="center"){
			if (this.anz_x_blocks<max_x_blocks){
				start_block_x = (int)(this.max_x_blocks-this.anz_x_blocks)/2;		
			}
			if (this.anz_y_blocks<max_y_blocks){
				start_block_y = (int)(this.max_y_blocks-this.anz_y_blocks)/2;	
			}
		}
		this.create_room(start_block_x,start_block_y, start_block_x+this.anz_x_blocks, start_block_y+this.anz_y_blocks);
	}
	
	public void create_room(int start_block_x, int start_block_y, int end_block_x, int end_block_y) {
		try {
			create_wall(start_block_x, start_block_y, end_block_x, start_block_y);
			create_wall(start_block_x, end_block_y, end_block_x, end_block_y);
			create_wall(start_block_x, start_block_y, start_block_x, end_block_y);
			create_wall(end_block_x, start_block_y, end_block_x, end_block_y);
			
		} catch (Exception e) {
			System.out.println("Fehler:");
			System.out.println(e.getCause());
			System.out.println(e.getMessage());
		}
	}
	
	public int create_room(String datei, String center) {
		String zeileninhalt ="";
		int zeilenlaenge =0;
		int zeile;
		boolean feld= false;
		try {
			try {
				FileReader fr = new FileReader("data/"+datei);
				BufferedReader br = new BufferedReader(fr);
			    zeile=0;
			    do {
				    zeileninhalt = br.readLine();
				    zeilenlaenge=zeileninhalt.length();
				    if (zeileninhalt.charAt(0)=='#') {
				    	feld=true;
				    }
				    if (feld) {
					    if (center=="center" && zeile == 0) {
					    	offset_x = (int)(this.max_x_blocks-zeilenlaenge)/2;
					    }
					    
					    for(int spalte=0; spalte<zeilenlaenge;spalte++){
					    	switch(zeileninhalt.charAt(spalte)) {
					    		case 'w':
					    			create_wall (this.offset_x+spalte, zeile);
					    			break;
					    		case 'k':
					    			create_killer(this.offset_x+spalte, zeile, "hase");
					    			break;
					    		case 't':
					    			create_killer(this.offset_x+spalte, zeile, "baum");
					    			break;
					    		case 'h':
					    			create_hero(this.offset_x+spalte, zeile);
					    			break;
					    		default:
					    			// nix
					    	}
					    }
					    zeile++;
				    } else {
	
				    }
			    } while (zeileninhalt != null);
			    br.close();
			} catch (IOException e) {
				this.create_room(12,10, "center");
			}
		} catch (Exception e) {
			status =1;
		}
		return status;
	}

		// 1 Wand-Block a Position x / y
	public void create_wall(int start_x, int start_y){
			create_wall(start_x, start_y, start_x, start_y);
	}
	
		// Wand
	public void create_wall (int start_x, int start_y, int end_x, int end_y){
		int start;
		int end;
		int on_line;
		wall wand;
		boolean direction = true; // direction: true = horizontal, false = vertikal
		
		if (start_x==end_x){ // horizontal
			start  = start_y;
			end = end_y;
			on_line = start_x*this.block_groesse;
		} else { // vertikale
			start  = start_x;
			end = end_x;
			on_line = start_y*this.block_groesse;
			direction = false;
		}
		for (int i=start; i<=end; i++) {
			if (direction) {
				wand = new wall(on_line,i*this.block_groesse);
			} else {
				wand = new wall(i*this.block_groesse,on_line);
			}
			walls.add(wand);
		}
	}
	
	public void max_blocks_berechnen() {
		int h = 768;
		int b = 1024;
		this.max_x_blocks = b/this.block_groesse; 
		if (this.max_y_blocks>(h/this.block_groesse)) {
			this.max_y_blocks=h/this.block_groesse;
		}
	}
	
	public class wall extends objekt {
		public int pos_x;
		public int pos_y;
		public wall (int x, int y) {
			super("objekt");
			this.pos_x = x;
			this.pos_y = y;
			this.dichte = 10;
			this.geschwindigkeit = 0;
			this.sichtbar = true;
			this.image = Toolkit.getDefaultToolkit().getImage(fs.img_pfad+"baum_eng.png");
		}
	}
	
	public class killerbunny extends objekt {
		public int pos_x;
		public int pos_y;
		public killerbunny (int x, int y) {
			super("objekt");
			this.pos_x = x;
			this.pos_y = y;
			this.dichte = 10;
			this.geschwindigkeit = 1;
			this.schaden_punkte = 100;
			this.schaden_reichweite = 0;
			this.schaden_wiederholung=0;
			this.schaden_anzahl_gegner = 0;
			this.sichtbar = true;
			this.image = Toolkit.getDefaultToolkit().getImage(fs.img_pfad+"killerhase_links.png");
		}
	}

	public class todesbaum extends objekt {
		public int pos_x;
		public int pos_y;
		public todesbaum (int x, int y) {
			super("objekt");
			this.pos_x = x;
			this.pos_y = y;
			this.dichte = 10;
			this.geschwindigkeit = 0;
			this.schaden_punkte = 1;
			this.schaden_reichweite = 0;
			this.schaden_wiederholung=0;
			this.schaden_anzahl_gegner = 0;
			this.sichtbar = true;
			this.ebene = 0;
			this.image = Toolkit.getDefaultToolkit().getImage(fs.img_pfad+"todesbaum.png");
		}
	}
	
	public class hero extends objekt {
		public int pos_x;
		public int pos_y;
		public hero (int x, int y) {
			super("objekt");
			this.pos_x = x;
			this.pos_y = y;
			this.dichte = 10;
			this.leben_punkte = 100;
			this.geschwindigkeit = 5;
			this.schaden_punkte = 100;
			this.schaden_reichweite = 0;
			this.schaden_wiederholung=0;
			this.schaden_anzahl_gegner = 0;
			this.sichtbar = true;
			this.zerstoerbar=true;
			this.ebene = 0;
			this.image = Toolkit.getDefaultToolkit().getImage(fs.img_pfad+"held.png");
		}
	
	}
	public void create_killer (int start_x, int start_y, String killertyp){
		if (killertyp =="hase") {
			killerbunny hase;
			hase = new killerbunny(start_x*this.block_groesse, start_y*this.block_groesse);
			killers.add(hase);
		} 
	    if (killertyp=="baum") {
	    	todesbaum baum;
			baum = new todesbaum(start_x*this.block_groesse, start_y*this.block_groesse);
			plants.add(baum);
		}
	}
	
	public void create_hero (int start_x, int start_y){
		hero hase;
		hase = new hero(start_x*this.block_groesse, start_y*this.block_groesse);
		heros.add(hase);
	}
}