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
    JLabel labelB, labelHP, labelMP;

    public ShopFrame(){
        super("Shop");

        
        //creating some JLabels
        
        //PlayerLeft Icon
        JLabel lIcon = new JLabel(new ImageIcon(local.Pics.icon1));
        lIcon.setBounds(80,50,200,50);
        
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
        
        labelB = new JLabel (String.valueOf(new Integer(gameobjects.Create.hero1.getBugs())));
        labelB.setBounds(100,100,50,50);

        JLabel klabel1_b = new JLabel ("HP:");
        klabel1_b.setBounds(50, 120, 50, 50);
        
        labelHP = new JLabel (String.valueOf(new Integer(gameobjects.Create.hero1.getHp())));
        labelHP.setBounds(100,120,50,50);
        
        JLabel labelMP_a = new JLabel("MP:");
        labelMP_a.setBounds(50,140,50,50);
        
        labelMP = new JLabel(String.valueOf(new Integer(gameobjects.Create.hero1.getMp())));
        labelMP.setBounds(100,140,50,50);
        
        //PlayerRight Label	
    //    JLabel PlayerRechts = new JLabel();
    //    PlayerRechts.setText("Player 2");
    //    PlayerRechts.setBounds(200,100,200,50);
        
        //Bugs Player 2
    //    JLabel kLabel2 = new JLabel();
   //     kLabel2.setText("Käfer Player 2 = "+ iKaefer2);
   //     kLabel2.setBounds(200,150,200,50);

        //ShopLabel
        JLabel sLabel = new JLabel();
        sLabel.setText("Shop");
        sLabel.setHorizontalTextPosition(JLabel.CENTER);
        sLabel.setBounds(0,20,512,50);

        //HealLabel
        JLabel hLabel = new JLabel();
        hLabel.setText("Heiltrank (10HP)");
        hLabel.setBounds(400,200,200,50);

        //ManaLabel
        JLabel mLabel = new JLabel();
        mLabel.setText("Manatrank (8MP)");
        mLabel.setBounds(400,250,200,50);

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
        hButton1.setBounds(50,200,150,50);
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
        mButton1.setBounds(50,250,150,50);
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

        //Bugtransfer Button Player 1
       // JButton tButton1 = new JButton();
       // tButton1.setText("Give Bugs to Player2");
       // tButton1.setBounds(50,300,150,50);
       // tButton1.addActionListener(new java.awt.event.ActionListener() {
       //     public void actionPerformed(java.awt.event.ActionEvent evt) {
       //         if (iKaefer1< 0){
       //             System.out.println("Nicht genug Käfer");
        //        }
        //        else{
        //            iKaefer2++;
        //            kLabel2.setText("Käfer Player 2 = "+ iKaefer2);
        //            kLabel1.setText("Käfer Player 1 = "+ iKaefer1);
        //            System.out.println("Kaeferzahl Player 1 = "+iKaefer1);
        //            System.out.println("Kaeferzahl Player 2 = "+iKaefer2);
        //           }
         //   }
        //});


//        //Heal Player 2
//        JButton hButton2 = new JButton();
//        hButton2.setText("Heiltrank");
//        hButton2.setBounds(200,200,150,50);
//        hButton2.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                iKaefer2 = iKaefer2 - iHeiltrank;
//                if (iKaefer2< 0){
//                    iKaefer2 = iKaefer2 +iHeiltrank;
//                    System.out.println("Nicht genug Käfer");
//                }
//                else{
//                    kLabel2.setText("Käfer Player 2 = "+ iKaefer2);        //missing Healevent!!!
//                    main.Main.obj_list.get(3).hp+=10;
//                    System.out.println("Neue HP Player 2 = "+main.Main.obj_list.get(3).hp);
//                    System.out.println("Kaeferzahl Player 2 = "+iKaefer2);
//                   }
//            }
//
//        });
//
//        //Mana Player2
//        JButton mButton2 = new JButton();
//        mButton2.setText("Manatrank");
//        mButton2.setBounds(200,250,150,50);
//        mButton2.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                iKaefer2 = iKaefer2 - iManatrank;
//                if (iKaefer2< 0){
//                    iKaefer2 = iKaefer2 +iManatrank;
//                    System.out.println("Nicht genug Käfer");
//                }
//                else{
//                    kLabel2.setText("Käfer Player 2 = "+ iKaefer2);        //missing Manaevent!!!
//                    main.Main.obj_list.get(3).mp+=10;
//                    System.out.println("Neue HP Player 2 = "+main.Main.obj_list.get(3).hp);
//                    System.out.println("Kaeferzahl Player 2 = "+iKaefer2);
//                   }
//            }
//        });
//
//        //Bugtransfer Button Player 2
//        JButton tButton2 = new JButton();
//        tButton2.setText("Give Bugs to Player1");
//        tButton2.setBounds(200,300,150,50);
//        tButton2.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                iKaefer2--;
//                if (iKaefer2< 0){
//                    iKaefer2++;
//                    System.out.println("Nicht genug Käfer");
//                }
//                else{
//                    iKaefer1++;
//                    kLabel2.setText("Käfer Player 2 = "+ iKaefer2);
//                    kLabel1.setText("Käfer Player 1 = "+ iKaefer1);
//                    System.out.println("Kaeferzahl Player 1 = "+iKaefer1);
//                    System.out.println("Kaeferzahl Player 2 = "+iKaefer2);
//                   }
//            }
//        });

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
         this.setUndecorated(true);
         setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

         //adding Components to ShopFrame
         layeredPane = getLayeredPane();
         layeredPane.add(cButton, new Integer(10));
         layeredPane.add(hButton1, new Integer(10));
         layeredPane.add(mButton1, new Integer(10));
     //    layeredPane.add(hButton2, new Integer(10));
     //    layeredPane.add(mButton2, new Integer(10));
     //    layeredPane.add(tButton1, new Integer(10));
     //    layeredPane.add(tButton2, new Integer(10));
         layeredPane.add(hLabel, new Integer(10));
         layeredPane.add(mLabel, new Integer(10));
         layeredPane.add(sLabel, new Integer(10));
         layeredPane.add(kLabel1, new Integer(10));
       //  layeredPane.add(kLabel2, new Integer(10));
         layeredPane.add(PlayerLinks, new Integer(10));
//         layeredPane.add(PlayerRechts, new Integer(10));
         layeredPane.add(lIcon, new Integer(10));
         layeredPane.add(kLabel1_a, new Integer(10));
         layeredPane.add(labelHP, new Integer(10));
         layeredPane.add(klabel1_b, new Integer(10));
         layeredPane.add(labelB, new Integer(11));
         layeredPane.add(labelMP, new Integer(10));
         layeredPane.add(labelMP_a, new Integer(10));

         setVisible(true);
         
         
    }

public void updatePoints(int index){
	switch (index)
	{
		case 1: {//change Bugs
					labelB.setText(String.valueOf(new Integer(gameobjects.Create.hero1.getBugs())));
				}break;
		case 2: {//change HPs
					labelHP.setText(String.valueOf(new Integer(gameobjects.Create.hero1.getHp())));
				}break;
		case 3: {//changes MPs
			       labelMP.setText(String.valueOf(new Integer(gameobjects.Create.hero1.getMp())));
				}break;
	
	}
}
    
public void noBugs(){
	JOptionPane.showMessageDialog(null, "Zu wenig Käfer!"); 
}
	
    
} 