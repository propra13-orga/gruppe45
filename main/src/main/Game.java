package main;

import java.util.Random;
import collision.Coll;
import movement.*;

public class Game {

	public static boolean ingame = false;

	public static int Nr_of_Players = 1;
	public static int shop = 0;							//1 for player 1 in range, 2 for player 2, 3 for both, 0 else
	
	public static Random rand = new Random();

	public static void manageGame(){
		while(Coll.goal())
		{
			Keyboard KPlayer1 = new Keyboard();
			if(Game.Nr_of_Players == 2)
			{
				Keyboard KPlayer2 = new Keyboard();
			}
			AI.move_all_opp();
			Coll.shop();
		}
	}
	
}
