package gameobjects;

import java.awt.Image;

import main.Main;

// fox class
public class Fox extends Figure {

	@Override public Image getPic(int direction){
		if(direction == 2 || direction == 1) return local.Pics.fox_r;
		else return local.Pics.fox_l;
	}

	@Override public int getHp(){
		return 100 * Main.level;
	}

	@Override public int getDmg(){
		return this.dmg * Main.level;
	}

	public Fox () {
		super();
		this.dmg = 100;
		this.ep = 1;
		this.bugs = 1;
		this.destroyable = true;
		this.height = local.Pics.fox_r.getHeight(null);
		this.width = local.Pics.fox_r.getWidth(null);
	}

}