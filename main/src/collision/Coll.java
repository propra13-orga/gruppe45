package collision;

import gameobjects.Figure;
import gameobjects.Item;
import local.Create;
import main.Main;

public class Coll {

	//returns false if player hits goal, good for while-loop
	public static boolean goal(){
		if (   (Main.obj_list.get(2)[2] < Main.obj_list.get(1)[2] + Create.gameobjects[Main.obj_list.get(1)[0]].width)
			&& (Main.obj_list.get(2)[2] + Create.gameobjects[Main.obj_list.get(2)[0]].width > Main.obj_list.get(1)[2])
			&& (Main.obj_list.get(2)[3] < Main.obj_list.get(1)[3] + Create.gameobjects[Main.obj_list.get(1)[0]].height)
			&& (Main.obj_list.get(2)[3] + Create.gameobjects[Main.obj_list.get(2)[0]].height > Main.obj_list.get(1)[3]) )
		{
			return false;
		}
		else if(Main.Nr_of_Players == 2)
		{
			if (   (Main.obj_list.get(3)[2] < Main.obj_list.get(1)[2] + Create.gameobjects[Main.obj_list.get(1)[0]].width)
				&& (Main.obj_list.get(3)[2] + Create.gameobjects[Main.obj_list.get(3)[0]].width > Main.obj_list.get(1)[2])
				&& (Main.obj_list.get(3)[3] < Main.obj_list.get(1)[3] + Create.gameobjects[Main.obj_list.get(1)[0]].height)
				&& (Main.obj_list.get(3)[3] + Create.gameobjects[Main.obj_list.get(3)[0]].height > Main.obj_list.get(1)[3]) )
			{
				return false;
			}
		}
		else return true;
		return true;
	}

	//changes value of Main.npc if player hits npc
	public static void npc(){
		if(local.Create.npc > 0){
			int i = local.Create.npc;
			if (   (Main.obj_list.get(2)[2] < Main.obj_list.get(i)[2] + Create.gameobjects[Main.obj_list.get(i)[0]].width + Create.gameobjects[Main.obj_list.get(i)[0]].fog)
				&& (Main.obj_list.get(2)[2] + Create.gameobjects[Main.obj_list.get(2)[0]].width > Main.obj_list.get(i)[2] - Create.gameobjects[Main.obj_list.get(i)[0]].fog)
				&& (Main.obj_list.get(2)[3] < Main.obj_list.get(i)[3] + Create.gameobjects[Main.obj_list.get(i)[0]].height + Create.gameobjects[Main.obj_list.get(i)[0]].fog)
				&& (Main.obj_list.get(2)[3] + Create.gameobjects[Main.obj_list.get(2)[0]].height > Main.obj_list.get(i)[3] - Create.gameobjects[Main.obj_list.get(i)[0]].fog) )
			{
			Main.npc = true;
			}
			
			else if(Main.Nr_of_Players == 2)
			{
				if (   (Main.obj_list.get(3)[2] < Main.obj_list.get(i)[2] + Create.gameobjects[Main.obj_list.get(i)[0]].width + Create.gameobjects[Main.obj_list.get(i)[0]].fog)
					&& (Main.obj_list.get(3)[2] + Create.gameobjects[Main.obj_list.get(3)[0]].width > Main.obj_list.get(i)[2] - Create.gameobjects[Main.obj_list.get(i)[0]].fog)
					&& (Main.obj_list.get(3)[3] < Main.obj_list.get(i)[3] + Create.gameobjects[Main.obj_list.get(i)[0]].height + Create.gameobjects[Main.obj_list.get(i)[0]].fog)
					&& (Main.obj_list.get(3)[3] + Create.gameobjects[Main.obj_list.get(3)[0]].height > Main.obj_list.get(i)[3] - Create.gameobjects[Main.obj_list.get(i)[0]].fog) )
				{
				Main.npc = true;
				}
			}
			else Main.npc = false;
		}
	}
	
