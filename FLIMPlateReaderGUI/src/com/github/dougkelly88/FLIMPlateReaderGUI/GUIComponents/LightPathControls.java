/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.github.dougkelly88.FLIMPlateReaderGUI.GUIComponents;

import com.github.dougkelly88.FLIMPlateReaderGUI.Classes.SeqAcqProps;
import com.github.dougkelly88.FLIMPlateReaderGUI.Classes.VariableTest;
import com.google.common.eventbus.Subscribe;
import java.util.logging.Level;
import java.util.logging.Logger;
import mmcorej.CMMCore;
import mmcorej.StrVector;
import org.micromanager.MMStudio;
import org.micromanager.acquisition.AcquisitionEngine;
import org.micromanager.api.ScriptInterface;
import org.micromanager.api.events.PropertyChangedEvent;



/**
 *
 * @author dk1109
 */
public class LightPathControls extends javax.swing.JPanel {
    MMStudio gui_;
    CMMCore core_;
    PropertyChangedEvent event_;
    private SeqAcqProps sap_;
    private VariableTest var_;
//    private SequencedAcquisitionProperties sap_;
    
    /**
     * Creates new form FLIMControls
     */
    public LightPathControls() {
        initComponents();
        gui_ = MMStudio.getInstance();
        sap_ = SeqAcqProps.getInstance();
        var_ = VariableTest.getInstance();
        try{
        gui_.registerForEvents(this);
        core_ = gui_.getCore();
        }
        catch (Exception e) 
        {
            //gui_.showMessage("Error in FLIMPanel constructor: " + e.getMessage());
        }
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        General = new javax.swing.JPanel();
        TestButton = new javax.swing.JButton();
        TestField = new javax.swing.JTextField();
        LaserON = new javax.swing.JToggleButton();
        LoadHardwareConfigButton = new javax.swing.JButton();
        TestField2 = new javax.swing.JTextField();
        Filters = new javax.swing.JPanel();
        NDFWComboBox = new javax.swing.JComboBox();
        ExcitationComboBox = new javax.swing.JComboBox();
        DichroicComboBox = new javax.swing.JComboBox();
        EmissionComboBox = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        Olympus = new javax.swing.JPanel();
        ObjectiveComboBox = new javax.swing.JComboBox();
        FilterCubeComboBox = new javax.swing.JComboBox();
        SwitchPortComboBox = new javax.swing.JComboBox();
        ObjectiveLabel = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        General.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "General"));

        TestButton.setText("Test");
        TestButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TestButtonActionPerformed(evt);
            }
        });

        LaserON.setText("Laser ON");
        LaserON.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LaserONActionPerformed(evt);
            }
        });

        LoadHardwareConfigButton.setText("LoadHardwareConfig");
        LoadHardwareConfigButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoadHardwareConfigButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout GeneralLayout = new javax.swing.GroupLayout(General);
        General.setLayout(GeneralLayout);
        GeneralLayout.setHorizontalGroup(
            GeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(GeneralLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(LaserON, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(GeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(GeneralLayout.createSequentialGroup()
                        .addComponent(TestField, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TestField2, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(GeneralLayout.createSequentialGroup()
                        .addComponent(TestButton, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(LoadHardwareConfigButton)))
                .addGap(201, 201, 201))
        );
        GeneralLayout.setVerticalGroup(
            GeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(GeneralLayout.createSequentialGroup()
                .addGroup(GeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(GeneralLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(GeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TestButton)
                            .addComponent(LoadHardwareConfigButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(GeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TestField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TestField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(GeneralLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(LaserON, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Filters.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Filters"));

        NDFWComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        NDFWComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NDFWComboBoxActionPerformed(evt);
            }
        });

        ExcitationComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        ExcitationComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExcitationComboBoxActionPerformed(evt);
            }
        });

        DichroicComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        DichroicComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DichroicComboBoxActionPerformed(evt);
            }
        });

        EmissionComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        EmissionComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EmissionComboBoxActionPerformed(evt);
            }
        });

        jLabel1.setText("Intensity");

        jLabel2.setText("Excitation");

        jLabel3.setText("Dichroic");

        jLabel4.setText("Emission");

        javax.swing.GroupLayout FiltersLayout = new javax.swing.GroupLayout(Filters);
        Filters.setLayout(FiltersLayout);
        FiltersLayout.setHorizontalGroup(
            FiltersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FiltersLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(FiltersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(NDFWComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(FiltersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ExcitationComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(FiltersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(DichroicComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(FiltersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(EmissionComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        FiltersLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {DichroicComboBox, EmissionComboBox, ExcitationComboBox, NDFWComboBox});

        FiltersLayout.setVerticalGroup(
            FiltersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FiltersLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(FiltersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(FiltersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NDFWComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ExcitationComboBox)
                    .addComponent(DichroicComboBox)
                    .addComponent(EmissionComboBox))
                .addContainerGap())
        );

        Olympus.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Olympus"));

        ObjectiveComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        ObjectiveComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ObjectiveComboBoxActionPerformed(evt);
            }
        });

        FilterCubeComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        FilterCubeComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FilterCubeComboBoxActionPerformed(evt);
            }
        });

        SwitchPortComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        SwitchPortComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SwitchPortComboBoxActionPerformed(evt);
            }
        });

        ObjectiveLabel.setText("Objective");

        jLabel6.setText("Filter Cube");

        jLabel7.setText("BeamPath");

        javax.swing.GroupLayout OlympusLayout = new javax.swing.GroupLayout(Olympus);
        Olympus.setLayout(OlympusLayout);
        OlympusLayout.setHorizontalGroup(
            OlympusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OlympusLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(OlympusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ObjectiveComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ObjectiveLabel))
                .addGap(18, 18, 18)
                .addGroup(OlympusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(FilterCubeComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(OlympusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(SwitchPortComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(215, 215, 215))
        );
        OlympusLayout.setVerticalGroup(
            OlympusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OlympusLayout.createSequentialGroup()
                .addGroup(OlympusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ObjectiveLabel)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(OlympusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ObjectiveComboBox)
                    .addComponent(FilterCubeComboBox)
                    .addComponent(SwitchPortComboBox)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Olympus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(General, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Filters, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(General, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Filters, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Olympus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void TestButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TestButtonActionPerformed
      /* try {
           TestField.setText(core_.getProperty("Dichroic", "Label"));
           
           //Write Dichroic Label ComboBox
           
           // TODO add your handling code here:
       } catch (Exception ex) {
           Logger.getLogger(LightPathControls.class.getName()).log(Level.SEVERE, null, ex);
       }*/ 
              
        
       
        //var_.DichroicComboBoxSelectedItem=(String)DichroicComboBox.getSelectedItem();
        TestField.setText("Item: "+(String) DichroicComboBox.getSelectedItem());
        //var_.tet1=(String) DichroicComboBox.getSelectedItem();
        TestField2.setText("Item: "+var_.tet1);

        
    }//GEN-LAST:event_TestButtonActionPerformed

    private void LoadHardwareConfigButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoadHardwareConfigButtonActionPerformed
//Intensity (NDFW) Load
        // Read allowed values for Label
        StrVector NDFWBox = null;
        try {
            NDFWBox = core_.getAllowedPropertyValues("NDFW", "Label");
        } catch (Exception ex) {
            Logger.getLogger(LightPathControls.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(LightPathControls.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(LightPathControls.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(LightPathControls.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Write Dichroic Label ComboBox
        EmissionComboBox.removeAllItems();
        for(String str : EmisBox) {
        EmissionComboBox.addItem(str);
        }    
        
//FilterCube Load
        // Read allowed values for Label
        StrVector CubeBox = null;
        try {
            CubeBox = core_.getAllowedPropertyValues("FilterCube", "Label");
        } catch (Exception ex) {
            Logger.getLogger(LightPathControls.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Write Dichroic Label ComboBox
        FilterCubeComboBox.removeAllItems();
        for(String str : CubeBox) {
        FilterCubeComboBox.addItem(str);
        }
        
 //Objective Load
        // Read allowed values for Label
        StrVector ObjeBox = null;
        try {
            ObjeBox = core_.getAllowedPropertyValues("Objective", "Label");
        } catch (Exception ex) {
            Logger.getLogger(LightPathControls.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Write Dichroic Label ComboBox
        ObjectiveComboBox.removeAllItems();
        for(String str : ObjeBox) {
        ObjectiveComboBox.addItem(str);
        }  
        
 //SwitchProt Load
        // Read allowed values for Label
        StrVector SwPoBox = null;
        try {
            SwPoBox = core_.getAllowedPropertyValues("LightPathPrism", "Label");
        } catch (Exception ex) {
            Logger.getLogger(LightPathControls.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Write Dichroic Label ComboBox
        SwitchPortComboBox.removeAllItems();
        for(String str : ObjeBox) {
        SwitchPortComboBox.addItem(str);
        }        
                        
                // TODO add your handling code here:
    }//GEN-LAST:event_LoadHardwareConfigButtonActionPerformed

    private void LaserONActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LaserONActionPerformed

            if (LaserON.isSelected()){
                LaserON.setText("Laser ON");
                try {
                    core_.setProperty("FianiumSC","LaserOn?", "Off");
                } catch (Exception ex) {
                    Logger.getLogger(LightPathControls.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            } else {
                LaserON.setText("Laser OFF");
                try {
                    core_.setProperty("FianiumSC","LaserOn?", "On");
                } catch (Exception ex) {
                    Logger.getLogger(LightPathControls.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
           // TODO add your handling code here:
    }//GEN-LAST:event_LaserONActionPerformed

    private void DichroicComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DichroicComboBoxActionPerformed
        int DichState= DichroicComboBox.getSelectedIndex(); 
        try {
            core_.setProperty("CSUX-Dichroic Mirror", "State", DichState);
        } catch (Exception ex) {
            Logger.getLogger(LightPathControls.class.getName()).log(Level.SEVERE, null, ex);
        }
        TestField.setText("Item: "+(String) DichroicComboBox.getSelectedItem());
        var_.DichroicComboBoxSelectedItem=(String)DichroicComboBox.getSelectedItem();
        TestField2.setText("Item: "+var_.DichroicComboBoxSelectedItem);
        // TODO add your handling code here:
    }//GEN-LAST:event_DichroicComboBoxActionPerformed

    private void NDFWComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NDFWComboBoxActionPerformed
        int NDFWState= NDFWComboBox.getSelectedIndex();
        try {
            core_.setProperty("NDFW", "State", NDFWState);
        } catch (Exception ex) {
            Logger.getLogger(LightPathControls.class.getName()).log(Level.SEVERE, null, ex);
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_NDFWComboBoxActionPerformed

    private void ExcitationComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExcitationComboBoxActionPerformed
        int ExciState= ExcitationComboBox.getSelectedIndex();
        try {
            core_.setProperty("SpectralFW", "State", ExciState);
        } catch (Exception ex) {
            Logger.getLogger(LightPathControls.class.getName()).log(Level.SEVERE, null, ex);
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_ExcitationComboBoxActionPerformed

    private void EmissionComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EmissionComboBoxActionPerformed
        int EmisState= EmissionComboBox.getSelectedIndex();
        try {
            core_.setProperty("CSUX-Filter Wheel", "State", EmisState);
        } catch (Exception ex) {
            Logger.getLogger(LightPathControls.class.getName()).log(Level.SEVERE, null, ex);
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_EmissionComboBoxActionPerformed

    private void ObjectiveComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ObjectiveComboBoxActionPerformed
        int ObjeState= ObjectiveComboBox.getSelectedIndex();
        try {
            core_.setProperty("Objective", "State", ObjeState);
        } catch (Exception ex) {
            Logger.getLogger(LightPathControls.class.getName()).log(Level.SEVERE, null, ex);
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_ObjectiveComboBoxActionPerformed

    private void FilterCubeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FilterCubeComboBoxActionPerformed
        int CubeState= FilterCubeComboBox.getSelectedIndex();
        try {
            core_.setProperty("FilterCube", "State", CubeState);
        } catch (Exception ex) {
            Logger.getLogger(LightPathControls.class.getName()).log(Level.SEVERE, null, ex);
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_FilterCubeComboBoxActionPerformed

    private void SwitchPortComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SwitchPortComboBoxActionPerformed
        int SwPoState= SwitchPortComboBox.getSelectedIndex();
        try {
            core_.setProperty("LightPathPrism", "State", SwPoState);
        } catch (Exception ex) {
            Logger.getLogger(LightPathControls.class.getName()).log(Level.SEVERE, null, ex);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_SwitchPortComboBoxActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox DichroicComboBox;
    private javax.swing.JComboBox EmissionComboBox;
    private javax.swing.JComboBox ExcitationComboBox;
    private javax.swing.JComboBox FilterCubeComboBox;
    private javax.swing.JPanel Filters;
    private javax.swing.JPanel General;
    private javax.swing.JToggleButton LaserON;
    private javax.swing.JButton LoadHardwareConfigButton;
    private javax.swing.JComboBox NDFWComboBox;
    private javax.swing.JComboBox ObjectiveComboBox;
    private javax.swing.JLabel ObjectiveLabel;
    private javax.swing.JPanel Olympus;
    private javax.swing.JComboBox SwitchPortComboBox;
    private javax.swing.JButton TestButton;
    private javax.swing.JTextField TestField;
    private javax.swing.JTextField TestField2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    // End of variables declaration//GEN-END:variables
}
