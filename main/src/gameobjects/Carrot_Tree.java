package gameobjects;

import java.awt.Image;

public class Carrot_Tree extends Figure {

	@Override public Image getPic( int direction){
		return local.Pics.carrot;
	}
	
	public Carrot_Tree() {
		super();
	}
}
