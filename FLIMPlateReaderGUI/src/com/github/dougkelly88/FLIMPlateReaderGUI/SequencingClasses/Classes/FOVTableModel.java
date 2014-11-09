/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.github.dougkelly88.FLIMPlateReaderGUI.SequencingClasses.Classes;

import com.github.dougkelly88.FLIMPlateReaderGUI.GeneralClasses.PlateProperties;
import com.github.dougkelly88.FLIMPlateReaderGUI.SequencingClasses.Classes.*;
import static java.lang.Math.round;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;


/**
 *
 * @author dk1109
 */

public class FOVTableModel extends AbstractTableModel {
   public static final int WELL_INDEX = 0;
   public static final int X_INDEX = 1;
   public static final int Y_INDEX = 2;
   public static final int Z_INDEX = 3;
    
    private ArrayList<FOV> data_ = new ArrayList<FOV>();
    private String[] colNames_ = { "Well", "X", "Y", "Z" };
    
    private PlateProperties pp_;

//    private SeqAcqProps sap_;
    
    public FOVTableModel(PlateProperties pp){
        this.pp_ = pp;
    }
    
    public FOVTableModel(String[] columnNames, PlateProperties pp) {
         this.colNames_ = columnNames;
         this.pp_ = pp;
    }
    
    @Override
    public String getColumnName(int col) {
      return colNames_[col];
    }
    
    @Override
    public Object getValueAt(int row, int col) {
        FOV fov = data_.get(row);
        switch (col){
            case WELL_INDEX:
                return fov.getWell();
            case X_INDEX:
                return fov.getX();
            case Y_INDEX:
                return fov.getY();
            case Z_INDEX:
                return fov.getZ();
            default: 
                return fov;
        }
    }
    
   @Override
    public Class getColumnClass(int column) {
         switch (column) {
             case WELL_INDEX:
                 return String.class;
             case X_INDEX:
             case Y_INDEX:
             case Z_INDEX:
                return Double.class;
             default:
                return FOV.class;
         }
     }
    
    @Override
    public int getColumnCount() {
        return 4;
    }
    
    @Override
    public int getRowCount() {
        return data_.size();
    }
    
    public ArrayList<FOV> getData(){
        return data_;
    }
 
    public void addRow(FOV fov) {
        int row = data_.size();
        fov = validateData(fov);
        data_.add(fov);
        fireTableRowsInserted(row, row);
//        sap_.setDelaysArray(0, data_);
    }
    
    public void insertRow(int index, FOV fov){
        fov = validateData(fov);
        data_.add(index, fov);
        fireTableRowsInserted(data_.size() - 1, data_.size() - 1);
//        sap_.setDelaysArray(0, data_);
    }
    
    public void removeRow(int row) {
        data_.remove(row);
        fireTableRowsDeleted(row, row);
//        sap_.setDelaysArray(0, data_);
    }
    
    public void addWholeData(ArrayList<FOV> data){
        data_.clear();
        
        data_.addAll(data);
        
        fireTableDataChanged();
//        sap_.setDelaysArray(0, data_);
//        this.addEmptyRow();
    }
    
    public void clearAllData(){
        data_.clear();
        fireTableDataChanged();
//        sap_.setDelaysArray(0, data_);
    }
    
    @Override
    public boolean isCellEditable(int row, int col){
        switch (col){
            case WELL_INDEX:
                return false;
            case X_INDEX:
            case Y_INDEX:
            case Z_INDEX:
                return true;
            default: 
                return true;
        }
    }
    
    @Override
    public void setValueAt(Object value, int row, int column) {
         FOV fov = (FOV) data_.get(row);
         switch (column) {
            case WELL_INDEX:
                fov.setWell((String)value);
                break;
            case X_INDEX:
                value = validateX((Double) value);
                fov.setX((Double)value);
                break;
            case Y_INDEX:
                value = validateY((Double) value);
                fov.setY((Double)value);
                break;
            case Z_INDEX:
                fov.setZ((Double)value);
                break;
            default:
                System.out.println("invalid index");
         }
         fireTableCellUpdated(row, column);
     }
    
    public void setValueAtRow(FOV fov, int row){
        fov = validateData(fov);
        data_.set(row, fov);
    }

    private FOV validateData(FOV fov){
        double xmin = pp_.getTopLeftWellOffsetH() - pp_.getWellSpacingH()/2;
        double xmax = pp_.getTopLeftWellOffsetH() + 
                (pp_.getPlateColumns() - 0.5) * pp_.getWellSpacingH();
        double ymin = pp_.getTopLeftWellOffsetV() - pp_.getWellSpacingV()/2;
        double ymax = pp_.getTopLeftWellOffsetV() + 
                (pp_.getPlateRows() - 0.5) * pp_.getWellSpacingV();
                
        if (fov.x_ < xmin)
            fov.x_ = xmin;
        if (fov.x_ > xmax)
            fov.x_ = xmax;
        if (fov.y_ < ymin)
            fov.y_ = ymin;
        if (fov.y_ < ymax)
            fov.y_ = ymax;      
        
        return fov;
    }
    
    private double validateX(double x){
        
        double xmin = pp_.getTopLeftWellOffsetH() - pp_.getWellSpacingH()/2;
        double xmax = pp_.getTopLeftWellOffsetH() + 
                (pp_.getPlateColumns() - 0.5) * pp_.getWellSpacingH();
        
        if (x < xmin)
            x = xmin;
        if (x > xmax)
            x = xmax;
        
        return x;
    }
    
    private double validateY(double y){
        
        double ymin = pp_.getTopLeftWellOffsetV() - pp_.getWellSpacingV()/2;
        double ymax = pp_.getTopLeftWellOffsetV() + 
                (pp_.getPlateRows() - 0.5) * pp_.getWellSpacingV();
        
        if (y < ymin)
            y = ymin;
        if (y > ymax)
            y = ymax;
        
        return y;
    }
    
    public void setPlateProps(PlateProperties pp){
        pp_ = pp;
    }
    
}

