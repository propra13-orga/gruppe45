package gameobjects;

import java.awt.Image;
import java.awt.Toolkit;

import local.Fs;
import collision.Coll;

// wall class
public class Wall extends Figure {
	public Wall (int x, int y) {
		super(1,x,y,Toolkit.getDefaultToolkit().getImage(Fs.img_pfad+"wall"+"_"+String.valueOf(1)+".png"));	//change tree to variable according to level
	}
	
	public Image getWallImage(){
		if(Coll.wall_opaque(this.pos_x , this.pos_y , this.width , this.height * 3))		//*3 to get real height
		{
			return Toolkit.getDefaultToolkit().getImage(Fs.img_pfad+"tree"+"_opaque.png");	//change tree to variable according to level
		}
		else return this.image;
	}
}