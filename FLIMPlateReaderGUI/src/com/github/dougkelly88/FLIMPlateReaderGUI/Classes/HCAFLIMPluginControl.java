/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.github.dougkelly88.FLIMPlateReaderGUI.Classes;

import com.github.dougkelly88.FLIMPlateReaderGUI.GUIComponents.HCAFLIMPluginFrame;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import mmcorej.CMMCore;
import org.micromanager.acquisition.AcquisitionWrapperEngine;
import org.micromanager.utils.GUIUtils;

/**
 *
 * @author dk1109
 */
public class HCAFLIMPluginControl {
    CMMCore core_;    
    AcquisitionWrapperEngine engineWrapper_;
    
    public HCAFLIMPluginFrame frame_;
    
    public HCAFLIMPluginControl(AcquisitionWrapperEngine engineWrapper, CMMCore core) 
    {
        engineWrapper_ = engineWrapper;
        core_ = core; 
    }
    
    public JFrame getFrame() 
    {
        if (frame_ == null) {
            frame_ = new HCAFLIMPluginFrame(this);
        }
        return frame_;
    }
    
    public String test(String dev, String prop)
    {
        String out;
        try{out = core_.getProperty(dev, prop);}
        catch (Exception e){out = "Error:" + e.getMessage();}
        return out;
    }
    

}
