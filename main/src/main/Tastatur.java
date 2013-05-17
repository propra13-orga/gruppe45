package main;


/**
 * implements KeyListener and returns Position
 * @author ProgPra
 */

import g.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Scanner;

public class Tastatur implements KeyListener {

	int key;
	
	public void keyPressed (KeyEvent e){
		key = e.getKeyCode();
		if (calc.ingame) {
			switch(key) {
				case KeyEvent.VK_LEFT:// int 37
					calc.P1_richtung_x -= 1;
					break;
				case KeyEvent.VK_RIGHT: // int 18
					calc.P1_richtung_x += 1;
					break;
				case KeyEvent.VK_UP: // int 38
					calc.P1_richtung_y -= 1;
					break;
				case KeyEvent.VK_DOWN: // int 40
					calc.P1_richtung_y += 1;
					break;
				default:
					// nix			
			}
		}
		if (key== KeyEvent.VK_ESCAPE) {
			calc.ingame = false;
		}
	}
	
	public void keyReleased(KeyEvent e){
		key = e.getKeyCode();
	    if (key == KeyEvent.VK_LEFT) calc.P1_richtung_x = 0;
	    if (key == KeyEvent.VK_RIGHT) calc.P1_richtung_x = 0;
	    if (key == KeyEvent.VK_UP) calc.P1_richtung_y = 0;
	    if (key == KeyEvent.VK_DOWN) calc.P1_richtung_y = 0;
	}
	
	  public void keyTyped(java.awt.event.KeyEvent e) {
		        // notwendig, da abstract inherited
	  }
}