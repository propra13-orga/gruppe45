package gameobjects;

import java.awt.Image;

// todesbaum class
public class Poisonous_Tree extends Figure {

	@Override public Image getPic(int direction){
		return local.Pics.poisonous_tree;
	}

	public Poisonous_Tree () {
		super();
		this.dmg = 2;
		this.height = local.Pics.poisonous_tree.getHeight(null);
		this.width = local.Pics.poisonous_tree.getWidth(null);
	}
}