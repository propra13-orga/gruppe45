package spielobjekte;

import java.awt.Toolkit;

import lokal.Fs;

// destination class (exit of a level)
public class Ziel extends Objekt {
	public Ziel (int x, int y) {
		super(0,x,y);
		this.image = Toolkit.getDefaultToolkit().getImage(Fs.img_pfad+"ziel.png");
	}
}