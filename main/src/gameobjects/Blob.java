package gameobjects;

import java.awt.Image;

public class Blob extends Figure {

	@Override public Image getPic(int direction){
		return local.Pics.blob;
	}

	public Blob() {
		super();
		this.height = 50;
		this.width = 50;
		
	}
}
