package gameobjects;

import local.Create;


public class Inventory {
	
	public int[] inventory = {0,0};
	
	public Inventory() {
		
	}
	
	public void add(int[] item) {
		switch(item[5]){
		
			//mp-pot
			case 4:	add(1,this.inventory[0]);
					break;
			
			//hp-pot
			case 5:	add(1,this.inventory[1]);
					break;
			
			//blob
			case 6:	Create.hero1.attack = true;
					break;
			
			//fireball
			case 7:	Create.hero1.spell = true;
					break;
			
			//flower
			case 8:	Create.hero1.defense = true;
					Create.hero1.defe = 2;
					break;
			
			//bug
			case 9:	Create.hero1.bugs += 1;
					break;
		}
	}
	
	public void add (int anzahl, int pos){
		pos += anzahl;
	}

	public boolean getMp_pot(){
		if(this.inventory[0] > 0){
			this.inventory[0] -= 1;
			return true;
		}
		else return false;
	}
	
	public boolean getHp_pot(){
		if(this.inventory[1] > 0){
			this.inventory[1] -=1;
			return true;
		}
		else return false;
	}

}