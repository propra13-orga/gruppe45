package movement;

import local.Create;
import main.Main;

public class Fly {

	public static void fly(){
	
		int STEP = 20;
		
		for(int i = 0 ; i < Main.obj_list.size() ; i++)
		{
			if(Main.obj_list.get(i)[0] == 11 || Main.obj_list.get(i)[0] == 12)
			{
				switch(Main.obj_list.get(i)[5]){
					case 1:
						Move.up(Main.obj_list.get(i), STEP);
						break;
						
					case 2:
						Move.right(Main.obj_list.get(i), STEP);
						break;
						
					case 3:
						Move.down(Main.obj_list.get(i), STEP);
						break;
						
					case 4:
						Move.left(Main.obj_list.get(i), STEP);
						break;
						
				}
				
				if(i < Main.obj_list.size())
				{
					if(	Main.obj_list.get(i)[2] < Main.off + STEP * 2
						|| Main.obj_list.get(i)[2] + Create.gameobjects[Main.obj_list.get(i)[0]].width > Main.board_width - Main.off-STEP * 2
						|| Main.obj_list.get(i)[3] < Main.off + STEP * 2
						|| Main.obj_list.get(i)[3] + Create.gameobjects[Main.obj_list.get(i)[0]].height > Main.board_height - Main.off-STEP * 2 )
					{
						Main.obj_list.remove(i);
						for(int j = i ; j < Main.obj_list.size() ; j++)
						{
							Main.obj_list.get(j)[1] = j;
						}
					}
				}
			}
		}
	}
}
