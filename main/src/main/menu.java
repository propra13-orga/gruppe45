package main;

import javax.swing.JButton;

public class menu {
		public JButton start;
		public JButton ende;
		
	public menu() {
		start = new JButton("Start");
		ende = new JButton("Beenden");
		this.verstecke_menu();
	}
	
	public void zeige_menu (){
		start.setVisible(true);
		ende.setVisible(true);
	}
	
	public void verstecke_menu (){
		start.setVisible(false);
		ende.setVisible(false);
	}

}
