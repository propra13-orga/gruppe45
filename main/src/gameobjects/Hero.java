package gameobjects;

import java.awt.Image;
import java.awt.Toolkit;
import local.Fs;

// hero class
public class Hero extends Figure {
	public Image lives_img;
//	public Image lives_img=Toolkit.getDefaultToolkit().getImage(Fs.img_pfad+"lives_bunny.png");
	public boolean defense = false;
	public boolean attack = false;
	public boolean spell = false;

	public Hero (int x, int y, String player) {
		super(4,x,y,Toolkit.getDefaultToolkit().getImage(Fs.img_pfad+player+"_l.png"));
		this.start_x=x;
		this.start_y=y;
		this.level = 1;
		this.hp = 50 + 50 * this.level;
		this.bugs=2;
		this.mp = 100;
		this.lives = 2;
		this.dmg = 100;
		this.destroyable = true;
		this.bag = new Inventory();
		this.lives_img = local.Pics.lives_img;
	}

	public int getHp()
	{
		return this.hp;
	}

	public void setHp(int hp)
	{
		if(hp > 50 + 50 * this.level) hp = 50 + 50 * this.level;
		this.hp = hp;
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

	public int getLives()
	{
		return this.lives;
	}

	public void setLives(int lives)
	{
		this.lives = lives;
	}

	public void cast_Spell()
	{
		int[] pos = Methods.getPosition_Spell(this);
		
		if( ((this.mp -= 10) >= 0) && (this.spell))
		{
			@SuppressWarnings("unused")
			Spell fireball = new Spell(	pos[0],
										pos[1],
										Toolkit.getDefaultToolkit().getImage(Fs.img_pfad +"fireball_" + this.direction + ".png"),
										this.direction,
										this.level * this.dmg );
		}
		else if(this.attack)
		{
			this.mp += 10;
			@SuppressWarnings("unused")
			Spell blob = new Spell(	pos[0],
										pos[1],
										local.Pics.blob,
									//	Toolkit.getDefaultToolkit().getImage(Fs.img_pfad +"blob.png"),
										this.direction,
										(int) this.dmg / 2 );
		}
		
	}

	public void use_hp_pot()
	{
		
	}

	public void use_mp_pot()
	{
		
	}

}