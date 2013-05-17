/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import g.*;
import l.*;
import o.*;
import java.io.IOException;
import javax.swing.JFrame;

public class Main{
    
    public static void main(String[] args) throws IOException{
       fs.init();
       JFrame mainFrame = new JFrame();
       Renderer rendern = new Renderer();
       rendern.setFrame(mainFrame);
       rendern.run();
 
    }
}

