package gameobjects;

import java.awt.Toolkit;

import local.Fs;

// killerbunny class
public class Fox extends Figure {
	public Fox (int x, int y) {
		super(3,x,y);
		this.geschwindigkeit = 1;
		this.schaden_punkte = 100;
		this.image = Toolkit.getDefaultToolkit().getImage(Fs.img_pfad+"killerhase_links.png");
	}
}