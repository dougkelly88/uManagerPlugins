/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.dougkelly88.FLIMPlateReaderGUI.SequencingClasses.Classes;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author dk1109
 */
public class FilterTableModel extends AbstractTableModel {
   public static final int EX_INDEX = 0;
   public static final int ND_INDEX = 1;
   public static final int DI_INDEX = 2;
   public static final int EM_INDEX = 3;
   public static final int INT_INDEX = 4;
   public static final int DELS_INDEX = 5;
   
   private ArrayList<FilterSetup> data_ = new ArrayList<FilterSetup>();
   private String[] colNames_ = { "Ex filter", "Dichroic", "Em filter", "Camera integration (ms)", "Delays" };
   
   public FilterTableModel(String[] columnNames) {
         this.colNames_ = columnNames;
    }
   
   public FilterTableModel(String[] columnNames, FilterSetup filts){
       this.colNames_ = columnNames;
       this.data_.add(filts);
   }
   
   public FilterTableModel(FilterSetup filts){
       this.data_.add(filts);
   }

    @Override
    public int getRowCount() {
        return data_.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        
        FilterSetup f = data_.get(rowIndex);
        switch (columnIndex){
            case EX_INDEX:
                return f.getExFilt();
            case DI_INDEX:
                return f.getDiFilt();
            case EM_INDEX:
                return f.getEmFilt();
            case INT_INDEX:
                return f.getIntTime();
            case DELS_INDEX: 
                return f.getDelays();
            case ND_INDEX:
                return f.getNDFilt();
            default: 
                return f;
        }
    }

    @Override
    public Class getColumnClass(int column) {
         switch (column) {
             case EX_INDEX:
             case DI_INDEX:
             case EM_INDEX:
             case INT_INDEX:
             case ND_INDEX:
                return Integer.class;
             default:
                return FilterSetup.class;
         }
     }
    
    public ArrayList<FilterSetup> getData(){
        return data_;
    }
    
    public void addRow(FilterSetup f) {
        int row = data_.size();
        data_.add(f);
        fireTableRowsInserted(row, row);
//        sap_.setDelaysArray(0, data_);
    }
   
    public void insertRow(int index, FilterSetup f){
        data_.add(index, f);
        fireTableRowsInserted(data_.size() - 1, data_.size() - 1);
//        sap_.setDelaysArray(0, data_);
    }
    
    public void removeRow(int row) {
        data_.remove(row);
        fireTableRowsDeleted(row, row);
//        sap_.setDelaysArray(0, data_);
    }
    
    public void addWholeData(ArrayList<FilterSetup> data){
        data_.clear();
        data_.addAll(data);
        fireTableDataChanged();
    }
    
    public void clearAllData(){
        data_.clear();
        fireTableDataChanged();
    }
    
    @Override
    public boolean isCellEditable(int row, int col){
        return true;
    }
    
        
}


