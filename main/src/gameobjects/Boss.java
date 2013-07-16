package gameobjects;

import java.awt.Image;

import main.Main;

/**
 * Boss class
 * @author Andreas Roth
 *
 */
public class Boss extends Fox{

	@Override public Image getPic(int direction){
		if(direction == 2) return local.Pics.boss_r;
		else return local.Pics.boss_l;
	}

	@Override public int getHp(){
		return 1000 * Main.level;
	}

	/**
	 * Parameterless constructor
	 */
	public Boss(){
		super();
		this.destroyable = true;
		this.bugs = 10;
		this.ep = 10;
		this.dmg = 200;
		this.height = new Integer(local.Pics.boss_l.getHeight(null));
		this.width = new Integer(local.Pics.boss_l.getWidth(null));
	}
}
