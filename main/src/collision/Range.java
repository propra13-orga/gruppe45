package collision;

import main.Main;
import gameobjects.Figure;

public class Range {
	
	static int RANGE = 300;
	
	//returns difference between a and b
	static int diff(int a , int b){
		if(a < b) return b-a;
		else return a-b;
	}

	//returns square of range of 2 figures
	static int pyth_c(Figure subj1 , Figure subj2){
		int x = diff(subj1.pos_x , subj2.pos_x);
		int y = diff(subj1.pos_y , subj2.pos_y);
		return x*x+y*y;											//x=a, y=b, a²+b²=c² (Pythagoras)
	}
	
	//returns the number of the closest player in range
	public static int range(Figure tester){
		int range_rabbit = pyth_c(tester , Main.obj_list.get(2));
		
		if(Main.Nr_of_Players == 1){							//checks if single- or multiplayer game
			
			if(range_rabbit < RANGE*RANGE) return 2;			//returns 1 if player in range, 0 else
			else return 0;
		}
		else{
			int range_hedgehog = pyth_c(tester , Main.obj_list.get(3));
			
			if(range_rabbit <= range_hedgehog){					//returns 1 if player1 in range
				if(range_rabbit < RANGE*RANGE) return 2;
			}
			
			else if(range_hedgehog < RANGE*RANGE) return 3;		//returns 2 if player2 in range
			
			else return 0;										//returns 0 if no player in range
		}
		return 0;												//useless statement.... , error if removed
	}
}
