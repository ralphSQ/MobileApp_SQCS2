/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.mobileapp;

import java.awt.Color;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.UIManager;

/**
 *
 * @author Ralph
 */
public class loginFrame extends javax.swing.JFrame {

    /**
     * Creates new form loginFrame
     */
    public loginFrame() {
        initComponents();
        usernameInput.grabFocus();
        errorLabel.setVisible(false);
        errorUsernameLabel.setVisible(false);
        errorPasswordLabel.setVisible(false);
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
        logo = new javax.swing.JLabel();
        usernameInput = new javax.swing.JTextField();
        passwordInput = new javax.swing.JPasswordField();
        signupButton = new javax.swing.JButton();
        forgotpassButton = new javax.swing.JButton();
        loginButton = new javax.swing.JButton();
        errorUsernameLabel = new javax.swing.JLabel();
        errorPasswordLabel = new javax.swing.JLabel();
        errorLabel = new javax.swing.JLabel();
        background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login");
        setResizable(false);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SourceImages/logo.png"))); // NOI18N
        logo.setText("jLabel2");
        jPanel1.add(logo, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, 320, 270));

        usernameInput.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        usernameInput.setForeground(java.awt.Color.lightGray);
        usernameInput.setText("Username or Email Address");
        usernameInput.setToolTipText("");
        usernameInput.setBorder(null);
        usernameInput.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                usernameInputFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                usernameInputFocusLost(evt);
            }
        });
        jPanel1.add(usernameInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 340, 360, 50));

        passwordInput.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        passwordInput.setForeground(java.awt.Color.lightGray);
        passwordInput.setText("1234567890");
        passwordInput.setBorder(null);
        passwordInput.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                passwordInputFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                passwordInputFocusLost(evt);
            }
        });
        jPanel1.add(passwordInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 420, 360, 50));

        signupButton.setBackground(new java.awt.Color(51, 51, 51));
        signupButton.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        signupButton.setForeground(new java.awt.Color(255, 255, 255));
        signupButton.setText("Sign Up");
        signupButton.setToolTipText("");
        signupButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        signupButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        signupButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signupButtonActionPerformed(evt);
            }
        });
        jPanel1.add(signupButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 580, 360, 40));

        forgotpassButton.setBackground(new java.awt.Color(51, 51, 51));
        forgotpassButton.setFont(new java.awt.Font("Calibri", 0, 16)); // NOI18N
        forgotpassButton.setForeground(new java.awt.Color(255, 255, 255));
        forgotpassButton.setText("Forgot Password?");
        forgotpassButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        forgotpassButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        forgotpassButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                forgotpassButtonActionPerformed(evt);
            }
        });
        jPanel1.add(forgotpassButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 670, 150, 40));

        loginButton.setBackground(new java.awt.Color(38, 166, 154));
        loginButton.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        loginButton.setForeground(new java.awt.Color(255, 255, 255));
        loginButton.setText("Log In");
        loginButton.setToolTipText("");
        loginButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });
        jPanel1.add(loginButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 530, 360, 40));

        errorUsernameLabel.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        errorUsernameLabel.setForeground(java.awt.Color.red);
        errorUsernameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        errorUsernameLabel.setLabelFor(usernameInput);
        errorUsernameLabel.setText("Enter your Username/Email Address");
        jPanel1.add(errorUsernameLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, 360, 30));

        errorPasswordLabel.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        errorPasswordLabel.setForeground(java.awt.Color.red);
        errorPasswordLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        errorPasswordLabel.setLabelFor(passwordInput);
        errorPasswordLabel.setText("Enter your password");
        jPanel1.add(errorPasswordLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 390, 360, 30));

        errorLabel.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        errorLabel.setForeground(java.awt.Color.red);
        errorLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        errorLabel.setText("Incorrect username or password");
        jPanel1.add(errorLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 480, 360, 30));

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SourceImages/Untitled-5.jpg"))); // NOI18N
        background.setText("jLabel1");
        jPanel1.add(background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 420, 740));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 740, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonActionPerformed
        if (usernameInput.getText().trim().isEmpty() || usernameInput.getText().equals("Username or Email Address")) {
            errorUsernameLabel.setVisible(true);
        } else if (new String(passwordInput.getPassword()).trim().isEmpty() || new String(passwordInput.getPassword()).equals("1234567890")) {
            errorPasswordLabel.setVisible(true);
        } else {
            String password = "", username = "", fullName = "", firstName = "";
            int clientId = 0;
            password = new String(passwordInput.getPassword());
            username = usernameInput.getText().trim();
            if (new EmailValidator().validateEmail(usernameInput.getText().trim())) {
                clientId = Client.loginWithEmail(username, password);
            } else {
                clientId = Client.login(username, password);
            }
            if (clientId != 0) { // 0 means not found
                fullName = Client.createFullName(clientId);
                firstName = Client.getFirstName(clientId);
                // open home frame
                this.dispose();
                new homeFrame(clientId).setVisible(true);
            } else {
                errorLabel.setVisible(true);
            }
        }
    }//GEN-LAST:event_loginButtonActionPerformed

    private void forgotpassButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_forgotpassButtonActionPerformed
        this.dispose();
        new forgotPasswordFrame().setVisible(true);
    }//GEN-LAST:event_forgotpassButtonActionPerformed

    private void signupButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signupButtonActionPerformed
        new registerFrame().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_signupButtonActionPerformed

    private void passwordInputFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_passwordInputFocusLost
        if (passwordInput.getText().equals("")) {
            passwordInput.setBorder(BorderFactory.createLineBorder(Color.red));
            errorPasswordLabel.setVisible(true);
        } else {
            passwordInput.setBorder(BorderFactory.createLineBorder(Color.green));
            errorPasswordLabel.setVisible(false);
        }
    }//GEN-LAST:event_passwordInputFocusLost

    private void passwordInputFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_passwordInputFocusGained
        if (passwordInput.getText().equals("1234567890")) {
            passwordInput.setText("");
            passwordInput.setForeground(Color.black);
        }
    }//GEN-LAST:event_passwordInputFocusGained

    private void usernameInputFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_usernameInputFocusLost
        if (usernameInput.getText().equals("")) {
            usernameInput.setBorder(BorderFactory.createLineBorder(Color.red));
            errorUsernameLabel.setVisible(true);
        } else {
            errorUsernameLabel.setVisible(false);
            usernameInput.setBorder(BorderFactory.createLineBorder(Color.green));
        }
    }//GEN-LAST:event_usernameInputFocusLost

    private void usernameInputFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_usernameInputFocusGained
        if (usernameInput.getText().equals("Username or Email Address")) {
            usernameInput.setText("");
            usernameInput.setForeground(Color.black);
        }
    }//GEN-LAST:event_usernameInputFocusGained

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
                // select the Look and Feel
                UIManager.setLookAndFeel("com.jtattoo.plaf.graphite.GraphiteLookAndFeel");
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(loginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(loginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(loginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(loginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new loginFrame().setVisible(true);

        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel background;
    private javax.swing.JLabel errorLabel;
    private javax.swing.JLabel errorPasswordLabel;
    private javax.swing.JLabel errorUsernameLabel;
    private javax.swing.JButton forgotpassButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton loginButton;
    private javax.swing.JLabel logo;
    private javax.swing.JPasswordField passwordInput;
    private javax.swing.JButton signupButton;
    private javax.swing.JTextField usernameInput;
    // End of variables declaration//GEN-END:variables
}
