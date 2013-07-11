package gameobjects;

/*
* class creates the board with background image and all objects on the board with their positions
* hero, killerbunny, wall and todesbaum inherit from the objekt
* class in this package
*
* created in Renderer class
* reset for a new game in Renderer class
* collision detection in calc class
*
*/

import java.awt.Image;
import java.awt.Toolkit;

import local.Fs;
import main.*;

public class Board extends Figure{

	@Override public Image getPic(int direction){
		return Toolkit.getDefaultToolkit().getImage(Fs.img_pfad + "bg_" + String.valueOf(Main.level) + "_" + String.valueOf(Main.room) + ".png");
	}

	public Board(){
		super();
	}

}