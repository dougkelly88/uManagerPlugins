/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.dougkelly88.FLIMPlateReaderGUI.SequencingClasses.Classes;

/**
 *
 * @author Frederik
 */
//TODO: validate positions based on available

public class TimeSetup {
    private double time_ = 10;
    private double ld_ = 100;
    private boolean sa_ = false;
    
    public TimeSetup(double time, double ld, boolean sa){
        time_ = time;
        ld_ = ld;
        sa_ = sa;
   
    }
    
    public TimeSetup getTimeSetup(){
        return this;
    }
    
    public double getTimeCell() {
        return time_;   
    }

    public void setTimeCell(double time) {
        this.time_ = time;
    }

    public double getLDVolume() {
        return ld_;
    }

    public void setLDVolume(double LDState) {
        this.ld_ = LDState;
    }

    public boolean getSAState() {
        return sa_;
    }

    public void setSAState(boolean SAState) {
        this.sa_ = SAState;
    }

}
