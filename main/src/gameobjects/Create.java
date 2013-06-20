package gameobjects;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import local.Fs;
import main.Game;
/*
this.naechster_raum=Integer.parseInt(file)+1;
this.datei = file+".txt";
*/
import main.Main;

public class Create {

static ArrayList<Figure> gameobjects = new ArrayList<Figure>();

private static Goal goal;
private static Hero hero1;
private static Hero hero2;
private static Shop shop;

public static int datei = 1;
public static File fdatei;
public static int next_room;

public static ArrayList<Figure> init() {
Board board = new Board(Main.game.level, Main.game.room);
gameobjects.add(0,board);
create_room(Main.game.level, Main.game.room);
if (Main.game.Nr_of_Players==1) {
hero2 = new Hero(0, 0, "hedgehog");
hero2.image = Toolkit.getDefaultToolkit().getImage(Fs.img_pfad+"bg menue.png");
}
gameobjects.add(1,goal);
gameobjects.add(2,hero1);
gameobjects.add(3,hero2);
gameobjects.add(4,shop);

//set nr = index in ArrayList
for(int i = 0 ; i < gameobjects.size() ; i++)
{
gameobjects.get(i).nr = i;
}
//return gameobjects_wo_board;
return gameobjects;
}

public static Image set_board_bg_image(int lvl, int room) {
Image bg;
String dat_name="bg_"+String.valueOf(lvl)+"_"+String.valueOf(room)+".png";
bg = Toolkit.getDefaultToolkit().getImage(Fs.img_pfad+dat_name);
return bg;
}

public static void create_room(int lvl, int room) { // reads level file and creates game objects
// clears lists with game objects (needed for restarted games)
//Main.obj_list.clear();
String zeileninhalt =""; // line content of a level file
int zeilenlaenge =0;
int zeile;
boolean feld= false;
try {
fdatei = new File(Fs.data_pfad+datei+".txt");
// if (this.fdatei.exists()) {
FileReader fr = new FileReader(Fs.data_pfad+datei+".txt");
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
hero1 = new Hero(spalte*Board.block_groesse, zeile*Board.block_groesse, "bunny");
break;
case '2':
hero2 = new Hero(spalte*Board.block_groesse, zeile*Board.block_groesse, "hedgehog");
break;
case 's':
shop = new Shop(spalte*Board.block_groesse, zeile*Board.block_groesse);
break;
case 'z':
goal=new Goal(spalte*Board.block_groesse, zeile*Board.block_groesse);
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
}

}