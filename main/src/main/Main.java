package main;

import gameobjects.Figure;
import graphics.*;

import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFrame;

public class Main{
	
	public static ArrayList<Figure> obj_list = new ArrayList<Figure>();		//holds all figures in game
																			//0 reserved for goal
																			//1 reserved for rabbit
																			//2 reserved for hedgehog

	public static void main(String[] args) throws IOException{
        
       JFrame mainFrame = new JFrame();
       Renderer rendern = new Renderer();
       rendern.setFrame(mainFrame);
       rendern.run();
       
    }

}
