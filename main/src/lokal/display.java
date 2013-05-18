package lokal;

import java.awt.*;
import javax.swing.JFrame;

public class display {
	public static Dimension dim;
	public static int x;
	public static int y;
	public static int window_x;
	public static int window_y;
	
	public display () {	}
	
	public static void init(JFrame mainFrame){
		dim = Toolkit.getDefaultToolkit().getScreenSize();
		x = (int)dim.getWidth();
		y = (int)dim.getHeight();
		window_x =  mainFrame.getWidth();
		window_y = mainFrame.getHeight();
	}
}
