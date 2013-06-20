package main;

import java.util.Random;
import collision.Coll;
import movement.*;

public class Game
{

	public boolean ingame = true;				//ingame == false while in menu
	public boolean run = false;					//movements are made when run == true, then run ist set to false until painted

	public int Nr_of_Players = 1;				//1 == Singleplayer , 2 == Multiplayer
	public int raum = 1;
	public int level = 1;
	public int shop = 0;							//1 for player 1 in range, 2 for player 2, 3 for both, 0 else
	
	public Random rand = new Random();
	
	Keyboard KPlayer1 = new Keyboard();
	Main.renderFrame.setFocusable(true);
	Main.renderFrame.addKeyListener(KPlayer1);
		
	if(Game.Nr_of_Players == 2)
	{
		Keyboard2 KPlayer2 = new Keyboard2();
		Main.renderFrame.addKeyListener(KPlayer2);
	}

	while(true)
	{
		while(Coll.goal())
		{
			if(run)
			{
				AI.move_all_opp();
				Coll.shop();
				Coll.poison();
			}
		}
		raum += 1;
		
	}
}
