package gameobjects;

import local.Create;
import main.Main;

/**
 * Inventory carried by heroes
 * @author Andreas Roth
 * @author Martin Knonsalla
 *
 */
public class Inventory {
	
	public int[] inventory = {0,0};
	
	/**
	 * Parameterless constructor
	 */
	public Inventory() {
		
	}
	
	/**
	 * Includes an item to heroes inventory
	 * @param item - added item
	 */
	public void add(int[] item) {
		switch(item[5]){
		
			//snail
			case 10:	for(int i = 4 ; i < Main.obj_list.size() ;i++)
					{
							if(Main.obj_list.get(i)[0] == 13)
							{
								Main.obj_list.remove(i);
								i--;
							}
							Main.obj_list.get(i)[1] = i;
					}
			
			//mp-pot
			case 4:	this.inventory[0] = add(1,this.inventory[0]);
					break;
			
			//hp-pot
			case 5:	this.inventory[1] = add(1,this.inventory[1]);
					break;
			
			//blob
			case 6:	Create.hero1.attack = true;
					Create.hero2.attack = true;
					break;
			
			//fireball
			case 7:	Create.hero1.spell = true;
					Create.hero2.spell = true;
					break;
			
			//flower
			case 8:	Create.hero1.defense = true;
					Create.hero1.defe = 2;
					Create.hero2.defense = true;
					Create.hero2.defe = 2;
					break;
			
			//bug
			case 9:	Create.hero1.bugs += 1;
					break;
					
			//book
			case 11:Create.hero1.book = true;
		}
	}
	
	/**
	 * Includes an item to heroes inventory
	 * @param anzahl - amount included
	 * @param pos - current amount
	 * @return new amount
	 */
	public int add (int anzahl, int pos){
		return pos += anzahl;
	}

	/**
	 * Checks if mp pot is available
	 * @return <b>true</b> if pot is available, <b>false</b> else
	 */
	public boolean getMp_pot(){
		if(this.inventory[0] > 0){
			this.inventory[0] -= 1;
			return true;
		}
		else return false;
	}
	
	/**
	 * Checks if hp pot is available
	 * @return <b>true</b> if pot is available, <b>false</b> else
	 */
	public boolean getHp_pot(){
		if(this.inventory[1] > 0){
			this.inventory[1] -=1;
			return true;
		}
		else return false;
	}

}