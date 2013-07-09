package gameobjects;

import java.awt.Image;

//parent class of Fox, Boss, Hero, Goal, Poisonous_Tree, Wall, Shop, Npc
public class Figure {
	
	public boolean destroyable = false;
	public int level = 0;
	public int ep = 0;
	public int dmg;							//damage
	public int defe = 1;					//defensive multiplicator
	public int bugs = 0;
	public int fog = 20;					//only for poisonous trees, shop, npc
	public int height;
	public int width;
	
	
	public Image getPic(int direction) {
		return null;
	}
	
	public int getHp(){
		return 0;
	}
	
	public int getDmg(){
		return this.dmg;
	}
}
