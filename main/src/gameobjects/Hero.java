package gameobjects;

import java.awt.Image;
import java.awt.Toolkit;

import local.Fs;

// hero class
public class Hero extends Figure {
	public int start_hp =100;
	public Image lives_img=Toolkit.getDefaultToolkit().getImage(Fs.img_pfad+"lives_rabbit.png");
	
	public Hero (int x, int y, String player) {
		super(4,x,y,Toolkit.getDefaultToolkit().getImage(Fs.img_pfad+player+"_left.png"));
		this.start_x=x;
		this.start_y=y;
		this.hp = this.start_hp;
		this.lives=2;
		this.speed = 5;
		this.dmg = 100;
		this.destroyable = true;
	}

	public Image getImage_hero() {
		return this.image;
	}
	
	public Image getImage_healthbar() {
		if(this.hp>66) return Toolkit.getDefaultToolkit().getImage(Fs.img_pfad+"healthbar_green.png");
		if(this.hp>33) return Toolkit.getDefaultToolkit().getImage(Fs.img_pfad+"healthbar_yellow.png");
		else return Toolkit.getDefaultToolkit().getImage(Fs.img_pfad+"healthbar_red.png");
	}

}