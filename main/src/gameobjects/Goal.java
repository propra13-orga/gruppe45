package gameobjects;

import java.awt.Image;
import java.awt.Toolkit;
import local.Fs;
import main.Main;

// destination class (exit of a level)
public class Goal extends Figure {
	public Goal (int x, int y) {
		super(0,x,y,Toolkit.getDefaultToolkit().getImage(Fs.img_pfad+"goal_" + Main.level + "_" + Main.room + ".png"));
	}
	
	Image get_goal_image(int lvl, int room) {
		String dat_name;
		dat_name ="goal_" + Main.level + "_" + Main.room + ".png";
		return Toolkit.getDefaultToolkit().getImage(Fs.img_pfad+dat_name);
		
	}
}