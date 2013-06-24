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


public class NpcLevel1 extends JFrame 
	{
		private int height = Main.board_height;
		private int width = Main.board_width;
		
		Image cave, mole;
		
		JLabel background, moleLabel, textLabel;
		JLayeredPane pane;
		JButton okButton;
		
		public NpcLevel1()
			{	
				loadIcon();
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
					background = new JLabel(new ImageIcon(cave));
					background.setBounds(0, 0, 400, 400);
					background.setOpaque(true);
					background.setBackground(Color.black);
					
					//Label Hedghog
					moleLabel = new JLabel(new ImageIcon(mole));
			 		moleLabel.setBounds(300, 250, 100, 150);
			 		
			 		textLabel = new JLabel ();
			 	//	textLabel.setText("<html><marquee scrollamount=100 scrolldelay=5> tedsfgdsfsdfs </marquee>  </html>");
			 		String text ="<html> <FONT COLOR=#FAFAD2> 	Herzlich Willkommen im Lucky Bunny ich bin <I>Mole the Hole</I><p> <p> " +
			 				"Ich werde Dich auf der anstrengenden Reise begleiten und Dich mit wertvollen Tips versorgen."+
			 				"Ich wohne unter der Erde und mag kein Sonnenlicht. <p><p>" +
			 				"Am liebsten esse ich K&auml;fer. Wenn Du welche findest komm' einfach mal an meinem H&uuml;gel vorbei " +
			 				"dann kann ich Dir bestimmt einen guten Tausch anbieten.<p><p> " +
			 				"H&uuml;te Dich vor den F&uuml;chsen, sie sind sehr hungrig und auf der Suche nach einem leckerem Gaumenschmau&szlig;.<p><p>" +
			 				"Zu passender Gelegenheit werde ich Dich wieder aufsuchen. Bis dahin w&uuml;nsche ich Dir viel Gl&uuml;ck!</FONT></html>";
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