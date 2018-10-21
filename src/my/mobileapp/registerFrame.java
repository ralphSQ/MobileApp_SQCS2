/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.mobileapp;

import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;

/**
 *
 * @author Ralph
 */
public class registerFrame extends javax.swing.JFrame {

    /**
     * Creates new form registerFrame
     */
    public registerFrame() {
        initComponents();
        errorLabel.setVisible(false);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        submitButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        accountNumberInput = new javax.swing.JFormattedTextField();
        errorLabel = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sign Up");
        setResizable(false);

        jPanel1.setLayout(null);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SourceImages/web.png"))); // NOI18N
        jPanel1.add(jLabel3);
        jLabel3.setBounds(60, 270, 160, 170);

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SourceImages/right-arrow.png"))); // NOI18N
        jPanel1.add(jLabel6);
        jLabel6.setBounds(280, 340, 40, 40);

        jLabel4.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        jLabel4.setText("Input your Account Number");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(60, 420, 150, 50);

        submitButton.setBackground(new java.awt.Color(38, 166, 154));
        submitButton.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        submitButton.setText("Proceed");
        submitButton.setBorder(null);
        submitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitButtonActionPerformed(evt);
            }
        });
        jPanel1.add(submitButton);
        submitButton.setBounds(220, 590, 90, 40);

        cancelButton.setBackground(java.awt.Color.lightGray);
        cancelButton.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        cancelButton.setText("Cancel");
        cancelButton.setBorder(null);
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });
        jPanel1.add(cancelButton);
        cancelButton.setBounds(110, 590, 90, 40);

        try {
            accountNumberInput.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#########")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        accountNumberInput.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        accountNumberInput.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                accountNumberInputFocusLost(evt);
            }
        });
        accountNumberInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                accountNumberInputKeyReleased(evt);
            }
        });
        jPanel1.add(accountNumberInput);
        accountNumberInput.setBounds(80, 490, 270, 40);

        errorLabel.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        errorLabel.setForeground(java.awt.Color.red);
        errorLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        errorLabel.setText("Label for errors");
        jPanel1.add(errorLabel);
        errorLabel.setBounds(80, 530, 270, 30);

        jLabel10.setFont(new java.awt.Font("Calibri", 1, 48)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Sign Up");
        jPanel1.add(jLabel10);
        jLabel10.setBounds(130, 90, 400, 70);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SourceImages/Untitled-8.jpg"))); // NOI18N
        jPanel1.add(jLabel1);
        jLabel1.setBounds(0, -20, 416, 260);

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SourceImages/BG_LandPage.jpg"))); // NOI18N
        jPanel1.add(jLabel5);
        jLabel5.setBounds(0, 220, 420, 530);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 733, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void submitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitButtonActionPerformed

        boolean isExisting = false, isRegistered = false;

        if (accountNumberInput.getText().trim().isEmpty()) {
            errorLabel.setText("Please enter your account number");
            errorLabel.setVisible(true);
        } else {
            try {
                isExisting = Client.checkIfAccountExists(Integer.valueOf(accountNumberInput.getText().trim()));
                isRegistered = Client.checkIfRegistered(Integer.valueOf(accountNumberInput.getText().trim()));
                System.out.println(isExisting);
                System.out.println(isRegistered);
                if (!isExisting) {
                    errorLabel.setText("Account number does not exist");
                    errorLabel.setVisible(true);
                } else if (isRegistered) {
                    errorLabel.setText("Account number is already registered");
                    errorLabel.setVisible(true);
                } else {
                    int clientId = Client.getId(Integer.valueOf(accountNumberInput.getText().trim()));
                    System.out.println(clientId);
                    String username = Client.createUserName(clientId);
                    String password = PasswordGenerator.generatePassword(8);
                    String encryptedPassword = PasswordHasher.passwordHasher(password);

                    String receipient = Client.getEmail(clientId);
                    if (Client.sendCredentials(receipient, username, password)) {
                        if (Client.setUsername(username, clientId) && Client.setPassword(encryptedPassword, clientId)) {
                            JOptionPane.showMessageDialog(this, "Registration complete!\nCheck your email account for your login credentials.", "Success", JOptionPane.INFORMATION_MESSAGE);
                            this.dispose();
                            new loginFrame().setVisible(true);
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Account creation failed, please check your internet connection and try again.", "Failed", JOptionPane.WARNING_MESSAGE);
                    }

                }
            } catch (NullPointerException ex) {
                JOptionPane.showMessageDialog(this, "Failed to connect to database.\nPlease check your database connection and try again.", "Connection Failed", JOptionPane.ERROR_MESSAGE);
            }
        }

    }//GEN-LAST:event_submitButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        this.dispose();
        new loginFrame().setVisible(true);

    }//GEN-LAST:event_cancelButtonActionPerformed

    private void accountNumberInputKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_accountNumberInputKeyReleased
        if (accountNumberInput.getText().length() >= 9) {
            evt.consume();
        }
    }//GEN-LAST:event_accountNumberInputKeyReleased

    private void accountNumberInputFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_accountNumberInputFocusLost
        if (accountNumberInput.getText().isEmpty()) {
            errorLabel.setText("Please enter your account number");
            errorLabel.setVisible(true);
            accountNumberInput.setBorder(BorderFactory.createLineBorder(Color.red));
        } else {
            accountNumberInput.setBorder(BorderFactory.createLineBorder(Color.green));
        }
    }//GEN-LAST:event_accountNumberInputFocusLost

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
            java.util.logging.Logger.getLogger(registerFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(registerFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(registerFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(registerFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new registerFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFormattedTextField accountNumberInput;
    private javax.swing.JButton cancelButton;
    private javax.swing.JLabel errorLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton submitButton;
    // End of variables declaration//GEN-END:variables
}
