package movement;

import local.Create;
import main.Main;
import collision.*;

/**
 * Observes and controls movement of all enemies
 * @author Andreas Roth
 */
public class AI {

	static int STEP = 5;

	/**
	 * Invokes random movement for all foxes and bosses
	 */
	public static void move_all_opp(){
		for(int i = 4 ; i < Main.obj_list.size() ; i++){
			if(Main.obj_list.get(i)[0] == 6 || Main.obj_list.get(i)[0] == 7) rand_move(Main.obj_list.get(i));
		}
	}

	/**
	 * Moves an enemy in one direction until it collides with anything, then randomly chooses another
	 * @param figure enemy moved
	 */
	static void rand_move(int[] figure){
		if(rand_check(figure))
		{
			int rand = Main.rand.nextInt(4);						//get random number between 0 and 7

			switch(rand){											//random movement
				case 0:	figure[5] = 1;
						Move.up(figure , STEP);								//up
						break;

				case 1:	figure[5] = 2;
						Move.right(figure , STEP);								//right
						break;

				case 2:	figure[5] = 3;
						Move.down(figure , STEP);								//down
						break;

				case 3:	figure[5] = 4;
						Move.left(figure , STEP);								//left
						break;
			}
		}
	}

	/**
	 * Tests if an enemy can move in its direction or if it collides with anything
	 * @param figure testing enemy
	 * @return <b>false</b> if movement is possible, <b>true</b> else
	 */
	static boolean rand_check(int[] figure){

		switch(figure[5]){

			case 1:
				if(figure[3] < Main.off + STEP * 2) return true;
				else if(Coll.coll(figure, 0, -STEP))
				{
					Move.up(figure, STEP);
					return false;
				}
				break;

			case 3:
				if(figure[3] + Create.gameobjects[figure[0]].height > Main.board_height - Main.off - STEP * 2) return true;
				else if(Coll.coll(figure, 0, STEP))
				{
					Move.down(figure, STEP);
					return false;
				}
				break;

			case 4:
				if(figure[2] < Main.off + STEP * 2) return true;
				else if(Coll.coll(figure, -STEP, 0))
				{
					Move.left(figure, STEP);
					return false;
				}
				break;

			case 2:
				if(figure[2] + Create.gameobjects[figure[0]].width > Main.board_width - Main.off - STEP * 2) return true;
				else if(Coll.coll(figure, STEP, 0))
				{
					Move.right(figure, STEP);
					return false;
				}
				break;

			default: break;
		}

		return true;
		}
}