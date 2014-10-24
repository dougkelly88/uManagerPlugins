/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.dougkelly88.FLIMPlateReaderGUI.GUIComponents;

import java.util.logging.Level;
import java.util.logging.Logger;
import mmcorej.CMMCore;
import mmcorej.StrVector;
import org.micromanager.MMStudio;
import org.micromanager.acquisition.AcquisitionEngine;
import org.micromanager.api.ScriptInterface;

/**
 *
 * @author Frederik
 */
public class FrontPanelFred extends javax.swing.JFrame implements org.micromanager.api.MMPlugin {
    public static String menuName = "TestLightPathFred";
    public static String tooltipDescription = "Plugin allowing control of an OpenFLIM-HCA plate reader";
    private CMMCore core_;
    private MMStudio gui_;
    private AcquisitionEngine acq_;
    /**
     * Creates new form FrontPanelFred
     */
    public FrontPanelFred() {
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

        General = new javax.swing.JSeparator();
        Filter = new javax.swing.JSeparator();
        Olympus = new javax.swing.JSeparator();
        ObjectiveComboBox = new javax.swing.JComboBox();
        ObjectiveLabel = new javax.swing.JLabel();
        EmissionComboBox = new javax.swing.JComboBox();
        EmissionLabel = new javax.swing.JLabel();
        DichroicComboBox = new javax.swing.JComboBox();
        DichroicLabel = new javax.swing.JLabel();
        LaserON = new javax.swing.JToggleButton();
        loadConfigButton = new javax.swing.JButton();
        ExcitationLabel = new javax.swing.JLabel();
        ExcitationComboBox = new javax.swing.JComboBox();
        NDFWLabel = new javax.swing.JLabel();
        NDFWComboBox = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        General.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "General"));

        Filter.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "Filters"));

        Olympus.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "Olympus"));

        ObjectiveComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        ObjectiveComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ObjectiveComboBoxActionPerformed(evt);
            }
        });

        ObjectiveLabel.setText("Objective");

        EmissionComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        EmissionComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EmissionComboBoxActionPerformed(evt);
            }
        });

        EmissionLabel.setText("Emission");

        DichroicComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        DichroicComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DichroicComboBoxActionPerformed(evt);
            }
        });

        DichroicLabel.setText("Dichroic");

        LaserON.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LaserONActionPerformed(evt);
            }
        });

        loadConfigButton.setText("Load Config");
        loadConfigButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadConfigButtonActionPerformed(evt);
            }
        });

        ExcitationLabel.setText("Excitation");

        ExcitationComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        ExcitationComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExcitationComboBoxActionPerformed(evt);
            }
        });

        NDFWLabel.setText("Intensity");

        NDFWComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        NDFWComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NDFWComboBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Filter, javax.swing.GroupLayout.PREFERRED_SIZE, 492, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 41, Short.MAX_VALUE))
                            .addComponent(Olympus, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(LaserON, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(81, 81, 81)
                        .addComponent(loadConfigButton))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(General, javax.swing.GroupLayout.PREFERRED_SIZE, 492, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ObjectiveLabel)
                            .addComponent(ObjectiveComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(ExcitationLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(NDFWLabel))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(ExcitationComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(NDFWComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(DichroicComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(DichroicLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(EmissionLabel)
                            .addComponent(EmissionComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 176, Short.MAX_VALUE)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {DichroicComboBox, DichroicLabel, EmissionComboBox, EmissionLabel, ExcitationComboBox, ExcitationLabel, NDFWComboBox, NDFWLabel});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(General, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LaserON, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(loadConfigButton))
                .addGap(23, 23, 23)
                .addComponent(Filter, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(EmissionLabel)
                    .addComponent(DichroicLabel)
                    .addComponent(ExcitationLabel)
                    .addComponent(NDFWLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(EmissionComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DichroicComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ExcitationComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NDFWComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addComponent(Olympus, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ObjectiveLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ObjectiveComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void loadConfigButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadConfigButtonActionPerformed

//Intensity (NDFW) Load
        // Read allowed values for Label
        StrVector NDFWBox = null;
        try {
            NDFWBox = core_.getAllowedPropertyValues("NDFW", "Label");
        } catch (Exception ex) {
            Logger.getLogger(FrontPanel.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Write Dichroic Label ComboBox
        NDFWComboBox.removeAllItems();
        for(String str : NDFWBox) {
        NDFWComboBox.addItem(str);
        }
        
//Excitation (SpectralFW) Load
        // Read allowed values for Label
        StrVector ExciBox = null;
        try {
            ExciBox = core_.getAllowedPropertyValues("SpectralFW", "Label");
        } catch (Exception ex) {
            Logger.getLogger(FrontPanel.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Write Dichroic Label ComboBox
        ExcitationComboBox.removeAllItems();
        for(String str : ExciBox) {
        ExcitationComboBox.addItem(str);
        }
        
//Dichroic Load
        // Read allowed values for Label
        StrVector DichBox = null;
        try {
            DichBox = core_.getAllowedPropertyValues("CSUX-Dichroic Mirror", "Label");
        } catch (Exception ex) {
            Logger.getLogger(FrontPanel.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Write Dichroic Label ComboBox
        DichroicComboBox.removeAllItems();
        for(String str : DichBox) {
        DichroicComboBox.addItem(str);
        }      
        
//Emission Load
        // Read allowed values for Label
        StrVector EmisBox = null;
        try {
            EmisBox = core_.getAllowedPropertyValues("CSUX-Filter Wheel", "Label");
        } catch (Exception ex) {
            Logger.getLogger(FrontPanel.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Write Dichroic Label ComboBox
        EmissionComboBox.removeAllItems();
        for(String str : EmisBox) {
        EmissionComboBox.addItem(str);
        }    
        
 //Objective Load
        // Read allowed values for Label
        StrVector ObjeBox = null;
        try {
            ObjeBox = core_.getAllowedPropertyValues("Objective", "Label");
        } catch (Exception ex) {
            Logger.getLogger(FrontPanel.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Write Dichroic Label ComboBox
        ObjectiveComboBox.removeAllItems();
        for(String str : ObjeBox) {
        ObjectiveComboBox.addItem(str);
        }        
    }//GEN-LAST:event_loadConfigButtonActionPerformed

    private void DichroicComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DichroicComboBoxActionPerformed
        int DichState= DichroicComboBox.getSelectedIndex();
        try {
            core_.setProperty("CSUX-Dichroic Mirror", "State", DichState);
        } catch (Exception ex) {
            Logger.getLogger(FrontPanelFred.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_DichroicComboBoxActionPerformed

    private void EmissionComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EmissionComboBoxActionPerformed
        int EmisState= EmissionComboBox.getSelectedIndex();
        try {
            core_.setProperty("CSUX-Filter Wheel", "State", EmisState);
        } catch (Exception ex) {
            Logger.getLogger(FrontPanelFred.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }//GEN-LAST:event_EmissionComboBoxActionPerformed

    private void LaserONActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LaserONActionPerformed
            if (LaserON.isSelected()){
                LaserON.setText("Laser ON");
                try {
                    core_.setProperty("FianiumSC","LaserOn?", "Off");
                } catch (Exception ex) {
                    Logger.getLogger(FrontPanelFred.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            } else {
                LaserON.setText("Laser OFF");
                try {
                    core_.setProperty("FianiumSC","LaserOn?", "On");
                } catch (Exception ex) {
                    Logger.getLogger(FrontPanelFred.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
    }//GEN-LAST:event_LaserONActionPerformed

    private void ObjectiveComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ObjectiveComboBoxActionPerformed
        int ObjeState= ObjectiveComboBox.getSelectedIndex();
        try {
            core_.setProperty("Objective", "State", ObjeState);
        } catch (Exception ex) {
            Logger.getLogger(FrontPanelFred.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_ObjectiveComboBoxActionPerformed

    private void ExcitationComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExcitationComboBoxActionPerformed
        int ExciState= ExcitationComboBox.getSelectedIndex();
        try {
            core_.setProperty("SpectralFW", "State", ExciState);
        } catch (Exception ex) {
            Logger.getLogger(FrontPanelFred.class.getName()).log(Level.SEVERE, null, ex);
        }
            // TODO add your handling code here:
    }//GEN-LAST:event_ExcitationComboBoxActionPerformed

    private void NDFWComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NDFWComboBoxActionPerformed
        int NDFWState= NDFWComboBox.getSelectedIndex();
        try {
            core_.setProperty("NDFW", "State", NDFWState);
        } catch (Exception ex) {
            Logger.getLogger(FrontPanelFred.class.getName()).log(Level.SEVERE, null, ex);
        }
            // TODO add your handling code here:
    }//GEN-LAST:event_NDFWComboBoxActionPerformed

                        
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
            java.util.logging.Logger.getLogger(FrontPanelFred.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrontPanelFred.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrontPanelFred.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrontPanelFred.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrontPanelFred().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox DichroicComboBox;
    private javax.swing.JLabel DichroicLabel;
    private javax.swing.JComboBox EmissionComboBox;
    private javax.swing.JLabel EmissionLabel;
    private javax.swing.JComboBox ExcitationComboBox;
    private javax.swing.JLabel ExcitationLabel;
    private javax.swing.JSeparator Filter;
    private javax.swing.JSeparator General;
    private javax.swing.JToggleButton LaserON;
    private javax.swing.JComboBox NDFWComboBox;
    private javax.swing.JLabel NDFWLabel;
    private javax.swing.JComboBox ObjectiveComboBox;
    private javax.swing.JLabel ObjectiveLabel;
    private javax.swing.JSeparator Olympus;
    private javax.swing.JButton loadConfigButton;
    // End of variables declaration//GEN-END:variables

    @Override
    public void setApp(ScriptInterface app) {
        gui_ = (MMStudio) app;
        core_ = app.getMMCore();
        acq_ = gui_.getAcquisitionEngine();
    }


    @Override
    public String getDescription() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getInfo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getVersion() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getCopyright() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
