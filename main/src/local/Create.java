package local;

import gameobjects.*;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import main.Main;

/**
 * Creates vars to local paths (Fs.img_pfad , Fs.data_pfad)
 * Depending on OS sets the right slashes (in a folder path) 
 * @author Andreas Roth
 * @author Martin Knonsalla
 *
 */

public class Create {
		
	public static int block_groesse=50;
	
	public static Bunny hero1;
	public static Hedgehog hero2;
	public static int npc;
	public static int shop;
	
	public static Figure[] gameobjects = new Figure[14];
	
	static ArrayList<int[]> temp = new ArrayList<int[]>();
	
	//initializes gameobjects
	public static void init(){
	
		gameobjects[0] = new Board();
		gameobjects[1] = new Goal();
		gameobjects[2] = (hero1 = new Bunny());
		gameobjects[3] = (hero2 = new Hedgehog());
		gameobjects[4] = new Wall();
		gameobjects[5] = new Poisonous_Tree();
		gameobjects[6] = new Fox();
		gameobjects[7] = new Boss();
		gameobjects[8] = new Shop();
		gameobjects[9] = new Npc();
		gameobjects[10] = new Item();
		gameobjects[11] = new Blob();
		gameobjects[12] = new Fireball();
		gameobjects[13] = new Carrot_Tree();
	}
	
	// reads level file and creates game objects
	
	/**
	 * Reading room from file depending on Main.lvl and Main.room
	 * Switches between own created lvls (file prefix c_) and 'usual' game  
	 */
	
	public static void create_room(){
		String zeileninhalt =""; // line content of a level file
		String custom = "";
		int zeilenlaenge =0;
		int zeile;
		
		if(Main.custom) custom = "c_";
		try
		{
			temp.clear();
			temp.add(0,new int[]{0,0,0,0,0,0});
			temp.add(1,new int[]{0,0,0,0,0,0});
			temp.add(2,new int[]{0,0,0,0,0,0});
			temp.add(3,new int[]{3,3,-2000,-2000,0,0});
			npc = 0;
			shop = 0;
			
			gameobjects[4].height = gameobjects[4].getPic(0).getHeight(null);
			gameobjects[4].width = gameobjects[4].getPic(0).getWidth(null);
			
			BufferedReader br = new BufferedReader(new FileReader(Fs.data_pfad+custom+String.valueOf(Main.level)+"_"+String.valueOf(Main.room)+".txt"));
			zeile=0;
			
			// reading levelfile line by line , empty line -> EOF (end of file)
			do
			{
				zeileninhalt = br.readLine();
				//if line valid
				if(zeileninhalt != null)
					{
					if (zeileninhalt.charAt(0) == '#')
					{
						zeilenlaenge = zeileninhalt.length();
					
						for(int spalte = 1; spalte < zeilenlaenge ; spalte++)
						{
							switch(zeileninhalt.charAt(spalte)){
							
								//goal
								case 'z':	temp.set(1, new int[]{1,1,spalte*block_groesse, zeile*block_groesse,0,0});
											break;
										
								//bunny
								case '1':	temp.set(2, new int[]{2,2,spalte*block_groesse, zeile*block_groesse,hero1.hp,2});
											break;
										
								//hedgehog
								case '2':	if(Main.Nr_of_Players == 2)
											{
												temp.set(3, new int[]{3,3,spalte*block_groesse, zeile*block_groesse,hero2.hp,2});
											}
											
											break;
										
								//wall
								case 'w':	temp.add(new int[]{4,temp.size(),spalte*block_groesse, zeile*block_groesse,0,0});
											break;
										
								//poisonous_tree
								case 't':	temp.add(new int[]{5,temp.size(),spalte*block_groesse, zeile*block_groesse,0,0});
											break;
										
								//fox
								case 'k':	temp.add(new int[]{6,temp.size(),spalte*block_groesse, zeile*block_groesse,gameobjects[6].getHp(),2});
											break;
										
								//boss
								case 'b':	temp.add(new int[]{7,temp.size(),spalte*block_groesse, zeile*block_groesse,gameobjects[7].getHp(),2});
											break;
										
								//shop
								case 's':	temp.add(new int[]{8,temp.size(),spalte*block_groesse, zeile*block_groesse,0,0});
											shop = temp.size()-1;
											break;
										
								//npc
								case 'c':	temp.add(new int[]{9,temp.size(),spalte*block_groesse, zeile*block_groesse,0,0});
											npc = temp.size()-1;
											break;
							
								//mp-pot
								case '4':	temp.add(new int[]{10,temp.size(),spalte*block_groesse, zeile*block_groesse,0,4});
											break;
							
								//hp-pot
								case '5':	temp.add(new int[]{10,temp.size(),spalte*block_groesse, zeile*block_groesse,0,5});
											break;
							
								//blob
								case '6':	temp.add(new int[]{10,temp.size(),spalte*block_groesse, zeile*block_groesse,0,6});
											break;
							
								//fireball
								case '7':	temp.add(new int[]{10,temp.size(),spalte*block_groesse, zeile*block_groesse,0,7});
											break;
							
								//flower
								case '8':	temp.add(new int[]{10,temp.size(),spalte*block_groesse, zeile*block_groesse,0,8});
											break;
							
								//bug
								case '9':	temp.add(new int[]{10,temp.size(),spalte*block_groesse, zeile*block_groesse,0,9});
											break;
							
								//snail
								case 'q':	temp.add(new int[]{10,temp.size(),spalte*block_groesse, zeile*block_groesse,0,10});
											break;
											
								//carrot_tree
								case 'm':	temp.add(new int[]{13,temp.size(),spalte*block_groesse, zeile*block_groesse,0,2});
											break;
							}
						}
					}
				}
				zeile++; // next line
			}
			while (zeileninhalt != null);
			//close filestream
			br.close();
			//copy to obj_list
			Main.obj_list = temp;
		} catch (IOException e) {
			System.out.println("Fehler: " + e.getMessage());
		}
	}

	//restores Main.obj_list if player dies

	/**
	 * restores obj_list like in the room file
	 */
	public static void restore(){
		Main.obj_list = temp;
	}
}