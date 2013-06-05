package spielobjekte;


/*
 * parent class for the game objects hero, killerbunny, wall, todesbaum,...
 * subclass description and creation in the spielfeld class 
 */

import java.awt.Image;

public class Objekt {
	
	public Image image;
	public int dichte = 10;
	public int leben_punkte;
	public int geschwindigkeit = 1;
	public int schaden_punkte;
	public int schaden_reichweite;
	public int schaden_wiederholung;
	public int schaden_anzahl_gegner;
	public boolean sichtbar = true;
	public boolean zerstoerbar = false;
	public int type;			//gibt Kollisionsmethode Informationen zum weiteren Vorgehen
	public int nr;				//Nr. im Array
	public int ebene = 0;
	public int pos_x;
	public int pos_y;
	
	public Objekt(int type, int x, int y) {
		this.type = type;
		this.pos_x = x;
		this.pos_y = y;
		
	}
}
