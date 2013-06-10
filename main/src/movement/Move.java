package movement;

import gameobjects.*;
import collision.Coll;

public class Move{ //moves any figure
	
	static int height = 1024;
	static int width = 768;
	
	//move 1 pxl up
	public static void up(Figure figure){
		if((figure.pos_y + 1 < width) && Coll.coll(figure , 0 , 1)){
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
		if((figure.pos_x + 1 < height) && Coll.coll(figure , 1 , 0)){
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