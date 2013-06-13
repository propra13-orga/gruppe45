package collision;

import gameobjects.*;
import main.Main;

//Spielfeld.obj_list.get(0) = goal;
//Spielfeld.obj_list.get(1) = rabbit;
//Spielfeld.obj_list.get(2) = hedgehog;

public class Coll {
	
	//returns false if player hits exit, easy to run in while-loop
	public boolean goal(){
		if (   (Main.obj_list.get(1).pos_x < Main.obj_list.get(0).pos_x + Main.obj_list.get(0).image.getWidth(null))
			&& (Main.obj_list.get(1).pos_x + Main.obj_list.get(1).image.getWidth(null) > Main.obj_list.get(0).pos_x)
			&& (Main.obj_list.get(1).pos_y < Main.obj_list.get(0).pos_y + Main.obj_list.get(0).image.getHeight(null))
			&& (Main.obj_list.get(1).pos_y + Main.obj_list.get(1).image.getHeight(null) > Main.obj_list.get(0).pos_y) )
		{
			return false;
		}
		else return true;
	}
	
	//returns true for no collision, if false, invokes event if tester = killer / poisontree
	//1 for wall/tree, 2 for poisontree, 3 for fox, 4 for player
	public static boolean coll(Figure tester , int x , int y){
		switch (tester.type) {				//coll varies according to testers type
			case 3:							//
				for(int i=3; i < Main.obj_list.size(); i++){
					if (Main.obj_list.get(i).nr == tester.nr)
					{
						continue;
					}
					else if ( 	(tester.pos_x + x < Main.obj_list.get(i).pos_x + Main.obj_list.get(i).image.getWidth(null))
							 && (tester.pos_x + x + tester.image.getWidth(null) > Main.obj_list.get(i).pos_x)
							 && (tester.pos_y + y < Main.obj_list.get(i).pos_y + Main.obj_list.get(i).image.getHeight(null))
							 && (tester.pos_y + y +tester.image.getHeight(null) > Main.obj_list.get(i).pos_y) )
					{
						if(Main.obj_list.get(i).type == 4){
							//invoke event: player hit
						}
						
						return false;
					}
				}
				return true;

			default:
				for(int i=1; i < Main.obj_list.size(); i++){
					if (Main.obj_list.get(i).nr == tester.nr)
					{
						continue;
					}
					else if ( 	(tester.pos_x + x < Main.obj_list.get(i).pos_x + Main.obj_list.get(i).image.getWidth(null))
							 && (tester.pos_x + x + tester.image.getWidth(null) > Main.obj_list.get(i).pos_x)
							 && (tester.pos_y + y < Main.obj_list.get(i).pos_y + Main.obj_list.get(i).image.getHeight(null))
							 && (tester.pos_y + y + tester.image.getHeight(null) > Main.obj_list.get(i).pos_y) )
					{
						//invoke event: poisoned
						return false;
					}
				}
				return true;	
		}
			
	}

	//löst eigenständig Vergiftung aus, daher void
	public void poison(Poisonous_Tree tree){
		if (   (Main.obj_list.get(1).pos_x < tree.pos_x + tree.image.getWidth(null) + tree.fog)
			&& (Main.obj_list.get(1).pos_x + Main.obj_list.get(1).image.getWidth(null) > tree.pos_x - tree.fog)
			&& (Main.obj_list.get(1).pos_y < tree.pos_y + tree.image.getHeight(null) + tree.fog)
			&& (Main.obj_list.get(1).pos_y + Main.obj_list.get(1).image.getHeight(null) > tree.pos_y - tree.fog) )
		{
			//Event Vergiftung
		}
	}
	
	void deal_dmg(Figure dealer , Figure reciever){
		reciever.hp-=dealer.dmg;
	}
}
