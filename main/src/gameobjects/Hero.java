package gameobjects;

import java.awt.Image;
import java.awt.Toolkit;

import local.Fs;

// hero class
public class Hero extends Figure {
	public int start_health_points =100;
	public Image leben_img=Toolkit.getDefaultToolkit().getImage(Fs.img_pfad+"leben_kopf.png");
	public Hero (int x, int y) {
		super(4,x,y);
		this.start_x=x;
		this.start_y=y;
		this.hp = this.start_health_points;
		this.lives=2;
		this.speed = 5;
		this.dmg = 100;
		this.destroyable = true;
		this.image = Toolkit.getDefaultToolkit().getImage(Fs.img_pfad+"held.png");
	}
	public Image get_hero_image() {
		return this.image;
	}
	public Image get_lebensbalken_image() {
		if(this.hp>66) return Toolkit.getDefaultToolkit().getImage(Fs.img_pfad+"lebensbalken_gruen.png");
		if(this.hp>33) return Toolkit.getDefaultToolkit().getImage(Fs.img_pfad+"lebensbalken_gelb.png");
		else return Toolkit.getDefaultToolkit().getImage(Fs.img_pfad+"lebensbalken_rot.png");
	}

}