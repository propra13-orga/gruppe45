package collision;

import gameobjects.Figure;
import gameobjects.Hero;
import gameobjects.Item;
import main.Main;

public class Coll {

	//returns false if player hits goal, good for while-loop
	public static boolean goal(){
		if (   (Main.obj_list.get(2).pos_x < Main.obj_list.get(1).pos_x + Main.obj_list.get(1).image.getWidth(null))
			&& (Main.obj_list.get(2).pos_x + Main.obj_list.get(2).image.getWidth(null) > Main.obj_list.get(1).pos_x)
			&& (Main.obj_list.get(2).pos_y < Main.obj_list.get(1).pos_y + Main.obj_list.get(1).image.getHeight(null))
			&& (Main.obj_list.get(2).pos_y + Main.obj_list.get(2).image.getHeight(null) > Main.obj_list.get(1).pos_y) )
		{
			return false;
		}
		else if(Main.Nr_of_Players == 2)
		{
			if (   (Main.obj_list.get(3).pos_x < Main.obj_list.get(1).pos_x + Main.obj_list.get(1).image.getWidth(null))
				&& (Main.obj_list.get(3).pos_x + Main.obj_list.get(3).image.getWidth(null) > Main.obj_list.get(1).pos_x)
				&& (Main.obj_list.get(3).pos_y < Main.obj_list.get(1).pos_y + Main.obj_list.get(1).image.getHeight(null))
				&& (Main.obj_list.get(3).pos_y + Main.obj_list.get(3).image.getHeight(null) > Main.obj_list.get(1).pos_y) )
			{
				return false;
			}
		}
		else return true;
		return true;
	}

	//changes value of Game.shop if player hits shop
	
	//sets Game.shop =1 for player1, =2 for player2, =3 for both, =0 else
	public static void shop(){
		if (   (Main.obj_list.get(2).pos_x < Main.obj_list.get(4).pos_x + Main.obj_list.get(4).image.getWidth(null) + Main.obj_list.get(4).fog)
			&& (Main.obj_list.get(2).pos_x + Main.obj_list.get(2).image.getWidth(null) > Main.obj_list.get(4).pos_x - Main.obj_list.get(4).fog)
			&& (Main.obj_list.get(2).pos_y < Main.obj_list.get(4).pos_y + Main.obj_list.get(4).image.getHeight(null) + Main.obj_list.get(4).fog)
			&& (Main.obj_list.get(2).pos_y + Main.obj_list.get(2).image.getHeight(null) > Main.obj_list.get(4).pos_y - Main.obj_list.get(4).fog) )
		{
//		shopevent
		}
		
		if(Main.Nr_of_Players == 2)
		{
			if (   (Main.obj_list.get(3).pos_x < Main.obj_list.get(0).pos_x + Main.obj_list.get(4).image.getWidth(null) + Main.obj_list.get(4).fog)
						&& (Main.obj_list.get(3).pos_x + Main.obj_list.get(3).image.getWidth(null) > Main.obj_list.get(4).pos_x - Main.obj_list.get(4).fog)
						&& (Main.obj_list.get(3).pos_y < Main.obj_list.get(0).pos_y + Main.obj_list.get(4).image.getHeight(null) + Main.obj_list.get(4).fog)
						&& (Main.obj_list.get(3).pos_y + Main.obj_list.get(3).image.getHeight(null) > Main.obj_list.get(4).pos_y - Main.obj_list.get(4).fog) )
			{
//			shopevent
			}
		}
	}
	
