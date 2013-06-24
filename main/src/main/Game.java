package main;

import gameobjects.Create;
import collision.Coll;
import movement.*;

public class Game extends Thread{

//manages the game
	public void run(){
		Main.obj_list = Create.init();
		while(true)
		{
			//while not hitting goal
			Main.obj_list = Create.create_room(Main.level, Main.room);
			Main.reset = false;
			while(Coll.goal())
			{
				try
				{
				Thread.sleep(100);
				}
				catch (InterruptedException e) {}
				
				//if game is running
				if(Main.ingame)
				{
					Fly.fly();
					AI.move_all_opp();
					Coll.shop();
					Coll.npc();
					Coll.poison();
				}
				if(Main.reset) break;
			}

			if((Main.room += 1) > 3)
			{
				Main.room = 1;
				if((Main.level += 1) > 3)
				{
					System.exit(0);
				}
			}
		}
	}
}