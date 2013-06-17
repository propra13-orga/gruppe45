package gameobjects;

import java.awt.Toolkit;

import local.Fs;

// fox class
public class Fox extends Figure {
	public Fox (int x, int y) {
		super(3,x,y,Toolkit.getDefaultToolkit().getImage(Fs.img_pfad+"killerhase_links.png"));
		this.speed = 1;
		this.dmg = 100;
	}
}