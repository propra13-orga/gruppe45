package gameobjects;

import java.awt.Image;

/**
 * Used to block ways & seal exits on board
 * @author Andreas Roth
 *
 */
public class Carrot_Tree extends Wall {

	@Override public Image getPic( int direction){
		return local.Pics.carrot_tree;
	}
	
	/**
	 * Parameterless constructor
	 */
	public Carrot_Tree() {
		super();
		this.height = local.Pics.carrot_tree.getHeight(null);
		this.width = local.Pics.carrot_tree.getWidth(null);
	}
}
