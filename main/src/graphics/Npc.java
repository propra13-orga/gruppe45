package graphics;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JLabel;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.io.IOException;
import java.io.File;
import javax.swing.*;
import javax.imageio.ImageIO;
import local.Fs;
import main.Main;

import java.awt.*;


public class Npc extends JFrame 
	{
		private int height = Main.board_height;
		private int width = Main.board_width;
		
		Image cave, mole;
		
		JLabel background, moleLabel, textLabel;
		JLayeredPane pane;
		JButton okButton, ok2Button;
		private int level = main.Main.level;
		private String text, text1_story, text1_1, text1_3, text2_1, text2_3, text3_1, text3_3, textElse;
		
		public Npc()
			{	
				loadIcon();
				MasterFrame.set=true;
				Main.ingame = false;
			
				JPanel NpcPanel1 = new NpcPanel1();
				
				this.setSize(400, 400);
				this.setLocation((height/2)-200, (width/2)-200);
				this.setResizable(false);
				this.setLayout(null);
				this.setUndecorated(true);
				
				pane =  getLayeredPane();
				
				pane.add(background, new Integer(0));
				pane.add(moleLabel, new Integer(10));
				pane.add(textLabel, new Integer(10));
				pane.add(ok2Button, new Integer(10));
				pane.add(okButton, new Integer(10));
				
				this.setVisible(true);
			
			}
		
		public class NpcPanel1 extends JPanel
		{
			public NpcPanel1()
			{
				//Background
				background = new JLabel(new ImageIcon(local.Pics.cave));
				background.setBounds(0, 0, 400, 400);
				background.setOpaque(true);
				background.setBackground(Color.black);
				
				//Label mole
				moleLabel = new JLabel(new ImageIcon(local.Pics.mole));
		 		moleLabel.setBounds(280, 250, 110, 150);

			 		
			 		//text content for different level
			 		
			 		
			 		text1_story= "<html> <FONT COLOR=#FAFAD2>"+ 
			 				"Herzlich Willkommen im Lucky Bunny ich bin <I>Mole</I>.<p> <p>"+
			 						"Ich erz&auml;hle euch nun, wie es zum Abenteuer des Hasen kam.<p> <p>"+
			 						"Der Hase startete sein Abenteuer fr&uuml;h am Morgen. "+
			 						"Er steht jeden morgen fr&uuml;h auf um eine seiner k&ouml;stlichen Karotten zu verspeisen. Aber oh Schreck alle Karotten waren weg." +
			 						"Nicht eine einzige war <p> &uuml;brig geblieben und anfangs wusste er nicht was passiert war." +
			 						"Gestern als er zu Bett ging war noch alles in Ordnung. <p> <p> Da entdeckte er einen Brief."+
			 						"Er war an ihn adressiert und es stand <p> dort geschrieben: 'Ich freue mich dir mitteilen zu k&ouml;nnen, dass ich es notwendig"+ 
			 						"fand alle Karotten an mich zu nehmen. Ich danke dir f&uuml;r <p> das bevorstehende Festmahl.' Unterschrieben hatte niemand <p> anderes als das Große Weiße Kaninchen.</FONT></html>";
			 		 
			 		
			 		text1_1 ="<html> <FONT COLOR=#FAFAD2> 	 " +
			 				"Ich werde stehe dir auf der anstrengenden Reise gerne zur Seite f&uuml;r eine entsprechende Gegenleistung."+
			 				"Um zu laufen benutze die Pfeiltasten, wie du den Shop &ouml;ffnest hast du ja schon <p> herausgefunden (mit S). <p> <p> "+
			 				"H&uuml;te Dich vor den F&uuml;chsen, sie sind sehr hungrig und auf der Suche nach einem leckerem Gaumenschmau&szlig;.<p><p>" +
			 				"Wenn du mir eine Schnecke mitbringst werde ich dir daf&uuml;r etwas ganz besonderes schenken. <p><p>" +
			 				
			 				"Zu passender Gelegenheit werde ich Dich aufsuchen. Bis dahin w&uuml;nsche ich Dir viel Gl&uuml;ck!</FONT></html>";
			 				//For this first Quest you need to bring a snail to the Mole which is found in one of the next rooms(if you can´t find it in the second put annother one in the third, so you don´t need to restart the game)
			 				//As a reward the Mole will give you the Fireball technique.
			 		text1_3 ="<html> <FONT COLOR=#FAFAD2> : Dankesch&ouml;n! <p>" +
			 				"Ich weiß zwar nicht was das genau ist, und wof&uuml;r man sie braucht . . . aber ich wollte schon immer eine haben. <p>"+
			 				"Nimm diesen Zauber als Zeichen meines Dankes.<p>"+
			 				"Ich &uuml;berreiche Dir hiermit den magischen Feuerball. Diesen Zauber kannst Du durch die Leertaste auf Deine Gegner anwenden."+
			 				"Du ben&ouml;tigst Mana um den Zauber anwenden zu k&ouml;nnen.<p><p>" +
			 				"Sammle Mana Items in der Umgebung, wenn Du genug K&auml;fer hast kannst Du auch gern im Shop mit mir tauschen.</FONT></html>";
			 				
			 		text2_1 ="<html> <FONT COLOR=#FAFAD2> Wie ich sehe konntest Du den F&uuml;chsen entkommen. Doch sei weiterhin wachsam.<p> <p>" +
			 				"Wenn Du Erfahrung sammeln willst kannst Du mit <p> ihnen  k&auml;mpfen. Dr&uuml;cke die Leertaste f&uuml;r einen Angriff. " +
			 				"Jeder besiegte Fuchs bringt Dir Erfahrungspunkte, doch denke daran dass Du nicht unsterblich bist. <p><p>" +
			 				"Sammle herumliegende Items auf um neue Energie zu tanken und Deinen HP Wert wieder zu erh&ouml;hen. " +
			 				"Du kannst diesen Wert jederzeit &uuml;ber die Statusleiste im linken unteren Rand erkennen.</FONT></html>";
			 				//Put a Poisontree over the exit, so that you can only pass when the Quest is fulfilled and you have spoken to Mole
			 				//For this Quest you need to kill all foxes. 
			 				//As a reward the Mole will destroy or change the Poisontree at the end of the Level.
			 		text2_3 ="<html> <FONT COLOR=#FAFAD2> Vielen Dank! Jetzt f&uuml;hle ich mich sicherer. <p> <p>" +
			 				 "Lass mich dir zum Dank helfen! </FONT></html>";			 		
			 		text3_1 ="<html> <FONT COLOR=#FAFAD2> Du machst tolle Fortschritte <p><p>" +
			 				"Gratulation! <p><p>" +
			 				"Ich brauche aber noch einmal deine Hilfe. Das Große Weiße Kaninchen hat mein Buch gestohlen. <p>" +
			 				"Solltest du es finden bring es mir bitte zur&uuml;ck.<p><p>"+
			 				"Aber sei vorsichtig, es ist sehr stark. </FONT></html>";
			 		text3_3 ="<html> <FONT COLOR=#FAFAD2> Ich kann dir gar nicht genug danken!! <p><p>"+
			 				 "Ich hatte es fast ausgelesen, als es mir entwendet wurde. " +
			 				 "Nun kann ich es endlich zu Ende lesen.</FONT></html>";
			 		textElse = "<html> <FONT COLOR=#FAFAD2> Leider kann ich Dir gerade nicht helfen</FONT></html>";
			 	
			 		ok2Button = new JButton("weiter");
			 		ok2Button.setBounds(50, 350, 100, 30);
			 			 		
			 		switch (level)//checks which level and sets appropiate text
			 		{
			 			case 1:		 text = text1_story;break;
			 			case 2:		 text = text2_1; ok2Button.setVisible(false);break;
			 			case 3:		 text = text3_1;break;
			 			default:	 text = textElse; break;
			 		}
			 		
			 		textLabel = new JLabel ();			 					 		
			 		textLabel.setText(text);
			 		
			 		textLabel.setBounds(10,-80,398,400);
			 		
			 		
			 		okButton = new JButton("OK");
			 		okButton.setBounds(50, 350, 100, 30);
			 		okButton.addActionListener(new java.awt.event.ActionListener() {
			            public void actionPerformed(java.awt.event.ActionEvent evt) {		
			            	main.Main.ingame = true;			//continue game
			                dispose();         	               //close only ShopFrame
			            }
			            
			        
			        });
			 		

			 		
			 		ok2Button.addActionListener(new java.awt.event.ActionListener() {
			            public void actionPerformed(java.awt.event.ActionEvent evt) {		//push the button
			            	textLabel.setText(text1_1);
			           //     dispose();         	               //close only ShopFrame
			            ok2Button.setVisible(false);
			            }
			            
			        
			        });
			 		
				}
			}
		
		
		
		public void loadIcon(){
			
			try
				{
					cave = ImageIO.read(new File(local.Fs.img_pfad+"cave.jpg"));		
					mole = ImageIO.read(new File (local.Fs.img_pfad+"shop_active.png"));
					//		lives = ImageIO.read(new File (local.Fs.img_pfad+"heart.jpg"));}
				}
			catch(IOException e)
				{	
				System.out.println("Fehler in NpcLevel1 loadImage");	
				}
			 }

   
	
    
	} 
