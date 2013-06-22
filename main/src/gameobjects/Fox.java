package gameobjects;

import java.awt.Toolkit;

import local.Fs;

// fox class
public class Fox extends Figure {
	public Fox (int x, int y) {
		super(3,x,y,Toolkit.getDefaultToolkit().getImage(Fs.img_pfad+"fox_l.png"));
		this.dmg = 100;
		this.destroyable = true;
	}
}