package movement;

import main.Main;
import collision.Coll;

/**
 * Contains movement methods
 * @author Andreas Roth
 *
 */
public class Move{
	
	public static boolean[] keys = new boolean[]{false,false,false,false,false,false,false,false};
	
	/**
	 * Moves a figure upwards
	 * @param figure - moved figure
	 * @param STEP - distance
	 */
	public static void up(int[] figure , int STEP){
		if((figure[3] - STEP > Main.off) && Coll.coll(figure , 0 , -STEP)){
			figure[3] -= STEP;
			}
	}
	
	/**
	 * Moves a figure downwards
	 * @param figure  - moved figure
	 * @param STEP  - distance
	 */
	public static void down(int[] figure , int STEP){
		if((figure[3] + local.Create.gameobjects[figure[0]].height + STEP < Main.board_height - Main.off) && Coll.coll(figure , 0 , STEP)){
			figure[3] += STEP;
		}
	}
	
	/**
	 * Moves a figure to the right
	 * @param figure - moved figure
	 * @param STEP - distance
	 */
	public static void right(int[] figure , int STEP){
		if((figure[2] + local.Create.gameobjects[figure[0]].width + STEP < Main.board_width - Main.off) && Coll.coll(figure , STEP , 0)){
			figure[2] += STEP;
		}
	}
	
	/**
	 * Moves a figure to the left
	 * @param figure - moved figure
	 * @param STEP - distance
	 */
	public static void left(int[] figure , int STEP){
		if((figure[2] - STEP > Main.off) && Coll.coll(figure , -STEP , 0)){
			figure[2] -= STEP;
		}
	}

}