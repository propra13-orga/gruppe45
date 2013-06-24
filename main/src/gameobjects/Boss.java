package gameobjects;

import java.awt.Toolkit;

import local.Fs;

//Boss class
public class Boss extends Figure{

	public Boss(int x, int y){
		super(5,x,y,Toolkit.getDefaultToolkit().getImage(Fs.img_pfad + "boss_l.png"));
		this.hp = 10000;
		this.bugs = 10;
		this.ep = 10;
		this.dmg = 200;
	}
		
}
