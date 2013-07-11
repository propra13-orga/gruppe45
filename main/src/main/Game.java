package main;

import graphics.MasterFrame;
import collision.Coll;
import local.Create;
import movement.*;

public class Game extends Thread{

//manages the game
	public void run(){
		Create.init();
		
		System.out.println("Nr_of_Players: " + String.valueOf(Main.Nr_of_Players));
		
		while(true)
		{
			//while not hitting goal
			Create.create_room();
			Main.reset = false;
			while(Coll.goal())
			{
				try
				{
				Thread.sleep(75);
				}
				catch (InterruptedException e) {}
				
				
				//if game is running
				if(Main.ingame)
				{
//					Fly.fly();
//					AI.move_all_opp();
					Coll.shop();
					Coll.npc();
//					Coll.poison();
				}
				if(Main.reset)
				{
					Main.level -= 1;
					Main.room = 3;
					if((Create.hero1.lives -= 1) < 0) System.exit(0);
					Create.hero1.setHp(50 + 50 * Create.hero1.level);
					break;
				}
				if(Create.hero1.ep == (int) Math.pow(2, Create.hero1.level))
				{
					Create.hero1.ep = 0;
					Create.hero1.level += 1;
					Create.hero1.setHp(100 + 50 * Create.hero1.level);
				}
			}
			
			if((Main.room += 1) > 3)
			{
				Main.room = 1;
				MasterFrame.set = true;
				if((Main.level += 1) > 3)
				{
					System.exit(0);
				}
				if(Main.level == 2) Create.hero1.attack = true;
			}
		}
	}
}