package gameobjects;

import java.awt.Toolkit;

import local.Fs;
import main.Main;

// destination class (exit of a level)
public class Goal extends Figure {
	public Goal (int x, int y) {
		super(0,x,y,Toolkit.getDefaultToolkit().getImage(Fs.img_pfad+"goal_" + Main.level + "_" + Main.room + ".png"));
	}
}