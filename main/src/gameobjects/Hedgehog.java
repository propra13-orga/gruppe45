package gameobjects;

import java.awt.Image;

import main.Main;

/**
 * Secondary hero
 * @author Andreas Roth
 *
 */
public class Hedgehog extends Bunny {
	public Image lives_img = local.Pics.lives_img;
//	public Image lives_img=Toolkit.getDefaultToolkit().getImage(Fs.img_pfad+"lives_bunny.png");

	@Override public Image getPic(int direction){
		if(direction == 2)
		{
//			if(this.moving) return local.Pics.hedgehog_r_motion;
			/*else*/ return local.Pics.hedgehog_r;
		}
//		else if(this.moving) return local.Pics.hedgehog_l_motion;
			 else return local.Pics.hedgehog_l;
	}

	/**
	 * Parameterless constructor
	 */
	public Hedgehog () {
		super();
		this.level = 1;
		this.bugs=2;
		this.dmg = 100;
		this.destroyable = true;
		this.height = local.Pics.hedgehog_r.getHeight(null);
		this.width = local.Pics.hedgehog_r.getWidth(null);
		this.lives_img = local.Pics.lives_img;
	}

	public void setHp(int health){
		Main.obj_list.get(3)[4] += health;
		if(Main.obj_list.get(3)[4] > 50 + this.level * 50) Main.obj_list.get(3)[4] = 50 + this.level * 50;
	}
}