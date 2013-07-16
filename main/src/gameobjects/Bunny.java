package gameobjects;

import java.awt.Image;
import main.Main;


/**
 * Hero class
 * @author Andreas Roth
 * @author Martin Knonsalla
 *
 */
public class Bunny extends Figure {
	public Image lives_img = local.Pics.lives_img;

	public int lives = 2;
	public int mp = 100;
	public int hp = 100;
	public int ep = 0;
	
	public Inventory bag = new Inventory();
	
	public boolean defense = false;
	public boolean attack = false;
	public boolean spell = false;
	public boolean moving = false;
	public boolean book = false;
	
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

	/**
	 * Parameterless constructor
	 */
	public Bunny () {
		super();
		this.level = 1;
		this.bugs=2;
		this.dmg = 100;
		this.destroyable = true;
		this.height = local.Pics.bunny_l.getHeight(null);
		this.width = local.Pics.bunny_l.getWidth(null);
	}

	/**
	 * Getter for figures bug value
	 * @return <b>int</b> bug
	 */
	public int getBugs()
	{
		return this.bugs;
	}

	/**
	 * Setter for figures bug value
	 * @param bugs - added value
	 */
	public void setBugs(int bugs)
	{
		this.bugs = bugs;
	}

	/**
	 * Getter for figures mp value
	 * @return <b>int</b> mp
	 */
	public int getMp()
	{
		return this.mp;
	}

	/**
	 * Setter for figures mp value
	 * @param mp - added value
	 */
	public void setMp(int mp)
	{
		this.mp += mp;
	}

	public int getHp(){
		return this.hp;
	}

	/**
	 * Setter for figures hp value
	 * @param health - added value
	 */
	public void setHp(int health){
		Main.obj_list.get(2)[4] += health;
		if(Main.obj_list.get(2)[4] > 50 + this.level * 50) Main.obj_list.get(2)[4] = 50 + this.level * 50;
	}

	/**
	 * Uses hp pot
	 */
	public void use_hp_pot()
	{
		if(this.bag.getHp_pot()){
			this.setHp(10);
		}
	}

	/**
	 * Uses mp pot
	 */
	public void use_mp_pot()
	{
		if(this.bag.getMp_pot()){
			this.setMp(5);
		}
	}

	/**
	 * Adds a fireball to Main.obj_list
	 * @param direction - casters direction value
	 * @param x - new fireballs position on x-axis
	 * @param y - new fireballs position on y-axis
	 */
	public void cast_Fireball(int direction , int x , int y)
	{
		if( (this.getMp() >= 10) && (this.spell))
		{
			this.mp -= 10;
			Main.obj_list.add(this.getPosition_Spell(12,direction,x,y));
		}
	}

	/**
	 * Adds blob to Main.obj_list
	 * @param direction - casters direction value
	 * @param x - new blobs position on x-axis
	 * @param y - new blobs position on y-axis
	 */
	public void cast_Blob(int direction , int x , int y)
	{
		if(this.attack)
		{
			Main.obj_list.add(this.getPosition_Spell(11,direction,x,y));
		}
		
	}

	/**
	 * Calculates values of new spell
	 * @param type - type of spell created
	 * @param direction - direction in which spell is casted
	 * @param x - casters position on x-axis
	 * @param y - casters position on y-axis
	 * @return spell that is to be added to Main.obj_list
	 */
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