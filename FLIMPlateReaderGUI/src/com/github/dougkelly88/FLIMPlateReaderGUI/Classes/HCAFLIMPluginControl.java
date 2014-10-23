/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.github.dougkelly88.FLIMPlateReaderGUI.Classes;

import mmcorej.CMMCore;
import org.micromanager.acquisition.AcquisitionWrapperEngine;

import com.github.dougkelly88.FLIMPlateReaderGUI.GUIComponents.HCAFLIMPluginFrame;
import javax.swing.JFrame;
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
    
}
