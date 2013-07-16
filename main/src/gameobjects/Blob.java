package gameobjects;

import java.awt.Image;

/**
 * Main spell
 * @author Andreas Roth
 */
public class Blob extends Figure {

	@Override public Image getPic(int direction){
		return local.Pics.blob;
	}

	/**
	 * Parameterless constructor
	 */
	public Blob() {
		super();
		this.height = 50;
		this.width = 50;
		
	}
}
