package main;

import gameobjects.Figure;
import gameobjects.Create;
import graphics.*;
import local.Fs;
import main.GUI;
import movement.Keyboard;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

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
	}
	
	
	public static void main(String[] args) throws IOException{

       ini();
    }

}
