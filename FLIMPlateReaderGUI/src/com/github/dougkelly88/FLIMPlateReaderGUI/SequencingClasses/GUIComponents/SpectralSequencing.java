/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.dougkelly88.FLIMPlateReaderGUI.SequencingClasses.GUIComponents;

import com.github.dougkelly88.FLIMPlateReaderGUI.GeneralClasses.SeqAcqProps;
import com.github.dougkelly88.FLIMPlateReaderGUI.GeneralClasses.VariableTest;
import com.github.dougkelly88.FLIMPlateReaderGUI.SequencingClasses.Classes.FilterSetup;
import com.github.dougkelly88.FLIMPlateReaderGUI.SequencingClasses.Classes.FilterTableModel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableColumn;
import mmcorej.CMMCore;
import mmcorej.StrVector;
import org.micromanager.MMStudio;

/**
 *
 * @author dk1109
 */
public class SpectralSequencing extends javax.swing.JPanel {
    
    FilterTableModel tableModel_;
    JTable filtTable_;
    Object parent_;
    SeqAcqProps sap_;
    VariableTest var_;
    CMMCore core_; 
    MMStudio gui_;
    
    /**
     * Creates new form SpectralSequencing
     */
    public SpectralSequencing() {
        initComponents();
        setControlDefaults();
    }
    
    private void setControlDefaults(){
        try{
            gui_ = MMStudio.getInstance();
            core_ = gui_.getCore();
        }
        catch (Exception e){
            System.out.println("Exception = " + e.getMessage());
        }
        
        sap_ = SeqAcqProps.getInstance();
       
        
        tableModel_ = new FilterTableModel(new FilterSetup("465/30","ND 1.0","473/561",
                        "525/30",100,sap_.getDelaysArray().get(0)));
        tableModel_.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {

            }
        });
        filtTable_ = new JTable();
        filtTable_.setModel(tableModel_);
        filtTable_.setSurrendersFocusOnKeystroke(true);
        filtTable_.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);

        JScrollPane scroller = new javax.swing.JScrollPane(filtTable_);
        filtTable_.setPreferredScrollableViewportSize(new java.awt.Dimension(500, 500));
        filtSeqBasePanel.setLayout(new BorderLayout());
        filtSeqBasePanel.add(scroller, BorderLayout.CENTER);
        
        final JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem deleteItem = new JMenuItem("Delete filter config");
        deleteItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int r = filtTable_.getSelectedRow();
                tableModel_.removeRow(r);
            }
        });
        JMenuItem addItem = new JMenuItem("Add filter config");
        addItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int r = filtTable_.getSelectedRow();
                tableModel_.insertRow(r, new FilterSetup("465/30","ND 1.0","473/561",
                        "525/30",100,sap_.getDelaysArray().get(0)));
            }
        });
        popupMenu.add(addItem);
        popupMenu.add(deleteItem);
        filtTable_.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
//                System.out.println("pressed");
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    JTable source = (JTable) e.getSource();
                    int row = source.rowAtPoint(e.getPoint());
                    int column = source.columnAtPoint(e.getPoint());

                    if (!source.isRowSelected(row)) {
                        source.changeSelection(row, column, false, false);
                    }

                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });
        
        JComboBox exCombo = new JComboBox();
        populateCombos(exCombo, "Emission", filtTable_.getColumnModel().getColumn(1));
        
    }
    
    private void populateCombos(JComboBox combo, String devLabel, TableColumn col){
        try{
            StrVector vals = core_.getAllowedPropertyValues(devLabel, "Label");
            for (String str : vals){
                combo.addItem(str);
            }
            col.setCellEditor(new DefaultCellEditor(combo));
        } catch (Exception ex) {
            String str = "Exception at = " + ex.getMessage();
            System.out.println(str);
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

        filtSeqBasePanel = new javax.swing.JPanel();

        filtSeqBasePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        javax.swing.GroupLayout filtSeqBasePanelLayout = new javax.swing.GroupLayout(filtSeqBasePanel);
        filtSeqBasePanel.setLayout(filtSeqBasePanelLayout);
        filtSeqBasePanelLayout.setHorizontalGroup(
            filtSeqBasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 623, Short.MAX_VALUE)
        );
        filtSeqBasePanelLayout.setVerticalGroup(
            filtSeqBasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 506, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 647, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(filtSeqBasePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 533, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(filtSeqBasePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel filtSeqBasePanel;
    // End of variables declaration//GEN-END:variables
}
