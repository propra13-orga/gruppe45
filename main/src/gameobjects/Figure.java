package gameobjects;

import java.awt.Image;

/**
 * Superclass of all gameobjects
 * @author Andreas Roth
 * @author Rebecca Simon
 */
public class Figure {
	
	public boolean destroyable = false;
	public int level = 0;
	public int ep = 0;
	public int dmg;							//damage
	public int defe = 1;					//defensive multiplicator
	public int bugs = 0;
	public int fog = 15;					//only for poisonous trees, shop, npc
	public int height;
	public int width;
	
	/**
	 * Getter for figures image
	 * @param direction - decides which image is returned
	 * @return situational image from local.Pics
	 */
	public Image getPic(int direction) {
		return null;
	}
	
	/**
	 * Getter for figures dmg value
	 * @return <b>int</b> dmg
	 */
	public int getDmg(){
		return this.dmg;
	}


	/**
	 * Getter for figures hp value
	 * @return <b>int</b> hp
	 */
	public int getHp() {
		return 0;
	}
}
