package main;


/**
 * implements KeyListener and returns Position
 * @author ProgPra
 */

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.util.Scanner;


public class Tastatur implements KeyListener {
    
    
public void keyPressed (KeyEvent e){
 
   if (e.getKeyCode() == KeyEvent.VK_LEFT)  {Zeichnen.figurx -= 7;};          //left arrow key
   if (e.getKeyCode() == KeyEvent.VK_RIGHT) {Zeichnen.figurx += 7;};          //right arrow key
   if (e.getKeyCode() == KeyEvent.VK_UP)    {Zeichnen.figury -= 7;};          //up arrow key
   if (e.getKeyCode() == KeyEvent.VK_DOWN)  {Zeichnen.figury += 7;};          //down arrow key

}

public void keyReleased(KeyEvent event){
    
    /*if (e.getKeyCode() == KeyEvent.VK_LEFT) left = 0;
    if (e.getKeyCode() == KeyEvent.VK_RIGHT) right = 0;
    if (e.getKeyCode() == KeyEvent.VK_UP) up = 0;
    if (e.getKeyCode() == KeyEvent.VK_DOWN) down = 0;
*/}

  public void keyTyped(java.awt.event.KeyEvent e) {
	        // do nothing
  }
}