package movement;

import main.Main;
import collision.*;
import gameobjects.Figure;

public class AI {

	//uses move on all opponents
	public static void move_all_opp(){
		for(int i = 5 ; i < Main.obj_list.size() ; i++){
			if(Main.obj_list.get(i).type == 3) move(Main.obj_list.get(i));
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
		
		int rand = Main.rand.nextInt(9);						//get random number between 0 and 7
		
		switch(rand){											//random movement
		case 0:	Move.up(figure , 1);								//up
				break;
		
		case 1:	Move.up(figure , 1);								//up + right
				Move.right(figure , 1);
				break;
		
		case 2:	Move.right(figure , 1);								//right
				break;
		
		case 3:	Move.right(figure , 1);								//right + down
				Move.down(figure , 1);
				break;
		
		case 4:	Move.down(figure , 1);								//down
				break;
		
		case 5:	Move.down(figure , 1);								//down + left
				Move.left(figure , 1);
				break;
		
		case 6:	Move.left(figure , 1);								//left
				break;
		
		case 7:	Move.left(figure , 1);								//left + up
				Move.up(figure , 1);
				break;
		
		case 8:	break;												//too lazy to move
		}
	}
	
	//intelligent movement towards player
	static void intel_move(Figure opp , Figure player){
		
	}

}