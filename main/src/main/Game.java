package main;

import java.util.Random;
import collision.Coll;
import movement.*;

public class Game {

	public boolean ingame = true;					//ingame == false while in menu
	public boolean run = false;						//movements are made when run == true, then run ist set to false until painted

	public int Nr_of_Players = 1;					//1 == Singleplayer , 2 == Multiplayer
	public int room = 1;
	public int level =1;
	public int shop = 0;							//1 for player 1 in range, 2 for player 2, 3 for both, 0 else
	
	public Random rand = new Random();				//variable for random movement

	//creates keyboards and adds keylistener
/*	void setKeyboard(){
		Keyboard KPlayer1 = new Keyboard();
		Main.renderFrame.setFocusable(true);
		Main.renderFrame.addKeyListener(KPlayer1);
		
		if(Nr_of_Players == 2)
		{
			Keyboard2 KPlayer2 = new Keyboard2();
			Main.renderFrame.addKeyListener(KPlayer2);
		}
	}
*/	
	
	//manages the game
	void manage(){
		
		while(true)
		{
			//while not hitting goal
			while(Coll.goal())
			{
				//if game is running
				if(ingame)
				{
					AI.move_all_opp();
					Coll.shop();
					Coll.poison();
				}
			}
			
/*			if((room += 1) > 3)
			{
				room = 1;
				if((level += 1) > 3)
				{
					System.exit(0);
				}
				
			}*/
			
			System.exit(0);
			
		}
	}
}
