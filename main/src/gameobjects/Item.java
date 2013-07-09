package gameobjects;

import java.awt.Image;

public class Item extends Figure{

	public int info;
	public int anzahl=0;
	
	@Override public Image getPic(int direction){
		switch(direction){
			
			//mp-pot
			case 4:		return local.Pics.mp;
			
			//hp-pot
			case 5:		return local.Pics.hp;
			
			//blob
			case 6:		return local.Pics.blob;
			
			//fireball
			case 7:		return local.Pics.fireball;
			
			//flower
			case 8:		return local.Pics.flower;
			
			//bug
			default:	return local.Pics.bug;
		}
	}
	
	public Item() {
		super();
	}

}