package gameobjects;

import java.awt.Image;

// hero class
public class Hedgehog extends Bunny {

	@Override public Image getPic(int direction){
		if(direction == 2)
		{
//			if(this.moving) return local.Pics.hedgehog_r_motion;
			/*else*/ return local.Pics.hedgehog_r;
		}
//		else if(this.moving) return local.Pics.hedgehog_l_motion;
			 else return local.Pics.hedgehog_l;
	}

	public Hedgehog () {
		super();
		this.level = 1;
		this.bugs=2;
		this.dmg = 100;
		this.destroyable = true;
		this.height = this.getPic(2).getHeight(null);
		this.width = this.getPic(2).getWidth(null);
		this.lives_img = local.Pics.lives_img;
	}

}