/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.dougkelly88.FLIMPlateReaderGUI.GUIComponents;

import com.github.dougkelly88.FLIMPlateReaderGUI.Classes.SeqAcqProps;
import com.google.common.io.Files;
import java.awt.Component;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import mmcorej.CMMCore;
import org.micromanager.MMStudio;
import org.micromanager.api.events.PropertyChangedEvent;
import java.util.Arrays;

/**
 *
 * @author Frederik
 */
public class SaveData extends javax.swing.JPanel {
    MMStudio gui_;
    CMMCore core_;
    PropertyChangedEvent event_;
    private SeqAcqProps sap_;
    String path=null;
    /**
     * Creates new form SaveData
     */
    public SaveData() {
        initComponents();
        gui_ = MMStudio.getInstance();
        sap_ = SeqAcqProps.getInstance();
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

        SaveDataButton = new javax.swing.JButton();
        SelectPathButton = new javax.swing.JButton();
        ChoosenPathField = new javax.swing.JTextField();
        ChoosenPathLabel = new javax.swing.JLabel();
        SavedDataField = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TextArea = new javax.swing.JTextArea();

        SaveDataButton.setText("SaveData");
        SaveDataButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveDataButtonActionPerformed(evt);
            }
        });

        SelectPathButton.setText("Select Path");
        SelectPathButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SelectPathButtonActionPerformed(evt);
            }
        });

        ChoosenPathLabel.setText("Choosen Path:");

        jButton1.setText("ReadData");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        TextArea.setColumns(20);
        TextArea.setRows(5);
        jScrollPane1.setViewportView(TextArea);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(SelectPathButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ChoosenPathField, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(SaveDataButton)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton1))
                                .addComponent(SavedDataField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(118, 118, 118)
                        .addComponent(ChoosenPathLabel)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ChoosenPathLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SelectPathButton)
                    .addComponent(ChoosenPathField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SaveDataButton)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(SavedDataField, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void SaveDataButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveDataButtonActionPerformed
        if (path==null){
        JOptionPane.showMessageDialog(null,"Please choose a path!");
        }
        else{
        PrintWriter writer=null;
        try {
            writer = new PrintWriter(path+"\\ConfigSoftware.txt", "UTF-8");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SaveData.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(SaveData.class.getName()).log(Level.SEVERE, null, ex);
        }
        writer.println("Data Document:");
        writer.close();  
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_SaveDataButtonActionPerformed

    private void SelectPathButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SelectPathButtonActionPerformed
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Select target directory");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        Component parentFrame = null;
        int returnVal = chooser.showOpenDialog(parentFrame);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
        File myFile = chooser.getSelectedFile();
        } 
        path= chooser.getSelectedFile().getPath();
        ChoosenPathField.setText(path);        // TODO add your handling code here:
    }//GEN-LAST:event_SelectPathButtonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        FileReader allConfig = null ;   
        try {
            allConfig = new FileReader("C:\\Users\\Frederik\\Desktop\\ConfigSoftware.txt");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SaveData.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
                    TextArea.read(allConfig, null);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }    
        
        
        /*
            File file = new File("C:\\Users\\Frederik\\Desktop\\ConfigSoftware.txt");
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            DataInputStream dis = null;
            
            try {
            fis = new FileInputStream(file);
            
            // Here BufferedInputStream is added for fast reading.
            bis = new BufferedInputStream(fis);
            dis = new DataInputStream(bis);
            
            // dis.available() returns 0 if the file does not have more lines.
            while (dis.available() != 0) {
            
            // this statement reads the line from the file and print it to
            // the console.
            
            TextArea.setText(dis.readLine());
            
            }
            
            // dispose all the resources after using them.
            fis.close();
            bis.close();
            dis.close();
            
            } catch (FileNotFoundException e) {
            e.printStackTrace();
            } catch (IOException e) {
            e.printStackTrace();
        }*/
    
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField ChoosenPathField;
    private javax.swing.JLabel ChoosenPathLabel;
    private javax.swing.JButton SaveDataButton;
    private javax.swing.JTextField SavedDataField;
    private javax.swing.JButton SelectPathButton;
    private javax.swing.JTextArea TextArea;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
