package kollision;

import spielobjekte.Objekt;

public class Koll {
	
	//returns false if player hits exit, easy to run in while-loop
	public boolean ziel(){
		if ( (hase.pos_x < ziel.pos_x+ziel.image.getWidth(null)) && (hase.pos_x+hase.image.getWidth(null) > ziel_pos_x) && (hase.pos_y < ziel.pos_y+ziel.image.getHeight(null)) && (hase.pos_y+hase.image.getHeight(null) > ziel.pos_y) ){
			return false;
		}
		else return true;
	}
	
	//returns 0 for no collision, 1 for wall/tree, 2 for poisontree, 3 for killerbunny, 4 for player
	public Boolean koll(Objekt tester){
		for(int i; i < Main.obj_list.length; i++){
			if (Main.obj_list[i].nr == tester.nr) continue;
			else if ( (tester.pos_x < Main.obj_array[i].pos_x+Main.obj_array[i].image.getWidth(null)) && (tester.pos_x+tester.image.getWidth(null) > Main.obj_array[i].pos_x) && (tester.pos_y < Main.obj_array[i].pos_y+Main.obj_array[i].image.getHeight(null)) && (tester.pos_y+tester.image.getHeight(null) > Main.obj_array[i].pos_y) ){
				Main.obj_array[i].type
				return true;
			}
		//}
		else return false;		
	}
	
}
