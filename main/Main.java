/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.IOException;
import javax.swing.JFrame;

/**
 *
 * @author ProgPra
 */
public class Main{
    
    
    public static void main(String[] args) throws IOException{
        
       SpielObjekte dorf = new SpielObjekte();                  //greift auf alle Gegenstände, NPCs PCs usw zu 
       
       JFrame mainFrame = new JFrame();
       Renderer rendern = new Renderer();
       rendern.setFrame(mainFrame);
       rendern.run();
       
    }
}
