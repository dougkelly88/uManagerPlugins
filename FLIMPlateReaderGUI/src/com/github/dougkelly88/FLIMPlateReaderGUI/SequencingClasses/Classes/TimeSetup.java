/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.dougkelly88.FLIMPlateReaderGUI.SequencingClasses.Classes;

import java.util.ArrayList;

/**
 *
 * @author Frederik
 */
//TODO: validate positions based on available

public class TimeSetup {
    private String time_ = "10s";
    private boolean ld_ = false;
    private boolean sa_ = false;
    
    public TimeSetup(String time, boolean ld, boolean sa){
        time_ = time;
        ld_ = ld;
        sa_ = sa;
   
    }
    
    public TimeSetup getTimeSetup(){
        return this;
    }
    
    public String getTimeCell() {
        return time_;
    }

    public void setTimeCell(String time) {
        this.time_ = time;
    }

    public boolean getLDState() {
        return ld_;
    }

    public void setLDState(boolean LDState) {
        this.ld_ = LDState;
    }

    public boolean getSAState() {
        return sa_;
    }

    public void setSAState(boolean SAState) {
        this.sa_ = SAState;
    }

}
