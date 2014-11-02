/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.github.dougkelly88.FLIMPlateReaderGUI.Classes;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
//
///**
// *
// * @author dk1109
// */
//public class DelayTableModel extends AbstractTableModel {
//
//    public String[] colNames_ = { "Delays (ps)" };
//    public Class[] colTypes = { Integer.class };
//    Vector delays_;
//   
//    public static final int DELAY_INDEX = 0;
//
//    public DelayTableModel(String[] columnNames) {
//         this.colNames_ = columnNames;
//         delays_ = new Vector();
//     }
//    public int getColumnCount() {
//      return colNames_.length;
//    }
//    public int getRowCount() {
//      return delays_.size();
//    }
//     public void setValueAt(Object value, int row, int column) {
//         Data data_ = (Data)delays_.get(row);
//         switch (column) {
//             case DELAY_INDEX:
//                data_.setDelay((Integer)value);
//                break;
////             case ARTIST_INDEX:
////                data_.setDelayrtist((String)value);
////                break;
////             case ALBUM_INDEX:
////                data_.setDelaylbum((String)value);
////                break;
//             default:
//                System.out.println("invalid index");
//         }
//         fireTableCellUpdated(row, column);
//     }
//
//    public String getColumnName(int col) {
//      return colNames_[col];
//    }
//
//    public Class getColumnClass(int col) {
//      return colTypes[col];
//    }
//    public Object getValueAt(int row, int col) {
//      Data macData = (Data) (delays_.elementAt(row));
//
//      switch (col) {
//      case 0:
//        return macData.getDelay();
//
//      }
//
//      return new String();
//    }
//    
//    public boolean hasEmptyRow() {
//         if (delays_.size() == 0) return false;
//         Data data_ = (Data)delays_.get(delays_.size() - 1);
//         if (data_.getDelay().equals(""))
//            return true;
//         
//         else return false;
//     }
//
//     public void addEmptyRow() {
//         delays_.add(new Data());
//         fireTableRowsInserted(
//            delays_.size() - 1,
//            delays_.size() - 1);
//     }
//     
//     public void populateFromVector(Vector delayArray){
//        delays_ = (Vector) delayArray;
//        int row = delayArray.size();
//        int column = 0;
//        fireTableCellUpdated(row, column);
////        for (int i = 0; i < delayArray.size(); i++)
////        {
////            
////        }
//     }
//  }
//
//
//
//class Data {
//  private Integer a;
//
//  public Data() {
//  }
//
//  public Data(Integer aa) {
//    a = aa;
//
//  }
//
//  public Integer getDelay() {
//    return a;
//  }
//
//  public void setDelay(Integer aa) {
//    a = aa;
//  }
//
//}
//
//
public class DelayTableModel extends AbstractTableModel {
    private List data_ = new ArrayList<Integer>();
    private String[] colNames_ = { "Delays (ps)" };
    
    public DelayTableModel(){}
    
    public DelayTableModel(String[] columnNames) {
         this.colNames_ = columnNames;
    }
    
    public DelayTableModel(String[] columnNames, ArrayList<Integer> delays){
        this.colNames_ = columnNames;
        this.data_ = delays;
        fireTableDataChanged();
    }
 
    public String getColumnName(int col) {
      return colNames_[col];
    }
    
    public Object getValueAt(int row, int col) {
        return ((Integer) data_.get(row));
    }
    public int getColumnCount() {
        return 1;
    }
    public int getRowCount() {
        return data_.size();
    }
 
    public void addRow(Integer rowData) {
        int row = data_.size();
        data_.add(rowData);
        fireTableRowsInserted(row, row);
    }
    
    public void addWholeData(ArrayList<Integer> data){
        data_.clear();
        data_.addAll(data);
        fireTableDataChanged();
        this.addEmptyRow();
    }
    
    public boolean hasEmptyRow() {
         if (data_.size() == 0) return false;
         Integer d = ((Integer) data_.get(data_.size() - 1));
         if (d.equals(""))
            return true;
         
         else return false;
     }
    
    public void addEmptyRow() {
         data_.add(null);
         fireTableRowsInserted(
            data_.size() - 1,
            data_.size() - 1);
     }
}

