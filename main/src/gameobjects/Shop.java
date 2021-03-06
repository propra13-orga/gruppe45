package gameobjects;

import java.awt.Image;

/**
 * Shop class
 * @author Andreas Roth
 * @author Martin Knonsalla
 *
 */
public class Shop  extends Figure{
	
	@Override public Image getPic(int direction){
		if(main.Main.shop) return local.Pics.shop_active;
		else return local.Pics.shop_inactive;
	}
	
	/**
	 * Parameterless constructor
	 */
	public Shop () {
		super();
		this.height = local.Pics.shop_active.getHeight(null);
		this.width = local.Pics.shop_active.getWidth(null);
	}
}
