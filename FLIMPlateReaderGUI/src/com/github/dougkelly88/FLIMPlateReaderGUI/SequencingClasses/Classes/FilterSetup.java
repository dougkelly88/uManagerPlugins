/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.dougkelly88.FLIMPlateReaderGUI.SequencingClasses.Classes;

import java.util.ArrayList;

/**
 *
 * @author dk1109
 */
//TODO: validate positions based on available

public class FilterSetup {
    private String label_ = "GFP";
    private String exFilt_ = "465/30";
    private String ndFilt_ = "ND 1.0";
    private String diFilt_ = "473/561";
    private String emFilt_ = "525/35";    
    private int intTime_ = 100;
    private ArrayList<Integer> delays_;
    
    public FilterSetup(String label, String ex, String nd, String di, String em,
            int intTime, ArrayList<Integer> delays){
        label_ = label;
        exFilt_ = ex;
        ndFilt_ = nd;
        diFilt_ = di;
        emFilt_ = em;
        intTime_ = intTime;
        delays_ = delays;
    }
    
    public FilterSetup getFilterSetup(){
        return this;
    }
    
    public String getExFilt() {
        return exFilt_;
    }

    public void setExFilt(String exFilt) {
        this.exFilt_ = exFilt;
    }

    public String getDiFilt() {
        return diFilt_;
    }

    public void setDiFilt(String diFilt) {
        this.diFilt_ = diFilt;
    }

    public String getEmFilt() {
        return emFilt_;
    }

    public void setEmFilt(String emFilt) {
        this.emFilt_ = emFilt;
    }

    public int getIntTime() {
        return intTime_;
    }

    public void setIntTime(int intTime) {
        this.intTime_ = intTime;
    }

    public ArrayList<Integer> getDelays() {
        return delays_;
    }

    public void setDelays(ArrayList<Integer> delays) {
        this.delays_ = delays;
    }
    
   public String getNDFilt(){
       return ndFilt_;
   }
   
   public void setNDFilt(String ndFilt){
       this.ndFilt_ = ndFilt;
   }
   
   public String getLabel(){
       return label_;
   }
   
   public void setLabel(String label){
       this.label_ = label;
   }
   
    @Override
   public String toString(){
       return "Filters: Label = " + this.label_ + ", ND = " + this.ndFilt_ 
                + ", Excitation = " + this.exFilt_ + ", Emission = " + this.emFilt_
                + ", Camera integration (ms) = " + this.intTime_ 
                + ", Delays (ps) = " + this.delays_.toString();
   }
    
}
