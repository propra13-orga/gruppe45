package gameobjects;

import java.awt.Image;

public class Npc extends Figure{

	@Override public Image getPic(int direction){
		return local.Pics.sign;
	}

	public Npc(){
		super();
	}
}
