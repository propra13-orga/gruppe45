package movement;

import main.Main;
import gameobjects.*;
import collision.Coll;

public class Move{ //moves any figure
	
	static int STEP = 5;
	
	//move STEP pxl up
	public static void up(Figure figure){
		if((figure.pos_y - STEP > 0)/* && Coll.coll(figure , 0 , -STEP)*/){
			figure.pos_y -= STEP;
			}
	}
	
	//move STEP pxl down
	public static void down(Figure figure){
		if((figure.pos_y + figure.height + STEP + 125 < Main.board_height)/* && Coll.coll(figure , 0 , STEP)*/){
			figure.pos_y += STEP;
		}
	}
	
	//move STEP pxl right
	public static void right(Figure figure){
		if((figure.pos_x + figure.width + STEP + 50 < Main.board_width)/* && Coll.coll(figure , STEP , 0)*/){
			figure.pos_x += STEP;
		}
	}
	
	//move STEP pxl left
	public static void left(Figure figure){
		if((figure.pos_x - STEP > 0)/* && Coll.coll(figure , -STEP , 0)*/){
			figure.pos_x -= STEP;
		}
	}

}