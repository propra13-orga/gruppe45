package gameobjects;

//parent class of Fox, Hero, Goal, Poisonous_Tree, Wall

import java.awt.Image;

import main.Main;


public class Figure {
	
	public Image image;
	public int denseness = 10;			//can objects pass through?
	public int hp;						//health points
	public int mp;						//mana points
	public int lives = 0;
	public int speed = 1;
	public int dmg;						//damage
	public int aoe;						//area of effect (range of dmg)
	public int dps;						//damage per second
	public int dmg_opp_nr;				//nr. of opponents hit
	public boolean visible = true;
	public boolean destroyable = false;
	public int type;					//describes what type of figure this is:
										//0 == goal, shop
										//1 == wall
										//2 == poisonous tree
										//3 == fox
										//4 == player
										
	public int nr;						//index in obj_list
	public int layer = 0;
	public int pos_x;
	public int start_x;
	public int pos_y;
	public int start_y;
	public int height;
	public int width;
	public int fog = 10;				//only for poisonous trees
	
	public Figure(int type, int x, int y, Image img) {
		this.type = type;
		this.pos_x = x;
		this.pos_y = y;
		this.image = img;
//		this.height = ( (int) (this.image.getHeight(null) * Main.scale * 0.3) );
//		this.width = ( (int) (this.image.getWidth(null) * Main.scale) );
		this.height = this.image.getHeight(null);
		this.width = this.image.getWidth(null);
		
	}
}
