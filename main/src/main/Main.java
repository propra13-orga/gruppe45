package main;

import local.Fs;
import multiplayer.MultMasterFrame;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import graphics.*;


public class Main{
public static double scale = 0.5;
public static int board_height = 768;
public static int board_width = 1024;
public static int off = 50;						//boarder at the side of bord
public static int Nr_of_Players = 1;			//1 == Singleplayer , 2 == Multiplayer
public static int level = 1;
public static int room = 1;
public static Random rand = new Random();		//variable for random movement
public static boolean run = false;				//movements are made when run == true, then run ist set to false until painted
public static boolean ingame = false;			//ingame == false while in menu
public static boolean shop = false;				//can shop be openend?
public static boolean music = true;				//music on/off
public static boolean go = false;
public static boolean onOff = true;
public static boolean npc = false;
public static boolean reset=true;
public static String player1_name = "Bunny";
public static String player2_name = "Hedgehog";
public static MasterFrame window;

public static ArrayList<int[]> obj_list = new ArrayList<int[]>();	//holds all figures in game
//0 reserved for board
//1 reserved for goal
//2 reserved for player 1
//3 reserved for player 2
//4 reserved for shop



public static void main(String[] args) throws IOException
	{
		Fs.init();
		
		System.out.println("initializing pics");
		
		local.Pics.loadPics();
		
		System.out.println("done");
		
		Game game = new Game();										//creates Game logic		
		
		System.out.println("initializing gameobjects");
		
		local.Create.init();
		
		System.out.println("done");
		System.out.println("initializing room");
		
		game.start();												//starts Game logic
		
		System.out.println("done");
		
		window = new graphics.MasterFrame();	//creates RenderFrame
		
		@SuppressWarnings("unused")
		Gui start = new Gui();										//Starts Gui
		
	
		
		while(onOff)
		{
			if (go == true)											//wait boolean from Gui to start
			{
				Main.ingame = true;
				window.startNow();									//sets MasterFrame
				onOff = false;										//if started once cannot start again
			}
		}
	}
}