package spielobjekte;

import java.awt.Toolkit;

import lokal.Fs;

// killerbunny class
public class Killerbunny extends Objekt {
	public Killerbunny (int x, int y) {
		super(3,x,y);
		this.geschwindigkeit = 1;
		this.schaden_punkte = 100;
		this.image = Toolkit.getDefaultToolkit().getImage(Fs.img_pfad+"killerhase_links.png");
	}
}