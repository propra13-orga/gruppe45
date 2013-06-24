package gameobjects;

import java.io.IOException;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

/*
this.naechster_raum=Integer.parseInt(file)+1;
this.datei = file+".txt";
*/
import local.Fs;
import main.Main;

public class Create {
	
	static ArrayList<Figure> gameobjects = new ArrayList<Figure>();
	
	private static Goal goal;
	public static Hero hero1;
	public static Hero hero2;
	private static Shop shop;
	public static Board board;
	
	public static int datei = 1;
	public static File fdatei;
	public static int next_room;

	public static ArrayList<Figure> init() {
		board = new Board(Main.level, Main.room);
		Create.create_room(Main.level, Main.room);
	
		//set nr = index in ArrayList
		for(int i = 0 ; i < gameobjects.size() ; i++)
			{
				gameobjects.get(i).nr = i;
			}
		//return gameobjects_wo_board;
		return gameobjects;
	}

public static Image get_board_bg_image(int lvl, int room) {
	Image bg;
	String dat_name="bg_"+String.valueOf(lvl)+"_"+String.valueOf(room)+".png";
	bg = Toolkit.getDefaultToolkit().getImage(Fs.img_pfad+dat_name);
	System.out.println("Neuer Hintergrund:");
	System.out.println(dat_name);
	System.out.println(bg);
	System.out.println(bg.getHeight(null));
	System.out.println(bg.getWidth(null));
	System.out.println("----------");
	return bg;
}


public static ArrayList<Figure> create_room(int lvl, int room) { // reads level file and creates game objects
	String zeileninhalt =""; // line content of a level file
	String dat_name="";
	int zeilenlaenge =0;
	int zeile;
	boolean feld=false;
	if (shop!=null){
		shop.pos_x = -1000;
		shop.pos_y = -1000;
	}
	if (goal!=null){
		shop.pos_x = -2000;
		shop.pos_y = -2000;
	}
	
	gameobjects.clear();
	try {
		fdatei = new File(Fs.data_pfad+dat_name+".txt");
		// if (this.fdatei.exists()) {
		dat_name = String.valueOf(lvl)+"_"+String.valueOf(room);
		FileReader fr = new FileReader(Fs.data_pfad+dat_name+".txt");
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
								gameobjects.add(new Wall(spalte*Board.block_groesse, zeile*Board.block_groesse));
								break;
							case 'k':
								gameobjects.add(new Fox(spalte*Board.block_groesse, zeile*Board.block_groesse));
								break;
							case 't':
								gameobjects.add(new Poisonous_Tree(spalte*Board.block_groesse, zeile*Board.block_groesse));
								break;
							case '1':
								if (hero1!=null) {
									hero1.pos_x = spalte*Board.block_groesse;
									hero1.pos_y = zeile*Board.block_groesse;
								} else {
									hero1 = new Hero(spalte*Board.block_groesse, zeile*Board.block_groesse, "bunny");
								}
								break;
							case '2':
								hero2 = new Hero(spalte*Board.block_groesse, zeile*Board.block_groesse, "hedgehog");
								break;
							case 's':
								if (shop!=null) {
									shop.pos_x =spalte*Board.block_groesse;
									shop.pos_y=zeile*Board.block_groesse;
								} else {
									shop = new Shop(spalte*Board.block_groesse, zeile*Board.block_groesse);
								}
								break;
							case 'z':
								if (goal!=null) {
									goal.pos_x = spalte*Board.block_groesse;
									goal.pos_y = zeile*Board.block_groesse;
								} else {
									goal=new Goal(spalte*Board.block_groesse, zeile*Board.block_groesse);
								}
								break;
							case 'b':
								gameobjects.add(new Boss(spalte*Board.block_groesse, zeile*Board.block_groesse));
								break;
                            case 'i':
                                    // noch ein zufälliges item
                            		int l=(int)(Math.random()*5);
                            		System.out.println(l);
                                    Item i = Item.items.get(l);
                                    i.pos_x=spalte*Board.block_groesse; 
                                    i.pos_y=zeile*Board.block_groesse;
                                    
                                    gameobjects.add((Figure) i.copy());
                                    System.out.println(i.getClass().getSimpleName());
                                    System.out.println(i.image.getWidth(null));
                                    System.out.println(i.image.getWidth(null));
                                    System.out.println("-----------------------------"); 
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
	} catch (IOException e) {
		System.out.println("Fehler: " + e.getMessage());
	}
	if (Main.Nr_of_Players==1) {
		hero2 = new Hero(0, 0, "hedgehog");
		hero2.image = Toolkit.getDefaultToolkit().getImage(Fs.img_pfad+"bg_menue.png");
	}
	board.image = Create.get_board_bg_image(lvl, room);
	gameobjects.add(0,board);
	gameobjects.add(1,goal);
	gameobjects.add(2,hero1);
	gameobjects.add(3,hero2);
	gameobjects.add(4,shop);

		int i = 0;
		for (Figure  figur: gameobjects) {
			System.out.println(i);
			System.out.println(figur.getClass().getSimpleName());
			System.out.println(figur.image);
			System.out.println(figur.image.getWidth(null));
//			System.out.println(figur.image.getHeight(null));
//			System.out.println(figur.pos_x);
			System.out.println(figur.pos_y);
			System.out.println("-------------------");
			i++;
		}
		System.out.println(String.valueOf(i) + " Objekte in der Liste");
		System.out.println("-------------------");
		return gameobjects;
	}
}