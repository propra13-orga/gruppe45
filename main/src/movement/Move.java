package movement;

import spielobjekte.*;
import kollision.Koll;

public class Move{ //Bewegung für beliebiges Objekt
	
	public static void up(Objekt figur){
		if((figur.pos_y+1<768) && (if(Koll.koll(figur)){true})){
			figur.pos_y+=1;
			}
	}
	
	public static void down(Objekt figur){
		if(figur.pos_y-1>0){
			figur.pos_y-=1;
		}
	}
	
	public static void right(Objekt figur){
		if(figur.pos_x+1<1024){
			figur.pos_x+=1;
		}
	}
	
	public static void left(Objekt figur){
		if(figur.pos_x-1>0){
			figur.pos_x-=1;
		}
	}
}