package movement;

import spielobjekte.*;

public class move{ //Bewegung für beliebiges Objekt
	
	public static void up(objekt figur){
		if(figur.pos_y+1<768){
			figur.pos_y+=1;
			}
	}
	public static void down(objekt figur){
		if(figur.pos_y-1<0){
			figur.pos_y-=1;
		}
	}
	public static void right(objekt figur){
		if(figur.pos_x+1<1024){
			figur.pos_x+=1;
		}
	}
	public static void left(objekt figur){
		if(figur.pos_x-1<0){
			figur.pos_x-=1;
		}
	}
}