package collision;

import gameobjects.Figure;
import main.Game;
import main.Main;

public class Coll {

	//returns false if player hits goal, good for while-loop
	//returns false if player hits exit, easy to run in while-loop
	public static boolean goal(){
		if (   (Main.obj_list.get(2).pos_x < Main.obj_list.get(1).pos_x + Main.obj_list.get(1).width)
			&& (Main.obj_list.get(2).pos_x + Main.obj_list.get(2).width > Main.obj_list.get(1).pos_x)
			&& (Main.obj_list.get(2).pos_y < Main.obj_list.get(1).pos_y + Main.obj_list.get(1).height)
			&& (Main.obj_list.get(2).pos_y + Main.obj_list.get(2).height > Main.obj_list.get(1).pos_y) )
		{
			return false;
		}
		else if(Game.Nr_of_Players == 2)
		{
			if (   (Main.obj_list.get(3).pos_x < Main.obj_list.get(1).pos_x + Main.obj_list.get(1).width)
				&& (Main.obj_list.get(3).pos_x + Main.obj_list.get(3).width > Main.obj_list.get(1).pos_x)
				&& (Main.obj_list.get(3).pos_y < Main.obj_list.get(1).pos_y + Main.obj_list.get(1).height)
				&& (Main.obj_list.get(3).pos_y + Main.obj_list.get(3).height > Main.obj_list.get(1).pos_y) )
			{
				return false;
			}
		}
		return true;
	}

	//changes value of Game.shop if player hits shop
	
	//sets Game.shop =1 for player1, =2 for player2, =3 for both, =0 else
	public static void shop(){
		if (   (Main.obj_list.get(2).pos_x < Main.obj_list.get(4).pos_x + Main.obj_list.get(4).width + Main.obj_list.get(4).fog)
			&& (Main.obj_list.get(2).pos_x + Main.obj_list.get(2).width > Main.obj_list.get(4).pos_x - Main.obj_list.get(4).fog)
			&& (Main.obj_list.get(2).pos_y < Main.obj_list.get(4).pos_y + Main.obj_list.get(4).height + Main.obj_list.get(4).fog)
			&& (Main.obj_list.get(2).pos_y + Main.obj_list.get(2).height > Main.obj_list.get(4).pos_y - Main.obj_list.get(4).fog) )
		{
			if(Game.shop == 0 || Game.shop == 1) Game.shop = 1;
			else Game.shop = 3;
		}
		else if(Game.shop == 3) Game.shop = 2;
		
		if(Game.Nr_of_Players == 2)
		{
			if (   (Main.obj_list.get(3).pos_x < Main.obj_list.get(0).pos_x + Main.obj_list.get(4).width + Main.obj_list.get(4).fog)
						&& (Main.obj_list.get(3).pos_x + Main.obj_list.get(3).width > Main.obj_list.get(4).pos_x - Main.obj_list.get(4).fog)
						&& (Main.obj_list.get(3).pos_y < Main.obj_list.get(0).pos_y + Main.obj_list.get(4).height + Main.obj_list.get(4).fog)
						&& (Main.obj_list.get(3).pos_y + Main.obj_list.get(3).height > Main.obj_list.get(4).pos_y - Main.obj_list.get(4).fog) )
			{
				if(Game.shop == 0 || Game.shop == 2) Game.shop = 2;
				else Game.shop = 3;
			}
			else if(Game.shop == 3) Game.shop = 1;
		}
	}
	
