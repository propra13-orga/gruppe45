package main;

import java.util.Random;
import collision.Coll;
import movement.*;

public class Game {

	public static boolean ingame = true;				//ingame == false while in menu
	public static boolean run = false;					//movements are made when run == true, then run ist set to false until painted

	public static int Nr_of_Players = 1;				//1 == Singleplayer , 2 == Multiplayer
	public static int shop = 0;							//1 for player 1 in range, 2 for player 2, 3 for both, 0 else
	
	public static Random rand = new Random();

	public void manageGame(){
		
		Keyboard KPlayer1 = new Keyboard();
		Main.renderFrame.setFocusable(true);
		Main.renderFrame.addKeyListener(KPlayer1);
		
		if(Game.Nr_of_Players == 2)
		{
			Keyboard2 KPlayer2 = new Keyboard2();
			Main.renderFrame.addKeyListener(KPlayer2);
		}
		
		while(Coll.goal())
		{
			if(run)
			{
				AI.move_all_opp();
				Coll.shop();
				Coll.poison();
			}
		}
	}
	
}
