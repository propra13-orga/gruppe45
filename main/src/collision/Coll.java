package collision;

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
			else Main.npc = false;
			
			if(Main.Nr_of_Players == 2)
			{
				if (   (Main.obj_list.get(3)[2] < Main.obj_list.get(i)[2] + Create.gameobjects[Main.obj_list.get(i)[0]].width + Create.gameobjects[Main.obj_list.get(i)[0]].fog)
					&& (Main.obj_list.get(3)[2] + Create.gameobjects[Main.obj_list.get(3)[0]].width > Main.obj_list.get(i)[2] - Create.gameobjects[Main.obj_list.get(i)[0]].fog)
					&& (Main.obj_list.get(3)[3] < Main.obj_list.get(i)[3] + Create.gameobjects[Main.obj_list.get(i)[0]].height + Create.gameobjects[Main.obj_list.get(i)[0]].fog)
					&& (Main.obj_list.get(3)[3] + Create.gameobjects[Main.obj_list.get(3)[0]].height > Main.obj_list.get(i)[3] - Create.gameobjects[Main.obj_list.get(i)[0]].fog) )
				{
				Main.npc = true;
				}
				else Main.npc = false;
			}
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
			else Main.shop = false;
			
			if(Main.Nr_of_Players == 2)
			{
				if (   (Main.obj_list.get(3)[2] < Main.obj_list.get(i)[2] + Create.gameobjects[Main.obj_list.get(i)[0]].width + Create.gameobjects[Main.obj_list.get(i)[0]].fog)
						&& (Main.obj_list.get(3)[2] + Create.gameobjects[Main.obj_list.get(3)[0]].width > Main.obj_list.get(i)[2] - Create.gameobjects[Main.obj_list.get(i)[0]].fog)
						&& (Main.obj_list.get(3)[3] < Main.obj_list.get(i)[3] + Create.gameobjects[Main.obj_list.get(i)[0]].height + Create.gameobjects[Main.obj_list.get(i)[0]].fog)
						&& (Main.obj_list.get(3)[3] + Create.gameobjects[Main.obj_list.get(3)[0]].height > Main.obj_list.get(i)[3] - Create.gameobjects[Main.obj_list.get(i)[0]].fog) )
				{
				Main.shop = true;
				}
				else Main.shop = false;
			}
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
				
				//tester == bunny
				if(tester[0] == 2)
				{
					
					//figure == item
					if(figure[0] == 10)
					{
						Create.hero1.bag.add(figure);
						return true;
					}
				}
				
				//tester == hedgehog
				else if(tester[0] == 3)
				{
					
					//figure == item
					if(figure[0] == 10)
					{
						Create.hero2.bag.add(figure);
						return true;
					}
				}
				
				//tester == opponent
				else if(tester[0] == 6 || tester[0] == 7)
				{
					if(figure[0] == 2 || figure[0] == 3)
					{
						deal_dmg(tester , figure);
					}
				}
				
				//tester == spell
				else if(tester[0] == 11 || tester[0] == 12)
				{
						hit(tester , figure);
				}
				return false;
			}
		}
		return true;
	}
	
	//deals dmg to players
	static void deal_dmg(int[] dealer , int[] reciever){
		reciever[4] -= (Create.gameobjects[dealer[0]].dmg / Create.gameobjects[reciever[0]].defe);
		if(reciever[4] < 1)
		{
			//reduce lives of bunny
			if(reciever[1] == 2)
			{
				Main.reset = true;
			}
			//reduce lives of hedgehog
			else if(reciever[1] == 3)
			{
				Main.reset = true;
			}
			//remove figure from board
			else
			{
				Main.obj_list.remove(reciever[1]);
				for(int j = 2 ; j < Main.obj_list.size() ; j++)
				{
					Main.obj_list.get(j)[1] = j;
					if(Main.obj_list.get(j)[0] == 8) Create.shop = j;
					else if(Main.obj_list.get(j)[0] == 9) Create.npc = j;
				}
			}
		}
	}
	
	//called upon a spells collision, damages the target and destroyes it if hp <= 0
	public static void hit(int[] spell , int[] victim){
		if(victim[0] == 6 || victim[0] == 7)
		{
			
			//if targets hp is < 1
			if((victim[4] -= spell[4] / Create.gameobjects[victim[0]].defe) < 1)
			{
				
				//get bugs
				Create.hero1.setBugs(Create.hero1.getBugs() +Create.gameobjects[victim[0]].bugs);
				
				//get ep
				Create.hero1.ep += Create.gameobjects[victim[0]].ep;
				Create.hero2.ep += Create.gameobjects[victim[0]].ep;
				
				//if boss dies clear room
				if(victim[0] == 7)
				{
					for(int j = 4 ; j < Main.obj_list.size() ;){
						if(Main.obj_list.get(j)[0] == 4)
						{
							Main.obj_list.remove(j);
							if(j < Main.obj_list.size()) Main.obj_list.get(j)[1] = j;
						}
						else j++;
					}
				}
				
				Main.obj_list.remove(spell[1]);
				Main.obj_list.remove(victim[1]);
				for(int j = 4 ; j < Main.obj_list.size() ; j++)			//update index
				{
					Main.obj_list.get(j)[1] = j;
					if(Main.obj_list.get(j)[0] == 8) Create.shop = j;
					else if(Main.obj_list.get(j)[0] == 9) Create.npc = j;
				}
			}
			
			else
			{
				Main.obj_list.remove(spell[1]);
				for(int j = 4 ; j < Main.obj_list.size() ; j++)			//update index
				{
					Main.obj_list.get(j)[1] = j;
					if(Main.obj_list.get(j)[0] == 8) Create.shop = j;
					else if(Main.obj_list.get(j)[0] == 9) Create.npc = j;
				}
			}
		}
		else
		{
			Main.obj_list.remove(spell[1]);
			for(int j = 4 ; j < Main.obj_list.size() ; j++)			//update index
			{
				Main.obj_list.get(j)[1] = j;
				if(Main.obj_list.get(j)[0] == 8) Create.shop = j;
				else if(Main.obj_list.get(j)[0] == 9) Create.npc = j;
			}
		}
	}

}