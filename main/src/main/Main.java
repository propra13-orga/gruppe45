package main;

import gameobjects.Create;
import gameobjects.Figure;
import local.Fs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Main{
	public static double scale = 0.5;
	public static int board_height = 768;
	public static int board_width = 1024;
	public static int Nr_of_Players = 1;					//1 == Singleplayer , 2 == Multiplayer
	public static int level =1;
	public static int room = 1;
	public static Random rand = new Random();				//variable for random movement
	public static boolean run = false;						//movements are made when run == true, then run ist set to false until painted
	public static boolean ingame = true;					//ingame == false while in menu
	
	
	public static ArrayList<Figure> obj_list = new ArrayList<Figure>();		//holds all figures in game
																			//0 reserved for board
																			//1 reserved for goal
																			//2 reserved for player 1
																			//3 reserved for player 2
																			//4 reserved for shop
	
	public graphics.MasterFrame window;
	
	public static void main(String[] args) throws IOException{
		Fs.init();
//		obj_list = Create.init();
		Game game = new Game();
		game.start();
		graphics.MasterFrame window = new graphics.MasterFrame();	
		
		window.startNow();
    }

}