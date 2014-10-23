/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.github.dougkelly88.FLIMPlateReaderGUI.Classes;

import com.github.dougkelly88.FLIMPlateReaderGUI.GUIComponents.FrontPanelFrame;
import javax.swing.JFrame;
import mmcorej.CMMCore;
import org.micromanager.utils.GUIUtils;

/**
 *
 * @author dk1109
 */
public class FrontPanelControl {
    CMMCore core_;
    public FrontPanelFrame fpFrame_;
    
    public FrontPanelControl(CMMCore core){
        core_ = core;
//        GUIUtils.registerImageFocusListener(this);
    }
    
    public void updateCore()
    {
        core_ = FLIMPlateReaderPlugin.getCMMCore();
    }
    
    public JFrame getFPFrame()
    {
        if (fpFrame_ == null)
        {
            fpFrame_ = new FrontPanelFrame(this);
        }
        return fpFrame_;
    }
}
