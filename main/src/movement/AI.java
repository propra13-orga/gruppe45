package movement;

import main.Game;
import main.Main;
import collision.*;
import gameobjects.Figure;

public class AI {

	//uses move on all opponents
	public void move_all_opp(){
		for(int i = 3 ; i < Main.obj_list.size() ; i++){
			move(Main.obj_list.get(i));
		}
	}

	//moves an opponent
	public void move(Figure figure){
		
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
	void rand_move(Figure figure){
		
		int rand = Game.rand.nextInt(8);						//get random number between 0 and 7
		
		switch(rand){											//random movement
		case 0:	Move.up(figure);								//up
				break;
		
		case 1:	Move.up(figure);								//up + right
				Move.right(figure);
				break;
		
		case 2:	Move.right(figure);								//right
				break;
		
		case 3:	Move.right(figure);								//right + down
				Move.down(figure);
				break;
		
		case 4:	Move.down(figure);								//down
				break;
		
		case 5:	Move.down(figure);								//down + left
				Move.left(figure);
				break;
		
		case 6:	Move.left(figure);								//left
				break;
		
		case 7:	Move.left(figure);								//left + up
				Move.up(figure);
				break;
		
		}
	}
	
	//intelligent movement towards player
	void intel_move(Figure opp , Figure player){
		
	}

}