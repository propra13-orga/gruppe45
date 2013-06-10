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
			int rand = Game.rand.nextInt(4);
			
			switch(rand){											//random movement
			case 0:	Move.up(figure);
					break;
			
			case 1:	Move.down(figure);
					break;
			
			case 2:	Move.left(figure);
					break;
			
			case 3:	Move.right(figure);
					break;
			
			}
		}
		else if(closest_player == 1)								//player 1 is closest in range
		{							
			
		}
		else														//player 2 is closest in range
		{
			
		}
	}
}
