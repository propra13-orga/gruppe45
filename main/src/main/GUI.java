package main;

import graphics.*;
import local.*;
import movement.Keyboard;
import main.Main;

import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;


/*public class Main{
    
    public static void main(String[] args) throws IOException{
       Fs.init(); // fs = filesystem. Wo sind wir (und die Bild- und Leveldateien)?
       menu spielmenu = new menu(); 
       spielmenu.zeige_menu();
       GUI1 run = new GUI1();
       run.testGUI1();
       JFrame mainFrame = new JFrame();
       Renderer rendern = new Renderer();
       rendern.setFrame(mainFrame);
       rendern.run();
       
    }
}
*/
public class GUI extends javax.swing.JFrame {
	
	public static int FRAMESIZE_X = Main.board_width;
	public static int FRAMESIZE_Y = Main.board_height;
	JLayeredPane cont;
	graphics.PlayerLeft left;

    public GUI() {
        initComponents();
        setSize(GUI.FRAMESIZE_X, GUI.FRAMESIZE_Y);
        setResizable(false);      
    }

    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Start");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Exit");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(105, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE))
                .addContainerGap(105, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(100, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(29, 29, 29)
                .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap(88, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    	this.setVisible(true);
    	
        //JInternalFrame renderFrame = new JInternalFrame();
        Main.renderFrame.setSize(Main.board_width, Main.board_height);
	   	Main.renderFrame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
	   	
	   	//ab hier Änderungen
	  	StatusBar sbar = new StatusBar();
	  	
	  	Keyboard KPlayer1 = new Keyboard();
    	Main.renderFrame.setFocusable(true);
    	Main.renderFrame.addKeyListener(KPlayer1);
    	 	
	 
	   		   	
	   	//hier neuer Container
	   	cont = getLayeredPane();
	   	cont.add(Main.renderFrame, new Integer(100));
	  	 
	   	

	   	
       // this.add(Main.renderFrame);auskommentiert Jessica
        Main.renderFrame.setVisible(true); 
        //Main.renderFrame.toFront();
        
        Main.ini();
    	Main.dauerhaft();
    	
    	
	    /*javax.swing.SwingUtilities.invokeLater(new Runnable() {
	        public void run() {
	        	Main.ini();
	        	Main.dauerhaft(renderFrame);
	        }
	        
	    });*/
 	
    }//GEN-LAST:event_jButton1ActionPerformed

 	

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])throws IOException {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    // End of variables declaration//GEN-END:variables
}
