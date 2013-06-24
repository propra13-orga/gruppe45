package gameobjects;

import java.awt.Toolkit;

import main.Main;
import local.Fs;

// fox class
public class Fox extends Figure {
	public Fox (int x, int y) {
		super(3,x,y,Toolkit.getDefaultToolkit().getImage(Fs.img_pfad+"fox_l.png"));
		this.level = Main.level;
		this.dmg = 100 * this.level;
		this.hp = 100 * this.level;
		this.ep = 1;
		this.destroyable = true;
	}
}