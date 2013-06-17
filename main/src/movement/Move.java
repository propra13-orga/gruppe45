package movement;

import main.Main;
import gameobjects.*;
import collision.Coll;

public class Move{ //moves any figure
	
	//move 1 pxl up
	public static void up(Figure figure){
		if((figure.pos_y + 1 < Main.board_width) && Coll.coll(figure , 0 , 1)){
			figure.pos_y += 1;
			}
	}
	
	//move 1 pxl down
	public static void down(Figure figure){
		if((figure.pos_y - 1 > 0) && Coll.coll(figure , 0 , -1)){
			figure.pos_y -= 1;
		}
	}
	
	//move 1 pxl right
	public static void right(Figure figure){
		if((figure.pos_x + 1 < Main.board_height) && Coll.coll(figure , 1 , 0)){
			figure.pos_x += 1;
		}
	}
	
	//move 1 pxl left
	public static void left(Figure figure){
		if((figure.pos_x - 1 > 0) && Coll.coll(figure , -1 , 0)){
			figure.pos_x -= 1;
		}
	}

}