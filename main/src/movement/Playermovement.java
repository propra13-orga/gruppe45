package movement;

import local.Create;
import main.Main;

/**
 * Moves players
 * @author Andreas Roth
 *
 */
public class Playermovement extends Thread{

	/**
	 * Moves players
	 */
	public void run(){
		
		int STEP = 10;
		
		while(true)
		{
		
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			Create.hero1.moving = false;
			Create.hero2.moving = false;
			for(int i = 0 ; i < 8 ; i++)
			{
				if(i < 4) if(Move.keys[i]) Create.hero1.moving = true;
				else if(Move.keys[i]) Create.hero2.moving = true;
			}
			
			
			if(Move.keys[0])
			{
				Main.obj_list.get(2)[5] = 1;
				Create.hero1.moving = true;
				Move.up(Main.obj_list.get(2) , STEP);
			}
		
			if(Move.keys[1])
			{
				Main.obj_list.get(2)[5] = 4;
				Create.hero1.moving = true;
				Move.left(Main.obj_list.get(2) , STEP);
			}
		
			if(Move.keys[2])
			{
				Main.obj_list.get(2)[5] = 3;
				Create.hero1.moving = true;
				Move.down(Main.obj_list.get(2) , STEP);
			}
		
			if(Move.keys[3])
			{
				Main.obj_list.get(2)[5] = 2;
				Create.hero2.moving = true;
				Move.right(Main.obj_list.get(2) , STEP);
			}
			
			if(Move.keys[4])
			{
				Main.obj_list.get(3)[5] = 1;
				Create.hero2.moving = true;
				Move.up(Main.obj_list.get(3) , STEP);
			}
		
			if(Move.keys[5])
			{
				Main.obj_list.get(3)[5] = 4;
				Create.hero2.moving = true;
				Move.left(Main.obj_list.get(3) , STEP);
			}
		
			if(Move.keys[6])
			{
				Main.obj_list.get(3)[5] = 3;
				Create.hero2.moving = true;
				Move.down(Main.obj_list.get(3) , STEP);
			}
		
			if(Move.keys[7])
			{
				Main.obj_list.get(3)[5] = 2;
				Create.hero2.moving = true;
				Move.right(Main.obj_list.get(3) , STEP);
			}
		}
	}
}