package collision;

import main.Main;

public class Range {
	
	static int RANGE = 300;
	
	//returns difference between a and b
	static int diff(int a , int b){
		if(a < b) return b-a;
		else return a-b;
	}

	//returns square of range of 2 figures
	static int pyth_c(int[] tester , int[] subject){
		int x = diff(tester[2] , subject[2]);
		int y = diff(tester[3] , subject[3]);
		return x*x+y*y;											//x=a, y=b, a²+b²=c² (Pythagoras)
	}
	
	//returns the number of the closest player in range
	public static int range(int[] tester){
		int range_bunny = pyth_c(tester , Main.obj_list.get(2));
		
		if(Main.Nr_of_Players == 1){							//checks if single- or multiplayer game
			
			if(range_bunny < RANGE*RANGE) return 2;			//returns 2 if player in range, 0 else
			else return 0;
		}
		else{
			int range_hedgehog = pyth_c(tester , Main.obj_list.get(3));
			
			if(range_bunny <= range_hedgehog){					//returns 2 if player1 in range
				if(range_bunny < RANGE*RANGE) return 2;
			}
			
			else if(range_hedgehog < RANGE*RANGE) return 3;		//returns 3 if player2 in range
			
			else return 0;										//returns 0 if no player in range
		}
		return 0;												//useless statement.... , error if removed
	}
}
