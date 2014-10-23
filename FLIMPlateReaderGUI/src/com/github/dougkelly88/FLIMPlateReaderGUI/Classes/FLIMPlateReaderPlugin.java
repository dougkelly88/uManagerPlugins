/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.github.dougkelly88.FLIMPlateReaderGUI.Classes;

import javax.swing.JFrame;
import mmcorej.CMMCore;
import org.micromanager.MMStudio;
import org.micromanager.acquisition.AcquisitionEngine;
import org.micromanager.api.ScriptInterface;

/**
 *
 * @author dk1109
 */
public class FLIMPlateReaderPlugin implements org.micromanager.api.MMPlugin {
    public static String menuName = "FLIMPlateReader";
   public static String tooltipDescription = "Plugin allowing control of an OpenFLIM-HCA plate reader";
   private CMMCore core_;
   private MMStudio gui_;
   static ScriptInterface si_;
   private AcquisitionEngine acq_;
   public static JFrame frame;
   FrontPanelControl fpc_;

    @Override
    public void dispose() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setApp(ScriptInterface app) {
      gui_ = (MMStudio) app;
      core_ = app.getMMCore();
      acq_ = gui_.getAcquisitionEngine();
      si_ = app;
      fpc_ = new FrontPanelControl(core_);
    }

    @Override
    public void show() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getDescription() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getInfo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getVersion() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getCopyright() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public static CMMCore getCMMCore()
    {
        // for debug
        CMMCore a;
        a = si_.getMMCore();
        return a;
//return gui_.getMMCore();
    }
}
