package gameobjects;

import java.awt.Image;
import java.awt.Toolkit;

import local.Fs;
import main.*;

/**
 * Only used to hold the background image
 * @author Andreas Roth
 *
 */
public class Board extends Figure{

	@Override public Image getPic(int direction){
		return Toolkit.getDefaultToolkit().getImage(Fs.img_pfad + "bg_" + String.valueOf(Main.level) + "_" + String.valueOf(Main.room) + ".png");
	}

	/**
	 * Parameterless constructor
	 */
	public Board(){
		super();
	}

}