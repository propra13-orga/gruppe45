package gameobjects;

import java.awt.Image;
import main.Main;

/**
 * Wall class
 * @author Andreas Roth
 * @author Martin Knonsalla
 *
 */
public class Wall extends Figure {

	@Override public Image getPic( int direction){
		switch(Main.level){
			case 1:		return local.Pics.tree;
			case 2:		return local.Pics.bush;
			default:	return local.Pics.rock;
		}
	}
	
	/**
	 * Parameterless constructor
	 */
	public Wall () {
		super();	//change tree to variable according to level
	}
}