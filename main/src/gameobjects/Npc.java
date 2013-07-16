package gameobjects;

import java.awt.Image;

public class Npc extends Figure{

	@Override public Image getPic(int direction){
		return local.Pics.sign;
	}

	/**
	 * Parameterless constructor
	 */
	public Npc(){
		super();
		this.height = local.Pics.sign.getHeight(null);
		this.width = local.Pics.sign.getWidth(null);
	}
}