	//invokes poisoning
	public static void poison(){
		for(int i = 5 ; i < Main.obj_list.size() ; i++)
		{
			if(Main.obj_list.get(i).type == 2)
			{
				if (   (Main.obj_list.get(2).pos_x < Main.obj_list.get(i).pos_x + Main.obj_list.get(i).width + Main.obj_list.get(i).fog)
					&& (Main.obj_list.get(2).pos_x + Main.obj_list.get(2).width > Main.obj_list.get(i).pos_x - Main.obj_list.get(i).fog)
					&& (Main.obj_list.get(2).pos_y < Main.obj_list.get(i).pos_y + Main.obj_list.get(i).height + Main.obj_list.get(i).fog)
					&& (Main.obj_list.get(2).pos_y + Main.obj_list.get(2).height > Main.obj_list.get(i).pos_y - Main.obj_list.get(i).fog) )
				{
					deal_dmg(Main.obj_list.get(i) , Main.obj_list.get(2));
				}
				if(Game.Nr_of_Players == 2)
				{
					if (   (Main.obj_list.get(3).pos_x < Main.obj_list.get(i).pos_x + Main.obj_list.get(i).width + Main.obj_list.get(i).fog)
						&& (Main.obj_list.get(3).pos_x + Main.obj_list.get(3).width > Main.obj_list.get(i).pos_x - Main.obj_list.get(i).fog)
						&& (Main.obj_list.get(3).pos_y < Main.obj_list.get(i).pos_y + Main.obj_list.get(i).height + Main.obj_list.get(i).fog)
						&& (Main.obj_list.get(3).pos_y + Main.obj_list.get(3).height > Main.obj_list.get(i).pos_y - Main.obj_list.get(i).fog) )
					{
						deal_dmg(Main.obj_list.get(i) , Main.obj_list.get(3));
					}
				}
			}
		}
	}

	//returns true for no collision, if false, invokes event if tester = killer / poisontree
	//1 for wall/tree, 2 for poisontree, 3 for fox, 4 for player
	public static boolean coll(Figure tester , int x , int y){
	switch (tester.type) {				//coll varies according to testers type

		//tester == fox
		case 3:
			for(int i=2; i < Main.obj_list.size(); i++){
				if (Main.obj_list.get(i).nr == tester.nr)		//don't test yourself
				{
					continue;
				}
				else if ( 	(tester.pos_x + x < Main.obj_list.get(i).pos_x + Main.obj_list.get(i).width)
						 && (tester.pos_x + x + tester.width > Main.obj_list.get(i).pos_x)
						 && (tester.pos_y + y < Main.obj_list.get(i).pos_y + Main.obj_list.get(i).height)
						 && (tester.pos_y + y +tester.height > Main.obj_list.get(i).pos_y) )
				{
					if(Main.obj_list.get(i).type == 4)
					{
						deal_dmg(tester , Main.obj_list.get(i));
					}

					return false;
				}
			}
			return true;


		//tester == player
		case 4:
			for(int i=2; i < Main.obj_list.size(); i++){
				if (Main.obj_list.get(i).nr == tester.nr)		//don't test yourself
				{
					continue;
				}
				else if ( 	(tester.pos_x + x < Main.obj_list.get(i).pos_x + Main.obj_list.get(i).width)
						 && (tester.pos_x + x + tester.width > Main.obj_list.get(i).pos_x)
						 && (tester.pos_y + y < Main.obj_list.get(i).pos_y + Main.obj_list.get(i).height)
						 && (tester.pos_y + y + tester.height > Main.obj_list.get(i).pos_y) )
				{
					return false;
				}
			}
			return true;

		default:
			return true;
	}

}

	//returns true if fox or player behind tree
	public static boolean wall_opaque(int x , int y , int width , int height){
		for(int i=2 ; i < Main.obj_list.size() ; i++){
			if(Main.obj_list.get(i).type < 2)
			{
				if(		(Main.obj_list.get(i).pos_x < x + width)
					&&	(Main.obj_list.get(i).pos_x + Main.obj_list.get(i).width > x)
					&&	(Main.obj_list.get(i).pos_y < y + height)
					&&	(Main.obj_list.get(i).pos_y + Main.obj_list.get(i).height > y))
				{
					return true;
				}
			}
		}
		return false;
	}

	//deals dmg to players
	static void deal_dmg(Figure dealer , Figure reciever){
		reciever.hp-=dealer.dmg;
	}

}