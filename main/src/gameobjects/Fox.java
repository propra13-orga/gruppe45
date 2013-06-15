package gameobjects;

import java.awt.Toolkit;

import local.Fs;

// fox class
public class Fox extends Figure {
	public Fox (int x, int y) {
		super(3,x,y);
		this.speed = 1;
		this.dmg = 100;
		this.image = Toolkit.getDefaultToolkit().getImage(Fs.img_pfad+"killerhase_links.png");
	}
}