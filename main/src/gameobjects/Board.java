package gameobjects;

/*
* class creates the board with background image and all objects on the board with their positions
* hero, killerbunny, wall and todesbaum inherit from the objekt
* class in this package
*
* created in Renderer class
* reset for a new game in Renderer class
* collision detection in calc class
*
*/

import java.awt.Image;
import main.*;

public class Board extends Figure{

public static final int block_groesse=50;

public Image bg_image;
public int max_x_blocks = 0;
public int max_y_blocks = 0;
public int rand_x = 40;
public int rand_y = 70;
public String nachricht ="";

	public Board(int lvl, int room){
		super(0,0,0,Create.get_board_bg_image(lvl, room)); //Überarbeiten....aktuelles bild einfügen
		this.initBoard();
        Item.get_item_list();
	}


	private void initBoard(){ // called by all class constructors to set start values
	//this.bg_image =Toolkit.getDefaultToolkit().getImage(Fs.img_pfad+"Su_s BG.png");
	this.max_x_blocks = Main.board_width/this.block_groesse;
	this.max_y_blocks = Main.board_height/this.block_groesse;
	}
}