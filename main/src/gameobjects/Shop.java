package gameobjects;

import java.awt.Toolkit;

import local.Fs;

public class Shop  extends Figure{
	public Shop (int x, int y) {
		super(0,x,y,Toolkit.getDefaultToolkit().getImage(Fs.img_pfad+"shop.png"));
	}
}
