package main;

import gameobjects.Figure;
import gameobjects.Board;
import graphics.*;
import local.Fs;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFrame;

public class Main{
	
	public static double scale = 0.5;
	public static int board_height = 768;
	public static int board_width = 1024;
	public static ArrayList<Figure> obj_list = new ArrayList<Figure>();		//holds all figures in game
																			//0 reserved for board
																			//1 reserved for goal
																			//2 reserved for player 1
																			//3 reserved for player 2
																			//4 reserved for shop
	public static void main(String[] args) throws IOException{
       Fs.init();
	   obj_list = Board.init();
       JFrame mainFrame = new JFrame();
       Renderer rendern = new Renderer();
       rendern.setFrame(mainFrame);
       rendern.run();
       
    }

}
