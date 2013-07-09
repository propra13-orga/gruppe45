package gameobjects;

import java.awt.Image;


public class Shop  extends Figure{
	
	@Override public Image getPic(int direction){
		if(main.Main.shop) return local.Pics.shop_active;
		else return local.Pics.shop_inactive;
	}
	
	public Shop () {
		super();
	}
}
