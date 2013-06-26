package graphics;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JLabel;
import javax.swing.*;
import main.Main;

import java.awt.*;


public class Npc extends JFrame 
	{
		private int height = Main.board_height;
		private int width = Main.board_width;
		
//		Image cave, mole;
		
		JLabel background, moleLabel, textLabel;
		JLayeredPane pane;
		JButton okButton;
		private int level = main.Main.level;
		private String text, text1, text2, text3, textElse;
		
		public Npc()
			{	
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
			 		text1 ="<html> <FONT COLOR=#FAFAD2> 	Herzlich Willkommen im Lucky Bunny ich bin <I>Mole the Hole</I>.<p> <p> " +
			 				"Ich werde Dich auf der anstrengenden Reise begleiten und Dich mit wertvollen Tips versorgen."+
			 				"Ich wohne unter der Erde und mag kein Sonnenlicht. <p><p>" +
			 				"Am liebsten esse ich K&auml;fer. Wenn Du welche findest komm' einfach mal an meinem H&uuml;gel vorbei " +
			 				"dann kann ich Dir bestimmt einen guten Tausch anbieten.<p><p> " +
			 				"H&uuml;te Dich vor den F&uuml;chsen, sie sind sehr hungrig und auf der Suche nach einem leckerem Gaumenschmau&szlig;.<p><p>" +
			 				"Zu passender Gelegenheit werde ich Dich wieder aufsuchen. Bis dahin w&uuml;nsche ich Dir viel Gl&uuml;ck!</FONT></html>";
			 		text2 ="<html> <FONT COLOR=#FAFAD2> Wie ich sehe konntest Du den F&uuml;chen entkommen. Doch sei weiterhin wachsam.<p> <p>" +
			 				"Wenn Du Erfahrung sammeln willst kannst Du mit <p> ihnen  k&auml;mpfen. Dr&uuml;cke die Leertaste f&uuml;r einen Angriff. " +
			 				"Jeder besiegte Fuchs bringt Dir Erfahrungspunkte, doch denke daran dass Du nicht unsterblich bist. <p><p>" +
			 				"Sammle herumliegende Items auf um neue Energie zu tanken und Deinen HP Wert wieder zu erh&ouml;hen. " +
			 				"Du kannst diesen Wert jederzeit &uuml;ber die Statusleiste im linken unteren Rand erkennen.</FONT></html>"; 
			 		text3 ="<html> <FONT COLOR=#FAFAD2> Du machst tolle Fortschritte <p><p>" +
			 				"Gratulation! <p><p>" +
			 				"Ich denke ich kann Dir nun ein Geheimnis anvertrauen. " +
			 				"Seit Generationen wird dieses Geheimnis beh&uuml;tet also geh sorgsam damit um.<p><p>" +
			 				"Ich &uuml;berreiche Dir hiermit den magischen Feuerball. Diesen Zauber kannst Du durch die Leertaste auf Deine Gegner anwenden." +
			 				"Du ben&ouml;tigst Mana um den Zauber anwenden zu k&ouml;nnen.<p><p>" +
			 				"Sammle Mana Items in der Umgebung, wenn Du genug K&auml;fer hast kannst Du auch gern vorbeikommen und mit mir tauschen.</FONT></html>";
			 		textElse = "<html> <FONT COLOR=#FAFAD2> Leider kann ich Dir gerade nicht helfen</FONT></html>";
			 	
			 			 		
			 		switch (level)//checks which level and sets appropiate text
			 		{
			 			case 1:		 text = text1;break;
			 			case 2:		 text = text2;break;
			 			case 3:		 text = text3;break;
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
			 		
				}
			}
		
		
		
		

   
	
    
	} 