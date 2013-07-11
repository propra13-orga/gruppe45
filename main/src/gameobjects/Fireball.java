package gameobjects;

import java.awt.Image;

public class Fireball extends Blob {

	@Override public Image getPic(int direction){
		switch(direction){
			case 1:		return local.Pics.fireball_u;
			case 2:		return local.Pics.fireball_r;
			case 3:		return local.Pics.fireball_d;
			default:	return local.Pics.fireball_l;
		}
	}
	
	public Fireball() {
		super();
	}
}
