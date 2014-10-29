/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.github.dougkelly88.FLIMPlateReaderGUI.Classes;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 *
 * @author dk1109
 */
public class SequencedAcquisitionProperties {
    
    // We use a builder pattern rather than a standard constructor to allow 
    // default parameters to be decided at development time, but initialisation
    // parameters to be set at runtime. 
    // http://stackoverflow.com/questions/7428039/java-constructor-method-with-optional-parameters
    
    
    private boolean useScanFLIM;
    private boolean powerMonitoring;
    private PropertyChangeSupport changes = new PropertyChangeSupport(this);
    
    private SequencedAcquisitionProperties(Builder builder){
        this.useScanFLIM = builder.useScanFLIM;
        this.powerMonitoring = builder.powerMonitoring;
    }
    
    public static class Builder {
        
        private boolean useScanFLIM = false;
        private boolean powerMonitoring = false;
        
        public Builder useScanFLIM(boolean useScanFLIM){
            this.useScanFLIM = useScanFLIM;
            return this;
        }
        
        public Builder powerMonitoring(boolean powerMonitoring){
            this.powerMonitoring = powerMonitoring;
            return this;
        }
             
        public SequencedAcquisitionProperties build() {
            return new SequencedAcquisitionProperties(this);
        }
        
    }
    
    // Getters
    public boolean getPowerMonitoring(){
        return powerMonitoring;
    }
    
    public boolean getUseScanFLIM(){
        return useScanFLIM;
    }
    
    
    // Setters
    public void setPowerMonitoring(boolean pm){
        changes.firePropertyChange("PowerMonitoring", powerMonitoring, pm);
        powerMonitoring = pm;
    }
    
    public void setUseScanFLIM(boolean sf){
        // CHECK INSTRUMENT PARAMETERS WHETHER SCAN FLIM IS POSSIBLE!
        changes.firePropertyChange("UseScanFLIM", useScanFLIM, sf);
        useScanFLIM = sf;
    }
           
    public void addPropertyChangeListener(PropertyChangeListener l) {
        changes.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        changes.removePropertyChangeListener(l);
    }
    
}
