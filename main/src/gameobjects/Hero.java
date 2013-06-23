package gameobjects;

import java.awt.Image;
import java.awt.Toolkit;

import local.Fs;

// hero class
public class Hero extends Figure {
    public Image lives_img=Toolkit.getDefaultToolkit().getImage(Fs.img_pfad+"lives_bunny.png");
	
	public Hero (int x, int y, String player) {
		super(4,x,y,Toolkit.getDefaultToolkit().getImage(Fs.img_pfad+player+"_l.png"));
		this.start_x=x;
		this.start_y=y;
		this.level = 1;
		this.hp = 100;
		this.mp = 100;
		this.lives = 2;
		this.dmg = 100;
		this.destroyable = true;
		this.bag = new Inventory();
	}
}