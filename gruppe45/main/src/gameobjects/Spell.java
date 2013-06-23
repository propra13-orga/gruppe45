package gameobjects;

import java.awt.Image;

import main.Main;

public class Spell extends Figure {

	public Spell(int x, int y, Image img, char direc, int damage) {
		super(9, x, y, img);
		this.dmg = damage;
		this.direction = direc;
		Main.obj_list.add(this);
		this.nr = Main.obj_list.size() -1;
	}
}
