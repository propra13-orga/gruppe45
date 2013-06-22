package main;

import gameobjects.Create;
import collision.Coll;
import movement.*;

public class Game extends Thread{

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
	public void run(){
		
		while(true)
		{
			//while not hitting goal
			System.out.println(Main.room);
//			System.out.println(Main.level);
			Main.obj_list = Create.init();
			while(Coll.goal())
			{
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {}
				//if game is running
				if(Main.ingame)
				{
					Fly.fly();
					AI.move_all_opp();
					Coll.shop();
					Coll.poison();
					System.out.println(Main.obj_list.get(2).hp);
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
			
			Main.obj_list.clear();
			Main.obj_list = Create.init();
			
		}
	}
}
