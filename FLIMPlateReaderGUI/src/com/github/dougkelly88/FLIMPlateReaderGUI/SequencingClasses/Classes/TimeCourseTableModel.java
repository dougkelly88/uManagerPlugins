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
public class TimeCourseTableModel extends AbstractTableModel {
   public static final int Time_INDEX = 0;
   public static final int LD_INDEX = 1;
   public static final int SA_INDEX = 2;
 
   
   private ArrayList<TimeSetup> data_ = new ArrayList<TimeSetup>();
   private String[] colNames_ = { "Time", "Liquid Dispension", "Sound Alert"};
   
   public TimeCourseTableModel(String[] columnNames) {
         this.colNames_ = columnNames;
    }
   
   public TimeCourseTableModel(String[] columnNames, TimeSetup filts){
       this.colNames_ = columnNames;
       this.data_.add(filts);
   }
   
   public TimeCourseTableModel(TimeSetup filts){
       this.data_.add(filts);
   }

    @Override
    public int getRowCount() {
        return data_.size();
    }

    @Override
    public String getColumnName(int col) {
      return colNames_[col];
    }
    
    @Override
    public int getColumnCount() {
        return 3;
    }
    
    @Override
    public void setValueAt(Object value, int row, int col) {
        TimeSetup f = data_.get(row);
        switch (col){
            
            case Time_INDEX:
                f.setTimeCell(value.toString());
                break;
            case LD_INDEX:
                boolean bo=(Boolean) value;
                f.setLDState(bo);
                break;
            case SA_INDEX:
                boolean boo=(Boolean) value;
                f.setLDState(boo);
                break;

            default: 
                break;
        }
               
           data_.set(row, f);
           fireTableCellUpdated(row, col);
        
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        
        TimeSetup f = data_.get(rowIndex);
        switch (columnIndex){
            case Time_INDEX:
                return f.getTimeCell();
            case LD_INDEX:
                return f.getLDState();
            case SA_INDEX:
                return f.getSAState();
            default: 
                return f;
        }
    }

    @Override
    public Class getColumnClass(int column) {
         switch (column) {
             case Time_INDEX:
             case LD_INDEX:
                 return Boolean.class;
             case SA_INDEX:
                 return Boolean.class;
             default:
                return TimeSetup.class;
         }
     }
    
    public ArrayList<TimeSetup> getData(){
        return data_;
    }
    
    public void addRow(TimeSetup f) {
        int row = data_.size();
        data_.add(f);
        fireTableRowsInserted(row, row);
    }
   
    public void insertRow(int index, TimeSetup f){
        data_.add(index, f);
//        fireTableRowsInserted(data_.size() - 1, data_.size() - 1);
        fireTableRowsInserted(data_.size(), data_.size());
    }
    
    public void removeRow(int row) {
        data_.remove(row);
        fireTableRowsDeleted(row, row);
    }
    
    public void addWholeData(ArrayList<TimeSetup> data){
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
