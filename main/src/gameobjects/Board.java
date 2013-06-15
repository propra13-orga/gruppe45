package gameobjects;

/*
 *  class creates the board with background image and all objects on the board with their positions
 *  hero, killerbunny, wall and todesbaum inherit from the objekt 
 *  class in this package
 *  
 *  created in Renderer class
 *  reset for a new game in Renderer class
 *  collision detection in calc class
 *
 */

import java.awt.Image;
import java.awt.Toolkit;
import java.io.*;
import java.util.ArrayList;

import local.*;
import main.*;

public class Board extends Figure{
	
	public int block_groesse=50;
	
	public Image bg_image;
	public String datei;
	public File fdatei;
	private int status = 0;	
	public int max_x_blocks = 0;
	public int max_y_blocks = 0;
	public int rand_x = 40;
	public int rand_y = 70;
	public int naechster_raum;
	public String nachricht ="";
	private Goal goal;
	private Hero hero1;
	private Hero hero2;
	private Shop shop;
	static ArrayList<Figure> gameobjects = new ArrayList<Figure>();
	
	public Board(String file){
		super(0,0,0);
		this.initBoard();
		this.pos_x = 0;
		this.pos_y = 0;
		this.naechster_raum=Integer.parseInt(file)+1;
		this.datei = file+".txt";
	}
	
	public static ArrayList<Figure> init() {		
		Board board = new Board("1");
		gameobjects.add(0,board);
		board.create_room("1");
		gameobjects.add(1,board.goal);
		gameobjects.add(2,board.hero1);
		gameobjects.add(3,board.hero2);
		gameobjects.add(4,board.shop);
		//return gameobjects_wo_board;
		return gameobjects;	
	}
		
	private void initBoard(){ // called by all class constructors to set start values
		//this.bg_image =Toolkit.getDefaultToolkit().getImage(Fs.img_pfad+"Su_s BG.png");
		this.max_x_blocks = Main.board_width/this.block_groesse;
		this.max_y_blocks = Main.board_height/this.block_groesse; 
	}
	
	public int create_room(String datei) { // reads level file and creates game objects
		// clears lists with game objects (needed for restarted games)
		//Main.obj_list.clear();
		String zeileninhalt =""; // line content of a level file
		int zeilenlaenge =0;
		int zeile;
		boolean feld= false;
		try {
			this.fdatei = new File(Fs.data_pfad+this.datei);
			//if (this.fdatei.exists()) {
				FileReader fr = new FileReader(Fs.data_pfad+this.datei);
				BufferedReader br = new BufferedReader(fr);
			    zeile=0;
			    do { // reading levelfile line by line
				    zeileninhalt = br.readLine(); 
				    if (zeileninhalt != null) { // empty line
					    zeilenlaenge=zeileninhalt.length();
					    if (zeileninhalt.charAt(0)=='#') { // reading chars of line
					    	feld=true;
					    }
					    if (feld) { // depending on char create objects on board
						    for(int spalte=0; spalte<zeilenlaenge;spalte++){
						    	switch(zeileninhalt.charAt(spalte)) {
						    		case 'w':
						    			gameobjects.add(new Wall(spalte*this.block_groesse, zeile*this.block_groesse));
						    			break;
						    		case 'k':
						    			gameobjects.add(new Fox(spalte*this.block_groesse, zeile*this.block_groesse));
						    			break;
						    		case 't':
						    			gameobjects.add(new Poisonous_Tree(spalte*this.block_groesse, zeile*this.block_groesse));
						    			break;
						    		case '1':
						    			hero1 = new Hero(spalte*this.block_groesse, zeile*this.block_groesse);
						    			break;
						    		case '2':
						    			hero2 = new Hero(spalte*this.block_groesse, zeile*this.block_groesse);
						    			break;						    			
						    		case 's':
						    			shop = new Shop(spalte*this.block_groesse, zeile*this.block_groesse);
						    			break;
						    		case 'z':
						    			goal=new Goal(spalte*this.block_groesse, zeile*this.block_groesse);
						    			break;
						    		default:
						    			// nix
						    	}
						    }
						    zeile++; // next line
					    } else {
		
					    }
				    }
			    } while (zeileninhalt != null); // empty line -> EOF (end of file)
			    br.close();
			//}
		} catch (Exception e) {
			System.out.println("Was? " + e.getMessage());
		}
		return status;
	}

	public void set_board_bg_image(int naechster_raum) {
		switch (naechster_raum % 6) {
			case 1:
				this.bg_image = Toolkit.getDefaultToolkit().getImage(Fs.img_pfad+"Su_s BG.png");
				break;
			case 2:
				this.bg_image = Toolkit.getDefaultToolkit().getImage(Fs.img_pfad+"bg kurve l.png");;
				break;
			case 3:
				this.bg_image = Toolkit.getDefaultToolkit().getImage(Fs.img_pfad+"bg kurve u l.png");;
				break;
			case 4:
				this.bg_image = Toolkit.getDefaultToolkit().getImage(Fs.img_pfad+"Su_s BG.png");;
				break;
			case 5:
				this.bg_image = Toolkit.getDefaultToolkit().getImage(Fs.img_pfad+"bg kurve r.png");;
				break;
			case 6:
				this.bg_image = Toolkit.getDefaultToolkit().getImage(Fs.img_pfad+"bg kurve u r.png");;
				break;
			default:
				break;
		}
	}
}