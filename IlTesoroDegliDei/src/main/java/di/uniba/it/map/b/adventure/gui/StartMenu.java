/*
    * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
    * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package di.uniba.it.map.b.adventure.gui;

import di.uniba.it.map.b.adventure.database.Database;
import di.uniba.it.map.b.adventures.GameDescription;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Gabriele Paladini, Domenico Scalise, Leonardo Moro
 */
public class StartMenu extends javax.swing.JFrame {

    /**
     * Creates new form StartMenu
     */
    public StartMenu() {
        initComponents();
        Sound.startMusic();
        MusicButtonOFF.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        MusicButtonON = new javax.swing.JToggleButton();
        MusicButtonOFF = new javax.swing.JToggleButton();
        Title = new javax.swing.JLabel();
        NewGameButton = new javax.swing.JButton();
        LoadGameButton = new javax.swing.JButton();
        CommandsButton = new javax.swing.JButton();
        ExitButton = new javax.swing.JButton();
        MenuBackGround = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setLocation(new java.awt.Point(57, 25));
        setMaximumSize(new java.awt.Dimension(740, 560));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        MusicButtonON.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Icons/SoundON_icon.png"))); // NOI18N
        MusicButtonON.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MusicButtonONMouseClicked(evt);
            }
        });
        getContentPane().add(MusicButtonON, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 500, 40, 40));

        MusicButtonOFF.setForeground(new java.awt.Color(255, 255, 255));
        MusicButtonOFF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Icons/SoundOFF_icon.png"))); // NOI18N
        MusicButtonOFF.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MusicButtonOFFMouseClicked(evt);
            }
        });
        getContentPane().add(MusicButtonOFF, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 500, 40, 40));

        Title.setFont(new java.awt.Font("Arial", 1, 50)); // NOI18N
        Title.setForeground(new java.awt.Color(255, 255, 255));
        Title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Title.setText("IL TESORO DEGLI DEI");
        getContentPane().add(Title, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, 650, 150));

        NewGameButton.setText("NUOVA PARTITA");
        NewGameButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NewGameButtonActionPerformed(evt);
            }
        });
        getContentPane().add(NewGameButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 210, 160, 50));

        LoadGameButton.setText("CARICA PARTITA");
        LoadGameButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoadGameButtonActionPerformed(evt);
            }
        });
        getContentPane().add(LoadGameButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 280, 160, 50));

        CommandsButton.setText("COMANDI");
        CommandsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CommandsButtonActionPerformed(evt);
            }
        });
        getContentPane().add(CommandsButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 350, 160, 50));

        ExitButton.setText("ESCI");
        ExitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitButtonActionPerformed(evt);
            }
        });
        getContentPane().add(ExitButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 420, 160, 50));

        MenuBackGround.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/view/Menu_background.jpg"))); // NOI18N
        MenuBackGround.setText("M");
        getContentPane().add(MenuBackGround, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -10, 740, 570));

        setSize(new java.awt.Dimension(736, 577));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

        //gestisce la situazione in cui l'utente clicca sul tasto 'nuova partita's
        private void NewGameButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NewGameButtonActionPerformed

            setVisible(false);
            dispose();
            Sound.stopMusic();

            Database db = new Database();
            Database.createConnection();
            db.createTable();

            Database.deletePlayerData();
            Database.deleteUsedObject();

            EngineGUI gameInterface = new EngineGUI();
            gameInterface.setVisible(true);
        }//GEN-LAST:event_NewGameButtonActionPerformed

        private void ExitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitButtonActionPerformed
            Object[] exitMessage = {"Sei sicuro di voler uscire dal gioco?"};
            Object[] options = {"Si", "No"};

            JFrame exitOptions = new JFrame();
            exitOptions.setAlwaysOnTop(true);

            int selectedOption = JOptionPane.showOptionDialog(exitOptions, exitMessage, "USCITA", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[1]);

            if (selectedOption == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        }//GEN-LAST:event_ExitButtonActionPerformed

        private void CommandsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CommandsButtonActionPerformed
            String commands = "Il tuo obiettivo è esplorare la piramide alla ricerca del leggendario 'Tesoro degli Dei'.\n"
                    + "\nPer interagire con l'ambiente che ti circoda fai uso di questi comandi : "
                    + "\n -nord                                                              ti sposti a nord"
                    + "\n -sud                                                                ti sposti a sud"
                    + "\n -est                                                                 ti sposti a est"
                    + "\n -ovest                                                              ti sposti a ovest"
                    + "\n -usa (nome_oggetto)                                    utilizzi l'oggetto indicato"
                    + "\n -premi/sposta (nome_oggetto)                            premi l'oggetto indicato"
                    + "\n -apri (nome_oggetto)                                     apri l'oggetto indicato"
                    + "\n -guarda (nome stanza)                               esamini la stanza in cui ti trovi"
                    + "\n -esamina (nome_oggetto)                               esamini l'oggetto indicato"
                    + "\n -prendi (nome_oggetto)                                 raccogli l'oggetto indicato"
                    + "\n -inserisci (nome_oggetto)                               inserisci l'oggetto indicato"
                    + "\n -leggi (nome_oggetto)                                     leggi l'oggetto indicato"                    
                    + "\n\n(digita i nomi degli elementi in minuscolo senza utilizzare spazi)";
            JOptionPane.showMessageDialog(this, commands, "INFO", JOptionPane.INFORMATION_MESSAGE);
        }//GEN-LAST:event_CommandsButtonActionPerformed

        private void LoadGameButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoadGameButtonActionPerformed
            Sound.stopMusic();
            Database db = new Database();
            Database.createConnection();
            db.createTable();

            boolean hasPlayerData = db.checkSavedGame();
            if (hasPlayerData) {
                setVisible(false);
                dispose();
                GameDescription.loadedGame = true;

                EngineGUI gameInterface = new EngineGUI();
                gameInterface.setVisible(true);                
            } else {
                JOptionPane.showMessageDialog(null, "Nessun salvataggio disponibile!", "ERRORE", JOptionPane.INFORMATION_MESSAGE);
            }
        }//GEN-LAST:event_LoadGameButtonActionPerformed

        private void MusicButtonONMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MusicButtonONMouseClicked
            MusicButtonON.setVisible(false);
            MusicButtonOFF.setVisible(true);
            Sound.stopMusic();
        }//GEN-LAST:event_MusicButtonONMouseClicked

        private void MusicButtonOFFMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MusicButtonOFFMouseClicked
            MusicButtonOFF.setVisible(false);
            MusicButtonON.setVisible(true);
            Sound.startMusic();
        }//GEN-LAST:event_MusicButtonOFFMouseClicked

        /**
         * @param args the command line arguments
         */
        public static void main(String args[]) {
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
                java.util.logging.Logger.getLogger(StartMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                java.util.logging.Logger.getLogger(StartMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                java.util.logging.Logger.getLogger(StartMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (javax.swing.UnsupportedLookAndFeelException ex) {
                java.util.logging.Logger.getLogger(StartMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
            //</editor-fold>

            /* Create and display the form */
            java.awt.EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    new StartMenu().setVisible(true);
                }
            });
        }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CommandsButton;
    private javax.swing.JButton ExitButton;
    private static javax.swing.JButton LoadGameButton;
    private javax.swing.JLabel MenuBackGround;
    private static javax.swing.JToggleButton MusicButtonOFF;
    private static javax.swing.JToggleButton MusicButtonON;
    private javax.swing.JButton NewGameButton;
    private javax.swing.JLabel Title;
    // End of variables declaration//GEN-END:variables
}
