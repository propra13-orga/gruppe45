package gameobjects;

import java.awt.Toolkit;

import local.Fs;

//Boss class
public class Boss extends Figure{

	public Boss(int x, int y){
		super(5,x,y,local.Pics.boss_l);
		this.destroyable = true;
		this.hp = 10;
		this.bugs = 10;
		this.ep = 10;
		this.dmg = 200;
	}
		
}
