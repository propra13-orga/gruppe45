package spielobjekte;

import java.awt.Image;

public class objekt {
	
	public String name;
	public Image image;
	public int dichte;
	public int leben_punkte;
	public int geschwindigkeit;
	public int schaden_punkte;
	public int schaden_reichweite;
	public int schaden_wiederholung;
	public int schaden_anzahl_gegner;
	public boolean sichtbar;
	public boolean zerstoerbar;
	public int ebene;
	
	public objekt(String name) {
		this.name = "irgendwas";
		this.dichte = 10;
		this.leben_punkte = 0;
		this.geschwindigkeit = 1;
		this.schaden_punkte = 0;
		this.schaden_reichweite = 0;
		this.schaden_wiederholung=0;
		this.schaden_anzahl_gegner = 0;
		this.sichtbar = true;
		this.zerstoerbar = false;
		this.ebene = 0;
		
	}
}
