package graphics;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

public class ShopFrame extends JFrame {

    JLayeredPane layeredPane;
    JLabel labelB1, labelHP1, labelMP1;
    JLabel labelB2, labelHP2, labelMP2;

    public ShopFrame(){
        super("Shop");

        
        //creating some JLabels
        
        //PlayerLeft Icon
        JLabel lIcon = new JLabel(new ImageIcon(local.Pics.icon1));
        lIcon.setBounds(60,50,200,50);
        
        //PlayerLeft Label
        JLabel PlayerLinks = new JLabel();
        PlayerLinks.setText("Player 1");
        PlayerLinks.setBounds(50,50,200,50);
                        
       //Bugs Player 1
        JLabel kLabel1 = new JLabel();
        kLabel1.setText("Käfer: ");
        kLabel1.setBounds(50,100,50,50);
       
        JLabel kLabel1_a = new JLabel(new ImageIcon(local.Pics.bug));
        kLabel1_a.setBounds(10,100,50,50);
        
        labelB1 = new JLabel (String.valueOf(new Integer(gameobjects.Create.hero1.getBugs())));
        labelB1.setBounds(100,100,50,50);

        JLabel klabel1_b = new JLabel ("HP:");
        klabel1_b.setBounds(50, 120, 50, 50);
        
        labelHP1 = new JLabel (String.valueOf(new Integer(gameobjects.Create.hero1.getHp())));
        labelHP1.setBounds(100,120,50,50);
        
        JLabel labelMP1_a = new JLabel("MP:");
        labelMP1_a.setBounds(50,140,50,50);
        
        labelMP1 = new JLabel(String.valueOf(new Integer(gameobjects.Create.hero1.getMp())));
        labelMP1.setBounds(100,140,50,50);
        
        
        //ShopLabel
        JLabel sLabel = new JLabel();
        sLabel.setText("Shop");
        sLabel.setHorizontalTextPosition(JLabel.CENTER);
        sLabel.setBounds(0,20,512,50);

        //HealLabel
        JLabel hLabel = new JLabel();
        hLabel.setText("Heiltrank (10HP)");
        hLabel.setBounds(420,200,200,50);

        //ManaLabel
        JLabel mLabel = new JLabel();
        mLabel.setText("Manatrank (8MP)");
        mLabel.setBounds(420,250,200,50);

        //CloseButton
        JButton cButton = new JButton();
        cButton.setText("Close");
        cButton.setBounds(156,412,200,50);
        cButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	main.Main.ingame = true;			//continue game
                dispose();         	               //close only ShopFrame
            }
        });

        //BuyButtons
        //Heal Player 1
        JButton hButton1 = new JButton();
        hButton1.setText("Heiltrank");
        hButton1.setBounds(50,200,180,50);
        hButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if (gameobjects.Create.hero1.getBugs()< 1){
                       noBugs();
                }
                else{
                	int hp = gameobjects.Create.hero1.getHp()+10;
                	gameobjects.Create.hero1.setHp(hp); 					//1 bug = 10 hps
                	int bug = gameobjects.Create.hero1.getBugs()-1;
                	gameobjects.Create.hero1.setBugs(bug);					//minus 1 bug
                	updatePoints(1);
                	updatePoints(2);
                	
                }
            }

        });

        //Mana Player 1
        JButton mButton1 = new JButton();
        mButton1.setText("Manatrank");
        mButton1.setBounds(50,250,180,50);
        mButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	if (gameobjects.Create.hero1.getBugs()< 1){
                  noBugs();
                }
                else{
                	int mp = gameobjects.Create.hero1.getMp()+8;
                	gameobjects.Create.hero1.setMp(mp);//1 bug for 8 mana
           			int bug = gameobjects.Create.hero1.getBugs()-1;
                	gameobjects.Create.hero1.setBugs(bug);					//minus 1 bug	
                	updatePoints(1);
                	updatePoints(3);
                	
                 }
            }
        });

        //Eventlistener for Window close button added
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
            //  PlayerLeft.count =0;                        //resets counter for open shop windows
            }
          });
        //Eventlistener Ende

        setSize(600,512);
         setResizable(false);
         setLayout(null);
      //   this.setUndecorated(true);
         setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

         //adding Components to ShopFrame
         layeredPane = getLayeredPane();
         layeredPane.add(cButton, new Integer(10));
         layeredPane.add(hButton1, new Integer(10));
         layeredPane.add(mButton1, new Integer(10));
         layeredPane.add(hLabel, new Integer(10));
         layeredPane.add(mLabel, new Integer(10));
         layeredPane.add(sLabel, new Integer(10));
         layeredPane.add(kLabel1, new Integer(10));
         layeredPane.add(PlayerLinks, new Integer(10));
         layeredPane.add(lIcon, new Integer(10));
         layeredPane.add(kLabel1_a, new Integer(10));
         layeredPane.add(labelHP1, new Integer(10));
         layeredPane.add(klabel1_b, new Integer(10));
         layeredPane.add(labelB1, new Integer(11));
         layeredPane.add(labelMP1, new Integer(10));
         layeredPane.add(labelMP1_a, new Integer(10));
         
         //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
         
         if(main.Main.Nr_of_Players==2){				//!!!!Muss zum Testen bei mir auf 1 gestellt sein!!!!!
        	 
             //PlayerRight Icon
             JLabel rIcon = new JLabel(new ImageIcon(local.Pics.icon1));
             rIcon.setBounds(240,50,200,50);
             
         	//PlayerRight Label	
         	JLabel PlayerRechts = new JLabel();
         	PlayerRechts.setText("Player 2");
         	PlayerRechts.setBounds(230,50,200,50);
         
             JLabel kLabel2_a = new JLabel(new ImageIcon(local.Pics.bug));
             kLabel2_a.setBounds(10,100,50,50);
             
             labelB2 = new JLabel (String.valueOf(new Integer(gameobjects.Create.hero2.getBugs())));
             labelB2.setBounds(280,100,50,50);

             JLabel klabel2_b = new JLabel ("HP:");
             klabel2_b.setBounds(230, 120, 50, 50);
             
             labelHP2 = new JLabel (String.valueOf(new Integer(gameobjects.Create.hero2.getHp())));
             labelHP2.setBounds(280,120,50,50);
             
             JLabel labelMP2_a = new JLabel("MP:");
             labelMP2_a.setBounds(230,140,50,50);
             
             labelMP2 = new JLabel(String.valueOf(new Integer(gameobjects.Create.hero2.getMp())));
             labelMP2.setBounds(280,140,50,50);
             
             //Bugtransfer Button Player 1
             JButton tButton1 = new JButton();
             tButton1.setText("Give Bugs to Player2");
             tButton1.setBounds(50,300,180,50);
             tButton1.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                      if (gameobjects.Create.hero1.getBugs()< 1){
                          noBugs();
                      }
                      else{
                    	  int bug1 = gameobjects.Create.hero1.getBugs()-1;
                    	  gameobjects.Create.hero1.setBugs(bug1);					//minus 1 bug
                    	  int bug2 = gameobjects.Create.hero2.getBugs()+1;
                    	  gameobjects.Create.hero2.setBugs(bug2);					//plus 1 bug
                    	  updatePoints(1);
                    	  updatePoints2(1);
                   	
                   }
                 }
             });

             //Heal Player 2
             JButton hButton2 = new JButton();
             hButton2.setText("Heiltrank");
             hButton2.setBounds(230,200,180,50);
             hButton2.addActionListener(new java.awt.event.ActionListener() {
                 public void actionPerformed(java.awt.event.ActionEvent evt) {
                     if (gameobjects.Create.hero2.getBugs()< 1){
                            noBugs();
                     }
                     else{
                     	int hp2 = gameobjects.Create.hero2.getHp()+10;
                     	gameobjects.Create.hero2.setHp(hp2); 					//1 bug = 10 hps
                     	int bug2 = gameobjects.Create.hero2.getBugs()-1;
                     	gameobjects.Create.hero2.setBugs(bug2);					//minus 1 bug
                     	updatePoints2(1);
                     	updatePoints2(2);
                     	
                     }
                 }

             });
             
             //Mana Player 2
             JButton mButton2 = new JButton();
             mButton2.setText("Manatrank");
             mButton2.setBounds(230,250,180,50);
             mButton2.addActionListener(new java.awt.event.ActionListener() {
                 public void actionPerformed(java.awt.event.ActionEvent evt) {
                 	if (gameobjects.Create.hero2.getBugs()< 1){
                       noBugs();
                     }
                     else{
                     	int mp2 = gameobjects.Create.hero2.getMp()+8;
                     	gameobjects.Create.hero2.setMp(mp2);//1 bug for 8 mana
                			int bug2 = gameobjects.Create.hero2.getBugs()-1;
                     	gameobjects.Create.hero2.setBugs(bug2);					//minus 1 bug	
                     	updatePoints2(1);
                     	updatePoints2(3);
                     	
                      }
                 }
             });


             //Bugtransfer Button Player 1
             JButton tButton2 = new JButton();
             tButton2.setText("Give Bugs to Player1");
             tButton2.setBounds(230,300,180,50);
             tButton2.addActionListener(new java.awt.event.ActionListener() {
                  public void actionPerformed(java.awt.event.ActionEvent evt) {
                      if (gameobjects.Create.hero2.getBugs()< 1){
                          noBugs();
                      }
                      else{
                    	  int bug2 = gameobjects.Create.hero2.getBugs()-1;
                    	  gameobjects.Create.hero2.setBugs(bug2);					//minus 1 bug
                    	  int bug1 = gameobjects.Create.hero1.getBugs()+1;
                    	  gameobjects.Create.hero1.setBugs(bug1);					//plus 1 bug
                    	  updatePoints(1);
                    	  updatePoints2(1);
                   	
                   }
                 }
             });
             
        	 layeredPane.add(PlayerRechts, new Integer(10));
             layeredPane.add(labelB2, new Integer(10));
             layeredPane.add(klabel2_b, new Integer(10));
             layeredPane.add(labelHP2, new Integer(10));
             layeredPane.add(labelMP2_a, new Integer(10));
             layeredPane.add(labelMP2, new Integer(10));
             layeredPane.add(tButton1, new Integer(10));
             layeredPane.add(tButton2, new Integer(10));
             layeredPane.add(mButton2, new Integer(10));
             layeredPane.add(hButton2, new Integer(10));
             layeredPane.add(rIcon, new Integer(10));
             
         }
         
         //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

         setVisible(true);
         
         
    }

public void updatePoints(int index){
	switch (index)
	{
		case 1: {//change Bugs
					labelB1.setText(String.valueOf(new Integer(gameobjects.Create.hero1.getBugs())));
				}break;
		case 2: {//change HPs
					labelHP1.setText(String.valueOf(new Integer(gameobjects.Create.hero1.getHp())));
				}break;
		case 3: {//changes MPs
			       labelMP1.setText(String.valueOf(new Integer(gameobjects.Create.hero1.getMp())));
				}break;
	
	}
}

public void updatePoints2(int index){
	switch (index)
	{
		case 1: {//change Bugs
					labelB2.setText(String.valueOf(new Integer(gameobjects.Create.hero2.getBugs())));
				}break;
		case 2: {//change HPs
					labelHP2.setText(String.valueOf(new Integer(gameobjects.Create.hero2.getHp())));
				}break;
		case 3: {//changes MPs
			       labelMP2.setText(String.valueOf(new Integer(gameobjects.Create.hero2.getMp())));
				}break;
	
	}
}
    
public void noBugs(){
	JOptionPane.showMessageDialog(null, "Zu wenig Käfer!"); 
}
	
    
} 
