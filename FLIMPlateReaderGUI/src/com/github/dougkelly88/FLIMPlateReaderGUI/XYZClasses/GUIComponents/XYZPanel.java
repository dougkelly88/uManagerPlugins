/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.github.dougkelly88.FLIMPlateReaderGUI.XYZClasses.GUIComponents;

/**
 *
 * @author dk1109
 */
public class XYZPanel extends javax.swing.JPanel {

    /**
     * Creates new form XYZPanel
     */
    public XYZPanel() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        canvas1 = new java.awt.Canvas();
        manualXYZPanel = new javax.swing.JPanel();
        panningPanel = new javax.swing.JPanel();
        uButton = new javax.swing.JButton();
        dButton = new javax.swing.JButton();
        lButton = new javax.swing.JButton();
        rButton = new javax.swing.JButton();
        stepSizeLabel = new javax.swing.JLabel();
        stepSizeField = new javax.swing.JFormattedTextField();
        wellMapPanel = new javax.swing.JPanel();
        zPanel = new javax.swing.JPanel();
        zuButton = new javax.swing.JButton();
        zdButton = new javax.swing.JButton();
        zStepSizeLabel = new javax.swing.JLabel();
        zStepSizeField = new javax.swing.JFormattedTextField();

        canvas1.setPreferredSize(new java.awt.Dimension(100, 100));

        manualXYZPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("User interface XYZ"));

        panningPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Panning"));

        uButton.setText("U");

        dButton.setText("D");
        dButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dButtonActionPerformed(evt);
            }
        });

        lButton.setText("L");
        lButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lButtonActionPerformed(evt);
            }
        });

        rButton.setText("R");
        rButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rButtonActionPerformed(evt);
            }
        });

        stepSizeLabel.setText("Step size (um)");

        stepSizeField.setText("9000");
        stepSizeField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stepSizeFieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panningPanelLayout = new javax.swing.GroupLayout(panningPanel);
        panningPanel.setLayout(panningPanelLayout);
        panningPanelLayout.setHorizontalGroup(
            panningPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panningPanelLayout.createSequentialGroup()
                .addGroup(panningPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panningPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panningPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panningPanelLayout.createSequentialGroup()
                                .addComponent(lButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                                .addComponent(rButton))
                            .addGroup(panningPanelLayout.createSequentialGroup()
                                .addGroup(panningPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panningPanelLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(uButton))
                                    .addGroup(panningPanelLayout.createSequentialGroup()
                                        .addGap(43, 43, 43)
                                        .addComponent(dButton)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panningPanelLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(stepSizeLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(stepSizeField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 6, Short.MAX_VALUE))
        );
        panningPanelLayout.setVerticalGroup(
            panningPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panningPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(uButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addGroup(panningPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panningPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(stepSizeLabel)
                    .addComponent(stepSizeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        wellMapPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Well map"));

        javax.swing.GroupLayout wellMapPanelLayout = new javax.swing.GroupLayout(wellMapPanel);
        wellMapPanel.setLayout(wellMapPanelLayout);
        wellMapPanelLayout.setHorizontalGroup(
            wellMapPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 202, Short.MAX_VALUE)
        );
        wellMapPanelLayout.setVerticalGroup(
            wellMapPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        zPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Z focus"));

        zuButton.setText("U");

        zdButton.setText("D");
        zdButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zdButtonActionPerformed(evt);
            }
        });

        zStepSizeLabel.setText("Step size (um)");

        zStepSizeField.setText("9000");
        zStepSizeField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zStepSizeFieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout zPanelLayout = new javax.swing.GroupLayout(zPanel);
        zPanel.setLayout(zPanelLayout);
        zPanelLayout.setHorizontalGroup(
            zPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(zPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(zPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(zStepSizeField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(zStepSizeLabel)
                    .addComponent(zdButton, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(zuButton, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        zPanelLayout.setVerticalGroup(
            zPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(zPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(zuButton, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(zdButton, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(zStepSizeLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(zStepSizeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout manualXYZPanelLayout = new javax.swing.GroupLayout(manualXYZPanel);
        manualXYZPanel.setLayout(manualXYZPanelLayout);
        manualXYZPanelLayout.setHorizontalGroup(
            manualXYZPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(manualXYZPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panningPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(wellMapPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(zPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        manualXYZPanelLayout.setVerticalGroup(
            manualXYZPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(manualXYZPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(manualXYZPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(wellMapPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panningPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(zPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(38, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addComponent(canvas1, javax.swing.GroupLayout.PREFERRED_SIZE, 506, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(74, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(manualXYZPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(manualXYZPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 115, Short.MAX_VALUE)
                .addComponent(canvas1, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void stepSizeFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stepSizeFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_stepSizeFieldActionPerformed

    private void rButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rButtonActionPerformed

    private void lButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lButtonActionPerformed

    private void dButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dButtonActionPerformed

    private void zdButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zdButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_zdButtonActionPerformed

    private void zStepSizeFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zStepSizeFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_zStepSizeFieldActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Canvas canvas1;
    private javax.swing.JButton dButton;
    private javax.swing.JButton lButton;
    private javax.swing.JPanel manualXYZPanel;
    private javax.swing.JPanel panningPanel;
    private javax.swing.JButton rButton;
    private javax.swing.JFormattedTextField stepSizeField;
    private javax.swing.JLabel stepSizeLabel;
    private javax.swing.JButton uButton;
    private javax.swing.JPanel wellMapPanel;
    private javax.swing.JPanel zPanel;
    private javax.swing.JFormattedTextField zStepSizeField;
    private javax.swing.JLabel zStepSizeLabel;
    private javax.swing.JButton zdButton;
    private javax.swing.JButton zuButton;
    // End of variables declaration//GEN-END:variables
}
