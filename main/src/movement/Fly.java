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
				
				if(i < Main.obj_list.size())
				{
					if(	Main.obj_list.get(i).pos_x < Main.off + STEP
						|| Main.obj_list.get(i).pos_x + Main.obj_list.get(i).image.getWidth(null) > Main.board_width - Main.off-STEP
						|| Main.obj_list.get(i).pos_y < Main.off + STEP
						|| Main.obj_list.get(i).pos_y + Main.obj_list.get(i).image.getHeight(null) > Main.board_height - Main.off-STEP )
					{
						Main.obj_list.remove(i);
						for(int j = i ; j < Main.obj_list.size() ; j++)
						{
							Main.obj_list.get(j).nr = j;
						}
					}
				}
			}
		}
	}
}
