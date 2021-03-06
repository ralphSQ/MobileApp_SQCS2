/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.mobileapp;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;

/**
 *
 * @author Ralph
 */
public class fundTransferFrame extends javax.swing.JFrame {

    private int clientId;

    /**
     * Creates new form fundTransferFrame
     */
    public fundTransferFrame() {
        initComponents();
    }

    public fundTransferFrame(int clientId) {
        initComponents();
        this.clientId = clientId;
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
        jLabel10 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        amountInput = new javax.swing.JFormattedTextField();
        targetAccountNumberInput = new javax.swing.JFormattedTextField();
        errorLabel = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        pinInput = new javax.swing.JFormattedTextField();
        submitButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Fund Transfer");

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setFont(new java.awt.Font("Calibri", 1, 48)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Fund Transfer");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 80, 290, 70));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SourceImages/FundTransfer_Banner.jpg"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -20, -1, 260));

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SourceImages/fund_trans.png"))); // NOI18N
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 370, 150));

        jLabel8.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel8.setText("Amount");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 550, 270, 20));

        jLabel9.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel9.setText("Receipient's Account Number");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 430, 270, -1));

        amountInput.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,###.00"))));
        amountInput.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        amountInput.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                amountInputFocusLost(evt);
            }
        });
        jPanel1.add(amountInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 570, 270, 40));

        targetAccountNumberInput.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        targetAccountNumberInput.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        targetAccountNumberInput.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                targetAccountNumberInputFocusLost(evt);
            }
        });
        targetAccountNumberInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                targetAccountNumberInputKeyTyped(evt);
            }
        });
        jPanel1.add(targetAccountNumberInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 450, 270, 40));

        errorLabel.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        errorLabel.setForeground(java.awt.Color.red);
        errorLabel.setText("jLabel8");
        jPanel1.add(errorLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 610, 270, 30));

        jLabel11.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel11.setText("Your PIN");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 490, 270, 20));

        pinInput.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        pinInput.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        pinInput.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                pinInputFocusLost(evt);
            }
        });
        pinInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                pinInputKeyTyped(evt);
            }
        });
        jPanel1.add(pinInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 510, 270, 40));

        submitButton.setBackground(new java.awt.Color(38, 166, 154));
        submitButton.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        submitButton.setText("Proceed");
        submitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitButtonActionPerformed(evt);
            }
        });
        jPanel1.add(submitButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 640, 90, 40));

        cancelButton.setBackground(java.awt.Color.lightGray);
        cancelButton.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });
        jPanel1.add(cancelButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 640, 90, 40));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SourceImages/BG_LandPage.jpg"))); // NOI18N
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 230, 420, 500));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 731, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void amountInputFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_amountInputFocusLost
        if (amountInput.getText().trim().isEmpty()) {
            amountInput.setBorder(BorderFactory.createLineBorder(Color.red));
        } else {
            amountInput.setBorder(BorderFactory.createLineBorder(Color.green));
        }
    }//GEN-LAST:event_amountInputFocusLost

    private void targetAccountNumberInputFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_targetAccountNumberInputFocusLost
        if (targetAccountNumberInput.getText().trim().isEmpty()) {
            targetAccountNumberInput.setBorder(BorderFactory.createLineBorder(Color.red));
        } else {
            targetAccountNumberInput.setBorder(BorderFactory.createLineBorder(Color.green));
        }
    }//GEN-LAST:event_targetAccountNumberInputFocusLost

    private void targetAccountNumberInputKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_targetAccountNumberInputKeyTyped
        if (targetAccountNumberInput.getText().length() >= 9) {
            evt.consume();
        }
    }//GEN-LAST:event_targetAccountNumberInputKeyTyped

    private void pinInputFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pinInputFocusLost
        if (pinInput.getText().trim().isEmpty()) {
            pinInput.setBorder(BorderFactory.createLineBorder(Color.red));
        } else {
            pinInput.setBorder(BorderFactory.createLineBorder(Color.green));
        }
    }//GEN-LAST:event_pinInputFocusLost

    private void pinInputKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pinInputKeyTyped
        if (pinInput.getText().length() >= 6) {
            evt.consume();
        }
    }//GEN-LAST:event_pinInputKeyTyped

    private void submitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitButtonActionPerformed

        if (!amountInput.getText().trim().isEmpty() && !targetAccountNumberInput.getText().trim().isEmpty() && !amountInput.getText().trim().equals("PIN") && !targetAccountNumberInput.getText().trim().equals("Receipient's Account Number")) {
            int pin = 0, targetAccountNumber = 0, confirm = 0, targetId = 0, threshold = 0;
            double amount, balance;
            String targetName, message;
            pin = Integer.parseInt(pinInput.getText().trim());
            targetAccountNumber = Integer.parseInt(targetAccountNumberInput.getText().trim());
            amount = Double.parseDouble(amountInput.getText().trim().replace(",", ""));
            balance = Client.getBalance(clientId);

            if (Client.checkIfAccountExists(targetAccountNumber)) {
                if (Client.checkIfPinIsCorrect(pin, this.clientId) || Client.checkIfTemporaryPinIsCorrect(pin, clientId)) {
                    if (balance < amount) {
                        errorLabel.setText("Insufficient Balance");
                        errorLabel.setVisible(true);
                    } else if ((balance - 2000 < amount)) {

                    }
                    if ((balance - 2000 < amount)) {
                        threshold = JOptionPane.showConfirmDialog(this, "You are about to go beyond the maintaining balance\nContinue transaction?", "Maintaining Balance", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE);
                        if (threshold == 0) {
                            if (Client.checkIfTemporaryPinIsCorrect(pin, clientId)) {
                                JOptionPane.showMessageDialog(this, "You are using your temporary pin assigned to your account.\nChange your PIN as soon as possible to increase your account's security", "Tip", JOptionPane.INFORMATION_MESSAGE);
                            }

                            amountInput.setBorder(BorderFactory.createLineBorder(Color.green));
                            pinInput.setBorder(BorderFactory.createLineBorder(Color.green));
                            targetAccountNumberInput.setBorder(BorderFactory.createLineBorder(Color.green));
                            targetId = Client.getId(targetAccountNumber);
                            targetName = Client.createFullName(targetId);
                            errorLabel.setVisible(false);
                            message = "Fund Transfer"
                                    + "\n------------------------------"
                                    + "\nReceipient Name: " + targetName
                                    + "\nReceipient Account Number: " + targetAccountNumber
                                    + "\nAmount to Transfer: " + amount
                                    + "\n------------------------------";
                            confirm = JOptionPane.showConfirmDialog(this, message, "Confirmation", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                            if (confirm == 0) {
                                if (Client.fundTransfer(clientId, amount, targetAccountNumber, balance)) {
                                    this.dispose();
                                    new fundTransferConfirmFrame(clientId, targetAccountNumber, targetName, amount).setVisible(true);
                                } else {
                                    JOptionPane.showMessageDialog(this, "An error occured, please blame the programmer.", "Error", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                        }
                    }
                } else {
                    errorLabel.setText("Incorrect PIN");
                    pinInput.setBorder(BorderFactory.createLineBorder(Color.red));
                    pinInput.requestFocus();
                    errorLabel.setVisible(true);
                }
            } else {
                errorLabel.setText("Account number does not exist");
                targetAccountNumberInput.requestFocus();
                targetAccountNumberInput.setBorder(BorderFactory.createLineBorder(Color.red));
                errorLabel.setVisible(true);
            }

        } else if (targetAccountNumberInput.getText().trim().isEmpty() || targetAccountNumberInput.getText().trim().equals("Receipient's Account Number")) {
            errorLabel.setText("Enter the receipient's account number");
            targetAccountNumberInput.requestFocus();
            errorLabel.setVisible(true);
        } else if (pinInput.getText().trim().isEmpty() || pinInput.getText().trim().equals("PIN")) {
            errorLabel.setText("Enter your pin to continue");
            pinInput.requestFocus();
            errorLabel.setVisible(true);
        } else if (amountInput.getText().trim().isEmpty() || amountInput.getText().trim().equals("PIN")) {
            errorLabel.setText("Enter the amount to transfer");
            amountInput.requestFocus();
            errorLabel.setVisible(true);
        }
    }//GEN-LAST:event_submitButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        this.dispose();
        new homeFrame(clientId).setVisible(true);
    }//GEN-LAST:event_cancelButtonActionPerformed

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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(fundTransferFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new fundTransferFrame().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFormattedTextField amountInput;
    private javax.swing.JButton cancelButton;
    private javax.swing.JLabel errorLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JFormattedTextField pinInput;
    private javax.swing.JButton submitButton;
    private javax.swing.JFormattedTextField targetAccountNumberInput;
    // End of variables declaration//GEN-END:variables
}
