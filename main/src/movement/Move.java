package movement;

import main.Main;
import gameobjects.*;
import collision.Coll;

public class Move{ //moves any figure
	
	//move STEP pxl up
	public static void up(Figure figure , int STEP){
		if((figure.pos_y - STEP > Main.off) && Coll.coll(figure , 0 , -STEP)){
			figure.pos_y -= STEP;
			}
	}
	
	//move STEP pxl down
	public static void down(Figure figure , int STEP){
		if((figure.pos_y + figure.image.getHeight(null) + STEP < Main.board_height - Main.off) && Coll.coll(figure , 0 , STEP)){
			figure.pos_y += STEP;
		}
	}
	
	//move STEP pxl right
	public static void right(Figure figure , int STEP){
		if((figure.pos_x + figure.image.getWidth(null) + STEP < Main.board_width - Main.off) && Coll.coll(figure , STEP , 0)){
			figure.pos_x += STEP;
		}
	}
	
	//move STEP pxl left
	public static void left(Figure figure , int STEP){
		if((figure.pos_x - STEP > Main.off) && Coll.coll(figure , -STEP , 0)){
			figure.pos_x -= STEP;
		}
	}

}