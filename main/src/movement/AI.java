package movement;

import main.Main;
import collision.*;
import gameobjects.Figure;

public class AI {

	static int STEP = 5;

	//uses move on all opponents
	public static void move_all_opp(){
		for(int i = 5 ; i < Main.obj_list.size() ; i++){
			if(Main.obj_list.get(i).type == 3 || Main.obj_list.get(i).type == 5) move(Main.obj_list.get(i));
		}
	}

	//moves an opponent
	public static void move(Figure figure){
		
		int closest_player = Range.range(figure);					//index(obj_list) of closest player
		
		if(closest_player == 0)										//no players in range
		{
			rand_move(figure);
		}
		
		else if(closest_player == 1)								//player 1 is closest in range
		{							
			intel_move(figure , Main.obj_list.get(2));
		}
		
		else														//player 2 is closest in range
		{
			intel_move(figure , Main.obj_list.get(3));
		}
	}

	//random movement
	static void rand_move(Figure figure){
		if(rand_check(figure))
		{
			int rand = Main.rand.nextInt(4);						//get random number between 0 and 7

			switch(rand){											//random movement
				case 0:	figure.direction = 'u';
						Move.up(figure , STEP);								//up
						break;

				case 1:	figure.direction = 'r';
						Move.right(figure , STEP);								//right
						break;

				case 2:	figure.direction = 'd';
						Move.down(figure , STEP);								//down
						break;

				case 3:	figure.direction = 'l';
						Move.left(figure , STEP);								//left
						break;
			}
		}
	}

	//checkes if movment in "direction" is possible, if not, returns true
	static boolean rand_check(Figure figure){

		switch(figure.direction){

			case 'u':
				if(figure.pos_y < Main.off + STEP * 2) return true;
				else if(Coll.coll(figure, 0, -STEP))
				{
					Move.up(figure, STEP);
					return false;
				}
				break;

				case 'd':
					if((figure.pos_y + figure.image.getHeight(null)) > Main.board_height - Main.off - STEP * 2) return true;
					else if(Coll.coll(figure, 0, STEP))
					{
						Move.down(figure, STEP);
						return false;
					}
					break;

				case 'l':
					if(figure.pos_x < Main.off + STEP * 2) return true;
					else if(Coll.coll(figure, -STEP, 0))
					{
						Move.left(figure, STEP);
						return false;
					}
					break;

				case 'r':
					if((figure.pos_x + figure.image.getWidth(null)) > Main.board_width - Main.off - STEP * 2) return true;
					else if(Coll.coll(figure, STEP, 0))
					{
						Move.right(figure, STEP);
						return false;
					}
					break;
			}
			return true;
		}

	//intelligent movement towards player
	static void intel_move(Figure opp , Figure player){
		
	}

}