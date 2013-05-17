import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

class move {

    //Offset (Bildgrößen Wand(off) Spielfigur(player))
    final int off = 32;
    final int player = 16;
    final int step = 1;

    static boolean koll_left(){		//Kollision links
	if(("PlayerX"-step)<off) return false;
	else return true;
    }
    static boolean koll_right(){		//Kollision rechts
	if(("PlayerX"+step)>(1024-off-player)) return false;
	else return true;
    }
    static boolean koll_down(){		//Kollision unten
    	if(("playerY"-step)<(off)) return false;
    	else return true;
    }
    static boolean koll_up(){			//Kollision oben
    	if(("playerY"+step)>(768-off-player)) return false;
    	else return true;
    }
}

public class Movement {
    addkeylistener
    static int key = e.getKeyCode();

    static void movement(){
    if (key == KeyEvent.VK_W){		//Bewegung nach oben
            if(move.koll_up()) setPlayerY+=step;
    }
    else if(key == KeyEvent.VK_A){		//Bewegung nach links
            if(move.koll_left()) setPlayerX-=step;
    }
    else if(key == KeyEvent.VK_S){		//Bewegung nach unten
            if(move.koll_down()) setPlayerY-=step;
    }
    else if(key == KeyEvent.VK_D){		//Bewegung nach rechts
            if(move.koll_right()) setPlayerX+=step;
    }
    }
}