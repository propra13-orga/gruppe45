package movement;

import local.Create;
import main.Main;
import collision.*;

public class AI {

	static int STEP = 5;

	//uses move on all opponents
	public static void move_all_opp(){
		for(int i = 5 ; i < Main.obj_list.size() ; i++){
			if(Main.obj_list.get(i)[0] == 6 || Main.obj_list.get(i)[0] == 7) move(Main.obj_list.get(i));
		}
	}

	//moves an opponent
	public static void move(int[] figure){
		
//		int closest_player = Range.range(figure);					//index(obj_list) of closest player
//		
//		if(closest_player == 0)										//no players in range
//		{
			rand_move(figure);
//		}
//		
//		if(closest_player == 1)								//player 1 is closest in range
//		{							
//			intel_move(figure , Main.obj_list.get(2));
//		}
//		
//		if(closest_player == 2)								//player 2 is closest in range
//		{
//			intel_move(figure , Main.obj_list.get(3));
//		}
	}

	//random movement
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

	//checkes if movment in "direction" is possible, if not, returns true
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

	//intelligent movement towards player
	static void intel_move(int[] figure , int[] subject){
		
	}

}