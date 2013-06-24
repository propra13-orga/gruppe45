package gameobjects;

import java.util.ArrayList;


public class Inventory {
	
	public ArrayList<Item> inventory = new ArrayList<Item>();
	
	public Inventory() {
		
	}
	
	public void add(Item item) {
		add (item, 1);
	}
	
	public void add (Item item, int anzahl){
		boolean new_item = true;
		Item i;
		for (Item ding: inventory) {
			if (ding.itemname == item.itemname){
				ding.anzahl += anzahl;
				new_item = false;
			}
		}
		if(new_item) {
			i = item.copy();
			i.anzahl=anzahl;
			this.inventory.add(i);
		}
	}

	public void remove (Item item){
		this.inventory.remove(item.copy());
	}

	public void used(Item item) {
		used(item,1);
	}

	public int used (Item item, int anzahl){
		int used;
		used=anzahl;
		if (item.anzahl-anzahl>0) {
			item.anzahl -= anzahl;
		} else {
			used=item.anzahl;
			this.remove(item);
		}
		return used;
	}


}