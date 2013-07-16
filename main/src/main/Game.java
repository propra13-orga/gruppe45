package main;

import graphics.MasterFrame;
import collision.Coll;
import local.Create;
import movement.*;

/**
 * Gamelogic
 * @author Andreas Roth
 *
 */
public class Game extends Thread{

/**
 * Manages opponents, npc's, goal's
 */
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
					Fly.fly();
					AI.move_all_opp();
					Coll.shop();
					Coll.npc();
					Coll.poison();
					
					Create.hero1.hp = Main.obj_list.get(2)[4];
					Create.hero2.hp = Main.obj_list.get(3)[4];
					
				}
				if(Main.reset)
				{
					Main.level -= 1;
					Main.room = 3;
					if((Create.hero1.lives -= 1) < 0 || (Create.hero2.lives -= 1) < 0) System.exit(0);
					Create.hero1.hp = 50 + 50 * Create.hero1.level;
					Create.hero2.hp = 50 + 50 * Create.hero2.level;
					break;
				}
				if(Create.hero1.ep == (int) Math.pow(2, Create.hero1.level))
				{
					Create.hero1.ep = 0;
					Create.hero1.level += 1;
					Create.hero1.setHp(50);
				
					Create.hero2.ep = 0;
					Create.hero2.level += 1;
					Create.hero2.setHp(50);
				}
			}
			
			Main.room += 1;
			MasterFrame.set = false;
			if(Main.room > 3)
			{
				Main.room = 1;
				Main.level += 1;
				
			}
		}
	}
}