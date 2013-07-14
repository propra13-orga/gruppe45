package gameobjects;

import java.awt.Image;

public class Carrot_Tree extends Figure {

	@Override public Image getPic( int direction){
		return local.Pics.carrot;
	}
	
	public Carrot_Tree() {
		super();
		this.height = local.Pics.carrot.getHeight(null);
		this.width = local.Pics.carrot.getWidth(null);
	}
}
