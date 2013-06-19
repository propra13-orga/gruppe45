package graphics;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.swing.*;
import java.net.*;
import javax.imageio.ImageIO;

/*Player Left creates an Objekt of Lefthandside Panel making the statusbar
 * 
 */

class PlayerRight extends Panel{							
		JButton button1, button2;
		Image icon1,icon2,lives;
		JLabel labelHP,labelMP,labelEP, icon, iconDef, labelBack, iconArm, life1,life2,life3, life4, life5, text1, text2, text3;
		static int count=0;
		public int hp=200, mp=200, ep=200;								//init status labels
	
	public PlayerRight(){
		super();												//new Panel Object created
				
		setLayout(null);										//no Layput Manager needed, fix position
		
		//change points first Paramater = points, 2nd Parameter = index
	//	setPoints(5,1);//needs HP value							//HP index = 1
	//	setPoints(100,2);//need MP value						//MP index = 2
	//	setPoints(15,3);//needs EP value						//EP index = 3
				
		button1 = new JButton("Shop");							//Button for shop created
		button1.setBackground(Color.green);
		button1.setBounds(830,20,100,30);						//Size init
		
		//ShopButton klick-event
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });
		//ShopButton klick-event end

		//Background Label
		labelBack = new JLabel();
		labelBack.setOpaque(true);
		labelBack.setBackground(Color.black);
		labelBack.setForeground(Color.yellow);
		labelBack.setBounds(590,0,440,85);
		
		//3 Descripton Label
		text1 = new JLabel("Player 2");
		text2 = new JLabel("Defense");
		text3 = new JLabel("Armor");
		
		text1.setBounds(615, -10, 100, 50);
		text2.setBounds(680, 0, 100, 50);
		text3.setBounds(750, 0, 100, 50);

		
		
		//3 Status Label
		labelHP = new JLabel (("HPs: ")+(String.valueOf(new Integer(getPoints(1)))));							//label fpr HP's
		labelMP = new JLabel(("MPs: ")+(String.valueOf(new Integer(getPoints(2)))));							//Label for MP's
		labelEP = new JLabel(("EPs: ")+(String.valueOf(new Integer(getPoints(3)))));							//Label for Experiment Points

		labelHP.setForeground(Color.white);						//White Font
		labelMP.setForeground(Color.white);						//White Font
		labelEP.setForeground(Color.white);						//White Font
		
		//3 Picture Label Player, Defensive, Armor
		loadIcon();												//reads Image File
		icon = new JLabel(new ImageIcon(icon1));				//Image on Label Player Icon
		iconDef = new JLabel(new ImageIcon(icon1));				//Image on Label Defense Icon	
		iconArm = new JLabel(new ImageIcon (icon1));			//Image on Label Armor  Icon
		
		//3 Lives Label
		life1 = new JLabel(new ImageIcon(lives));
		life2 = new JLabel(new ImageIcon(lives));
		life3 = new JLabel(new ImageIcon(lives));
		life4 = new JLabel(new ImageIcon(lives));
		life5 = new JLabel(new ImageIcon(lives));
		
		
		life1.setBounds(600,59,12,12);							//live image 15pix+15pix needed
		life2.setBounds(620,59,12,12);	
		life3.setBounds(640,59,12,12);	
		life4.setBounds(660,59,12,12);
		life5.setBounds(680,59,12,12);
		
		labelHP.setBounds(950, 0, 100, 50);					
		labelMP.setBounds(950, 15, 100, 50);
		labelEP.setBounds(950, 30, 100, 50);
		
		icon.setBounds(595,-20,100,100);							//Icon 1st Player
		iconDef.setBounds(655, -20, 100, 100);					//Icon for defense
		iconArm.setBounds(715,-20,100,100);						//Icon for armor
		
	
		
	}

void setPoints(int point, int index)
{
	switch (index){
	case 1:	this.hp = point; break;
	case 2: this.mp = point; break;
	case 3: this.ep = point; break;
	}
}

public int getPoints(int index)
{
	switch (index){
	case 1:	return this.hp; 
	case 2: return this.mp; 
	case 3:	return this.ep; 
	default: return (0);									//this should never happen
	}
	
}
	


	
public void loadIcon(){
		
		try
		{
			icon1 = ImageIO.read(new File(local.Fs.img_pfad+"lives_bunny.png"));		
			icon2 = ImageIO.read(new File(local.Fs.img_pfad+"bunny_l.png"));
			lives = ImageIO.read(new File (local.Fs.img_pfad+"heart.jpg"));}
			
		catch(IOException e){	//TODO Auto-generated block		}
		 }}
	
//The Shopevent


public void button1ActionPerformed(java.awt.event.ActionEvent evt) {        
	if (count == 0){							//counts open shop windows
	ShopFrame frame = new ShopFrame();			//creates new shop
	frame.setVisible(true); 			
	count = 1;}									//shop window created = 1
}
	
//Event finish

}
