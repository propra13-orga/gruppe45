package gameobjects;

import java.awt.Image;
import main.Main;

// hero class
public class Bunny extends Figure {
	public Image lives_img = local.Pics.lives_img;
//	public Image lives_img=Toolkit.getDefaultToolkit().getImage(Fs.img_pfad+"lives_bunny.png");

	public int lives = 2;
	public int mp = 100;
	public int hp = 100;
	public int ep = 0;
	
	public Inventory bag = new Inventory();
	
	public boolean defense = false;
	public boolean attack = false;
	public boolean spell = false;
	public boolean moving = false;
	
	@Override public Image getPic(int direction){
		if(direction == 1 || direction == 2)
		{
			if(this.moving) return local.Pics.bunny_r_motion;
			else return local.Pics.bunny_r;
		}
		else if(this.moving) return local.Pics.bunny_l_motion;
			 else return local.Pics.bunny_l;
	}

	@Override public int getDmg(){
		return this.dmg * this.level;
	}

	public Bunny () {
		super();
		this.level = 1;
		this.bugs=2;
		this.dmg = 100;
		this.destroyable = true;
		this.height = local.Pics.bunny_l.getHeight(null);
		this.width = local.Pics.bunny_l.getWidth(null);
	}

	public int getBugs()
	{
		return this.bugs;
	}

	public void setBugs(int bugs)
	{
		this.bugs = bugs;
	}

	public int getMp()
	{
		return this.mp;
	}

	public void setMp(int mp)
	{
		this.mp = mp;
	}

	public int getHp(){
		return Main.obj_list.get(2)[4];
	}

	public void setHp(int hp){
		Main.obj_list.get(2)[4] += hp;
	}

	public void use_hp_pot()
	{
		if(this.bag.getHp_pot()){
			this.setHp(10);
		}
	}

	public void use_mp_pot()
	{
		if(this.bag.getMp_pot()){
			this.setMp(5);
		}
	}

	public void cast_Spell(int direction , int x , int y)
	{
		if( (this.getMp() >= 10) && (this.spell))
		{
			this.mp -= 10;
			Main.obj_list.add(this.getPosition_Spell(12,direction,x,y));
		}
		else if(this.attack)
		{
			Main.obj_list.add(this.getPosition_Spell(11,direction,x,y));
		}
		
	}
	

	int[] getPosition_Spell(int type , int direction , int x , int y){
		
		int[] pos = new int[6];
		int SPELL = 50;
		
		pos[0] = type;
		pos[1] = Main.obj_list.size();
		
		switch(direction){
		
			case 1:
				pos[2] = x + this.width / 2 - SPELL / 2;
				pos[3] = y - SPELL;
				break;
				
			case 2:
				pos[2] = x + this.width;
				pos[3] = y + this.height / 2 - SPELL / 2;
				break;
				
			case 3:
				pos[2] = x + this.width / 2 - SPELL / 2;
				pos[3] = y + this.height;
				break;
				
			case 4:
				pos[2] = x - SPELL;
				pos[3] = y + this.height / 2 - SPELL / 2;
				break;
		}
		
		if(type == 12) pos[4] = this.getDmg();
		else pos[4] = (int) this.getDmg() /2;
		pos[5] = direction;
		
		return pos;
	}

}