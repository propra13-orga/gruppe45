package main;

import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import lokal.fs;
import spielobjekte.*;

/*
 * 
 * 	Berechnet Kollisionen und Auswirkungen auf Spielfigur
 *  Wird in der Renderer-Klasse erstellt und aufgerufen
 */

public class calc {
	
    public static int P1_richtung_x = 0;              //Startposition X-Achse
    public static int P1_richtung_y = 0;            //Startposition Y-Achse
    public static boolean ingame=true;
    public static boolean neues_spiel = false;
    public Spielfeld board;
    public int held_breite;
    public int held_hoehe;
    public int wand_breite;
    public int wand_hoehe;
    public int killer_breite;
    public int killer_hoehe;
    public int ziel_breite;
    public int ziel_hoehe;
    Rectangle w;
    Rectangle h;
    Rectangle k;
    Rectangle z;
	public calc(Spielfeld board) {
		this.board = board;
	}
	
	// adjusting values for new level (bg image, level file, ...)
	public void naechster_Raum(){
		String imgdat ="";
		switch(board.naechster_raum%6){
			case 1:
				imgdat=fs.img_pfad+"Su_s BG.png";
				break;
			case 2:
				imgdat=fs.img_pfad+"bg kurve l.png";
				break;
			case 3:
				imgdat=fs.img_pfad+"bg kurve u l.png";
				break;
			case 4:
				imgdat=fs.img_pfad+"Su_s BG.png";
				break;
			case 5:
				imgdat=fs.img_pfad+"bg kurve r.png";
				break;
			case 0:
				imgdat=fs.img_pfad+"bg kurve u r.";
				break;
			default:
				imgdat=fs.img_pfad+"Su_s BG.png";
		}
		try {
			board.bg_image=ImageIO.read(new File(imgdat));
		} catch (IOException e) {
			e.printStackTrace();
		};
		board.datei = Integer.toString(board.naechster_raum)+".txt";
		board.create_room(Integer.toString(board.naechster_raum));
		board.naechster_raum++;
		board.datei = Integer.toString(board.naechster_raum)+".txt";
		board.fdatei = new File(fs.data_pfad+board.datei);
	}
	
	
	public void updateData () { // Berechnungen, Kollisionen
		boolean collision = false;
		int pos_x_alt;
		int pos_y_alt;
		int schaden;
		board.nachricht = "";
		// für jeden Held Kollisionen berechnen
		// Kollision wird gegen jedes Objekt einzeln geprüft. Geht bestimmt schöner
		for (Spielfeld.hero hase: board.heros){
			pos_x_alt = hase.pos_x;
			pos_y_alt = hase.pos_y;
			schaden=0;
			hase.pos_x = hase.pos_x + calc.P1_richtung_x * hase.geschwindigkeit;
			hase.pos_y = hase.pos_y + calc.P1_richtung_y * hase.geschwindigkeit;
			held_breite = hase.image.getWidth(null);
			held_hoehe = hase.image.getHeight(null);
			if (hase.pos_y>768-board.rand_x-held_hoehe || hase.pos_y<board.rand_x) {
				collision = true;
			}
			if (hase.pos_x>1024-board.rand_y-held_breite || hase.pos_x<board.rand_y) {
				collision = true;
			}
			h = new Rectangle(hase.pos_x+40, hase.pos_y-50, held_breite-40, held_hoehe-50);
	        for (Spielfeld.wall wand: board.walls){
	        	wand_breite = wand.image.getWidth(null);
	        	wand_hoehe = wand.image.getHeight(null);
	        	w = new Rectangle(wand.pos_x+40, wand.pos_y-50, wand_breite-40, wand_hoehe-50);
	        	if (h.intersects(w)) {
	        		collision = true;
	        	}
	        }
	        for (Spielfeld.killerbunny killerhase: board.killers){
	        	killer_breite = killerhase.image.getWidth(null);
	        	killer_hoehe = killerhase.image.getWidth(null);
	        	k = new Rectangle(killerhase.pos_x+20, killerhase.pos_y-50, killer_breite+5, killer_hoehe);
	        	if (h.intersects(k)) {
	        		collision = true;
	        		schaden = 1000;
	        		board.nachricht = "...von Killerhasen zerfetzt.";
	        	}
	        }
	        for (Spielfeld.todesbaum plant: board.plants){
	        	killer_breite = plant.image.getWidth(null);
	        	killer_hoehe = plant.image.getWidth(null);
	        	k = new Rectangle(plant.pos_x+40, plant.pos_y-50, killer_breite-40, killer_hoehe-50);
	        	if (h.intersects(k)) {
	        		collision = true;
	        		schaden = 1;
	        		board.nachricht = "...am Baum vergiftet.";
	        	}
	        }
	        for (Spielfeld.ziel ziel: board.ziele){
	        	ziel_breite = ziel.image.getWidth(null);
	        	ziel_hoehe = ziel.image.getWidth(null);
	        	z = new Rectangle(ziel.pos_x+5, ziel.pos_y-ziel_hoehe,ziel_breite-5, ziel_hoehe);
	        	if (h.intersects(z)) {
	        		//System.out.println(fs.data_pfad+board.datei);
	        		if (board.fdatei.exists()) {
	        			this.naechster_Raum();
	        		} else { // Es gibt keinen nächsten Raum. Gewonnen!!
	        			hase.leben_punkte=0;
	        			hase.anz_leben=0;
	        			board.nachricht = "GEWONNEN! ";
	        		}
	        	}
	        }
			if (collision==true && calc.ingame){
				hase.pos_x = pos_x_alt;
				hase.pos_y = pos_y_alt;
				hase.leben_punkte = hase.leben_punkte-schaden;
			}
			if (hase.leben_punkte<=0) {
				if (hase.anz_leben>0) {
					hase.pos_x = hase.start_pos_x;
					hase.pos_y = hase.start_pos_y;
					hase.anz_leben--;
				} else {
					calc.ingame = false;
					board.nachricht = board.nachricht+"Spielende";
				}
				hase.leben_punkte = hase.start_leben_punkte;
			}
		}		
	}
}
