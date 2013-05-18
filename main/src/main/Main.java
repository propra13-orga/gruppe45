/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import grafik.*;
import lokal.*;
import java.io.IOException;
import javax.swing.JFrame;

import spielobjekte.*;

public class Main{
    
    public static void main(String[] args) throws IOException{
       fs.init(); // fs = filesystem. Wo sind wir (und die Bild- und Leveldateien)?
       //menu spielmenu = new menu(); 
       // spielmenu.zeige_menu();
       // GUI1 run = new GUI1();
       // run.testGUI1();
       JFrame mainFrame = new JFrame();
       Renderer rendern = new Renderer();
       rendern.setFrame(mainFrame);
       rendern.run();
       
    }
}

