/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.mobileapp;

import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;

/**
 *
 * @author Ralph
 */
public class forgotPasswordFrame extends javax.swing.JFrame {

    /**
     * Creates new form forgotPasswordFrame
     */
    private String email = "";

    public forgotPasswordFrame() {
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
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        emailInput = new javax.swing.JTextField();
        submitButton = new javax.swing.JButton();
        errorLabel = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setLayout(null);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SourceImages/pass.png"))); // NOI18N
        jPanel1.add(jLabel2);
        jLabel2.setBounds(70, 260, 160, 150);

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SourceImages/right-arrow.png"))); // NOI18N
        jPanel1.add(jLabel6);
        jLabel6.setBounds(280, 330, 150, 40);

        jLabel3.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        jLabel3.setText("Input Email");
        jLabel3.setToolTipText("");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(110, 410, 110, 14);

        jLabel10.setFont(new java.awt.Font("Calibri", 1, 48)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Forgot Password");
        jPanel1.add(jLabel10);
        jLabel10.setBounds(30, 90, 400, 70);

        emailInput.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        emailInput.setForeground(new java.awt.Color(204, 204, 204));
        emailInput.setText("Enter Email");
        emailInput.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                emailInputFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                emailInputFocusLost(evt);
            }
        });
        jPanel1.add(emailInput);
        emailInput.setBounds(40, 480, 330, 40);

        submitButton.setText("Proceed");
        submitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitButtonActionPerformed(evt);
            }
        });
        jPanel1.add(submitButton);
        submitButton.setBounds(150, 580, 100, 40);

        errorLabel.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        errorLabel.setForeground(java.awt.Color.red);
        errorLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        errorLabel.setText("Invalid email address");
        jPanel1.add(errorLabel);
        errorLabel.setBounds(40, 540, 330, 23);

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SourceImages/BG_LandPage.jpg"))); // NOI18N
        jPanel1.add(jLabel5);
        jLabel5.setBounds(0, 220, 420, 530);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SourceImages/Changepass_Banner.jpg"))); // NOI18N
        jPanel1.add(jLabel1);
        jLabel1.setBounds(0, -20, 416, 260);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 737, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void submitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitButtonActionPerformed
        try {
            if (!emailInput.getText().trim().isEmpty()) {

                EmailValidator validator = new EmailValidator();

                if (validator.validateEmail(emailInput.getText().trim())) {

                    emailInput.setBorder(BorderFactory.createLineBorder(Color.green));

                    if (Client.checkIfEmailExists(emailInput.getText().trim())) {

                        try {
                            String resetCode = PasswordGenerator.generateResetPasswordCode(6);

                            Statement st = DatabaseConnection.connect().createStatement();
                            String DBQ = "UPDATE CA_ABS.CLIENT SET RESET_PW_CODE='" + resetCode + "' WHERE CLIENT_EMAIL='" + emailInput.getText() + "'";
                            int result = st.executeUpdate(DBQ);
                            if (result > 0) {

                                if (Client.sendResetPassword(emailInput.getText(), resetCode)) {
                                    JOptionPane.showMessageDialog(this, "Password reset code has been sent to your email","Success",JOptionPane.INFORMATION_MESSAGE);
                                } else {
                                    JOptionPane.showMessageDialog(this, "Email not sent, please try again or check your internet connection", "Try Again", JOptionPane.WARNING_MESSAGE);
                                }
                                this.email = emailInput.getText();
                                this.dispose();
                                new forgotPasswordInputCode(this.email).setVisible(true);
                            }
                        } catch (SQLException | NumberFormatException e) {
                            System.out.println(e.getMessage());
                        }
                    } else {

                        errorLabel.setText("Email address not found");
                        errorLabel.setVisible(true);
                    }
                } else {
                    errorLabel.setText("Invalid email address format");
                    errorLabel.setVisible(true);
                }
            } else {
                errorLabel.setText("Please enter your email address");
                errorLabel.setVisible(true);
            }
        } catch (SQLException ex) {
            Logger.getLogger(forgotPasswordFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_submitButtonActionPerformed

    private void emailInputFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_emailInputFocusGained
        if (emailInput.getText().equals("Enter Email")) {
            emailInput.setForeground(Color.black);
            emailInput.setText("");
        }
    }//GEN-LAST:event_emailInputFocusGained

    private void emailInputFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_emailInputFocusLost
        if (emailInput.getText().trim().isEmpty()) {
            emailInput.setBorder(BorderFactory.createLineBorder(Color.red));
        }
    }//GEN-LAST:event_emailInputFocusLost


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
            java.util.logging.Logger.getLogger(forgotPasswordFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(forgotPasswordFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(forgotPasswordFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(forgotPasswordFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new forgotPasswordFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField emailInput;
    private javax.swing.JLabel errorLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton submitButton;
    // End of variables declaration//GEN-END:variables
}
