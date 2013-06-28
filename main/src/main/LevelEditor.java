package main;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;


public class LevelEditor extends JFrame {
	
	editorPanel start = new editorPanel();				//create Gui Elements
	JLayeredPane lpane;
	JLabel text;
	
	public LevelEditor(){
		
		setTitle("Du wolltest ein Fenster");
		setSize(600, 600);							//Window Size
		setResizable(false);						//Window frame not resizable
		setAlwaysOnTop(true);
		this.setLayout(null);

		lpane =  getLayeredPane();
		lpane.add(text, 			 new Integer(10));
		
		this.setVisible(true);
	}
 
 public class editorPanel extends JPanel
 	{
	 	public editorPanel()
	 	{	
	 		text = new JLabel("Hier ist Dein Fenster ;-) ");
	 		text.setBounds(100, 200, 200, 30);
	 }
 	}
}
