package gameobjects;

import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import local.Fs;

public class Item extends Figure{

public String itemname;
public int zustand; // 0-99%
public int dauer; // 0=einmalig, 1=dauerhaft
public int angelegt; // 0=im Rucksack, 1=angelegt
public float faktor_schaden;
public float faktor_schutz;
public float faktor_lebenspunkte;
public float faktor_geschwindigkeit;
public int in_shop = 0; // Anzahl im Shop verfügbar
public String info ="";
public int min_level;
public int preis;
public int anzahl=0;

public static ArrayList<Item> items = new ArrayList<Item>();

public Item() {
super(4,0,0,Toolkit.getDefaultToolkit().getImage(Fs.img_pfad+"bg_menue.png"));
}

public static ArrayList<Item> get_item_list () {
String zeileninhalt =""; // line content of a level file
String dat_name="";
Item item;
int zeilenlaenge =0;
int zeile=0;
if (items.size()==0) {
String[] splitResult;
FileReader fr;
try {
fr = new FileReader(Fs.data_pfad+"items.txt");
BufferedReader br = new BufferedReader(fr);
do { // reading levelfile line by line
zeileninhalt = br.readLine();
if (zeileninhalt != null) { // empty line
if (zeileninhalt.charAt(0) != '#') {
splitResult = zeileninhalt.split(";");
item = new Item();
item.min_level= Integer.parseInt(splitResult[0]);
item.preis= Integer.parseInt(splitResult[1]);
item.itemname= splitResult[2];
//item.helps_against= splitResult[3]; // wenn nur gegen bestimmte Gegner nützlich
item.faktor_schaden= Integer.parseInt(splitResult[4]);
item.faktor_schutz= Integer.parseInt(splitResult[5]);
item.faktor_lebenspunkte= Integer.parseInt(splitResult[7]);
item.faktor_geschwindigkeit= Integer.parseInt(splitResult[6]);
item.in_shop= Integer.parseInt(splitResult[8]);
item.info= splitResult[9];
item.image= Toolkit.getDefaultToolkit().getImage(Fs.img_pfad+splitResult[10]);
Item.items.add(item);
zeile++; // next line
System.out.println(item.itemname + " erschaffen. Bild:"+splitResult[10]);
}
}
} while (zeileninhalt != null); // empty line -> EOF (end of file)
br.close();
} catch (IOException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}
}
// for (Item ding :Item.items){
// System.out.println(ding.itemname);
// System.out.println(ding.preis);
// System.out.println(ding.min_level);
// System.out.println(ding.faktor_schaden);
// System.out.println(ding.faktor_schaden);
// System.out.println("---------------");
// }
return items;
}

public Item copy() {
Item itemcopy;
itemcopy = new Item();
itemcopy.min_level= this.min_level;
itemcopy.itemname= this.itemname;
itemcopy.zustand= this.zustand;
itemcopy.dauer= this.dauer;
itemcopy.angelegt= this.angelegt;
itemcopy.faktor_schaden= this.faktor_schaden;
itemcopy.faktor_schutz= this.faktor_schutz;
itemcopy.faktor_lebenspunkte= this.faktor_lebenspunkte;
itemcopy.faktor_geschwindigkeit= this.faktor_geschwindigkeit;
itemcopy.in_shop= this.in_shop;
itemcopy.info= this.info;
itemcopy.image= this.image;
itemcopy.pos_x = this.pos_x;
itemcopy.pos_y = this.pos_y;
itemcopy.image = this.image;

return itemcopy;
}


}