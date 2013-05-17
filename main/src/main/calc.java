package main;

import o.*;

import java.awt.Rectangle;

public class calc {
	
    public static int P1_richtung_x = 0;              //Startposition X-Achse
    public static int P1_richtung_y = 0;            //Startposition Y-Achse
    public static boolean ingame=true;
    public spielfeld board;
    public int held_breite;
    public int held_hoehe;
    public int wand_breite;
    public int wand_hoehe;
    public int killer_breite;
    public int killer_hoehe;
    Rectangle w;
    Rectangle h;
    Rectangle k;
    
	public calc(spielfeld board) {
		this.board = board;
	}
	
	public void updateData () {
		boolean collision = false;
		int pos_x_alt;
		int pos_y_alt;
		int schaden;
		
		for (spielfeld.hero hase: board.heros){
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
	        for (spielfeld.wall wand: board.walls){
	        	wand_breite = wand.image.getWidth(null);
	        	wand_hoehe = wand.image.getHeight(null);
	        	w = new Rectangle(wand.pos_x+40, wand.pos_y-50, wand_breite-40, wand_hoehe-50);
	        	if (h.intersects(w)) {
	        		collision = true;
	        	}
	        }
	        for (spielfeld.killerbunny killerhase: board.killers){
	        	killer_breite = killerhase.image.getWidth(null);
	        	killer_hoehe = killerhase.image.getWidth(null);
	        	k = new Rectangle(killerhase.pos_x+20, killerhase.pos_y-50, killer_breite+5, killer_hoehe);
	        	if (h.intersects(k)) {
	        		collision = true;
	        		schaden = 1000;
	        	}
	        }
	        for (spielfeld.todesbaum plant: board.plants){
	        	killer_breite = plant.image.getWidth(null);
	        	killer_hoehe = plant.image.getWidth(null);
	        	k = new Rectangle(plant.pos_x+40, plant.pos_y-50, killer_breite-40, killer_hoehe-50);
	        	if (h.intersects(k)) {
	        		collision = true;
	        		schaden = 1;
	        	}
	        }
			if (collision==true){
				hase.pos_x = pos_x_alt;
				hase.pos_y = pos_y_alt;
				hase.leben_punkte = hase.leben_punkte-schaden;
			}
			if (hase.leben_punkte<=0) {
				calc.ingame = false;
			}
		}		
	}
}
