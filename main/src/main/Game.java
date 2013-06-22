package main;

import gameobjects.Create;
import gameobjects.Figure;
import collision.Coll;
import movement.*;

public class Game extends Thread{

//creates keyboards and adds keylistener
/* void setKeyboard(){
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
		Main.obj_list = Create.init();
		while(true)
		{
			//while not hitting goal
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

			//Main.obj_list.clear();
			Main.obj_list = Create.create_room(Main.level, Main.room);
		}
	}
}