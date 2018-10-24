/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.mobileapp;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;

/**
 *
 * @author Ralph
 */
public class cardlessWithdrawalStep2 extends javax.swing.JFrame {

    private int clientId;
    private int pin2;
    private double amount;
    private int timeRequest;

    /**
     * Creates new form cardlessWithdrawalStep2
     */
    public cardlessWithdrawalStep2() {
        initComponents();
    }

    public cardlessWithdrawalStep2(int clientId, int pin2, double amount, int timeRequest) {
        this.clientId = clientId;
        this.pin2 = pin2;
        this.amount = amount;
        this.timeRequest = timeRequest;
        initComponents();
        pin2Label.setText(String.valueOf(this.pin2));

        LocalDateTime expiryTime = LocalDateTime.ofEpochSecond(timeRequest, 0, ZoneOffset.of("+8"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
        expiryTimeLabel.setText(String.valueOf(expiryTime.plusMinutes(30).format(formatter)));
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
        jLabel11 = new javax.swing.JLabel();
        labelForPin2 = new javax.swing.JLabel();
        confirmButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        pin2Label = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        expiryTimeLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cardless Withdrawal");
        setResizable(false);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setFont(new java.awt.Font("Calibri", 1, 48)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Cash Withdrawal");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 350, 70));

        jLabel11.setFont(new java.awt.Font("Calibri", 1, 48)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Request");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 90, 260, 100));

        labelForPin2.setFont(new java.awt.Font("Calibri", 1, 28)); // NOI18N
        labelForPin2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelForPin2.setText("Use this PIN 2 on an ATM ");
        jPanel1.add(labelForPin2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 370, 120));

        confirmButton.setBackground(new java.awt.Color(38, 166, 154));
        confirmButton.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        confirmButton.setText("Done");
        confirmButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmButtonActionPerformed(evt);
            }
        });
        jPanel1.add(confirmButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 620, 90, 40));

        cancelButton.setBackground(java.awt.Color.lightGray);
        cancelButton.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });
        jPanel1.add(cancelButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 620, 90, 40));

        pin2Label.setFont(new java.awt.Font("Calibri", 1, 40)); // NOI18N
        pin2Label.setForeground(new java.awt.Color(32, 173, 32));
        pin2Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pin2Label.setText("123456");
        jPanel1.add(pin2Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 370, 370, 90));

        jLabel2.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Use Code Before");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 500, 370, -1));

        expiryTimeLabel.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        expiryTimeLabel.setForeground(new java.awt.Color(38, 166, 154));
        expiryTimeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        expiryTimeLabel.setText("5 OKLOK");
        jPanel1.add(expiryTimeLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 540, 370, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SourceImages/CashWthdrawReq_Banner.jpg"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -20, -1, 260));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SourceImages/BG_LandPage.jpg"))); // NOI18N
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 230, 420, 500));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        if (Client.checkIfWithdrawDone(clientId, pin2, timeRequest)) {
            JOptionPane.showMessageDialog(this, "Transaction is already done.\nThank you");
            this.dispose();
            new homeFrame(this.clientId).setVisible(true);
        } else if (Client.cancelWithdrawal(clientId, pin2, timeRequest, 2)) {
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to cancel this transaction?", "Confirmation", JOptionPane.OK_CANCEL_OPTION);
            if (confirm == 0) {
                JOptionPane.showMessageDialog(this, "Transaction cancelled");
                this.dispose();
                new homeFrame(this.clientId).setVisible(true);
            }
        } else {
            JOptionPane.showMessageDialog(this, "An error occured, please blame the programmer.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void confirmButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmButtonActionPerformed
        boolean isDone = Client.checkIfWithdrawDone(clientId, pin2, timeRequest);
        if (isDone) {
            JOptionPane.showMessageDialog(this, "Withdrawal successful!");
        } else {
            JOptionPane.showMessageDialog(this, "Transaction is not yet done, click cancel if you wish to end the transaction.");
        }
    }//GEN-LAST:event_confirmButtonActionPerformed

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
            java.util.logging.Logger.getLogger(cardlessWithdrawalStep2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(cardlessWithdrawalStep2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(cardlessWithdrawalStep2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(cardlessWithdrawalStep2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new cardlessWithdrawalStep2().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton confirmButton;
    private javax.swing.JLabel expiryTimeLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel labelForPin2;
    private javax.swing.JLabel pin2Label;
    // End of variables declaration//GEN-END:variables
}
