package gameobjects;

import java.awt.Image;

/**
 * Static enemy, damage over time
 * @author Andreas Roth
 * @author Martin Knonsalla
 *
 */
public class Poisonous_Tree extends Figure {

	@Override public Image getPic(int direction){
		return local.Pics.poisonous_tree;
	}

	/**
	 * Parameterless constructor
	 */
	public Poisonous_Tree () {
		super();
		this.dmg = 2;
		this.height = local.Pics.poisonous_tree.getHeight(null);
		this.width = local.Pics.poisonous_tree.getWidth(null);
	}
}