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
    private int exPos_ = 1;
    private int ndPos_ = 1;
    private int diPos_ = 1;
    private int emPos_ = 1;    
    private int intTime_ = 100;
    private ArrayList<Integer> delays_;
    
    public FilterSetup(int ex, int nd, int di, int em, int intTime, ArrayList<Integer> delays){
        exPos_ = ex;
        ndPos_ = nd;
        diPos_ = di;
        emPos_ = em;
        intTime_ = intTime;
        delays_ = delays;
    }
    
    public FilterSetup getFilterSetup(){
        return this;
    }
    
    public int getExPos() {
        return exPos_;
    }

    public void setExPos(int exPos) {
        this.exPos_ = exPos;
    }

    public int getDiPos() {
        return diPos_;
    }

    public void setDiPos(int diPos) {
        this.diPos_ = diPos;
    }

    public int getEmPos() {
        return emPos_;
    }

    public void setEmPos(int emPos) {
        this.emPos_ = emPos;
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
    
   public int getNDPos(){
       return ndPos_;
   }
   
   public void setNDPos(int ndPos){
       this.ndPos_ = ndPos;
   }
    
}
