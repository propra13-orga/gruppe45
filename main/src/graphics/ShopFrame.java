package graphics;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JLabel;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import main.*;
import graphics.*;


public class ShopFrame extends JFrame {

    JLayeredPane layeredPane;
    
    //testvalues
    int iKäfer1 = 100;
    int iKäfer2 = 100;

    //
    int iHeiltrank = 5;
    int iManatrank = 7;

    public ShopFrame(){
        super("Shop");

        //creating some JLabels
        //Bugs Player 1
        final JLabel kLabel1 = new JLabel();
        kLabel1.setText("Käfer Player 1 = "+ iKäfer1);
        kLabel1.setBounds(50,100,200,50);

        //Bugs Player 2
        final JLabel kLabel2 = new JLabel();
        kLabel2.setText("Käfer Player 2 = "+ iKäfer2);
        kLabel2.setBounds(200,100,200,50);

        //ShopLabel
        JLabel sLabel = new JLabel();
        sLabel.setText("Shop");
        sLabel.setHorizontalTextPosition(JLabel.CENTER);
        sLabel.setBounds(0,50,512,50);

        //HealLabel
        JLabel hLabel = new JLabel();
        hLabel.setText("1 x Heiltrank");
        hLabel.setBounds(400,200,200,50);

        //ManaLabel
        JLabel mLabel = new JLabel();
        mLabel.setText("1 x Manatrank");
        mLabel.setBounds(400,250,200,50);

        //CloseButton
        JButton cButton = new JButton();
        cButton.setText("Close");
        cButton.setBounds(156,412,200,50);
        cButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dispose();                        //close only ShopFrame
                PlayerLeft.count=0;                //resets counter for open shop windows
            }
        });

        //BuyButtons
        //Heal Player 1
        JButton hButton1 = new JButton();
        hButton1.setText("Heiltrank Player 1");
        hButton1.setBounds(50,200,150,50);
        hButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iKäfer1 = iKäfer1 - iHeiltrank;
                if (iKäfer1< 0){
                    iKäfer1 = iKäfer1 +iHeiltrank;
                    System.out.println("Nicht genug Käfer");
                }
                else{
                    kLabel1.setText("Käfer Player 1 = "+ iKäfer1);        //missing Healevent!!!
                    main.Main.obj_list.get(2).hp+=10;
                    System.out.println("Neue HP Player 1 = "+main.Main.obj_list.get(2).hp);
                    System.out.println("Kaeferzahl Player 1 = "+iKäfer1);
                   }
            }

        });

        //Mana Player 1
        JButton mButton1 = new JButton();
        mButton1.setText("Manatrank Player 1");
        mButton1.setBounds(50,250,150,50);
        mButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iKäfer1 = iKäfer1 - iManatrank;
                if (iKäfer1< 0){
                    iKäfer1 = iKäfer1 +iManatrank;
                    System.out.println("Nicht genug Käfer");
                }
                else{
                    kLabel1.setText("Käfer Player 1 = "+ iKäfer1);        //missing Manaevent!!!
                    main.Main.obj_list.get(2).mp+=10;
                    System.out.println("Neue MP Player 1 = "+main.Main.obj_list.get(2).mp);
                    System.out.println("Kaeferzahl Player 1 = "+iKäfer1);
                   }
            }
        });

        //Bugtransfer Button Player 1
        JButton tButton1 = new JButton();
        tButton1.setText("Give Bugs to Player2");
        tButton1.setBounds(50,300,150,50);
        tButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iKäfer1--;
                if (iKäfer1< 0){
                    iKäfer1++;
                    System.out.println("Nicht genug Käfer");
                }
                else{
                    iKäfer2++;
                    kLabel2.setText("Käfer Player 2 = "+ iKäfer2);
                    kLabel1.setText("Käfer Player 1 = "+ iKäfer1);
                    System.out.println("Kaeferzahl Player 1 = "+iKäfer1);
                    System.out.println("Kaeferzahl Player 2 = "+iKäfer2);
                   }
            }
        });


        //Heal Player 2
        JButton hButton2 = new JButton();
        hButton2.setText("Heiltrank Player 2");
        hButton2.setBounds(200,200,150,50);
        hButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iKäfer2 = iKäfer2 - iHeiltrank;
                if (iKäfer2< 0){
                    iKäfer2 = iKäfer2 +iHeiltrank;
                    System.out.println("Nicht genug Käfer");
                }
                else{
                    kLabel2.setText("Käfer Player 2 = "+ iKäfer2);        //missing Healevent!!!
                    main.Main.obj_list.get(3).hp+=10;
                    System.out.println("Neue HP Player 2 = "+main.Main.obj_list.get(3).hp);
                    System.out.println("Kaeferzahl Player 2 = "+iKäfer2);
                   }
            }

        });

        //Mana Player2
        JButton mButton2 = new JButton();
        mButton2.setText("Manatrank Player 2");
        mButton2.setBounds(200,250,150,50);
        mButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iKäfer2 = iKäfer2 - iManatrank;
                if (iKäfer2< 0){
                    iKäfer2 = iKäfer2 +iManatrank;
                    System.out.println("Nicht genug Käfer");
                }
                else{
                    kLabel2.setText("Käfer Player 2 = "+ iKäfer2);        //missing Manaevent!!!
                    main.Main.obj_list.get(3).mp+=10;
                    System.out.println("Neue HP Player 2 = "+main.Main.obj_list.get(3).hp);
                    System.out.println("Kaeferzahl Player 2 = "+iKäfer2);
                   }
            }
        });

        //Bugtransfer Button Player 2
        JButton tButton2 = new JButton();
        tButton2.setText("Give Bugs to Player1");
        tButton2.setBounds(200,300,150,50);
        tButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iKäfer2--;
                if (iKäfer2< 0){
                    iKäfer2++;
                    System.out.println("Nicht genug Käfer");
                }
                else{
                    iKäfer1++;
                    kLabel2.setText("Käfer Player 2 = "+ iKäfer2);
                    kLabel1.setText("Käfer Player 1 = "+ iKäfer1);
                    System.out.println("Kaeferzahl Player 1 = "+iKäfer1);
                    System.out.println("Kaeferzahl Player 2 = "+iKäfer2);
                   }
            }
        });

        //Eventlistener for Window close button added
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
              PlayerLeft.count =0;                        //resets counter for open shop windows
            }
          });
        //Eventlistener Ende

        setSize(512,512);
         setResizable(false);
         setLayout(null);
         setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

         //adding Components to ShopFrame
         layeredPane = getLayeredPane();
         layeredPane.add(cButton, new Integer(10));
         layeredPane.add(hButton1, new Integer(10));
         layeredPane.add(mButton1, new Integer(10));
         layeredPane.add(hButton2, new Integer(10));
         layeredPane.add(mButton2, new Integer(10));
         layeredPane.add(tButton1, new Integer(10));
         layeredPane.add(tButton2, new Integer(10));
         layeredPane.add(hLabel, new Integer(10));
         layeredPane.add(mLabel, new Integer(10));
         layeredPane.add(sLabel, new Integer(10));
         layeredPane.add(kLabel1, new Integer(10));
         layeredPane.add(kLabel2, new Integer(10));

         setVisible(true);
    }
} 