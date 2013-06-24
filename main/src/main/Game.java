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
				if(Main.reset)
				{
					Main.level -= 1;
					Main.room = 3;
					if((Create.hero1.lives -= 1) < 0) System.exit(0);
					Create.hero1.setHp(50 + 50 * Create.hero1.level);
					break;
				}
				if(Main.obj_list.get(2).ep == (int) Math.pow(2, Main.obj_list.get(2).level))
				{
					Main.obj_list.get(2).ep = 0;
					Main.obj_list.get(2).level += 1;
					Create.hero1.setHp(100 + 50 * Main.obj_list.get(2).level);
				}
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