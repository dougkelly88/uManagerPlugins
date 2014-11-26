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
    public static final int GROUP_INDEX = 4;

    final static String um = "(" + "\u00B5" + "m)";

    private ArrayList<FOV> data_ = new ArrayList<FOV>();
    private String[] colNames_ = {"Well", "X " + um, "Y" + um, "Z" + um, "Group"};

    private PlateProperties pp_;

//    private SeqAcqProps sap_;
    //TODO: remove duplicates!
    public FOVTableModel(PlateProperties pp) {
        pp_ = pp;
    }

    public FOVTableModel(String[] columnNames, PlateProperties pp) {
        this.colNames_ = columnNames;
        pp_ = pp;
    }

    @Override
    public String getColumnName(int col) {
        return colNames_[col];
    }

    @Override
    public Object getValueAt(int row, int col) {
        FOV fov = data_.get(row);
        switch (col) {
            case WELL_INDEX:
                return fov.getWell();
            case X_INDEX:
                return fov.getX();
            case Y_INDEX:
                return fov.getY();
            case Z_INDEX:
                return fov.getZ();
            case GROUP_INDEX:
                return fov.getGroup();
            default:
                return fov;
        }
    }

    @Override
    public Class getColumnClass(int column) {
        switch (column) {
            case WELL_INDEX:
            case GROUP_INDEX:
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
        return 5;
    }

    @Override
    public int getRowCount() {
        return data_.size();
    }

    public ArrayList<FOV> getData() {
        return data_;
    }

    public void addRow(FOV fov) {
        int row = data_.size();
        fov = validateData(fov);
        if (!data_.contains(fov)) {
            data_.add(fov);
            fireTableRowsInserted(row, row);
        }

//        sap_.setDelaysArray(0, data_);
    }

    public void insertRow(int index, FOV fov) {
        fov = validateData(fov);
        if (!data_.contains(fov)) {
            data_.add(index, fov);
            fireTableRowsInserted(data_.size() - 1, data_.size() - 1);
        }
//        sap_.setDelaysArray(0, data_);
    }

    public void removeRow(int row) {
        data_.remove(row);
        fireTableRowsDeleted(row, row);
//        sap_.setDelaysArray(0, data_);
    }

    public void addWholeData(ArrayList<FOV> data) {
        data_.clear();

        data_.addAll(data);

        fireTableDataChanged();
//        sap_.setDelaysArray(0, data_);
//        this.addEmptyRow();
    }

    public void clearAllData() {
        data_.clear();
        fireTableDataChanged();
//        sap_.setDelaysArray(0, data_);
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        switch (col) {
            case WELL_INDEX:
                return false;
            case X_INDEX:
            case Y_INDEX:
            case Z_INDEX:
            case GROUP_INDEX:
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
                fov.setWell((String) value);
                break;
            case X_INDEX:
                value = validateX((Double) value, fov.getPlateProps());
                fov.setX((Double) value);
                break;
            case Y_INDEX:
                value = validateY((Double) value, fov.getPlateProps());
                fov.setY((Double) value);
                break;
            case Z_INDEX:
                fov.setZ((Double) value);
                break;
            case GROUP_INDEX:
                fov.setGroup((String) value);
                break;
            default:
                System.out.println("invalid index");
        }
        fireTableCellUpdated(row, column);
    }

    public void setValueAtRow(FOV fov, int row) {
        fov = validateData(fov);
        data_.set(row, fov);
    }

    private FOV validateData(FOV fov) {
        PlateProperties pp = fov.getPlateProps();
        double xmin = pp.getTopLeftWellOffsetH() - pp.getWellSpacingH() / 2;
        double xmax = pp.getTopLeftWellOffsetH()
                + (pp.getPlateColumns() - 0.5) * pp.getWellSpacingH();
        double ymin = pp.getTopLeftWellOffsetV() - pp.getWellSpacingV() / 2;
        double ymax = pp.getTopLeftWellOffsetV()
                + (pp.getPlateRows() - 0.5) * pp.getWellSpacingV();

        if (fov.getX() < xmin) {
            fov.setX(xmin);
        }
        if (fov.getX() > xmax) {
            fov.setX(xmax);
        }
        if (fov.getY() < ymin) {
            fov.setY(ymin);
        }
        if (fov.getY() > ymax) {
            fov.setY(ymax);
        }

        return fov;
    }

    private double validateX(double x, PlateProperties pp) {

        double xmin = pp.getTopLeftWellOffsetH() - pp.getWellSpacingH() / 2;
        double xmax = pp.getTopLeftWellOffsetH()
                + (pp.getPlateColumns() - 0.5) * pp.getWellSpacingH();

        if (x < xmin) {
            x = xmin;
        }
        if (x > xmax) {
            x = xmax;
        }

        return x;
    }

    private double validateY(double y, PlateProperties pp) {

        double ymin = pp.getTopLeftWellOffsetV() - pp.getWellSpacingV() / 2;
        double ymax = pp.getTopLeftWellOffsetV()
                + (pp.getPlateRows() - 0.5) * pp.getWellSpacingV();

        if (y < ymin) {
            y = ymin;
        }
        if (y > ymax) {
            y = ymax;
        }

        return y;
    }

    public void setPlateProps(PlateProperties pp) {
        pp_ = pp;
    }

}
