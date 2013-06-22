package gameobjects;

import java.awt.Image;
import java.awt.Toolkit;

import local.Fs;

public class Methods {

	public static Image getImage_healthbar(Figure hero) {
		if(hero.hp>66) return Toolkit.getDefaultToolkit().getImage(Fs.img_pfad+"healthbar_green.png");
		if(hero.hp>33) return Toolkit.getDefaultToolkit().getImage(Fs.img_pfad+"healthbar_yellow.png");
		else return Toolkit.getDefaultToolkit().getImage(Fs.img_pfad+"healthbar_red.png");
	}

	public static int[] getPosition_Spell(Figure caster){
		
		int[] pos = new int[2];
		
		switch(caster.direction){
		
			case 'u':
				pos[0] = caster.pos_x + caster.image.getHeight(null) / 2;
				pos[1] = caster.pos_y;
				break;
				
			case 'r':
				pos[0] = caster.pos_x + caster.image.getWidth(null);
				pos[1] = caster.pos_y + caster.image.getHeight(null) / 2;
				break;
				
			case 'd':
				pos[0] = caster.pos_x + caster.image.getWidth(null) / 2;
				pos[1] = caster.pos_y + caster.image.getHeight(null);
				break;
				
			case 'l':
				pos[0] = caster.pos_x;
				pos[1] = caster.pos_y + caster.image.getHeight(null) / 2;
				break;
		}
		
		return pos;
	}
}
