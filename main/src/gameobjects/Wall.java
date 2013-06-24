package gameobjects;

import java.awt.Image;
import java.awt.Toolkit;

import local.Fs;
import main.Main;
import collision.Coll;

// wall class
public class Wall extends Figure {
	public Wall (int x, int y) {
		super(1,x,y,Toolkit.getDefaultToolkit().getImage(Fs.img_pfad+"wall"+"_"+String.valueOf(Main.level)+".png"));	//change tree to variable according to level
	}
	
	public Image getWallImage(){
		if(Coll.wall_opaque(pos_x , pos_y , image.getWidth(null) , image.getHeight(null)))		//*3 to get real height
		{
			return Toolkit.getDefaultToolkit().getImage(Fs.img_pfad+"tree"+"_opaque.png");	//change tree to variable according to level
		}
		else return this.image;
	}
}