	//sets Main.shop = true if player in range
	public static void shop(){
		if(local.Create.shop > 0){
			int i = local.Create.shop;
			if (   (Main.obj_list.get(2)[2] < Main.obj_list.get(i)[2] + Create.gameobjects[Main.obj_list.get(i)[0]].width + Create.gameobjects[Main.obj_list.get(i)[0]].fog)
				&& (Main.obj_list.get(2)[2] + Create.gameobjects[Main.obj_list.get(2)[0]].width > Main.obj_list.get(i)[2] - Create.gameobjects[Main.obj_list.get(i)[0]].fog)
				&& (Main.obj_list.get(2)[3] < Main.obj_list.get(i)[3] + Create.gameobjects[Main.obj_list.get(i)[0]].height + Create.gameobjects[Main.obj_list.get(i)[0]].fog)
				&& (Main.obj_list.get(2)[3] + Create.gameobjects[Main.obj_list.get(2)[0]].height > Main.obj_list.get(i)[3] - Create.gameobjects[Main.obj_list.get(i)[0]].fog) )
			{
			Main.shop = true;
			}
			
			else if(Main.Nr_of_Players == 2)
			{
				if (   (Main.obj_list.get(3)[2] < Main.obj_list.get(i)[2] + Create.gameobjects[Main.obj_list.get(i)[0]].width + Create.gameobjects[Main.obj_list.get(i)[0]].fog)
						&& (Main.obj_list.get(3)[2] + Create.gameobjects[Main.obj_list.get(3)[0]].width > Main.obj_list.get(i)[2] - Create.gameobjects[Main.obj_list.get(i)[0]].fog)
						&& (Main.obj_list.get(3)[3] < Main.obj_list.get(i)[3] + Create.gameobjects[Main.obj_list.get(i)[0]].height + Create.gameobjects[Main.obj_list.get(i)[0]].fog)
						&& (Main.obj_list.get(3)[3] + Create.gameobjects[Main.obj_list.get(3)[0]].height > Main.obj_list.get(i)[3] - Create.gameobjects[Main.obj_list.get(i)[0]].fog) )
				{
				Main.shop = true;
				}
			}
			else Main.shop = false;
		}
	}
	
	//invokes poisoning
	public static void poison(){
		for(int i = 4 ; i < Main.obj_list.size() ; i++)
		{
			if(Main.obj_list.get(i)[0] == 5)
			{
				if (   (Main.obj_list.get(2)[2] < Main.obj_list.get(i)[2] + Create.gameobjects[Main.obj_list.get(i)[0]].width + Create.gameobjects[Main.obj_list.get(i)[0]].fog)
						&& (Main.obj_list.get(2)[2] + Create.gameobjects[Main.obj_list.get(2)[0]].width > Main.obj_list.get(i)[2] - Create.gameobjects[Main.obj_list.get(i)[0]].fog)
						&& (Main.obj_list.get(2)[3] < Main.obj_list.get(i)[3] + Create.gameobjects[Main.obj_list.get(i)[0]].height + Create.gameobjects[Main.obj_list.get(i)[0]].fog)
						&& (Main.obj_list.get(2)[3] + Create.gameobjects[Main.obj_list.get(2)[0]].height > Main.obj_list.get(i)[3] - Create.gameobjects[Main.obj_list.get(i)[0]].fog + (int) Create.gameobjects[Main.obj_list.get(i)[0]].height / 3) )
					{
					deal_dmg(Main.obj_list.get(i) , Main.obj_list.get(2));
				}
				if(Main.Nr_of_Players == 2)
				{
					if (   (Main.obj_list.get(3)[2] < Main.obj_list.get(i)[2] + Create.gameobjects[Main.obj_list.get(i)[0]].width + Create.gameobjects[Main.obj_list.get(i)[0]].fog)
						&& (Main.obj_list.get(3)[2] + Create.gameobjects[Main.obj_list.get(3)[0]].width > Main.obj_list.get(i)[2] - Create.gameobjects[Main.obj_list.get(i)[0]].fog)
						&& (Main.obj_list.get(3)[3] < Main.obj_list.get(i)[3] + Create.gameobjects[Main.obj_list.get(i)[0]].height + Create.gameobjects[Main.obj_list.get(i)[0]].fog)
						&& (Main.obj_list.get(3)[3] + Create.gameobjects[Main.obj_list.get(3)[0]].height > Main.obj_list.get(i)[3] - Create.gameobjects[Main.obj_list.get(i)[0]].fog + (int) Create.gameobjects[Main.obj_list.get(i)[0]].height / 3) )
					{
						deal_dmg(Main.obj_list.get(i) , Main.obj_list.get(3));
					}
				}
			}
		}
	}

