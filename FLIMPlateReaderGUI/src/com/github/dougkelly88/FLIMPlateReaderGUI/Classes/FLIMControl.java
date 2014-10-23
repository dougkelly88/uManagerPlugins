/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.github.dougkelly88.FLIMPlateReaderGUI.Classes;

import mmcorej.CMMCore;
import com.github.dougkelly88.FLIMPlateReaderGUI.GUIComponents.FrontPanelFrame;

/**
 *
 * @author dk1109
 */
public class FLIMControl {
    private CMMCore core_;
    private FrontPanelFrame fp_;
    
    /**
     * Constructor
     */
    public FLIMControl(FrontPanelFrame fp) {
//        try{
//        fp_ = fp;
//        core_ = fp_.getCMMCore();
//        core_.clearROI();
//        }
//        catch (Exception e)
//        {
//            e.getMessage();
//        }
    }
    
    public int test()
    {
        String a = "Dummy";
        try{a = (core_.getProperty("Dichroic","Label"));}
        catch (Exception e)
        {
            a = e.getMessage();
        }
        
        return -50;
        
    }
}
