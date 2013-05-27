package spielobjekte;

/*
 *  class creates the board with background image and all objects on the board with their positions
 *  hero, killerbunny, wall and todesbaum inherit from the objekt 
 *  class in this package
 *  
 *  created in Renderer class
 *  reset for a new game in Renderer class
 *  collision detection in calc class
 *
 */

import java.awt.Image;
import javax.imageio.ImageIO;
import java.awt.Toolkit;
import java.io.*;
import java.util.ArrayList;

import lokal.*;

public class spielfeld {
	
	public int block_groesse=50;
	
	public Image bg_image;
	public String datei;
	public File fdatei;
	private int status = 0;	
	public int max_x_blocks = 0;
	public int max_y_blocks = 0;
	public int rand_x = 40;
	public int rand_y = 70;
	public int naechster_raum;
	public String nachricht ="";
	public ArrayList<wall> walls = new ArrayList<wall>();
	public ArrayList<killerbunny> killers = new ArrayList<killerbunny>();
	public ArrayList<todesbaum> plants = new ArrayList<todesbaum>();
	public ArrayList<hero> heros = new ArrayList<hero>();
	public ArrayList<ziel> ziele = new ArrayList<ziel>();
	
	public spielfeld(String file){
		this.init();
		this.naechster_raum=Integer.parseInt(file)+1;
		this.datei = file+".txt";
		this.create_room(this.datei);
	}
		
	private void init(){ // called by all class constructors to set start values
		try {
			this.bg_image =ImageIO.read(new File(fs.img_pfad+"Su_s BG.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		int h = 768;
		int b = 1024;
		this.max_x_blocks = b/this.block_groesse;
		this.max_y_blocks = h/this.block_groesse; 
	}
	
	public int create_room(String datei) { // reads level file and creates game objects
		// clears lists with game objects (needed for restarted games)
		walls.clear();
		killers.clear();
		plants.clear();
		heros.clear();
		ziele.clear();
		String zeileninhalt =""; // line content of a level file
		int zeilenlaenge =0;
		int zeile;
		boolean feld= false;
		try {
			this.fdatei = new File(fs.data_pfad+this.datei);
			//if (this.fdatei.exists()) {
				FileReader fr = new FileReader(fs.data_pfad+this.datei);
				BufferedReader br = new BufferedReader(fr);
			    zeile=0;
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
						    			create_wall (spalte, zeile);
						    			break;
						    		case 'k':
						    			create_killer(spalte, zeile, "hase");
						    			break;
						    		case 't':
						    			create_killer(spalte, zeile, "baum");
						    			break;
						    		case 'h':
						    			create_hero(spalte, zeile);
						    			break;
						    		case 'z':
						    			create_ziel(spalte, zeile);
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
			//}
		} catch (Exception e) {
			System.out.println("Was? " + e.getMessage());
		}
		return status;
	}

	// 1 wall  at position x / y
	public void create_wall(int start_x, int start_y){
			create_wall(start_x, start_y, start_x, start_y);
	}
	
	// creates walls from start to end with the distance block_groesse
	// used by the method above and slow, much room for improvement
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

	// wall class
	public class wall extends objekt {
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
	// killerbunny class
	public class killerbunny extends objekt {
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
	// todesbaum class
	public class todesbaum extends objekt {
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
			this.image = Toolkit.getDefaultToolkit().getImage(fs.img_pfad+"todesbaum.png");
		}
	}
	// hero class
	public class hero extends objekt {
		public int start_pos_x;
		public int start_pos_y;
		public int anz_leben=2;
		public int start_leben_punkte =100;
		public hero (int x, int y) {
			super("objekt");
			this.pos_x = x;
			this.pos_y = y;
			this.start_pos_x=x;
			this.start_pos_y=y;
			this.dichte = 10;
			this.leben_punkte = this.start_leben_punkte;
			this.geschwindigkeit = 5;
			this.schaden_punkte = 100;
			this.schaden_reichweite = 0;
			this.schaden_wiederholung=0;
			this.schaden_anzahl_gegner = 0;
			this.sichtbar = true;
			this.zerstoerbar=true;
			this.image = Toolkit.getDefaultToolkit().getImage(fs.img_pfad+"held.png");
		}
	
	}
	// destination class (exit of a level)
	public class ziel extends objekt {
		public ziel (int x, int y) {
			super("objekt");
			this.pos_x = x;
			this.pos_y = y;
			this.leben_punkte = 100;
			this.schaden_punkte = 0;
			this.geschwindigkeit = 0;
			this.sichtbar = true;
			this.zerstoerbar=false;
			this.image = Toolkit.getDefaultToolkit().getImage(fs.img_pfad+"ziel.png");
		}
	}
	
	//  creates objects of killerbunny / todesbaum class and adds to the lists 
	// of killers / plants  in this level
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
	// creates object of the hero class and adds to the list of heros in this level
	public void create_hero (int start_x, int start_y){
		hero hase;
		hase = new hero(start_x*this.block_groesse, start_y*this.block_groesse);
		heros.add(hase);
	}
	// creates object of the ziel class and adds to the list of destinations in this level
	public void create_ziel (int start_x, int start_y){
		ziel ziel;
		ziel = new ziel(start_x*this.block_groesse, start_y*this.block_groesse);
		ziele.add(ziel);
	}
}