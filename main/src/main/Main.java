package main;

import gameobjects.Create;
import gameobjects.Figure;
import graphics.Renderer;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JInternalFrame;

import local.Fs;

//import local;

public class Main{
	public static JInternalFrame renderFrame = new JInternalFrame();
	public static double scale = 0.5;
	public static int board_height = 768;
	public static int board_width = 1024;
	public static ArrayList<Figure> obj_list = new ArrayList<Figure>();		//holds all figures in game
																			//0 reserved for board
																			//1 reserved for goal
																			//2 reserved for player 1
																			//3 reserved for player 2
																			//4 reserved for shop
	public static Game game;
	
	public static void dauerhaft(){
		 
		Renderer rendern = new Renderer();
		rendern.start();
		game = new Game();
//		game.manage();

	}
	
	public static void ini(){
	       Fs.init();
		   obj_list = Create.init();
		   //print_obj_list();
	}
	
	public static void print_obj_list() {
		int i =0;
		for (Figure figur: obj_list) {
			System.out.println(i);
			System.out.println(figur.getClass().getSimpleName());
			System.out.println(figur.pos_x);
			System.out.println(figur.pos_y);
			System.out.println(figur.image.getWidth(null));
			System.out.println(figur.image.getHeight(null));
			System.out.println("-------------------------");
			i++;
		}
		System.out.println("--- Ende der Liste -------");
		
	}
	
	public static void main(String[] args) throws IOException{

       ini();
    }

}