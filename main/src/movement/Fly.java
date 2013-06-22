package movement;

import main.Main;

public class Fly {

	public static void fly(){
	
		int STEP = 20;
		
		for(int i = 0 ; i < Main.obj_list.size() ; i++)
		{
			if(Main.obj_list.get(i).type == 9)
			{
				switch(Main.obj_list.get(i).direction){
					case 'u':
						Move.up(Main.obj_list.get(i), STEP);
						break;
						
					case 'r':
						Move.right(Main.obj_list.get(i), STEP);
						break;
						
					case 'd':
						Move.down(Main.obj_list.get(i), STEP);
						break;
						
					case 'l':
						Move.left(Main.obj_list.get(i), STEP);
						break;
						
				}
			}
		}
	}
}