	//invokes poisoning
	public static void poison(){
		for(int i = 5 ; i < Main.obj_list.size() ; i++)
		{
			if(Main.obj_list.get(i).type == 2)
			{
				if (   (Main.obj_list.get(2).pos_x < Main.obj_list.get(i).pos_x + Main.obj_list.get(i).image.getWidth(null) + Main.obj_list.get(i).fog)
					&& (Main.obj_list.get(2).pos_x + Main.obj_list.get(2).image.getWidth(null) > Main.obj_list.get(i).pos_x - Main.obj_list.get(i).fog)
					&& (Main.obj_list.get(2).pos_y < Main.obj_list.get(i).pos_y + Main.obj_list.get(i).image.getHeight(null) + Main.obj_list.get(i).fog)
					&& (Main.obj_list.get(2).pos_y + Main.obj_list.get(2).image.getHeight(null) > Main.obj_list.get(i).pos_y + Main.obj_list.get(i).image.getHeight(null) / 3 - Main.obj_list.get(i).fog) )
				{
					deal_dmg(Main.obj_list.get(i) , Main.obj_list.get(2));
				}
				if(Main.Nr_of_Players == 2)
				{
					if (   (Main.obj_list.get(3).pos_x < Main.obj_list.get(i).pos_x + Main.obj_list.get(i).image.getWidth(null) + Main.obj_list.get(i).fog)
						&& (Main.obj_list.get(3).pos_x + Main.obj_list.get(3).image.getWidth(null) > Main.obj_list.get(i).pos_x - Main.obj_list.get(i).fog)
						&& (Main.obj_list.get(3).pos_y < Main.obj_list.get(i).pos_y + Main.obj_list.get(i).image.getHeight(null) + Main.obj_list.get(i).fog)
						&& (Main.obj_list.get(3).pos_y + Main.obj_list.get(3).image.getHeight(null) > Main.obj_list.get(i).pos_y - Main.obj_list.get(i).fog) )
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
				else if ( 	(tester.pos_x + x < Main.obj_list.get(i).pos_x + Main.obj_list.get(i).image.getWidth(null))
						 && (tester.pos_x + x + tester.image.getWidth(null) > Main.obj_list.get(i).pos_x)
						 && (tester.pos_y + y < Main.obj_list.get(i).pos_y + Main.obj_list.get(i).image.getHeight(null))
						 && (tester.pos_y + y +tester.image.getHeight(null) > Main.obj_list.get(i).pos_y) )
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
				else if ( 	(tester.pos_x + x < Main.obj_list.get(i).pos_x + Main.obj_list.get(i).image.getWidth(null))
						 && (tester.pos_x + x + tester.image.getWidth(null) > Main.obj_list.get(i).pos_x)
						 && (tester.pos_y + y < Main.obj_list.get(i).pos_y + Main.obj_list.get(i).image.getHeight(null))
						 && (tester.pos_y + y + tester.image.getHeight(null) > Main.obj_list.get(i).pos_y + Main.obj_list.get(i).image.getHeight(null) / 3) )
				{
					if(Main.obj_list.get(i).type == 8)
					{
						tester.bag.add((Item) Main.obj_list.get(i));
						Main.obj_list.remove(i);
						for(int j = i ; j < Main.obj_list.size() ; j++)
						{
							Main.obj_list.get(j).nr = j;
						}
						return true;
					}
					else return false;
				}
			}
			return true;


		//Spells
		case 9:
			for(int i=2; i < Main.obj_list.size(); i++){
				if (Main.obj_list.get(i).nr == tester.nr)		//don't test yourself
				{
					continue;
				}
				else if ( 	(tester.pos_x + x < Main.obj_list.get(i).pos_x + Main.obj_list.get(i).image.getWidth(null))
						 && (tester.pos_x + x + tester.image.getWidth(null) > Main.obj_list.get(i).pos_x)
						 && (tester.pos_y + y < Main.obj_list.get(i).pos_y + Main.obj_list.get(i).image.getHeight(null))
						 && (tester.pos_y + y +tester.image.getHeight(null) > Main.obj_list.get(i).pos_y) )
				{
					hit(tester , i);
					Main.obj_list.remove(tester.nr);
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
					&&	(Main.obj_list.get(i).pos_x + Main.obj_list.get(i).image.getWidth(null) > x)
					&&	(Main.obj_list.get(i).pos_y < y + height)
					&&	(Main.obj_list.get(i).pos_y + Main.obj_list.get(i).image.getHeight(null) > y))
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
	
	//called upon a spells collision, damages the target and destroyes it if hp <= 0
	public static void hit(Figure spell, int i){
		if(Main.obj_list.get(i).type == 3)
		{
			if(Main.obj_list.get(i).destroyable)
			{
				if((Main.obj_list.get(i).hp -= spell.dmg) < 1)				//if targets hp is < 1
				{
					Main.obj_list.remove(i);
					for(int j = i ; j < Main.obj_list.size() ; j++)			//update index, since 1 figure was removed
					{
						Main.obj_list.get(j).nr = j;
					}
				}
			}
		}
	}

}