package gameobjects;

//parent class of Fox, Hero, Goal, Poisonous_Tree, Wall

import java.awt.Image;
import java.awt.Toolkit;

import local.Fs;

public class Figure {
	
	public Image image;
        public Image off = Toolkit.getDefaultToolkit().getImage(Fs.img_pfad + "icon_fireball.png");
        public Image def;
        public String name = "blubb";
	public int hp = 0;						//health points
	public int hp_pot = 0;
        public int mp = 0;                                          //mana points
	public int mp_pot = 0;
        public int ep = 0;
        public int level = 0;
        public int defe;
        public int bugs = 0;
	public int lives = 0;
	public int dmg;						//damage
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
	public int fog = 20;				//only for poisonous trees, shop
	public char direction = 's';		//used for spells, random movement
	
	public Figure(int figure_type, int x, int y, Image img) {
		type = figure_type;
		pos_x = x;
		pos_y = y;
		image = img;

	}
}
