package gameobjects;

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
private static Hero hero1;
private static Hero hero2;
private static Shop shop;

public static int datei = 1;
public static File fdatei;
public static int next_room;
public static boolean first_init=true;

public static ArrayList<Figure> init() {
Board board = new Board(Main.level, Main.room);
gameobjects.add(0,board);
Create.create_room(Main.level, Main.room);

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
String dat_name="";
int zeilenlaenge =0;
int zeile;
boolean feld= false;

if (shop!=null){
shop.pos_x = -1000;
shop.pos_y = -1000;
}

if (goal!=null){
shop.pos_x = -2000;
shop.pos_y = -2000;
}

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
} catch (Exception e) {
System.out.println("Was? " + e.getCause());
}
if (Create.first_init) {
if (Main.Nr_of_Players==1) {
hero2 = new Hero(0, 0, "hedgehog");
hero2.image = Toolkit.getDefaultToolkit().getImage(Fs.img_pfad+"bg menue.png");
}
gameobjects.add(1,goal);
gameobjects.add(2,hero1);
gameobjects.add(3,hero2);
gameobjects.add(4,shop);
Create.first_init = false;
}
}
}