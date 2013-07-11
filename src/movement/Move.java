package movement;

import main.Main;
import collision.Coll;

public class Move{ //moves any figure
	
	//move STEP pxl up
	public static void up(int[] figure , int STEP){
		if((figure[3] - STEP > Main.off)/* && Coll.coll(figure , 0 , -STEP)*/){
			figure[3] -= STEP;
			}
	}
	
	//move STEP pxl down
	public static void down(int[] figure , int STEP){
		if((figure[3] + local.Create.gameobjects[figure[0]].height + STEP < Main.board_height - Main.off)/* && Coll.coll(figure , 0 , STEP)*/){
			figure[3] += STEP;
		}
	}
	
	//move STEP pxl right
	public static void right(int[] figure , int STEP){
		if((figure[2] + local.Create.gameobjects[figure[0]].width + STEP < Main.board_width - Main.off)/* && Coll.coll(figure , STEP , 0)*/){
			figure[2] += STEP;
		}
	}
	
	//move STEP pxl left
	public static void left(int[] figure , int STEP){
		if((figure[2] - STEP > Main.off)/* && Coll.coll(figure , -STEP , 0)*/){
			figure[2] -= STEP;
		}
	}

}