package gameobjects;

import java.awt.Image;


public class Methods {

	public static Image getImage_healthbar(Figure hero) {
		if(hero.hp>66) return local.Pics.healthbar_green;
	//	if(hero.hp>66) return Toolkit.getDefaultToolkit().getImage(Fs.img_pfad+"healthbar_green.png");
		if(hero.hp>33) return local.Pics.healthbar_yellow;
	//	if(hero.hp>33) return Toolkit.getDefaultToolkit().getImage(Fs.img_pfad+"healthbar_yellow.png");
		else return  local.Pics.healthbar_red;
	//	else return Toolkit.getDefaultToolkit().getImage(Fs.img_pfad+"healthbar_red.png");
	}

	public static int[] getPosition_Spell(Figure caster){
		
		int[] pos = new int[2];
		int SPELL = 50;
		
		switch(caster.direction){
		
			case 'u':
				pos[0] = caster.pos_x + caster.image.getWidth(null) / 2 - SPELL / 2;
				pos[1] = caster.pos_y - SPELL;
				break;
				
			case 'r':
				pos[0] = caster.pos_x + caster.image.getWidth(null);
				pos[1] = caster.pos_y + caster.image.getHeight(null) / 2 - SPELL / 2;
				break;
				
			case 'd':
				pos[0] = caster.pos_x + caster.image.getWidth(null) / 2 - SPELL / 2;
				pos[1] = caster.pos_y + caster.image.getHeight(null);
				break;
				
			case 'l':
				pos[0] = caster.pos_x - SPELL;
				pos[1] = caster.pos_y + caster.image.getHeight(null) / 2 - SPELL / 2;
				break;
		}
		
		return pos;
	}

}