	//returns true for no collision, if false, invokes event if tester = killer / poisontree
	//1 for wall/tree, 2 for poisontree, 3 for fox, 4 for player
	public static boolean coll(int[] figure , int x , int y){
	switch (figure[0]) {				//coll varies according to testers type

		//tester == fox
		case 6:
			for(int i=2; i < Main.obj_list.size(); i++){
				if (Main.obj_list.get(i).nr == figure.nr)		//don't test yourself
				{
					continue;
				}
				else if ( 	(figure.pos_x + x < Main.obj_list.get(i).pos_x + Main.obj_list.get(i).image.getWidth(null))
						 && (figure.pos_x + x + figure.image.getWidth(null) > Main.obj_list.get(i).pos_x)
						 && (figure.pos_y + y < Main.obj_list.get(i).pos_y + Main.obj_list.get(i).image.getHeight(null))
						 && (figure.pos_y + y +figure.image.getHeight(null) > Main.obj_list.get(i).pos_y) )
				{
					if(Main.obj_list.get(i).type == 4)
					{
						deal_dmg(figure , Main.obj_list.get(i));
					}

					return false;
				}
			}
			return true;


		//tester == bunny
		case 2:
			for(int i=2; i < Main.obj_list.size(); i++){
				if (Main.obj_list.get(i).nr == figure.nr)		//don't test yourself
				{
					continue;
				}
				else if ( 	(figure.pos_x + x < Main.obj_list.get(i).pos_x + Main.obj_list.get(i).image.getWidth(null))
						 && (figure.pos_x + x + figure.image.getWidth(null) > Main.obj_list.get(i).pos_x)
						 && (figure.pos_y + y < Main.obj_list.get(i).pos_y + Main.obj_list.get(i).image.getHeight(null))
						 && (figure.pos_y + y + figure.image.getHeight(null) > Main.obj_list.get(i).pos_y + Main.obj_list.get(i).image.getHeight(null) / 3) )
				{
					if(Main.obj_list.get(i).type == 8)
					{
						figure.bag.add((Item) Main.obj_list.get(i));
						if(Main.obj_list.get(i).name.equals("Feuerball")) Create.hero1.spell = true;
						Main.obj_list.remove(i);
						
						Create.hero1.setHp(Create.hero1.getHp() + 10);					//Meilenstein 2
						Create.hero1.setMp(Create.hero1.getMp() + 5);					//
						
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


		//tester == boss
		case 7:
			for(int i=2; i < Main.obj_list.size(); i++){
				if (Main.obj_list.get(i).nr == figure.nr)		//don't test yourself
				{
					continue;
				}
				else if ( 	(figure.pos_x + x < Main.obj_list.get(i).pos_x + Main.obj_list.get(i).image.getWidth(null))
						 && (figure.pos_x + x + figure.image.getWidth(null) > Main.obj_list.get(i).pos_x)
						 && (figure.pos_y + y < Main.obj_list.get(i).pos_y + Main.obj_list.get(i).image.getHeight(null))
						 && (figure.pos_y + y +figure.image.getHeight(null) > Main.obj_list.get(i).pos_y) )
				{
					if(Main.obj_list.get(i).type == 4)
					{
						deal_dmg(figure , Main.obj_list.get(i));
					}

					return false;
				}
			}
			return true;
			
			
		//tester = blob
		case 11:
			for(int i=2; i < Main.obj_list.size(); i++){
				if (Main.obj_list.get(i).nr == figure.nr)		//don't test yourself
				{
					continue;
				}
				else if ( 	(figure.pos_x + x < Main.obj_list.get(i).pos_x + Main.obj_list.get(i).image.getWidth(null))
						 && (figure.pos_x + x + figure.image.getWidth(null) > Main.obj_list.get(i).pos_x)
						 && (figure.pos_y + y < Main.obj_list.get(i).pos_y + Main.obj_list.get(i).image.getHeight(null))
						 && (figure.pos_y + y +figure.image.getHeight(null) > Main.obj_list.get(i).pos_y) )
				{
					hit(figure , i);
					return false;
				}
			}
			return true;

		default:
			return true;
	}

}

	//returns true if no collision, else invokes event
	public static boolean coll(int[] tester , int x , int y){
		for(int i = 2 ; i < Main.obj_list.size() ; i++)
		{
			//local variable
			int[] figure = Main.obj_list.get(i);
			
			//no self testing
			if(tester[1] == i) continue;
			
			//collision test
			if (   (tester[2] + x < figure[2] + Create.gameobjects[figure[0]].width)
				&& (tester[2] + x + Create.gameobjects[tester[0]].width > figure[2])
				&& (tester[3] + y < figure[3] + Create.gameobjects[figure[0]].height)
				&& (tester[3] + y + Create.gameobjects[tester[0]].height > figure[3]))
			{
				if(tester[0] == 6 || tester[0] == 7)
			}
			
			return true;
		}
	}
	
	//deals dmg to players
	static void deal_dmg(int[] dealer , int[] reciever){
		reciever[4] -= (Create.gameobjects[dealer[0]].dmg / Create.gameobjects[reciever[0]].defe);
		if(reciever[4] < 1)
		{
			//reduce lives of bunny
			if(reciever[1] == 2)
			{
				if((Create.hero1.lives -= 1) < 0) System.exit(0);
				Main.reset = true;
			}
			//reduce lives of hedgehog
			else if(reciever[1] == 3)
			{
				if((Create.hero2.lives -= 1) < 0) System.exit(0);
				Main.reset = true;
			}
			//remove figure from board
			else
			{
				Main.obj_list.remove(reciever[1]);
				for(int j = 2 ; j < Main.obj_list.size() ; j++)
				{
					Main.obj_list.get(j)[1] = j;
				}
			}
		}
	}
	
	//called upon a spells collision, damages the target and destroyes it if hp <= 0
	public static void hit(Figure spell, int i){
		if(Main.obj_list.get(i).type == 3 | Main.obj_list.get(i).type == 5)
		{
			if(Main.obj_list.get(i).destroyable)
			{
				if((Main.obj_list.get(i).hp -= spell.dmg / Main.obj_list.get(i).defe) < 1)				//if targets hp is < 1
				{
					Create.hero1.setBugs(Create.hero1.getBugs()+Main.obj_list.get(i).bugs);
					if(Create.hero1.level < Main.level) Main.obj_list.get(2).ep += Main.obj_list.get(i).ep;
					
					//if boss dies clear room
					if(Main.obj_list.get(i).type == 5)
					{
						for(int j = 6 ; j < Main.obj_list.size() ;){
							Main.obj_list.remove(j);
						}
					}
					else
					{
						Main.obj_list.remove(spell.nr);
						Main.obj_list.remove(i);
						for(int j = i ; j < Main.obj_list.size() ; j++)			//update index, since 1 figure was removed
						{
							Main.obj_list.get(j).nr = j;
						}
					}
				}
				else Main.obj_list.remove(spell.nr);
			}
		}
		else Main.obj_list.remove(spell.nr);
	}

}