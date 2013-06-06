package kollision;

import spielobjekte.*;

//Spielfeld.obj_list.get(0) = ziel
//Spielfeld.obj_list.get(1) = hase
//Spielfeld.obj_list.get(2) = igel

public class Koll {
	
	//returns false if player hits exit, easy to run in while-loop
	public boolean goal(){
		if (   (Spielfeld.obj_list.get(1).pos_x < Spielfeld.obj_list.get(0).pos_x + Spielfeld.obj_list.get(0).image.getWidth(null))
			&& (Spielfeld.obj_list.get(1).pos_x + Spielfeld.obj_list.get(1).image.getWidth(null) > Spielfeld.obj_list.get(0).pos_x)
			&& (Spielfeld.obj_list.get(1).pos_y < Spielfeld.obj_list.get(0).pos_y + Spielfeld.obj_list.get(0).image.getHeight(null))
			&& (Spielfeld.obj_list.get(1).pos_y + Spielfeld.obj_list.get(1).image.getHeight(null) > Spielfeld.obj_list.get(0).pos_y) )
		{
			return false;
		}
		else return true;
	}
	
	//returns true for no collision, if false, invokes event if tester = killer / poisontree
	//1 for wall/tree, 2 for poisontree, 3 for killerbunny, 4 for player
	public static boolean koll(Objekt tester){
		switch (tester.type) { //Kollision je nach Tester varierend
			case 3:
				for(int i=3; i < Spielfeld.obj_list.size(); i++){
					if (Spielfeld.obj_list.get(i).nr == tester.nr)
					{
						continue;
					}
					else if ( 	(tester.pos_x < Spielfeld.obj_list.get(i).pos_x + Spielfeld.obj_list.get(i).image.getWidth(null))
							 && (tester.pos_x + tester.image.getWidth(null) > Spielfeld.obj_list.get(i).pos_x)
							 && (tester.pos_y < Spielfeld.obj_list.get(i).pos_y + Spielfeld.obj_list.get(i).image.getHeight(null))
							 && (tester.pos_y + tester.image.getHeight(null) > Spielfeld.obj_list.get(i).pos_y) )
					{
						if(tester.type == 3){
							//Event auslösen	
						}
						
						return false;
					}
				}
				return true;

			default:
				for(int i=3; i < Spielfeld.obj_list.size(); i++){
					if (Spielfeld.obj_list.get(i).nr == tester.nr)
					{
						continue;
					}
					else if ( 	(tester.pos_x < Spielfeld.obj_list.get(i).pos_x + Spielfeld.obj_list.get(i).image.getWidth(null))
							 && (tester.pos_x + tester.image.getWidth(null) > Spielfeld.obj_list.get(i).pos_x)
							 && (tester.pos_y < Spielfeld.obj_list.get(i).pos_y + Spielfeld.obj_list.get(i).image.getHeight(null))
							 && (tester.pos_y + tester.image.getHeight(null) > Spielfeld.obj_list.get(i).pos_y) )
					{
						//Event auslösen,
						return false;
					}
				}
				return true;	
		}
			
	}

	//löst eigenständig Vergiftung aus, daher void
	public void poison(Todesbaum tree){
		if (   (Spielfeld.obj_list.get(1).pos_x < tree.pos_x + tree.image.getWidth(null) + tree.fog)
			&& (Spielfeld.obj_list.get(1).pos_x + Spielfeld.obj_list.get(1).image.getWidth(null) > tree.pos_x - tree.fog)
			&& (Spielfeld.obj_list.get(1).pos_y < tree.pos_y + tree.image.getHeight(null) + tree.fog)
			&& (Spielfeld.obj_list.get(1).pos_y + Spielfeld.obj_list.get(1).image.getHeight(null) > tree.pos_y - tree.fog) )
		{
			//Event Vergiftung
		}
	}
}
