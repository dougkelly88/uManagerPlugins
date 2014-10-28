/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.github.dougkelly88.FLIMPlateReaderGUI.Classes;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import mmcorej.CMMCore;

/**
 *
 * @author dk1109
 */
public class sliderListener implements PropertyChangeListener {

    CMMCore core_; 
    
    public sliderListener(CMMCore core) {
        core_ = core;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Object source = evt.getSource();
//        try {
//            core_.setProperty("","","");
//        } catch (Exception e) {
//            Logger.getLogger(sliderListener.class.getName()).log(Level.SEVERE, null, e);
//        }
        core_.sleep(100);
    }
    
}
