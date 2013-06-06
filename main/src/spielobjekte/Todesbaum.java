package spielobjekte;

import java.awt.Toolkit;

import lokal.Fs;

// todesbaum class
public class Todesbaum extends Objekt {
	public int fog = 10;
	public Todesbaum (int x, int y) {
		super(2,x,y);
		this.schaden_punkte = 1;
		this.image = Toolkit.getDefaultToolkit().getImage(Fs.img_pfad+"todesbaum.png");
	}
}