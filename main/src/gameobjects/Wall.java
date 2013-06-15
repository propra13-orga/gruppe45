package gameobjects;

import java.awt.Toolkit;

import local.Fs;

// wall class
public class Wall extends Figure {
	public Wall (int x, int y) {
		super(1,x,y,Toolkit.getDefaultToolkit().getImage(Fs.img_pfad+"baum_eng.png"));
	}
}