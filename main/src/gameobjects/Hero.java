package gameobjects;

import java.awt.Image;
import java.awt.Toolkit;

import local.Fs;

// hero class
public class Hero extends Figure {
    public Image lives_img=Toolkit.getDefaultToolkit().getImage(Fs.img_pfad+"lives_bunny.png");
	
	public Hero (int x, int y, String player) {
		super(4,x,y,Toolkit.getDefaultToolkit().getImage(Fs.img_pfad+player+"_l.png"));
		this.start_x=x;
		this.start_y=y;
		this.level = 1;
		this.hp = 555;
		this.bugs=2;
		this.mp = 100;
		this.lives = 2;
		this.dmg = 100;
		this.destroyable = true;
		this.bag = new Inventory();
	}
	public int getHp()
	{
		return this.hp;
	}
	public void setHp(int hp)
	{
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
}