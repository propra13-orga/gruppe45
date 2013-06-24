package gameobjects;

import java.awt.Toolkit;

import local.Fs;

public class Npc extends Figure{

	public Npc(int x, int y){
		super(6,x,y,Toolkit.getDefaultToolkit().getImage(Fs.img_pfad + "sign.png"));
	}
}
